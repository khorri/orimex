package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Couleur.
 */
@Entity
@Table(name = "couleur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "couleur")
public class Couleur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_couleur")
    private Integer idCouleur;

    @Column(name = "code_html")
    private String codeHtml;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCouleur() {
        return idCouleur;
    }

    public Couleur idCouleur(Integer idCouleur) {
        this.idCouleur = idCouleur;
        return this;
    }

    public void setIdCouleur(Integer idCouleur) {
        this.idCouleur = idCouleur;
    }

    public String getCodeHtml() {
        return codeHtml;
    }

    public Couleur codeHtml(String codeHtml) {
        this.codeHtml = codeHtml;
        return this;
    }

    public void setCodeHtml(String codeHtml) {
        this.codeHtml = codeHtml;
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
        Couleur couleur = (Couleur) o;
        if (couleur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), couleur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Couleur{" +
            "id=" + getId() +
            ", idCouleur=" + getIdCouleur() +
            ", codeHtml='" + getCodeHtml() + "'" +
            "}";
    }
}
