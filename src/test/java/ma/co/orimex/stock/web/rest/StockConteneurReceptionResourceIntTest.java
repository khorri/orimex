package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.StockConteneurReception;
import ma.co.orimex.stock.repository.StockConteneurReceptionRepository;
import ma.co.orimex.stock.repository.search.StockConteneurReceptionSearchRepository;
import ma.co.orimex.stock.service.StockConteneurReceptionService;
import ma.co.orimex.stock.service.dto.StockConteneurReceptionDTO;
import ma.co.orimex.stock.service.mapper.StockConteneurReceptionMapper;
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
 * Test class for the StockConteneurReceptionResource REST controller.
 *
 * @see StockConteneurReceptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class StockConteneurReceptionResourceIntTest {

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
    private StockConteneurReceptionRepository stockConteneurReceptionRepository;

    @Autowired
    private StockConteneurReceptionMapper stockConteneurReceptionMapper;

    @Autowired
    private StockConteneurReceptionService stockConteneurReceptionService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.StockConteneurReceptionSearchRepositoryMockConfiguration
     */
    @Autowired
    private StockConteneurReceptionSearchRepository mockStockConteneurReceptionSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStockConteneurReceptionMockMvc;

    private StockConteneurReception stockConteneurReception;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StockConteneurReceptionResource stockConteneurReceptionResource = new StockConteneurReceptionResource(stockConteneurReceptionService);
        this.restStockConteneurReceptionMockMvc = MockMvcBuilders.standaloneSetup(stockConteneurReceptionResource)
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
    public static StockConteneurReception createEntity(EntityManager em) {
        StockConteneurReception stockConteneurReception = new StockConteneurReception()
            .idOperation(DEFAULT_ID_OPERATION)
            .dateReception(DEFAULT_DATE_RECEPTION)
            .numeroOperation(DEFAULT_NUMERO_OPERATION)
            .numeroBonEntree(DEFAULT_NUMERO_BON_ENTREE)
            .numeroConstatNonConformite(DEFAULT_NUMERO_CONSTAT_NON_CONFORMITE)
            .isValide(DEFAULT_IS_VALIDE);
        return stockConteneurReception;
    }

    @Before
    public void initTest() {
        stockConteneurReception = createEntity(em);
    }

    @Test
    @Transactional
    public void createStockConteneurReception() throws Exception {
        int databaseSizeBeforeCreate = stockConteneurReceptionRepository.findAll().size();

        // Create the StockConteneurReception
        StockConteneurReceptionDTO stockConteneurReceptionDTO = stockConteneurReceptionMapper.toDto(stockConteneurReception);
        restStockConteneurReceptionMockMvc.perform(post("/api/stock-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockConteneurReceptionDTO)))
            .andExpect(status().isCreated());

        // Validate the StockConteneurReception in the database
        List<StockConteneurReception> stockConteneurReceptionList = stockConteneurReceptionRepository.findAll();
        assertThat(stockConteneurReceptionList).hasSize(databaseSizeBeforeCreate + 1);
        StockConteneurReception testStockConteneurReception = stockConteneurReceptionList.get(stockConteneurReceptionList.size() - 1);
        assertThat(testStockConteneurReception.getIdOperation()).isEqualTo(DEFAULT_ID_OPERATION);
        assertThat(testStockConteneurReception.getDateReception()).isEqualTo(DEFAULT_DATE_RECEPTION);
        assertThat(testStockConteneurReception.getNumeroOperation()).isEqualTo(DEFAULT_NUMERO_OPERATION);
        assertThat(testStockConteneurReception.getNumeroBonEntree()).isEqualTo(DEFAULT_NUMERO_BON_ENTREE);
        assertThat(testStockConteneurReception.getNumeroConstatNonConformite()).isEqualTo(DEFAULT_NUMERO_CONSTAT_NON_CONFORMITE);
        assertThat(testStockConteneurReception.getIsValide()).isEqualTo(DEFAULT_IS_VALIDE);

        // Validate the StockConteneurReception in Elasticsearch
        verify(mockStockConteneurReceptionSearchRepository, times(1)).save(testStockConteneurReception);
    }

    @Test
    @Transactional
    public void createStockConteneurReceptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stockConteneurReceptionRepository.findAll().size();

        // Create the StockConteneurReception with an existing ID
        stockConteneurReception.setId(1L);
        StockConteneurReceptionDTO stockConteneurReceptionDTO = stockConteneurReceptionMapper.toDto(stockConteneurReception);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockConteneurReceptionMockMvc.perform(post("/api/stock-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockConteneurReceptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockConteneurReception in the database
        List<StockConteneurReception> stockConteneurReceptionList = stockConteneurReceptionRepository.findAll();
        assertThat(stockConteneurReceptionList).hasSize(databaseSizeBeforeCreate);

        // Validate the StockConteneurReception in Elasticsearch
        verify(mockStockConteneurReceptionSearchRepository, times(0)).save(stockConteneurReception);
    }

    @Test
    @Transactional
    public void getAllStockConteneurReceptions() throws Exception {
        // Initialize the database
        stockConteneurReceptionRepository.saveAndFlush(stockConteneurReception);

        // Get all the stockConteneurReceptionList
        restStockConteneurReceptionMockMvc.perform(get("/api/stock-conteneur-receptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockConteneurReception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateReception").value(hasItem(DEFAULT_DATE_RECEPTION.toString())))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].numeroBonEntree").value(hasItem(DEFAULT_NUMERO_BON_ENTREE.toString())))
            .andExpect(jsonPath("$.[*].numeroConstatNonConformite").value(hasItem(DEFAULT_NUMERO_CONSTAT_NON_CONFORMITE.toString())))
            .andExpect(jsonPath("$.[*].isValide").value(hasItem(DEFAULT_IS_VALIDE)));
    }
    
    @Test
    @Transactional
    public void getStockConteneurReception() throws Exception {
        // Initialize the database
        stockConteneurReceptionRepository.saveAndFlush(stockConteneurReception);

        // Get the stockConteneurReception
        restStockConteneurReceptionMockMvc.perform(get("/api/stock-conteneur-receptions/{id}", stockConteneurReception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stockConteneurReception.getId().intValue()))
            .andExpect(jsonPath("$.idOperation").value(DEFAULT_ID_OPERATION))
            .andExpect(jsonPath("$.dateReception").value(DEFAULT_DATE_RECEPTION.toString()))
            .andExpect(jsonPath("$.numeroOperation").value(DEFAULT_NUMERO_OPERATION.toString()))
            .andExpect(jsonPath("$.numeroBonEntree").value(DEFAULT_NUMERO_BON_ENTREE.toString()))
            .andExpect(jsonPath("$.numeroConstatNonConformite").value(DEFAULT_NUMERO_CONSTAT_NON_CONFORMITE.toString()))
            .andExpect(jsonPath("$.isValide").value(DEFAULT_IS_VALIDE));
    }

    @Test
    @Transactional
    public void getNonExistingStockConteneurReception() throws Exception {
        // Get the stockConteneurReception
        restStockConteneurReceptionMockMvc.perform(get("/api/stock-conteneur-receptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStockConteneurReception() throws Exception {
        // Initialize the database
        stockConteneurReceptionRepository.saveAndFlush(stockConteneurReception);

        int databaseSizeBeforeUpdate = stockConteneurReceptionRepository.findAll().size();

        // Update the stockConteneurReception
        StockConteneurReception updatedStockConteneurReception = stockConteneurReceptionRepository.findById(stockConteneurReception.getId()).get();
        // Disconnect from session so that the updates on updatedStockConteneurReception are not directly saved in db
        em.detach(updatedStockConteneurReception);
        updatedStockConteneurReception
            .idOperation(UPDATED_ID_OPERATION)
            .dateReception(UPDATED_DATE_RECEPTION)
            .numeroOperation(UPDATED_NUMERO_OPERATION)
            .numeroBonEntree(UPDATED_NUMERO_BON_ENTREE)
            .numeroConstatNonConformite(UPDATED_NUMERO_CONSTAT_NON_CONFORMITE)
            .isValide(UPDATED_IS_VALIDE);
        StockConteneurReceptionDTO stockConteneurReceptionDTO = stockConteneurReceptionMapper.toDto(updatedStockConteneurReception);

        restStockConteneurReceptionMockMvc.perform(put("/api/stock-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockConteneurReceptionDTO)))
            .andExpect(status().isOk());

        // Validate the StockConteneurReception in the database
        List<StockConteneurReception> stockConteneurReceptionList = stockConteneurReceptionRepository.findAll();
        assertThat(stockConteneurReceptionList).hasSize(databaseSizeBeforeUpdate);
        StockConteneurReception testStockConteneurReception = stockConteneurReceptionList.get(stockConteneurReceptionList.size() - 1);
        assertThat(testStockConteneurReception.getIdOperation()).isEqualTo(UPDATED_ID_OPERATION);
        assertThat(testStockConteneurReception.getDateReception()).isEqualTo(UPDATED_DATE_RECEPTION);
        assertThat(testStockConteneurReception.getNumeroOperation()).isEqualTo(UPDATED_NUMERO_OPERATION);
        assertThat(testStockConteneurReception.getNumeroBonEntree()).isEqualTo(UPDATED_NUMERO_BON_ENTREE);
        assertThat(testStockConteneurReception.getNumeroConstatNonConformite()).isEqualTo(UPDATED_NUMERO_CONSTAT_NON_CONFORMITE);
        assertThat(testStockConteneurReception.getIsValide()).isEqualTo(UPDATED_IS_VALIDE);

        // Validate the StockConteneurReception in Elasticsearch
        verify(mockStockConteneurReceptionSearchRepository, times(1)).save(testStockConteneurReception);
    }

    @Test
    @Transactional
    public void updateNonExistingStockConteneurReception() throws Exception {
        int databaseSizeBeforeUpdate = stockConteneurReceptionRepository.findAll().size();

        // Create the StockConteneurReception
        StockConteneurReceptionDTO stockConteneurReceptionDTO = stockConteneurReceptionMapper.toDto(stockConteneurReception);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockConteneurReceptionMockMvc.perform(put("/api/stock-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockConteneurReceptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockConteneurReception in the database
        List<StockConteneurReception> stockConteneurReceptionList = stockConteneurReceptionRepository.findAll();
        assertThat(stockConteneurReceptionList).hasSize(databaseSizeBeforeUpdate);

        // Validate the StockConteneurReception in Elasticsearch
        verify(mockStockConteneurReceptionSearchRepository, times(0)).save(stockConteneurReception);
    }

    @Test
    @Transactional
    public void deleteStockConteneurReception() throws Exception {
        // Initialize the database
        stockConteneurReceptionRepository.saveAndFlush(stockConteneurReception);

        int databaseSizeBeforeDelete = stockConteneurReceptionRepository.findAll().size();

        // Get the stockConteneurReception
        restStockConteneurReceptionMockMvc.perform(delete("/api/stock-conteneur-receptions/{id}", stockConteneurReception.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StockConteneurReception> stockConteneurReceptionList = stockConteneurReceptionRepository.findAll();
        assertThat(stockConteneurReceptionList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the StockConteneurReception in Elasticsearch
        verify(mockStockConteneurReceptionSearchRepository, times(1)).deleteById(stockConteneurReception.getId());
    }

    @Test
    @Transactional
    public void searchStockConteneurReception() throws Exception {
        // Initialize the database
        stockConteneurReceptionRepository.saveAndFlush(stockConteneurReception);
        when(mockStockConteneurReceptionSearchRepository.search(queryStringQuery("id:" + stockConteneurReception.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(stockConteneurReception), PageRequest.of(0, 1), 1));
        // Search the stockConteneurReception
        restStockConteneurReceptionMockMvc.perform(get("/api/_search/stock-conteneur-receptions?query=id:" + stockConteneurReception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockConteneurReception.getId().intValue())))
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
        TestUtil.equalsVerifier(StockConteneurReception.class);
        StockConteneurReception stockConteneurReception1 = new StockConteneurReception();
        stockConteneurReception1.setId(1L);
        StockConteneurReception stockConteneurReception2 = new StockConteneurReception();
        stockConteneurReception2.setId(stockConteneurReception1.getId());
        assertThat(stockConteneurReception1).isEqualTo(stockConteneurReception2);
        stockConteneurReception2.setId(2L);
        assertThat(stockConteneurReception1).isNotEqualTo(stockConteneurReception2);
        stockConteneurReception1.setId(null);
        assertThat(stockConteneurReception1).isNotEqualTo(stockConteneurReception2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockConteneurReceptionDTO.class);
        StockConteneurReceptionDTO stockConteneurReceptionDTO1 = new StockConteneurReceptionDTO();
        stockConteneurReceptionDTO1.setId(1L);
        StockConteneurReceptionDTO stockConteneurReceptionDTO2 = new StockConteneurReceptionDTO();
        assertThat(stockConteneurReceptionDTO1).isNotEqualTo(stockConteneurReceptionDTO2);
        stockConteneurReceptionDTO2.setId(stockConteneurReceptionDTO1.getId());
        assertThat(stockConteneurReceptionDTO1).isEqualTo(stockConteneurReceptionDTO2);
        stockConteneurReceptionDTO2.setId(2L);
        assertThat(stockConteneurReceptionDTO1).isNotEqualTo(stockConteneurReceptionDTO2);
        stockConteneurReceptionDTO1.setId(null);
        assertThat(stockConteneurReceptionDTO1).isNotEqualTo(stockConteneurReceptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(stockConteneurReceptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(stockConteneurReceptionMapper.fromId(null)).isNull();
    }
}
