package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.UtilisateurService;
import ma.co.orimex.stock.domain.Utilisateur;
import ma.co.orimex.stock.repository.UtilisateurRepository;
import ma.co.orimex.stock.repository.search.UtilisateurSearchRepository;
import ma.co.orimex.stock.service.dto.UtilisateurDTO;
import ma.co.orimex.stock.service.mapper.UtilisateurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Utilisateur.
 */
@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final Logger log = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    private final UtilisateurRepository utilisateurRepository;

    private final UtilisateurMapper utilisateurMapper;

    private final UtilisateurSearchRepository utilisateurSearchRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper, UtilisateurSearchRepository utilisateurSearchRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
        this.utilisateurSearchRepository = utilisateurSearchRepository;
    }

    /**
     * Save a utilisateur.
     *
     * @param utilisateurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UtilisateurDTO save(UtilisateurDTO utilisateurDTO) {
        log.debug("Request to save Utilisateur : {}", utilisateurDTO);

        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        utilisateur = utilisateurRepository.save(utilisateur);
        UtilisateurDTO result = utilisateurMapper.toDto(utilisateur);
        utilisateurSearchRepository.save(utilisateur);
        return result;
    }

    /**
     * Get all the utilisateurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Utilisateurs");
        return utilisateurRepository.findAll(pageable)
            .map(utilisateurMapper::toDto);
    }


    /**
     * Get one utilisateur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UtilisateurDTO> findOne(Long id) {
        log.debug("Request to get Utilisateur : {}", id);
        return utilisateurRepository.findById(id)
            .map(utilisateurMapper::toDto);
    }

    /**
     * Delete the utilisateur by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Utilisateur : {}", id);
        utilisateurRepository.deleteById(id);
        utilisateurSearchRepository.deleteById(id);
    }

    /**
     * Search for the utilisateur corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Utilisateurs for query {}", query);
        return utilisateurSearchRepository.search(queryStringQuery(query), pageable)
            .map(utilisateurMapper::toDto);
    }
}
