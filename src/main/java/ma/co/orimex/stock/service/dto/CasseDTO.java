package ma.co.orimex.stock.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Casse entity.
 */
public class CasseDTO implements Serializable {

    private Long id;

    private Integer idOperation;

    private Instant dateOperation;

    private String description;

    private Integer nombrePlateaux;

    private String numeroOperation;

    private BigDecimal superfcie;

    private Long utilisateurId;

    private Long caisseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Instant getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNombrePlateaux() {
        return nombrePlateaux;
    }

    public void setNombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
    }

    public String getNumeroOperation() {
        return numeroOperation;
    }

    public void setNumeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
    }

    public BigDecimal getSuperfcie() {
        return superfcie;
    }

    public void setSuperfcie(BigDecimal superfcie) {
        this.superfcie = superfcie;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Long getCaisseId() {
        return caisseId;
    }

    public void setCaisseId(Long caisseId) {
        this.caisseId = caisseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CasseDTO casseDTO = (CasseDTO) o;
        if (casseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), casseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CasseDTO{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateOperation='" + getDateOperation() + "'" +
            ", description='" + getDescription() + "'" +
            ", nombrePlateaux=" + getNombrePlateaux() +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            ", superfcie=" + getSuperfcie() +
            ", utilisateur=" + getUtilisateurId() +
            ", caisse=" + getCaisseId() +
            "}";
    }
}
