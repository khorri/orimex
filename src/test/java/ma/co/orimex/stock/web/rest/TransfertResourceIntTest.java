package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Transfert;
import ma.co.orimex.stock.repository.TransfertRepository;
import ma.co.orimex.stock.repository.search.TransfertSearchRepository;
import ma.co.orimex.stock.service.TransfertService;
import ma.co.orimex.stock.service.dto.TransfertDTO;
import ma.co.orimex.stock.service.mapper.TransfertMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Test class for the TransfertResource REST controller.
 *
 * @see TransfertResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class TransfertResourceIntTest {

    private static final Integer DEFAULT_ID_OPERATION = 1;
    private static final Integer UPDATED_ID_OPERATION = 2;

    private static final Instant DEFAULT_DATE_OPERATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OPERATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_NOMBRE_PLATEAUX = 1;
    private static final Integer UPDATED_NOMBRE_PLATEAUX = 2;

    private static final String DEFAULT_NUMERO_OPERATION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_OPERATION = "BBBBBBBBBB";

    @Autowired
    private TransfertRepository transfertRepository;

    @Autowired
    private TransfertMapper transfertMapper;

    @Autowired
    private TransfertService transfertService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.TransfertSearchRepositoryMockConfiguration
     */
    @Autowired
    private TransfertSearchRepository mockTransfertSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTransfertMockMvc;

    private Transfert transfert;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransfertResource transfertResource = new TransfertResource(transfertService);
        this.restTransfertMockMvc = MockMvcBuilders.standaloneSetup(transfertResource)
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
    public static Transfert createEntity(EntityManager em) {
        Transfert transfert = new Transfert()
            .idOperation(DEFAULT_ID_OPERATION)
            .dateOperation(DEFAULT_DATE_OPERATION)
            .nombrePlateaux(DEFAULT_NOMBRE_PLATEAUX)
            .numeroOperation(DEFAULT_NUMERO_OPERATION);
        return transfert;
    }

    @Before
    public void initTest() {
        transfert = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransfert() throws Exception {
        int databaseSizeBeforeCreate = transfertRepository.findAll().size();

        // Create the Transfert
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);
        restTransfertMockMvc.perform(post("/api/transferts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isCreated());

        // Validate the Transfert in the database
        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeCreate + 1);
        Transfert testTransfert = transfertList.get(transfertList.size() - 1);
        assertThat(testTransfert.getIdOperation()).isEqualTo(DEFAULT_ID_OPERATION);
        assertThat(testTransfert.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
        assertThat(testTransfert.getNombrePlateaux()).isEqualTo(DEFAULT_NOMBRE_PLATEAUX);
        assertThat(testTransfert.getNumeroOperation()).isEqualTo(DEFAULT_NUMERO_OPERATION);

        // Validate the Transfert in Elasticsearch
        verify(mockTransfertSearchRepository, times(1)).save(testTransfert);
    }

    @Test
    @Transactional
    public void createTransfertWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transfertRepository.findAll().size();

        // Create the Transfert with an existing ID
        transfert.setId(1L);
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransfertMockMvc.perform(post("/api/transferts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transfert in the database
        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeCreate);

        // Validate the Transfert in Elasticsearch
        verify(mockTransfertSearchRepository, times(0)).save(transfert);
    }

    @Test
    @Transactional
    public void getAllTransferts() throws Exception {
        // Initialize the database
        transfertRepository.saveAndFlush(transfert);

        // Get all the transfertList
        restTransfertMockMvc.perform(get("/api/transferts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transfert.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(DEFAULT_DATE_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION.toString())));
    }
    
    @Test
    @Transactional
    public void getTransfert() throws Exception {
        // Initialize the database
        transfertRepository.saveAndFlush(transfert);

        // Get the transfert
        restTransfertMockMvc.perform(get("/api/transferts/{id}", transfert.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transfert.getId().intValue()))
            .andExpect(jsonPath("$.idOperation").value(DEFAULT_ID_OPERATION))
            .andExpect(jsonPath("$.dateOperation").value(DEFAULT_DATE_OPERATION.toString()))
            .andExpect(jsonPath("$.nombrePlateaux").value(DEFAULT_NOMBRE_PLATEAUX))
            .andExpect(jsonPath("$.numeroOperation").value(DEFAULT_NUMERO_OPERATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTransfert() throws Exception {
        // Get the transfert
        restTransfertMockMvc.perform(get("/api/transferts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransfert() throws Exception {
        // Initialize the database
        transfertRepository.saveAndFlush(transfert);

        int databaseSizeBeforeUpdate = transfertRepository.findAll().size();

        // Update the transfert
        Transfert updatedTransfert = transfertRepository.findById(transfert.getId()).get();
        // Disconnect from session so that the updates on updatedTransfert are not directly saved in db
        em.detach(updatedTransfert);
        updatedTransfert
            .idOperation(UPDATED_ID_OPERATION)
            .dateOperation(UPDATED_DATE_OPERATION)
            .nombrePlateaux(UPDATED_NOMBRE_PLATEAUX)
            .numeroOperation(UPDATED_NUMERO_OPERATION);
        TransfertDTO transfertDTO = transfertMapper.toDto(updatedTransfert);

        restTransfertMockMvc.perform(put("/api/transferts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isOk());

        // Validate the Transfert in the database
        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeUpdate);
        Transfert testTransfert = transfertList.get(transfertList.size() - 1);
        assertThat(testTransfert.getIdOperation()).isEqualTo(UPDATED_ID_OPERATION);
        assertThat(testTransfert.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
        assertThat(testTransfert.getNombrePlateaux()).isEqualTo(UPDATED_NOMBRE_PLATEAUX);
        assertThat(testTransfert.getNumeroOperation()).isEqualTo(UPDATED_NUMERO_OPERATION);

        // Validate the Transfert in Elasticsearch
        verify(mockTransfertSearchRepository, times(1)).save(testTransfert);
    }

    @Test
    @Transactional
    public void updateNonExistingTransfert() throws Exception {
        int databaseSizeBeforeUpdate = transfertRepository.findAll().size();

        // Create the Transfert
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransfertMockMvc.perform(put("/api/transferts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transfert in the database
        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Transfert in Elasticsearch
        verify(mockTransfertSearchRepository, times(0)).save(transfert);
    }

    @Test
    @Transactional
    public void deleteTransfert() throws Exception {
        // Initialize the database
        transfertRepository.saveAndFlush(transfert);

        int databaseSizeBeforeDelete = transfertRepository.findAll().size();

        // Get the transfert
        restTransfertMockMvc.perform(delete("/api/transferts/{id}", transfert.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Transfert in Elasticsearch
        verify(mockTransfertSearchRepository, times(1)).deleteById(transfert.getId());
    }

    @Test
    @Transactional
    public void searchTransfert() throws Exception {
        // Initialize the database
        transfertRepository.saveAndFlush(transfert);
        when(mockTransfertSearchRepository.search(queryStringQuery("id:" + transfert.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(transfert), PageRequest.of(0, 1), 1));
        // Search the transfert
        restTransfertMockMvc.perform(get("/api/_search/transferts?query=id:" + transfert.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transfert.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(DEFAULT_DATE_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transfert.class);
        Transfert transfert1 = new Transfert();
        transfert1.setId(1L);
        Transfert transfert2 = new Transfert();
        transfert2.setId(transfert1.getId());
        assertThat(transfert1).isEqualTo(transfert2);
        transfert2.setId(2L);
        assertThat(transfert1).isNotEqualTo(transfert2);
        transfert1.setId(null);
        assertThat(transfert1).isNotEqualTo(transfert2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransfertDTO.class);
        TransfertDTO transfertDTO1 = new TransfertDTO();
        transfertDTO1.setId(1L);
        TransfertDTO transfertDTO2 = new TransfertDTO();
        assertThat(transfertDTO1).isNotEqualTo(transfertDTO2);
        transfertDTO2.setId(transfertDTO1.getId());
        assertThat(transfertDTO1).isEqualTo(transfertDTO2);
        transfertDTO2.setId(2L);
        assertThat(transfertDTO1).isNotEqualTo(transfertDTO2);
        transfertDTO1.setId(null);
        assertThat(transfertDTO1).isNotEqualTo(transfertDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(transfertMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(transfertMapper.fromId(null)).isNull();
    }
}
