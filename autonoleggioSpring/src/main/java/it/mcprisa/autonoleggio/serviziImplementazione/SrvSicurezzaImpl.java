package it.mcprisa.autonoleggio.serviziImplementazione;

import it.mcprisa.autonoleggio.eccezioni.CattivaRichiestaException;
import it.mcprisa.autonoleggio.servizi.SrvSicurezza;
import it.mcprisa.autonoleggio.sicurezza.JwtUtil;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class SrvSicurezzaImpl implements SrvSicurezza {

    @Override
    public void storeToken(String token, HttpServletResponse response) {
        //gets the expiration time of the token in order to set expiration of the cookie
        Date expirationTime = JwtUtil.getExpirationTime(token);
        long span = expirationTime.getTime() - System.currentTimeMillis();

        // create a cookie containing the jwt
        Cookie authorizationCookie = new Cookie(JwtUtil.HEADER_STRING, JwtUtil.TOKEN_PREFIX + token);
        authorizationCookie.setMaxAge((int) span / 1000);
        authorizationCookie.setPath("/token");
        authorizationCookie.setHttpOnly(true);      //for security reason set http only
        //adds cookie to http response
        response.addCookie(authorizationCookie);
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();        //get all cookies
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(JwtUtil.HEADER_STRING)) {       //null pointer exception is thrown if the cookie is not present
                    token = cookie.getValue().replace(JwtUtil.TOKEN_PREFIX, "");
                }
            }
            //if there is no such cookie Bad request exception is thrown
        } catch (NullPointerException e) {
            throw new CattivaRichiestaException("non c'è nessun cookie da ottenere.");
        }
        return token;
    }

    @Override
    //token will be overwritten with one with immediate expiration in order to delete the stored one (if there is one)
    public void removeToken(HttpServletResponse response) {
        // create a cookie containing the jwt
        Cookie authorizationCookie = new Cookie(JwtUtil.HEADER_STRING, "");
        authorizationCookie.setMaxAge(0);
        authorizationCookie.setPath("/token");
        authorizationCookie.setHttpOnly(true);      //for copy-paste reason set http only
        //adds cookie to http response
        response.addCookie(authorizationCookie);
    }

}
