package it.mcprisa.autonoleggio.serviziImplementazione;

import it.mcprisa.autonoleggio.eccezioni.CattivaRichiestaException;
import it.mcprisa.autonoleggio.model.Intervento;
import it.mcprisa.autonoleggio.repository.RepIntervento;
import it.mcprisa.autonoleggio.servizi.SrvIntervento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SrvInterventoImpl implements SrvIntervento{

    @Autowired
    RepIntervento repIntervento;
    
    @Override
    public List<Intervento> lista() {
        return repIntervento.findAll();
    }

    @Override
    public Intervento inserisci(Intervento intervento) {
        //se nella richiesta è stato inserito un id valido viene lanciata una eccezione di CattivaRichiesta per evitare che il campo presente nel database venga sovrascritto
        try {
            repIntervento.getOne(intervento.getId());
            throw new CattivaRichiestaException("id già esistente, togliere l'id.");
        } catch (RuntimeException e) {
            //se manca qualche attributo non nullabile viene lanciata una eccezione di CattivaRichiesta
            try {
                return repIntervento.save(intervento);
            } catch (RuntimeException e2) {
                throw new CattivaRichiestaException(
                        "assenza attributo obbligatorio, invia la richiesta inserendo tutti gli attributi obbligatori o controlla che i nomi degli attributi siano scritti correttamente"
                );
            }
        }
    }

    @Override
    public void elimina(Intervento intervento) {
        //in queste API non si fanno cancellazioni
        throw new CattivaRichiestaException("Operazione non consentita!");
    }

    @Override
    public Intervento modifica(Intervento intervento) {
        //si cerca se il intervento inviato esista effettivamente nel datatbase
        try {
            repIntervento.getOne(intervento.getId());
            return repIntervento.save(intervento);
            //se il intervento non è presente nel database viene lanciata una eccezione di CattivaRichiesta
        } catch (RuntimeException e) {
            throw new CattivaRichiestaException("Intervento non presente nel database.");
        }
    }
    
}
