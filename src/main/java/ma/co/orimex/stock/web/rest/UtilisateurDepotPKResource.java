package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.UtilisateurDepotPKService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.UtilisateurDepotPKDTO;
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
 * REST controller for managing UtilisateurDepotPK.
 */
@RestController
@RequestMapping("/api")
public class UtilisateurDepotPKResource {

    private final Logger log = LoggerFactory.getLogger(UtilisateurDepotPKResource.class);

    private static final String ENTITY_NAME = "utilisateurDepotPK";

    private final UtilisateurDepotPKService utilisateurDepotPKService;

    public UtilisateurDepotPKResource(UtilisateurDepotPKService utilisateurDepotPKService) {
        this.utilisateurDepotPKService = utilisateurDepotPKService;
    }

    /**
     * POST  /utilisateur-depot-pks : Create a new utilisateurDepotPK.
     *
     * @param utilisateurDepotPKDTO the utilisateurDepotPKDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new utilisateurDepotPKDTO, or with status 400 (Bad Request) if the utilisateurDepotPK has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/utilisateur-depot-pks")
    @Timed
    public ResponseEntity<UtilisateurDepotPKDTO> createUtilisateurDepotPK(@RequestBody UtilisateurDepotPKDTO utilisateurDepotPKDTO) throws URISyntaxException {
        log.debug("REST request to save UtilisateurDepotPK : {}", utilisateurDepotPKDTO);
        if (utilisateurDepotPKDTO.getId() != null) {
            throw new BadRequestAlertException("A new utilisateurDepotPK cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UtilisateurDepotPKDTO result = utilisateurDepotPKService.save(utilisateurDepotPKDTO);
        return ResponseEntity.created(new URI("/api/utilisateur-depot-pks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /utilisateur-depot-pks : Updates an existing utilisateurDepotPK.
     *
     * @param utilisateurDepotPKDTO the utilisateurDepotPKDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated utilisateurDepotPKDTO,
     * or with status 400 (Bad Request) if the utilisateurDepotPKDTO is not valid,
     * or with status 500 (Internal Server Error) if the utilisateurDepotPKDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/utilisateur-depot-pks")
    @Timed
    public ResponseEntity<UtilisateurDepotPKDTO> updateUtilisateurDepotPK(@RequestBody UtilisateurDepotPKDTO utilisateurDepotPKDTO) throws URISyntaxException {
        log.debug("REST request to update UtilisateurDepotPK : {}", utilisateurDepotPKDTO);
        if (utilisateurDepotPKDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UtilisateurDepotPKDTO result = utilisateurDepotPKService.save(utilisateurDepotPKDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, utilisateurDepotPKDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /utilisateur-depot-pks : get all the utilisateurDepotPKS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of utilisateurDepotPKS in body
     */
    @GetMapping("/utilisateur-depot-pks")
    @Timed
    public ResponseEntity<List<UtilisateurDepotPKDTO>> getAllUtilisateurDepotPKS(Pageable pageable) {
        log.debug("REST request to get a page of UtilisateurDepotPKS");
        Page<UtilisateurDepotPKDTO> page = utilisateurDepotPKService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/utilisateur-depot-pks");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /utilisateur-depot-pks/:id : get the "id" utilisateurDepotPK.
     *
     * @param id the id of the utilisateurDepotPKDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the utilisateurDepotPKDTO, or with status 404 (Not Found)
     */
    @GetMapping("/utilisateur-depot-pks/{id}")
    @Timed
    public ResponseEntity<UtilisateurDepotPKDTO> getUtilisateurDepotPK(@PathVariable Long id) {
        log.debug("REST request to get UtilisateurDepotPK : {}", id);
        Optional<UtilisateurDepotPKDTO> utilisateurDepotPKDTO = utilisateurDepotPKService.findOne(id);
        return ResponseUtil.wrapOrNotFound(utilisateurDepotPKDTO);
    }

    /**
     * DELETE  /utilisateur-depot-pks/:id : delete the "id" utilisateurDepotPK.
     *
     * @param id the id of the utilisateurDepotPKDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/utilisateur-depot-pks/{id}")
    @Timed
    public ResponseEntity<Void> deleteUtilisateurDepotPK(@PathVariable Long id) {
        log.debug("REST request to delete UtilisateurDepotPK : {}", id);
        utilisateurDepotPKService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/utilisateur-depot-pks?query=:query : search for the utilisateurDepotPK corresponding
     * to the query.
     *
     * @param query the query of the utilisateurDepotPK search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/utilisateur-depot-pks")
    @Timed
    public ResponseEntity<List<UtilisateurDepotPKDTO>> searchUtilisateurDepotPKS(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of UtilisateurDepotPKS for query {}", query);
        Page<UtilisateurDepotPKDTO> page = utilisateurDepotPKService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/utilisateur-depot-pks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
