package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatArticleLigneProformaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatArticleLigneProforma and its DTO AchatArticleLigneProformaDTO.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class, AchatLigneProformaMapper.class})
public interface AchatArticleLigneProformaMapper extends EntityMapper<AchatArticleLigneProformaDTO, AchatArticleLigneProforma> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "achatLigneProforma.id", target = "achatLigneProformaId")
    AchatArticleLigneProformaDTO toDto(AchatArticleLigneProforma achatArticleLigneProforma);

    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "achatLigneProformaId", target = "achatLigneProforma")
    AchatArticleLigneProforma toEntity(AchatArticleLigneProformaDTO achatArticleLigneProformaDTO);

    default AchatArticleLigneProforma fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatArticleLigneProforma achatArticleLigneProforma = new AchatArticleLigneProforma();
        achatArticleLigneProforma.setId(id);
        return achatArticleLigneProforma;
    }
}
