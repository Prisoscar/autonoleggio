package it.mcprisa.autonoleggio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.mcprisa.autonoleggio.sicurezza.RuoloUtente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "abilitato", nullable = false)
    private Boolean abilitato;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cognome", nullable = false)
    private String cognome;
    
    @Enumerated(EnumType.STRING)
    private RuoloUtente ruoloUtente;
    
    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Noleggio> noleggi = new ArrayList<>();

    public Utente() {
    }

    public Utente(String id, String username, String password, Boolean abilitato, String nome, String cognome, RuoloUtente ruoloUtente) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.abilitato = abilitato;
        this.nome = nome;
        this.cognome = cognome;
        this.ruoloUtente = ruoloUtente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAbilitato() {
        return abilitato;
    }

    public void setAbilitato(Boolean abilitato) {
        this.abilitato = abilitato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public RuoloUtente getRuoloUtente() {
        return ruoloUtente;
    }

    public void setRuoloUtente(RuoloUtente ruoloUtente) {
        this.ruoloUtente = ruoloUtente;
    }

    public List<Noleggio> getNoleggi() {
        return noleggi;
    }

    public void setNoleggi(List<Noleggio> noleggi) {
        this.noleggi = noleggi;
    }
    
    
}
