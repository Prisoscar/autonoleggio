package it.mcprisa.autonoleggio.repository;

import it.mcprisa.autonoleggio.model.Modello;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepModello extends JpaRepository<Modello, String>{
    
}
