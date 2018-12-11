package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.DepotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Depot and its DTO DepotDTO.
 */
@Mapper(componentModel = "spring", uses = {VilleMapper.class})
public interface DepotMapper extends EntityMapper<DepotDTO, Depot> {

    @Mapping(source = "ville.id", target = "villeId")
    DepotDTO toDto(Depot depot);

    @Mapping(source = "villeId", target = "ville")
    Depot toEntity(DepotDTO depotDTO);

    default Depot fromId(Long id) {
        if (id == null) {
            return null;
        }
        Depot depot = new Depot();
        depot.setId(id);
        return depot;
    }
}
