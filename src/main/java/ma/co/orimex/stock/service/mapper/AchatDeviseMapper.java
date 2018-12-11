package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatDeviseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatDevise and its DTO AchatDeviseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AchatDeviseMapper extends EntityMapper<AchatDeviseDTO, AchatDevise> {


    @Mapping(target = "achatDossiers", ignore = true)
    AchatDevise toEntity(AchatDeviseDTO achatDeviseDTO);

    default AchatDevise fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatDevise achatDevise = new AchatDevise();
        achatDevise.setId(id);
        return achatDevise;
    }
}
