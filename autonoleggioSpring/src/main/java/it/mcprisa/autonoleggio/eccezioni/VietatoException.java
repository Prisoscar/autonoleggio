package it.mcprisa.autonoleggio.eccezioni;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class VietatoException extends ModelloEccezionePersonalizzataConHttpStatus{

    public VietatoException(String message) {
        super(message);
    }
    
}
