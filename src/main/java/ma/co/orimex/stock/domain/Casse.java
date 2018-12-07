// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:21:18
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Casse.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.*;

// Referenced classes of package ma.co.orimex.stock.entite:
//            Caisse, Utilisateur

@Entity
@Table(name="CASSE")
public class Casse
    implements Serializable
{

    public Casse()
    {
    }

    public int getIdOperation()
    {
        return idOperation;
    }

    public void setIdOperation(int idOperation)
    {
        this.idOperation = idOperation;
    }

    public Timestamp getDateOperation()
    {
        return dateOperation;
    }

    public void setDateOperation(Timestamp dateOperation)
    {
        this.dateOperation = dateOperation;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getNombrePlateaux()
    {
        return nombrePlateaux;
    }

    public void setNombrePlateaux(int nombrePlateaux)
    {
        this.nombrePlateaux = nombrePlateaux;
    }

    public String getNumeroOperation()
    {
        return numeroOperation;
    }

    public void setNumeroOperation(String numeroOperation)
    {
        this.numeroOperation = numeroOperation;
    }

    public BigDecimal getSuperfcie()
    {
        return superfcie;
    }

    public void setSuperfcie(BigDecimal superfcie)
    {
        this.superfcie = superfcie;
    }

    public Caisse getCaisse()
    {
        return caisse;
    }

    public void setCaisse(Caisse caisse)
    {
        this.caisse = caisse;
    }

    public Utilisateur getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_OPERATION")
    private int idOperation;
    @Column(name="DATE_OPERATION")
    private Timestamp dateOperation;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="NOMBRE_PLATEAUX")
    private int nombrePlateaux;
    @Column(name="NUMERO_OPERATION")
    private String numeroOperation;
    @Column(name="SUPERFCIE")
    private BigDecimal superfcie;
    @ManyToOne
    @JoinColumn(name="ID_CAISSE")
    private Caisse caisse;
    @ManyToOne
    @JoinColumn(name="ID_UTILISATEUR")
    private Utilisateur utilisateur;
}
