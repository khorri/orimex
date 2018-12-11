package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.OrigineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Origine and its DTO OrigineDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrigineMapper extends EntityMapper<OrigineDTO, Origine> {



    default Origine fromId(Long id) {
        if (id == null) {
            return null;
        }
        Origine origine = new Origine();
        origine.setId(id);
        return origine;
    }
}
