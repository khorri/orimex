package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatConteneurReceptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatConteneurReception and its DTO AchatConteneurReceptionDTO.
 */
@Mapper(componentModel = "spring", uses = {AchatConteneurArrivageMapper.class})
public interface AchatConteneurReceptionMapper extends EntityMapper<AchatConteneurReceptionDTO, AchatConteneurReception> {

    @Mapping(source = "achatConteneurArrivage.id", target = "achatConteneurArrivageId")
    AchatConteneurReceptionDTO toDto(AchatConteneurReception achatConteneurReception);

    @Mapping(source = "achatConteneurArrivageId", target = "achatConteneurArrivage")
    AchatConteneurReception toEntity(AchatConteneurReceptionDTO achatConteneurReceptionDTO);

    default AchatConteneurReception fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatConteneurReception achatConteneurReception = new AchatConteneurReception();
        achatConteneurReception.setId(id);
        return achatConteneurReception;
    }
}
