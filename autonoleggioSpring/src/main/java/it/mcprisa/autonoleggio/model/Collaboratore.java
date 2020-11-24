package it.mcprisa.autonoleggio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "collaboratore")
public class Collaboratore {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "partita_iva", nullable = false)
    private String partitaIva;
    
    @JsonIgnore
    @OneToMany(mappedBy = "collaboratore")
    private List<Intervento> interventi = new ArrayList<>();

    public Collaboratore() {
    }

    public Collaboratore(String id, String nome, String partitaIva) {
        this.id = id;
        this.nome = nome;
        this.partitaIva = partitaIva;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public List<Intervento> getInterventi() {
        return interventi;
    }

    public void setInterventi(List<Intervento> interventi) {
        this.interventi = interventi;
    }
    
}
