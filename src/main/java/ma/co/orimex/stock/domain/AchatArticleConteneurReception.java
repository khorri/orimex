// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:45:14
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatArticleConteneurReception.java

package ma.co.orimex.stock.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatConteneurReception, Produit

@Entity
@Table(name="ACHAT_ARTICLE_CONTENEUR_RECEPTION")
public class AchatArticleConteneurReception
    implements Serializable
{

    public AchatArticleConteneurReception()
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

    public AchatConteneurReception getAchatConteneurReception()
    {
        return achatConteneurReception;
    }

    public void setAchatConteneurReception(AchatConteneurReception achatConteneurReception)
    {
        this.achatConteneurReception = achatConteneurReception;
    }

    public Produit getProduit()
    {
        return produit;
    }

    public void setProduit(Produit produit)
    {
        this.produit = produit;
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
    private AchatConteneurReception achatConteneurReception;
    @ManyToOne
    @JoinColumn(name="FK_ID_ARTICLE")
    private Produit produit;
}
