package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.JourFerier;
import ma.co.orimex.stock.repository.JourFerierRepository;
import ma.co.orimex.stock.repository.search.JourFerierSearchRepository;
import ma.co.orimex.stock.service.JourFerierService;
import ma.co.orimex.stock.service.dto.JourFerierDTO;
import ma.co.orimex.stock.service.mapper.JourFerierMapper;
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
 * Test class for the JourFerierResource REST controller.
 *
 * @see JourFerierResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class JourFerierResourceIntTest {

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final LocalDate DEFAULT_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FIN = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private JourFerierRepository jourFerierRepository;

    @Autowired
    private JourFerierMapper jourFerierMapper;

    @Autowired
    private JourFerierService jourFerierService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.JourFerierSearchRepositoryMockConfiguration
     */
    @Autowired
    private JourFerierSearchRepository mockJourFerierSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJourFerierMockMvc;

    private JourFerier jourFerier;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JourFerierResource jourFerierResource = new JourFerierResource(jourFerierService);
        this.restJourFerierMockMvc = MockMvcBuilders.standaloneSetup(jourFerierResource)
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
    public static JourFerier createEntity(EntityManager em) {
        JourFerier jourFerier = new JourFerier()
            .annee(DEFAULT_ANNEE)
            .debut(DEFAULT_DEBUT)
            .fin(DEFAULT_FIN);
        return jourFerier;
    }

    @Before
    public void initTest() {
        jourFerier = createEntity(em);
    }

    @Test
    @Transactional
    public void createJourFerier() throws Exception {
        int databaseSizeBeforeCreate = jourFerierRepository.findAll().size();

        // Create the JourFerier
        JourFerierDTO jourFerierDTO = jourFerierMapper.toDto(jourFerier);
        restJourFerierMockMvc.perform(post("/api/jour-feriers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jourFerierDTO)))
            .andExpect(status().isCreated());

        // Validate the JourFerier in the database
        List<JourFerier> jourFerierList = jourFerierRepository.findAll();
        assertThat(jourFerierList).hasSize(databaseSizeBeforeCreate + 1);
        JourFerier testJourFerier = jourFerierList.get(jourFerierList.size() - 1);
        assertThat(testJourFerier.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testJourFerier.getDebut()).isEqualTo(DEFAULT_DEBUT);
        assertThat(testJourFerier.getFin()).isEqualTo(DEFAULT_FIN);

        // Validate the JourFerier in Elasticsearch
        verify(mockJourFerierSearchRepository, times(1)).save(testJourFerier);
    }

    @Test
    @Transactional
    public void createJourFerierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jourFerierRepository.findAll().size();

        // Create the JourFerier with an existing ID
        jourFerier.setId(1L);
        JourFerierDTO jourFerierDTO = jourFerierMapper.toDto(jourFerier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJourFerierMockMvc.perform(post("/api/jour-feriers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jourFerierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JourFerier in the database
        List<JourFerier> jourFerierList = jourFerierRepository.findAll();
        assertThat(jourFerierList).hasSize(databaseSizeBeforeCreate);

        // Validate the JourFerier in Elasticsearch
        verify(mockJourFerierSearchRepository, times(0)).save(jourFerier);
    }

    @Test
    @Transactional
    public void getAllJourFeriers() throws Exception {
        // Initialize the database
        jourFerierRepository.saveAndFlush(jourFerier);

        // Get all the jourFerierList
        restJourFerierMockMvc.perform(get("/api/jour-feriers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jourFerier.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].debut").value(hasItem(DEFAULT_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].fin").value(hasItem(DEFAULT_FIN.toString())));
    }
    
    @Test
    @Transactional
    public void getJourFerier() throws Exception {
        // Initialize the database
        jourFerierRepository.saveAndFlush(jourFerier);

        // Get the jourFerier
        restJourFerierMockMvc.perform(get("/api/jour-feriers/{id}", jourFerier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jourFerier.getId().intValue()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.debut").value(DEFAULT_DEBUT.toString()))
            .andExpect(jsonPath("$.fin").value(DEFAULT_FIN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJourFerier() throws Exception {
        // Get the jourFerier
        restJourFerierMockMvc.perform(get("/api/jour-feriers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJourFerier() throws Exception {
        // Initialize the database
        jourFerierRepository.saveAndFlush(jourFerier);

        int databaseSizeBeforeUpdate = jourFerierRepository.findAll().size();

        // Update the jourFerier
        JourFerier updatedJourFerier = jourFerierRepository.findById(jourFerier.getId()).get();
        // Disconnect from session so that the updates on updatedJourFerier are not directly saved in db
        em.detach(updatedJourFerier);
        updatedJourFerier
            .annee(UPDATED_ANNEE)
            .debut(UPDATED_DEBUT)
            .fin(UPDATED_FIN);
        JourFerierDTO jourFerierDTO = jourFerierMapper.toDto(updatedJourFerier);

        restJourFerierMockMvc.perform(put("/api/jour-feriers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jourFerierDTO)))
            .andExpect(status().isOk());

        // Validate the JourFerier in the database
        List<JourFerier> jourFerierList = jourFerierRepository.findAll();
        assertThat(jourFerierList).hasSize(databaseSizeBeforeUpdate);
        JourFerier testJourFerier = jourFerierList.get(jourFerierList.size() - 1);
        assertThat(testJourFerier.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testJourFerier.getDebut()).isEqualTo(UPDATED_DEBUT);
        assertThat(testJourFerier.getFin()).isEqualTo(UPDATED_FIN);

        // Validate the JourFerier in Elasticsearch
        verify(mockJourFerierSearchRepository, times(1)).save(testJourFerier);
    }

    @Test
    @Transactional
    public void updateNonExistingJourFerier() throws Exception {
        int databaseSizeBeforeUpdate = jourFerierRepository.findAll().size();

        // Create the JourFerier
        JourFerierDTO jourFerierDTO = jourFerierMapper.toDto(jourFerier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJourFerierMockMvc.perform(put("/api/jour-feriers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jourFerierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JourFerier in the database
        List<JourFerier> jourFerierList = jourFerierRepository.findAll();
        assertThat(jourFerierList).hasSize(databaseSizeBeforeUpdate);

        // Validate the JourFerier in Elasticsearch
        verify(mockJourFerierSearchRepository, times(0)).save(jourFerier);
    }

    @Test
    @Transactional
    public void deleteJourFerier() throws Exception {
        // Initialize the database
        jourFerierRepository.saveAndFlush(jourFerier);

        int databaseSizeBeforeDelete = jourFerierRepository.findAll().size();

        // Get the jourFerier
        restJourFerierMockMvc.perform(delete("/api/jour-feriers/{id}", jourFerier.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<JourFerier> jourFerierList = jourFerierRepository.findAll();
        assertThat(jourFerierList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the JourFerier in Elasticsearch
        verify(mockJourFerierSearchRepository, times(1)).deleteById(jourFerier.getId());
    }

    @Test
    @Transactional
    public void searchJourFerier() throws Exception {
        // Initialize the database
        jourFerierRepository.saveAndFlush(jourFerier);
        when(mockJourFerierSearchRepository.search(queryStringQuery("id:" + jourFerier.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(jourFerier), PageRequest.of(0, 1), 1));
        // Search the jourFerier
        restJourFerierMockMvc.perform(get("/api/_search/jour-feriers?query=id:" + jourFerier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jourFerier.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].debut").value(hasItem(DEFAULT_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].fin").value(hasItem(DEFAULT_FIN.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JourFerier.class);
        JourFerier jourFerier1 = new JourFerier();
        jourFerier1.setId(1L);
        JourFerier jourFerier2 = new JourFerier();
        jourFerier2.setId(jourFerier1.getId());
        assertThat(jourFerier1).isEqualTo(jourFerier2);
        jourFerier2.setId(2L);
        assertThat(jourFerier1).isNotEqualTo(jourFerier2);
        jourFerier1.setId(null);
        assertThat(jourFerier1).isNotEqualTo(jourFerier2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JourFerierDTO.class);
        JourFerierDTO jourFerierDTO1 = new JourFerierDTO();
        jourFerierDTO1.setId(1L);
        JourFerierDTO jourFerierDTO2 = new JourFerierDTO();
        assertThat(jourFerierDTO1).isNotEqualTo(jourFerierDTO2);
        jourFerierDTO2.setId(jourFerierDTO1.getId());
        assertThat(jourFerierDTO1).isEqualTo(jourFerierDTO2);
        jourFerierDTO2.setId(2L);
        assertThat(jourFerierDTO1).isNotEqualTo(jourFerierDTO2);
        jourFerierDTO1.setId(null);
        assertThat(jourFerierDTO1).isNotEqualTo(jourFerierDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(jourFerierMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(jourFerierMapper.fromId(null)).isNull();
    }
}
