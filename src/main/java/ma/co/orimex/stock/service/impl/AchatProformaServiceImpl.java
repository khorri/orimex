package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatProformaService;
import ma.co.orimex.stock.domain.AchatProforma;
import ma.co.orimex.stock.repository.AchatProformaRepository;
import ma.co.orimex.stock.repository.search.AchatProformaSearchRepository;
import ma.co.orimex.stock.service.dto.AchatProformaDTO;
import ma.co.orimex.stock.service.mapper.AchatProformaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatProforma.
 */
@Service
@Transactional
public class AchatProformaServiceImpl implements AchatProformaService {

    private final Logger log = LoggerFactory.getLogger(AchatProformaServiceImpl.class);

    private final AchatProformaRepository achatProformaRepository;

    private final AchatProformaMapper achatProformaMapper;

    private final AchatProformaSearchRepository achatProformaSearchRepository;

    public AchatProformaServiceImpl(AchatProformaRepository achatProformaRepository, AchatProformaMapper achatProformaMapper, AchatProformaSearchRepository achatProformaSearchRepository) {
        this.achatProformaRepository = achatProformaRepository;
        this.achatProformaMapper = achatProformaMapper;
        this.achatProformaSearchRepository = achatProformaSearchRepository;
    }

    /**
     * Save a achatProforma.
     *
     * @param achatProformaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatProformaDTO save(AchatProformaDTO achatProformaDTO) {
        log.debug("Request to save AchatProforma : {}", achatProformaDTO);

        AchatProforma achatProforma = achatProformaMapper.toEntity(achatProformaDTO);
        achatProforma = achatProformaRepository.save(achatProforma);
        AchatProformaDTO result = achatProformaMapper.toDto(achatProforma);
        achatProformaSearchRepository.save(achatProforma);
        return result;
    }

    /**
     * Get all the achatProformas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatProformaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatProformas");
        return achatProformaRepository.findAll(pageable)
            .map(achatProformaMapper::toDto);
    }


    /**
     * Get one achatProforma by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatProformaDTO> findOne(Long id) {
        log.debug("Request to get AchatProforma : {}", id);
        return achatProformaRepository.findById(id)
            .map(achatProformaMapper::toDto);
    }

    /**
     * Delete the achatProforma by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatProforma : {}", id);
        achatProformaRepository.deleteById(id);
        achatProformaSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatProforma corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatProformaDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatProformas for query {}", query);
        return achatProformaSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatProformaMapper::toDto);
    }
}
