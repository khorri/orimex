package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.ReceptionService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.ReceptionDTO;
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
 * REST controller for managing Reception.
 */
@RestController
@RequestMapping("/api")
public class ReceptionResource {

    private final Logger log = LoggerFactory.getLogger(ReceptionResource.class);

    private static final String ENTITY_NAME = "reception";

    private final ReceptionService receptionService;

    public ReceptionResource(ReceptionService receptionService) {
        this.receptionService = receptionService;
    }

    /**
     * POST  /receptions : Create a new reception.
     *
     * @param receptionDTO the receptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new receptionDTO, or with status 400 (Bad Request) if the reception has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/receptions")
    @Timed
    public ResponseEntity<ReceptionDTO> createReception(@RequestBody ReceptionDTO receptionDTO) throws URISyntaxException {
        log.debug("REST request to save Reception : {}", receptionDTO);
        if (receptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new reception cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReceptionDTO result = receptionService.save(receptionDTO);
        return ResponseEntity.created(new URI("/api/receptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /receptions : Updates an existing reception.
     *
     * @param receptionDTO the receptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated receptionDTO,
     * or with status 400 (Bad Request) if the receptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the receptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/receptions")
    @Timed
    public ResponseEntity<ReceptionDTO> updateReception(@RequestBody ReceptionDTO receptionDTO) throws URISyntaxException {
        log.debug("REST request to update Reception : {}", receptionDTO);
        if (receptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReceptionDTO result = receptionService.save(receptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, receptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /receptions : get all the receptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of receptions in body
     */
    @GetMapping("/receptions")
    @Timed
    public ResponseEntity<List<ReceptionDTO>> getAllReceptions(Pageable pageable) {
        log.debug("REST request to get a page of Receptions");
        Page<ReceptionDTO> page = receptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/receptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /receptions/:id : get the "id" reception.
     *
     * @param id the id of the receptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the receptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/receptions/{id}")
    @Timed
    public ResponseEntity<ReceptionDTO> getReception(@PathVariable Long id) {
        log.debug("REST request to get Reception : {}", id);
        Optional<ReceptionDTO> receptionDTO = receptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(receptionDTO);
    }

    /**
     * DELETE  /receptions/:id : delete the "id" reception.
     *
     * @param id the id of the receptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/receptions/{id}")
    @Timed
    public ResponseEntity<Void> deleteReception(@PathVariable Long id) {
        log.debug("REST request to delete Reception : {}", id);
        receptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/receptions?query=:query : search for the reception corresponding
     * to the query.
     *
     * @param query the query of the reception search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/receptions")
    @Timed
    public ResponseEntity<List<ReceptionDTO>> searchReceptions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Receptions for query {}", query);
        Page<ReceptionDTO> page = receptionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/receptions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
