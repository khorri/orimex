package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatBanqueDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatBanque.
 */
public interface AchatBanqueService {

    /**
     * Save a achatBanque.
     *
     * @param achatBanqueDTO the entity to save
     * @return the persisted entity
     */
    AchatBanqueDTO save(AchatBanqueDTO achatBanqueDTO);

    /**
     * Get all the achatBanques.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatBanqueDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatBanque.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatBanqueDTO> findOne(Long id);

    /**
     * Delete the "id" achatBanque.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatBanque corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatBanqueDTO> search(String query, Pageable pageable);
}
