package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatTypePaiementService;
import ma.co.orimex.stock.domain.AchatTypePaiement;
import ma.co.orimex.stock.repository.AchatTypePaiementRepository;
import ma.co.orimex.stock.repository.search.AchatTypePaiementSearchRepository;
import ma.co.orimex.stock.service.dto.AchatTypePaiementDTO;
import ma.co.orimex.stock.service.mapper.AchatTypePaiementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatTypePaiement.
 */
@Service
@Transactional
public class AchatTypePaiementServiceImpl implements AchatTypePaiementService {

    private final Logger log = LoggerFactory.getLogger(AchatTypePaiementServiceImpl.class);

    private final AchatTypePaiementRepository achatTypePaiementRepository;

    private final AchatTypePaiementMapper achatTypePaiementMapper;

    private final AchatTypePaiementSearchRepository achatTypePaiementSearchRepository;

    public AchatTypePaiementServiceImpl(AchatTypePaiementRepository achatTypePaiementRepository, AchatTypePaiementMapper achatTypePaiementMapper, AchatTypePaiementSearchRepository achatTypePaiementSearchRepository) {
        this.achatTypePaiementRepository = achatTypePaiementRepository;
        this.achatTypePaiementMapper = achatTypePaiementMapper;
        this.achatTypePaiementSearchRepository = achatTypePaiementSearchRepository;
    }

    /**
     * Save a achatTypePaiement.
     *
     * @param achatTypePaiementDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatTypePaiementDTO save(AchatTypePaiementDTO achatTypePaiementDTO) {
        log.debug("Request to save AchatTypePaiement : {}", achatTypePaiementDTO);

        AchatTypePaiement achatTypePaiement = achatTypePaiementMapper.toEntity(achatTypePaiementDTO);
        achatTypePaiement = achatTypePaiementRepository.save(achatTypePaiement);
        AchatTypePaiementDTO result = achatTypePaiementMapper.toDto(achatTypePaiement);
        achatTypePaiementSearchRepository.save(achatTypePaiement);
        return result;
    }

    /**
     * Get all the achatTypePaiements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatTypePaiementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatTypePaiements");
        return achatTypePaiementRepository.findAll(pageable)
            .map(achatTypePaiementMapper::toDto);
    }


    /**
     * Get one achatTypePaiement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatTypePaiementDTO> findOne(Long id) {
        log.debug("Request to get AchatTypePaiement : {}", id);
        return achatTypePaiementRepository.findById(id)
            .map(achatTypePaiementMapper::toDto);
    }

    /**
     * Delete the achatTypePaiement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatTypePaiement : {}", id);
        achatTypePaiementRepository.deleteById(id);
        achatTypePaiementSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatTypePaiement corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatTypePaiementDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatTypePaiements for query {}", query);
        return achatTypePaiementSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatTypePaiementMapper::toDto);
    }
}
