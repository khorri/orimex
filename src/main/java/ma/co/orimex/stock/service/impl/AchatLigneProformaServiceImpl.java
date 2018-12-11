package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatLigneProformaService;
import ma.co.orimex.stock.domain.AchatLigneProforma;
import ma.co.orimex.stock.repository.AchatLigneProformaRepository;
import ma.co.orimex.stock.repository.search.AchatLigneProformaSearchRepository;
import ma.co.orimex.stock.service.dto.AchatLigneProformaDTO;
import ma.co.orimex.stock.service.mapper.AchatLigneProformaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatLigneProforma.
 */
@Service
@Transactional
public class AchatLigneProformaServiceImpl implements AchatLigneProformaService {

    private final Logger log = LoggerFactory.getLogger(AchatLigneProformaServiceImpl.class);

    private final AchatLigneProformaRepository achatLigneProformaRepository;

    private final AchatLigneProformaMapper achatLigneProformaMapper;

    private final AchatLigneProformaSearchRepository achatLigneProformaSearchRepository;

    public AchatLigneProformaServiceImpl(AchatLigneProformaRepository achatLigneProformaRepository, AchatLigneProformaMapper achatLigneProformaMapper, AchatLigneProformaSearchRepository achatLigneProformaSearchRepository) {
        this.achatLigneProformaRepository = achatLigneProformaRepository;
        this.achatLigneProformaMapper = achatLigneProformaMapper;
        this.achatLigneProformaSearchRepository = achatLigneProformaSearchRepository;
    }

    /**
     * Save a achatLigneProforma.
     *
     * @param achatLigneProformaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatLigneProformaDTO save(AchatLigneProformaDTO achatLigneProformaDTO) {
        log.debug("Request to save AchatLigneProforma : {}", achatLigneProformaDTO);

        AchatLigneProforma achatLigneProforma = achatLigneProformaMapper.toEntity(achatLigneProformaDTO);
        achatLigneProforma = achatLigneProformaRepository.save(achatLigneProforma);
        AchatLigneProformaDTO result = achatLigneProformaMapper.toDto(achatLigneProforma);
        achatLigneProformaSearchRepository.save(achatLigneProforma);
        return result;
    }

    /**
     * Get all the achatLigneProformas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatLigneProformaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatLigneProformas");
        return achatLigneProformaRepository.findAll(pageable)
            .map(achatLigneProformaMapper::toDto);
    }


    /**
     * Get one achatLigneProforma by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatLigneProformaDTO> findOne(Long id) {
        log.debug("Request to get AchatLigneProforma : {}", id);
        return achatLigneProformaRepository.findById(id)
            .map(achatLigneProformaMapper::toDto);
    }

    /**
     * Delete the achatLigneProforma by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatLigneProforma : {}", id);
        achatLigneProformaRepository.deleteById(id);
        achatLigneProformaSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatLigneProforma corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatLigneProformaDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatLigneProformas for query {}", query);
        return achatLigneProformaSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatLigneProformaMapper::toDto);
    }
}
