package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.StockReception;
import ma.co.orimex.stock.repository.StockReceptionRepository;
import ma.co.orimex.stock.repository.search.StockReceptionSearchRepository;
import ma.co.orimex.stock.service.StockReceptionService;
import ma.co.orimex.stock.service.dto.StockReceptionDTO;
import ma.co.orimex.stock.service.mapper.StockReceptionMapper;
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
 * Test class for the StockReceptionResource REST controller.
 *
 * @see StockReceptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class StockReceptionResourceIntTest {

    private static final Integer DEFAULT_ID_OPERATION = 1;
    private static final Integer UPDATED_ID_OPERATION = 2;

    private static final LocalDate DEFAULT_DATE_RECEPTION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECEPTION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NUMERO_OPERATION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_OPERATION = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_BON_ENTREE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_BON_ENTREE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_CONSTAT_NON_CONFORMITE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CONSTAT_NON_CONFORMITE = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_VALIDE = 1;
    private static final Integer UPDATED_IS_VALIDE = 2;

    @Autowired
    private StockReceptionRepository stockReceptionRepository;

    @Autowired
    private StockReceptionMapper stockReceptionMapper;

    @Autowired
    private StockReceptionService stockReceptionService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.StockReceptionSearchRepositoryMockConfiguration
     */
    @Autowired
    private StockReceptionSearchRepository mockStockReceptionSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStockReceptionMockMvc;

    private StockReception stockReception;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StockReceptionResource stockReceptionResource = new StockReceptionResource(stockReceptionService);
        this.restStockReceptionMockMvc = MockMvcBuilders.standaloneSetup(stockReceptionResource)
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
    public static StockReception createEntity(EntityManager em) {
        StockReception stockReception = new StockReception()
            .idOperation(DEFAULT_ID_OPERATION)
            .dateReception(DEFAULT_DATE_RECEPTION)
            .numeroOperation(DEFAULT_NUMERO_OPERATION)
            .numeroBonEntree(DEFAULT_NUMERO_BON_ENTREE)
            .numeroConstatNonConformite(DEFAULT_NUMERO_CONSTAT_NON_CONFORMITE)
            .isValide(DEFAULT_IS_VALIDE);
        return stockReception;
    }

    @Before
    public void initTest() {
        stockReception = createEntity(em);
    }

    @Test
    @Transactional
    public void createStockReception() throws Exception {
        int databaseSizeBeforeCreate = stockReceptionRepository.findAll().size();

        // Create the StockReception
        StockReceptionDTO stockReceptionDTO = stockReceptionMapper.toDto(stockReception);
        restStockReceptionMockMvc.perform(post("/api/stock-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockReceptionDTO)))
            .andExpect(status().isCreated());

        // Validate the StockReception in the database
        List<StockReception> stockReceptionList = stockReceptionRepository.findAll();
        assertThat(stockReceptionList).hasSize(databaseSizeBeforeCreate + 1);
        StockReception testStockReception = stockReceptionList.get(stockReceptionList.size() - 1);
        assertThat(testStockReception.getIdOperation()).isEqualTo(DEFAULT_ID_OPERATION);
        assertThat(testStockReception.getDateReception()).isEqualTo(DEFAULT_DATE_RECEPTION);
        assertThat(testStockReception.getNumeroOperation()).isEqualTo(DEFAULT_NUMERO_OPERATION);
        assertThat(testStockReception.getNumeroBonEntree()).isEqualTo(DEFAULT_NUMERO_BON_ENTREE);
        assertThat(testStockReception.getNumeroConstatNonConformite()).isEqualTo(DEFAULT_NUMERO_CONSTAT_NON_CONFORMITE);
        assertThat(testStockReception.getIsValide()).isEqualTo(DEFAULT_IS_VALIDE);

        // Validate the StockReception in Elasticsearch
        verify(mockStockReceptionSearchRepository, times(1)).save(testStockReception);
    }

    @Test
    @Transactional
    public void createStockReceptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stockReceptionRepository.findAll().size();

        // Create the StockReception with an existing ID
        stockReception.setId(1L);
        StockReceptionDTO stockReceptionDTO = stockReceptionMapper.toDto(stockReception);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockReceptionMockMvc.perform(post("/api/stock-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockReceptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockReception in the database
        List<StockReception> stockReceptionList = stockReceptionRepository.findAll();
        assertThat(stockReceptionList).hasSize(databaseSizeBeforeCreate);

        // Validate the StockReception in Elasticsearch
        verify(mockStockReceptionSearchRepository, times(0)).save(stockReception);
    }

    @Test
    @Transactional
    public void getAllStockReceptions() throws Exception {
        // Initialize the database
        stockReceptionRepository.saveAndFlush(stockReception);

        // Get all the stockReceptionList
        restStockReceptionMockMvc.perform(get("/api/stock-receptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockReception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateReception").value(hasItem(DEFAULT_DATE_RECEPTION.toString())))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].numeroBonEntree").value(hasItem(DEFAULT_NUMERO_BON_ENTREE.toString())))
            .andExpect(jsonPath("$.[*].numeroConstatNonConformite").value(hasItem(DEFAULT_NUMERO_CONSTAT_NON_CONFORMITE.toString())))
            .andExpect(jsonPath("$.[*].isValide").value(hasItem(DEFAULT_IS_VALIDE)));
    }
    
    @Test
    @Transactional
    public void getStockReception() throws Exception {
        // Initialize the database
        stockReceptionRepository.saveAndFlush(stockReception);

        // Get the stockReception
        restStockReceptionMockMvc.perform(get("/api/stock-receptions/{id}", stockReception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stockReception.getId().intValue()))
            .andExpect(jsonPath("$.idOperation").value(DEFAULT_ID_OPERATION))
            .andExpect(jsonPath("$.dateReception").value(DEFAULT_DATE_RECEPTION.toString()))
            .andExpect(jsonPath("$.numeroOperation").value(DEFAULT_NUMERO_OPERATION.toString()))
            .andExpect(jsonPath("$.numeroBonEntree").value(DEFAULT_NUMERO_BON_ENTREE.toString()))
            .andExpect(jsonPath("$.numeroConstatNonConformite").value(DEFAULT_NUMERO_CONSTAT_NON_CONFORMITE.toString()))
            .andExpect(jsonPath("$.isValide").value(DEFAULT_IS_VALIDE));
    }

    @Test
    @Transactional
    public void getNonExistingStockReception() throws Exception {
        // Get the stockReception
        restStockReceptionMockMvc.perform(get("/api/stock-receptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStockReception() throws Exception {
        // Initialize the database
        stockReceptionRepository.saveAndFlush(stockReception);

        int databaseSizeBeforeUpdate = stockReceptionRepository.findAll().size();

        // Update the stockReception
        StockReception updatedStockReception = stockReceptionRepository.findById(stockReception.getId()).get();
        // Disconnect from session so that the updates on updatedStockReception are not directly saved in db
        em.detach(updatedStockReception);
        updatedStockReception
            .idOperation(UPDATED_ID_OPERATION)
            .dateReception(UPDATED_DATE_RECEPTION)
            .numeroOperation(UPDATED_NUMERO_OPERATION)
            .numeroBonEntree(UPDATED_NUMERO_BON_ENTREE)
            .numeroConstatNonConformite(UPDATED_NUMERO_CONSTAT_NON_CONFORMITE)
            .isValide(UPDATED_IS_VALIDE);
        StockReceptionDTO stockReceptionDTO = stockReceptionMapper.toDto(updatedStockReception);

        restStockReceptionMockMvc.perform(put("/api/stock-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockReceptionDTO)))
            .andExpect(status().isOk());

        // Validate the StockReception in the database
        List<StockReception> stockReceptionList = stockReceptionRepository.findAll();
        assertThat(stockReceptionList).hasSize(databaseSizeBeforeUpdate);
        StockReception testStockReception = stockReceptionList.get(stockReceptionList.size() - 1);
        assertThat(testStockReception.getIdOperation()).isEqualTo(UPDATED_ID_OPERATION);
        assertThat(testStockReception.getDateReception()).isEqualTo(UPDATED_DATE_RECEPTION);
        assertThat(testStockReception.getNumeroOperation()).isEqualTo(UPDATED_NUMERO_OPERATION);
        assertThat(testStockReception.getNumeroBonEntree()).isEqualTo(UPDATED_NUMERO_BON_ENTREE);
        assertThat(testStockReception.getNumeroConstatNonConformite()).isEqualTo(UPDATED_NUMERO_CONSTAT_NON_CONFORMITE);
        assertThat(testStockReception.getIsValide()).isEqualTo(UPDATED_IS_VALIDE);

        // Validate the StockReception in Elasticsearch
        verify(mockStockReceptionSearchRepository, times(1)).save(testStockReception);
    }

    @Test
    @Transactional
    public void updateNonExistingStockReception() throws Exception {
        int databaseSizeBeforeUpdate = stockReceptionRepository.findAll().size();

        // Create the StockReception
        StockReceptionDTO stockReceptionDTO = stockReceptionMapper.toDto(stockReception);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockReceptionMockMvc.perform(put("/api/stock-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockReceptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockReception in the database
        List<StockReception> stockReceptionList = stockReceptionRepository.findAll();
        assertThat(stockReceptionList).hasSize(databaseSizeBeforeUpdate);

        // Validate the StockReception in Elasticsearch
        verify(mockStockReceptionSearchRepository, times(0)).save(stockReception);
    }

    @Test
    @Transactional
    public void deleteStockReception() throws Exception {
        // Initialize the database
        stockReceptionRepository.saveAndFlush(stockReception);

        int databaseSizeBeforeDelete = stockReceptionRepository.findAll().size();

        // Get the stockReception
        restStockReceptionMockMvc.perform(delete("/api/stock-receptions/{id}", stockReception.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StockReception> stockReceptionList = stockReceptionRepository.findAll();
        assertThat(stockReceptionList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the StockReception in Elasticsearch
        verify(mockStockReceptionSearchRepository, times(1)).deleteById(stockReception.getId());
    }

    @Test
    @Transactional
    public void searchStockReception() throws Exception {
        // Initialize the database
        stockReceptionRepository.saveAndFlush(stockReception);
        when(mockStockReceptionSearchRepository.search(queryStringQuery("id:" + stockReception.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(stockReception), PageRequest.of(0, 1), 1));
        // Search the stockReception
        restStockReceptionMockMvc.perform(get("/api/_search/stock-receptions?query=id:" + stockReception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockReception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateReception").value(hasItem(DEFAULT_DATE_RECEPTION.toString())))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION)))
            .andExpect(jsonPath("$.[*].numeroBonEntree").value(hasItem(DEFAULT_NUMERO_BON_ENTREE)))
            .andExpect(jsonPath("$.[*].numeroConstatNonConformite").value(hasItem(DEFAULT_NUMERO_CONSTAT_NON_CONFORMITE)))
            .andExpect(jsonPath("$.[*].isValide").value(hasItem(DEFAULT_IS_VALIDE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockReception.class);
        StockReception stockReception1 = new StockReception();
        stockReception1.setId(1L);
        StockReception stockReception2 = new StockReception();
        stockReception2.setId(stockReception1.getId());
        assertThat(stockReception1).isEqualTo(stockReception2);
        stockReception2.setId(2L);
        assertThat(stockReception1).isNotEqualTo(stockReception2);
        stockReception1.setId(null);
        assertThat(stockReception1).isNotEqualTo(stockReception2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockReceptionDTO.class);
        StockReceptionDTO stockReceptionDTO1 = new StockReceptionDTO();
        stockReceptionDTO1.setId(1L);
        StockReceptionDTO stockReceptionDTO2 = new StockReceptionDTO();
        assertThat(stockReceptionDTO1).isNotEqualTo(stockReceptionDTO2);
        stockReceptionDTO2.setId(stockReceptionDTO1.getId());
        assertThat(stockReceptionDTO1).isEqualTo(stockReceptionDTO2);
        stockReceptionDTO2.setId(2L);
        assertThat(stockReceptionDTO1).isNotEqualTo(stockReceptionDTO2);
        stockReceptionDTO1.setId(null);
        assertThat(stockReceptionDTO1).isNotEqualTo(stockReceptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(stockReceptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(stockReceptionMapper.fromId(null)).isNull();
    }
}
