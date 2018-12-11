package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatStatutDossier;
import ma.co.orimex.stock.repository.AchatStatutDossierRepository;
import ma.co.orimex.stock.repository.search.AchatStatutDossierSearchRepository;
import ma.co.orimex.stock.service.AchatStatutDossierService;
import ma.co.orimex.stock.service.dto.AchatStatutDossierDTO;
import ma.co.orimex.stock.service.mapper.AchatStatutDossierMapper;
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
 * Test class for the AchatStatutDossierResource REST controller.
 *
 * @see AchatStatutDossierResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatStatutDossierResourceIntTest {

    private static final Integer DEFAULT_ID_STATUT_DOSSIER = 1;
    private static final Integer UPDATED_ID_STATUT_DOSSIER = 2;

    private static final String DEFAULT_LIBELLE_STATUT_DOSSIER = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_STATUT_DOSSIER = "BBBBBBBBBB";

    @Autowired
    private AchatStatutDossierRepository achatStatutDossierRepository;

    @Autowired
    private AchatStatutDossierMapper achatStatutDossierMapper;

    @Autowired
    private AchatStatutDossierService achatStatutDossierService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatStatutDossierSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatStatutDossierSearchRepository mockAchatStatutDossierSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatStatutDossierMockMvc;

    private AchatStatutDossier achatStatutDossier;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatStatutDossierResource achatStatutDossierResource = new AchatStatutDossierResource(achatStatutDossierService);
        this.restAchatStatutDossierMockMvc = MockMvcBuilders.standaloneSetup(achatStatutDossierResource)
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
    public static AchatStatutDossier createEntity(EntityManager em) {
        AchatStatutDossier achatStatutDossier = new AchatStatutDossier()
            .idStatutDossier(DEFAULT_ID_STATUT_DOSSIER)
            .libelleStatutDossier(DEFAULT_LIBELLE_STATUT_DOSSIER);
        return achatStatutDossier;
    }

    @Before
    public void initTest() {
        achatStatutDossier = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatStatutDossier() throws Exception {
        int databaseSizeBeforeCreate = achatStatutDossierRepository.findAll().size();

        // Create the AchatStatutDossier
        AchatStatutDossierDTO achatStatutDossierDTO = achatStatutDossierMapper.toDto(achatStatutDossier);
        restAchatStatutDossierMockMvc.perform(post("/api/achat-statut-dossiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatStatutDossierDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatStatutDossier in the database
        List<AchatStatutDossier> achatStatutDossierList = achatStatutDossierRepository.findAll();
        assertThat(achatStatutDossierList).hasSize(databaseSizeBeforeCreate + 1);
        AchatStatutDossier testAchatStatutDossier = achatStatutDossierList.get(achatStatutDossierList.size() - 1);
        assertThat(testAchatStatutDossier.getIdStatutDossier()).isEqualTo(DEFAULT_ID_STATUT_DOSSIER);
        assertThat(testAchatStatutDossier.getLibelleStatutDossier()).isEqualTo(DEFAULT_LIBELLE_STATUT_DOSSIER);

        // Validate the AchatStatutDossier in Elasticsearch
        verify(mockAchatStatutDossierSearchRepository, times(1)).save(testAchatStatutDossier);
    }

    @Test
    @Transactional
    public void createAchatStatutDossierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatStatutDossierRepository.findAll().size();

        // Create the AchatStatutDossier with an existing ID
        achatStatutDossier.setId(1L);
        AchatStatutDossierDTO achatStatutDossierDTO = achatStatutDossierMapper.toDto(achatStatutDossier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatStatutDossierMockMvc.perform(post("/api/achat-statut-dossiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatStatutDossierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatStatutDossier in the database
        List<AchatStatutDossier> achatStatutDossierList = achatStatutDossierRepository.findAll();
        assertThat(achatStatutDossierList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatStatutDossier in Elasticsearch
        verify(mockAchatStatutDossierSearchRepository, times(0)).save(achatStatutDossier);
    }

    @Test
    @Transactional
    public void getAllAchatStatutDossiers() throws Exception {
        // Initialize the database
        achatStatutDossierRepository.saveAndFlush(achatStatutDossier);

        // Get all the achatStatutDossierList
        restAchatStatutDossierMockMvc.perform(get("/api/achat-statut-dossiers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatStatutDossier.getId().intValue())))
            .andExpect(jsonPath("$.[*].idStatutDossier").value(hasItem(DEFAULT_ID_STATUT_DOSSIER)))
            .andExpect(jsonPath("$.[*].libelleStatutDossier").value(hasItem(DEFAULT_LIBELLE_STATUT_DOSSIER.toString())));
    }
    
    @Test
    @Transactional
    public void getAchatStatutDossier() throws Exception {
        // Initialize the database
        achatStatutDossierRepository.saveAndFlush(achatStatutDossier);

        // Get the achatStatutDossier
        restAchatStatutDossierMockMvc.perform(get("/api/achat-statut-dossiers/{id}", achatStatutDossier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatStatutDossier.getId().intValue()))
            .andExpect(jsonPath("$.idStatutDossier").value(DEFAULT_ID_STATUT_DOSSIER))
            .andExpect(jsonPath("$.libelleStatutDossier").value(DEFAULT_LIBELLE_STATUT_DOSSIER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatStatutDossier() throws Exception {
        // Get the achatStatutDossier
        restAchatStatutDossierMockMvc.perform(get("/api/achat-statut-dossiers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatStatutDossier() throws Exception {
        // Initialize the database
        achatStatutDossierRepository.saveAndFlush(achatStatutDossier);

        int databaseSizeBeforeUpdate = achatStatutDossierRepository.findAll().size();

        // Update the achatStatutDossier
        AchatStatutDossier updatedAchatStatutDossier = achatStatutDossierRepository.findById(achatStatutDossier.getId()).get();
        // Disconnect from session so that the updates on updatedAchatStatutDossier are not directly saved in db
        em.detach(updatedAchatStatutDossier);
        updatedAchatStatutDossier
            .idStatutDossier(UPDATED_ID_STATUT_DOSSIER)
            .libelleStatutDossier(UPDATED_LIBELLE_STATUT_DOSSIER);
        AchatStatutDossierDTO achatStatutDossierDTO = achatStatutDossierMapper.toDto(updatedAchatStatutDossier);

        restAchatStatutDossierMockMvc.perform(put("/api/achat-statut-dossiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatStatutDossierDTO)))
            .andExpect(status().isOk());

        // Validate the AchatStatutDossier in the database
        List<AchatStatutDossier> achatStatutDossierList = achatStatutDossierRepository.findAll();
        assertThat(achatStatutDossierList).hasSize(databaseSizeBeforeUpdate);
        AchatStatutDossier testAchatStatutDossier = achatStatutDossierList.get(achatStatutDossierList.size() - 1);
        assertThat(testAchatStatutDossier.getIdStatutDossier()).isEqualTo(UPDATED_ID_STATUT_DOSSIER);
        assertThat(testAchatStatutDossier.getLibelleStatutDossier()).isEqualTo(UPDATED_LIBELLE_STATUT_DOSSIER);

        // Validate the AchatStatutDossier in Elasticsearch
        verify(mockAchatStatutDossierSearchRepository, times(1)).save(testAchatStatutDossier);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatStatutDossier() throws Exception {
        int databaseSizeBeforeUpdate = achatStatutDossierRepository.findAll().size();

        // Create the AchatStatutDossier
        AchatStatutDossierDTO achatStatutDossierDTO = achatStatutDossierMapper.toDto(achatStatutDossier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatStatutDossierMockMvc.perform(put("/api/achat-statut-dossiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatStatutDossierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatStatutDossier in the database
        List<AchatStatutDossier> achatStatutDossierList = achatStatutDossierRepository.findAll();
        assertThat(achatStatutDossierList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatStatutDossier in Elasticsearch
        verify(mockAchatStatutDossierSearchRepository, times(0)).save(achatStatutDossier);
    }

    @Test
    @Transactional
    public void deleteAchatStatutDossier() throws Exception {
        // Initialize the database
        achatStatutDossierRepository.saveAndFlush(achatStatutDossier);

        int databaseSizeBeforeDelete = achatStatutDossierRepository.findAll().size();

        // Get the achatStatutDossier
        restAchatStatutDossierMockMvc.perform(delete("/api/achat-statut-dossiers/{id}", achatStatutDossier.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatStatutDossier> achatStatutDossierList = achatStatutDossierRepository.findAll();
        assertThat(achatStatutDossierList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatStatutDossier in Elasticsearch
        verify(mockAchatStatutDossierSearchRepository, times(1)).deleteById(achatStatutDossier.getId());
    }

    @Test
    @Transactional
    public void searchAchatStatutDossier() throws Exception {
        // Initialize the database
        achatStatutDossierRepository.saveAndFlush(achatStatutDossier);
        when(mockAchatStatutDossierSearchRepository.search(queryStringQuery("id:" + achatStatutDossier.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatStatutDossier), PageRequest.of(0, 1), 1));
        // Search the achatStatutDossier
        restAchatStatutDossierMockMvc.perform(get("/api/_search/achat-statut-dossiers?query=id:" + achatStatutDossier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatStatutDossier.getId().intValue())))
            .andExpect(jsonPath("$.[*].idStatutDossier").value(hasItem(DEFAULT_ID_STATUT_DOSSIER)))
            .andExpect(jsonPath("$.[*].libelleStatutDossier").value(hasItem(DEFAULT_LIBELLE_STATUT_DOSSIER)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatStatutDossier.class);
        AchatStatutDossier achatStatutDossier1 = new AchatStatutDossier();
        achatStatutDossier1.setId(1L);
        AchatStatutDossier achatStatutDossier2 = new AchatStatutDossier();
        achatStatutDossier2.setId(achatStatutDossier1.getId());
        assertThat(achatStatutDossier1).isEqualTo(achatStatutDossier2);
        achatStatutDossier2.setId(2L);
        assertThat(achatStatutDossier1).isNotEqualTo(achatStatutDossier2);
        achatStatutDossier1.setId(null);
        assertThat(achatStatutDossier1).isNotEqualTo(achatStatutDossier2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatStatutDossierDTO.class);
        AchatStatutDossierDTO achatStatutDossierDTO1 = new AchatStatutDossierDTO();
        achatStatutDossierDTO1.setId(1L);
        AchatStatutDossierDTO achatStatutDossierDTO2 = new AchatStatutDossierDTO();
        assertThat(achatStatutDossierDTO1).isNotEqualTo(achatStatutDossierDTO2);
        achatStatutDossierDTO2.setId(achatStatutDossierDTO1.getId());
        assertThat(achatStatutDossierDTO1).isEqualTo(achatStatutDossierDTO2);
        achatStatutDossierDTO2.setId(2L);
        assertThat(achatStatutDossierDTO1).isNotEqualTo(achatStatutDossierDTO2);
        achatStatutDossierDTO1.setId(null);
        assertThat(achatStatutDossierDTO1).isNotEqualTo(achatStatutDossierDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatStatutDossierMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatStatutDossierMapper.fromId(null)).isNull();
    }
}
