package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Camion.
 */
@Entity
@Table(name = "camion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "camion")
public class Camion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_camion")
    private Integer idCamion;

    @Column(name = "numero_camion")
    private String numeroCamion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCamion() {
        return idCamion;
    }

    public Camion idCamion(Integer idCamion) {
        this.idCamion = idCamion;
        return this;
    }

    public void setIdCamion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public String getNumeroCamion() {
        return numeroCamion;
    }

    public Camion numeroCamion(String numeroCamion) {
        this.numeroCamion = numeroCamion;
        return this;
    }

    public void setNumeroCamion(String numeroCamion) {
        this.numeroCamion = numeroCamion;
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
        Camion camion = (Camion) o;
        if (camion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), camion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Camion{" +
            "id=" + getId() +
            ", idCamion=" + getIdCamion() +
            ", numeroCamion='" + getNumeroCamion() + "'" +
            "}";
    }
}
