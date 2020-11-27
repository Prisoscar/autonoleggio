package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.eccezioni.CattivaRichiestaException;
import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collaboratore")
public class ControllerCollaboratore {
    
    @RolesAllowed("CLIENTE")
    @RequestMapping("/metodo")
    public void metodo(){        
    }
    
    @RequestMapping("/public/eccezionePersonalizzata")
    public void provaLancioErrorePersonalizato(){
        throw new CattivaRichiestaException("l'eccezione ha fatto il suo dovere!");
    }
    
    @RequestMapping("/public/eccezioneStandard")
    public void provaLancioErroreNonPersonalizato(){
        int i = 2/0;
    }
    
}
