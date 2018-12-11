package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.VilleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ville and its DTO VilleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VilleMapper extends EntityMapper<VilleDTO, Ville> {


    @Mapping(target = "depots", ignore = true)
    Ville toEntity(VilleDTO villeDTO);

    default Ville fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ville ville = new Ville();
        ville.setId(id);
        return ville;
    }
}
