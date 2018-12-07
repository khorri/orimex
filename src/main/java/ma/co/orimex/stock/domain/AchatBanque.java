// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 05/12/2018 11:45:42
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   AchatBanque.java

package ma.co.orimex.stock.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="ACHAT_BANQUE")
public class AchatBanque
    implements Serializable
{

    public AchatBanque()
    {
    }

    public int getIdBanque()
    {
        return idBanque;
    }

    public void setIdBanque(int idBanque)
    {
        this.idBanque = idBanque;
    }

    public String getCodeBanque()
    {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque)
    {
        this.codeBanque = codeBanque;
    }

    public String getDesignationBanque()
    {
        return designationBanque;
    }

    public void setDesignationBanque(String designationBanque)
    {
        this.designationBanque = designationBanque;
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
    @Column(name="ID_BANQUE")
    @GeneratedValue
    private int idBanque;
    @Column(name="CODE_BANQUE")
    private String codeBanque;
    @Column(name="DESIGNATION_BANQUE")
    private String designationBanque;
    @OneToMany(mappedBy="banque")
    private Set achatDossiers;
}
