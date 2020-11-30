package it.mcprisa.autonoleggio.sicurezza;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /* si trigghera con richieste POST a /login
    nel body della richiesta dovremmo altresì inserire {"username":"user", "password":"passw"}
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //Memorizzo l'URI in un nuovo attributo della request, dato che se qualcosa fallisce spring reindirizza ad "/error" e lancia lo status 403 FORBIDDEN e viene persa la URI iniziale
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).setAttribute(
                "originalUri",
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest().getRequestURI(),
                0
        );
        //se il metodo usato per triggerare il login non è un post viene lanciata un'eccezione e salta tutto
        if (!request.getMethod().equals("POST")) {
            //aggiunge il fatto che il problema è dovuto al fatto che non è stata fatta una richiesta POST a "/login"
            ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).setAttribute("metodoNonAmmesso", "", 0);
            throw new AuthenticationServiceException("");
        }
        // prende le credenziali e le mappa
        UsernameAndPasswordAuthenticationRequest credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crea il token del login
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                new ArrayList<>());

        // Autentica l'utente
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // prendo i dati utente dopo l'autenticazione
        ApplicationUser applicationUser = (ApplicationUser) authResult.getPrincipal();

        // Crea il Token JWT
        String token = JwtUtil.creaToken(applicationUser);

        // aggiungo il token al header dell'http response
        response.addHeader(JwtUtil.HEADER_STRING, JwtUtil.TOKEN_PREFIX + token);
    }
}
