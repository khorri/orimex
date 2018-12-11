package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.UtilisateurProfilPKDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UtilisateurProfilPK and its DTO UtilisateurProfilPKDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, ProfilMapper.class})
public interface UtilisateurProfilPKMapper extends EntityMapper<UtilisateurProfilPKDTO, UtilisateurProfilPK> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "profil.id", target = "profilId")
    UtilisateurProfilPKDTO toDto(UtilisateurProfilPK utilisateurProfilPK);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "profilId", target = "profil")
    UtilisateurProfilPK toEntity(UtilisateurProfilPKDTO utilisateurProfilPKDTO);

    default UtilisateurProfilPK fromId(Long id) {
        if (id == null) {
            return null;
        }
        UtilisateurProfilPK utilisateurProfilPK = new UtilisateurProfilPK();
        utilisateurProfilPK.setId(id);
        return utilisateurProfilPK;
    }
}
