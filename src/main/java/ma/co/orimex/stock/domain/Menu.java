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
 * A Menu.
 */
@Entity
@Table(name = "menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_menu")
    private String codeMenu;

    @Column(name = "cmamps_menu")
    private String cmampsMenu;

    @Column(name = "libelle_menu")
    private String libelleMenu;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "domaine")
    private String domaine;

    @OneToMany(mappedBy = "menu")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Action> actions = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("menus")
    private Menu menu;

    @OneToMany(mappedBy = "menu")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Menu> menus = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeMenu() {
        return codeMenu;
    }

    public Menu codeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
        return this;
    }

    public void setCodeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
    }

    public String getCmampsMenu() {
        return cmampsMenu;
    }

    public Menu cmampsMenu(String cmampsMenu) {
        this.cmampsMenu = cmampsMenu;
        return this;
    }

    public void setCmampsMenu(String cmampsMenu) {
        this.cmampsMenu = cmampsMenu;
    }

    public String getLibelleMenu() {
        return libelleMenu;
    }

    public Menu libelleMenu(String libelleMenu) {
        this.libelleMenu = libelleMenu;
        return this;
    }

    public void setLibelleMenu(String libelleMenu) {
        this.libelleMenu = libelleMenu;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public Menu ordre(Integer ordre) {
        this.ordre = ordre;
        return this;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public String getDomaine() {
        return domaine;
    }

    public Menu domaine(String domaine) {
        this.domaine = domaine;
        return this;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public Menu actions(Set<Action> actions) {
        this.actions = actions;
        return this;
    }

    public Menu addAction(Action action) {
        this.actions.add(action);
        action.setMenu(this);
        return this;
    }

    public Menu removeAction(Action action) {
        this.actions.remove(action);
        action.setMenu(null);
        return this;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public Menu getMenu() {
        return menu;
    }

    public Menu menu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public Menu menus(Set<Menu> menus) {
        this.menus = menus;
        return this;
    }

    public Menu addMenu(Menu menu) {
        this.menus.add(menu);
        menu.setMenu(this);
        return this;
    }

    public Menu removeMenu(Menu menu) {
        this.menus.remove(menu);
        menu.setMenu(null);
        return this;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
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
        Menu menu = (Menu) o;
        if (menu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id=" + getId() +
            ", codeMenu='" + getCodeMenu() + "'" +
            ", cmampsMenu='" + getCmampsMenu() + "'" +
            ", libelleMenu='" + getLibelleMenu() + "'" +
            ", ordre=" + getOrdre() +
            ", domaine='" + getDomaine() + "'" +
            "}";
    }
}
