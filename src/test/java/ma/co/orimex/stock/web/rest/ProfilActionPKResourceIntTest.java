package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.ProfilActionPK;
import ma.co.orimex.stock.repository.ProfilActionPKRepository;
import ma.co.orimex.stock.repository.search.ProfilActionPKSearchRepository;
import ma.co.orimex.stock.service.ProfilActionPKService;
import ma.co.orimex.stock.service.dto.ProfilActionPKDTO;
import ma.co.orimex.stock.service.mapper.ProfilActionPKMapper;
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
 * Test class for the ProfilActionPKResource REST controller.
 *
 * @see ProfilActionPKResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class ProfilActionPKResourceIntTest {

    @Autowired
    private ProfilActionPKRepository profilActionPKRepository;

    @Autowired
    private ProfilActionPKMapper profilActionPKMapper;

    @Autowired
    private ProfilActionPKService profilActionPKService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.ProfilActionPKSearchRepositoryMockConfiguration
     */
    @Autowired
    private ProfilActionPKSearchRepository mockProfilActionPKSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProfilActionPKMockMvc;

    private ProfilActionPK profilActionPK;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfilActionPKResource profilActionPKResource = new ProfilActionPKResource(profilActionPKService);
        this.restProfilActionPKMockMvc = MockMvcBuilders.standaloneSetup(profilActionPKResource)
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
    public static ProfilActionPK createEntity(EntityManager em) {
        ProfilActionPK profilActionPK = new ProfilActionPK();
        return profilActionPK;
    }

    @Before
    public void initTest() {
        profilActionPK = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfilActionPK() throws Exception {
        int databaseSizeBeforeCreate = profilActionPKRepository.findAll().size();

        // Create the ProfilActionPK
        ProfilActionPKDTO profilActionPKDTO = profilActionPKMapper.toDto(profilActionPK);
        restProfilActionPKMockMvc.perform(post("/api/profil-action-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilActionPKDTO)))
            .andExpect(status().isCreated());

        // Validate the ProfilActionPK in the database
        List<ProfilActionPK> profilActionPKList = profilActionPKRepository.findAll();
        assertThat(profilActionPKList).hasSize(databaseSizeBeforeCreate + 1);
        ProfilActionPK testProfilActionPK = profilActionPKList.get(profilActionPKList.size() - 1);

        // Validate the ProfilActionPK in Elasticsearch
        verify(mockProfilActionPKSearchRepository, times(1)).save(testProfilActionPK);
    }

    @Test
    @Transactional
    public void createProfilActionPKWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profilActionPKRepository.findAll().size();

        // Create the ProfilActionPK with an existing ID
        profilActionPK.setId(1L);
        ProfilActionPKDTO profilActionPKDTO = profilActionPKMapper.toDto(profilActionPK);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfilActionPKMockMvc.perform(post("/api/profil-action-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilActionPKDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilActionPK in the database
        List<ProfilActionPK> profilActionPKList = profilActionPKRepository.findAll();
        assertThat(profilActionPKList).hasSize(databaseSizeBeforeCreate);

        // Validate the ProfilActionPK in Elasticsearch
        verify(mockProfilActionPKSearchRepository, times(0)).save(profilActionPK);
    }

    @Test
    @Transactional
    public void getAllProfilActionPKS() throws Exception {
        // Initialize the database
        profilActionPKRepository.saveAndFlush(profilActionPK);

        // Get all the profilActionPKList
        restProfilActionPKMockMvc.perform(get("/api/profil-action-pks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profilActionPK.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getProfilActionPK() throws Exception {
        // Initialize the database
        profilActionPKRepository.saveAndFlush(profilActionPK);

        // Get the profilActionPK
        restProfilActionPKMockMvc.perform(get("/api/profil-action-pks/{id}", profilActionPK.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profilActionPK.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProfilActionPK() throws Exception {
        // Get the profilActionPK
        restProfilActionPKMockMvc.perform(get("/api/profil-action-pks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfilActionPK() throws Exception {
        // Initialize the database
        profilActionPKRepository.saveAndFlush(profilActionPK);

        int databaseSizeBeforeUpdate = profilActionPKRepository.findAll().size();

        // Update the profilActionPK
        ProfilActionPK updatedProfilActionPK = profilActionPKRepository.findById(profilActionPK.getId()).get();
        // Disconnect from session so that the updates on updatedProfilActionPK are not directly saved in db
        em.detach(updatedProfilActionPK);
        ProfilActionPKDTO profilActionPKDTO = profilActionPKMapper.toDto(updatedProfilActionPK);

        restProfilActionPKMockMvc.perform(put("/api/profil-action-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilActionPKDTO)))
            .andExpect(status().isOk());

        // Validate the ProfilActionPK in the database
        List<ProfilActionPK> profilActionPKList = profilActionPKRepository.findAll();
        assertThat(profilActionPKList).hasSize(databaseSizeBeforeUpdate);
        ProfilActionPK testProfilActionPK = profilActionPKList.get(profilActionPKList.size() - 1);

        // Validate the ProfilActionPK in Elasticsearch
        verify(mockProfilActionPKSearchRepository, times(1)).save(testProfilActionPK);
    }

    @Test
    @Transactional
    public void updateNonExistingProfilActionPK() throws Exception {
        int databaseSizeBeforeUpdate = profilActionPKRepository.findAll().size();

        // Create the ProfilActionPK
        ProfilActionPKDTO profilActionPKDTO = profilActionPKMapper.toDto(profilActionPK);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfilActionPKMockMvc.perform(put("/api/profil-action-pks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilActionPKDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilActionPK in the database
        List<ProfilActionPK> profilActionPKList = profilActionPKRepository.findAll();
        assertThat(profilActionPKList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ProfilActionPK in Elasticsearch
        verify(mockProfilActionPKSearchRepository, times(0)).save(profilActionPK);
    }

    @Test
    @Transactional
    public void deleteProfilActionPK() throws Exception {
        // Initialize the database
        profilActionPKRepository.saveAndFlush(profilActionPK);

        int databaseSizeBeforeDelete = profilActionPKRepository.findAll().size();

        // Get the profilActionPK
        restProfilActionPKMockMvc.perform(delete("/api/profil-action-pks/{id}", profilActionPK.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProfilActionPK> profilActionPKList = profilActionPKRepository.findAll();
        assertThat(profilActionPKList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ProfilActionPK in Elasticsearch
        verify(mockProfilActionPKSearchRepository, times(1)).deleteById(profilActionPK.getId());
    }

    @Test
    @Transactional
    public void searchProfilActionPK() throws Exception {
        // Initialize the database
        profilActionPKRepository.saveAndFlush(profilActionPK);
        when(mockProfilActionPKSearchRepository.search(queryStringQuery("id:" + profilActionPK.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(profilActionPK), PageRequest.of(0, 1), 1));
        // Search the profilActionPK
        restProfilActionPKMockMvc.perform(get("/api/_search/profil-action-pks?query=id:" + profilActionPK.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profilActionPK.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilActionPK.class);
        ProfilActionPK profilActionPK1 = new ProfilActionPK();
        profilActionPK1.setId(1L);
        ProfilActionPK profilActionPK2 = new ProfilActionPK();
        profilActionPK2.setId(profilActionPK1.getId());
        assertThat(profilActionPK1).isEqualTo(profilActionPK2);
        profilActionPK2.setId(2L);
        assertThat(profilActionPK1).isNotEqualTo(profilActionPK2);
        profilActionPK1.setId(null);
        assertThat(profilActionPK1).isNotEqualTo(profilActionPK2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilActionPKDTO.class);
        ProfilActionPKDTO profilActionPKDTO1 = new ProfilActionPKDTO();
        profilActionPKDTO1.setId(1L);
        ProfilActionPKDTO profilActionPKDTO2 = new ProfilActionPKDTO();
        assertThat(profilActionPKDTO1).isNotEqualTo(profilActionPKDTO2);
        profilActionPKDTO2.setId(profilActionPKDTO1.getId());
        assertThat(profilActionPKDTO1).isEqualTo(profilActionPKDTO2);
        profilActionPKDTO2.setId(2L);
        assertThat(profilActionPKDTO1).isNotEqualTo(profilActionPKDTO2);
        profilActionPKDTO1.setId(null);
        assertThat(profilActionPKDTO1).isNotEqualTo(profilActionPKDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(profilActionPKMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(profilActionPKMapper.fromId(null)).isNull();
    }
}
