package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.CamionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Camion and its DTO CamionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CamionMapper extends EntityMapper<CamionDTO, Camion> {



    default Camion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Camion camion = new Camion();
        camion.setId(id);
        return camion;
    }
}
