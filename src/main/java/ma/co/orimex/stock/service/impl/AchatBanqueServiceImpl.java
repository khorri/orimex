package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatBanqueService;
import ma.co.orimex.stock.domain.AchatBanque;
import ma.co.orimex.stock.repository.AchatBanqueRepository;
import ma.co.orimex.stock.repository.search.AchatBanqueSearchRepository;
import ma.co.orimex.stock.service.dto.AchatBanqueDTO;
import ma.co.orimex.stock.service.mapper.AchatBanqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatBanque.
 */
@Service
@Transactional
public class AchatBanqueServiceImpl implements AchatBanqueService {

    private final Logger log = LoggerFactory.getLogger(AchatBanqueServiceImpl.class);

    private final AchatBanqueRepository achatBanqueRepository;

    private final AchatBanqueMapper achatBanqueMapper;

    private final AchatBanqueSearchRepository achatBanqueSearchRepository;

    public AchatBanqueServiceImpl(AchatBanqueRepository achatBanqueRepository, AchatBanqueMapper achatBanqueMapper, AchatBanqueSearchRepository achatBanqueSearchRepository) {
        this.achatBanqueRepository = achatBanqueRepository;
        this.achatBanqueMapper = achatBanqueMapper;
        this.achatBanqueSearchRepository = achatBanqueSearchRepository;
    }

    /**
     * Save a achatBanque.
     *
     * @param achatBanqueDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatBanqueDTO save(AchatBanqueDTO achatBanqueDTO) {
        log.debug("Request to save AchatBanque : {}", achatBanqueDTO);

        AchatBanque achatBanque = achatBanqueMapper.toEntity(achatBanqueDTO);
        achatBanque = achatBanqueRepository.save(achatBanque);
        AchatBanqueDTO result = achatBanqueMapper.toDto(achatBanque);
        achatBanqueSearchRepository.save(achatBanque);
        return result;
    }

    /**
     * Get all the achatBanques.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatBanqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatBanques");
        return achatBanqueRepository.findAll(pageable)
            .map(achatBanqueMapper::toDto);
    }


    /**
     * Get one achatBanque by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatBanqueDTO> findOne(Long id) {
        log.debug("Request to get AchatBanque : {}", id);
        return achatBanqueRepository.findById(id)
            .map(achatBanqueMapper::toDto);
    }

    /**
     * Delete the achatBanque by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatBanque : {}", id);
        achatBanqueRepository.deleteById(id);
        achatBanqueSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatBanque corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatBanqueDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatBanques for query {}", query);
        return achatBanqueSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatBanqueMapper::toDto);
    }
}
