package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.RetourService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.RetourDTO;
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
 * REST controller for managing Retour.
 */
@RestController
@RequestMapping("/api")
public class RetourResource {

    private final Logger log = LoggerFactory.getLogger(RetourResource.class);

    private static final String ENTITY_NAME = "retour";

    private final RetourService retourService;

    public RetourResource(RetourService retourService) {
        this.retourService = retourService;
    }

    /**
     * POST  /retours : Create a new retour.
     *
     * @param retourDTO the retourDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new retourDTO, or with status 400 (Bad Request) if the retour has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/retours")
    @Timed
    public ResponseEntity<RetourDTO> createRetour(@RequestBody RetourDTO retourDTO) throws URISyntaxException {
        log.debug("REST request to save Retour : {}", retourDTO);
        if (retourDTO.getId() != null) {
            throw new BadRequestAlertException("A new retour cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RetourDTO result = retourService.save(retourDTO);
        return ResponseEntity.created(new URI("/api/retours/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /retours : Updates an existing retour.
     *
     * @param retourDTO the retourDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated retourDTO,
     * or with status 400 (Bad Request) if the retourDTO is not valid,
     * or with status 500 (Internal Server Error) if the retourDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/retours")
    @Timed
    public ResponseEntity<RetourDTO> updateRetour(@RequestBody RetourDTO retourDTO) throws URISyntaxException {
        log.debug("REST request to update Retour : {}", retourDTO);
        if (retourDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RetourDTO result = retourService.save(retourDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, retourDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /retours : get all the retours.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of retours in body
     */
    @GetMapping("/retours")
    @Timed
    public ResponseEntity<List<RetourDTO>> getAllRetours(Pageable pageable) {
        log.debug("REST request to get a page of Retours");
        Page<RetourDTO> page = retourService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/retours");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /retours/:id : get the "id" retour.
     *
     * @param id the id of the retourDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the retourDTO, or with status 404 (Not Found)
     */
    @GetMapping("/retours/{id}")
    @Timed
    public ResponseEntity<RetourDTO> getRetour(@PathVariable Long id) {
        log.debug("REST request to get Retour : {}", id);
        Optional<RetourDTO> retourDTO = retourService.findOne(id);
        return ResponseUtil.wrapOrNotFound(retourDTO);
    }

    /**
     * DELETE  /retours/:id : delete the "id" retour.
     *
     * @param id the id of the retourDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/retours/{id}")
    @Timed
    public ResponseEntity<Void> deleteRetour(@PathVariable Long id) {
        log.debug("REST request to delete Retour : {}", id);
        retourService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/retours?query=:query : search for the retour corresponding
     * to the query.
     *
     * @param query the query of the retour search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/retours")
    @Timed
    public ResponseEntity<List<RetourDTO>> searchRetours(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Retours for query {}", query);
        Page<RetourDTO> page = retourService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/retours");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
