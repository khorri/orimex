package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.DepotService;
import ma.co.orimex.stock.domain.Depot;
import ma.co.orimex.stock.repository.DepotRepository;
import ma.co.orimex.stock.repository.search.DepotSearchRepository;
import ma.co.orimex.stock.service.dto.DepotDTO;
import ma.co.orimex.stock.service.mapper.DepotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Depot.
 */
@Service
@Transactional
public class DepotServiceImpl implements DepotService {

    private final Logger log = LoggerFactory.getLogger(DepotServiceImpl.class);

    private final DepotRepository depotRepository;

    private final DepotMapper depotMapper;

    private final DepotSearchRepository depotSearchRepository;

    public DepotServiceImpl(DepotRepository depotRepository, DepotMapper depotMapper, DepotSearchRepository depotSearchRepository) {
        this.depotRepository = depotRepository;
        this.depotMapper = depotMapper;
        this.depotSearchRepository = depotSearchRepository;
    }

    /**
     * Save a depot.
     *
     * @param depotDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DepotDTO save(DepotDTO depotDTO) {
        log.debug("Request to save Depot : {}", depotDTO);

        Depot depot = depotMapper.toEntity(depotDTO);
        depot = depotRepository.save(depot);
        DepotDTO result = depotMapper.toDto(depot);
        depotSearchRepository.save(depot);
        return result;
    }

    /**
     * Get all the depots.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DepotDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Depots");
        return depotRepository.findAll(pageable)
            .map(depotMapper::toDto);
    }


    /**
     * Get one depot by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DepotDTO> findOne(Long id) {
        log.debug("Request to get Depot : {}", id);
        return depotRepository.findById(id)
            .map(depotMapper::toDto);
    }

    /**
     * Delete the depot by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Depot : {}", id);
        depotRepository.deleteById(id);
        depotSearchRepository.deleteById(id);
    }

    /**
     * Search for the depot corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DepotDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Depots for query {}", query);
        return depotSearchRepository.search(queryStringQuery(query), pageable)
            .map(depotMapper::toDto);
    }
}
