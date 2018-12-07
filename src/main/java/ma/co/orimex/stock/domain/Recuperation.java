// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:24:11
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Recuperation.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            Depot, Produit, Utilisateur

@Entity
@Table(name="RECUPERATION")
public class Recuperation
    implements Serializable
{

    public Recuperation()
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

    public int getNombrePanneaux()
    {
        return nombrePanneaux;
    }

    public void setNombrePanneaux(int nombrePanneaux)
    {
        this.nombrePanneaux = nombrePanneaux;
    }

    public String getNumeroOperation()
    {
        return numeroOperation;
    }

    public void setNumeroOperation(String numeroOperation)
    {
        this.numeroOperation = numeroOperation;
    }

    public BigDecimal getSuperficie()
    {
        return superficie;
    }

    public void setSuperficie(BigDecimal superficie)
    {
        this.superficie = superficie;
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
    @Column(name="NOMBRE_PANNEAUX")
    private int nombrePanneaux;
    @Column(name="NUMERO_OPERATION")
    private String numeroOperation;
    @Column(name="SUPERFICIE")
    private BigDecimal superficie;
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
