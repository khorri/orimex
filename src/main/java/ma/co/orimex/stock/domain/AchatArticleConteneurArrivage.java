// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:45:09
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatArticleConteneurArrivage.java

package ma.co.orimex.stock.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatConteneurArrivage, Produit, StockArticleConteneurReception

@Entity
@Table(name="ACHAT_ARTICLE_CONTENEUR_ARRIVAGE")
public class AchatArticleConteneurArrivage
    implements Serializable
{

    public AchatArticleConteneurArrivage()
    {
    }

    public int getIdArticleConteneurArrivage()
    {
        return idArticleConteneurArrivage;
    }

    public void setIdArticleConteneurArrivage(int idArticleConteneurArrivage)
    {
        this.idArticleConteneurArrivage = idArticleConteneurArrivage;
    }

    public BigDecimal getDimension()
    {
        return dimension;
    }

    public void setDimension(BigDecimal dimension)
    {
        this.dimension = dimension;
    }

    public BigDecimal getMontant()
    {
        return montant;
    }

    public void setMontant(BigDecimal montant)
    {
        this.montant = montant;
    }

    public int getNombreCaissestc()
    {
        return nombreCaissestc;
    }

    public void setNombreCaissestc(int nombreCaissestc)
    {
        this.nombreCaissestc = nombreCaissestc;
    }

    public int getNombreFeuillecaisse()
    {
        return nombreFeuillecaisse;
    }

    public void setNombreFeuillecaisse(int nombreFeuillecaisse)
    {
        this.nombreFeuillecaisse = nombreFeuillecaisse;
    }

    public BigDecimal getPrixUnitaire()
    {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire)
    {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getQuantite()
    {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite)
    {
        this.quantite = quantite;
    }

    public AchatConteneurArrivage getAchatConteneurArrivage()
    {
        return achatConteneurArrivage;
    }

    public void setAchatConteneurArrivage(AchatConteneurArrivage achatConteneurArrivage)
    {
        this.achatConteneurArrivage = achatConteneurArrivage;
    }

    public Produit getProduit()
    {
        return produit;
    }

    public void setProduit(Produit produit)
    {
        this.produit = produit;
    }

    public BigDecimal getPoids()
    {
        return poids;
    }

    public void setPoids(BigDecimal poids)
    {
        this.poids = poids;
    }

    public StockArticleConteneurReception getStockArticleConteneurReception()
    {
        return stockArticleConteneurReception;
    }

    public void setStockArticleConteneurReception(StockArticleConteneurReception stockArticleConteneurReception)
    {
        this.stockArticleConteneurReception = stockArticleConteneurReception;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_ARTICLE_CONTENEUR_ARRIVAGE")
    @GeneratedValue
    private int idArticleConteneurArrivage;
    @Column(name="DIMENSION")
    private BigDecimal dimension;
    @Column(name="MONTANT")
    private BigDecimal montant;
    @Column(name="NOMBRE_CAISSESTC")
    private int nombreCaissestc;
    @Column(name="NOMBRE_FEUILLECAISSE")
    private int nombreFeuillecaisse;
    @Column(name="PRIX_UNITAIRE")
    private BigDecimal prixUnitaire;
    @Column(name="QUANTITE")
    private BigDecimal quantite;
    @Column(name="POIDS")
    private BigDecimal poids;
    @ManyToOne
    @JoinColumn(name="FK_ID_CONTENEUR_ARRIVAGE")
    private AchatConteneurArrivage achatConteneurArrivage;
    @ManyToOne
    @JoinColumn(name="FK_ID_ARTICLE")
    private Produit produit;
    @OneToOne(mappedBy="achatArticleConteneurArrivage")
    private StockArticleConteneurReception stockArticleConteneurReception;
}
