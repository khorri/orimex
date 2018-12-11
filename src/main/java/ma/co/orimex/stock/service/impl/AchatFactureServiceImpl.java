package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatFactureService;
import ma.co.orimex.stock.domain.AchatFacture;
import ma.co.orimex.stock.repository.AchatFactureRepository;
import ma.co.orimex.stock.repository.search.AchatFactureSearchRepository;
import ma.co.orimex.stock.service.dto.AchatFactureDTO;
import ma.co.orimex.stock.service.mapper.AchatFactureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatFacture.
 */
@Service
@Transactional
public class AchatFactureServiceImpl implements AchatFactureService {

    private final Logger log = LoggerFactory.getLogger(AchatFactureServiceImpl.class);

    private final AchatFactureRepository achatFactureRepository;

    private final AchatFactureMapper achatFactureMapper;

    private final AchatFactureSearchRepository achatFactureSearchRepository;

    public AchatFactureServiceImpl(AchatFactureRepository achatFactureRepository, AchatFactureMapper achatFactureMapper, AchatFactureSearchRepository achatFactureSearchRepository) {
        this.achatFactureRepository = achatFactureRepository;
        this.achatFactureMapper = achatFactureMapper;
        this.achatFactureSearchRepository = achatFactureSearchRepository;
    }

    /**
     * Save a achatFacture.
     *
     * @param achatFactureDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatFactureDTO save(AchatFactureDTO achatFactureDTO) {
        log.debug("Request to save AchatFacture : {}", achatFactureDTO);

        AchatFacture achatFacture = achatFactureMapper.toEntity(achatFactureDTO);
        achatFacture = achatFactureRepository.save(achatFacture);
        AchatFactureDTO result = achatFactureMapper.toDto(achatFacture);
        achatFactureSearchRepository.save(achatFacture);
        return result;
    }

    /**
     * Get all the achatFactures.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatFactureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatFactures");
        return achatFactureRepository.findAll(pageable)
            .map(achatFactureMapper::toDto);
    }


    /**
     * Get one achatFacture by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatFactureDTO> findOne(Long id) {
        log.debug("Request to get AchatFacture : {}", id);
        return achatFactureRepository.findById(id)
            .map(achatFactureMapper::toDto);
    }

    /**
     * Delete the achatFacture by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatFacture : {}", id);
        achatFactureRepository.deleteById(id);
        achatFactureSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatFacture corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatFactureDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatFactures for query {}", query);
        return achatFactureSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatFactureMapper::toDto);
    }
}
