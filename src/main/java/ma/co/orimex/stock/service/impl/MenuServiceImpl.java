package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.MenuService;
import ma.co.orimex.stock.domain.Menu;
import ma.co.orimex.stock.repository.MenuRepository;
import ma.co.orimex.stock.repository.search.MenuSearchRepository;
import ma.co.orimex.stock.service.dto.MenuDTO;
import ma.co.orimex.stock.service.mapper.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Menu.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    private final MenuRepository menuRepository;

    private final MenuMapper menuMapper;

    private final MenuSearchRepository menuSearchRepository;

    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper, MenuSearchRepository menuSearchRepository) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.menuSearchRepository = menuSearchRepository;
    }

    /**
     * Save a menu.
     *
     * @param menuDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MenuDTO save(MenuDTO menuDTO) {
        log.debug("Request to save Menu : {}", menuDTO);

        Menu menu = menuMapper.toEntity(menuDTO);
        menu = menuRepository.save(menu);
        MenuDTO result = menuMapper.toDto(menu);
        menuSearchRepository.save(menu);
        return result;
    }

    /**
     * Get all the menus.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Menus");
        return menuRepository.findAll(pageable)
            .map(menuMapper::toDto);
    }


    /**
     * Get one menu by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MenuDTO> findOne(Long id) {
        log.debug("Request to get Menu : {}", id);
        return menuRepository.findById(id)
            .map(menuMapper::toDto);
    }

    /**
     * Delete the menu by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Menu : {}", id);
        menuRepository.deleteById(id);
        menuSearchRepository.deleteById(id);
    }

    /**
     * Search for the menu corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Menus for query {}", query);
        return menuSearchRepository.search(queryStringQuery(query), pageable)
            .map(menuMapper::toDto);
    }
}
