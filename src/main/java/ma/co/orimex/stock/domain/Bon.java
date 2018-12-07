// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:20:50
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Bon.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="BON")
public class Bon
    implements Serializable
{

    public Bon()
    {
    }

    public int getIdBon()
    {
        return idBon;
    }

    public void setIdBon(int idBon)
    {
        this.idBon = idBon;
    }

    public int getIdTypeBon()
    {
        return idTypeBon;
    }

    public void setIdTypeBon(int idTypeBon)
    {
        this.idTypeBon = idTypeBon;
    }

    public String getNumeroBon()
    {
        return numeroBon;
    }

    public void setNumeroBon(String numeroBon)
    {
        this.numeroBon = numeroBon;
    }

    public Set getReceptions()
    {
        return receptions;
    }

    public void setReceptions(Set receptions)
    {
        this.receptions = receptions;
    }

    public Set getRetours()
    {
        return retours;
    }

    public void setRetours(Set retours)
    {
        this.retours = retours;
    }

    public Set getSorties()
    {
        return sorties;
    }

    public void setSorties(Set sorties)
    {
        this.sorties = sorties;
    }

    public Set getTransferts()
    {
        return transferts;
    }

    public void setTransferts(Set transferts)
    {
        this.transferts = transferts;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_BON")
    private int idBon;
    @Column(name="ID_TYPE_BON")
    private int idTypeBon;
    @Column(name="NUMERO_BON")
    private String numeroBon;
    @OneToMany(mappedBy="bon")
    private Set receptions;
    @OneToMany(mappedBy="bon")
    private Set retours;
    @OneToMany(mappedBy="bon")
    private Set sorties;
    @OneToMany(mappedBy="bon")
    private Set transferts;
}
