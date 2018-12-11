package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.UtilisateurDepotPKService;
import ma.co.orimex.stock.domain.UtilisateurDepotPK;
import ma.co.orimex.stock.repository.UtilisateurDepotPKRepository;
import ma.co.orimex.stock.repository.search.UtilisateurDepotPKSearchRepository;
import ma.co.orimex.stock.service.dto.UtilisateurDepotPKDTO;
import ma.co.orimex.stock.service.mapper.UtilisateurDepotPKMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing UtilisateurDepotPK.
 */
@Service
@Transactional
public class UtilisateurDepotPKServiceImpl implements UtilisateurDepotPKService {

    private final Logger log = LoggerFactory.getLogger(UtilisateurDepotPKServiceImpl.class);

    private final UtilisateurDepotPKRepository utilisateurDepotPKRepository;

    private final UtilisateurDepotPKMapper utilisateurDepotPKMapper;

    private final UtilisateurDepotPKSearchRepository utilisateurDepotPKSearchRepository;

    public UtilisateurDepotPKServiceImpl(UtilisateurDepotPKRepository utilisateurDepotPKRepository, UtilisateurDepotPKMapper utilisateurDepotPKMapper, UtilisateurDepotPKSearchRepository utilisateurDepotPKSearchRepository) {
        this.utilisateurDepotPKRepository = utilisateurDepotPKRepository;
        this.utilisateurDepotPKMapper = utilisateurDepotPKMapper;
        this.utilisateurDepotPKSearchRepository = utilisateurDepotPKSearchRepository;
    }

    /**
     * Save a utilisateurDepotPK.
     *
     * @param utilisateurDepotPKDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UtilisateurDepotPKDTO save(UtilisateurDepotPKDTO utilisateurDepotPKDTO) {
        log.debug("Request to save UtilisateurDepotPK : {}", utilisateurDepotPKDTO);

        UtilisateurDepotPK utilisateurDepotPK = utilisateurDepotPKMapper.toEntity(utilisateurDepotPKDTO);
        utilisateurDepotPK = utilisateurDepotPKRepository.save(utilisateurDepotPK);
        UtilisateurDepotPKDTO result = utilisateurDepotPKMapper.toDto(utilisateurDepotPK);
        utilisateurDepotPKSearchRepository.save(utilisateurDepotPK);
        return result;
    }

    /**
     * Get all the utilisateurDepotPKS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurDepotPKDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UtilisateurDepotPKS");
        return utilisateurDepotPKRepository.findAll(pageable)
            .map(utilisateurDepotPKMapper::toDto);
    }


    /**
     * Get one utilisateurDepotPK by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UtilisateurDepotPKDTO> findOne(Long id) {
        log.debug("Request to get UtilisateurDepotPK : {}", id);
        return utilisateurDepotPKRepository.findById(id)
            .map(utilisateurDepotPKMapper::toDto);
    }

    /**
     * Delete the utilisateurDepotPK by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UtilisateurDepotPK : {}", id);
        utilisateurDepotPKRepository.deleteById(id);
        utilisateurDepotPKSearchRepository.deleteById(id);
    }

    /**
     * Search for the utilisateurDepotPK corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurDepotPKDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UtilisateurDepotPKS for query {}", query);
        return utilisateurDepotPKSearchRepository.search(queryStringQuery(query), pageable)
            .map(utilisateurDepotPKMapper::toDto);
    }
}
