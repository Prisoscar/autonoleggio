package it.mcprisa.autonoleggio.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "intervento")
public class Intervento {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "data_inizio_intervento", nullable = false)
    private Date dataInizioIntervento;
    @Column(name = "data_fine_intervento", nullable = false)
    private Date dataFineIntervento;
    @Column(name = "costo_intervento", nullable = false)
    private double costoIntervento;
    
    @Enumerated(EnumType.STRING)
    private TipoIntervento tipoIntervento;
    
    @ManyToOne
    @JoinColumn(name = "id_collaboratore", nullable = false)
    private Collaboratore collaboratore;
    @ManyToOne
    @JoinColumn(name = "id_vettura", nullable = false)
    private Vettura vettura;

    public Intervento() {
    }

    public Intervento(String id, Date dataInizioIntervento, Date dataFineIntervento, double costoIntervento, TipoIntervento tipoIntervento, Collaboratore collaboratore, Vettura vettura) {
        this.id = id;
        this.dataInizioIntervento = dataInizioIntervento;
        this.dataFineIntervento = dataFineIntervento;
        this.costoIntervento = costoIntervento;
        this.tipoIntervento = tipoIntervento;
        this.collaboratore = collaboratore;
        this.vettura = vettura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataInizioIntervento() {
        return dataInizioIntervento;
    }

    public void setDataInizioIntervento(Date dataInizioIntervento) {
        this.dataInizioIntervento = dataInizioIntervento;
    }

    public Date getDataFineIntervento() {
        return dataFineIntervento;
    }

    public void setDataFineIntervento(Date dataFineIntervento) {
        this.dataFineIntervento = dataFineIntervento;
    }

    public double getCostoIntervento() {
        return costoIntervento;
    }

    public void setCostoIntervento(double costoIntervento) {
        this.costoIntervento = costoIntervento;
    }

    public TipoIntervento getTipoIntervento() {
        return tipoIntervento;
    }

    public void setTipoIntervento(TipoIntervento tipoIntervento) {
        this.tipoIntervento = tipoIntervento;
    }

    public Collaboratore getCollaboratore() {
        return collaboratore;
    }

    public void setCollaboratore(Collaboratore collaboratore) {
        this.collaboratore = collaboratore;
    }

    public Vettura getVettura() {
        return vettura;
    }

    public void setVettura(Vettura vettura) {
        this.vettura = vettura;
    }
    
}
