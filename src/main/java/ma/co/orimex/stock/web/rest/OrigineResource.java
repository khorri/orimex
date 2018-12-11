package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.OrigineService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.OrigineDTO;
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
 * REST controller for managing Origine.
 */
@RestController
@RequestMapping("/api")
public class OrigineResource {

    private final Logger log = LoggerFactory.getLogger(OrigineResource.class);

    private static final String ENTITY_NAME = "origine";

    private final OrigineService origineService;

    public OrigineResource(OrigineService origineService) {
        this.origineService = origineService;
    }

    /**
     * POST  /origines : Create a new origine.
     *
     * @param origineDTO the origineDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new origineDTO, or with status 400 (Bad Request) if the origine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/origines")
    @Timed
    public ResponseEntity<OrigineDTO> createOrigine(@RequestBody OrigineDTO origineDTO) throws URISyntaxException {
        log.debug("REST request to save Origine : {}", origineDTO);
        if (origineDTO.getId() != null) {
            throw new BadRequestAlertException("A new origine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrigineDTO result = origineService.save(origineDTO);
        return ResponseEntity.created(new URI("/api/origines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /origines : Updates an existing origine.
     *
     * @param origineDTO the origineDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated origineDTO,
     * or with status 400 (Bad Request) if the origineDTO is not valid,
     * or with status 500 (Internal Server Error) if the origineDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/origines")
    @Timed
    public ResponseEntity<OrigineDTO> updateOrigine(@RequestBody OrigineDTO origineDTO) throws URISyntaxException {
        log.debug("REST request to update Origine : {}", origineDTO);
        if (origineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrigineDTO result = origineService.save(origineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, origineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /origines : get all the origines.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of origines in body
     */
    @GetMapping("/origines")
    @Timed
    public ResponseEntity<List<OrigineDTO>> getAllOrigines(Pageable pageable) {
        log.debug("REST request to get a page of Origines");
        Page<OrigineDTO> page = origineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/origines");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /origines/:id : get the "id" origine.
     *
     * @param id the id of the origineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the origineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/origines/{id}")
    @Timed
    public ResponseEntity<OrigineDTO> getOrigine(@PathVariable Long id) {
        log.debug("REST request to get Origine : {}", id);
        Optional<OrigineDTO> origineDTO = origineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(origineDTO);
    }

    /**
     * DELETE  /origines/:id : delete the "id" origine.
     *
     * @param id the id of the origineDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/origines/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrigine(@PathVariable Long id) {
        log.debug("REST request to delete Origine : {}", id);
        origineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/origines?query=:query : search for the origine corresponding
     * to the query.
     *
     * @param query the query of the origine search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/origines")
    @Timed
    public ResponseEntity<List<OrigineDTO>> searchOrigines(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Origines for query {}", query);
        Page<OrigineDTO> page = origineService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/origines");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
