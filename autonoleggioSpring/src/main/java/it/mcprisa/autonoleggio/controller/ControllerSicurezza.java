package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.servizi.SrvSicurezza;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerSicurezza {

    @Autowired
    SrvSicurezza srvSicurezza;

    @RequestMapping(value = "/storeToken", method = RequestMethod.POST)
    public void storeToken(
            @RequestParam String token,
            HttpServletResponse response
    ) {
            srvSicurezza.storeToken(token, response);
    }
    
    @RequestMapping (value = "/getToken", method = RequestMethod.POST)
    public String getToken(HttpServletRequest request){
        return srvSicurezza.getToken(request);
    }
    
    @RequestMapping (value = "/removeToken", method = RequestMethod.POST)
    public void removeToken (HttpServletResponse response){
        srvSicurezza.removeToken (response);
    }
}
