// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:46:18
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatDossier.java

package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatStatutDossier, AchatTypePaiement, AchatBanque, AchatDevise

@Entity
@Table(name="ACHAT_DOSSIER")
public class AchatDossier
    implements Serializable
{

    public AchatDossier()
    {
    }

    public int getIdDossier()
    {
        return idDossier;
    }

    public void setIdDossier(int idDossier)
    {
        this.idDossier = idDossier;
    }

    public String getNumeroDossier()
    {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier)
    {
        this.numeroDossier = numeroDossier;
    }

    public Date getDateCreation()
    {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation)
    {
        this.dateCreation = dateCreation;
    }

    public int getDelaiPaiement()
    {
        return delaiPaiement;
    }

    public void setDelaiPaiement(int delaiPaiement)
    {
        this.delaiPaiement = delaiPaiement;
    }

    public Date getDateExpedition()
    {
        return dateExpedition;
    }

    public void setDateExpedition(Date dateExpedition)
    {
        this.dateExpedition = dateExpedition;
    }

    public Date getDateOuverture()
    {
        return dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture)
    {
        this.dateOuverture = dateOuverture;
    }

    public Date getDateValiditeEI()
    {
        return dateValiditeEI;
    }

    public void setDateValiditeEI(Date dateValiditeEI)
    {
        this.dateValiditeEI = dateValiditeEI;
    }

    public Date getDateLimiteExp()
    {
        return dateLimiteExp;
    }

    public void setDateLimiteExp(Date dateLimiteExp)
    {
        this.dateLimiteExp = dateLimiteExp;
    }

    public Date getDateValiditeLC()
    {
        return dateValiditeLC;
    }

    public void setDateValiditeLC(Date dateValiditeLC)
    {
        this.dateValiditeLC = dateValiditeLC;
    }

    public AchatStatutDossier getStatut()
    {
        return statut;
    }

    public void setStatut(AchatStatutDossier statut)
    {
        this.statut = statut;
    }

    public AchatTypePaiement getTypePaiement()
    {
        return typePaiement;
    }

    public void setTypePaiement(AchatTypePaiement typePaiement)
    {
        this.typePaiement = typePaiement;
    }

    public AchatBanque getBanque()
    {
        return banque;
    }

    public void setBanque(AchatBanque banque)
    {
        this.banque = banque;
    }

    public AchatDevise getDevise()
    {
        return devise;
    }

    public void setDevise(AchatDevise devise)
    {
        this.devise = devise;
    }

    public Set getAchatProformats()
    {
        return achatProformats;
    }

    public void setAchatProformats(Set achatProformats)
    {
        this.achatProformats = achatProformats;
    }

    public String getCodeFournisseur()
    {
        return codeFournisseur;
    }

    public void setCodeFournisseur(String codeFournisseur)
    {
        this.codeFournisseur = codeFournisseur;
    }

    public String getDesignationFournisseur()
    {
        return designationFournisseur;
    }

    public void setDesignationFournisseur(String designationFournisseur)
    {
        this.designationFournisseur = designationFournisseur;
    }

    public String getIncoterm()
    {
        return incoterm;
    }

    public void setIncoterm(String incoterm)
    {
        this.incoterm = incoterm;
    }

    public BigDecimal getMontnatTotal()
    {
        return montnatTotal;
    }

    public void setMontnatTotal(BigDecimal montnatTotal)
    {
        this.montnatTotal = montnatTotal;
    }

    public BigDecimal getMontnatFOB()
    {
        return montnatFOB;
    }

    public void setMontnatFOB(BigDecimal montnatFOB)
    {
        this.montnatFOB = montnatFOB;
    }

    public BigDecimal getMontnatFRET()
    {
        return montnatFRET;
    }

    public void setMontnatFRET(BigDecimal montnatFRET)
    {
        this.montnatFRET = montnatFRET;
    }

    public int getTotalTC()
    {
        return totalTC;
    }

    public void setTotalTC(int totalTC)
    {
        this.totalTC = totalTC;
    }

    public String getReference()
    {
        return reference;
    }

    public void setReference(String reference)
    {
        this.reference = reference;
    }

    public String getNumeroEI()
    {
        return numeroEI;
    }

    public void setNumeroEI(String numeroEI)
    {
        this.numeroEI = numeroEI;
    }

    public String getNumeroEIComp()
    {
        return numeroEIComp;
    }

    public void setNumeroEIComp(String numeroEIComp)
    {
        this.numeroEIComp = numeroEIComp;
    }

    public String getDesignationBanque()
    {
        return designationBanque;
    }

    public void setDesignationBanque(String designationBanque)
    {
        this.designationBanque = designationBanque;
    }

    public int getDelaiValiditeLC()
    {
        return delaiValiditeLC;
    }

    public void setDelaiValiditeLC(int delaiValiditeLC)
    {
        this.delaiValiditeLC = delaiValiditeLC;
    }

    public Set getAchatLigneProformaCopys()
    {
        return achatLigneProformaCopys;
    }

    public void setAchatLigneProformaCopys(Set achatLigneProformaCopys)
    {
        this.achatLigneProformaCopys = achatLigneProformaCopys;
    }

    public int getPaiementAvue()
    {
        return paiementAvue;
    }

    public void setPaiementAvue(int paiementAvue)
    {
        this.paiementAvue = paiementAvue;
    }

    public Set getAchatArrivages()
    {
        return achatArrivages;
    }

    public void setAchatArrivages(Set achatArrivages)
    {
        this.achatArrivages = achatArrivages;
    }

    public Set getAchatPrevisionArrivages()
    {
        return achatPrevisionArrivages;
    }

    public void setAchatPrevisionArrivages(Set achatPrevisionArrivages)
    {
        this.achatPrevisionArrivages = achatPrevisionArrivages;
    }

    public int getTolerance()
    {
        return tolerance;
    }

    public void setTolerance(int tolerance)
    {
        this.tolerance = tolerance;
    }

    public BigDecimal getEncours()
    {
        return encours;
    }

    public void setEncours(BigDecimal encours)
    {
        this.encours = encours;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_DOSSIER")
    @GeneratedValue
    private int idDossier;
    @Column(name="NUMERO_DOSSIER")
    private String numeroDossier;
    @Column(name="CODE_FOURNISSEUR")
    private String codeFournisseur;
    @Column(name="DESIGNATION_FOURNISSEUR")
    private String designationFournisseur;
    @Column(name="INCOTERM")
    private String incoterm;
    @Column(name="REFERENCE")
    private String reference;
    @Column(name="TOLERANCE")
    private int tolerance;
    @Column(name="NUMERO_EI")
    private String numeroEI;
    @Column(name="NUMERO_EI_COMP")
    private String numeroEIComp;
    @Column(name="DATE_CREATION")
    private Date dateCreation;
    @Column(name="DELAI_PAIEMENT")
    private int delaiPaiement;
    @Column(name="DELAI_VALIDITE_LC")
    private int delaiValiditeLC;
    @Column(name="DATE_EXPEDITION")
    private Date dateExpedition;
    @Column(name="DATE_OUVERTURE")
    private Date dateOuverture;
    @Column(name="DATE_VALIDITE_EI")
    private Date dateValiditeEI;
    @Column(name="DATE_LIMITE_EXP")
    private Date dateLimiteExp;
    @Column(name="DATE_VALIDITE_LC")
    private Date dateValiditeLC;
    @Column(name="MONTANT_TOTAL")
    private BigDecimal montnatTotal;
    @Column(name="MONTANT_FOB")
    private BigDecimal montnatFOB;
    @Column(name="MONTANT_FRET")
    private BigDecimal montnatFRET;
    @Column(name="TOTAL_TC")
    private int totalTC;
    @Column(name="BANQUE")
    private String designationBanque;
    @ManyToOne
    @JoinColumn(name="FK_ID_STATUT")
    private AchatStatutDossier statut;
    @Column(name="PAIEMENT_AVUE")
    private int paiementAvue;
    @Column(name="ENCOURS")
    private BigDecimal encours;
    @ManyToOne
    @JoinColumn(name="FK_ID_TYPE_PAYEMENT")
    private AchatTypePaiement typePaiement;
    @ManyToOne
    @JoinColumn(name="FK_ID_BANQUE")
    private AchatBanque banque;
    @ManyToOne
    @JoinColumn(name="FK_ID_DEVISE")
    private AchatDevise devise;
    @OneToMany(mappedBy="achatDossier")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE})
    private Set achatProformats;
    @OneToMany(mappedBy="achatDossier")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE})
    private Set achatLigneProformaCopys;
    @OneToMany(mappedBy="achatDossier")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE})
    private Set achatArrivages;
    @OneToMany(mappedBy="achatDossier")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE})
    private Set achatPrevisionArrivages;
}
