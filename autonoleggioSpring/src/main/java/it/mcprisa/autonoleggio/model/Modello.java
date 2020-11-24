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
@Table(name = "modello")
public class Modello {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "modello", nullable = false)
    private String modello;
    
    @JsonIgnore
    @OneToMany(mappedBy = "modello")
    private List<Vettura> vetture = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;

    public Modello() {
    }

    public Modello(String id, String modello, Marca marca) {
        this.id = id;
        this.modello = modello;
        this.marca = marca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public List<Vettura> getVetture() {
        return vetture;
    }

    public void setVetture(List<Vettura> vetture) {
        this.vetture = vetture;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setVettura(Marca marca) {
        this.marca = marca;
    }
    
}
