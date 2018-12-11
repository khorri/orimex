package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AchatConteneurReception entity.
 */
public class AchatConteneurReceptionDTO implements Serializable {

    private Long id;

    private Integer idConteneurReception;

    private String numeroConteneur;

    private Integer numeroSequence;

    private Long achatConteneurArrivageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdConteneurReception() {
        return idConteneurReception;
    }

    public void setIdConteneurReception(Integer idConteneurReception) {
        this.idConteneurReception = idConteneurReception;
    }

    public String getNumeroConteneur() {
        return numeroConteneur;
    }

    public void setNumeroConteneur(String numeroConteneur) {
        this.numeroConteneur = numeroConteneur;
    }

    public Integer getNumeroSequence() {
        return numeroSequence;
    }

    public void setNumeroSequence(Integer numeroSequence) {
        this.numeroSequence = numeroSequence;
    }

    public Long getAchatConteneurArrivageId() {
        return achatConteneurArrivageId;
    }

    public void setAchatConteneurArrivageId(Long achatConteneurArrivageId) {
        this.achatConteneurArrivageId = achatConteneurArrivageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatConteneurReceptionDTO achatConteneurReceptionDTO = (AchatConteneurReceptionDTO) o;
        if (achatConteneurReceptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatConteneurReceptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatConteneurReceptionDTO{" +
            "id=" + getId() +
            ", idConteneurReception=" + getIdConteneurReception() +
            ", numeroConteneur='" + getNumeroConteneur() + "'" +
            ", numeroSequence=" + getNumeroSequence() +
            ", achatConteneurArrivage=" + getAchatConteneurArrivageId() +
            "}";
    }
}
