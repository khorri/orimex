package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Camion;
import ma.co.orimex.stock.repository.CamionRepository;
import ma.co.orimex.stock.repository.search.CamionSearchRepository;
import ma.co.orimex.stock.service.CamionService;
import ma.co.orimex.stock.service.dto.CamionDTO;
import ma.co.orimex.stock.service.mapper.CamionMapper;
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
 * Test class for the CamionResource REST controller.
 *
 * @see CamionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class CamionResourceIntTest {

    private static final Integer DEFAULT_ID_CAMION = 1;
    private static final Integer UPDATED_ID_CAMION = 2;

    private static final String DEFAULT_NUMERO_CAMION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CAMION = "BBBBBBBBBB";

    @Autowired
    private CamionRepository camionRepository;

    @Autowired
    private CamionMapper camionMapper;

    @Autowired
    private CamionService camionService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.CamionSearchRepositoryMockConfiguration
     */
    @Autowired
    private CamionSearchRepository mockCamionSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCamionMockMvc;

    private Camion camion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CamionResource camionResource = new CamionResource(camionService);
        this.restCamionMockMvc = MockMvcBuilders.standaloneSetup(camionResource)
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
    public static Camion createEntity(EntityManager em) {
        Camion camion = new Camion()
            .idCamion(DEFAULT_ID_CAMION)
            .numeroCamion(DEFAULT_NUMERO_CAMION);
        return camion;
    }

    @Before
    public void initTest() {
        camion = createEntity(em);
    }

    @Test
    @Transactional
    public void createCamion() throws Exception {
        int databaseSizeBeforeCreate = camionRepository.findAll().size();

        // Create the Camion
        CamionDTO camionDTO = camionMapper.toDto(camion);
        restCamionMockMvc.perform(post("/api/camions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(camionDTO)))
            .andExpect(status().isCreated());

        // Validate the Camion in the database
        List<Camion> camionList = camionRepository.findAll();
        assertThat(camionList).hasSize(databaseSizeBeforeCreate + 1);
        Camion testCamion = camionList.get(camionList.size() - 1);
        assertThat(testCamion.getIdCamion()).isEqualTo(DEFAULT_ID_CAMION);
        assertThat(testCamion.getNumeroCamion()).isEqualTo(DEFAULT_NUMERO_CAMION);

        // Validate the Camion in Elasticsearch
        verify(mockCamionSearchRepository, times(1)).save(testCamion);
    }

    @Test
    @Transactional
    public void createCamionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = camionRepository.findAll().size();

        // Create the Camion with an existing ID
        camion.setId(1L);
        CamionDTO camionDTO = camionMapper.toDto(camion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCamionMockMvc.perform(post("/api/camions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(camionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Camion in the database
        List<Camion> camionList = camionRepository.findAll();
        assertThat(camionList).hasSize(databaseSizeBeforeCreate);

        // Validate the Camion in Elasticsearch
        verify(mockCamionSearchRepository, times(0)).save(camion);
    }

    @Test
    @Transactional
    public void getAllCamions() throws Exception {
        // Initialize the database
        camionRepository.saveAndFlush(camion);

        // Get all the camionList
        restCamionMockMvc.perform(get("/api/camions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(camion.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCamion").value(hasItem(DEFAULT_ID_CAMION)))
            .andExpect(jsonPath("$.[*].numeroCamion").value(hasItem(DEFAULT_NUMERO_CAMION.toString())));
    }
    
    @Test
    @Transactional
    public void getCamion() throws Exception {
        // Initialize the database
        camionRepository.saveAndFlush(camion);

        // Get the camion
        restCamionMockMvc.perform(get("/api/camions/{id}", camion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(camion.getId().intValue()))
            .andExpect(jsonPath("$.idCamion").value(DEFAULT_ID_CAMION))
            .andExpect(jsonPath("$.numeroCamion").value(DEFAULT_NUMERO_CAMION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCamion() throws Exception {
        // Get the camion
        restCamionMockMvc.perform(get("/api/camions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCamion() throws Exception {
        // Initialize the database
        camionRepository.saveAndFlush(camion);

        int databaseSizeBeforeUpdate = camionRepository.findAll().size();

        // Update the camion
        Camion updatedCamion = camionRepository.findById(camion.getId()).get();
        // Disconnect from session so that the updates on updatedCamion are not directly saved in db
        em.detach(updatedCamion);
        updatedCamion
            .idCamion(UPDATED_ID_CAMION)
            .numeroCamion(UPDATED_NUMERO_CAMION);
        CamionDTO camionDTO = camionMapper.toDto(updatedCamion);

        restCamionMockMvc.perform(put("/api/camions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(camionDTO)))
            .andExpect(status().isOk());

        // Validate the Camion in the database
        List<Camion> camionList = camionRepository.findAll();
        assertThat(camionList).hasSize(databaseSizeBeforeUpdate);
        Camion testCamion = camionList.get(camionList.size() - 1);
        assertThat(testCamion.getIdCamion()).isEqualTo(UPDATED_ID_CAMION);
        assertThat(testCamion.getNumeroCamion()).isEqualTo(UPDATED_NUMERO_CAMION);

        // Validate the Camion in Elasticsearch
        verify(mockCamionSearchRepository, times(1)).save(testCamion);
    }

    @Test
    @Transactional
    public void updateNonExistingCamion() throws Exception {
        int databaseSizeBeforeUpdate = camionRepository.findAll().size();

        // Create the Camion
        CamionDTO camionDTO = camionMapper.toDto(camion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCamionMockMvc.perform(put("/api/camions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(camionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Camion in the database
        List<Camion> camionList = camionRepository.findAll();
        assertThat(camionList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Camion in Elasticsearch
        verify(mockCamionSearchRepository, times(0)).save(camion);
    }

    @Test
    @Transactional
    public void deleteCamion() throws Exception {
        // Initialize the database
        camionRepository.saveAndFlush(camion);

        int databaseSizeBeforeDelete = camionRepository.findAll().size();

        // Get the camion
        restCamionMockMvc.perform(delete("/api/camions/{id}", camion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Camion> camionList = camionRepository.findAll();
        assertThat(camionList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Camion in Elasticsearch
        verify(mockCamionSearchRepository, times(1)).deleteById(camion.getId());
    }

    @Test
    @Transactional
    public void searchCamion() throws Exception {
        // Initialize the database
        camionRepository.saveAndFlush(camion);
        when(mockCamionSearchRepository.search(queryStringQuery("id:" + camion.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(camion), PageRequest.of(0, 1), 1));
        // Search the camion
        restCamionMockMvc.perform(get("/api/_search/camions?query=id:" + camion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(camion.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCamion").value(hasItem(DEFAULT_ID_CAMION)))
            .andExpect(jsonPath("$.[*].numeroCamion").value(hasItem(DEFAULT_NUMERO_CAMION)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Camion.class);
        Camion camion1 = new Camion();
        camion1.setId(1L);
        Camion camion2 = new Camion();
        camion2.setId(camion1.getId());
        assertThat(camion1).isEqualTo(camion2);
        camion2.setId(2L);
        assertThat(camion1).isNotEqualTo(camion2);
        camion1.setId(null);
        assertThat(camion1).isNotEqualTo(camion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CamionDTO.class);
        CamionDTO camionDTO1 = new CamionDTO();
        camionDTO1.setId(1L);
        CamionDTO camionDTO2 = new CamionDTO();
        assertThat(camionDTO1).isNotEqualTo(camionDTO2);
        camionDTO2.setId(camionDTO1.getId());
        assertThat(camionDTO1).isEqualTo(camionDTO2);
        camionDTO2.setId(2L);
        assertThat(camionDTO1).isNotEqualTo(camionDTO2);
        camionDTO1.setId(null);
        assertThat(camionDTO1).isNotEqualTo(camionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(camionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(camionMapper.fromId(null)).isNull();
    }
}
