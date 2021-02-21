package it.mcprisa.autonoleggio.repository;

import it.mcprisa.autonoleggio.model.Marca;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepMarca extends JpaRepository<Marca, String>{

    public List<Marca> findByMarca(String criterioRicerca);

    public List<Marca> findByMarcaLike(String string);
    
}
