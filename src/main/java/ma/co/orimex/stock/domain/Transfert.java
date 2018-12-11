package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Transfert.
 */
@Entity
@Table(name = "transfert")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "transfert")
public class Transfert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_operation")
    private Integer idOperation;

    @Column(name = "date_operation")
    private Instant dateOperation;

    @Column(name = "nombre_plateaux")
    private Integer nombrePlateaux;

    @Column(name = "numero_operation")
    private String numeroOperation;

    @ManyToOne
    @JsonIgnoreProperties("transferts")
    private Utilisateur utilisateur;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Bon bon;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Caisse caisse;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Camion camion;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Conteneur conteneur;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Depot depot;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public Transfert idOperation(Integer idOperation) {
        this.idOperation = idOperation;
        return this;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Instant getDateOperation() {
        return dateOperation;
    }

    public Transfert dateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Integer getNombrePlateaux() {
        return nombrePlateaux;
    }

    public Transfert nombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
        return this;
    }

    public void setNombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
    }

    public String getNumeroOperation() {
        return numeroOperation;
    }

    public Transfert numeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
        return this;
    }

    public void setNumeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Transfert utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Bon getBon() {
        return bon;
    }

    public Transfert bon(Bon bon) {
        this.bon = bon;
        return this;
    }

    public void setBon(Bon bon) {
        this.bon = bon;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public Transfert caisse(Caisse caisse) {
        this.caisse = caisse;
        return this;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }

    public Camion getCamion() {
        return camion;
    }

    public Transfert camion(Camion camion) {
        this.camion = camion;
        return this;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public Conteneur getConteneur() {
        return conteneur;
    }

    public Transfert conteneur(Conteneur conteneur) {
        this.conteneur = conteneur;
        return this;
    }

    public void setConteneur(Conteneur conteneur) {
        this.conteneur = conteneur;
    }

    public Depot getDepot() {
        return depot;
    }

    public Transfert depot(Depot depot) {
        this.depot = depot;
        return this;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transfert transfert = (Transfert) o;
        if (transfert.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transfert.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Transfert{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateOperation='" + getDateOperation() + "'" +
            ", nombrePlateaux=" + getNombrePlateaux() +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            "}";
    }
}
