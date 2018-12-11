package ma.co.orimex.stock.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the AchatProforma entity.
 */
public class AchatProformaDTO implements Serializable {

    private Long id;

    private Integer idProforma;

    private Integer nombreTc;

    private BigDecimal coutFob;

    private BigDecimal coutFret;

    private BigDecimal montantTotal;

    private String numeroBonProforma;

    private String typeAcht;

    private BigDecimal poids;

    private LocalDate dateProforma;

    private Long achatDossierId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(Integer idProforma) {
        this.idProforma = idProforma;
    }

    public Integer getNombreTc() {
        return nombreTc;
    }

    public void setNombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
    }

    public BigDecimal getCoutFob() {
        return coutFob;
    }

    public void setCoutFob(BigDecimal coutFob) {
        this.coutFob = coutFob;
    }

    public BigDecimal getCoutFret() {
        return coutFret;
    }

    public void setCoutFret(BigDecimal coutFret) {
        this.coutFret = coutFret;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getNumeroBonProforma() {
        return numeroBonProforma;
    }

    public void setNumeroBonProforma(String numeroBonProforma) {
        this.numeroBonProforma = numeroBonProforma;
    }

    public String getTypeAcht() {
        return typeAcht;
    }

    public void setTypeAcht(String typeAcht) {
        this.typeAcht = typeAcht;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public LocalDate getDateProforma() {
        return dateProforma;
    }

    public void setDateProforma(LocalDate dateProforma) {
        this.dateProforma = dateProforma;
    }

    public Long getAchatDossierId() {
        return achatDossierId;
    }

    public void setAchatDossierId(Long achatDossierId) {
        this.achatDossierId = achatDossierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatProformaDTO achatProformaDTO = (AchatProformaDTO) o;
        if (achatProformaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatProformaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatProformaDTO{" +
            "id=" + getId() +
            ", idProforma=" + getIdProforma() +
            ", nombreTc=" + getNombreTc() +
            ", coutFob=" + getCoutFob() +
            ", coutFret=" + getCoutFret() +
            ", montantTotal=" + getMontantTotal() +
            ", numeroBonProforma='" + getNumeroBonProforma() + "'" +
            ", typeAcht='" + getTypeAcht() + "'" +
            ", poids=" + getPoids() +
            ", dateProforma='" + getDateProforma() + "'" +
            ", achatDossier=" + getAchatDossierId() +
            "}";
    }
}
