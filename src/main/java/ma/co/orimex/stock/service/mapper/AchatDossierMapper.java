package ma.co.orimex.stock.service.mapper;

import ma.co.orimex.stock.domain.*;
import ma.co.orimex.stock.service.dto.AchatDossierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AchatDossier and its DTO AchatDossierDTO.
 */
@Mapper(componentModel = "spring", uses = {AchatBanqueMapper.class, AchatTypePaiementMapper.class, AchatDeviseMapper.class, AchatStatutDossierMapper.class})
public interface AchatDossierMapper extends EntityMapper<AchatDossierDTO, AchatDossier> {

    @Mapping(source = "achatBanque.id", target = "achatBanqueId")
    @Mapping(source = "typePaiement.id", target = "typePaiementId")
    @Mapping(source = "achatDevise.id", target = "achatDeviseId")
    @Mapping(source = "achatStatutDossier.id", target = "achatStatutDossierId")
    AchatDossierDTO toDto(AchatDossier achatDossier);

    @Mapping(source = "achatBanqueId", target = "achatBanque")
    @Mapping(source = "typePaiementId", target = "typePaiement")
    @Mapping(source = "achatDeviseId", target = "achatDevise")
    @Mapping(target = "achatPrevisionArrivages", ignore = true)
    @Mapping(source = "achatStatutDossierId", target = "achatStatutDossier")
    AchatDossier toEntity(AchatDossierDTO achatDossierDTO);

    default AchatDossier fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchatDossier achatDossier = new AchatDossier();
        achatDossier.setId(id);
        return achatDossier;
    }
}
