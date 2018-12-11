package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.UtilisateurDepotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UtilisateurDepot and its DTO UtilisateurDepotDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface UtilisateurDepotMapper extends EntityMapper<UtilisateurDepotDTO, UtilisateurDepot> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    UtilisateurDepotDTO toDto(UtilisateurDepot utilisateurDepot);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    UtilisateurDepot toEntity(UtilisateurDepotDTO utilisateurDepotDTO);

    default UtilisateurDepot fromId(Long id) {
        if (id == null) {
            return null;
        }
        UtilisateurDepot utilisateurDepot = new UtilisateurDepot();
        utilisateurDepot.setId(id);
        return utilisateurDepot;
    }
}
