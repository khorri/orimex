// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:27:47
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   UtilisateurDepotPK.java

package ma.co.orimex.stock.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

// Referenced classes of package ma.co.orimex.stock.entite:
//            UtilisateurProfilPK, Utilisateur, Depot

@Embeddable
public class UtilisateurDepotPK
    implements Serializable
{

    public UtilisateurDepotPK()
    {
    }

    public boolean equals(Object other)
    {
        if(this == other)
            return true;
        if(!(other instanceof UtilisateurProfilPK))
        {
            return false;
        } else
        {
            UtilisateurDepotPK castOther = (UtilisateurDepotPK)other;
            return utilisateur.getIdUtilisateur() == castOther.utilisateur.getIdUtilisateur() && depot.getIdDepot() == castOther.depot.getIdDepot();
        }
    }

    public int hashCode()
    {
        int prime = 31;
        int hash = 17;
        hash = hash * 31 + utilisateur.getIdUtilisateur();
        hash = hash * 31 + depot.getIdDepot();
        return hash;
    }

    public Utilisateur getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    public Depot getDepot()
    {
        return depot;
    }

    public void setDepot(Depot depot)
    {
        this.depot = depot;
    }

    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name="ID_UTILISATEUR")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name="ID_DEPOT")
    private Depot depot;
}
