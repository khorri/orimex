package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A AchatLigneProforma.
 */
@Entity
@Table(name = "achat_ligne_proforma")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatligneproforma")
public class AchatLigneProforma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_ligne_proforma")
    private Integer idLigneProforma;

    @Column(name = "nombre_conteneurs")
    private Integer nombreConteneurs;

    @Column(name = "montant", precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(name = "numero_sequence")
    private Integer numeroSequence;

    @Column(name = "poids", precision = 10, scale = 2)
    private BigDecimal poids;

    @ManyToOne
    @JsonIgnoreProperties("achatLigneProformas")
    private AchatProforma achatProforma;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdLigneProforma() {
        return idLigneProforma;
    }

    public AchatLigneProforma idLigneProforma(Integer idLigneProforma) {
        this.idLigneProforma = idLigneProforma;
        return this;
    }

    public void setIdLigneProforma(Integer idLigneProforma) {
        this.idLigneProforma = idLigneProforma;
    }

    public Integer getNombreConteneurs() {
        return nombreConteneurs;
    }

    public AchatLigneProforma nombreConteneurs(Integer nombreConteneurs) {
        this.nombreConteneurs = nombreConteneurs;
        return this;
    }

    public void setNombreConteneurs(Integer nombreConteneurs) {
        this.nombreConteneurs = nombreConteneurs;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public AchatLigneProforma montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Integer getNumeroSequence() {
        return numeroSequence;
    }

    public AchatLigneProforma numeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
        return this;
    }

    public void setNumeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public AchatLigneProforma poids(BigDecimal poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public AchatProforma getAchatProforma() {
        return achatProforma;
    }

    public AchatLigneProforma achatProforma(AchatProforma achatProforma) {
        this.achatProforma = achatProforma;
        return this;
    }

    public void setAchatProforma(AchatProforma achatProforma) {
        this.achatProforma = achatProforma;
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
        AchatLigneProforma achatLigneProforma = (AchatLigneProforma) o;
        if (achatLigneProforma.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatLigneProforma.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatLigneProforma{" +
            "id=" + getId() +
            ", idLigneProforma=" + getIdLigneProforma() +
            ", nombreConteneurs=" + getNombreConteneurs() +
            ", montant=" + getMontant() +
            ", numeroSequence=" + getNumeroSequence() +
            ", poids=" + getPoids() +
            "}";
    }
}
