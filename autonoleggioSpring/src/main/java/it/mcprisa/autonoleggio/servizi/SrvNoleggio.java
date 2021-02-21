package it.mcprisa.autonoleggio.servizi;

import it.mcprisa.autonoleggio.model.Noleggio;
import java.util.List;

public interface SrvNoleggio {

    public List<Noleggio> lista();

    public Noleggio inserisci(Noleggio noleggio);

    public void elimina(Noleggio noleggio);

    public Noleggio modifica(Noleggio noleggio);
}
