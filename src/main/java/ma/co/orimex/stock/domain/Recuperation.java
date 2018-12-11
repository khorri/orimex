package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A Recuperation.
 */
@Entity
@Table(name = "recuperation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "recuperation")
public class Recuperation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_operation")
    private Integer idOperation;

    @Column(name = "date_operation")
    private Instant dateOperation;

    @Column(name = "nombre_panneaux")
    private Integer nombrePanneaux;

    @Column(name = "numero_operation")
    private String numeroOperation;

    @Column(name = "superficie", precision = 10, scale = 2)
    private BigDecimal superficie;

    @ManyToOne
    @JsonIgnoreProperties("recuperations")
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Depot depot;

    @ManyToOne
    @JsonIgnoreProperties("recuperations")
    private Utilisateur utilisateur;

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

    public Recuperation idOperation(Integer idOperation) {
        this.idOperation = idOperation;
        return this;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Instant getDateOperation() {
        return dateOperation;
    }

    public Recuperation dateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Integer getNombrePanneaux() {
        return nombrePanneaux;
    }

    public Recuperation nombrePanneaux(Integer nombrePanneaux) {
        this.nombrePanneaux = nombrePanneaux;
        return this;
    }

    public void setNombrePanneaux(Integer nombrePanneaux) {
        this.nombrePanneaux = nombrePanneaux;
    }

    public String getNumeroOperation() {
        return numeroOperation;
    }

    public Recuperation numeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
        return this;
    }

    public void setNumeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
    }

    public BigDecimal getSuperficie() {
        return superficie;
    }

    public Recuperation superficie(BigDecimal superficie) {
        this.superficie = superficie;
        return this;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public Produit getProduit() {
        return produit;
    }

    public Recuperation produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Depot getDepot() {
        return depot;
    }

    public Recuperation depot(Depot depot) {
        this.depot = depot;
        return this;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Recuperation utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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
        Recuperation recuperation = (Recuperation) o;
        if (recuperation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recuperation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Recuperation{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateOperation='" + getDateOperation() + "'" +
            ", nombrePanneaux=" + getNombrePanneaux() +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            ", superficie=" + getSuperficie() +
            "}";
    }
}
