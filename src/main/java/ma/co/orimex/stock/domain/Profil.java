// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:23:00
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Profil.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="PROFIL")
public class Profil
    implements Serializable
{

    public Profil()
    {
    }

    public String getCodeProfil()
    {
        return codeProfil;
    }

    public void setCodeProfil(String codeProfil)
    {
        this.codeProfil = codeProfil;
    }

    public String getDescriptionProfil()
    {
        return descriptionProfil;
    }

    public void setDescriptionProfil(String descriptionProfil)
    {
        this.descriptionProfil = descriptionProfil;
    }

    public Set getUtilisateurProfils()
    {
        return utilisateurProfils;
    }

    public void setUtilisateurProfils(Set utilisateurProfils)
    {
        this.utilisateurProfils = utilisateurProfils;
    }

    public Set getProfilActions()
    {
        return profilActions;
    }

    public void setProfilActions(Set profilActions)
    {
        this.profilActions = profilActions;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="CODE_PROFIL")
    private String codeProfil;
    @Column(name="DESCRIPTION_PROFIL")
    private String descriptionProfil;
    @OneToMany(mappedBy="id.profil")
    private Set utilisateurProfils;
    @OneToMany(mappedBy="id.profil")
    private Set profilActions;
}
