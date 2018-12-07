// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:21:07
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Camion.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="CAMION")
public class Camion
    implements Serializable
{

    public Camion()
    {
    }

    public int getIdCamion()
    {
        return idCamion;
    }

    public void setIdCamion(int idCamion)
    {
        this.idCamion = idCamion;
    }

    public String getNumeroCamion()
    {
        return numeroCamion;
    }

    public void setNumeroCamion(String numeroCamion)
    {
        this.numeroCamion = numeroCamion;
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
    @Column(name="ID_CAMION")
    private int idCamion;
    @Column(name="NUMERO_CAMION")
    private String numeroCamion;
    @OneToMany(mappedBy="camion")
    private Set receptions;
    @OneToMany(mappedBy="camion")
    private Set transferts;
}
