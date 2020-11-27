package it.mcprisa.autonoleggio.eccezioni;

import it.mcprisa.autonoleggio.dto.ErrorePersonalizzatoDto;
import java.nio.file.AccessDeniedException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Questa classe intercetta le eccezioni lanciate dal controller per restituire
 * dei messaggi di errore customizzati
 */
@ControllerAdvice
public class CustomExceptionsHandler {

    //Questo è il metodo che gestisce le eccezioni di bad request (400)
    @ExceptionHandler(ModelloEccezionePersonalizzataConHttpStatus.class)
    public ResponseEntity<ErrorePersonalizzatoDto> handleBadRequestCustomException(ModelloEccezionePersonalizzataConHttpStatus e) {
        //Istanzio l'oggetto che restituirò nella risposta
        ErrorePersonalizzatoDto errorePersonalizzatoDto = new ErrorePersonalizzatoDto();
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null && AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class).value() != null){
                errorePersonalizzatoDto.setStatus(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class).value().value());
                errorePersonalizzatoDto.setError(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class).value().getReasonPhrase());
                errorePersonalizzatoDto.setProblem(e.getMessage());
        }else{
                throw e;
        }
        return new ResponseEntity<>(errorePersonalizzatoDto, AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class).value());
    }
}
 
