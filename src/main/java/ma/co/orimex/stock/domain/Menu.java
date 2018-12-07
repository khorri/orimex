// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:22:29
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Menu.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="MENU")
public class Menu
    implements Serializable
{

    public Menu()
    {
    }

    public String getCodeMenu()
    {
        return codeMenu;
    }

    public void setCodeMenu(String codeMenu)
    {
        this.codeMenu = codeMenu;
    }

    public String getCmampsMenu()
    {
        return cmampsMenu;
    }

    public void setCmampsMenu(String cmampsMenu)
    {
        this.cmampsMenu = cmampsMenu;
    }

    public String getLibelleMenu()
    {
        return libelleMenu;
    }

    public void setLibelleMenu(String libelleMenu)
    {
        this.libelleMenu = libelleMenu;
    }

    public int getOrdre()
    {
        return ordre;
    }

    public void setOrdre(int ordre)
    {
        this.ordre = ordre;
    }

    public Menu getMenu()
    {
        return menu;
    }

    public void setMenu(Menu menu)
    {
        this.menu = menu;
    }

    public Set getMenus()
    {
        return menus;
    }

    public void setMenus(Set menus)
    {
        this.menus = menus;
    }

    public String getDomaine()
    {
        return domaine;
    }

    public void setDomaine(String domaine)
    {
        this.domaine = domaine;
    }

    public Set getActions()
    {
        return actions;
    }

    public void setActions(Set actions)
    {
        this.actions = actions;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="CODE_MENU")
    private String codeMenu;
    @Column(name="CMAMPS_MENU")
    private String cmampsMenu;
    @Column(name="LIBELLE_MENU")
    private String libelleMenu;
    @Column(name="ORDRE")
    private int ordre;
    @Column(name="DOMAINE")
    private String domaine;
    @OneToMany(mappedBy="menu")
    private Set actions;
    @ManyToOne
    @JoinColumn(name="CODE_MENU_RECINE")
    private Menu menu;
    @OneToMany(mappedBy="menu")
    private Set menus;
}
