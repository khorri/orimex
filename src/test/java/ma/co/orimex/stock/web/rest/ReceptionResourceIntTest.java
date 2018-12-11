package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Reception;
import ma.co.orimex.stock.repository.ReceptionRepository;
import ma.co.orimex.stock.repository.search.ReceptionSearchRepository;
import ma.co.orimex.stock.service.ReceptionService;
import ma.co.orimex.stock.service.dto.ReceptionDTO;
import ma.co.orimex.stock.service.mapper.ReceptionMapper;
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
 * Test class for the ReceptionResource REST controller.
 *
 * @see ReceptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class ReceptionResourceIntTest {

    private static final Integer DEFAULT_ID_OPERATION = 1;
    private static final Integer UPDATED_ID_OPERATION = 2;

    private static final Instant DEFAULT_DATE_RECEPTION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_RECEPTION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_NOMBRE_PLATEAUX = 1;
    private static final Integer UPDATED_NOMBRE_PLATEAUX = 2;

    private static final String DEFAULT_NUMERO_OPERATION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_OPERATION = "BBBBBBBBBB";

    @Autowired
    private ReceptionRepository receptionRepository;

    @Autowired
    private ReceptionMapper receptionMapper;

    @Autowired
    private ReceptionService receptionService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.ReceptionSearchRepositoryMockConfiguration
     */
    @Autowired
    private ReceptionSearchRepository mockReceptionSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReceptionMockMvc;

    private Reception reception;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReceptionResource receptionResource = new ReceptionResource(receptionService);
        this.restReceptionMockMvc = MockMvcBuilders.standaloneSetup(receptionResource)
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
    public static Reception createEntity(EntityManager em) {
        Reception reception = new Reception()
            .idOperation(DEFAULT_ID_OPERATION)
            .dateReception(DEFAULT_DATE_RECEPTION)
            .nombrePlateaux(DEFAULT_NOMBRE_PLATEAUX)
            .numeroOperation(DEFAULT_NUMERO_OPERATION);
        return reception;
    }

    @Before
    public void initTest() {
        reception = createEntity(em);
    }

    @Test
    @Transactional
    public void createReception() throws Exception {
        int databaseSizeBeforeCreate = receptionRepository.findAll().size();

        // Create the Reception
        ReceptionDTO receptionDTO = receptionMapper.toDto(reception);
        restReceptionMockMvc.perform(post("/api/receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receptionDTO)))
            .andExpect(status().isCreated());

        // Validate the Reception in the database
        List<Reception> receptionList = receptionRepository.findAll();
        assertThat(receptionList).hasSize(databaseSizeBeforeCreate + 1);
        Reception testReception = receptionList.get(receptionList.size() - 1);
        assertThat(testReception.getIdOperation()).isEqualTo(DEFAULT_ID_OPERATION);
        assertThat(testReception.getDateReception()).isEqualTo(DEFAULT_DATE_RECEPTION);
        assertThat(testReception.getNombrePlateaux()).isEqualTo(DEFAULT_NOMBRE_PLATEAUX);
        assertThat(testReception.getNumeroOperation()).isEqualTo(DEFAULT_NUMERO_OPERATION);

        // Validate the Reception in Elasticsearch
        verify(mockReceptionSearchRepository, times(1)).save(testReception);
    }

    @Test
    @Transactional
    public void createReceptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = receptionRepository.findAll().size();

        // Create the Reception with an existing ID
        reception.setId(1L);
        ReceptionDTO receptionDTO = receptionMapper.toDto(reception);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReceptionMockMvc.perform(post("/api/receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reception in the database
        List<Reception> receptionList = receptionRepository.findAll();
        assertThat(receptionList).hasSize(databaseSizeBeforeCreate);

        // Validate the Reception in Elasticsearch
        verify(mockReceptionSearchRepository, times(0)).save(reception);
    }

    @Test
    @Transactional
    public void getAllReceptions() throws Exception {
        // Initialize the database
        receptionRepository.saveAndFlush(reception);

        // Get all the receptionList
        restReceptionMockMvc.perform(get("/api/receptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateReception").value(hasItem(DEFAULT_DATE_RECEPTION.toString())))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION.toString())));
    }
    
    @Test
    @Transactional
    public void getReception() throws Exception {
        // Initialize the database
        receptionRepository.saveAndFlush(reception);

        // Get the reception
        restReceptionMockMvc.perform(get("/api/receptions/{id}", reception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reception.getId().intValue()))
            .andExpect(jsonPath("$.idOperation").value(DEFAULT_ID_OPERATION))
            .andExpect(jsonPath("$.dateReception").value(DEFAULT_DATE_RECEPTION.toString()))
            .andExpect(jsonPath("$.nombrePlateaux").value(DEFAULT_NOMBRE_PLATEAUX))
            .andExpect(jsonPath("$.numeroOperation").value(DEFAULT_NUMERO_OPERATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReception() throws Exception {
        // Get the reception
        restReceptionMockMvc.perform(get("/api/receptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReception() throws Exception {
        // Initialize the database
        receptionRepository.saveAndFlush(reception);

        int databaseSizeBeforeUpdate = receptionRepository.findAll().size();

        // Update the reception
        Reception updatedReception = receptionRepository.findById(reception.getId()).get();
        // Disconnect from session so that the updates on updatedReception are not directly saved in db
        em.detach(updatedReception);
        updatedReception
            .idOperation(UPDATED_ID_OPERATION)
            .dateReception(UPDATED_DATE_RECEPTION)
            .nombrePlateaux(UPDATED_NOMBRE_PLATEAUX)
            .numeroOperation(UPDATED_NUMERO_OPERATION);
        ReceptionDTO receptionDTO = receptionMapper.toDto(updatedReception);

        restReceptionMockMvc.perform(put("/api/receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receptionDTO)))
            .andExpect(status().isOk());

        // Validate the Reception in the database
        List<Reception> receptionList = receptionRepository.findAll();
        assertThat(receptionList).hasSize(databaseSizeBeforeUpdate);
        Reception testReception = receptionList.get(receptionList.size() - 1);
        assertThat(testReception.getIdOperation()).isEqualTo(UPDATED_ID_OPERATION);
        assertThat(testReception.getDateReception()).isEqualTo(UPDATED_DATE_RECEPTION);
        assertThat(testReception.getNombrePlateaux()).isEqualTo(UPDATED_NOMBRE_PLATEAUX);
        assertThat(testReception.getNumeroOperation()).isEqualTo(UPDATED_NUMERO_OPERATION);

        // Validate the Reception in Elasticsearch
        verify(mockReceptionSearchRepository, times(1)).save(testReception);
    }

    @Test
    @Transactional
    public void updateNonExistingReception() throws Exception {
        int databaseSizeBeforeUpdate = receptionRepository.findAll().size();

        // Create the Reception
        ReceptionDTO receptionDTO = receptionMapper.toDto(reception);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReceptionMockMvc.perform(put("/api/receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reception in the database
        List<Reception> receptionList = receptionRepository.findAll();
        assertThat(receptionList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Reception in Elasticsearch
        verify(mockReceptionSearchRepository, times(0)).save(reception);
    }

    @Test
    @Transactional
    public void deleteReception() throws Exception {
        // Initialize the database
        receptionRepository.saveAndFlush(reception);

        int databaseSizeBeforeDelete = receptionRepository.findAll().size();

        // Get the reception
        restReceptionMockMvc.perform(delete("/api/receptions/{id}", reception.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Reception> receptionList = receptionRepository.findAll();
        assertThat(receptionList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Reception in Elasticsearch
        verify(mockReceptionSearchRepository, times(1)).deleteById(reception.getId());
    }

    @Test
    @Transactional
    public void searchReception() throws Exception {
        // Initialize the database
        receptionRepository.saveAndFlush(reception);
        when(mockReceptionSearchRepository.search(queryStringQuery("id:" + reception.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(reception), PageRequest.of(0, 1), 1));
        // Search the reception
        restReceptionMockMvc.perform(get("/api/_search/receptions?query=id:" + reception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateReception").value(hasItem(DEFAULT_DATE_RECEPTION.toString())))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reception.class);
        Reception reception1 = new Reception();
        reception1.setId(1L);
        Reception reception2 = new Reception();
        reception2.setId(reception1.getId());
        assertThat(reception1).isEqualTo(reception2);
        reception2.setId(2L);
        assertThat(reception1).isNotEqualTo(reception2);
        reception1.setId(null);
        assertThat(reception1).isNotEqualTo(reception2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReceptionDTO.class);
        ReceptionDTO receptionDTO1 = new ReceptionDTO();
        receptionDTO1.setId(1L);
        ReceptionDTO receptionDTO2 = new ReceptionDTO();
        assertThat(receptionDTO1).isNotEqualTo(receptionDTO2);
        receptionDTO2.setId(receptionDTO1.getId());
        assertThat(receptionDTO1).isEqualTo(receptionDTO2);
        receptionDTO2.setId(2L);
        assertThat(receptionDTO1).isNotEqualTo(receptionDTO2);
        receptionDTO1.setId(null);
        assertThat(receptionDTO1).isNotEqualTo(receptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(receptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(receptionMapper.fromId(null)).isNull();
    }
}
