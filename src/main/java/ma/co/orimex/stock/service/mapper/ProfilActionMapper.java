package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.ProfilActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ProfilAction and its DTO ProfilActionDTO.
 */
@Mapper(componentModel = "spring", uses = {ActionMapper.class, ProfilMapper.class})
public interface ProfilActionMapper extends EntityMapper<ProfilActionDTO, ProfilAction> {

    @Mapping(source = "action.id", target = "actionId")
    @Mapping(source = "profil.id", target = "profilId")
    ProfilActionDTO toDto(ProfilAction profilAction);

    @Mapping(source = "actionId", target = "action")
    @Mapping(source = "profilId", target = "profil")
    ProfilAction toEntity(ProfilActionDTO profilActionDTO);

    default ProfilAction fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProfilAction profilAction = new ProfilAction();
        profilAction.setId(id);
        return profilAction;
    }
}
