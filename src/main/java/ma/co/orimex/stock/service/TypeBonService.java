package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.TypeBonDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TypeBon.
 */
public interface TypeBonService {

    /**
     * Save a typeBon.
     *
     * @param typeBonDTO the entity to save
     * @return the persisted entity
     */
    TypeBonDTO save(TypeBonDTO typeBonDTO);

    /**
     * Get all the typeBons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TypeBonDTO> findAll(Pageable pageable);


    /**
     * Get the "id" typeBon.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeBonDTO> findOne(Long id);

    /**
     * Delete the "id" typeBon.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the typeBon corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TypeBonDTO> search(String query, Pageable pageable);
}
