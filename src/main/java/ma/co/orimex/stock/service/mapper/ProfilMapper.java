package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.ProfilDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Profil and its DTO ProfilDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfilMapper extends EntityMapper<ProfilDTO, Profil> {


    @Mapping(target = "utilisateurProfils", ignore = true)
    @Mapping(target = "profilActions", ignore = true)
    Profil toEntity(ProfilDTO profilDTO);

    default Profil fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profil profil = new Profil();
        profil.setId(id);
        return profil;
    }
}
