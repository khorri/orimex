package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.UtilisateurDepot;
import ma.co.orimex.stock.repository.UtilisateurDepotRepository;
import ma.co.orimex.stock.repository.search.UtilisateurDepotSearchRepository;
import ma.co.orimex.stock.service.UtilisateurDepotService;
import ma.co.orimex.stock.service.dto.UtilisateurDepotDTO;
import ma.co.orimex.stock.service.mapper.UtilisateurDepotMapper;
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
 * Test class for the UtilisateurDepotResource REST controller.
 *
 * @see UtilisateurDepotResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class UtilisateurDepotResourceIntTest {

    @Autowired
    private UtilisateurDepotRepository utilisateurDepotRepository;

    @Autowired
    private UtilisateurDepotMapper utilisateurDepotMapper;

    @Autowired
    private UtilisateurDepotService utilisateurDepotService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.UtilisateurDepotSearchRepositoryMockConfiguration
     */
    @Autowired
    private UtilisateurDepotSearchRepository mockUtilisateurDepotSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUtilisateurDepotMockMvc;

    private UtilisateurDepot utilisateurDepot;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UtilisateurDepotResource utilisateurDepotResource = new UtilisateurDepotResource(utilisateurDepotService);
        this.restUtilisateurDepotMockMvc = MockMvcBuilders.standaloneSetup(utilisateurDepotResource)
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
    public static UtilisateurDepot createEntity(EntityManager em) {
        UtilisateurDepot utilisateurDepot = new UtilisateurDepot();
        return utilisateurDepot;
    }

    @Before
    public void initTest() {
        utilisateurDepot = createEntity(em);
    }

    @Test
    @Transactional
    public void createUtilisateurDepot() throws Exception {
        int databaseSizeBeforeCreate = utilisateurDepotRepository.findAll().size();

        // Create the UtilisateurDepot
        UtilisateurDepotDTO utilisateurDepotDTO = utilisateurDepotMapper.toDto(utilisateurDepot);
        restUtilisateurDepotMockMvc.perform(post("/api/utilisateur-depots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDepotDTO)))
            .andExpect(status().isCreated());

        // Validate the UtilisateurDepot in the database
        List<UtilisateurDepot> utilisateurDepotList = utilisateurDepotRepository.findAll();
        assertThat(utilisateurDepotList).hasSize(databaseSizeBeforeCreate + 1);
        UtilisateurDepot testUtilisateurDepot = utilisateurDepotList.get(utilisateurDepotList.size() - 1);

        // Validate the UtilisateurDepot in Elasticsearch
        verify(mockUtilisateurDepotSearchRepository, times(1)).save(testUtilisateurDepot);
    }

    @Test
    @Transactional
    public void createUtilisateurDepotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = utilisateurDepotRepository.findAll().size();

        // Create the UtilisateurDepot with an existing ID
        utilisateurDepot.setId(1L);
        UtilisateurDepotDTO utilisateurDepotDTO = utilisateurDepotMapper.toDto(utilisateurDepot);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUtilisateurDepotMockMvc.perform(post("/api/utilisateur-depots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDepotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UtilisateurDepot in the database
        List<UtilisateurDepot> utilisateurDepotList = utilisateurDepotRepository.findAll();
        assertThat(utilisateurDepotList).hasSize(databaseSizeBeforeCreate);

        // Validate the UtilisateurDepot in Elasticsearch
        verify(mockUtilisateurDepotSearchRepository, times(0)).save(utilisateurDepot);
    }

    @Test
    @Transactional
    public void getAllUtilisateurDepots() throws Exception {
        // Initialize the database
        utilisateurDepotRepository.saveAndFlush(utilisateurDepot);

        // Get all the utilisateurDepotList
        restUtilisateurDepotMockMvc.perform(get("/api/utilisateur-depots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateurDepot.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUtilisateurDepot() throws Exception {
        // Initialize the database
        utilisateurDepotRepository.saveAndFlush(utilisateurDepot);

        // Get the utilisateurDepot
        restUtilisateurDepotMockMvc.perform(get("/api/utilisateur-depots/{id}", utilisateurDepot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(utilisateurDepot.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUtilisateurDepot() throws Exception {
        // Get the utilisateurDepot
        restUtilisateurDepotMockMvc.perform(get("/api/utilisateur-depots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUtilisateurDepot() throws Exception {
        // Initialize the database
        utilisateurDepotRepository.saveAndFlush(utilisateurDepot);

        int databaseSizeBeforeUpdate = utilisateurDepotRepository.findAll().size();

        // Update the utilisateurDepot
        UtilisateurDepot updatedUtilisateurDepot = utilisateurDepotRepository.findById(utilisateurDepot.getId()).get();
        // Disconnect from session so that the updates on updatedUtilisateurDepot are not directly saved in db
        em.detach(updatedUtilisateurDepot);
        UtilisateurDepotDTO utilisateurDepotDTO = utilisateurDepotMapper.toDto(updatedUtilisateurDepot);

        restUtilisateurDepotMockMvc.perform(put("/api/utilisateur-depots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDepotDTO)))
            .andExpect(status().isOk());

        // Validate the UtilisateurDepot in the database
        List<UtilisateurDepot> utilisateurDepotList = utilisateurDepotRepository.findAll();
        assertThat(utilisateurDepotList).hasSize(databaseSizeBeforeUpdate);
        UtilisateurDepot testUtilisateurDepot = utilisateurDepotList.get(utilisateurDepotList.size() - 1);

        // Validate the UtilisateurDepot in Elasticsearch
        verify(mockUtilisateurDepotSearchRepository, times(1)).save(testUtilisateurDepot);
    }

    @Test
    @Transactional
    public void updateNonExistingUtilisateurDepot() throws Exception {
        int databaseSizeBeforeUpdate = utilisateurDepotRepository.findAll().size();

        // Create the UtilisateurDepot
        UtilisateurDepotDTO utilisateurDepotDTO = utilisateurDepotMapper.toDto(utilisateurDepot);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUtilisateurDepotMockMvc.perform(put("/api/utilisateur-depots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDepotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UtilisateurDepot in the database
        List<UtilisateurDepot> utilisateurDepotList = utilisateurDepotRepository.findAll();
        assertThat(utilisateurDepotList).hasSize(databaseSizeBeforeUpdate);

        // Validate the UtilisateurDepot in Elasticsearch
        verify(mockUtilisateurDepotSearchRepository, times(0)).save(utilisateurDepot);
    }

    @Test
    @Transactional
    public void deleteUtilisateurDepot() throws Exception {
        // Initialize the database
        utilisateurDepotRepository.saveAndFlush(utilisateurDepot);

        int databaseSizeBeforeDelete = utilisateurDepotRepository.findAll().size();

        // Get the utilisateurDepot
        restUtilisateurDepotMockMvc.perform(delete("/api/utilisateur-depots/{id}", utilisateurDepot.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UtilisateurDepot> utilisateurDepotList = utilisateurDepotRepository.findAll();
        assertThat(utilisateurDepotList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the UtilisateurDepot in Elasticsearch
        verify(mockUtilisateurDepotSearchRepository, times(1)).deleteById(utilisateurDepot.getId());
    }

    @Test
    @Transactional
    public void searchUtilisateurDepot() throws Exception {
        // Initialize the database
        utilisateurDepotRepository.saveAndFlush(utilisateurDepot);
        when(mockUtilisateurDepotSearchRepository.search(queryStringQuery("id:" + utilisateurDepot.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(utilisateurDepot), PageRequest.of(0, 1), 1));
        // Search the utilisateurDepot
        restUtilisateurDepotMockMvc.perform(get("/api/_search/utilisateur-depots?query=id:" + utilisateurDepot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateurDepot.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisateurDepot.class);
        UtilisateurDepot utilisateurDepot1 = new UtilisateurDepot();
        utilisateurDepot1.setId(1L);
        UtilisateurDepot utilisateurDepot2 = new UtilisateurDepot();
        utilisateurDepot2.setId(utilisateurDepot1.getId());
        assertThat(utilisateurDepot1).isEqualTo(utilisateurDepot2);
        utilisateurDepot2.setId(2L);
        assertThat(utilisateurDepot1).isNotEqualTo(utilisateurDepot2);
        utilisateurDepot1.setId(null);
        assertThat(utilisateurDepot1).isNotEqualTo(utilisateurDepot2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisateurDepotDTO.class);
        UtilisateurDepotDTO utilisateurDepotDTO1 = new UtilisateurDepotDTO();
        utilisateurDepotDTO1.setId(1L);
        UtilisateurDepotDTO utilisateurDepotDTO2 = new UtilisateurDepotDTO();
        assertThat(utilisateurDepotDTO1).isNotEqualTo(utilisateurDepotDTO2);
        utilisateurDepotDTO2.setId(utilisateurDepotDTO1.getId());
        assertThat(utilisateurDepotDTO1).isEqualTo(utilisateurDepotDTO2);
        utilisateurDepotDTO2.setId(2L);
        assertThat(utilisateurDepotDTO1).isNotEqualTo(utilisateurDepotDTO2);
        utilisateurDepotDTO1.setId(null);
        assertThat(utilisateurDepotDTO1).isNotEqualTo(utilisateurDepotDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(utilisateurDepotMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(utilisateurDepotMapper.fromId(null)).isNull();
    }
}
