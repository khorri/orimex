// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:45:58
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatConteneurArrivage.java

package ma.co.orimex.stock.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatFacture, StockConteneurReception

@Entity
@Table(name="ACHAT_CONTENEUR_ARRIVAGE")
public class AchatConteneurArrivage
    implements Serializable
{

    public AchatConteneurArrivage()
    {
    }

    public int getIdConteneurArrivage()
    {
        return idConteneurArrivage;
    }

    public void setIdConteneurArrivage(int idConteneurArrivage)
    {
        this.idConteneurArrivage = idConteneurArrivage;
    }

    public BigDecimal getMontant()
    {
        return montant;
    }

    public void setMontant(BigDecimal montant)
    {
        this.montant = montant;
    }

    public String getNumeroConteneurs()
    {
        return numeroConteneurs;
    }

    public void setNumeroConteneurs(String numeroConteneurs)
    {
        this.numeroConteneurs = numeroConteneurs;
    }

    public int getNumeroSequence()
    {
        return numeroSequence;
    }

    public void setNumeroSequence(int numeroSequence)
    {
        this.numeroSequence = numeroSequence;
    }

    public Set getAchatArticleConteneurArrivages()
    {
        return achatArticleConteneurArrivages;
    }

    public void setAchatArticleConteneurArrivages(Set achatArticleConteneurArrivages)
    {
        this.achatArticleConteneurArrivages = achatArticleConteneurArrivages;
    }

    public AchatFacture getAchatFacture()
    {
        return achatFacture;
    }

    public void setAchatFacture(AchatFacture achatFacture)
    {
        this.achatFacture = achatFacture;
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

    public StockConteneurReception getStockConteneurReception()
    {
        return stockConteneurReception;
    }

    public void setStockConteneurReception(StockConteneurReception stockConteneurReception)
    {
        this.stockConteneurReception = stockConteneurReception;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_CONTENEUR_ARRIVAGE")
    @GeneratedValue
    private int idConteneurArrivage;
    @Column(name="MONTANT")
    private BigDecimal montant;
    @Column(name="NUMERO_CONTENEURS")
    private String numeroConteneurs;
    @Column(name="NUMERO_SEQUENCE")
    private int numeroSequence;
    @Column(name="POIDS")
    private BigDecimal poids;
    @Column(name="QUANTITE")
    private BigDecimal quantite;
    @ManyToOne
    @JoinColumn(name="FK_ID_FACTURE")
    private AchatFacture achatFacture;
    @OneToMany(mappedBy="achatConteneurArrivage")
    private Set achatArticleConteneurArrivages;
    @OneToOne(mappedBy="achatConteneurArrivage")
    private StockConteneurReception stockConteneurReception;
}
