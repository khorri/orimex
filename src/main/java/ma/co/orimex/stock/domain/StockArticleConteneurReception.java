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
 * A StockArticleConteneurReception.
 */
@Entity
@Table(name = "article_conteneur_reception")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "stockarticleconteneurreception")
public class StockArticleConteneurReception implements Serializable {

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

    @Column(name = "is_non_confrome")
    private Integer isNonConfrome;

    @OneToOne    @JoinColumn(unique = true)
    private AchatArticleConteneurArrivage achatArticleConteneurArrivage;

    @ManyToOne
    @JsonIgnoreProperties("stockArticleConteneurReceptions")
    private StockConteneurReception stockConteneurReception;

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

    public StockArticleConteneurReception idArticleConteneurReception(Integer idArticleConteneurReception) {
        this.idArticleConteneurReception = idArticleConteneurReception;
        return this;
    }

    public void setIdArticleConteneurReception(Integer idArticleConteneurReception) {
        this.idArticleConteneurReception = idArticleConteneurReception;
    }

    public BigDecimal getDimension() {
        return dimension;
    }

    public StockArticleConteneurReception dimension(BigDecimal dimension) {
        this.dimension = dimension;
        return this;
    }

    public void setDimension(BigDecimal dimension) {
        this.dimension = dimension;
    }

    public Integer getNombreCaissestc() {
        return nombreCaissestc;
    }

    public StockArticleConteneurReception nombreCaissestc(Integer nombreCaissestc) {
        this.nombreCaissestc = nombreCaissestc;
        return this;
    }

    public void setNombreCaissestc(Integer nombreCaissestc) {
        this.nombreCaissestc = nombreCaissestc;
    }

    public Integer getNombreFeuillecaisse() {
        return nombreFeuillecaisse;
    }

    public StockArticleConteneurReception nombreFeuillecaisse(Integer nombreFeuillecaisse) {
        this.nombreFeuillecaisse = nombreFeuillecaisse;
        return this;
    }

    public void setNombreFeuillecaisse(Integer nombreFeuillecaisse) {
        this.nombreFeuillecaisse = nombreFeuillecaisse;
    }

    public Integer getIsNonConfrome() {
        return isNonConfrome;
    }

    public StockArticleConteneurReception isNonConfrome(Integer isNonConfrome) {
        this.isNonConfrome = isNonConfrome;
        return this;
    }

    public void setIsNonConfrome(Integer isNonConfrome) {
        this.isNonConfrome = isNonConfrome;
    }

    public AchatArticleConteneurArrivage getAchatArticleConteneurArrivage() {
        return achatArticleConteneurArrivage;
    }

    public StockArticleConteneurReception achatArticleConteneurArrivage(AchatArticleConteneurArrivage achatArticleConteneurArrivage) {
        this.achatArticleConteneurArrivage = achatArticleConteneurArrivage;
        return this;
    }

    public void setAchatArticleConteneurArrivage(AchatArticleConteneurArrivage achatArticleConteneurArrivage) {
        this.achatArticleConteneurArrivage = achatArticleConteneurArrivage;
    }

    public StockConteneurReception getStockConteneurReception() {
        return stockConteneurReception;
    }

    public StockArticleConteneurReception stockConteneurReception(StockConteneurReception stockConteneurReception) {
        this.stockConteneurReception = stockConteneurReception;
        return this;
    }

    public void setStockConteneurReception(StockConteneurReception stockConteneurReception) {
        this.stockConteneurReception = stockConteneurReception;
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
        StockArticleConteneurReception stockArticleConteneurReception = (StockArticleConteneurReception) o;
        if (stockArticleConteneurReception.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stockArticleConteneurReception.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StockArticleConteneurReception{" +
            "id=" + getId() +
            ", idArticleConteneurReception=" + getIdArticleConteneurReception() +
            ", dimension=" + getDimension() +
            ", nombreCaissestc=" + getNombreCaissestc() +
            ", nombreFeuillecaisse=" + getNombreFeuillecaisse() +
            ", isNonConfrome=" + getIsNonConfrome() +
            "}";
    }
}
