package it.mcprisa.autonoleggio.serviziImplementazione;

import it.mcprisa.autonoleggio.eccezioni.CattivaRichiestaException;
import it.mcprisa.autonoleggio.model.Noleggio;
import it.mcprisa.autonoleggio.repository.RepNolerggio;
import it.mcprisa.autonoleggio.servizi.SrvNoleggio;
import it.mcprisa.autonoleggio.servizi.SrvVettura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SrvNoleggioImpl implements SrvNoleggio {

    @Autowired
    RepNolerggio repNoleggio;
    @Autowired
    SrvVettura srvVettura;

    @Override
    public List<Noleggio> lista() {
        return repNoleggio.findAll();
    }

    @Override
    public Noleggio inserisci(Noleggio noleggio) {
        //se nella richiesta è stato inserito un id valido viene lanciata una eccezione di CattivaRichiesta per evitare che il campo presente nel database venga sovrascritto
        try {
            repNoleggio.getOne(noleggio.getId());
            throw new CattivaRichiestaException("id già esistente, togliere l'id.");
        } catch (RuntimeException e) {
            //viene controllata la disponibilità nelle date indicate e in caso negativo viene lanciata un'eccezione
            if (!srvVettura.verificaDisponibilita(noleggio.getVettura(), noleggio.getDataInizioNoleggio(), noleggio.getDataFineNoleggio())) {
                throw new CattivaRichiestaException("La vettura non è disponibile nelle date indicate");
            }
            //se manca qualche attributo non nullabile viene lanciata una eccezione di CattivaRichiesta
            try {
                return repNoleggio.save(noleggio);
            } catch (RuntimeException e2) {
                throw new CattivaRichiestaException(
                        "assenza attributo obbligatorio, invia la richiesta inserendo tutti gli attributi obbligatori o controlla che i nomi degli attributi siano scritti correttamente"
                );
            }
        }
    }

    @Override
    public void elimina(Noleggio noleggio) {
        //in queste API non si fanno cancellazioni
        throw new CattivaRichiestaException("Operazione non consentita!");
    }

    @Override
    public Noleggio modifica(Noleggio noleggio) {
        //si cerca se il noleggio inviato esista effettivamente nel datatbase
        try {
            repNoleggio.getOne(noleggio.getId());
            //viene controllata la disponibilità nelle date indicate e in caso negativo viene lanciata un'eccezione
            if (!srvVettura.verificaDisponibilita(noleggio.getVettura(), noleggio.getDataInizioNoleggio(), noleggio.getDataFineNoleggio())) {
                throw new CattivaRichiestaException("La vettura non è disponibile nelle date indicate");
            }
            return repNoleggio.save(noleggio);
            //se il noleggio non è presente nel database viene lanciata una eccezione di CattivaRichiesta
        } catch (RuntimeException e) {
            throw new CattivaRichiestaException("Noleggio non presente nel database.");
        }
    }
}
