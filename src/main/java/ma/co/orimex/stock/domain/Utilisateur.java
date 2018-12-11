package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Utilisateur.
 */
@Entity
@Table(name = "utilisateur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "utilisateur")
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;

    @Column(name = "login_utilisateur")
    private String loginUtilisateur;

    @Column(name = "matricule_utilisateur")
    private String matriculeUtilisateur;

    @Column(name = "nom_utilsateur")
    private String nomUtilsateur;

    @Column(name = "password_utilisateur")
    private String passwordUtilisateur;

    @Column(name = "prenom_utilsateur")
    private String prenomUtilsateur;

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Casse> casses = new HashSet<>();
    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reception> receptions = new HashSet<>();
    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Sortie> sorties = new HashSet<>();
    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Retour> retours = new HashSet<>();
    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Recuperation> recuperations = new HashSet<>();
    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UtilisateurProfil> utilisateurProfils = new HashSet<>();
    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UtilisateurDepot> utilisateurDepots = new HashSet<>();
    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Transfert> transferts = new HashSet<>();
    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<StockReception> stockReceptions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public Utilisateur idUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        return this;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLoginUtilisateur() {
        return loginUtilisateur;
    }

    public Utilisateur loginUtilisateur(String loginUtilisateur) {
        this.loginUtilisateur = loginUtilisateur;
        return this;
    }

    public void setLoginUtilisateur(String loginUtilisateur) {
        this.loginUtilisateur = loginUtilisateur;
    }

    public String getMatriculeUtilisateur() {
        return matriculeUtilisateur;
    }

    public Utilisateur matriculeUtilisateur(String matriculeUtilisateur) {
        this.matriculeUtilisateur = matriculeUtilisateur;
        return this;
    }

    public void setMatriculeUtilisateur(String matriculeUtilisateur) {
        this.matriculeUtilisateur = matriculeUtilisateur;
    }

    public String getNomUtilsateur() {
        return nomUtilsateur;
    }

    public Utilisateur nomUtilsateur(String nomUtilsateur) {
        this.nomUtilsateur = nomUtilsateur;
        return this;
    }

    public void setNomUtilsateur(String nomUtilsateur) {
        this.nomUtilsateur = nomUtilsateur;
    }

    public String getPasswordUtilisateur() {
        return passwordUtilisateur;
    }

    public Utilisateur passwordUtilisateur(String passwordUtilisateur) {
        this.passwordUtilisateur = passwordUtilisateur;
        return this;
    }

    public void setPasswordUtilisateur(String passwordUtilisateur) {
        this.passwordUtilisateur = passwordUtilisateur;
    }

    public String getPrenomUtilsateur() {
        return prenomUtilsateur;
    }

    public Utilisateur prenomUtilsateur(String prenomUtilsateur) {
        this.prenomUtilsateur = prenomUtilsateur;
        return this;
    }

    public void setPrenomUtilsateur(String prenomUtilsateur) {
        this.prenomUtilsateur = prenomUtilsateur;
    }

    public Set<Casse> getCasses() {
        return casses;
    }

    public Utilisateur casses(Set<Casse> casses) {
        this.casses = casses;
        return this;
    }

    public Utilisateur addCasse(Casse casse) {
        this.casses.add(casse);
        casse.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeCasse(Casse casse) {
        this.casses.remove(casse);
        casse.setUtilisateur(null);
        return this;
    }

    public void setCasses(Set<Casse> casses) {
        this.casses = casses;
    }

    public Set<Reception> getReceptions() {
        return receptions;
    }

    public Utilisateur receptions(Set<Reception> receptions) {
        this.receptions = receptions;
        return this;
    }

    public Utilisateur addReception(Reception reception) {
        this.receptions.add(reception);
        reception.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeReception(Reception reception) {
        this.receptions.remove(reception);
        reception.setUtilisateur(null);
        return this;
    }

    public void setReceptions(Set<Reception> receptions) {
        this.receptions = receptions;
    }

    public Set<Sortie> getSorties() {
        return sorties;
    }

    public Utilisateur sorties(Set<Sortie> sorties) {
        this.sorties = sorties;
        return this;
    }

    public Utilisateur addSortie(Sortie sortie) {
        this.sorties.add(sortie);
        sortie.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeSortie(Sortie sortie) {
        this.sorties.remove(sortie);
        sortie.setUtilisateur(null);
        return this;
    }

    public void setSorties(Set<Sortie> sorties) {
        this.sorties = sorties;
    }

    public Set<Retour> getRetours() {
        return retours;
    }

    public Utilisateur retours(Set<Retour> retours) {
        this.retours = retours;
        return this;
    }

    public Utilisateur addRetour(Retour retour) {
        this.retours.add(retour);
        retour.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeRetour(Retour retour) {
        this.retours.remove(retour);
        retour.setUtilisateur(null);
        return this;
    }

    public void setRetours(Set<Retour> retours) {
        this.retours = retours;
    }

    public Set<Recuperation> getRecuperations() {
        return recuperations;
    }

    public Utilisateur recuperations(Set<Recuperation> recuperations) {
        this.recuperations = recuperations;
        return this;
    }

    public Utilisateur addRecuperation(Recuperation recuperation) {
        this.recuperations.add(recuperation);
        recuperation.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeRecuperation(Recuperation recuperation) {
        this.recuperations.remove(recuperation);
        recuperation.setUtilisateur(null);
        return this;
    }

    public void setRecuperations(Set<Recuperation> recuperations) {
        this.recuperations = recuperations;
    }

    public Set<UtilisateurProfil> getUtilisateurProfils() {
        return utilisateurProfils;
    }

    public Utilisateur utilisateurProfils(Set<UtilisateurProfil> utilisateurProfils) {
        this.utilisateurProfils = utilisateurProfils;
        return this;
    }

    public Utilisateur addUtilisateurProfil(UtilisateurProfil utilisateurProfil) {
        this.utilisateurProfils.add(utilisateurProfil);
        utilisateurProfil.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeUtilisateurProfil(UtilisateurProfil utilisateurProfil) {
        this.utilisateurProfils.remove(utilisateurProfil);
        utilisateurProfil.setUtilisateur(null);
        return this;
    }

    public void setUtilisateurProfils(Set<UtilisateurProfil> utilisateurProfils) {
        this.utilisateurProfils = utilisateurProfils;
    }

    public Set<UtilisateurDepot> getUtilisateurDepots() {
        return utilisateurDepots;
    }

    public Utilisateur utilisateurDepots(Set<UtilisateurDepot> utilisateurDepots) {
        this.utilisateurDepots = utilisateurDepots;
        return this;
    }

    public Utilisateur addUtilisateurDepot(UtilisateurDepot utilisateurDepot) {
        this.utilisateurDepots.add(utilisateurDepot);
        utilisateurDepot.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeUtilisateurDepot(UtilisateurDepot utilisateurDepot) {
        this.utilisateurDepots.remove(utilisateurDepot);
        utilisateurDepot.setUtilisateur(null);
        return this;
    }

    public void setUtilisateurDepots(Set<UtilisateurDepot> utilisateurDepots) {
        this.utilisateurDepots = utilisateurDepots;
    }

    public Set<Transfert> getTransferts() {
        return transferts;
    }

    public Utilisateur transferts(Set<Transfert> transferts) {
        this.transferts = transferts;
        return this;
    }

    public Utilisateur addTransfert(Transfert transfert) {
        this.transferts.add(transfert);
        transfert.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeTransfert(Transfert transfert) {
        this.transferts.remove(transfert);
        transfert.setUtilisateur(null);
        return this;
    }

    public void setTransferts(Set<Transfert> transferts) {
        this.transferts = transferts;
    }

    public Set<StockReception> getStockReceptions() {
        return stockReceptions;
    }

    public Utilisateur stockReceptions(Set<StockReception> stockReceptions) {
        this.stockReceptions = stockReceptions;
        return this;
    }

    public Utilisateur addStockReception(StockReception stockReception) {
        this.stockReceptions.add(stockReception);
        stockReception.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeStockReception(StockReception stockReception) {
        this.stockReceptions.remove(stockReception);
        stockReception.setUtilisateur(null);
        return this;
    }

    public void setStockReceptions(Set<StockReception> stockReceptions) {
        this.stockReceptions = stockReceptions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Utilisateur utilisateur = (Utilisateur) o;
        if (utilisateur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), utilisateur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
            "id=" + getId() +
            ", idUtilisateur=" + getIdUtilisateur() +
            ", loginUtilisateur='" + getLoginUtilisateur() + "'" +
            ", matriculeUtilisateur='" + getMatriculeUtilisateur() + "'" +
            ", nomUtilsateur='" + getNomUtilsateur() + "'" +
            ", passwordUtilisateur='" + getPasswordUtilisateur() + "'" +
            ", prenomUtilsateur='" + getPrenomUtilsateur() + "'" +
            "}";
    }
}
