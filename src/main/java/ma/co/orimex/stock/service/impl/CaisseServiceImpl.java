package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.CaisseService;
import ma.co.orimex.stock.domain.Caisse;
import ma.co.orimex.stock.repository.CaisseRepository;
import ma.co.orimex.stock.repository.search.CaisseSearchRepository;
import ma.co.orimex.stock.service.dto.CaisseDTO;
import ma.co.orimex.stock.service.mapper.CaisseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Caisse.
 */
@Service
@Transactional
public class CaisseServiceImpl implements CaisseService {

    private final Logger log = LoggerFactory.getLogger(CaisseServiceImpl.class);

    private final CaisseRepository caisseRepository;

    private final CaisseMapper caisseMapper;

    private final CaisseSearchRepository caisseSearchRepository;

    public CaisseServiceImpl(CaisseRepository caisseRepository, CaisseMapper caisseMapper, CaisseSearchRepository caisseSearchRepository) {
        this.caisseRepository = caisseRepository;
        this.caisseMapper = caisseMapper;
        this.caisseSearchRepository = caisseSearchRepository;
    }

    /**
     * Save a caisse.
     *
     * @param caisseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaisseDTO save(CaisseDTO caisseDTO) {
        log.debug("Request to save Caisse : {}", caisseDTO);

        Caisse caisse = caisseMapper.toEntity(caisseDTO);
        caisse = caisseRepository.save(caisse);
        CaisseDTO result = caisseMapper.toDto(caisse);
        caisseSearchRepository.save(caisse);
        return result;
    }

    /**
     * Get all the caisses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaisseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Caisses");
        return caisseRepository.findAll(pageable)
            .map(caisseMapper::toDto);
    }


    /**
     * Get one caisse by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaisseDTO> findOne(Long id) {
        log.debug("Request to get Caisse : {}", id);
        return caisseRepository.findById(id)
            .map(caisseMapper::toDto);
    }

    /**
     * Delete the caisse by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Caisse : {}", id);
        caisseRepository.deleteById(id);
        caisseSearchRepository.deleteById(id);
    }

    /**
     * Search for the caisse corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaisseDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Caisses for query {}", query);
        return caisseSearchRepository.search(queryStringQuery(query), pageable)
            .map(caisseMapper::toDto);
    }
}
