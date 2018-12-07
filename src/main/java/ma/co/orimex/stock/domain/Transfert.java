// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:25:34
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Transfert.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            Bon, Caisse, Camion, Conteneur,
//            Depot, Utilisateur

@Entity
@Table(name="TRANSFERT")
public class Transfert
    implements Serializable
{

    public Transfert()
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

    public Bon getBon()
    {
        return bon;
    }

    public void setBon(Bon bon)
    {
        this.bon = bon;
    }

    public Caisse getCaisse()
    {
        return caisse;
    }

    public void setCaisse(Caisse caisse)
    {
        this.caisse = caisse;
    }

    public Camion getCamion()
    {
        return camion;
    }

    public void setCamion(Camion camion)
    {
        this.camion = camion;
    }

    public Conteneur getConteneur()
    {
        return conteneur;
    }

    public void setConteneur(Conteneur conteneur)
    {
        this.conteneur = conteneur;
    }

    public Depot getDepotDestinataire()
    {
        return depotDestinataire;
    }

    public void setDepotDestinataire(Depot depotDestinataire)
    {
        this.depotDestinataire = depotDestinataire;
    }

    public Depot getDepotExpediteur()
    {
        return depotExpediteur;
    }

    public void setDepotExpediteur(Depot depotExpediteur)
    {
        this.depotExpediteur = depotExpediteur;
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
    @ManyToOne
    @JoinColumn(name="ID_BON")
    private Bon bon;
    @ManyToOne
    @JoinColumn(name="ID_CAISSE")
    private Caisse caisse;
    @ManyToOne
    @JoinColumn(name="ID_CAMION")
    private Camion camion;
    @ManyToOne
    @JoinColumn(name="NUMERO_CONTENEUR")
    private Conteneur conteneur;
    @ManyToOne
    @JoinColumn(name="ID_DEPOT_DEST")
    private Depot depotDestinataire;
    @ManyToOne
    @JoinColumn(name="ID_DEPOT_EXP")
    private Depot depotExpediteur;
    @ManyToOne
    @JoinColumn(name="ID_UTILISATEUR")
    private Utilisateur utilisateur;
}
