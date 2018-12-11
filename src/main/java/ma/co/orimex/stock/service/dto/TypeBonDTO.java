package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TypeBon entity.
 */
public class TypeBonDTO implements Serializable {

    private Long id;

    private Integer idTypeBon;

    private String libelleTypeBon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTypeBon() {
        return idTypeBon;
    }

    public void setIdTypeBon(Integer idTypeBon) {
        this.idTypeBon = idTypeBon;
    }

    public String getLibelleTypeBon() {
        return libelleTypeBon;
    }

    public void setLibelleTypeBon(String libelleTypeBon) {
        this.libelleTypeBon = libelleTypeBon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeBonDTO typeBonDTO = (TypeBonDTO) o;
        if (typeBonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeBonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeBonDTO{" +
            "id=" + getId() +
            ", idTypeBon=" + getIdTypeBon() +
            ", libelleTypeBon='" + getLibelleTypeBon() + "'" +
            "}";
    }
}
