package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.UtilisateurProfilDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UtilisateurProfil and its DTO UtilisateurProfilDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, ProfilMapper.class})
public interface UtilisateurProfilMapper extends EntityMapper<UtilisateurProfilDTO, UtilisateurProfil> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "profil.id", target = "profilId")
    UtilisateurProfilDTO toDto(UtilisateurProfil utilisateurProfil);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "profilId", target = "profil")
    UtilisateurProfil toEntity(UtilisateurProfilDTO utilisateurProfilDTO);

    default UtilisateurProfil fromId(Long id) {
        if (id == null) {
            return null;
        }
        UtilisateurProfil utilisateurProfil = new UtilisateurProfil();
        utilisateurProfil.setId(id);
        return utilisateurProfil;
    }
}
