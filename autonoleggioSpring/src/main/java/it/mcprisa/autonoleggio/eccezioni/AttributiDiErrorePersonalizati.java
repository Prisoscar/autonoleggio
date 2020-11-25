package it.mcprisa.autonoleggio.eccezioni;

import it.mcprisa.autonoleggio.dto.ErrorePersonalizzatoDto;
import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

public class AttributiDiErrorePersonalizati extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        var attributes = super.getErrorAttributes(webRequest, options);
        String httpStatusNome = attributes.get("error").toString();     //ottengo il nome dell'http status che si è generato dall'eccezione
        int httpStatusCodice = Integer.parseInt(attributes.get("status").toString());       //ottengo il codice dell'http status
        String messaggio = attributes.get("message ").toString();       //ottengo il messaggio che l'eccezione ha generato se lo ha generato
        ErrorePersonalizzatoDto errorePersonalizzatoDto = new ErrorePersonalizzatoDto(httpStatusCodice, httpStatusNome, messaggio);     //ora creo l'errore personalizzato che restituirò al cliente
        attributes.clear();
        attributes.put("timestamp", errorePersonalizzatoDto.getTimestamp());
        attributes.put("path", errorePersonalizzatoDto.getPath());
        attributes.put("status", errorePersonalizzatoDto.getStatus());
        attributes.put("error", errorePersonalizzatoDto.getError());
        attributes.put("problem", errorePersonalizzatoDto.getProblem());
        return attributes;
    }

}
