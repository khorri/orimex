package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.SortieService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.SortieDTO;
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
 * REST controller for managing Sortie.
 */
@RestController
@RequestMapping("/api")
public class SortieResource {

    private final Logger log = LoggerFactory.getLogger(SortieResource.class);

    private static final String ENTITY_NAME = "sortie";

    private final SortieService sortieService;

    public SortieResource(SortieService sortieService) {
        this.sortieService = sortieService;
    }

    /**
     * POST  /sorties : Create a new sortie.
     *
     * @param sortieDTO the sortieDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sortieDTO, or with status 400 (Bad Request) if the sortie has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sorties")
    @Timed
    public ResponseEntity<SortieDTO> createSortie(@RequestBody SortieDTO sortieDTO) throws URISyntaxException {
        log.debug("REST request to save Sortie : {}", sortieDTO);
        if (sortieDTO.getId() != null) {
            throw new BadRequestAlertException("A new sortie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SortieDTO result = sortieService.save(sortieDTO);
        return ResponseEntity.created(new URI("/api/sorties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sorties : Updates an existing sortie.
     *
     * @param sortieDTO the sortieDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sortieDTO,
     * or with status 400 (Bad Request) if the sortieDTO is not valid,
     * or with status 500 (Internal Server Error) if the sortieDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sorties")
    @Timed
    public ResponseEntity<SortieDTO> updateSortie(@RequestBody SortieDTO sortieDTO) throws URISyntaxException {
        log.debug("REST request to update Sortie : {}", sortieDTO);
        if (sortieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SortieDTO result = sortieService.save(sortieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sortieDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sorties : get all the sorties.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sorties in body
     */
    @GetMapping("/sorties")
    @Timed
    public ResponseEntity<List<SortieDTO>> getAllSorties(Pageable pageable) {
        log.debug("REST request to get a page of Sorties");
        Page<SortieDTO> page = sortieService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sorties");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /sorties/:id : get the "id" sortie.
     *
     * @param id the id of the sortieDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sortieDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sorties/{id}")
    @Timed
    public ResponseEntity<SortieDTO> getSortie(@PathVariable Long id) {
        log.debug("REST request to get Sortie : {}", id);
        Optional<SortieDTO> sortieDTO = sortieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sortieDTO);
    }

    /**
     * DELETE  /sorties/:id : delete the "id" sortie.
     *
     * @param id the id of the sortieDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sorties/{id}")
    @Timed
    public ResponseEntity<Void> deleteSortie(@PathVariable Long id) {
        log.debug("REST request to delete Sortie : {}", id);
        sortieService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sorties?query=:query : search for the sortie corresponding
     * to the query.
     *
     * @param query the query of the sortie search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/sorties")
    @Timed
    public ResponseEntity<List<SortieDTO>> searchSorties(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Sorties for query {}", query);
        Page<SortieDTO> page = sortieService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sorties");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
