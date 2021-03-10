package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.model.Utente;
import it.mcprisa.autonoleggio.servizi.SrvUtente;
import java.util.List;
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
    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<Utente> lista() {
        return srvUtente.lista();
    }

    @RequestMapping(value = "/inserisci", method = RequestMethod.PUT)
    public Utente inserisci(@RequestBody Utente utente) {
        return srvUtente.inserisci(utente);
    }
    
    @RequestMapping(value = "/elimina", method = RequestMethod.DELETE)
    public void elimina(@RequestBody Utente utente){
        srvUtente.elimina(utente);
    }
    
    @RequestMapping(value = "/modifica",method = RequestMethod.PUT)
    public Utente modifica(@RequestBody Utente utente){
        return srvUtente.modifica(utente);
    }
    
    @RequestMapping(value = "/ricercaGenerica", method = RequestMethod.POST)
    public List<Utente> ricercaGenerica (@RequestBody String criterioRicerca){
        return srvUtente.ricercaGenerica(criterioRicerca);
    }
}
