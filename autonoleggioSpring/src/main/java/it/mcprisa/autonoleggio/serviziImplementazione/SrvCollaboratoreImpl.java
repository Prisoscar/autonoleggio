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
    public void elimina(Collaboratore collaboratore, String id) {
        //come prima cosa si prova a eliminare il record usando il collaboratore
        try {
            repCollaboratore.delete(collaboratore);
        } catch (IllegalArgumentException e) {
            //se non è presesente il collaboratore si prova ad eliminare per id
            try {
                repCollaboratore.deleteById(id);
                //se anche il secondo tentativo va male si lancia una eccezione di CattivaRichiesta
            } catch (IllegalArgumentException e2) {
                throw new CattivaRichiestaException("Il collaboratore inviato non è presente nel database, controllare che i dati siano scritti correttamente.");
            }
        }
    }

    @Override
    public Collaboratore modifica(Collaboratore collaboratore) {
        //si cerca se il collaboratore inviato esista effettivamente nel datatbase
        try {
            repCollaboratore.getOne(collaboratore.getId());
            return repCollaboratore.save(collaboratore);
            //se il collaboratore non è presente nel database viene lanciata una eccezione di CattivaRichiesta
        } catch (RuntimeException e) {
            throw new CattivaRichiestaException("Il collaboratore inviato non è presente nel database.");
        }
    }
}
