package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the AchatLigneProforma entity.
 */
public class AchatLigneProformaDTO implements Serializable {

    private Long id;

    private Integer idLigneProforma;

    private Integer nombreConteneurs;

    private BigDecimal montant;

    private Integer numeroSequence;

    private BigDecimal poids;

    private Long achatProformaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdLigneProforma() {
        return idLigneProforma;
    }

    public void setIdLigneProforma(Integer idLigneProforma) {
        this.idLigneProforma = idLigneProforma;
    }

    public Integer getNombreConteneurs() {
        return nombreConteneurs;
    }

    public void setNombreConteneurs(Integer nombreConteneurs) {
        this.nombreConteneurs = nombreConteneurs;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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

    public Long getAchatProformaId() {
        return achatProformaId;
    }

    public void setAchatProformaId(Long achatProformaId) {
        this.achatProformaId = achatProformaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatLigneProformaDTO achatLigneProformaDTO = (AchatLigneProformaDTO) o;
        if (achatLigneProformaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatLigneProformaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatLigneProformaDTO{" +
            "id=" + getId() +
            ", idLigneProforma=" + getIdLigneProforma() +
            ", nombreConteneurs=" + getNombreConteneurs() +
            ", montant=" + getMontant() +
            ", numeroSequence=" + getNumeroSequence() +
            ", poids=" + getPoids() +
            ", achatProforma=" + getAchatProformaId() +
            "}";
    }
}
