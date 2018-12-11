package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the AchatArticleLigneProforma entity.
 */
public class AchatArticleLigneProformaDTO implements Serializable {

    private Long id;

    private Integer idArticleLigneProforma;

    private Integer numeroSequence;

    private Integer nombreCaissesTc;

    private Integer nombreFeuillesCaisse;

    private BigDecimal dimention;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private BigDecimal montant;

    private BigDecimal poids;

    private Long produitId;

    private Long achatLigneProformaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdArticleLigneProforma() {
        return idArticleLigneProforma;
    }

    public void setIdArticleLigneProforma(Integer idArticleLigneProforma) {
        this.idArticleLigneProforma = idArticleLigneProforma;
    }

    public Integer getNumeroSequence() {
        return numeroSequence;
    }

    public void setNumeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
    }

    public Integer getNombreCaissesTc() {
        return nombreCaissesTc;
    }

    public void setNombreCaissesTc(Integer nombreCaissesTc) {
        this.nombreCaissesTc = nombreCaissesTc;
    }

    public Integer getNombreFeuillesCaisse() {
        return nombreFeuillesCaisse;
    }

    public void setNombreFeuillesCaisse(Integer nombreFeuillesCaisse) {
        this.nombreFeuillesCaisse = nombreFeuillesCaisse;
    }

    public BigDecimal getDimention() {
        return dimention;
    }

    public void setDimention(BigDecimal dimention) {
        this.dimention = dimention;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getAchatLigneProformaId() {
        return achatLigneProformaId;
    }

    public void setAchatLigneProformaId(Long achatLigneProformaId) {
        this.achatLigneProformaId = achatLigneProformaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatArticleLigneProformaDTO achatArticleLigneProformaDTO = (AchatArticleLigneProformaDTO) o;
        if (achatArticleLigneProformaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatArticleLigneProformaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatArticleLigneProformaDTO{" +
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
            ", produit=" + getProduitId() +
            ", achatLigneProforma=" + getAchatLigneProformaId() +
            "}";
    }
}
