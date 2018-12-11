package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AchatDevise entity.
 */
public class AchatDeviseDTO implements Serializable {

    private Long id;

    private Integer idDevise;

    private String codeDevise;

    private String libelleDevise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(Integer idDevise) {
        this.idDevise = idDevise;
    }

    public String getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(String codeDevise) {
        this.codeDevise = codeDevise;
    }

    public String getLibelleDevise() {
        return libelleDevise;
    }

    public void setLibelleDevise(String libelleDevise) {
        this.libelleDevise = libelleDevise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatDeviseDTO achatDeviseDTO = (AchatDeviseDTO) o;
        if (achatDeviseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatDeviseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatDeviseDTO{" +
            "id=" + getId() +
            ", idDevise=" + getIdDevise() +
            ", codeDevise='" + getCodeDevise() + "'" +
            ", libelleDevise='" + getLibelleDevise() + "'" +
            "}";
    }
}
