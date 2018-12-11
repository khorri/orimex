package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AchatConteneurArrivage.
 */
@Entity
@Table(name = "achat_conteneur_arrivage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatconteneurarrivage")
public class AchatConteneurArrivage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_conteneur_arrivage")
    private Integer idConteneurArrivage;

    @Column(name = "montant", precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(name = "numero_conteneurs")
    private String numeroConteneurs;

    @Column(name = "numero_sequence")
    private Integer numeroSequence;

    @Column(name = "poids", precision = 10, scale = 2)
    private BigDecimal poids;

    @Column(name = "quantite", precision = 10, scale = 2)
    private BigDecimal quantite;

    @ManyToOne
    @JsonIgnoreProperties("achatConteneurArrivages")
    private AchatFacture achatFacture;

    @OneToOne    @JoinColumn(unique = true)
    private StockConteneurReception stockConteneurReception;

    @OneToMany(mappedBy = "achatConteneurArrivage")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AchatArticleConteneurArrivage> achatArticleConteneurArrivages = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdConteneurArrivage() {
        return idConteneurArrivage;
    }

    public AchatConteneurArrivage idConteneurArrivage(Integer idConteneurArrivage) {
        this.idConteneurArrivage = idConteneurArrivage;
        return this;
    }

    public void setIdConteneurArrivage(Integer idConteneurArrivage) {
        this.idConteneurArrivage = idConteneurArrivage;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public AchatConteneurArrivage montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getNumeroConteneurs() {
        return numeroConteneurs;
    }

    public AchatConteneurArrivage numeroConteneurs(String numeroConteneurs) {
        this.numeroConteneurs = numeroConteneurs;
        return this;
    }

    public void setNumeroConteneurs(String numeroConteneurs) {
        this.numeroConteneurs = numeroConteneurs;
    }

    public Integer getNumeroSequence() {
        return numeroSequence;
    }

    public AchatConteneurArrivage numeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
        return this;
    }

    public void setNumeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public AchatConteneurArrivage poids(BigDecimal poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public AchatConteneurArrivage quantite(BigDecimal quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public AchatFacture getAchatFacture() {
        return achatFacture;
    }

    public AchatConteneurArrivage achatFacture(AchatFacture achatFacture) {
        this.achatFacture = achatFacture;
        return this;
    }

    public void setAchatFacture(AchatFacture achatFacture) {
        this.achatFacture = achatFacture;
    }

    public StockConteneurReception getStockConteneurReception() {
        return stockConteneurReception;
    }

    public AchatConteneurArrivage stockConteneurReception(StockConteneurReception stockConteneurReception) {
        this.stockConteneurReception = stockConteneurReception;
        return this;
    }

    public void setStockConteneurReception(StockConteneurReception stockConteneurReception) {
        this.stockConteneurReception = stockConteneurReception;
    }

    public Set<AchatArticleConteneurArrivage> getAchatArticleConteneurArrivages() {
        return achatArticleConteneurArrivages;
    }

    public AchatConteneurArrivage achatArticleConteneurArrivages(Set<AchatArticleConteneurArrivage> achatArticleConteneurArrivages) {
        this.achatArticleConteneurArrivages = achatArticleConteneurArrivages;
        return this;
    }

    public AchatConteneurArrivage addAchatArticleConteneurArrivage(AchatArticleConteneurArrivage achatArticleConteneurArrivage) {
        this.achatArticleConteneurArrivages.add(achatArticleConteneurArrivage);
        achatArticleConteneurArrivage.setAchatConteneurArrivage(this);
        return this;
    }

    public AchatConteneurArrivage removeAchatArticleConteneurArrivage(AchatArticleConteneurArrivage achatArticleConteneurArrivage) {
        this.achatArticleConteneurArrivages.remove(achatArticleConteneurArrivage);
        achatArticleConteneurArrivage.setAchatConteneurArrivage(null);
        return this;
    }

    public void setAchatArticleConteneurArrivages(Set<AchatArticleConteneurArrivage> achatArticleConteneurArrivages) {
        this.achatArticleConteneurArrivages = achatArticleConteneurArrivages;
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
        AchatConteneurArrivage achatConteneurArrivage = (AchatConteneurArrivage) o;
        if (achatConteneurArrivage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatConteneurArrivage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatConteneurArrivage{" +
            "id=" + getId() +
            ", idConteneurArrivage=" + getIdConteneurArrivage() +
            ", montant=" + getMontant() +
            ", numeroConteneurs='" + getNumeroConteneurs() + "'" +
            ", numeroSequence=" + getNumeroSequence() +
            ", poids=" + getPoids() +
            ", quantite=" + getQuantite() +
            "}";
    }
}
