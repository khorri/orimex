package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.ProfilService;
import ma.co.orimex.stock.domain.Profil;
import ma.co.orimex.stock.repository.ProfilRepository;
import ma.co.orimex.stock.repository.search.ProfilSearchRepository;
import ma.co.orimex.stock.service.dto.ProfilDTO;
import ma.co.orimex.stock.service.mapper.ProfilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Profil.
 */
@Service
@Transactional
public class ProfilServiceImpl implements ProfilService {

    private final Logger log = LoggerFactory.getLogger(ProfilServiceImpl.class);

    private final ProfilRepository profilRepository;

    private final ProfilMapper profilMapper;

    private final ProfilSearchRepository profilSearchRepository;

    public ProfilServiceImpl(ProfilRepository profilRepository, ProfilMapper profilMapper, ProfilSearchRepository profilSearchRepository) {
        this.profilRepository = profilRepository;
        this.profilMapper = profilMapper;
        this.profilSearchRepository = profilSearchRepository;
    }

    /**
     * Save a profil.
     *
     * @param profilDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProfilDTO save(ProfilDTO profilDTO) {
        log.debug("Request to save Profil : {}", profilDTO);

        Profil profil = profilMapper.toEntity(profilDTO);
        profil = profilRepository.save(profil);
        ProfilDTO result = profilMapper.toDto(profil);
        profilSearchRepository.save(profil);
        return result;
    }

    /**
     * Get all the profils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfilDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Profils");
        return profilRepository.findAll(pageable)
            .map(profilMapper::toDto);
    }


    /**
     * Get one profil by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProfilDTO> findOne(Long id) {
        log.debug("Request to get Profil : {}", id);
        return profilRepository.findById(id)
            .map(profilMapper::toDto);
    }

    /**
     * Delete the profil by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Profil : {}", id);
        profilRepository.deleteById(id);
        profilSearchRepository.deleteById(id);
    }

    /**
     * Search for the profil corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfilDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Profils for query {}", query);
        return profilSearchRepository.search(queryStringQuery(query), pageable)
            .map(profilMapper::toDto);
    }
}
