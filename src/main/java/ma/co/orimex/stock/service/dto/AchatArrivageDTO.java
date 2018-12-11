package ma.co.orimex.stock.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the AchatArrivage entity.
 */
public class AchatArrivageDTO implements Serializable {

    private Long id;

    private Integer idArrivage;

    private String numeroDossierArrivage;

    private String codeCompagnieMaritime;

    private String codeOperateur;

    private String codeTransitaire;

    private String codeTransporteur;

    private LocalDate dateArrivePort;

    private String designationCompagnieMaritime;

    private String designationOperateur;

    private String designationTransitaire;

    private String designationTransporteur;

    private String flagProduit;

    private Integer franchise;

    private BigDecimal montantFob;

    private BigDecimal montantFret;

    private BigDecimal montantTotal;

    private Integer nombreTc;

    private LocalDate dateRealisation;

    private BigDecimal poid;

    private Long achatDossierId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdArrivage() {
        return idArrivage;
    }

    public void setIdArrivage(Integer idArrivage) {
        this.idArrivage = idArrivage;
    }

    public String getNumeroDossierArrivage() {
        return numeroDossierArrivage;
    }

    public void setNumeroDossierArrivage(String numeroDossierArrivage) {
        this.numeroDossierArrivage = numeroDossierArrivage;
    }

    public String getCodeCompagnieMaritime() {
        return codeCompagnieMaritime;
    }

    public void setCodeCompagnieMaritime(String codeCompagnieMaritime) {
        this.codeCompagnieMaritime = codeCompagnieMaritime;
    }

    public String getCodeOperateur() {
        return codeOperateur;
    }

    public void setCodeOperateur(String codeOperateur) {
        this.codeOperateur = codeOperateur;
    }

    public String getCodeTransitaire() {
        return codeTransitaire;
    }

    public void setCodeTransitaire(String codeTransitaire) {
        this.codeTransitaire = codeTransitaire;
    }

    public String getCodeTransporteur() {
        return codeTransporteur;
    }

    public void setCodeTransporteur(String codeTransporteur) {
        this.codeTransporteur = codeTransporteur;
    }

    public LocalDate getDateArrivePort() {
        return dateArrivePort;
    }

    public void setDateArrivePort(LocalDate dateArrivePort) {
        this.dateArrivePort = dateArrivePort;
    }

    public String getDesignationCompagnieMaritime() {
        return designationCompagnieMaritime;
    }

    public void setDesignationCompagnieMaritime(String designationCompagnieMaritime) {
        this.designationCompagnieMaritime = designationCompagnieMaritime;
    }

    public String getDesignationOperateur() {
        return designationOperateur;
    }

    public void setDesignationOperateur(String designationOperateur) {
        this.designationOperateur = designationOperateur;
    }

    public String getDesignationTransitaire() {
        return designationTransitaire;
    }

    public void setDesignationTransitaire(String designationTransitaire) {
        this.designationTransitaire = designationTransitaire;
    }

    public String getDesignationTransporteur() {
        return designationTransporteur;
    }

    public void setDesignationTransporteur(String designationTransporteur) {
        this.designationTransporteur = designationTransporteur;
    }

    public String getFlagProduit() {
        return flagProduit;
    }

    public void setFlagProduit(String flagProduit) {
        this.flagProduit = flagProduit;
    }

    public Integer getFranchise() {
        return franchise;
    }

    public void setFranchise(Integer franchise) {
        this.franchise = franchise;
    }

    public BigDecimal getMontantFob() {
        return montantFob;
    }

    public void setMontantFob(BigDecimal montantFob) {
        this.montantFob = montantFob;
    }

    public BigDecimal getMontantFret() {
        return montantFret;
    }

    public void setMontantFret(BigDecimal montantFret) {
        this.montantFret = montantFret;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Integer getNombreTc() {
        return nombreTc;
    }

    public void setNombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
    }

    public LocalDate getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(LocalDate dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public BigDecimal getPoid() {
        return poid;
    }

    public void setPoid(BigDecimal poid) {
        this.poid = poid;
    }

    public Long getAchatDossierId() {
        return achatDossierId;
    }

    public void setAchatDossierId(Long achatDossierId) {
        this.achatDossierId = achatDossierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatArrivageDTO achatArrivageDTO = (AchatArrivageDTO) o;
        if (achatArrivageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatArrivageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatArrivageDTO{" +
            "id=" + getId() +
            ", idArrivage=" + getIdArrivage() +
            ", numeroDossierArrivage='" + getNumeroDossierArrivage() + "'" +
            ", codeCompagnieMaritime='" + getCodeCompagnieMaritime() + "'" +
            ", codeOperateur='" + getCodeOperateur() + "'" +
            ", codeTransitaire='" + getCodeTransitaire() + "'" +
            ", codeTransporteur='" + getCodeTransporteur() + "'" +
            ", dateArrivePort='" + getDateArrivePort() + "'" +
            ", designationCompagnieMaritime='" + getDesignationCompagnieMaritime() + "'" +
            ", designationOperateur='" + getDesignationOperateur() + "'" +
            ", designationTransitaire='" + getDesignationTransitaire() + "'" +
            ", designationTransporteur='" + getDesignationTransporteur() + "'" +
            ", flagProduit='" + getFlagProduit() + "'" +
            ", franchise=" + getFranchise() +
            ", montantFob=" + getMontantFob() +
            ", montantFret=" + getMontantFret() +
            ", montantTotal=" + getMontantTotal() +
            ", nombreTc=" + getNombreTc() +
            ", dateRealisation='" + getDateRealisation() + "'" +
            ", poid=" + getPoid() +
            ", achatDossier=" + getAchatDossierId() +
            "}";
    }
}
