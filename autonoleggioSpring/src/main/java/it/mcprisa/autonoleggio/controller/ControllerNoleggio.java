package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.model.Noleggio;
import it.mcprisa.autonoleggio.servizi.SrvNoleggio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noleggio")
public class ControllerNoleggio {
    
    @Autowired
    SrvNoleggio srvNoleggio;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<Noleggio> lista() {
        return srvNoleggio.lista();
    }

    @RequestMapping(value = "/inserisci", method = RequestMethod.PUT)
    public Noleggio inserisci(@RequestBody Noleggio noleggio) {
        return srvNoleggio.inserisci(noleggio);
    }
    
    @RequestMapping(value = "/elimina", method = RequestMethod.DELETE)
    public void elimina(@RequestBody Noleggio noleggio){
        srvNoleggio.elimina(noleggio);
    }
    
    @RequestMapping(value = "/modifica",method = RequestMethod.PUT)
    public Noleggio modifica(@RequestBody Noleggio noleggio){
        return srvNoleggio.modifica(noleggio);
    }
}
