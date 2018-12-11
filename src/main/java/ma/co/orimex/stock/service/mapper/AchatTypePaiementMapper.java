package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatTypePaiementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatTypePaiement and its DTO AchatTypePaiementDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AchatTypePaiementMapper extends EntityMapper<AchatTypePaiementDTO, AchatTypePaiement> {



    default AchatTypePaiement fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatTypePaiement achatTypePaiement = new AchatTypePaiement();
        achatTypePaiement.setId(id);
        return achatTypePaiement;
    }
}
