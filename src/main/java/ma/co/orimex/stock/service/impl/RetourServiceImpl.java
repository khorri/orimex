package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.RetourService;
import ma.co.orimex.stock.domain.Retour;
import ma.co.orimex.stock.repository.RetourRepository;
import ma.co.orimex.stock.repository.search.RetourSearchRepository;
import ma.co.orimex.stock.service.dto.RetourDTO;
import ma.co.orimex.stock.service.mapper.RetourMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Retour.
 */
@Service
@Transactional
public class RetourServiceImpl implements RetourService {

    private final Logger log = LoggerFactory.getLogger(RetourServiceImpl.class);

    private final RetourRepository retourRepository;

    private final RetourMapper retourMapper;

    private final RetourSearchRepository retourSearchRepository;

    public RetourServiceImpl(RetourRepository retourRepository, RetourMapper retourMapper, RetourSearchRepository retourSearchRepository) {
        this.retourRepository = retourRepository;
        this.retourMapper = retourMapper;
        this.retourSearchRepository = retourSearchRepository;
    }

    /**
     * Save a retour.
     *
     * @param retourDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RetourDTO save(RetourDTO retourDTO) {
        log.debug("Request to save Retour : {}", retourDTO);

        Retour retour = retourMapper.toEntity(retourDTO);
        retour = retourRepository.save(retour);
        RetourDTO result = retourMapper.toDto(retour);
        retourSearchRepository.save(retour);
        return result;
    }

    /**
     * Get all the retours.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RetourDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Retours");
        return retourRepository.findAll(pageable)
            .map(retourMapper::toDto);
    }


    /**
     * Get one retour by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RetourDTO> findOne(Long id) {
        log.debug("Request to get Retour : {}", id);
        return retourRepository.findById(id)
            .map(retourMapper::toDto);
    }

    /**
     * Delete the retour by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Retour : {}", id);
        retourRepository.deleteById(id);
        retourSearchRepository.deleteById(id);
    }

    /**
     * Search for the retour corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RetourDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Retours for query {}", query);
        return retourSearchRepository.search(queryStringQuery(query), pageable)
            .map(retourMapper::toDto);
    }
}
