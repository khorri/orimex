// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:46:46
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatLigneProforma.java

package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatProforma

@Entity
@Table(name="ACHAT_LIGNE_PROFORMA")
public class AchatLigneProforma
    implements Serializable
{

    public AchatLigneProforma()
    {
    }

    public int getIdLigneProforma()
    {
        return idLigneProforma;
    }

    public void setIdLigneProforma(int idLigneProforma)
    {
        this.idLigneProforma = idLigneProforma;
    }

    public BigDecimal getMontant()
    {
        return montant;
    }

    public void setMontant(BigDecimal montant)
    {
        this.montant = montant;
    }

    public AchatProforma getAchatProforma()
    {
        return achatProforma;
    }

    public void setAchatProforma(AchatProforma achatProforma)
    {
        this.achatProforma = achatProforma;
    }

    public Set getAchatArticleLigneProforma()
    {
        return achatArticleLigneProforma;
    }

    public void setAchatArticleLigneProforma(Set achatArticleLigneProforma)
    {
        this.achatArticleLigneProforma = achatArticleLigneProforma;
    }

    public int getNumeroSequence()
    {
        return numeroSequence;
    }

    public void setNumeroSequence(int numeroSequence)
    {
        this.numeroSequence = numeroSequence;
    }

    public int getNombreConteneurs()
    {
        return nombreConteneurs;
    }

    public void setNombreConteneurs(int nombreConteneurs)
    {
        this.nombreConteneurs = nombreConteneurs;
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
    @Column(name="ID_LIGNE_PROFORMA")
    @GeneratedValue
    private int idLigneProforma;
    @Column(name="NOMBRE_CONTENEURS")
    private int nombreConteneurs;
    @Column(name="MONTANT")
    private BigDecimal montant;
    @Column(name="NUMERO_SEQUENCE")
    private int numeroSequence;
    @Column(name="POIDS")
    private BigDecimal poids;
    @ManyToOne
    @JoinColumn(name="FK_ID_PROFORMA")
    private AchatProforma achatProforma;
    @OneToMany(mappedBy="achatLigneProforma")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE})
    private Set achatArticleLigneProforma;
}
