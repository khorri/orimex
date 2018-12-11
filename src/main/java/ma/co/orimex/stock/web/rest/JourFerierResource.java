package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.JourFerierService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.JourFerierDTO;
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
 * REST controller for managing JourFerier.
 */
@RestController
@RequestMapping("/api")
public class JourFerierResource {

    private final Logger log = LoggerFactory.getLogger(JourFerierResource.class);

    private static final String ENTITY_NAME = "jourFerier";

    private final JourFerierService jourFerierService;

    public JourFerierResource(JourFerierService jourFerierService) {
        this.jourFerierService = jourFerierService;
    }

    /**
     * POST  /jour-feriers : Create a new jourFerier.
     *
     * @param jourFerierDTO the jourFerierDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jourFerierDTO, or with status 400 (Bad Request) if the jourFerier has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/jour-feriers")
    @Timed
    public ResponseEntity<JourFerierDTO> createJourFerier(@RequestBody JourFerierDTO jourFerierDTO) throws URISyntaxException {
        log.debug("REST request to save JourFerier : {}", jourFerierDTO);
        if (jourFerierDTO.getId() != null) {
            throw new BadRequestAlertException("A new jourFerier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JourFerierDTO result = jourFerierService.save(jourFerierDTO);
        return ResponseEntity.created(new URI("/api/jour-feriers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /jour-feriers : Updates an existing jourFerier.
     *
     * @param jourFerierDTO the jourFerierDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jourFerierDTO,
     * or with status 400 (Bad Request) if the jourFerierDTO is not valid,
     * or with status 500 (Internal Server Error) if the jourFerierDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/jour-feriers")
    @Timed
    public ResponseEntity<JourFerierDTO> updateJourFerier(@RequestBody JourFerierDTO jourFerierDTO) throws URISyntaxException {
        log.debug("REST request to update JourFerier : {}", jourFerierDTO);
        if (jourFerierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JourFerierDTO result = jourFerierService.save(jourFerierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jourFerierDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /jour-feriers : get all the jourFeriers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of jourFeriers in body
     */
    @GetMapping("/jour-feriers")
    @Timed
    public ResponseEntity<List<JourFerierDTO>> getAllJourFeriers(Pageable pageable) {
        log.debug("REST request to get a page of JourFeriers");
        Page<JourFerierDTO> page = jourFerierService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jour-feriers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /jour-feriers/:id : get the "id" jourFerier.
     *
     * @param id the id of the jourFerierDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jourFerierDTO, or with status 404 (Not Found)
     */
    @GetMapping("/jour-feriers/{id}")
    @Timed
    public ResponseEntity<JourFerierDTO> getJourFerier(@PathVariable Long id) {
        log.debug("REST request to get JourFerier : {}", id);
        Optional<JourFerierDTO> jourFerierDTO = jourFerierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jourFerierDTO);
    }

    /**
     * DELETE  /jour-feriers/:id : delete the "id" jourFerier.
     *
     * @param id the id of the jourFerierDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/jour-feriers/{id}")
    @Timed
    public ResponseEntity<Void> deleteJourFerier(@PathVariable Long id) {
        log.debug("REST request to delete JourFerier : {}", id);
        jourFerierService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/jour-feriers?query=:query : search for the jourFerier corresponding
     * to the query.
     *
     * @param query the query of the jourFerier search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/jour-feriers")
    @Timed
    public ResponseEntity<List<JourFerierDTO>> searchJourFeriers(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of JourFeriers for query {}", query);
        Page<JourFerierDTO> page = jourFerierService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/jour-feriers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
