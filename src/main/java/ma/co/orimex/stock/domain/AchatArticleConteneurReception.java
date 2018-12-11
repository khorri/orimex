package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A AchatArticleConteneurReception.
 */
@Entity
@Table(name = "acht_art_conteneur_reception")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatarticleconteneurreception")
public class AchatArticleConteneurReception implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_article_conteneur_reception")
    private Integer idArticleConteneurReception;

    @Column(name = "dimension", precision = 10, scale = 2)
    private BigDecimal dimension;

    @Column(name = "nombre_caissestc")
    private Integer nombreCaissestc;

    @Column(name = "nombre_feuillecaisse")
    private Integer nombreFeuillecaisse;

    @ManyToOne
    @JsonIgnoreProperties("")
    private AchatConteneurReception achatConteneurReception;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Produit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdArticleConteneurReception() {
        return idArticleConteneurReception;
    }

    public AchatArticleConteneurReception idArticleConteneurReception(Integer idArticleConteneurReception) {
        this.idArticleConteneurReception = idArticleConteneurReception;
        return this;
    }

    public void setIdArticleConteneurReception(Integer idArticleConteneurReception) {
        this.idArticleConteneurReception = idArticleConteneurReception;
    }

    public BigDecimal getDimension() {
        return dimension;
    }

    public AchatArticleConteneurReception dimension(BigDecimal dimension) {
        this.dimension = dimension;
        return this;
    }

    public void setDimension(BigDecimal dimension) {
        this.dimension = dimension;
    }

    public Integer getNombreCaissestc() {
        return nombreCaissestc;
    }

    public AchatArticleConteneurReception nombreCaissestc(Integer nombreCaissestc) {
        this.nombreCaissestc = nombreCaissestc;
        return this;
    }

    public void setNombreCaissestc(Integer nombreCaissestc) {
        this.nombreCaissestc = nombreCaissestc;
    }

    public Integer getNombreFeuillecaisse() {
        return nombreFeuillecaisse;
    }

    public AchatArticleConteneurReception nombreFeuillecaisse(Integer nombreFeuillecaisse) {
        this.nombreFeuillecaisse = nombreFeuillecaisse;
        return this;
    }

    public void setNombreFeuillecaisse(Integer nombreFeuillecaisse) {
        this.nombreFeuillecaisse = nombreFeuillecaisse;
    }

    public AchatConteneurReception getAchatConteneurReception() {
        return achatConteneurReception;
    }

    public AchatArticleConteneurReception achatConteneurReception(AchatConteneurReception achatConteneurReception) {
        this.achatConteneurReception = achatConteneurReception;
        return this;
    }

    public void setAchatConteneurReception(AchatConteneurReception achatConteneurReception) {
        this.achatConteneurReception = achatConteneurReception;
    }

    public Produit getProduit() {
        return produit;
    }

    public AchatArticleConteneurReception produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
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
        AchatArticleConteneurReception achatArticleConteneurReception = (AchatArticleConteneurReception) o;
        if (achatArticleConteneurReception.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatArticleConteneurReception.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatArticleConteneurReception{" +
            "id=" + getId() +
            ", idArticleConteneurReception=" + getIdArticleConteneurReception() +
            ", dimension=" + getDimension() +
            ", nombreCaissestc=" + getNombreCaissestc() +
            ", nombreFeuillecaisse=" + getNombreFeuillecaisse() +
            "}";
    }
}
