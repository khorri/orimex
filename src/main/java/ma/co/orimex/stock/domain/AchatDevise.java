package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AchatDevise.
 */
@Entity
@Table(name = "achat_devise")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatdevise")
public class AchatDevise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_devise")
    private Integer idDevise;

    @Column(name = "code_devise")
    private String codeDevise;

    @Column(name = "libelle_devise")
    private String libelleDevise;

    @OneToMany(mappedBy = "achatDevise")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AchatDossier> achatDossiers = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdDevise() {
        return idDevise;
    }

    public AchatDevise idDevise(Integer idDevise) {
        this.idDevise = idDevise;
        return this;
    }

    public void setIdDevise(Integer idDevise) {
        this.idDevise = idDevise;
    }

    public String getCodeDevise() {
        return codeDevise;
    }

    public AchatDevise codeDevise(String codeDevise) {
        this.codeDevise = codeDevise;
        return this;
    }

    public void setCodeDevise(String codeDevise) {
        this.codeDevise = codeDevise;
    }

    public String getLibelleDevise() {
        return libelleDevise;
    }

    public AchatDevise libelleDevise(String libelleDevise) {
        this.libelleDevise = libelleDevise;
        return this;
    }

    public void setLibelleDevise(String libelleDevise) {
        this.libelleDevise = libelleDevise;
    }

    public Set<AchatDossier> getAchatDossiers() {
        return achatDossiers;
    }

    public AchatDevise achatDossiers(Set<AchatDossier> achatDossiers) {
        this.achatDossiers = achatDossiers;
        return this;
    }

    public AchatDevise addAchatDossier(AchatDossier achatDossier) {
        this.achatDossiers.add(achatDossier);
        achatDossier.setAchatDevise(this);
        return this;
    }

    public AchatDevise removeAchatDossier(AchatDossier achatDossier) {
        this.achatDossiers.remove(achatDossier);
        achatDossier.setAchatDevise(null);
        return this;
    }

    public void setAchatDossiers(Set<AchatDossier> achatDossiers) {
        this.achatDossiers = achatDossiers;
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
        AchatDevise achatDevise = (AchatDevise) o;
        if (achatDevise.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatDevise.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatDevise{" +
            "id=" + getId() +
            ", idDevise=" + getIdDevise() +
            ", codeDevise='" + getCodeDevise() + "'" +
            ", libelleDevise='" + getLibelleDevise() + "'" +
            "}";
    }
}
