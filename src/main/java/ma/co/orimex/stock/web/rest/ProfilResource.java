package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.ProfilService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.ProfilDTO;
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
 * REST controller for managing Profil.
 */
@RestController
@RequestMapping("/api")
public class ProfilResource {

    private final Logger log = LoggerFactory.getLogger(ProfilResource.class);

    private static final String ENTITY_NAME = "profil";

    private final ProfilService profilService;

    public ProfilResource(ProfilService profilService) {
        this.profilService = profilService;
    }

    /**
     * POST  /profils : Create a new profil.
     *
     * @param profilDTO the profilDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new profilDTO, or with status 400 (Bad Request) if the profil has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/profils")
    @Timed
    public ResponseEntity<ProfilDTO> createProfil(@RequestBody ProfilDTO profilDTO) throws URISyntaxException {
        log.debug("REST request to save Profil : {}", profilDTO);
        if (profilDTO.getId() != null) {
            throw new BadRequestAlertException("A new profil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfilDTO result = profilService.save(profilDTO);
        return ResponseEntity.created(new URI("/api/profils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /profils : Updates an existing profil.
     *
     * @param profilDTO the profilDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated profilDTO,
     * or with status 400 (Bad Request) if the profilDTO is not valid,
     * or with status 500 (Internal Server Error) if the profilDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/profils")
    @Timed
    public ResponseEntity<ProfilDTO> updateProfil(@RequestBody ProfilDTO profilDTO) throws URISyntaxException {
        log.debug("REST request to update Profil : {}", profilDTO);
        if (profilDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfilDTO result = profilService.save(profilDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, profilDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /profils : get all the profils.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of profils in body
     */
    @GetMapping("/profils")
    @Timed
    public ResponseEntity<List<ProfilDTO>> getAllProfils(Pageable pageable) {
        log.debug("REST request to get a page of Profils");
        Page<ProfilDTO> page = profilService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/profils");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /profils/:id : get the "id" profil.
     *
     * @param id the id of the profilDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the profilDTO, or with status 404 (Not Found)
     */
    @GetMapping("/profils/{id}")
    @Timed
    public ResponseEntity<ProfilDTO> getProfil(@PathVariable Long id) {
        log.debug("REST request to get Profil : {}", id);
        Optional<ProfilDTO> profilDTO = profilService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profilDTO);
    }

    /**
     * DELETE  /profils/:id : delete the "id" profil.
     *
     * @param id the id of the profilDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/profils/{id}")
    @Timed
    public ResponseEntity<Void> deleteProfil(@PathVariable Long id) {
        log.debug("REST request to delete Profil : {}", id);
        profilService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/profils?query=:query : search for the profil corresponding
     * to the query.
     *
     * @param query the query of the profil search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/profils")
    @Timed
    public ResponseEntity<List<ProfilDTO>> searchProfils(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Profils for query {}", query);
        Page<ProfilDTO> page = profilService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/profils");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
