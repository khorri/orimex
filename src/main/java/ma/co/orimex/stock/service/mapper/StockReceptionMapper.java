package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.StockReceptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StockReception and its DTO StockReceptionDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface StockReceptionMapper extends EntityMapper<StockReceptionDTO, StockReception> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    StockReceptionDTO toDto(StockReception stockReception);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    StockReception toEntity(StockReceptionDTO stockReceptionDTO);

    default StockReception fromId(Long id) {
        if (id == null) {
            return null;
        }
        StockReception stockReception = new StockReception();
        stockReception.setId(id);
        return stockReception;
    }
}
