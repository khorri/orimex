package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AchatFournisseur entity.
 */
public class AchatFournisseurDTO implements Serializable {

    private Long id;

    private String typeFournisseur;

    private String codeFournisseur;

    private String designationFournisseur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeFournisseur() {
        return typeFournisseur;
    }

    public void setTypeFournisseur(String typeFournisseur) {
        this.typeFournisseur = typeFournisseur;
    }

    public String getCodeFournisseur() {
        return codeFournisseur;
    }

    public void setCodeFournisseur(String codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
    }

    public String getDesignationFournisseur() {
        return designationFournisseur;
    }

    public void setDesignationFournisseur(String designationFournisseur) {
        this.designationFournisseur = designationFournisseur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatFournisseurDTO achatFournisseurDTO = (AchatFournisseurDTO) o;
        if (achatFournisseurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatFournisseurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatFournisseurDTO{" +
            "id=" + getId() +
            ", typeFournisseur='" + getTypeFournisseur() + "'" +
            ", codeFournisseur='" + getCodeFournisseur() + "'" +
            ", designationFournisseur='" + getDesignationFournisseur() + "'" +
            "}";
    }
}
