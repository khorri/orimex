package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatTypePaiement;
import ma.co.orimex.stock.repository.AchatTypePaiementRepository;
import ma.co.orimex.stock.repository.search.AchatTypePaiementSearchRepository;
import ma.co.orimex.stock.service.AchatTypePaiementService;
import ma.co.orimex.stock.service.dto.AchatTypePaiementDTO;
import ma.co.orimex.stock.service.mapper.AchatTypePaiementMapper;
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
 * Test class for the AchatTypePaiementResource REST controller.
 *
 * @see AchatTypePaiementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatTypePaiementResourceIntTest {

    private static final Integer DEFAULT_ID_TYPE_PAIEMENT = 1;
    private static final Integer UPDATED_ID_TYPE_PAIEMENT = 2;

    private static final String DEFAULT_LIBELLE_TYPE_PAIEMENT = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_TYPE_PAIEMENT = "BBBBBBBBBB";

    @Autowired
    private AchatTypePaiementRepository achatTypePaiementRepository;

    @Autowired
    private AchatTypePaiementMapper achatTypePaiementMapper;

    @Autowired
    private AchatTypePaiementService achatTypePaiementService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatTypePaiementSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatTypePaiementSearchRepository mockAchatTypePaiementSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatTypePaiementMockMvc;

    private AchatTypePaiement achatTypePaiement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatTypePaiementResource achatTypePaiementResource = new AchatTypePaiementResource(achatTypePaiementService);
        this.restAchatTypePaiementMockMvc = MockMvcBuilders.standaloneSetup(achatTypePaiementResource)
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
    public static AchatTypePaiement createEntity(EntityManager em) {
        AchatTypePaiement achatTypePaiement = new AchatTypePaiement()
            .idTypePaiement(DEFAULT_ID_TYPE_PAIEMENT)
            .libelleTypePaiement(DEFAULT_LIBELLE_TYPE_PAIEMENT);
        return achatTypePaiement;
    }

    @Before
    public void initTest() {
        achatTypePaiement = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatTypePaiement() throws Exception {
        int databaseSizeBeforeCreate = achatTypePaiementRepository.findAll().size();

        // Create the AchatTypePaiement
        AchatTypePaiementDTO achatTypePaiementDTO = achatTypePaiementMapper.toDto(achatTypePaiement);
        restAchatTypePaiementMockMvc.perform(post("/api/achat-type-paiements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatTypePaiementDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatTypePaiement in the database
        List<AchatTypePaiement> achatTypePaiementList = achatTypePaiementRepository.findAll();
        assertThat(achatTypePaiementList).hasSize(databaseSizeBeforeCreate + 1);
        AchatTypePaiement testAchatTypePaiement = achatTypePaiementList.get(achatTypePaiementList.size() - 1);
        assertThat(testAchatTypePaiement.getIdTypePaiement()).isEqualTo(DEFAULT_ID_TYPE_PAIEMENT);
        assertThat(testAchatTypePaiement.getLibelleTypePaiement()).isEqualTo(DEFAULT_LIBELLE_TYPE_PAIEMENT);

        // Validate the AchatTypePaiement in Elasticsearch
        verify(mockAchatTypePaiementSearchRepository, times(1)).save(testAchatTypePaiement);
    }

    @Test
    @Transactional
    public void createAchatTypePaiementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatTypePaiementRepository.findAll().size();

        // Create the AchatTypePaiement with an existing ID
        achatTypePaiement.setId(1L);
        AchatTypePaiementDTO achatTypePaiementDTO = achatTypePaiementMapper.toDto(achatTypePaiement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatTypePaiementMockMvc.perform(post("/api/achat-type-paiements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatTypePaiementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatTypePaiement in the database
        List<AchatTypePaiement> achatTypePaiementList = achatTypePaiementRepository.findAll();
        assertThat(achatTypePaiementList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatTypePaiement in Elasticsearch
        verify(mockAchatTypePaiementSearchRepository, times(0)).save(achatTypePaiement);
    }

    @Test
    @Transactional
    public void getAllAchatTypePaiements() throws Exception {
        // Initialize the database
        achatTypePaiementRepository.saveAndFlush(achatTypePaiement);

        // Get all the achatTypePaiementList
        restAchatTypePaiementMockMvc.perform(get("/api/achat-type-paiements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatTypePaiement.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTypePaiement").value(hasItem(DEFAULT_ID_TYPE_PAIEMENT)))
            .andExpect(jsonPath("$.[*].libelleTypePaiement").value(hasItem(DEFAULT_LIBELLE_TYPE_PAIEMENT.toString())));
    }
    
    @Test
    @Transactional
    public void getAchatTypePaiement() throws Exception {
        // Initialize the database
        achatTypePaiementRepository.saveAndFlush(achatTypePaiement);

        // Get the achatTypePaiement
        restAchatTypePaiementMockMvc.perform(get("/api/achat-type-paiements/{id}", achatTypePaiement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatTypePaiement.getId().intValue()))
            .andExpect(jsonPath("$.idTypePaiement").value(DEFAULT_ID_TYPE_PAIEMENT))
            .andExpect(jsonPath("$.libelleTypePaiement").value(DEFAULT_LIBELLE_TYPE_PAIEMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatTypePaiement() throws Exception {
        // Get the achatTypePaiement
        restAchatTypePaiementMockMvc.perform(get("/api/achat-type-paiements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatTypePaiement() throws Exception {
        // Initialize the database
        achatTypePaiementRepository.saveAndFlush(achatTypePaiement);

        int databaseSizeBeforeUpdate = achatTypePaiementRepository.findAll().size();

        // Update the achatTypePaiement
        AchatTypePaiement updatedAchatTypePaiement = achatTypePaiementRepository.findById(achatTypePaiement.getId()).get();
        // Disconnect from session so that the updates on updatedAchatTypePaiement are not directly saved in db
        em.detach(updatedAchatTypePaiement);
        updatedAchatTypePaiement
            .idTypePaiement(UPDATED_ID_TYPE_PAIEMENT)
            .libelleTypePaiement(UPDATED_LIBELLE_TYPE_PAIEMENT);
        AchatTypePaiementDTO achatTypePaiementDTO = achatTypePaiementMapper.toDto(updatedAchatTypePaiement);

        restAchatTypePaiementMockMvc.perform(put("/api/achat-type-paiements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatTypePaiementDTO)))
            .andExpect(status().isOk());

        // Validate the AchatTypePaiement in the database
        List<AchatTypePaiement> achatTypePaiementList = achatTypePaiementRepository.findAll();
        assertThat(achatTypePaiementList).hasSize(databaseSizeBeforeUpdate);
        AchatTypePaiement testAchatTypePaiement = achatTypePaiementList.get(achatTypePaiementList.size() - 1);
        assertThat(testAchatTypePaiement.getIdTypePaiement()).isEqualTo(UPDATED_ID_TYPE_PAIEMENT);
        assertThat(testAchatTypePaiement.getLibelleTypePaiement()).isEqualTo(UPDATED_LIBELLE_TYPE_PAIEMENT);

        // Validate the AchatTypePaiement in Elasticsearch
        verify(mockAchatTypePaiementSearchRepository, times(1)).save(testAchatTypePaiement);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatTypePaiement() throws Exception {
        int databaseSizeBeforeUpdate = achatTypePaiementRepository.findAll().size();

        // Create the AchatTypePaiement
        AchatTypePaiementDTO achatTypePaiementDTO = achatTypePaiementMapper.toDto(achatTypePaiement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatTypePaiementMockMvc.perform(put("/api/achat-type-paiements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatTypePaiementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatTypePaiement in the database
        List<AchatTypePaiement> achatTypePaiementList = achatTypePaiementRepository.findAll();
        assertThat(achatTypePaiementList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatTypePaiement in Elasticsearch
        verify(mockAchatTypePaiementSearchRepository, times(0)).save(achatTypePaiement);
    }

    @Test
    @Transactional
    public void deleteAchatTypePaiement() throws Exception {
        // Initialize the database
        achatTypePaiementRepository.saveAndFlush(achatTypePaiement);

        int databaseSizeBeforeDelete = achatTypePaiementRepository.findAll().size();

        // Get the achatTypePaiement
        restAchatTypePaiementMockMvc.perform(delete("/api/achat-type-paiements/{id}", achatTypePaiement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatTypePaiement> achatTypePaiementList = achatTypePaiementRepository.findAll();
        assertThat(achatTypePaiementList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatTypePaiement in Elasticsearch
        verify(mockAchatTypePaiementSearchRepository, times(1)).deleteById(achatTypePaiement.getId());
    }

    @Test
    @Transactional
    public void searchAchatTypePaiement() throws Exception {
        // Initialize the database
        achatTypePaiementRepository.saveAndFlush(achatTypePaiement);
        when(mockAchatTypePaiementSearchRepository.search(queryStringQuery("id:" + achatTypePaiement.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatTypePaiement), PageRequest.of(0, 1), 1));
        // Search the achatTypePaiement
        restAchatTypePaiementMockMvc.perform(get("/api/_search/achat-type-paiements?query=id:" + achatTypePaiement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatTypePaiement.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTypePaiement").value(hasItem(DEFAULT_ID_TYPE_PAIEMENT)))
            .andExpect(jsonPath("$.[*].libelleTypePaiement").value(hasItem(DEFAULT_LIBELLE_TYPE_PAIEMENT)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatTypePaiement.class);
        AchatTypePaiement achatTypePaiement1 = new AchatTypePaiement();
        achatTypePaiement1.setId(1L);
        AchatTypePaiement achatTypePaiement2 = new AchatTypePaiement();
        achatTypePaiement2.setId(achatTypePaiement1.getId());
        assertThat(achatTypePaiement1).isEqualTo(achatTypePaiement2);
        achatTypePaiement2.setId(2L);
        assertThat(achatTypePaiement1).isNotEqualTo(achatTypePaiement2);
        achatTypePaiement1.setId(null);
        assertThat(achatTypePaiement1).isNotEqualTo(achatTypePaiement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatTypePaiementDTO.class);
        AchatTypePaiementDTO achatTypePaiementDTO1 = new AchatTypePaiementDTO();
        achatTypePaiementDTO1.setId(1L);
        AchatTypePaiementDTO achatTypePaiementDTO2 = new AchatTypePaiementDTO();
        assertThat(achatTypePaiementDTO1).isNotEqualTo(achatTypePaiementDTO2);
        achatTypePaiementDTO2.setId(achatTypePaiementDTO1.getId());
        assertThat(achatTypePaiementDTO1).isEqualTo(achatTypePaiementDTO2);
        achatTypePaiementDTO2.setId(2L);
        assertThat(achatTypePaiementDTO1).isNotEqualTo(achatTypePaiementDTO2);
        achatTypePaiementDTO1.setId(null);
        assertThat(achatTypePaiementDTO1).isNotEqualTo(achatTypePaiementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatTypePaiementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatTypePaiementMapper.fromId(null)).isNull();
    }
}
