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
 * A Ville.
 */
@Entity
@Table(name = "ville")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "ville")
public class Ville implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_ville")
    private Integer idVille;

    @Column(name = "libelle_ville")
    private String libelleVille;

    @OneToMany(mappedBy = "ville")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Depot> depots = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdVille() {
        return idVille;
    }

    public Ville idVille(Integer idVille) {
        this.idVille = idVille;
        return this;
    }

    public void setIdVille(Integer idVille) {
        this.idVille = idVille;
    }

    public String getLibelleVille() {
        return libelleVille;
    }

    public Ville libelleVille(String libelleVille) {
        this.libelleVille = libelleVille;
        return this;
    }

    public void setLibelleVille(String libelleVille) {
        this.libelleVille = libelleVille;
    }

    public Set<Depot> getDepots() {
        return depots;
    }

    public Ville depots(Set<Depot> depots) {
        this.depots = depots;
        return this;
    }

    public Ville addDepot(Depot depot) {
        this.depots.add(depot);
        depot.setVille(this);
        return this;
    }

    public Ville removeDepot(Depot depot) {
        this.depots.remove(depot);
        depot.setVille(null);
        return this;
    }

    public void setDepots(Set<Depot> depots) {
        this.depots = depots;
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
        Ville ville = (Ville) o;
        if (ville.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ville.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ville{" +
            "id=" + getId() +
            ", idVille=" + getIdVille() +
            ", libelleVille='" + getLibelleVille() + "'" +
            "}";
    }
}
