package it.mcprisa.autonoleggio.servizi;

import it.mcprisa.autonoleggio.model.Utente;
import java.util.List;

public interface SrvUtente {

    public void nuovoUtente(Utente utente);

    public List<Utente> lista();

    public Utente inserisci(Utente utente);

    public void elimina(Utente utente);

    public Utente modifica(Utente utente);

    public List<Utente> ricercaGenerica(String criterioRicerca);
    
}
