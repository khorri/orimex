package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AchatFournisseur.
 */
@Entity
@Table(name = "achat_fournisseur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatfournisseur")
public class AchatFournisseur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "type_fournisseur")
    private String typeFournisseur;

    @Column(name = "code_fournisseur")
    private String codeFournisseur;

    @Column(name = "designation_fournisseur")
    private String designationFournisseur;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeFournisseur() {
        return typeFournisseur;
    }

    public AchatFournisseur typeFournisseur(String typeFournisseur) {
        this.typeFournisseur = typeFournisseur;
        return this;
    }

    public void setTypeFournisseur(String typeFournisseur) {
        this.typeFournisseur = typeFournisseur;
    }

    public String getCodeFournisseur() {
        return codeFournisseur;
    }

    public AchatFournisseur codeFournisseur(String codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
        return this;
    }

    public void setCodeFournisseur(String codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
    }

    public String getDesignationFournisseur() {
        return designationFournisseur;
    }

    public AchatFournisseur designationFournisseur(String designationFournisseur) {
        this.designationFournisseur = designationFournisseur;
        return this;
    }

    public void setDesignationFournisseur(String designationFournisseur) {
        this.designationFournisseur = designationFournisseur;
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
        AchatFournisseur achatFournisseur = (AchatFournisseur) o;
        if (achatFournisseur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatFournisseur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatFournisseur{" +
            "id=" + getId() +
            ", typeFournisseur='" + getTypeFournisseur() + "'" +
            ", codeFournisseur='" + getCodeFournisseur() + "'" +
            ", designationFournisseur='" + getDesignationFournisseur() + "'" +
            "}";
    }
}
