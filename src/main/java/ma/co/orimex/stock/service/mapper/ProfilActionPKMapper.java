package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.ProfilActionPKDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ProfilActionPK and its DTO ProfilActionPKDTO.
 */
@Mapper(componentModel = "spring", uses = {ActionMapper.class, ProfilMapper.class})
public interface ProfilActionPKMapper extends EntityMapper<ProfilActionPKDTO, ProfilActionPK> {

    @Mapping(source = "action.id", target = "actionId")
    @Mapping(source = "profil.id", target = "profilId")
    ProfilActionPKDTO toDto(ProfilActionPK profilActionPK);

    @Mapping(source = "actionId", target = "action")
    @Mapping(source = "profilId", target = "profil")
    ProfilActionPK toEntity(ProfilActionPKDTO profilActionPKDTO);

    default ProfilActionPK fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProfilActionPK profilActionPK = new ProfilActionPK();
        profilActionPK.setId(id);
        return profilActionPK;
    }
}
