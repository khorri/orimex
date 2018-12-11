package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Conteneur;
import ma.co.orimex.stock.repository.ConteneurRepository;
import ma.co.orimex.stock.repository.search.ConteneurSearchRepository;
import ma.co.orimex.stock.service.ConteneurService;
import ma.co.orimex.stock.service.dto.ConteneurDTO;
import ma.co.orimex.stock.service.mapper.ConteneurMapper;
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
 * Test class for the ConteneurResource REST controller.
 *
 * @see ConteneurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class ConteneurResourceIntTest {

    private static final String DEFAULT_NUMERO_CONTENEUR = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CONTENEUR = "BBBBBBBBBB";

    @Autowired
    private ConteneurRepository conteneurRepository;

    @Autowired
    private ConteneurMapper conteneurMapper;

    @Autowired
    private ConteneurService conteneurService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.ConteneurSearchRepositoryMockConfiguration
     */
    @Autowired
    private ConteneurSearchRepository mockConteneurSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConteneurMockMvc;

    private Conteneur conteneur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConteneurResource conteneurResource = new ConteneurResource(conteneurService);
        this.restConteneurMockMvc = MockMvcBuilders.standaloneSetup(conteneurResource)
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
    public static Conteneur createEntity(EntityManager em) {
        Conteneur conteneur = new Conteneur()
            .numeroConteneur(DEFAULT_NUMERO_CONTENEUR);
        return conteneur;
    }

    @Before
    public void initTest() {
        conteneur = createEntity(em);
    }

    @Test
    @Transactional
    public void createConteneur() throws Exception {
        int databaseSizeBeforeCreate = conteneurRepository.findAll().size();

        // Create the Conteneur
        ConteneurDTO conteneurDTO = conteneurMapper.toDto(conteneur);
        restConteneurMockMvc.perform(post("/api/conteneurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteneurDTO)))
            .andExpect(status().isCreated());

        // Validate the Conteneur in the database
        List<Conteneur> conteneurList = conteneurRepository.findAll();
        assertThat(conteneurList).hasSize(databaseSizeBeforeCreate + 1);
        Conteneur testConteneur = conteneurList.get(conteneurList.size() - 1);
        assertThat(testConteneur.getNumeroConteneur()).isEqualTo(DEFAULT_NUMERO_CONTENEUR);

        // Validate the Conteneur in Elasticsearch
        verify(mockConteneurSearchRepository, times(1)).save(testConteneur);
    }

    @Test
    @Transactional
    public void createConteneurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conteneurRepository.findAll().size();

        // Create the Conteneur with an existing ID
        conteneur.setId(1L);
        ConteneurDTO conteneurDTO = conteneurMapper.toDto(conteneur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConteneurMockMvc.perform(post("/api/conteneurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteneurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Conteneur in the database
        List<Conteneur> conteneurList = conteneurRepository.findAll();
        assertThat(conteneurList).hasSize(databaseSizeBeforeCreate);

        // Validate the Conteneur in Elasticsearch
        verify(mockConteneurSearchRepository, times(0)).save(conteneur);
    }

    @Test
    @Transactional
    public void getAllConteneurs() throws Exception {
        // Initialize the database
        conteneurRepository.saveAndFlush(conteneur);

        // Get all the conteneurList
        restConteneurMockMvc.perform(get("/api/conteneurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conteneur.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroConteneur").value(hasItem(DEFAULT_NUMERO_CONTENEUR.toString())));
    }
    
    @Test
    @Transactional
    public void getConteneur() throws Exception {
        // Initialize the database
        conteneurRepository.saveAndFlush(conteneur);

        // Get the conteneur
        restConteneurMockMvc.perform(get("/api/conteneurs/{id}", conteneur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(conteneur.getId().intValue()))
            .andExpect(jsonPath("$.numeroConteneur").value(DEFAULT_NUMERO_CONTENEUR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConteneur() throws Exception {
        // Get the conteneur
        restConteneurMockMvc.perform(get("/api/conteneurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConteneur() throws Exception {
        // Initialize the database
        conteneurRepository.saveAndFlush(conteneur);

        int databaseSizeBeforeUpdate = conteneurRepository.findAll().size();

        // Update the conteneur
        Conteneur updatedConteneur = conteneurRepository.findById(conteneur.getId()).get();
        // Disconnect from session so that the updates on updatedConteneur are not directly saved in db
        em.detach(updatedConteneur);
        updatedConteneur
            .numeroConteneur(UPDATED_NUMERO_CONTENEUR);
        ConteneurDTO conteneurDTO = conteneurMapper.toDto(updatedConteneur);

        restConteneurMockMvc.perform(put("/api/conteneurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteneurDTO)))
            .andExpect(status().isOk());

        // Validate the Conteneur in the database
        List<Conteneur> conteneurList = conteneurRepository.findAll();
        assertThat(conteneurList).hasSize(databaseSizeBeforeUpdate);
        Conteneur testConteneur = conteneurList.get(conteneurList.size() - 1);
        assertThat(testConteneur.getNumeroConteneur()).isEqualTo(UPDATED_NUMERO_CONTENEUR);

        // Validate the Conteneur in Elasticsearch
        verify(mockConteneurSearchRepository, times(1)).save(testConteneur);
    }

    @Test
    @Transactional
    public void updateNonExistingConteneur() throws Exception {
        int databaseSizeBeforeUpdate = conteneurRepository.findAll().size();

        // Create the Conteneur
        ConteneurDTO conteneurDTO = conteneurMapper.toDto(conteneur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConteneurMockMvc.perform(put("/api/conteneurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteneurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Conteneur in the database
        List<Conteneur> conteneurList = conteneurRepository.findAll();
        assertThat(conteneurList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Conteneur in Elasticsearch
        verify(mockConteneurSearchRepository, times(0)).save(conteneur);
    }

    @Test
    @Transactional
    public void deleteConteneur() throws Exception {
        // Initialize the database
        conteneurRepository.saveAndFlush(conteneur);

        int databaseSizeBeforeDelete = conteneurRepository.findAll().size();

        // Get the conteneur
        restConteneurMockMvc.perform(delete("/api/conteneurs/{id}", conteneur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Conteneur> conteneurList = conteneurRepository.findAll();
        assertThat(conteneurList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Conteneur in Elasticsearch
        verify(mockConteneurSearchRepository, times(1)).deleteById(conteneur.getId());
    }

    @Test
    @Transactional
    public void searchConteneur() throws Exception {
        // Initialize the database
        conteneurRepository.saveAndFlush(conteneur);
        when(mockConteneurSearchRepository.search(queryStringQuery("id:" + conteneur.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(conteneur), PageRequest.of(0, 1), 1));
        // Search the conteneur
        restConteneurMockMvc.perform(get("/api/_search/conteneurs?query=id:" + conteneur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conteneur.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroConteneur").value(hasItem(DEFAULT_NUMERO_CONTENEUR)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Conteneur.class);
        Conteneur conteneur1 = new Conteneur();
        conteneur1.setId(1L);
        Conteneur conteneur2 = new Conteneur();
        conteneur2.setId(conteneur1.getId());
        assertThat(conteneur1).isEqualTo(conteneur2);
        conteneur2.setId(2L);
        assertThat(conteneur1).isNotEqualTo(conteneur2);
        conteneur1.setId(null);
        assertThat(conteneur1).isNotEqualTo(conteneur2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConteneurDTO.class);
        ConteneurDTO conteneurDTO1 = new ConteneurDTO();
        conteneurDTO1.setId(1L);
        ConteneurDTO conteneurDTO2 = new ConteneurDTO();
        assertThat(conteneurDTO1).isNotEqualTo(conteneurDTO2);
        conteneurDTO2.setId(conteneurDTO1.getId());
        assertThat(conteneurDTO1).isEqualTo(conteneurDTO2);
        conteneurDTO2.setId(2L);
        assertThat(conteneurDTO1).isNotEqualTo(conteneurDTO2);
        conteneurDTO1.setId(null);
        assertThat(conteneurDTO1).isNotEqualTo(conteneurDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(conteneurMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(conteneurMapper.fromId(null)).isNull();
    }
}
