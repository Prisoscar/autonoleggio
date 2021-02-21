package it.mcprisa.autonoleggio.serviziImplementazione;

import it.mcprisa.autonoleggio.model.Vettura;
import it.mcprisa.autonoleggio.servizi.SrvVettura;
import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SrvVetturaImpl implements SrvVettura{

    @Override
    public List<Vettura> lista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vettura inserisci(Vettura vettura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void elimina(Vettura vettura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vettura modifica(Vettura vettura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vettura> ricercaGenerica(String criterioRicerca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificaDisponibilita(Vettura vettura, Date dataInizioNoleggio, Date dataFineNoleggio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
