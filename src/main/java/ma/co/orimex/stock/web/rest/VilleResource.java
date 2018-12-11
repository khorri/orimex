package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.VilleService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.VilleDTO;
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
 * REST controller for managing Ville.
 */
@RestController
@RequestMapping("/api")
public class VilleResource {

    private final Logger log = LoggerFactory.getLogger(VilleResource.class);

    private static final String ENTITY_NAME = "ville";

    private final VilleService villeService;

    public VilleResource(VilleService villeService) {
        this.villeService = villeService;
    }

    /**
     * POST  /villes : Create a new ville.
     *
     * @param villeDTO the villeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new villeDTO, or with status 400 (Bad Request) if the ville has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/villes")
    @Timed
    public ResponseEntity<VilleDTO> createVille(@RequestBody VilleDTO villeDTO) throws URISyntaxException {
        log.debug("REST request to save Ville : {}", villeDTO);
        if (villeDTO.getId() != null) {
            throw new BadRequestAlertException("A new ville cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VilleDTO result = villeService.save(villeDTO);
        return ResponseEntity.created(new URI("/api/villes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /villes : Updates an existing ville.
     *
     * @param villeDTO the villeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated villeDTO,
     * or with status 400 (Bad Request) if the villeDTO is not valid,
     * or with status 500 (Internal Server Error) if the villeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/villes")
    @Timed
    public ResponseEntity<VilleDTO> updateVille(@RequestBody VilleDTO villeDTO) throws URISyntaxException {
        log.debug("REST request to update Ville : {}", villeDTO);
        if (villeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VilleDTO result = villeService.save(villeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, villeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /villes : get all the villes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of villes in body
     */
    @GetMapping("/villes")
    @Timed
    public ResponseEntity<List<VilleDTO>> getAllVilles(Pageable pageable) {
        log.debug("REST request to get a page of Villes");
        Page<VilleDTO> page = villeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/villes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /villes/:id : get the "id" ville.
     *
     * @param id the id of the villeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the villeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/villes/{id}")
    @Timed
    public ResponseEntity<VilleDTO> getVille(@PathVariable Long id) {
        log.debug("REST request to get Ville : {}", id);
        Optional<VilleDTO> villeDTO = villeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(villeDTO);
    }

    /**
     * DELETE  /villes/:id : delete the "id" ville.
     *
     * @param id the id of the villeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/villes/{id}")
    @Timed
    public ResponseEntity<Void> deleteVille(@PathVariable Long id) {
        log.debug("REST request to delete Ville : {}", id);
        villeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/villes?query=:query : search for the ville corresponding
     * to the query.
     *
     * @param query the query of the ville search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/villes")
    @Timed
    public ResponseEntity<List<VilleDTO>> searchVilles(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Villes for query {}", query);
        Page<VilleDTO> page = villeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/villes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
