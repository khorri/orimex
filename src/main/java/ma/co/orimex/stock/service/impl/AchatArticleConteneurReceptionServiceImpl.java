package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatArticleConteneurReceptionService;
import ma.co.orimex.stock.domain.AchatArticleConteneurReception;
import ma.co.orimex.stock.repository.AchatArticleConteneurReceptionRepository;
import ma.co.orimex.stock.repository.search.AchatArticleConteneurReceptionSearchRepository;
import ma.co.orimex.stock.service.dto.AchatArticleConteneurReceptionDTO;
import ma.co.orimex.stock.service.mapper.AchatArticleConteneurReceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatArticleConteneurReception.
 */
@Service
@Transactional
public class AchatArticleConteneurReceptionServiceImpl implements AchatArticleConteneurReceptionService {

    private final Logger log = LoggerFactory.getLogger(AchatArticleConteneurReceptionServiceImpl.class);

    private final AchatArticleConteneurReceptionRepository achatArticleConteneurReceptionRepository;

    private final AchatArticleConteneurReceptionMapper achatArticleConteneurReceptionMapper;

    private final AchatArticleConteneurReceptionSearchRepository achatArticleConteneurReceptionSearchRepository;

    public AchatArticleConteneurReceptionServiceImpl(AchatArticleConteneurReceptionRepository achatArticleConteneurReceptionRepository, AchatArticleConteneurReceptionMapper achatArticleConteneurReceptionMapper, AchatArticleConteneurReceptionSearchRepository achatArticleConteneurReceptionSearchRepository) {
        this.achatArticleConteneurReceptionRepository = achatArticleConteneurReceptionRepository;
        this.achatArticleConteneurReceptionMapper = achatArticleConteneurReceptionMapper;
        this.achatArticleConteneurReceptionSearchRepository = achatArticleConteneurReceptionSearchRepository;
    }

    /**
     * Save a achatArticleConteneurReception.
     *
     * @param achatArticleConteneurReceptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatArticleConteneurReceptionDTO save(AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO) {
        log.debug("Request to save AchatArticleConteneurReception : {}", achatArticleConteneurReceptionDTO);

        AchatArticleConteneurReception achatArticleConteneurReception = achatArticleConteneurReceptionMapper.toEntity(achatArticleConteneurReceptionDTO);
        achatArticleConteneurReception = achatArticleConteneurReceptionRepository.save(achatArticleConteneurReception);
        AchatArticleConteneurReceptionDTO result = achatArticleConteneurReceptionMapper.toDto(achatArticleConteneurReception);
        achatArticleConteneurReceptionSearchRepository.save(achatArticleConteneurReception);
        return result;
    }

    /**
     * Get all the achatArticleConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatArticleConteneurReceptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatArticleConteneurReceptions");
        return achatArticleConteneurReceptionRepository.findAll(pageable)
            .map(achatArticleConteneurReceptionMapper::toDto);
    }


    /**
     * Get one achatArticleConteneurReception by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatArticleConteneurReceptionDTO> findOne(Long id) {
        log.debug("Request to get AchatArticleConteneurReception : {}", id);
        return achatArticleConteneurReceptionRepository.findById(id)
            .map(achatArticleConteneurReceptionMapper::toDto);
    }

    /**
     * Delete the achatArticleConteneurReception by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatArticleConteneurReception : {}", id);
        achatArticleConteneurReceptionRepository.deleteById(id);
        achatArticleConteneurReceptionSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatArticleConteneurReception corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatArticleConteneurReceptionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatArticleConteneurReceptions for query {}", query);
        return achatArticleConteneurReceptionSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatArticleConteneurReceptionMapper::toDto);
    }
}
