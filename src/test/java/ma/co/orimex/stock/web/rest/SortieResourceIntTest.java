package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Sortie;
import ma.co.orimex.stock.repository.SortieRepository;
import ma.co.orimex.stock.repository.search.SortieSearchRepository;
import ma.co.orimex.stock.service.SortieService;
import ma.co.orimex.stock.service.dto.SortieDTO;
import ma.co.orimex.stock.service.mapper.SortieMapper;
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
 * Test class for the SortieResource REST controller.
 *
 * @see SortieResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class SortieResourceIntTest {

    private static final Integer DEFAULT_ID_OPERATION = 1;
    private static final Integer UPDATED_ID_OPERATION = 2;

    private static final Instant DEFAULT_DATE_OPERATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OPERATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_NOMBRE_PLATEAUX = 1;
    private static final Integer UPDATED_NOMBRE_PLATEAUX = 2;

    private static final String DEFAULT_NUMERO_OPERATION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_OPERATION = "BBBBBBBBBB";

    @Autowired
    private SortieRepository sortieRepository;

    @Autowired
    private SortieMapper sortieMapper;

    @Autowired
    private SortieService sortieService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.SortieSearchRepositoryMockConfiguration
     */
    @Autowired
    private SortieSearchRepository mockSortieSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSortieMockMvc;

    private Sortie sortie;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SortieResource sortieResource = new SortieResource(sortieService);
        this.restSortieMockMvc = MockMvcBuilders.standaloneSetup(sortieResource)
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
    public static Sortie createEntity(EntityManager em) {
        Sortie sortie = new Sortie()
            .idOperation(DEFAULT_ID_OPERATION)
            .dateOperation(DEFAULT_DATE_OPERATION)
            .nombrePlateaux(DEFAULT_NOMBRE_PLATEAUX)
            .numeroOperation(DEFAULT_NUMERO_OPERATION);
        return sortie;
    }

    @Before
    public void initTest() {
        sortie = createEntity(em);
    }

    @Test
    @Transactional
    public void createSortie() throws Exception {
        int databaseSizeBeforeCreate = sortieRepository.findAll().size();

        // Create the Sortie
        SortieDTO sortieDTO = sortieMapper.toDto(sortie);
        restSortieMockMvc.perform(post("/api/sorties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sortieDTO)))
            .andExpect(status().isCreated());

        // Validate the Sortie in the database
        List<Sortie> sortieList = sortieRepository.findAll();
        assertThat(sortieList).hasSize(databaseSizeBeforeCreate + 1);
        Sortie testSortie = sortieList.get(sortieList.size() - 1);
        assertThat(testSortie.getIdOperation()).isEqualTo(DEFAULT_ID_OPERATION);
        assertThat(testSortie.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
        assertThat(testSortie.getNombrePlateaux()).isEqualTo(DEFAULT_NOMBRE_PLATEAUX);
        assertThat(testSortie.getNumeroOperation()).isEqualTo(DEFAULT_NUMERO_OPERATION);

        // Validate the Sortie in Elasticsearch
        verify(mockSortieSearchRepository, times(1)).save(testSortie);
    }

    @Test
    @Transactional
    public void createSortieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sortieRepository.findAll().size();

        // Create the Sortie with an existing ID
        sortie.setId(1L);
        SortieDTO sortieDTO = sortieMapper.toDto(sortie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSortieMockMvc.perform(post("/api/sorties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sortieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sortie in the database
        List<Sortie> sortieList = sortieRepository.findAll();
        assertThat(sortieList).hasSize(databaseSizeBeforeCreate);

        // Validate the Sortie in Elasticsearch
        verify(mockSortieSearchRepository, times(0)).save(sortie);
    }

    @Test
    @Transactional
    public void getAllSorties() throws Exception {
        // Initialize the database
        sortieRepository.saveAndFlush(sortie);

        // Get all the sortieList
        restSortieMockMvc.perform(get("/api/sorties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sortie.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(DEFAULT_DATE_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION.toString())));
    }
    
    @Test
    @Transactional
    public void getSortie() throws Exception {
        // Initialize the database
        sortieRepository.saveAndFlush(sortie);

        // Get the sortie
        restSortieMockMvc.perform(get("/api/sorties/{id}", sortie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sortie.getId().intValue()))
            .andExpect(jsonPath("$.idOperation").value(DEFAULT_ID_OPERATION))
            .andExpect(jsonPath("$.dateOperation").value(DEFAULT_DATE_OPERATION.toString()))
            .andExpect(jsonPath("$.nombrePlateaux").value(DEFAULT_NOMBRE_PLATEAUX))
            .andExpect(jsonPath("$.numeroOperation").value(DEFAULT_NUMERO_OPERATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSortie() throws Exception {
        // Get the sortie
        restSortieMockMvc.perform(get("/api/sorties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSortie() throws Exception {
        // Initialize the database
        sortieRepository.saveAndFlush(sortie);

        int databaseSizeBeforeUpdate = sortieRepository.findAll().size();

        // Update the sortie
        Sortie updatedSortie = sortieRepository.findById(sortie.getId()).get();
        // Disconnect from session so that the updates on updatedSortie are not directly saved in db
        em.detach(updatedSortie);
        updatedSortie
            .idOperation(UPDATED_ID_OPERATION)
            .dateOperation(UPDATED_DATE_OPERATION)
            .nombrePlateaux(UPDATED_NOMBRE_PLATEAUX)
            .numeroOperation(UPDATED_NUMERO_OPERATION);
        SortieDTO sortieDTO = sortieMapper.toDto(updatedSortie);

        restSortieMockMvc.perform(put("/api/sorties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sortieDTO)))
            .andExpect(status().isOk());

        // Validate the Sortie in the database
        List<Sortie> sortieList = sortieRepository.findAll();
        assertThat(sortieList).hasSize(databaseSizeBeforeUpdate);
        Sortie testSortie = sortieList.get(sortieList.size() - 1);
        assertThat(testSortie.getIdOperation()).isEqualTo(UPDATED_ID_OPERATION);
        assertThat(testSortie.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
        assertThat(testSortie.getNombrePlateaux()).isEqualTo(UPDATED_NOMBRE_PLATEAUX);
        assertThat(testSortie.getNumeroOperation()).isEqualTo(UPDATED_NUMERO_OPERATION);

        // Validate the Sortie in Elasticsearch
        verify(mockSortieSearchRepository, times(1)).save(testSortie);
    }

    @Test
    @Transactional
    public void updateNonExistingSortie() throws Exception {
        int databaseSizeBeforeUpdate = sortieRepository.findAll().size();

        // Create the Sortie
        SortieDTO sortieDTO = sortieMapper.toDto(sortie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSortieMockMvc.perform(put("/api/sorties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sortieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sortie in the database
        List<Sortie> sortieList = sortieRepository.findAll();
        assertThat(sortieList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Sortie in Elasticsearch
        verify(mockSortieSearchRepository, times(0)).save(sortie);
    }

    @Test
    @Transactional
    public void deleteSortie() throws Exception {
        // Initialize the database
        sortieRepository.saveAndFlush(sortie);

        int databaseSizeBeforeDelete = sortieRepository.findAll().size();

        // Get the sortie
        restSortieMockMvc.perform(delete("/api/sorties/{id}", sortie.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sortie> sortieList = sortieRepository.findAll();
        assertThat(sortieList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Sortie in Elasticsearch
        verify(mockSortieSearchRepository, times(1)).deleteById(sortie.getId());
    }

    @Test
    @Transactional
    public void searchSortie() throws Exception {
        // Initialize the database
        sortieRepository.saveAndFlush(sortie);
        when(mockSortieSearchRepository.search(queryStringQuery("id:" + sortie.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(sortie), PageRequest.of(0, 1), 1));
        // Search the sortie
        restSortieMockMvc.perform(get("/api/_search/sorties?query=id:" + sortie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sortie.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOperation").value(hasItem(DEFAULT_ID_OPERATION)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(DEFAULT_DATE_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroOperation").value(hasItem(DEFAULT_NUMERO_OPERATION)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sortie.class);
        Sortie sortie1 = new Sortie();
        sortie1.setId(1L);
        Sortie sortie2 = new Sortie();
        sortie2.setId(sortie1.getId());
        assertThat(sortie1).isEqualTo(sortie2);
        sortie2.setId(2L);
        assertThat(sortie1).isNotEqualTo(sortie2);
        sortie1.setId(null);
        assertThat(sortie1).isNotEqualTo(sortie2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SortieDTO.class);
        SortieDTO sortieDTO1 = new SortieDTO();
        sortieDTO1.setId(1L);
        SortieDTO sortieDTO2 = new SortieDTO();
        assertThat(sortieDTO1).isNotEqualTo(sortieDTO2);
        sortieDTO2.setId(sortieDTO1.getId());
        assertThat(sortieDTO1).isEqualTo(sortieDTO2);
        sortieDTO2.setId(2L);
        assertThat(sortieDTO1).isNotEqualTo(sortieDTO2);
        sortieDTO1.setId(null);
        assertThat(sortieDTO1).isNotEqualTo(sortieDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sortieMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sortieMapper.fromId(null)).isNull();
    }
}
