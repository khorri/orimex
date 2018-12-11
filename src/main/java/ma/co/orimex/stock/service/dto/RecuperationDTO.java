package ma.co.orimex.stock.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Recuperation entity.
 */
public class RecuperationDTO implements Serializable {

    private Long id;

    private Integer idOperation;

    private Instant dateOperation;

    private Integer nombrePanneaux;

    private String numeroOperation;

    private BigDecimal superficie;

    private Long produitId;

    private Long depotId;

    private Long utilisateurId;

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

    public Integer getNombrePanneaux() {
        return nombrePanneaux;
    }

    public void setNombrePanneaux(Integer nombrePanneaux) {
        this.nombrePanneaux = nombrePanneaux;
    }

    public String getNumeroOperation() {
        return numeroOperation;
    }

    public void setNumeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
    }

    public BigDecimal getSuperficie() {
        return superficie;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecuperationDTO recuperationDTO = (RecuperationDTO) o;
        if (recuperationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recuperationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecuperationDTO{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateOperation='" + getDateOperation() + "'" +
            ", nombrePanneaux=" + getNombrePanneaux() +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            ", superficie=" + getSuperficie() +
            ", produit=" + getProduitId() +
            ", depot=" + getDepotId() +
            ", utilisateur=" + getUtilisateurId() +
            "}";
    }
}
