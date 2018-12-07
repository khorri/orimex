// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:21:44
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Depot.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

// Referenced classes of package ma.co.orimex.stock.entite:
//            Ville

@Entity
@Table(name="DEPOT")
public class Depot
    implements Serializable
{

    public Depot()
    {
    }

    public int getIdDepot()
    {
        return idDepot;
    }

    public void setIdDepot(int idDepot)
    {
        this.idDepot = idDepot;
    }

    public String getReferenceDepot()
    {
        return referenceDepot;
    }

    public void setReferenceDepot(String referenceDepot)
    {
        this.referenceDepot = referenceDepot;
    }

    public Ville getVille()
    {
        return ville;
    }

    public void setVille(Ville ville)
    {
        this.ville = ville;
    }

    public Set getReceptions()
    {
        return receptions;
    }

    public void setReceptions(Set receptions)
    {
        this.receptions = receptions;
    }

    public Set getRecuperations()
    {
        return recuperations;
    }

    public void setRecuperations(Set recuperations)
    {
        this.recuperations = recuperations;
    }

    public Set getRetours()
    {
        return retours;
    }

    public void setRetours(Set retours)
    {
        this.retours = retours;
    }

    public Set getTransfertsDestinatiaire()
    {
        return transfertsDestinatiaire;
    }

    public void setTransfertsDestinatiaire(Set transfertsDestinatiaire)
    {
        this.transfertsDestinatiaire = transfertsDestinatiaire;
    }

    public Set getTransfertsExpediteur()
    {
        return transfertsExpediteur;
    }

    public void setTransfertsExpediteur(Set transfertsExpediteur)
    {
        this.transfertsExpediteur = transfertsExpediteur;
    }

    public Set getSorties()
    {
        return sorties;
    }

    public void setSorties(Set sorties)
    {
        this.sorties = sorties;
    }

    public Set getUtilisateurDepots()
    {
        return utilisateurDepots;
    }

    public void setUtilisateurDepots(Set utilisateurDepots)
    {
        this.utilisateurDepots = utilisateurDepots;
    }

    public Set getStockReceptions()
    {
        return stockReceptions;
    }

    public void setStockReceptions(Set stockReceptions)
    {
        this.stockReceptions = stockReceptions;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_DEPOT")
    private int idDepot;
    @Column(name="REFERENCE_DEPOT")
    private String referenceDepot;
    @ManyToOne
    @JoinColumn(name="ID_VILLE")
    private Ville ville;
    @OneToMany(mappedBy="depot")
    private Set receptions;
    @OneToMany(mappedBy="depot")
    private Set recuperations;
    @OneToMany(mappedBy="depot")
    private Set retours;
    @OneToMany(mappedBy="depot")
    private Set sorties;
    @OneToMany(mappedBy="depotDestinataire")
    private Set transfertsDestinatiaire;
    @OneToMany(mappedBy="depotExpediteur")
    private Set transfertsExpediteur;
    @OneToMany(mappedBy="id.depot")
    private Set utilisateurDepots;
    @OneToMany(mappedBy="depot")
    private Set stockReceptions;
}
