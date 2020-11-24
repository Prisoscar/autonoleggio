package it.mcprisa.autonoleggio.repository;

import it.mcprisa.autonoleggio.model.Vettura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepVettura extends JpaRepository<Vettura, String>{
    
}
