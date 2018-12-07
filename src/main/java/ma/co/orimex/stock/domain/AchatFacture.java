// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:46:27
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatFacture.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatArrivage

@Entity
@Table(name="ACHAT_FACTURE")
public class AchatFacture
    implements Serializable
{

    public AchatFacture()
    {
    }

    public int getEtat()
    {
        return etat;
    }

    public void setEtat(int etat)
    {
        this.etat = etat;
    }

    public int getBanqueReglement()
    {
        return banqueReglement;
    }

    public void setBanqueReglement(int banqueReglement)
    {
        this.banqueReglement = banqueReglement;
    }

    public Date getDateValeur()
    {
        return dateValeur;
    }

    public void setDateValeur(Date dateValeur)
    {
        this.dateValeur = dateValeur;
    }

    public BigDecimal getCours()
    {
        return cours;
    }

    public void setCours(BigDecimal cours)
    {
        this.cours = cours;
    }

    public BigDecimal getMontantDH()
    {
        return montantDH;
    }

    public void setMontantDH(BigDecimal montantDH)
    {
        this.montantDH = montantDH;
    }

    public Date getEchecanceFinancement()
    {
        return echecanceFinancement;
    }

    public void setEchecanceFinancement(Date echecanceFinancement)
    {
        this.echecanceFinancement = echecanceFinancement;
    }

    public BigDecimal getInteret1()
    {
        return interet1;
    }

    public void setInteret1(BigDecimal interet1)
    {
        this.interet1 = interet1;
    }

    public Date getEcheanceRefinancement()
    {
        return echeanceRefinancement;
    }

    public void setEcheanceRefinancement(Date echeanceRefinancement)
    {
        this.echeanceRefinancement = echeanceRefinancement;
    }

    public BigDecimal getInteret2()
    {
        return interet2;
    }

    public void setInteret2(BigDecimal interet2)
    {
        this.interet2 = interet2;
    }

    public Date getDateFacture()
    {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture)
    {
        this.dateFacture = dateFacture;
    }

    public String getNumeroFacture()
    {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture)
    {
        this.numeroFacture = numeroFacture;
    }

    public int getIdFacture()
    {
        return idFacture;
    }

    public void setIdFacture(int idFacture)
    {
        this.idFacture = idFacture;
    }

    public Set getAchatConteneurArrivages()
    {
        return achatConteneurArrivages;
    }

    public void setAchatConteneurArrivages(Set achatConteneurArrivages)
    {
        this.achatConteneurArrivages = achatConteneurArrivages;
    }

    public AchatArrivage getAchatArrivage()
    {
        return achatArrivage;
    }

    public void setAchatArrivage(AchatArrivage achatArrivage)
    {
        this.achatArrivage = achatArrivage;
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

    public int getNombreTc()
    {
        return nombreTc;
    }

    public void setNombreTc(int nombreTc)
    {
        this.nombreTc = nombreTc;
    }

    public BigDecimal getMontantTotal()
    {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal)
    {
        this.montantTotal = montantTotal;
    }

    public BigDecimal getPoids()
    {
        return poids;
    }

    public void setPoids(BigDecimal poids)
    {
        this.poids = poids;
    }

    public BigDecimal getQuantite()
    {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite)
    {
        this.quantite = quantite;
    }

    public BigDecimal getAjustement()
    {
        return ajustement;
    }

    public void setAjustement(BigDecimal ajustement)
    {
        this.ajustement = ajustement;
    }

    public String getNumeroPiece()
    {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece)
    {
        this.numeroPiece = numeroPiece;
    }

    public Date getDateBl()
    {
        return dateBl;
    }

    public void setDateBl(Date dateBl)
    {
        this.dateBl = dateBl;
    }

    public Date getDateEcheance()
    {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance)
    {
        this.dateEcheance = dateEcheance;
    }

    public String getNumeroBl()
    {
        return numeroBl;
    }

    public void setNumeroBl(String numeroBl)
    {
        this.numeroBl = numeroBl;
    }

    public int getInteret1Regle()
    {
        return interet1Regle;
    }

    public void setInteret1Regle(int interet1Regle)
    {
        this.interet1Regle = interet1Regle;
    }

    public Date getDateReglement()
    {
        return dateReglement;
    }

    public void setDateReglement(Date dateReglement)
    {
        this.dateReglement = dateReglement;
    }

    public Date getDerniereEcheance()
    {
        return derniereEcheance;
    }

    public void setDerniereEcheance(Date derniereEcheance)
    {
        this.derniereEcheance = derniereEcheance;
    }

    public int getTransmise()
    {
        return transmise;
    }

    public void setTransmise(int transmise)
    {
        this.transmise = transmise;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_FACTURE")
    @GeneratedValue
    private int idFacture;
    @Column(name="NUMERO_FACTURE")
    private String numeroFacture;
    @Column(name="DATE_FACTURE")
    private Date dateFacture;
    @ManyToOne
    @JoinColumn(name="FK_ID_ARRIVAGE")
    private AchatArrivage achatArrivage;
    @Column(name="MONTANT_FOB")
    private BigDecimal montantFob;
    @Column(name="MONTANT_FRET")
    private BigDecimal montantFret;
    @Column(name="MONTANT_TOTAL")
    private BigDecimal montantTotal;
    @Column(name="NOMBRE_TC")
    private int nombreTc;
    @Column(name="POIDS")
    private BigDecimal poids;
    @Column(name="QUANTITE")
    private BigDecimal quantite;
    @Column(name="AJUSTEMENT")
    private BigDecimal ajustement;
    @Column(name="NUMERO_PIECE")
    private String numeroPiece;
    @Column(name="DATE_BL")
    private Date dateBl;
    @Column(name="NUMERO_BL")
    private String numeroBl;
    @Column(name="DATE_ECHEANCE")
    private Date dateEcheance;
    @Column(name="ETAT")
    private int etat;
    @Column(name="BANQUE_REGLEMENT")
    private int banqueReglement;
    @Column(name="DATE_VALEUR")
    private Date dateValeur;
    @Column(name="COURS")
    private BigDecimal cours;
    @Column(name="MONTANT_DH")
    private BigDecimal montantDH;
    @Column(name="ECHEANCE_FINANCEMENT")
    private Date echecanceFinancement;
    @Column(name="INTERET1")
    private BigDecimal interet1;
    @Column(name="DATE_REGLEMENT")
    private Date dateReglement;
    @Column(name="LAST_ECHEANCE")
    private Date derniereEcheance;
    @Column(name="TRANSMISE")
    private int transmise;
    @Column(name="ECHEANCE_REFINANCEMENT")
    private Date echeanceRefinancement;
    @Column(name="INTERET2")
    private BigDecimal interet2;
    @Column(name="INTERET1_REGLE")
    private int interet1Regle;
    @OneToMany(mappedBy="achatFacture")
    private Set achatConteneurArrivages;
}
