package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.ProfilActionPKService;
import ma.co.orimex.stock.domain.ProfilActionPK;
import ma.co.orimex.stock.repository.ProfilActionPKRepository;
import ma.co.orimex.stock.repository.search.ProfilActionPKSearchRepository;
import ma.co.orimex.stock.service.dto.ProfilActionPKDTO;
import ma.co.orimex.stock.service.mapper.ProfilActionPKMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing ProfilActionPK.
 */
@Service
@Transactional
public class ProfilActionPKServiceImpl implements ProfilActionPKService {

    private final Logger log = LoggerFactory.getLogger(ProfilActionPKServiceImpl.class);

    private final ProfilActionPKRepository profilActionPKRepository;

    private final ProfilActionPKMapper profilActionPKMapper;

    private final ProfilActionPKSearchRepository profilActionPKSearchRepository;

    public ProfilActionPKServiceImpl(ProfilActionPKRepository profilActionPKRepository, ProfilActionPKMapper profilActionPKMapper, ProfilActionPKSearchRepository profilActionPKSearchRepository) {
        this.profilActionPKRepository = profilActionPKRepository;
        this.profilActionPKMapper = profilActionPKMapper;
        this.profilActionPKSearchRepository = profilActionPKSearchRepository;
    }

    /**
     * Save a profilActionPK.
     *
     * @param profilActionPKDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProfilActionPKDTO save(ProfilActionPKDTO profilActionPKDTO) {
        log.debug("Request to save ProfilActionPK : {}", profilActionPKDTO);

        ProfilActionPK profilActionPK = profilActionPKMapper.toEntity(profilActionPKDTO);
        profilActionPK = profilActionPKRepository.save(profilActionPK);
        ProfilActionPKDTO result = profilActionPKMapper.toDto(profilActionPK);
        profilActionPKSearchRepository.save(profilActionPK);
        return result;
    }

    /**
     * Get all the profilActionPKS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfilActionPKDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProfilActionPKS");
        return profilActionPKRepository.findAll(pageable)
            .map(profilActionPKMapper::toDto);
    }


    /**
     * Get one profilActionPK by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProfilActionPKDTO> findOne(Long id) {
        log.debug("Request to get ProfilActionPK : {}", id);
        return profilActionPKRepository.findById(id)
            .map(profilActionPKMapper::toDto);
    }

    /**
     * Delete the profilActionPK by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProfilActionPK : {}", id);
        profilActionPKRepository.deleteById(id);
        profilActionPKSearchRepository.deleteById(id);
    }

    /**
     * Search for the profilActionPK corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfilActionPKDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ProfilActionPKS for query {}", query);
        return profilActionPKSearchRepository.search(queryStringQuery(query), pageable)
            .map(profilActionPKMapper::toDto);
    }
}
