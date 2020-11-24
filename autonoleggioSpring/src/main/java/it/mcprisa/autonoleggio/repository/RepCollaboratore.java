package it.mcprisa.autonoleggio.repository;

import it.mcprisa.autonoleggio.model.Collaboratore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepCollaboratore extends JpaRepository<Collaboratore, String>{
    
}
