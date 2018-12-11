package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ProfilAction entity.
 */
public class ProfilActionDTO implements Serializable {

    private Long id;

    private Long actionId;

    private Long profilId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Long getProfilId() {
        return profilId;
    }

    public void setProfilId(Long profilId) {
        this.profilId = profilId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProfilActionDTO profilActionDTO = (ProfilActionDTO) o;
        if (profilActionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profilActionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfilActionDTO{" +
            "id=" + getId() +
            ", action=" + getActionId() +
            ", profil=" + getProfilId() +
            "}";
    }
}
