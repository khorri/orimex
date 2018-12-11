package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TypeBon.
 */
@Entity
@Table(name = "type_bon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "typebon")
public class TypeBon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_type_bon")
    private Integer idTypeBon;

    @Column(name = "libelle_type_bon")
    private String libelleTypeBon;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTypeBon() {
        return idTypeBon;
    }

    public TypeBon idTypeBon(Integer idTypeBon) {
        this.idTypeBon = idTypeBon;
        return this;
    }

    public void setIdTypeBon(Integer idTypeBon) {
        this.idTypeBon = idTypeBon;
    }

    public String getLibelleTypeBon() {
        return libelleTypeBon;
    }

    public TypeBon libelleTypeBon(String libelleTypeBon) {
        this.libelleTypeBon = libelleTypeBon;
        return this;
    }

    public void setLibelleTypeBon(String libelleTypeBon) {
        this.libelleTypeBon = libelleTypeBon;
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
        TypeBon typeBon = (TypeBon) o;
        if (typeBon.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeBon.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeBon{" +
            "id=" + getId() +
            ", idTypeBon=" + getIdTypeBon() +
            ", libelleTypeBon='" + getLibelleTypeBon() + "'" +
            "}";
    }
}
