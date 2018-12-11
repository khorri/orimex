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
 * A AchatFacture.
 */
@Entity
@Table(name = "achat_facture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatfacture")
public class AchatFacture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_facture")
    private Integer idFacture;

    @Column(name = "numero_facture")
    private String numeroFacture;

    @Column(name = "date_facture")
    private LocalDate dateFacture;

    @Column(name = "montant_fob", precision = 10, scale = 2)
    private BigDecimal montantFob;

    @Column(name = "montant_fret", precision = 10, scale = 2)
    private BigDecimal montantFret;

    @Column(name = "montant_total", precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(name = "nombre_tc")
    private Integer nombreTc;

    @Column(name = "poids", precision = 10, scale = 2)
    private BigDecimal poids;

    @Column(name = "quantite", precision = 10, scale = 2)
    private BigDecimal quantite;

    @Column(name = "ajustement", precision = 10, scale = 2)
    private BigDecimal ajustement;

    @Column(name = "numero_piece")
    private String numeroPiece;

    @Column(name = "date_bl")
    private LocalDate dateBl;

    @Column(name = "numero_bl")
    private String numeroBl;

    @Column(name = "date_echeance")
    private LocalDate dateEcheance;

    @Column(name = "etat")
    private Integer etat;

    @Column(name = "banque_reglement")
    private Integer banqueReglement;

    @Column(name = "date_valeur")
    private LocalDate dateValeur;

    @Column(name = "cours", precision = 10, scale = 2)
    private BigDecimal cours;

    @Column(name = "montant_dh", precision = 10, scale = 2)
    private BigDecimal montantDh;

    @Column(name = "echecance_financement")
    private LocalDate echecanceFinancement;

    @Column(name = "interet_1", precision = 10, scale = 2)
    private BigDecimal interet1;

    @Column(name = "date_reglement")
    private LocalDate dateReglement;

    @Column(name = "derniere_echeance")
    private LocalDate derniereEcheance;

    @Column(name = "transmise")
    private Integer transmise;

    @Column(name = "echeance_refinancement")
    private LocalDate echeanceRefinancement;

    @Column(name = "interet_2", precision = 10, scale = 2)
    private BigDecimal interet2;

    @Column(name = "interet_1_regle")
    private Integer interet1Regle;

    @ManyToOne
    @JsonIgnoreProperties("achatFactures")
    private AchatArrivage achatArrivage;

    @OneToMany(mappedBy = "achatFacture")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AchatConteneurArrivage> achatConteneurArrivages = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdFacture() {
        return idFacture;
    }

    public AchatFacture idFacture(Integer idFacture) {
        this.idFacture = idFacture;
        return this;
    }

    public void setIdFacture(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public AchatFacture numeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
        return this;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public AchatFacture dateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
        return this;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
    }

    public BigDecimal getMontantFob() {
        return montantFob;
    }

    public AchatFacture montantFob(BigDecimal montantFob) {
        this.montantFob = montantFob;
        return this;
    }

    public void setMontantFob(BigDecimal montantFob) {
        this.montantFob = montantFob;
    }

    public BigDecimal getMontantFret() {
        return montantFret;
    }

    public AchatFacture montantFret(BigDecimal montantFret) {
        this.montantFret = montantFret;
        return this;
    }

    public void setMontantFret(BigDecimal montantFret) {
        this.montantFret = montantFret;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public AchatFacture montantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
        return this;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Integer getNombreTc() {
        return nombreTc;
    }

    public AchatFacture nombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
        return this;
    }

    public void setNombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public AchatFacture poids(BigDecimal poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public AchatFacture quantite(BigDecimal quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getAjustement() {
        return ajustement;
    }

    public AchatFacture ajustement(BigDecimal ajustement) {
        this.ajustement = ajustement;
        return this;
    }

    public void setAjustement(BigDecimal ajustement) {
        this.ajustement = ajustement;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public AchatFacture numeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
        return this;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public LocalDate getDateBl() {
        return dateBl;
    }

    public AchatFacture dateBl(LocalDate dateBl) {
        this.dateBl = dateBl;
        return this;
    }

    public void setDateBl(LocalDate dateBl) {
        this.dateBl = dateBl;
    }

    public String getNumeroBl() {
        return numeroBl;
    }

    public AchatFacture numeroBl(String numeroBl) {
        this.numeroBl = numeroBl;
        return this;
    }

    public void setNumeroBl(String numeroBl) {
        this.numeroBl = numeroBl;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public AchatFacture dateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
        return this;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Integer getEtat() {
        return etat;
    }

    public AchatFacture etat(Integer etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Integer getBanqueReglement() {
        return banqueReglement;
    }

    public AchatFacture banqueReglement(Integer banqueReglement) {
        this.banqueReglement = banqueReglement;
        return this;
    }

    public void setBanqueReglement(Integer banqueReglement) {
        this.banqueReglement = banqueReglement;
    }

    public LocalDate getDateValeur() {
        return dateValeur;
    }

    public AchatFacture dateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
        return this;
    }

    public void setDateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
    }

    public BigDecimal getCours() {
        return cours;
    }

    public AchatFacture cours(BigDecimal cours) {
        this.cours = cours;
        return this;
    }

    public void setCours(BigDecimal cours) {
        this.cours = cours;
    }

    public BigDecimal getMontantDh() {
        return montantDh;
    }

    public AchatFacture montantDh(BigDecimal montantDh) {
        this.montantDh = montantDh;
        return this;
    }

    public void setMontantDh(BigDecimal montantDh) {
        this.montantDh = montantDh;
    }

    public LocalDate getEchecanceFinancement() {
        return echecanceFinancement;
    }

    public AchatFacture echecanceFinancement(LocalDate echecanceFinancement) {
        this.echecanceFinancement = echecanceFinancement;
        return this;
    }

    public void setEchecanceFinancement(LocalDate echecanceFinancement) {
        this.echecanceFinancement = echecanceFinancement;
    }

    public BigDecimal getInteret1() {
        return interet1;
    }

    public AchatFacture interet1(BigDecimal interet1) {
        this.interet1 = interet1;
        return this;
    }

    public void setInteret1(BigDecimal interet1) {
        this.interet1 = interet1;
    }

    public LocalDate getDateReglement() {
        return dateReglement;
    }

    public AchatFacture dateReglement(LocalDate dateReglement) {
        this.dateReglement = dateReglement;
        return this;
    }

    public void setDateReglement(LocalDate dateReglement) {
        this.dateReglement = dateReglement;
    }

    public LocalDate getDerniereEcheance() {
        return derniereEcheance;
    }

    public AchatFacture derniereEcheance(LocalDate derniereEcheance) {
        this.derniereEcheance = derniereEcheance;
        return this;
    }

    public void setDerniereEcheance(LocalDate derniereEcheance) {
        this.derniereEcheance = derniereEcheance;
    }

    public Integer getTransmise() {
        return transmise;
    }

    public AchatFacture transmise(Integer transmise) {
        this.transmise = transmise;
        return this;
    }

    public void setTransmise(Integer transmise) {
        this.transmise = transmise;
    }

    public LocalDate getEcheanceRefinancement() {
        return echeanceRefinancement;
    }

    public AchatFacture echeanceRefinancement(LocalDate echeanceRefinancement) {
        this.echeanceRefinancement = echeanceRefinancement;
        return this;
    }

    public void setEcheanceRefinancement(LocalDate echeanceRefinancement) {
        this.echeanceRefinancement = echeanceRefinancement;
    }

    public BigDecimal getInteret2() {
        return interet2;
    }

    public AchatFacture interet2(BigDecimal interet2) {
        this.interet2 = interet2;
        return this;
    }

    public void setInteret2(BigDecimal interet2) {
        this.interet2 = interet2;
    }

    public Integer getInteret1Regle() {
        return interet1Regle;
    }

    public AchatFacture interet1Regle(Integer interet1Regle) {
        this.interet1Regle = interet1Regle;
        return this;
    }

    public void setInteret1Regle(Integer interet1Regle) {
        this.interet1Regle = interet1Regle;
    }

    public AchatArrivage getAchatArrivage() {
        return achatArrivage;
    }

    public AchatFacture achatArrivage(AchatArrivage achatArrivage) {
        this.achatArrivage = achatArrivage;
        return this;
    }

    public void setAchatArrivage(AchatArrivage achatArrivage) {
        this.achatArrivage = achatArrivage;
    }

    public Set<AchatConteneurArrivage> getAchatConteneurArrivages() {
        return achatConteneurArrivages;
    }

    public AchatFacture achatConteneurArrivages(Set<AchatConteneurArrivage> achatConteneurArrivages) {
        this.achatConteneurArrivages = achatConteneurArrivages;
        return this;
    }

    public AchatFacture addAchatConteneurArrivage(AchatConteneurArrivage achatConteneurArrivage) {
        this.achatConteneurArrivages.add(achatConteneurArrivage);
        achatConteneurArrivage.setAchatFacture(this);
        return this;
    }

    public AchatFacture removeAchatConteneurArrivage(AchatConteneurArrivage achatConteneurArrivage) {
        this.achatConteneurArrivages.remove(achatConteneurArrivage);
        achatConteneurArrivage.setAchatFacture(null);
        return this;
    }

    public void setAchatConteneurArrivages(Set<AchatConteneurArrivage> achatConteneurArrivages) {
        this.achatConteneurArrivages = achatConteneurArrivages;
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
        AchatFacture achatFacture = (AchatFacture) o;
        if (achatFacture.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatFacture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatFacture{" +
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
            "}";
    }
}
