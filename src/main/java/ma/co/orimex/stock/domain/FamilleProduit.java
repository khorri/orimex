package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A FamilleProduit.
 */
@Entity
@Table(name = "famille_produit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "familleproduit")
public class FamilleProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_famille")
    private Integer idFamille;

    @Column(name = "designation_famille")
    private String designationFamille;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdFamille() {
        return idFamille;
    }

    public FamilleProduit idFamille(Integer idFamille) {
        this.idFamille = idFamille;
        return this;
    }

    public void setIdFamille(Integer idFamille) {
        this.idFamille = idFamille;
    }

    public String getDesignationFamille() {
        return designationFamille;
    }

    public FamilleProduit designationFamille(String designationFamille) {
        this.designationFamille = designationFamille;
        return this;
    }

    public void setDesignationFamille(String designationFamille) {
        this.designationFamille = designationFamille;
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
        FamilleProduit familleProduit = (FamilleProduit) o;
        if (familleProduit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), familleProduit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FamilleProduit{" +
            "id=" + getId() +
            ", idFamille=" + getIdFamille() +
            ", designationFamille='" + getDesignationFamille() + "'" +
            "}";
    }
}
