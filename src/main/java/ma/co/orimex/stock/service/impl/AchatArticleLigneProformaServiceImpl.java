package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatArticleLigneProformaService;
import ma.co.orimex.stock.domain.AchatArticleLigneProforma;
import ma.co.orimex.stock.repository.AchatArticleLigneProformaRepository;
import ma.co.orimex.stock.repository.search.AchatArticleLigneProformaSearchRepository;
import ma.co.orimex.stock.service.dto.AchatArticleLigneProformaDTO;
import ma.co.orimex.stock.service.mapper.AchatArticleLigneProformaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatArticleLigneProforma.
 */
@Service
@Transactional
public class AchatArticleLigneProformaServiceImpl implements AchatArticleLigneProformaService {

    private final Logger log = LoggerFactory.getLogger(AchatArticleLigneProformaServiceImpl.class);

    private final AchatArticleLigneProformaRepository achatArticleLigneProformaRepository;

    private final AchatArticleLigneProformaMapper achatArticleLigneProformaMapper;

    private final AchatArticleLigneProformaSearchRepository achatArticleLigneProformaSearchRepository;

    public AchatArticleLigneProformaServiceImpl(AchatArticleLigneProformaRepository achatArticleLigneProformaRepository, AchatArticleLigneProformaMapper achatArticleLigneProformaMapper, AchatArticleLigneProformaSearchRepository achatArticleLigneProformaSearchRepository) {
        this.achatArticleLigneProformaRepository = achatArticleLigneProformaRepository;
        this.achatArticleLigneProformaMapper = achatArticleLigneProformaMapper;
        this.achatArticleLigneProformaSearchRepository = achatArticleLigneProformaSearchRepository;
    }

    /**
     * Save a achatArticleLigneProforma.
     *
     * @param achatArticleLigneProformaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatArticleLigneProformaDTO save(AchatArticleLigneProformaDTO achatArticleLigneProformaDTO) {
        log.debug("Request to save AchatArticleLigneProforma : {}", achatArticleLigneProformaDTO);

        AchatArticleLigneProforma achatArticleLigneProforma = achatArticleLigneProformaMapper.toEntity(achatArticleLigneProformaDTO);
        achatArticleLigneProforma = achatArticleLigneProformaRepository.save(achatArticleLigneProforma);
        AchatArticleLigneProformaDTO result = achatArticleLigneProformaMapper.toDto(achatArticleLigneProforma);
        achatArticleLigneProformaSearchRepository.save(achatArticleLigneProforma);
        return result;
    }

    /**
     * Get all the achatArticleLigneProformas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatArticleLigneProformaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatArticleLigneProformas");
        return achatArticleLigneProformaRepository.findAll(pageable)
            .map(achatArticleLigneProformaMapper::toDto);
    }


    /**
     * Get one achatArticleLigneProforma by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatArticleLigneProformaDTO> findOne(Long id) {
        log.debug("Request to get AchatArticleLigneProforma : {}", id);
        return achatArticleLigneProformaRepository.findById(id)
            .map(achatArticleLigneProformaMapper::toDto);
    }

    /**
     * Delete the achatArticleLigneProforma by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatArticleLigneProforma : {}", id);
        achatArticleLigneProformaRepository.deleteById(id);
        achatArticleLigneProformaSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatArticleLigneProforma corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatArticleLigneProformaDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatArticleLigneProformas for query {}", query);
        return achatArticleLigneProformaSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatArticleLigneProformaMapper::toDto);
    }
}
