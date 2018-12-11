package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.ConteneurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Conteneur and its DTO ConteneurDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConteneurMapper extends EntityMapper<ConteneurDTO, Conteneur> {



    default Conteneur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Conteneur conteneur = new Conteneur();
        conteneur.setId(id);
        return conteneur;
    }
}
