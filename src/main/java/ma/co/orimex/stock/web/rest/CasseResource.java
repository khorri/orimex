package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.CasseService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.CasseDTO;
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
 * REST controller for managing Casse.
 */
@RestController
@RequestMapping("/api")
public class CasseResource {

    private final Logger log = LoggerFactory.getLogger(CasseResource.class);

    private static final String ENTITY_NAME = "casse";

    private final CasseService casseService;

    public CasseResource(CasseService casseService) {
        this.casseService = casseService;
    }

    /**
     * POST  /casses : Create a new casse.
     *
     * @param casseDTO the casseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new casseDTO, or with status 400 (Bad Request) if the casse has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/casses")
    @Timed
    public ResponseEntity<CasseDTO> createCasse(@RequestBody CasseDTO casseDTO) throws URISyntaxException {
        log.debug("REST request to save Casse : {}", casseDTO);
        if (casseDTO.getId() != null) {
            throw new BadRequestAlertException("A new casse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CasseDTO result = casseService.save(casseDTO);
        return ResponseEntity.created(new URI("/api/casses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /casses : Updates an existing casse.
     *
     * @param casseDTO the casseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated casseDTO,
     * or with status 400 (Bad Request) if the casseDTO is not valid,
     * or with status 500 (Internal Server Error) if the casseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/casses")
    @Timed
    public ResponseEntity<CasseDTO> updateCasse(@RequestBody CasseDTO casseDTO) throws URISyntaxException {
        log.debug("REST request to update Casse : {}", casseDTO);
        if (casseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CasseDTO result = casseService.save(casseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, casseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /casses : get all the casses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of casses in body
     */
    @GetMapping("/casses")
    @Timed
    public ResponseEntity<List<CasseDTO>> getAllCasses(Pageable pageable) {
        log.debug("REST request to get a page of Casses");
        Page<CasseDTO> page = casseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/casses");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /casses/:id : get the "id" casse.
     *
     * @param id the id of the casseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the casseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/casses/{id}")
    @Timed
    public ResponseEntity<CasseDTO> getCasse(@PathVariable Long id) {
        log.debug("REST request to get Casse : {}", id);
        Optional<CasseDTO> casseDTO = casseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(casseDTO);
    }

    /**
     * DELETE  /casses/:id : delete the "id" casse.
     *
     * @param id the id of the casseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/casses/{id}")
    @Timed
    public ResponseEntity<Void> deleteCasse(@PathVariable Long id) {
        log.debug("REST request to delete Casse : {}", id);
        casseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/casses?query=:query : search for the casse corresponding
     * to the query.
     *
     * @param query the query of the casse search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/casses")
    @Timed
    public ResponseEntity<List<CasseDTO>> searchCasses(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Casses for query {}", query);
        Page<CasseDTO> page = casseService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/casses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
