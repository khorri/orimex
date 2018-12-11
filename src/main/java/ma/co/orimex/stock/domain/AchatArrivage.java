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
 * A AchatArrivage.
 */
@Entity
@Table(name = "achat_arrivage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "achatarrivage")
public class AchatArrivage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_arrivage")
    private Integer idArrivage;

    @Column(name = "numero_dossier_arrivage")
    private String numeroDossierArrivage;

    @Column(name = "code_compagnie_maritime")
    private String codeCompagnieMaritime;

    @Column(name = "code_operateur")
    private String codeOperateur;

    @Column(name = "code_transitaire")
    private String codeTransitaire;

    @Column(name = "code_transporteur")
    private String codeTransporteur;

    @Column(name = "date_arrive_port")
    private LocalDate dateArrivePort;

    @Column(name = "designation_compagnie_maritime")
    private String designationCompagnieMaritime;

    @Column(name = "designation_operateur")
    private String designationOperateur;

    @Column(name = "designation_transitaire")
    private String designationTransitaire;

    @Column(name = "designation_transporteur")
    private String designationTransporteur;

    @Column(name = "flag_produit")
    private String flagProduit;

    @Column(name = "franchise")
    private Integer franchise;

    @Column(name = "montant_fob", precision = 10, scale = 2)
    private BigDecimal montantFob;

    @Column(name = "montant_fret", precision = 10, scale = 2)
    private BigDecimal montantFret;

    @Column(name = "montant_total", precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(name = "nombre_tc")
    private Integer nombreTc;

    @Column(name = "date_realisation")
    private LocalDate dateRealisation;

    @Column(name = "poid", precision = 10, scale = 2)
    private BigDecimal poid;

    @OneToMany(mappedBy = "achatArrivage")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AchatFacture> achatFactures = new HashSet<>();
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

    public Integer getIdArrivage() {
        return idArrivage;
    }

    public AchatArrivage idArrivage(Integer idArrivage) {
        this.idArrivage = idArrivage;
        return this;
    }

    public void setIdArrivage(Integer idArrivage) {
        this.idArrivage = idArrivage;
    }

    public String getNumeroDossierArrivage() {
        return numeroDossierArrivage;
    }

    public AchatArrivage numeroDossierArrivage(String numeroDossierArrivage) {
        this.numeroDossierArrivage = numeroDossierArrivage;
        return this;
    }

    public void setNumeroDossierArrivage(String numeroDossierArrivage) {
        this.numeroDossierArrivage = numeroDossierArrivage;
    }

    public String getCodeCompagnieMaritime() {
        return codeCompagnieMaritime;
    }

    public AchatArrivage codeCompagnieMaritime(String codeCompagnieMaritime) {
        this.codeCompagnieMaritime = codeCompagnieMaritime;
        return this;
    }

    public void setCodeCompagnieMaritime(String codeCompagnieMaritime) {
        this.codeCompagnieMaritime = codeCompagnieMaritime;
    }

    public String getCodeOperateur() {
        return codeOperateur;
    }

    public AchatArrivage codeOperateur(String codeOperateur) {
        this.codeOperateur = codeOperateur;
        return this;
    }

    public void setCodeOperateur(String codeOperateur) {
        this.codeOperateur = codeOperateur;
    }

    public String getCodeTransitaire() {
        return codeTransitaire;
    }

    public AchatArrivage codeTransitaire(String codeTransitaire) {
        this.codeTransitaire = codeTransitaire;
        return this;
    }

    public void setCodeTransitaire(String codeTransitaire) {
        this.codeTransitaire = codeTransitaire;
    }

    public String getCodeTransporteur() {
        return codeTransporteur;
    }

    public AchatArrivage codeTransporteur(String codeTransporteur) {
        this.codeTransporteur = codeTransporteur;
        return this;
    }

    public void setCodeTransporteur(String codeTransporteur) {
        this.codeTransporteur = codeTransporteur;
    }

    public LocalDate getDateArrivePort() {
        return dateArrivePort;
    }

    public AchatArrivage dateArrivePort(LocalDate dateArrivePort) {
        this.dateArrivePort = dateArrivePort;
        return this;
    }

    public void setDateArrivePort(LocalDate dateArrivePort) {
        this.dateArrivePort = dateArrivePort;
    }

    public String getDesignationCompagnieMaritime() {
        return designationCompagnieMaritime;
    }

    public AchatArrivage designationCompagnieMaritime(String designationCompagnieMaritime) {
        this.designationCompagnieMaritime = designationCompagnieMaritime;
        return this;
    }

    public void setDesignationCompagnieMaritime(String designationCompagnieMaritime) {
        this.designationCompagnieMaritime = designationCompagnieMaritime;
    }

    public String getDesignationOperateur() {
        return designationOperateur;
    }

    public AchatArrivage designationOperateur(String designationOperateur) {
        this.designationOperateur = designationOperateur;
        return this;
    }

    public void setDesignationOperateur(String designationOperateur) {
        this.designationOperateur = designationOperateur;
    }

    public String getDesignationTransitaire() {
        return designationTransitaire;
    }

    public AchatArrivage designationTransitaire(String designationTransitaire) {
        this.designationTransitaire = designationTransitaire;
        return this;
    }

    public void setDesignationTransitaire(String designationTransitaire) {
        this.designationTransitaire = designationTransitaire;
    }

    public String getDesignationTransporteur() {
        return designationTransporteur;
    }

    public AchatArrivage designationTransporteur(String designationTransporteur) {
        this.designationTransporteur = designationTransporteur;
        return this;
    }

    public void setDesignationTransporteur(String designationTransporteur) {
        this.designationTransporteur = designationTransporteur;
    }

    public String getFlagProduit() {
        return flagProduit;
    }

    public AchatArrivage flagProduit(String flagProduit) {
        this.flagProduit = flagProduit;
        return this;
    }

    public void setFlagProduit(String flagProduit) {
        this.flagProduit = flagProduit;
    }

    public Integer getFranchise() {
        return franchise;
    }

    public AchatArrivage franchise(Integer franchise) {
        this.franchise = franchise;
        return this;
    }

    public void setFranchise(Integer franchise) {
        this.franchise = franchise;
    }

    public BigDecimal getMontantFob() {
        return montantFob;
    }

    public AchatArrivage montantFob(BigDecimal montantFob) {
        this.montantFob = montantFob;
        return this;
    }

    public void setMontantFob(BigDecimal montantFob) {
        this.montantFob = montantFob;
    }

    public BigDecimal getMontantFret() {
        return montantFret;
    }

    public AchatArrivage montantFret(BigDecimal montantFret) {
        this.montantFret = montantFret;
        return this;
    }

    public void setMontantFret(BigDecimal montantFret) {
        this.montantFret = montantFret;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public AchatArrivage montantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
        return this;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Integer getNombreTc() {
        return nombreTc;
    }

    public AchatArrivage nombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
        return this;
    }

    public void setNombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
    }

    public LocalDate getDateRealisation() {
        return dateRealisation;
    }

    public AchatArrivage dateRealisation(LocalDate dateRealisation) {
        this.dateRealisation = dateRealisation;
        return this;
    }

    public void setDateRealisation(LocalDate dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public BigDecimal getPoid() {
        return poid;
    }

    public AchatArrivage poid(BigDecimal poid) {
        this.poid = poid;
        return this;
    }

    public void setPoid(BigDecimal poid) {
        this.poid = poid;
    }

    public Set<AchatFacture> getAchatFactures() {
        return achatFactures;
    }

    public AchatArrivage achatFactures(Set<AchatFacture> achatFactures) {
        this.achatFactures = achatFactures;
        return this;
    }

    public AchatArrivage addAchatFacture(AchatFacture achatFacture) {
        this.achatFactures.add(achatFacture);
        achatFacture.setAchatArrivage(this);
        return this;
    }

    public AchatArrivage removeAchatFacture(AchatFacture achatFacture) {
        this.achatFactures.remove(achatFacture);
        achatFacture.setAchatArrivage(null);
        return this;
    }

    public void setAchatFactures(Set<AchatFacture> achatFactures) {
        this.achatFactures = achatFactures;
    }

    public AchatDossier getAchatDossier() {
        return achatDossier;
    }

    public AchatArrivage achatDossier(AchatDossier achatDossier) {
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
        AchatArrivage achatArrivage = (AchatArrivage) o;
        if (achatArrivage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatArrivage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatArrivage{" +
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
            "}";
    }
}
