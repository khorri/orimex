package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatPrevisionArrivage;
import ma.co.orimex.stock.repository.AchatPrevisionArrivageRepository;
import ma.co.orimex.stock.repository.search.AchatPrevisionArrivageSearchRepository;
import ma.co.orimex.stock.service.AchatPrevisionArrivageService;
import ma.co.orimex.stock.service.dto.AchatPrevisionArrivageDTO;
import ma.co.orimex.stock.service.mapper.AchatPrevisionArrivageMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Test class for the AchatPrevisionArrivageResource REST controller.
 *
 * @see AchatPrevisionArrivageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatPrevisionArrivageResourceIntTest {

    private static final Integer DEFAULT_ID_PREVISION_ARRIVAGE = 1;
    private static final Integer UPDATED_ID_PREVISION_ARRIVAGE = 2;

    private static final String DEFAULT_PRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUIT = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGANTION_COPAGNIE_MARITME = "AAAAAAAAAA";
    private static final String UPDATED_DESIGANTION_COPAGNIE_MARITME = "BBBBBBBBBB";

    private static final String DEFAULT_POL = "AAAAAAAAAA";
    private static final String UPDATED_POL = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_BL = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_BL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NOMBRE_TC = 1;
    private static final Integer UPDATED_NOMBRE_TC = 2;

    private static final LocalDate DEFAULT_ETD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ETD = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ETA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ETA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DOCUMENTS = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_DOUANE = "AAAAAAAAAA";
    private static final String UPDATED_DOUANE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ACTIVE = 1;
    private static final Integer UPDATED_ACTIVE = 2;

    @Autowired
    private AchatPrevisionArrivageRepository achatPrevisionArrivageRepository;

    @Autowired
    private AchatPrevisionArrivageMapper achatPrevisionArrivageMapper;

    @Autowired
    private AchatPrevisionArrivageService achatPrevisionArrivageService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatPrevisionArrivageSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatPrevisionArrivageSearchRepository mockAchatPrevisionArrivageSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatPrevisionArrivageMockMvc;

    private AchatPrevisionArrivage achatPrevisionArrivage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatPrevisionArrivageResource achatPrevisionArrivageResource = new AchatPrevisionArrivageResource(achatPrevisionArrivageService);
        this.restAchatPrevisionArrivageMockMvc = MockMvcBuilders.standaloneSetup(achatPrevisionArrivageResource)
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
    public static AchatPrevisionArrivage createEntity(EntityManager em) {
        AchatPrevisionArrivage achatPrevisionArrivage = new AchatPrevisionArrivage()
            .idPrevisionArrivage(DEFAULT_ID_PREVISION_ARRIVAGE)
            .produit(DEFAULT_PRODUIT)
            .desigantionCopagnieMaritme(DEFAULT_DESIGANTION_COPAGNIE_MARITME)
            .pol(DEFAULT_POL)
            .numeroBl(DEFAULT_NUMERO_BL)
            .nombreTc(DEFAULT_NOMBRE_TC)
            .etd(DEFAULT_ETD)
            .eta(DEFAULT_ETA)
            .documents(DEFAULT_DOCUMENTS)
            .douane(DEFAULT_DOUANE)
            .active(DEFAULT_ACTIVE);
        return achatPrevisionArrivage;
    }

    @Before
    public void initTest() {
        achatPrevisionArrivage = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatPrevisionArrivage() throws Exception {
        int databaseSizeBeforeCreate = achatPrevisionArrivageRepository.findAll().size();

        // Create the AchatPrevisionArrivage
        AchatPrevisionArrivageDTO achatPrevisionArrivageDTO = achatPrevisionArrivageMapper.toDto(achatPrevisionArrivage);
        restAchatPrevisionArrivageMockMvc.perform(post("/api/achat-prevision-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatPrevisionArrivageDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatPrevisionArrivage in the database
        List<AchatPrevisionArrivage> achatPrevisionArrivageList = achatPrevisionArrivageRepository.findAll();
        assertThat(achatPrevisionArrivageList).hasSize(databaseSizeBeforeCreate + 1);
        AchatPrevisionArrivage testAchatPrevisionArrivage = achatPrevisionArrivageList.get(achatPrevisionArrivageList.size() - 1);
        assertThat(testAchatPrevisionArrivage.getIdPrevisionArrivage()).isEqualTo(DEFAULT_ID_PREVISION_ARRIVAGE);
        assertThat(testAchatPrevisionArrivage.getProduit()).isEqualTo(DEFAULT_PRODUIT);
        assertThat(testAchatPrevisionArrivage.getDesigantionCopagnieMaritme()).isEqualTo(DEFAULT_DESIGANTION_COPAGNIE_MARITME);
        assertThat(testAchatPrevisionArrivage.getPol()).isEqualTo(DEFAULT_POL);
        assertThat(testAchatPrevisionArrivage.getNumeroBl()).isEqualTo(DEFAULT_NUMERO_BL);
        assertThat(testAchatPrevisionArrivage.getNombreTc()).isEqualTo(DEFAULT_NOMBRE_TC);
        assertThat(testAchatPrevisionArrivage.getEtd()).isEqualTo(DEFAULT_ETD);
        assertThat(testAchatPrevisionArrivage.getEta()).isEqualTo(DEFAULT_ETA);
        assertThat(testAchatPrevisionArrivage.getDocuments()).isEqualTo(DEFAULT_DOCUMENTS);
        assertThat(testAchatPrevisionArrivage.getDouane()).isEqualTo(DEFAULT_DOUANE);
        assertThat(testAchatPrevisionArrivage.getActive()).isEqualTo(DEFAULT_ACTIVE);

        // Validate the AchatPrevisionArrivage in Elasticsearch
        verify(mockAchatPrevisionArrivageSearchRepository, times(1)).save(testAchatPrevisionArrivage);
    }

    @Test
    @Transactional
    public void createAchatPrevisionArrivageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatPrevisionArrivageRepository.findAll().size();

        // Create the AchatPrevisionArrivage with an existing ID
        achatPrevisionArrivage.setId(1L);
        AchatPrevisionArrivageDTO achatPrevisionArrivageDTO = achatPrevisionArrivageMapper.toDto(achatPrevisionArrivage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatPrevisionArrivageMockMvc.perform(post("/api/achat-prevision-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatPrevisionArrivageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatPrevisionArrivage in the database
        List<AchatPrevisionArrivage> achatPrevisionArrivageList = achatPrevisionArrivageRepository.findAll();
        assertThat(achatPrevisionArrivageList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatPrevisionArrivage in Elasticsearch
        verify(mockAchatPrevisionArrivageSearchRepository, times(0)).save(achatPrevisionArrivage);
    }

    @Test
    @Transactional
    public void getAllAchatPrevisionArrivages() throws Exception {
        // Initialize the database
        achatPrevisionArrivageRepository.saveAndFlush(achatPrevisionArrivage);

        // Get all the achatPrevisionArrivageList
        restAchatPrevisionArrivageMockMvc.perform(get("/api/achat-prevision-arrivages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatPrevisionArrivage.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPrevisionArrivage").value(hasItem(DEFAULT_ID_PREVISION_ARRIVAGE)))
            .andExpect(jsonPath("$.[*].produit").value(hasItem(DEFAULT_PRODUIT.toString())))
            .andExpect(jsonPath("$.[*].desigantionCopagnieMaritme").value(hasItem(DEFAULT_DESIGANTION_COPAGNIE_MARITME.toString())))
            .andExpect(jsonPath("$.[*].pol").value(hasItem(DEFAULT_POL.toString())))
            .andExpect(jsonPath("$.[*].numeroBl").value(hasItem(DEFAULT_NUMERO_BL.toString())))
            .andExpect(jsonPath("$.[*].nombreTc").value(hasItem(DEFAULT_NOMBRE_TC)))
            .andExpect(jsonPath("$.[*].etd").value(hasItem(DEFAULT_ETD.toString())))
            .andExpect(jsonPath("$.[*].eta").value(hasItem(DEFAULT_ETA.toString())))
            .andExpect(jsonPath("$.[*].documents").value(hasItem(DEFAULT_DOCUMENTS.toString())))
            .andExpect(jsonPath("$.[*].douane").value(hasItem(DEFAULT_DOUANE.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE)));
    }
    
    @Test
    @Transactional
    public void getAchatPrevisionArrivage() throws Exception {
        // Initialize the database
        achatPrevisionArrivageRepository.saveAndFlush(achatPrevisionArrivage);

        // Get the achatPrevisionArrivage
        restAchatPrevisionArrivageMockMvc.perform(get("/api/achat-prevision-arrivages/{id}", achatPrevisionArrivage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatPrevisionArrivage.getId().intValue()))
            .andExpect(jsonPath("$.idPrevisionArrivage").value(DEFAULT_ID_PREVISION_ARRIVAGE))
            .andExpect(jsonPath("$.produit").value(DEFAULT_PRODUIT.toString()))
            .andExpect(jsonPath("$.desigantionCopagnieMaritme").value(DEFAULT_DESIGANTION_COPAGNIE_MARITME.toString()))
            .andExpect(jsonPath("$.pol").value(DEFAULT_POL.toString()))
            .andExpect(jsonPath("$.numeroBl").value(DEFAULT_NUMERO_BL.toString()))
            .andExpect(jsonPath("$.nombreTc").value(DEFAULT_NOMBRE_TC))
            .andExpect(jsonPath("$.etd").value(DEFAULT_ETD.toString()))
            .andExpect(jsonPath("$.eta").value(DEFAULT_ETA.toString()))
            .andExpect(jsonPath("$.documents").value(DEFAULT_DOCUMENTS.toString()))
            .andExpect(jsonPath("$.douane").value(DEFAULT_DOUANE.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE));
    }

    @Test
    @Transactional
    public void getNonExistingAchatPrevisionArrivage() throws Exception {
        // Get the achatPrevisionArrivage
        restAchatPrevisionArrivageMockMvc.perform(get("/api/achat-prevision-arrivages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatPrevisionArrivage() throws Exception {
        // Initialize the database
        achatPrevisionArrivageRepository.saveAndFlush(achatPrevisionArrivage);

        int databaseSizeBeforeUpdate = achatPrevisionArrivageRepository.findAll().size();

        // Update the achatPrevisionArrivage
        AchatPrevisionArrivage updatedAchatPrevisionArrivage = achatPrevisionArrivageRepository.findById(achatPrevisionArrivage.getId()).get();
        // Disconnect from session so that the updates on updatedAchatPrevisionArrivage are not directly saved in db
        em.detach(updatedAchatPrevisionArrivage);
        updatedAchatPrevisionArrivage
            .idPrevisionArrivage(UPDATED_ID_PREVISION_ARRIVAGE)
            .produit(UPDATED_PRODUIT)
            .desigantionCopagnieMaritme(UPDATED_DESIGANTION_COPAGNIE_MARITME)
            .pol(UPDATED_POL)
            .numeroBl(UPDATED_NUMERO_BL)
            .nombreTc(UPDATED_NOMBRE_TC)
            .etd(UPDATED_ETD)
            .eta(UPDATED_ETA)
            .documents(UPDATED_DOCUMENTS)
            .douane(UPDATED_DOUANE)
            .active(UPDATED_ACTIVE);
        AchatPrevisionArrivageDTO achatPrevisionArrivageDTO = achatPrevisionArrivageMapper.toDto(updatedAchatPrevisionArrivage);

        restAchatPrevisionArrivageMockMvc.perform(put("/api/achat-prevision-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatPrevisionArrivageDTO)))
            .andExpect(status().isOk());

        // Validate the AchatPrevisionArrivage in the database
        List<AchatPrevisionArrivage> achatPrevisionArrivageList = achatPrevisionArrivageRepository.findAll();
        assertThat(achatPrevisionArrivageList).hasSize(databaseSizeBeforeUpdate);
        AchatPrevisionArrivage testAchatPrevisionArrivage = achatPrevisionArrivageList.get(achatPrevisionArrivageList.size() - 1);
        assertThat(testAchatPrevisionArrivage.getIdPrevisionArrivage()).isEqualTo(UPDATED_ID_PREVISION_ARRIVAGE);
        assertThat(testAchatPrevisionArrivage.getProduit()).isEqualTo(UPDATED_PRODUIT);
        assertThat(testAchatPrevisionArrivage.getDesigantionCopagnieMaritme()).isEqualTo(UPDATED_DESIGANTION_COPAGNIE_MARITME);
        assertThat(testAchatPrevisionArrivage.getPol()).isEqualTo(UPDATED_POL);
        assertThat(testAchatPrevisionArrivage.getNumeroBl()).isEqualTo(UPDATED_NUMERO_BL);
        assertThat(testAchatPrevisionArrivage.getNombreTc()).isEqualTo(UPDATED_NOMBRE_TC);
        assertThat(testAchatPrevisionArrivage.getEtd()).isEqualTo(UPDATED_ETD);
        assertThat(testAchatPrevisionArrivage.getEta()).isEqualTo(UPDATED_ETA);
        assertThat(testAchatPrevisionArrivage.getDocuments()).isEqualTo(UPDATED_DOCUMENTS);
        assertThat(testAchatPrevisionArrivage.getDouane()).isEqualTo(UPDATED_DOUANE);
        assertThat(testAchatPrevisionArrivage.getActive()).isEqualTo(UPDATED_ACTIVE);

        // Validate the AchatPrevisionArrivage in Elasticsearch
        verify(mockAchatPrevisionArrivageSearchRepository, times(1)).save(testAchatPrevisionArrivage);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatPrevisionArrivage() throws Exception {
        int databaseSizeBeforeUpdate = achatPrevisionArrivageRepository.findAll().size();

        // Create the AchatPrevisionArrivage
        AchatPrevisionArrivageDTO achatPrevisionArrivageDTO = achatPrevisionArrivageMapper.toDto(achatPrevisionArrivage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatPrevisionArrivageMockMvc.perform(put("/api/achat-prevision-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatPrevisionArrivageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatPrevisionArrivage in the database
        List<AchatPrevisionArrivage> achatPrevisionArrivageList = achatPrevisionArrivageRepository.findAll();
        assertThat(achatPrevisionArrivageList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatPrevisionArrivage in Elasticsearch
        verify(mockAchatPrevisionArrivageSearchRepository, times(0)).save(achatPrevisionArrivage);
    }

    @Test
    @Transactional
    public void deleteAchatPrevisionArrivage() throws Exception {
        // Initialize the database
        achatPrevisionArrivageRepository.saveAndFlush(achatPrevisionArrivage);

        int databaseSizeBeforeDelete = achatPrevisionArrivageRepository.findAll().size();

        // Get the achatPrevisionArrivage
        restAchatPrevisionArrivageMockMvc.perform(delete("/api/achat-prevision-arrivages/{id}", achatPrevisionArrivage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatPrevisionArrivage> achatPrevisionArrivageList = achatPrevisionArrivageRepository.findAll();
        assertThat(achatPrevisionArrivageList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatPrevisionArrivage in Elasticsearch
        verify(mockAchatPrevisionArrivageSearchRepository, times(1)).deleteById(achatPrevisionArrivage.getId());
    }

    @Test
    @Transactional
    public void searchAchatPrevisionArrivage() throws Exception {
        // Initialize the database
        achatPrevisionArrivageRepository.saveAndFlush(achatPrevisionArrivage);
        when(mockAchatPrevisionArrivageSearchRepository.search(queryStringQuery("id:" + achatPrevisionArrivage.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatPrevisionArrivage), PageRequest.of(0, 1), 1));
        // Search the achatPrevisionArrivage
        restAchatPrevisionArrivageMockMvc.perform(get("/api/_search/achat-prevision-arrivages?query=id:" + achatPrevisionArrivage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatPrevisionArrivage.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPrevisionArrivage").value(hasItem(DEFAULT_ID_PREVISION_ARRIVAGE)))
            .andExpect(jsonPath("$.[*].produit").value(hasItem(DEFAULT_PRODUIT)))
            .andExpect(jsonPath("$.[*].desigantionCopagnieMaritme").value(hasItem(DEFAULT_DESIGANTION_COPAGNIE_MARITME)))
            .andExpect(jsonPath("$.[*].pol").value(hasItem(DEFAULT_POL)))
            .andExpect(jsonPath("$.[*].numeroBl").value(hasItem(DEFAULT_NUMERO_BL)))
            .andExpect(jsonPath("$.[*].nombreTc").value(hasItem(DEFAULT_NOMBRE_TC)))
            .andExpect(jsonPath("$.[*].etd").value(hasItem(DEFAULT_ETD.toString())))
            .andExpect(jsonPath("$.[*].eta").value(hasItem(DEFAULT_ETA.toString())))
            .andExpect(jsonPath("$.[*].documents").value(hasItem(DEFAULT_DOCUMENTS)))
            .andExpect(jsonPath("$.[*].douane").value(hasItem(DEFAULT_DOUANE)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatPrevisionArrivage.class);
        AchatPrevisionArrivage achatPrevisionArrivage1 = new AchatPrevisionArrivage();
        achatPrevisionArrivage1.setId(1L);
        AchatPrevisionArrivage achatPrevisionArrivage2 = new AchatPrevisionArrivage();
        achatPrevisionArrivage2.setId(achatPrevisionArrivage1.getId());
        assertThat(achatPrevisionArrivage1).isEqualTo(achatPrevisionArrivage2);
        achatPrevisionArrivage2.setId(2L);
        assertThat(achatPrevisionArrivage1).isNotEqualTo(achatPrevisionArrivage2);
        achatPrevisionArrivage1.setId(null);
        assertThat(achatPrevisionArrivage1).isNotEqualTo(achatPrevisionArrivage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatPrevisionArrivageDTO.class);
        AchatPrevisionArrivageDTO achatPrevisionArrivageDTO1 = new AchatPrevisionArrivageDTO();
        achatPrevisionArrivageDTO1.setId(1L);
        AchatPrevisionArrivageDTO achatPrevisionArrivageDTO2 = new AchatPrevisionArrivageDTO();
        assertThat(achatPrevisionArrivageDTO1).isNotEqualTo(achatPrevisionArrivageDTO2);
        achatPrevisionArrivageDTO2.setId(achatPrevisionArrivageDTO1.getId());
        assertThat(achatPrevisionArrivageDTO1).isEqualTo(achatPrevisionArrivageDTO2);
        achatPrevisionArrivageDTO2.setId(2L);
        assertThat(achatPrevisionArrivageDTO1).isNotEqualTo(achatPrevisionArrivageDTO2);
        achatPrevisionArrivageDTO1.setId(null);
        assertThat(achatPrevisionArrivageDTO1).isNotEqualTo(achatPrevisionArrivageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatPrevisionArrivageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatPrevisionArrivageMapper.fromId(null)).isNull();
    }
}
