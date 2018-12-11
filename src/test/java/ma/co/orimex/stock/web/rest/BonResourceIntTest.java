package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Bon;
import ma.co.orimex.stock.repository.BonRepository;
import ma.co.orimex.stock.repository.search.BonSearchRepository;
import ma.co.orimex.stock.service.BonService;
import ma.co.orimex.stock.service.dto.BonDTO;
import ma.co.orimex.stock.service.mapper.BonMapper;
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
 * Test class for the BonResource REST controller.
 *
 * @see BonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class BonResourceIntTest {

    private static final Integer DEFAULT_ID_BON = 1;
    private static final Integer UPDATED_ID_BON = 2;

    private static final Integer DEFAULT_ID_TYPE_BON = 1;
    private static final Integer UPDATED_ID_TYPE_BON = 2;

    private static final String DEFAULT_NUMERO_BON = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_BON = "BBBBBBBBBB";

    @Autowired
    private BonRepository bonRepository;

    @Autowired
    private BonMapper bonMapper;

    @Autowired
    private BonService bonService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.BonSearchRepositoryMockConfiguration
     */
    @Autowired
    private BonSearchRepository mockBonSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBonMockMvc;

    private Bon bon;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BonResource bonResource = new BonResource(bonService);
        this.restBonMockMvc = MockMvcBuilders.standaloneSetup(bonResource)
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
    public static Bon createEntity(EntityManager em) {
        Bon bon = new Bon()
            .idBon(DEFAULT_ID_BON)
            .idTypeBon(DEFAULT_ID_TYPE_BON)
            .numeroBon(DEFAULT_NUMERO_BON);
        return bon;
    }

    @Before
    public void initTest() {
        bon = createEntity(em);
    }

    @Test
    @Transactional
    public void createBon() throws Exception {
        int databaseSizeBeforeCreate = bonRepository.findAll().size();

        // Create the Bon
        BonDTO bonDTO = bonMapper.toDto(bon);
        restBonMockMvc.perform(post("/api/bons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bonDTO)))
            .andExpect(status().isCreated());

        // Validate the Bon in the database
        List<Bon> bonList = bonRepository.findAll();
        assertThat(bonList).hasSize(databaseSizeBeforeCreate + 1);
        Bon testBon = bonList.get(bonList.size() - 1);
        assertThat(testBon.getIdBon()).isEqualTo(DEFAULT_ID_BON);
        assertThat(testBon.getIdTypeBon()).isEqualTo(DEFAULT_ID_TYPE_BON);
        assertThat(testBon.getNumeroBon()).isEqualTo(DEFAULT_NUMERO_BON);

        // Validate the Bon in Elasticsearch
        verify(mockBonSearchRepository, times(1)).save(testBon);
    }

    @Test
    @Transactional
    public void createBonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bonRepository.findAll().size();

        // Create the Bon with an existing ID
        bon.setId(1L);
        BonDTO bonDTO = bonMapper.toDto(bon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBonMockMvc.perform(post("/api/bons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bon in the database
        List<Bon> bonList = bonRepository.findAll();
        assertThat(bonList).hasSize(databaseSizeBeforeCreate);

        // Validate the Bon in Elasticsearch
        verify(mockBonSearchRepository, times(0)).save(bon);
    }

    @Test
    @Transactional
    public void getAllBons() throws Exception {
        // Initialize the database
        bonRepository.saveAndFlush(bon);

        // Get all the bonList
        restBonMockMvc.perform(get("/api/bons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bon.getId().intValue())))
            .andExpect(jsonPath("$.[*].idBon").value(hasItem(DEFAULT_ID_BON)))
            .andExpect(jsonPath("$.[*].idTypeBon").value(hasItem(DEFAULT_ID_TYPE_BON)))
            .andExpect(jsonPath("$.[*].numeroBon").value(hasItem(DEFAULT_NUMERO_BON.toString())));
    }
    
    @Test
    @Transactional
    public void getBon() throws Exception {
        // Initialize the database
        bonRepository.saveAndFlush(bon);

        // Get the bon
        restBonMockMvc.perform(get("/api/bons/{id}", bon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bon.getId().intValue()))
            .andExpect(jsonPath("$.idBon").value(DEFAULT_ID_BON))
            .andExpect(jsonPath("$.idTypeBon").value(DEFAULT_ID_TYPE_BON))
            .andExpect(jsonPath("$.numeroBon").value(DEFAULT_NUMERO_BON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBon() throws Exception {
        // Get the bon
        restBonMockMvc.perform(get("/api/bons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBon() throws Exception {
        // Initialize the database
        bonRepository.saveAndFlush(bon);

        int databaseSizeBeforeUpdate = bonRepository.findAll().size();

        // Update the bon
        Bon updatedBon = bonRepository.findById(bon.getId()).get();
        // Disconnect from session so that the updates on updatedBon are not directly saved in db
        em.detach(updatedBon);
        updatedBon
            .idBon(UPDATED_ID_BON)
            .idTypeBon(UPDATED_ID_TYPE_BON)
            .numeroBon(UPDATED_NUMERO_BON);
        BonDTO bonDTO = bonMapper.toDto(updatedBon);

        restBonMockMvc.perform(put("/api/bons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bonDTO)))
            .andExpect(status().isOk());

        // Validate the Bon in the database
        List<Bon> bonList = bonRepository.findAll();
        assertThat(bonList).hasSize(databaseSizeBeforeUpdate);
        Bon testBon = bonList.get(bonList.size() - 1);
        assertThat(testBon.getIdBon()).isEqualTo(UPDATED_ID_BON);
        assertThat(testBon.getIdTypeBon()).isEqualTo(UPDATED_ID_TYPE_BON);
        assertThat(testBon.getNumeroBon()).isEqualTo(UPDATED_NUMERO_BON);

        // Validate the Bon in Elasticsearch
        verify(mockBonSearchRepository, times(1)).save(testBon);
    }

    @Test
    @Transactional
    public void updateNonExistingBon() throws Exception {
        int databaseSizeBeforeUpdate = bonRepository.findAll().size();

        // Create the Bon
        BonDTO bonDTO = bonMapper.toDto(bon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBonMockMvc.perform(put("/api/bons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bon in the database
        List<Bon> bonList = bonRepository.findAll();
        assertThat(bonList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Bon in Elasticsearch
        verify(mockBonSearchRepository, times(0)).save(bon);
    }

    @Test
    @Transactional
    public void deleteBon() throws Exception {
        // Initialize the database
        bonRepository.saveAndFlush(bon);

        int databaseSizeBeforeDelete = bonRepository.findAll().size();

        // Get the bon
        restBonMockMvc.perform(delete("/api/bons/{id}", bon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Bon> bonList = bonRepository.findAll();
        assertThat(bonList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Bon in Elasticsearch
        verify(mockBonSearchRepository, times(1)).deleteById(bon.getId());
    }

    @Test
    @Transactional
    public void searchBon() throws Exception {
        // Initialize the database
        bonRepository.saveAndFlush(bon);
        when(mockBonSearchRepository.search(queryStringQuery("id:" + bon.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(bon), PageRequest.of(0, 1), 1));
        // Search the bon
        restBonMockMvc.perform(get("/api/_search/bons?query=id:" + bon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bon.getId().intValue())))
            .andExpect(jsonPath("$.[*].idBon").value(hasItem(DEFAULT_ID_BON)))
            .andExpect(jsonPath("$.[*].idTypeBon").value(hasItem(DEFAULT_ID_TYPE_BON)))
            .andExpect(jsonPath("$.[*].numeroBon").value(hasItem(DEFAULT_NUMERO_BON)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bon.class);
        Bon bon1 = new Bon();
        bon1.setId(1L);
        Bon bon2 = new Bon();
        bon2.setId(bon1.getId());
        assertThat(bon1).isEqualTo(bon2);
        bon2.setId(2L);
        assertThat(bon1).isNotEqualTo(bon2);
        bon1.setId(null);
        assertThat(bon1).isNotEqualTo(bon2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BonDTO.class);
        BonDTO bonDTO1 = new BonDTO();
        bonDTO1.setId(1L);
        BonDTO bonDTO2 = new BonDTO();
        assertThat(bonDTO1).isNotEqualTo(bonDTO2);
        bonDTO2.setId(bonDTO1.getId());
        assertThat(bonDTO1).isEqualTo(bonDTO2);
        bonDTO2.setId(2L);
        assertThat(bonDTO1).isNotEqualTo(bonDTO2);
        bonDTO1.setId(null);
        assertThat(bonDTO1).isNotEqualTo(bonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bonMapper.fromId(null)).isNull();
    }
}
