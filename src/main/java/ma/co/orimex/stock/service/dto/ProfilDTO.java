package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Profil entity.
 */
public class ProfilDTO implements Serializable {

    private Long id;

    private String codeProfil;

    private String descriptionProfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProfil() {
        return codeProfil;
    }

    public void setCodeProfil(String codeProfil) {
        this.codeProfil = codeProfil;
    }

    public String getDescriptionProfil() {
        return descriptionProfil;
    }

    public void setDescriptionProfil(String descriptionProfil) {
        this.descriptionProfil = descriptionProfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProfilDTO profilDTO = (ProfilDTO) o;
        if (profilDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profilDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfilDTO{" +
            "id=" + getId() +
            ", codeProfil='" + getCodeProfil() + "'" +
            ", descriptionProfil='" + getDescriptionProfil() + "'" +
            "}";
    }
}
