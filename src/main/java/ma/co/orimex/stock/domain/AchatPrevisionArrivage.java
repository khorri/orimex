// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:19:59
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatPrevisionArrivage.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatDossier

@Entity
@Table(name="ACHAT_PREVISION_ARRIVAGE")
public class AchatPrevisionArrivage
    implements Serializable
{

    public AchatPrevisionArrivage()
    {
    }

    public int getIdPrevisionArrivage()
    {
        return idPrevisionArrivage;
    }

    public void setIdPrevisionArrivage(int idPrevisionArrivage)
    {
        this.idPrevisionArrivage = idPrevisionArrivage;
    }

    public String getProduit()
    {
        return produit;
    }

    public void setProduit(String produit)
    {
        this.produit = produit;
    }

    public String getDesigantionCopagnieMaritme()
    {
        return desigantionCopagnieMaritme;
    }

    public void setDesigantionCopagnieMaritme(String desigantionCopagnieMaritme)
    {
        this.desigantionCopagnieMaritme = desigantionCopagnieMaritme;
    }

    public String getPol()
    {
        return pol;
    }

    public void setPol(String pol)
    {
        this.pol = pol;
    }

    public String getNumeroBL()
    {
        return numeroBL;
    }

    public void setNumeroBL(String numeroBL)
    {
        this.numeroBL = numeroBL;
    }

    public int getNombreTC()
    {
        return nombreTC;
    }

    public void setNombreTC(int nombreTC)
    {
        this.nombreTC = nombreTC;
    }

    public Date getEtd()
    {
        return etd;
    }

    public void setEtd(Date etd)
    {
        this.etd = etd;
    }

    public Date getEta()
    {
        return eta;
    }

    public void setEta(Date eta)
    {
        this.eta = eta;
    }

    public String getDocuments()
    {
        return documents;
    }

    public void setDocuments(String documents)
    {
        this.documents = documents;
    }

    public String getDouane()
    {
        return douane;
    }

    public void setDouane(String douane)
    {
        this.douane = douane;
    }

    public AchatDossier getAchatDossier()
    {
        return achatDossier;
    }

    public void setAchatDossier(AchatDossier achatDossier)
    {
        this.achatDossier = achatDossier;
    }

    public int getActive()
    {
        return active;
    }

    public void setActive(int active)
    {
        this.active = active;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_PREVISION_ARRIVAGE")
    @GeneratedValue
    private int idPrevisionArrivage;
    @Column(name="PRODUIT")
    private String produit;
    @Column(name="DESIGNATION_COMPAGNIE_MARITIME")
    private String desigantionCopagnieMaritme;
    @Column(name="POL")
    private String pol;
    @Column(name="NUMERO_BL")
    private String numeroBL;
    @Column(name="NOMBRE_TC")
    private int nombreTC;
    @Column(name="ETD")
    private Date etd;
    @Column(name="ETA")
    private Date eta;
    @Column(name="DOCUMENTS")
    private String documents;
    @Column(name="DOUANE")
    private String douane;
    @Column(name="ACTIVE")
    private int active;
    @ManyToOne
    @JoinColumn(name="FK_ID_DOSSIER_ACHAT")
    private AchatDossier achatDossier;
}
