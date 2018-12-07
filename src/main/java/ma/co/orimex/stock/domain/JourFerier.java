// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:22:21
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   JourFerier.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="JOUR_FERIER")
public class JourFerier
    implements Serializable
{

    public JourFerier()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAnnee()
    {
        return annee;
    }

    public void setAnnee(int annee)
    {
        this.annee = annee;
    }

    public Date getDebut()
    {
        return debut;
    }

    public void setDebut(Date debut)
    {
        this.debut = debut;
    }

    public Date getFin()
    {
        return fin;
    }

    public void setFin(Date fin)
    {
        this.fin = fin;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID")
    private int id;
    @Column(name="ANNEE")
    private int annee;
    @Column(name="DEBUT")
    private Date debut;
    @Column(name="FIN")
    private Date fin;
}
