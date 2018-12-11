package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatPrevisionArrivageService;
import ma.co.orimex.stock.domain.AchatPrevisionArrivage;
import ma.co.orimex.stock.repository.AchatPrevisionArrivageRepository;
import ma.co.orimex.stock.repository.search.AchatPrevisionArrivageSearchRepository;
import ma.co.orimex.stock.service.dto.AchatPrevisionArrivageDTO;
import ma.co.orimex.stock.service.mapper.AchatPrevisionArrivageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatPrevisionArrivage.
 */
@Service
@Transactional
public class AchatPrevisionArrivageServiceImpl implements AchatPrevisionArrivageService {

    private final Logger log = LoggerFactory.getLogger(AchatPrevisionArrivageServiceImpl.class);

    private final AchatPrevisionArrivageRepository achatPrevisionArrivageRepository;

    private final AchatPrevisionArrivageMapper achatPrevisionArrivageMapper;

    private final AchatPrevisionArrivageSearchRepository achatPrevisionArrivageSearchRepository;

    public AchatPrevisionArrivageServiceImpl(AchatPrevisionArrivageRepository achatPrevisionArrivageRepository, AchatPrevisionArrivageMapper achatPrevisionArrivageMapper, AchatPrevisionArrivageSearchRepository achatPrevisionArrivageSearchRepository) {
        this.achatPrevisionArrivageRepository = achatPrevisionArrivageRepository;
        this.achatPrevisionArrivageMapper = achatPrevisionArrivageMapper;
        this.achatPrevisionArrivageSearchRepository = achatPrevisionArrivageSearchRepository;
    }

    /**
     * Save a achatPrevisionArrivage.
     *
     * @param achatPrevisionArrivageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatPrevisionArrivageDTO save(AchatPrevisionArrivageDTO achatPrevisionArrivageDTO) {
        log.debug("Request to save AchatPrevisionArrivage : {}", achatPrevisionArrivageDTO);

        AchatPrevisionArrivage achatPrevisionArrivage = achatPrevisionArrivageMapper.toEntity(achatPrevisionArrivageDTO);
        achatPrevisionArrivage = achatPrevisionArrivageRepository.save(achatPrevisionArrivage);
        AchatPrevisionArrivageDTO result = achatPrevisionArrivageMapper.toDto(achatPrevisionArrivage);
        achatPrevisionArrivageSearchRepository.save(achatPrevisionArrivage);
        return result;
    }

    /**
     * Get all the achatPrevisionArrivages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatPrevisionArrivageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatPrevisionArrivages");
        return achatPrevisionArrivageRepository.findAll(pageable)
            .map(achatPrevisionArrivageMapper::toDto);
    }


    /**
     * Get one achatPrevisionArrivage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatPrevisionArrivageDTO> findOne(Long id) {
        log.debug("Request to get AchatPrevisionArrivage : {}", id);
        return achatPrevisionArrivageRepository.findById(id)
            .map(achatPrevisionArrivageMapper::toDto);
    }

    /**
     * Delete the achatPrevisionArrivage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatPrevisionArrivage : {}", id);
        achatPrevisionArrivageRepository.deleteById(id);
        achatPrevisionArrivageSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatPrevisionArrivage corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatPrevisionArrivageDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatPrevisionArrivages for query {}", query);
        return achatPrevisionArrivageSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatPrevisionArrivageMapper::toDto);
    }
}
