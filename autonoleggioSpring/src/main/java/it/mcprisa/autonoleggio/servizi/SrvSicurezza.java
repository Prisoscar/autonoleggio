package it.mcprisa.autonoleggio.servizi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SrvSicurezza {

    public void storeToken(String token, HttpServletResponse response);

    public String getToken(HttpServletRequest request);

    public void removeToken(HttpServletResponse response);
    
}
