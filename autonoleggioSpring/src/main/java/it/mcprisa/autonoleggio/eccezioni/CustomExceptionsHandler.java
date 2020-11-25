package it.mcprisa.autonoleggio.eccezioni;

import it.mcprisa.autonoleggio.dto.ErrorePersonalizzatoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Questa classe intercetta le eccezioni lanciate dal controller per restituire
 * dei messaggi di errore customizzati
 */
@ControllerAdvice
public class CustomExceptionsHandler {

    //Questo è il metodo che gestisce le eccezioni di bad request (400)
    @ExceptionHandler({CattivaRichiestaException.class})
    public ResponseEntity<ErrorePersonalizzatoDto> handleBadRequestCustomException(CattivaRichiestaException e) {

        ErrorePersonalizzatoDto errorePersonalizzatoDto = new ErrorePersonalizzatoDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage()
        );
        //istanzio il CustomErrorDto che verrà inserito nel JSON di risposta

        return new ResponseEntity<>(errorePersonalizzatoDto, HttpStatus.BAD_REQUEST);
    }
}
