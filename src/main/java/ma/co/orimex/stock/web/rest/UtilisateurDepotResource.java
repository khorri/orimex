package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.UtilisateurDepotService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.UtilisateurDepotDTO;
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
 * REST controller for managing UtilisateurDepot.
 */
@RestController
@RequestMapping("/api")
public class UtilisateurDepotResource {

    private final Logger log = LoggerFactory.getLogger(UtilisateurDepotResource.class);

    private static final String ENTITY_NAME = "utilisateurDepot";

    private final UtilisateurDepotService utilisateurDepotService;

    public UtilisateurDepotResource(UtilisateurDepotService utilisateurDepotService) {
        this.utilisateurDepotService = utilisateurDepotService;
    }

    /**
     * POST  /utilisateur-depots : Create a new utilisateurDepot.
     *
     * @param utilisateurDepotDTO the utilisateurDepotDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new utilisateurDepotDTO, or with status 400 (Bad Request) if the utilisateurDepot has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/utilisateur-depots")
    @Timed
    public ResponseEntity<UtilisateurDepotDTO> createUtilisateurDepot(@RequestBody UtilisateurDepotDTO utilisateurDepotDTO) throws URISyntaxException {
        log.debug("REST request to save UtilisateurDepot : {}", utilisateurDepotDTO);
        if (utilisateurDepotDTO.getId() != null) {
            throw new BadRequestAlertException("A new utilisateurDepot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UtilisateurDepotDTO result = utilisateurDepotService.save(utilisateurDepotDTO);
        return ResponseEntity.created(new URI("/api/utilisateur-depots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /utilisateur-depots : Updates an existing utilisateurDepot.
     *
     * @param utilisateurDepotDTO the utilisateurDepotDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated utilisateurDepotDTO,
     * or with status 400 (Bad Request) if the utilisateurDepotDTO is not valid,
     * or with status 500 (Internal Server Error) if the utilisateurDepotDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/utilisateur-depots")
    @Timed
    public ResponseEntity<UtilisateurDepotDTO> updateUtilisateurDepot(@RequestBody UtilisateurDepotDTO utilisateurDepotDTO) throws URISyntaxException {
        log.debug("REST request to update UtilisateurDepot : {}", utilisateurDepotDTO);
        if (utilisateurDepotDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UtilisateurDepotDTO result = utilisateurDepotService.save(utilisateurDepotDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, utilisateurDepotDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /utilisateur-depots : get all the utilisateurDepots.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of utilisateurDepots in body
     */
    @GetMapping("/utilisateur-depots")
    @Timed
    public ResponseEntity<List<UtilisateurDepotDTO>> getAllUtilisateurDepots(Pageable pageable) {
        log.debug("REST request to get a page of UtilisateurDepots");
        Page<UtilisateurDepotDTO> page = utilisateurDepotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/utilisateur-depots");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /utilisateur-depots/:id : get the "id" utilisateurDepot.
     *
     * @param id the id of the utilisateurDepotDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the utilisateurDepotDTO, or with status 404 (Not Found)
     */
    @GetMapping("/utilisateur-depots/{id}")
    @Timed
    public ResponseEntity<UtilisateurDepotDTO> getUtilisateurDepot(@PathVariable Long id) {
        log.debug("REST request to get UtilisateurDepot : {}", id);
        Optional<UtilisateurDepotDTO> utilisateurDepotDTO = utilisateurDepotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(utilisateurDepotDTO);
    }

    /**
     * DELETE  /utilisateur-depots/:id : delete the "id" utilisateurDepot.
     *
     * @param id the id of the utilisateurDepotDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/utilisateur-depots/{id}")
    @Timed
    public ResponseEntity<Void> deleteUtilisateurDepot(@PathVariable Long id) {
        log.debug("REST request to delete UtilisateurDepot : {}", id);
        utilisateurDepotService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/utilisateur-depots?query=:query : search for the utilisateurDepot corresponding
     * to the query.
     *
     * @param query the query of the utilisateurDepot search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/utilisateur-depots")
    @Timed
    public ResponseEntity<List<UtilisateurDepotDTO>> searchUtilisateurDepots(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of UtilisateurDepots for query {}", query);
        Page<UtilisateurDepotDTO> page = utilisateurDepotService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/utilisateur-depots");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
