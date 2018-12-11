package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Utilisateur;
import ma.co.orimex.stock.repository.UtilisateurRepository;
import ma.co.orimex.stock.repository.search.UtilisateurSearchRepository;
import ma.co.orimex.stock.service.UtilisateurService;
import ma.co.orimex.stock.service.dto.UtilisateurDTO;
import ma.co.orimex.stock.service.mapper.UtilisateurMapper;
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
 * Test class for the UtilisateurResource REST controller.
 *
 * @see UtilisateurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class UtilisateurResourceIntTest {

    private static final Integer DEFAULT_ID_UTILISATEUR = 1;
    private static final Integer UPDATED_ID_UTILISATEUR = 2;

    private static final String DEFAULT_LOGIN_UTILISATEUR = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_UTILISATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULE_UTILISATEUR = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE_UTILISATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_UTILSATEUR = "AAAAAAAAAA";
    private static final String UPDATED_NOM_UTILSATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD_UTILISATEUR = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD_UTILISATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_UTILSATEUR = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_UTILSATEUR = "BBBBBBBBBB";

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.UtilisateurSearchRepositoryMockConfiguration
     */
    @Autowired
    private UtilisateurSearchRepository mockUtilisateurSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUtilisateurMockMvc;

    private Utilisateur utilisateur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UtilisateurResource utilisateurResource = new UtilisateurResource(utilisateurService);
        this.restUtilisateurMockMvc = MockMvcBuilders.standaloneSetup(utilisateurResource)
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
    public static Utilisateur createEntity(EntityManager em) {
        Utilisateur utilisateur = new Utilisateur()
            .idUtilisateur(DEFAULT_ID_UTILISATEUR)
            .loginUtilisateur(DEFAULT_LOGIN_UTILISATEUR)
            .matriculeUtilisateur(DEFAULT_MATRICULE_UTILISATEUR)
            .nomUtilsateur(DEFAULT_NOM_UTILSATEUR)
            .passwordUtilisateur(DEFAULT_PASSWORD_UTILISATEUR)
            .prenomUtilsateur(DEFAULT_PRENOM_UTILSATEUR);
        return utilisateur;
    }

    @Before
    public void initTest() {
        utilisateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createUtilisateur() throws Exception {
        int databaseSizeBeforeCreate = utilisateurRepository.findAll().size();

        // Create the Utilisateur
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);
        restUtilisateurMockMvc.perform(post("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isCreated());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeCreate + 1);
        Utilisateur testUtilisateur = utilisateurList.get(utilisateurList.size() - 1);
        assertThat(testUtilisateur.getIdUtilisateur()).isEqualTo(DEFAULT_ID_UTILISATEUR);
        assertThat(testUtilisateur.getLoginUtilisateur()).isEqualTo(DEFAULT_LOGIN_UTILISATEUR);
        assertThat(testUtilisateur.getMatriculeUtilisateur()).isEqualTo(DEFAULT_MATRICULE_UTILISATEUR);
        assertThat(testUtilisateur.getNomUtilsateur()).isEqualTo(DEFAULT_NOM_UTILSATEUR);
        assertThat(testUtilisateur.getPasswordUtilisateur()).isEqualTo(DEFAULT_PASSWORD_UTILISATEUR);
        assertThat(testUtilisateur.getPrenomUtilsateur()).isEqualTo(DEFAULT_PRENOM_UTILSATEUR);

        // Validate the Utilisateur in Elasticsearch
        verify(mockUtilisateurSearchRepository, times(1)).save(testUtilisateur);
    }

    @Test
    @Transactional
    public void createUtilisateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = utilisateurRepository.findAll().size();

        // Create the Utilisateur with an existing ID
        utilisateur.setId(1L);
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUtilisateurMockMvc.perform(post("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeCreate);

        // Validate the Utilisateur in Elasticsearch
        verify(mockUtilisateurSearchRepository, times(0)).save(utilisateur);
    }

    @Test
    @Transactional
    public void getAllUtilisateurs() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        // Get all the utilisateurList
        restUtilisateurMockMvc.perform(get("/api/utilisateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUtilisateur").value(hasItem(DEFAULT_ID_UTILISATEUR)))
            .andExpect(jsonPath("$.[*].loginUtilisateur").value(hasItem(DEFAULT_LOGIN_UTILISATEUR.toString())))
            .andExpect(jsonPath("$.[*].matriculeUtilisateur").value(hasItem(DEFAULT_MATRICULE_UTILISATEUR.toString())))
            .andExpect(jsonPath("$.[*].nomUtilsateur").value(hasItem(DEFAULT_NOM_UTILSATEUR.toString())))
            .andExpect(jsonPath("$.[*].passwordUtilisateur").value(hasItem(DEFAULT_PASSWORD_UTILISATEUR.toString())))
            .andExpect(jsonPath("$.[*].prenomUtilsateur").value(hasItem(DEFAULT_PRENOM_UTILSATEUR.toString())));
    }
    
    @Test
    @Transactional
    public void getUtilisateur() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        // Get the utilisateur
        restUtilisateurMockMvc.perform(get("/api/utilisateurs/{id}", utilisateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(utilisateur.getId().intValue()))
            .andExpect(jsonPath("$.idUtilisateur").value(DEFAULT_ID_UTILISATEUR))
            .andExpect(jsonPath("$.loginUtilisateur").value(DEFAULT_LOGIN_UTILISATEUR.toString()))
            .andExpect(jsonPath("$.matriculeUtilisateur").value(DEFAULT_MATRICULE_UTILISATEUR.toString()))
            .andExpect(jsonPath("$.nomUtilsateur").value(DEFAULT_NOM_UTILSATEUR.toString()))
            .andExpect(jsonPath("$.passwordUtilisateur").value(DEFAULT_PASSWORD_UTILISATEUR.toString()))
            .andExpect(jsonPath("$.prenomUtilsateur").value(DEFAULT_PRENOM_UTILSATEUR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUtilisateur() throws Exception {
        // Get the utilisateur
        restUtilisateurMockMvc.perform(get("/api/utilisateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUtilisateur() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        int databaseSizeBeforeUpdate = utilisateurRepository.findAll().size();

        // Update the utilisateur
        Utilisateur updatedUtilisateur = utilisateurRepository.findById(utilisateur.getId()).get();
        // Disconnect from session so that the updates on updatedUtilisateur are not directly saved in db
        em.detach(updatedUtilisateur);
        updatedUtilisateur
            .idUtilisateur(UPDATED_ID_UTILISATEUR)
            .loginUtilisateur(UPDATED_LOGIN_UTILISATEUR)
            .matriculeUtilisateur(UPDATED_MATRICULE_UTILISATEUR)
            .nomUtilsateur(UPDATED_NOM_UTILSATEUR)
            .passwordUtilisateur(UPDATED_PASSWORD_UTILISATEUR)
            .prenomUtilsateur(UPDATED_PRENOM_UTILSATEUR);
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(updatedUtilisateur);

        restUtilisateurMockMvc.perform(put("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isOk());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeUpdate);
        Utilisateur testUtilisateur = utilisateurList.get(utilisateurList.size() - 1);
        assertThat(testUtilisateur.getIdUtilisateur()).isEqualTo(UPDATED_ID_UTILISATEUR);
        assertThat(testUtilisateur.getLoginUtilisateur()).isEqualTo(UPDATED_LOGIN_UTILISATEUR);
        assertThat(testUtilisateur.getMatriculeUtilisateur()).isEqualTo(UPDATED_MATRICULE_UTILISATEUR);
        assertThat(testUtilisateur.getNomUtilsateur()).isEqualTo(UPDATED_NOM_UTILSATEUR);
        assertThat(testUtilisateur.getPasswordUtilisateur()).isEqualTo(UPDATED_PASSWORD_UTILISATEUR);
        assertThat(testUtilisateur.getPrenomUtilsateur()).isEqualTo(UPDATED_PRENOM_UTILSATEUR);

        // Validate the Utilisateur in Elasticsearch
        verify(mockUtilisateurSearchRepository, times(1)).save(testUtilisateur);
    }

    @Test
    @Transactional
    public void updateNonExistingUtilisateur() throws Exception {
        int databaseSizeBeforeUpdate = utilisateurRepository.findAll().size();

        // Create the Utilisateur
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUtilisateurMockMvc.perform(put("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Utilisateur in Elasticsearch
        verify(mockUtilisateurSearchRepository, times(0)).save(utilisateur);
    }

    @Test
    @Transactional
    public void deleteUtilisateur() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        int databaseSizeBeforeDelete = utilisateurRepository.findAll().size();

        // Get the utilisateur
        restUtilisateurMockMvc.perform(delete("/api/utilisateurs/{id}", utilisateur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Utilisateur in Elasticsearch
        verify(mockUtilisateurSearchRepository, times(1)).deleteById(utilisateur.getId());
    }

    @Test
    @Transactional
    public void searchUtilisateur() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);
        when(mockUtilisateurSearchRepository.search(queryStringQuery("id:" + utilisateur.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(utilisateur), PageRequest.of(0, 1), 1));
        // Search the utilisateur
        restUtilisateurMockMvc.perform(get("/api/_search/utilisateurs?query=id:" + utilisateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUtilisateur").value(hasItem(DEFAULT_ID_UTILISATEUR)))
            .andExpect(jsonPath("$.[*].loginUtilisateur").value(hasItem(DEFAULT_LOGIN_UTILISATEUR)))
            .andExpect(jsonPath("$.[*].matriculeUtilisateur").value(hasItem(DEFAULT_MATRICULE_UTILISATEUR)))
            .andExpect(jsonPath("$.[*].nomUtilsateur").value(hasItem(DEFAULT_NOM_UTILSATEUR)))
            .andExpect(jsonPath("$.[*].passwordUtilisateur").value(hasItem(DEFAULT_PASSWORD_UTILISATEUR)))
            .andExpect(jsonPath("$.[*].prenomUtilsateur").value(hasItem(DEFAULT_PRENOM_UTILSATEUR)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Utilisateur.class);
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setId(1L);
        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setId(utilisateur1.getId());
        assertThat(utilisateur1).isEqualTo(utilisateur2);
        utilisateur2.setId(2L);
        assertThat(utilisateur1).isNotEqualTo(utilisateur2);
        utilisateur1.setId(null);
        assertThat(utilisateur1).isNotEqualTo(utilisateur2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisateurDTO.class);
        UtilisateurDTO utilisateurDTO1 = new UtilisateurDTO();
        utilisateurDTO1.setId(1L);
        UtilisateurDTO utilisateurDTO2 = new UtilisateurDTO();
        assertThat(utilisateurDTO1).isNotEqualTo(utilisateurDTO2);
        utilisateurDTO2.setId(utilisateurDTO1.getId());
        assertThat(utilisateurDTO1).isEqualTo(utilisateurDTO2);
        utilisateurDTO2.setId(2L);
        assertThat(utilisateurDTO1).isNotEqualTo(utilisateurDTO2);
        utilisateurDTO1.setId(null);
        assertThat(utilisateurDTO1).isNotEqualTo(utilisateurDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(utilisateurMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(utilisateurMapper.fromId(null)).isNull();
    }
}
