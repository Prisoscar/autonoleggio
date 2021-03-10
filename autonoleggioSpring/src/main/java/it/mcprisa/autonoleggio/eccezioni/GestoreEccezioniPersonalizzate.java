package it.mcprisa.autonoleggio.eccezioni;

import it.mcprisa.autonoleggio.dto.ErrorePersonalizzatoDto;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Questa classe intercetta le eccezioni lanciate dal controller per restituire
 * dei messaggi di errore customizzati
 */
@ControllerAdvice
public class GestoreEccezioniPersonalizzate {

    //Questo è il metodo che gestisce le eccezioni personaliizte
    @ExceptionHandler(ModelloEccezionePersonalizzataConHttpStatus.class)
    public ResponseEntity<ErrorePersonalizzatoDto> handleBadRequestCustomException(ModelloEccezionePersonalizzataConHttpStatus e) {
        //Istanzio l'oggetto che restituirò nella risposta
        ErrorePersonalizzatoDto errorePersonalizzatoDto = new ErrorePersonalizzatoDto(
                AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class).value().value(),
                AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class).value().getReasonPhrase(),
                e.getMessage()
        );
        return new ResponseEntity<>(errorePersonalizzatoDto, AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class).value());
    }
}
