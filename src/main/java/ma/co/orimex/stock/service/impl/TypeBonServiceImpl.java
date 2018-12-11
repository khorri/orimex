package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.TypeBonService;
import ma.co.orimex.stock.domain.TypeBon;
import ma.co.orimex.stock.repository.TypeBonRepository;
import ma.co.orimex.stock.repository.search.TypeBonSearchRepository;
import ma.co.orimex.stock.service.dto.TypeBonDTO;
import ma.co.orimex.stock.service.mapper.TypeBonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing TypeBon.
 */
@Service
@Transactional
public class TypeBonServiceImpl implements TypeBonService {

    private final Logger log = LoggerFactory.getLogger(TypeBonServiceImpl.class);

    private final TypeBonRepository typeBonRepository;

    private final TypeBonMapper typeBonMapper;

    private final TypeBonSearchRepository typeBonSearchRepository;

    public TypeBonServiceImpl(TypeBonRepository typeBonRepository, TypeBonMapper typeBonMapper, TypeBonSearchRepository typeBonSearchRepository) {
        this.typeBonRepository = typeBonRepository;
        this.typeBonMapper = typeBonMapper;
        this.typeBonSearchRepository = typeBonSearchRepository;
    }

    /**
     * Save a typeBon.
     *
     * @param typeBonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeBonDTO save(TypeBonDTO typeBonDTO) {
        log.debug("Request to save TypeBon : {}", typeBonDTO);

        TypeBon typeBon = typeBonMapper.toEntity(typeBonDTO);
        typeBon = typeBonRepository.save(typeBon);
        TypeBonDTO result = typeBonMapper.toDto(typeBon);
        typeBonSearchRepository.save(typeBon);
        return result;
    }

    /**
     * Get all the typeBons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeBonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeBons");
        return typeBonRepository.findAll(pageable)
            .map(typeBonMapper::toDto);
    }


    /**
     * Get one typeBon by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeBonDTO> findOne(Long id) {
        log.debug("Request to get TypeBon : {}", id);
        return typeBonRepository.findById(id)
            .map(typeBonMapper::toDto);
    }

    /**
     * Delete the typeBon by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeBon : {}", id);
        typeBonRepository.deleteById(id);
        typeBonSearchRepository.deleteById(id);
    }

    /**
     * Search for the typeBon corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeBonDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TypeBons for query {}", query);
        return typeBonSearchRepository.search(queryStringQuery(query), pageable)
            .map(typeBonMapper::toDto);
    }
}
