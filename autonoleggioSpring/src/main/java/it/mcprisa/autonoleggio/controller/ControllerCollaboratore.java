package it.mcprisa.autonoleggio.controller;

import it.mcprisa.autonoleggio.eccezioni.CattivaRichiestaException;
import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collaboratore")
public class ControllerCollaboratore {
    
    @RolesAllowed("CLIENTE")
    @RequestMapping("/admin/metodo")
    public void metodo(){ 
        System.out.println("\n\n\nheyy");
    }
    
    /*@PostMapping
    @RequestMapping("/login")
    public void*/
    
    @RequestMapping("/public/eccezionePersonalizzata")
    public void provaLancioErrorePersonalizato(){
        throw new CattivaRichiestaException("l'eccezione ha fatto il suo dovere!");
    }
    
    @RequestMapping("/public/eccezioneStandard")
    public void provaLancioErroreNonPersonalizato(){
        int i = 2/0;
    }
    @PostMapping
    @RequestMapping("/user")
    public String user (){
     return "Qui possono accedere solo utenti";   
    }
    @PostMapping
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String admin (){
     return "Qui possono accedere solo amministratori";   
    }
}
