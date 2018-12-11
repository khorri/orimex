package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AchatTypePaiement.
 */
@Entity
@Table(name = "achat_type_paiement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achattypepaiement")
public class AchatTypePaiement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_type_paiement")
    private Integer idTypePaiement;

    @Column(name = "libelle_type_paiement")
    private String libelleTypePaiement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTypePaiement() {
        return idTypePaiement;
    }

    public AchatTypePaiement idTypePaiement(Integer idTypePaiement) {
        this.idTypePaiement = idTypePaiement;
        return this;
    }

    public void setIdTypePaiement(Integer idTypePaiement) {
        this.idTypePaiement = idTypePaiement;
    }

    public String getLibelleTypePaiement() {
        return libelleTypePaiement;
    }

    public AchatTypePaiement libelleTypePaiement(String libelleTypePaiement) {
        this.libelleTypePaiement = libelleTypePaiement;
        return this;
    }

    public void setLibelleTypePaiement(String libelleTypePaiement) {
        this.libelleTypePaiement = libelleTypePaiement;
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
        AchatTypePaiement achatTypePaiement = (AchatTypePaiement) o;
        if (achatTypePaiement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatTypePaiement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatTypePaiement{" +
            "id=" + getId() +
            ", idTypePaiement=" + getIdTypePaiement() +
            ", libelleTypePaiement='" + getLibelleTypePaiement() + "'" +
            "}";
    }
}
