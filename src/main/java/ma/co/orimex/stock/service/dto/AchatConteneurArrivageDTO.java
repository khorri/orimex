package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the AchatConteneurArrivage entity.
 */
public class AchatConteneurArrivageDTO implements Serializable {

    private Long id;

    private Integer idConteneurArrivage;

    private BigDecimal montant;

    private String numeroConteneurs;

    private Integer numeroSequence;

    private BigDecimal poids;

    private BigDecimal quantite;

    private Long achatFactureId;

    private Long stockConteneurReceptionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdConteneurArrivage() {
        return idConteneurArrivage;
    }

    public void setIdConteneurArrivage(Integer idConteneurArrivage) {
        this.idConteneurArrivage = idConteneurArrivage;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getNumeroConteneurs() {
        return numeroConteneurs;
    }

    public void setNumeroConteneurs(String numeroConteneurs) {
        this.numeroConteneurs = numeroConteneurs;
    }

    public Integer getNumeroSequence() {
        return numeroSequence;
    }

    public void setNumeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public Long getAchatFactureId() {
        return achatFactureId;
    }

    public void setAchatFactureId(Long achatFactureId) {
        this.achatFactureId = achatFactureId;
    }

    public Long getStockConteneurReceptionId() {
        return stockConteneurReceptionId;
    }

    public void setStockConteneurReceptionId(Long stockConteneurReceptionId) {
        this.stockConteneurReceptionId = stockConteneurReceptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatConteneurArrivageDTO achatConteneurArrivageDTO = (AchatConteneurArrivageDTO) o;
        if (achatConteneurArrivageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatConteneurArrivageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatConteneurArrivageDTO{" +
            "id=" + getId() +
            ", idConteneurArrivage=" + getIdConteneurArrivage() +
            ", montant=" + getMontant() +
            ", numeroConteneurs='" + getNumeroConteneurs() + "'" +
            ", numeroSequence=" + getNumeroSequence() +
            ", poids=" + getPoids() +
            ", quantite=" + getQuantite() +
            ", achatFacture=" + getAchatFactureId() +
            ", stockConteneurReception=" + getStockConteneurReceptionId() +
            "}";
    }
}
