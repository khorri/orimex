package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.ProfilActionService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.ProfilActionDTO;
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
 * REST controller for managing ProfilAction.
 */
@RestController
@RequestMapping("/api")
public class ProfilActionResource {

    private final Logger log = LoggerFactory.getLogger(ProfilActionResource.class);

    private static final String ENTITY_NAME = "profilAction";

    private final ProfilActionService profilActionService;

    public ProfilActionResource(ProfilActionService profilActionService) {
        this.profilActionService = profilActionService;
    }

    /**
     * POST  /profil-actions : Create a new profilAction.
     *
     * @param profilActionDTO the profilActionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new profilActionDTO, or with status 400 (Bad Request) if the profilAction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/profil-actions")
    @Timed
    public ResponseEntity<ProfilActionDTO> createProfilAction(@RequestBody ProfilActionDTO profilActionDTO) throws URISyntaxException {
        log.debug("REST request to save ProfilAction : {}", profilActionDTO);
        if (profilActionDTO.getId() != null) {
            throw new BadRequestAlertException("A new profilAction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfilActionDTO result = profilActionService.save(profilActionDTO);
        return ResponseEntity.created(new URI("/api/profil-actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /profil-actions : Updates an existing profilAction.
     *
     * @param profilActionDTO the profilActionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated profilActionDTO,
     * or with status 400 (Bad Request) if the profilActionDTO is not valid,
     * or with status 500 (Internal Server Error) if the profilActionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/profil-actions")
    @Timed
    public ResponseEntity<ProfilActionDTO> updateProfilAction(@RequestBody ProfilActionDTO profilActionDTO) throws URISyntaxException {
        log.debug("REST request to update ProfilAction : {}", profilActionDTO);
        if (profilActionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfilActionDTO result = profilActionService.save(profilActionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, profilActionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /profil-actions : get all the profilActions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of profilActions in body
     */
    @GetMapping("/profil-actions")
    @Timed
    public ResponseEntity<List<ProfilActionDTO>> getAllProfilActions(Pageable pageable) {
        log.debug("REST request to get a page of ProfilActions");
        Page<ProfilActionDTO> page = profilActionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/profil-actions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /profil-actions/:id : get the "id" profilAction.
     *
     * @param id the id of the profilActionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the profilActionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/profil-actions/{id}")
    @Timed
    public ResponseEntity<ProfilActionDTO> getProfilAction(@PathVariable Long id) {
        log.debug("REST request to get ProfilAction : {}", id);
        Optional<ProfilActionDTO> profilActionDTO = profilActionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profilActionDTO);
    }

    /**
     * DELETE  /profil-actions/:id : delete the "id" profilAction.
     *
     * @param id the id of the profilActionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/profil-actions/{id}")
    @Timed
    public ResponseEntity<Void> deleteProfilAction(@PathVariable Long id) {
        log.debug("REST request to delete ProfilAction : {}", id);
        profilActionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/profil-actions?query=:query : search for the profilAction corresponding
     * to the query.
     *
     * @param query the query of the profilAction search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/profil-actions")
    @Timed
    public ResponseEntity<List<ProfilActionDTO>> searchProfilActions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ProfilActions for query {}", query);
        Page<ProfilActionDTO> page = profilActionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/profil-actions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
