package ma.co.orimex.stock.service;

import ma.co.orimex.stock.domain.AchatArticleConteneurArrivage;
import ma.co.orimex.stock.repository.AchatArticleConteneurArrivageRepository;
import ma.co.orimex.stock.repository.search.AchatArticleConteneurArrivageSearchRepository;
import ma.co.orimex.stock.service.dto.AchatArticleConteneurArrivageDTO;
import ma.co.orimex.stock.service.mapper.AchatArticleConteneurArrivageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatArticleConteneurArrivage.
 */
@Service
@Transactional
public class AchatArticleConteneurArrivageService {

    private final Logger log = LoggerFactory.getLogger(AchatArticleConteneurArrivageService.class);

    private final AchatArticleConteneurArrivageRepository achatArticleConteneurArrivageRepository;

    private final AchatArticleConteneurArrivageMapper achatArticleConteneurArrivageMapper;

    private final AchatArticleConteneurArrivageSearchRepository achatArticleConteneurArrivageSearchRepository;

    public AchatArticleConteneurArrivageService(AchatArticleConteneurArrivageRepository achatArticleConteneurArrivageRepository, AchatArticleConteneurArrivageMapper achatArticleConteneurArrivageMapper, AchatArticleConteneurArrivageSearchRepository achatArticleConteneurArrivageSearchRepository) {
        this.achatArticleConteneurArrivageRepository = achatArticleConteneurArrivageRepository;
        this.achatArticleConteneurArrivageMapper = achatArticleConteneurArrivageMapper;
        this.achatArticleConteneurArrivageSearchRepository = achatArticleConteneurArrivageSearchRepository;
    }

    /**
     * Save a achatArticleConteneurArrivage.
     *
     * @param achatArticleConteneurArrivageDTO the entity to save
     * @return the persisted entity
     */
    public AchatArticleConteneurArrivageDTO save(AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO) {
        log.debug("Request to save AchatArticleConteneurArrivage : {}", achatArticleConteneurArrivageDTO);

        AchatArticleConteneurArrivage achatArticleConteneurArrivage = achatArticleConteneurArrivageMapper.toEntity(achatArticleConteneurArrivageDTO);
        achatArticleConteneurArrivage = achatArticleConteneurArrivageRepository.save(achatArticleConteneurArrivage);
        AchatArticleConteneurArrivageDTO result = achatArticleConteneurArrivageMapper.toDto(achatArticleConteneurArrivage);
        achatArticleConteneurArrivageSearchRepository.save(achatArticleConteneurArrivage);
        return result;
    }

    /**
     * Get all the achatArticleConteneurArrivages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AchatArticleConteneurArrivageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatArticleConteneurArrivages");
        return achatArticleConteneurArrivageRepository.findAll(pageable)
            .map(achatArticleConteneurArrivageMapper::toDto);
    }


    /**
     * Get one achatArticleConteneurArrivage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AchatArticleConteneurArrivageDTO> findOne(Long id) {
        log.debug("Request to get AchatArticleConteneurArrivage : {}", id);
        return achatArticleConteneurArrivageRepository.findById(id)
            .map(achatArticleConteneurArrivageMapper::toDto);
    }

    /**
     * Delete the achatArticleConteneurArrivage by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AchatArticleConteneurArrivage : {}", id);
        achatArticleConteneurArrivageRepository.deleteById(id);
        achatArticleConteneurArrivageSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatArticleConteneurArrivage corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AchatArticleConteneurArrivageDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatArticleConteneurArrivages for query {}", query);
        return achatArticleConteneurArrivageSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatArticleConteneurArrivageMapper::toDto);
    }
}
