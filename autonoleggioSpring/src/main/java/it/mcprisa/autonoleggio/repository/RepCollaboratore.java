package it.mcprisa.autonoleggio.repository;

import it.mcprisa.autonoleggio.model.Collaboratore;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepCollaboratore extends JpaRepository<Collaboratore, String>{

    public List<Collaboratore> findByNomeOrPartitaIva(String criterioRicerca);

    public List<Collaboratore> findByNomeLikeOrPartitaIvaLike(String string);
    
}
