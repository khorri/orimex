package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatFournisseurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatFournisseur and its DTO AchatFournisseurDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AchatFournisseurMapper extends EntityMapper<AchatFournisseurDTO, AchatFournisseur> {



    default AchatFournisseur fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatFournisseur achatFournisseur = new AchatFournisseur();
        achatFournisseur.setId(id);
        return achatFournisseur;
    }
}
