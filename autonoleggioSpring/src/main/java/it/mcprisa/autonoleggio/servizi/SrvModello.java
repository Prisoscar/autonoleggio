package it.mcprisa.autonoleggio.servizi;

import it.mcprisa.autonoleggio.model.Modello;
import java.util.List;

public interface SrvModello {

    public List<Modello> lista();

    public Modello inserisci(Modello modello);

    public void elimina(Modello modello);

    public Modello modifica(Modello modello);

    public List<Modello> cercaModello(String criterioRicerca);
    
}
