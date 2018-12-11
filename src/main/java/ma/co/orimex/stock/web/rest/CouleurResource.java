package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.CouleurService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.CouleurDTO;
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
 * REST controller for managing Couleur.
 */
@RestController
@RequestMapping("/api")
public class CouleurResource {

    private final Logger log = LoggerFactory.getLogger(CouleurResource.class);

    private static final String ENTITY_NAME = "couleur";

    private final CouleurService couleurService;

    public CouleurResource(CouleurService couleurService) {
        this.couleurService = couleurService;
    }

    /**
     * POST  /couleurs : Create a new couleur.
     *
     * @param couleurDTO the couleurDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new couleurDTO, or with status 400 (Bad Request) if the couleur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/couleurs")
    @Timed
    public ResponseEntity<CouleurDTO> createCouleur(@RequestBody CouleurDTO couleurDTO) throws URISyntaxException {
        log.debug("REST request to save Couleur : {}", couleurDTO);
        if (couleurDTO.getId() != null) {
            throw new BadRequestAlertException("A new couleur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CouleurDTO result = couleurService.save(couleurDTO);
        return ResponseEntity.created(new URI("/api/couleurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /couleurs : Updates an existing couleur.
     *
     * @param couleurDTO the couleurDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated couleurDTO,
     * or with status 400 (Bad Request) if the couleurDTO is not valid,
     * or with status 500 (Internal Server Error) if the couleurDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/couleurs")
    @Timed
    public ResponseEntity<CouleurDTO> updateCouleur(@RequestBody CouleurDTO couleurDTO) throws URISyntaxException {
        log.debug("REST request to update Couleur : {}", couleurDTO);
        if (couleurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CouleurDTO result = couleurService.save(couleurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, couleurDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /couleurs : get all the couleurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of couleurs in body
     */
    @GetMapping("/couleurs")
    @Timed
    public ResponseEntity<List<CouleurDTO>> getAllCouleurs(Pageable pageable) {
        log.debug("REST request to get a page of Couleurs");
        Page<CouleurDTO> page = couleurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/couleurs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /couleurs/:id : get the "id" couleur.
     *
     * @param id the id of the couleurDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the couleurDTO, or with status 404 (Not Found)
     */
    @GetMapping("/couleurs/{id}")
    @Timed
    public ResponseEntity<CouleurDTO> getCouleur(@PathVariable Long id) {
        log.debug("REST request to get Couleur : {}", id);
        Optional<CouleurDTO> couleurDTO = couleurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(couleurDTO);
    }

    /**
     * DELETE  /couleurs/:id : delete the "id" couleur.
     *
     * @param id the id of the couleurDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/couleurs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCouleur(@PathVariable Long id) {
        log.debug("REST request to delete Couleur : {}", id);
        couleurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/couleurs?query=:query : search for the couleur corresponding
     * to the query.
     *
     * @param query the query of the couleur search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/couleurs")
    @Timed
    public ResponseEntity<List<CouleurDTO>> searchCouleurs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Couleurs for query {}", query);
        Page<CouleurDTO> page = couleurService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/couleurs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
