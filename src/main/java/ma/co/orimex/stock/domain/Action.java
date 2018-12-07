// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:20:41
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Action.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            Menu

@Entity
@Table(name="ACTION")
public class Action
    implements Serializable
{

    public Action()
    {
    }

    public String getCodeAction()
    {
        return codeAction;
    }

    public void setCodeAction(String codeAction)
    {
        this.codeAction = codeAction;
    }

    public String getLibelleAction()
    {
        return libelleAction;
    }

    public void setLibelleAction(String libelleAction)
    {
        this.libelleAction = libelleAction;
    }

    public Menu getMenu()
    {
        return menu;
    }

    public void setMenu(Menu menu)
    {
        this.menu = menu;
    }

    public Set getProfilActions()
    {
        return profilActions;
    }

    public void setProfilActions(Set profilActions)
    {
        this.profilActions = profilActions;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="CODE_ACTION")
    private String codeAction;
    @Column(name="LIBELLE_ACTION")
    private String libelleAction;
    @ManyToOne
    @JoinColumn(name="CODE_MENU")
    private Menu menu;
    @OneToMany(mappedBy="id.action")
    private Set profilActions;
}
