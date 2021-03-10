package it.mcprisa.autonoleggio.sicurezza;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.mcprisa.autonoleggio.model.Utente;
import it.mcprisa.autonoleggio.repository.RepUtente;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private RepUtente userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, RepUtente userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Memorizzo l'URI in un nuovo attributo della request, dato che se qualcosa fallisce spring reindirizza ad "/error" e lancia lo status 403 FORBIDDEN e viene persa la URI iniziale
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).setAttribute(
                "originalUri",
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest().getRequestURI(),
                0
        );

        // Legge il header "Authorization", dove dovrebbe trovarsi il jwt
        String header = request.getHeader(JwtUtil.HEADER_STRING);

        // se il header non contiene BEARER o è nullo continua la chain ed esce dal metodo
        if (header == null || !header.startsWith(JwtUtil.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // se il header è presente, prova ottenere i dettagli dell'utente dal database e lo autentica
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // continua l'esecuzione del filtro
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtUtil.HEADER_STRING)
                .replace(JwtUtil.TOKEN_PREFIX, "");

        if (token != null) {
            // analizza e valida il token e restituisce lo username del proprietario
            String userName = JwtUtil.getSubject(token);

            // cerco ne DB se trovo il subject (username) del token
            // se si, prendo l'user in questione e lo autentico col suo username e le sue authorities/roles
            if (userName != null) {
                Utente utente = userRepository.findByUsername(userName);
                ApplicationUser principal = new ApplicationUser(utente);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, principal.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;
    }
}
