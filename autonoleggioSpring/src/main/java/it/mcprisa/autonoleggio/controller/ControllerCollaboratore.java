package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.model.Collaboratore;
import it.mcprisa.autonoleggio.servizi.SrvCollaboratore;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/collaboratore", method = RequestMethod.POST)
public class ControllerCollaboratore {

    @Autowired
    SrvCollaboratore srvCollaboratore;

    @RequestMapping("/lista")
    public List<Collaboratore> lista() {
        return srvCollaboratore.lista();
    }

    @RequestMapping("/inserisci")
    public Collaboratore inserisci(@RequestBody Collaboratore collaboratore) {
        return srvCollaboratore.inserisci(collaboratore);
    }
    
    @RequestMapping("/elimina")
    public void elimina(@RequestBody @Nullable Collaboratore collaboratore, @Nullable String id){
        srvCollaboratore.elimina(collaboratore, id);
    }
    
    @RequestMapping("/modifica")
    public Collaboratore modifica(@RequestBody Collaboratore collaboratore){
        return srvCollaboratore.modifica(collaboratore);
    }
}
