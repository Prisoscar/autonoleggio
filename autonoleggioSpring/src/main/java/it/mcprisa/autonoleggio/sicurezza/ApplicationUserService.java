
package it.mcprisa.autonoleggio.sicurezza;

import it.mcprisa.autonoleggio.model.Utente;
import it.mcprisa.autonoleggio.repository.RepUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private RepUtente repUtente;

    @Override
    //cerca l'utente dal database
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = repUtente.findByUsername(username);
       /* if (utente == null){
            throw new UsernameNotFoundException("Username non presente");
        }*/
        return new ApplicationUser(utente);
    }
}
