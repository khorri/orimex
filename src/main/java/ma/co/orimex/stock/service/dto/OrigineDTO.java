package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Origine entity.
 */
public class OrigineDTO implements Serializable {

    private Long id;

    private Integer idOrigine;

    private String designationOrigine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOrigine() {
        return idOrigine;
    }

    public void setIdOrigine(Integer idOrigine) {
        this.idOrigine = idOrigine;
    }

    public String getDesignationOrigine() {
        return designationOrigine;
    }

    public void setDesignationOrigine(String designationOrigine) {
        this.designationOrigine = designationOrigine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrigineDTO origineDTO = (OrigineDTO) o;
        if (origineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), origineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrigineDTO{" +
            "id=" + getId() +
            ", idOrigine=" + getIdOrigine() +
            ", designationOrigine='" + getDesignationOrigine() + "'" +
            "}";
    }
}
