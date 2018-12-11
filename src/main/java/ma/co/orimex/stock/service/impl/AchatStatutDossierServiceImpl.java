package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatStatutDossierService;
import ma.co.orimex.stock.domain.AchatStatutDossier;
import ma.co.orimex.stock.repository.AchatStatutDossierRepository;
import ma.co.orimex.stock.repository.search.AchatStatutDossierSearchRepository;
import ma.co.orimex.stock.service.dto.AchatStatutDossierDTO;
import ma.co.orimex.stock.service.mapper.AchatStatutDossierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatStatutDossier.
 */
@Service
@Transactional
public class AchatStatutDossierServiceImpl implements AchatStatutDossierService {

    private final Logger log = LoggerFactory.getLogger(AchatStatutDossierServiceImpl.class);

    private final AchatStatutDossierRepository achatStatutDossierRepository;

    private final AchatStatutDossierMapper achatStatutDossierMapper;

    private final AchatStatutDossierSearchRepository achatStatutDossierSearchRepository;

    public AchatStatutDossierServiceImpl(AchatStatutDossierRepository achatStatutDossierRepository, AchatStatutDossierMapper achatStatutDossierMapper, AchatStatutDossierSearchRepository achatStatutDossierSearchRepository) {
        this.achatStatutDossierRepository = achatStatutDossierRepository;
        this.achatStatutDossierMapper = achatStatutDossierMapper;
        this.achatStatutDossierSearchRepository = achatStatutDossierSearchRepository;
    }

    /**
     * Save a achatStatutDossier.
     *
     * @param achatStatutDossierDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatStatutDossierDTO save(AchatStatutDossierDTO achatStatutDossierDTO) {
        log.debug("Request to save AchatStatutDossier : {}", achatStatutDossierDTO);

        AchatStatutDossier achatStatutDossier = achatStatutDossierMapper.toEntity(achatStatutDossierDTO);
        achatStatutDossier = achatStatutDossierRepository.save(achatStatutDossier);
        AchatStatutDossierDTO result = achatStatutDossierMapper.toDto(achatStatutDossier);
        achatStatutDossierSearchRepository.save(achatStatutDossier);
        return result;
    }

    /**
     * Get all the achatStatutDossiers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatStatutDossierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatStatutDossiers");
        return achatStatutDossierRepository.findAll(pageable)
            .map(achatStatutDossierMapper::toDto);
    }


    /**
     * Get one achatStatutDossier by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatStatutDossierDTO> findOne(Long id) {
        log.debug("Request to get AchatStatutDossier : {}", id);
        return achatStatutDossierRepository.findById(id)
            .map(achatStatutDossierMapper::toDto);
    }

    /**
     * Delete the achatStatutDossier by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatStatutDossier : {}", id);
        achatStatutDossierRepository.deleteById(id);
        achatStatutDossierSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatStatutDossier corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatStatutDossierDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatStatutDossiers for query {}", query);
        return achatStatutDossierSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatStatutDossierMapper::toDto);
    }
}
