// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:22:04
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   FamilleProduit.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="FAMILLE_PRODUIT")
public class FamilleProduit
    implements Serializable
{

    public FamilleProduit()
    {
    }

    public int getIdFamille()
    {
        return idFamille;
    }

    public void setIdFamille(int idFamille)
    {
        this.idFamille = idFamille;
    }

    public String getDesignationFamille()
    {
        return designationFamille;
    }

    public void setDesignationFamille(String designationFamille)
    {
        this.designationFamille = designationFamille;
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
    @Column(name="ID_FAMILLE")
    private int idFamille;
    @Column(name="DESIGNATION_FAMILLE")
    private String designationFamille;
    @OneToMany(mappedBy="familleProduit")
    private Set produits;
}
