package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.UtilisateurProfil;
import ma.co.orimex.stock.repository.UtilisateurProfilRepository;
import ma.co.orimex.stock.repository.search.UtilisateurProfilSearchRepository;
import ma.co.orimex.stock.service.UtilisateurProfilService;
import ma.co.orimex.stock.service.dto.UtilisateurProfilDTO;
import ma.co.orimex.stock.service.mapper.UtilisateurProfilMapper;
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
 * Test class for the UtilisateurProfilResource REST controller.
 *
 * @see UtilisateurProfilResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class UtilisateurProfilResourceIntTest {

    @Autowired
    private UtilisateurProfilRepository utilisateurProfilRepository;

    @Autowired
    private UtilisateurProfilMapper utilisateurProfilMapper;

    @Autowired
    private UtilisateurProfilService utilisateurProfilService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.UtilisateurProfilSearchRepositoryMockConfiguration
     */
    @Autowired
    private UtilisateurProfilSearchRepository mockUtilisateurProfilSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUtilisateurProfilMockMvc;

    private UtilisateurProfil utilisateurProfil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UtilisateurProfilResource utilisateurProfilResource = new UtilisateurProfilResource(utilisateurProfilService);
        this.restUtilisateurProfilMockMvc = MockMvcBuilders.standaloneSetup(utilisateurProfilResource)
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
    public static UtilisateurProfil createEntity(EntityManager em) {
        UtilisateurProfil utilisateurProfil = new UtilisateurProfil();
        return utilisateurProfil;
    }

    @Before
    public void initTest() {
        utilisateurProfil = createEntity(em);
    }

    @Test
    @Transactional
    public void createUtilisateurProfil() throws Exception {
        int databaseSizeBeforeCreate = utilisateurProfilRepository.findAll().size();

        // Create the UtilisateurProfil
        UtilisateurProfilDTO utilisateurProfilDTO = utilisateurProfilMapper.toDto(utilisateurProfil);
        restUtilisateurProfilMockMvc.perform(post("/api/utilisateur-profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurProfilDTO)))
            .andExpect(status().isCreated());

        // Validate the UtilisateurProfil in the database
        List<UtilisateurProfil> utilisateurProfilList = utilisateurProfilRepository.findAll();
        assertThat(utilisateurProfilList).hasSize(databaseSizeBeforeCreate + 1);
        UtilisateurProfil testUtilisateurProfil = utilisateurProfilList.get(utilisateurProfilList.size() - 1);

        // Validate the UtilisateurProfil in Elasticsearch
        verify(mockUtilisateurProfilSearchRepository, times(1)).save(testUtilisateurProfil);
    }

    @Test
    @Transactional
    public void createUtilisateurProfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = utilisateurProfilRepository.findAll().size();

        // Create the UtilisateurProfil with an existing ID
        utilisateurProfil.setId(1L);
        UtilisateurProfilDTO utilisateurProfilDTO = utilisateurProfilMapper.toDto(utilisateurProfil);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUtilisateurProfilMockMvc.perform(post("/api/utilisateur-profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurProfilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UtilisateurProfil in the database
        List<UtilisateurProfil> utilisateurProfilList = utilisateurProfilRepository.findAll();
        assertThat(utilisateurProfilList).hasSize(databaseSizeBeforeCreate);

        // Validate the UtilisateurProfil in Elasticsearch
        verify(mockUtilisateurProfilSearchRepository, times(0)).save(utilisateurProfil);
    }

    @Test
    @Transactional
    public void getAllUtilisateurProfils() throws Exception {
        // Initialize the database
        utilisateurProfilRepository.saveAndFlush(utilisateurProfil);

        // Get all the utilisateurProfilList
        restUtilisateurProfilMockMvc.perform(get("/api/utilisateur-profils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateurProfil.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUtilisateurProfil() throws Exception {
        // Initialize the database
        utilisateurProfilRepository.saveAndFlush(utilisateurProfil);

        // Get the utilisateurProfil
        restUtilisateurProfilMockMvc.perform(get("/api/utilisateur-profils/{id}", utilisateurProfil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(utilisateurProfil.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUtilisateurProfil() throws Exception {
        // Get the utilisateurProfil
        restUtilisateurProfilMockMvc.perform(get("/api/utilisateur-profils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUtilisateurProfil() throws Exception {
        // Initialize the database
        utilisateurProfilRepository.saveAndFlush(utilisateurProfil);

        int databaseSizeBeforeUpdate = utilisateurProfilRepository.findAll().size();

        // Update the utilisateurProfil
        UtilisateurProfil updatedUtilisateurProfil = utilisateurProfilRepository.findById(utilisateurProfil.getId()).get();
        // Disconnect from session so that the updates on updatedUtilisateurProfil are not directly saved in db
        em.detach(updatedUtilisateurProfil);
        UtilisateurProfilDTO utilisateurProfilDTO = utilisateurProfilMapper.toDto(updatedUtilisateurProfil);

        restUtilisateurProfilMockMvc.perform(put("/api/utilisateur-profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurProfilDTO)))
            .andExpect(status().isOk());

        // Validate the UtilisateurProfil in the database
        List<UtilisateurProfil> utilisateurProfilList = utilisateurProfilRepository.findAll();
        assertThat(utilisateurProfilList).hasSize(databaseSizeBeforeUpdate);
        UtilisateurProfil testUtilisateurProfil = utilisateurProfilList.get(utilisateurProfilList.size() - 1);

        // Validate the UtilisateurProfil in Elasticsearch
        verify(mockUtilisateurProfilSearchRepository, times(1)).save(testUtilisateurProfil);
    }

    @Test
    @Transactional
    public void updateNonExistingUtilisateurProfil() throws Exception {
        int databaseSizeBeforeUpdate = utilisateurProfilRepository.findAll().size();

        // Create the UtilisateurProfil
        UtilisateurProfilDTO utilisateurProfilDTO = utilisateurProfilMapper.toDto(utilisateurProfil);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUtilisateurProfilMockMvc.perform(put("/api/utilisateur-profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurProfilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UtilisateurProfil in the database
        List<UtilisateurProfil> utilisateurProfilList = utilisateurProfilRepository.findAll();
        assertThat(utilisateurProfilList).hasSize(databaseSizeBeforeUpdate);

        // Validate the UtilisateurProfil in Elasticsearch
        verify(mockUtilisateurProfilSearchRepository, times(0)).save(utilisateurProfil);
    }

    @Test
    @Transactional
    public void deleteUtilisateurProfil() throws Exception {
        // Initialize the database
        utilisateurProfilRepository.saveAndFlush(utilisateurProfil);

        int databaseSizeBeforeDelete = utilisateurProfilRepository.findAll().size();

        // Get the utilisateurProfil
        restUtilisateurProfilMockMvc.perform(delete("/api/utilisateur-profils/{id}", utilisateurProfil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UtilisateurProfil> utilisateurProfilList = utilisateurProfilRepository.findAll();
        assertThat(utilisateurProfilList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the UtilisateurProfil in Elasticsearch
        verify(mockUtilisateurProfilSearchRepository, times(1)).deleteById(utilisateurProfil.getId());
    }

    @Test
    @Transactional
    public void searchUtilisateurProfil() throws Exception {
        // Initialize the database
        utilisateurProfilRepository.saveAndFlush(utilisateurProfil);
        when(mockUtilisateurProfilSearchRepository.search(queryStringQuery("id:" + utilisateurProfil.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(utilisateurProfil), PageRequest.of(0, 1), 1));
        // Search the utilisateurProfil
        restUtilisateurProfilMockMvc.perform(get("/api/_search/utilisateur-profils?query=id:" + utilisateurProfil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateurProfil.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisateurProfil.class);
        UtilisateurProfil utilisateurProfil1 = new UtilisateurProfil();
        utilisateurProfil1.setId(1L);
        UtilisateurProfil utilisateurProfil2 = new UtilisateurProfil();
        utilisateurProfil2.setId(utilisateurProfil1.getId());
        assertThat(utilisateurProfil1).isEqualTo(utilisateurProfil2);
        utilisateurProfil2.setId(2L);
        assertThat(utilisateurProfil1).isNotEqualTo(utilisateurProfil2);
        utilisateurProfil1.setId(null);
        assertThat(utilisateurProfil1).isNotEqualTo(utilisateurProfil2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisateurProfilDTO.class);
        UtilisateurProfilDTO utilisateurProfilDTO1 = new UtilisateurProfilDTO();
        utilisateurProfilDTO1.setId(1L);
        UtilisateurProfilDTO utilisateurProfilDTO2 = new UtilisateurProfilDTO();
        assertThat(utilisateurProfilDTO1).isNotEqualTo(utilisateurProfilDTO2);
        utilisateurProfilDTO2.setId(utilisateurProfilDTO1.getId());
        assertThat(utilisateurProfilDTO1).isEqualTo(utilisateurProfilDTO2);
        utilisateurProfilDTO2.setId(2L);
        assertThat(utilisateurProfilDTO1).isNotEqualTo(utilisateurProfilDTO2);
        utilisateurProfilDTO1.setId(null);
        assertThat(utilisateurProfilDTO1).isNotEqualTo(utilisateurProfilDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(utilisateurProfilMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(utilisateurProfilMapper.fromId(null)).isNull();
    }
}
