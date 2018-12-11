package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.CouleurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Couleur and its DTO CouleurDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CouleurMapper extends EntityMapper<CouleurDTO, Couleur> {



    default Couleur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Couleur couleur = new Couleur();
        couleur.setId(id);
        return couleur;
    }
}
