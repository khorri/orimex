package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.DepotService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.DepotDTO;
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
 * REST controller for managing Depot.
 */
@RestController
@RequestMapping("/api")
public class DepotResource {

    private final Logger log = LoggerFactory.getLogger(DepotResource.class);

    private static final String ENTITY_NAME = "depot";

    private final DepotService depotService;

    public DepotResource(DepotService depotService) {
        this.depotService = depotService;
    }

    /**
     * POST  /depots : Create a new depot.
     *
     * @param depotDTO the depotDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new depotDTO, or with status 400 (Bad Request) if the depot has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/depots")
    @Timed
    public ResponseEntity<DepotDTO> createDepot(@RequestBody DepotDTO depotDTO) throws URISyntaxException {
        log.debug("REST request to save Depot : {}", depotDTO);
        if (depotDTO.getId() != null) {
            throw new BadRequestAlertException("A new depot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepotDTO result = depotService.save(depotDTO);
        return ResponseEntity.created(new URI("/api/depots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /depots : Updates an existing depot.
     *
     * @param depotDTO the depotDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated depotDTO,
     * or with status 400 (Bad Request) if the depotDTO is not valid,
     * or with status 500 (Internal Server Error) if the depotDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/depots")
    @Timed
    public ResponseEntity<DepotDTO> updateDepot(@RequestBody DepotDTO depotDTO) throws URISyntaxException {
        log.debug("REST request to update Depot : {}", depotDTO);
        if (depotDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepotDTO result = depotService.save(depotDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, depotDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /depots : get all the depots.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of depots in body
     */
    @GetMapping("/depots")
    @Timed
    public ResponseEntity<List<DepotDTO>> getAllDepots(Pageable pageable) {
        log.debug("REST request to get a page of Depots");
        Page<DepotDTO> page = depotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/depots");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /depots/:id : get the "id" depot.
     *
     * @param id the id of the depotDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the depotDTO, or with status 404 (Not Found)
     */
    @GetMapping("/depots/{id}")
    @Timed
    public ResponseEntity<DepotDTO> getDepot(@PathVariable Long id) {
        log.debug("REST request to get Depot : {}", id);
        Optional<DepotDTO> depotDTO = depotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(depotDTO);
    }

    /**
     * DELETE  /depots/:id : delete the "id" depot.
     *
     * @param id the id of the depotDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/depots/{id}")
    @Timed
    public ResponseEntity<Void> deleteDepot(@PathVariable Long id) {
        log.debug("REST request to delete Depot : {}", id);
        depotService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/depots?query=:query : search for the depot corresponding
     * to the query.
     *
     * @param query the query of the depot search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/depots")
    @Timed
    public ResponseEntity<List<DepotDTO>> searchDepots(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Depots for query {}", query);
        Page<DepotDTO> page = depotService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/depots");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
