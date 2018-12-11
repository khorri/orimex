package ma.co.orimex.stock.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Transfert entity.
 */
public class TransfertDTO implements Serializable {

    private Long id;

    private Integer idOperation;

    private Instant dateOperation;

    private Integer nombrePlateaux;

    private String numeroOperation;

    private Long utilisateurId;

    private Long bonId;

    private Long caisseId;

    private Long camionId;

    private Long conteneurId;

    private Long depotId;

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

    public Long getCaisseId() {
        return caisseId;
    }

    public void setCaisseId(Long caisseId) {
        this.caisseId = caisseId;
    }

    public Long getCamionId() {
        return camionId;
    }

    public void setCamionId(Long camionId) {
        this.camionId = camionId;
    }

    public Long getConteneurId() {
        return conteneurId;
    }

    public void setConteneurId(Long conteneurId) {
        this.conteneurId = conteneurId;
    }

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransfertDTO transfertDTO = (TransfertDTO) o;
        if (transfertDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transfertDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransfertDTO{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateOperation='" + getDateOperation() + "'" +
            ", nombrePlateaux=" + getNombrePlateaux() +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            ", utilisateur=" + getUtilisateurId() +
            ", bon=" + getBonId() +
            ", caisse=" + getCaisseId() +
            ", camion=" + getCamionId() +
            ", conteneur=" + getConteneurId() +
            ", depot=" + getDepotId() +
            "}";
    }
}
