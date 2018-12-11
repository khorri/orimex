package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.TransfertService;
import ma.co.orimex.stock.domain.Transfert;
import ma.co.orimex.stock.repository.TransfertRepository;
import ma.co.orimex.stock.repository.search.TransfertSearchRepository;
import ma.co.orimex.stock.service.dto.TransfertDTO;
import ma.co.orimex.stock.service.mapper.TransfertMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Transfert.
 */
@Service
@Transactional
public class TransfertServiceImpl implements TransfertService {

    private final Logger log = LoggerFactory.getLogger(TransfertServiceImpl.class);

    private final TransfertRepository transfertRepository;

    private final TransfertMapper transfertMapper;

    private final TransfertSearchRepository transfertSearchRepository;

    public TransfertServiceImpl(TransfertRepository transfertRepository, TransfertMapper transfertMapper, TransfertSearchRepository transfertSearchRepository) {
        this.transfertRepository = transfertRepository;
        this.transfertMapper = transfertMapper;
        this.transfertSearchRepository = transfertSearchRepository;
    }

    /**
     * Save a transfert.
     *
     * @param transfertDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TransfertDTO save(TransfertDTO transfertDTO) {
        log.debug("Request to save Transfert : {}", transfertDTO);

        Transfert transfert = transfertMapper.toEntity(transfertDTO);
        transfert = transfertRepository.save(transfert);
        TransfertDTO result = transfertMapper.toDto(transfert);
        transfertSearchRepository.save(transfert);
        return result;
    }

    /**
     * Get all the transferts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TransfertDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Transferts");
        return transfertRepository.findAll(pageable)
            .map(transfertMapper::toDto);
    }


    /**
     * Get one transfert by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransfertDTO> findOne(Long id) {
        log.debug("Request to get Transfert : {}", id);
        return transfertRepository.findById(id)
            .map(transfertMapper::toDto);
    }

    /**
     * Delete the transfert by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Transfert : {}", id);
        transfertRepository.deleteById(id);
        transfertSearchRepository.deleteById(id);
    }

    /**
     * Search for the transfert corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TransfertDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Transferts for query {}", query);
        return transfertSearchRepository.search(queryStringQuery(query), pageable)
            .map(transfertMapper::toDto);
    }
}
