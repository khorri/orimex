// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:28:12
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Ville.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="VILLE")
public class Ville
    implements Serializable
{

    public Ville()
    {
    }

    public int getIdVille()
    {
        return idVille;
    }

    public void setIdVille(int idVille)
    {
        this.idVille = idVille;
    }

    public String getLibelleVille()
    {
        return libelleVille;
    }

    public void setLibelleVille(String libelleVille)
    {
        this.libelleVille = libelleVille;
    }

    public Set getDepots()
    {
        return depots;
    }

    public void setDepots(Set depots)
    {
        this.depots = depots;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_VILLE")
    private int idVille;
    @Column(name="LIBELLE_VILLE")
    private String libelleVille;
    @OneToMany(mappedBy="ville")
    private Set depots;
}
