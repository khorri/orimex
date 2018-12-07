package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatArrivage;
import ma.co.orimex.stock.repository.AchatArrivageRepository;
import ma.co.orimex.stock.repository.search.AchatArrivageSearchRepository;
import ma.co.orimex.stock.service.AchatArrivageService;
import ma.co.orimex.stock.service.dto.AchatArrivageDTO;
import ma.co.orimex.stock.service.mapper.AchatArrivageMapper;
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
 * Test class for the AchatArrivageResource REST controller.
 *
 * @see AchatArrivageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatArrivageResourceIntTest {

    @Autowired
    private AchatArrivageRepository achatArrivageRepository;

    @Autowired
    private AchatArrivageMapper achatArrivageMapper;

    @Autowired
    private AchatArrivageService achatArrivageService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatArrivageSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatArrivageSearchRepository mockAchatArrivageSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatArrivageMockMvc;

    private AchatArrivage achatArrivage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatArrivageResource achatArrivageResource = new AchatArrivageResource(achatArrivageService);
        this.restAchatArrivageMockMvc = MockMvcBuilders.standaloneSetup(achatArrivageResource)
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
    public static AchatArrivage createEntity(EntityManager em) {
        AchatArrivage achatArrivage = new AchatArrivage();
        return achatArrivage;
    }

    @Before
    public void initTest() {
        achatArrivage = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatArrivage() throws Exception {
        int databaseSizeBeforeCreate = achatArrivageRepository.findAll().size();

        // Create the AchatArrivage
        AchatArrivageDTO achatArrivageDTO = achatArrivageMapper.toDto(achatArrivage);
        restAchatArrivageMockMvc.perform(post("/api/achat-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArrivageDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatArrivage in the database
        List<AchatArrivage> achatArrivageList = achatArrivageRepository.findAll();
        assertThat(achatArrivageList).hasSize(databaseSizeBeforeCreate + 1);
        AchatArrivage testAchatArrivage = achatArrivageList.get(achatArrivageList.size() - 1);

        // Validate the AchatArrivage in Elasticsearch
        verify(mockAchatArrivageSearchRepository, times(1)).save(testAchatArrivage);
    }

    @Test
    @Transactional
    public void createAchatArrivageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatArrivageRepository.findAll().size();

        // Create the AchatArrivage with an existing ID
        achatArrivage.setId(1L);
        AchatArrivageDTO achatArrivageDTO = achatArrivageMapper.toDto(achatArrivage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatArrivageMockMvc.perform(post("/api/achat-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArrivageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatArrivage in the database
        List<AchatArrivage> achatArrivageList = achatArrivageRepository.findAll();
        assertThat(achatArrivageList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatArrivage in Elasticsearch
        verify(mockAchatArrivageSearchRepository, times(0)).save(achatArrivage);
    }

    @Test
    @Transactional
    public void getAllAchatArrivages() throws Exception {
        // Initialize the database
        achatArrivageRepository.saveAndFlush(achatArrivage);

        // Get all the achatArrivageList
        restAchatArrivageMockMvc.perform(get("/api/achat-arrivages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatArrivage.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getAchatArrivage() throws Exception {
        // Initialize the database
        achatArrivageRepository.saveAndFlush(achatArrivage);

        // Get the achatArrivage
        restAchatArrivageMockMvc.perform(get("/api/achat-arrivages/{id}", achatArrivage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatArrivage.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatArrivage() throws Exception {
        // Get the achatArrivage
        restAchatArrivageMockMvc.perform(get("/api/achat-arrivages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatArrivage() throws Exception {
        // Initialize the database
        achatArrivageRepository.saveAndFlush(achatArrivage);

        int databaseSizeBeforeUpdate = achatArrivageRepository.findAll().size();

        // Update the achatArrivage
        AchatArrivage updatedAchatArrivage = achatArrivageRepository.findById(achatArrivage.getId()).get();
        // Disconnect from session so that the updates on updatedAchatArrivage are not directly saved in db
        em.detach(updatedAchatArrivage);
        AchatArrivageDTO achatArrivageDTO = achatArrivageMapper.toDto(updatedAchatArrivage);

        restAchatArrivageMockMvc.perform(put("/api/achat-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArrivageDTO)))
            .andExpect(status().isOk());

        // Validate the AchatArrivage in the database
        List<AchatArrivage> achatArrivageList = achatArrivageRepository.findAll();
        assertThat(achatArrivageList).hasSize(databaseSizeBeforeUpdate);
        AchatArrivage testAchatArrivage = achatArrivageList.get(achatArrivageList.size() - 1);

        // Validate the AchatArrivage in Elasticsearch
        verify(mockAchatArrivageSearchRepository, times(1)).save(testAchatArrivage);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatArrivage() throws Exception {
        int databaseSizeBeforeUpdate = achatArrivageRepository.findAll().size();

        // Create the AchatArrivage
        AchatArrivageDTO achatArrivageDTO = achatArrivageMapper.toDto(achatArrivage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatArrivageMockMvc.perform(put("/api/achat-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArrivageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatArrivage in the database
        List<AchatArrivage> achatArrivageList = achatArrivageRepository.findAll();
        assertThat(achatArrivageList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatArrivage in Elasticsearch
        verify(mockAchatArrivageSearchRepository, times(0)).save(achatArrivage);
    }

    @Test
    @Transactional
    public void deleteAchatArrivage() throws Exception {
        // Initialize the database
        achatArrivageRepository.saveAndFlush(achatArrivage);

        int databaseSizeBeforeDelete = achatArrivageRepository.findAll().size();

        // Get the achatArrivage
        restAchatArrivageMockMvc.perform(delete("/api/achat-arrivages/{id}", achatArrivage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatArrivage> achatArrivageList = achatArrivageRepository.findAll();
        assertThat(achatArrivageList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatArrivage in Elasticsearch
        verify(mockAchatArrivageSearchRepository, times(1)).deleteById(achatArrivage.getId());
    }

    @Test
    @Transactional
    public void searchAchatArrivage() throws Exception {
        // Initialize the database
        achatArrivageRepository.saveAndFlush(achatArrivage);
        when(mockAchatArrivageSearchRepository.search(queryStringQuery("id:" + achatArrivage.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatArrivage), PageRequest.of(0, 1), 1));
        // Search the achatArrivage
        restAchatArrivageMockMvc.perform(get("/api/_search/achat-arrivages?query=id:" + achatArrivage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatArrivage.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatArrivage.class);
        AchatArrivage achatArrivage1 = new AchatArrivage();
        achatArrivage1.setId(1L);
        AchatArrivage achatArrivage2 = new AchatArrivage();
        achatArrivage2.setId(achatArrivage1.getId());
        assertThat(achatArrivage1).isEqualTo(achatArrivage2);
        achatArrivage2.setId(2L);
        assertThat(achatArrivage1).isNotEqualTo(achatArrivage2);
        achatArrivage1.setId(null);
        assertThat(achatArrivage1).isNotEqualTo(achatArrivage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatArrivageDTO.class);
        AchatArrivageDTO achatArrivageDTO1 = new AchatArrivageDTO();
        achatArrivageDTO1.setId(1L);
        AchatArrivageDTO achatArrivageDTO2 = new AchatArrivageDTO();
        assertThat(achatArrivageDTO1).isNotEqualTo(achatArrivageDTO2);
        achatArrivageDTO2.setId(achatArrivageDTO1.getId());
        assertThat(achatArrivageDTO1).isEqualTo(achatArrivageDTO2);
        achatArrivageDTO2.setId(2L);
        assertThat(achatArrivageDTO1).isNotEqualTo(achatArrivageDTO2);
        achatArrivageDTO1.setId(null);
        assertThat(achatArrivageDTO1).isNotEqualTo(achatArrivageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatArrivageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatArrivageMapper.fromId(null)).isNull();
    }
}
