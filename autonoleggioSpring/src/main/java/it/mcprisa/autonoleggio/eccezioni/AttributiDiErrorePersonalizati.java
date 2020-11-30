package it.mcprisa.autonoleggio.eccezioni;

import it.mcprisa.autonoleggio.dto.ErrorePersonalizzatoDto;
import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

/*
in questa classe viene sovrascritto il json standard generato da spring per venire riadattato a
Errore personalizzatoDto che è il formato json che restituisce gli errori in queste api 
*/
@Component
public class AttributiDiErrorePersonalizati extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        var attributes = super.getErrorAttributes(webRequest, options);        
        String httpStatusNome = attributes.get("error").toString();     //ottengo il nome dell'http status che si è generato dall'eccezione
        int httpStatusCodice = Integer.parseInt(attributes.get("status").toString());       //ottengo il codice dell'http status
        String messaggio = "Errore non mappato.";       //genero il messaggio di errore preimpostato
        String percorso = attributes.get("path").toString();    //ottengo il percorso dal quale si è generato l'errore, dato che una volta arrivato qui spring ha reindirizzato la richiesta su "/error"
        ErrorePersonalizzatoDto errorePersonalizzatoDto = new ErrorePersonalizzatoDto(httpStatusCodice, httpStatusNome, messaggio);     //ora creo l'errore personalizzato che restituirò al cliente
        attributes.clear();     //Pulisco gli attributi predefiniti
        //e ci metto i miei attributi personalizzati
        attributes.put("timestamp", errorePersonalizzatoDto.getTimestamp());
        attributes.put("path", percorso);
        attributes.put("status", errorePersonalizzatoDto.getStatus());
        attributes.put("error", errorePersonalizzatoDto.getError());
        attributes.put("problem", errorePersonalizzatoDto.getMessage());
        return attributes;
    }
}
