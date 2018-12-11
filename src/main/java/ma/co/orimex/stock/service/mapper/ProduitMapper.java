package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.ProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Produit and its DTO ProduitDTO.
 */
@Mapper(componentModel = "spring", uses = {CouleurMapper.class, FamilleProduitMapper.class, OrigineMapper.class})
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {

    @Mapping(source = "couleur.id", target = "couleurId")
    @Mapping(source = "familleProduit.id", target = "familleProduitId")
    @Mapping(source = "origine.id", target = "origineId")
    ProduitDTO toDto(Produit produit);

    @Mapping(target = "recuperations", ignore = true)
    @Mapping(target = "retours", ignore = true)
    @Mapping(target = "achatArticleLigneProformas", ignore = true)
    @Mapping(source = "couleurId", target = "couleur")
    @Mapping(source = "familleProduitId", target = "familleProduit")
    @Mapping(source = "origineId", target = "origine")
    @Mapping(target = "achatArticleConteneurArrivages", ignore = true)
    Produit toEntity(ProduitDTO produitDTO);

    default Produit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(id);
        return produit;
    }
}
