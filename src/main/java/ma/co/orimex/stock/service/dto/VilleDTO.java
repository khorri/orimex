package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Ville entity.
 */
public class VilleDTO implements Serializable {

    private Long id;

    private Integer idVille;

    private String libelleVille;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdVille() {
        return idVille;
    }

    public void setIdVille(Integer idVille) {
        this.idVille = idVille;
    }

    public String getLibelleVille() {
        return libelleVille;
    }

    public void setLibelleVille(String libelleVille) {
        this.libelleVille = libelleVille;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VilleDTO villeDTO = (VilleDTO) o;
        if (villeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), villeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VilleDTO{" +
            "id=" + getId() +
            ", idVille=" + getIdVille() +
            ", libelleVille='" + getLibelleVille() + "'" +
            "}";
    }
}
