package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatArrivageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatArrivage and its DTO AchatArrivageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AchatArrivageMapper extends EntityMapper<AchatArrivageDTO, AchatArrivage> {



    default AchatArrivage fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatArrivage achatArrivage = new AchatArrivage();
        achatArrivage.setId(id);
        return achatArrivage;
    }
}
