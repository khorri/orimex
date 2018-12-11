package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UtilisateurDepotPK entity.
 */
public class UtilisateurDepotPKDTO implements Serializable {

    private Long id;

    private Long utilisateurId;

    private Long depotId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
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

        UtilisateurDepotPKDTO utilisateurDepotPKDTO = (UtilisateurDepotPKDTO) o;
        if (utilisateurDepotPKDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), utilisateurDepotPKDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UtilisateurDepotPKDTO{" +
            "id=" + getId() +
            ", utilisateur=" + getUtilisateurId() +
            ", depot=" + getDepotId() +
            "}";
    }
}
