package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.ConteneurService;
import ma.co.orimex.stock.domain.Conteneur;
import ma.co.orimex.stock.repository.ConteneurRepository;
import ma.co.orimex.stock.repository.search.ConteneurSearchRepository;
import ma.co.orimex.stock.service.dto.ConteneurDTO;
import ma.co.orimex.stock.service.mapper.ConteneurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Conteneur.
 */
@Service
@Transactional
public class ConteneurServiceImpl implements ConteneurService {

    private final Logger log = LoggerFactory.getLogger(ConteneurServiceImpl.class);

    private final ConteneurRepository conteneurRepository;

    private final ConteneurMapper conteneurMapper;

    private final ConteneurSearchRepository conteneurSearchRepository;

    public ConteneurServiceImpl(ConteneurRepository conteneurRepository, ConteneurMapper conteneurMapper, ConteneurSearchRepository conteneurSearchRepository) {
        this.conteneurRepository = conteneurRepository;
        this.conteneurMapper = conteneurMapper;
        this.conteneurSearchRepository = conteneurSearchRepository;
    }

    /**
     * Save a conteneur.
     *
     * @param conteneurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConteneurDTO save(ConteneurDTO conteneurDTO) {
        log.debug("Request to save Conteneur : {}", conteneurDTO);

        Conteneur conteneur = conteneurMapper.toEntity(conteneurDTO);
        conteneur = conteneurRepository.save(conteneur);
        ConteneurDTO result = conteneurMapper.toDto(conteneur);
        conteneurSearchRepository.save(conteneur);
        return result;
    }

    /**
     * Get all the conteneurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConteneurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Conteneurs");
        return conteneurRepository.findAll(pageable)
            .map(conteneurMapper::toDto);
    }


    /**
     * Get one conteneur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConteneurDTO> findOne(Long id) {
        log.debug("Request to get Conteneur : {}", id);
        return conteneurRepository.findById(id)
            .map(conteneurMapper::toDto);
    }

    /**
     * Delete the conteneur by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Conteneur : {}", id);
        conteneurRepository.deleteById(id);
        conteneurSearchRepository.deleteById(id);
    }

    /**
     * Search for the conteneur corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConteneurDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Conteneurs for query {}", query);
        return conteneurSearchRepository.search(queryStringQuery(query), pageable)
            .map(conteneurMapper::toDto);
    }
}
