// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:23:12
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   ProfilAction.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import javax.persistence.*;

// Referenced classes of package ma.co.orimex.stock.entite:
//            ProfilActionPK

@Entity
@Table(name="PROFIL_ACTION")
public class ProfilAction
    implements Serializable
{

    public ProfilAction()
    {
    }

    public ProfilActionPK getId()
    {
        return id;
    }

    public void setId(ProfilActionPK id)
    {
        this.id = id;
    }

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private ProfilActionPK id;
}
