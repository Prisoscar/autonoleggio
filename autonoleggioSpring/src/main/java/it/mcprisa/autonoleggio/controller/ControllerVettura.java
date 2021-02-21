package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.model.Vettura;
import it.mcprisa.autonoleggio.servizi.SrvVettura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vettura")
public class ControllerVettura {
    
    @Autowired
    SrvVettura srvVettura;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<Vettura> lista() {
        return srvVettura.lista();
    }

    @RequestMapping(value = "/inserisci", method = RequestMethod.PUT)
    public Vettura inserisci(@RequestBody Vettura vettura) {
        return srvVettura.inserisci(vettura);
    }
    
    @RequestMapping(value = "/elimina", method = RequestMethod.DELETE)
    public void elimina(@RequestBody Vettura vettura){
        srvVettura.elimina(vettura);
    }
    
    @RequestMapping(value = "/modifica",method = RequestMethod.PUT)
    public Vettura modifica(@RequestBody Vettura vettura){
        return srvVettura.modifica(vettura);
    }
    
    @RequestMapping(value = "/ricercaGenerica", method = RequestMethod.POST)
    public List<Vettura> ricercaGenerica (@RequestBody String criterioRicerca){
        return srvVettura.ricercaGenerica(criterioRicerca);
    }
}
