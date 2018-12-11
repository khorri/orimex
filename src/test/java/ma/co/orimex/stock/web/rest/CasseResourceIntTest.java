package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Casse;
import ma.co.orimex.stock.repository.CasseRepository;
import ma.co.orimex.stock.repository.search.CasseSearchRepository;
import ma.co.orimex.stock.service.CasseService;
import ma.co.orimex.stock.service.dto.CasseDTO;
import ma.co.orimex.stock.service.mapper.CasseMapper;
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
 * Test class for the CasseResource REST controller.
 *
 * @see CasseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class CasseResourceIntTest {

    private static final Integer DEFAULT_ID_OPERATION = 1;
    private static final Integer UPDATED_ID_OPERATION = 2;

    private static final Instant DEFAULT_DATE_OPERATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OPERATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_NOMBRE_PLATEAUX = 1;
    private static final Integer UPDATED_NOMBRE_PLATEAUX = 2;

    private static final String DEFAULT_NUMERO_OPERATION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_OPERATION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SUPERFCIE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUPERFCIE = new BigDecimal(2);

    @Autowired
    private CasseRepository casseRepository;

    @Autowired
    private CasseMapper casseMapper;

    @Autowired
    private CasseService casseService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.CasseSearchRepositoryMockConfiguration
     */
    @Autowired
    private CasseSearchRepository mockCasseSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCasseMockMvc;

    private Casse casse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CasseResource casseResource = new CasseResource(casseService);
        this.restCasseMockMvc = MockMvcBuilders.standaloneSetup(casseResource)
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
    public static Casse createEntity(EntityManager em) {
        Casse casse = new Casse()
            .idOperation(DEFAULT_ID_OPERATION)
            .dateOperation(DEFAULT_DATE_OPERATION)
            .description(DEFAULT_DESCRIPTION)
            .nombrePlateaux(DEFAULT_NOMBRE_PLATEAUX)
            .numeroOperation(DEFAULT_NUMERO_OPERATION)
            .superfcie(DEFAULT_SUPERFCIE);
        return casse;
    }

    @Before
    public void initTest() {
        casse = createEntity(em);
    }

    @Test
    @Transactional
    public void createCasse() throws Exception {
        int databaseSizeBeforeCreate = casseRepository.findAll().size();

        // Create the Casse
        CasseDTO casseDTO = casseMapper.toDto(casse);
        restCasseMockMvc.perform(post("/api/casses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casseDTO)))
            .andExpect(status().isCreated());

        // Validate the Casse in the database
        List<Casse> casseList = casseRepository.findAll();
        assertThat(casseList).hasSize(databaseSizeBeforeCreate + 1);
        Casse testCasse = casseList.get(casseList.size() - 1);
        assertThat(testCasse.getIdOperation()).isEqualTo(DEFAULT_ID_OPERATION);
        assertThat(testCasse.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
        assertThat(testCasse.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCasse.getNombrePlateaux()).isEqualTo(DEFAULT_NOMBRE_PLATEAUX);
        assertThat(testCasse.getNumeroOperation()).isEqualTo(DEFAULT_NUMERO_OPERATION);
        assertThat(testCasse.getSuperfcie()).isEqualTo(DEFAULT_SUPERFCIE);

        // Validate the Casse in Elasticsearch
        verify(mockCasseSearchRepository, times(1)).save(testCasse);
    }

    @Test
    @Transactional
    public void createCasseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = casseRepository.findAll().size();

        // Create the Casse with an existing ID
        casse.setId(1L);
        CasseDTO casseDTO = casseMapper.toDto(casse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCasseMockMvc.perform(post("/api/casses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Casse in the database
        List<Casse> casseList = casseRepository.findAll();
        assertThat(casseList).hasSize(databaseSizeBeforeCreate);

        // Validate the Casse in Elasticsearch
        verify(mockCasseSearchRepository, times(0)).save(casse);
    }

    @Test
    @Transactional
    public void getAllCasses() throws Exception {
        // Initialize the database
        casseRepository.saveAndFlush(casse);

        // Get all the casseList
        restCasseMockMvc.perform(get("/api/casses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(casse.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(DEFAULT_DATE_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].superfcie").value(hasItem(DEFAULT_SUPERFCIE.intValue())));
    }
    
    @Test
    @Transactional
    public void getCasse() throws Exception {
        // Initialize the database
        casseRepository.saveAndFlush(casse);

        // Get the casse
        restCasseMockMvc.perform(get("/api/casses/{id}", casse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(casse.getId().intValue()))
            .andExpect(jsonPath("$.idOperation").value(DEFAULT_ID_OPERATION))
            .andExpect(jsonPath("$.dateOperation").value(DEFAULT_DATE_OPERATION.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.nombrePlateaux").value(DEFAULT_NOMBRE_PLATEAUX))
            .andExpect(jsonPath("$.numeroOperation").value(DEFAULT_NUMERO_OPERATION.toString()))
            .andExpect(jsonPath("$.superfcie").value(DEFAULT_SUPERFCIE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCasse() throws Exception {
        // Get the casse
        restCasseMockMvc.perform(get("/api/casses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCasse() throws Exception {
        // Initialize the database
        casseRepository.saveAndFlush(casse);

        int databaseSizeBeforeUpdate = casseRepository.findAll().size();

        // Update the casse
        Casse updatedCasse = casseRepository.findById(casse.getId()).get();
        // Disconnect from session so that the updates on updatedCasse are not directly saved in db
        em.detach(updatedCasse);
        updatedCasse
            .idOperation(UPDATED_ID_OPERATION)
            .dateOperation(UPDATED_DATE_OPERATION)
            .description(UPDATED_DESCRIPTION)
            .nombrePlateaux(UPDATED_NOMBRE_PLATEAUX)
            .numeroOperation(UPDATED_NUMERO_OPERATION)
            .superfcie(UPDATED_SUPERFCIE);
        CasseDTO casseDTO = casseMapper.toDto(updatedCasse);

        restCasseMockMvc.perform(put("/api/casses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casseDTO)))
            .andExpect(status().isOk());

        // Validate the Casse in the database
        List<Casse> casseList = casseRepository.findAll();
        assertThat(casseList).hasSize(databaseSizeBeforeUpdate);
        Casse testCasse = casseList.get(casseList.size() - 1);
        assertThat(testCasse.getIdOperation()).isEqualTo(UPDATED_ID_OPERATION);
        assertThat(testCasse.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
        assertThat(testCasse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCasse.getNombrePlateaux()).isEqualTo(UPDATED_NOMBRE_PLATEAUX);
        assertThat(testCasse.getNumeroOperation()).isEqualTo(UPDATED_NUMERO_OPERATION);
        assertThat(testCasse.getSuperfcie()).isEqualTo(UPDATED_SUPERFCIE);

        // Validate the Casse in Elasticsearch
        verify(mockCasseSearchRepository, times(1)).save(testCasse);
    }

    @Test
    @Transactional
    public void updateNonExistingCasse() throws Exception {
        int databaseSizeBeforeUpdate = casseRepository.findAll().size();

        // Create the Casse
        CasseDTO casseDTO = casseMapper.toDto(casse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCasseMockMvc.perform(put("/api/casses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Casse in the database
        List<Casse> casseList = casseRepository.findAll();
        assertThat(casseList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Casse in Elasticsearch
        verify(mockCasseSearchRepository, times(0)).save(casse);
    }

    @Test
    @Transactional
    public void deleteCasse() throws Exception {
        // Initialize the database
        casseRepository.saveAndFlush(casse);

        int databaseSizeBeforeDelete = casseRepository.findAll().size();

        // Get the casse
        restCasseMockMvc.perform(delete("/api/casses/{id}", casse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Casse> casseList = casseRepository.findAll();
        assertThat(casseList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Casse in Elasticsearch
        verify(mockCasseSearchRepository, times(1)).deleteById(casse.getId());
    }

    @Test
    @Transactional
    public void searchCasse() throws Exception {
        // Initialize the database
        casseRepository.saveAndFlush(casse);
        when(mockCasseSearchRepository.search(queryStringQuery("id:" + casse.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(casse), PageRequest.of(0, 1), 1));
        // Search the casse
        restCasseMockMvc.perform(get("/api/_search/casses?query=id:" + casse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(casse.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(DEFAULT_DATE_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION)))
            .andExpect(jsonPath("$.[*].superfcie").value(hasItem(DEFAULT_SUPERFCIE.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Casse.class);
        Casse casse1 = new Casse();
        casse1.setId(1L);
        Casse casse2 = new Casse();
        casse2.setId(casse1.getId());
        assertThat(casse1).isEqualTo(casse2);
        casse2.setId(2L);
        assertThat(casse1).isNotEqualTo(casse2);
        casse1.setId(null);
        assertThat(casse1).isNotEqualTo(casse2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CasseDTO.class);
        CasseDTO casseDTO1 = new CasseDTO();
        casseDTO1.setId(1L);
        CasseDTO casseDTO2 = new CasseDTO();
        assertThat(casseDTO1).isNotEqualTo(casseDTO2);
        casseDTO2.setId(casseDTO1.getId());
        assertThat(casseDTO1).isEqualTo(casseDTO2);
        casseDTO2.setId(2L);
        assertThat(casseDTO1).isNotEqualTo(casseDTO2);
        casseDTO1.setId(null);
        assertThat(casseDTO1).isNotEqualTo(casseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(casseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(casseMapper.fromId(null)).isNull();
    }
}
