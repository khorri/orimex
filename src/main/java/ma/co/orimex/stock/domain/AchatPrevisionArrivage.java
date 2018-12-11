package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The Employee entity.
 */
@ApiModel(description = "The Employee entity.")
@Entity
@Table(name = "achat_prevision_arrivage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatprevisionarrivage")
public class AchatPrevisionArrivage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_prevision_arrivage")
    private Integer idPrevisionArrivage;

    @Column(name = "produit")
    private String produit;

    @Column(name = "desigantion_copagnie_maritme")
    private String desigantionCopagnieMaritme;

    @Column(name = "pol")
    private String pol;

    @Column(name = "numero_bl")
    private String numeroBl;

    @Column(name = "nombre_tc")
    private Integer nombreTc;

    @Column(name = "etd")
    private LocalDate etd;

    @Column(name = "eta")
    private LocalDate eta;

    @Column(name = "documents")
    private String documents;

    @Column(name = "douane")
    private String douane;

    @Column(name = "active")
    private Integer active;

    @ManyToOne
    @JsonIgnoreProperties("achatPrevisionArrivages")
    private AchatDossier achatDossier;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPrevisionArrivage() {
        return idPrevisionArrivage;
    }

    public AchatPrevisionArrivage idPrevisionArrivage(Integer idPrevisionArrivage) {
        this.idPrevisionArrivage = idPrevisionArrivage;
        return this;
    }

    public void setIdPrevisionArrivage(Integer idPrevisionArrivage) {
        this.idPrevisionArrivage = idPrevisionArrivage;
    }

    public String getProduit() {
        return produit;
    }

    public AchatPrevisionArrivage produit(String produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getDesigantionCopagnieMaritme() {
        return desigantionCopagnieMaritme;
    }

    public AchatPrevisionArrivage desigantionCopagnieMaritme(String desigantionCopagnieMaritme) {
        this.desigantionCopagnieMaritme = desigantionCopagnieMaritme;
        return this;
    }

    public void setDesigantionCopagnieMaritme(String desigantionCopagnieMaritme) {
        this.desigantionCopagnieMaritme = desigantionCopagnieMaritme;
    }

    public String getPol() {
        return pol;
    }

    public AchatPrevisionArrivage pol(String pol) {
        this.pol = pol;
        return this;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getNumeroBl() {
        return numeroBl;
    }

    public AchatPrevisionArrivage numeroBl(String numeroBl) {
        this.numeroBl = numeroBl;
        return this;
    }

    public void setNumeroBl(String numeroBl) {
        this.numeroBl = numeroBl;
    }

    public Integer getNombreTc() {
        return nombreTc;
    }

    public AchatPrevisionArrivage nombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
        return this;
    }

    public void setNombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
    }

    public LocalDate getEtd() {
        return etd;
    }

    public AchatPrevisionArrivage etd(LocalDate etd) {
        this.etd = etd;
        return this;
    }

    public void setEtd(LocalDate etd) {
        this.etd = etd;
    }

    public LocalDate getEta() {
        return eta;
    }

    public AchatPrevisionArrivage eta(LocalDate eta) {
        this.eta = eta;
        return this;
    }

    public void setEta(LocalDate eta) {
        this.eta = eta;
    }

    public String getDocuments() {
        return documents;
    }

    public AchatPrevisionArrivage documents(String documents) {
        this.documents = documents;
        return this;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getDouane() {
        return douane;
    }

    public AchatPrevisionArrivage douane(String douane) {
        this.douane = douane;
        return this;
    }

    public void setDouane(String douane) {
        this.douane = douane;
    }

    public Integer getActive() {
        return active;
    }

    public AchatPrevisionArrivage active(Integer active) {
        this.active = active;
        return this;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public AchatDossier getAchatDossier() {
        return achatDossier;
    }

    public AchatPrevisionArrivage achatDossier(AchatDossier achatDossier) {
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
        AchatPrevisionArrivage achatPrevisionArrivage = (AchatPrevisionArrivage) o;
        if (achatPrevisionArrivage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatPrevisionArrivage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatPrevisionArrivage{" +
            "id=" + getId() +
            ", idPrevisionArrivage=" + getIdPrevisionArrivage() +
            ", produit='" + getProduit() + "'" +
            ", desigantionCopagnieMaritme='" + getDesigantionCopagnieMaritme() + "'" +
            ", pol='" + getPol() + "'" +
            ", numeroBl='" + getNumeroBl() + "'" +
            ", nombreTc=" + getNombreTc() +
            ", etd='" + getEtd() + "'" +
            ", eta='" + getEta() + "'" +
            ", documents='" + getDocuments() + "'" +
            ", douane='" + getDouane() + "'" +
            ", active=" + getActive() +
            "}";
    }
}
