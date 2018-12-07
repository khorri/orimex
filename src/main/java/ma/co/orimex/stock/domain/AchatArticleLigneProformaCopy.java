// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:45:32
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatArticleLigneProformaCopy.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatLigneProformaCopy, Produit

@Entity
@Table(name="ACHAT_ARTICLE_LIGNE_PROFORMA_COPY")
public class AchatArticleLigneProformaCopy
    implements Serializable
{

    public AchatArticleLigneProformaCopy()
    {
    }

    public int getNombreCaissesTC()
    {
        return nombreCaissesTC;
    }

    public void setNombreCaissesTC(int nombreCaissesTC)
    {
        this.nombreCaissesTC = nombreCaissesTC;
    }

    public int getNombreFeuillesCaisse()
    {
        return nombreFeuillesCaisse;
    }

    public void setNombreFeuillesCaisse(int nombreFeuillesCaisse)
    {
        this.nombreFeuillesCaisse = nombreFeuillesCaisse;
    }

    public BigDecimal getQuantite()
    {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite)
    {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire()
    {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire)
    {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getMontant()
    {
        return montant;
    }

    public void setMontant(BigDecimal montant)
    {
        this.montant = montant;
    }

    public Produit getProduit()
    {
        return produit;
    }

    public void setProduit(Produit produit)
    {
        this.produit = produit;
    }

    public int getNumeroSequence()
    {
        return numeroSequence;
    }

    public void setNumeroSequence(int numeroSequence)
    {
        this.numeroSequence = numeroSequence;
    }

    public int getIdArticleLigneProformaCopy()
    {
        return idArticleLigneProformaCopy;
    }

    public void setIdArticleLigneProformaCopy(int idArticleLigneProformaCopy)
    {
        this.idArticleLigneProformaCopy = idArticleLigneProformaCopy;
    }

    public AchatLigneProformaCopy getAchatLigneProformaCopy()
    {
        return achatLigneProformaCopy;
    }

    public void setAchatLigneProformaCopy(AchatLigneProformaCopy achatLigneProformaCopy)
    {
        this.achatLigneProformaCopy = achatLigneProformaCopy;
    }

    public BigDecimal getDimention()
    {
        return dimention;
    }

    public void setDimention(BigDecimal dimention)
    {
        this.dimention = dimention;
    }

    public BigDecimal getPoids()
    {
        return poids;
    }

    public void setPoids(BigDecimal poids)
    {
        this.poids = poids;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_ARTICLE_LIGNE_PROFORMA_COPY")
    @GeneratedValue
    private int idArticleLigneProformaCopy;
    @Column(name="NUMERO_SEQUENCE")
    private int numeroSequence;
    @Column(name="NOMBRE_CAISSESTC")
    private int nombreCaissesTC;
    @Column(name="NOMBRE_FEUILLECAISSE")
    private int nombreFeuillesCaisse;
    @Column(name="DIMENSION")
    private BigDecimal dimention;
    @Column(name="QUANTITE")
    private BigDecimal quantite;
    @Column(name="PRIX_UNITAIRE")
    private BigDecimal prixUnitaire;
    @Column(name="MONTANT")
    private BigDecimal montant;
    @Column(name="POIDS")
    private BigDecimal poids;
    @ManyToOne
    @JoinColumn(name="FK_ID_LIGNE_PROFORMA_COPY")
    private AchatLigneProformaCopy achatLigneProformaCopy;
    @ManyToOne
    @JoinColumn(name="FK_ID_ARTICLE")
    private Produit produit;
}
