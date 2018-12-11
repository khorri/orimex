package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.TypeBonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeBon and its DTO TypeBonDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeBonMapper extends EntityMapper<TypeBonDTO, TypeBon> {



    default TypeBon fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeBon typeBon = new TypeBon();
        typeBon.setId(id);
        return typeBon;
    }
}
