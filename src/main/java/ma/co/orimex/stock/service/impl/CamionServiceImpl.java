package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.CamionService;
import ma.co.orimex.stock.domain.Camion;
import ma.co.orimex.stock.repository.CamionRepository;
import ma.co.orimex.stock.repository.search.CamionSearchRepository;
import ma.co.orimex.stock.service.dto.CamionDTO;
import ma.co.orimex.stock.service.mapper.CamionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Camion.
 */
@Service
@Transactional
public class CamionServiceImpl implements CamionService {

    private final Logger log = LoggerFactory.getLogger(CamionServiceImpl.class);

    private final CamionRepository camionRepository;

    private final CamionMapper camionMapper;

    private final CamionSearchRepository camionSearchRepository;

    public CamionServiceImpl(CamionRepository camionRepository, CamionMapper camionMapper, CamionSearchRepository camionSearchRepository) {
        this.camionRepository = camionRepository;
        this.camionMapper = camionMapper;
        this.camionSearchRepository = camionSearchRepository;
    }

    /**
     * Save a camion.
     *
     * @param camionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CamionDTO save(CamionDTO camionDTO) {
        log.debug("Request to save Camion : {}", camionDTO);

        Camion camion = camionMapper.toEntity(camionDTO);
        camion = camionRepository.save(camion);
        CamionDTO result = camionMapper.toDto(camion);
        camionSearchRepository.save(camion);
        return result;
    }

    /**
     * Get all the camions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CamionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Camions");
        return camionRepository.findAll(pageable)
            .map(camionMapper::toDto);
    }


    /**
     * Get one camion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CamionDTO> findOne(Long id) {
        log.debug("Request to get Camion : {}", id);
        return camionRepository.findById(id)
            .map(camionMapper::toDto);
    }

    /**
     * Delete the camion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Camion : {}", id);
        camionRepository.deleteById(id);
        camionSearchRepository.deleteById(id);
    }

    /**
     * Search for the camion corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CamionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Camions for query {}", query);
        return camionSearchRepository.search(queryStringQuery(query), pageable)
            .map(camionMapper::toDto);
    }
}
