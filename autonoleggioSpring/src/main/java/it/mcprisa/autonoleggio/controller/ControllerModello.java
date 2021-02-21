package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.model.Modello;
import it.mcprisa.autonoleggio.servizi.SrvModello;
import it.mcprisa.autonoleggio.servizi.SrvModello;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modello")
public class ControllerModello {
 
    @Autowired
    SrvModello srvModello;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<Modello> lista() {
        return srvModello.lista();
    }

    @RequestMapping(value = "/inserisci", method = RequestMethod.PUT)
    public Modello inserisci(@RequestBody Modello modello) {
        return srvModello.inserisci(modello);
    }
    
    @RequestMapping(value = "/elimina", method = RequestMethod.DELETE)
    public void elimina(@RequestBody Modello modello){
        srvModello.elimina(modello);
    }
    
    @RequestMapping(value = "/modifica",method = RequestMethod.PUT)
    public Modello modifica(@RequestBody Modello modello){
        return srvModello.modifica(modello);
    }
    
    @RequestMapping(value = "/cercaModello", method = RequestMethod.POST)
    public List<Modello> cercaModello (@RequestBody String criterioRicerca){
        return srvModello.cercaModello(criterioRicerca);
    }
}
