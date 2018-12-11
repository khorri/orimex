package ma.co.orimex.stock.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the AchatDossier entity.
 */
public class AchatDossierDTO implements Serializable {

    private Long id;

    private Integer idDossier;

    private String numeroDossier;

    private String codeFournisseur;

    private String designationFournisseur;

    private String incoterm;

    private String reference;

    private Integer tolerance;

    private String numeroEi;

    private String numeroEiComp;

    private LocalDate dateCreation;

    private Integer delaiPaiement;

    private Integer delaiValiditeLc;

    private LocalDate dateExpedition;

    private LocalDate dateOuverture;

    private LocalDate dateValiditeEi;

    private LocalDate dateLimiteExp;

    private LocalDate dateValiditeLc;

    private BigDecimal montnatTotal;

    private BigDecimal montnatFob;

    private BigDecimal montnatFret;

    private Integer totalTc;

    private String designationBanque;

    private Integer paiementAvue;

    private BigDecimal encours;

    private Long achatBanqueId;

    private Long typePaiementId;

    private Long achatDeviseId;

    private Long achatStatutDossierId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(Integer idDossier) {
        this.idDossier = idDossier;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getCodeFournisseur() {
        return codeFournisseur;
    }

    public void setCodeFournisseur(String codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
    }

    public String getDesignationFournisseur() {
        return designationFournisseur;
    }

    public void setDesignationFournisseur(String designationFournisseur) {
        this.designationFournisseur = designationFournisseur;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getTolerance() {
        return tolerance;
    }

    public void setTolerance(Integer tolerance) {
        this.tolerance = tolerance;
    }

    public String getNumeroEi() {
        return numeroEi;
    }

    public void setNumeroEi(String numeroEi) {
        this.numeroEi = numeroEi;
    }

    public String getNumeroEiComp() {
        return numeroEiComp;
    }

    public void setNumeroEiComp(String numeroEiComp) {
        this.numeroEiComp = numeroEiComp;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Integer getDelaiPaiement() {
        return delaiPaiement;
    }

    public void setDelaiPaiement(Integer delaiPaiement) {
        this.delaiPaiement = delaiPaiement;
    }

    public Integer getDelaiValiditeLc() {
        return delaiValiditeLc;
    }

    public void setDelaiValiditeLc(Integer delaiValiditeLc) {
        this.delaiValiditeLc = delaiValiditeLc;
    }

    public LocalDate getDateExpedition() {
        return dateExpedition;
    }

    public void setDateExpedition(LocalDate dateExpedition) {
        this.dateExpedition = dateExpedition;
    }

    public LocalDate getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(LocalDate dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public LocalDate getDateValiditeEi() {
        return dateValiditeEi;
    }

    public void setDateValiditeEi(LocalDate dateValiditeEi) {
        this.dateValiditeEi = dateValiditeEi;
    }

    public LocalDate getDateLimiteExp() {
        return dateLimiteExp;
    }

    public void setDateLimiteExp(LocalDate dateLimiteExp) {
        this.dateLimiteExp = dateLimiteExp;
    }

    public LocalDate getDateValiditeLc() {
        return dateValiditeLc;
    }

    public void setDateValiditeLc(LocalDate dateValiditeLc) {
        this.dateValiditeLc = dateValiditeLc;
    }

    public BigDecimal getMontnatTotal() {
        return montnatTotal;
    }

    public void setMontnatTotal(BigDecimal montnatTotal) {
        this.montnatTotal = montnatTotal;
    }

    public BigDecimal getMontnatFob() {
        return montnatFob;
    }

    public void setMontnatFob(BigDecimal montnatFob) {
        this.montnatFob = montnatFob;
    }

    public BigDecimal getMontnatFret() {
        return montnatFret;
    }

    public void setMontnatFret(BigDecimal montnatFret) {
        this.montnatFret = montnatFret;
    }

    public Integer getTotalTc() {
        return totalTc;
    }

    public void setTotalTc(Integer totalTc) {
        this.totalTc = totalTc;
    }

    public String getDesignationBanque() {
        return designationBanque;
    }

    public void setDesignationBanque(String designationBanque) {
        this.designationBanque = designationBanque;
    }

    public Integer getPaiementAvue() {
        return paiementAvue;
    }

    public void setPaiementAvue(Integer paiementAvue) {
        this.paiementAvue = paiementAvue;
    }

    public BigDecimal getEncours() {
        return encours;
    }

    public void setEncours(BigDecimal encours) {
        this.encours = encours;
    }

    public Long getAchatBanqueId() {
        return achatBanqueId;
    }

    public void setAchatBanqueId(Long achatBanqueId) {
        this.achatBanqueId = achatBanqueId;
    }

    public Long getTypePaiementId() {
        return typePaiementId;
    }

    public void setTypePaiementId(Long achatTypePaiementId) {
        this.typePaiementId = achatTypePaiementId;
    }

    public Long getAchatDeviseId() {
        return achatDeviseId;
    }

    public void setAchatDeviseId(Long achatDeviseId) {
        this.achatDeviseId = achatDeviseId;
    }

    public Long getAchatStatutDossierId() {
        return achatStatutDossierId;
    }

    public void setAchatStatutDossierId(Long achatStatutDossierId) {
        this.achatStatutDossierId = achatStatutDossierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatDossierDTO achatDossierDTO = (AchatDossierDTO) o;
        if (achatDossierDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatDossierDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatDossierDTO{" +
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
            ", achatBanque=" + getAchatBanqueId() +
            ", typePaiement=" + getTypePaiementId() +
            ", achatDevise=" + getAchatDeviseId() +
            ", achatStatutDossier=" + getAchatStatutDossierId() +
            "}";
    }
}
