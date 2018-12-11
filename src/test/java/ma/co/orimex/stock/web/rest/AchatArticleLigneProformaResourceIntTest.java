package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatArticleLigneProforma;
import ma.co.orimex.stock.repository.AchatArticleLigneProformaRepository;
import ma.co.orimex.stock.repository.search.AchatArticleLigneProformaSearchRepository;
import ma.co.orimex.stock.service.AchatArticleLigneProformaService;
import ma.co.orimex.stock.service.dto.AchatArticleLigneProformaDTO;
import ma.co.orimex.stock.service.mapper.AchatArticleLigneProformaMapper;
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
 * Test class for the AchatArticleLigneProformaResource REST controller.
 *
 * @see AchatArticleLigneProformaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatArticleLigneProformaResourceIntTest {

    private static final Integer DEFAULT_ID_ARTICLE_LIGNE_PROFORMA = 1;
    private static final Integer UPDATED_ID_ARTICLE_LIGNE_PROFORMA = 2;

    private static final Integer DEFAULT_NUMERO_SEQUENCE = 1;
    private static final Integer UPDATED_NUMERO_SEQUENCE = 2;

    private static final Integer DEFAULT_NOMBRE_CAISSES_TC = 1;
    private static final Integer UPDATED_NOMBRE_CAISSES_TC = 2;

    private static final Integer DEFAULT_NOMBRE_FEUILLES_CAISSE = 1;
    private static final Integer UPDATED_NOMBRE_FEUILLES_CAISSE = 2;

    private static final BigDecimal DEFAULT_DIMENTION = new BigDecimal(1);
    private static final BigDecimal UPDATED_DIMENTION = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QUANTITE = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTITE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRIX_UNITAIRE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRIX_UNITAIRE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_POIDS = new BigDecimal(1);
    private static final BigDecimal UPDATED_POIDS = new BigDecimal(2);

    @Autowired
    private AchatArticleLigneProformaRepository achatArticleLigneProformaRepository;

    @Autowired
    private AchatArticleLigneProformaMapper achatArticleLigneProformaMapper;

    @Autowired
    private AchatArticleLigneProformaService achatArticleLigneProformaService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatArticleLigneProformaSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatArticleLigneProformaSearchRepository mockAchatArticleLigneProformaSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatArticleLigneProformaMockMvc;

    private AchatArticleLigneProforma achatArticleLigneProforma;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatArticleLigneProformaResource achatArticleLigneProformaResource = new AchatArticleLigneProformaResource(achatArticleLigneProformaService);
        this.restAchatArticleLigneProformaMockMvc = MockMvcBuilders.standaloneSetup(achatArticleLigneProformaResource)
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
    public static AchatArticleLigneProforma createEntity(EntityManager em) {
        AchatArticleLigneProforma achatArticleLigneProforma = new AchatArticleLigneProforma()
            .idArticleLigneProforma(DEFAULT_ID_ARTICLE_LIGNE_PROFORMA)
            .numeroSequence(DEFAULT_NUMERO_SEQUENCE)
            .nombreCaissesTc(DEFAULT_NOMBRE_CAISSES_TC)
            .nombreFeuillesCaisse(DEFAULT_NOMBRE_FEUILLES_CAISSE)
            .dimention(DEFAULT_DIMENTION)
            .quantite(DEFAULT_QUANTITE)
            .prixUnitaire(DEFAULT_PRIX_UNITAIRE)
            .montant(DEFAULT_MONTANT)
            .poids(DEFAULT_POIDS);
        return achatArticleLigneProforma;
    }

    @Before
    public void initTest() {
        achatArticleLigneProforma = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatArticleLigneProforma() throws Exception {
        int databaseSizeBeforeCreate = achatArticleLigneProformaRepository.findAll().size();

        // Create the AchatArticleLigneProforma
        AchatArticleLigneProformaDTO achatArticleLigneProformaDTO = achatArticleLigneProformaMapper.toDto(achatArticleLigneProforma);
        restAchatArticleLigneProformaMockMvc.perform(post("/api/achat-article-ligne-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleLigneProformaDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatArticleLigneProforma in the database
        List<AchatArticleLigneProforma> achatArticleLigneProformaList = achatArticleLigneProformaRepository.findAll();
        assertThat(achatArticleLigneProformaList).hasSize(databaseSizeBeforeCreate + 1);
        AchatArticleLigneProforma testAchatArticleLigneProforma = achatArticleLigneProformaList.get(achatArticleLigneProformaList.size() - 1);
        assertThat(testAchatArticleLigneProforma.getIdArticleLigneProforma()).isEqualTo(DEFAULT_ID_ARTICLE_LIGNE_PROFORMA);
        assertThat(testAchatArticleLigneProforma.getNumeroSequence()).isEqualTo(DEFAULT_NUMERO_SEQUENCE);
        assertThat(testAchatArticleLigneProforma.getNombreCaissesTc()).isEqualTo(DEFAULT_NOMBRE_CAISSES_TC);
        assertThat(testAchatArticleLigneProforma.getNombreFeuillesCaisse()).isEqualTo(DEFAULT_NOMBRE_FEUILLES_CAISSE);
        assertThat(testAchatArticleLigneProforma.getDimention()).isEqualTo(DEFAULT_DIMENTION);
        assertThat(testAchatArticleLigneProforma.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
        assertThat(testAchatArticleLigneProforma.getPrixUnitaire()).isEqualTo(DEFAULT_PRIX_UNITAIRE);
        assertThat(testAchatArticleLigneProforma.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testAchatArticleLigneProforma.getPoids()).isEqualTo(DEFAULT_POIDS);

        // Validate the AchatArticleLigneProforma in Elasticsearch
        verify(mockAchatArticleLigneProformaSearchRepository, times(1)).save(testAchatArticleLigneProforma);
    }

    @Test
    @Transactional
    public void createAchatArticleLigneProformaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatArticleLigneProformaRepository.findAll().size();

        // Create the AchatArticleLigneProforma with an existing ID
        achatArticleLigneProforma.setId(1L);
        AchatArticleLigneProformaDTO achatArticleLigneProformaDTO = achatArticleLigneProformaMapper.toDto(achatArticleLigneProforma);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatArticleLigneProformaMockMvc.perform(post("/api/achat-article-ligne-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleLigneProformaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatArticleLigneProforma in the database
        List<AchatArticleLigneProforma> achatArticleLigneProformaList = achatArticleLigneProformaRepository.findAll();
        assertThat(achatArticleLigneProformaList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatArticleLigneProforma in Elasticsearch
        verify(mockAchatArticleLigneProformaSearchRepository, times(0)).save(achatArticleLigneProforma);
    }

    @Test
    @Transactional
    public void getAllAchatArticleLigneProformas() throws Exception {
        // Initialize the database
        achatArticleLigneProformaRepository.saveAndFlush(achatArticleLigneProforma);

        // Get all the achatArticleLigneProformaList
        restAchatArticleLigneProformaMockMvc.perform(get("/api/achat-article-ligne-proformas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatArticleLigneProforma.getId().intValue())))
            .andExpect(jsonPath("$.[*].idArticleLigneProforma").value(hasItem(DEFAULT_ID_ARTICLE_LIGNE_PROFORMA)))
            .andExpect(jsonPath("$.[*].numeroSequence").value(hasItem(DEFAULT_NUMERO_SEQUENCE)))
            .andExpect(jsonPath("$.[*].nombreCaissesTc").value(hasItem(DEFAULT_NOMBRE_CAISSES_TC)))
            .andExpect(jsonPath("$.[*].nombreFeuillesCaisse").value(hasItem(DEFAULT_NOMBRE_FEUILLES_CAISSE)))
            .andExpect(jsonPath("$.[*].dimention").value(hasItem(DEFAULT_DIMENTION.intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.intValue())))
            .andExpect(jsonPath("$.[*].prixUnitaire").value(hasItem(DEFAULT_PRIX_UNITAIRE.intValue())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())));
    }
    
    @Test
    @Transactional
    public void getAchatArticleLigneProforma() throws Exception {
        // Initialize the database
        achatArticleLigneProformaRepository.saveAndFlush(achatArticleLigneProforma);

        // Get the achatArticleLigneProforma
        restAchatArticleLigneProformaMockMvc.perform(get("/api/achat-article-ligne-proformas/{id}", achatArticleLigneProforma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatArticleLigneProforma.getId().intValue()))
            .andExpect(jsonPath("$.idArticleLigneProforma").value(DEFAULT_ID_ARTICLE_LIGNE_PROFORMA))
            .andExpect(jsonPath("$.numeroSequence").value(DEFAULT_NUMERO_SEQUENCE))
            .andExpect(jsonPath("$.nombreCaissesTc").value(DEFAULT_NOMBRE_CAISSES_TC))
            .andExpect(jsonPath("$.nombreFeuillesCaisse").value(DEFAULT_NOMBRE_FEUILLES_CAISSE))
            .andExpect(jsonPath("$.dimention").value(DEFAULT_DIMENTION.intValue()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE.intValue()))
            .andExpect(jsonPath("$.prixUnitaire").value(DEFAULT_PRIX_UNITAIRE.intValue()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatArticleLigneProforma() throws Exception {
        // Get the achatArticleLigneProforma
        restAchatArticleLigneProformaMockMvc.perform(get("/api/achat-article-ligne-proformas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatArticleLigneProforma() throws Exception {
        // Initialize the database
        achatArticleLigneProformaRepository.saveAndFlush(achatArticleLigneProforma);

        int databaseSizeBeforeUpdate = achatArticleLigneProformaRepository.findAll().size();

        // Update the achatArticleLigneProforma
        AchatArticleLigneProforma updatedAchatArticleLigneProforma = achatArticleLigneProformaRepository.findById(achatArticleLigneProforma.getId()).get();
        // Disconnect from session so that the updates on updatedAchatArticleLigneProforma are not directly saved in db
        em.detach(updatedAchatArticleLigneProforma);
        updatedAchatArticleLigneProforma
            .idArticleLigneProforma(UPDATED_ID_ARTICLE_LIGNE_PROFORMA)
            .numeroSequence(UPDATED_NUMERO_SEQUENCE)
            .nombreCaissesTc(UPDATED_NOMBRE_CAISSES_TC)
            .nombreFeuillesCaisse(UPDATED_NOMBRE_FEUILLES_CAISSE)
            .dimention(UPDATED_DIMENTION)
            .quantite(UPDATED_QUANTITE)
            .prixUnitaire(UPDATED_PRIX_UNITAIRE)
            .montant(UPDATED_MONTANT)
            .poids(UPDATED_POIDS);
        AchatArticleLigneProformaDTO achatArticleLigneProformaDTO = achatArticleLigneProformaMapper.toDto(updatedAchatArticleLigneProforma);

        restAchatArticleLigneProformaMockMvc.perform(put("/api/achat-article-ligne-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleLigneProformaDTO)))
            .andExpect(status().isOk());

        // Validate the AchatArticleLigneProforma in the database
        List<AchatArticleLigneProforma> achatArticleLigneProformaList = achatArticleLigneProformaRepository.findAll();
        assertThat(achatArticleLigneProformaList).hasSize(databaseSizeBeforeUpdate);
        AchatArticleLigneProforma testAchatArticleLigneProforma = achatArticleLigneProformaList.get(achatArticleLigneProformaList.size() - 1);
        assertThat(testAchatArticleLigneProforma.getIdArticleLigneProforma()).isEqualTo(UPDATED_ID_ARTICLE_LIGNE_PROFORMA);
        assertThat(testAchatArticleLigneProforma.getNumeroSequence()).isEqualTo(UPDATED_NUMERO_SEQUENCE);
        assertThat(testAchatArticleLigneProforma.getNombreCaissesTc()).isEqualTo(UPDATED_NOMBRE_CAISSES_TC);
        assertThat(testAchatArticleLigneProforma.getNombreFeuillesCaisse()).isEqualTo(UPDATED_NOMBRE_FEUILLES_CAISSE);
        assertThat(testAchatArticleLigneProforma.getDimention()).isEqualTo(UPDATED_DIMENTION);
        assertThat(testAchatArticleLigneProforma.getQuantite()).isEqualTo(UPDATED_QUANTITE);
        assertThat(testAchatArticleLigneProforma.getPrixUnitaire()).isEqualTo(UPDATED_PRIX_UNITAIRE);
        assertThat(testAchatArticleLigneProforma.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testAchatArticleLigneProforma.getPoids()).isEqualTo(UPDATED_POIDS);

        // Validate the AchatArticleLigneProforma in Elasticsearch
        verify(mockAchatArticleLigneProformaSearchRepository, times(1)).save(testAchatArticleLigneProforma);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatArticleLigneProforma() throws Exception {
        int databaseSizeBeforeUpdate = achatArticleLigneProformaRepository.findAll().size();

        // Create the AchatArticleLigneProforma
        AchatArticleLigneProformaDTO achatArticleLigneProformaDTO = achatArticleLigneProformaMapper.toDto(achatArticleLigneProforma);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatArticleLigneProformaMockMvc.perform(put("/api/achat-article-ligne-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleLigneProformaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatArticleLigneProforma in the database
        List<AchatArticleLigneProforma> achatArticleLigneProformaList = achatArticleLigneProformaRepository.findAll();
        assertThat(achatArticleLigneProformaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatArticleLigneProforma in Elasticsearch
        verify(mockAchatArticleLigneProformaSearchRepository, times(0)).save(achatArticleLigneProforma);
    }

    @Test
    @Transactional
    public void deleteAchatArticleLigneProforma() throws Exception {
        // Initialize the database
        achatArticleLigneProformaRepository.saveAndFlush(achatArticleLigneProforma);

        int databaseSizeBeforeDelete = achatArticleLigneProformaRepository.findAll().size();

        // Get the achatArticleLigneProforma
        restAchatArticleLigneProformaMockMvc.perform(delete("/api/achat-article-ligne-proformas/{id}", achatArticleLigneProforma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatArticleLigneProforma> achatArticleLigneProformaList = achatArticleLigneProformaRepository.findAll();
        assertThat(achatArticleLigneProformaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatArticleLigneProforma in Elasticsearch
        verify(mockAchatArticleLigneProformaSearchRepository, times(1)).deleteById(achatArticleLigneProforma.getId());
    }

    @Test
    @Transactional
    public void searchAchatArticleLigneProforma() throws Exception {
        // Initialize the database
        achatArticleLigneProformaRepository.saveAndFlush(achatArticleLigneProforma);
        when(mockAchatArticleLigneProformaSearchRepository.search(queryStringQuery("id:" + achatArticleLigneProforma.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatArticleLigneProforma), PageRequest.of(0, 1), 1));
        // Search the achatArticleLigneProforma
        restAchatArticleLigneProformaMockMvc.perform(get("/api/_search/achat-article-ligne-proformas?query=id:" + achatArticleLigneProforma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatArticleLigneProforma.getId().intValue())))
            .andExpect(jsonPath("$.[*].idArticleLigneProforma").value(hasItem(DEFAULT_ID_ARTICLE_LIGNE_PROFORMA)))
            .andExpect(jsonPath("$.[*].numeroSequence").value(hasItem(DEFAULT_NUMERO_SEQUENCE)))
            .andExpect(jsonPath("$.[*].nombreCaissesTc").value(hasItem(DEFAULT_NOMBRE_CAISSES_TC)))
            .andExpect(jsonPath("$.[*].nombreFeuillesCaisse").value(hasItem(DEFAULT_NOMBRE_FEUILLES_CAISSE)))
            .andExpect(jsonPath("$.[*].dimention").value(hasItem(DEFAULT_DIMENTION.intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.intValue())))
            .andExpect(jsonPath("$.[*].prixUnitaire").value(hasItem(DEFAULT_PRIX_UNITAIRE.intValue())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatArticleLigneProforma.class);
        AchatArticleLigneProforma achatArticleLigneProforma1 = new AchatArticleLigneProforma();
        achatArticleLigneProforma1.setId(1L);
        AchatArticleLigneProforma achatArticleLigneProforma2 = new AchatArticleLigneProforma();
        achatArticleLigneProforma2.setId(achatArticleLigneProforma1.getId());
        assertThat(achatArticleLigneProforma1).isEqualTo(achatArticleLigneProforma2);
        achatArticleLigneProforma2.setId(2L);
        assertThat(achatArticleLigneProforma1).isNotEqualTo(achatArticleLigneProforma2);
        achatArticleLigneProforma1.setId(null);
        assertThat(achatArticleLigneProforma1).isNotEqualTo(achatArticleLigneProforma2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatArticleLigneProformaDTO.class);
        AchatArticleLigneProformaDTO achatArticleLigneProformaDTO1 = new AchatArticleLigneProformaDTO();
        achatArticleLigneProformaDTO1.setId(1L);
        AchatArticleLigneProformaDTO achatArticleLigneProformaDTO2 = new AchatArticleLigneProformaDTO();
        assertThat(achatArticleLigneProformaDTO1).isNotEqualTo(achatArticleLigneProformaDTO2);
        achatArticleLigneProformaDTO2.setId(achatArticleLigneProformaDTO1.getId());
        assertThat(achatArticleLigneProformaDTO1).isEqualTo(achatArticleLigneProformaDTO2);
        achatArticleLigneProformaDTO2.setId(2L);
        assertThat(achatArticleLigneProformaDTO1).isNotEqualTo(achatArticleLigneProformaDTO2);
        achatArticleLigneProformaDTO1.setId(null);
        assertThat(achatArticleLigneProformaDTO1).isNotEqualTo(achatArticleLigneProformaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatArticleLigneProformaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatArticleLigneProformaMapper.fromId(null)).isNull();
    }
}
