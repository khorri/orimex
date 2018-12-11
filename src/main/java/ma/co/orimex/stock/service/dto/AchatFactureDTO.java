package ma.co.orimex.stock.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the AchatFacture entity.
 */
public class AchatFactureDTO implements Serializable {

    private Long id;

    private Integer idFacture;

    private String numeroFacture;

    private LocalDate dateFacture;

    private BigDecimal montantFob;

    private BigDecimal montantFret;

    private BigDecimal montantTotal;

    private Integer nombreTc;

    private BigDecimal poids;

    private BigDecimal quantite;

    private BigDecimal ajustement;

    private String numeroPiece;

    private LocalDate dateBl;

    private String numeroBl;

    private LocalDate dateEcheance;

    private Integer etat;

    private Integer banqueReglement;

    private LocalDate dateValeur;

    private BigDecimal cours;

    private BigDecimal montantDh;

    private LocalDate echecanceFinancement;

    private BigDecimal interet1;

    private LocalDate dateReglement;

    private LocalDate derniereEcheance;

    private Integer transmise;

    private LocalDate echeanceRefinancement;

    private BigDecimal interet2;

    private Integer interet1Regle;

    private Long achatArrivageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
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

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getAjustement() {
        return ajustement;
    }

    public void setAjustement(BigDecimal ajustement) {
        this.ajustement = ajustement;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public LocalDate getDateBl() {
        return dateBl;
    }

    public void setDateBl(LocalDate dateBl) {
        this.dateBl = dateBl;
    }

    public String getNumeroBl() {
        return numeroBl;
    }

    public void setNumeroBl(String numeroBl) {
        this.numeroBl = numeroBl;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Integer getBanqueReglement() {
        return banqueReglement;
    }

    public void setBanqueReglement(Integer banqueReglement) {
        this.banqueReglement = banqueReglement;
    }

    public LocalDate getDateValeur() {
        return dateValeur;
    }

    public void setDateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
    }

    public BigDecimal getCours() {
        return cours;
    }

    public void setCours(BigDecimal cours) {
        this.cours = cours;
    }

    public BigDecimal getMontantDh() {
        return montantDh;
    }

    public void setMontantDh(BigDecimal montantDh) {
        this.montantDh = montantDh;
    }

    public LocalDate getEchecanceFinancement() {
        return echecanceFinancement;
    }

    public void setEchecanceFinancement(LocalDate echecanceFinancement) {
        this.echecanceFinancement = echecanceFinancement;
    }

    public BigDecimal getInteret1() {
        return interet1;
    }

    public void setInteret1(BigDecimal interet1) {
        this.interet1 = interet1;
    }

    public LocalDate getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(LocalDate dateReglement) {
        this.dateReglement = dateReglement;
    }

    public LocalDate getDerniereEcheance() {
        return derniereEcheance;
    }

    public void setDerniereEcheance(LocalDate derniereEcheance) {
        this.derniereEcheance = derniereEcheance;
    }

    public Integer getTransmise() {
        return transmise;
    }

    public void setTransmise(Integer transmise) {
        this.transmise = transmise;
    }

    public LocalDate getEcheanceRefinancement() {
        return echeanceRefinancement;
    }

    public void setEcheanceRefinancement(LocalDate echeanceRefinancement) {
        this.echeanceRefinancement = echeanceRefinancement;
    }

    public BigDecimal getInteret2() {
        return interet2;
    }

    public void setInteret2(BigDecimal interet2) {
        this.interet2 = interet2;
    }

    public Integer getInteret1Regle() {
        return interet1Regle;
    }

    public void setInteret1Regle(Integer interet1Regle) {
        this.interet1Regle = interet1Regle;
    }

    public Long getAchatArrivageId() {
        return achatArrivageId;
    }

    public void setAchatArrivageId(Long achatArrivageId) {
        this.achatArrivageId = achatArrivageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatFactureDTO achatFactureDTO = (AchatFactureDTO) o;
        if (achatFactureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatFactureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatFactureDTO{" +
            "id=" + getId() +
            ", idFacture=" + getIdFacture() +
            ", numeroFacture='" + getNumeroFacture() + "'" +
            ", dateFacture='" + getDateFacture() + "'" +
            ", montantFob=" + getMontantFob() +
            ", montantFret=" + getMontantFret() +
            ", montantTotal=" + getMontantTotal() +
            ", nombreTc=" + getNombreTc() +
            ", poids=" + getPoids() +
            ", quantite=" + getQuantite() +
            ", ajustement=" + getAjustement() +
            ", numeroPiece='" + getNumeroPiece() + "'" +
            ", dateBl='" + getDateBl() + "'" +
            ", numeroBl='" + getNumeroBl() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            ", etat=" + getEtat() +
            ", banqueReglement=" + getBanqueReglement() +
            ", dateValeur='" + getDateValeur() + "'" +
            ", cours=" + getCours() +
            ", montantDh=" + getMontantDh() +
            ", echecanceFinancement='" + getEchecanceFinancement() + "'" +
            ", interet1=" + getInteret1() +
            ", dateReglement='" + getDateReglement() + "'" +
            ", derniereEcheance='" + getDerniereEcheance() + "'" +
            ", transmise=" + getTransmise() +
            ", echeanceRefinancement='" + getEcheanceRefinancement() + "'" +
            ", interet2=" + getInteret2() +
            ", interet1Regle=" + getInteret1Regle() +
            ", achatArrivage=" + getAchatArrivageId() +
            "}";
    }
}
