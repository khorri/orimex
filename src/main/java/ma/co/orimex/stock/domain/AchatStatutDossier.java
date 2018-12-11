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
 * A AchatStatutDossier.
 */
@Entity
@Table(name = "achat_statut_dossier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatstatutdossier")
public class AchatStatutDossier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_statut_dossier")
    private Integer idStatutDossier;

    @Column(name = "libelle_statut_dossier")
    private String libelleStatutDossier;

    @OneToMany(mappedBy = "achatStatutDossier")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AchatDossier> achatDossiers = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdStatutDossier() {
        return idStatutDossier;
    }

    public AchatStatutDossier idStatutDossier(Integer idStatutDossier) {
        this.idStatutDossier = idStatutDossier;
        return this;
    }

    public void setIdStatutDossier(Integer idStatutDossier) {
        this.idStatutDossier = idStatutDossier;
    }

    public String getLibelleStatutDossier() {
        return libelleStatutDossier;
    }

    public AchatStatutDossier libelleStatutDossier(String libelleStatutDossier) {
        this.libelleStatutDossier = libelleStatutDossier;
        return this;
    }

    public void setLibelleStatutDossier(String libelleStatutDossier) {
        this.libelleStatutDossier = libelleStatutDossier;
    }

    public Set<AchatDossier> getAchatDossiers() {
        return achatDossiers;
    }

    public AchatStatutDossier achatDossiers(Set<AchatDossier> achatDossiers) {
        this.achatDossiers = achatDossiers;
        return this;
    }

    public AchatStatutDossier addAchatDossier(AchatDossier achatDossier) {
        this.achatDossiers.add(achatDossier);
        achatDossier.setAchatStatutDossier(this);
        return this;
    }

    public AchatStatutDossier removeAchatDossier(AchatDossier achatDossier) {
        this.achatDossiers.remove(achatDossier);
        achatDossier.setAchatStatutDossier(null);
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
        AchatStatutDossier achatStatutDossier = (AchatStatutDossier) o;
        if (achatStatutDossier.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatStatutDossier.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatStatutDossier{" +
            "id=" + getId() +
            ", idStatutDossier=" + getIdStatutDossier() +
            ", libelleStatutDossier='" + getLibelleStatutDossier() + "'" +
            "}";
    }
}
