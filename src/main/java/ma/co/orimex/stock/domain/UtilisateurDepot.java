// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 06/12/2018 09:27:39
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   UtilisateurDepot.java

package ma.co.orimex.stock.domain;

import java.io.Serializable;
import javax.persistence.*;
// Referenced classes of package ma.co.orimex.stock.entite:
//            UtilisateurDepotPK

@Entity
@Table(name="UTILISATEUR_DEPOT")
public class UtilisateurDepot
    implements Serializable
{

    public UtilisateurDepot()
    {
    }

    public UtilisateurDepotPK getId()
    {
        return id;
    }

    public void setId(UtilisateurDepotPK id)
    {
        this.id = id;
    }

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private UtilisateurDepotPK id;
}
