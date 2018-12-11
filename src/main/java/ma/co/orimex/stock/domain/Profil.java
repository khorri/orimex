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
 * A Profil.
 */
@Entity
@Table(name = "profil")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "profil")
public class Profil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_profil")
    private String codeProfil;

    @Column(name = "description_profil")
    private String descriptionProfil;

    @OneToMany(mappedBy = "profil")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UtilisateurProfil> utilisateurProfils = new HashSet<>();
    @OneToMany(mappedBy = "profil")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProfilAction> profilActions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProfil() {
        return codeProfil;
    }

    public Profil codeProfil(String codeProfil) {
        this.codeProfil = codeProfil;
        return this;
    }

    public void setCodeProfil(String codeProfil) {
        this.codeProfil = codeProfil;
    }

    public String getDescriptionProfil() {
        return descriptionProfil;
    }

    public Profil descriptionProfil(String descriptionProfil) {
        this.descriptionProfil = descriptionProfil;
        return this;
    }

    public void setDescriptionProfil(String descriptionProfil) {
        this.descriptionProfil = descriptionProfil;
    }

    public Set<UtilisateurProfil> getUtilisateurProfils() {
        return utilisateurProfils;
    }

    public Profil utilisateurProfils(Set<UtilisateurProfil> utilisateurProfils) {
        this.utilisateurProfils = utilisateurProfils;
        return this;
    }

    public Profil addUtilisateurProfil(UtilisateurProfil utilisateurProfil) {
        this.utilisateurProfils.add(utilisateurProfil);
        utilisateurProfil.setProfil(this);
        return this;
    }

    public Profil removeUtilisateurProfil(UtilisateurProfil utilisateurProfil) {
        this.utilisateurProfils.remove(utilisateurProfil);
        utilisateurProfil.setProfil(null);
        return this;
    }

    public void setUtilisateurProfils(Set<UtilisateurProfil> utilisateurProfils) {
        this.utilisateurProfils = utilisateurProfils;
    }

    public Set<ProfilAction> getProfilActions() {
        return profilActions;
    }

    public Profil profilActions(Set<ProfilAction> profilActions) {
        this.profilActions = profilActions;
        return this;
    }

    public Profil addProfilAction(ProfilAction profilAction) {
        this.profilActions.add(profilAction);
        profilAction.setProfil(this);
        return this;
    }

    public Profil removeProfilAction(ProfilAction profilAction) {
        this.profilActions.remove(profilAction);
        profilAction.setProfil(null);
        return this;
    }

    public void setProfilActions(Set<ProfilAction> profilActions) {
        this.profilActions = profilActions;
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
        Profil profil = (Profil) o;
        if (profil.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profil.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Profil{" +
            "id=" + getId() +
            ", codeProfil='" + getCodeProfil() + "'" +
            ", descriptionProfil='" + getDescriptionProfil() + "'" +
            "}";
    }
}
