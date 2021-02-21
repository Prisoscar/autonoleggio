package it.mcprisa.autonoleggio.servizi;

import it.mcprisa.autonoleggio.model.Vettura;
import java.sql.Date;
import java.util.List;

public interface SrvVettura {

    public List<Vettura> lista();

    public Vettura inserisci(Vettura vettura);

    public void elimina(Vettura vettura);

    public Vettura modifica(Vettura vettura);

    public List<Vettura> ricercaGenerica(String criterioRicerca);

    public boolean verificaDisponibilita(Vettura vettura, Date dataInizioNoleggio, Date dataFineNoleggio);
    
}
