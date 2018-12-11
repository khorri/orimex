package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Depot entity.
 */
public class DepotDTO implements Serializable {

    private Long id;

    private Integer idDepot;

    private String referenceDepot;

    private String utilisateurDepots;

    private Long villeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdDepot() {
        return idDepot;
    }

    public void setIdDepot(Integer idDepot) {
        this.idDepot = idDepot;
    }

    public String getReferenceDepot() {
        return referenceDepot;
    }

    public void setReferenceDepot(String referenceDepot) {
        this.referenceDepot = referenceDepot;
    }

    public String getUtilisateurDepots() {
        return utilisateurDepots;
    }

    public void setUtilisateurDepots(String utilisateurDepots) {
        this.utilisateurDepots = utilisateurDepots;
    }

    public Long getVilleId() {
        return villeId;
    }

    public void setVilleId(Long villeId) {
        this.villeId = villeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DepotDTO depotDTO = (DepotDTO) o;
        if (depotDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), depotDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DepotDTO{" +
            "id=" + getId() +
            ", idDepot=" + getIdDepot() +
            ", referenceDepot='" + getReferenceDepot() + "'" +
            ", utilisateurDepots='" + getUtilisateurDepots() + "'" +
            ", ville=" + getVilleId() +
            "}";
    }
}
