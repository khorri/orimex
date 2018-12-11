package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.ReceptionService;
import ma.co.orimex.stock.domain.Reception;
import ma.co.orimex.stock.repository.ReceptionRepository;
import ma.co.orimex.stock.repository.search.ReceptionSearchRepository;
import ma.co.orimex.stock.service.dto.ReceptionDTO;
import ma.co.orimex.stock.service.mapper.ReceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Reception.
 */
@Service
@Transactional
public class ReceptionServiceImpl implements ReceptionService {

    private final Logger log = LoggerFactory.getLogger(ReceptionServiceImpl.class);

    private final ReceptionRepository receptionRepository;

    private final ReceptionMapper receptionMapper;

    private final ReceptionSearchRepository receptionSearchRepository;

    public ReceptionServiceImpl(ReceptionRepository receptionRepository, ReceptionMapper receptionMapper, ReceptionSearchRepository receptionSearchRepository) {
        this.receptionRepository = receptionRepository;
        this.receptionMapper = receptionMapper;
        this.receptionSearchRepository = receptionSearchRepository;
    }

    /**
     * Save a reception.
     *
     * @param receptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReceptionDTO save(ReceptionDTO receptionDTO) {
        log.debug("Request to save Reception : {}", receptionDTO);

        Reception reception = receptionMapper.toEntity(receptionDTO);
        reception = receptionRepository.save(reception);
        ReceptionDTO result = receptionMapper.toDto(reception);
        receptionSearchRepository.save(reception);
        return result;
    }

    /**
     * Get all the receptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReceptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Receptions");
        return receptionRepository.findAll(pageable)
            .map(receptionMapper::toDto);
    }


    /**
     * Get one reception by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReceptionDTO> findOne(Long id) {
        log.debug("Request to get Reception : {}", id);
        return receptionRepository.findById(id)
            .map(receptionMapper::toDto);
    }

    /**
     * Delete the reception by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reception : {}", id);
        receptionRepository.deleteById(id);
        receptionSearchRepository.deleteById(id);
    }

    /**
     * Search for the reception corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReceptionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Receptions for query {}", query);
        return receptionSearchRepository.search(queryStringQuery(query), pageable)
            .map(receptionMapper::toDto);
    }
}
