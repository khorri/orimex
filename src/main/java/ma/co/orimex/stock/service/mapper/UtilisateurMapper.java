package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.UtilisateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Utilisateur and its DTO UtilisateurDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UtilisateurMapper extends EntityMapper<UtilisateurDTO, Utilisateur> {


    @Mapping(target = "casses", ignore = true)
    @Mapping(target = "receptions", ignore = true)
    @Mapping(target = "sorties", ignore = true)
    @Mapping(target = "retours", ignore = true)
    @Mapping(target = "recuperations", ignore = true)
    @Mapping(target = "utilisateurProfils", ignore = true)
    @Mapping(target = "utilisateurDepots", ignore = true)
    @Mapping(target = "transferts", ignore = true)
    @Mapping(target = "stockReceptions", ignore = true)
    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);

    default Utilisateur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(id);
        return utilisateur;
    }
}
