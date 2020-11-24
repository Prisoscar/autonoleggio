package it.mcprisa.autonoleggio.controller;

import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collaboratore")
public class ControllerCollaboratore {
    
    @RolesAllowed("CLIENTE")
    public void metodo(){
        
    }
    
}
