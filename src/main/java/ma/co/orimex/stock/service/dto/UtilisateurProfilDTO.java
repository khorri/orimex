package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UtilisateurProfil entity.
 */
public class UtilisateurProfilDTO implements Serializable {

    private Long id;

    private Long utilisateurId;

    private Long profilId;

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

        UtilisateurProfilDTO utilisateurProfilDTO = (UtilisateurProfilDTO) o;
        if (utilisateurProfilDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), utilisateurProfilDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UtilisateurProfilDTO{" +
            "id=" + getId() +
            ", utilisateur=" + getUtilisateurId() +
            ", profil=" + getProfilId() +
            "}";
    }
}
