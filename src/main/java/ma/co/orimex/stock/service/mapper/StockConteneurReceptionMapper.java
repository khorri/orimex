package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.StockConteneurReceptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StockConteneurReception and its DTO StockConteneurReceptionDTO.
 */
@Mapper(componentModel = "spring", uses = {StockReceptionMapper.class})
public interface StockConteneurReceptionMapper extends EntityMapper<StockConteneurReceptionDTO, StockConteneurReception> {

    @Mapping(source = "stockReception.id", target = "stockReceptionId")
    StockConteneurReceptionDTO toDto(StockConteneurReception stockConteneurReception);

    @Mapping(source = "stockReceptionId", target = "stockReception")
    @Mapping(target = "stockArticleConteneurReceptions", ignore = true)
    StockConteneurReception toEntity(StockConteneurReceptionDTO stockConteneurReceptionDTO);

    default StockConteneurReception fromId(Long id) {
        if (id == null) {
            return null;
        }
        StockConteneurReception stockConteneurReception = new StockConteneurReception();
        stockConteneurReception.setId(id);
        return stockConteneurReception;
    }
}
