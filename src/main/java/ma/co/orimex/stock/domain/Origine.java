package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Origine.
 */
@Entity
@Table(name = "origine")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "origine")
public class Origine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_origine")
    private Integer idOrigine;

    @Column(name = "designation_origine")
    private String designationOrigine;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOrigine() {
        return idOrigine;
    }

    public Origine idOrigine(Integer idOrigine) {
        this.idOrigine = idOrigine;
        return this;
    }

    public void setIdOrigine(Integer idOrigine) {
        this.idOrigine = idOrigine;
    }

    public String getDesignationOrigine() {
        return designationOrigine;
    }

    public Origine designationOrigine(String designationOrigine) {
        this.designationOrigine = designationOrigine;
        return this;
    }

    public void setDesignationOrigine(String designationOrigine) {
        this.designationOrigine = designationOrigine;
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
        Origine origine = (Origine) o;
        if (origine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), origine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Origine{" +
            "id=" + getId() +
            ", idOrigine=" + getIdOrigine() +
            ", designationOrigine='" + getDesignationOrigine() + "'" +
            "}";
    }
}
