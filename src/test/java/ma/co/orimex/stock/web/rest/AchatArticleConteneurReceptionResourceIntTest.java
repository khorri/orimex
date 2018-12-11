package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatArticleConteneurReception;
import ma.co.orimex.stock.repository.AchatArticleConteneurReceptionRepository;
import ma.co.orimex.stock.repository.search.AchatArticleConteneurReceptionSearchRepository;
import ma.co.orimex.stock.service.AchatArticleConteneurReceptionService;
import ma.co.orimex.stock.service.dto.AchatArticleConteneurReceptionDTO;
import ma.co.orimex.stock.service.mapper.AchatArticleConteneurReceptionMapper;
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
 * Test class for the AchatArticleConteneurReceptionResource REST controller.
 *
 * @see AchatArticleConteneurReceptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatArticleConteneurReceptionResourceIntTest {

    private static final Integer DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION = 1;
    private static final Integer UPDATED_ID_ARTICLE_CONTENEUR_RECEPTION = 2;

    private static final BigDecimal DEFAULT_DIMENSION = new BigDecimal(1);
    private static final BigDecimal UPDATED_DIMENSION = new BigDecimal(2);

    private static final Integer DEFAULT_NOMBRE_CAISSESTC = 1;
    private static final Integer UPDATED_NOMBRE_CAISSESTC = 2;

    private static final Integer DEFAULT_NOMBRE_FEUILLECAISSE = 1;
    private static final Integer UPDATED_NOMBRE_FEUILLECAISSE = 2;

    @Autowired
    private AchatArticleConteneurReceptionRepository achatArticleConteneurReceptionRepository;

    @Autowired
    private AchatArticleConteneurReceptionMapper achatArticleConteneurReceptionMapper;

    @Autowired
    private AchatArticleConteneurReceptionService achatArticleConteneurReceptionService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatArticleConteneurReceptionSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatArticleConteneurReceptionSearchRepository mockAchatArticleConteneurReceptionSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatArticleConteneurReceptionMockMvc;

    private AchatArticleConteneurReception achatArticleConteneurReception;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatArticleConteneurReceptionResource achatArticleConteneurReceptionResource = new AchatArticleConteneurReceptionResource(achatArticleConteneurReceptionService);
        this.restAchatArticleConteneurReceptionMockMvc = MockMvcBuilders.standaloneSetup(achatArticleConteneurReceptionResource)
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
    public static AchatArticleConteneurReception createEntity(EntityManager em) {
        AchatArticleConteneurReception achatArticleConteneurReception = new AchatArticleConteneurReception()
            .idArticleConteneurReception(DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION)
            .dimension(DEFAULT_DIMENSION)
            .nombreCaissestc(DEFAULT_NOMBRE_CAISSESTC)
            .nombreFeuillecaisse(DEFAULT_NOMBRE_FEUILLECAISSE);
        return achatArticleConteneurReception;
    }

    @Before
    public void initTest() {
        achatArticleConteneurReception = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatArticleConteneurReception() throws Exception {
        int databaseSizeBeforeCreate = achatArticleConteneurReceptionRepository.findAll().size();

        // Create the AchatArticleConteneurReception
        AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO = achatArticleConteneurReceptionMapper.toDto(achatArticleConteneurReception);
        restAchatArticleConteneurReceptionMockMvc.perform(post("/api/achat-article-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleConteneurReceptionDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatArticleConteneurReception in the database
        List<AchatArticleConteneurReception> achatArticleConteneurReceptionList = achatArticleConteneurReceptionRepository.findAll();
        assertThat(achatArticleConteneurReceptionList).hasSize(databaseSizeBeforeCreate + 1);
        AchatArticleConteneurReception testAchatArticleConteneurReception = achatArticleConteneurReceptionList.get(achatArticleConteneurReceptionList.size() - 1);
        assertThat(testAchatArticleConteneurReception.getIdArticleConteneurReception()).isEqualTo(DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION);
        assertThat(testAchatArticleConteneurReception.getDimension()).isEqualTo(DEFAULT_DIMENSION);
        assertThat(testAchatArticleConteneurReception.getNombreCaissestc()).isEqualTo(DEFAULT_NOMBRE_CAISSESTC);
        assertThat(testAchatArticleConteneurReception.getNombreFeuillecaisse()).isEqualTo(DEFAULT_NOMBRE_FEUILLECAISSE);

        // Validate the AchatArticleConteneurReception in Elasticsearch
        verify(mockAchatArticleConteneurReceptionSearchRepository, times(1)).save(testAchatArticleConteneurReception);
    }

    @Test
    @Transactional
    public void createAchatArticleConteneurReceptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatArticleConteneurReceptionRepository.findAll().size();

        // Create the AchatArticleConteneurReception with an existing ID
        achatArticleConteneurReception.setId(1L);
        AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO = achatArticleConteneurReceptionMapper.toDto(achatArticleConteneurReception);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatArticleConteneurReceptionMockMvc.perform(post("/api/achat-article-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleConteneurReceptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatArticleConteneurReception in the database
        List<AchatArticleConteneurReception> achatArticleConteneurReceptionList = achatArticleConteneurReceptionRepository.findAll();
        assertThat(achatArticleConteneurReceptionList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatArticleConteneurReception in Elasticsearch
        verify(mockAchatArticleConteneurReceptionSearchRepository, times(0)).save(achatArticleConteneurReception);
    }

    @Test
    @Transactional
    public void getAllAchatArticleConteneurReceptions() throws Exception {
        // Initialize the database
        achatArticleConteneurReceptionRepository.saveAndFlush(achatArticleConteneurReception);

        // Get all the achatArticleConteneurReceptionList
        restAchatArticleConteneurReceptionMockMvc.perform(get("/api/achat-article-conteneur-receptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatArticleConteneurReception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idArticleConteneurReception").value(hasItem(DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION)))
            .andExpect(jsonPath("$.[*].dimension").value(hasItem(DEFAULT_DIMENSION.intValue())))
            .andExpect(jsonPath("$.[*].nombreCaissestc").value(hasItem(DEFAULT_NOMBRE_CAISSESTC)))
            .andExpect(jsonPath("$.[*].nombreFeuillecaisse").value(hasItem(DEFAULT_NOMBRE_FEUILLECAISSE)));
    }
    
    @Test
    @Transactional
    public void getAchatArticleConteneurReception() throws Exception {
        // Initialize the database
        achatArticleConteneurReceptionRepository.saveAndFlush(achatArticleConteneurReception);

        // Get the achatArticleConteneurReception
        restAchatArticleConteneurReceptionMockMvc.perform(get("/api/achat-article-conteneur-receptions/{id}", achatArticleConteneurReception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatArticleConteneurReception.getId().intValue()))
            .andExpect(jsonPath("$.idArticleConteneurReception").value(DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION))
            .andExpect(jsonPath("$.dimension").value(DEFAULT_DIMENSION.intValue()))
            .andExpect(jsonPath("$.nombreCaissestc").value(DEFAULT_NOMBRE_CAISSESTC))
            .andExpect(jsonPath("$.nombreFeuillecaisse").value(DEFAULT_NOMBRE_FEUILLECAISSE));
    }

    @Test
    @Transactional
    public void getNonExistingAchatArticleConteneurReception() throws Exception {
        // Get the achatArticleConteneurReception
        restAchatArticleConteneurReceptionMockMvc.perform(get("/api/achat-article-conteneur-receptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatArticleConteneurReception() throws Exception {
        // Initialize the database
        achatArticleConteneurReceptionRepository.saveAndFlush(achatArticleConteneurReception);

        int databaseSizeBeforeUpdate = achatArticleConteneurReceptionRepository.findAll().size();

        // Update the achatArticleConteneurReception
        AchatArticleConteneurReception updatedAchatArticleConteneurReception = achatArticleConteneurReceptionRepository.findById(achatArticleConteneurReception.getId()).get();
        // Disconnect from session so that the updates on updatedAchatArticleConteneurReception are not directly saved in db
        em.detach(updatedAchatArticleConteneurReception);
        updatedAchatArticleConteneurReception
            .idArticleConteneurReception(UPDATED_ID_ARTICLE_CONTENEUR_RECEPTION)
            .dimension(UPDATED_DIMENSION)
            .nombreCaissestc(UPDATED_NOMBRE_CAISSESTC)
            .nombreFeuillecaisse(UPDATED_NOMBRE_FEUILLECAISSE);
        AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO = achatArticleConteneurReceptionMapper.toDto(updatedAchatArticleConteneurReception);

        restAchatArticleConteneurReceptionMockMvc.perform(put("/api/achat-article-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleConteneurReceptionDTO)))
            .andExpect(status().isOk());

        // Validate the AchatArticleConteneurReception in the database
        List<AchatArticleConteneurReception> achatArticleConteneurReceptionList = achatArticleConteneurReceptionRepository.findAll();
        assertThat(achatArticleConteneurReceptionList).hasSize(databaseSizeBeforeUpdate);
        AchatArticleConteneurReception testAchatArticleConteneurReception = achatArticleConteneurReceptionList.get(achatArticleConteneurReceptionList.size() - 1);
        assertThat(testAchatArticleConteneurReception.getIdArticleConteneurReception()).isEqualTo(UPDATED_ID_ARTICLE_CONTENEUR_RECEPTION);
        assertThat(testAchatArticleConteneurReception.getDimension()).isEqualTo(UPDATED_DIMENSION);
        assertThat(testAchatArticleConteneurReception.getNombreCaissestc()).isEqualTo(UPDATED_NOMBRE_CAISSESTC);
        assertThat(testAchatArticleConteneurReception.getNombreFeuillecaisse()).isEqualTo(UPDATED_NOMBRE_FEUILLECAISSE);

        // Validate the AchatArticleConteneurReception in Elasticsearch
        verify(mockAchatArticleConteneurReceptionSearchRepository, times(1)).save(testAchatArticleConteneurReception);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatArticleConteneurReception() throws Exception {
        int databaseSizeBeforeUpdate = achatArticleConteneurReceptionRepository.findAll().size();

        // Create the AchatArticleConteneurReception
        AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO = achatArticleConteneurReceptionMapper.toDto(achatArticleConteneurReception);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatArticleConteneurReceptionMockMvc.perform(put("/api/achat-article-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatArticleConteneurReceptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatArticleConteneurReception in the database
        List<AchatArticleConteneurReception> achatArticleConteneurReceptionList = achatArticleConteneurReceptionRepository.findAll();
        assertThat(achatArticleConteneurReceptionList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatArticleConteneurReception in Elasticsearch
        verify(mockAchatArticleConteneurReceptionSearchRepository, times(0)).save(achatArticleConteneurReception);
    }

    @Test
    @Transactional
    public void deleteAchatArticleConteneurReception() throws Exception {
        // Initialize the database
        achatArticleConteneurReceptionRepository.saveAndFlush(achatArticleConteneurReception);

        int databaseSizeBeforeDelete = achatArticleConteneurReceptionRepository.findAll().size();

        // Get the achatArticleConteneurReception
        restAchatArticleConteneurReceptionMockMvc.perform(delete("/api/achat-article-conteneur-receptions/{id}", achatArticleConteneurReception.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatArticleConteneurReception> achatArticleConteneurReceptionList = achatArticleConteneurReceptionRepository.findAll();
        assertThat(achatArticleConteneurReceptionList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatArticleConteneurReception in Elasticsearch
        verify(mockAchatArticleConteneurReceptionSearchRepository, times(1)).deleteById(achatArticleConteneurReception.getId());
    }

    @Test
    @Transactional
    public void searchAchatArticleConteneurReception() throws Exception {
        // Initialize the database
        achatArticleConteneurReceptionRepository.saveAndFlush(achatArticleConteneurReception);
        when(mockAchatArticleConteneurReceptionSearchRepository.search(queryStringQuery("id:" + achatArticleConteneurReception.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatArticleConteneurReception), PageRequest.of(0, 1), 1));
        // Search the achatArticleConteneurReception
        restAchatArticleConteneurReceptionMockMvc.perform(get("/api/_search/achat-article-conteneur-receptions?query=id:" + achatArticleConteneurReception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatArticleConteneurReception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idArticleConteneurReception").value(hasItem(DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION)))
            .andExpect(jsonPath("$.[*].dimension").value(hasItem(DEFAULT_DIMENSION.intValue())))
            .andExpect(jsonPath("$.[*].nombreCaissestc").value(hasItem(DEFAULT_NOMBRE_CAISSESTC)))
            .andExpect(jsonPath("$.[*].nombreFeuillecaisse").value(hasItem(DEFAULT_NOMBRE_FEUILLECAISSE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatArticleConteneurReception.class);
        AchatArticleConteneurReception achatArticleConteneurReception1 = new AchatArticleConteneurReception();
        achatArticleConteneurReception1.setId(1L);
        AchatArticleConteneurReception achatArticleConteneurReception2 = new AchatArticleConteneurReception();
        achatArticleConteneurReception2.setId(achatArticleConteneurReception1.getId());
        assertThat(achatArticleConteneurReception1).isEqualTo(achatArticleConteneurReception2);
        achatArticleConteneurReception2.setId(2L);
        assertThat(achatArticleConteneurReception1).isNotEqualTo(achatArticleConteneurReception2);
        achatArticleConteneurReception1.setId(null);
        assertThat(achatArticleConteneurReception1).isNotEqualTo(achatArticleConteneurReception2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatArticleConteneurReceptionDTO.class);
        AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO1 = new AchatArticleConteneurReceptionDTO();
        achatArticleConteneurReceptionDTO1.setId(1L);
        AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO2 = new AchatArticleConteneurReceptionDTO();
        assertThat(achatArticleConteneurReceptionDTO1).isNotEqualTo(achatArticleConteneurReceptionDTO2);
        achatArticleConteneurReceptionDTO2.setId(achatArticleConteneurReceptionDTO1.getId());
        assertThat(achatArticleConteneurReceptionDTO1).isEqualTo(achatArticleConteneurReceptionDTO2);
        achatArticleConteneurReceptionDTO2.setId(2L);
        assertThat(achatArticleConteneurReceptionDTO1).isNotEqualTo(achatArticleConteneurReceptionDTO2);
        achatArticleConteneurReceptionDTO1.setId(null);
        assertThat(achatArticleConteneurReceptionDTO1).isNotEqualTo(achatArticleConteneurReceptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatArticleConteneurReceptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatArticleConteneurReceptionMapper.fromId(null)).isNull();
    }
}
