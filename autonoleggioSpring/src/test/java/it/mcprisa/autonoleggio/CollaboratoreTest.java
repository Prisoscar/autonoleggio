package it.mcprisa.autonoleggio;

import it.mcprisa.autonoleggio.controller.ControllerTests;
import it.mcprisa.autonoleggio.eccezioni.CattivaRichiestaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CollaboratoreTest {
    
    @Autowired
    ControllerTests controllerCollaboratore;

    public CollaboratoreTest() {
    }
   /* 
    @Test
    public void testProvaLancioErrorePersonalizato (){
        CattivaRichiestaException assertThrows = Assertions.assertThrows(CattivaRichiestaException.class, () -> controllerCollaboratore.provaLancioErrorePersonalizato());
    }*/
}
