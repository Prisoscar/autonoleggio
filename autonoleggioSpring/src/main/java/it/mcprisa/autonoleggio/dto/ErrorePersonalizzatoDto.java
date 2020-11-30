package it.mcprisa.autonoleggio.dto;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Questo è l'oggetto che verrà inserito come json nella risposta http in caso
 * di errori personalizati
 */
public class ErrorePersonalizzatoDto {

    private String timestamp;
    private String path;
    private int status;
    private String error;
    private String message;

    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss z");   //il time lo stampo con questo formato
        /*
        il path e il timestamp verranno sempre definiti in questo modo in questa API, dunque
        per evitare la scrittura di codice inutile ogni volta che viene istanziato un oggetto
        di tipo CustomErrorDto istanzio questi 2 attributi direttamente dentro a un inizializer
         */
        this.path = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRequestURI();
        this.timestamp = ZonedDateTime.now().format(formatter);
    }

    public ErrorePersonalizzatoDto(int status, String error, String problem) {
        this.status = status;
        this.error = error;
        this.message = problem;
    }

    public ErrorePersonalizzatoDto() {
    }

    @Override
    //Il toString genererà un json
    public String toString() {
        return "{" + "\"timestamp\":\"" + timestamp + "\",\"path\":\"" + path + "\",\"status\":" + status + ",\"error\":\"" + error + "\",\"message\":\"" + message + "\"}";
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
