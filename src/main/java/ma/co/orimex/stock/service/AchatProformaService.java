package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatProformaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatProforma.
 */
public interface AchatProformaService {

    /**
     * Save a achatProforma.
     *
     * @param achatProformaDTO the entity to save
     * @return the persisted entity
     */
    AchatProformaDTO save(AchatProformaDTO achatProformaDTO);

    /**
     * Get all the achatProformas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatProformaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatProforma.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatProformaDTO> findOne(Long id);

    /**
     * Delete the "id" achatProforma.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatProforma corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatProformaDTO> search(String query, Pageable pageable);
}
