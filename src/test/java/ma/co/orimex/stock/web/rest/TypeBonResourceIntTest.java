package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.TypeBon;
import ma.co.orimex.stock.repository.TypeBonRepository;
import ma.co.orimex.stock.repository.search.TypeBonSearchRepository;
import ma.co.orimex.stock.service.TypeBonService;
import ma.co.orimex.stock.service.dto.TypeBonDTO;
import ma.co.orimex.stock.service.mapper.TypeBonMapper;
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
 * Test class for the TypeBonResource REST controller.
 *
 * @see TypeBonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class TypeBonResourceIntTest {

    private static final Integer DEFAULT_ID_TYPE_BON = 1;
    private static final Integer UPDATED_ID_TYPE_BON = 2;

    private static final String DEFAULT_LIBELLE_TYPE_BON = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_TYPE_BON = "BBBBBBBBBB";

    @Autowired
    private TypeBonRepository typeBonRepository;

    @Autowired
    private TypeBonMapper typeBonMapper;

    @Autowired
    private TypeBonService typeBonService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.TypeBonSearchRepositoryMockConfiguration
     */
    @Autowired
    private TypeBonSearchRepository mockTypeBonSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTypeBonMockMvc;

    private TypeBon typeBon;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeBonResource typeBonResource = new TypeBonResource(typeBonService);
        this.restTypeBonMockMvc = MockMvcBuilders.standaloneSetup(typeBonResource)
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
    public static TypeBon createEntity(EntityManager em) {
        TypeBon typeBon = new TypeBon()
            .idTypeBon(DEFAULT_ID_TYPE_BON)
            .libelleTypeBon(DEFAULT_LIBELLE_TYPE_BON);
        return typeBon;
    }

    @Before
    public void initTest() {
        typeBon = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeBon() throws Exception {
        int databaseSizeBeforeCreate = typeBonRepository.findAll().size();

        // Create the TypeBon
        TypeBonDTO typeBonDTO = typeBonMapper.toDto(typeBon);
        restTypeBonMockMvc.perform(post("/api/type-bons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeBonDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeBon in the database
        List<TypeBon> typeBonList = typeBonRepository.findAll();
        assertThat(typeBonList).hasSize(databaseSizeBeforeCreate + 1);
        TypeBon testTypeBon = typeBonList.get(typeBonList.size() - 1);
        assertThat(testTypeBon.getIdTypeBon()).isEqualTo(DEFAULT_ID_TYPE_BON);
        assertThat(testTypeBon.getLibelleTypeBon()).isEqualTo(DEFAULT_LIBELLE_TYPE_BON);

        // Validate the TypeBon in Elasticsearch
        verify(mockTypeBonSearchRepository, times(1)).save(testTypeBon);
    }

    @Test
    @Transactional
    public void createTypeBonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeBonRepository.findAll().size();

        // Create the TypeBon with an existing ID
        typeBon.setId(1L);
        TypeBonDTO typeBonDTO = typeBonMapper.toDto(typeBon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeBonMockMvc.perform(post("/api/type-bons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeBonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeBon in the database
        List<TypeBon> typeBonList = typeBonRepository.findAll();
        assertThat(typeBonList).hasSize(databaseSizeBeforeCreate);

        // Validate the TypeBon in Elasticsearch
        verify(mockTypeBonSearchRepository, times(0)).save(typeBon);
    }

    @Test
    @Transactional
    public void getAllTypeBons() throws Exception {
        // Initialize the database
        typeBonRepository.saveAndFlush(typeBon);

        // Get all the typeBonList
        restTypeBonMockMvc.perform(get("/api/type-bons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeBon.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTypeBon").value(hasItem(DEFAULT_ID_TYPE_BON)))
            .andExpect(jsonPath("$.[*].libelleTypeBon").value(hasItem(DEFAULT_LIBELLE_TYPE_BON.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeBon() throws Exception {
        // Initialize the database
        typeBonRepository.saveAndFlush(typeBon);

        // Get the typeBon
        restTypeBonMockMvc.perform(get("/api/type-bons/{id}", typeBon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeBon.getId().intValue()))
            .andExpect(jsonPath("$.idTypeBon").value(DEFAULT_ID_TYPE_BON))
            .andExpect(jsonPath("$.libelleTypeBon").value(DEFAULT_LIBELLE_TYPE_BON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeBon() throws Exception {
        // Get the typeBon
        restTypeBonMockMvc.perform(get("/api/type-bons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeBon() throws Exception {
        // Initialize the database
        typeBonRepository.saveAndFlush(typeBon);

        int databaseSizeBeforeUpdate = typeBonRepository.findAll().size();

        // Update the typeBon
        TypeBon updatedTypeBon = typeBonRepository.findById(typeBon.getId()).get();
        // Disconnect from session so that the updates on updatedTypeBon are not directly saved in db
        em.detach(updatedTypeBon);
        updatedTypeBon
            .idTypeBon(UPDATED_ID_TYPE_BON)
            .libelleTypeBon(UPDATED_LIBELLE_TYPE_BON);
        TypeBonDTO typeBonDTO = typeBonMapper.toDto(updatedTypeBon);

        restTypeBonMockMvc.perform(put("/api/type-bons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeBonDTO)))
            .andExpect(status().isOk());

        // Validate the TypeBon in the database
        List<TypeBon> typeBonList = typeBonRepository.findAll();
        assertThat(typeBonList).hasSize(databaseSizeBeforeUpdate);
        TypeBon testTypeBon = typeBonList.get(typeBonList.size() - 1);
        assertThat(testTypeBon.getIdTypeBon()).isEqualTo(UPDATED_ID_TYPE_BON);
        assertThat(testTypeBon.getLibelleTypeBon()).isEqualTo(UPDATED_LIBELLE_TYPE_BON);

        // Validate the TypeBon in Elasticsearch
        verify(mockTypeBonSearchRepository, times(1)).save(testTypeBon);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeBon() throws Exception {
        int databaseSizeBeforeUpdate = typeBonRepository.findAll().size();

        // Create the TypeBon
        TypeBonDTO typeBonDTO = typeBonMapper.toDto(typeBon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeBonMockMvc.perform(put("/api/type-bons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeBonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeBon in the database
        List<TypeBon> typeBonList = typeBonRepository.findAll();
        assertThat(typeBonList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TypeBon in Elasticsearch
        verify(mockTypeBonSearchRepository, times(0)).save(typeBon);
    }

    @Test
    @Transactional
    public void deleteTypeBon() throws Exception {
        // Initialize the database
        typeBonRepository.saveAndFlush(typeBon);

        int databaseSizeBeforeDelete = typeBonRepository.findAll().size();

        // Get the typeBon
        restTypeBonMockMvc.perform(delete("/api/type-bons/{id}", typeBon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeBon> typeBonList = typeBonRepository.findAll();
        assertThat(typeBonList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TypeBon in Elasticsearch
        verify(mockTypeBonSearchRepository, times(1)).deleteById(typeBon.getId());
    }

    @Test
    @Transactional
    public void searchTypeBon() throws Exception {
        // Initialize the database
        typeBonRepository.saveAndFlush(typeBon);
        when(mockTypeBonSearchRepository.search(queryStringQuery("id:" + typeBon.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(typeBon), PageRequest.of(0, 1), 1));
        // Search the typeBon
        restTypeBonMockMvc.perform(get("/api/_search/type-bons?query=id:" + typeBon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeBon.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTypeBon").value(hasItem(DEFAULT_ID_TYPE_BON)))
            .andExpect(jsonPath("$.[*].libelleTypeBon").value(hasItem(DEFAULT_LIBELLE_TYPE_BON)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeBon.class);
        TypeBon typeBon1 = new TypeBon();
        typeBon1.setId(1L);
        TypeBon typeBon2 = new TypeBon();
        typeBon2.setId(typeBon1.getId());
        assertThat(typeBon1).isEqualTo(typeBon2);
        typeBon2.setId(2L);
        assertThat(typeBon1).isNotEqualTo(typeBon2);
        typeBon1.setId(null);
        assertThat(typeBon1).isNotEqualTo(typeBon2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeBonDTO.class);
        TypeBonDTO typeBonDTO1 = new TypeBonDTO();
        typeBonDTO1.setId(1L);
        TypeBonDTO typeBonDTO2 = new TypeBonDTO();
        assertThat(typeBonDTO1).isNotEqualTo(typeBonDTO2);
        typeBonDTO2.setId(typeBonDTO1.getId());
        assertThat(typeBonDTO1).isEqualTo(typeBonDTO2);
        typeBonDTO2.setId(2L);
        assertThat(typeBonDTO1).isNotEqualTo(typeBonDTO2);
        typeBonDTO1.setId(null);
        assertThat(typeBonDTO1).isNotEqualTo(typeBonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(typeBonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(typeBonMapper.fromId(null)).isNull();
    }
}
