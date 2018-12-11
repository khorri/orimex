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
 * A Bon.
 */
@Entity
@Table(name = "bon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "bon")
public class Bon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_bon")
    private Integer idBon;

    @Column(name = "id_type_bon")
    private Integer idTypeBon;

    @Column(name = "numero_bon")
    private String numeroBon;

    @OneToMany(mappedBy = "bon")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Retour> retours = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdBon() {
        return idBon;
    }

    public Bon idBon(Integer idBon) {
        this.idBon = idBon;
        return this;
    }

    public void setIdBon(Integer idBon) {
        this.idBon = idBon;
    }

    public Integer getIdTypeBon() {
        return idTypeBon;
    }

    public Bon idTypeBon(Integer idTypeBon) {
        this.idTypeBon = idTypeBon;
        return this;
    }

    public void setIdTypeBon(Integer idTypeBon) {
        this.idTypeBon = idTypeBon;
    }

    public String getNumeroBon() {
        return numeroBon;
    }

    public Bon numeroBon(String numeroBon) {
        this.numeroBon = numeroBon;
        return this;
    }

    public void setNumeroBon(String numeroBon) {
        this.numeroBon = numeroBon;
    }

    public Set<Retour> getRetours() {
        return retours;
    }

    public Bon retours(Set<Retour> retours) {
        this.retours = retours;
        return this;
    }

    public Bon addRetour(Retour retour) {
        this.retours.add(retour);
        retour.setBon(this);
        return this;
    }

    public Bon removeRetour(Retour retour) {
        this.retours.remove(retour);
        retour.setBon(null);
        return this;
    }

    public void setRetours(Set<Retour> retours) {
        this.retours = retours;
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
        Bon bon = (Bon) o;
        if (bon.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bon.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bon{" +
            "id=" + getId() +
            ", idBon=" + getIdBon() +
            ", idTypeBon=" + getIdTypeBon() +
            ", numeroBon='" + getNumeroBon() + "'" +
            "}";
    }
}
