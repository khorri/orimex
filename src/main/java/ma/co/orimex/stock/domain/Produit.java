// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:22:50
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Produit.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;

// Referenced classes of package ma.co.orimex.stock.entite:
//            Couleur, FamilleProduit, Origine

@Entity
@Table(name="PRODUIT")
public class Produit
    implements Serializable
{

    public String getDesignationProduit()
    {
        return designationProduit;
    }

    public void setDesignationProduit(String designationProduit)
    {
        this.designationProduit = designationProduit;
    }

    public String getDimension()
    {
        return dimension;
    }

    public void setDimension(String dimension)
    {
        this.dimension = dimension;
    }

    public String getEpaisseur()
    {
        return epaisseur;
    }

    public void setEpaisseur(String epaisseur)
    {
        this.epaisseur = epaisseur;
    }

    public BigDecimal getLargeure()
    {
        return largeure;
    }

    public void setLargeure(BigDecimal largeure)
    {
        this.largeure = largeure;
    }

    public BigDecimal getLongueur()
    {
        return longueur;
    }

    public void setLongueur(BigDecimal longueur)
    {
        this.longueur = longueur;
    }

    public String getReferenceProduit()
    {
        return referenceProduit;
    }

    public void setReferenceProduit(String referenceProduit)
    {
        this.referenceProduit = referenceProduit;
    }

    public Produit()
    {
    }

    public int getIdProduit()
    {
        return idProduit;
    }

    public void setIdProduit(int idProduit)
    {
        this.idProduit = idProduit;
    }

    public Couleur getCouleur()
    {
        return couleur;
    }

    public void setCouleur(Couleur couleur)
    {
        this.couleur = couleur;
    }

    public FamilleProduit getFamilleProduit()
    {
        return familleProduit;
    }

    public void setFamilleProduit(FamilleProduit familleProduit)
    {
        this.familleProduit = familleProduit;
    }

    public Origine getOrigine()
    {
        return origine;
    }

    public void setOrigine(Origine origine)
    {
        this.origine = origine;
    }

    public Set getRecuperations()
    {
        return recuperations;
    }

    public void setRecuperations(Set recuperations)
    {
        this.recuperations = recuperations;
    }

    public Set getRetours()
    {
        return retours;
    }

    public void setRetours(Set retours)
    {
        this.retours = retours;
    }

    public Set getAchatArticleLigneProforma()
    {
        return achatArticleLigneProforma;
    }

    public void setAchatArticleLigneProforma(Set achatArticleLigneProforma)
    {
        this.achatArticleLigneProforma = achatArticleLigneProforma;
    }

    public Set getAchatArticleLigneProformaCopys()
    {
        return achatArticleLigneProformaCopys;
    }

    public void setAchatArticleLigneProformaCopys(Set achatArticleLigneProformaCopys)
    {
        this.achatArticleLigneProformaCopys = achatArticleLigneProformaCopys;
    }

    public BigDecimal getPoids()
    {
        return poids;
    }

    public void setPoids(BigDecimal poids)
    {
        this.poids = poids;
    }

    public String getLibelleCouleur()
    {
        return libelleCouleur;
    }

    public void setLibelleCouleur(String libelleCouleur)
    {
        this.libelleCouleur = libelleCouleur;
    }

    public String getLibelleFamille()
    {
        return libelleFamille;
    }

    public void setLibelleFamille(String libelleFamille)
    {
        this.libelleFamille = libelleFamille;
    }

    public String getLibelleOrigine()
    {
        return libelleOrigine;
    }

    public void setLibelleOrigine(String libelleOrigine)
    {
        this.libelleOrigine = libelleOrigine;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_PRODUIT")
    private int idProduit;
    @Column(name="DESIGNATION_PRODUIT")
    private String designationProduit;
    @Column(name="DIMENSION")
    private String dimension;
    @Column(name="EPAISSEUR")
    private String epaisseur;
    @Column(name="LARGEURE")
    private BigDecimal largeure;
    @Column(name="LONGUEUR")
    private BigDecimal longueur;
    @Column(name="REFERENCE_PRODUIT")
    private String referenceProduit;
    @ManyToOne
    @JoinColumn(name="ID_COULEUR")
    private Couleur couleur;
    @ManyToOne
    @JoinColumn(name="ID_FAMILLE")
    private FamilleProduit familleProduit;
    @ManyToOne
    @JoinColumn(name="ID_ORIGINE")
    private Origine origine;
    @Column(name="POIDS")
    private BigDecimal poids;
    @Column(name="LIBELLE_COULEUR")
    private String libelleCouleur;
    @Column(name="LIBELLE_FAMILLE")
    private String libelleFamille;
    @Column(name="LIBELLE_ORIGINE")
    private String libelleOrigine;
    @OneToMany(mappedBy="produit")
    private Set recuperations;
    @OneToMany(mappedBy="produit")
    private Set retours;
    @OneToMany(mappedBy="produit")
    private Set achatArticleLigneProforma;
    @OneToMany(mappedBy="produit")
    private Set achatArticleLigneProformaCopys;
}
