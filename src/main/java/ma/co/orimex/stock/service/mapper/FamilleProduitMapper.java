package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.FamilleProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FamilleProduit and its DTO FamilleProduitDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FamilleProduitMapper extends EntityMapper<FamilleProduitDTO, FamilleProduit> {



    default FamilleProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        FamilleProduit familleProduit = new FamilleProduit();
        familleProduit.setId(id);
        return familleProduit;
    }
}
