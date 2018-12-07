package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatArticleConteneurArrivageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatArticleConteneurArrivage and its DTO AchatArticleConteneurArrivageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AchatArticleConteneurArrivageMapper extends EntityMapper<AchatArticleConteneurArrivageDTO, AchatArticleConteneurArrivage> {



    default AchatArticleConteneurArrivage fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatArticleConteneurArrivage achatArticleConteneurArrivage = new AchatArticleConteneurArrivage();
        achatArticleConteneurArrivage.setId(id);
        return achatArticleConteneurArrivage;
    }
}
