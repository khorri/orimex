package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatDevise;
import ma.co.orimex.stock.repository.AchatDeviseRepository;
import ma.co.orimex.stock.repository.search.AchatDeviseSearchRepository;
import ma.co.orimex.stock.service.AchatDeviseService;
import ma.co.orimex.stock.service.dto.AchatDeviseDTO;
import ma.co.orimex.stock.service.mapper.AchatDeviseMapper;
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
 * Test class for the AchatDeviseResource REST controller.
 *
 * @see AchatDeviseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatDeviseResourceIntTest {

    private static final Integer DEFAULT_ID_DEVISE = 1;
    private static final Integer UPDATED_ID_DEVISE = 2;

    private static final String DEFAULT_CODE_DEVISE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_DEVISE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_DEVISE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_DEVISE = "BBBBBBBBBB";

    @Autowired
    private AchatDeviseRepository achatDeviseRepository;

    @Autowired
    private AchatDeviseMapper achatDeviseMapper;

    @Autowired
    private AchatDeviseService achatDeviseService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatDeviseSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatDeviseSearchRepository mockAchatDeviseSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatDeviseMockMvc;

    private AchatDevise achatDevise;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatDeviseResource achatDeviseResource = new AchatDeviseResource(achatDeviseService);
        this.restAchatDeviseMockMvc = MockMvcBuilders.standaloneSetup(achatDeviseResource)
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
    public static AchatDevise createEntity(EntityManager em) {
        AchatDevise achatDevise = new AchatDevise()
            .idDevise(DEFAULT_ID_DEVISE)
            .codeDevise(DEFAULT_CODE_DEVISE)
            .libelleDevise(DEFAULT_LIBELLE_DEVISE);
        return achatDevise;
    }

    @Before
    public void initTest() {
        achatDevise = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatDevise() throws Exception {
        int databaseSizeBeforeCreate = achatDeviseRepository.findAll().size();

        // Create the AchatDevise
        AchatDeviseDTO achatDeviseDTO = achatDeviseMapper.toDto(achatDevise);
        restAchatDeviseMockMvc.perform(post("/api/achat-devises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatDeviseDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatDevise in the database
        List<AchatDevise> achatDeviseList = achatDeviseRepository.findAll();
        assertThat(achatDeviseList).hasSize(databaseSizeBeforeCreate + 1);
        AchatDevise testAchatDevise = achatDeviseList.get(achatDeviseList.size() - 1);
        assertThat(testAchatDevise.getIdDevise()).isEqualTo(DEFAULT_ID_DEVISE);
        assertThat(testAchatDevise.getCodeDevise()).isEqualTo(DEFAULT_CODE_DEVISE);
        assertThat(testAchatDevise.getLibelleDevise()).isEqualTo(DEFAULT_LIBELLE_DEVISE);

        // Validate the AchatDevise in Elasticsearch
        verify(mockAchatDeviseSearchRepository, times(1)).save(testAchatDevise);
    }

    @Test
    @Transactional
    public void createAchatDeviseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatDeviseRepository.findAll().size();

        // Create the AchatDevise with an existing ID
        achatDevise.setId(1L);
        AchatDeviseDTO achatDeviseDTO = achatDeviseMapper.toDto(achatDevise);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatDeviseMockMvc.perform(post("/api/achat-devises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatDeviseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatDevise in the database
        List<AchatDevise> achatDeviseList = achatDeviseRepository.findAll();
        assertThat(achatDeviseList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatDevise in Elasticsearch
        verify(mockAchatDeviseSearchRepository, times(0)).save(achatDevise);
    }

    @Test
    @Transactional
    public void getAllAchatDevises() throws Exception {
        // Initialize the database
        achatDeviseRepository.saveAndFlush(achatDevise);

        // Get all the achatDeviseList
        restAchatDeviseMockMvc.perform(get("/api/achat-devises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatDevise.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDevise").value(hasItem(DEFAULT_ID_DEVISE)))
            .andExpect(jsonPath("$.[*].codeDevise").value(hasItem(DEFAULT_CODE_DEVISE.toString())))
            .andExpect(jsonPath("$.[*].libelleDevise").value(hasItem(DEFAULT_LIBELLE_DEVISE.toString())));
    }
    
    @Test
    @Transactional
    public void getAchatDevise() throws Exception {
        // Initialize the database
        achatDeviseRepository.saveAndFlush(achatDevise);

        // Get the achatDevise
        restAchatDeviseMockMvc.perform(get("/api/achat-devises/{id}", achatDevise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatDevise.getId().intValue()))
            .andExpect(jsonPath("$.idDevise").value(DEFAULT_ID_DEVISE))
            .andExpect(jsonPath("$.codeDevise").value(DEFAULT_CODE_DEVISE.toString()))
            .andExpect(jsonPath("$.libelleDevise").value(DEFAULT_LIBELLE_DEVISE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatDevise() throws Exception {
        // Get the achatDevise
        restAchatDeviseMockMvc.perform(get("/api/achat-devises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatDevise() throws Exception {
        // Initialize the database
        achatDeviseRepository.saveAndFlush(achatDevise);

        int databaseSizeBeforeUpdate = achatDeviseRepository.findAll().size();

        // Update the achatDevise
        AchatDevise updatedAchatDevise = achatDeviseRepository.findById(achatDevise.getId()).get();
        // Disconnect from session so that the updates on updatedAchatDevise are not directly saved in db
        em.detach(updatedAchatDevise);
        updatedAchatDevise
            .idDevise(UPDATED_ID_DEVISE)
            .codeDevise(UPDATED_CODE_DEVISE)
            .libelleDevise(UPDATED_LIBELLE_DEVISE);
        AchatDeviseDTO achatDeviseDTO = achatDeviseMapper.toDto(updatedAchatDevise);

        restAchatDeviseMockMvc.perform(put("/api/achat-devises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatDeviseDTO)))
            .andExpect(status().isOk());

        // Validate the AchatDevise in the database
        List<AchatDevise> achatDeviseList = achatDeviseRepository.findAll();
        assertThat(achatDeviseList).hasSize(databaseSizeBeforeUpdate);
        AchatDevise testAchatDevise = achatDeviseList.get(achatDeviseList.size() - 1);
        assertThat(testAchatDevise.getIdDevise()).isEqualTo(UPDATED_ID_DEVISE);
        assertThat(testAchatDevise.getCodeDevise()).isEqualTo(UPDATED_CODE_DEVISE);
        assertThat(testAchatDevise.getLibelleDevise()).isEqualTo(UPDATED_LIBELLE_DEVISE);

        // Validate the AchatDevise in Elasticsearch
        verify(mockAchatDeviseSearchRepository, times(1)).save(testAchatDevise);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatDevise() throws Exception {
        int databaseSizeBeforeUpdate = achatDeviseRepository.findAll().size();

        // Create the AchatDevise
        AchatDeviseDTO achatDeviseDTO = achatDeviseMapper.toDto(achatDevise);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatDeviseMockMvc.perform(put("/api/achat-devises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatDeviseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatDevise in the database
        List<AchatDevise> achatDeviseList = achatDeviseRepository.findAll();
        assertThat(achatDeviseList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatDevise in Elasticsearch
        verify(mockAchatDeviseSearchRepository, times(0)).save(achatDevise);
    }

    @Test
    @Transactional
    public void deleteAchatDevise() throws Exception {
        // Initialize the database
        achatDeviseRepository.saveAndFlush(achatDevise);

        int databaseSizeBeforeDelete = achatDeviseRepository.findAll().size();

        // Get the achatDevise
        restAchatDeviseMockMvc.perform(delete("/api/achat-devises/{id}", achatDevise.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatDevise> achatDeviseList = achatDeviseRepository.findAll();
        assertThat(achatDeviseList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatDevise in Elasticsearch
        verify(mockAchatDeviseSearchRepository, times(1)).deleteById(achatDevise.getId());
    }

    @Test
    @Transactional
    public void searchAchatDevise() throws Exception {
        // Initialize the database
        achatDeviseRepository.saveAndFlush(achatDevise);
        when(mockAchatDeviseSearchRepository.search(queryStringQuery("id:" + achatDevise.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatDevise), PageRequest.of(0, 1), 1));
        // Search the achatDevise
        restAchatDeviseMockMvc.perform(get("/api/_search/achat-devises?query=id:" + achatDevise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatDevise.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDevise").value(hasItem(DEFAULT_ID_DEVISE)))
            .andExpect(jsonPath("$.[*].codeDevise").value(hasItem(DEFAULT_CODE_DEVISE)))
            .andExpect(jsonPath("$.[*].libelleDevise").value(hasItem(DEFAULT_LIBELLE_DEVISE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatDevise.class);
        AchatDevise achatDevise1 = new AchatDevise();
        achatDevise1.setId(1L);
        AchatDevise achatDevise2 = new AchatDevise();
        achatDevise2.setId(achatDevise1.getId());
        assertThat(achatDevise1).isEqualTo(achatDevise2);
        achatDevise2.setId(2L);
        assertThat(achatDevise1).isNotEqualTo(achatDevise2);
        achatDevise1.setId(null);
        assertThat(achatDevise1).isNotEqualTo(achatDevise2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatDeviseDTO.class);
        AchatDeviseDTO achatDeviseDTO1 = new AchatDeviseDTO();
        achatDeviseDTO1.setId(1L);
        AchatDeviseDTO achatDeviseDTO2 = new AchatDeviseDTO();
        assertThat(achatDeviseDTO1).isNotEqualTo(achatDeviseDTO2);
        achatDeviseDTO2.setId(achatDeviseDTO1.getId());
        assertThat(achatDeviseDTO1).isEqualTo(achatDeviseDTO2);
        achatDeviseDTO2.setId(2L);
        assertThat(achatDeviseDTO1).isNotEqualTo(achatDeviseDTO2);
        achatDeviseDTO1.setId(null);
        assertThat(achatDeviseDTO1).isNotEqualTo(achatDeviseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatDeviseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatDeviseMapper.fromId(null)).isNull();
    }
}
