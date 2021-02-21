package it.mcprisa.autonoleggio.serviziImplementazione;

import it.mcprisa.autonoleggio.eccezioni.CattivaRichiestaException;
import it.mcprisa.autonoleggio.model.Modello;
import it.mcprisa.autonoleggio.repository.RepModello;
import it.mcprisa.autonoleggio.servizi.SrvModello;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SrvModelloImpl implements SrvModello {

    @Autowired
    RepModello repModello;

    @Override
    public List<Modello> lista() {
        return repModello.findAll();
    }

    @Override
    public Modello inserisci(Modello modello) {
        //se nella richiesta è stato inserito un id valido viene lanciata una eccezione di CattivaRichiesta per evitare che il campo presente nel database venga sovrascritto
        try {
            repModello.getOne(modello.getId());
            throw new CattivaRichiestaException("id già esistente, togliere l'id.");
        } catch (RuntimeException e) {
            //se manca qualche attributo non nullabile viene lanciata una eccezione di CattivaRichiesta
            try {
                return repModello.save(modello);
            } catch (RuntimeException e2) {
                throw new CattivaRichiestaException(
                        "assenza attributo obbligatorio, invia la richiesta inserendo tutti gli attributi obbligatori o controlla che i nomi degli attributi siano scritti correttamente"
                );
            }
        }
    }

    @Override
    public void elimina(Modello modello) {
        //in queste API non si fanno cancellazioni
        throw new CattivaRichiestaException("Operazione non consentita!");
    }

    @Override
    public Modello modifica(Modello modello) {
        //si cerca se il modello inviato esista effettivamente nel datatbase
        try {
            repModello.getOne(modello.getId());
            return repModello.save(modello);
            //se il modello non è presente nel database viene lanciata una eccezione di CattivaRichiesta
        } catch (RuntimeException e) {
            throw new CattivaRichiestaException("Modello non presente nel database.");
        }
    }

    @Override
    public List<Modello> cercaModello(String criterioRicerca) {
        //se viene mandata una stringa vuota ritorno solo i risultati dove una delle voci è vuota
        if (criterioRicerca.equals("")) {
            return repModello.findByModello(criterioRicerca);
            //altrimenti si fa una ricerca tramite le wildcard % di mysql
        } else {
            return repModello.findByModelloLike("%" + criterioRicerca + "%");
        }
    }

}
