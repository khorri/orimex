package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatConteneurReception;
import ma.co.orimex.stock.repository.AchatConteneurReceptionRepository;
import ma.co.orimex.stock.repository.search.AchatConteneurReceptionSearchRepository;
import ma.co.orimex.stock.service.AchatConteneurReceptionService;
import ma.co.orimex.stock.service.dto.AchatConteneurReceptionDTO;
import ma.co.orimex.stock.service.mapper.AchatConteneurReceptionMapper;
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
 * Test class for the AchatConteneurReceptionResource REST controller.
 *
 * @see AchatConteneurReceptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatConteneurReceptionResourceIntTest {

    private static final Integer DEFAULT_ID_CONTENEUR_RECEPTION = 1;
    private static final Integer UPDATED_ID_CONTENEUR_RECEPTION = 2;

    private static final String DEFAULT_NUMERO_CONTENEUR = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CONTENEUR = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_SEQUENCE = 1;
    private static final Integer UPDATED_NUMERO_SEQUENCE = 2;

    @Autowired
    private AchatConteneurReceptionRepository achatConteneurReceptionRepository;

    @Autowired
    private AchatConteneurReceptionMapper achatConteneurReceptionMapper;

    @Autowired
    private AchatConteneurReceptionService achatConteneurReceptionService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatConteneurReceptionSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatConteneurReceptionSearchRepository mockAchatConteneurReceptionSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatConteneurReceptionMockMvc;

    private AchatConteneurReception achatConteneurReception;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatConteneurReceptionResource achatConteneurReceptionResource = new AchatConteneurReceptionResource(achatConteneurReceptionService);
        this.restAchatConteneurReceptionMockMvc = MockMvcBuilders.standaloneSetup(achatConteneurReceptionResource)
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
    public static AchatConteneurReception createEntity(EntityManager em) {
        AchatConteneurReception achatConteneurReception = new AchatConteneurReception()
            .idConteneurReception(DEFAULT_ID_CONTENEUR_RECEPTION)
            .numeroConteneur(DEFAULT_NUMERO_CONTENEUR)
            .numeroSequence(DEFAULT_NUMERO_SEQUENCE);
        return achatConteneurReception;
    }

    @Before
    public void initTest() {
        achatConteneurReception = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatConteneurReception() throws Exception {
        int databaseSizeBeforeCreate = achatConteneurReceptionRepository.findAll().size();

        // Create the AchatConteneurReception
        AchatConteneurReceptionDTO achatConteneurReceptionDTO = achatConteneurReceptionMapper.toDto(achatConteneurReception);
        restAchatConteneurReceptionMockMvc.perform(post("/api/achat-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatConteneurReceptionDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatConteneurReception in the database
        List<AchatConteneurReception> achatConteneurReceptionList = achatConteneurReceptionRepository.findAll();
        assertThat(achatConteneurReceptionList).hasSize(databaseSizeBeforeCreate + 1);
        AchatConteneurReception testAchatConteneurReception = achatConteneurReceptionList.get(achatConteneurReceptionList.size() - 1);
        assertThat(testAchatConteneurReception.getIdConteneurReception()).isEqualTo(DEFAULT_ID_CONTENEUR_RECEPTION);
        assertThat(testAchatConteneurReception.getNumeroConteneur()).isEqualTo(DEFAULT_NUMERO_CONTENEUR);
        assertThat(testAchatConteneurReception.getNumeroSequence()).isEqualTo(DEFAULT_NUMERO_SEQUENCE);

        // Validate the AchatConteneurReception in Elasticsearch
        verify(mockAchatConteneurReceptionSearchRepository, times(1)).save(testAchatConteneurReception);
    }

    @Test
    @Transactional
    public void createAchatConteneurReceptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatConteneurReceptionRepository.findAll().size();

        // Create the AchatConteneurReception with an existing ID
        achatConteneurReception.setId(1L);
        AchatConteneurReceptionDTO achatConteneurReceptionDTO = achatConteneurReceptionMapper.toDto(achatConteneurReception);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatConteneurReceptionMockMvc.perform(post("/api/achat-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatConteneurReceptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatConteneurReception in the database
        List<AchatConteneurReception> achatConteneurReceptionList = achatConteneurReceptionRepository.findAll();
        assertThat(achatConteneurReceptionList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatConteneurReception in Elasticsearch
        verify(mockAchatConteneurReceptionSearchRepository, times(0)).save(achatConteneurReception);
    }

    @Test
    @Transactional
    public void getAllAchatConteneurReceptions() throws Exception {
        // Initialize the database
        achatConteneurReceptionRepository.saveAndFlush(achatConteneurReception);

        // Get all the achatConteneurReceptionList
        restAchatConteneurReceptionMockMvc.perform(get("/api/achat-conteneur-receptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatConteneurReception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConteneurReception").value(hasItem(DEFAULT_ID_CONTENEUR_RECEPTION)))
            .andExpect(jsonPath("$.[*].numeroConteneur").value(hasItem(DEFAULT_NUMERO_CONTENEUR.toString())))
            .andExpect(jsonPath("$.[*].numeroSequence").value(hasItem(DEFAULT_NUMERO_SEQUENCE)));
    }
    
    @Test
    @Transactional
    public void getAchatConteneurReception() throws Exception {
        // Initialize the database
        achatConteneurReceptionRepository.saveAndFlush(achatConteneurReception);

        // Get the achatConteneurReception
        restAchatConteneurReceptionMockMvc.perform(get("/api/achat-conteneur-receptions/{id}", achatConteneurReception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatConteneurReception.getId().intValue()))
            .andExpect(jsonPath("$.idConteneurReception").value(DEFAULT_ID_CONTENEUR_RECEPTION))
            .andExpect(jsonPath("$.numeroConteneur").value(DEFAULT_NUMERO_CONTENEUR.toString()))
            .andExpect(jsonPath("$.numeroSequence").value(DEFAULT_NUMERO_SEQUENCE));
    }

    @Test
    @Transactional
    public void getNonExistingAchatConteneurReception() throws Exception {
        // Get the achatConteneurReception
        restAchatConteneurReceptionMockMvc.perform(get("/api/achat-conteneur-receptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatConteneurReception() throws Exception {
        // Initialize the database
        achatConteneurReceptionRepository.saveAndFlush(achatConteneurReception);

        int databaseSizeBeforeUpdate = achatConteneurReceptionRepository.findAll().size();

        // Update the achatConteneurReception
        AchatConteneurReception updatedAchatConteneurReception = achatConteneurReceptionRepository.findById(achatConteneurReception.getId()).get();
        // Disconnect from session so that the updates on updatedAchatConteneurReception are not directly saved in db
        em.detach(updatedAchatConteneurReception);
        updatedAchatConteneurReception
            .idConteneurReception(UPDATED_ID_CONTENEUR_RECEPTION)
            .numeroConteneur(UPDATED_NUMERO_CONTENEUR)
            .numeroSequence(UPDATED_NUMERO_SEQUENCE);
        AchatConteneurReceptionDTO achatConteneurReceptionDTO = achatConteneurReceptionMapper.toDto(updatedAchatConteneurReception);

        restAchatConteneurReceptionMockMvc.perform(put("/api/achat-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatConteneurReceptionDTO)))
            .andExpect(status().isOk());

        // Validate the AchatConteneurReception in the database
        List<AchatConteneurReception> achatConteneurReceptionList = achatConteneurReceptionRepository.findAll();
        assertThat(achatConteneurReceptionList).hasSize(databaseSizeBeforeUpdate);
        AchatConteneurReception testAchatConteneurReception = achatConteneurReceptionList.get(achatConteneurReceptionList.size() - 1);
        assertThat(testAchatConteneurReception.getIdConteneurReception()).isEqualTo(UPDATED_ID_CONTENEUR_RECEPTION);
        assertThat(testAchatConteneurReception.getNumeroConteneur()).isEqualTo(UPDATED_NUMERO_CONTENEUR);
        assertThat(testAchatConteneurReception.getNumeroSequence()).isEqualTo(UPDATED_NUMERO_SEQUENCE);

        // Validate the AchatConteneurReception in Elasticsearch
        verify(mockAchatConteneurReceptionSearchRepository, times(1)).save(testAchatConteneurReception);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatConteneurReception() throws Exception {
        int databaseSizeBeforeUpdate = achatConteneurReceptionRepository.findAll().size();

        // Create the AchatConteneurReception
        AchatConteneurReceptionDTO achatConteneurReceptionDTO = achatConteneurReceptionMapper.toDto(achatConteneurReception);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatConteneurReceptionMockMvc.perform(put("/api/achat-conteneur-receptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatConteneurReceptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatConteneurReception in the database
        List<AchatConteneurReception> achatConteneurReceptionList = achatConteneurReceptionRepository.findAll();
        assertThat(achatConteneurReceptionList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatConteneurReception in Elasticsearch
        verify(mockAchatConteneurReceptionSearchRepository, times(0)).save(achatConteneurReception);
    }

    @Test
    @Transactional
    public void deleteAchatConteneurReception() throws Exception {
        // Initialize the database
        achatConteneurReceptionRepository.saveAndFlush(achatConteneurReception);

        int databaseSizeBeforeDelete = achatConteneurReceptionRepository.findAll().size();

        // Get the achatConteneurReception
        restAchatConteneurReceptionMockMvc.perform(delete("/api/achat-conteneur-receptions/{id}", achatConteneurReception.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatConteneurReception> achatConteneurReceptionList = achatConteneurReceptionRepository.findAll();
        assertThat(achatConteneurReceptionList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatConteneurReception in Elasticsearch
        verify(mockAchatConteneurReceptionSearchRepository, times(1)).deleteById(achatConteneurReception.getId());
    }

    @Test
    @Transactional
    public void searchAchatConteneurReception() throws Exception {
        // Initialize the database
        achatConteneurReceptionRepository.saveAndFlush(achatConteneurReception);
        when(mockAchatConteneurReceptionSearchRepository.search(queryStringQuery("id:" + achatConteneurReception.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatConteneurReception), PageRequest.of(0, 1), 1));
        // Search the achatConteneurReception
        restAchatConteneurReceptionMockMvc.perform(get("/api/_search/achat-conteneur-receptions?query=id:" + achatConteneurReception.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatConteneurReception.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConteneurReception").value(hasItem(DEFAULT_ID_CONTENEUR_RECEPTION)))
            .andExpect(jsonPath("$.[*].numeroConteneur").value(hasItem(DEFAULT_NUMERO_CONTENEUR)))
            .andExpect(jsonPath("$.[*].numeroSequence").value(hasItem(DEFAULT_NUMERO_SEQUENCE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatConteneurReception.class);
        AchatConteneurReception achatConteneurReception1 = new AchatConteneurReception();
        achatConteneurReception1.setId(1L);
        AchatConteneurReception achatConteneurReception2 = new AchatConteneurReception();
        achatConteneurReception2.setId(achatConteneurReception1.getId());
        assertThat(achatConteneurReception1).isEqualTo(achatConteneurReception2);
        achatConteneurReception2.setId(2L);
        assertThat(achatConteneurReception1).isNotEqualTo(achatConteneurReception2);
        achatConteneurReception1.setId(null);
        assertThat(achatConteneurReception1).isNotEqualTo(achatConteneurReception2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatConteneurReceptionDTO.class);
        AchatConteneurReceptionDTO achatConteneurReceptionDTO1 = new AchatConteneurReceptionDTO();
        achatConteneurReceptionDTO1.setId(1L);
        AchatConteneurReceptionDTO achatConteneurReceptionDTO2 = new AchatConteneurReceptionDTO();
        assertThat(achatConteneurReceptionDTO1).isNotEqualTo(achatConteneurReceptionDTO2);
        achatConteneurReceptionDTO2.setId(achatConteneurReceptionDTO1.getId());
        assertThat(achatConteneurReceptionDTO1).isEqualTo(achatConteneurReceptionDTO2);
        achatConteneurReceptionDTO2.setId(2L);
        assertThat(achatConteneurReceptionDTO1).isNotEqualTo(achatConteneurReceptionDTO2);
        achatConteneurReceptionDTO1.setId(null);
        assertThat(achatConteneurReceptionDTO1).isNotEqualTo(achatConteneurReceptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatConteneurReceptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatConteneurReceptionMapper.fromId(null)).isNull();
    }
}
