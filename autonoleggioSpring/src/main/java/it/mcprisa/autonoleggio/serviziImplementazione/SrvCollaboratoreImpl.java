package it.mcprisa.autonoleggio.serviziImplementazione;

import it.mcprisa.autonoleggio.eccezioni.CattivaRichiestaException;
import it.mcprisa.autonoleggio.model.Collaboratore;
import it.mcprisa.autonoleggio.repository.RepCollaboratore;
import it.mcprisa.autonoleggio.servizi.SrvCollaboratore;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SrvCollaboratoreImpl implements SrvCollaboratore {

    @Autowired
    RepCollaboratore repCollaboratore;

    @Override
    public List<Collaboratore> lista() {
        return repCollaboratore.findAll();
    }

    @Override
    public Collaboratore inserisci(Collaboratore collaboratore) {
        //se nella richiesta è stato inserito un id valido viene lanciata una eccezione di CattivaRichiesta per evitare che il campo presente nel database venga sovrascritto
        try {
            repCollaboratore.getOne(collaboratore.getId());
            throw new CattivaRichiestaException("id già esistente, togliere l'id.");
        } catch (RuntimeException e) {
            //se manca qualche attributo non nullabile viene lanciata una eccezione di CattivaRichiesta
            try {
                return repCollaboratore.save(collaboratore);
            } catch (RuntimeException e2) {
                throw new CattivaRichiestaException(
                        "assenza attributo obbligatorio, invia la richiesta inserendo tutti gli attributi obbligatori o controlla che i nomi degli attributi siano scritti correttamente"
                );
            }
        }
    }

    @Override
    public void elimina(Collaboratore collaboratore) {
        //in queste API non si fanno cancellazioni
        throw new CattivaRichiestaException("Operazione non consentita!");
        /*
        //si prova ad eliminare il collaboratore dal DB
        try {
            repCollaboratore.delete(collaboratore);
        } catch (IllegalArgumentException e) {
            //se insorgono problemi viene lanciata una eccezione di cattiva richiesta
            throw new CattivaRichiestaException("Il collaboratore inviato non è presente nel database, controllare che i dati siano scritti correttamente.");
        }*/
    }

    @Override
    public Collaboratore modifica(Collaboratore collaboratore) {
        //si cerca se il collaboratore inviato esista effettivamente nel datatbase
        try {
            repCollaboratore.getOne(collaboratore.getId());
            return repCollaboratore.save(collaboratore);
            //se il collaboratore non è presente nel database viene lanciata una eccezione di CattivaRichiesta
        } catch (RuntimeException e) {
            throw new CattivaRichiestaException("Collaboratore non presente nel database.");
        }
    }

    @Override
    public List<Collaboratore> ricercaGenerica(String criterioRicerca) {
        //se viene mandata una stringa vuota ritorno solo i risultati dove una delle voci è vuota
        if (criterioRicerca.equals("")) {
            return repCollaboratore.findByNomeOrPartitaIva(criterioRicerca);
        //altrimenti si fa una ricerca tramite le wildcard % di mysql
        } else {
            return repCollaboratore.findByNomeLikeOrPartitaIvaLike("%" + criterioRicerca + "%");
        }
    }
}
