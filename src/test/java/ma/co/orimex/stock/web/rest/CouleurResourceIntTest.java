package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Couleur;
import ma.co.orimex.stock.repository.CouleurRepository;
import ma.co.orimex.stock.repository.search.CouleurSearchRepository;
import ma.co.orimex.stock.service.CouleurService;
import ma.co.orimex.stock.service.dto.CouleurDTO;
import ma.co.orimex.stock.service.mapper.CouleurMapper;
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
 * Test class for the CouleurResource REST controller.
 *
 * @see CouleurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class CouleurResourceIntTest {

    private static final Integer DEFAULT_ID_COULEUR = 1;
    private static final Integer UPDATED_ID_COULEUR = 2;

    private static final String DEFAULT_CODE_HTML = "AAAAAAAAAA";
    private static final String UPDATED_CODE_HTML = "BBBBBBBBBB";

    @Autowired
    private CouleurRepository couleurRepository;

    @Autowired
    private CouleurMapper couleurMapper;

    @Autowired
    private CouleurService couleurService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.CouleurSearchRepositoryMockConfiguration
     */
    @Autowired
    private CouleurSearchRepository mockCouleurSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCouleurMockMvc;

    private Couleur couleur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CouleurResource couleurResource = new CouleurResource(couleurService);
        this.restCouleurMockMvc = MockMvcBuilders.standaloneSetup(couleurResource)
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
    public static Couleur createEntity(EntityManager em) {
        Couleur couleur = new Couleur()
            .idCouleur(DEFAULT_ID_COULEUR)
            .codeHtml(DEFAULT_CODE_HTML);
        return couleur;
    }

    @Before
    public void initTest() {
        couleur = createEntity(em);
    }

    @Test
    @Transactional
    public void createCouleur() throws Exception {
        int databaseSizeBeforeCreate = couleurRepository.findAll().size();

        // Create the Couleur
        CouleurDTO couleurDTO = couleurMapper.toDto(couleur);
        restCouleurMockMvc.perform(post("/api/couleurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(couleurDTO)))
            .andExpect(status().isCreated());

        // Validate the Couleur in the database
        List<Couleur> couleurList = couleurRepository.findAll();
        assertThat(couleurList).hasSize(databaseSizeBeforeCreate + 1);
        Couleur testCouleur = couleurList.get(couleurList.size() - 1);
        assertThat(testCouleur.getIdCouleur()).isEqualTo(DEFAULT_ID_COULEUR);
        assertThat(testCouleur.getCodeHtml()).isEqualTo(DEFAULT_CODE_HTML);

        // Validate the Couleur in Elasticsearch
        verify(mockCouleurSearchRepository, times(1)).save(testCouleur);
    }

    @Test
    @Transactional
    public void createCouleurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = couleurRepository.findAll().size();

        // Create the Couleur with an existing ID
        couleur.setId(1L);
        CouleurDTO couleurDTO = couleurMapper.toDto(couleur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCouleurMockMvc.perform(post("/api/couleurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(couleurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Couleur in the database
        List<Couleur> couleurList = couleurRepository.findAll();
        assertThat(couleurList).hasSize(databaseSizeBeforeCreate);

        // Validate the Couleur in Elasticsearch
        verify(mockCouleurSearchRepository, times(0)).save(couleur);
    }

    @Test
    @Transactional
    public void getAllCouleurs() throws Exception {
        // Initialize the database
        couleurRepository.saveAndFlush(couleur);

        // Get all the couleurList
        restCouleurMockMvc.perform(get("/api/couleurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(couleur.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCouleur").value(hasItem(DEFAULT_ID_COULEUR)))
            .andExpect(jsonPath("$.[*].codeHtml").value(hasItem(DEFAULT_CODE_HTML.toString())));
    }
    
    @Test
    @Transactional
    public void getCouleur() throws Exception {
        // Initialize the database
        couleurRepository.saveAndFlush(couleur);

        // Get the couleur
        restCouleurMockMvc.perform(get("/api/couleurs/{id}", couleur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(couleur.getId().intValue()))
            .andExpect(jsonPath("$.idCouleur").value(DEFAULT_ID_COULEUR))
            .andExpect(jsonPath("$.codeHtml").value(DEFAULT_CODE_HTML.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCouleur() throws Exception {
        // Get the couleur
        restCouleurMockMvc.perform(get("/api/couleurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCouleur() throws Exception {
        // Initialize the database
        couleurRepository.saveAndFlush(couleur);

        int databaseSizeBeforeUpdate = couleurRepository.findAll().size();

        // Update the couleur
        Couleur updatedCouleur = couleurRepository.findById(couleur.getId()).get();
        // Disconnect from session so that the updates on updatedCouleur are not directly saved in db
        em.detach(updatedCouleur);
        updatedCouleur
            .idCouleur(UPDATED_ID_COULEUR)
            .codeHtml(UPDATED_CODE_HTML);
        CouleurDTO couleurDTO = couleurMapper.toDto(updatedCouleur);

        restCouleurMockMvc.perform(put("/api/couleurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(couleurDTO)))
            .andExpect(status().isOk());

        // Validate the Couleur in the database
        List<Couleur> couleurList = couleurRepository.findAll();
        assertThat(couleurList).hasSize(databaseSizeBeforeUpdate);
        Couleur testCouleur = couleurList.get(couleurList.size() - 1);
        assertThat(testCouleur.getIdCouleur()).isEqualTo(UPDATED_ID_COULEUR);
        assertThat(testCouleur.getCodeHtml()).isEqualTo(UPDATED_CODE_HTML);

        // Validate the Couleur in Elasticsearch
        verify(mockCouleurSearchRepository, times(1)).save(testCouleur);
    }

    @Test
    @Transactional
    public void updateNonExistingCouleur() throws Exception {
        int databaseSizeBeforeUpdate = couleurRepository.findAll().size();

        // Create the Couleur
        CouleurDTO couleurDTO = couleurMapper.toDto(couleur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCouleurMockMvc.perform(put("/api/couleurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(couleurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Couleur in the database
        List<Couleur> couleurList = couleurRepository.findAll();
        assertThat(couleurList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Couleur in Elasticsearch
        verify(mockCouleurSearchRepository, times(0)).save(couleur);
    }

    @Test
    @Transactional
    public void deleteCouleur() throws Exception {
        // Initialize the database
        couleurRepository.saveAndFlush(couleur);

        int databaseSizeBeforeDelete = couleurRepository.findAll().size();

        // Get the couleur
        restCouleurMockMvc.perform(delete("/api/couleurs/{id}", couleur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Couleur> couleurList = couleurRepository.findAll();
        assertThat(couleurList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Couleur in Elasticsearch
        verify(mockCouleurSearchRepository, times(1)).deleteById(couleur.getId());
    }

    @Test
    @Transactional
    public void searchCouleur() throws Exception {
        // Initialize the database
        couleurRepository.saveAndFlush(couleur);
        when(mockCouleurSearchRepository.search(queryStringQuery("id:" + couleur.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(couleur), PageRequest.of(0, 1), 1));
        // Search the couleur
        restCouleurMockMvc.perform(get("/api/_search/couleurs?query=id:" + couleur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(couleur.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCouleur").value(hasItem(DEFAULT_ID_COULEUR)))
            .andExpect(jsonPath("$.[*].codeHtml").value(hasItem(DEFAULT_CODE_HTML)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Couleur.class);
        Couleur couleur1 = new Couleur();
        couleur1.setId(1L);
        Couleur couleur2 = new Couleur();
        couleur2.setId(couleur1.getId());
        assertThat(couleur1).isEqualTo(couleur2);
        couleur2.setId(2L);
        assertThat(couleur1).isNotEqualTo(couleur2);
        couleur1.setId(null);
        assertThat(couleur1).isNotEqualTo(couleur2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CouleurDTO.class);
        CouleurDTO couleurDTO1 = new CouleurDTO();
        couleurDTO1.setId(1L);
        CouleurDTO couleurDTO2 = new CouleurDTO();
        assertThat(couleurDTO1).isNotEqualTo(couleurDTO2);
        couleurDTO2.setId(couleurDTO1.getId());
        assertThat(couleurDTO1).isEqualTo(couleurDTO2);
        couleurDTO2.setId(2L);
        assertThat(couleurDTO1).isNotEqualTo(couleurDTO2);
        couleurDTO1.setId(null);
        assertThat(couleurDTO1).isNotEqualTo(couleurDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(couleurMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(couleurMapper.fromId(null)).isNull();
    }
}
