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
 * A AchatBanque.
 */
@Entity
@Table(name = "achat_banque")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatbanque")
public class AchatBanque implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_banque")
    private Integer idBanque;

    @Column(name = "code_banque")
    private String codeBanque;

    @Column(name = "designation_banque")
    private String designationBanque;

    @OneToMany(mappedBy = "achatBanque")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AchatDossier> achatDossiers = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdBanque() {
        return idBanque;
    }

    public AchatBanque idBanque(Integer idBanque) {
        this.idBanque = idBanque;
        return this;
    }

    public void setIdBanque(Integer idBanque) {
        this.idBanque = idBanque;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public AchatBanque codeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
        return this;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getDesignationBanque() {
        return designationBanque;
    }

    public AchatBanque designationBanque(String designationBanque) {
        this.designationBanque = designationBanque;
        return this;
    }

    public void setDesignationBanque(String designationBanque) {
        this.designationBanque = designationBanque;
    }

    public Set<AchatDossier> getAchatDossiers() {
        return achatDossiers;
    }

    public AchatBanque achatDossiers(Set<AchatDossier> achatDossiers) {
        this.achatDossiers = achatDossiers;
        return this;
    }

    public AchatBanque addAchatDossier(AchatDossier achatDossier) {
        this.achatDossiers.add(achatDossier);
        achatDossier.setAchatBanque(this);
        return this;
    }

    public AchatBanque removeAchatDossier(AchatDossier achatDossier) {
        this.achatDossiers.remove(achatDossier);
        achatDossier.setAchatBanque(null);
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
        AchatBanque achatBanque = (AchatBanque) o;
        if (achatBanque.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatBanque.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatBanque{" +
            "id=" + getId() +
            ", idBanque=" + getIdBanque() +
            ", codeBanque='" + getCodeBanque() + "'" +
            ", designationBanque='" + getDesignationBanque() + "'" +
            "}";
    }
}
