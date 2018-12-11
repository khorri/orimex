package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatFactureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatFacture and its DTO AchatFactureDTO.
 */
@Mapper(componentModel = "spring", uses = {AchatArrivageMapper.class})
public interface AchatFactureMapper extends EntityMapper<AchatFactureDTO, AchatFacture> {

    @Mapping(source = "achatArrivage.id", target = "achatArrivageId")
    AchatFactureDTO toDto(AchatFacture achatFacture);

    @Mapping(source = "achatArrivageId", target = "achatArrivage")
    @Mapping(target = "achatConteneurArrivages", ignore = true)
    AchatFacture toEntity(AchatFactureDTO achatFactureDTO);

    default AchatFacture fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatFacture achatFacture = new AchatFacture();
        achatFacture.setId(id);
        return achatFacture;
    }
}
