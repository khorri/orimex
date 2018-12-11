package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.FamilleProduitService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.FamilleProduitDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing FamilleProduit.
 */
@RestController
@RequestMapping("/api")
public class FamilleProduitResource {

    private final Logger log = LoggerFactory.getLogger(FamilleProduitResource.class);

    private static final String ENTITY_NAME = "familleProduit";

    private final FamilleProduitService familleProduitService;

    public FamilleProduitResource(FamilleProduitService familleProduitService) {
        this.familleProduitService = familleProduitService;
    }

    /**
     * POST  /famille-produits : Create a new familleProduit.
     *
     * @param familleProduitDTO the familleProduitDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new familleProduitDTO, or with status 400 (Bad Request) if the familleProduit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/famille-produits")
    @Timed
    public ResponseEntity<FamilleProduitDTO> createFamilleProduit(@RequestBody FamilleProduitDTO familleProduitDTO) throws URISyntaxException {
        log.debug("REST request to save FamilleProduit : {}", familleProduitDTO);
        if (familleProduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new familleProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FamilleProduitDTO result = familleProduitService.save(familleProduitDTO);
        return ResponseEntity.created(new URI("/api/famille-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /famille-produits : Updates an existing familleProduit.
     *
     * @param familleProduitDTO the familleProduitDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated familleProduitDTO,
     * or with status 400 (Bad Request) if the familleProduitDTO is not valid,
     * or with status 500 (Internal Server Error) if the familleProduitDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/famille-produits")
    @Timed
    public ResponseEntity<FamilleProduitDTO> updateFamilleProduit(@RequestBody FamilleProduitDTO familleProduitDTO) throws URISyntaxException {
        log.debug("REST request to update FamilleProduit : {}", familleProduitDTO);
        if (familleProduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FamilleProduitDTO result = familleProduitService.save(familleProduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, familleProduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /famille-produits : get all the familleProduits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of familleProduits in body
     */
    @GetMapping("/famille-produits")
    @Timed
    public ResponseEntity<List<FamilleProduitDTO>> getAllFamilleProduits(Pageable pageable) {
        log.debug("REST request to get a page of FamilleProduits");
        Page<FamilleProduitDTO> page = familleProduitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/famille-produits");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /famille-produits/:id : get the "id" familleProduit.
     *
     * @param id the id of the familleProduitDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the familleProduitDTO, or with status 404 (Not Found)
     */
    @GetMapping("/famille-produits/{id}")
    @Timed
    public ResponseEntity<FamilleProduitDTO> getFamilleProduit(@PathVariable Long id) {
        log.debug("REST request to get FamilleProduit : {}", id);
        Optional<FamilleProduitDTO> familleProduitDTO = familleProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(familleProduitDTO);
    }

    /**
     * DELETE  /famille-produits/:id : delete the "id" familleProduit.
     *
     * @param id the id of the familleProduitDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/famille-produits/{id}")
    @Timed
    public ResponseEntity<Void> deleteFamilleProduit(@PathVariable Long id) {
        log.debug("REST request to delete FamilleProduit : {}", id);
        familleProduitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/famille-produits?query=:query : search for the familleProduit corresponding
     * to the query.
     *
     * @param query the query of the familleProduit search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/famille-produits")
    @Timed
    public ResponseEntity<List<FamilleProduitDTO>> searchFamilleProduits(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of FamilleProduits for query {}", query);
        Page<FamilleProduitDTO> page = familleProduitService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/famille-produits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
