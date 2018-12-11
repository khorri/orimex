package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatStatutDossierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatStatutDossier and its DTO AchatStatutDossierDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AchatStatutDossierMapper extends EntityMapper<AchatStatutDossierDTO, AchatStatutDossier> {


    @Mapping(target = "achatDossiers", ignore = true)
    AchatStatutDossier toEntity(AchatStatutDossierDTO achatStatutDossierDTO);

    default AchatStatutDossier fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatStatutDossier achatStatutDossier = new AchatStatutDossier();
        achatStatutDossier.setId(id);
        return achatStatutDossier;
    }
}
