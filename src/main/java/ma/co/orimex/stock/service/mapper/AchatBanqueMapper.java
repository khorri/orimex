package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatBanqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatBanque and its DTO AchatBanqueDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AchatBanqueMapper extends EntityMapper<AchatBanqueDTO, AchatBanque> {


    @Mapping(target = "achatDossiers", ignore = true)
    AchatBanque toEntity(AchatBanqueDTO achatBanqueDTO);

    default AchatBanque fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatBanque achatBanque = new AchatBanque();
        achatBanque.setId(id);
        return achatBanque;
    }
}
