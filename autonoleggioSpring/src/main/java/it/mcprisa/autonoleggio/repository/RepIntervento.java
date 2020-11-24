package it.mcprisa.autonoleggio.repository;

import it.mcprisa.autonoleggio.model.Intervento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepIntervento extends JpaRepository<Intervento, String>{
    
}
