package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.ActionService;
import ma.co.orimex.stock.domain.Action;
import ma.co.orimex.stock.repository.ActionRepository;
import ma.co.orimex.stock.repository.search.ActionSearchRepository;
import ma.co.orimex.stock.service.dto.ActionDTO;
import ma.co.orimex.stock.service.mapper.ActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Action.
 */
@Service
@Transactional
public class ActionServiceImpl implements ActionService {

    private final Logger log = LoggerFactory.getLogger(ActionServiceImpl.class);

    private final ActionRepository actionRepository;

    private final ActionMapper actionMapper;

    private final ActionSearchRepository actionSearchRepository;

    public ActionServiceImpl(ActionRepository actionRepository, ActionMapper actionMapper, ActionSearchRepository actionSearchRepository) {
        this.actionRepository = actionRepository;
        this.actionMapper = actionMapper;
        this.actionSearchRepository = actionSearchRepository;
    }

    /**
     * Save a action.
     *
     * @param actionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ActionDTO save(ActionDTO actionDTO) {
        log.debug("Request to save Action : {}", actionDTO);

        Action action = actionMapper.toEntity(actionDTO);
        action = actionRepository.save(action);
        ActionDTO result = actionMapper.toDto(action);
        actionSearchRepository.save(action);
        return result;
    }

    /**
     * Get all the actions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Actions");
        return actionRepository.findAll(pageable)
            .map(actionMapper::toDto);
    }


    /**
     * Get one action by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ActionDTO> findOne(Long id) {
        log.debug("Request to get Action : {}", id);
        return actionRepository.findById(id)
            .map(actionMapper::toDto);
    }

    /**
     * Delete the action by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Action : {}", id);
        actionRepository.deleteById(id);
        actionSearchRepository.deleteById(id);
    }

    /**
     * Search for the action corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Actions for query {}", query);
        return actionSearchRepository.search(queryStringQuery(query), pageable)
            .map(actionMapper::toDto);
    }
}
