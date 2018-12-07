// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:20:11
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatProforma.java

package ma.co.orimex.stock.domain;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatDossier

@Entity
@Table(name="ACHAT_PROFORMA")
public class AchatProforma
    implements Serializable
{

    public AchatProforma()
    {
    }

    public int getIdProforma()
    {
        return idProforma;
    }

    public void setIdProforma(int idProforma)
    {
        this.idProforma = idProforma;
    }

    public BigDecimal getCoutFob()
    {
        return coutFob;
    }

    public void setCoutFob(BigDecimal coutFob)
    {
        this.coutFob = coutFob;
    }

    public BigDecimal getCoutFret()
    {
        return coutFret;
    }

    public void setCoutFret(BigDecimal coutFret)
    {
        this.coutFret = coutFret;
    }

    public BigDecimal getMontantTotal()
    {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal)
    {
        this.montantTotal = montantTotal;
    }

    public String getNumeroBonProforma()
    {
        return numeroBonProforma;
    }

    public void setNumeroBonProforma(String numeroBonProforma)
    {
        this.numeroBonProforma = numeroBonProforma;
    }

    public AchatDossier getAchatDossier()
    {
        return achatDossier;
    }

    public void setAchatDossier(AchatDossier achatDossier)
    {
        this.achatDossier = achatDossier;
    }

    public int getNombreTC()
    {
        return nombreTC;
    }

    public void setNombreTC(int nombreTC)
    {
        this.nombreTC = nombreTC;
    }

    public Set getAchatLigneProformas()
    {
        return achatLigneProformas;
    }

    public void setAchatLigneProformas(Set achatLigneProformas)
    {
        this.achatLigneProformas = achatLigneProformas;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public BigDecimal getPoids()
    {
        return poids;
    }

    public void setPoids(BigDecimal poids)
    {
        this.poids = poids;
    }

    public Date getDateProforma()
    {
        return dateProforma;
    }

    public void setDateProforma(Date dateProforma)
    {
        this.dateProforma = dateProforma;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_PROFORMA")
    @GeneratedValue
    private int idProforma;
    @Column(name="NOMBRE_TC")
    private int nombreTC;
    @Column(name="COUT_FOB")
    private BigDecimal coutFob;
    @Column(name="COUT_FRET")
    private BigDecimal coutFret;
    @Column(name="MONTANT_TOTAL")
    private BigDecimal montantTotal;
    @Column(name="NUMERO_BON_PROFORMA")
    private String numeroBonProforma;
    @Column(name="TYPE")
    private String type;
    @Column(name="POIDS")
    private BigDecimal poids;
    @Column(name="DATE_PROFORMA")
    private Date dateProforma;
    @OneToMany(mappedBy="achatProforma")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE})
    private Set achatLigneProformas;
    @ManyToOne
    @JoinColumn(name="FK_ID_DOSSIER")
    private AchatDossier achatDossier;
}
