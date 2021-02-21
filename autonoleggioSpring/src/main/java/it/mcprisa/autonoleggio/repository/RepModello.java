package it.mcprisa.autonoleggio.repository;

import it.mcprisa.autonoleggio.model.Modello;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepModello extends JpaRepository<Modello, String>{

    public List<Modello> findByModello(String criterioRicerca);

    public List<Modello> findByModelloLike(String string);
    
}
