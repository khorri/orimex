// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:46:12
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatDevise.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="ACHAT_DEVISE")
public class AchatDevise
    implements Serializable
{

    public AchatDevise()
    {
    }

    public int getIdDevise()
    {
        return idDevise;
    }

    public void setIdDevise(int idDevise)
    {
        this.idDevise = idDevise;
    }

    public String getCodeDevise()
    {
        return codeDevise;
    }

    public void setCodeDevise(String codeDevise)
    {
        this.codeDevise = codeDevise;
    }

    public String getLibelleDevise()
    {
        return libelleDevise;
    }

    public void setLibelleDevise(String libelleDevise)
    {
        this.libelleDevise = libelleDevise;
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
    @Column(name="ID_DEVISE")
    @GeneratedValue
    private int idDevise;
    @Column(name="CODE_DEVISE")
    private String codeDevise;
    @Column(name="LIBELLE_DEVISE")
    private String libelleDevise;
    @OneToMany(mappedBy="devise")
    private Set achatDossiers;
}
