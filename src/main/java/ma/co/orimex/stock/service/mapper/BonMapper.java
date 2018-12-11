package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.BonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bon and its DTO BonDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BonMapper extends EntityMapper<BonDTO, Bon> {


    @Mapping(target = "retours", ignore = true)
    Bon toEntity(BonDTO bonDTO);

    default Bon fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bon bon = new Bon();
        bon.setId(id);
        return bon;
    }
}
