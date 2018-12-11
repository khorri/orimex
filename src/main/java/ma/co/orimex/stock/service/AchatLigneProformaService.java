package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatLigneProformaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatLigneProforma.
 */
public interface AchatLigneProformaService {

    /**
     * Save a achatLigneProforma.
     *
     * @param achatLigneProformaDTO the entity to save
     * @return the persisted entity
     */
    AchatLigneProformaDTO save(AchatLigneProformaDTO achatLigneProformaDTO);

    /**
     * Get all the achatLigneProformas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatLigneProformaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatLigneProforma.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatLigneProformaDTO> findOne(Long id);

    /**
     * Delete the "id" achatLigneProforma.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatLigneProforma corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatLigneProformaDTO> search(String query, Pageable pageable);
}
