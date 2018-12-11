package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.UtilisateurProfilPK;
import ma.co.orimex.stock.repository.UtilisateurProfilPKRepository;
import ma.co.orimex.stock.repository.search.UtilisateurProfilPKSearchRepository;
import ma.co.orimex.stock.service.UtilisateurProfilPKService;
import ma.co.orimex.stock.service.dto.UtilisateurProfilPKDTO;
import ma.co.orimex.stock.service.mapper.UtilisateurProfilPKMapper;
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
 * Test class for the UtilisateurProfilPKResource REST controller.
 *
 * @see UtilisateurProfilPKResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class UtilisateurProfilPKResourceIntTest {

    @Autowired
    private UtilisateurProfilPKRepository utilisateurProfilPKRepository;

    @Autowired
    private UtilisateurProfilPKMapper utilisateurProfilPKMapper;

    @Autowired
    private UtilisateurProfilPKService utilisateurProfilPKService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.UtilisateurProfilPKSearchRepositoryMockConfiguration
     */
    @Autowired
    private UtilisateurProfilPKSearchRepository mockUtilisateurProfilPKSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUtilisateurProfilPKMockMvc;

    private UtilisateurProfilPK utilisateurProfilPK;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UtilisateurProfilPKResource utilisateurProfilPKResource = new UtilisateurProfilPKResource(utilisateurProfilPKService);
        this.restUtilisateurProfilPKMockMvc = MockMvcBuilders.standaloneSetup(utilisateurProfilPKResource)
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
    public static UtilisateurProfilPK createEntity(EntityManager em) {
        UtilisateurProfilPK utilisateurProfilPK = new UtilisateurProfilPK();
        return utilisateurProfilPK;
    }

    @Before
    public void initTest() {
        utilisateurProfilPK = createEntity(em);
    }

    @Test
    @Transactional
    public void createUtilisateurProfilPK() throws Exception {
        int databaseSizeBeforeCreate = utilisateurProfilPKRepository.findAll().size();

        // Create the UtilisateurProfilPK
        UtilisateurProfilPKDTO utilisateurProfilPKDTO = utilisateurProfilPKMapper.toDto(utilisateurProfilPK);
        restUtilisateurProfilPKMockMvc.perform(post("/api/utilisateur-profil-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurProfilPKDTO)))
            .andExpect(status().isCreated());

        // Validate the UtilisateurProfilPK in the database
        List<UtilisateurProfilPK> utilisateurProfilPKList = utilisateurProfilPKRepository.findAll();
        assertThat(utilisateurProfilPKList).hasSize(databaseSizeBeforeCreate + 1);
        UtilisateurProfilPK testUtilisateurProfilPK = utilisateurProfilPKList.get(utilisateurProfilPKList.size() - 1);

        // Validate the UtilisateurProfilPK in Elasticsearch
        verify(mockUtilisateurProfilPKSearchRepository, times(1)).save(testUtilisateurProfilPK);
    }

    @Test
    @Transactional
    public void createUtilisateurProfilPKWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = utilisateurProfilPKRepository.findAll().size();

        // Create the UtilisateurProfilPK with an existing ID
        utilisateurProfilPK.setId(1L);
        UtilisateurProfilPKDTO utilisateurProfilPKDTO = utilisateurProfilPKMapper.toDto(utilisateurProfilPK);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUtilisateurProfilPKMockMvc.perform(post("/api/utilisateur-profil-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurProfilPKDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UtilisateurProfilPK in the database
        List<UtilisateurProfilPK> utilisateurProfilPKList = utilisateurProfilPKRepository.findAll();
        assertThat(utilisateurProfilPKList).hasSize(databaseSizeBeforeCreate);

        // Validate the UtilisateurProfilPK in Elasticsearch
        verify(mockUtilisateurProfilPKSearchRepository, times(0)).save(utilisateurProfilPK);
    }

    @Test
    @Transactional
    public void getAllUtilisateurProfilPKS() throws Exception {
        // Initialize the database
        utilisateurProfilPKRepository.saveAndFlush(utilisateurProfilPK);

        // Get all the utilisateurProfilPKList
        restUtilisateurProfilPKMockMvc.perform(get("/api/utilisateur-profil-pks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateurProfilPK.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUtilisateurProfilPK() throws Exception {
        // Initialize the database
        utilisateurProfilPKRepository.saveAndFlush(utilisateurProfilPK);

        // Get the utilisateurProfilPK
        restUtilisateurProfilPKMockMvc.perform(get("/api/utilisateur-profil-pks/{id}", utilisateurProfilPK.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(utilisateurProfilPK.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUtilisateurProfilPK() throws Exception {
        // Get the utilisateurProfilPK
        restUtilisateurProfilPKMockMvc.perform(get("/api/utilisateur-profil-pks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUtilisateurProfilPK() throws Exception {
        // Initialize the database
        utilisateurProfilPKRepository.saveAndFlush(utilisateurProfilPK);

        int databaseSizeBeforeUpdate = utilisateurProfilPKRepository.findAll().size();

        // Update the utilisateurProfilPK
        UtilisateurProfilPK updatedUtilisateurProfilPK = utilisateurProfilPKRepository.findById(utilisateurProfilPK.getId()).get();
        // Disconnect from session so that the updates on updatedUtilisateurProfilPK are not directly saved in db
        em.detach(updatedUtilisateurProfilPK);
        UtilisateurProfilPKDTO utilisateurProfilPKDTO = utilisateurProfilPKMapper.toDto(updatedUtilisateurProfilPK);

        restUtilisateurProfilPKMockMvc.perform(put("/api/utilisateur-profil-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurProfilPKDTO)))
            .andExpect(status().isOk());

        // Validate the UtilisateurProfilPK in the database
        List<UtilisateurProfilPK> utilisateurProfilPKList = utilisateurProfilPKRepository.findAll();
        assertThat(utilisateurProfilPKList).hasSize(databaseSizeBeforeUpdate);
        UtilisateurProfilPK testUtilisateurProfilPK = utilisateurProfilPKList.get(utilisateurProfilPKList.size() - 1);

        // Validate the UtilisateurProfilPK in Elasticsearch
        verify(mockUtilisateurProfilPKSearchRepository, times(1)).save(testUtilisateurProfilPK);
    }

    @Test
    @Transactional
    public void updateNonExistingUtilisateurProfilPK() throws Exception {
        int databaseSizeBeforeUpdate = utilisateurProfilPKRepository.findAll().size();

        // Create the UtilisateurProfilPK
        UtilisateurProfilPKDTO utilisateurProfilPKDTO = utilisateurProfilPKMapper.toDto(utilisateurProfilPK);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUtilisateurProfilPKMockMvc.perform(put("/api/utilisateur-profil-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurProfilPKDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UtilisateurProfilPK in the database
        List<UtilisateurProfilPK> utilisateurProfilPKList = utilisateurProfilPKRepository.findAll();
        assertThat(utilisateurProfilPKList).hasSize(databaseSizeBeforeUpdate);

        // Validate the UtilisateurProfilPK in Elasticsearch
        verify(mockUtilisateurProfilPKSearchRepository, times(0)).save(utilisateurProfilPK);
    }

    @Test
    @Transactional
    public void deleteUtilisateurProfilPK() throws Exception {
        // Initialize the database
        utilisateurProfilPKRepository.saveAndFlush(utilisateurProfilPK);

        int databaseSizeBeforeDelete = utilisateurProfilPKRepository.findAll().size();

        // Get the utilisateurProfilPK
        restUtilisateurProfilPKMockMvc.perform(delete("/api/utilisateur-profil-pks/{id}", utilisateurProfilPK.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UtilisateurProfilPK> utilisateurProfilPKList = utilisateurProfilPKRepository.findAll();
        assertThat(utilisateurProfilPKList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the UtilisateurProfilPK in Elasticsearch
        verify(mockUtilisateurProfilPKSearchRepository, times(1)).deleteById(utilisateurProfilPK.getId());
    }

    @Test
    @Transactional
    public void searchUtilisateurProfilPK() throws Exception {
        // Initialize the database
        utilisateurProfilPKRepository.saveAndFlush(utilisateurProfilPK);
        when(mockUtilisateurProfilPKSearchRepository.search(queryStringQuery("id:" + utilisateurProfilPK.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(utilisateurProfilPK), PageRequest.of(0, 1), 1));
        // Search the utilisateurProfilPK
        restUtilisateurProfilPKMockMvc.perform(get("/api/_search/utilisateur-profil-pks?query=id:" + utilisateurProfilPK.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateurProfilPK.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisateurProfilPK.class);
        UtilisateurProfilPK utilisateurProfilPK1 = new UtilisateurProfilPK();
        utilisateurProfilPK1.setId(1L);
        UtilisateurProfilPK utilisateurProfilPK2 = new UtilisateurProfilPK();
        utilisateurProfilPK2.setId(utilisateurProfilPK1.getId());
        assertThat(utilisateurProfilPK1).isEqualTo(utilisateurProfilPK2);
        utilisateurProfilPK2.setId(2L);
        assertThat(utilisateurProfilPK1).isNotEqualTo(utilisateurProfilPK2);
        utilisateurProfilPK1.setId(null);
        assertThat(utilisateurProfilPK1).isNotEqualTo(utilisateurProfilPK2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisateurProfilPKDTO.class);
        UtilisateurProfilPKDTO utilisateurProfilPKDTO1 = new UtilisateurProfilPKDTO();
        utilisateurProfilPKDTO1.setId(1L);
        UtilisateurProfilPKDTO utilisateurProfilPKDTO2 = new UtilisateurProfilPKDTO();
        assertThat(utilisateurProfilPKDTO1).isNotEqualTo(utilisateurProfilPKDTO2);
        utilisateurProfilPKDTO2.setId(utilisateurProfilPKDTO1.getId());
        assertThat(utilisateurProfilPKDTO1).isEqualTo(utilisateurProfilPKDTO2);
        utilisateurProfilPKDTO2.setId(2L);
        assertThat(utilisateurProfilPKDTO1).isNotEqualTo(utilisateurProfilPKDTO2);
        utilisateurProfilPKDTO1.setId(null);
        assertThat(utilisateurProfilPKDTO1).isNotEqualTo(utilisateurProfilPKDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(utilisateurProfilPKMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(utilisateurProfilPKMapper.fromId(null)).isNull();
    }
}
