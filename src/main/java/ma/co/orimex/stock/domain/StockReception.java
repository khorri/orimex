// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:25:09
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   StockReception.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            Depot, Utilisateur

@Entity
@Table(name="STOCK_RECEPTION")
public class StockReception
    implements Serializable
{

    public StockReception()
    {
    }

    public int getIdOperation()
    {
        return idOperation;
    }

    public void setIdOperation(int idOperation)
    {
        this.idOperation = idOperation;
    }

    public Date getDateReception()
    {
        return dateReception;
    }

    public void setDateReception(Date dateReception)
    {
        this.dateReception = dateReception;
    }

    public String getNumeroOperation()
    {
        return numeroOperation;
    }

    public void setNumeroOperation(String numeroOperation)
    {
        this.numeroOperation = numeroOperation;
    }

    public String getNumeroBonEntree()
    {
        return numeroBonEntree;
    }

    public void setNumeroBonEntree(String numeroBonEntree)
    {
        this.numeroBonEntree = numeroBonEntree;
    }

    public Depot getDepot()
    {
        return depot;
    }

    public void setDepot(Depot depot)
    {
        this.depot = depot;
    }

    public Utilisateur getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    public String getNumeroConstatNonConformite()
    {
        return numeroConstatNonConformite;
    }

    public void setNumeroConstatNonConformite(String numeroConstatNonConformite)
    {
        this.numeroConstatNonConformite = numeroConstatNonConformite;
    }

    public int getIsValide()
    {
        return isValide;
    }

    public void setIsValide(int isValide)
    {
        this.isValide = isValide;
    }

    public Set getStockConteneurReceptions()
    {
        return stockConteneurReceptions;
    }

    public void setStockConteneurReceptions(Set stockConteneurReceptions)
    {
        this.stockConteneurReceptions = stockConteneurReceptions;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_OPERATION")
    @GeneratedValue
    private int idOperation;
    @Column(name="DATE_RECEPTION")
    private Date dateReception;
    @Column(name="NUMERO_OPERATION")
    private String numeroOperation;
    @Column(name="NUMERO_BON_ENTREE")
    private String numeroBonEntree;
    @Column(name="NUMERO_CONSTAT_NON_CONFORMITE")
    private String numeroConstatNonConformite;
    @OneToMany(mappedBy="stockReception")
    private Set stockConteneurReceptions;
    @Column(name="VALIDE")
    private int isValide;
    @ManyToOne
    @JoinColumn(name="FK_ID_DEPOT")
    private Depot depot;
    @ManyToOne
    @JoinColumn(name="FK_ID_UTILISATEUR")
    private Utilisateur utilisateur;
}
