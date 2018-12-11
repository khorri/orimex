package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.AchatFournisseurService;
import ma.co.orimex.stock.domain.AchatFournisseur;
import ma.co.orimex.stock.repository.AchatFournisseurRepository;
import ma.co.orimex.stock.repository.search.AchatFournisseurSearchRepository;
import ma.co.orimex.stock.service.dto.AchatFournisseurDTO;
import ma.co.orimex.stock.service.mapper.AchatFournisseurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AchatFournisseur.
 */
@Service
@Transactional
public class AchatFournisseurServiceImpl implements AchatFournisseurService {

    private final Logger log = LoggerFactory.getLogger(AchatFournisseurServiceImpl.class);

    private final AchatFournisseurRepository achatFournisseurRepository;

    private final AchatFournisseurMapper achatFournisseurMapper;

    private final AchatFournisseurSearchRepository achatFournisseurSearchRepository;

    public AchatFournisseurServiceImpl(AchatFournisseurRepository achatFournisseurRepository, AchatFournisseurMapper achatFournisseurMapper, AchatFournisseurSearchRepository achatFournisseurSearchRepository) {
        this.achatFournisseurRepository = achatFournisseurRepository;
        this.achatFournisseurMapper = achatFournisseurMapper;
        this.achatFournisseurSearchRepository = achatFournisseurSearchRepository;
    }

    /**
     * Save a achatFournisseur.
     *
     * @param achatFournisseurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AchatFournisseurDTO save(AchatFournisseurDTO achatFournisseurDTO) {
        log.debug("Request to save AchatFournisseur : {}", achatFournisseurDTO);

        AchatFournisseur achatFournisseur = achatFournisseurMapper.toEntity(achatFournisseurDTO);
        achatFournisseur = achatFournisseurRepository.save(achatFournisseur);
        AchatFournisseurDTO result = achatFournisseurMapper.toDto(achatFournisseur);
        achatFournisseurSearchRepository.save(achatFournisseur);
        return result;
    }

    /**
     * Get all the achatFournisseurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatFournisseurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchatFournisseurs");
        return achatFournisseurRepository.findAll(pageable)
            .map(achatFournisseurMapper::toDto);
    }


    /**
     * Get one achatFournisseur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchatFournisseurDTO> findOne(Long id) {
        log.debug("Request to get AchatFournisseur : {}", id);
        return achatFournisseurRepository.findById(id)
            .map(achatFournisseurMapper::toDto);
    }

    /**
     * Delete the achatFournisseur by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchatFournisseur : {}", id);
        achatFournisseurRepository.deleteById(id);
        achatFournisseurSearchRepository.deleteById(id);
    }

    /**
     * Search for the achatFournisseur corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AchatFournisseurDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AchatFournisseurs for query {}", query);
        return achatFournisseurSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatFournisseurMapper::toDto);
    }
}
