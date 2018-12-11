package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.UtilisateurProfilPKService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.UtilisateurProfilPKDTO;
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
 * REST controller for managing UtilisateurProfilPK.
 */
@RestController
@RequestMapping("/api")
public class UtilisateurProfilPKResource {

    private final Logger log = LoggerFactory.getLogger(UtilisateurProfilPKResource.class);

    private static final String ENTITY_NAME = "utilisateurProfilPK";

    private final UtilisateurProfilPKService utilisateurProfilPKService;

    public UtilisateurProfilPKResource(UtilisateurProfilPKService utilisateurProfilPKService) {
        this.utilisateurProfilPKService = utilisateurProfilPKService;
    }

    /**
     * POST  /utilisateur-profil-pks : Create a new utilisateurProfilPK.
     *
     * @param utilisateurProfilPKDTO the utilisateurProfilPKDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new utilisateurProfilPKDTO, or with status 400 (Bad Request) if the utilisateurProfilPK has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/utilisateur-profil-pks")
    @Timed
    public ResponseEntity<UtilisateurProfilPKDTO> createUtilisateurProfilPK(@RequestBody UtilisateurProfilPKDTO utilisateurProfilPKDTO) throws URISyntaxException {
        log.debug("REST request to save UtilisateurProfilPK : {}", utilisateurProfilPKDTO);
        if (utilisateurProfilPKDTO.getId() != null) {
            throw new BadRequestAlertException("A new utilisateurProfilPK cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UtilisateurProfilPKDTO result = utilisateurProfilPKService.save(utilisateurProfilPKDTO);
        return ResponseEntity.created(new URI("/api/utilisateur-profil-pks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /utilisateur-profil-pks : Updates an existing utilisateurProfilPK.
     *
     * @param utilisateurProfilPKDTO the utilisateurProfilPKDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated utilisateurProfilPKDTO,
     * or with status 400 (Bad Request) if the utilisateurProfilPKDTO is not valid,
     * or with status 500 (Internal Server Error) if the utilisateurProfilPKDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/utilisateur-profil-pks")
    @Timed
    public ResponseEntity<UtilisateurProfilPKDTO> updateUtilisateurProfilPK(@RequestBody UtilisateurProfilPKDTO utilisateurProfilPKDTO) throws URISyntaxException {
        log.debug("REST request to update UtilisateurProfilPK : {}", utilisateurProfilPKDTO);
        if (utilisateurProfilPKDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UtilisateurProfilPKDTO result = utilisateurProfilPKService.save(utilisateurProfilPKDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, utilisateurProfilPKDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /utilisateur-profil-pks : get all the utilisateurProfilPKS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of utilisateurProfilPKS in body
     */
    @GetMapping("/utilisateur-profil-pks")
    @Timed
    public ResponseEntity<List<UtilisateurProfilPKDTO>> getAllUtilisateurProfilPKS(Pageable pageable) {
        log.debug("REST request to get a page of UtilisateurProfilPKS");
        Page<UtilisateurProfilPKDTO> page = utilisateurProfilPKService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/utilisateur-profil-pks");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /utilisateur-profil-pks/:id : get the "id" utilisateurProfilPK.
     *
     * @param id the id of the utilisateurProfilPKDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the utilisateurProfilPKDTO, or with status 404 (Not Found)
     */
    @GetMapping("/utilisateur-profil-pks/{id}")
    @Timed
    public ResponseEntity<UtilisateurProfilPKDTO> getUtilisateurProfilPK(@PathVariable Long id) {
        log.debug("REST request to get UtilisateurProfilPK : {}", id);
        Optional<UtilisateurProfilPKDTO> utilisateurProfilPKDTO = utilisateurProfilPKService.findOne(id);
        return ResponseUtil.wrapOrNotFound(utilisateurProfilPKDTO);
    }

    /**
     * DELETE  /utilisateur-profil-pks/:id : delete the "id" utilisateurProfilPK.
     *
     * @param id the id of the utilisateurProfilPKDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/utilisateur-profil-pks/{id}")
    @Timed
    public ResponseEntity<Void> deleteUtilisateurProfilPK(@PathVariable Long id) {
        log.debug("REST request to delete UtilisateurProfilPK : {}", id);
        utilisateurProfilPKService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/utilisateur-profil-pks?query=:query : search for the utilisateurProfilPK corresponding
     * to the query.
     *
     * @param query the query of the utilisateurProfilPK search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/utilisateur-profil-pks")
    @Timed
    public ResponseEntity<List<UtilisateurProfilPKDTO>> searchUtilisateurProfilPKS(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of UtilisateurProfilPKS for query {}", query);
        Page<UtilisateurProfilPKDTO> page = utilisateurProfilPKService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/utilisateur-profil-pks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
