// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:21:37
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Couleur.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="COULEUR")
public class Couleur
    implements Serializable
{

    public Couleur()
    {
    }

    public int getIdCouleur()
    {
        return idCouleur;
    }

    public void setIdCouleur(int idCouleur)
    {
        this.idCouleur = idCouleur;
    }

    public String getCodeHtml()
    {
        return codeHtml;
    }

    public void setCodeHtml(String codeHtml)
    {
        this.codeHtml = codeHtml;
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
    @Column(name="ID_COULEUR")
    private int idCouleur;
    @Column(name="CODE_HTML")
    private String codeHtml;
    @OneToMany(mappedBy="couleur")
    private Set produits;
}
