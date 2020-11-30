package it.mcprisa.autonoleggio.eccezioni;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MetodoNonAmmessoException extends ModelloEccezionePersonalizzataConHttpStatus{

    public MetodoNonAmmessoException(String message) {
        super(message);
    }
    
}
