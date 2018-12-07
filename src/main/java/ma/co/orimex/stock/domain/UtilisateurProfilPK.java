// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:28:04
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   UtilisateurProfilPK.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            Utilisateur, Profil

@Embeddable
public class UtilisateurProfilPK
    implements Serializable
{

    public UtilisateurProfilPK()
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
            UtilisateurProfilPK castOther = (UtilisateurProfilPK)other;
            return utilisateur.getIdUtilisateur() == castOther.utilisateur.getIdUtilisateur() && profil.getCodeProfil().equals(castOther.profil.getCodeProfil());
        }
    }

    public int hashCode()
    {
        int prime = 31;
        int hash = 17;
        hash = hash * 31 + utilisateur.getIdUtilisateur();
        hash = hash * 31 + profil.getCodeProfil().hashCode();
        return hash;
    }

    public Profil getProfil()
    {
        return profil;
    }

    public void setProfil(Profil profil)
    {
        this.profil = profil;
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
    @ManyToOne
    @JoinColumn(name="ID_UTILISATEUR")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name="CODE_PROFIL")
    private Profil profil;
}
