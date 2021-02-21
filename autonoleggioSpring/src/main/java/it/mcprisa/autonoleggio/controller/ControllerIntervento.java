package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.model.Intervento;
import it.mcprisa.autonoleggio.servizi.SrvIntervento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/intervento")
public class ControllerIntervento {
    
    @Autowired
    SrvIntervento srvIntervento;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<Intervento> lista() {
        return srvIntervento.lista();
    }

    @RequestMapping(value = "/inserisci", method = RequestMethod.PUT)
    public Intervento inserisci(@RequestBody Intervento intervento) {
        return srvIntervento.inserisci(intervento);
    }
    
    @RequestMapping(value = "/elimina", method = RequestMethod.DELETE)
    public void elimina(@RequestBody Intervento intervento){
        srvIntervento.elimina(intervento);
    }
    
    @RequestMapping(value = "/modifica",method = RequestMethod.PUT)
    public Intervento modifica(@RequestBody Intervento intervento){
        return srvIntervento.modifica(intervento);
    }
}
