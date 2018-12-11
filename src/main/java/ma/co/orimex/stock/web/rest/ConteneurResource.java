package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.ConteneurService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.ConteneurDTO;
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
 * REST controller for managing Conteneur.
 */
@RestController
@RequestMapping("/api")
public class ConteneurResource {

    private final Logger log = LoggerFactory.getLogger(ConteneurResource.class);

    private static final String ENTITY_NAME = "conteneur";

    private final ConteneurService conteneurService;

    public ConteneurResource(ConteneurService conteneurService) {
        this.conteneurService = conteneurService;
    }

    /**
     * POST  /conteneurs : Create a new conteneur.
     *
     * @param conteneurDTO the conteneurDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new conteneurDTO, or with status 400 (Bad Request) if the conteneur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/conteneurs")
    @Timed
    public ResponseEntity<ConteneurDTO> createConteneur(@RequestBody ConteneurDTO conteneurDTO) throws URISyntaxException {
        log.debug("REST request to save Conteneur : {}", conteneurDTO);
        if (conteneurDTO.getId() != null) {
            throw new BadRequestAlertException("A new conteneur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConteneurDTO result = conteneurService.save(conteneurDTO);
        return ResponseEntity.created(new URI("/api/conteneurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /conteneurs : Updates an existing conteneur.
     *
     * @param conteneurDTO the conteneurDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated conteneurDTO,
     * or with status 400 (Bad Request) if the conteneurDTO is not valid,
     * or with status 500 (Internal Server Error) if the conteneurDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/conteneurs")
    @Timed
    public ResponseEntity<ConteneurDTO> updateConteneur(@RequestBody ConteneurDTO conteneurDTO) throws URISyntaxException {
        log.debug("REST request to update Conteneur : {}", conteneurDTO);
        if (conteneurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConteneurDTO result = conteneurService.save(conteneurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, conteneurDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /conteneurs : get all the conteneurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of conteneurs in body
     */
    @GetMapping("/conteneurs")
    @Timed
    public ResponseEntity<List<ConteneurDTO>> getAllConteneurs(Pageable pageable) {
        log.debug("REST request to get a page of Conteneurs");
        Page<ConteneurDTO> page = conteneurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conteneurs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /conteneurs/:id : get the "id" conteneur.
     *
     * @param id the id of the conteneurDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the conteneurDTO, or with status 404 (Not Found)
     */
    @GetMapping("/conteneurs/{id}")
    @Timed
    public ResponseEntity<ConteneurDTO> getConteneur(@PathVariable Long id) {
        log.debug("REST request to get Conteneur : {}", id);
        Optional<ConteneurDTO> conteneurDTO = conteneurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(conteneurDTO);
    }

    /**
     * DELETE  /conteneurs/:id : delete the "id" conteneur.
     *
     * @param id the id of the conteneurDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/conteneurs/{id}")
    @Timed
    public ResponseEntity<Void> deleteConteneur(@PathVariable Long id) {
        log.debug("REST request to delete Conteneur : {}", id);
        conteneurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/conteneurs?query=:query : search for the conteneur corresponding
     * to the query.
     *
     * @param query the query of the conteneur search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/conteneurs")
    @Timed
    public ResponseEntity<List<ConteneurDTO>> searchConteneurs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Conteneurs for query {}", query);
        Page<ConteneurDTO> page = conteneurService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/conteneurs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
