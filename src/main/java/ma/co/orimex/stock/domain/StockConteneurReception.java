package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A StockConteneurReception.
 */
@Entity
@Table(name = "stock_conteneur_reception")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "stockconteneurreception")
public class StockConteneurReception implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_operation")
    private Integer idOperation;

    @Column(name = "date_reception")
    private LocalDate dateReception;

    @Column(name = "numero_operation")
    private String numeroOperation;

    @Column(name = "numero_bon_entree")
    private String numeroBonEntree;

    @Column(name = "numero_constat_non_conformite")
    private String numeroConstatNonConformite;

    @Column(name = "is_valide")
    private Integer isValide;

    @OneToOne    @JoinColumn(unique = true)
    private StockReception stockReception;

    @OneToMany(mappedBy = "stockConteneurReception")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<StockArticleConteneurReception> stockArticleConteneurReceptions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public StockConteneurReception idOperation(Integer idOperation) {
        this.idOperation = idOperation;
        return this;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public LocalDate getDateReception() {
        return dateReception;
    }

    public StockConteneurReception dateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
        return this;
    }

    public void setDateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
    }

    public String getNumeroOperation() {
        return numeroOperation;
    }

    public StockConteneurReception numeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
        return this;
    }

    public void setNumeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
    }

    public String getNumeroBonEntree() {
        return numeroBonEntree;
    }

    public StockConteneurReception numeroBonEntree(String numeroBonEntree) {
        this.numeroBonEntree = numeroBonEntree;
        return this;
    }

    public void setNumeroBonEntree(String numeroBonEntree) {
        this.numeroBonEntree = numeroBonEntree;
    }

    public String getNumeroConstatNonConformite() {
        return numeroConstatNonConformite;
    }

    public StockConteneurReception numeroConstatNonConformite(String numeroConstatNonConformite) {
        this.numeroConstatNonConformite = numeroConstatNonConformite;
        return this;
    }

    public void setNumeroConstatNonConformite(String numeroConstatNonConformite) {
        this.numeroConstatNonConformite = numeroConstatNonConformite;
    }

    public Integer getIsValide() {
        return isValide;
    }

    public StockConteneurReception isValide(Integer isValide) {
        this.isValide = isValide;
        return this;
    }

    public void setIsValide(Integer isValide) {
        this.isValide = isValide;
    }

    public StockReception getStockReception() {
        return stockReception;
    }

    public StockConteneurReception stockReception(StockReception stockReception) {
        this.stockReception = stockReception;
        return this;
    }

    public void setStockReception(StockReception stockReception) {
        this.stockReception = stockReception;
    }

    public Set<StockArticleConteneurReception> getStockArticleConteneurReceptions() {
        return stockArticleConteneurReceptions;
    }

    public StockConteneurReception stockArticleConteneurReceptions(Set<StockArticleConteneurReception> stockArticleConteneurReceptions) {
        this.stockArticleConteneurReceptions = stockArticleConteneurReceptions;
        return this;
    }

    public StockConteneurReception addStockArticleConteneurReception(StockArticleConteneurReception stockArticleConteneurReception) {
        this.stockArticleConteneurReceptions.add(stockArticleConteneurReception);
        stockArticleConteneurReception.setStockConteneurReception(this);
        return this;
    }

    public StockConteneurReception removeStockArticleConteneurReception(StockArticleConteneurReception stockArticleConteneurReception) {
        this.stockArticleConteneurReceptions.remove(stockArticleConteneurReception);
        stockArticleConteneurReception.setStockConteneurReception(null);
        return this;
    }

    public void setStockArticleConteneurReceptions(Set<StockArticleConteneurReception> stockArticleConteneurReceptions) {
        this.stockArticleConteneurReceptions = stockArticleConteneurReceptions;
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
        StockConteneurReception stockConteneurReception = (StockConteneurReception) o;
        if (stockConteneurReception.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stockConteneurReception.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StockConteneurReception{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateReception='" + getDateReception() + "'" +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            ", numeroBonEntree='" + getNumeroBonEntree() + "'" +
            ", numeroConstatNonConformite='" + getNumeroConstatNonConformite() + "'" +
            ", isValide=" + getIsValide() +
            "}";
    }
}
