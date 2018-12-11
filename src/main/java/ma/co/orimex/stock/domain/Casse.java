package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A Casse.
 */
@Entity
@Table(name = "casse")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "casse")
public class Casse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_operation")
    private Integer idOperation;

    @Column(name = "date_operation")
    private Instant dateOperation;

    @Column(name = "description")
    private String description;

    @Column(name = "nombre_plateaux")
    private Integer nombrePlateaux;

    @Column(name = "numero_operation")
    private String numeroOperation;

    @Column(name = "superfcie", precision = 10, scale = 2)
    private BigDecimal superfcie;

    @ManyToOne
    @JsonIgnoreProperties("casses")
    private Utilisateur utilisateur;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Caisse caisse;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public Casse idOperation(Integer idOperation) {
        this.idOperation = idOperation;
        return this;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Instant getDateOperation() {
        return dateOperation;
    }

    public Casse dateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getDescription() {
        return description;
    }

    public Casse description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNombrePlateaux() {
        return nombrePlateaux;
    }

    public Casse nombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
        return this;
    }

    public void setNombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
    }

    public String getNumeroOperation() {
        return numeroOperation;
    }

    public Casse numeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
        return this;
    }

    public void setNumeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
    }

    public BigDecimal getSuperfcie() {
        return superfcie;
    }

    public Casse superfcie(BigDecimal superfcie) {
        this.superfcie = superfcie;
        return this;
    }

    public void setSuperfcie(BigDecimal superfcie) {
        this.superfcie = superfcie;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Casse utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public Casse caisse(Caisse caisse) {
        this.caisse = caisse;
        return this;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
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
        Casse casse = (Casse) o;
        if (casse.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), casse.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Casse{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateOperation='" + getDateOperation() + "'" +
            ", description='" + getDescription() + "'" +
            ", nombrePlateaux=" + getNombrePlateaux() +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            ", superfcie=" + getSuperfcie() +
            "}";
    }
}
