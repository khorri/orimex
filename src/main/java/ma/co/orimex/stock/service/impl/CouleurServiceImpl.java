package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.CouleurService;
import ma.co.orimex.stock.domain.Couleur;
import ma.co.orimex.stock.repository.CouleurRepository;
import ma.co.orimex.stock.repository.search.CouleurSearchRepository;
import ma.co.orimex.stock.service.dto.CouleurDTO;
import ma.co.orimex.stock.service.mapper.CouleurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Couleur.
 */
@Service
@Transactional
public class CouleurServiceImpl implements CouleurService {

    private final Logger log = LoggerFactory.getLogger(CouleurServiceImpl.class);

    private final CouleurRepository couleurRepository;

    private final CouleurMapper couleurMapper;

    private final CouleurSearchRepository couleurSearchRepository;

    public CouleurServiceImpl(CouleurRepository couleurRepository, CouleurMapper couleurMapper, CouleurSearchRepository couleurSearchRepository) {
        this.couleurRepository = couleurRepository;
        this.couleurMapper = couleurMapper;
        this.couleurSearchRepository = couleurSearchRepository;
    }

    /**
     * Save a couleur.
     *
     * @param couleurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CouleurDTO save(CouleurDTO couleurDTO) {
        log.debug("Request to save Couleur : {}", couleurDTO);

        Couleur couleur = couleurMapper.toEntity(couleurDTO);
        couleur = couleurRepository.save(couleur);
        CouleurDTO result = couleurMapper.toDto(couleur);
        couleurSearchRepository.save(couleur);
        return result;
    }

    /**
     * Get all the couleurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CouleurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Couleurs");
        return couleurRepository.findAll(pageable)
            .map(couleurMapper::toDto);
    }


    /**
     * Get one couleur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CouleurDTO> findOne(Long id) {
        log.debug("Request to get Couleur : {}", id);
        return couleurRepository.findById(id)
            .map(couleurMapper::toDto);
    }

    /**
     * Delete the couleur by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Couleur : {}", id);
        couleurRepository.deleteById(id);
        couleurSearchRepository.deleteById(id);
    }

    /**
     * Search for the couleur corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CouleurDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Couleurs for query {}", query);
        return couleurSearchRepository.search(queryStringQuery(query), pageable)
            .map(couleurMapper::toDto);
    }
}
