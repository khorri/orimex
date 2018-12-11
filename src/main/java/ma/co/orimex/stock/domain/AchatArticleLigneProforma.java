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
 * A AchatArticleLigneProforma.
 */
@Entity
@Table(name = "achat_article_ligne_proforma")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatarticleligneproforma")
public class AchatArticleLigneProforma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_article_ligne_proforma")
    private Integer idArticleLigneProforma;

    @Column(name = "numero_sequence")
    private Integer numeroSequence;

    @Column(name = "nombre_caisses_tc")
    private Integer nombreCaissesTc;

    @Column(name = "nombre_feuilles_caisse")
    private Integer nombreFeuillesCaisse;

    @Column(name = "dimention", precision = 10, scale = 2)
    private BigDecimal dimention;

    @Column(name = "quantite", precision = 10, scale = 2)
    private BigDecimal quantite;

    @Column(name = "prix_unitaire", precision = 10, scale = 2)
    private BigDecimal prixUnitaire;

    @Column(name = "montant", precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(name = "poids", precision = 10, scale = 2)
    private BigDecimal poids;

    @ManyToOne
    @JsonIgnoreProperties("achatArticleLigneProformas")
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties("")
    private AchatLigneProforma achatLigneProforma;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdArticleLigneProforma() {
        return idArticleLigneProforma;
    }

    public AchatArticleLigneProforma idArticleLigneProforma(Integer idArticleLigneProforma) {
        this.idArticleLigneProforma = idArticleLigneProforma;
        return this;
    }

    public void setIdArticleLigneProforma(Integer idArticleLigneProforma) {
        this.idArticleLigneProforma = idArticleLigneProforma;
    }

    public Integer getNumeroSequence() {
        return numeroSequence;
    }

    public AchatArticleLigneProforma numeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
        return this;
    }

    public void setNumeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
    }

    public Integer getNombreCaissesTc() {
        return nombreCaissesTc;
    }

    public AchatArticleLigneProforma nombreCaissesTc(Integer nombreCaissesTc) {
        this.nombreCaissesTc = nombreCaissesTc;
        return this;
    }

    public void setNombreCaissesTc(Integer nombreCaissesTc) {
        this.nombreCaissesTc = nombreCaissesTc;
    }

    public Integer getNombreFeuillesCaisse() {
        return nombreFeuillesCaisse;
    }

    public AchatArticleLigneProforma nombreFeuillesCaisse(Integer nombreFeuillesCaisse) {
        this.nombreFeuillesCaisse = nombreFeuillesCaisse;
        return this;
    }

    public void setNombreFeuillesCaisse(Integer nombreFeuillesCaisse) {
        this.nombreFeuillesCaisse = nombreFeuillesCaisse;
    }

    public BigDecimal getDimention() {
        return dimention;
    }

    public AchatArticleLigneProforma dimention(BigDecimal dimention) {
        this.dimention = dimention;
        return this;
    }

    public void setDimention(BigDecimal dimention) {
        this.dimention = dimention;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public AchatArticleLigneProforma quantite(BigDecimal quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public AchatArticleLigneProforma prixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
        return this;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public AchatArticleLigneProforma montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public AchatArticleLigneProforma poids(BigDecimal poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public Produit getProduit() {
        return produit;
    }

    public AchatArticleLigneProforma produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public AchatLigneProforma getAchatLigneProforma() {
        return achatLigneProforma;
    }

    public AchatArticleLigneProforma achatLigneProforma(AchatLigneProforma achatLigneProforma) {
        this.achatLigneProforma = achatLigneProforma;
        return this;
    }

    public void setAchatLigneProforma(AchatLigneProforma achatLigneProforma) {
        this.achatLigneProforma = achatLigneProforma;
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
        AchatArticleLigneProforma achatArticleLigneProforma = (AchatArticleLigneProforma) o;
        if (achatArticleLigneProforma.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatArticleLigneProforma.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatArticleLigneProforma{" +
            "id=" + getId() +
            ", idArticleLigneProforma=" + getIdArticleLigneProforma() +
            ", numeroSequence=" + getNumeroSequence() +
            ", nombreCaissesTc=" + getNombreCaissesTc() +
            ", nombreFeuillesCaisse=" + getNombreFeuillesCaisse() +
            ", dimention=" + getDimention() +
            ", quantite=" + getQuantite() +
            ", prixUnitaire=" + getPrixUnitaire() +
            ", montant=" + getMontant() +
            ", poids=" + getPoids() +
            "}";
    }
}
