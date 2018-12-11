package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Caisse;
import ma.co.orimex.stock.repository.CaisseRepository;
import ma.co.orimex.stock.repository.search.CaisseSearchRepository;
import ma.co.orimex.stock.service.CaisseService;
import ma.co.orimex.stock.service.dto.CaisseDTO;
import ma.co.orimex.stock.service.mapper.CaisseMapper;
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
 * Test class for the CaisseResource REST controller.
 *
 * @see CaisseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class CaisseResourceIntTest {

    private static final Integer DEFAULT_ID_CAISSE = 1;
    private static final Integer UPDATED_ID_CAISSE = 2;

    private static final Integer DEFAULT_NOMBRE_PLATEAUX = 1;
    private static final Integer UPDATED_NOMBRE_PLATEAUX = 2;

    private static final String DEFAULT_NUMERO_CONTENEUR = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CONTENEUR = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_CAISSE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_CAISSE = "BBBBBBBBBB";

    @Autowired
    private CaisseRepository caisseRepository;

    @Autowired
    private CaisseMapper caisseMapper;

    @Autowired
    private CaisseService caisseService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.CaisseSearchRepositoryMockConfiguration
     */
    @Autowired
    private CaisseSearchRepository mockCaisseSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaisseMockMvc;

    private Caisse caisse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaisseResource caisseResource = new CaisseResource(caisseService);
        this.restCaisseMockMvc = MockMvcBuilders.standaloneSetup(caisseResource)
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
    public static Caisse createEntity(EntityManager em) {
        Caisse caisse = new Caisse()
            .idCaisse(DEFAULT_ID_CAISSE)
            .nombrePlateaux(DEFAULT_NOMBRE_PLATEAUX)
            .numeroConteneur(DEFAULT_NUMERO_CONTENEUR)
            .referenceCaisse(DEFAULT_REFERENCE_CAISSE);
        return caisse;
    }

    @Before
    public void initTest() {
        caisse = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaisse() throws Exception {
        int databaseSizeBeforeCreate = caisseRepository.findAll().size();

        // Create the Caisse
        CaisseDTO caisseDTO = caisseMapper.toDto(caisse);
        restCaisseMockMvc.perform(post("/api/caisses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caisseDTO)))
            .andExpect(status().isCreated());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeCreate + 1);
        Caisse testCaisse = caisseList.get(caisseList.size() - 1);
        assertThat(testCaisse.getIdCaisse()).isEqualTo(DEFAULT_ID_CAISSE);
        assertThat(testCaisse.getNombrePlateaux()).isEqualTo(DEFAULT_NOMBRE_PLATEAUX);
        assertThat(testCaisse.getNumeroConteneur()).isEqualTo(DEFAULT_NUMERO_CONTENEUR);
        assertThat(testCaisse.getReferenceCaisse()).isEqualTo(DEFAULT_REFERENCE_CAISSE);

        // Validate the Caisse in Elasticsearch
        verify(mockCaisseSearchRepository, times(1)).save(testCaisse);
    }

    @Test
    @Transactional
    public void createCaisseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caisseRepository.findAll().size();

        // Create the Caisse with an existing ID
        caisse.setId(1L);
        CaisseDTO caisseDTO = caisseMapper.toDto(caisse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaisseMockMvc.perform(post("/api/caisses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caisseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeCreate);

        // Validate the Caisse in Elasticsearch
        verify(mockCaisseSearchRepository, times(0)).save(caisse);
    }

    @Test
    @Transactional
    public void getAllCaisses() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);

        // Get all the caisseList
        restCaisseMockMvc.perform(get("/api/caisses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caisse.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCaisse").value(hasItem(DEFAULT_ID_CAISSE)))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroConteneur").value(hasItem(DEFAULT_NUMERO_CONTENEUR.toString())))
            .andExpect(jsonPath("$.[*].referenceCaisse").value(hasItem(DEFAULT_REFERENCE_CAISSE.toString())));
    }
    
    @Test
    @Transactional
    public void getCaisse() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);

        // Get the caisse
        restCaisseMockMvc.perform(get("/api/caisses/{id}", caisse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caisse.getId().intValue()))
            .andExpect(jsonPath("$.idCaisse").value(DEFAULT_ID_CAISSE))
            .andExpect(jsonPath("$.nombrePlateaux").value(DEFAULT_NOMBRE_PLATEAUX))
            .andExpect(jsonPath("$.numeroConteneur").value(DEFAULT_NUMERO_CONTENEUR.toString()))
            .andExpect(jsonPath("$.referenceCaisse").value(DEFAULT_REFERENCE_CAISSE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCaisse() throws Exception {
        // Get the caisse
        restCaisseMockMvc.perform(get("/api/caisses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaisse() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);

        int databaseSizeBeforeUpdate = caisseRepository.findAll().size();

        // Update the caisse
        Caisse updatedCaisse = caisseRepository.findById(caisse.getId()).get();
        // Disconnect from session so that the updates on updatedCaisse are not directly saved in db
        em.detach(updatedCaisse);
        updatedCaisse
            .idCaisse(UPDATED_ID_CAISSE)
            .nombrePlateaux(UPDATED_NOMBRE_PLATEAUX)
            .numeroConteneur(UPDATED_NUMERO_CONTENEUR)
            .referenceCaisse(UPDATED_REFERENCE_CAISSE);
        CaisseDTO caisseDTO = caisseMapper.toDto(updatedCaisse);

        restCaisseMockMvc.perform(put("/api/caisses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caisseDTO)))
            .andExpect(status().isOk());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeUpdate);
        Caisse testCaisse = caisseList.get(caisseList.size() - 1);
        assertThat(testCaisse.getIdCaisse()).isEqualTo(UPDATED_ID_CAISSE);
        assertThat(testCaisse.getNombrePlateaux()).isEqualTo(UPDATED_NOMBRE_PLATEAUX);
        assertThat(testCaisse.getNumeroConteneur()).isEqualTo(UPDATED_NUMERO_CONTENEUR);
        assertThat(testCaisse.getReferenceCaisse()).isEqualTo(UPDATED_REFERENCE_CAISSE);

        // Validate the Caisse in Elasticsearch
        verify(mockCaisseSearchRepository, times(1)).save(testCaisse);
    }

    @Test
    @Transactional
    public void updateNonExistingCaisse() throws Exception {
        int databaseSizeBeforeUpdate = caisseRepository.findAll().size();

        // Create the Caisse
        CaisseDTO caisseDTO = caisseMapper.toDto(caisse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaisseMockMvc.perform(put("/api/caisses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caisseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Caisse in Elasticsearch
        verify(mockCaisseSearchRepository, times(0)).save(caisse);
    }

    @Test
    @Transactional
    public void deleteCaisse() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);

        int databaseSizeBeforeDelete = caisseRepository.findAll().size();

        // Get the caisse
        restCaisseMockMvc.perform(delete("/api/caisses/{id}", caisse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Caisse in Elasticsearch
        verify(mockCaisseSearchRepository, times(1)).deleteById(caisse.getId());
    }

    @Test
    @Transactional
    public void searchCaisse() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);
        when(mockCaisseSearchRepository.search(queryStringQuery("id:" + caisse.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(caisse), PageRequest.of(0, 1), 1));
        // Search the caisse
        restCaisseMockMvc.perform(get("/api/_search/caisses?query=id:" + caisse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caisse.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCaisse").value(hasItem(DEFAULT_ID_CAISSE)))
            .andExpect(jsonPath("$.[*].nombrePlateaux").value(hasItem(DEFAULT_NOMBRE_PLATEAUX)))
            .andExpect(jsonPath("$.[*].numeroConteneur").value(hasItem(DEFAULT_NUMERO_CONTENEUR)))
            .andExpect(jsonPath("$.[*].referenceCaisse").value(hasItem(DEFAULT_REFERENCE_CAISSE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Caisse.class);
        Caisse caisse1 = new Caisse();
        caisse1.setId(1L);
        Caisse caisse2 = new Caisse();
        caisse2.setId(caisse1.getId());
        assertThat(caisse1).isEqualTo(caisse2);
        caisse2.setId(2L);
        assertThat(caisse1).isNotEqualTo(caisse2);
        caisse1.setId(null);
        assertThat(caisse1).isNotEqualTo(caisse2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaisseDTO.class);
        CaisseDTO caisseDTO1 = new CaisseDTO();
        caisseDTO1.setId(1L);
        CaisseDTO caisseDTO2 = new CaisseDTO();
        assertThat(caisseDTO1).isNotEqualTo(caisseDTO2);
        caisseDTO2.setId(caisseDTO1.getId());
        assertThat(caisseDTO1).isEqualTo(caisseDTO2);
        caisseDTO2.setId(2L);
        assertThat(caisseDTO1).isNotEqualTo(caisseDTO2);
        caisseDTO1.setId(null);
        assertThat(caisseDTO1).isNotEqualTo(caisseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caisseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caisseMapper.fromId(null)).isNull();
    }
}
