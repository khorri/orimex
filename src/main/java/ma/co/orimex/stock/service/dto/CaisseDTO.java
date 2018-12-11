package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Caisse entity.
 */
public class CaisseDTO implements Serializable {

    private Long id;

    private Integer idCaisse;

    private Integer nombrePlateaux;

    private String numeroConteneur;

    private String referenceCaisse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCaisse() {
        return idCaisse;
    }

    public void setIdCaisse(Integer idCaisse) {
        this.idCaisse = idCaisse;
    }

    public Integer getNombrePlateaux() {
        return nombrePlateaux;
    }

    public void setNombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
    }

    public String getNumeroConteneur() {
        return numeroConteneur;
    }

    public void setNumeroConteneur(String numeroConteneur) {
        this.numeroConteneur = numeroConteneur;
    }

    public String getReferenceCaisse() {
        return referenceCaisse;
    }

    public void setReferenceCaisse(String referenceCaisse) {
        this.referenceCaisse = referenceCaisse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaisseDTO caisseDTO = (CaisseDTO) o;
        if (caisseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caisseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaisseDTO{" +
            "id=" + getId() +
            ", idCaisse=" + getIdCaisse() +
            ", nombrePlateaux=" + getNombrePlateaux() +
            ", numeroConteneur='" + getNumeroConteneur() + "'" +
            ", referenceCaisse='" + getReferenceCaisse() + "'" +
            "}";
    }
}
