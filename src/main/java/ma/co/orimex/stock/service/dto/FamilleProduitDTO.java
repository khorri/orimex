package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FamilleProduit entity.
 */
public class FamilleProduitDTO implements Serializable {

    private Long id;

    private Integer idFamille;

    private String designationFamille;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdFamille() {
        return idFamille;
    }

    public void setIdFamille(Integer idFamille) {
        this.idFamille = idFamille;
    }

    public String getDesignationFamille() {
        return designationFamille;
    }

    public void setDesignationFamille(String designationFamille) {
        this.designationFamille = designationFamille;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FamilleProduitDTO familleProduitDTO = (FamilleProduitDTO) o;
        if (familleProduitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), familleProduitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FamilleProduitDTO{" +
            "id=" + getId() +
            ", idFamille=" + getIdFamille() +
            ", designationFamille='" + getDesignationFamille() + "'" +
            "}";
    }
}
