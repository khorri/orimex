package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AchatBanque entity.
 */
public class AchatBanqueDTO implements Serializable {

    private Long id;

    private Integer idBanque;

    private String codeBanque;

    private String designationBanque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdBanque() {
        return idBanque;
    }

    public void setIdBanque(Integer idBanque) {
        this.idBanque = idBanque;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getDesignationBanque() {
        return designationBanque;
    }

    public void setDesignationBanque(String designationBanque) {
        this.designationBanque = designationBanque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatBanqueDTO achatBanqueDTO = (AchatBanqueDTO) o;
        if (achatBanqueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatBanqueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatBanqueDTO{" +
            "id=" + getId() +
            ", idBanque=" + getIdBanque() +
            ", codeBanque='" + getCodeBanque() + "'" +
            ", designationBanque='" + getDesignationBanque() + "'" +
            "}";
    }
}
