package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.ProfilAction;
import ma.co.orimex.stock.repository.ProfilActionRepository;
import ma.co.orimex.stock.repository.search.ProfilActionSearchRepository;
import ma.co.orimex.stock.service.ProfilActionService;
import ma.co.orimex.stock.service.dto.ProfilActionDTO;
import ma.co.orimex.stock.service.mapper.ProfilActionMapper;
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
 * Test class for the ProfilActionResource REST controller.
 *
 * @see ProfilActionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class ProfilActionResourceIntTest {

    @Autowired
    private ProfilActionRepository profilActionRepository;

    @Autowired
    private ProfilActionMapper profilActionMapper;

    @Autowired
    private ProfilActionService profilActionService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.ProfilActionSearchRepositoryMockConfiguration
     */
    @Autowired
    private ProfilActionSearchRepository mockProfilActionSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProfilActionMockMvc;

    private ProfilAction profilAction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfilActionResource profilActionResource = new ProfilActionResource(profilActionService);
        this.restProfilActionMockMvc = MockMvcBuilders.standaloneSetup(profilActionResource)
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
    public static ProfilAction createEntity(EntityManager em) {
        ProfilAction profilAction = new ProfilAction();
        return profilAction;
    }

    @Before
    public void initTest() {
        profilAction = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfilAction() throws Exception {
        int databaseSizeBeforeCreate = profilActionRepository.findAll().size();

        // Create the ProfilAction
        ProfilActionDTO profilActionDTO = profilActionMapper.toDto(profilAction);
        restProfilActionMockMvc.perform(post("/api/profil-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilActionDTO)))
            .andExpect(status().isCreated());

        // Validate the ProfilAction in the database
        List<ProfilAction> profilActionList = profilActionRepository.findAll();
        assertThat(profilActionList).hasSize(databaseSizeBeforeCreate + 1);
        ProfilAction testProfilAction = profilActionList.get(profilActionList.size() - 1);

        // Validate the ProfilAction in Elasticsearch
        verify(mockProfilActionSearchRepository, times(1)).save(testProfilAction);
    }

    @Test
    @Transactional
    public void createProfilActionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profilActionRepository.findAll().size();

        // Create the ProfilAction with an existing ID
        profilAction.setId(1L);
        ProfilActionDTO profilActionDTO = profilActionMapper.toDto(profilAction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfilActionMockMvc.perform(post("/api/profil-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilAction in the database
        List<ProfilAction> profilActionList = profilActionRepository.findAll();
        assertThat(profilActionList).hasSize(databaseSizeBeforeCreate);

        // Validate the ProfilAction in Elasticsearch
        verify(mockProfilActionSearchRepository, times(0)).save(profilAction);
    }

    @Test
    @Transactional
    public void getAllProfilActions() throws Exception {
        // Initialize the database
        profilActionRepository.saveAndFlush(profilAction);

        // Get all the profilActionList
        restProfilActionMockMvc.perform(get("/api/profil-actions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profilAction.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getProfilAction() throws Exception {
        // Initialize the database
        profilActionRepository.saveAndFlush(profilAction);

        // Get the profilAction
        restProfilActionMockMvc.perform(get("/api/profil-actions/{id}", profilAction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profilAction.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProfilAction() throws Exception {
        // Get the profilAction
        restProfilActionMockMvc.perform(get("/api/profil-actions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfilAction() throws Exception {
        // Initialize the database
        profilActionRepository.saveAndFlush(profilAction);

        int databaseSizeBeforeUpdate = profilActionRepository.findAll().size();

        // Update the profilAction
        ProfilAction updatedProfilAction = profilActionRepository.findById(profilAction.getId()).get();
        // Disconnect from session so that the updates on updatedProfilAction are not directly saved in db
        em.detach(updatedProfilAction);
        ProfilActionDTO profilActionDTO = profilActionMapper.toDto(updatedProfilAction);

        restProfilActionMockMvc.perform(put("/api/profil-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilActionDTO)))
            .andExpect(status().isOk());

        // Validate the ProfilAction in the database
        List<ProfilAction> profilActionList = profilActionRepository.findAll();
        assertThat(profilActionList).hasSize(databaseSizeBeforeUpdate);
        ProfilAction testProfilAction = profilActionList.get(profilActionList.size() - 1);

        // Validate the ProfilAction in Elasticsearch
        verify(mockProfilActionSearchRepository, times(1)).save(testProfilAction);
    }

    @Test
    @Transactional
    public void updateNonExistingProfilAction() throws Exception {
        int databaseSizeBeforeUpdate = profilActionRepository.findAll().size();

        // Create the ProfilAction
        ProfilActionDTO profilActionDTO = profilActionMapper.toDto(profilAction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfilActionMockMvc.perform(put("/api/profil-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilAction in the database
        List<ProfilAction> profilActionList = profilActionRepository.findAll();
        assertThat(profilActionList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ProfilAction in Elasticsearch
        verify(mockProfilActionSearchRepository, times(0)).save(profilAction);
    }

    @Test
    @Transactional
    public void deleteProfilAction() throws Exception {
        // Initialize the database
        profilActionRepository.saveAndFlush(profilAction);

        int databaseSizeBeforeDelete = profilActionRepository.findAll().size();

        // Get the profilAction
        restProfilActionMockMvc.perform(delete("/api/profil-actions/{id}", profilAction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProfilAction> profilActionList = profilActionRepository.findAll();
        assertThat(profilActionList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ProfilAction in Elasticsearch
        verify(mockProfilActionSearchRepository, times(1)).deleteById(profilAction.getId());
    }

    @Test
    @Transactional
    public void searchProfilAction() throws Exception {
        // Initialize the database
        profilActionRepository.saveAndFlush(profilAction);
        when(mockProfilActionSearchRepository.search(queryStringQuery("id:" + profilAction.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(profilAction), PageRequest.of(0, 1), 1));
        // Search the profilAction
        restProfilActionMockMvc.perform(get("/api/_search/profil-actions?query=id:" + profilAction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profilAction.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilAction.class);
        ProfilAction profilAction1 = new ProfilAction();
        profilAction1.setId(1L);
        ProfilAction profilAction2 = new ProfilAction();
        profilAction2.setId(profilAction1.getId());
        assertThat(profilAction1).isEqualTo(profilAction2);
        profilAction2.setId(2L);
        assertThat(profilAction1).isNotEqualTo(profilAction2);
        profilAction1.setId(null);
        assertThat(profilAction1).isNotEqualTo(profilAction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilActionDTO.class);
        ProfilActionDTO profilActionDTO1 = new ProfilActionDTO();
        profilActionDTO1.setId(1L);
        ProfilActionDTO profilActionDTO2 = new ProfilActionDTO();
        assertThat(profilActionDTO1).isNotEqualTo(profilActionDTO2);
        profilActionDTO2.setId(profilActionDTO1.getId());
        assertThat(profilActionDTO1).isEqualTo(profilActionDTO2);
        profilActionDTO2.setId(2L);
        assertThat(profilActionDTO1).isNotEqualTo(profilActionDTO2);
        profilActionDTO1.setId(null);
        assertThat(profilActionDTO1).isNotEqualTo(profilActionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(profilActionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(profilActionMapper.fromId(null)).isNull();
    }
}
