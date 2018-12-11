package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Depot.
 */
@Entity
@Table(name = "depot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "depot")
public class Depot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_depot")
    private Integer idDepot;

    @Column(name = "reference_depot")
    private String referenceDepot;

    @Column(name = "utilisateur_depots")
    private String utilisateurDepots;

    @ManyToOne
    @JsonIgnoreProperties("depots")
    private Ville ville;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdDepot() {
        return idDepot;
    }

    public Depot idDepot(Integer idDepot) {
        this.idDepot = idDepot;
        return this;
    }

    public void setIdDepot(Integer idDepot) {
        this.idDepot = idDepot;
    }

    public String getReferenceDepot() {
        return referenceDepot;
    }

    public Depot referenceDepot(String referenceDepot) {
        this.referenceDepot = referenceDepot;
        return this;
    }

    public void setReferenceDepot(String referenceDepot) {
        this.referenceDepot = referenceDepot;
    }

    public String getUtilisateurDepots() {
        return utilisateurDepots;
    }

    public Depot utilisateurDepots(String utilisateurDepots) {
        this.utilisateurDepots = utilisateurDepots;
        return this;
    }

    public void setUtilisateurDepots(String utilisateurDepots) {
        this.utilisateurDepots = utilisateurDepots;
    }

    public Ville getVille() {
        return ville;
    }

    public Depot ville(Ville ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
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
        Depot depot = (Depot) o;
        if (depot.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), depot.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Depot{" +
            "id=" + getId() +
            ", idDepot=" + getIdDepot() +
            ", referenceDepot='" + getReferenceDepot() + "'" +
            ", utilisateurDepots='" + getUtilisateurDepots() + "'" +
            "}";
    }
}
