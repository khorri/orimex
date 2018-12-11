package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatLigneProforma;
import ma.co.orimex.stock.repository.AchatLigneProformaRepository;
import ma.co.orimex.stock.repository.search.AchatLigneProformaSearchRepository;
import ma.co.orimex.stock.service.AchatLigneProformaService;
import ma.co.orimex.stock.service.dto.AchatLigneProformaDTO;
import ma.co.orimex.stock.service.mapper.AchatLigneProformaMapper;
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
import java.math.BigDecimal;
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
 * Test class for the AchatLigneProformaResource REST controller.
 *
 * @see AchatLigneProformaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatLigneProformaResourceIntTest {

    private static final Integer DEFAULT_ID_LIGNE_PROFORMA = 1;
    private static final Integer UPDATED_ID_LIGNE_PROFORMA = 2;

    private static final Integer DEFAULT_NOMBRE_CONTENEURS = 1;
    private static final Integer UPDATED_NOMBRE_CONTENEURS = 2;

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(2);

    private static final Integer DEFAULT_NUMERO_SEQUENCE = 1;
    private static final Integer UPDATED_NUMERO_SEQUENCE = 2;

    private static final BigDecimal DEFAULT_POIDS = new BigDecimal(1);
    private static final BigDecimal UPDATED_POIDS = new BigDecimal(2);

    @Autowired
    private AchatLigneProformaRepository achatLigneProformaRepository;

    @Autowired
    private AchatLigneProformaMapper achatLigneProformaMapper;

    @Autowired
    private AchatLigneProformaService achatLigneProformaService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatLigneProformaSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatLigneProformaSearchRepository mockAchatLigneProformaSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatLigneProformaMockMvc;

    private AchatLigneProforma achatLigneProforma;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatLigneProformaResource achatLigneProformaResource = new AchatLigneProformaResource(achatLigneProformaService);
        this.restAchatLigneProformaMockMvc = MockMvcBuilders.standaloneSetup(achatLigneProformaResource)
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
    public static AchatLigneProforma createEntity(EntityManager em) {
        AchatLigneProforma achatLigneProforma = new AchatLigneProforma()
            .idLigneProforma(DEFAULT_ID_LIGNE_PROFORMA)
            .nombreConteneurs(DEFAULT_NOMBRE_CONTENEURS)
            .montant(DEFAULT_MONTANT)
            .numeroSequence(DEFAULT_NUMERO_SEQUENCE)
            .poids(DEFAULT_POIDS);
        return achatLigneProforma;
    }

    @Before
    public void initTest() {
        achatLigneProforma = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatLigneProforma() throws Exception {
        int databaseSizeBeforeCreate = achatLigneProformaRepository.findAll().size();

        // Create the AchatLigneProforma
        AchatLigneProformaDTO achatLigneProformaDTO = achatLigneProformaMapper.toDto(achatLigneProforma);
        restAchatLigneProformaMockMvc.perform(post("/api/achat-ligne-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatLigneProformaDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatLigneProforma in the database
        List<AchatLigneProforma> achatLigneProformaList = achatLigneProformaRepository.findAll();
        assertThat(achatLigneProformaList).hasSize(databaseSizeBeforeCreate + 1);
        AchatLigneProforma testAchatLigneProforma = achatLigneProformaList.get(achatLigneProformaList.size() - 1);
        assertThat(testAchatLigneProforma.getIdLigneProforma()).isEqualTo(DEFAULT_ID_LIGNE_PROFORMA);
        assertThat(testAchatLigneProforma.getNombreConteneurs()).isEqualTo(DEFAULT_NOMBRE_CONTENEURS);
        assertThat(testAchatLigneProforma.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testAchatLigneProforma.getNumeroSequence()).isEqualTo(DEFAULT_NUMERO_SEQUENCE);
        assertThat(testAchatLigneProforma.getPoids()).isEqualTo(DEFAULT_POIDS);

        // Validate the AchatLigneProforma in Elasticsearch
        verify(mockAchatLigneProformaSearchRepository, times(1)).save(testAchatLigneProforma);
    }

    @Test
    @Transactional
    public void createAchatLigneProformaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatLigneProformaRepository.findAll().size();

        // Create the AchatLigneProforma with an existing ID
        achatLigneProforma.setId(1L);
        AchatLigneProformaDTO achatLigneProformaDTO = achatLigneProformaMapper.toDto(achatLigneProforma);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatLigneProformaMockMvc.perform(post("/api/achat-ligne-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatLigneProformaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatLigneProforma in the database
        List<AchatLigneProforma> achatLigneProformaList = achatLigneProformaRepository.findAll();
        assertThat(achatLigneProformaList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatLigneProforma in Elasticsearch
        verify(mockAchatLigneProformaSearchRepository, times(0)).save(achatLigneProforma);
    }

    @Test
    @Transactional
    public void getAllAchatLigneProformas() throws Exception {
        // Initialize the database
        achatLigneProformaRepository.saveAndFlush(achatLigneProforma);

        // Get all the achatLigneProformaList
        restAchatLigneProformaMockMvc.perform(get("/api/achat-ligne-proformas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatLigneProforma.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLigneProforma").value(hasItem(DEFAULT_ID_LIGNE_PROFORMA)))
            .andExpect(jsonPath("$.[*].nombreConteneurs").value(hasItem(DEFAULT_NOMBRE_CONTENEURS)))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].numeroSequence").value(hasItem(DEFAULT_NUMERO_SEQUENCE)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())));
    }
    
    @Test
    @Transactional
    public void getAchatLigneProforma() throws Exception {
        // Initialize the database
        achatLigneProformaRepository.saveAndFlush(achatLigneProforma);

        // Get the achatLigneProforma
        restAchatLigneProformaMockMvc.perform(get("/api/achat-ligne-proformas/{id}", achatLigneProforma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatLigneProforma.getId().intValue()))
            .andExpect(jsonPath("$.idLigneProforma").value(DEFAULT_ID_LIGNE_PROFORMA))
            .andExpect(jsonPath("$.nombreConteneurs").value(DEFAULT_NOMBRE_CONTENEURS))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.numeroSequence").value(DEFAULT_NUMERO_SEQUENCE))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatLigneProforma() throws Exception {
        // Get the achatLigneProforma
        restAchatLigneProformaMockMvc.perform(get("/api/achat-ligne-proformas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatLigneProforma() throws Exception {
        // Initialize the database
        achatLigneProformaRepository.saveAndFlush(achatLigneProforma);

        int databaseSizeBeforeUpdate = achatLigneProformaRepository.findAll().size();

        // Update the achatLigneProforma
        AchatLigneProforma updatedAchatLigneProforma = achatLigneProformaRepository.findById(achatLigneProforma.getId()).get();
        // Disconnect from session so that the updates on updatedAchatLigneProforma are not directly saved in db
        em.detach(updatedAchatLigneProforma);
        updatedAchatLigneProforma
            .idLigneProforma(UPDATED_ID_LIGNE_PROFORMA)
            .nombreConteneurs(UPDATED_NOMBRE_CONTENEURS)
            .montant(UPDATED_MONTANT)
            .numeroSequence(UPDATED_NUMERO_SEQUENCE)
            .poids(UPDATED_POIDS);
        AchatLigneProformaDTO achatLigneProformaDTO = achatLigneProformaMapper.toDto(updatedAchatLigneProforma);

        restAchatLigneProformaMockMvc.perform(put("/api/achat-ligne-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatLigneProformaDTO)))
            .andExpect(status().isOk());

        // Validate the AchatLigneProforma in the database
        List<AchatLigneProforma> achatLigneProformaList = achatLigneProformaRepository.findAll();
        assertThat(achatLigneProformaList).hasSize(databaseSizeBeforeUpdate);
        AchatLigneProforma testAchatLigneProforma = achatLigneProformaList.get(achatLigneProformaList.size() - 1);
        assertThat(testAchatLigneProforma.getIdLigneProforma()).isEqualTo(UPDATED_ID_LIGNE_PROFORMA);
        assertThat(testAchatLigneProforma.getNombreConteneurs()).isEqualTo(UPDATED_NOMBRE_CONTENEURS);
        assertThat(testAchatLigneProforma.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testAchatLigneProforma.getNumeroSequence()).isEqualTo(UPDATED_NUMERO_SEQUENCE);
        assertThat(testAchatLigneProforma.getPoids()).isEqualTo(UPDATED_POIDS);

        // Validate the AchatLigneProforma in Elasticsearch
        verify(mockAchatLigneProformaSearchRepository, times(1)).save(testAchatLigneProforma);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatLigneProforma() throws Exception {
        int databaseSizeBeforeUpdate = achatLigneProformaRepository.findAll().size();

        // Create the AchatLigneProforma
        AchatLigneProformaDTO achatLigneProformaDTO = achatLigneProformaMapper.toDto(achatLigneProforma);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatLigneProformaMockMvc.perform(put("/api/achat-ligne-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatLigneProformaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatLigneProforma in the database
        List<AchatLigneProforma> achatLigneProformaList = achatLigneProformaRepository.findAll();
        assertThat(achatLigneProformaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatLigneProforma in Elasticsearch
        verify(mockAchatLigneProformaSearchRepository, times(0)).save(achatLigneProforma);
    }

    @Test
    @Transactional
    public void deleteAchatLigneProforma() throws Exception {
        // Initialize the database
        achatLigneProformaRepository.saveAndFlush(achatLigneProforma);

        int databaseSizeBeforeDelete = achatLigneProformaRepository.findAll().size();

        // Get the achatLigneProforma
        restAchatLigneProformaMockMvc.perform(delete("/api/achat-ligne-proformas/{id}", achatLigneProforma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatLigneProforma> achatLigneProformaList = achatLigneProformaRepository.findAll();
        assertThat(achatLigneProformaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatLigneProforma in Elasticsearch
        verify(mockAchatLigneProformaSearchRepository, times(1)).deleteById(achatLigneProforma.getId());
    }

    @Test
    @Transactional
    public void searchAchatLigneProforma() throws Exception {
        // Initialize the database
        achatLigneProformaRepository.saveAndFlush(achatLigneProforma);
        when(mockAchatLigneProformaSearchRepository.search(queryStringQuery("id:" + achatLigneProforma.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatLigneProforma), PageRequest.of(0, 1), 1));
        // Search the achatLigneProforma
        restAchatLigneProformaMockMvc.perform(get("/api/_search/achat-ligne-proformas?query=id:" + achatLigneProforma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatLigneProforma.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLigneProforma").value(hasItem(DEFAULT_ID_LIGNE_PROFORMA)))
            .andExpect(jsonPath("$.[*].nombreConteneurs").value(hasItem(DEFAULT_NOMBRE_CONTENEURS)))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].numeroSequence").value(hasItem(DEFAULT_NUMERO_SEQUENCE)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatLigneProforma.class);
        AchatLigneProforma achatLigneProforma1 = new AchatLigneProforma();
        achatLigneProforma1.setId(1L);
        AchatLigneProforma achatLigneProforma2 = new AchatLigneProforma();
        achatLigneProforma2.setId(achatLigneProforma1.getId());
        assertThat(achatLigneProforma1).isEqualTo(achatLigneProforma2);
        achatLigneProforma2.setId(2L);
        assertThat(achatLigneProforma1).isNotEqualTo(achatLigneProforma2);
        achatLigneProforma1.setId(null);
        assertThat(achatLigneProforma1).isNotEqualTo(achatLigneProforma2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatLigneProformaDTO.class);
        AchatLigneProformaDTO achatLigneProformaDTO1 = new AchatLigneProformaDTO();
        achatLigneProformaDTO1.setId(1L);
        AchatLigneProformaDTO achatLigneProformaDTO2 = new AchatLigneProformaDTO();
        assertThat(achatLigneProformaDTO1).isNotEqualTo(achatLigneProformaDTO2);
        achatLigneProformaDTO2.setId(achatLigneProformaDTO1.getId());
        assertThat(achatLigneProformaDTO1).isEqualTo(achatLigneProformaDTO2);
        achatLigneProformaDTO2.setId(2L);
        assertThat(achatLigneProformaDTO1).isNotEqualTo(achatLigneProformaDTO2);
        achatLigneProformaDTO1.setId(null);
        assertThat(achatLigneProformaDTO1).isNotEqualTo(achatLigneProformaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatLigneProformaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatLigneProformaMapper.fromId(null)).isNull();
    }
}
