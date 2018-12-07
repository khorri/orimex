// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:45:04
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatArrivage.java

package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatDossier

@Entity
@Table(name="ACHAT_ARRIVAGE")
public class AchatArrivage
    implements Serializable
{

    public AchatArrivage()
    {
    }

    public int getIdArrivage()
    {
        return idArrivage;
    }

    public void setIdArrivage(int idArrivage)
    {
        this.idArrivage = idArrivage;
    }

    public String getCodeCompagnieMaritime()
    {
        return codeCompagnieMaritime;
    }

    public void setCodeCompagnieMaritime(String codeCompagnieMaritime)
    {
        this.codeCompagnieMaritime = codeCompagnieMaritime;
    }

    public String getCodeOperateur()
    {
        return codeOperateur;
    }

    public void setCodeOperateur(String codeOperateur)
    {
        this.codeOperateur = codeOperateur;
    }

    public String getCodeTransitaire()
    {
        return codeTransitaire;
    }

    public void setCodeTransitaire(String codeTransitaire)
    {
        this.codeTransitaire = codeTransitaire;
    }

    public String getCodeTransporteur()
    {
        return codeTransporteur;
    }

    public void setCodeTransporteur(String codeTransporteur)
    {
        this.codeTransporteur = codeTransporteur;
    }

    public Date getDateArrivePort()
    {
        return dateArrivePort;
    }

    public void setDateArrivePort(Date dateArrivePort)
    {
        this.dateArrivePort = dateArrivePort;
    }

    public String getDesignationCompagnieMaritime()
    {
        return designationCompagnieMaritime;
    }

    public void setDesignationCompagnieMaritime(String designationCompagnieMaritime)
    {
        this.designationCompagnieMaritime = designationCompagnieMaritime;
    }

    public String getDesignationOperateur()
    {
        return designationOperateur;
    }

    public void setDesignationOperateur(String designationOperateur)
    {
        this.designationOperateur = designationOperateur;
    }

    public String getDesignationTransitaire()
    {
        return designationTransitaire;
    }

    public void setDesignationTransitaire(String designationTransitaire)
    {
        this.designationTransitaire = designationTransitaire;
    }

    public String getDesignationTransporteur()
    {
        return designationTransporteur;
    }

    public void setDesignationTransporteur(String designationTransporteur)
    {
        this.designationTransporteur = designationTransporteur;
    }

    public int getFranchise()
    {
        return franchise;
    }

    public void setFranchise(int franchise)
    {
        this.franchise = franchise;
    }

    public BigDecimal getMontantFob()
    {
        return montantFob;
    }

    public void setMontantFob(BigDecimal montantFob)
    {
        this.montantFob = montantFob;
    }

    public BigDecimal getMontantFret()
    {
        return montantFret;
    }

    public void setMontantFret(BigDecimal montantFret)
    {
        this.montantFret = montantFret;
    }

    public BigDecimal getMontantTotal()
    {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal)
    {
        this.montantTotal = montantTotal;
    }

    public int getNombreTc()
    {
        return nombreTc;
    }

    public void setNombreTc(int nombreTc)
    {
        this.nombreTc = nombreTc;
    }

    public String getNumeroDossierArrivage()
    {
        return numeroDossierArrivage;
    }

    public void setNumeroDossierArrivage(String numeroDossierArrivage)
    {
        this.numeroDossierArrivage = numeroDossierArrivage;
    }

    public AchatDossier getAchatDossier()
    {
        return achatDossier;
    }

    public void setAchatDossier(AchatDossier achatDossier)
    {
        this.achatDossier = achatDossier;
    }

    public Date getDateRealisation()
    {
        return dateRealisation;
    }

    public void setDateRealisation(Date dateRealisation)
    {
        this.dateRealisation = dateRealisation;
    }

    public BigDecimal getPoids()
    {
        return poids;
    }

    public void setPoids(BigDecimal poids)
    {
        this.poids = poids;
    }

    public Set getAchatFactures()
    {
        return achatFactures;
    }

    public void setAchatFactures(Set achatFactures)
    {
        this.achatFactures = achatFactures;
    }

    public String getFlagProduit()
    {
        return flagProduit;
    }

    public void setFlagProduit(String flagProduit)
    {
        this.flagProduit = flagProduit;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_ARRIVAGE")
    @GeneratedValue
    private int idArrivage;
    @Column(name="NUMERO_DOSSIER_ARRIVAGE")
    private String numeroDossierArrivage;
    @Column(name="CODE_COMPAGNIE_MARITIME")
    private String codeCompagnieMaritime;
    @Column(name="CODE_OPERATEUR")
    private String codeOperateur;
    @Column(name="CODE_TRANSITAIRE")
    private String codeTransitaire;
    @Column(name="CODE_TRANSPORTEUR")
    private String codeTransporteur;
    @Column(name="DATE_ARRIVE_PORT")
    private Date dateArrivePort;
    @Column(name="DESIGNATION_COMPAGNIE_MARITIME")
    private String designationCompagnieMaritime;
    @Column(name="DESIGNATION_OPERATEUR")
    private String designationOperateur;
    @Column(name="DESIGNATION_TRANSITAIRE")
    private String designationTransitaire;
    @Column(name="DESIGNATION_TRANSPORTEUR")
    private String designationTransporteur;
    @Column(name="FLAG_PRODUIT")
    private String flagProduit;
    @Column(name="FRANCHISE")
    private int franchise;
    @Column(name="MONTANT_FOB")
    private BigDecimal montantFob;
    @Column(name="MONTANT_FRET")
    private BigDecimal montantFret;
    @Column(name="MONTANT_TOTAL")
    private BigDecimal montantTotal;
    @Column(name="NOMBRE_TC")
    private int nombreTc;
    @Column(name="DATE_REALISATION")
    private Date dateRealisation;
    @ManyToOne
    @JoinColumn(name="FK_ID_DOSSIER_ACHAT")
    private AchatDossier achatDossier;
    @OneToMany(mappedBy="achatArrivage")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE})
    private Set achatFactures;
    @Column(name="POIDS")
    private BigDecimal poids;
}
