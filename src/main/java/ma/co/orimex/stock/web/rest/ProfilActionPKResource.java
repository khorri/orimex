package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.ProfilActionPKService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.ProfilActionPKDTO;
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
 * REST controller for managing ProfilActionPK.
 */
@RestController
@RequestMapping("/api")
public class ProfilActionPKResource {

    private final Logger log = LoggerFactory.getLogger(ProfilActionPKResource.class);

    private static final String ENTITY_NAME = "profilActionPK";

    private final ProfilActionPKService profilActionPKService;

    public ProfilActionPKResource(ProfilActionPKService profilActionPKService) {
        this.profilActionPKService = profilActionPKService;
    }

    /**
     * POST  /profil-action-pks : Create a new profilActionPK.
     *
     * @param profilActionPKDTO the profilActionPKDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new profilActionPKDTO, or with status 400 (Bad Request) if the profilActionPK has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/profil-action-pks")
    @Timed
    public ResponseEntity<ProfilActionPKDTO> createProfilActionPK(@RequestBody ProfilActionPKDTO profilActionPKDTO) throws URISyntaxException {
        log.debug("REST request to save ProfilActionPK : {}", profilActionPKDTO);
        if (profilActionPKDTO.getId() != null) {
            throw new BadRequestAlertException("A new profilActionPK cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfilActionPKDTO result = profilActionPKService.save(profilActionPKDTO);
        return ResponseEntity.created(new URI("/api/profil-action-pks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /profil-action-pks : Updates an existing profilActionPK.
     *
     * @param profilActionPKDTO the profilActionPKDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated profilActionPKDTO,
     * or with status 400 (Bad Request) if the profilActionPKDTO is not valid,
     * or with status 500 (Internal Server Error) if the profilActionPKDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/profil-action-pks")
    @Timed
    public ResponseEntity<ProfilActionPKDTO> updateProfilActionPK(@RequestBody ProfilActionPKDTO profilActionPKDTO) throws URISyntaxException {
        log.debug("REST request to update ProfilActionPK : {}", profilActionPKDTO);
        if (profilActionPKDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfilActionPKDTO result = profilActionPKService.save(profilActionPKDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, profilActionPKDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /profil-action-pks : get all the profilActionPKS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of profilActionPKS in body
     */
    @GetMapping("/profil-action-pks")
    @Timed
    public ResponseEntity<List<ProfilActionPKDTO>> getAllProfilActionPKS(Pageable pageable) {
        log.debug("REST request to get a page of ProfilActionPKS");
        Page<ProfilActionPKDTO> page = profilActionPKService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/profil-action-pks");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /profil-action-pks/:id : get the "id" profilActionPK.
     *
     * @param id the id of the profilActionPKDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the profilActionPKDTO, or with status 404 (Not Found)
     */
    @GetMapping("/profil-action-pks/{id}")
    @Timed
    public ResponseEntity<ProfilActionPKDTO> getProfilActionPK(@PathVariable Long id) {
        log.debug("REST request to get ProfilActionPK : {}", id);
        Optional<ProfilActionPKDTO> profilActionPKDTO = profilActionPKService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profilActionPKDTO);
    }

    /**
     * DELETE  /profil-action-pks/:id : delete the "id" profilActionPK.
     *
     * @param id the id of the profilActionPKDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/profil-action-pks/{id}")
    @Timed
    public ResponseEntity<Void> deleteProfilActionPK(@PathVariable Long id) {
        log.debug("REST request to delete ProfilActionPK : {}", id);
        profilActionPKService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/profil-action-pks?query=:query : search for the profilActionPK corresponding
     * to the query.
     *
     * @param query the query of the profilActionPK search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/profil-action-pks")
    @Timed
    public ResponseEntity<List<ProfilActionPKDTO>> searchProfilActionPKS(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ProfilActionPKS for query {}", query);
        Page<ProfilActionPKDTO> page = profilActionPKService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/profil-action-pks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
