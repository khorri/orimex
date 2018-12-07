// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:24:27
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Retour.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            Bon, Depot, Produit, Utilisateur

@Entity
@Table(name="RETOUR")
public class Retour
    implements Serializable
{

    public Retour()
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

    public Timestamp getDateOperation()
    {
        return dateOperation;
    }

    public void setDateOperation(Timestamp dateOperation)
    {
        this.dateOperation = dateOperation;
    }

    public int getNombrePlateaux()
    {
        return nombrePlateaux;
    }

    public void setNombrePlateaux(int nombrePlateaux)
    {
        this.nombrePlateaux = nombrePlateaux;
    }

    public String getNumeroOperation()
    {
        return numeroOperation;
    }

    public void setNumeroOperation(String numeroOperation)
    {
        this.numeroOperation = numeroOperation;
    }

    public BigDecimal getSuperfecie()
    {
        return superfecie;
    }

    public void setSuperfecie(BigDecimal superfecie)
    {
        this.superfecie = superfecie;
    }

    public Bon getBon()
    {
        return bon;
    }

    public void setBon(Bon bon)
    {
        this.bon = bon;
    }

    public Depot getDepot()
    {
        return depot;
    }

    public void setDepot(Depot depot)
    {
        this.depot = depot;
    }

    public Produit getProduit()
    {
        return produit;
    }

    public void setProduit(Produit produit)
    {
        this.produit = produit;
    }

    public Utilisateur getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_OPERATION")
    private int idOperation;
    @Column(name="DATE_OPERATION")
    private Timestamp dateOperation;
    @Column(name="NOMBRE_PLATEAUX")
    private int nombrePlateaux;
    @Column(name="NUMERO_OPERATION")
    private String numeroOperation;
    @Column(name="SUPERFECIE")
    private BigDecimal superfecie;
    @ManyToOne
    @JoinColumn(name="ID_BON")
    private Bon bon;
    @ManyToOne
    @JoinColumn(name="ID_DEPOT")
    private Depot depot;
    @ManyToOne
    @JoinColumn(name="ID_PRODUIT")
    private Produit produit;
    @ManyToOne
    @JoinColumn(name="ID_UTILISATEUR")
    private Utilisateur utilisateur;
}
