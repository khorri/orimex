package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.UtilisateurDepotPK;
import ma.co.orimex.stock.repository.UtilisateurDepotPKRepository;
import ma.co.orimex.stock.repository.search.UtilisateurDepotPKSearchRepository;
import ma.co.orimex.stock.service.UtilisateurDepotPKService;
import ma.co.orimex.stock.service.dto.UtilisateurDepotPKDTO;
import ma.co.orimex.stock.service.mapper.UtilisateurDepotPKMapper;
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
 * Test class for the UtilisateurDepotPKResource REST controller.
 *
 * @see UtilisateurDepotPKResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class UtilisateurDepotPKResourceIntTest {

    @Autowired
    private UtilisateurDepotPKRepository utilisateurDepotPKRepository;

    @Autowired
    private UtilisateurDepotPKMapper utilisateurDepotPKMapper;

    @Autowired
    private UtilisateurDepotPKService utilisateurDepotPKService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.UtilisateurDepotPKSearchRepositoryMockConfiguration
     */
    @Autowired
    private UtilisateurDepotPKSearchRepository mockUtilisateurDepotPKSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUtilisateurDepotPKMockMvc;

    private UtilisateurDepotPK utilisateurDepotPK;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UtilisateurDepotPKResource utilisateurDepotPKResource = new UtilisateurDepotPKResource(utilisateurDepotPKService);
        this.restUtilisateurDepotPKMockMvc = MockMvcBuilders.standaloneSetup(utilisateurDepotPKResource)
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
    public static UtilisateurDepotPK createEntity(EntityManager em) {
        UtilisateurDepotPK utilisateurDepotPK = new UtilisateurDepotPK();
        return utilisateurDepotPK;
    }

    @Before
    public void initTest() {
        utilisateurDepotPK = createEntity(em);
    }

    @Test
    @Transactional
    public void createUtilisateurDepotPK() throws Exception {
        int databaseSizeBeforeCreate = utilisateurDepotPKRepository.findAll().size();

        // Create the UtilisateurDepotPK
        UtilisateurDepotPKDTO utilisateurDepotPKDTO = utilisateurDepotPKMapper.toDto(utilisateurDepotPK);
        restUtilisateurDepotPKMockMvc.perform(post("/api/utilisateur-depot-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDepotPKDTO)))
            .andExpect(status().isCreated());

        // Validate the UtilisateurDepotPK in the database
        List<UtilisateurDepotPK> utilisateurDepotPKList = utilisateurDepotPKRepository.findAll();
        assertThat(utilisateurDepotPKList).hasSize(databaseSizeBeforeCreate + 1);
        UtilisateurDepotPK testUtilisateurDepotPK = utilisateurDepotPKList.get(utilisateurDepotPKList.size() - 1);

        // Validate the UtilisateurDepotPK in Elasticsearch
        verify(mockUtilisateurDepotPKSearchRepository, times(1)).save(testUtilisateurDepotPK);
    }

    @Test
    @Transactional
    public void createUtilisateurDepotPKWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = utilisateurDepotPKRepository.findAll().size();

        // Create the UtilisateurDepotPK with an existing ID
        utilisateurDepotPK.setId(1L);
        UtilisateurDepotPKDTO utilisateurDepotPKDTO = utilisateurDepotPKMapper.toDto(utilisateurDepotPK);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUtilisateurDepotPKMockMvc.perform(post("/api/utilisateur-depot-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDepotPKDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UtilisateurDepotPK in the database
        List<UtilisateurDepotPK> utilisateurDepotPKList = utilisateurDepotPKRepository.findAll();
        assertThat(utilisateurDepotPKList).hasSize(databaseSizeBeforeCreate);

        // Validate the UtilisateurDepotPK in Elasticsearch
        verify(mockUtilisateurDepotPKSearchRepository, times(0)).save(utilisateurDepotPK);
    }

    @Test
    @Transactional
    public void getAllUtilisateurDepotPKS() throws Exception {
        // Initialize the database
        utilisateurDepotPKRepository.saveAndFlush(utilisateurDepotPK);

        // Get all the utilisateurDepotPKList
        restUtilisateurDepotPKMockMvc.perform(get("/api/utilisateur-depot-pks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateurDepotPK.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUtilisateurDepotPK() throws Exception {
        // Initialize the database
        utilisateurDepotPKRepository.saveAndFlush(utilisateurDepotPK);

        // Get the utilisateurDepotPK
        restUtilisateurDepotPKMockMvc.perform(get("/api/utilisateur-depot-pks/{id}", utilisateurDepotPK.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(utilisateurDepotPK.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUtilisateurDepotPK() throws Exception {
        // Get the utilisateurDepotPK
        restUtilisateurDepotPKMockMvc.perform(get("/api/utilisateur-depot-pks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUtilisateurDepotPK() throws Exception {
        // Initialize the database
        utilisateurDepotPKRepository.saveAndFlush(utilisateurDepotPK);

        int databaseSizeBeforeUpdate = utilisateurDepotPKRepository.findAll().size();

        // Update the utilisateurDepotPK
        UtilisateurDepotPK updatedUtilisateurDepotPK = utilisateurDepotPKRepository.findById(utilisateurDepotPK.getId()).get();
        // Disconnect from session so that the updates on updatedUtilisateurDepotPK are not directly saved in db
        em.detach(updatedUtilisateurDepotPK);
        UtilisateurDepotPKDTO utilisateurDepotPKDTO = utilisateurDepotPKMapper.toDto(updatedUtilisateurDepotPK);

        restUtilisateurDepotPKMockMvc.perform(put("/api/utilisateur-depot-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDepotPKDTO)))
            .andExpect(status().isOk());

        // Validate the UtilisateurDepotPK in the database
        List<UtilisateurDepotPK> utilisateurDepotPKList = utilisateurDepotPKRepository.findAll();
        assertThat(utilisateurDepotPKList).hasSize(databaseSizeBeforeUpdate);
        UtilisateurDepotPK testUtilisateurDepotPK = utilisateurDepotPKList.get(utilisateurDepotPKList.size() - 1);

        // Validate the UtilisateurDepotPK in Elasticsearch
        verify(mockUtilisateurDepotPKSearchRepository, times(1)).save(testUtilisateurDepotPK);
    }

    @Test
    @Transactional
    public void updateNonExistingUtilisateurDepotPK() throws Exception {
        int databaseSizeBeforeUpdate = utilisateurDepotPKRepository.findAll().size();

        // Create the UtilisateurDepotPK
        UtilisateurDepotPKDTO utilisateurDepotPKDTO = utilisateurDepotPKMapper.toDto(utilisateurDepotPK);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUtilisateurDepotPKMockMvc.perform(put("/api/utilisateur-depot-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDepotPKDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UtilisateurDepotPK in the database
        List<UtilisateurDepotPK> utilisateurDepotPKList = utilisateurDepotPKRepository.findAll();
        assertThat(utilisateurDepotPKList).hasSize(databaseSizeBeforeUpdate);

        // Validate the UtilisateurDepotPK in Elasticsearch
        verify(mockUtilisateurDepotPKSearchRepository, times(0)).save(utilisateurDepotPK);
    }

    @Test
    @Transactional
    public void deleteUtilisateurDepotPK() throws Exception {
        // Initialize the database
        utilisateurDepotPKRepository.saveAndFlush(utilisateurDepotPK);

        int databaseSizeBeforeDelete = utilisateurDepotPKRepository.findAll().size();

        // Get the utilisateurDepotPK
        restUtilisateurDepotPKMockMvc.perform(delete("/api/utilisateur-depot-pks/{id}", utilisateurDepotPK.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UtilisateurDepotPK> utilisateurDepotPKList = utilisateurDepotPKRepository.findAll();
        assertThat(utilisateurDepotPKList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the UtilisateurDepotPK in Elasticsearch
        verify(mockUtilisateurDepotPKSearchRepository, times(1)).deleteById(utilisateurDepotPK.getId());
    }

    @Test
    @Transactional
    public void searchUtilisateurDepotPK() throws Exception {
        // Initialize the database
        utilisateurDepotPKRepository.saveAndFlush(utilisateurDepotPK);
        when(mockUtilisateurDepotPKSearchRepository.search(queryStringQuery("id:" + utilisateurDepotPK.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(utilisateurDepotPK), PageRequest.of(0, 1), 1));
        // Search the utilisateurDepotPK
        restUtilisateurDepotPKMockMvc.perform(get("/api/_search/utilisateur-depot-pks?query=id:" + utilisateurDepotPK.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateurDepotPK.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisateurDepotPK.class);
        UtilisateurDepotPK utilisateurDepotPK1 = new UtilisateurDepotPK();
        utilisateurDepotPK1.setId(1L);
        UtilisateurDepotPK utilisateurDepotPK2 = new UtilisateurDepotPK();
        utilisateurDepotPK2.setId(utilisateurDepotPK1.getId());
        assertThat(utilisateurDepotPK1).isEqualTo(utilisateurDepotPK2);
        utilisateurDepotPK2.setId(2L);
        assertThat(utilisateurDepotPK1).isNotEqualTo(utilisateurDepotPK2);
        utilisateurDepotPK1.setId(null);
        assertThat(utilisateurDepotPK1).isNotEqualTo(utilisateurDepotPK2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisateurDepotPKDTO.class);
        UtilisateurDepotPKDTO utilisateurDepotPKDTO1 = new UtilisateurDepotPKDTO();
        utilisateurDepotPKDTO1.setId(1L);
        UtilisateurDepotPKDTO utilisateurDepotPKDTO2 = new UtilisateurDepotPKDTO();
        assertThat(utilisateurDepotPKDTO1).isNotEqualTo(utilisateurDepotPKDTO2);
        utilisateurDepotPKDTO2.setId(utilisateurDepotPKDTO1.getId());
        assertThat(utilisateurDepotPKDTO1).isEqualTo(utilisateurDepotPKDTO2);
        utilisateurDepotPKDTO2.setId(2L);
        assertThat(utilisateurDepotPKDTO1).isNotEqualTo(utilisateurDepotPKDTO2);
        utilisateurDepotPKDTO1.setId(null);
        assertThat(utilisateurDepotPKDTO1).isNotEqualTo(utilisateurDepotPKDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(utilisateurDepotPKMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(utilisateurDepotPKMapper.fromId(null)).isNull();
    }
}
