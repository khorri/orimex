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
 * A Sortie.
 */
@Entity
@Table(name = "sortie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "sortie")
public class Sortie implements Serializable {

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
    @JsonIgnoreProperties("sorties")
    private Utilisateur utilisateur;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Bon bon;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Caisse caisse;

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

    public Sortie idOperation(Integer idOperation) {
        this.idOperation = idOperation;
        return this;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Instant getDateOperation() {
        return dateOperation;
    }

    public Sortie dateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Integer getNombrePlateaux() {
        return nombrePlateaux;
    }

    public Sortie nombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
        return this;
    }

    public void setNombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
    }

    public String getNumeroOperation() {
        return numeroOperation;
    }

    public Sortie numeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
        return this;
    }

    public void setNumeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Sortie utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Bon getBon() {
        return bon;
    }

    public Sortie bon(Bon bon) {
        this.bon = bon;
        return this;
    }

    public void setBon(Bon bon) {
        this.bon = bon;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public Sortie caisse(Caisse caisse) {
        this.caisse = caisse;
        return this;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }

    public Depot getDepot() {
        return depot;
    }

    public Sortie depot(Depot depot) {
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
        Sortie sortie = (Sortie) o;
        if (sortie.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sortie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sortie{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateOperation='" + getDateOperation() + "'" +
            ", nombrePlateaux=" + getNombrePlateaux() +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            "}";
    }
}
