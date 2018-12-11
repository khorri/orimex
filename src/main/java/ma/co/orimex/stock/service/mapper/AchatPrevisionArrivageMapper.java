package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatPrevisionArrivageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatPrevisionArrivage and its DTO AchatPrevisionArrivageDTO.
 */
@Mapper(componentModel = "spring", uses = {AchatDossierMapper.class})
public interface AchatPrevisionArrivageMapper extends EntityMapper<AchatPrevisionArrivageDTO, AchatPrevisionArrivage> {

    @Mapping(source = "achatDossier.id", target = "achatDossierId")
    AchatPrevisionArrivageDTO toDto(AchatPrevisionArrivage achatPrevisionArrivage);

    @Mapping(source = "achatDossierId", target = "achatDossier")
    AchatPrevisionArrivage toEntity(AchatPrevisionArrivageDTO achatPrevisionArrivageDTO);

    default AchatPrevisionArrivage fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatPrevisionArrivage achatPrevisionArrivage = new AchatPrevisionArrivage();
        achatPrevisionArrivage.setId(id);
        return achatPrevisionArrivage;
    }
}
