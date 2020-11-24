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
@Table(name = "marca")
public class Marca {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "marca", nullable = false)
    private String marca;

    @JsonIgnore
    @OneToMany(mappedBy = "marca")
    private List<Modello> modelli = new ArrayList<>();
    
    public Marca() {
    }

    public Marca(String id, String marca) {
        this.id = id;
        this.marca = marca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public List<Modello> getModelli() {
        return modelli;
    }

    public void setModelli(List<Modello> modelli) {
        this.modelli = modelli;
    }
    
    
}
