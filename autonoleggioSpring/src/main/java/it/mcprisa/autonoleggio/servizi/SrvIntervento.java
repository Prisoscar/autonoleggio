package it.mcprisa.autonoleggio.servizi;

import it.mcprisa.autonoleggio.model.Intervento;
import java.util.List;

public interface SrvIntervento {

    public List<Intervento> lista();

    public Intervento inserisci(Intervento intervento);

    public void elimina(Intervento intervento);

    public Intervento modifica(Intervento intervento);
    
}
