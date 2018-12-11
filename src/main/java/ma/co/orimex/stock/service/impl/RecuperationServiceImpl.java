package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.RecuperationService;
import ma.co.orimex.stock.domain.Recuperation;
import ma.co.orimex.stock.repository.RecuperationRepository;
import ma.co.orimex.stock.repository.search.RecuperationSearchRepository;
import ma.co.orimex.stock.service.dto.RecuperationDTO;
import ma.co.orimex.stock.service.mapper.RecuperationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Recuperation.
 */
@Service
@Transactional
public class RecuperationServiceImpl implements RecuperationService {

    private final Logger log = LoggerFactory.getLogger(RecuperationServiceImpl.class);

    private final RecuperationRepository recuperationRepository;

    private final RecuperationMapper recuperationMapper;

    private final RecuperationSearchRepository recuperationSearchRepository;

    public RecuperationServiceImpl(RecuperationRepository recuperationRepository, RecuperationMapper recuperationMapper, RecuperationSearchRepository recuperationSearchRepository) {
        this.recuperationRepository = recuperationRepository;
        this.recuperationMapper = recuperationMapper;
        this.recuperationSearchRepository = recuperationSearchRepository;
    }

    /**
     * Save a recuperation.
     *
     * @param recuperationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RecuperationDTO save(RecuperationDTO recuperationDTO) {
        log.debug("Request to save Recuperation : {}", recuperationDTO);

        Recuperation recuperation = recuperationMapper.toEntity(recuperationDTO);
        recuperation = recuperationRepository.save(recuperation);
        RecuperationDTO result = recuperationMapper.toDto(recuperation);
        recuperationSearchRepository.save(recuperation);
        return result;
    }

    /**
     * Get all the recuperations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RecuperationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Recuperations");
        return recuperationRepository.findAll(pageable)
            .map(recuperationMapper::toDto);
    }


    /**
     * Get one recuperation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RecuperationDTO> findOne(Long id) {
        log.debug("Request to get Recuperation : {}", id);
        return recuperationRepository.findById(id)
            .map(recuperationMapper::toDto);
    }

    /**
     * Delete the recuperation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Recuperation : {}", id);
        recuperationRepository.deleteById(id);
        recuperationSearchRepository.deleteById(id);
    }

    /**
     * Search for the recuperation corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RecuperationDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Recuperations for query {}", query);
        return recuperationSearchRepository.search(queryStringQuery(query), pageable)
            .map(recuperationMapper::toDto);
    }
}
