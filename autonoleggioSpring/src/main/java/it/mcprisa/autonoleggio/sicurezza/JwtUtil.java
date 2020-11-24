package it.mcprisa.autonoleggio.sicurezza;

import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import java.util.Date;

/**
 * Qui ci metto gli attributi del token e i metodi per la gestione del token
 */
public class JwtUtil {

    public static final String SECRET = "rtin&UPSG46iobmCH$YG5noSPa9#CJibvXPGXY$GaU9WG&2jA5hiJnjMt%tLe3!WrAWLk#DCFh7uT6TApJrgy*GVh#V&F^PaCHa@J2UmZwJn!vbt2@54T$3R*Kg**tDY";
    public static final int EXPIRATION_TIME = 43_200_000; // 12 ore
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    
    //Questo metodo crea il token a partire da un applicationUser (il quale contiene semplicemente un utente)
    public static String creaToken (ApplicationUser applicationUser) {     
        String token = JWT.create()
                .withSubject(applicationUser.getUsername())         //aggiungo il proprietario del token
                .withIssuedAt(new Date(System.currentTimeMillis()))     //aggiungo la data di creazione del token
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRATION_TIME))      //aggiungo la scadenza del token
                .sign(HMAC512(JwtUtil.SECRET.getBytes()));      //firmo con algoritmo HMAC512 e chiave di sicurezza
        return token;
    }
    //Questo metodo valida il token e restituisce l'utente proprietario del token
    public static String getSubject (String token){
        String userName = JWT.require(HMAC512(JwtUtil.SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
        return userName;
    }
}
