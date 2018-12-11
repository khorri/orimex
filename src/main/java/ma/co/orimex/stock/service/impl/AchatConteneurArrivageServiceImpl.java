package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatConteneurArrivageService;
import ma.co.orimex.stock.domain.AchatConteneurArrivage;
import ma.co.orimex.stock.repository.AchatConteneurArrivageRepository;
import ma.co.orimex.stock.repository.search.AchatConteneurArrivageSearchRepository;
import ma.co.orimex.stock.service.dto.AchatConteneurArrivageDTO;
import ma.co.orimex.stock.service.mapper.AchatConteneurArrivageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatConteneurArrivage.
 */
@Service
@Transactional
public class AchatConteneurArrivageServiceImpl implements AchatConteneurArrivageService {

    private final Logger log = LoggerFactory.getLogger(AchatConteneurArrivageServiceImpl.class);

    private final AchatConteneurArrivageRepository achatConteneurArrivageRepository;

    private final AchatConteneurArrivageMapper achatConteneurArrivageMapper;

    private final AchatConteneurArrivageSearchRepository achatConteneurArrivageSearchRepository;

    public AchatConteneurArrivageServiceImpl(AchatConteneurArrivageRepository achatConteneurArrivageRepository, AchatConteneurArrivageMapper achatConteneurArrivageMapper, AchatConteneurArrivageSearchRepository achatConteneurArrivageSearchRepository) {
        this.achatConteneurArrivageRepository = achatConteneurArrivageRepository;
        this.achatConteneurArrivageMapper = achatConteneurArrivageMapper;
        this.achatConteneurArrivageSearchRepository = achatConteneurArrivageSearchRepository;
    }

    /**
     * Save a achatConteneurArrivage.
     *
     * @param achatConteneurArrivageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatConteneurArrivageDTO save(AchatConteneurArrivageDTO achatConteneurArrivageDTO) {
        log.debug("Request to save AchatConteneurArrivage : {}", achatConteneurArrivageDTO);

        AchatConteneurArrivage achatConteneurArrivage = achatConteneurArrivageMapper.toEntity(achatConteneurArrivageDTO);
        achatConteneurArrivage = achatConteneurArrivageRepository.save(achatConteneurArrivage);
        AchatConteneurArrivageDTO result = achatConteneurArrivageMapper.toDto(achatConteneurArrivage);
        achatConteneurArrivageSearchRepository.save(achatConteneurArrivage);
        return result;
    }

    /**
     * Get all the achatConteneurArrivages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatConteneurArrivageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatConteneurArrivages");
        return achatConteneurArrivageRepository.findAll(pageable)
            .map(achatConteneurArrivageMapper::toDto);
    }


    /**
     * Get one achatConteneurArrivage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatConteneurArrivageDTO> findOne(Long id) {
        log.debug("Request to get AchatConteneurArrivage : {}", id);
        return achatConteneurArrivageRepository.findById(id)
            .map(achatConteneurArrivageMapper::toDto);
    }

    /**
     * Delete the achatConteneurArrivage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatConteneurArrivage : {}", id);
        achatConteneurArrivageRepository.deleteById(id);
        achatConteneurArrivageSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatConteneurArrivage corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatConteneurArrivageDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatConteneurArrivages for query {}", query);
        return achatConteneurArrivageSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatConteneurArrivageMapper::toDto);
    }
}
