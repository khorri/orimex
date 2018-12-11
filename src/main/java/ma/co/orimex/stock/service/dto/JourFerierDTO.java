package ma.co.orimex.stock.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the JourFerier entity.
 */
public class JourFerierDTO implements Serializable {

    private Long id;

    private Integer annee;

    private LocalDate debut;

    private LocalDate fin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JourFerierDTO jourFerierDTO = (JourFerierDTO) o;
        if (jourFerierDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jourFerierDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JourFerierDTO{" +
            "id=" + getId() +
            ", annee=" + getAnnee() +
            ", debut='" + getDebut() + "'" +
            ", fin='" + getFin() + "'" +
            "}";
    }
}
