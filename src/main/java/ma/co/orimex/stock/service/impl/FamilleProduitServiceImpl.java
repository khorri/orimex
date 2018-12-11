package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.FamilleProduitService;
import ma.co.orimex.stock.domain.FamilleProduit;
import ma.co.orimex.stock.repository.FamilleProduitRepository;
import ma.co.orimex.stock.repository.search.FamilleProduitSearchRepository;
import ma.co.orimex.stock.service.dto.FamilleProduitDTO;
import ma.co.orimex.stock.service.mapper.FamilleProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing FamilleProduit.
 */
@Service
@Transactional
public class FamilleProduitServiceImpl implements FamilleProduitService {

    private final Logger log = LoggerFactory.getLogger(FamilleProduitServiceImpl.class);

    private final FamilleProduitRepository familleProduitRepository;

    private final FamilleProduitMapper familleProduitMapper;

    private final FamilleProduitSearchRepository familleProduitSearchRepository;

    public FamilleProduitServiceImpl(FamilleProduitRepository familleProduitRepository, FamilleProduitMapper familleProduitMapper, FamilleProduitSearchRepository familleProduitSearchRepository) {
        this.familleProduitRepository = familleProduitRepository;
        this.familleProduitMapper = familleProduitMapper;
        this.familleProduitSearchRepository = familleProduitSearchRepository;
    }

    /**
     * Save a familleProduit.
     *
     * @param familleProduitDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FamilleProduitDTO save(FamilleProduitDTO familleProduitDTO) {
        log.debug("Request to save FamilleProduit : {}", familleProduitDTO);

        FamilleProduit familleProduit = familleProduitMapper.toEntity(familleProduitDTO);
        familleProduit = familleProduitRepository.save(familleProduit);
        FamilleProduitDTO result = familleProduitMapper.toDto(familleProduit);
        familleProduitSearchRepository.save(familleProduit);
        return result;
    }

    /**
     * Get all the familleProduits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FamilleProduitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FamilleProduits");
        return familleProduitRepository.findAll(pageable)
            .map(familleProduitMapper::toDto);
    }


    /**
     * Get one familleProduit by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FamilleProduitDTO> findOne(Long id) {
        log.debug("Request to get FamilleProduit : {}", id);
        return familleProduitRepository.findById(id)
            .map(familleProduitMapper::toDto);
    }

    /**
     * Delete the familleProduit by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FamilleProduit : {}", id);
        familleProduitRepository.deleteById(id);
        familleProduitSearchRepository.deleteById(id);
    }

    /**
     * Search for the familleProduit corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FamilleProduitDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of FamilleProduits for query {}", query);
        return familleProduitSearchRepository.search(queryStringQuery(query), pageable)
            .map(familleProduitMapper::toDto);
    }
}
