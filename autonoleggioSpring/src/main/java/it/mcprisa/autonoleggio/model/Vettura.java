package it.mcprisa.autonoleggio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "vettura")
public class Vettura {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "targa")
    private String targa;
    
    @ManyToOne
    @JoinColumn(name = "id_modello", nullable = false)
    private Modello modello;
    
    @JsonIgnore
    @OneToMany(mappedBy = "vettura")
    private List<Noleggio> noleggi = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "vettura")
    private List<Intervento> interventi = new ArrayList<>();

    public Vettura() {
    }

    public Vettura(String id, String targa, Modello modello) {
        this.id = id;
        this.targa = targa;
        this.modello = modello;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public Modello getModello() {
        return modello;
    }

    public void setModello(Modello modello) {
        this.modello = modello;
    }

    public List<Noleggio> getNoleggi() {
        return noleggi;
    }

    public void setNoleggi(List<Noleggio> noleggi) {
        this.noleggi = noleggi;
    }

    public List<Intervento> getInterventi() {
        return interventi;
    }

    public void setInterventi(List<Intervento> interventi) {
        this.interventi = interventi;
    }

    
    
}
