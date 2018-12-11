package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.VilleService;
import ma.co.orimex.stock.domain.Ville;
import ma.co.orimex.stock.repository.VilleRepository;
import ma.co.orimex.stock.repository.search.VilleSearchRepository;
import ma.co.orimex.stock.service.dto.VilleDTO;
import ma.co.orimex.stock.service.mapper.VilleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Ville.
 */
@Service
@Transactional
public class VilleServiceImpl implements VilleService {

    private final Logger log = LoggerFactory.getLogger(VilleServiceImpl.class);

    private final VilleRepository villeRepository;

    private final VilleMapper villeMapper;

    private final VilleSearchRepository villeSearchRepository;

    public VilleServiceImpl(VilleRepository villeRepository, VilleMapper villeMapper, VilleSearchRepository villeSearchRepository) {
        this.villeRepository = villeRepository;
        this.villeMapper = villeMapper;
        this.villeSearchRepository = villeSearchRepository;
    }

    /**
     * Save a ville.
     *
     * @param villeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VilleDTO save(VilleDTO villeDTO) {
        log.debug("Request to save Ville : {}", villeDTO);

        Ville ville = villeMapper.toEntity(villeDTO);
        ville = villeRepository.save(ville);
        VilleDTO result = villeMapper.toDto(ville);
        villeSearchRepository.save(ville);
        return result;
    }

    /**
     * Get all the villes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VilleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Villes");
        return villeRepository.findAll(pageable)
            .map(villeMapper::toDto);
    }


    /**
     * Get one ville by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VilleDTO> findOne(Long id) {
        log.debug("Request to get Ville : {}", id);
        return villeRepository.findById(id)
            .map(villeMapper::toDto);
    }

    /**
     * Delete the ville by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ville : {}", id);
        villeRepository.deleteById(id);
        villeSearchRepository.deleteById(id);
    }

    /**
     * Search for the ville corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VilleDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Villes for query {}", query);
        return villeSearchRepository.search(queryStringQuery(query), pageable)
            .map(villeMapper::toDto);
    }
}
