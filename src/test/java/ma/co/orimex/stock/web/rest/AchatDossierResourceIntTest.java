package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatDossier;
import ma.co.orimex.stock.repository.AchatDossierRepository;
import ma.co.orimex.stock.repository.search.AchatDossierSearchRepository;
import ma.co.orimex.stock.service.AchatDossierService;
import ma.co.orimex.stock.service.dto.AchatDossierDTO;
import ma.co.orimex.stock.service.mapper.AchatDossierMapper;
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
 * Test class for the AchatDossierResource REST controller.
 *
 * @see AchatDossierResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatDossierResourceIntTest {

    private static final Integer DEFAULT_ID_DOSSIER = 1;
    private static final Integer UPDATED_ID_DOSSIER = 2;

    private static final String DEFAULT_NUMERO_DOSSIER = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_DOSSIER = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_FOURNISSEUR = "AAAAAAAAAA";
    private static final String UPDATED_CODE_FOURNISSEUR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_FOURNISSEUR = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_FOURNISSEUR = "BBBBBBBBBB";

    private static final String DEFAULT_INCOTERM = "AAAAAAAAAA";
    private static final String UPDATED_INCOTERM = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOLERANCE = 1;
    private static final Integer UPDATED_TOLERANCE = 2;

    private static final String DEFAULT_NUMERO_EI = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_EI = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_EI_COMP = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_EI_COMP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_CREATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATION = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_DELAI_PAIEMENT = 1;
    private static final Integer UPDATED_DELAI_PAIEMENT = 2;

    private static final Integer DEFAULT_DELAI_VALIDITE_LC = 1;
    private static final Integer UPDATED_DELAI_VALIDITE_LC = 2;

    private static final LocalDate DEFAULT_DATE_EXPEDITION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EXPEDITION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_OUVERTURE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OUVERTURE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_VALIDITE_EI = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VALIDITE_EI = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_LIMITE_EXP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_LIMITE_EXP = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_VALIDITE_LC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VALIDITE_LC = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_MONTNAT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTNAT_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTNAT_FOB = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTNAT_FOB = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTNAT_FRET = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTNAT_FRET = new BigDecimal(2);

    private static final Integer DEFAULT_TOTAL_TC = 1;
    private static final Integer UPDATED_TOTAL_TC = 2;

    private static final String DEFAULT_DESIGNATION_BANQUE = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_BANQUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAIEMENT_AVUE = 1;
    private static final Integer UPDATED_PAIEMENT_AVUE = 2;

    private static final BigDecimal DEFAULT_ENCOURS = new BigDecimal(1);
    private static final BigDecimal UPDATED_ENCOURS = new BigDecimal(2);

    @Autowired
    private AchatDossierRepository achatDossierRepository;

    @Autowired
    private AchatDossierMapper achatDossierMapper;

    @Autowired
    private AchatDossierService achatDossierService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatDossierSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatDossierSearchRepository mockAchatDossierSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatDossierMockMvc;

    private AchatDossier achatDossier;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatDossierResource achatDossierResource = new AchatDossierResource(achatDossierService);
        this.restAchatDossierMockMvc = MockMvcBuilders.standaloneSetup(achatDossierResource)
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
    public static AchatDossier createEntity(EntityManager em) {
        AchatDossier achatDossier = new AchatDossier()
            .idDossier(DEFAULT_ID_DOSSIER)
            .numeroDossier(DEFAULT_NUMERO_DOSSIER)
            .codeFournisseur(DEFAULT_CODE_FOURNISSEUR)
            .designationFournisseur(DEFAULT_DESIGNATION_FOURNISSEUR)
            .incoterm(DEFAULT_INCOTERM)
            .reference(DEFAULT_REFERENCE)
            .tolerance(DEFAULT_TOLERANCE)
            .numeroEi(DEFAULT_NUMERO_EI)
            .numeroEiComp(DEFAULT_NUMERO_EI_COMP)
            .dateCreation(DEFAULT_DATE_CREATION)
            .delaiPaiement(DEFAULT_DELAI_PAIEMENT)
            .delaiValiditeLc(DEFAULT_DELAI_VALIDITE_LC)
            .dateExpedition(DEFAULT_DATE_EXPEDITION)
            .dateOuverture(DEFAULT_DATE_OUVERTURE)
            .dateValiditeEi(DEFAULT_DATE_VALIDITE_EI)
            .dateLimiteExp(DEFAULT_DATE_LIMITE_EXP)
            .dateValiditeLc(DEFAULT_DATE_VALIDITE_LC)
            .montnatTotal(DEFAULT_MONTNAT_TOTAL)
            .montnatFob(DEFAULT_MONTNAT_FOB)
            .montnatFret(DEFAULT_MONTNAT_FRET)
            .totalTc(DEFAULT_TOTAL_TC)
            .designationBanque(DEFAULT_DESIGNATION_BANQUE)
            .paiementAvue(DEFAULT_PAIEMENT_AVUE)
            .encours(DEFAULT_ENCOURS);
        return achatDossier;
    }

    @Before
    public void initTest() {
        achatDossier = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatDossier() throws Exception {
        int databaseSizeBeforeCreate = achatDossierRepository.findAll().size();

        // Create the AchatDossier
        AchatDossierDTO achatDossierDTO = achatDossierMapper.toDto(achatDossier);
        restAchatDossierMockMvc.perform(post("/api/achat-dossiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatDossierDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatDossier in the database
        List<AchatDossier> achatDossierList = achatDossierRepository.findAll();
        assertThat(achatDossierList).hasSize(databaseSizeBeforeCreate + 1);
        AchatDossier testAchatDossier = achatDossierList.get(achatDossierList.size() - 1);
        assertThat(testAchatDossier.getIdDossier()).isEqualTo(DEFAULT_ID_DOSSIER);
        assertThat(testAchatDossier.getNumeroDossier()).isEqualTo(DEFAULT_NUMERO_DOSSIER);
        assertThat(testAchatDossier.getCodeFournisseur()).isEqualTo(DEFAULT_CODE_FOURNISSEUR);
        assertThat(testAchatDossier.getDesignationFournisseur()).isEqualTo(DEFAULT_DESIGNATION_FOURNISSEUR);
        assertThat(testAchatDossier.getIncoterm()).isEqualTo(DEFAULT_INCOTERM);
        assertThat(testAchatDossier.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testAchatDossier.getTolerance()).isEqualTo(DEFAULT_TOLERANCE);
        assertThat(testAchatDossier.getNumeroEi()).isEqualTo(DEFAULT_NUMERO_EI);
        assertThat(testAchatDossier.getNumeroEiComp()).isEqualTo(DEFAULT_NUMERO_EI_COMP);
        assertThat(testAchatDossier.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testAchatDossier.getDelaiPaiement()).isEqualTo(DEFAULT_DELAI_PAIEMENT);
        assertThat(testAchatDossier.getDelaiValiditeLc()).isEqualTo(DEFAULT_DELAI_VALIDITE_LC);
        assertThat(testAchatDossier.getDateExpedition()).isEqualTo(DEFAULT_DATE_EXPEDITION);
        assertThat(testAchatDossier.getDateOuverture()).isEqualTo(DEFAULT_DATE_OUVERTURE);
        assertThat(testAchatDossier.getDateValiditeEi()).isEqualTo(DEFAULT_DATE_VALIDITE_EI);
        assertThat(testAchatDossier.getDateLimiteExp()).isEqualTo(DEFAULT_DATE_LIMITE_EXP);
        assertThat(testAchatDossier.getDateValiditeLc()).isEqualTo(DEFAULT_DATE_VALIDITE_LC);
        assertThat(testAchatDossier.getMontnatTotal()).isEqualTo(DEFAULT_MONTNAT_TOTAL);
        assertThat(testAchatDossier.getMontnatFob()).isEqualTo(DEFAULT_MONTNAT_FOB);
        assertThat(testAchatDossier.getMontnatFret()).isEqualTo(DEFAULT_MONTNAT_FRET);
        assertThat(testAchatDossier.getTotalTc()).isEqualTo(DEFAULT_TOTAL_TC);
        assertThat(testAchatDossier.getDesignationBanque()).isEqualTo(DEFAULT_DESIGNATION_BANQUE);
        assertThat(testAchatDossier.getPaiementAvue()).isEqualTo(DEFAULT_PAIEMENT_AVUE);
        assertThat(testAchatDossier.getEncours()).isEqualTo(DEFAULT_ENCOURS);

        // Validate the AchatDossier in Elasticsearch
        verify(mockAchatDossierSearchRepository, times(1)).save(testAchatDossier);
    }

    @Test
    @Transactional
    public void createAchatDossierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatDossierRepository.findAll().size();

        // Create the AchatDossier with an existing ID
        achatDossier.setId(1L);
        AchatDossierDTO achatDossierDTO = achatDossierMapper.toDto(achatDossier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatDossierMockMvc.perform(post("/api/achat-dossiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatDossierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatDossier in the database
        List<AchatDossier> achatDossierList = achatDossierRepository.findAll();
        assertThat(achatDossierList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatDossier in Elasticsearch
        verify(mockAchatDossierSearchRepository, times(0)).save(achatDossier);
    }

    @Test
    @Transactional
    public void getAllAchatDossiers() throws Exception {
        // Initialize the database
        achatDossierRepository.saveAndFlush(achatDossier);

        // Get all the achatDossierList
        restAchatDossierMockMvc.perform(get("/api/achat-dossiers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatDossier.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDossier").value(hasItem(DEFAULT_ID_DOSSIER)))
            .andExpect(jsonPath("$.[*].numeroDossier").value(hasItem(DEFAULT_NUMERO_DOSSIER.toString())))
            .andExpect(jsonPath("$.[*].codeFournisseur").value(hasItem(DEFAULT_CODE_FOURNISSEUR.toString())))
            .andExpect(jsonPath("$.[*].designationFournisseur").value(hasItem(DEFAULT_DESIGNATION_FOURNISSEUR.toString())))
            .andExpect(jsonPath("$.[*].incoterm").value(hasItem(DEFAULT_INCOTERM.toString())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].tolerance").value(hasItem(DEFAULT_TOLERANCE)))
            .andExpect(jsonPath("$.[*].numeroEi").value(hasItem(DEFAULT_NUMERO_EI.toString())))
            .andExpect(jsonPath("$.[*].numeroEiComp").value(hasItem(DEFAULT_NUMERO_EI_COMP.toString())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].delaiPaiement").value(hasItem(DEFAULT_DELAI_PAIEMENT)))
            .andExpect(jsonPath("$.[*].delaiValiditeLc").value(hasItem(DEFAULT_DELAI_VALIDITE_LC)))
            .andExpect(jsonPath("$.[*].dateExpedition").value(hasItem(DEFAULT_DATE_EXPEDITION.toString())))
            .andExpect(jsonPath("$.[*].dateOuverture").value(hasItem(DEFAULT_DATE_OUVERTURE.toString())))
            .andExpect(jsonPath("$.[*].dateValiditeEi").value(hasItem(DEFAULT_DATE_VALIDITE_EI.toString())))
            .andExpect(jsonPath("$.[*].dateLimiteExp").value(hasItem(DEFAULT_DATE_LIMITE_EXP.toString())))
            .andExpect(jsonPath("$.[*].dateValiditeLc").value(hasItem(DEFAULT_DATE_VALIDITE_LC.toString())))
            .andExpect(jsonPath("$.[*].montnatTotal").value(hasItem(DEFAULT_MONTNAT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].montnatFob").value(hasItem(DEFAULT_MONTNAT_FOB.intValue())))
            .andExpect(jsonPath("$.[*].montnatFret").value(hasItem(DEFAULT_MONTNAT_FRET.intValue())))
            .andExpect(jsonPath("$.[*].totalTc").value(hasItem(DEFAULT_TOTAL_TC)))
            .andExpect(jsonPath("$.[*].designationBanque").value(hasItem(DEFAULT_DESIGNATION_BANQUE.toString())))
            .andExpect(jsonPath("$.[*].paiementAvue").value(hasItem(DEFAULT_PAIEMENT_AVUE)))
            .andExpect(jsonPath("$.[*].encours").value(hasItem(DEFAULT_ENCOURS.intValue())));
    }
    
    @Test
    @Transactional
    public void getAchatDossier() throws Exception {
        // Initialize the database
        achatDossierRepository.saveAndFlush(achatDossier);

        // Get the achatDossier
        restAchatDossierMockMvc.perform(get("/api/achat-dossiers/{id}", achatDossier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatDossier.getId().intValue()))
            .andExpect(jsonPath("$.idDossier").value(DEFAULT_ID_DOSSIER))
            .andExpect(jsonPath("$.numeroDossier").value(DEFAULT_NUMERO_DOSSIER.toString()))
            .andExpect(jsonPath("$.codeFournisseur").value(DEFAULT_CODE_FOURNISSEUR.toString()))
            .andExpect(jsonPath("$.designationFournisseur").value(DEFAULT_DESIGNATION_FOURNISSEUR.toString()))
            .andExpect(jsonPath("$.incoterm").value(DEFAULT_INCOTERM.toString()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.tolerance").value(DEFAULT_TOLERANCE))
            .andExpect(jsonPath("$.numeroEi").value(DEFAULT_NUMERO_EI.toString()))
            .andExpect(jsonPath("$.numeroEiComp").value(DEFAULT_NUMERO_EI_COMP.toString()))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.toString()))
            .andExpect(jsonPath("$.delaiPaiement").value(DEFAULT_DELAI_PAIEMENT))
            .andExpect(jsonPath("$.delaiValiditeLc").value(DEFAULT_DELAI_VALIDITE_LC))
            .andExpect(jsonPath("$.dateExpedition").value(DEFAULT_DATE_EXPEDITION.toString()))
            .andExpect(jsonPath("$.dateOuverture").value(DEFAULT_DATE_OUVERTURE.toString()))
            .andExpect(jsonPath("$.dateValiditeEi").value(DEFAULT_DATE_VALIDITE_EI.toString()))
            .andExpect(jsonPath("$.dateLimiteExp").value(DEFAULT_DATE_LIMITE_EXP.toString()))
            .andExpect(jsonPath("$.dateValiditeLc").value(DEFAULT_DATE_VALIDITE_LC.toString()))
            .andExpect(jsonPath("$.montnatTotal").value(DEFAULT_MONTNAT_TOTAL.intValue()))
            .andExpect(jsonPath("$.montnatFob").value(DEFAULT_MONTNAT_FOB.intValue()))
            .andExpect(jsonPath("$.montnatFret").value(DEFAULT_MONTNAT_FRET.intValue()))
            .andExpect(jsonPath("$.totalTc").value(DEFAULT_TOTAL_TC))
            .andExpect(jsonPath("$.designationBanque").value(DEFAULT_DESIGNATION_BANQUE.toString()))
            .andExpect(jsonPath("$.paiementAvue").value(DEFAULT_PAIEMENT_AVUE))
            .andExpect(jsonPath("$.encours").value(DEFAULT_ENCOURS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatDossier() throws Exception {
        // Get the achatDossier
        restAchatDossierMockMvc.perform(get("/api/achat-dossiers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatDossier() throws Exception {
        // Initialize the database
        achatDossierRepository.saveAndFlush(achatDossier);

        int databaseSizeBeforeUpdate = achatDossierRepository.findAll().size();

        // Update the achatDossier
        AchatDossier updatedAchatDossier = achatDossierRepository.findById(achatDossier.getId()).get();
        // Disconnect from session so that the updates on updatedAchatDossier are not directly saved in db
        em.detach(updatedAchatDossier);
        updatedAchatDossier
            .idDossier(UPDATED_ID_DOSSIER)
            .numeroDossier(UPDATED_NUMERO_DOSSIER)
            .codeFournisseur(UPDATED_CODE_FOURNISSEUR)
            .designationFournisseur(UPDATED_DESIGNATION_FOURNISSEUR)
            .incoterm(UPDATED_INCOTERM)
            .reference(UPDATED_REFERENCE)
            .tolerance(UPDATED_TOLERANCE)
            .numeroEi(UPDATED_NUMERO_EI)
            .numeroEiComp(UPDATED_NUMERO_EI_COMP)
            .dateCreation(UPDATED_DATE_CREATION)
            .delaiPaiement(UPDATED_DELAI_PAIEMENT)
            .delaiValiditeLc(UPDATED_DELAI_VALIDITE_LC)
            .dateExpedition(UPDATED_DATE_EXPEDITION)
            .dateOuverture(UPDATED_DATE_OUVERTURE)
            .dateValiditeEi(UPDATED_DATE_VALIDITE_EI)
            .dateLimiteExp(UPDATED_DATE_LIMITE_EXP)
            .dateValiditeLc(UPDATED_DATE_VALIDITE_LC)
            .montnatTotal(UPDATED_MONTNAT_TOTAL)
            .montnatFob(UPDATED_MONTNAT_FOB)
            .montnatFret(UPDATED_MONTNAT_FRET)
            .totalTc(UPDATED_TOTAL_TC)
            .designationBanque(UPDATED_DESIGNATION_BANQUE)
            .paiementAvue(UPDATED_PAIEMENT_AVUE)
            .encours(UPDATED_ENCOURS);
        AchatDossierDTO achatDossierDTO = achatDossierMapper.toDto(updatedAchatDossier);

        restAchatDossierMockMvc.perform(put("/api/achat-dossiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatDossierDTO)))
            .andExpect(status().isOk());

        // Validate the AchatDossier in the database
        List<AchatDossier> achatDossierList = achatDossierRepository.findAll();
        assertThat(achatDossierList).hasSize(databaseSizeBeforeUpdate);
        AchatDossier testAchatDossier = achatDossierList.get(achatDossierList.size() - 1);
        assertThat(testAchatDossier.getIdDossier()).isEqualTo(UPDATED_ID_DOSSIER);
        assertThat(testAchatDossier.getNumeroDossier()).isEqualTo(UPDATED_NUMERO_DOSSIER);
        assertThat(testAchatDossier.getCodeFournisseur()).isEqualTo(UPDATED_CODE_FOURNISSEUR);
        assertThat(testAchatDossier.getDesignationFournisseur()).isEqualTo(UPDATED_DESIGNATION_FOURNISSEUR);
        assertThat(testAchatDossier.getIncoterm()).isEqualTo(UPDATED_INCOTERM);
        assertThat(testAchatDossier.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testAchatDossier.getTolerance()).isEqualTo(UPDATED_TOLERANCE);
        assertThat(testAchatDossier.getNumeroEi()).isEqualTo(UPDATED_NUMERO_EI);
        assertThat(testAchatDossier.getNumeroEiComp()).isEqualTo(UPDATED_NUMERO_EI_COMP);
        assertThat(testAchatDossier.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testAchatDossier.getDelaiPaiement()).isEqualTo(UPDATED_DELAI_PAIEMENT);
        assertThat(testAchatDossier.getDelaiValiditeLc()).isEqualTo(UPDATED_DELAI_VALIDITE_LC);
        assertThat(testAchatDossier.getDateExpedition()).isEqualTo(UPDATED_DATE_EXPEDITION);
        assertThat(testAchatDossier.getDateOuverture()).isEqualTo(UPDATED_DATE_OUVERTURE);
        assertThat(testAchatDossier.getDateValiditeEi()).isEqualTo(UPDATED_DATE_VALIDITE_EI);
        assertThat(testAchatDossier.getDateLimiteExp()).isEqualTo(UPDATED_DATE_LIMITE_EXP);
        assertThat(testAchatDossier.getDateValiditeLc()).isEqualTo(UPDATED_DATE_VALIDITE_LC);
        assertThat(testAchatDossier.getMontnatTotal()).isEqualTo(UPDATED_MONTNAT_TOTAL);
        assertThat(testAchatDossier.getMontnatFob()).isEqualTo(UPDATED_MONTNAT_FOB);
        assertThat(testAchatDossier.getMontnatFret()).isEqualTo(UPDATED_MONTNAT_FRET);
        assertThat(testAchatDossier.getTotalTc()).isEqualTo(UPDATED_TOTAL_TC);
        assertThat(testAchatDossier.getDesignationBanque()).isEqualTo(UPDATED_DESIGNATION_BANQUE);
        assertThat(testAchatDossier.getPaiementAvue()).isEqualTo(UPDATED_PAIEMENT_AVUE);
        assertThat(testAchatDossier.getEncours()).isEqualTo(UPDATED_ENCOURS);

        // Validate the AchatDossier in Elasticsearch
        verify(mockAchatDossierSearchRepository, times(1)).save(testAchatDossier);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatDossier() throws Exception {
        int databaseSizeBeforeUpdate = achatDossierRepository.findAll().size();

        // Create the AchatDossier
        AchatDossierDTO achatDossierDTO = achatDossierMapper.toDto(achatDossier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatDossierMockMvc.perform(put("/api/achat-dossiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatDossierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatDossier in the database
        List<AchatDossier> achatDossierList = achatDossierRepository.findAll();
        assertThat(achatDossierList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatDossier in Elasticsearch
        verify(mockAchatDossierSearchRepository, times(0)).save(achatDossier);
    }

    @Test
    @Transactional
    public void deleteAchatDossier() throws Exception {
        // Initialize the database
        achatDossierRepository.saveAndFlush(achatDossier);

        int databaseSizeBeforeDelete = achatDossierRepository.findAll().size();

        // Get the achatDossier
        restAchatDossierMockMvc.perform(delete("/api/achat-dossiers/{id}", achatDossier.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatDossier> achatDossierList = achatDossierRepository.findAll();
        assertThat(achatDossierList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatDossier in Elasticsearch
        verify(mockAchatDossierSearchRepository, times(1)).deleteById(achatDossier.getId());
    }

    @Test
    @Transactional
    public void searchAchatDossier() throws Exception {
        // Initialize the database
        achatDossierRepository.saveAndFlush(achatDossier);
        when(mockAchatDossierSearchRepository.search(queryStringQuery("id:" + achatDossier.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatDossier), PageRequest.of(0, 1), 1));
        // Search the achatDossier
        restAchatDossierMockMvc.perform(get("/api/_search/achat-dossiers?query=id:" + achatDossier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatDossier.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDossier").value(hasItem(DEFAULT_ID_DOSSIER)))
            .andExpect(jsonPath("$.[*].numeroDossier").value(hasItem(DEFAULT_NUMERO_DOSSIER)))
            .andExpect(jsonPath("$.[*].codeFournisseur").value(hasItem(DEFAULT_CODE_FOURNISSEUR)))
            .andExpect(jsonPath("$.[*].designationFournisseur").value(hasItem(DEFAULT_DESIGNATION_FOURNISSEUR)))
            .andExpect(jsonPath("$.[*].incoterm").value(hasItem(DEFAULT_INCOTERM)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].tolerance").value(hasItem(DEFAULT_TOLERANCE)))
            .andExpect(jsonPath("$.[*].numeroEi").value(hasItem(DEFAULT_NUMERO_EI)))
            .andExpect(jsonPath("$.[*].numeroEiComp").value(hasItem(DEFAULT_NUMERO_EI_COMP)))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].delaiPaiement").value(hasItem(DEFAULT_DELAI_PAIEMENT)))
            .andExpect(jsonPath("$.[*].delaiValiditeLc").value(hasItem(DEFAULT_DELAI_VALIDITE_LC)))
            .andExpect(jsonPath("$.[*].dateExpedition").value(hasItem(DEFAULT_DATE_EXPEDITION.toString())))
            .andExpect(jsonPath("$.[*].dateOuverture").value(hasItem(DEFAULT_DATE_OUVERTURE.toString())))
            .andExpect(jsonPath("$.[*].dateValiditeEi").value(hasItem(DEFAULT_DATE_VALIDITE_EI.toString())))
            .andExpect(jsonPath("$.[*].dateLimiteExp").value(hasItem(DEFAULT_DATE_LIMITE_EXP.toString())))
            .andExpect(jsonPath("$.[*].dateValiditeLc").value(hasItem(DEFAULT_DATE_VALIDITE_LC.toString())))
            .andExpect(jsonPath("$.[*].montnatTotal").value(hasItem(DEFAULT_MONTNAT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].montnatFob").value(hasItem(DEFAULT_MONTNAT_FOB.intValue())))
            .andExpect(jsonPath("$.[*].montnatFret").value(hasItem(DEFAULT_MONTNAT_FRET.intValue())))
            .andExpect(jsonPath("$.[*].totalTc").value(hasItem(DEFAULT_TOTAL_TC)))
            .andExpect(jsonPath("$.[*].designationBanque").value(hasItem(DEFAULT_DESIGNATION_BANQUE)))
            .andExpect(jsonPath("$.[*].paiementAvue").value(hasItem(DEFAULT_PAIEMENT_AVUE)))
            .andExpect(jsonPath("$.[*].encours").value(hasItem(DEFAULT_ENCOURS.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatDossier.class);
        AchatDossier achatDossier1 = new AchatDossier();
        achatDossier1.setId(1L);
        AchatDossier achatDossier2 = new AchatDossier();
        achatDossier2.setId(achatDossier1.getId());
        assertThat(achatDossier1).isEqualTo(achatDossier2);
        achatDossier2.setId(2L);
        assertThat(achatDossier1).isNotEqualTo(achatDossier2);
        achatDossier1.setId(null);
        assertThat(achatDossier1).isNotEqualTo(achatDossier2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatDossierDTO.class);
        AchatDossierDTO achatDossierDTO1 = new AchatDossierDTO();
        achatDossierDTO1.setId(1L);
        AchatDossierDTO achatDossierDTO2 = new AchatDossierDTO();
        assertThat(achatDossierDTO1).isNotEqualTo(achatDossierDTO2);
        achatDossierDTO2.setId(achatDossierDTO1.getId());
        assertThat(achatDossierDTO1).isEqualTo(achatDossierDTO2);
        achatDossierDTO2.setId(2L);
        assertThat(achatDossierDTO1).isNotEqualTo(achatDossierDTO2);
        achatDossierDTO1.setId(null);
        assertThat(achatDossierDTO1).isNotEqualTo(achatDossierDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatDossierMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatDossierMapper.fromId(null)).isNull();
    }
}
