package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AchatProforma.
 */
@Entity
@Table(name = "achat_proforma")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatproforma")
public class AchatProforma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_proforma")
    private Integer idProforma;

    @Column(name = "nombre_tc")
    private Integer nombreTc;

    @Column(name = "cout_fob", precision = 10, scale = 2)
    private BigDecimal coutFob;

    @Column(name = "cout_fret", precision = 10, scale = 2)
    private BigDecimal coutFret;

    @Column(name = "montant_total", precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(name = "numero_bon_proforma")
    private String numeroBonProforma;

    @Column(name = "type_acht")
    private String typeAcht;

    @Column(name = "poids", precision = 10, scale = 2)
    private BigDecimal poids;

    @Column(name = "date_proforma")
    private LocalDate dateProforma;

    @OneToMany(mappedBy = "achatProforma")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AchatLigneProforma> achatLigneProformas = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private AchatDossier achatDossier;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdProforma() {
        return idProforma;
    }

    public AchatProforma idProforma(Integer idProforma) {
        this.idProforma = idProforma;
        return this;
    }

    public void setIdProforma(Integer idProforma) {
        this.idProforma = idProforma;
    }

    public Integer getNombreTc() {
        return nombreTc;
    }

    public AchatProforma nombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
        return this;
    }

    public void setNombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
    }

    public BigDecimal getCoutFob() {
        return coutFob;
    }

    public AchatProforma coutFob(BigDecimal coutFob) {
        this.coutFob = coutFob;
        return this;
    }

    public void setCoutFob(BigDecimal coutFob) {
        this.coutFob = coutFob;
    }

    public BigDecimal getCoutFret() {
        return coutFret;
    }

    public AchatProforma coutFret(BigDecimal coutFret) {
        this.coutFret = coutFret;
        return this;
    }

    public void setCoutFret(BigDecimal coutFret) {
        this.coutFret = coutFret;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public AchatProforma montantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
        return this;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getNumeroBonProforma() {
        return numeroBonProforma;
    }

    public AchatProforma numeroBonProforma(String numeroBonProforma) {
        this.numeroBonProforma = numeroBonProforma;
        return this;
    }

    public void setNumeroBonProforma(String numeroBonProforma) {
        this.numeroBonProforma = numeroBonProforma;
    }

    public String getTypeAcht() {
        return typeAcht;
    }

    public AchatProforma typeAcht(String typeAcht) {
        this.typeAcht = typeAcht;
        return this;
    }

    public void setTypeAcht(String typeAcht) {
        this.typeAcht = typeAcht;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public AchatProforma poids(BigDecimal poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public LocalDate getDateProforma() {
        return dateProforma;
    }

    public AchatProforma dateProforma(LocalDate dateProforma) {
        this.dateProforma = dateProforma;
        return this;
    }

    public void setDateProforma(LocalDate dateProforma) {
        this.dateProforma = dateProforma;
    }

    public Set<AchatLigneProforma> getAchatLigneProformas() {
        return achatLigneProformas;
    }

    public AchatProforma achatLigneProformas(Set<AchatLigneProforma> achatLigneProformas) {
        this.achatLigneProformas = achatLigneProformas;
        return this;
    }

    public AchatProforma addAchatLigneProforma(AchatLigneProforma achatLigneProforma) {
        this.achatLigneProformas.add(achatLigneProforma);
        achatLigneProforma.setAchatProforma(this);
        return this;
    }

    public AchatProforma removeAchatLigneProforma(AchatLigneProforma achatLigneProforma) {
        this.achatLigneProformas.remove(achatLigneProforma);
        achatLigneProforma.setAchatProforma(null);
        return this;
    }

    public void setAchatLigneProformas(Set<AchatLigneProforma> achatLigneProformas) {
        this.achatLigneProformas = achatLigneProformas;
    }

    public AchatDossier getAchatDossier() {
        return achatDossier;
    }

    public AchatProforma achatDossier(AchatDossier achatDossier) {
        this.achatDossier = achatDossier;
        return this;
    }

    public void setAchatDossier(AchatDossier achatDossier) {
        this.achatDossier = achatDossier;
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
        AchatProforma achatProforma = (AchatProforma) o;
        if (achatProforma.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatProforma.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatProforma{" +
            "id=" + getId() +
            ", idProforma=" + getIdProforma() +
            ", nombreTc=" + getNombreTc() +
            ", coutFob=" + getCoutFob() +
            ", coutFret=" + getCoutFret() +
            ", montantTotal=" + getMontantTotal() +
            ", numeroBonProforma='" + getNumeroBonProforma() + "'" +
            ", typeAcht='" + getTypeAcht() + "'" +
            ", poids=" + getPoids() +
            ", dateProforma='" + getDateProforma() + "'" +
            "}";
    }
}
