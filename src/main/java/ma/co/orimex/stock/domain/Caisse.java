package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Caisse.
 */
@Entity
@Table(name = "caisse")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "caisse")
public class Caisse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_caisse")
    private Integer idCaisse;

    @Column(name = "nombre_plateaux")
    private Integer nombrePlateaux;

    @Column(name = "numero_conteneur")
    private String numeroConteneur;

    @Column(name = "reference_caisse")
    private String referenceCaisse;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCaisse() {
        return idCaisse;
    }

    public Caisse idCaisse(Integer idCaisse) {
        this.idCaisse = idCaisse;
        return this;
    }

    public void setIdCaisse(Integer idCaisse) {
        this.idCaisse = idCaisse;
    }

    public Integer getNombrePlateaux() {
        return nombrePlateaux;
    }

    public Caisse nombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
        return this;
    }

    public void setNombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
    }

    public String getNumeroConteneur() {
        return numeroConteneur;
    }

    public Caisse numeroConteneur(String numeroConteneur) {
        this.numeroConteneur = numeroConteneur;
        return this;
    }

    public void setNumeroConteneur(String numeroConteneur) {
        this.numeroConteneur = numeroConteneur;
    }

    public String getReferenceCaisse() {
        return referenceCaisse;
    }

    public Caisse referenceCaisse(String referenceCaisse) {
        this.referenceCaisse = referenceCaisse;
        return this;
    }

    public void setReferenceCaisse(String referenceCaisse) {
        this.referenceCaisse = referenceCaisse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Caisse caisse = (Caisse) o;
        if (caisse.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caisse.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Caisse{" +
            "id=" + getId() +
            ", idCaisse=" + getIdCaisse() +
            ", nombrePlateaux=" + getNombrePlateaux() +
            ", numeroConteneur='" + getNumeroConteneur() + "'" +
            ", referenceCaisse='" + getReferenceCaisse() + "'" +
            "}";
    }
}
