
package it.mcprisa.autonoleggio.sicurezza;

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
