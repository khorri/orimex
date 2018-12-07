// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:20:58
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Caisse.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

// Referenced classes of package ma.co.orimex.stock.entite:
//            Produit

@Entity
@Table(name="CAISSE")
public class Caisse
    implements Serializable
{

    public Caisse()
    {
    }

    public int getIdCaisse()
    {
        return idCaisse;
    }

    public void setIdCaisse(int idCaisse)
    {
        this.idCaisse = idCaisse;
    }

    public int getNombrePlateaux()
    {
        return nombrePlateaux;
    }

    public void setNombrePlateaux(int nombrePlateaux)
    {
        this.nombrePlateaux = nombrePlateaux;
    }

    public String getNumeroConteneur()
    {
        return numeroConteneur;
    }

    public void setNumeroConteneur(String numeroConteneur)
    {
        this.numeroConteneur = numeroConteneur;
    }

    public String getReferenceCaisse()
    {
        return referenceCaisse;
    }

    public void setReferenceCaisse(String referenceCaisse)
    {
        this.referenceCaisse = referenceCaisse;
    }

    public Set getCasses()
    {
        return casses;
    }

    public void setCasses(Set casses)
    {
        this.casses = casses;
    }

    public Set getReceptions()
    {
        return receptions;
    }

    public void setReceptions(Set receptions)
    {
        this.receptions = receptions;
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

    public Produit getProduit()
    {
        return produit;
    }

    public void setProduit(Produit produit)
    {
        this.produit = produit;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_CAISSE")
    private int idCaisse;
    @Column(name="NOMBRE_PLATEAUX")
    private int nombrePlateaux;
    @Column(name="NUMERO_CONTENEUR")
    private String numeroConteneur;
    @Column(name="REFERENCE_CAISSE")
    private String referenceCaisse;
    @ManyToOne
    @JoinColumn(name="ID_PRODUIT")
    private Produit produit;
    @OneToMany(mappedBy="caisse")
    private Set casses;
    @OneToMany(mappedBy="caisse")
    private Set receptions;
    @OneToMany(mappedBy="caisse")
    private Set sorties;
    @OneToMany(mappedBy="caisse")
    private Set transferts;
}
