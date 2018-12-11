package ma.co.orimex.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Action.
 */
@Entity
@Table(name = "action")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "action")
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_action")
    private String codeAction;

    @Column(name = "libelle_action")
    private String libelleAction;

    @ManyToOne
    @JsonIgnoreProperties("actions")
    private Menu menu;

    @OneToMany(mappedBy = "action")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProfilAction> profilActions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeAction() {
        return codeAction;
    }

    public Action codeAction(String codeAction) {
        this.codeAction = codeAction;
        return this;
    }

    public void setCodeAction(String codeAction) {
        this.codeAction = codeAction;
    }

    public String getLibelleAction() {
        return libelleAction;
    }

    public Action libelleAction(String libelleAction) {
        this.libelleAction = libelleAction;
        return this;
    }

    public void setLibelleAction(String libelleAction) {
        this.libelleAction = libelleAction;
    }

    public Menu getMenu() {
        return menu;
    }

    public Action menu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Set<ProfilAction> getProfilActions() {
        return profilActions;
    }

    public Action profilActions(Set<ProfilAction> profilActions) {
        this.profilActions = profilActions;
        return this;
    }

    public Action addProfilAction(ProfilAction profilAction) {
        this.profilActions.add(profilAction);
        profilAction.setAction(this);
        return this;
    }

    public Action removeProfilAction(ProfilAction profilAction) {
        this.profilActions.remove(profilAction);
        profilAction.setAction(null);
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
        Action action = (Action) o;
        if (action.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), action.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Action{" +
            "id=" + getId() +
            ", codeAction='" + getCodeAction() + "'" +
            ", libelleAction='" + getLibelleAction() + "'" +
            "}";
    }
}
