package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.UtilisateurDepotPKDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UtilisateurDepotPK and its DTO UtilisateurDepotPKDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, DepotMapper.class})
public interface UtilisateurDepotPKMapper extends EntityMapper<UtilisateurDepotPKDTO, UtilisateurDepotPK> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "depot.id", target = "depotId")
    UtilisateurDepotPKDTO toDto(UtilisateurDepotPK utilisateurDepotPK);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "depotId", target = "depot")
    UtilisateurDepotPK toEntity(UtilisateurDepotPKDTO utilisateurDepotPKDTO);

    default UtilisateurDepotPK fromId(Long id) {
        if (id == null) {
            return null;
        }
        UtilisateurDepotPK utilisateurDepotPK = new UtilisateurDepotPK();
        utilisateurDepotPK.setId(id);
        return utilisateurDepotPK;
    }
}
