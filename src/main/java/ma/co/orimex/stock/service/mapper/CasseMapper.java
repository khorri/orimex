package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.CasseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Casse and its DTO CasseDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, CaisseMapper.class})
public interface CasseMapper extends EntityMapper<CasseDTO, Casse> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "caisse.id", target = "caisseId")
    CasseDTO toDto(Casse casse);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "caisseId", target = "caisse")
    Casse toEntity(CasseDTO casseDTO);

    default Casse fromId(Long id) {
        if (id == null) {
            return null;
        }
        Casse casse = new Casse();
        casse.setId(id);
        return casse;
    }
}
