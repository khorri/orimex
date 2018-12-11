package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatArticleConteneurReceptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatArticleConteneurReception and its DTO AchatArticleConteneurReceptionDTO.
 */
@Mapper(componentModel = "spring", uses = {AchatConteneurReceptionMapper.class, ProduitMapper.class})
public interface AchatArticleConteneurReceptionMapper extends EntityMapper<AchatArticleConteneurReceptionDTO, AchatArticleConteneurReception> {

    @Mapping(source = "achatConteneurReception.id", target = "achatConteneurReceptionId")
    @Mapping(source = "produit.id", target = "produitId")
    AchatArticleConteneurReceptionDTO toDto(AchatArticleConteneurReception achatArticleConteneurReception);

    @Mapping(source = "achatConteneurReceptionId", target = "achatConteneurReception")
    @Mapping(source = "produitId", target = "produit")
    AchatArticleConteneurReception toEntity(AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO);

    default AchatArticleConteneurReception fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatArticleConteneurReception achatArticleConteneurReception = new AchatArticleConteneurReception();
        achatArticleConteneurReception.setId(id);
        return achatArticleConteneurReception;
    }
}
