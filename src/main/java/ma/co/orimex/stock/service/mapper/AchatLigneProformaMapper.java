package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatLigneProformaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatLigneProforma and its DTO AchatLigneProformaDTO.
 */
@Mapper(componentModel = "spring", uses = {AchatProformaMapper.class})
public interface AchatLigneProformaMapper extends EntityMapper<AchatLigneProformaDTO, AchatLigneProforma> {

    @Mapping(source = "achatProforma.id", target = "achatProformaId")
    AchatLigneProformaDTO toDto(AchatLigneProforma achatLigneProforma);

    @Mapping(source = "achatProformaId", target = "achatProforma")
    AchatLigneProforma toEntity(AchatLigneProformaDTO achatLigneProformaDTO);

    default AchatLigneProforma fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatLigneProforma achatLigneProforma = new AchatLigneProforma();
        achatLigneProforma.setId(id);
        return achatLigneProforma;
    }
}
