package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.model.Collaboratore;
import it.mcprisa.autonoleggio.servizi.SrvCollaboratore;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collaboratore")
public class ControllerCollaboratore {

    @Autowired
    SrvCollaboratore srvCollaboratore;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<Collaboratore> lista() {
        return srvCollaboratore.lista();
    }

    @RequestMapping(value = "/inserisci", method = RequestMethod.PUT)
    public Collaboratore inserisci(@RequestBody Collaboratore collaboratore) {
        return srvCollaboratore.inserisci(collaboratore);
    }
    
    @RequestMapping(value = "/elimina", method = RequestMethod.DELETE)
    public void elimina(@RequestBody Collaboratore collaboratore){
        srvCollaboratore.elimina(collaboratore);
    }
    
    @RequestMapping(value = "/modifica",method = RequestMethod.PUT)
    public Collaboratore modifica(@RequestBody Collaboratore collaboratore){
        return srvCollaboratore.modifica(collaboratore);
    }
    
    @RequestMapping(value = "/ricercaGenerica", method = RequestMethod.POST)
    public List<Collaboratore> ricercaGenerica (@RequestBody String criterioRicerca){
        return srvCollaboratore.ricercaGenerica(criterioRicerca);
    }
}
