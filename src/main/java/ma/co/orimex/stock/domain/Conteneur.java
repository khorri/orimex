// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:21:28
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Conteneur.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="CONTENEUR")
public class Conteneur
    implements Serializable
{

    public Conteneur()
    {
    }

    public String getNumeroConteneur()
    {
        return numeroConteneur;
    }

    public void setNumeroConteneur(String numeroConteneur)
    {
        this.numeroConteneur = numeroConteneur;
    }

    public Set getReceptions()
    {
        return receptions;
    }

    public void setReceptions(Set receptions)
    {
        this.receptions = receptions;
    }

    public Set getTransferts()
    {
        return transferts;
    }

    public void setTransferts(Set transferts)
    {
        this.transferts = transferts;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="NUMERO_CONTENEUR")
    private String numeroConteneur;
    @OneToMany(mappedBy="conteneur")
    private Set receptions;
    @OneToMany(mappedBy="conteneur")
    private Set transferts;
}
