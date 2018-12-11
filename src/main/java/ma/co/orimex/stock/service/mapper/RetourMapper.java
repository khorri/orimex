package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.RetourDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Retour and its DTO RetourDTO.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class, UtilisateurMapper.class, BonMapper.class})
public interface RetourMapper extends EntityMapper<RetourDTO, Retour> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "bon.id", target = "bonId")
    RetourDTO toDto(Retour retour);

    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "bonId", target = "bon")
    Retour toEntity(RetourDTO retourDTO);

    default Retour fromId(Long id) {
        if (id == null) {
            return null;
        }
        Retour retour = new Retour();
        retour.setId(id);
        return retour;
    }
}
