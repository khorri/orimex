// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:46:05
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatConteneurReception.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            AchatConteneurArrivage

@Entity
@Table(name="ACHAT_CONTENEUR_RECEPTION")
public class AchatConteneurReception
    implements Serializable
{

    public AchatConteneurReception()
    {
    }

    public int getIdConteneurReception()
    {
        return idConteneurReception;
    }

    public void setIdConteneurReception(int idConteneurReception)
    {
        this.idConteneurReception = idConteneurReception;
    }

    public String getNumeroConteneur()
    {
        return numeroConteneur;
    }

    public void setNumeroConteneur(String numeroConteneur)
    {
        this.numeroConteneur = numeroConteneur;
    }

    public int getNumeroSequence()
    {
        return numeroSequence;
    }

    public void setNumeroSequence(int numeroSequence)
    {
        this.numeroSequence = numeroSequence;
    }

    public AchatConteneurArrivage getAchatConteneurArrivage()
    {
        return achatConteneurArrivage;
    }

    public void setAchatConteneurArrivage(AchatConteneurArrivage achatConteneurArrivage)
    {
        this.achatConteneurArrivage = achatConteneurArrivage;
    }

    public Set getAchatArticleConteneurReception()
    {
        return achatArticleConteneurReception;
    }

    public void setAchatArticleConteneurReception(Set achatArticleConteneurReception)
    {
        this.achatArticleConteneurReception = achatArticleConteneurReception;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_CONTENEUR_RECEPTION")
    @GeneratedValue
    private int idConteneurReception;
    @Column(name="NUMERO_CONTENEUR")
    private String numeroConteneur;
    @Column(name="NUMERO_SEQUENCE")
    private int numeroSequence;
    @OneToOne
    @JoinColumn(name="FK_ID_CONTENEUR_ARRIVAGE")
    private AchatConteneurArrivage achatConteneurArrivage;
    @OneToMany(mappedBy="achatConteneurReception")
    private Set achatArticleConteneurReception;
}
