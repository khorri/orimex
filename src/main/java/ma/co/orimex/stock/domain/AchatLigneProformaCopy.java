// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:19:50
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatLigneProformaCopy.java

package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatDossier

@Entity
@Table(name="ACHAT_LIGNE_PROFORMAT_COPY")
public class AchatLigneProformaCopy
    implements Serializable
{

    public AchatLigneProformaCopy()
    {
    }

    public int getIdLigneProformaCopy()
    {
        return idLigneProformaCopy;
    }

    public void setIdLigneProformaCopy(int idLigneProformaCopy)
    {
        this.idLigneProformaCopy = idLigneProformaCopy;
    }

    public int getNombreConteneurs()
    {
        return nombreConteneurs;
    }

    public void setNombreConteneurs(int nombreConteneurs)
    {
        this.nombreConteneurs = nombreConteneurs;
    }

    public int getNumeroSequence()
    {
        return numeroSequence;
    }

    public void setNumeroSequence(int numeroSequence)
    {
        this.numeroSequence = numeroSequence;
    }

    public Set getAchatArticleLigneProformaCopys()
    {
        return achatArticleLigneProformaCopys;
    }

    public void setAchatArticleLigneProformaCopys(Set achatArticleLigneProformaCopys)
    {
        this.achatArticleLigneProformaCopys = achatArticleLigneProformaCopys;
    }

    public AchatDossier getAchatDossier()
    {
        return achatDossier;
    }

    public void setAchatDossier(AchatDossier achatDossier)
    {
        this.achatDossier = achatDossier;
    }

    public BigDecimal getPoids()
    {
        return poids;
    }

    public void setPoids(BigDecimal poids)
    {
        this.poids = poids;
    }

    public BigDecimal getMontantTC()
    {
        return montantTC;
    }

    public void setMontantTC(BigDecimal montantTC)
    {
        this.montantTC = montantTC;
    }

    public BigDecimal getQuantiteTC()
    {
        return quantiteTC;
    }

    public void setQuantiteTC(BigDecimal quantiteTC)
    {
        this.quantiteTC = quantiteTC;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_LIGNE_PROFORMA_COPY")
    @GeneratedValue
    private int idLigneProformaCopy;
    @Column(name="MONTANT_TC")
    private BigDecimal montantTC;
    @Column(name="QUANTITE_TC")
    private BigDecimal quantiteTC;
    @Column(name="NOMBRE_CONTENEURS")
    private int nombreConteneurs;
    @Column(name="NUMERO_SEQUENCE")
    private int numeroSequence;
    @Column(name="POIDS")
    private BigDecimal poids;
    @ManyToOne
    @JoinColumn(name="FK_ID_DOSSIER_ACHAT")
    private AchatDossier achatDossier;
    @OneToMany(mappedBy="achatLigneProformaCopy")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE})
    private Set achatArticleLigneProformaCopys;
}
