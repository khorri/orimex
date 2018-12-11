package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatConteneurArrivageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatConteneurArrivage and its DTO AchatConteneurArrivageDTO.
 */
@Mapper(componentModel = "spring", uses = {AchatFactureMapper.class, StockConteneurReceptionMapper.class})
public interface AchatConteneurArrivageMapper extends EntityMapper<AchatConteneurArrivageDTO, AchatConteneurArrivage> {

    @Mapping(source = "achatFacture.id", target = "achatFactureId")
    @Mapping(source = "stockConteneurReception.id", target = "stockConteneurReceptionId")
    AchatConteneurArrivageDTO toDto(AchatConteneurArrivage achatConteneurArrivage);

    @Mapping(source = "achatFactureId", target = "achatFacture")
    @Mapping(source = "stockConteneurReceptionId", target = "stockConteneurReception")
    @Mapping(target = "achatArticleConteneurArrivages", ignore = true)
    AchatConteneurArrivage toEntity(AchatConteneurArrivageDTO achatConteneurArrivageDTO);

    default AchatConteneurArrivage fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatConteneurArrivage achatConteneurArrivage = new AchatConteneurArrivage();
        achatConteneurArrivage.setId(id);
        return achatConteneurArrivage;
    }
}
