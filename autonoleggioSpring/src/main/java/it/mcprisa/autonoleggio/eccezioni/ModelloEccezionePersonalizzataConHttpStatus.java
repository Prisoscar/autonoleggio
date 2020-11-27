package it.mcprisa.autonoleggio.eccezioni;

public class ModelloEccezionePersonalizzataConHttpStatus extends RuntimeException{

    public ModelloEccezionePersonalizzataConHttpStatus(String message) {
        super(message);
    }
    
}
