package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the AchatArticleConteneurArrivage entity.
 */
public class AchatArticleConteneurArrivageDTO implements Serializable {

    private Long id;

    private Integer idArticleConteneurArrivage;

    private BigDecimal dimension;

    private BigDecimal montant;

    private Integer nombreCaissestc;

    private Integer nombreFeuillecaisse;

    private BigDecimal prixUnitaire;

    private BigDecimal quantite;

    private BigDecimal poids;

    private Long achatConteneurArrivageId;

    private Long produitId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdArticleConteneurArrivage() {
        return idArticleConteneurArrivage;
    }

    public void setIdArticleConteneurArrivage(Integer idArticleConteneurArrivage) {
        this.idArticleConteneurArrivage = idArticleConteneurArrivage;
    }

    public BigDecimal getDimension() {
        return dimension;
    }

    public void setDimension(BigDecimal dimension) {
        this.dimension = dimension;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Integer getNombreCaissestc() {
        return nombreCaissestc;
    }

    public void setNombreCaissestc(Integer nombreCaissestc) {
        this.nombreCaissestc = nombreCaissestc;
    }

    public Integer getNombreFeuillecaisse() {
        return nombreFeuillecaisse;
    }

    public void setNombreFeuillecaisse(Integer nombreFeuillecaisse) {
        this.nombreFeuillecaisse = nombreFeuillecaisse;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public Long getAchatConteneurArrivageId() {
        return achatConteneurArrivageId;
    }

    public void setAchatConteneurArrivageId(Long achatConteneurArrivageId) {
        this.achatConteneurArrivageId = achatConteneurArrivageId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO = (AchatArticleConteneurArrivageDTO) o;
        if (achatArticleConteneurArrivageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatArticleConteneurArrivageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatArticleConteneurArrivageDTO{" +
            "id=" + getId() +
            ", idArticleConteneurArrivage=" + getIdArticleConteneurArrivage() +
            ", dimension=" + getDimension() +
            ", montant=" + getMontant() +
            ", nombreCaissestc=" + getNombreCaissestc() +
            ", nombreFeuillecaisse=" + getNombreFeuillecaisse() +
            ", prixUnitaire=" + getPrixUnitaire() +
            ", quantite=" + getQuantite() +
            ", poids=" + getPoids() +
            ", achatConteneurArrivage=" + getAchatConteneurArrivageId() +
            ", produit=" + getProduitId() +
            "}";
    }
}
