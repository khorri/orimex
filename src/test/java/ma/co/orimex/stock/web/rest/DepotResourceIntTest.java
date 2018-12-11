package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Depot;
import ma.co.orimex.stock.repository.DepotRepository;
import ma.co.orimex.stock.repository.search.DepotSearchRepository;
import ma.co.orimex.stock.service.DepotService;
import ma.co.orimex.stock.service.dto.DepotDTO;
import ma.co.orimex.stock.service.mapper.DepotMapper;
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
 * Test class for the DepotResource REST controller.
 *
 * @see DepotResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class DepotResourceIntTest {

    private static final Integer DEFAULT_ID_DEPOT = 1;
    private static final Integer UPDATED_ID_DEPOT = 2;

    private static final String DEFAULT_REFERENCE_DEPOT = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_DEPOT = "BBBBBBBBBB";

    private static final String DEFAULT_UTILISATEUR_DEPOTS = "AAAAAAAAAA";
    private static final String UPDATED_UTILISATEUR_DEPOTS = "BBBBBBBBBB";

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private DepotMapper depotMapper;

    @Autowired
    private DepotService depotService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.DepotSearchRepositoryMockConfiguration
     */
    @Autowired
    private DepotSearchRepository mockDepotSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDepotMockMvc;

    private Depot depot;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DepotResource depotResource = new DepotResource(depotService);
        this.restDepotMockMvc = MockMvcBuilders.standaloneSetup(depotResource)
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
    public static Depot createEntity(EntityManager em) {
        Depot depot = new Depot()
            .idDepot(DEFAULT_ID_DEPOT)
            .referenceDepot(DEFAULT_REFERENCE_DEPOT)
            .utilisateurDepots(DEFAULT_UTILISATEUR_DEPOTS);
        return depot;
    }

    @Before
    public void initTest() {
        depot = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepot() throws Exception {
        int databaseSizeBeforeCreate = depotRepository.findAll().size();

        // Create the Depot
        DepotDTO depotDTO = depotMapper.toDto(depot);
        restDepotMockMvc.perform(post("/api/depots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depotDTO)))
            .andExpect(status().isCreated());

        // Validate the Depot in the database
        List<Depot> depotList = depotRepository.findAll();
        assertThat(depotList).hasSize(databaseSizeBeforeCreate + 1);
        Depot testDepot = depotList.get(depotList.size() - 1);
        assertThat(testDepot.getIdDepot()).isEqualTo(DEFAULT_ID_DEPOT);
        assertThat(testDepot.getReferenceDepot()).isEqualTo(DEFAULT_REFERENCE_DEPOT);
        assertThat(testDepot.getUtilisateurDepots()).isEqualTo(DEFAULT_UTILISATEUR_DEPOTS);

        // Validate the Depot in Elasticsearch
        verify(mockDepotSearchRepository, times(1)).save(testDepot);
    }

    @Test
    @Transactional
    public void createDepotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = depotRepository.findAll().size();

        // Create the Depot with an existing ID
        depot.setId(1L);
        DepotDTO depotDTO = depotMapper.toDto(depot);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepotMockMvc.perform(post("/api/depots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Depot in the database
        List<Depot> depotList = depotRepository.findAll();
        assertThat(depotList).hasSize(databaseSizeBeforeCreate);

        // Validate the Depot in Elasticsearch
        verify(mockDepotSearchRepository, times(0)).save(depot);
    }

    @Test
    @Transactional
    public void getAllDepots() throws Exception {
        // Initialize the database
        depotRepository.saveAndFlush(depot);

        // Get all the depotList
        restDepotMockMvc.perform(get("/api/depots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(depot.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDepot").value(hasItem(DEFAULT_ID_DEPOT)))
            .andExpect(jsonPath("$.[*].referenceDepot").value(hasItem(DEFAULT_REFERENCE_DEPOT.toString())))
            .andExpect(jsonPath("$.[*].utilisateurDepots").value(hasItem(DEFAULT_UTILISATEUR_DEPOTS.toString())));
    }
    
    @Test
    @Transactional
    public void getDepot() throws Exception {
        // Initialize the database
        depotRepository.saveAndFlush(depot);

        // Get the depot
        restDepotMockMvc.perform(get("/api/depots/{id}", depot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(depot.getId().intValue()))
            .andExpect(jsonPath("$.idDepot").value(DEFAULT_ID_DEPOT))
            .andExpect(jsonPath("$.referenceDepot").value(DEFAULT_REFERENCE_DEPOT.toString()))
            .andExpect(jsonPath("$.utilisateurDepots").value(DEFAULT_UTILISATEUR_DEPOTS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDepot() throws Exception {
        // Get the depot
        restDepotMockMvc.perform(get("/api/depots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepot() throws Exception {
        // Initialize the database
        depotRepository.saveAndFlush(depot);

        int databaseSizeBeforeUpdate = depotRepository.findAll().size();

        // Update the depot
        Depot updatedDepot = depotRepository.findById(depot.getId()).get();
        // Disconnect from session so that the updates on updatedDepot are not directly saved in db
        em.detach(updatedDepot);
        updatedDepot
            .idDepot(UPDATED_ID_DEPOT)
            .referenceDepot(UPDATED_REFERENCE_DEPOT)
            .utilisateurDepots(UPDATED_UTILISATEUR_DEPOTS);
        DepotDTO depotDTO = depotMapper.toDto(updatedDepot);

        restDepotMockMvc.perform(put("/api/depots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depotDTO)))
            .andExpect(status().isOk());

        // Validate the Depot in the database
        List<Depot> depotList = depotRepository.findAll();
        assertThat(depotList).hasSize(databaseSizeBeforeUpdate);
        Depot testDepot = depotList.get(depotList.size() - 1);
        assertThat(testDepot.getIdDepot()).isEqualTo(UPDATED_ID_DEPOT);
        assertThat(testDepot.getReferenceDepot()).isEqualTo(UPDATED_REFERENCE_DEPOT);
        assertThat(testDepot.getUtilisateurDepots()).isEqualTo(UPDATED_UTILISATEUR_DEPOTS);

        // Validate the Depot in Elasticsearch
        verify(mockDepotSearchRepository, times(1)).save(testDepot);
    }

    @Test
    @Transactional
    public void updateNonExistingDepot() throws Exception {
        int databaseSizeBeforeUpdate = depotRepository.findAll().size();

        // Create the Depot
        DepotDTO depotDTO = depotMapper.toDto(depot);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepotMockMvc.perform(put("/api/depots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Depot in the database
        List<Depot> depotList = depotRepository.findAll();
        assertThat(depotList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Depot in Elasticsearch
        verify(mockDepotSearchRepository, times(0)).save(depot);
    }

    @Test
    @Transactional
    public void deleteDepot() throws Exception {
        // Initialize the database
        depotRepository.saveAndFlush(depot);

        int databaseSizeBeforeDelete = depotRepository.findAll().size();

        // Get the depot
        restDepotMockMvc.perform(delete("/api/depots/{id}", depot.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Depot> depotList = depotRepository.findAll();
        assertThat(depotList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Depot in Elasticsearch
        verify(mockDepotSearchRepository, times(1)).deleteById(depot.getId());
    }

    @Test
    @Transactional
    public void searchDepot() throws Exception {
        // Initialize the database
        depotRepository.saveAndFlush(depot);
        when(mockDepotSearchRepository.search(queryStringQuery("id:" + depot.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(depot), PageRequest.of(0, 1), 1));
        // Search the depot
        restDepotMockMvc.perform(get("/api/_search/depots?query=id:" + depot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(depot.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDepot").value(hasItem(DEFAULT_ID_DEPOT)))
            .andExpect(jsonPath("$.[*].referenceDepot").value(hasItem(DEFAULT_REFERENCE_DEPOT)))
            .andExpect(jsonPath("$.[*].utilisateurDepots").value(hasItem(DEFAULT_UTILISATEUR_DEPOTS)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Depot.class);
        Depot depot1 = new Depot();
        depot1.setId(1L);
        Depot depot2 = new Depot();
        depot2.setId(depot1.getId());
        assertThat(depot1).isEqualTo(depot2);
        depot2.setId(2L);
        assertThat(depot1).isNotEqualTo(depot2);
        depot1.setId(null);
        assertThat(depot1).isNotEqualTo(depot2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepotDTO.class);
        DepotDTO depotDTO1 = new DepotDTO();
        depotDTO1.setId(1L);
        DepotDTO depotDTO2 = new DepotDTO();
        assertThat(depotDTO1).isNotEqualTo(depotDTO2);
        depotDTO2.setId(depotDTO1.getId());
        assertThat(depotDTO1).isEqualTo(depotDTO2);
        depotDTO2.setId(2L);
        assertThat(depotDTO1).isNotEqualTo(depotDTO2);
        depotDTO1.setId(null);
        assertThat(depotDTO1).isNotEqualTo(depotDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(depotMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(depotMapper.fromId(null)).isNull();
    }
}
