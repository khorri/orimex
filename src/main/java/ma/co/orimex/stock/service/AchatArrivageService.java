package ma.co.orimex.stock.service;

import ma.co.orimex.stock.domain.AchatArrivage;
import ma.co.orimex.stock.repository.AchatArrivageRepository;
import ma.co.orimex.stock.repository.search.AchatArrivageSearchRepository;
import ma.co.orimex.stock.service.dto.AchatArrivageDTO;
import ma.co.orimex.stock.service.mapper.AchatArrivageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatArrivage.
 */
@Service
@Transactional
public class AchatArrivageService {

    private final Logger log = LoggerFactory.getLogger(AchatArrivageService.class);

    private final AchatArrivageRepository achatArrivageRepository;

    private final AchatArrivageMapper achatArrivageMapper;

    private final AchatArrivageSearchRepository achatArrivageSearchRepository;

    public AchatArrivageService(AchatArrivageRepository achatArrivageRepository, AchatArrivageMapper achatArrivageMapper, AchatArrivageSearchRepository achatArrivageSearchRepository) {
        this.achatArrivageRepository = achatArrivageRepository;
        this.achatArrivageMapper = achatArrivageMapper;
        this.achatArrivageSearchRepository = achatArrivageSearchRepository;
    }

    /**
     * Save a achatArrivage.
     *
     * @param achatArrivageDTO the entity to save
     * @return the persisted entity
     */
    public AchatArrivageDTO save(AchatArrivageDTO achatArrivageDTO) {
        log.debug("Request to save AchatArrivage : {}", achatArrivageDTO);

        AchatArrivage achatArrivage = achatArrivageMapper.toEntity(achatArrivageDTO);
        achatArrivage = achatArrivageRepository.save(achatArrivage);
        AchatArrivageDTO result = achatArrivageMapper.toDto(achatArrivage);
        achatArrivageSearchRepository.save(achatArrivage);
        return result;
    }

    /**
     * Get all the achatArrivages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AchatArrivageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatArrivages");
        return achatArrivageRepository.findAll(pageable)
            .map(achatArrivageMapper::toDto);
    }


    /**
     * Get one achatArrivage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AchatArrivageDTO> findOne(Long id) {
        log.debug("Request to get AchatArrivage : {}", id);
        return achatArrivageRepository.findById(id)
            .map(achatArrivageMapper::toDto);
    }

    /**
     * Delete the achatArrivage by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AchatArrivage : {}", id);
        achatArrivageRepository.deleteById(id);
        achatArrivageSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatArrivage corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AchatArrivageDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatArrivages for query {}", query);
        return achatArrivageSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatArrivageMapper::toDto);
    }
}
