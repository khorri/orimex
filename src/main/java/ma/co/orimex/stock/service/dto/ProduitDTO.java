package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Produit entity.
 */
public class ProduitDTO implements Serializable {

    private Long id;

    private Integer idProduit;

    private String designationProduit;

    private String dimension;

    private String epaisseur;

    private BigDecimal largeure;

    private BigDecimal longueur;

    private String referenceProduit;

    private BigDecimal poids;

    private String libelleCouleur;

    private String libelleFamille;

    private String libelleOrigine;

    private Long couleurId;

    private Long familleProduitId;

    private Long origineId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getDesignationProduit() {
        return designationProduit;
    }

    public void setDesignationProduit(String designationProduit) {
        this.designationProduit = designationProduit;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(String epaisseur) {
        this.epaisseur = epaisseur;
    }

    public BigDecimal getLargeure() {
        return largeure;
    }

    public void setLargeure(BigDecimal largeure) {
        this.largeure = largeure;
    }

    public BigDecimal getLongueur() {
        return longueur;
    }

    public void setLongueur(BigDecimal longueur) {
        this.longueur = longueur;
    }

    public String getReferenceProduit() {
        return referenceProduit;
    }

    public void setReferenceProduit(String referenceProduit) {
        this.referenceProduit = referenceProduit;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public String getLibelleCouleur() {
        return libelleCouleur;
    }

    public void setLibelleCouleur(String libelleCouleur) {
        this.libelleCouleur = libelleCouleur;
    }

    public String getLibelleFamille() {
        return libelleFamille;
    }

    public void setLibelleFamille(String libelleFamille) {
        this.libelleFamille = libelleFamille;
    }

    public String getLibelleOrigine() {
        return libelleOrigine;
    }

    public void setLibelleOrigine(String libelleOrigine) {
        this.libelleOrigine = libelleOrigine;
    }

    public Long getCouleurId() {
        return couleurId;
    }

    public void setCouleurId(Long couleurId) {
        this.couleurId = couleurId;
    }

    public Long getFamilleProduitId() {
        return familleProduitId;
    }

    public void setFamilleProduitId(Long familleProduitId) {
        this.familleProduitId = familleProduitId;
    }

    public Long getOrigineId() {
        return origineId;
    }

    public void setOrigineId(Long origineId) {
        this.origineId = origineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProduitDTO produitDTO = (ProduitDTO) o;
        if (produitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), produitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProduitDTO{" +
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
            ", couleur=" + getCouleurId() +
            ", familleProduit=" + getFamilleProduitId() +
            ", origine=" + getOrigineId() +
            "}";
    }
}
