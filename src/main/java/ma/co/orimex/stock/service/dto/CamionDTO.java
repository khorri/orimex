package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Camion entity.
 */
public class CamionDTO implements Serializable {

    private Long id;

    private Integer idCamion;

    private String numeroCamion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public String getNumeroCamion() {
        return numeroCamion;
    }

    public void setNumeroCamion(String numeroCamion) {
        this.numeroCamion = numeroCamion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CamionDTO camionDTO = (CamionDTO) o;
        if (camionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), camionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CamionDTO{" +
            "id=" + getId() +
            ", idCamion=" + getIdCamion() +
            ", numeroCamion='" + getNumeroCamion() + "'" +
            "}";
    }
}
