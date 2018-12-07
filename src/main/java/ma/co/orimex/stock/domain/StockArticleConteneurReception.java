// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:24:47
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   StockArticleConteneurReception.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            StockConteneurReception, Produit, AchatArticleConteneurArrivage

@Entity
@Table(name="STOCK_ARTICLE_CONTENEUR_RECEPTION")
public class StockArticleConteneurReception
    implements Serializable
{

    public StockArticleConteneurReception()
    {
    }

    public int getIdArticleConteneurReception()
    {
        return idArticleConteneurReception;
    }

    public void setIdArticleConteneurReception(int idArticleConteneurReception)
    {
        this.idArticleConteneurReception = idArticleConteneurReception;
    }

    public BigDecimal getDimension()
    {
        return dimension;
    }

    public void setDimension(BigDecimal dimension)
    {
        this.dimension = dimension;
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

    public Produit getProduit()
    {
        return produit;
    }

    public void setProduit(Produit produit)
    {
        this.produit = produit;
    }

    public AchatArticleConteneurArrivage getAchatArticleConteneurArrivage()
    {
        return achatArticleConteneurArrivage;
    }

    public void setAchatArticleConteneurArrivage(AchatArticleConteneurArrivage achatArticleConteneurArrivage)
    {
        this.achatArticleConteneurArrivage = achatArticleConteneurArrivage;
    }

    public StockConteneurReception getStockConteneurReception()
    {
        return stockConteneurReception;
    }

    public void setStockConteneurReception(StockConteneurReception stockConteneurReception)
    {
        this.stockConteneurReception = stockConteneurReception;
    }

    public int getIsNonConfrome()
    {
        return isNonConfrome;
    }

    public void setIsNonConfrome(int isNonConfrome)
    {
        this.isNonConfrome = isNonConfrome;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_ARTICLE_CONTENEUR_RECEPTION")
    @GeneratedValue
    private int idArticleConteneurReception;
    @Column(name="DIMENSION")
    private BigDecimal dimension;
    @Column(name="NOMBRE_CAISSESTC")
    private int nombreCaissestc;
    @Column(name="NOMBRE_FEUILLECAISSE")
    private int nombreFeuillecaisse;
    @ManyToOne
    @JoinColumn(name="FK_ID_CONTENEUR_RECEPTION")
    private StockConteneurReception stockConteneurReception;
    @ManyToOne
    @JoinColumn(name="FK_ID_ARTICLE")
    private Produit produit;
    @OneToOne
    @JoinColumn(name="KF_ID_ARTICLE_CONTENEUR_ARRIVAGE")
    private AchatArticleConteneurArrivage achatArticleConteneurArrivage;
    @Column(name="NON_CONFORME")
    private int isNonConfrome;
}
