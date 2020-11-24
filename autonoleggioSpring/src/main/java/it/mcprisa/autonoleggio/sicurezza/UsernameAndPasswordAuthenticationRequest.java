
package it.mcprisa.autonoleggio.sicurezza;

/**
 *
 * È questa la struttura che avrà il form dell'autenticazione
 * Parte del PASSAGGIO 1
 */
public class UsernameAndPasswordAuthenticationRequest {
    //nel nostro caso l'autenticazione avverrà tramite user e passsword
    private String username;
    private String password;

    public UsernameAndPasswordAuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
