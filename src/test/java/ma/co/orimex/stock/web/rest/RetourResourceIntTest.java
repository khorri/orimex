package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Retour;
import ma.co.orimex.stock.repository.RetourRepository;
import ma.co.orimex.stock.repository.search.RetourSearchRepository;
import ma.co.orimex.stock.service.RetourService;
import ma.co.orimex.stock.service.dto.RetourDTO;
import ma.co.orimex.stock.service.mapper.RetourMapper;
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
 * Test class for the RetourResource REST controller.
 *
 * @see RetourResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class RetourResourceIntTest {

    private static final Integer DEFAULT_ID_OPERATION = 1;
    private static final Integer UPDATED_ID_OPERATION = 2;

    private static final Instant DEFAULT_DATE_OPERATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OPERATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_NOMBRE_PLATEAUX = 1;
    private static final Integer UPDATED_NOMBRE_PLATEAUX = 2;

    private static final String DEFAULT_NUMERO_OPERATION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_OPERATION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SUPERFECIE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUPERFECIE = new BigDecimal(2);

    @Autowired
    private RetourRepository retourRepository;

    @Autowired
    private RetourMapper retourMapper;

    @Autowired
    private RetourService retourService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.RetourSearchRepositoryMockConfiguration
     */
    @Autowired
    private RetourSearchRepository mockRetourSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRetourMockMvc;

    private Retour retour;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RetourResource retourResource = new RetourResource(retourService);
        this.restRetourMockMvc = MockMvcBuilders.standaloneSetup(retourResource)
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
    public static Retour createEntity(EntityManager em) {
        Retour retour = new Retour()
            .idOperation(DEFAULT_ID_OPERATION)
            .dateOperation(DEFAULT_DATE_OPERATION)
            .nombrePlateaux(DEFAULT_NOMBRE_PLATEAUX)
            .numeroOperation(DEFAULT_NUMERO_OPERATION)
            .superfecie(DEFAULT_SUPERFECIE);
        return retour;
    }

    @Before
    public void initTest() {
        retour = createEntity(em);
    }

    @Test
    @Transactional
    public void createRetour() throws Exception {
        int databaseSizeBeforeCreate = retourRepository.findAll().size();

        // Create the Retour
        RetourDTO retourDTO = retourMapper.toDto(retour);
        restRetourMockMvc.perform(post("/api/retours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(retourDTO)))
            .andExpect(status().isCreated());

        // Validate the Retour in the database
        List<Retour> retourList = retourRepository.findAll();
        assertThat(retourList).hasSize(databaseSizeBeforeCreate + 1);
        Retour testRetour = retourList.get(retourList.size() - 1);
        assertThat(testRetour.getIdOperation()).isEqualTo(DEFAULT_ID_OPERATION);
        assertThat(testRetour.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
        assertThat(testRetour.getNombrePlateaux()).isEqualTo(DEFAULT_NOMBRE_PLATEAUX);
        assertThat(testRetour.getNumeroOperation()).isEqualTo(DEFAULT_NUMERO_OPERATION);
        assertThat(testRetour.getSuperfecie()).isEqualTo(DEFAULT_SUPERFECIE);

        // Validate the Retour in Elasticsearch
        verify(mockRetourSearchRepository, times(1)).save(testRetour);
    }

    @Test
    @Transactional
    public void createRetourWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = retourRepository.findAll().size();

        // Create the Retour with an existing ID
        retour.setId(1L);
        RetourDTO retourDTO = retourMapper.toDto(retour);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRetourMockMvc.perform(post("/api/retours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(retourDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Retour in the database
        List<Retour> retourList = retourRepository.findAll();
        assertThat(retourList).hasSize(databaseSizeBeforeCreate);

        // Validate the Retour in Elasticsearch
        verify(mockRetourSearchRepository, times(0)).save(retour);
    }

    @Test
    @Transactional
    public void getAllRetours() throws Exception {
        // Initialize the database
        retourRepository.saveAndFlush(retour);

        // Get all the retourList
        restRetourMockMvc.perform(get("/api/retours?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(retour.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(DEFAULT_DATE_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].superfecie").value(hasItem(DEFAULT_SUPERFECIE.intValue())));
    }
    
    @Test
    @Transactional
    public void getRetour() throws Exception {
        // Initialize the database
        retourRepository.saveAndFlush(retour);

        // Get the retour
        restRetourMockMvc.perform(get("/api/retours/{id}", retour.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(retour.getId().intValue()))
            .andExpect(jsonPath("$.idOperation").value(DEFAULT_ID_OPERATION))
            .andExpect(jsonPath("$.dateOperation").value(DEFAULT_DATE_OPERATION.toString()))
            .andExpect(jsonPath("$.nombrePlateaux").value(DEFAULT_NOMBRE_PLATEAUX))
            .andExpect(jsonPath("$.numeroOperation").value(DEFAULT_NUMERO_OPERATION.toString()))
            .andExpect(jsonPath("$.superfecie").value(DEFAULT_SUPERFECIE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRetour() throws Exception {
        // Get the retour
        restRetourMockMvc.perform(get("/api/retours/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRetour() throws Exception {
        // Initialize the database
        retourRepository.saveAndFlush(retour);

        int databaseSizeBeforeUpdate = retourRepository.findAll().size();

        // Update the retour
        Retour updatedRetour = retourRepository.findById(retour.getId()).get();
        // Disconnect from session so that the updates on updatedRetour are not directly saved in db
        em.detach(updatedRetour);
        updatedRetour
            .idOperation(UPDATED_ID_OPERATION)
            .dateOperation(UPDATED_DATE_OPERATION)
            .nombrePlateaux(UPDATED_NOMBRE_PLATEAUX)
            .numeroOperation(UPDATED_NUMERO_OPERATION)
            .superfecie(UPDATED_SUPERFECIE);
        RetourDTO retourDTO = retourMapper.toDto(updatedRetour);

        restRetourMockMvc.perform(put("/api/retours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(retourDTO)))
            .andExpect(status().isOk());

        // Validate the Retour in the database
        List<Retour> retourList = retourRepository.findAll();
        assertThat(retourList).hasSize(databaseSizeBeforeUpdate);
        Retour testRetour = retourList.get(retourList.size() - 1);
        assertThat(testRetour.getIdOperation()).isEqualTo(UPDATED_ID_OPERATION);
        assertThat(testRetour.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
        assertThat(testRetour.getNombrePlateaux()).isEqualTo(UPDATED_NOMBRE_PLATEAUX);
        assertThat(testRetour.getNumeroOperation()).isEqualTo(UPDATED_NUMERO_OPERATION);
        assertThat(testRetour.getSuperfecie()).isEqualTo(UPDATED_SUPERFECIE);

        // Validate the Retour in Elasticsearch
        verify(mockRetourSearchRepository, times(1)).save(testRetour);
    }

    @Test
    @Transactional
    public void updateNonExistingRetour() throws Exception {
        int databaseSizeBeforeUpdate = retourRepository.findAll().size();

        // Create the Retour
        RetourDTO retourDTO = retourMapper.toDto(retour);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRetourMockMvc.perform(put("/api/retours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(retourDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Retour in the database
        List<Retour> retourList = retourRepository.findAll();
        assertThat(retourList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Retour in Elasticsearch
        verify(mockRetourSearchRepository, times(0)).save(retour);
    }

    @Test
    @Transactional
    public void deleteRetour() throws Exception {
        // Initialize the database
        retourRepository.saveAndFlush(retour);

        int databaseSizeBeforeDelete = retourRepository.findAll().size();

        // Get the retour
        restRetourMockMvc.perform(delete("/api/retours/{id}", retour.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Retour> retourList = retourRepository.findAll();
        assertThat(retourList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Retour in Elasticsearch
        verify(mockRetourSearchRepository, times(1)).deleteById(retour.getId());
    }

    @Test
    @Transactional
    public void searchRetour() throws Exception {
        // Initialize the database
        retourRepository.saveAndFlush(retour);
        when(mockRetourSearchRepository.search(queryStringQuery("id:" + retour.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(retour), PageRequest.of(0, 1), 1));
        // Search the retour
        restRetourMockMvc.perform(get("/api/_search/retours?query=id:" + retour.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(retour.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(DEFAULT_DATE_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION)))
            .andExpect(jsonPath("$.[*].superfecie").value(hasItem(DEFAULT_SUPERFECIE.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Retour.class);
        Retour retour1 = new Retour();
        retour1.setId(1L);
        Retour retour2 = new Retour();
        retour2.setId(retour1.getId());
        assertThat(retour1).isEqualTo(retour2);
        retour2.setId(2L);
        assertThat(retour1).isNotEqualTo(retour2);
        retour1.setId(null);
        assertThat(retour1).isNotEqualTo(retour2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RetourDTO.class);
        RetourDTO retourDTO1 = new RetourDTO();
        retourDTO1.setId(1L);
        RetourDTO retourDTO2 = new RetourDTO();
        assertThat(retourDTO1).isNotEqualTo(retourDTO2);
        retourDTO2.setId(retourDTO1.getId());
        assertThat(retourDTO1).isEqualTo(retourDTO2);
        retourDTO2.setId(2L);
        assertThat(retourDTO1).isNotEqualTo(retourDTO2);
        retourDTO1.setId(null);
        assertThat(retourDTO1).isNotEqualTo(retourDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(retourMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(retourMapper.fromId(null)).isNull();
    }
}
