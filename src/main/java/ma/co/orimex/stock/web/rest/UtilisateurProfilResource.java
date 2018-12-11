package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.UtilisateurProfilService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.UtilisateurProfilDTO;
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
 * REST controller for managing UtilisateurProfil.
 */
@RestController
@RequestMapping("/api")
public class UtilisateurProfilResource {

    private final Logger log = LoggerFactory.getLogger(UtilisateurProfilResource.class);

    private static final String ENTITY_NAME = "utilisateurProfil";

    private final UtilisateurProfilService utilisateurProfilService;

    public UtilisateurProfilResource(UtilisateurProfilService utilisateurProfilService) {
        this.utilisateurProfilService = utilisateurProfilService;
    }

    /**
     * POST  /utilisateur-profils : Create a new utilisateurProfil.
     *
     * @param utilisateurProfilDTO the utilisateurProfilDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new utilisateurProfilDTO, or with status 400 (Bad Request) if the utilisateurProfil has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/utilisateur-profils")
    @Timed
    public ResponseEntity<UtilisateurProfilDTO> createUtilisateurProfil(@RequestBody UtilisateurProfilDTO utilisateurProfilDTO) throws URISyntaxException {
        log.debug("REST request to save UtilisateurProfil : {}", utilisateurProfilDTO);
        if (utilisateurProfilDTO.getId() != null) {
            throw new BadRequestAlertException("A new utilisateurProfil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UtilisateurProfilDTO result = utilisateurProfilService.save(utilisateurProfilDTO);
        return ResponseEntity.created(new URI("/api/utilisateur-profils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /utilisateur-profils : Updates an existing utilisateurProfil.
     *
     * @param utilisateurProfilDTO the utilisateurProfilDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated utilisateurProfilDTO,
     * or with status 400 (Bad Request) if the utilisateurProfilDTO is not valid,
     * or with status 500 (Internal Server Error) if the utilisateurProfilDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/utilisateur-profils")
    @Timed
    public ResponseEntity<UtilisateurProfilDTO> updateUtilisateurProfil(@RequestBody UtilisateurProfilDTO utilisateurProfilDTO) throws URISyntaxException {
        log.debug("REST request to update UtilisateurProfil : {}", utilisateurProfilDTO);
        if (utilisateurProfilDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UtilisateurProfilDTO result = utilisateurProfilService.save(utilisateurProfilDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, utilisateurProfilDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /utilisateur-profils : get all the utilisateurProfils.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of utilisateurProfils in body
     */
    @GetMapping("/utilisateur-profils")
    @Timed
    public ResponseEntity<List<UtilisateurProfilDTO>> getAllUtilisateurProfils(Pageable pageable) {
        log.debug("REST request to get a page of UtilisateurProfils");
        Page<UtilisateurProfilDTO> page = utilisateurProfilService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/utilisateur-profils");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /utilisateur-profils/:id : get the "id" utilisateurProfil.
     *
     * @param id the id of the utilisateurProfilDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the utilisateurProfilDTO, or with status 404 (Not Found)
     */
    @GetMapping("/utilisateur-profils/{id}")
    @Timed
    public ResponseEntity<UtilisateurProfilDTO> getUtilisateurProfil(@PathVariable Long id) {
        log.debug("REST request to get UtilisateurProfil : {}", id);
        Optional<UtilisateurProfilDTO> utilisateurProfilDTO = utilisateurProfilService.findOne(id);
        return ResponseUtil.wrapOrNotFound(utilisateurProfilDTO);
    }

    /**
     * DELETE  /utilisateur-profils/:id : delete the "id" utilisateurProfil.
     *
     * @param id the id of the utilisateurProfilDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/utilisateur-profils/{id}")
    @Timed
    public ResponseEntity<Void> deleteUtilisateurProfil(@PathVariable Long id) {
        log.debug("REST request to delete UtilisateurProfil : {}", id);
        utilisateurProfilService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/utilisateur-profils?query=:query : search for the utilisateurProfil corresponding
     * to the query.
     *
     * @param query the query of the utilisateurProfil search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/utilisateur-profils")
    @Timed
    public ResponseEntity<List<UtilisateurProfilDTO>> searchUtilisateurProfils(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of UtilisateurProfils for query {}", query);
        Page<UtilisateurProfilDTO> page = utilisateurProfilService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/utilisateur-profils");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
