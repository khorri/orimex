package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.ReceptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Reception and its DTO ReceptionDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, BonMapper.class, CaisseMapper.class, CamionMapper.class, ConteneurMapper.class, DepotMapper.class})
public interface ReceptionMapper extends EntityMapper<ReceptionDTO, Reception> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "bon.id", target = "bonId")
    @Mapping(source = "caisse.id", target = "caisseId")
    @Mapping(source = "camion.id", target = "camionId")
    @Mapping(source = "conteneur.id", target = "conteneurId")
    @Mapping(source = "depot.id", target = "depotId")
    ReceptionDTO toDto(Reception reception);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "bonId", target = "bon")
    @Mapping(source = "caisseId", target = "caisse")
    @Mapping(source = "camionId", target = "camion")
    @Mapping(source = "conteneurId", target = "conteneur")
    @Mapping(source = "depotId", target = "depot")
    Reception toEntity(ReceptionDTO receptionDTO);

    default Reception fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reception reception = new Reception();
        reception.setId(id);
        return reception;
    }
}
