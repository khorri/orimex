package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.ProfilActionService;
import ma.co.orimex.stock.domain.ProfilAction;
import ma.co.orimex.stock.repository.ProfilActionRepository;
import ma.co.orimex.stock.repository.search.ProfilActionSearchRepository;
import ma.co.orimex.stock.service.dto.ProfilActionDTO;
import ma.co.orimex.stock.service.mapper.ProfilActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing ProfilAction.
 */
@Service
@Transactional
public class ProfilActionServiceImpl implements ProfilActionService {

    private final Logger log = LoggerFactory.getLogger(ProfilActionServiceImpl.class);

    private final ProfilActionRepository profilActionRepository;

    private final ProfilActionMapper profilActionMapper;

    private final ProfilActionSearchRepository profilActionSearchRepository;

    public ProfilActionServiceImpl(ProfilActionRepository profilActionRepository, ProfilActionMapper profilActionMapper, ProfilActionSearchRepository profilActionSearchRepository) {
        this.profilActionRepository = profilActionRepository;
        this.profilActionMapper = profilActionMapper;
        this.profilActionSearchRepository = profilActionSearchRepository;
    }

    /**
     * Save a profilAction.
     *
     * @param profilActionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProfilActionDTO save(ProfilActionDTO profilActionDTO) {
        log.debug("Request to save ProfilAction : {}", profilActionDTO);

        ProfilAction profilAction = profilActionMapper.toEntity(profilActionDTO);
        profilAction = profilActionRepository.save(profilAction);
        ProfilActionDTO result = profilActionMapper.toDto(profilAction);
        profilActionSearchRepository.save(profilAction);
        return result;
    }

    /**
     * Get all the profilActions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfilActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProfilActions");
        return profilActionRepository.findAll(pageable)
            .map(profilActionMapper::toDto);
    }


    /**
     * Get one profilAction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProfilActionDTO> findOne(Long id) {
        log.debug("Request to get ProfilAction : {}", id);
        return profilActionRepository.findById(id)
            .map(profilActionMapper::toDto);
    }

    /**
     * Delete the profilAction by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProfilAction : {}", id);
        profilActionRepository.deleteById(id);
        profilActionSearchRepository.deleteById(id);
    }

    /**
     * Search for the profilAction corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfilActionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ProfilActions for query {}", query);
        return profilActionSearchRepository.search(queryStringQuery(query), pageable)
            .map(profilActionMapper::toDto);
    }
}
