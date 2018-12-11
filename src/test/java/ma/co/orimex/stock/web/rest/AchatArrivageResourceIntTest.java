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
import java.math.BigDecimal;
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
 * Test class for the AchatArrivageResource REST controller.
 *
 * @see AchatArrivageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatArrivageResourceIntTest {

    private static final Integer DEFAULT_ID_ARRIVAGE = 1;
    private static final Integer UPDATED_ID_ARRIVAGE = 2;

    private static final String DEFAULT_NUMERO_DOSSIER_ARRIVAGE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_DOSSIER_ARRIVAGE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_COMPAGNIE_MARITIME = "AAAAAAAAAA";
    private static final String UPDATED_CODE_COMPAGNIE_MARITIME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_CODE_OPERATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_TRANSITAIRE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_TRANSITAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_TRANSPORTEUR = "AAAAAAAAAA";
    private static final String UPDATED_CODE_TRANSPORTEUR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ARRIVE_PORT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ARRIVE_PORT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESIGNATION_COMPAGNIE_MARITIME = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_COMPAGNIE_MARITIME = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_OPERATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_TRANSITAIRE = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_TRANSITAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_TRANSPORTEUR = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_TRANSPORTEUR = "BBBBBBBBBB";

    private static final String DEFAULT_FLAG_PRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_FLAG_PRODUIT = "BBBBBBBBBB";

    private static final Integer DEFAULT_FRANCHISE = 1;
    private static final Integer UPDATED_FRANCHISE = 2;

    private static final BigDecimal DEFAULT_MONTANT_FOB = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_FOB = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_FRET = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_FRET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_TOTAL = new BigDecimal(2);

    private static final Integer DEFAULT_NOMBRE_TC = 1;
    private static final Integer UPDATED_NOMBRE_TC = 2;

    private static final LocalDate DEFAULT_DATE_REALISATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REALISATION = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_POID = new BigDecimal(1);
    private static final BigDecimal UPDATED_POID = new BigDecimal(2);

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
        AchatArrivage achatArrivage = new AchatArrivage()
            .idArrivage(DEFAULT_ID_ARRIVAGE)
            .numeroDossierArrivage(DEFAULT_NUMERO_DOSSIER_ARRIVAGE)
            .codeCompagnieMaritime(DEFAULT_CODE_COMPAGNIE_MARITIME)
            .codeOperateur(DEFAULT_CODE_OPERATEUR)
            .codeTransitaire(DEFAULT_CODE_TRANSITAIRE)
            .codeTransporteur(DEFAULT_CODE_TRANSPORTEUR)
            .dateArrivePort(DEFAULT_DATE_ARRIVE_PORT)
            .designationCompagnieMaritime(DEFAULT_DESIGNATION_COMPAGNIE_MARITIME)
            .designationOperateur(DEFAULT_DESIGNATION_OPERATEUR)
            .designationTransitaire(DEFAULT_DESIGNATION_TRANSITAIRE)
            .designationTransporteur(DEFAULT_DESIGNATION_TRANSPORTEUR)
            .flagProduit(DEFAULT_FLAG_PRODUIT)
            .franchise(DEFAULT_FRANCHISE)
            .montantFob(DEFAULT_MONTANT_FOB)
            .montantFret(DEFAULT_MONTANT_FRET)
            .montantTotal(DEFAULT_MONTANT_TOTAL)
            .nombreTc(DEFAULT_NOMBRE_TC)
            .dateRealisation(DEFAULT_DATE_REALISATION)
            .poid(DEFAULT_POID);
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
        assertThat(testAchatArrivage.getIdArrivage()).isEqualTo(DEFAULT_ID_ARRIVAGE);
        assertThat(testAchatArrivage.getNumeroDossierArrivage()).isEqualTo(DEFAULT_NUMERO_DOSSIER_ARRIVAGE);
        assertThat(testAchatArrivage.getCodeCompagnieMaritime()).isEqualTo(DEFAULT_CODE_COMPAGNIE_MARITIME);
        assertThat(testAchatArrivage.getCodeOperateur()).isEqualTo(DEFAULT_CODE_OPERATEUR);
        assertThat(testAchatArrivage.getCodeTransitaire()).isEqualTo(DEFAULT_CODE_TRANSITAIRE);
        assertThat(testAchatArrivage.getCodeTransporteur()).isEqualTo(DEFAULT_CODE_TRANSPORTEUR);
        assertThat(testAchatArrivage.getDateArrivePort()).isEqualTo(DEFAULT_DATE_ARRIVE_PORT);
        assertThat(testAchatArrivage.getDesignationCompagnieMaritime()).isEqualTo(DEFAULT_DESIGNATION_COMPAGNIE_MARITIME);
        assertThat(testAchatArrivage.getDesignationOperateur()).isEqualTo(DEFAULT_DESIGNATION_OPERATEUR);
        assertThat(testAchatArrivage.getDesignationTransitaire()).isEqualTo(DEFAULT_DESIGNATION_TRANSITAIRE);
        assertThat(testAchatArrivage.getDesignationTransporteur()).isEqualTo(DEFAULT_DESIGNATION_TRANSPORTEUR);
        assertThat(testAchatArrivage.getFlagProduit()).isEqualTo(DEFAULT_FLAG_PRODUIT);
        assertThat(testAchatArrivage.getFranchise()).isEqualTo(DEFAULT_FRANCHISE);
        assertThat(testAchatArrivage.getMontantFob()).isEqualTo(DEFAULT_MONTANT_FOB);
        assertThat(testAchatArrivage.getMontantFret()).isEqualTo(DEFAULT_MONTANT_FRET);
        assertThat(testAchatArrivage.getMontantTotal()).isEqualTo(DEFAULT_MONTANT_TOTAL);
        assertThat(testAchatArrivage.getNombreTc()).isEqualTo(DEFAULT_NOMBRE_TC);
        assertThat(testAchatArrivage.getDateRealisation()).isEqualTo(DEFAULT_DATE_REALISATION);
        assertThat(testAchatArrivage.getPoid()).isEqualTo(DEFAULT_POID);

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
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatArrivage.getId().intValue())))
            .andExpect(jsonPath("$.[*].idArrivage").value(hasItem(DEFAULT_ID_ARRIVAGE)))
            .andExpect(jsonPath("$.[*].numeroDossierArrivage").value(hasItem(DEFAULT_NUMERO_DOSSIER_ARRIVAGE.toString())))
            .andExpect(jsonPath("$.[*].codeCompagnieMaritime").value(hasItem(DEFAULT_CODE_COMPAGNIE_MARITIME.toString())))
            .andExpect(jsonPath("$.[*].codeOperateur").value(hasItem(DEFAULT_CODE_OPERATEUR.toString())))
            .andExpect(jsonPath("$.[*].codeTransitaire").value(hasItem(DEFAULT_CODE_TRANSITAIRE.toString())))
            .andExpect(jsonPath("$.[*].codeTransporteur").value(hasItem(DEFAULT_CODE_TRANSPORTEUR.toString())))
            .andExpect(jsonPath("$.[*].dateArrivePort").value(hasItem(DEFAULT_DATE_ARRIVE_PORT.toString())))
            .andExpect(jsonPath("$.[*].designationCompagnieMaritime").value(hasItem(DEFAULT_DESIGNATION_COMPAGNIE_MARITIME.toString())))
            .andExpect(jsonPath("$.[*].designationOperateur").value(hasItem(DEFAULT_DESIGNATION_OPERATEUR.toString())))
            .andExpect(jsonPath("$.[*].designationTransitaire").value(hasItem(DEFAULT_DESIGNATION_TRANSITAIRE.toString())))
            .andExpect(jsonPath("$.[*].designationTransporteur").value(hasItem(DEFAULT_DESIGNATION_TRANSPORTEUR.toString())))
            .andExpect(jsonPath("$.[*].flagProduit").value(hasItem(DEFAULT_FLAG_PRODUIT.toString())))
            .andExpect(jsonPath("$.[*].franchise").value(hasItem(DEFAULT_FRANCHISE)))
            .andExpect(jsonPath("$.[*].montantFob").value(hasItem(DEFAULT_MONTANT_FOB.intValue())))
            .andExpect(jsonPath("$.[*].montantFret").value(hasItem(DEFAULT_MONTANT_FRET.intValue())))
            .andExpect(jsonPath("$.[*].montantTotal").value(hasItem(DEFAULT_MONTANT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].nombreTc").value(hasItem(DEFAULT_NOMBRE_TC)))
            .andExpect(jsonPath("$.[*].dateRealisation").value(hasItem(DEFAULT_DATE_REALISATION.toString())))
            .andExpect(jsonPath("$.[*].poid").value(hasItem(DEFAULT_POID.intValue())));
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
            .andExpect(jsonPath("$.id").value(achatArrivage.getId().intValue()))
            .andExpect(jsonPath("$.idArrivage").value(DEFAULT_ID_ARRIVAGE))
            .andExpect(jsonPath("$.numeroDossierArrivage").value(DEFAULT_NUMERO_DOSSIER_ARRIVAGE.toString()))
            .andExpect(jsonPath("$.codeCompagnieMaritime").value(DEFAULT_CODE_COMPAGNIE_MARITIME.toString()))
            .andExpect(jsonPath("$.codeOperateur").value(DEFAULT_CODE_OPERATEUR.toString()))
            .andExpect(jsonPath("$.codeTransitaire").value(DEFAULT_CODE_TRANSITAIRE.toString()))
            .andExpect(jsonPath("$.codeTransporteur").value(DEFAULT_CODE_TRANSPORTEUR.toString()))
            .andExpect(jsonPath("$.dateArrivePort").value(DEFAULT_DATE_ARRIVE_PORT.toString()))
            .andExpect(jsonPath("$.designationCompagnieMaritime").value(DEFAULT_DESIGNATION_COMPAGNIE_MARITIME.toString()))
            .andExpect(jsonPath("$.designationOperateur").value(DEFAULT_DESIGNATION_OPERATEUR.toString()))
            .andExpect(jsonPath("$.designationTransitaire").value(DEFAULT_DESIGNATION_TRANSITAIRE.toString()))
            .andExpect(jsonPath("$.designationTransporteur").value(DEFAULT_DESIGNATION_TRANSPORTEUR.toString()))
            .andExpect(jsonPath("$.flagProduit").value(DEFAULT_FLAG_PRODUIT.toString()))
            .andExpect(jsonPath("$.franchise").value(DEFAULT_FRANCHISE))
            .andExpect(jsonPath("$.montantFob").value(DEFAULT_MONTANT_FOB.intValue()))
            .andExpect(jsonPath("$.montantFret").value(DEFAULT_MONTANT_FRET.intValue()))
            .andExpect(jsonPath("$.montantTotal").value(DEFAULT_MONTANT_TOTAL.intValue()))
            .andExpect(jsonPath("$.nombreTc").value(DEFAULT_NOMBRE_TC))
            .andExpect(jsonPath("$.dateRealisation").value(DEFAULT_DATE_REALISATION.toString()))
            .andExpect(jsonPath("$.poid").value(DEFAULT_POID.intValue()));
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
        updatedAchatArrivage
            .idArrivage(UPDATED_ID_ARRIVAGE)
            .numeroDossierArrivage(UPDATED_NUMERO_DOSSIER_ARRIVAGE)
            .codeCompagnieMaritime(UPDATED_CODE_COMPAGNIE_MARITIME)
            .codeOperateur(UPDATED_CODE_OPERATEUR)
            .codeTransitaire(UPDATED_CODE_TRANSITAIRE)
            .codeTransporteur(UPDATED_CODE_TRANSPORTEUR)
            .dateArrivePort(UPDATED_DATE_ARRIVE_PORT)
            .designationCompagnieMaritime(UPDATED_DESIGNATION_COMPAGNIE_MARITIME)
            .designationOperateur(UPDATED_DESIGNATION_OPERATEUR)
            .designationTransitaire(UPDATED_DESIGNATION_TRANSITAIRE)
            .designationTransporteur(UPDATED_DESIGNATION_TRANSPORTEUR)
            .flagProduit(UPDATED_FLAG_PRODUIT)
            .franchise(UPDATED_FRANCHISE)
            .montantFob(UPDATED_MONTANT_FOB)
            .montantFret(UPDATED_MONTANT_FRET)
            .montantTotal(UPDATED_MONTANT_TOTAL)
            .nombreTc(UPDATED_NOMBRE_TC)
            .dateRealisation(UPDATED_DATE_REALISATION)
            .poid(UPDATED_POID);
        AchatArrivageDTO achatArrivageDTO = achatArrivageMapper.toDto(updatedAchatArrivage);

        restAchatArrivageMockMvc.perform(put("/api/achat-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArrivageDTO)))
            .andExpect(status().isOk());

        // Validate the AchatArrivage in the database
        List<AchatArrivage> achatArrivageList = achatArrivageRepository.findAll();
        assertThat(achatArrivageList).hasSize(databaseSizeBeforeUpdate);
        AchatArrivage testAchatArrivage = achatArrivageList.get(achatArrivageList.size() - 1);
        assertThat(testAchatArrivage.getIdArrivage()).isEqualTo(UPDATED_ID_ARRIVAGE);
        assertThat(testAchatArrivage.getNumeroDossierArrivage()).isEqualTo(UPDATED_NUMERO_DOSSIER_ARRIVAGE);
        assertThat(testAchatArrivage.getCodeCompagnieMaritime()).isEqualTo(UPDATED_CODE_COMPAGNIE_MARITIME);
        assertThat(testAchatArrivage.getCodeOperateur()).isEqualTo(UPDATED_CODE_OPERATEUR);
        assertThat(testAchatArrivage.getCodeTransitaire()).isEqualTo(UPDATED_CODE_TRANSITAIRE);
        assertThat(testAchatArrivage.getCodeTransporteur()).isEqualTo(UPDATED_CODE_TRANSPORTEUR);
        assertThat(testAchatArrivage.getDateArrivePort()).isEqualTo(UPDATED_DATE_ARRIVE_PORT);
        assertThat(testAchatArrivage.getDesignationCompagnieMaritime()).isEqualTo(UPDATED_DESIGNATION_COMPAGNIE_MARITIME);
        assertThat(testAchatArrivage.getDesignationOperateur()).isEqualTo(UPDATED_DESIGNATION_OPERATEUR);
        assertThat(testAchatArrivage.getDesignationTransitaire()).isEqualTo(UPDATED_DESIGNATION_TRANSITAIRE);
        assertThat(testAchatArrivage.getDesignationTransporteur()).isEqualTo(UPDATED_DESIGNATION_TRANSPORTEUR);
        assertThat(testAchatArrivage.getFlagProduit()).isEqualTo(UPDATED_FLAG_PRODUIT);
        assertThat(testAchatArrivage.getFranchise()).isEqualTo(UPDATED_FRANCHISE);
        assertThat(testAchatArrivage.getMontantFob()).isEqualTo(UPDATED_MONTANT_FOB);
        assertThat(testAchatArrivage.getMontantFret()).isEqualTo(UPDATED_MONTANT_FRET);
        assertThat(testAchatArrivage.getMontantTotal()).isEqualTo(UPDATED_MONTANT_TOTAL);
        assertThat(testAchatArrivage.getNombreTc()).isEqualTo(UPDATED_NOMBRE_TC);
        assertThat(testAchatArrivage.getDateRealisation()).isEqualTo(UPDATED_DATE_REALISATION);
        assertThat(testAchatArrivage.getPoid()).isEqualTo(UPDATED_POID);

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
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatArrivage.getId().intValue())))
            .andExpect(jsonPath("$.[*].idArrivage").value(hasItem(DEFAULT_ID_ARRIVAGE)))
            .andExpect(jsonPath("$.[*].numeroDossierArrivage").value(hasItem(DEFAULT_NUMERO_DOSSIER_ARRIVAGE)))
            .andExpect(jsonPath("$.[*].codeCompagnieMaritime").value(hasItem(DEFAULT_CODE_COMPAGNIE_MARITIME)))
            .andExpect(jsonPath("$.[*].codeOperateur").value(hasItem(DEFAULT_CODE_OPERATEUR)))
            .andExpect(jsonPath("$.[*].codeTransitaire").value(hasItem(DEFAULT_CODE_TRANSITAIRE)))
            .andExpect(jsonPath("$.[*].codeTransporteur").value(hasItem(DEFAULT_CODE_TRANSPORTEUR)))
            .andExpect(jsonPath("$.[*].dateArrivePort").value(hasItem(DEFAULT_DATE_ARRIVE_PORT.toString())))
            .andExpect(jsonPath("$.[*].designationCompagnieMaritime").value(hasItem(DEFAULT_DESIGNATION_COMPAGNIE_MARITIME)))
            .andExpect(jsonPath("$.[*].designationOperateur").value(hasItem(DEFAULT_DESIGNATION_OPERATEUR)))
            .andExpect(jsonPath("$.[*].designationTransitaire").value(hasItem(DEFAULT_DESIGNATION_TRANSITAIRE)))
            .andExpect(jsonPath("$.[*].designationTransporteur").value(hasItem(DEFAULT_DESIGNATION_TRANSPORTEUR)))
            .andExpect(jsonPath("$.[*].flagProduit").value(hasItem(DEFAULT_FLAG_PRODUIT)))
            .andExpect(jsonPath("$.[*].franchise").value(hasItem(DEFAULT_FRANCHISE)))
            .andExpect(jsonPath("$.[*].montantFob").value(hasItem(DEFAULT_MONTANT_FOB.intValue())))
            .andExpect(jsonPath("$.[*].montantFret").value(hasItem(DEFAULT_MONTANT_FRET.intValue())))
            .andExpect(jsonPath("$.[*].montantTotal").value(hasItem(DEFAULT_MONTANT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].nombreTc").value(hasItem(DEFAULT_NOMBRE_TC)))
            .andExpect(jsonPath("$.[*].dateRealisation").value(hasItem(DEFAULT_DATE_REALISATION.toString())))
            .andExpect(jsonPath("$.[*].poid").value(hasItem(DEFAULT_POID.intValue())));
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
