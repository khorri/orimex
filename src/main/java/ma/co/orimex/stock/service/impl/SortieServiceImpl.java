package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.SortieService;
import ma.co.orimex.stock.domain.Sortie;
import ma.co.orimex.stock.repository.SortieRepository;
import ma.co.orimex.stock.repository.search.SortieSearchRepository;
import ma.co.orimex.stock.service.dto.SortieDTO;
import ma.co.orimex.stock.service.mapper.SortieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Sortie.
 */
@Service
@Transactional
public class SortieServiceImpl implements SortieService {

    private final Logger log = LoggerFactory.getLogger(SortieServiceImpl.class);

    private final SortieRepository sortieRepository;

    private final SortieMapper sortieMapper;

    private final SortieSearchRepository sortieSearchRepository;

    public SortieServiceImpl(SortieRepository sortieRepository, SortieMapper sortieMapper, SortieSearchRepository sortieSearchRepository) {
        this.sortieRepository = sortieRepository;
        this.sortieMapper = sortieMapper;
        this.sortieSearchRepository = sortieSearchRepository;
    }

    /**
     * Save a sortie.
     *
     * @param sortieDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SortieDTO save(SortieDTO sortieDTO) {
        log.debug("Request to save Sortie : {}", sortieDTO);

        Sortie sortie = sortieMapper.toEntity(sortieDTO);
        sortie = sortieRepository.save(sortie);
        SortieDTO result = sortieMapper.toDto(sortie);
        sortieSearchRepository.save(sortie);
        return result;
    }

    /**
     * Get all the sorties.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SortieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sorties");
        return sortieRepository.findAll(pageable)
            .map(sortieMapper::toDto);
    }


    /**
     * Get one sortie by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SortieDTO> findOne(Long id) {
        log.debug("Request to get Sortie : {}", id);
        return sortieRepository.findById(id)
            .map(sortieMapper::toDto);
    }

    /**
     * Delete the sortie by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sortie : {}", id);
        sortieRepository.deleteById(id);
        sortieSearchRepository.deleteById(id);
    }

    /**
     * Search for the sortie corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SortieDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Sorties for query {}", query);
        return sortieSearchRepository.search(queryStringQuery(query), pageable)
            .map(sortieMapper::toDto);
    }
}
