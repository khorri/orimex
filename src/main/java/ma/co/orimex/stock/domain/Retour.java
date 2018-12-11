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
 * A Retour.
 */
@Entity
@Table(name = "retour")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "retour")
public class Retour implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_operation")
    private Integer idOperation;

    @Column(name = "date_operation")
    private Instant dateOperation;

    @Column(name = "nombre_plateaux")
    private Integer nombrePlateaux;

    @Column(name = "numero_operation")
    private String numeroOperation;

    @Column(name = "superfecie", precision = 10, scale = 2)
    private BigDecimal superfecie;

    @ManyToOne
    @JsonIgnoreProperties("retours")
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties("retours")
    private Utilisateur utilisateur;

    @ManyToOne
    @JsonIgnoreProperties("retours")
    private Bon bon;

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

    public Retour idOperation(Integer idOperation) {
        this.idOperation = idOperation;
        return this;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Instant getDateOperation() {
        return dateOperation;
    }

    public Retour dateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Integer getNombrePlateaux() {
        return nombrePlateaux;
    }

    public Retour nombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
        return this;
    }

    public void setNombrePlateaux(Integer nombrePlateaux) {
        this.nombrePlateaux = nombrePlateaux;
    }

    public String getNumeroOperation() {
        return numeroOperation;
    }

    public Retour numeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
        return this;
    }

    public void setNumeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
    }

    public BigDecimal getSuperfecie() {
        return superfecie;
    }

    public Retour superfecie(BigDecimal superfecie) {
        this.superfecie = superfecie;
        return this;
    }

    public void setSuperfecie(BigDecimal superfecie) {
        this.superfecie = superfecie;
    }

    public Produit getProduit() {
        return produit;
    }

    public Retour produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Retour utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Bon getBon() {
        return bon;
    }

    public Retour bon(Bon bon) {
        this.bon = bon;
        return this;
    }

    public void setBon(Bon bon) {
        this.bon = bon;
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
        Retour retour = (Retour) o;
        if (retour.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), retour.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Retour{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateOperation='" + getDateOperation() + "'" +
            ", nombrePlateaux=" + getNombrePlateaux() +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            ", superfecie=" + getSuperfecie() +
            "}";
    }
}
