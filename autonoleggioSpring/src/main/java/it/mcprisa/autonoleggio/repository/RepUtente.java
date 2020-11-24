package it.mcprisa.autonoleggio.repository;

import it.mcprisa.autonoleggio.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepUtente extends JpaRepository<Utente, String>{

    public Utente findByUsername(String username);
    
}
