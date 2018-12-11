package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.JourFerierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JourFerier and its DTO JourFerierDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JourFerierMapper extends EntityMapper<JourFerierDTO, JourFerier> {



    default JourFerier fromId(Long id) {
        if (id == null) {
            return null;
        }
        JourFerier jourFerier = new JourFerier();
        jourFerier.setId(id);
        return jourFerier;
    }
}
