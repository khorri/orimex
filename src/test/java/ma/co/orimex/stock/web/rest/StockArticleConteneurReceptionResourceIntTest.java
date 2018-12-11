package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.StockArticleConteneurReception;
import ma.co.orimex.stock.repository.StockArticleConteneurReceptionRepository;
import ma.co.orimex.stock.repository.search.StockArticleConteneurReceptionSearchRepository;
import ma.co.orimex.stock.service.StockArticleConteneurReceptionService;
import ma.co.orimex.stock.service.dto.StockArticleConteneurReceptionDTO;
import ma.co.orimex.stock.service.mapper.StockArticleConteneurReceptionMapper;
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
 * Test class for the StockArticleConteneurReceptionResource REST controller.
 *
 * @see StockArticleConteneurReceptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class StockArticleConteneurReceptionResourceIntTest {

    private static final Integer DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION = 1;
    private static final Integer UPDATED_ID_ARTICLE_CONTENEUR_RECEPTION = 2;

    private static final BigDecimal DEFAULT_DIMENSION = new BigDecimal(1);
    private static final BigDecimal UPDATED_DIMENSION = new BigDecimal(2);

    private static final Integer DEFAULT_NOMBRE_CAISSESTC = 1;
    private static final Integer UPDATED_NOMBRE_CAISSESTC = 2;

    private static final Integer DEFAULT_NOMBRE_FEUILLECAISSE = 1;
    private static final Integer UPDATED_NOMBRE_FEUILLECAISSE = 2;

    private static final Integer DEFAULT_IS_NON_CONFROME = 1;
    private static final Integer UPDATED_IS_NON_CONFROME = 2;

    @Autowired
    private StockArticleConteneurReceptionRepository stockArticleConteneurReceptionRepository;

    @Autowired
    private StockArticleConteneurReceptionMapper stockArticleConteneurReceptionMapper;

    @Autowired
    private StockArticleConteneurReceptionService stockArticleConteneurReceptionService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.StockArticleConteneurReceptionSearchRepositoryMockConfiguration
     */
    @Autowired
    private StockArticleConteneurReceptionSearchRepository mockStockArticleConteneurReceptionSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStockArticleConteneurReceptionMockMvc;

    private StockArticleConteneurReception stockArticleConteneurReception;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StockArticleConteneurReceptionResource stockArticleConteneurReceptionResource = new StockArticleConteneurReceptionResource(stockArticleConteneurReceptionService);
        this.restStockArticleConteneurReceptionMockMvc = MockMvcBuilders.standaloneSetup(stockArticleConteneurReceptionResource)
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
    public static StockArticleConteneurReception createEntity(EntityManager em) {
        StockArticleConteneurReception stockArticleConteneurReception = new StockArticleConteneurReception()
            .idArticleConteneurReception(DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION)
            .dimension(DEFAULT_DIMENSION)
            .nombreCaissestc(DEFAULT_NOMBRE_CAISSESTC)
            .nombreFeuillecaisse(DEFAULT_NOMBRE_FEUILLECAISSE)
            .isNonConfrome(DEFAULT_IS_NON_CONFROME);
        return stockArticleConteneurReception;
    }

    @Before
    public void initTest() {
        stockArticleConteneurReception = createEntity(em);
    }

    @Test
    @Transactional
    public void createStockArticleConteneurReception() throws Exception {
        int databaseSizeBeforeCreate = stockArticleConteneurReceptionRepository.findAll().size();

        // Create the StockArticleConteneurReception
        StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO = stockArticleConteneurReceptionMapper.toDto(stockArticleConteneurReception);
        restStockArticleConteneurReceptionMockMvc.perform(post("/api/stock-article-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockArticleConteneurReceptionDTO)))
            .andExpect(status().isCreated());

        // Validate the StockArticleConteneurReception in the database
        List<StockArticleConteneurReception> stockArticleConteneurReceptionList = stockArticleConteneurReceptionRepository.findAll();
        assertThat(stockArticleConteneurReceptionList).hasSize(databaseSizeBeforeCreate + 1);
        StockArticleConteneurReception testStockArticleConteneurReception = stockArticleConteneurReceptionList.get(stockArticleConteneurReceptionList.size() - 1);
        assertThat(testStockArticleConteneurReception.getIdArticleConteneurReception()).isEqualTo(DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION);
        assertThat(testStockArticleConteneurReception.getDimension()).isEqualTo(DEFAULT_DIMENSION);
        assertThat(testStockArticleConteneurReception.getNombreCaissestc()).isEqualTo(DEFAULT_NOMBRE_CAISSESTC);
        assertThat(testStockArticleConteneurReception.getNombreFeuillecaisse()).isEqualTo(DEFAULT_NOMBRE_FEUILLECAISSE);
        assertThat(testStockArticleConteneurReception.getIsNonConfrome()).isEqualTo(DEFAULT_IS_NON_CONFROME);

        // Validate the StockArticleConteneurReception in Elasticsearch
        verify(mockStockArticleConteneurReceptionSearchRepository, times(1)).save(testStockArticleConteneurReception);
    }

    @Test
    @Transactional
    public void createStockArticleConteneurReceptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stockArticleConteneurReceptionRepository.findAll().size();

        // Create the StockArticleConteneurReception with an existing ID
        stockArticleConteneurReception.setId(1L);
        StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO = stockArticleConteneurReceptionMapper.toDto(stockArticleConteneurReception);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockArticleConteneurReceptionMockMvc.perform(post("/api/stock-article-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockArticleConteneurReceptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockArticleConteneurReception in the database
        List<StockArticleConteneurReception> stockArticleConteneurReceptionList = stockArticleConteneurReceptionRepository.findAll();
        assertThat(stockArticleConteneurReceptionList).hasSize(databaseSizeBeforeCreate);

        // Validate the StockArticleConteneurReception in Elasticsearch
        verify(mockStockArticleConteneurReceptionSearchRepository, times(0)).save(stockArticleConteneurReception);
    }

    @Test
    @Transactional
    public void getAllStockArticleConteneurReceptions() throws Exception {
        // Initialize the database
        stockArticleConteneurReceptionRepository.saveAndFlush(stockArticleConteneurReception);

        // Get all the stockArticleConteneurReceptionList
        restStockArticleConteneurReceptionMockMvc.perform(get("/api/stock-article-conteneur-receptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockArticleConteneurReception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idArticleConteneurReception").value(hasItem(DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION)))
            .andExpect(jsonPath("$.[*].dimension").value(hasItem(DEFAULT_DIMENSION.intValue())))
            .andExpect(jsonPath("$.[*].nombreCaissestc").value(hasItem(DEFAULT_NOMBRE_CAISSESTC)))
            .andExpect(jsonPath("$.[*].nombreFeuillecaisse").value(hasItem(DEFAULT_NOMBRE_FEUILLECAISSE)))
            .andExpect(jsonPath("$.[*].isNonConfrome").value(hasItem(DEFAULT_IS_NON_CONFROME)));
    }
    
    @Test
    @Transactional
    public void getStockArticleConteneurReception() throws Exception {
        // Initialize the database
        stockArticleConteneurReceptionRepository.saveAndFlush(stockArticleConteneurReception);

        // Get the stockArticleConteneurReception
        restStockArticleConteneurReceptionMockMvc.perform(get("/api/stock-article-conteneur-receptions/{id}", stockArticleConteneurReception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stockArticleConteneurReception.getId().intValue()))
            .andExpect(jsonPath("$.idArticleConteneurReception").value(DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION))
            .andExpect(jsonPath("$.dimension").value(DEFAULT_DIMENSION.intValue()))
            .andExpect(jsonPath("$.nombreCaissestc").value(DEFAULT_NOMBRE_CAISSESTC))
            .andExpect(jsonPath("$.nombreFeuillecaisse").value(DEFAULT_NOMBRE_FEUILLECAISSE))
            .andExpect(jsonPath("$.isNonConfrome").value(DEFAULT_IS_NON_CONFROME));
    }

    @Test
    @Transactional
    public void getNonExistingStockArticleConteneurReception() throws Exception {
        // Get the stockArticleConteneurReception
        restStockArticleConteneurReceptionMockMvc.perform(get("/api/stock-article-conteneur-receptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStockArticleConteneurReception() throws Exception {
        // Initialize the database
        stockArticleConteneurReceptionRepository.saveAndFlush(stockArticleConteneurReception);

        int databaseSizeBeforeUpdate = stockArticleConteneurReceptionRepository.findAll().size();

        // Update the stockArticleConteneurReception
        StockArticleConteneurReception updatedStockArticleConteneurReception = stockArticleConteneurReceptionRepository.findById(stockArticleConteneurReception.getId()).get();
        // Disconnect from session so that the updates on updatedStockArticleConteneurReception are not directly saved in db
        em.detach(updatedStockArticleConteneurReception);
        updatedStockArticleConteneurReception
            .idArticleConteneurReception(UPDATED_ID_ARTICLE_CONTENEUR_RECEPTION)
            .dimension(UPDATED_DIMENSION)
            .nombreCaissestc(UPDATED_NOMBRE_CAISSESTC)
            .nombreFeuillecaisse(UPDATED_NOMBRE_FEUILLECAISSE)
            .isNonConfrome(UPDATED_IS_NON_CONFROME);
        StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO = stockArticleConteneurReceptionMapper.toDto(updatedStockArticleConteneurReception);

        restStockArticleConteneurReceptionMockMvc.perform(put("/api/stock-article-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockArticleConteneurReceptionDTO)))
            .andExpect(status().isOk());

        // Validate the StockArticleConteneurReception in the database
        List<StockArticleConteneurReception> stockArticleConteneurReceptionList = stockArticleConteneurReceptionRepository.findAll();
        assertThat(stockArticleConteneurReceptionList).hasSize(databaseSizeBeforeUpdate);
        StockArticleConteneurReception testStockArticleConteneurReception = stockArticleConteneurReceptionList.get(stockArticleConteneurReceptionList.size() - 1);
        assertThat(testStockArticleConteneurReception.getIdArticleConteneurReception()).isEqualTo(UPDATED_ID_ARTICLE_CONTENEUR_RECEPTION);
        assertThat(testStockArticleConteneurReception.getDimension()).isEqualTo(UPDATED_DIMENSION);
        assertThat(testStockArticleConteneurReception.getNombreCaissestc()).isEqualTo(UPDATED_NOMBRE_CAISSESTC);
        assertThat(testStockArticleConteneurReception.getNombreFeuillecaisse()).isEqualTo(UPDATED_NOMBRE_FEUILLECAISSE);
        assertThat(testStockArticleConteneurReception.getIsNonConfrome()).isEqualTo(UPDATED_IS_NON_CONFROME);

        // Validate the StockArticleConteneurReception in Elasticsearch
        verify(mockStockArticleConteneurReceptionSearchRepository, times(1)).save(testStockArticleConteneurReception);
    }

    @Test
    @Transactional
    public void updateNonExistingStockArticleConteneurReception() throws Exception {
        int databaseSizeBeforeUpdate = stockArticleConteneurReceptionRepository.findAll().size();

        // Create the StockArticleConteneurReception
        StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO = stockArticleConteneurReceptionMapper.toDto(stockArticleConteneurReception);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockArticleConteneurReceptionMockMvc.perform(put("/api/stock-article-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockArticleConteneurReceptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockArticleConteneurReception in the database
        List<StockArticleConteneurReception> stockArticleConteneurReceptionList = stockArticleConteneurReceptionRepository.findAll();
        assertThat(stockArticleConteneurReceptionList).hasSize(databaseSizeBeforeUpdate);

        // Validate the StockArticleConteneurReception in Elasticsearch
        verify(mockStockArticleConteneurReceptionSearchRepository, times(0)).save(stockArticleConteneurReception);
    }

    @Test
    @Transactional
    public void deleteStockArticleConteneurReception() throws Exception {
        // Initialize the database
        stockArticleConteneurReceptionRepository.saveAndFlush(stockArticleConteneurReception);

        int databaseSizeBeforeDelete = stockArticleConteneurReceptionRepository.findAll().size();

        // Get the stockArticleConteneurReception
        restStockArticleConteneurReceptionMockMvc.perform(delete("/api/stock-article-conteneur-receptions/{id}", stockArticleConteneurReception.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StockArticleConteneurReception> stockArticleConteneurReceptionList = stockArticleConteneurReceptionRepository.findAll();
        assertThat(stockArticleConteneurReceptionList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the StockArticleConteneurReception in Elasticsearch
        verify(mockStockArticleConteneurReceptionSearchRepository, times(1)).deleteById(stockArticleConteneurReception.getId());
    }

    @Test
    @Transactional
    public void searchStockArticleConteneurReception() throws Exception {
        // Initialize the database
        stockArticleConteneurReceptionRepository.saveAndFlush(stockArticleConteneurReception);
        when(mockStockArticleConteneurReceptionSearchRepository.search(queryStringQuery("id:" + stockArticleConteneurReception.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(stockArticleConteneurReception), PageRequest.of(0, 1), 1));
        // Search the stockArticleConteneurReception
        restStockArticleConteneurReceptionMockMvc.perform(get("/api/_search/stock-article-conteneur-receptions?query=id:" + stockArticleConteneurReception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockArticleConteneurReception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idArticleConteneurReception").value(hasItem(DEFAULT_ID_ARTICLE_CONTENEUR_RECEPTION)))
            .andExpect(jsonPath("$.[*].dimension").value(hasItem(DEFAULT_DIMENSION.intValue())))
            .andExpect(jsonPath("$.[*].nombreCaissestc").value(hasItem(DEFAULT_NOMBRE_CAISSESTC)))
            .andExpect(jsonPath("$.[*].nombreFeuillecaisse").value(hasItem(DEFAULT_NOMBRE_FEUILLECAISSE)))
            .andExpect(jsonPath("$.[*].isNonConfrome").value(hasItem(DEFAULT_IS_NON_CONFROME)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockArticleConteneurReception.class);
        StockArticleConteneurReception stockArticleConteneurReception1 = new StockArticleConteneurReception();
        stockArticleConteneurReception1.setId(1L);
        StockArticleConteneurReception stockArticleConteneurReception2 = new StockArticleConteneurReception();
        stockArticleConteneurReception2.setId(stockArticleConteneurReception1.getId());
        assertThat(stockArticleConteneurReception1).isEqualTo(stockArticleConteneurReception2);
        stockArticleConteneurReception2.setId(2L);
        assertThat(stockArticleConteneurReception1).isNotEqualTo(stockArticleConteneurReception2);
        stockArticleConteneurReception1.setId(null);
        assertThat(stockArticleConteneurReception1).isNotEqualTo(stockArticleConteneurReception2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockArticleConteneurReceptionDTO.class);
        StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO1 = new StockArticleConteneurReceptionDTO();
        stockArticleConteneurReceptionDTO1.setId(1L);
        StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO2 = new StockArticleConteneurReceptionDTO();
        assertThat(stockArticleConteneurReceptionDTO1).isNotEqualTo(stockArticleConteneurReceptionDTO2);
        stockArticleConteneurReceptionDTO2.setId(stockArticleConteneurReceptionDTO1.getId());
        assertThat(stockArticleConteneurReceptionDTO1).isEqualTo(stockArticleConteneurReceptionDTO2);
        stockArticleConteneurReceptionDTO2.setId(2L);
        assertThat(stockArticleConteneurReceptionDTO1).isNotEqualTo(stockArticleConteneurReceptionDTO2);
        stockArticleConteneurReceptionDTO1.setId(null);
        assertThat(stockArticleConteneurReceptionDTO1).isNotEqualTo(stockArticleConteneurReceptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(stockArticleConteneurReceptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(stockArticleConteneurReceptionMapper.fromId(null)).isNull();
    }
}
