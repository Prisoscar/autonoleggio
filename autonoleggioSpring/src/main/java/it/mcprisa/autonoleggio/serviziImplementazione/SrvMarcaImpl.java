package it.mcprisa.autonoleggio.serviziImplementazione;

import it.mcprisa.autonoleggio.eccezioni.CattivaRichiestaException;
import it.mcprisa.autonoleggio.model.Marca;
import it.mcprisa.autonoleggio.repository.RepMarca;
import it.mcprisa.autonoleggio.servizi.SrvMarca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SrvMarcaImpl implements SrvMarca{

    @Autowired
    RepMarca repMarca;
    
    @Override
    public List<Marca> lista() {
        return repMarca.findAll();
    }

    @Override
    public Marca inserisci(Marca marca) {
        //se nella richiesta è stato inserito un id valido viene lanciata una eccezione di CattivaRichiesta per evitare che il campo presente nel database venga sovrascritto
        try {
            repMarca.getOne(marca.getId());
            throw new CattivaRichiestaException("id già esistente, togliere l'id.");
        } catch (RuntimeException e) {
            //se manca qualche attributo non nullabile viene lanciata una eccezione di CattivaRichiesta
            try {
                return repMarca.save(marca);
            } catch (RuntimeException e2) {
                throw new CattivaRichiestaException(
                        "assenza attributo obbligatorio, invia la richiesta inserendo tutti gli attributi obbligatori o controlla che i nomi degli attributi siano scritti correttamente"
                );
            }
        }
    }

    @Override
    public void elimina(Marca marca) {
        //in queste API non si fanno cancellazioni
        throw new CattivaRichiestaException("Operazione non consentita!");
    }

    @Override
    public Marca modifica(Marca marca) {
        //si cerca se il marca inviato esista effettivamente nel datatbase
        try {
            repMarca.getOne(marca.getId());
            return repMarca.save(marca);
            //se il marca non è presente nel database viene lanciata una eccezione di CattivaRichiesta
        } catch (RuntimeException e) {
            throw new CattivaRichiestaException("Marca non presente nel database.");
        }
    }

    @Override
    public List<Marca> cercaMarca(String criterioRicerca) {
        //se viene mandata una stringa vuota ritorno solo i risultati dove una delle voci è vuota
        if (criterioRicerca.equals("")) {
            return repMarca.findByMarca(criterioRicerca);
        //altrimenti si fa una ricerca tramite le wildcard % di mysql
        } else {
            return repMarca.findByMarcaLike("%" + criterioRicerca + "%");
        }
    }
    
}
