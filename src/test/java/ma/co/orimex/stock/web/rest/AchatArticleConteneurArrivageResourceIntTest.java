package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatArticleConteneurArrivage;
import ma.co.orimex.stock.repository.AchatArticleConteneurArrivageRepository;
import ma.co.orimex.stock.repository.search.AchatArticleConteneurArrivageSearchRepository;
import ma.co.orimex.stock.service.AchatArticleConteneurArrivageService;
import ma.co.orimex.stock.service.dto.AchatArticleConteneurArrivageDTO;
import ma.co.orimex.stock.service.mapper.AchatArticleConteneurArrivageMapper;
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
 * Test class for the AchatArticleConteneurArrivageResource REST controller.
 *
 * @see AchatArticleConteneurArrivageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatArticleConteneurArrivageResourceIntTest {

    private static final Integer DEFAULT_ID_ARTICLE_CONTENEUR_ARRIVAGE = 1;
    private static final Integer UPDATED_ID_ARTICLE_CONTENEUR_ARRIVAGE = 2;

    private static final BigDecimal DEFAULT_DIMENSION = new BigDecimal(1);
    private static final BigDecimal UPDATED_DIMENSION = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(2);

    private static final Integer DEFAULT_NOMBRE_CAISSESTC = 1;
    private static final Integer UPDATED_NOMBRE_CAISSESTC = 2;

    private static final Integer DEFAULT_NOMBRE_FEUILLECAISSE = 1;
    private static final Integer UPDATED_NOMBRE_FEUILLECAISSE = 2;

    private static final BigDecimal DEFAULT_PRIX_UNITAIRE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRIX_UNITAIRE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QUANTITE = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTITE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_POIDS = new BigDecimal(1);
    private static final BigDecimal UPDATED_POIDS = new BigDecimal(2);

    @Autowired
    private AchatArticleConteneurArrivageRepository achatArticleConteneurArrivageRepository;

    @Autowired
    private AchatArticleConteneurArrivageMapper achatArticleConteneurArrivageMapper;

    @Autowired
    private AchatArticleConteneurArrivageService achatArticleConteneurArrivageService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatArticleConteneurArrivageSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatArticleConteneurArrivageSearchRepository mockAchatArticleConteneurArrivageSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatArticleConteneurArrivageMockMvc;

    private AchatArticleConteneurArrivage achatArticleConteneurArrivage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatArticleConteneurArrivageResource achatArticleConteneurArrivageResource = new AchatArticleConteneurArrivageResource(achatArticleConteneurArrivageService);
        this.restAchatArticleConteneurArrivageMockMvc = MockMvcBuilders.standaloneSetup(achatArticleConteneurArrivageResource)
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
    public static AchatArticleConteneurArrivage createEntity(EntityManager em) {
        AchatArticleConteneurArrivage achatArticleConteneurArrivage = new AchatArticleConteneurArrivage()
            .idArticleConteneurArrivage(DEFAULT_ID_ARTICLE_CONTENEUR_ARRIVAGE)
            .dimension(DEFAULT_DIMENSION)
            .montant(DEFAULT_MONTANT)
            .nombreCaissestc(DEFAULT_NOMBRE_CAISSESTC)
            .nombreFeuillecaisse(DEFAULT_NOMBRE_FEUILLECAISSE)
            .prixUnitaire(DEFAULT_PRIX_UNITAIRE)
            .quantite(DEFAULT_QUANTITE)
            .poids(DEFAULT_POIDS);
        return achatArticleConteneurArrivage;
    }

    @Before
    public void initTest() {
        achatArticleConteneurArrivage = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatArticleConteneurArrivage() throws Exception {
        int databaseSizeBeforeCreate = achatArticleConteneurArrivageRepository.findAll().size();

        // Create the AchatArticleConteneurArrivage
        AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO = achatArticleConteneurArrivageMapper.toDto(achatArticleConteneurArrivage);
        restAchatArticleConteneurArrivageMockMvc.perform(post("/api/achat-article-conteneur-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleConteneurArrivageDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatArticleConteneurArrivage in the database
        List<AchatArticleConteneurArrivage> achatArticleConteneurArrivageList = achatArticleConteneurArrivageRepository.findAll();
        assertThat(achatArticleConteneurArrivageList).hasSize(databaseSizeBeforeCreate + 1);
        AchatArticleConteneurArrivage testAchatArticleConteneurArrivage = achatArticleConteneurArrivageList.get(achatArticleConteneurArrivageList.size() - 1);
        assertThat(testAchatArticleConteneurArrivage.getIdArticleConteneurArrivage()).isEqualTo(DEFAULT_ID_ARTICLE_CONTENEUR_ARRIVAGE);
        assertThat(testAchatArticleConteneurArrivage.getDimension()).isEqualTo(DEFAULT_DIMENSION);
        assertThat(testAchatArticleConteneurArrivage.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testAchatArticleConteneurArrivage.getNombreCaissestc()).isEqualTo(DEFAULT_NOMBRE_CAISSESTC);
        assertThat(testAchatArticleConteneurArrivage.getNombreFeuillecaisse()).isEqualTo(DEFAULT_NOMBRE_FEUILLECAISSE);
        assertThat(testAchatArticleConteneurArrivage.getPrixUnitaire()).isEqualTo(DEFAULT_PRIX_UNITAIRE);
        assertThat(testAchatArticleConteneurArrivage.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
        assertThat(testAchatArticleConteneurArrivage.getPoids()).isEqualTo(DEFAULT_POIDS);

        // Validate the AchatArticleConteneurArrivage in Elasticsearch
        verify(mockAchatArticleConteneurArrivageSearchRepository, times(1)).save(testAchatArticleConteneurArrivage);
    }

    @Test
    @Transactional
    public void createAchatArticleConteneurArrivageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatArticleConteneurArrivageRepository.findAll().size();

        // Create the AchatArticleConteneurArrivage with an existing ID
        achatArticleConteneurArrivage.setId(1L);
        AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO = achatArticleConteneurArrivageMapper.toDto(achatArticleConteneurArrivage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatArticleConteneurArrivageMockMvc.perform(post("/api/achat-article-conteneur-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleConteneurArrivageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatArticleConteneurArrivage in the database
        List<AchatArticleConteneurArrivage> achatArticleConteneurArrivageList = achatArticleConteneurArrivageRepository.findAll();
        assertThat(achatArticleConteneurArrivageList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatArticleConteneurArrivage in Elasticsearch
        verify(mockAchatArticleConteneurArrivageSearchRepository, times(0)).save(achatArticleConteneurArrivage);
    }

    @Test
    @Transactional
    public void getAllAchatArticleConteneurArrivages() throws Exception {
        // Initialize the database
        achatArticleConteneurArrivageRepository.saveAndFlush(achatArticleConteneurArrivage);

        // Get all the achatArticleConteneurArrivageList
        restAchatArticleConteneurArrivageMockMvc.perform(get("/api/achat-article-conteneur-arrivages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatArticleConteneurArrivage.getId().intValue())))
            .andExpect(jsonPath("$.[*].idArticleConteneurArrivage").value(hasItem(DEFAULT_ID_ARTICLE_CONTENEUR_ARRIVAGE)))
            .andExpect(jsonPath("$.[*].dimension").value(hasItem(DEFAULT_DIMENSION.intValue())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].nombreCaissestc").value(hasItem(DEFAULT_NOMBRE_CAISSESTC)))
            .andExpect(jsonPath("$.[*].nombreFeuillecaisse").value(hasItem(DEFAULT_NOMBRE_FEUILLECAISSE)))
            .andExpect(jsonPath("$.[*].prixUnitaire").value(hasItem(DEFAULT_PRIX_UNITAIRE.intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.intValue())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())));
    }
    
    @Test
    @Transactional
    public void getAchatArticleConteneurArrivage() throws Exception {
        // Initialize the database
        achatArticleConteneurArrivageRepository.saveAndFlush(achatArticleConteneurArrivage);

        // Get the achatArticleConteneurArrivage
        restAchatArticleConteneurArrivageMockMvc.perform(get("/api/achat-article-conteneur-arrivages/{id}", achatArticleConteneurArrivage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatArticleConteneurArrivage.getId().intValue()))
            .andExpect(jsonPath("$.idArticleConteneurArrivage").value(DEFAULT_ID_ARTICLE_CONTENEUR_ARRIVAGE))
            .andExpect(jsonPath("$.dimension").value(DEFAULT_DIMENSION.intValue()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.nombreCaissestc").value(DEFAULT_NOMBRE_CAISSESTC))
            .andExpect(jsonPath("$.nombreFeuillecaisse").value(DEFAULT_NOMBRE_FEUILLECAISSE))
            .andExpect(jsonPath("$.prixUnitaire").value(DEFAULT_PRIX_UNITAIRE.intValue()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE.intValue()))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatArticleConteneurArrivage() throws Exception {
        // Get the achatArticleConteneurArrivage
        restAchatArticleConteneurArrivageMockMvc.perform(get("/api/achat-article-conteneur-arrivages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatArticleConteneurArrivage() throws Exception {
        // Initialize the database
        achatArticleConteneurArrivageRepository.saveAndFlush(achatArticleConteneurArrivage);

        int databaseSizeBeforeUpdate = achatArticleConteneurArrivageRepository.findAll().size();

        // Update the achatArticleConteneurArrivage
        AchatArticleConteneurArrivage updatedAchatArticleConteneurArrivage = achatArticleConteneurArrivageRepository.findById(achatArticleConteneurArrivage.getId()).get();
        // Disconnect from session so that the updates on updatedAchatArticleConteneurArrivage are not directly saved in db
        em.detach(updatedAchatArticleConteneurArrivage);
        updatedAchatArticleConteneurArrivage
            .idArticleConteneurArrivage(UPDATED_ID_ARTICLE_CONTENEUR_ARRIVAGE)
            .dimension(UPDATED_DIMENSION)
            .montant(UPDATED_MONTANT)
            .nombreCaissestc(UPDATED_NOMBRE_CAISSESTC)
            .nombreFeuillecaisse(UPDATED_NOMBRE_FEUILLECAISSE)
            .prixUnitaire(UPDATED_PRIX_UNITAIRE)
            .quantite(UPDATED_QUANTITE)
            .poids(UPDATED_POIDS);
        AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO = achatArticleConteneurArrivageMapper.toDto(updatedAchatArticleConteneurArrivage);

        restAchatArticleConteneurArrivageMockMvc.perform(put("/api/achat-article-conteneur-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleConteneurArrivageDTO)))
            .andExpect(status().isOk());

        // Validate the AchatArticleConteneurArrivage in the database
        List<AchatArticleConteneurArrivage> achatArticleConteneurArrivageList = achatArticleConteneurArrivageRepository.findAll();
        assertThat(achatArticleConteneurArrivageList).hasSize(databaseSizeBeforeUpdate);
        AchatArticleConteneurArrivage testAchatArticleConteneurArrivage = achatArticleConteneurArrivageList.get(achatArticleConteneurArrivageList.size() - 1);
        assertThat(testAchatArticleConteneurArrivage.getIdArticleConteneurArrivage()).isEqualTo(UPDATED_ID_ARTICLE_CONTENEUR_ARRIVAGE);
        assertThat(testAchatArticleConteneurArrivage.getDimension()).isEqualTo(UPDATED_DIMENSION);
        assertThat(testAchatArticleConteneurArrivage.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testAchatArticleConteneurArrivage.getNombreCaissestc()).isEqualTo(UPDATED_NOMBRE_CAISSESTC);
        assertThat(testAchatArticleConteneurArrivage.getNombreFeuillecaisse()).isEqualTo(UPDATED_NOMBRE_FEUILLECAISSE);
        assertThat(testAchatArticleConteneurArrivage.getPrixUnitaire()).isEqualTo(UPDATED_PRIX_UNITAIRE);
        assertThat(testAchatArticleConteneurArrivage.getQuantite()).isEqualTo(UPDATED_QUANTITE);
        assertThat(testAchatArticleConteneurArrivage.getPoids()).isEqualTo(UPDATED_POIDS);

        // Validate the AchatArticleConteneurArrivage in Elasticsearch
        verify(mockAchatArticleConteneurArrivageSearchRepository, times(1)).save(testAchatArticleConteneurArrivage);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatArticleConteneurArrivage() throws Exception {
        int databaseSizeBeforeUpdate = achatArticleConteneurArrivageRepository.findAll().size();

        // Create the AchatArticleConteneurArrivage
        AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO = achatArticleConteneurArrivageMapper.toDto(achatArticleConteneurArrivage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatArticleConteneurArrivageMockMvc.perform(put("/api/achat-article-conteneur-arrivages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleConteneurArrivageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatArticleConteneurArrivage in the database
        List<AchatArticleConteneurArrivage> achatArticleConteneurArrivageList = achatArticleConteneurArrivageRepository.findAll();
        assertThat(achatArticleConteneurArrivageList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatArticleConteneurArrivage in Elasticsearch
        verify(mockAchatArticleConteneurArrivageSearchRepository, times(0)).save(achatArticleConteneurArrivage);
    }

    @Test
    @Transactional
    public void deleteAchatArticleConteneurArrivage() throws Exception {
        // Initialize the database
        achatArticleConteneurArrivageRepository.saveAndFlush(achatArticleConteneurArrivage);

        int databaseSizeBeforeDelete = achatArticleConteneurArrivageRepository.findAll().size();

        // Get the achatArticleConteneurArrivage
        restAchatArticleConteneurArrivageMockMvc.perform(delete("/api/achat-article-conteneur-arrivages/{id}", achatArticleConteneurArrivage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatArticleConteneurArrivage> achatArticleConteneurArrivageList = achatArticleConteneurArrivageRepository.findAll();
        assertThat(achatArticleConteneurArrivageList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatArticleConteneurArrivage in Elasticsearch
        verify(mockAchatArticleConteneurArrivageSearchRepository, times(1)).deleteById(achatArticleConteneurArrivage.getId());
    }

    @Test
    @Transactional
    public void searchAchatArticleConteneurArrivage() throws Exception {
        // Initialize the database
        achatArticleConteneurArrivageRepository.saveAndFlush(achatArticleConteneurArrivage);
        when(mockAchatArticleConteneurArrivageSearchRepository.search(queryStringQuery("id:" + achatArticleConteneurArrivage.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatArticleConteneurArrivage), PageRequest.of(0, 1), 1));
        // Search the achatArticleConteneurArrivage
        restAchatArticleConteneurArrivageMockMvc.perform(get("/api/_search/achat-article-conteneur-arrivages?query=id:" + achatArticleConteneurArrivage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatArticleConteneurArrivage.getId().intValue())))
            .andExpect(jsonPath("$.[*].idArticleConteneurArrivage").value(hasItem(DEFAULT_ID_ARTICLE_CONTENEUR_ARRIVAGE)))
            .andExpect(jsonPath("$.[*].dimension").value(hasItem(DEFAULT_DIMENSION.intValue())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].nombreCaissestc").value(hasItem(DEFAULT_NOMBRE_CAISSESTC)))
            .andExpect(jsonPath("$.[*].nombreFeuillecaisse").value(hasItem(DEFAULT_NOMBRE_FEUILLECAISSE)))
            .andExpect(jsonPath("$.[*].prixUnitaire").value(hasItem(DEFAULT_PRIX_UNITAIRE.intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.intValue())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatArticleConteneurArrivage.class);
        AchatArticleConteneurArrivage achatArticleConteneurArrivage1 = new AchatArticleConteneurArrivage();
        achatArticleConteneurArrivage1.setId(1L);
        AchatArticleConteneurArrivage achatArticleConteneurArrivage2 = new AchatArticleConteneurArrivage();
        achatArticleConteneurArrivage2.setId(achatArticleConteneurArrivage1.getId());
        assertThat(achatArticleConteneurArrivage1).isEqualTo(achatArticleConteneurArrivage2);
        achatArticleConteneurArrivage2.setId(2L);
        assertThat(achatArticleConteneurArrivage1).isNotEqualTo(achatArticleConteneurArrivage2);
        achatArticleConteneurArrivage1.setId(null);
        assertThat(achatArticleConteneurArrivage1).isNotEqualTo(achatArticleConteneurArrivage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatArticleConteneurArrivageDTO.class);
        AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO1 = new AchatArticleConteneurArrivageDTO();
        achatArticleConteneurArrivageDTO1.setId(1L);
        AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO2 = new AchatArticleConteneurArrivageDTO();
        assertThat(achatArticleConteneurArrivageDTO1).isNotEqualTo(achatArticleConteneurArrivageDTO2);
        achatArticleConteneurArrivageDTO2.setId(achatArticleConteneurArrivageDTO1.getId());
        assertThat(achatArticleConteneurArrivageDTO1).isEqualTo(achatArticleConteneurArrivageDTO2);
        achatArticleConteneurArrivageDTO2.setId(2L);
        assertThat(achatArticleConteneurArrivageDTO1).isNotEqualTo(achatArticleConteneurArrivageDTO2);
        achatArticleConteneurArrivageDTO1.setId(null);
        assertThat(achatArticleConteneurArrivageDTO1).isNotEqualTo(achatArticleConteneurArrivageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatArticleConteneurArrivageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatArticleConteneurArrivageMapper.fromId(null)).isNull();
    }
}
