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
 * A AchatDossier.
 */
@Entity
@Table(name = "achat_dossier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatdossier")
public class AchatDossier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_dossier")
    private Integer idDossier;

    @Column(name = "numero_dossier")
    private String numeroDossier;

    @Column(name = "code_fournisseur")
    private String codeFournisseur;

    @Column(name = "designation_fournisseur")
    private String designationFournisseur;

    @Column(name = "incoterm")
    private String incoterm;

    @Column(name = "reference")
    private String reference;

    @Column(name = "tolerance")
    private Integer tolerance;

    @Column(name = "numero_ei")
    private String numeroEi;

    @Column(name = "numero_ei_comp")
    private String numeroEiComp;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "delai_paiement")
    private Integer delaiPaiement;

    @Column(name = "delai_validite_lc")
    private Integer delaiValiditeLc;

    @Column(name = "date_expedition")
    private LocalDate dateExpedition;

    @Column(name = "date_ouverture")
    private LocalDate dateOuverture;

    @Column(name = "date_validite_ei")
    private LocalDate dateValiditeEi;

    @Column(name = "date_limite_exp")
    private LocalDate dateLimiteExp;

    @Column(name = "date_validite_lc")
    private LocalDate dateValiditeLc;

    @Column(name = "montnat_total", precision = 10, scale = 2)
    private BigDecimal montnatTotal;

    @Column(name = "montnat_fob", precision = 10, scale = 2)
    private BigDecimal montnatFob;

    @Column(name = "montnat_fret", precision = 10, scale = 2)
    private BigDecimal montnatFret;

    @Column(name = "total_tc")
    private Integer totalTc;

    @Column(name = "designation_banque")
    private String designationBanque;

    @Column(name = "paiement_avue")
    private Integer paiementAvue;

    @Column(name = "encours", precision = 10, scale = 2)
    private BigDecimal encours;

    @ManyToOne
    @JsonIgnoreProperties("achatDossiers")
    private AchatBanque achatBanque;

    @ManyToOne
    @JsonIgnoreProperties("")
    private AchatTypePaiement typePaiement;

    @ManyToOne
    @JsonIgnoreProperties("achatDossiers")
    private AchatDevise achatDevise;

    @OneToMany(mappedBy = "achatDossier")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AchatPrevisionArrivage> achatPrevisionArrivages = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("achatDossiers")
    private AchatStatutDossier achatStatutDossier;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdDossier() {
        return idDossier;
    }

    public AchatDossier idDossier(Integer idDossier) {
        this.idDossier = idDossier;
        return this;
    }

    public void setIdDossier(Integer idDossier) {
        this.idDossier = idDossier;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public AchatDossier numeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
        return this;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getCodeFournisseur() {
        return codeFournisseur;
    }

    public AchatDossier codeFournisseur(String codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
        return this;
    }

    public void setCodeFournisseur(String codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
    }

    public String getDesignationFournisseur() {
        return designationFournisseur;
    }

    public AchatDossier designationFournisseur(String designationFournisseur) {
        this.designationFournisseur = designationFournisseur;
        return this;
    }

    public void setDesignationFournisseur(String designationFournisseur) {
        this.designationFournisseur = designationFournisseur;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public AchatDossier incoterm(String incoterm) {
        this.incoterm = incoterm;
        return this;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }

    public String getReference() {
        return reference;
    }

    public AchatDossier reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getTolerance() {
        return tolerance;
    }

    public AchatDossier tolerance(Integer tolerance) {
        this.tolerance = tolerance;
        return this;
    }

    public void setTolerance(Integer tolerance) {
        this.tolerance = tolerance;
    }

    public String getNumeroEi() {
        return numeroEi;
    }

    public AchatDossier numeroEi(String numeroEi) {
        this.numeroEi = numeroEi;
        return this;
    }

    public void setNumeroEi(String numeroEi) {
        this.numeroEi = numeroEi;
    }

    public String getNumeroEiComp() {
        return numeroEiComp;
    }

    public AchatDossier numeroEiComp(String numeroEiComp) {
        this.numeroEiComp = numeroEiComp;
        return this;
    }

    public void setNumeroEiComp(String numeroEiComp) {
        this.numeroEiComp = numeroEiComp;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public AchatDossier dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Integer getDelaiPaiement() {
        return delaiPaiement;
    }

    public AchatDossier delaiPaiement(Integer delaiPaiement) {
        this.delaiPaiement = delaiPaiement;
        return this;
    }

    public void setDelaiPaiement(Integer delaiPaiement) {
        this.delaiPaiement = delaiPaiement;
    }

    public Integer getDelaiValiditeLc() {
        return delaiValiditeLc;
    }

    public AchatDossier delaiValiditeLc(Integer delaiValiditeLc) {
        this.delaiValiditeLc = delaiValiditeLc;
        return this;
    }

    public void setDelaiValiditeLc(Integer delaiValiditeLc) {
        this.delaiValiditeLc = delaiValiditeLc;
    }

    public LocalDate getDateExpedition() {
        return dateExpedition;
    }

    public AchatDossier dateExpedition(LocalDate dateExpedition) {
        this.dateExpedition = dateExpedition;
        return this;
    }

    public void setDateExpedition(LocalDate dateExpedition) {
        this.dateExpedition = dateExpedition;
    }

    public LocalDate getDateOuverture() {
        return dateOuverture;
    }

    public AchatDossier dateOuverture(LocalDate dateOuverture) {
        this.dateOuverture = dateOuverture;
        return this;
    }

    public void setDateOuverture(LocalDate dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public LocalDate getDateValiditeEi() {
        return dateValiditeEi;
    }

    public AchatDossier dateValiditeEi(LocalDate dateValiditeEi) {
        this.dateValiditeEi = dateValiditeEi;
        return this;
    }

    public void setDateValiditeEi(LocalDate dateValiditeEi) {
        this.dateValiditeEi = dateValiditeEi;
    }

    public LocalDate getDateLimiteExp() {
        return dateLimiteExp;
    }

    public AchatDossier dateLimiteExp(LocalDate dateLimiteExp) {
        this.dateLimiteExp = dateLimiteExp;
        return this;
    }

    public void setDateLimiteExp(LocalDate dateLimiteExp) {
        this.dateLimiteExp = dateLimiteExp;
    }

    public LocalDate getDateValiditeLc() {
        return dateValiditeLc;
    }

    public AchatDossier dateValiditeLc(LocalDate dateValiditeLc) {
        this.dateValiditeLc = dateValiditeLc;
        return this;
    }

    public void setDateValiditeLc(LocalDate dateValiditeLc) {
        this.dateValiditeLc = dateValiditeLc;
    }

    public BigDecimal getMontnatTotal() {
        return montnatTotal;
    }

    public AchatDossier montnatTotal(BigDecimal montnatTotal) {
        this.montnatTotal = montnatTotal;
        return this;
    }

    public void setMontnatTotal(BigDecimal montnatTotal) {
        this.montnatTotal = montnatTotal;
    }

    public BigDecimal getMontnatFob() {
        return montnatFob;
    }

    public AchatDossier montnatFob(BigDecimal montnatFob) {
        this.montnatFob = montnatFob;
        return this;
    }

    public void setMontnatFob(BigDecimal montnatFob) {
        this.montnatFob = montnatFob;
    }

    public BigDecimal getMontnatFret() {
        return montnatFret;
    }

    public AchatDossier montnatFret(BigDecimal montnatFret) {
        this.montnatFret = montnatFret;
        return this;
    }

    public void setMontnatFret(BigDecimal montnatFret) {
        this.montnatFret = montnatFret;
    }

    public Integer getTotalTc() {
        return totalTc;
    }

    public AchatDossier totalTc(Integer totalTc) {
        this.totalTc = totalTc;
        return this;
    }

    public void setTotalTc(Integer totalTc) {
        this.totalTc = totalTc;
    }

    public String getDesignationBanque() {
        return designationBanque;
    }

    public AchatDossier designationBanque(String designationBanque) {
        this.designationBanque = designationBanque;
        return this;
    }

    public void setDesignationBanque(String designationBanque) {
        this.designationBanque = designationBanque;
    }

    public Integer getPaiementAvue() {
        return paiementAvue;
    }

    public AchatDossier paiementAvue(Integer paiementAvue) {
        this.paiementAvue = paiementAvue;
        return this;
    }

    public void setPaiementAvue(Integer paiementAvue) {
        this.paiementAvue = paiementAvue;
    }

    public BigDecimal getEncours() {
        return encours;
    }

    public AchatDossier encours(BigDecimal encours) {
        this.encours = encours;
        return this;
    }

    public void setEncours(BigDecimal encours) {
        this.encours = encours;
    }

    public AchatBanque getAchatBanque() {
        return achatBanque;
    }

    public AchatDossier achatBanque(AchatBanque achatBanque) {
        this.achatBanque = achatBanque;
        return this;
    }

    public void setAchatBanque(AchatBanque achatBanque) {
        this.achatBanque = achatBanque;
    }

    public AchatTypePaiement getTypePaiement() {
        return typePaiement;
    }

    public AchatDossier typePaiement(AchatTypePaiement achatTypePaiement) {
        this.typePaiement = achatTypePaiement;
        return this;
    }

    public void setTypePaiement(AchatTypePaiement achatTypePaiement) {
        this.typePaiement = achatTypePaiement;
    }

    public AchatDevise getAchatDevise() {
        return achatDevise;
    }

    public AchatDossier achatDevise(AchatDevise achatDevise) {
        this.achatDevise = achatDevise;
        return this;
    }

    public void setAchatDevise(AchatDevise achatDevise) {
        this.achatDevise = achatDevise;
    }

    public Set<AchatPrevisionArrivage> getAchatPrevisionArrivages() {
        return achatPrevisionArrivages;
    }

    public AchatDossier achatPrevisionArrivages(Set<AchatPrevisionArrivage> achatPrevisionArrivages) {
        this.achatPrevisionArrivages = achatPrevisionArrivages;
        return this;
    }

    public AchatDossier addAchatPrevisionArrivage(AchatPrevisionArrivage achatPrevisionArrivage) {
        this.achatPrevisionArrivages.add(achatPrevisionArrivage);
        achatPrevisionArrivage.setAchatDossier(this);
        return this;
    }

    public AchatDossier removeAchatPrevisionArrivage(AchatPrevisionArrivage achatPrevisionArrivage) {
        this.achatPrevisionArrivages.remove(achatPrevisionArrivage);
        achatPrevisionArrivage.setAchatDossier(null);
        return this;
    }

    public void setAchatPrevisionArrivages(Set<AchatPrevisionArrivage> achatPrevisionArrivages) {
        this.achatPrevisionArrivages = achatPrevisionArrivages;
    }

    public AchatStatutDossier getAchatStatutDossier() {
        return achatStatutDossier;
    }

    public AchatDossier achatStatutDossier(AchatStatutDossier achatStatutDossier) {
        this.achatStatutDossier = achatStatutDossier;
        return this;
    }

    public void setAchatStatutDossier(AchatStatutDossier achatStatutDossier) {
        this.achatStatutDossier = achatStatutDossier;
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
        AchatDossier achatDossier = (AchatDossier) o;
        if (achatDossier.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatDossier.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatDossier{" +
            "id=" + getId() +
            ", idDossier=" + getIdDossier() +
            ", numeroDossier='" + getNumeroDossier() + "'" +
            ", codeFournisseur='" + getCodeFournisseur() + "'" +
            ", designationFournisseur='" + getDesignationFournisseur() + "'" +
            ", incoterm='" + getIncoterm() + "'" +
            ", reference='" + getReference() + "'" +
            ", tolerance=" + getTolerance() +
            ", numeroEi='" + getNumeroEi() + "'" +
            ", numeroEiComp='" + getNumeroEiComp() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", delaiPaiement=" + getDelaiPaiement() +
            ", delaiValiditeLc=" + getDelaiValiditeLc() +
            ", dateExpedition='" + getDateExpedition() + "'" +
            ", dateOuverture='" + getDateOuverture() + "'" +
            ", dateValiditeEi='" + getDateValiditeEi() + "'" +
            ", dateLimiteExp='" + getDateLimiteExp() + "'" +
            ", dateValiditeLc='" + getDateValiditeLc() + "'" +
            ", montnatTotal=" + getMontnatTotal() +
            ", montnatFob=" + getMontnatFob() +
            ", montnatFret=" + getMontnatFret() +
            ", totalTc=" + getTotalTc() +
            ", designationBanque='" + getDesignationBanque() + "'" +
            ", paiementAvue=" + getPaiementAvue() +
            ", encours=" + getEncours() +
            "}";
    }
}
