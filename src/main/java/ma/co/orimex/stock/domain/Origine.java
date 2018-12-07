// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:22:37
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Origine.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="ORIGINE")
public class Origine
    implements Serializable
{

    public Origine()
    {
    }

    public int getIdOrigine()
    {
        return idOrigine;
    }

    public void setIdOrigine(int idOrigine)
    {
        this.idOrigine = idOrigine;
    }

    public String getDesignationOrigine()
    {
        return designationOrigine;
    }

    public void setDesignationOrigine(String designationOrigine)
    {
        this.designationOrigine = designationOrigine;
    }

    public Set getProduits()
    {
        return produits;
    }

    public void setProduits(Set produits)
    {
        this.produits = produits;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_ORIGINE")
    private int idOrigine;
    @Column(name="DESIGNATION_ORIGINE")
    private String designationOrigine;
    @OneToMany(mappedBy="origine")
    private Set produits;
}
