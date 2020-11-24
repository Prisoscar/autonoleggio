package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.model.Utente;
import it.mcprisa.autonoleggio.servizi.SrvUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/utente", method = RequestMethod.POST)
public class ControllerUtente {
    
    @Autowired
    SrvUtente srvUtente;
    
    @RequestMapping("/public/nuovoUtente")
    public void nuovoUtente (@RequestBody Utente utente){
        srvUtente.nuovoUtente(utente);
    }
}
