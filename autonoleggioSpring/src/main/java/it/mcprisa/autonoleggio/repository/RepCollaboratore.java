package it.mcprisa.autonoleggio.repository;

import it.mcprisa.autonoleggio.model.Collaboratore;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepCollaboratore extends JpaRepository<Collaboratore, String>{

    @Query("select t from Collaboratore t where t.nome = ?1 OR t.partitaIva = ?1")
    public List<Collaboratore> findByNomeOrPartitaIva(String criterioRicerca);

    @Query("select t from Collaboratore t where t.nome LIKE  ?1 OR t.partitaIva LIKE  ?1")
    public List<Collaboratore> findByNomeLikeOrPartitaIvaLike(String string);
    
}
