package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatConteneurReceptionService;
import ma.co.orimex.stock.domain.AchatConteneurReception;
import ma.co.orimex.stock.repository.AchatConteneurReceptionRepository;
import ma.co.orimex.stock.repository.search.AchatConteneurReceptionSearchRepository;
import ma.co.orimex.stock.service.dto.AchatConteneurReceptionDTO;
import ma.co.orimex.stock.service.mapper.AchatConteneurReceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatConteneurReception.
 */
@Service
@Transactional
public class AchatConteneurReceptionServiceImpl implements AchatConteneurReceptionService {

    private final Logger log = LoggerFactory.getLogger(AchatConteneurReceptionServiceImpl.class);

    private final AchatConteneurReceptionRepository achatConteneurReceptionRepository;

    private final AchatConteneurReceptionMapper achatConteneurReceptionMapper;

    private final AchatConteneurReceptionSearchRepository achatConteneurReceptionSearchRepository;

    public AchatConteneurReceptionServiceImpl(AchatConteneurReceptionRepository achatConteneurReceptionRepository, AchatConteneurReceptionMapper achatConteneurReceptionMapper, AchatConteneurReceptionSearchRepository achatConteneurReceptionSearchRepository) {
        this.achatConteneurReceptionRepository = achatConteneurReceptionRepository;
        this.achatConteneurReceptionMapper = achatConteneurReceptionMapper;
        this.achatConteneurReceptionSearchRepository = achatConteneurReceptionSearchRepository;
    }

    /**
     * Save a achatConteneurReception.
     *
     * @param achatConteneurReceptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatConteneurReceptionDTO save(AchatConteneurReceptionDTO achatConteneurReceptionDTO) {
        log.debug("Request to save AchatConteneurReception : {}", achatConteneurReceptionDTO);

        AchatConteneurReception achatConteneurReception = achatConteneurReceptionMapper.toEntity(achatConteneurReceptionDTO);
        achatConteneurReception = achatConteneurReceptionRepository.save(achatConteneurReception);
        AchatConteneurReceptionDTO result = achatConteneurReceptionMapper.toDto(achatConteneurReception);
        achatConteneurReceptionSearchRepository.save(achatConteneurReception);
        return result;
    }

    /**
     * Get all the achatConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatConteneurReceptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatConteneurReceptions");
        return achatConteneurReceptionRepository.findAll(pageable)
            .map(achatConteneurReceptionMapper::toDto);
    }


    /**
     * Get one achatConteneurReception by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatConteneurReceptionDTO> findOne(Long id) {
        log.debug("Request to get AchatConteneurReception : {}", id);
        return achatConteneurReceptionRepository.findById(id)
            .map(achatConteneurReceptionMapper::toDto);
    }

    /**
     * Delete the achatConteneurReception by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatConteneurReception : {}", id);
        achatConteneurReceptionRepository.deleteById(id);
        achatConteneurReceptionSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatConteneurReception corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatConteneurReceptionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatConteneurReceptions for query {}", query);
        return achatConteneurReceptionSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatConteneurReceptionMapper::toDto);
    }
}
