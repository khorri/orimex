package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.FamilleProduit;
import ma.co.orimex.stock.repository.FamilleProduitRepository;
import ma.co.orimex.stock.repository.search.FamilleProduitSearchRepository;
import ma.co.orimex.stock.service.FamilleProduitService;
import ma.co.orimex.stock.service.dto.FamilleProduitDTO;
import ma.co.orimex.stock.service.mapper.FamilleProduitMapper;
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
 * Test class for the FamilleProduitResource REST controller.
 *
 * @see FamilleProduitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class FamilleProduitResourceIntTest {

    private static final Integer DEFAULT_ID_FAMILLE = 1;
    private static final Integer UPDATED_ID_FAMILLE = 2;

    private static final String DEFAULT_DESIGNATION_FAMILLE = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_FAMILLE = "BBBBBBBBBB";

    @Autowired
    private FamilleProduitRepository familleProduitRepository;

    @Autowired
    private FamilleProduitMapper familleProduitMapper;

    @Autowired
    private FamilleProduitService familleProduitService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.FamilleProduitSearchRepositoryMockConfiguration
     */
    @Autowired
    private FamilleProduitSearchRepository mockFamilleProduitSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFamilleProduitMockMvc;

    private FamilleProduit familleProduit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FamilleProduitResource familleProduitResource = new FamilleProduitResource(familleProduitService);
        this.restFamilleProduitMockMvc = MockMvcBuilders.standaloneSetup(familleProduitResource)
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
    public static FamilleProduit createEntity(EntityManager em) {
        FamilleProduit familleProduit = new FamilleProduit()
            .idFamille(DEFAULT_ID_FAMILLE)
            .designationFamille(DEFAULT_DESIGNATION_FAMILLE);
        return familleProduit;
    }

    @Before
    public void initTest() {
        familleProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createFamilleProduit() throws Exception {
        int databaseSizeBeforeCreate = familleProduitRepository.findAll().size();

        // Create the FamilleProduit
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(familleProduit);
        restFamilleProduitMockMvc.perform(post("/api/famille-produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the FamilleProduit in the database
        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeCreate + 1);
        FamilleProduit testFamilleProduit = familleProduitList.get(familleProduitList.size() - 1);
        assertThat(testFamilleProduit.getIdFamille()).isEqualTo(DEFAULT_ID_FAMILLE);
        assertThat(testFamilleProduit.getDesignationFamille()).isEqualTo(DEFAULT_DESIGNATION_FAMILLE);

        // Validate the FamilleProduit in Elasticsearch
        verify(mockFamilleProduitSearchRepository, times(1)).save(testFamilleProduit);
    }

    @Test
    @Transactional
    public void createFamilleProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = familleProduitRepository.findAll().size();

        // Create the FamilleProduit with an existing ID
        familleProduit.setId(1L);
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(familleProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFamilleProduitMockMvc.perform(post("/api/famille-produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FamilleProduit in the database
        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeCreate);

        // Validate the FamilleProduit in Elasticsearch
        verify(mockFamilleProduitSearchRepository, times(0)).save(familleProduit);
    }

    @Test
    @Transactional
    public void getAllFamilleProduits() throws Exception {
        // Initialize the database
        familleProduitRepository.saveAndFlush(familleProduit);

        // Get all the familleProduitList
        restFamilleProduitMockMvc.perform(get("/api/famille-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(familleProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFamille").value(hasItem(DEFAULT_ID_FAMILLE)))
            .andExpect(jsonPath("$.[*].designationFamille").value(hasItem(DEFAULT_DESIGNATION_FAMILLE.toString())));
    }
    
    @Test
    @Transactional
    public void getFamilleProduit() throws Exception {
        // Initialize the database
        familleProduitRepository.saveAndFlush(familleProduit);

        // Get the familleProduit
        restFamilleProduitMockMvc.perform(get("/api/famille-produits/{id}", familleProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(familleProduit.getId().intValue()))
            .andExpect(jsonPath("$.idFamille").value(DEFAULT_ID_FAMILLE))
            .andExpect(jsonPath("$.designationFamille").value(DEFAULT_DESIGNATION_FAMILLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFamilleProduit() throws Exception {
        // Get the familleProduit
        restFamilleProduitMockMvc.perform(get("/api/famille-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFamilleProduit() throws Exception {
        // Initialize the database
        familleProduitRepository.saveAndFlush(familleProduit);

        int databaseSizeBeforeUpdate = familleProduitRepository.findAll().size();

        // Update the familleProduit
        FamilleProduit updatedFamilleProduit = familleProduitRepository.findById(familleProduit.getId()).get();
        // Disconnect from session so that the updates on updatedFamilleProduit are not directly saved in db
        em.detach(updatedFamilleProduit);
        updatedFamilleProduit
            .idFamille(UPDATED_ID_FAMILLE)
            .designationFamille(UPDATED_DESIGNATION_FAMILLE);
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(updatedFamilleProduit);

        restFamilleProduitMockMvc.perform(put("/api/famille-produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isOk());

        // Validate the FamilleProduit in the database
        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeUpdate);
        FamilleProduit testFamilleProduit = familleProduitList.get(familleProduitList.size() - 1);
        assertThat(testFamilleProduit.getIdFamille()).isEqualTo(UPDATED_ID_FAMILLE);
        assertThat(testFamilleProduit.getDesignationFamille()).isEqualTo(UPDATED_DESIGNATION_FAMILLE);

        // Validate the FamilleProduit in Elasticsearch
        verify(mockFamilleProduitSearchRepository, times(1)).save(testFamilleProduit);
    }

    @Test
    @Transactional
    public void updateNonExistingFamilleProduit() throws Exception {
        int databaseSizeBeforeUpdate = familleProduitRepository.findAll().size();

        // Create the FamilleProduit
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(familleProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFamilleProduitMockMvc.perform(put("/api/famille-produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FamilleProduit in the database
        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeUpdate);

        // Validate the FamilleProduit in Elasticsearch
        verify(mockFamilleProduitSearchRepository, times(0)).save(familleProduit);
    }

    @Test
    @Transactional
    public void deleteFamilleProduit() throws Exception {
        // Initialize the database
        familleProduitRepository.saveAndFlush(familleProduit);

        int databaseSizeBeforeDelete = familleProduitRepository.findAll().size();

        // Get the familleProduit
        restFamilleProduitMockMvc.perform(delete("/api/famille-produits/{id}", familleProduit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the FamilleProduit in Elasticsearch
        verify(mockFamilleProduitSearchRepository, times(1)).deleteById(familleProduit.getId());
    }

    @Test
    @Transactional
    public void searchFamilleProduit() throws Exception {
        // Initialize the database
        familleProduitRepository.saveAndFlush(familleProduit);
        when(mockFamilleProduitSearchRepository.search(queryStringQuery("id:" + familleProduit.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(familleProduit), PageRequest.of(0, 1), 1));
        // Search the familleProduit
        restFamilleProduitMockMvc.perform(get("/api/_search/famille-produits?query=id:" + familleProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(familleProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFamille").value(hasItem(DEFAULT_ID_FAMILLE)))
            .andExpect(jsonPath("$.[*].designationFamille").value(hasItem(DEFAULT_DESIGNATION_FAMILLE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FamilleProduit.class);
        FamilleProduit familleProduit1 = new FamilleProduit();
        familleProduit1.setId(1L);
        FamilleProduit familleProduit2 = new FamilleProduit();
        familleProduit2.setId(familleProduit1.getId());
        assertThat(familleProduit1).isEqualTo(familleProduit2);
        familleProduit2.setId(2L);
        assertThat(familleProduit1).isNotEqualTo(familleProduit2);
        familleProduit1.setId(null);
        assertThat(familleProduit1).isNotEqualTo(familleProduit2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FamilleProduitDTO.class);
        FamilleProduitDTO familleProduitDTO1 = new FamilleProduitDTO();
        familleProduitDTO1.setId(1L);
        FamilleProduitDTO familleProduitDTO2 = new FamilleProduitDTO();
        assertThat(familleProduitDTO1).isNotEqualTo(familleProduitDTO2);
        familleProduitDTO2.setId(familleProduitDTO1.getId());
        assertThat(familleProduitDTO1).isEqualTo(familleProduitDTO2);
        familleProduitDTO2.setId(2L);
        assertThat(familleProduitDTO1).isNotEqualTo(familleProduitDTO2);
        familleProduitDTO1.setId(null);
        assertThat(familleProduitDTO1).isNotEqualTo(familleProduitDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(familleProduitMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(familleProduitMapper.fromId(null)).isNull();
    }
}
