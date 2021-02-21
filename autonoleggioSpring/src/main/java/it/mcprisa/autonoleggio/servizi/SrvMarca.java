package it.mcprisa.autonoleggio.servizi;

import it.mcprisa.autonoleggio.model.Marca;
import java.util.List;

public interface SrvMarca {

    public List<Marca> lista();

    public Marca inserisci(Marca marca);

    public void elimina(Marca marca);

    public Marca modifica(Marca marca);

    public List<Marca> cercaMarca(String criterioRicerca);
    
}
