// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:27:19
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   TypeBon.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="TYPE_BON")
public class TypeBon
    implements Serializable
{

    public TypeBon()
    {
    }

    public int getIdTypeBon()
    {
        return idTypeBon;
    }

    public void setIdTypeBon(int idTypeBon)
    {
        this.idTypeBon = idTypeBon;
    }

    public String getLibelleTypeBon()
    {
        return libelleTypeBon;
    }

    public void setLibelleTypeBon(String libelleTypeBon)
    {
        this.libelleTypeBon = libelleTypeBon;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_TYPE_BON")
    private int idTypeBon;
    @Column(name="LIBELLE_TYPE_BON")
    private String libelleTypeBon;
}
