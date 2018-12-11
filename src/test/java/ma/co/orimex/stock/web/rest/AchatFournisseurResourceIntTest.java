package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatFournisseur;
import ma.co.orimex.stock.repository.AchatFournisseurRepository;
import ma.co.orimex.stock.repository.search.AchatFournisseurSearchRepository;
import ma.co.orimex.stock.service.AchatFournisseurService;
import ma.co.orimex.stock.service.dto.AchatFournisseurDTO;
import ma.co.orimex.stock.service.mapper.AchatFournisseurMapper;
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
 * Test class for the AchatFournisseurResource REST controller.
 *
 * @see AchatFournisseurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatFournisseurResourceIntTest {

    private static final String DEFAULT_TYPE_FOURNISSEUR = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_FOURNISSEUR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_FOURNISSEUR = "AAAAAAAAAA";
    private static final String UPDATED_CODE_FOURNISSEUR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_FOURNISSEUR = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_FOURNISSEUR = "BBBBBBBBBB";

    @Autowired
    private AchatFournisseurRepository achatFournisseurRepository;

    @Autowired
    private AchatFournisseurMapper achatFournisseurMapper;

    @Autowired
    private AchatFournisseurService achatFournisseurService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatFournisseurSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatFournisseurSearchRepository mockAchatFournisseurSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatFournisseurMockMvc;

    private AchatFournisseur achatFournisseur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatFournisseurResource achatFournisseurResource = new AchatFournisseurResource(achatFournisseurService);
        this.restAchatFournisseurMockMvc = MockMvcBuilders.standaloneSetup(achatFournisseurResource)
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
    public static AchatFournisseur createEntity(EntityManager em) {
        AchatFournisseur achatFournisseur = new AchatFournisseur()
            .typeFournisseur(DEFAULT_TYPE_FOURNISSEUR)
            .codeFournisseur(DEFAULT_CODE_FOURNISSEUR)
            .designationFournisseur(DEFAULT_DESIGNATION_FOURNISSEUR);
        return achatFournisseur;
    }

    @Before
    public void initTest() {
        achatFournisseur = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatFournisseur() throws Exception {
        int databaseSizeBeforeCreate = achatFournisseurRepository.findAll().size();

        // Create the AchatFournisseur
        AchatFournisseurDTO achatFournisseurDTO = achatFournisseurMapper.toDto(achatFournisseur);
        restAchatFournisseurMockMvc.perform(post("/api/achat-fournisseurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatFournisseurDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatFournisseur in the database
        List<AchatFournisseur> achatFournisseurList = achatFournisseurRepository.findAll();
        assertThat(achatFournisseurList).hasSize(databaseSizeBeforeCreate + 1);
        AchatFournisseur testAchatFournisseur = achatFournisseurList.get(achatFournisseurList.size() - 1);
        assertThat(testAchatFournisseur.getTypeFournisseur()).isEqualTo(DEFAULT_TYPE_FOURNISSEUR);
        assertThat(testAchatFournisseur.getCodeFournisseur()).isEqualTo(DEFAULT_CODE_FOURNISSEUR);
        assertThat(testAchatFournisseur.getDesignationFournisseur()).isEqualTo(DEFAULT_DESIGNATION_FOURNISSEUR);

        // Validate the AchatFournisseur in Elasticsearch
        verify(mockAchatFournisseurSearchRepository, times(1)).save(testAchatFournisseur);
    }

    @Test
    @Transactional
    public void createAchatFournisseurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatFournisseurRepository.findAll().size();

        // Create the AchatFournisseur with an existing ID
        achatFournisseur.setId(1L);
        AchatFournisseurDTO achatFournisseurDTO = achatFournisseurMapper.toDto(achatFournisseur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatFournisseurMockMvc.perform(post("/api/achat-fournisseurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatFournisseurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatFournisseur in the database
        List<AchatFournisseur> achatFournisseurList = achatFournisseurRepository.findAll();
        assertThat(achatFournisseurList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatFournisseur in Elasticsearch
        verify(mockAchatFournisseurSearchRepository, times(0)).save(achatFournisseur);
    }

    @Test
    @Transactional
    public void getAllAchatFournisseurs() throws Exception {
        // Initialize the database
        achatFournisseurRepository.saveAndFlush(achatFournisseur);

        // Get all the achatFournisseurList
        restAchatFournisseurMockMvc.perform(get("/api/achat-fournisseurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatFournisseur.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeFournisseur").value(hasItem(DEFAULT_TYPE_FOURNISSEUR.toString())))
            .andExpect(jsonPath("$.[*].codeFournisseur").value(hasItem(DEFAULT_CODE_FOURNISSEUR.toString())))
            .andExpect(jsonPath("$.[*].designationFournisseur").value(hasItem(DEFAULT_DESIGNATION_FOURNISSEUR.toString())));
    }
    
    @Test
    @Transactional
    public void getAchatFournisseur() throws Exception {
        // Initialize the database
        achatFournisseurRepository.saveAndFlush(achatFournisseur);

        // Get the achatFournisseur
        restAchatFournisseurMockMvc.perform(get("/api/achat-fournisseurs/{id}", achatFournisseur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatFournisseur.getId().intValue()))
            .andExpect(jsonPath("$.typeFournisseur").value(DEFAULT_TYPE_FOURNISSEUR.toString()))
            .andExpect(jsonPath("$.codeFournisseur").value(DEFAULT_CODE_FOURNISSEUR.toString()))
            .andExpect(jsonPath("$.designationFournisseur").value(DEFAULT_DESIGNATION_FOURNISSEUR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatFournisseur() throws Exception {
        // Get the achatFournisseur
        restAchatFournisseurMockMvc.perform(get("/api/achat-fournisseurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatFournisseur() throws Exception {
        // Initialize the database
        achatFournisseurRepository.saveAndFlush(achatFournisseur);

        int databaseSizeBeforeUpdate = achatFournisseurRepository.findAll().size();

        // Update the achatFournisseur
        AchatFournisseur updatedAchatFournisseur = achatFournisseurRepository.findById(achatFournisseur.getId()).get();
        // Disconnect from session so that the updates on updatedAchatFournisseur are not directly saved in db
        em.detach(updatedAchatFournisseur);
        updatedAchatFournisseur
            .typeFournisseur(UPDATED_TYPE_FOURNISSEUR)
            .codeFournisseur(UPDATED_CODE_FOURNISSEUR)
            .designationFournisseur(UPDATED_DESIGNATION_FOURNISSEUR);
        AchatFournisseurDTO achatFournisseurDTO = achatFournisseurMapper.toDto(updatedAchatFournisseur);

        restAchatFournisseurMockMvc.perform(put("/api/achat-fournisseurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatFournisseurDTO)))
            .andExpect(status().isOk());

        // Validate the AchatFournisseur in the database
        List<AchatFournisseur> achatFournisseurList = achatFournisseurRepository.findAll();
        assertThat(achatFournisseurList).hasSize(databaseSizeBeforeUpdate);
        AchatFournisseur testAchatFournisseur = achatFournisseurList.get(achatFournisseurList.size() - 1);
        assertThat(testAchatFournisseur.getTypeFournisseur()).isEqualTo(UPDATED_TYPE_FOURNISSEUR);
        assertThat(testAchatFournisseur.getCodeFournisseur()).isEqualTo(UPDATED_CODE_FOURNISSEUR);
        assertThat(testAchatFournisseur.getDesignationFournisseur()).isEqualTo(UPDATED_DESIGNATION_FOURNISSEUR);

        // Validate the AchatFournisseur in Elasticsearch
        verify(mockAchatFournisseurSearchRepository, times(1)).save(testAchatFournisseur);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatFournisseur() throws Exception {
        int databaseSizeBeforeUpdate = achatFournisseurRepository.findAll().size();

        // Create the AchatFournisseur
        AchatFournisseurDTO achatFournisseurDTO = achatFournisseurMapper.toDto(achatFournisseur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatFournisseurMockMvc.perform(put("/api/achat-fournisseurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatFournisseurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatFournisseur in the database
        List<AchatFournisseur> achatFournisseurList = achatFournisseurRepository.findAll();
        assertThat(achatFournisseurList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatFournisseur in Elasticsearch
        verify(mockAchatFournisseurSearchRepository, times(0)).save(achatFournisseur);
    }

    @Test
    @Transactional
    public void deleteAchatFournisseur() throws Exception {
        // Initialize the database
        achatFournisseurRepository.saveAndFlush(achatFournisseur);

        int databaseSizeBeforeDelete = achatFournisseurRepository.findAll().size();

        // Get the achatFournisseur
        restAchatFournisseurMockMvc.perform(delete("/api/achat-fournisseurs/{id}", achatFournisseur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatFournisseur> achatFournisseurList = achatFournisseurRepository.findAll();
        assertThat(achatFournisseurList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatFournisseur in Elasticsearch
        verify(mockAchatFournisseurSearchRepository, times(1)).deleteById(achatFournisseur.getId());
    }

    @Test
    @Transactional
    public void searchAchatFournisseur() throws Exception {
        // Initialize the database
        achatFournisseurRepository.saveAndFlush(achatFournisseur);
        when(mockAchatFournisseurSearchRepository.search(queryStringQuery("id:" + achatFournisseur.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatFournisseur), PageRequest.of(0, 1), 1));
        // Search the achatFournisseur
        restAchatFournisseurMockMvc.perform(get("/api/_search/achat-fournisseurs?query=id:" + achatFournisseur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatFournisseur.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeFournisseur").value(hasItem(DEFAULT_TYPE_FOURNISSEUR)))
            .andExpect(jsonPath("$.[*].codeFournisseur").value(hasItem(DEFAULT_CODE_FOURNISSEUR)))
            .andExpect(jsonPath("$.[*].designationFournisseur").value(hasItem(DEFAULT_DESIGNATION_FOURNISSEUR)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatFournisseur.class);
        AchatFournisseur achatFournisseur1 = new AchatFournisseur();
        achatFournisseur1.setId(1L);
        AchatFournisseur achatFournisseur2 = new AchatFournisseur();
        achatFournisseur2.setId(achatFournisseur1.getId());
        assertThat(achatFournisseur1).isEqualTo(achatFournisseur2);
        achatFournisseur2.setId(2L);
        assertThat(achatFournisseur1).isNotEqualTo(achatFournisseur2);
        achatFournisseur1.setId(null);
        assertThat(achatFournisseur1).isNotEqualTo(achatFournisseur2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatFournisseurDTO.class);
        AchatFournisseurDTO achatFournisseurDTO1 = new AchatFournisseurDTO();
        achatFournisseurDTO1.setId(1L);
        AchatFournisseurDTO achatFournisseurDTO2 = new AchatFournisseurDTO();
        assertThat(achatFournisseurDTO1).isNotEqualTo(achatFournisseurDTO2);
        achatFournisseurDTO2.setId(achatFournisseurDTO1.getId());
        assertThat(achatFournisseurDTO1).isEqualTo(achatFournisseurDTO2);
        achatFournisseurDTO2.setId(2L);
        assertThat(achatFournisseurDTO1).isNotEqualTo(achatFournisseurDTO2);
        achatFournisseurDTO1.setId(null);
        assertThat(achatFournisseurDTO1).isNotEqualTo(achatFournisseurDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatFournisseurMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatFournisseurMapper.fromId(null)).isNull();
    }
}
