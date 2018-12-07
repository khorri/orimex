// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:25:00
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   StockConteneurReception.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatConteneurArrivage, StockReception

@Entity
@Table(name="STOCK_CONTENEUR_RECEPTION")
public class StockConteneurReception
    implements Serializable
{

    public StockConteneurReception()
    {
    }

    public int getIdConteneurReception()
    {
        return idConteneurReception;
    }

    public void setIdConteneurReception(int idConteneurReception)
    {
        this.idConteneurReception = idConteneurReception;
    }

    public String getNumeroConteneur()
    {
        return numeroConteneur;
    }

    public void setNumeroConteneur(String numeroConteneur)
    {
        this.numeroConteneur = numeroConteneur;
    }

    public int getNumeroSequence()
    {
        return numeroSequence;
    }

    public void setNumeroSequence(int numeroSequence)
    {
        this.numeroSequence = numeroSequence;
    }

    public AchatConteneurArrivage getAchatConteneurArrivage()
    {
        return achatConteneurArrivage;
    }

    public void setAchatConteneurArrivage(AchatConteneurArrivage achatConteneurArrivage)
    {
        this.achatConteneurArrivage = achatConteneurArrivage;
    }

    public StockReception getStockReception()
    {
        return stockReception;
    }

    public void setStockReception(StockReception stockReception)
    {
        this.stockReception = stockReception;
    }

    public Set getStockArticleConteneurReception()
    {
        return stockArticleConteneurReception;
    }

    public void setStockArticleConteneurReception(Set stockArticleConteneurReception)
    {
        this.stockArticleConteneurReception = stockArticleConteneurReception;
    }

    public int getIsNonConfrome()
    {
        return isNonConfrome;
    }

    public void setIsNonConfrome(int isNonConfrome)
    {
        this.isNonConfrome = isNonConfrome;
    }

    public Date getDateRetour()
    {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour)
    {
        this.dateRetour = dateRetour;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_CONTENEUR_RECEPTION")
    @GeneratedValue
    private int idConteneurReception;
    @Column(name="NUMERO_CONTENEUR")
    private String numeroConteneur;
    @Column(name="NUMERO_SEQUENCE")
    private int numeroSequence;
    @OneToOne
    @JoinColumn(name="FK_ID_CONTENEUR_ARRIVAGE")
    private AchatConteneurArrivage achatConteneurArrivage;
    @OneToMany(mappedBy="stockConteneurReception")
    private Set stockArticleConteneurReception;
    @OneToOne
    @JoinColumn(name="FK_ID_STOCK_RECEPTION")
    private StockReception stockReception;
    @Column(name="NON_CONFORME")
    private int isNonConfrome;
    @Column(name="DATE_RETOUR")
    private Date dateRetour;
}
