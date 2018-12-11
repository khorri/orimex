package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.SortieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Sortie and its DTO SortieDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, BonMapper.class, CaisseMapper.class, DepotMapper.class})
public interface SortieMapper extends EntityMapper<SortieDTO, Sortie> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "bon.id", target = "bonId")
    @Mapping(source = "caisse.id", target = "caisseId")
    @Mapping(source = "depot.id", target = "depotId")
    SortieDTO toDto(Sortie sortie);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "bonId", target = "bon")
    @Mapping(source = "caisseId", target = "caisse")
    @Mapping(source = "depotId", target = "depot")
    Sortie toEntity(SortieDTO sortieDTO);

    default Sortie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sortie sortie = new Sortie();
        sortie.setId(id);
        return sortie;
    }
}
