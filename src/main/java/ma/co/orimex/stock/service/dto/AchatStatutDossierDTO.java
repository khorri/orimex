package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AchatStatutDossier entity.
 */
public class AchatStatutDossierDTO implements Serializable {

    private Long id;

    private Integer idStatutDossier;

    private String libelleStatutDossier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdStatutDossier() {
        return idStatutDossier;
    }

    public void setIdStatutDossier(Integer idStatutDossier) {
        this.idStatutDossier = idStatutDossier;
    }

    public String getLibelleStatutDossier() {
        return libelleStatutDossier;
    }

    public void setLibelleStatutDossier(String libelleStatutDossier) {
        this.libelleStatutDossier = libelleStatutDossier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatStatutDossierDTO achatStatutDossierDTO = (AchatStatutDossierDTO) o;
        if (achatStatutDossierDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatStatutDossierDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatStatutDossierDTO{" +
            "id=" + getId() +
            ", idStatutDossier=" + getIdStatutDossier() +
            ", libelleStatutDossier='" + getLibelleStatutDossier() + "'" +
            "}";
    }
}
