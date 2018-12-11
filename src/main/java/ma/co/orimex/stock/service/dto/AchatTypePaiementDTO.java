package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AchatTypePaiement entity.
 */
public class AchatTypePaiementDTO implements Serializable {

    private Long id;

    private Integer idTypePaiement;

    private String libelleTypePaiement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTypePaiement() {
        return idTypePaiement;
    }

    public void setIdTypePaiement(Integer idTypePaiement) {
        this.idTypePaiement = idTypePaiement;
    }

    public String getLibelleTypePaiement() {
        return libelleTypePaiement;
    }

    public void setLibelleTypePaiement(String libelleTypePaiement) {
        this.libelleTypePaiement = libelleTypePaiement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatTypePaiementDTO achatTypePaiementDTO = (AchatTypePaiementDTO) o;
        if (achatTypePaiementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatTypePaiementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatTypePaiementDTO{" +
            "id=" + getId() +
            ", idTypePaiement=" + getIdTypePaiement() +
            ", libelleTypePaiement='" + getLibelleTypePaiement() + "'" +
            "}";
    }
}
