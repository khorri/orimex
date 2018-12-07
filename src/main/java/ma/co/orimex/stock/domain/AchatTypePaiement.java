// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:20:31
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatTypePaiement.java

package ma.co.orimex.stock.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="ACHAT_TYPE_PAIEMENT")
public class AchatTypePaiement
    implements Serializable
{

    public AchatTypePaiement()
    {
    }

    public int getIdTypePaiement()
    {
        return idTypePaiement;
    }

    public void setIdTypePaiement(int idTypePaiement)
    {
        this.idTypePaiement = idTypePaiement;
    }

    public String getLibelleTypePaiement()
    {
        return libelleTypePaiement;
    }

    public void setLibelleTypePaiement(String libelleTypePaiement)
    {
        this.libelleTypePaiement = libelleTypePaiement;
    }

    public Set getAchatDossiers()
    {
        return achatDossiers;
    }

    public void setAchatDossiers(Set achatDossiers)
    {
        this.achatDossiers = achatDossiers;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_TYPE_PAIEMENT")
    @GeneratedValue
    private int idTypePaiement;
    @Column(name="LIBELLE_TYPE_PAIEMENT")
    private String libelleTypePaiement;
    @OneToMany(mappedBy="typePaiement")
    private Set achatDossiers;
}
