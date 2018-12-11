package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.UtilisateurProfilPKService;
import ma.co.orimex.stock.domain.UtilisateurProfilPK;
import ma.co.orimex.stock.repository.UtilisateurProfilPKRepository;
import ma.co.orimex.stock.repository.search.UtilisateurProfilPKSearchRepository;
import ma.co.orimex.stock.service.dto.UtilisateurProfilPKDTO;
import ma.co.orimex.stock.service.mapper.UtilisateurProfilPKMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing UtilisateurProfilPK.
 */
@Service
@Transactional
public class UtilisateurProfilPKServiceImpl implements UtilisateurProfilPKService {

    private final Logger log = LoggerFactory.getLogger(UtilisateurProfilPKServiceImpl.class);

    private final UtilisateurProfilPKRepository utilisateurProfilPKRepository;

    private final UtilisateurProfilPKMapper utilisateurProfilPKMapper;

    private final UtilisateurProfilPKSearchRepository utilisateurProfilPKSearchRepository;

    public UtilisateurProfilPKServiceImpl(UtilisateurProfilPKRepository utilisateurProfilPKRepository, UtilisateurProfilPKMapper utilisateurProfilPKMapper, UtilisateurProfilPKSearchRepository utilisateurProfilPKSearchRepository) {
        this.utilisateurProfilPKRepository = utilisateurProfilPKRepository;
        this.utilisateurProfilPKMapper = utilisateurProfilPKMapper;
        this.utilisateurProfilPKSearchRepository = utilisateurProfilPKSearchRepository;
    }

    /**
     * Save a utilisateurProfilPK.
     *
     * @param utilisateurProfilPKDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UtilisateurProfilPKDTO save(UtilisateurProfilPKDTO utilisateurProfilPKDTO) {
        log.debug("Request to save UtilisateurProfilPK : {}", utilisateurProfilPKDTO);

        UtilisateurProfilPK utilisateurProfilPK = utilisateurProfilPKMapper.toEntity(utilisateurProfilPKDTO);
        utilisateurProfilPK = utilisateurProfilPKRepository.save(utilisateurProfilPK);
        UtilisateurProfilPKDTO result = utilisateurProfilPKMapper.toDto(utilisateurProfilPK);
        utilisateurProfilPKSearchRepository.save(utilisateurProfilPK);
        return result;
    }

    /**
     * Get all the utilisateurProfilPKS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurProfilPKDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UtilisateurProfilPKS");
        return utilisateurProfilPKRepository.findAll(pageable)
            .map(utilisateurProfilPKMapper::toDto);
    }


    /**
     * Get one utilisateurProfilPK by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UtilisateurProfilPKDTO> findOne(Long id) {
        log.debug("Request to get UtilisateurProfilPK : {}", id);
        return utilisateurProfilPKRepository.findById(id)
            .map(utilisateurProfilPKMapper::toDto);
    }

    /**
     * Delete the utilisateurProfilPK by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UtilisateurProfilPK : {}", id);
        utilisateurProfilPKRepository.deleteById(id);
        utilisateurProfilPKSearchRepository.deleteById(id);
    }

    /**
     * Search for the utilisateurProfilPK corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurProfilPKDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UtilisateurProfilPKS for query {}", query);
        return utilisateurProfilPKSearchRepository.search(queryStringQuery(query), pageable)
            .map(utilisateurProfilPKMapper::toDto);
    }
}
