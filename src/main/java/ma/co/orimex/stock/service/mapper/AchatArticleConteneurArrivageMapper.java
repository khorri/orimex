package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatArticleConteneurArrivageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatArticleConteneurArrivage and its DTO AchatArticleConteneurArrivageDTO.
 */
@Mapper(componentModel = "spring", uses = {AchatConteneurArrivageMapper.class, ProduitMapper.class})
public interface AchatArticleConteneurArrivageMapper extends EntityMapper<AchatArticleConteneurArrivageDTO, AchatArticleConteneurArrivage> {

    @Mapping(source = "achatConteneurArrivage.id", target = "achatConteneurArrivageId")
    @Mapping(source = "produit.id", target = "produitId")
    AchatArticleConteneurArrivageDTO toDto(AchatArticleConteneurArrivage achatArticleConteneurArrivage);

    @Mapping(source = "achatConteneurArrivageId", target = "achatConteneurArrivage")
    @Mapping(source = "produitId", target = "produit")
    AchatArticleConteneurArrivage toEntity(AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO);

    default AchatArticleConteneurArrivage fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatArticleConteneurArrivage achatArticleConteneurArrivage = new AchatArticleConteneurArrivage();
        achatArticleConteneurArrivage.setId(id);
        return achatArticleConteneurArrivage;
    }
}
