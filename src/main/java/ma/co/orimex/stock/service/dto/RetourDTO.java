package ma.co.orimex.stock.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Retour entity.
 */
public class RetourDTO implements Serializable {

    private Long id;

    private Integer idOperation;

    private Instant dateOperation;

    private Integer nombrePlateaux;

    private String numeroOperation;

    private BigDecimal superfecie;

    private Long produitId;

    private Long utilisateurId;

    private Long bonId;

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

    public BigDecimal getSuperfecie() {
        return superfecie;
    }

    public void setSuperfecie(BigDecimal superfecie) {
        this.superfecie = superfecie;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Long getBonId() {
        return bonId;
    }

    public void setBonId(Long bonId) {
        this.bonId = bonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RetourDTO retourDTO = (RetourDTO) o;
        if (retourDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), retourDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RetourDTO{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateOperation='" + getDateOperation() + "'" +
            ", nombrePlateaux=" + getNombrePlateaux() +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            ", superfecie=" + getSuperfecie() +
            ", produit=" + getProduitId() +
            ", utilisateur=" + getUtilisateurId() +
            ", bon=" + getBonId() +
            "}";
    }
}
