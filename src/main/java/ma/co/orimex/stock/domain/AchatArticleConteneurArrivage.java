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
 * A AchatArticleConteneurArrivage.
 */
@Entity
@Table(name = "article_conteneur_arrivage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatarticleconteneurarrivage")
public class AchatArticleConteneurArrivage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_article_conteneur_arrivage")
    private Integer idArticleConteneurArrivage;

    @Column(name = "dimension", precision = 10, scale = 2)
    private BigDecimal dimension;

    @Column(name = "montant", precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(name = "nombre_caissestc")
    private Integer nombreCaissestc;

    @Column(name = "nombre_feuillecaisse")
    private Integer nombreFeuillecaisse;

    @Column(name = "prix_unitaire", precision = 10, scale = 2)
    private BigDecimal prixUnitaire;

    @Column(name = "quantite", precision = 10, scale = 2)
    private BigDecimal quantite;

    @Column(name = "poids", precision = 10, scale = 2)
    private BigDecimal poids;

    @ManyToOne
    @JsonIgnoreProperties("achatArticleConteneurArrivages")
    private AchatConteneurArrivage achatConteneurArrivage;

    @ManyToOne
    @JsonIgnoreProperties("achatArticleConteneurArrivages")
    private Produit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdArticleConteneurArrivage() {
        return idArticleConteneurArrivage;
    }

    public AchatArticleConteneurArrivage idArticleConteneurArrivage(Integer idArticleConteneurArrivage) {
        this.idArticleConteneurArrivage = idArticleConteneurArrivage;
        return this;
    }

    public void setIdArticleConteneurArrivage(Integer idArticleConteneurArrivage) {
        this.idArticleConteneurArrivage = idArticleConteneurArrivage;
    }

    public BigDecimal getDimension() {
        return dimension;
    }

    public AchatArticleConteneurArrivage dimension(BigDecimal dimension) {
        this.dimension = dimension;
        return this;
    }

    public void setDimension(BigDecimal dimension) {
        this.dimension = dimension;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public AchatArticleConteneurArrivage montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Integer getNombreCaissestc() {
        return nombreCaissestc;
    }

    public AchatArticleConteneurArrivage nombreCaissestc(Integer nombreCaissestc) {
        this.nombreCaissestc = nombreCaissestc;
        return this;
    }

    public void setNombreCaissestc(Integer nombreCaissestc) {
        this.nombreCaissestc = nombreCaissestc;
    }

    public Integer getNombreFeuillecaisse() {
        return nombreFeuillecaisse;
    }

    public AchatArticleConteneurArrivage nombreFeuillecaisse(Integer nombreFeuillecaisse) {
        this.nombreFeuillecaisse = nombreFeuillecaisse;
        return this;
    }

    public void setNombreFeuillecaisse(Integer nombreFeuillecaisse) {
        this.nombreFeuillecaisse = nombreFeuillecaisse;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public AchatArticleConteneurArrivage prixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
        return this;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public AchatArticleConteneurArrivage quantite(BigDecimal quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public AchatArticleConteneurArrivage poids(BigDecimal poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public AchatConteneurArrivage getAchatConteneurArrivage() {
        return achatConteneurArrivage;
    }

    public AchatArticleConteneurArrivage achatConteneurArrivage(AchatConteneurArrivage achatConteneurArrivage) {
        this.achatConteneurArrivage = achatConteneurArrivage;
        return this;
    }

    public void setAchatConteneurArrivage(AchatConteneurArrivage achatConteneurArrivage) {
        this.achatConteneurArrivage = achatConteneurArrivage;
    }

    public Produit getProduit() {
        return produit;
    }

    public AchatArticleConteneurArrivage produit(Produit produit) {
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
        AchatArticleConteneurArrivage achatArticleConteneurArrivage = (AchatArticleConteneurArrivage) o;
        if (achatArticleConteneurArrivage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatArticleConteneurArrivage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatArticleConteneurArrivage{" +
            "id=" + getId() +
            ", idArticleConteneurArrivage=" + getIdArticleConteneurArrivage() +
            ", dimension=" + getDimension() +
            ", montant=" + getMontant() +
            ", nombreCaissestc=" + getNombreCaissestc() +
            ", nombreFeuillecaisse=" + getNombreFeuillecaisse() +
            ", prixUnitaire=" + getPrixUnitaire() +
            ", quantite=" + getQuantite() +
            ", poids=" + getPoids() +
            "}";
    }
}
