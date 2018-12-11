package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Couleur entity.
 */
public class CouleurDTO implements Serializable {

    private Long id;

    private Integer idCouleur;

    private String codeHtml;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(Integer idCouleur) {
        this.idCouleur = idCouleur;
    }

    public String getCodeHtml() {
        return codeHtml;
    }

    public void setCodeHtml(String codeHtml) {
        this.codeHtml = codeHtml;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CouleurDTO couleurDTO = (CouleurDTO) o;
        if (couleurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), couleurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CouleurDTO{" +
            "id=" + getId() +
            ", idCouleur=" + getIdCouleur() +
            ", codeHtml='" + getCodeHtml() + "'" +
            "}";
    }
}
