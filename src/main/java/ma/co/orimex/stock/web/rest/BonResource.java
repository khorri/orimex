package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.BonService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.BonDTO;
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
 * REST controller for managing Bon.
 */
@RestController
@RequestMapping("/api")
public class BonResource {

    private final Logger log = LoggerFactory.getLogger(BonResource.class);

    private static final String ENTITY_NAME = "bon";

    private final BonService bonService;

    public BonResource(BonService bonService) {
        this.bonService = bonService;
    }

    /**
     * POST  /bons : Create a new bon.
     *
     * @param bonDTO the bonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bonDTO, or with status 400 (Bad Request) if the bon has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bons")
    @Timed
    public ResponseEntity<BonDTO> createBon(@RequestBody BonDTO bonDTO) throws URISyntaxException {
        log.debug("REST request to save Bon : {}", bonDTO);
        if (bonDTO.getId() != null) {
            throw new BadRequestAlertException("A new bon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BonDTO result = bonService.save(bonDTO);
        return ResponseEntity.created(new URI("/api/bons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bons : Updates an existing bon.
     *
     * @param bonDTO the bonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bonDTO,
     * or with status 400 (Bad Request) if the bonDTO is not valid,
     * or with status 500 (Internal Server Error) if the bonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bons")
    @Timed
    public ResponseEntity<BonDTO> updateBon(@RequestBody BonDTO bonDTO) throws URISyntaxException {
        log.debug("REST request to update Bon : {}", bonDTO);
        if (bonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BonDTO result = bonService.save(bonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bons : get all the bons.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bons in body
     */
    @GetMapping("/bons")
    @Timed
    public ResponseEntity<List<BonDTO>> getAllBons(Pageable pageable) {
        log.debug("REST request to get a page of Bons");
        Page<BonDTO> page = bonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bons");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /bons/:id : get the "id" bon.
     *
     * @param id the id of the bonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bons/{id}")
    @Timed
    public ResponseEntity<BonDTO> getBon(@PathVariable Long id) {
        log.debug("REST request to get Bon : {}", id);
        Optional<BonDTO> bonDTO = bonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bonDTO);
    }

    /**
     * DELETE  /bons/:id : delete the "id" bon.
     *
     * @param id the id of the bonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bons/{id}")
    @Timed
    public ResponseEntity<Void> deleteBon(@PathVariable Long id) {
        log.debug("REST request to delete Bon : {}", id);
        bonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/bons?query=:query : search for the bon corresponding
     * to the query.
     *
     * @param query the query of the bon search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/bons")
    @Timed
    public ResponseEntity<List<BonDTO>> searchBons(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Bons for query {}", query);
        Page<BonDTO> page = bonService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/bons");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
