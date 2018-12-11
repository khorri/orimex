package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.RecuperationService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.RecuperationDTO;
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
 * REST controller for managing Recuperation.
 */
@RestController
@RequestMapping("/api")
public class RecuperationResource {

    private final Logger log = LoggerFactory.getLogger(RecuperationResource.class);

    private static final String ENTITY_NAME = "recuperation";

    private final RecuperationService recuperationService;

    public RecuperationResource(RecuperationService recuperationService) {
        this.recuperationService = recuperationService;
    }

    /**
     * POST  /recuperations : Create a new recuperation.
     *
     * @param recuperationDTO the recuperationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recuperationDTO, or with status 400 (Bad Request) if the recuperation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recuperations")
    @Timed
    public ResponseEntity<RecuperationDTO> createRecuperation(@RequestBody RecuperationDTO recuperationDTO) throws URISyntaxException {
        log.debug("REST request to save Recuperation : {}", recuperationDTO);
        if (recuperationDTO.getId() != null) {
            throw new BadRequestAlertException("A new recuperation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecuperationDTO result = recuperationService.save(recuperationDTO);
        return ResponseEntity.created(new URI("/api/recuperations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recuperations : Updates an existing recuperation.
     *
     * @param recuperationDTO the recuperationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recuperationDTO,
     * or with status 400 (Bad Request) if the recuperationDTO is not valid,
     * or with status 500 (Internal Server Error) if the recuperationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recuperations")
    @Timed
    public ResponseEntity<RecuperationDTO> updateRecuperation(@RequestBody RecuperationDTO recuperationDTO) throws URISyntaxException {
        log.debug("REST request to update Recuperation : {}", recuperationDTO);
        if (recuperationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RecuperationDTO result = recuperationService.save(recuperationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recuperationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recuperations : get all the recuperations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of recuperations in body
     */
    @GetMapping("/recuperations")
    @Timed
    public ResponseEntity<List<RecuperationDTO>> getAllRecuperations(Pageable pageable) {
        log.debug("REST request to get a page of Recuperations");
        Page<RecuperationDTO> page = recuperationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/recuperations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /recuperations/:id : get the "id" recuperation.
     *
     * @param id the id of the recuperationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recuperationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/recuperations/{id}")
    @Timed
    public ResponseEntity<RecuperationDTO> getRecuperation(@PathVariable Long id) {
        log.debug("REST request to get Recuperation : {}", id);
        Optional<RecuperationDTO> recuperationDTO = recuperationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recuperationDTO);
    }

    /**
     * DELETE  /recuperations/:id : delete the "id" recuperation.
     *
     * @param id the id of the recuperationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recuperations/{id}")
    @Timed
    public ResponseEntity<Void> deleteRecuperation(@PathVariable Long id) {
        log.debug("REST request to delete Recuperation : {}", id);
        recuperationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/recuperations?query=:query : search for the recuperation corresponding
     * to the query.
     *
     * @param query the query of the recuperation search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/recuperations")
    @Timed
    public ResponseEntity<List<RecuperationDTO>> searchRecuperations(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Recuperations for query {}", query);
        Page<RecuperationDTO> page = recuperationService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/recuperations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
