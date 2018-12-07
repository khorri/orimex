// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:23:21
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   ProfilActionPK.java

package ma.co.orimex.stock.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

// Referenced classes of package ma.co.orimex.stock.entite:
//            UtilisateurProfilPK, Action, Profil

@Embeddable
public class ProfilActionPK
    implements Serializable
{

    public ProfilActionPK()
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
            ProfilActionPK castOther = (ProfilActionPK)other;
            return action.getCodeAction().equals(castOther.action.getCodeAction()) && profil.getCodeProfil().equals(castOther.profil.getCodeProfil());
        }
    }

    public int hashCode()
    {
        int prime = 31;
        int hash = 17;
        hash = hash * 31 + action.getCodeAction().hashCode();
        hash = hash * 31 + profil.getCodeProfil().hashCode();
        return hash;
    }

    public Action getAction()
    {
        return action;
    }

    public void setAction(Action action)
    {
        this.action = action;
    }

    public Profil getProfil()
    {
        return profil;
    }

    public void setProfil(Profil profil)
    {
        this.profil = profil;
    }

    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name="CODE_ACTION")
    private Action action;
    @ManyToOne
    @JoinColumn(name="CODE_PROFIL")
    private Profil profil;
}
