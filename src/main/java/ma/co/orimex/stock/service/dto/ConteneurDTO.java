package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Conteneur entity.
 */
public class ConteneurDTO implements Serializable {

    private Long id;

    private String numeroConteneur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroConteneur() {
        return numeroConteneur;
    }

    public void setNumeroConteneur(String numeroConteneur) {
        this.numeroConteneur = numeroConteneur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConteneurDTO conteneurDTO = (ConteneurDTO) o;
        if (conteneurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conteneurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConteneurDTO{" +
            "id=" + getId() +
            ", numeroConteneur='" + getNumeroConteneur() + "'" +
            "}";
    }
}
