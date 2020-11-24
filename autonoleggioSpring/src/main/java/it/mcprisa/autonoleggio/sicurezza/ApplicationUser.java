package it.mcprisa.autonoleggio.sicurezza;

import it.mcprisa.autonoleggio.model.Utente;
import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class ApplicationUser implements UserDetails {

    private Utente utente;

    public ApplicationUser(Utente utente) {
        this.utente = utente;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of roles (ROLE_name)
            GrantedAuthority authority = new SimpleGrantedAuthority(this.utente.getRuoloUtente().toString());
            authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.utente.getPassword();
    }

    @Override
    public String getUsername() {
        return this.utente.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.utente.getAbilitato();
    }
}
