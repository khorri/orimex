// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:46:34
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatFournisseur.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="ACHAT_FOURNISSEUR")
public class AchatFournisseur
    implements Serializable
{

    public AchatFournisseur()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTypeFournisseur()
    {
        return typeFournisseur;
    }

    public void setTypeFournisseur(String typeFournisseur)
    {
        this.typeFournisseur = typeFournisseur;
    }

    public String getCodeFournisseur()
    {
        return codeFournisseur;
    }

    public void setCodeFournisseur(String codeFournisseur)
    {
        this.codeFournisseur = codeFournisseur;
    }

    public String getDesignationFournisseur()
    {
        return designationFournisseur;
    }

    public void setDesignationFournisseur(String designationFournisseur)
    {
        this.designationFournisseur = designationFournisseur;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID")
    @GeneratedValue
    private int id;
    @Column(name="TYPE_FOURNISSEUR")
    private String typeFournisseur;
    @Column(name="CODE_FOURNISSEUR")
    private String codeFournisseur;
    @Column(name="DESIGNATION_FOURNISSEUR")
    private String designationFournisseur;
}
