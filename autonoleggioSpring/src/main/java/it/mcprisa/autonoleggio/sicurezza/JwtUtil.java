package it.mcprisa.autonoleggio.sicurezza;

import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import java.util.Date;
import org.springframework.http.HttpStatus;

/**
 * Qui ci metto gli attributi del token e i metodi per la gestione del token
 */
public class JwtUtil {

    public static final String SECRET = "zS7Wm3X@*U3eM&tNMrD5@nsRqKoYrsb#^9m!HuuMdhwM$oKRhEWcMm!8DGRAAwXs3GnTc3ghGd*dmVB@N3&mSf6TPaFipG@4*5L^tT$WB5H4dSx&L5HXTUBkZsoJp3^p";
    public static final int EXPIRATION_TIME = 43_200_000; // 12 ore
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static HttpStatus httpStatus;

    public static HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static void setHttpStatus(HttpStatus httpStatus) {
        JwtUtil.httpStatus = httpStatus;
    }
    
    //Questo metodo crea il token a partire da un applicationUser (il quale contiene semplicemente un utente)
    public static String creaToken (ApplicationUser applicationUser) {     
        String token = JWT.create()
                .withSubject(applicationUser.getUsername())         //aggiungo il proprietario del token
                .withIssuedAt(new Date(System.currentTimeMillis()))     //aggiungo la data di creazione del token
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))      //aggiungo la scadenza del token
                .sign(HMAC512(SECRET.getBytes()));      //firmo con algoritmo HMAC512 e chiave di sicurezza
        return token;
    }
    
    //Questo metodo valida il token e restituisce l'utente proprietario del token
    public static String getSubject (String token){
        String userName = JWT.require(HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
        return userName;
    }
    
    //this method checks token and returns the expiration time
    public static Date getExpirationTime (String Token){
        Date expirationTime = JWT.require(HMAC512(SECRET.getBytes()))
                .build()
                .verify(Token)
                .getExpiresAt();
        return expirationTime;
    }
}
