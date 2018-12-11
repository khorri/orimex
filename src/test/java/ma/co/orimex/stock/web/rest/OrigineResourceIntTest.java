package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Origine;
import ma.co.orimex.stock.repository.OrigineRepository;
import ma.co.orimex.stock.repository.search.OrigineSearchRepository;
import ma.co.orimex.stock.service.OrigineService;
import ma.co.orimex.stock.service.dto.OrigineDTO;
import ma.co.orimex.stock.service.mapper.OrigineMapper;
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
 * Test class for the OrigineResource REST controller.
 *
 * @see OrigineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class OrigineResourceIntTest {

    private static final Integer DEFAULT_ID_ORIGINE = 1;
    private static final Integer UPDATED_ID_ORIGINE = 2;

    private static final String DEFAULT_DESIGNATION_ORIGINE = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_ORIGINE = "BBBBBBBBBB";

    @Autowired
    private OrigineRepository origineRepository;

    @Autowired
    private OrigineMapper origineMapper;

    @Autowired
    private OrigineService origineService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.OrigineSearchRepositoryMockConfiguration
     */
    @Autowired
    private OrigineSearchRepository mockOrigineSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrigineMockMvc;

    private Origine origine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrigineResource origineResource = new OrigineResource(origineService);
        this.restOrigineMockMvc = MockMvcBuilders.standaloneSetup(origineResource)
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
    public static Origine createEntity(EntityManager em) {
        Origine origine = new Origine()
            .idOrigine(DEFAULT_ID_ORIGINE)
            .designationOrigine(DEFAULT_DESIGNATION_ORIGINE);
        return origine;
    }

    @Before
    public void initTest() {
        origine = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrigine() throws Exception {
        int databaseSizeBeforeCreate = origineRepository.findAll().size();

        // Create the Origine
        OrigineDTO origineDTO = origineMapper.toDto(origine);
        restOrigineMockMvc.perform(post("/api/origines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origineDTO)))
            .andExpect(status().isCreated());

        // Validate the Origine in the database
        List<Origine> origineList = origineRepository.findAll();
        assertThat(origineList).hasSize(databaseSizeBeforeCreate + 1);
        Origine testOrigine = origineList.get(origineList.size() - 1);
        assertThat(testOrigine.getIdOrigine()).isEqualTo(DEFAULT_ID_ORIGINE);
        assertThat(testOrigine.getDesignationOrigine()).isEqualTo(DEFAULT_DESIGNATION_ORIGINE);

        // Validate the Origine in Elasticsearch
        verify(mockOrigineSearchRepository, times(1)).save(testOrigine);
    }

    @Test
    @Transactional
    public void createOrigineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = origineRepository.findAll().size();

        // Create the Origine with an existing ID
        origine.setId(1L);
        OrigineDTO origineDTO = origineMapper.toDto(origine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrigineMockMvc.perform(post("/api/origines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Origine in the database
        List<Origine> origineList = origineRepository.findAll();
        assertThat(origineList).hasSize(databaseSizeBeforeCreate);

        // Validate the Origine in Elasticsearch
        verify(mockOrigineSearchRepository, times(0)).save(origine);
    }

    @Test
    @Transactional
    public void getAllOrigines() throws Exception {
        // Initialize the database
        origineRepository.saveAndFlush(origine);

        // Get all the origineList
        restOrigineMockMvc.perform(get("/api/origines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(origine.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrigine").value(hasItem(DEFAULT_ID_ORIGINE)))
            .andExpect(jsonPath("$.[*].designationOrigine").value(hasItem(DEFAULT_DESIGNATION_ORIGINE.toString())));
    }
    
    @Test
    @Transactional
    public void getOrigine() throws Exception {
        // Initialize the database
        origineRepository.saveAndFlush(origine);

        // Get the origine
        restOrigineMockMvc.perform(get("/api/origines/{id}", origine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(origine.getId().intValue()))
            .andExpect(jsonPath("$.idOrigine").value(DEFAULT_ID_ORIGINE))
            .andExpect(jsonPath("$.designationOrigine").value(DEFAULT_DESIGNATION_ORIGINE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrigine() throws Exception {
        // Get the origine
        restOrigineMockMvc.perform(get("/api/origines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrigine() throws Exception {
        // Initialize the database
        origineRepository.saveAndFlush(origine);

        int databaseSizeBeforeUpdate = origineRepository.findAll().size();

        // Update the origine
        Origine updatedOrigine = origineRepository.findById(origine.getId()).get();
        // Disconnect from session so that the updates on updatedOrigine are not directly saved in db
        em.detach(updatedOrigine);
        updatedOrigine
            .idOrigine(UPDATED_ID_ORIGINE)
            .designationOrigine(UPDATED_DESIGNATION_ORIGINE);
        OrigineDTO origineDTO = origineMapper.toDto(updatedOrigine);

        restOrigineMockMvc.perform(put("/api/origines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origineDTO)))
            .andExpect(status().isOk());

        // Validate the Origine in the database
        List<Origine> origineList = origineRepository.findAll();
        assertThat(origineList).hasSize(databaseSizeBeforeUpdate);
        Origine testOrigine = origineList.get(origineList.size() - 1);
        assertThat(testOrigine.getIdOrigine()).isEqualTo(UPDATED_ID_ORIGINE);
        assertThat(testOrigine.getDesignationOrigine()).isEqualTo(UPDATED_DESIGNATION_ORIGINE);

        // Validate the Origine in Elasticsearch
        verify(mockOrigineSearchRepository, times(1)).save(testOrigine);
    }

    @Test
    @Transactional
    public void updateNonExistingOrigine() throws Exception {
        int databaseSizeBeforeUpdate = origineRepository.findAll().size();

        // Create the Origine
        OrigineDTO origineDTO = origineMapper.toDto(origine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrigineMockMvc.perform(put("/api/origines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Origine in the database
        List<Origine> origineList = origineRepository.findAll();
        assertThat(origineList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Origine in Elasticsearch
        verify(mockOrigineSearchRepository, times(0)).save(origine);
    }

    @Test
    @Transactional
    public void deleteOrigine() throws Exception {
        // Initialize the database
        origineRepository.saveAndFlush(origine);

        int databaseSizeBeforeDelete = origineRepository.findAll().size();

        // Get the origine
        restOrigineMockMvc.perform(delete("/api/origines/{id}", origine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Origine> origineList = origineRepository.findAll();
        assertThat(origineList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Origine in Elasticsearch
        verify(mockOrigineSearchRepository, times(1)).deleteById(origine.getId());
    }

    @Test
    @Transactional
    public void searchOrigine() throws Exception {
        // Initialize the database
        origineRepository.saveAndFlush(origine);
        when(mockOrigineSearchRepository.search(queryStringQuery("id:" + origine.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(origine), PageRequest.of(0, 1), 1));
        // Search the origine
        restOrigineMockMvc.perform(get("/api/_search/origines?query=id:" + origine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(origine.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrigine").value(hasItem(DEFAULT_ID_ORIGINE)))
            .andExpect(jsonPath("$.[*].designationOrigine").value(hasItem(DEFAULT_DESIGNATION_ORIGINE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Origine.class);
        Origine origine1 = new Origine();
        origine1.setId(1L);
        Origine origine2 = new Origine();
        origine2.setId(origine1.getId());
        assertThat(origine1).isEqualTo(origine2);
        origine2.setId(2L);
        assertThat(origine1).isNotEqualTo(origine2);
        origine1.setId(null);
        assertThat(origine1).isNotEqualTo(origine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrigineDTO.class);
        OrigineDTO origineDTO1 = new OrigineDTO();
        origineDTO1.setId(1L);
        OrigineDTO origineDTO2 = new OrigineDTO();
        assertThat(origineDTO1).isNotEqualTo(origineDTO2);
        origineDTO2.setId(origineDTO1.getId());
        assertThat(origineDTO1).isEqualTo(origineDTO2);
        origineDTO2.setId(2L);
        assertThat(origineDTO1).isNotEqualTo(origineDTO2);
        origineDTO1.setId(null);
        assertThat(origineDTO1).isNotEqualTo(origineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(origineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(origineMapper.fromId(null)).isNull();
    }
}
