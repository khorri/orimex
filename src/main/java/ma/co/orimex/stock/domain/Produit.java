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
 * A Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "produit")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_produit")
    private Integer idProduit;

    @Column(name = "designation_produit")
    private String designationProduit;

    @Column(name = "dimension")
    private String dimension;

    @Column(name = "epaisseur")
    private String epaisseur;

    @Column(name = "largeure", precision = 10, scale = 2)
    private BigDecimal largeure;

    @Column(name = "longueur", precision = 10, scale = 2)
    private BigDecimal longueur;

    @Column(name = "reference_produit")
    private String referenceProduit;

    @Column(name = "poids", precision = 10, scale = 2)
    private BigDecimal poids;

    @Column(name = "libelle_couleur")
    private String libelleCouleur;

    @Column(name = "libelle_famille")
    private String libelleFamille;

    @Column(name = "libelle_origine")
    private String libelleOrigine;

    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Recuperation> recuperations = new HashSet<>();
    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Retour> retours = new HashSet<>();
    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AchatArticleLigneProforma> achatArticleLigneProformas = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private Couleur couleur;

    @ManyToOne
    @JsonIgnoreProperties("")
    private FamilleProduit familleProduit;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Origine origine;

    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AchatArticleConteneurArrivage> achatArticleConteneurArrivages = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public Produit idProduit(Integer idProduit) {
        this.idProduit = idProduit;
        return this;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getDesignationProduit() {
        return designationProduit;
    }

    public Produit designationProduit(String designationProduit) {
        this.designationProduit = designationProduit;
        return this;
    }

    public void setDesignationProduit(String designationProduit) {
        this.designationProduit = designationProduit;
    }

    public String getDimension() {
        return dimension;
    }

    public Produit dimension(String dimension) {
        this.dimension = dimension;
        return this;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getEpaisseur() {
        return epaisseur;
    }

    public Produit epaisseur(String epaisseur) {
        this.epaisseur = epaisseur;
        return this;
    }

    public void setEpaisseur(String epaisseur) {
        this.epaisseur = epaisseur;
    }

    public BigDecimal getLargeure() {
        return largeure;
    }

    public Produit largeure(BigDecimal largeure) {
        this.largeure = largeure;
        return this;
    }

    public void setLargeure(BigDecimal largeure) {
        this.largeure = largeure;
    }

    public BigDecimal getLongueur() {
        return longueur;
    }

    public Produit longueur(BigDecimal longueur) {
        this.longueur = longueur;
        return this;
    }

    public void setLongueur(BigDecimal longueur) {
        this.longueur = longueur;
    }

    public String getReferenceProduit() {
        return referenceProduit;
    }

    public Produit referenceProduit(String referenceProduit) {
        this.referenceProduit = referenceProduit;
        return this;
    }

    public void setReferenceProduit(String referenceProduit) {
        this.referenceProduit = referenceProduit;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public Produit poids(BigDecimal poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public String getLibelleCouleur() {
        return libelleCouleur;
    }

    public Produit libelleCouleur(String libelleCouleur) {
        this.libelleCouleur = libelleCouleur;
        return this;
    }

    public void setLibelleCouleur(String libelleCouleur) {
        this.libelleCouleur = libelleCouleur;
    }

    public String getLibelleFamille() {
        return libelleFamille;
    }

    public Produit libelleFamille(String libelleFamille) {
        this.libelleFamille = libelleFamille;
        return this;
    }

    public void setLibelleFamille(String libelleFamille) {
        this.libelleFamille = libelleFamille;
    }

    public String getLibelleOrigine() {
        return libelleOrigine;
    }

    public Produit libelleOrigine(String libelleOrigine) {
        this.libelleOrigine = libelleOrigine;
        return this;
    }

    public void setLibelleOrigine(String libelleOrigine) {
        this.libelleOrigine = libelleOrigine;
    }

    public Set<Recuperation> getRecuperations() {
        return recuperations;
    }

    public Produit recuperations(Set<Recuperation> recuperations) {
        this.recuperations = recuperations;
        return this;
    }

    public Produit addRecuperation(Recuperation recuperation) {
        this.recuperations.add(recuperation);
        recuperation.setProduit(this);
        return this;
    }

    public Produit removeRecuperation(Recuperation recuperation) {
        this.recuperations.remove(recuperation);
        recuperation.setProduit(null);
        return this;
    }

    public void setRecuperations(Set<Recuperation> recuperations) {
        this.recuperations = recuperations;
    }

    public Set<Retour> getRetours() {
        return retours;
    }

    public Produit retours(Set<Retour> retours) {
        this.retours = retours;
        return this;
    }

    public Produit addRetour(Retour retour) {
        this.retours.add(retour);
        retour.setProduit(this);
        return this;
    }

    public Produit removeRetour(Retour retour) {
        this.retours.remove(retour);
        retour.setProduit(null);
        return this;
    }

    public void setRetours(Set<Retour> retours) {
        this.retours = retours;
    }

    public Set<AchatArticleLigneProforma> getAchatArticleLigneProformas() {
        return achatArticleLigneProformas;
    }

    public Produit achatArticleLigneProformas(Set<AchatArticleLigneProforma> achatArticleLigneProformas) {
        this.achatArticleLigneProformas = achatArticleLigneProformas;
        return this;
    }

    public Produit addAchatArticleLigneProforma(AchatArticleLigneProforma achatArticleLigneProforma) {
        this.achatArticleLigneProformas.add(achatArticleLigneProforma);
        achatArticleLigneProforma.setProduit(this);
        return this;
    }

    public Produit removeAchatArticleLigneProforma(AchatArticleLigneProforma achatArticleLigneProforma) {
        this.achatArticleLigneProformas.remove(achatArticleLigneProforma);
        achatArticleLigneProforma.setProduit(null);
        return this;
    }

    public void setAchatArticleLigneProformas(Set<AchatArticleLigneProforma> achatArticleLigneProformas) {
        this.achatArticleLigneProformas = achatArticleLigneProformas;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public Produit couleur(Couleur couleur) {
        this.couleur = couleur;
        return this;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public FamilleProduit getFamilleProduit() {
        return familleProduit;
    }

    public Produit familleProduit(FamilleProduit familleProduit) {
        this.familleProduit = familleProduit;
        return this;
    }

    public void setFamilleProduit(FamilleProduit familleProduit) {
        this.familleProduit = familleProduit;
    }

    public Origine getOrigine() {
        return origine;
    }

    public Produit origine(Origine origine) {
        this.origine = origine;
        return this;
    }

    public void setOrigine(Origine origine) {
        this.origine = origine;
    }

    public Set<AchatArticleConteneurArrivage> getAchatArticleConteneurArrivages() {
        return achatArticleConteneurArrivages;
    }

    public Produit achatArticleConteneurArrivages(Set<AchatArticleConteneurArrivage> achatArticleConteneurArrivages) {
        this.achatArticleConteneurArrivages = achatArticleConteneurArrivages;
        return this;
    }

    public Produit addAchatArticleConteneurArrivage(AchatArticleConteneurArrivage achatArticleConteneurArrivage) {
        this.achatArticleConteneurArrivages.add(achatArticleConteneurArrivage);
        achatArticleConteneurArrivage.setProduit(this);
        return this;
    }

    public Produit removeAchatArticleConteneurArrivage(AchatArticleConteneurArrivage achatArticleConteneurArrivage) {
        this.achatArticleConteneurArrivages.remove(achatArticleConteneurArrivage);
        achatArticleConteneurArrivage.setProduit(null);
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
        Produit produit = (Produit) o;
        if (produit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), produit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", idProduit=" + getIdProduit() +
            ", designationProduit='" + getDesignationProduit() + "'" +
            ", dimension='" + getDimension() + "'" +
            ", epaisseur='" + getEpaisseur() + "'" +
            ", largeure=" + getLargeure() +
            ", longueur=" + getLongueur() +
            ", referenceProduit='" + getReferenceProduit() + "'" +
            ", poids=" + getPoids() +
            ", libelleCouleur='" + getLibelleCouleur() + "'" +
            ", libelleFamille='" + getLibelleFamille() + "'" +
            ", libelleOrigine='" + getLibelleOrigine() + "'" +
            "}";
    }
}
