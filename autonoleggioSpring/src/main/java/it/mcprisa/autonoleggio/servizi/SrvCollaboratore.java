package it.mcprisa.autonoleggio.servizi;

import it.mcprisa.autonoleggio.model.Collaboratore;
import java.util.List;


public interface SrvCollaboratore {

    public List<Collaboratore> lista();

    public Collaboratore inserisci(Collaboratore collaboratore);

    public void elimina(Collaboratore collaboratore, String id);

    public Collaboratore modifica(Collaboratore collaboratore);
    
}
