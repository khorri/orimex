package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AchatConteneurReception.
 */
@Entity
@Table(name = "achat_conteneur_reception")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatconteneurreception")
public class AchatConteneurReception implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_conteneur_reception")
    private Integer idConteneurReception;

    @Column(name = "numero_conteneur")
    private String numeroConteneur;

    @Column(name = "numero_sequence")
    private Integer numeroSequence;

    @OneToOne    @JoinColumn(unique = true)
    private AchatConteneurArrivage achatConteneurArrivage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdConteneurReception() {
        return idConteneurReception;
    }

    public AchatConteneurReception idConteneurReception(Integer idConteneurReception) {
        this.idConteneurReception = idConteneurReception;
        return this;
    }

    public void setIdConteneurReception(Integer idConteneurReception) {
        this.idConteneurReception = idConteneurReception;
    }

    public String getNumeroConteneur() {
        return numeroConteneur;
    }

    public AchatConteneurReception numeroConteneur(String numeroConteneur) {
        this.numeroConteneur = numeroConteneur;
        return this;
    }

    public void setNumeroConteneur(String numeroConteneur) {
        this.numeroConteneur = numeroConteneur;
    }

    public Integer getNumeroSequence() {
        return numeroSequence;
    }

    public AchatConteneurReception numeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
        return this;
    }

    public void setNumeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
    }

    public AchatConteneurArrivage getAchatConteneurArrivage() {
        return achatConteneurArrivage;
    }

    public AchatConteneurReception achatConteneurArrivage(AchatConteneurArrivage achatConteneurArrivage) {
        this.achatConteneurArrivage = achatConteneurArrivage;
        return this;
    }

    public void setAchatConteneurArrivage(AchatConteneurArrivage achatConteneurArrivage) {
        this.achatConteneurArrivage = achatConteneurArrivage;
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
        AchatConteneurReception achatConteneurReception = (AchatConteneurReception) o;
        if (achatConteneurReception.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatConteneurReception.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatConteneurReception{" +
            "id=" + getId() +
            ", idConteneurReception=" + getIdConteneurReception() +
            ", numeroConteneur='" + getNumeroConteneur() + "'" +
            ", numeroSequence=" + getNumeroSequence() +
            "}";
    }
}
