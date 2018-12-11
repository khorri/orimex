package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.UtilisateurDepotService;
import ma.co.orimex.stock.domain.UtilisateurDepot;
import ma.co.orimex.stock.repository.UtilisateurDepotRepository;
import ma.co.orimex.stock.repository.search.UtilisateurDepotSearchRepository;
import ma.co.orimex.stock.service.dto.UtilisateurDepotDTO;
import ma.co.orimex.stock.service.mapper.UtilisateurDepotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing UtilisateurDepot.
 */
@Service
@Transactional
public class UtilisateurDepotServiceImpl implements UtilisateurDepotService {

    private final Logger log = LoggerFactory.getLogger(UtilisateurDepotServiceImpl.class);

    private final UtilisateurDepotRepository utilisateurDepotRepository;

    private final UtilisateurDepotMapper utilisateurDepotMapper;

    private final UtilisateurDepotSearchRepository utilisateurDepotSearchRepository;

    public UtilisateurDepotServiceImpl(UtilisateurDepotRepository utilisateurDepotRepository, UtilisateurDepotMapper utilisateurDepotMapper, UtilisateurDepotSearchRepository utilisateurDepotSearchRepository) {
        this.utilisateurDepotRepository = utilisateurDepotRepository;
        this.utilisateurDepotMapper = utilisateurDepotMapper;
        this.utilisateurDepotSearchRepository = utilisateurDepotSearchRepository;
    }

    /**
     * Save a utilisateurDepot.
     *
     * @param utilisateurDepotDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UtilisateurDepotDTO save(UtilisateurDepotDTO utilisateurDepotDTO) {
        log.debug("Request to save UtilisateurDepot : {}", utilisateurDepotDTO);

        UtilisateurDepot utilisateurDepot = utilisateurDepotMapper.toEntity(utilisateurDepotDTO);
        utilisateurDepot = utilisateurDepotRepository.save(utilisateurDepot);
        UtilisateurDepotDTO result = utilisateurDepotMapper.toDto(utilisateurDepot);
        utilisateurDepotSearchRepository.save(utilisateurDepot);
        return result;
    }

    /**
     * Get all the utilisateurDepots.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurDepotDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UtilisateurDepots");
        return utilisateurDepotRepository.findAll(pageable)
            .map(utilisateurDepotMapper::toDto);
    }


    /**
     * Get one utilisateurDepot by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UtilisateurDepotDTO> findOne(Long id) {
        log.debug("Request to get UtilisateurDepot : {}", id);
        return utilisateurDepotRepository.findById(id)
            .map(utilisateurDepotMapper::toDto);
    }

    /**
     * Delete the utilisateurDepot by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UtilisateurDepot : {}", id);
        utilisateurDepotRepository.deleteById(id);
        utilisateurDepotSearchRepository.deleteById(id);
    }

    /**
     * Search for the utilisateurDepot corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurDepotDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UtilisateurDepots for query {}", query);
        return utilisateurDepotSearchRepository.search(queryStringQuery(query), pageable)
            .map(utilisateurDepotMapper::toDto);
    }
}
