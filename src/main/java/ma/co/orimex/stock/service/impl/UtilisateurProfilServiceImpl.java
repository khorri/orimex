package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.UtilisateurProfilService;
import ma.co.orimex.stock.domain.UtilisateurProfil;
import ma.co.orimex.stock.repository.UtilisateurProfilRepository;
import ma.co.orimex.stock.repository.search.UtilisateurProfilSearchRepository;
import ma.co.orimex.stock.service.dto.UtilisateurProfilDTO;
import ma.co.orimex.stock.service.mapper.UtilisateurProfilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing UtilisateurProfil.
 */
@Service
@Transactional
public class UtilisateurProfilServiceImpl implements UtilisateurProfilService {

    private final Logger log = LoggerFactory.getLogger(UtilisateurProfilServiceImpl.class);

    private final UtilisateurProfilRepository utilisateurProfilRepository;

    private final UtilisateurProfilMapper utilisateurProfilMapper;

    private final UtilisateurProfilSearchRepository utilisateurProfilSearchRepository;

    public UtilisateurProfilServiceImpl(UtilisateurProfilRepository utilisateurProfilRepository, UtilisateurProfilMapper utilisateurProfilMapper, UtilisateurProfilSearchRepository utilisateurProfilSearchRepository) {
        this.utilisateurProfilRepository = utilisateurProfilRepository;
        this.utilisateurProfilMapper = utilisateurProfilMapper;
        this.utilisateurProfilSearchRepository = utilisateurProfilSearchRepository;
    }

    /**
     * Save a utilisateurProfil.
     *
     * @param utilisateurProfilDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UtilisateurProfilDTO save(UtilisateurProfilDTO utilisateurProfilDTO) {
        log.debug("Request to save UtilisateurProfil : {}", utilisateurProfilDTO);

        UtilisateurProfil utilisateurProfil = utilisateurProfilMapper.toEntity(utilisateurProfilDTO);
        utilisateurProfil = utilisateurProfilRepository.save(utilisateurProfil);
        UtilisateurProfilDTO result = utilisateurProfilMapper.toDto(utilisateurProfil);
        utilisateurProfilSearchRepository.save(utilisateurProfil);
        return result;
    }

    /**
     * Get all the utilisateurProfils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurProfilDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UtilisateurProfils");
        return utilisateurProfilRepository.findAll(pageable)
            .map(utilisateurProfilMapper::toDto);
    }


    /**
     * Get one utilisateurProfil by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UtilisateurProfilDTO> findOne(Long id) {
        log.debug("Request to get UtilisateurProfil : {}", id);
        return utilisateurProfilRepository.findById(id)
            .map(utilisateurProfilMapper::toDto);
    }

    /**
     * Delete the utilisateurProfil by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UtilisateurProfil : {}", id);
        utilisateurProfilRepository.deleteById(id);
        utilisateurProfilSearchRepository.deleteById(id);
    }

    /**
     * Search for the utilisateurProfil corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurProfilDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UtilisateurProfils for query {}", query);
        return utilisateurProfilSearchRepository.search(queryStringQuery(query), pageable)
            .map(utilisateurProfilMapper::toDto);
    }
}
