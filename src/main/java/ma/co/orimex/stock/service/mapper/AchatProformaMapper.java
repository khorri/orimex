package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatProformaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatProforma and its DTO AchatProformaDTO.
 */
@Mapper(componentModel = "spring", uses = {AchatDossierMapper.class})
public interface AchatProformaMapper extends EntityMapper<AchatProformaDTO, AchatProforma> {

    @Mapping(source = "achatDossier.id", target = "achatDossierId")
    AchatProformaDTO toDto(AchatProforma achatProforma);

    @Mapping(target = "achatLigneProformas", ignore = true)
    @Mapping(source = "achatDossierId", target = "achatDossier")
    AchatProforma toEntity(AchatProformaDTO achatProformaDTO);

    default AchatProforma fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatProforma achatProforma = new AchatProforma();
        achatProforma.setId(id);
        return achatProforma;
    }
}
