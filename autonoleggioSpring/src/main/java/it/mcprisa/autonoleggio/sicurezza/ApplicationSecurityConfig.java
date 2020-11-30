package it.mcprisa.autonoleggio.sicurezza;

import it.mcprisa.autonoleggio.dto.ErrorePersonalizzatoDto;
import it.mcprisa.autonoleggio.repository.RepUtente;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ApplicationUserService userDetailsService;
    @Autowired
    RepUtente repUtente;

    @Override
    //l'autenticazione avverrà tramite database
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Questo filtro scatterà ogni volta che viene triggherato il deadend /login
        JwtAuthenticationFilter customFilter = new JwtAuthenticationFilter(authenticationManager());
        customFilter.setFilterProcessesUrl("/login");
        customFilter.setPostOnly(true);

        http
                // rimuovo il csfr e lo stato nella sessione poerché non mi servono
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // aggiungo i filtri jwt (1. authentication, 2. authorization)
                .addFilter(customFilter)
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.repUtente))
                .authorizeRequests()
                // configuro le regole di accesso alle api
                //.antMatchers(HttpMethod.POST, "/login").permitAll()
                //.antMatchers("/admin/*").authenticated()
                .antMatchers("/**/public/**").permitAll()
                .antMatchers("/**/user/**").hasRole("CLIENTE")
                .antMatchers("/**/admin/**").hasRole("AMMINISTRATORE")
                .antMatchers("/**/impiegato/**").hasRole("IMPIEGATO")
                
                .anyRequest().authenticated();
        /*.and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint());*/

        //Exception handling configuration
        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e)
                        -> {

                    //se la causa del fallimento dell'autenticazione è dovuta al fatto che è stata fatta una richiesta non POST a "/login" ritorno un erroe con status METHOD_NOT_ALLOWED
                    if (!(request.getAttribute("metodoNonAmmesso") == null)) {
                        ErrorePersonalizzatoDto errorePersonalizzatoDto = new ErrorePersonalizzatoDto(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), "metodo non ammesso, provare POST.");
                        errorePersonalizzatoDto.setPath(request.getAttribute("originalUri").toString());
                        response.setContentType("application/json;charset=UTF-8");
                        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                        response.getWriter().write(errorePersonalizzatoDto
                                .toString());
                    } else {
                        ErrorePersonalizzatoDto errorePersonalizzatoDto = new ErrorePersonalizzatoDto(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), "Questo messaggio non deve essere mostrato!");
                        errorePersonalizzatoDto.setPath(request.getAttribute("originalUri").toString());
                        if(request.getAttribute("originalUri").toString().equals("/login")){
                            errorePersonalizzatoDto.setMessage("credenziali non valide.");
                        }else{
                            errorePersonalizzatoDto.setMessage("token non valido o scaduto.");
                        }
                        response.setContentType("application/json;charset=UTF-8");
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.getWriter().write(errorePersonalizzatoDto
                                .toString());
                    }
                });
    }

    /*@Bean
    public PersonalizazioneAuthenticationEntryPoint authenticationEntryPoint(){
        PersonalizazioneAuthenticationEntryPoint entryPoint = 
          new PersonalizazioneAuthenticationEntryPoint();
        return entryPoint;
    }*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
