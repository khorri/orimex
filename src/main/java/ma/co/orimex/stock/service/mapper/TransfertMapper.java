package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.TransfertDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Transfert and its DTO TransfertDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, BonMapper.class, CaisseMapper.class, CamionMapper.class, ConteneurMapper.class, DepotMapper.class})
public interface TransfertMapper extends EntityMapper<TransfertDTO, Transfert> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "bon.id", target = "bonId")
    @Mapping(source = "caisse.id", target = "caisseId")
    @Mapping(source = "camion.id", target = "camionId")
    @Mapping(source = "conteneur.id", target = "conteneurId")
    @Mapping(source = "depot.id", target = "depotId")
    TransfertDTO toDto(Transfert transfert);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "bonId", target = "bon")
    @Mapping(source = "caisseId", target = "caisse")
    @Mapping(source = "camionId", target = "camion")
    @Mapping(source = "conteneurId", target = "conteneur")
    @Mapping(source = "depotId", target = "depot")
    Transfert toEntity(TransfertDTO transfertDTO);

    default Transfert fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transfert transfert = new Transfert();
        transfert.setId(id);
        return transfert;
    }
}
