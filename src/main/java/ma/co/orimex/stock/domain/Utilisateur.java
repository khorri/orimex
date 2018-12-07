// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:27:29
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Utilisateur.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="UTILISATEUR")
public class Utilisateur
    implements Serializable
{

    public Utilisateur()
    {
    }

    public int getIdUtilisateur()
    {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur)
    {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLoginUtilisateur()
    {
        return loginUtilisateur;
    }

    public void setLoginUtilisateur(String loginUtilisateur)
    {
        this.loginUtilisateur = loginUtilisateur;
    }

    public String getMatriculeUtilisateur()
    {
        return matriculeUtilisateur;
    }

    public void setMatriculeUtilisateur(String matriculeUtilisateur)
    {
        this.matriculeUtilisateur = matriculeUtilisateur;
    }

    public String getNomUtilsateur()
    {
        return nomUtilsateur;
    }

    public void setNomUtilsateur(String nomUtilsateur)
    {
        this.nomUtilsateur = nomUtilsateur;
    }

    public String getPasswordUtilisateur()
    {
        return passwordUtilisateur;
    }

    public void setPasswordUtilisateur(String passwordUtilisateur)
    {
        this.passwordUtilisateur = passwordUtilisateur;
    }

    public String getPrenomUtilsateur()
    {
        return prenomUtilsateur;
    }

    public void setPrenomUtilsateur(String prenomUtilsateur)
    {
        this.prenomUtilsateur = prenomUtilsateur;
    }

    public Set getCasses()
    {
        return casses;
    }

    public void setCasses(Set casses)
    {
        this.casses = casses;
    }

    public Set getReceptions()
    {
        return receptions;
    }

    public void setReceptions(Set receptions)
    {
        this.receptions = receptions;
    }

    public Set getRecuperations()
    {
        return recuperations;
    }

    public void setRecuperations(Set recuperations)
    {
        this.recuperations = recuperations;
    }

    public Set getRetours()
    {
        return retours;
    }

    public void setRetours(Set retours)
    {
        this.retours = retours;
    }

    public Set getSorties()
    {
        return sorties;
    }

    public void setSorties(Set sorties)
    {
        this.sorties = sorties;
    }

    public Set getTransferts()
    {
        return transferts;
    }

    public void setTransferts(Set transferts)
    {
        this.transferts = transferts;
    }

    public Set getUtilisateurProfils()
    {
        return utilisateurProfils;
    }

    public void setUtilisateurProfils(Set utilisateurProfils)
    {
        this.utilisateurProfils = utilisateurProfils;
    }

    public Set getUtilisateurDepots()
    {
        return utilisateurDepots;
    }

    public void setUtilisateurDepots(Set utilisateurDepots)
    {
        this.utilisateurDepots = utilisateurDepots;
    }

    public Set getStockReceptions()
    {
        return stockReceptions;
    }

    public void setStockReceptions(Set stockReceptions)
    {
        this.stockReceptions = stockReceptions;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID_UTILISATEUR")
    private int idUtilisateur;
    @Column(name="LOGIN_UTILISATEUR")
    private String loginUtilisateur;
    @Column(name="MATRICULE_UTILISATEUR")
    private String matriculeUtilisateur;
    @Column(name="NOM_UTILSATEUR")
    private String nomUtilsateur;
    @Column(name="PASSWORD_UTILISATEUR")
    private String passwordUtilisateur;
    @Column(name="PRENOM_UTILSATEUR")
    private String prenomUtilsateur;
    @OneToMany(mappedBy="utilisateur")
    private Set casses;
    @OneToMany(mappedBy="utilisateur")
    private Set receptions;
    @OneToMany(mappedBy="utilisateur")
    private Set recuperations;
    @OneToMany(mappedBy="utilisateur")
    private Set retours;
    @OneToMany(mappedBy="utilisateur")
    private Set sorties;
    @OneToMany(mappedBy="utilisateur")
    private Set transferts;
    @OneToMany(mappedBy="id.utilisateur")
    private Set utilisateurProfils;
    @OneToMany(mappedBy="id.utilisateur")
    private Set utilisateurDepots;
    @OneToMany(mappedBy="utilisateur")
    private Set stockReceptions;
}
