package it.mcprisa.autonoleggio.sicurezza;

import it.mcprisa.autonoleggio.repository.RepUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/user/*").hasRole("CLIENTE")
                .antMatchers("/admin/*").hasRole("AMMINISTRATORE")
                .antMatchers("/impiegato/*").hasRole("IMPIEGATO")
                .antMatchers("/public/*").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
