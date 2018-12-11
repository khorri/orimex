package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.RecuperationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Recuperation and its DTO RecuperationDTO.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class, DepotMapper.class, UtilisateurMapper.class})
public interface RecuperationMapper extends EntityMapper<RecuperationDTO, Recuperation> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "depot.id", target = "depotId")
    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    RecuperationDTO toDto(Recuperation recuperation);

    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "depotId", target = "depot")
    @Mapping(source = "utilisateurId", target = "utilisateur")
    Recuperation toEntity(RecuperationDTO recuperationDTO);

    default Recuperation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Recuperation recuperation = new Recuperation();
        recuperation.setId(id);
        return recuperation;
    }
}
