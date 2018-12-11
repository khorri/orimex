package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.AchatProforma;
import ma.co.orimex.stock.repository.AchatProformaRepository;
import ma.co.orimex.stock.repository.search.AchatProformaSearchRepository;
import ma.co.orimex.stock.service.AchatProformaService;
import ma.co.orimex.stock.service.dto.AchatProformaDTO;
import ma.co.orimex.stock.service.mapper.AchatProformaMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Test class for the AchatProformaResource REST controller.
 *
 * @see AchatProformaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class AchatProformaResourceIntTest {

    private static final Integer DEFAULT_ID_PROFORMA = 1;
    private static final Integer UPDATED_ID_PROFORMA = 2;

    private static final Integer DEFAULT_NOMBRE_TC = 1;
    private static final Integer UPDATED_NOMBRE_TC = 2;

    private static final BigDecimal DEFAULT_COUT_FOB = new BigDecimal(1);
    private static final BigDecimal UPDATED_COUT_FOB = new BigDecimal(2);

    private static final BigDecimal DEFAULT_COUT_FRET = new BigDecimal(1);
    private static final BigDecimal UPDATED_COUT_FRET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_TOTAL = new BigDecimal(2);

    private static final String DEFAULT_NUMERO_BON_PROFORMA = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_BON_PROFORMA = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_ACHT = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_ACHT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_POIDS = new BigDecimal(1);
    private static final BigDecimal UPDATED_POIDS = new BigDecimal(2);

    private static final LocalDate DEFAULT_DATE_PROFORMA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PROFORMA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AchatProformaRepository achatProformaRepository;

    @Autowired
    private AchatProformaMapper achatProformaMapper;

    @Autowired
    private AchatProformaService achatProformaService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.AchatProformaSearchRepositoryMockConfiguration
     */
    @Autowired
    private AchatProformaSearchRepository mockAchatProformaSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAchatProformaMockMvc;

    private AchatProforma achatProforma;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchatProformaResource achatProformaResource = new AchatProformaResource(achatProformaService);
        this.restAchatProformaMockMvc = MockMvcBuilders.standaloneSetup(achatProformaResource)
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
    public static AchatProforma createEntity(EntityManager em) {
        AchatProforma achatProforma = new AchatProforma()
            .idProforma(DEFAULT_ID_PROFORMA)
            .nombreTc(DEFAULT_NOMBRE_TC)
            .coutFob(DEFAULT_COUT_FOB)
            .coutFret(DEFAULT_COUT_FRET)
            .montantTotal(DEFAULT_MONTANT_TOTAL)
            .numeroBonProforma(DEFAULT_NUMERO_BON_PROFORMA)
            .typeAcht(DEFAULT_TYPE_ACHT)
            .poids(DEFAULT_POIDS)
            .dateProforma(DEFAULT_DATE_PROFORMA);
        return achatProforma;
    }

    @Before
    public void initTest() {
        achatProforma = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchatProforma() throws Exception {
        int databaseSizeBeforeCreate = achatProformaRepository.findAll().size();

        // Create the AchatProforma
        AchatProformaDTO achatProformaDTO = achatProformaMapper.toDto(achatProforma);
        restAchatProformaMockMvc.perform(post("/api/achat-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatProformaDTO)))
            .andExpect(status().isCreated());

        // Validate the AchatProforma in the database
        List<AchatProforma> achatProformaList = achatProformaRepository.findAll();
        assertThat(achatProformaList).hasSize(databaseSizeBeforeCreate + 1);
        AchatProforma testAchatProforma = achatProformaList.get(achatProformaList.size() - 1);
        assertThat(testAchatProforma.getIdProforma()).isEqualTo(DEFAULT_ID_PROFORMA);
        assertThat(testAchatProforma.getNombreTc()).isEqualTo(DEFAULT_NOMBRE_TC);
        assertThat(testAchatProforma.getCoutFob()).isEqualTo(DEFAULT_COUT_FOB);
        assertThat(testAchatProforma.getCoutFret()).isEqualTo(DEFAULT_COUT_FRET);
        assertThat(testAchatProforma.getMontantTotal()).isEqualTo(DEFAULT_MONTANT_TOTAL);
        assertThat(testAchatProforma.getNumeroBonProforma()).isEqualTo(DEFAULT_NUMERO_BON_PROFORMA);
        assertThat(testAchatProforma.getTypeAcht()).isEqualTo(DEFAULT_TYPE_ACHT);
        assertThat(testAchatProforma.getPoids()).isEqualTo(DEFAULT_POIDS);
        assertThat(testAchatProforma.getDateProforma()).isEqualTo(DEFAULT_DATE_PROFORMA);

        // Validate the AchatProforma in Elasticsearch
        verify(mockAchatProformaSearchRepository, times(1)).save(testAchatProforma);
    }

    @Test
    @Transactional
    public void createAchatProformaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achatProformaRepository.findAll().size();

        // Create the AchatProforma with an existing ID
        achatProforma.setId(1L);
        AchatProformaDTO achatProformaDTO = achatProformaMapper.toDto(achatProforma);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchatProformaMockMvc.perform(post("/api/achat-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatProformaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatProforma in the database
        List<AchatProforma> achatProformaList = achatProformaRepository.findAll();
        assertThat(achatProformaList).hasSize(databaseSizeBeforeCreate);

        // Validate the AchatProforma in Elasticsearch
        verify(mockAchatProformaSearchRepository, times(0)).save(achatProforma);
    }

    @Test
    @Transactional
    public void getAllAchatProformas() throws Exception {
        // Initialize the database
        achatProformaRepository.saveAndFlush(achatProforma);

        // Get all the achatProformaList
        restAchatProformaMockMvc.perform(get("/api/achat-proformas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatProforma.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProforma").value(hasItem(DEFAULT_ID_PROFORMA)))
            .andExpect(jsonPath("$.[*].nombreTc").value(hasItem(DEFAULT_NOMBRE_TC)))
            .andExpect(jsonPath("$.[*].coutFob").value(hasItem(DEFAULT_COUT_FOB.intValue())))
            .andExpect(jsonPath("$.[*].coutFret").value(hasItem(DEFAULT_COUT_FRET.intValue())))
            .andExpect(jsonPath("$.[*].montantTotal").value(hasItem(DEFAULT_MONTANT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].numeroBonProforma").value(hasItem(DEFAULT_NUMERO_BON_PROFORMA.toString())))
            .andExpect(jsonPath("$.[*].typeAcht").value(hasItem(DEFAULT_TYPE_ACHT.toString())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())))
            .andExpect(jsonPath("$.[*].dateProforma").value(hasItem(DEFAULT_DATE_PROFORMA.toString())));
    }
    
    @Test
    @Transactional
    public void getAchatProforma() throws Exception {
        // Initialize the database
        achatProformaRepository.saveAndFlush(achatProforma);

        // Get the achatProforma
        restAchatProformaMockMvc.perform(get("/api/achat-proformas/{id}", achatProforma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achatProforma.getId().intValue()))
            .andExpect(jsonPath("$.idProforma").value(DEFAULT_ID_PROFORMA))
            .andExpect(jsonPath("$.nombreTc").value(DEFAULT_NOMBRE_TC))
            .andExpect(jsonPath("$.coutFob").value(DEFAULT_COUT_FOB.intValue()))
            .andExpect(jsonPath("$.coutFret").value(DEFAULT_COUT_FRET.intValue()))
            .andExpect(jsonPath("$.montantTotal").value(DEFAULT_MONTANT_TOTAL.intValue()))
            .andExpect(jsonPath("$.numeroBonProforma").value(DEFAULT_NUMERO_BON_PROFORMA.toString()))
            .andExpect(jsonPath("$.typeAcht").value(DEFAULT_TYPE_ACHT.toString()))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.intValue()))
            .andExpect(jsonPath("$.dateProforma").value(DEFAULT_DATE_PROFORMA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAchatProforma() throws Exception {
        // Get the achatProforma
        restAchatProformaMockMvc.perform(get("/api/achat-proformas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchatProforma() throws Exception {
        // Initialize the database
        achatProformaRepository.saveAndFlush(achatProforma);

        int databaseSizeBeforeUpdate = achatProformaRepository.findAll().size();

        // Update the achatProforma
        AchatProforma updatedAchatProforma = achatProformaRepository.findById(achatProforma.getId()).get();
        // Disconnect from session so that the updates on updatedAchatProforma are not directly saved in db
        em.detach(updatedAchatProforma);
        updatedAchatProforma
            .idProforma(UPDATED_ID_PROFORMA)
            .nombreTc(UPDATED_NOMBRE_TC)
            .coutFob(UPDATED_COUT_FOB)
            .coutFret(UPDATED_COUT_FRET)
            .montantTotal(UPDATED_MONTANT_TOTAL)
            .numeroBonProforma(UPDATED_NUMERO_BON_PROFORMA)
            .typeAcht(UPDATED_TYPE_ACHT)
            .poids(UPDATED_POIDS)
            .dateProforma(UPDATED_DATE_PROFORMA);
        AchatProformaDTO achatProformaDTO = achatProformaMapper.toDto(updatedAchatProforma);

        restAchatProformaMockMvc.perform(put("/api/achat-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatProformaDTO)))
            .andExpect(status().isOk());

        // Validate the AchatProforma in the database
        List<AchatProforma> achatProformaList = achatProformaRepository.findAll();
        assertThat(achatProformaList).hasSize(databaseSizeBeforeUpdate);
        AchatProforma testAchatProforma = achatProformaList.get(achatProformaList.size() - 1);
        assertThat(testAchatProforma.getIdProforma()).isEqualTo(UPDATED_ID_PROFORMA);
        assertThat(testAchatProforma.getNombreTc()).isEqualTo(UPDATED_NOMBRE_TC);
        assertThat(testAchatProforma.getCoutFob()).isEqualTo(UPDATED_COUT_FOB);
        assertThat(testAchatProforma.getCoutFret()).isEqualTo(UPDATED_COUT_FRET);
        assertThat(testAchatProforma.getMontantTotal()).isEqualTo(UPDATED_MONTANT_TOTAL);
        assertThat(testAchatProforma.getNumeroBonProforma()).isEqualTo(UPDATED_NUMERO_BON_PROFORMA);
        assertThat(testAchatProforma.getTypeAcht()).isEqualTo(UPDATED_TYPE_ACHT);
        assertThat(testAchatProforma.getPoids()).isEqualTo(UPDATED_POIDS);
        assertThat(testAchatProforma.getDateProforma()).isEqualTo(UPDATED_DATE_PROFORMA);

        // Validate the AchatProforma in Elasticsearch
        verify(mockAchatProformaSearchRepository, times(1)).save(testAchatProforma);
    }

    @Test
    @Transactional
    public void updateNonExistingAchatProforma() throws Exception {
        int databaseSizeBeforeUpdate = achatProformaRepository.findAll().size();

        // Create the AchatProforma
        AchatProformaDTO achatProformaDTO = achatProformaMapper.toDto(achatProforma);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchatProformaMockMvc.perform(put("/api/achat-proformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achatProformaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AchatProforma in the database
        List<AchatProforma> achatProformaList = achatProformaRepository.findAll();
        assertThat(achatProformaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AchatProforma in Elasticsearch
        verify(mockAchatProformaSearchRepository, times(0)).save(achatProforma);
    }

    @Test
    @Transactional
    public void deleteAchatProforma() throws Exception {
        // Initialize the database
        achatProformaRepository.saveAndFlush(achatProforma);

        int databaseSizeBeforeDelete = achatProformaRepository.findAll().size();

        // Get the achatProforma
        restAchatProformaMockMvc.perform(delete("/api/achat-proformas/{id}", achatProforma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchatProforma> achatProformaList = achatProformaRepository.findAll();
        assertThat(achatProformaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AchatProforma in Elasticsearch
        verify(mockAchatProformaSearchRepository, times(1)).deleteById(achatProforma.getId());
    }

    @Test
    @Transactional
    public void searchAchatProforma() throws Exception {
        // Initialize the database
        achatProformaRepository.saveAndFlush(achatProforma);
        when(mockAchatProformaSearchRepository.search(queryStringQuery("id:" + achatProforma.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(achatProforma), PageRequest.of(0, 1), 1));
        // Search the achatProforma
        restAchatProformaMockMvc.perform(get("/api/_search/achat-proformas?query=id:" + achatProforma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achatProforma.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProforma").value(hasItem(DEFAULT_ID_PROFORMA)))
            .andExpect(jsonPath("$.[*].nombreTc").value(hasItem(DEFAULT_NOMBRE_TC)))
            .andExpect(jsonPath("$.[*].coutFob").value(hasItem(DEFAULT_COUT_FOB.intValue())))
            .andExpect(jsonPath("$.[*].coutFret").value(hasItem(DEFAULT_COUT_FRET.intValue())))
            .andExpect(jsonPath("$.[*].montantTotal").value(hasItem(DEFAULT_MONTANT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].numeroBonProforma").value(hasItem(DEFAULT_NUMERO_BON_PROFORMA)))
            .andExpect(jsonPath("$.[*].typeAcht").value(hasItem(DEFAULT_TYPE_ACHT)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())))
            .andExpect(jsonPath("$.[*].dateProforma").value(hasItem(DEFAULT_DATE_PROFORMA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatProforma.class);
        AchatProforma achatProforma1 = new AchatProforma();
        achatProforma1.setId(1L);
        AchatProforma achatProforma2 = new AchatProforma();
        achatProforma2.setId(achatProforma1.getId());
        assertThat(achatProforma1).isEqualTo(achatProforma2);
        achatProforma2.setId(2L);
        assertThat(achatProforma1).isNotEqualTo(achatProforma2);
        achatProforma1.setId(null);
        assertThat(achatProforma1).isNotEqualTo(achatProforma2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchatProformaDTO.class);
        AchatProformaDTO achatProformaDTO1 = new AchatProformaDTO();
        achatProformaDTO1.setId(1L);
        AchatProformaDTO achatProformaDTO2 = new AchatProformaDTO();
        assertThat(achatProformaDTO1).isNotEqualTo(achatProformaDTO2);
        achatProformaDTO2.setId(achatProformaDTO1.getId());
        assertThat(achatProformaDTO1).isEqualTo(achatProformaDTO2);
        achatProformaDTO2.setId(2L);
        assertThat(achatProformaDTO1).isNotEqualTo(achatProformaDTO2);
        achatProformaDTO1.setId(null);
        assertThat(achatProformaDTO1).isNotEqualTo(achatProformaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(achatProformaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(achatProformaMapper.fromId(null)).isNull();
    }
}
