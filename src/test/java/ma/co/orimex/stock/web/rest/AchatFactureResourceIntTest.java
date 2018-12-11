package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatFacture;
import ma.co.orimex.stock.repository.AchatFactureRepository;
import ma.co.orimex.stock.repository.search.AchatFactureSearchRepository;
import ma.co.orimex.stock.service.AchatFactureService;
import ma.co.orimex.stock.service.dto.AchatFactureDTO;
import ma.co.orimex.stock.service.mapper.AchatFactureMapper;
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
 * Test class for the AchatFactureResource REST controller.
 *
 * @see AchatFactureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatFactureResourceIntTest {

    private static final Integer DEFAULT_ID_FACTURE = 1;
    private static final Integer UPDATED_ID_FACTURE = 2;

    private static final String DEFAULT_NUMERO_FACTURE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_FACTURE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_FACTURE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FACTURE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_MONTANT_FOB = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_FOB = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_FRET = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_FRET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_TOTAL = new BigDecimal(2);

    private static final Integer DEFAULT_NOMBRE_TC = 1;
    private static final Integer UPDATED_NOMBRE_TC = 2;

    private static final BigDecimal DEFAULT_POIDS = new BigDecimal(1);
    private static final BigDecimal UPDATED_POIDS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QUANTITE = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTITE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJUSTEMENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJUSTEMENT = new BigDecimal(2);

    private static final String DEFAULT_NUMERO_PIECE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_PIECE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_BL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_BL = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NUMERO_BL = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_BL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ECHEANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ECHEANCE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ETAT = 1;
    private static final Integer UPDATED_ETAT = 2;

    private static final Integer DEFAULT_BANQUE_REGLEMENT = 1;
    private static final Integer UPDATED_BANQUE_REGLEMENT = 2;

    private static final LocalDate DEFAULT_DATE_VALEUR = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VALEUR = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_COURS = new BigDecimal(1);
    private static final BigDecimal UPDATED_COURS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_DH = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_DH = new BigDecimal(2);

    private static final LocalDate DEFAULT_ECHECANCE_FINANCEMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ECHECANCE_FINANCEMENT = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_INTERET_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTERET_1 = new BigDecimal(2);

    private static final LocalDate DEFAULT_DATE_REGLEMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REGLEMENT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DERNIERE_ECHEANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DERNIERE_ECHEANCE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TRANSMISE = 1;
    private static final Integer UPDATED_TRANSMISE = 2;

    private static final LocalDate DEFAULT_ECHEANCE_REFINANCEMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ECHEANCE_REFINANCEMENT = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_INTERET_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTERET_2 = new BigDecimal(2);

    private static final Integer DEFAULT_INTERET_1_REGLE = 1;
    private static final Integer UPDATED_INTERET_1_REGLE = 2;

    @Autowired
    private AchatFactureRepository achatFactureRepository;

    @Autowired
    private AchatFactureMapper achatFactureMapper;

    @Autowired
    private AchatFactureService achatFactureService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatFactureSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatFactureSearchRepository mockAchatFactureSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatFactureMockMvc;

    private AchatFacture achatFacture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatFactureResource achatFactureResource = new AchatFactureResource(achatFactureService);
        this.restAchatFactureMockMvc = MockMvcBuilders.standaloneSetup(achatFactureResource)
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
    public static AchatFacture createEntity(EntityManager em) {
        AchatFacture achatFacture = new AchatFacture()
            .idFacture(DEFAULT_ID_FACTURE)
            .numeroFacture(DEFAULT_NUMERO_FACTURE)
            .dateFacture(DEFAULT_DATE_FACTURE)
            .montantFob(DEFAULT_MONTANT_FOB)
            .montantFret(DEFAULT_MONTANT_FRET)
            .montantTotal(DEFAULT_MONTANT_TOTAL)
            .nombreTc(DEFAULT_NOMBRE_TC)
            .poids(DEFAULT_POIDS)
            .quantite(DEFAULT_QUANTITE)
            .ajustement(DEFAULT_AJUSTEMENT)
            .numeroPiece(DEFAULT_NUMERO_PIECE)
            .dateBl(DEFAULT_DATE_BL)
            .numeroBl(DEFAULT_NUMERO_BL)
            .dateEcheance(DEFAULT_DATE_ECHEANCE)
            .etat(DEFAULT_ETAT)
            .banqueReglement(DEFAULT_BANQUE_REGLEMENT)
            .dateValeur(DEFAULT_DATE_VALEUR)
            .cours(DEFAULT_COURS)
            .montantDh(DEFAULT_MONTANT_DH)
            .echecanceFinancement(DEFAULT_ECHECANCE_FINANCEMENT)
            .interet1(DEFAULT_INTERET_1)
            .dateReglement(DEFAULT_DATE_REGLEMENT)
            .derniereEcheance(DEFAULT_DERNIERE_ECHEANCE)
            .transmise(DEFAULT_TRANSMISE)
            .echeanceRefinancement(DEFAULT_ECHEANCE_REFINANCEMENT)
            .interet2(DEFAULT_INTERET_2)
            .interet1Regle(DEFAULT_INTERET_1_REGLE);
        return achatFacture;
    }

    @Before
    public void initTest() {
        achatFacture = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatFacture() throws Exception {
        int databaseSizeBeforeCreate = achatFactureRepository.findAll().size();

        // Create the AchatFacture
        AchatFactureDTO achatFactureDTO = achatFactureMapper.toDto(achatFacture);
        restAchatFactureMockMvc.perform(post("/api/achat-factures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatFactureDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatFacture in the database
        List<AchatFacture> achatFactureList = achatFactureRepository.findAll();
        assertThat(achatFactureList).hasSize(databaseSizeBeforeCreate + 1);
        AchatFacture testAchatFacture = achatFactureList.get(achatFactureList.size() - 1);
        assertThat(testAchatFacture.getIdFacture()).isEqualTo(DEFAULT_ID_FACTURE);
        assertThat(testAchatFacture.getNumeroFacture()).isEqualTo(DEFAULT_NUMERO_FACTURE);
        assertThat(testAchatFacture.getDateFacture()).isEqualTo(DEFAULT_DATE_FACTURE);
        assertThat(testAchatFacture.getMontantFob()).isEqualTo(DEFAULT_MONTANT_FOB);
        assertThat(testAchatFacture.getMontantFret()).isEqualTo(DEFAULT_MONTANT_FRET);
        assertThat(testAchatFacture.getMontantTotal()).isEqualTo(DEFAULT_MONTANT_TOTAL);
        assertThat(testAchatFacture.getNombreTc()).isEqualTo(DEFAULT_NOMBRE_TC);
        assertThat(testAchatFacture.getPoids()).isEqualTo(DEFAULT_POIDS);
        assertThat(testAchatFacture.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
        assertThat(testAchatFacture.getAjustement()).isEqualTo(DEFAULT_AJUSTEMENT);
        assertThat(testAchatFacture.getNumeroPiece()).isEqualTo(DEFAULT_NUMERO_PIECE);
        assertThat(testAchatFacture.getDateBl()).isEqualTo(DEFAULT_DATE_BL);
        assertThat(testAchatFacture.getNumeroBl()).isEqualTo(DEFAULT_NUMERO_BL);
        assertThat(testAchatFacture.getDateEcheance()).isEqualTo(DEFAULT_DATE_ECHEANCE);
        assertThat(testAchatFacture.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testAchatFacture.getBanqueReglement()).isEqualTo(DEFAULT_BANQUE_REGLEMENT);
        assertThat(testAchatFacture.getDateValeur()).isEqualTo(DEFAULT_DATE_VALEUR);
        assertThat(testAchatFacture.getCours()).isEqualTo(DEFAULT_COURS);
        assertThat(testAchatFacture.getMontantDh()).isEqualTo(DEFAULT_MONTANT_DH);
        assertThat(testAchatFacture.getEchecanceFinancement()).isEqualTo(DEFAULT_ECHECANCE_FINANCEMENT);
        assertThat(testAchatFacture.getInteret1()).isEqualTo(DEFAULT_INTERET_1);
        assertThat(testAchatFacture.getDateReglement()).isEqualTo(DEFAULT_DATE_REGLEMENT);
        assertThat(testAchatFacture.getDerniereEcheance()).isEqualTo(DEFAULT_DERNIERE_ECHEANCE);
        assertThat(testAchatFacture.getTransmise()).isEqualTo(DEFAULT_TRANSMISE);
        assertThat(testAchatFacture.getEcheanceRefinancement()).isEqualTo(DEFAULT_ECHEANCE_REFINANCEMENT);
        assertThat(testAchatFacture.getInteret2()).isEqualTo(DEFAULT_INTERET_2);
        assertThat(testAchatFacture.getInteret1Regle()).isEqualTo(DEFAULT_INTERET_1_REGLE);

        // Validate the AchatFacture in Elasticsearch
        verify(mockAchatFactureSearchRepository, times(1)).save(testAchatFacture);
    }

    @Test
    @Transactional
    public void createAchatFactureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatFactureRepository.findAll().size();

        // Create the AchatFacture with an existing ID
        achatFacture.setId(1L);
        AchatFactureDTO achatFactureDTO = achatFactureMapper.toDto(achatFacture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatFactureMockMvc.perform(post("/api/achat-factures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatFactureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatFacture in the database
        List<AchatFacture> achatFactureList = achatFactureRepository.findAll();
        assertThat(achatFactureList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatFacture in Elasticsearch
        verify(mockAchatFactureSearchRepository, times(0)).save(achatFacture);
    }

    @Test
    @Transactional
    public void getAllAchatFactures() throws Exception {
        // Initialize the database
        achatFactureRepository.saveAndFlush(achatFacture);

        // Get all the achatFactureList
        restAchatFactureMockMvc.perform(get("/api/achat-factures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatFacture.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFacture").value(hasItem(DEFAULT_ID_FACTURE)))
            .andExpect(jsonPath("$.[*].numeroFacture").value(hasItem(DEFAULT_NUMERO_FACTURE.toString())))
            .andExpect(jsonPath("$.[*].dateFacture").value(hasItem(DEFAULT_DATE_FACTURE.toString())))
            .andExpect(jsonPath("$.[*].montantFob").value(hasItem(DEFAULT_MONTANT_FOB.intValue())))
            .andExpect(jsonPath("$.[*].montantFret").value(hasItem(DEFAULT_MONTANT_FRET.intValue())))
            .andExpect(jsonPath("$.[*].montantTotal").value(hasItem(DEFAULT_MONTANT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].nombreTc").value(hasItem(DEFAULT_NOMBRE_TC)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.intValue())))
            .andExpect(jsonPath("$.[*].ajustement").value(hasItem(DEFAULT_AJUSTEMENT.intValue())))
            .andExpect(jsonPath("$.[*].numeroPiece").value(hasItem(DEFAULT_NUMERO_PIECE.toString())))
            .andExpect(jsonPath("$.[*].dateBl").value(hasItem(DEFAULT_DATE_BL.toString())))
            .andExpect(jsonPath("$.[*].numeroBl").value(hasItem(DEFAULT_NUMERO_BL.toString())))
            .andExpect(jsonPath("$.[*].dateEcheance").value(hasItem(DEFAULT_DATE_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT)))
            .andExpect(jsonPath("$.[*].banqueReglement").value(hasItem(DEFAULT_BANQUE_REGLEMENT)))
            .andExpect(jsonPath("$.[*].dateValeur").value(hasItem(DEFAULT_DATE_VALEUR.toString())))
            .andExpect(jsonPath("$.[*].cours").value(hasItem(DEFAULT_COURS.intValue())))
            .andExpect(jsonPath("$.[*].montantDh").value(hasItem(DEFAULT_MONTANT_DH.intValue())))
            .andExpect(jsonPath("$.[*].echecanceFinancement").value(hasItem(DEFAULT_ECHECANCE_FINANCEMENT.toString())))
            .andExpect(jsonPath("$.[*].interet1").value(hasItem(DEFAULT_INTERET_1.intValue())))
            .andExpect(jsonPath("$.[*].dateReglement").value(hasItem(DEFAULT_DATE_REGLEMENT.toString())))
            .andExpect(jsonPath("$.[*].derniereEcheance").value(hasItem(DEFAULT_DERNIERE_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].transmise").value(hasItem(DEFAULT_TRANSMISE)))
            .andExpect(jsonPath("$.[*].echeanceRefinancement").value(hasItem(DEFAULT_ECHEANCE_REFINANCEMENT.toString())))
            .andExpect(jsonPath("$.[*].interet2").value(hasItem(DEFAULT_INTERET_2.intValue())))
            .andExpect(jsonPath("$.[*].interet1Regle").value(hasItem(DEFAULT_INTERET_1_REGLE)));
    }
    
    @Test
    @Transactional
    public void getAchatFacture() throws Exception {
        // Initialize the database
        achatFactureRepository.saveAndFlush(achatFacture);

        // Get the achatFacture
        restAchatFactureMockMvc.perform(get("/api/achat-factures/{id}", achatFacture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatFacture.getId().intValue()))
            .andExpect(jsonPath("$.idFacture").value(DEFAULT_ID_FACTURE))
            .andExpect(jsonPath("$.numeroFacture").value(DEFAULT_NUMERO_FACTURE.toString()))
            .andExpect(jsonPath("$.dateFacture").value(DEFAULT_DATE_FACTURE.toString()))
            .andExpect(jsonPath("$.montantFob").value(DEFAULT_MONTANT_FOB.intValue()))
            .andExpect(jsonPath("$.montantFret").value(DEFAULT_MONTANT_FRET.intValue()))
            .andExpect(jsonPath("$.montantTotal").value(DEFAULT_MONTANT_TOTAL.intValue()))
            .andExpect(jsonPath("$.nombreTc").value(DEFAULT_NOMBRE_TC))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.intValue()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE.intValue()))
            .andExpect(jsonPath("$.ajustement").value(DEFAULT_AJUSTEMENT.intValue()))
            .andExpect(jsonPath("$.numeroPiece").value(DEFAULT_NUMERO_PIECE.toString()))
            .andExpect(jsonPath("$.dateBl").value(DEFAULT_DATE_BL.toString()))
            .andExpect(jsonPath("$.numeroBl").value(DEFAULT_NUMERO_BL.toString()))
            .andExpect(jsonPath("$.dateEcheance").value(DEFAULT_DATE_ECHEANCE.toString()))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT))
            .andExpect(jsonPath("$.banqueReglement").value(DEFAULT_BANQUE_REGLEMENT))
            .andExpect(jsonPath("$.dateValeur").value(DEFAULT_DATE_VALEUR.toString()))
            .andExpect(jsonPath("$.cours").value(DEFAULT_COURS.intValue()))
            .andExpect(jsonPath("$.montantDh").value(DEFAULT_MONTANT_DH.intValue()))
            .andExpect(jsonPath("$.echecanceFinancement").value(DEFAULT_ECHECANCE_FINANCEMENT.toString()))
            .andExpect(jsonPath("$.interet1").value(DEFAULT_INTERET_1.intValue()))
            .andExpect(jsonPath("$.dateReglement").value(DEFAULT_DATE_REGLEMENT.toString()))
            .andExpect(jsonPath("$.derniereEcheance").value(DEFAULT_DERNIERE_ECHEANCE.toString()))
            .andExpect(jsonPath("$.transmise").value(DEFAULT_TRANSMISE))
            .andExpect(jsonPath("$.echeanceRefinancement").value(DEFAULT_ECHEANCE_REFINANCEMENT.toString()))
            .andExpect(jsonPath("$.interet2").value(DEFAULT_INTERET_2.intValue()))
            .andExpect(jsonPath("$.interet1Regle").value(DEFAULT_INTERET_1_REGLE));
    }

    @Test
    @Transactional
    public void getNonExistingAchatFacture() throws Exception {
        // Get the achatFacture
        restAchatFactureMockMvc.perform(get("/api/achat-factures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatFacture() throws Exception {
        // Initialize the database
        achatFactureRepository.saveAndFlush(achatFacture);

        int databaseSizeBeforeUpdate = achatFactureRepository.findAll().size();

        // Update the achatFacture
        AchatFacture updatedAchatFacture = achatFactureRepository.findById(achatFacture.getId()).get();
        // Disconnect from session so that the updates on updatedAchatFacture are not directly saved in db
        em.detach(updatedAchatFacture);
        updatedAchatFacture
            .idFacture(UPDATED_ID_FACTURE)
            .numeroFacture(UPDATED_NUMERO_FACTURE)
            .dateFacture(UPDATED_DATE_FACTURE)
            .montantFob(UPDATED_MONTANT_FOB)
            .montantFret(UPDATED_MONTANT_FRET)
            .montantTotal(UPDATED_MONTANT_TOTAL)
            .nombreTc(UPDATED_NOMBRE_TC)
            .poids(UPDATED_POIDS)
            .quantite(UPDATED_QUANTITE)
            .ajustement(UPDATED_AJUSTEMENT)
            .numeroPiece(UPDATED_NUMERO_PIECE)
            .dateBl(UPDATED_DATE_BL)
            .numeroBl(UPDATED_NUMERO_BL)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .etat(UPDATED_ETAT)
            .banqueReglement(UPDATED_BANQUE_REGLEMENT)
            .dateValeur(UPDATED_DATE_VALEUR)
            .cours(UPDATED_COURS)
            .montantDh(UPDATED_MONTANT_DH)
            .echecanceFinancement(UPDATED_ECHECANCE_FINANCEMENT)
            .interet1(UPDATED_INTERET_1)
            .dateReglement(UPDATED_DATE_REGLEMENT)
            .derniereEcheance(UPDATED_DERNIERE_ECHEANCE)
            .transmise(UPDATED_TRANSMISE)
            .echeanceRefinancement(UPDATED_ECHEANCE_REFINANCEMENT)
            .interet2(UPDATED_INTERET_2)
            .interet1Regle(UPDATED_INTERET_1_REGLE);
        AchatFactureDTO achatFactureDTO = achatFactureMapper.toDto(updatedAchatFacture);

        restAchatFactureMockMvc.perform(put("/api/achat-factures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatFactureDTO)))
            .andExpect(status().isOk());

        // Validate the AchatFacture in the database
        List<AchatFacture> achatFactureList = achatFactureRepository.findAll();
        assertThat(achatFactureList).hasSize(databaseSizeBeforeUpdate);
        AchatFacture testAchatFacture = achatFactureList.get(achatFactureList.size() - 1);
        assertThat(testAchatFacture.getIdFacture()).isEqualTo(UPDATED_ID_FACTURE);
        assertThat(testAchatFacture.getNumeroFacture()).isEqualTo(UPDATED_NUMERO_FACTURE);
        assertThat(testAchatFacture.getDateFacture()).isEqualTo(UPDATED_DATE_FACTURE);
        assertThat(testAchatFacture.getMontantFob()).isEqualTo(UPDATED_MONTANT_FOB);
        assertThat(testAchatFacture.getMontantFret()).isEqualTo(UPDATED_MONTANT_FRET);
        assertThat(testAchatFacture.getMontantTotal()).isEqualTo(UPDATED_MONTANT_TOTAL);
        assertThat(testAchatFacture.getNombreTc()).isEqualTo(UPDATED_NOMBRE_TC);
        assertThat(testAchatFacture.getPoids()).isEqualTo(UPDATED_POIDS);
        assertThat(testAchatFacture.getQuantite()).isEqualTo(UPDATED_QUANTITE);
        assertThat(testAchatFacture.getAjustement()).isEqualTo(UPDATED_AJUSTEMENT);
        assertThat(testAchatFacture.getNumeroPiece()).isEqualTo(UPDATED_NUMERO_PIECE);
        assertThat(testAchatFacture.getDateBl()).isEqualTo(UPDATED_DATE_BL);
        assertThat(testAchatFacture.getNumeroBl()).isEqualTo(UPDATED_NUMERO_BL);
        assertThat(testAchatFacture.getDateEcheance()).isEqualTo(UPDATED_DATE_ECHEANCE);
        assertThat(testAchatFacture.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testAchatFacture.getBanqueReglement()).isEqualTo(UPDATED_BANQUE_REGLEMENT);
        assertThat(testAchatFacture.getDateValeur()).isEqualTo(UPDATED_DATE_VALEUR);
        assertThat(testAchatFacture.getCours()).isEqualTo(UPDATED_COURS);
        assertThat(testAchatFacture.getMontantDh()).isEqualTo(UPDATED_MONTANT_DH);
        assertThat(testAchatFacture.getEchecanceFinancement()).isEqualTo(UPDATED_ECHECANCE_FINANCEMENT);
        assertThat(testAchatFacture.getInteret1()).isEqualTo(UPDATED_INTERET_1);
        assertThat(testAchatFacture.getDateReglement()).isEqualTo(UPDATED_DATE_REGLEMENT);
        assertThat(testAchatFacture.getDerniereEcheance()).isEqualTo(UPDATED_DERNIERE_ECHEANCE);
        assertThat(testAchatFacture.getTransmise()).isEqualTo(UPDATED_TRANSMISE);
        assertThat(testAchatFacture.getEcheanceRefinancement()).isEqualTo(UPDATED_ECHEANCE_REFINANCEMENT);
        assertThat(testAchatFacture.getInteret2()).isEqualTo(UPDATED_INTERET_2);
        assertThat(testAchatFacture.getInteret1Regle()).isEqualTo(UPDATED_INTERET_1_REGLE);

        // Validate the AchatFacture in Elasticsearch
        verify(mockAchatFactureSearchRepository, times(1)).save(testAchatFacture);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatFacture() throws Exception {
        int databaseSizeBeforeUpdate = achatFactureRepository.findAll().size();

        // Create the AchatFacture
        AchatFactureDTO achatFactureDTO = achatFactureMapper.toDto(achatFacture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatFactureMockMvc.perform(put("/api/achat-factures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatFactureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatFacture in the database
        List<AchatFacture> achatFactureList = achatFactureRepository.findAll();
        assertThat(achatFactureList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatFacture in Elasticsearch
        verify(mockAchatFactureSearchRepository, times(0)).save(achatFacture);
    }

    @Test
    @Transactional
    public void deleteAchatFacture() throws Exception {
        // Initialize the database
        achatFactureRepository.saveAndFlush(achatFacture);

        int databaseSizeBeforeDelete = achatFactureRepository.findAll().size();

        // Get the achatFacture
        restAchatFactureMockMvc.perform(delete("/api/achat-factures/{id}", achatFacture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatFacture> achatFactureList = achatFactureRepository.findAll();
        assertThat(achatFactureList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatFacture in Elasticsearch
        verify(mockAchatFactureSearchRepository, times(1)).deleteById(achatFacture.getId());
    }

    @Test
    @Transactional
    public void searchAchatFacture() throws Exception {
        // Initialize the database
        achatFactureRepository.saveAndFlush(achatFacture);
        when(mockAchatFactureSearchRepository.search(queryStringQuery("id:" + achatFacture.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatFacture), PageRequest.of(0, 1), 1));
        // Search the achatFacture
        restAchatFactureMockMvc.perform(get("/api/_search/achat-factures?query=id:" + achatFacture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatFacture.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFacture").value(hasItem(DEFAULT_ID_FACTURE)))
            .andExpect(jsonPath("$.[*].numeroFacture").value(hasItem(DEFAULT_NUMERO_FACTURE)))
            .andExpect(jsonPath("$.[*].dateFacture").value(hasItem(DEFAULT_DATE_FACTURE.toString())))
            .andExpect(jsonPath("$.[*].montantFob").value(hasItem(DEFAULT_MONTANT_FOB.intValue())))
            .andExpect(jsonPath("$.[*].montantFret").value(hasItem(DEFAULT_MONTANT_FRET.intValue())))
            .andExpect(jsonPath("$.[*].montantTotal").value(hasItem(DEFAULT_MONTANT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].nombreTc").value(hasItem(DEFAULT_NOMBRE_TC)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.intValue())))
            .andExpect(jsonPath("$.[*].ajustement").value(hasItem(DEFAULT_AJUSTEMENT.intValue())))
            .andExpect(jsonPath("$.[*].numeroPiece").value(hasItem(DEFAULT_NUMERO_PIECE)))
            .andExpect(jsonPath("$.[*].dateBl").value(hasItem(DEFAULT_DATE_BL.toString())))
            .andExpect(jsonPath("$.[*].numeroBl").value(hasItem(DEFAULT_NUMERO_BL)))
            .andExpect(jsonPath("$.[*].dateEcheance").value(hasItem(DEFAULT_DATE_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT)))
            .andExpect(jsonPath("$.[*].banqueReglement").value(hasItem(DEFAULT_BANQUE_REGLEMENT)))
            .andExpect(jsonPath("$.[*].dateValeur").value(hasItem(DEFAULT_DATE_VALEUR.toString())))
            .andExpect(jsonPath("$.[*].cours").value(hasItem(DEFAULT_COURS.intValue())))
            .andExpect(jsonPath("$.[*].montantDh").value(hasItem(DEFAULT_MONTANT_DH.intValue())))
            .andExpect(jsonPath("$.[*].echecanceFinancement").value(hasItem(DEFAULT_ECHECANCE_FINANCEMENT.toString())))
            .andExpect(jsonPath("$.[*].interet1").value(hasItem(DEFAULT_INTERET_1.intValue())))
            .andExpect(jsonPath("$.[*].dateReglement").value(hasItem(DEFAULT_DATE_REGLEMENT.toString())))
            .andExpect(jsonPath("$.[*].derniereEcheance").value(hasItem(DEFAULT_DERNIERE_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].transmise").value(hasItem(DEFAULT_TRANSMISE)))
            .andExpect(jsonPath("$.[*].echeanceRefinancement").value(hasItem(DEFAULT_ECHEANCE_REFINANCEMENT.toString())))
            .andExpect(jsonPath("$.[*].interet2").value(hasItem(DEFAULT_INTERET_2.intValue())))
            .andExpect(jsonPath("$.[*].interet1Regle").value(hasItem(DEFAULT_INTERET_1_REGLE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatFacture.class);
        AchatFacture achatFacture1 = new AchatFacture();
        achatFacture1.setId(1L);
        AchatFacture achatFacture2 = new AchatFacture();
        achatFacture2.setId(achatFacture1.getId());
        assertThat(achatFacture1).isEqualTo(achatFacture2);
        achatFacture2.setId(2L);
        assertThat(achatFacture1).isNotEqualTo(achatFacture2);
        achatFacture1.setId(null);
        assertThat(achatFacture1).isNotEqualTo(achatFacture2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatFactureDTO.class);
        AchatFactureDTO achatFactureDTO1 = new AchatFactureDTO();
        achatFactureDTO1.setId(1L);
        AchatFactureDTO achatFactureDTO2 = new AchatFactureDTO();
        assertThat(achatFactureDTO1).isNotEqualTo(achatFactureDTO2);
        achatFactureDTO2.setId(achatFactureDTO1.getId());
        assertThat(achatFactureDTO1).isEqualTo(achatFactureDTO2);
        achatFactureDTO2.setId(2L);
        assertThat(achatFactureDTO1).isNotEqualTo(achatFactureDTO2);
        achatFactureDTO1.setId(null);
        assertThat(achatFactureDTO1).isNotEqualTo(achatFactureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatFactureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatFactureMapper.fromId(null)).isNull();
    }
}
