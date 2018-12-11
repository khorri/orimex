package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Bon entity.
 */
public class BonDTO implements Serializable {

    private Long id;

    private Integer idBon;

    private Integer idTypeBon;

    private String numeroBon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdBon() {
        return idBon;
    }

    public void setIdBon(Integer idBon) {
        this.idBon = idBon;
    }

    public Integer getIdTypeBon() {
        return idTypeBon;
    }

    public void setIdTypeBon(Integer idTypeBon) {
        this.idTypeBon = idTypeBon;
    }

    public String getNumeroBon() {
        return numeroBon;
    }

    public void setNumeroBon(String numeroBon) {
        this.numeroBon = numeroBon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BonDTO bonDTO = (BonDTO) o;
        if (bonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BonDTO{" +
            "id=" + getId() +
            ", idBon=" + getIdBon() +
            ", idTypeBon=" + getIdTypeBon() +
            ", numeroBon='" + getNumeroBon() + "'" +
            "}";
    }
}
