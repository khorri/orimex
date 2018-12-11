package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatConteneurArrivage;
import ma.co.orimex.stock.repository.AchatConteneurArrivageRepository;
import ma.co.orimex.stock.repository.search.AchatConteneurArrivageSearchRepository;
import ma.co.orimex.stock.service.AchatConteneurArrivageService;
import ma.co.orimex.stock.service.dto.AchatConteneurArrivageDTO;
import ma.co.orimex.stock.service.mapper.AchatConteneurArrivageMapper;
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
import java.math.BigDecimal;
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
 * Test class for the AchatConteneurArrivageResource REST controller.
 *
 * @see AchatConteneurArrivageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatConteneurArrivageResourceIntTest {

    private static final Integer DEFAULT_ID_CONTENEUR_ARRIVAGE = 1;
    private static final Integer UPDATED_ID_CONTENEUR_ARRIVAGE = 2;

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(2);

    private static final String DEFAULT_NUMERO_CONTENEURS = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CONTENEURS = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_SEQUENCE = 1;
    private static final Integer UPDATED_NUMERO_SEQUENCE = 2;

    private static final BigDecimal DEFAULT_POIDS = new BigDecimal(1);
    private static final BigDecimal UPDATED_POIDS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QUANTITE = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTITE = new BigDecimal(2);

    @Autowired
    private AchatConteneurArrivageRepository achatConteneurArrivageRepository;

    @Autowired
    private AchatConteneurArrivageMapper achatConteneurArrivageMapper;

    @Autowired
    private AchatConteneurArrivageService achatConteneurArrivageService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatConteneurArrivageSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatConteneurArrivageSearchRepository mockAchatConteneurArrivageSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatConteneurArrivageMockMvc;

    private AchatConteneurArrivage achatConteneurArrivage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatConteneurArrivageResource achatConteneurArrivageResource = new AchatConteneurArrivageResource(achatConteneurArrivageService);
        this.restAchatConteneurArrivageMockMvc = MockMvcBuilders.standaloneSetup(achatConteneurArrivageResource)
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
    public static AchatConteneurArrivage createEntity(EntityManager em) {
        AchatConteneurArrivage achatConteneurArrivage = new AchatConteneurArrivage()
            .idConteneurArrivage(DEFAULT_ID_CONTENEUR_ARRIVAGE)
            .montant(DEFAULT_MONTANT)
            .numeroConteneurs(DEFAULT_NUMERO_CONTENEURS)
            .numeroSequence(DEFAULT_NUMERO_SEQUENCE)
            .poids(DEFAULT_POIDS)
            .quantite(DEFAULT_QUANTITE);
        return achatConteneurArrivage;
    }

    @Before
    public void initTest() {
        achatConteneurArrivage = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatConteneurArrivage() throws Exception {
        int databaseSizeBeforeCreate = achatConteneurArrivageRepository.findAll().size();

        // Create the AchatConteneurArrivage
        AchatConteneurArrivageDTO achatConteneurArrivageDTO = achatConteneurArrivageMapper.toDto(achatConteneurArrivage);
        restAchatConteneurArrivageMockMvc.perform(post("/api/achat-conteneur-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatConteneurArrivageDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatConteneurArrivage in the database
        List<AchatConteneurArrivage> achatConteneurArrivageList = achatConteneurArrivageRepository.findAll();
        assertThat(achatConteneurArrivageList).hasSize(databaseSizeBeforeCreate + 1);
        AchatConteneurArrivage testAchatConteneurArrivage = achatConteneurArrivageList.get(achatConteneurArrivageList.size() - 1);
        assertThat(testAchatConteneurArrivage.getIdConteneurArrivage()).isEqualTo(DEFAULT_ID_CONTENEUR_ARRIVAGE);
        assertThat(testAchatConteneurArrivage.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testAchatConteneurArrivage.getNumeroConteneurs()).isEqualTo(DEFAULT_NUMERO_CONTENEURS);
        assertThat(testAchatConteneurArrivage.getNumeroSequence()).isEqualTo(DEFAULT_NUMERO_SEQUENCE);
        assertThat(testAchatConteneurArrivage.getPoids()).isEqualTo(DEFAULT_POIDS);
        assertThat(testAchatConteneurArrivage.getQuantite()).isEqualTo(DEFAULT_QUANTITE);

        // Validate the AchatConteneurArrivage in Elasticsearch
        verify(mockAchatConteneurArrivageSearchRepository, times(1)).save(testAchatConteneurArrivage);
    }

    @Test
    @Transactional
    public void createAchatConteneurArrivageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatConteneurArrivageRepository.findAll().size();

        // Create the AchatConteneurArrivage with an existing ID
        achatConteneurArrivage.setId(1L);
        AchatConteneurArrivageDTO achatConteneurArrivageDTO = achatConteneurArrivageMapper.toDto(achatConteneurArrivage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatConteneurArrivageMockMvc.perform(post("/api/achat-conteneur-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatConteneurArrivageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatConteneurArrivage in the database
        List<AchatConteneurArrivage> achatConteneurArrivageList = achatConteneurArrivageRepository.findAll();
        assertThat(achatConteneurArrivageList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatConteneurArrivage in Elasticsearch
        verify(mockAchatConteneurArrivageSearchRepository, times(0)).save(achatConteneurArrivage);
    }

    @Test
    @Transactional
    public void getAllAchatConteneurArrivages() throws Exception {
        // Initialize the database
        achatConteneurArrivageRepository.saveAndFlush(achatConteneurArrivage);

        // Get all the achatConteneurArrivageList
        restAchatConteneurArrivageMockMvc.perform(get("/api/achat-conteneur-arrivages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatConteneurArrivage.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConteneurArrivage").value(hasItem(DEFAULT_ID_CONTENEUR_ARRIVAGE)))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].numeroConteneurs").value(hasItem(DEFAULT_NUMERO_CONTENEURS.toString())))
            .andExpect(jsonPath("$.[*].numeroSequence").value(hasItem(DEFAULT_NUMERO_SEQUENCE)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.intValue())));
    }
    
    @Test
    @Transactional
    public void getAchatConteneurArrivage() throws Exception {
        // Initialize the database
        achatConteneurArrivageRepository.saveAndFlush(achatConteneurArrivage);

        // Get the achatConteneurArrivage
        restAchatConteneurArrivageMockMvc.perform(get("/api/achat-conteneur-arrivages/{id}", achatConteneurArrivage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatConteneurArrivage.getId().intValue()))
            .andExpect(jsonPath("$.idConteneurArrivage").value(DEFAULT_ID_CONTENEUR_ARRIVAGE))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.numeroConteneurs").value(DEFAULT_NUMERO_CONTENEURS.toString()))
            .andExpect(jsonPath("$.numeroSequence").value(DEFAULT_NUMERO_SEQUENCE))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.intValue()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatConteneurArrivage() throws Exception {
        // Get the achatConteneurArrivage
        restAchatConteneurArrivageMockMvc.perform(get("/api/achat-conteneur-arrivages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatConteneurArrivage() throws Exception {
        // Initialize the database
        achatConteneurArrivageRepository.saveAndFlush(achatConteneurArrivage);

        int databaseSizeBeforeUpdate = achatConteneurArrivageRepository.findAll().size();

        // Update the achatConteneurArrivage
        AchatConteneurArrivage updatedAchatConteneurArrivage = achatConteneurArrivageRepository.findById(achatConteneurArrivage.getId()).get();
        // Disconnect from session so that the updates on updatedAchatConteneurArrivage are not directly saved in db
        em.detach(updatedAchatConteneurArrivage);
        updatedAchatConteneurArrivage
            .idConteneurArrivage(UPDATED_ID_CONTENEUR_ARRIVAGE)
            .montant(UPDATED_MONTANT)
            .numeroConteneurs(UPDATED_NUMERO_CONTENEURS)
            .numeroSequence(UPDATED_NUMERO_SEQUENCE)
            .poids(UPDATED_POIDS)
            .quantite(UPDATED_QUANTITE);
        AchatConteneurArrivageDTO achatConteneurArrivageDTO = achatConteneurArrivageMapper.toDto(updatedAchatConteneurArrivage);

        restAchatConteneurArrivageMockMvc.perform(put("/api/achat-conteneur-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatConteneurArrivageDTO)))
            .andExpect(status().isOk());

        // Validate the AchatConteneurArrivage in the database
        List<AchatConteneurArrivage> achatConteneurArrivageList = achatConteneurArrivageRepository.findAll();
        assertThat(achatConteneurArrivageList).hasSize(databaseSizeBeforeUpdate);
        AchatConteneurArrivage testAchatConteneurArrivage = achatConteneurArrivageList.get(achatConteneurArrivageList.size() - 1);
        assertThat(testAchatConteneurArrivage.getIdConteneurArrivage()).isEqualTo(UPDATED_ID_CONTENEUR_ARRIVAGE);
        assertThat(testAchatConteneurArrivage.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testAchatConteneurArrivage.getNumeroConteneurs()).isEqualTo(UPDATED_NUMERO_CONTENEURS);
        assertThat(testAchatConteneurArrivage.getNumeroSequence()).isEqualTo(UPDATED_NUMERO_SEQUENCE);
        assertThat(testAchatConteneurArrivage.getPoids()).isEqualTo(UPDATED_POIDS);
        assertThat(testAchatConteneurArrivage.getQuantite()).isEqualTo(UPDATED_QUANTITE);

        // Validate the AchatConteneurArrivage in Elasticsearch
        verify(mockAchatConteneurArrivageSearchRepository, times(1)).save(testAchatConteneurArrivage);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatConteneurArrivage() throws Exception {
        int databaseSizeBeforeUpdate = achatConteneurArrivageRepository.findAll().size();

        // Create the AchatConteneurArrivage
        AchatConteneurArrivageDTO achatConteneurArrivageDTO = achatConteneurArrivageMapper.toDto(achatConteneurArrivage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatConteneurArrivageMockMvc.perform(put("/api/achat-conteneur-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatConteneurArrivageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatConteneurArrivage in the database
        List<AchatConteneurArrivage> achatConteneurArrivageList = achatConteneurArrivageRepository.findAll();
        assertThat(achatConteneurArrivageList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatConteneurArrivage in Elasticsearch
        verify(mockAchatConteneurArrivageSearchRepository, times(0)).save(achatConteneurArrivage);
    }

    @Test
    @Transactional
    public void deleteAchatConteneurArrivage() throws Exception {
        // Initialize the database
        achatConteneurArrivageRepository.saveAndFlush(achatConteneurArrivage);

        int databaseSizeBeforeDelete = achatConteneurArrivageRepository.findAll().size();

        // Get the achatConteneurArrivage
        restAchatConteneurArrivageMockMvc.perform(delete("/api/achat-conteneur-arrivages/{id}", achatConteneurArrivage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatConteneurArrivage> achatConteneurArrivageList = achatConteneurArrivageRepository.findAll();
        assertThat(achatConteneurArrivageList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatConteneurArrivage in Elasticsearch
        verify(mockAchatConteneurArrivageSearchRepository, times(1)).deleteById(achatConteneurArrivage.getId());
    }

    @Test
    @Transactional
    public void searchAchatConteneurArrivage() throws Exception {
        // Initialize the database
        achatConteneurArrivageRepository.saveAndFlush(achatConteneurArrivage);
        when(mockAchatConteneurArrivageSearchRepository.search(queryStringQuery("id:" + achatConteneurArrivage.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatConteneurArrivage), PageRequest.of(0, 1), 1));
        // Search the achatConteneurArrivage
        restAchatConteneurArrivageMockMvc.perform(get("/api/_search/achat-conteneur-arrivages?query=id:" + achatConteneurArrivage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatConteneurArrivage.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConteneurArrivage").value(hasItem(DEFAULT_ID_CONTENEUR_ARRIVAGE)))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].numeroConteneurs").value(hasItem(DEFAULT_NUMERO_CONTENEURS)))
            .andExpect(jsonPath("$.[*].numeroSequence").value(hasItem(DEFAULT_NUMERO_SEQUENCE)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatConteneurArrivage.class);
        AchatConteneurArrivage achatConteneurArrivage1 = new AchatConteneurArrivage();
        achatConteneurArrivage1.setId(1L);
        AchatConteneurArrivage achatConteneurArrivage2 = new AchatConteneurArrivage();
        achatConteneurArrivage2.setId(achatConteneurArrivage1.getId());
        assertThat(achatConteneurArrivage1).isEqualTo(achatConteneurArrivage2);
        achatConteneurArrivage2.setId(2L);
        assertThat(achatConteneurArrivage1).isNotEqualTo(achatConteneurArrivage2);
        achatConteneurArrivage1.setId(null);
        assertThat(achatConteneurArrivage1).isNotEqualTo(achatConteneurArrivage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatConteneurArrivageDTO.class);
        AchatConteneurArrivageDTO achatConteneurArrivageDTO1 = new AchatConteneurArrivageDTO();
        achatConteneurArrivageDTO1.setId(1L);
        AchatConteneurArrivageDTO achatConteneurArrivageDTO2 = new AchatConteneurArrivageDTO();
        assertThat(achatConteneurArrivageDTO1).isNotEqualTo(achatConteneurArrivageDTO2);
        achatConteneurArrivageDTO2.setId(achatConteneurArrivageDTO1.getId());
        assertThat(achatConteneurArrivageDTO1).isEqualTo(achatConteneurArrivageDTO2);
        achatConteneurArrivageDTO2.setId(2L);
        assertThat(achatConteneurArrivageDTO1).isNotEqualTo(achatConteneurArrivageDTO2);
        achatConteneurArrivageDTO1.setId(null);
        assertThat(achatConteneurArrivageDTO1).isNotEqualTo(achatConteneurArrivageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatConteneurArrivageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatConteneurArrivageMapper.fromId(null)).isNull();
    }
}
