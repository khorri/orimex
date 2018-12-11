package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatDeviseService;
import ma.co.orimex.stock.domain.AchatDevise;
import ma.co.orimex.stock.repository.AchatDeviseRepository;
import ma.co.orimex.stock.repository.search.AchatDeviseSearchRepository;
import ma.co.orimex.stock.service.dto.AchatDeviseDTO;
import ma.co.orimex.stock.service.mapper.AchatDeviseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatDevise.
 */
@Service
@Transactional
public class AchatDeviseServiceImpl implements AchatDeviseService {

    private final Logger log = LoggerFactory.getLogger(AchatDeviseServiceImpl.class);

    private final AchatDeviseRepository achatDeviseRepository;

    private final AchatDeviseMapper achatDeviseMapper;

    private final AchatDeviseSearchRepository achatDeviseSearchRepository;

    public AchatDeviseServiceImpl(AchatDeviseRepository achatDeviseRepository, AchatDeviseMapper achatDeviseMapper, AchatDeviseSearchRepository achatDeviseSearchRepository) {
        this.achatDeviseRepository = achatDeviseRepository;
        this.achatDeviseMapper = achatDeviseMapper;
        this.achatDeviseSearchRepository = achatDeviseSearchRepository;
    }

    /**
     * Save a achatDevise.
     *
     * @param achatDeviseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatDeviseDTO save(AchatDeviseDTO achatDeviseDTO) {
        log.debug("Request to save AchatDevise : {}", achatDeviseDTO);

        AchatDevise achatDevise = achatDeviseMapper.toEntity(achatDeviseDTO);
        achatDevise = achatDeviseRepository.save(achatDevise);
        AchatDeviseDTO result = achatDeviseMapper.toDto(achatDevise);
        achatDeviseSearchRepository.save(achatDevise);
        return result;
    }

    /**
     * Get all the achatDevises.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatDeviseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatDevises");
        return achatDeviseRepository.findAll(pageable)
            .map(achatDeviseMapper::toDto);
    }


    /**
     * Get one achatDevise by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatDeviseDTO> findOne(Long id) {
        log.debug("Request to get AchatDevise : {}", id);
        return achatDeviseRepository.findById(id)
            .map(achatDeviseMapper::toDto);
    }

    /**
     * Delete the achatDevise by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatDevise : {}", id);
        achatDeviseRepository.deleteById(id);
        achatDeviseSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatDevise corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatDeviseDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatDevises for query {}", query);
        return achatDeviseSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatDeviseMapper::toDto);
    }
}
