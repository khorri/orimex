package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Menu;
import ma.co.orimex.stock.repository.MenuRepository;
import ma.co.orimex.stock.repository.search.MenuSearchRepository;
import ma.co.orimex.stock.service.MenuService;
import ma.co.orimex.stock.service.dto.MenuDTO;
import ma.co.orimex.stock.service.mapper.MenuMapper;
import ma.co.orimex.stock.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static ma.co.orimex.stock.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MenuResource REST controller.
 *
 * @see MenuResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class MenuResourceIntTest {

    private static final String DEFAULT_CODE_MENU = "AAAAAAAAAA";
    private static final String UPDATED_CODE_MENU = "BBBBBBBBBB";

    private static final String DEFAULT_CMAMPS_MENU = "AAAAAAAAAA";
    private static final String UPDATED_CMAMPS_MENU = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_MENU = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_MENU = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDRE = 1;
    private static final Integer UPDATED_ORDRE = 2;

    private static final String DEFAULT_DOMAINE = "AAAAAAAAAA";
    private static final String UPDATED_DOMAINE = "BBBBBBBBBB";

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuService menuService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.MenuSearchRepositoryMockConfiguration
     */
    @Autowired
    private MenuSearchRepository mockMenuSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMenuMockMvc;

    private Menu menu;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MenuResource menuResource = new MenuResource(menuService);
        this.restMenuMockMvc = MockMvcBuilders.standaloneSetup(menuResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Menu createEntity(EntityManager em) {
        Menu menu = new Menu()
            .codeMenu(DEFAULT_CODE_MENU)
            .cmampsMenu(DEFAULT_CMAMPS_MENU)
            .libelleMenu(DEFAULT_LIBELLE_MENU)
            .ordre(DEFAULT_ORDRE)
            .domaine(DEFAULT_DOMAINE);
        return menu;
    }

    @Before
    public void initTest() {
        menu = createEntity(em);
    }

    @Test
    @Transactional
    public void createMenu() throws Exception {
        int databaseSizeBeforeCreate = menuRepository.findAll().size();

        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);
        restMenuMockMvc.perform(post("/api/menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuDTO)))
            .andExpect(status().isCreated());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeCreate + 1);
        Menu testMenu = menuList.get(menuList.size() - 1);
        assertThat(testMenu.getCodeMenu()).isEqualTo(DEFAULT_CODE_MENU);
        assertThat(testMenu.getCmampsMenu()).isEqualTo(DEFAULT_CMAMPS_MENU);
        assertThat(testMenu.getLibelleMenu()).isEqualTo(DEFAULT_LIBELLE_MENU);
        assertThat(testMenu.getOrdre()).isEqualTo(DEFAULT_ORDRE);
        assertThat(testMenu.getDomaine()).isEqualTo(DEFAULT_DOMAINE);

        // Validate the Menu in Elasticsearch
        verify(mockMenuSearchRepository, times(1)).save(testMenu);
    }

    @Test
    @Transactional
    public void createMenuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = menuRepository.findAll().size();

        // Create the Menu with an existing ID
        menu.setId(1L);
        MenuDTO menuDTO = menuMapper.toDto(menu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuMockMvc.perform(post("/api/menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeCreate);

        // Validate the Menu in Elasticsearch
        verify(mockMenuSearchRepository, times(0)).save(menu);
    }

    @Test
    @Transactional
    public void getAllMenus() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        // Get all the menuList
        restMenuMockMvc.perform(get("/api/menus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menu.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeMenu").value(hasItem(DEFAULT_CODE_MENU.toString())))
            .andExpect(jsonPath("$.[*].cmampsMenu").value(hasItem(DEFAULT_CMAMPS_MENU.toString())))
            .andExpect(jsonPath("$.[*].libelleMenu").value(hasItem(DEFAULT_LIBELLE_MENU.toString())))
            .andExpect(jsonPath("$.[*].ordre").value(hasItem(DEFAULT_ORDRE)))
            .andExpect(jsonPath("$.[*].domaine").value(hasItem(DEFAULT_DOMAINE.toString())));
    }
    
    @Test
    @Transactional
    public void getMenu() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        // Get the menu
        restMenuMockMvc.perform(get("/api/menus/{id}", menu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(menu.getId().intValue()))
            .andExpect(jsonPath("$.codeMenu").value(DEFAULT_CODE_MENU.toString()))
            .andExpect(jsonPath("$.cmampsMenu").value(DEFAULT_CMAMPS_MENU.toString()))
            .andExpect(jsonPath("$.libelleMenu").value(DEFAULT_LIBELLE_MENU.toString()))
            .andExpect(jsonPath("$.ordre").value(DEFAULT_ORDRE))
            .andExpect(jsonPath("$.domaine").value(DEFAULT_DOMAINE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMenu() throws Exception {
        // Get the menu
        restMenuMockMvc.perform(get("/api/menus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenu() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        int databaseSizeBeforeUpdate = menuRepository.findAll().size();

        // Update the menu
        Menu updatedMenu = menuRepository.findById(menu.getId()).get();
        // Disconnect from session so that the updates on updatedMenu are not directly saved in db
        em.detach(updatedMenu);
        updatedMenu
            .codeMenu(UPDATED_CODE_MENU)
            .cmampsMenu(UPDATED_CMAMPS_MENU)
            .libelleMenu(UPDATED_LIBELLE_MENU)
            .ordre(UPDATED_ORDRE)
            .domaine(UPDATED_DOMAINE);
        MenuDTO menuDTO = menuMapper.toDto(updatedMenu);

        restMenuMockMvc.perform(put("/api/menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuDTO)))
            .andExpect(status().isOk());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
        Menu testMenu = menuList.get(menuList.size() - 1);
        assertThat(testMenu.getCodeMenu()).isEqualTo(UPDATED_CODE_MENU);
        assertThat(testMenu.getCmampsMenu()).isEqualTo(UPDATED_CMAMPS_MENU);
        assertThat(testMenu.getLibelleMenu()).isEqualTo(UPDATED_LIBELLE_MENU);
        assertThat(testMenu.getOrdre()).isEqualTo(UPDATED_ORDRE);
        assertThat(testMenu.getDomaine()).isEqualTo(UPDATED_DOMAINE);

        // Validate the Menu in Elasticsearch
        verify(mockMenuSearchRepository, times(1)).save(testMenu);
    }

    @Test
    @Transactional
    public void updateNonExistingMenu() throws Exception {
        int databaseSizeBeforeUpdate = menuRepository.findAll().size();

        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuMockMvc.perform(put("/api/menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Menu in Elasticsearch
        verify(mockMenuSearchRepository, times(0)).save(menu);
    }

    @Test
    @Transactional
    public void deleteMenu() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        int databaseSizeBeforeDelete = menuRepository.findAll().size();

        // Get the menu
        restMenuMockMvc.perform(delete("/api/menus/{id}", menu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Menu in Elasticsearch
        verify(mockMenuSearchRepository, times(1)).deleteById(menu.getId());
    }

    @Test
    @Transactional
    public void searchMenu() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);
        when(mockMenuSearchRepository.search(queryStringQuery("id:" + menu.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(menu), PageRequest.of(0, 1), 1));
        // Search the menu
        restMenuMockMvc.perform(get("/api/_search/menus?query=id:" + menu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menu.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeMenu").value(hasItem(DEFAULT_CODE_MENU)))
            .andExpect(jsonPath("$.[*].cmampsMenu").value(hasItem(DEFAULT_CMAMPS_MENU)))
            .andExpect(jsonPath("$.[*].libelleMenu").value(hasItem(DEFAULT_LIBELLE_MENU)))
            .andExpect(jsonPath("$.[*].ordre").value(hasItem(DEFAULT_ORDRE)))
            .andExpect(jsonPath("$.[*].domaine").value(hasItem(DEFAULT_DOMAINE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Menu.class);
        Menu menu1 = new Menu();
        menu1.setId(1L);
        Menu menu2 = new Menu();
        menu2.setId(menu1.getId());
        assertThat(menu1).isEqualTo(menu2);
        menu2.setId(2L);
        assertThat(menu1).isNotEqualTo(menu2);
        menu1.setId(null);
        assertThat(menu1).isNotEqualTo(menu2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuDTO.class);
        MenuDTO menuDTO1 = new MenuDTO();
        menuDTO1.setId(1L);
        MenuDTO menuDTO2 = new MenuDTO();
        assertThat(menuDTO1).isNotEqualTo(menuDTO2);
        menuDTO2.setId(menuDTO1.getId());
        assertThat(menuDTO1).isEqualTo(menuDTO2);
        menuDTO2.setId(2L);
        assertThat(menuDTO1).isNotEqualTo(menuDTO2);
        menuDTO1.setId(null);
        assertThat(menuDTO1).isNotEqualTo(menuDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(menuMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(menuMapper.fromId(null)).isNull();
    }
}
