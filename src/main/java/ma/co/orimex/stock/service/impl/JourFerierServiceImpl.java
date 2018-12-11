package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.JourFerierService;
import ma.co.orimex.stock.domain.JourFerier;
import ma.co.orimex.stock.repository.JourFerierRepository;
import ma.co.orimex.stock.repository.search.JourFerierSearchRepository;
import ma.co.orimex.stock.service.dto.JourFerierDTO;
import ma.co.orimex.stock.service.mapper.JourFerierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing JourFerier.
 */
@Service
@Transactional
public class JourFerierServiceImpl implements JourFerierService {

    private final Logger log = LoggerFactory.getLogger(JourFerierServiceImpl.class);

    private final JourFerierRepository jourFerierRepository;

    private final JourFerierMapper jourFerierMapper;

    private final JourFerierSearchRepository jourFerierSearchRepository;

    public JourFerierServiceImpl(JourFerierRepository jourFerierRepository, JourFerierMapper jourFerierMapper, JourFerierSearchRepository jourFerierSearchRepository) {
        this.jourFerierRepository = jourFerierRepository;
        this.jourFerierMapper = jourFerierMapper;
        this.jourFerierSearchRepository = jourFerierSearchRepository;
    }

    /**
     * Save a jourFerier.
     *
     * @param jourFerierDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JourFerierDTO save(JourFerierDTO jourFerierDTO) {
        log.debug("Request to save JourFerier : {}", jourFerierDTO);

        JourFerier jourFerier = jourFerierMapper.toEntity(jourFerierDTO);
        jourFerier = jourFerierRepository.save(jourFerier);
        JourFerierDTO result = jourFerierMapper.toDto(jourFerier);
        jourFerierSearchRepository.save(jourFerier);
        return result;
    }

    /**
     * Get all the jourFeriers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JourFerierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JourFeriers");
        return jourFerierRepository.findAll(pageable)
            .map(jourFerierMapper::toDto);
    }


    /**
     * Get one jourFerier by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JourFerierDTO> findOne(Long id) {
        log.debug("Request to get JourFerier : {}", id);
        return jourFerierRepository.findById(id)
            .map(jourFerierMapper::toDto);
    }

    /**
     * Delete the jourFerier by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JourFerier : {}", id);
        jourFerierRepository.deleteById(id);
        jourFerierSearchRepository.deleteById(id);
    }

    /**
     * Search for the jourFerier corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JourFerierDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of JourFeriers for query {}", query);
        return jourFerierSearchRepository.search(queryStringQuery(query), pageable)
            .map(jourFerierMapper::toDto);
    }
}
