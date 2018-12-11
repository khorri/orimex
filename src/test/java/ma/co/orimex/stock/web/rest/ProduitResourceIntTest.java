package ma.co.orimex.stock.web.rest;

import ma.co.orimex.stock.OrimexApp;

import ma.co.orimex.stock.domain.Produit;
import ma.co.orimex.stock.repository.ProduitRepository;
import ma.co.orimex.stock.repository.search.ProduitSearchRepository;
import ma.co.orimex.stock.service.ProduitService;
import ma.co.orimex.stock.service.dto.ProduitDTO;
import ma.co.orimex.stock.service.mapper.ProduitMapper;
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
 * Test class for the ProduitResource REST controller.
 *
 * @see ProduitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrimexApp.class)
public class ProduitResourceIntTest {

    private static final Integer DEFAULT_ID_PRODUIT = 1;
    private static final Integer UPDATED_ID_PRODUIT = 2;

    private static final String DEFAULT_DESIGNATION_PRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_PRODUIT = "BBBBBBBBBB";

    private static final String DEFAULT_DIMENSION = "AAAAAAAAAA";
    private static final String UPDATED_DIMENSION = "BBBBBBBBBB";

    private static final String DEFAULT_EPAISSEUR = "AAAAAAAAAA";
    private static final String UPDATED_EPAISSEUR = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LARGEURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_LARGEURE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LONGUEUR = new BigDecimal(1);
    private static final BigDecimal UPDATED_LONGUEUR = new BigDecimal(2);

    private static final String DEFAULT_REFERENCE_PRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_PRODUIT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_POIDS = new BigDecimal(1);
    private static final BigDecimal UPDATED_POIDS = new BigDecimal(2);

    private static final String DEFAULT_LIBELLE_COULEUR = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_COULEUR = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_FAMILLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_FAMILLE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_ORIGINE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_ORIGINE = "BBBBBBBBBB";

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitMapper produitMapper;

    @Autowired
    private ProduitService produitService;

    /**
     * This repository is mocked in the ma.co.orimex.stock.repository.search test package.
     *
     * @see ma.co.orimex.stock.repository.search.ProduitSearchRepositoryMockConfiguration
     */
    @Autowired
    private ProduitSearchRepository mockProduitSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProduitMockMvc;

    private Produit produit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProduitResource produitResource = new ProduitResource(produitService);
        this.restProduitMockMvc = MockMvcBuilders.standaloneSetup(produitResource)
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
    public static Produit createEntity(EntityManager em) {
        Produit produit = new Produit()
            .idProduit(DEFAULT_ID_PRODUIT)
            .designationProduit(DEFAULT_DESIGNATION_PRODUIT)
            .dimension(DEFAULT_DIMENSION)
            .epaisseur(DEFAULT_EPAISSEUR)
            .largeure(DEFAULT_LARGEURE)
            .longueur(DEFAULT_LONGUEUR)
            .referenceProduit(DEFAULT_REFERENCE_PRODUIT)
            .poids(DEFAULT_POIDS)
            .libelleCouleur(DEFAULT_LIBELLE_COULEUR)
            .libelleFamille(DEFAULT_LIBELLE_FAMILLE)
            .libelleOrigine(DEFAULT_LIBELLE_ORIGINE);
        return produit;
    }

    @Before
    public void initTest() {
        produit = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduit() throws Exception {
        int databaseSizeBeforeCreate = produitRepository.findAll().size();

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);
        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isCreated());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeCreate + 1);
        Produit testProduit = produitList.get(produitList.size() - 1);
        assertThat(testProduit.getIdProduit()).isEqualTo(DEFAULT_ID_PRODUIT);
        assertThat(testProduit.getDesignationProduit()).isEqualTo(DEFAULT_DESIGNATION_PRODUIT);
        assertThat(testProduit.getDimension()).isEqualTo(DEFAULT_DIMENSION);
        assertThat(testProduit.getEpaisseur()).isEqualTo(DEFAULT_EPAISSEUR);
        assertThat(testProduit.getLargeure()).isEqualTo(DEFAULT_LARGEURE);
        assertThat(testProduit.getLongueur()).isEqualTo(DEFAULT_LONGUEUR);
        assertThat(testProduit.getReferenceProduit()).isEqualTo(DEFAULT_REFERENCE_PRODUIT);
        assertThat(testProduit.getPoids()).isEqualTo(DEFAULT_POIDS);
        assertThat(testProduit.getLibelleCouleur()).isEqualTo(DEFAULT_LIBELLE_COULEUR);
        assertThat(testProduit.getLibelleFamille()).isEqualTo(DEFAULT_LIBELLE_FAMILLE);
        assertThat(testProduit.getLibelleOrigine()).isEqualTo(DEFAULT_LIBELLE_ORIGINE);

        // Validate the Produit in Elasticsearch
        verify(mockProduitSearchRepository, times(1)).save(testProduit);
    }

    @Test
    @Transactional
    public void createProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = produitRepository.findAll().size();

        // Create the Produit with an existing ID
        produit.setId(1L);
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeCreate);

        // Validate the Produit in Elasticsearch
        verify(mockProduitSearchRepository, times(0)).save(produit);
    }

    @Test
    @Transactional
    public void getAllProduits() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList
        restProduitMockMvc.perform(get("/api/produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produit.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProduit").value(hasItem(DEFAULT_ID_PRODUIT)))
            .andExpect(jsonPath("$.[*].designationProduit").value(hasItem(DEFAULT_DESIGNATION_PRODUIT.toString())))
            .andExpect(jsonPath("$.[*].dimension").value(hasItem(DEFAULT_DIMENSION.toString())))
            .andExpect(jsonPath("$.[*].epaisseur").value(hasItem(DEFAULT_EPAISSEUR.toString())))
            .andExpect(jsonPath("$.[*].largeure").value(hasItem(DEFAULT_LARGEURE.intValue())))
            .andExpect(jsonPath("$.[*].longueur").value(hasItem(DEFAULT_LONGUEUR.intValue())))
            .andExpect(jsonPath("$.[*].referenceProduit").value(hasItem(DEFAULT_REFERENCE_PRODUIT.toString())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())))
            .andExpect(jsonPath("$.[*].libelleCouleur").value(hasItem(DEFAULT_LIBELLE_COULEUR.toString())))
            .andExpect(jsonPath("$.[*].libelleFamille").value(hasItem(DEFAULT_LIBELLE_FAMILLE.toString())))
            .andExpect(jsonPath("$.[*].libelleOrigine").value(hasItem(DEFAULT_LIBELLE_ORIGINE.toString())));
    }
    
    @Test
    @Transactional
    public void getProduit() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get the produit
        restProduitMockMvc.perform(get("/api/produits/{id}", produit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(produit.getId().intValue()))
            .andExpect(jsonPath("$.idProduit").value(DEFAULT_ID_PRODUIT))
            .andExpect(jsonPath("$.designationProduit").value(DEFAULT_DESIGNATION_PRODUIT.toString()))
            .andExpect(jsonPath("$.dimension").value(DEFAULT_DIMENSION.toString()))
            .andExpect(jsonPath("$.epaisseur").value(DEFAULT_EPAISSEUR.toString()))
            .andExpect(jsonPath("$.largeure").value(DEFAULT_LARGEURE.intValue()))
            .andExpect(jsonPath("$.longueur").value(DEFAULT_LONGUEUR.intValue()))
            .andExpect(jsonPath("$.referenceProduit").value(DEFAULT_REFERENCE_PRODUIT.toString()))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.intValue()))
            .andExpect(jsonPath("$.libelleCouleur").value(DEFAULT_LIBELLE_COULEUR.toString()))
            .andExpect(jsonPath("$.libelleFamille").value(DEFAULT_LIBELLE_FAMILLE.toString()))
            .andExpect(jsonPath("$.libelleOrigine").value(DEFAULT_LIBELLE_ORIGINE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProduit() throws Exception {
        // Get the produit
        restProduitMockMvc.perform(get("/api/produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduit() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        int databaseSizeBeforeUpdate = produitRepository.findAll().size();

        // Update the produit
        Produit updatedProduit = produitRepository.findById(produit.getId()).get();
        // Disconnect from session so that the updates on updatedProduit are not directly saved in db
        em.detach(updatedProduit);
        updatedProduit
            .idProduit(UPDATED_ID_PRODUIT)
            .designationProduit(UPDATED_DESIGNATION_PRODUIT)
            .dimension(UPDATED_DIMENSION)
            .epaisseur(UPDATED_EPAISSEUR)
            .largeure(UPDATED_LARGEURE)
            .longueur(UPDATED_LONGUEUR)
            .referenceProduit(UPDATED_REFERENCE_PRODUIT)
            .poids(UPDATED_POIDS)
            .libelleCouleur(UPDATED_LIBELLE_COULEUR)
            .libelleFamille(UPDATED_LIBELLE_FAMILLE)
            .libelleOrigine(UPDATED_LIBELLE_ORIGINE);
        ProduitDTO produitDTO = produitMapper.toDto(updatedProduit);

        restProduitMockMvc.perform(put("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isOk());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeUpdate);
        Produit testProduit = produitList.get(produitList.size() - 1);
        assertThat(testProduit.getIdProduit()).isEqualTo(UPDATED_ID_PRODUIT);
        assertThat(testProduit.getDesignationProduit()).isEqualTo(UPDATED_DESIGNATION_PRODUIT);
        assertThat(testProduit.getDimension()).isEqualTo(UPDATED_DIMENSION);
        assertThat(testProduit.getEpaisseur()).isEqualTo(UPDATED_EPAISSEUR);
        assertThat(testProduit.getLargeure()).isEqualTo(UPDATED_LARGEURE);
        assertThat(testProduit.getLongueur()).isEqualTo(UPDATED_LONGUEUR);
        assertThat(testProduit.getReferenceProduit()).isEqualTo(UPDATED_REFERENCE_PRODUIT);
        assertThat(testProduit.getPoids()).isEqualTo(UPDATED_POIDS);
        assertThat(testProduit.getLibelleCouleur()).isEqualTo(UPDATED_LIBELLE_COULEUR);
        assertThat(testProduit.getLibelleFamille()).isEqualTo(UPDATED_LIBELLE_FAMILLE);
        assertThat(testProduit.getLibelleOrigine()).isEqualTo(UPDATED_LIBELLE_ORIGINE);

        // Validate the Produit in Elasticsearch
        verify(mockProduitSearchRepository, times(1)).save(testProduit);
    }

    @Test
    @Transactional
    public void updateNonExistingProduit() throws Exception {
        int databaseSizeBeforeUpdate = produitRepository.findAll().size();

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProduitMockMvc.perform(put("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Produit in Elasticsearch
        verify(mockProduitSearchRepository, times(0)).save(produit);
    }

    @Test
    @Transactional
    public void deleteProduit() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        int databaseSizeBeforeDelete = produitRepository.findAll().size();

        // Get the produit
        restProduitMockMvc.perform(delete("/api/produits/{id}", produit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Produit in Elasticsearch
        verify(mockProduitSearchRepository, times(1)).deleteById(produit.getId());
    }

    @Test
    @Transactional
    public void searchProduit() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);
        when(mockProduitSearchRepository.search(queryStringQuery("id:" + produit.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(produit), PageRequest.of(0, 1), 1));
        // Search the produit
        restProduitMockMvc.perform(get("/api/_search/produits?query=id:" + produit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produit.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProduit").value(hasItem(DEFAULT_ID_PRODUIT)))
            .andExpect(jsonPath("$.[*].designationProduit").value(hasItem(DEFAULT_DESIGNATION_PRODUIT)))
            .andExpect(jsonPath("$.[*].dimension").value(hasItem(DEFAULT_DIMENSION)))
            .andExpect(jsonPath("$.[*].epaisseur").value(hasItem(DEFAULT_EPAISSEUR)))
            .andExpect(jsonPath("$.[*].largeure").value(hasItem(DEFAULT_LARGEURE.intValue())))
            .andExpect(jsonPath("$.[*].longueur").value(hasItem(DEFAULT_LONGUEUR.intValue())))
            .andExpect(jsonPath("$.[*].referenceProduit").value(hasItem(DEFAULT_REFERENCE_PRODUIT)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())))
            .andExpect(jsonPath("$.[*].libelleCouleur").value(hasItem(DEFAULT_LIBELLE_COULEUR)))
            .andExpect(jsonPath("$.[*].libelleFamille").value(hasItem(DEFAULT_LIBELLE_FAMILLE)))
            .andExpect(jsonPath("$.[*].libelleOrigine").value(hasItem(DEFAULT_LIBELLE_ORIGINE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Produit.class);
        Produit produit1 = new Produit();
        produit1.setId(1L);
        Produit produit2 = new Produit();
        produit2.setId(produit1.getId());
        assertThat(produit1).isEqualTo(produit2);
        produit2.setId(2L);
        assertThat(produit1).isNotEqualTo(produit2);
        produit1.setId(null);
        assertThat(produit1).isNotEqualTo(produit2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProduitDTO.class);
        ProduitDTO produitDTO1 = new ProduitDTO();
        produitDTO1.setId(1L);
        ProduitDTO produitDTO2 = new ProduitDTO();
        assertThat(produitDTO1).isNotEqualTo(produitDTO2);
        produitDTO2.setId(produitDTO1.getId());
        assertThat(produitDTO1).isEqualTo(produitDTO2);
        produitDTO2.setId(2L);
        assertThat(produitDTO1).isNotEqualTo(produitDTO2);
        produitDTO1.setId(null);
        assertThat(produitDTO1).isNotEqualTo(produitDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(produitMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(produitMapper.fromId(null)).isNull();
    }
}
