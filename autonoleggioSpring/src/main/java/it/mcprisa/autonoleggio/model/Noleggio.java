package it.mcprisa.autonoleggio.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "noleggio")
public class Noleggio {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "data_inizio_noleggio", nullable = false)
    private Date dataInizioNoleggio;
    @Column(name = "data_fine_noleggio", nullable = false)
    private Date dataFineNoleggio;
    @Column(name = "costo_noleggio", nullable = false)
    private double costoNoleggio;
    
    @ManyToOne
    @JoinColumn(name = "id_vettura", nullable = false)
    private Vettura vettura;
    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    public Noleggio() {
    }

    public Noleggio(String id, Date dataInizioNoleggio, Date dataFineNoleggio, double costoNoleggio, Vettura vettura, Utente utente) {
        this.id = id;
        this.dataInizioNoleggio = dataInizioNoleggio;
        this.dataFineNoleggio = dataFineNoleggio;
        this.costoNoleggio = costoNoleggio;
        this.vettura = vettura;
        this.utente = utente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataInizioNoleggio() {
        return dataInizioNoleggio;
    }

    public void setDataInizioNoleggio(Date dataInizioNoleggio) {
        this.dataInizioNoleggio = dataInizioNoleggio;
    }

    public Date getDataFineNoleggio() {
        return dataFineNoleggio;
    }

    public void setDataFineNoleggio(Date dataFineNoleggio) {
        this.dataFineNoleggio = dataFineNoleggio;
    }

    public double getCostoNoleggio() {
        return costoNoleggio;
    }

    public void setCostoNoleggio(double costoNoleggio) {
        this.costoNoleggio = costoNoleggio;
    }

    public Vettura getVettura() {
        return vettura;
    }

    public void setVettura(Vettura vettura) {
        this.vettura = vettura;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    
}
