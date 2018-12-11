package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Recuperation;
import ma.co.orimex.stock.repository.RecuperationRepository;
import ma.co.orimex.stock.repository.search.RecuperationSearchRepository;
import ma.co.orimex.stock.service.RecuperationService;
import ma.co.orimex.stock.service.dto.RecuperationDTO;
import ma.co.orimex.stock.service.mapper.RecuperationMapper;
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
 * Test class for the RecuperationResource REST controller.
 *
 * @see RecuperationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class RecuperationResourceIntTest {

    private static final Integer DEFAULT_ID_OPERATION = 1;
    private static final Integer UPDATED_ID_OPERATION = 2;

    private static final Instant DEFAULT_DATE_OPERATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OPERATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_NOMBRE_PANNEAUX = 1;
    private static final Integer UPDATED_NOMBRE_PANNEAUX = 2;

    private static final String DEFAULT_NUMERO_OPERATION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_OPERATION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SUPERFICIE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUPERFICIE = new BigDecimal(2);

    @Autowired
    private RecuperationRepository recuperationRepository;

    @Autowired
    private RecuperationMapper recuperationMapper;

    @Autowired
    private RecuperationService recuperationService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.RecuperationSearchRepositoryMockConfiguration
     */
    @Autowired
    private RecuperationSearchRepository mockRecuperationSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRecuperationMockMvc;

    private Recuperation recuperation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RecuperationResource recuperationResource = new RecuperationResource(recuperationService);
        this.restRecuperationMockMvc = MockMvcBuilders.standaloneSetup(recuperationResource)
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
    public static Recuperation createEntity(EntityManager em) {
        Recuperation recuperation = new Recuperation()
            .idOperation(DEFAULT_ID_OPERATION)
            .dateOperation(DEFAULT_DATE_OPERATION)
            .nombrePanneaux(DEFAULT_NOMBRE_PANNEAUX)
            .numeroOperation(DEFAULT_NUMERO_OPERATION)
            .superficie(DEFAULT_SUPERFICIE);
        return recuperation;
    }

    @Before
    public void initTest() {
        recuperation = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecuperation() throws Exception {
        int databaseSizeBeforeCreate = recuperationRepository.findAll().size();

        // Create the Recuperation
        RecuperationDTO recuperationDTO = recuperationMapper.toDto(recuperation);
        restRecuperationMockMvc.perform(post("/api/recuperations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recuperationDTO)))
            .andExpect(status().isCreated());

        // Validate the Recuperation in the database
        List<Recuperation> recuperationList = recuperationRepository.findAll();
        assertThat(recuperationList).hasSize(databaseSizeBeforeCreate + 1);
        Recuperation testRecuperation = recuperationList.get(recuperationList.size() - 1);
        assertThat(testRecuperation.getIdOperation()).isEqualTo(DEFAULT_ID_OPERATION);
        assertThat(testRecuperation.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
        assertThat(testRecuperation.getNombrePanneaux()).isEqualTo(DEFAULT_NOMBRE_PANNEAUX);
        assertThat(testRecuperation.getNumeroOperation()).isEqualTo(DEFAULT_NUMERO_OPERATION);
        assertThat(testRecuperation.getSuperficie()).isEqualTo(DEFAULT_SUPERFICIE);

        // Validate the Recuperation in Elasticsearch
        verify(mockRecuperationSearchRepository, times(1)).save(testRecuperation);
    }

    @Test
    @Transactional
    public void createRecuperationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recuperationRepository.findAll().size();

        // Create the Recuperation with an existing ID
        recuperation.setId(1L);
        RecuperationDTO recuperationDTO = recuperationMapper.toDto(recuperation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecuperationMockMvc.perform(post("/api/recuperations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recuperationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Recuperation in the database
        List<Recuperation> recuperationList = recuperationRepository.findAll();
        assertThat(recuperationList).hasSize(databaseSizeBeforeCreate);

        // Validate the Recuperation in Elasticsearch
        verify(mockRecuperationSearchRepository, times(0)).save(recuperation);
    }

    @Test
    @Transactional
    public void getAllRecuperations() throws Exception {
        // Initialize the database
        recuperationRepository.saveAndFlush(recuperation);

        // Get all the recuperationList
        restRecuperationMockMvc.perform(get("/api/recuperations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recuperation.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(DEFAULT_DATE_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].nombrePanneaux").value(hasItem(DEFAULT_NOMBRE_PANNEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].superficie").value(hasItem(DEFAULT_SUPERFICIE.intValue())));
    }
    
    @Test
    @Transactional
    public void getRecuperation() throws Exception {
        // Initialize the database
        recuperationRepository.saveAndFlush(recuperation);

        // Get the recuperation
        restRecuperationMockMvc.perform(get("/api/recuperations/{id}", recuperation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recuperation.getId().intValue()))
            .andExpect(jsonPath("$.idOperation").value(DEFAULT_ID_OPERATION))
            .andExpect(jsonPath("$.dateOperation").value(DEFAULT_DATE_OPERATION.toString()))
            .andExpect(jsonPath("$.nombrePanneaux").value(DEFAULT_NOMBRE_PANNEAUX))
            .andExpect(jsonPath("$.numeroOperation").value(DEFAULT_NUMERO_OPERATION.toString()))
            .andExpect(jsonPath("$.superficie").value(DEFAULT_SUPERFICIE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRecuperation() throws Exception {
        // Get the recuperation
        restRecuperationMockMvc.perform(get("/api/recuperations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecuperation() throws Exception {
        // Initialize the database
        recuperationRepository.saveAndFlush(recuperation);

        int databaseSizeBeforeUpdate = recuperationRepository.findAll().size();

        // Update the recuperation
        Recuperation updatedRecuperation = recuperationRepository.findById(recuperation.getId()).get();
        // Disconnect from session so that the updates on updatedRecuperation are not directly saved in db
        em.detach(updatedRecuperation);
        updatedRecuperation
            .idOperation(UPDATED_ID_OPERATION)
            .dateOperation(UPDATED_DATE_OPERATION)
            .nombrePanneaux(UPDATED_NOMBRE_PANNEAUX)
            .numeroOperation(UPDATED_NUMERO_OPERATION)
            .superficie(UPDATED_SUPERFICIE);
        RecuperationDTO recuperationDTO = recuperationMapper.toDto(updatedRecuperation);

        restRecuperationMockMvc.perform(put("/api/recuperations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recuperationDTO)))
            .andExpect(status().isOk());

        // Validate the Recuperation in the database
        List<Recuperation> recuperationList = recuperationRepository.findAll();
        assertThat(recuperationList).hasSize(databaseSizeBeforeUpdate);
        Recuperation testRecuperation = recuperationList.get(recuperationList.size() - 1);
        assertThat(testRecuperation.getIdOperation()).isEqualTo(UPDATED_ID_OPERATION);
        assertThat(testRecuperation.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
        assertThat(testRecuperation.getNombrePanneaux()).isEqualTo(UPDATED_NOMBRE_PANNEAUX);
        assertThat(testRecuperation.getNumeroOperation()).isEqualTo(UPDATED_NUMERO_OPERATION);
        assertThat(testRecuperation.getSuperficie()).isEqualTo(UPDATED_SUPERFICIE);

        // Validate the Recuperation in Elasticsearch
        verify(mockRecuperationSearchRepository, times(1)).save(testRecuperation);
    }

    @Test
    @Transactional
    public void updateNonExistingRecuperation() throws Exception {
        int databaseSizeBeforeUpdate = recuperationRepository.findAll().size();

        // Create the Recuperation
        RecuperationDTO recuperationDTO = recuperationMapper.toDto(recuperation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecuperationMockMvc.perform(put("/api/recuperations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recuperationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Recuperation in the database
        List<Recuperation> recuperationList = recuperationRepository.findAll();
        assertThat(recuperationList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Recuperation in Elasticsearch
        verify(mockRecuperationSearchRepository, times(0)).save(recuperation);
    }

    @Test
    @Transactional
    public void deleteRecuperation() throws Exception {
        // Initialize the database
        recuperationRepository.saveAndFlush(recuperation);

        int databaseSizeBeforeDelete = recuperationRepository.findAll().size();

        // Get the recuperation
        restRecuperationMockMvc.perform(delete("/api/recuperations/{id}", recuperation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Recuperation> recuperationList = recuperationRepository.findAll();
        assertThat(recuperationList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Recuperation in Elasticsearch
        verify(mockRecuperationSearchRepository, times(1)).deleteById(recuperation.getId());
    }

    @Test
    @Transactional
    public void searchRecuperation() throws Exception {
        // Initialize the database
        recuperationRepository.saveAndFlush(recuperation);
        when(mockRecuperationSearchRepository.search(queryStringQuery("id:" + recuperation.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(recuperation), PageRequest.of(0, 1), 1));
        // Search the recuperation
        restRecuperationMockMvc.perform(get("/api/_search/recuperations?query=id:" + recuperation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recuperation.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(DEFAULT_DATE_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].nombrePanneaux").value(hasItem(DEFAULT_NOMBRE_PANNEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION)))
            .andExpect(jsonPath("$.[*].superficie").value(hasItem(DEFAULT_SUPERFICIE.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Recuperation.class);
        Recuperation recuperation1 = new Recuperation();
        recuperation1.setId(1L);
        Recuperation recuperation2 = new Recuperation();
        recuperation2.setId(recuperation1.getId());
        assertThat(recuperation1).isEqualTo(recuperation2);
        recuperation2.setId(2L);
        assertThat(recuperation1).isNotEqualTo(recuperation2);
        recuperation1.setId(null);
        assertThat(recuperation1).isNotEqualTo(recuperation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecuperationDTO.class);
        RecuperationDTO recuperationDTO1 = new RecuperationDTO();
        recuperationDTO1.setId(1L);
        RecuperationDTO recuperationDTO2 = new RecuperationDTO();
        assertThat(recuperationDTO1).isNotEqualTo(recuperationDTO2);
        recuperationDTO2.setId(recuperationDTO1.getId());
        assertThat(recuperationDTO1).isEqualTo(recuperationDTO2);
        recuperationDTO2.setId(2L);
        assertThat(recuperationDTO1).isNotEqualTo(recuperationDTO2);
        recuperationDTO1.setId(null);
        assertThat(recuperationDTO1).isNotEqualTo(recuperationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(recuperationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(recuperationMapper.fromId(null)).isNull();
    }
}
