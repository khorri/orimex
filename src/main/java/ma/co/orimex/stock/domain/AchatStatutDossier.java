// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:20:19
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatStatutDossier.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="ACHAT_STATUT_DOSSIER")
public class AchatStatutDossier
    implements Serializable
{

    public AchatStatutDossier()
    {
    }

    public int getIdStatutDossier()
    {
        return idStatutDossier;
    }

    public void setIdStatutDossier(int idStatutDossier)
    {
        this.idStatutDossier = idStatutDossier;
    }

    public String getLibelleStatutDossier()
    {
        return libelleStatutDossier;
    }

    public void setLibelleStatutDossier(String libelleStatutDossier)
    {
        this.libelleStatutDossier = libelleStatutDossier;
    }

    public Set getAchatDossiers()
    {
        return achatDossiers;
    }

    public void setAchatDossiers(Set achatDossiers)
    {
        this.achatDossiers = achatDossiers;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_STATUT_DOSSIER")
    @GeneratedValue
    private int idStatutDossier;
    @Column(name="LIBELLE_STATUT_DOSSIER")
    private String libelleStatutDossier;
    @OneToMany(mappedBy="statut")
    private Set achatDossiers;
}
