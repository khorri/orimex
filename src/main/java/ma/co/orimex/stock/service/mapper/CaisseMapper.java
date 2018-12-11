package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.CaisseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Caisse and its DTO CaisseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CaisseMapper extends EntityMapper<CaisseDTO, Caisse> {



    default Caisse fromId(Long id) {
        if (id == null) {
            return null;
        }
        Caisse caisse = new Caisse();
        caisse.setId(id);
        return caisse;
    }
}
