package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.StockArticleConteneurReceptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StockArticleConteneurReception and its DTO StockArticleConteneurReceptionDTO.
 */
@Mapper(componentModel = "spring", uses = {AchatArticleConteneurArrivageMapper.class, StockConteneurReceptionMapper.class})
public interface StockArticleConteneurReceptionMapper extends EntityMapper<StockArticleConteneurReceptionDTO, StockArticleConteneurReception> {

    @Mapping(source = "achatArticleConteneurArrivage.id", target = "achatArticleConteneurArrivageId")
    @Mapping(source = "stockConteneurReception.id", target = "stockConteneurReceptionId")
    StockArticleConteneurReceptionDTO toDto(StockArticleConteneurReception stockArticleConteneurReception);

    @Mapping(source = "achatArticleConteneurArrivageId", target = "achatArticleConteneurArrivage")
    @Mapping(source = "stockConteneurReceptionId", target = "stockConteneurReception")
    StockArticleConteneurReception toEntity(StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO);

    default StockArticleConteneurReception fromId(Long id) {
        if (id == null) {
            return null;
        }
        StockArticleConteneurReception stockArticleConteneurReception = new StockArticleConteneurReception();
        stockArticleConteneurReception.setId(id);
        return stockArticleConteneurReception;
    }
}
