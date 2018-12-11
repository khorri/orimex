package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Profil;
import ma.co.orimex.stock.repository.ProfilRepository;
import ma.co.orimex.stock.repository.search.ProfilSearchRepository;
import ma.co.orimex.stock.service.ProfilService;
import ma.co.orimex.stock.service.dto.ProfilDTO;
import ma.co.orimex.stock.service.mapper.ProfilMapper;
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
 * Test class for the ProfilResource REST controller.
 *
 * @see ProfilResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class ProfilResourceIntTest {

    private static final String DEFAULT_CODE_PROFIL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PROFIL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_PROFIL = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_PROFIL = "BBBBBBBBBB";

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private ProfilMapper profilMapper;

    @Autowired
    private ProfilService profilService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.ProfilSearchRepositoryMockConfiguration
     */
    @Autowired
    private ProfilSearchRepository mockProfilSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProfilMockMvc;

    private Profil profil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfilResource profilResource = new ProfilResource(profilService);
        this.restProfilMockMvc = MockMvcBuilders.standaloneSetup(profilResource)
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
    public static Profil createEntity(EntityManager em) {
        Profil profil = new Profil()
            .codeProfil(DEFAULT_CODE_PROFIL)
            .descriptionProfil(DEFAULT_DESCRIPTION_PROFIL);
        return profil;
    }

    @Before
    public void initTest() {
        profil = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfil() throws Exception {
        int databaseSizeBeforeCreate = profilRepository.findAll().size();

        // Create the Profil
        ProfilDTO profilDTO = profilMapper.toDto(profil);
        restProfilMockMvc.perform(post("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilDTO)))
            .andExpect(status().isCreated());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeCreate + 1);
        Profil testProfil = profilList.get(profilList.size() - 1);
        assertThat(testProfil.getCodeProfil()).isEqualTo(DEFAULT_CODE_PROFIL);
        assertThat(testProfil.getDescriptionProfil()).isEqualTo(DEFAULT_DESCRIPTION_PROFIL);

        // Validate the Profil in Elasticsearch
        verify(mockProfilSearchRepository, times(1)).save(testProfil);
    }

    @Test
    @Transactional
    public void createProfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profilRepository.findAll().size();

        // Create the Profil with an existing ID
        profil.setId(1L);
        ProfilDTO profilDTO = profilMapper.toDto(profil);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfilMockMvc.perform(post("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeCreate);

        // Validate the Profil in Elasticsearch
        verify(mockProfilSearchRepository, times(0)).save(profil);
    }

    @Test
    @Transactional
    public void getAllProfils() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);

        // Get all the profilList
        restProfilMockMvc.perform(get("/api/profils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profil.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeProfil").value(hasItem(DEFAULT_CODE_PROFIL.toString())))
            .andExpect(jsonPath("$.[*].descriptionProfil").value(hasItem(DEFAULT_DESCRIPTION_PROFIL.toString())));
    }
    
    @Test
    @Transactional
    public void getProfil() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);

        // Get the profil
        restProfilMockMvc.perform(get("/api/profils/{id}", profil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profil.getId().intValue()))
            .andExpect(jsonPath("$.codeProfil").value(DEFAULT_CODE_PROFIL.toString()))
            .andExpect(jsonPath("$.descriptionProfil").value(DEFAULT_DESCRIPTION_PROFIL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProfil() throws Exception {
        // Get the profil
        restProfilMockMvc.perform(get("/api/profils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfil() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);

        int databaseSizeBeforeUpdate = profilRepository.findAll().size();

        // Update the profil
        Profil updatedProfil = profilRepository.findById(profil.getId()).get();
        // Disconnect from session so that the updates on updatedProfil are not directly saved in db
        em.detach(updatedProfil);
        updatedProfil
            .codeProfil(UPDATED_CODE_PROFIL)
            .descriptionProfil(UPDATED_DESCRIPTION_PROFIL);
        ProfilDTO profilDTO = profilMapper.toDto(updatedProfil);

        restProfilMockMvc.perform(put("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilDTO)))
            .andExpect(status().isOk());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeUpdate);
        Profil testProfil = profilList.get(profilList.size() - 1);
        assertThat(testProfil.getCodeProfil()).isEqualTo(UPDATED_CODE_PROFIL);
        assertThat(testProfil.getDescriptionProfil()).isEqualTo(UPDATED_DESCRIPTION_PROFIL);

        // Validate the Profil in Elasticsearch
        verify(mockProfilSearchRepository, times(1)).save(testProfil);
    }

    @Test
    @Transactional
    public void updateNonExistingProfil() throws Exception {
        int databaseSizeBeforeUpdate = profilRepository.findAll().size();

        // Create the Profil
        ProfilDTO profilDTO = profilMapper.toDto(profil);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfilMockMvc.perform(put("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Profil in Elasticsearch
        verify(mockProfilSearchRepository, times(0)).save(profil);
    }

    @Test
    @Transactional
    public void deleteProfil() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);

        int databaseSizeBeforeDelete = profilRepository.findAll().size();

        // Get the profil
        restProfilMockMvc.perform(delete("/api/profils/{id}", profil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Profil in Elasticsearch
        verify(mockProfilSearchRepository, times(1)).deleteById(profil.getId());
    }

    @Test
    @Transactional
    public void searchProfil() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);
        when(mockProfilSearchRepository.search(queryStringQuery("id:" + profil.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(profil), PageRequest.of(0, 1), 1));
        // Search the profil
        restProfilMockMvc.perform(get("/api/_search/profils?query=id:" + profil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profil.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeProfil").value(hasItem(DEFAULT_CODE_PROFIL)))
            .andExpect(jsonPath("$.[*].descriptionProfil").value(hasItem(DEFAULT_DESCRIPTION_PROFIL)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Profil.class);
        Profil profil1 = new Profil();
        profil1.setId(1L);
        Profil profil2 = new Profil();
        profil2.setId(profil1.getId());
        assertThat(profil1).isEqualTo(profil2);
        profil2.setId(2L);
        assertThat(profil1).isNotEqualTo(profil2);
        profil1.setId(null);
        assertThat(profil1).isNotEqualTo(profil2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilDTO.class);
        ProfilDTO profilDTO1 = new ProfilDTO();
        profilDTO1.setId(1L);
        ProfilDTO profilDTO2 = new ProfilDTO();
        assertThat(profilDTO1).isNotEqualTo(profilDTO2);
        profilDTO2.setId(profilDTO1.getId());
        assertThat(profilDTO1).isEqualTo(profilDTO2);
        profilDTO2.setId(2L);
        assertThat(profilDTO1).isNotEqualTo(profilDTO2);
        profilDTO1.setId(null);
        assertThat(profilDTO1).isNotEqualTo(profilDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(profilMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(profilMapper.fromId(null)).isNull();
    }
}
