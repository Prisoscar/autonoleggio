package it.mcprisa.autonoleggio.eccezioni;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CattivaRichiestaException extends ModelloEccezionePersonalizzataConHttpStatus {

    public CattivaRichiestaException(String message) {
        super(message);
    }
}
