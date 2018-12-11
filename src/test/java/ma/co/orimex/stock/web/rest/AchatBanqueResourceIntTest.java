package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatBanque;
import ma.co.orimex.stock.repository.AchatBanqueRepository;
import ma.co.orimex.stock.repository.search.AchatBanqueSearchRepository;
import ma.co.orimex.stock.service.AchatBanqueService;
import ma.co.orimex.stock.service.dto.AchatBanqueDTO;
import ma.co.orimex.stock.service.mapper.AchatBanqueMapper;
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
 * Test class for the AchatBanqueResource REST controller.
 *
 * @see AchatBanqueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatBanqueResourceIntTest {

    private static final Integer DEFAULT_ID_BANQUE = 1;
    private static final Integer UPDATED_ID_BANQUE = 2;

    private static final String DEFAULT_CODE_BANQUE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BANQUE = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_BANQUE = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_BANQUE = "BBBBBBBBBB";

    @Autowired
    private AchatBanqueRepository achatBanqueRepository;

    @Autowired
    private AchatBanqueMapper achatBanqueMapper;

    @Autowired
    private AchatBanqueService achatBanqueService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatBanqueSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatBanqueSearchRepository mockAchatBanqueSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatBanqueMockMvc;

    private AchatBanque achatBanque;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatBanqueResource achatBanqueResource = new AchatBanqueResource(achatBanqueService);
        this.restAchatBanqueMockMvc = MockMvcBuilders.standaloneSetup(achatBanqueResource)
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
    public static AchatBanque createEntity(EntityManager em) {
        AchatBanque achatBanque = new AchatBanque()
            .idBanque(DEFAULT_ID_BANQUE)
            .codeBanque(DEFAULT_CODE_BANQUE)
            .designationBanque(DEFAULT_DESIGNATION_BANQUE);
        return achatBanque;
    }

    @Before
    public void initTest() {
        achatBanque = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatBanque() throws Exception {
        int databaseSizeBeforeCreate = achatBanqueRepository.findAll().size();

        // Create the AchatBanque
        AchatBanqueDTO achatBanqueDTO = achatBanqueMapper.toDto(achatBanque);
        restAchatBanqueMockMvc.perform(post("/api/achat-banques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatBanqueDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatBanque in the database
        List<AchatBanque> achatBanqueList = achatBanqueRepository.findAll();
        assertThat(achatBanqueList).hasSize(databaseSizeBeforeCreate + 1);
        AchatBanque testAchatBanque = achatBanqueList.get(achatBanqueList.size() - 1);
        assertThat(testAchatBanque.getIdBanque()).isEqualTo(DEFAULT_ID_BANQUE);
        assertThat(testAchatBanque.getCodeBanque()).isEqualTo(DEFAULT_CODE_BANQUE);
        assertThat(testAchatBanque.getDesignationBanque()).isEqualTo(DEFAULT_DESIGNATION_BANQUE);

        // Validate the AchatBanque in Elasticsearch
        verify(mockAchatBanqueSearchRepository, times(1)).save(testAchatBanque);
    }

    @Test
    @Transactional
    public void createAchatBanqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatBanqueRepository.findAll().size();

        // Create the AchatBanque with an existing ID
        achatBanque.setId(1L);
        AchatBanqueDTO achatBanqueDTO = achatBanqueMapper.toDto(achatBanque);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatBanqueMockMvc.perform(post("/api/achat-banques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatBanqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatBanque in the database
        List<AchatBanque> achatBanqueList = achatBanqueRepository.findAll();
        assertThat(achatBanqueList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatBanque in Elasticsearch
        verify(mockAchatBanqueSearchRepository, times(0)).save(achatBanque);
    }

    @Test
    @Transactional
    public void getAllAchatBanques() throws Exception {
        // Initialize the database
        achatBanqueRepository.saveAndFlush(achatBanque);

        // Get all the achatBanqueList
        restAchatBanqueMockMvc.perform(get("/api/achat-banques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatBanque.getId().intValue())))
            .andExpect(jsonPath("$.[*].idBanque").value(hasItem(DEFAULT_ID_BANQUE)))
            .andExpect(jsonPath("$.[*].codeBanque").value(hasItem(DEFAULT_CODE_BANQUE.toString())))
            .andExpect(jsonPath("$.[*].designationBanque").value(hasItem(DEFAULT_DESIGNATION_BANQUE.toString())));
    }
    
    @Test
    @Transactional
    public void getAchatBanque() throws Exception {
        // Initialize the database
        achatBanqueRepository.saveAndFlush(achatBanque);

        // Get the achatBanque
        restAchatBanqueMockMvc.perform(get("/api/achat-banques/{id}", achatBanque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatBanque.getId().intValue()))
            .andExpect(jsonPath("$.idBanque").value(DEFAULT_ID_BANQUE))
            .andExpect(jsonPath("$.codeBanque").value(DEFAULT_CODE_BANQUE.toString()))
            .andExpect(jsonPath("$.designationBanque").value(DEFAULT_DESIGNATION_BANQUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatBanque() throws Exception {
        // Get the achatBanque
        restAchatBanqueMockMvc.perform(get("/api/achat-banques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatBanque() throws Exception {
        // Initialize the database
        achatBanqueRepository.saveAndFlush(achatBanque);

        int databaseSizeBeforeUpdate = achatBanqueRepository.findAll().size();

        // Update the achatBanque
        AchatBanque updatedAchatBanque = achatBanqueRepository.findById(achatBanque.getId()).get();
        // Disconnect from session so that the updates on updatedAchatBanque are not directly saved in db
        em.detach(updatedAchatBanque);
        updatedAchatBanque
            .idBanque(UPDATED_ID_BANQUE)
            .codeBanque(UPDATED_CODE_BANQUE)
            .designationBanque(UPDATED_DESIGNATION_BANQUE);
        AchatBanqueDTO achatBanqueDTO = achatBanqueMapper.toDto(updatedAchatBanque);

        restAchatBanqueMockMvc.perform(put("/api/achat-banques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatBanqueDTO)))
            .andExpect(status().isOk());

        // Validate the AchatBanque in the database
        List<AchatBanque> achatBanqueList = achatBanqueRepository.findAll();
        assertThat(achatBanqueList).hasSize(databaseSizeBeforeUpdate);
        AchatBanque testAchatBanque = achatBanqueList.get(achatBanqueList.size() - 1);
        assertThat(testAchatBanque.getIdBanque()).isEqualTo(UPDATED_ID_BANQUE);
        assertThat(testAchatBanque.getCodeBanque()).isEqualTo(UPDATED_CODE_BANQUE);
        assertThat(testAchatBanque.getDesignationBanque()).isEqualTo(UPDATED_DESIGNATION_BANQUE);

        // Validate the AchatBanque in Elasticsearch
        verify(mockAchatBanqueSearchRepository, times(1)).save(testAchatBanque);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatBanque() throws Exception {
        int databaseSizeBeforeUpdate = achatBanqueRepository.findAll().size();

        // Create the AchatBanque
        AchatBanqueDTO achatBanqueDTO = achatBanqueMapper.toDto(achatBanque);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatBanqueMockMvc.perform(put("/api/achat-banques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatBanqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatBanque in the database
        List<AchatBanque> achatBanqueList = achatBanqueRepository.findAll();
        assertThat(achatBanqueList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatBanque in Elasticsearch
        verify(mockAchatBanqueSearchRepository, times(0)).save(achatBanque);
    }

    @Test
    @Transactional
    public void deleteAchatBanque() throws Exception {
        // Initialize the database
        achatBanqueRepository.saveAndFlush(achatBanque);

        int databaseSizeBeforeDelete = achatBanqueRepository.findAll().size();

        // Get the achatBanque
        restAchatBanqueMockMvc.perform(delete("/api/achat-banques/{id}", achatBanque.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatBanque> achatBanqueList = achatBanqueRepository.findAll();
        assertThat(achatBanqueList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatBanque in Elasticsearch
        verify(mockAchatBanqueSearchRepository, times(1)).deleteById(achatBanque.getId());
    }

    @Test
    @Transactional
    public void searchAchatBanque() throws Exception {
        // Initialize the database
        achatBanqueRepository.saveAndFlush(achatBanque);
        when(mockAchatBanqueSearchRepository.search(queryStringQuery("id:" + achatBanque.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatBanque), PageRequest.of(0, 1), 1));
        // Search the achatBanque
        restAchatBanqueMockMvc.perform(get("/api/_search/achat-banques?query=id:" + achatBanque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatBanque.getId().intValue())))
            .andExpect(jsonPath("$.[*].idBanque").value(hasItem(DEFAULT_ID_BANQUE)))
            .andExpect(jsonPath("$.[*].codeBanque").value(hasItem(DEFAULT_CODE_BANQUE)))
            .andExpect(jsonPath("$.[*].designationBanque").value(hasItem(DEFAULT_DESIGNATION_BANQUE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatBanque.class);
        AchatBanque achatBanque1 = new AchatBanque();
        achatBanque1.setId(1L);
        AchatBanque achatBanque2 = new AchatBanque();
        achatBanque2.setId(achatBanque1.getId());
        assertThat(achatBanque1).isEqualTo(achatBanque2);
        achatBanque2.setId(2L);
        assertThat(achatBanque1).isNotEqualTo(achatBanque2);
        achatBanque1.setId(null);
        assertThat(achatBanque1).isNotEqualTo(achatBanque2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatBanqueDTO.class);
        AchatBanqueDTO achatBanqueDTO1 = new AchatBanqueDTO();
        achatBanqueDTO1.setId(1L);
        AchatBanqueDTO achatBanqueDTO2 = new AchatBanqueDTO();
        assertThat(achatBanqueDTO1).isNotEqualTo(achatBanqueDTO2);
        achatBanqueDTO2.setId(achatBanqueDTO1.getId());
        assertThat(achatBanqueDTO1).isEqualTo(achatBanqueDTO2);
        achatBanqueDTO2.setId(2L);
        assertThat(achatBanqueDTO1).isNotEqualTo(achatBanqueDTO2);
        achatBanqueDTO1.setId(null);
        assertThat(achatBanqueDTO1).isNotEqualTo(achatBanqueDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatBanqueMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatBanqueMapper.fromId(null)).isNull();
    }
}
