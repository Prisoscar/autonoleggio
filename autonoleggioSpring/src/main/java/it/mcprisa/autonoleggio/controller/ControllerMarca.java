package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.model.Marca;
import it.mcprisa.autonoleggio.servizi.SrvMarca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marca")
public class ControllerMarca {
    
    @Autowired
    SrvMarca srvMarca;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<Marca> lista() {
        return srvMarca.lista();
    }

    @RequestMapping(value = "/inserisci", method = RequestMethod.PUT)
    public Marca inserisci(@RequestBody Marca marca) {
        return srvMarca.inserisci(marca);
    }
    
    @RequestMapping(value = "/elimina", method = RequestMethod.DELETE)
    public void elimina(@RequestBody Marca marca){
        srvMarca.elimina(marca);
    }
    
    @RequestMapping(value = "/modifica",method = RequestMethod.PUT)
    public Marca modifica(@RequestBody Marca marca){
        return srvMarca.modifica(marca);
    }
    
    @RequestMapping(value = "/cercaMarca", method = RequestMethod.POST)
    public List<Marca> cercaMarca (@RequestBody String criterioRicerca){
        return srvMarca.cercaMarca(criterioRicerca);
    }
}
