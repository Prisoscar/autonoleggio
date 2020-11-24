package it.mcprisa.autonoleggio.repository;

import it.mcprisa.autonoleggio.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepMarca extends JpaRepository<Marca, String>{
    
}
