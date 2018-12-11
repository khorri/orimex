package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatStatutDossierService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatStatutDossierDTO;
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
 * REST controller for managing AchatStatutDossier.
 */
@RestController
@RequestMapping("/api")
public class AchatStatutDossierResource {

    private final Logger log = LoggerFactory.getLogger(AchatStatutDossierResource.class);

    private static final String ENTITY_NAME = "achatStatutDossier";

    private final AchatStatutDossierService achatStatutDossierService;

    public AchatStatutDossierResource(AchatStatutDossierService achatStatutDossierService) {
        this.achatStatutDossierService = achatStatutDossierService;
    }

    /**
     * POST  /achat-statut-dossiers : Create a new achatStatutDossier.
     *
     * @param achatStatutDossierDTO the achatStatutDossierDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatStatutDossierDTO, or with status 400 (Bad Request) if the achatStatutDossier has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-statut-dossiers")
    @Timed
    public ResponseEntity<AchatStatutDossierDTO> createAchatStatutDossier(@RequestBody AchatStatutDossierDTO achatStatutDossierDTO) throws URISyntaxException {
        log.debug("REST request to save AchatStatutDossier : {}", achatStatutDossierDTO);
        if (achatStatutDossierDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatStatutDossier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatStatutDossierDTO result = achatStatutDossierService.save(achatStatutDossierDTO);
        return ResponseEntity.created(new URI("/api/achat-statut-dossiers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-statut-dossiers : Updates an existing achatStatutDossier.
     *
     * @param achatStatutDossierDTO the achatStatutDossierDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatStatutDossierDTO,
     * or with status 400 (Bad Request) if the achatStatutDossierDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatStatutDossierDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-statut-dossiers")
    @Timed
    public ResponseEntity<AchatStatutDossierDTO> updateAchatStatutDossier(@RequestBody AchatStatutDossierDTO achatStatutDossierDTO) throws URISyntaxException {
        log.debug("REST request to update AchatStatutDossier : {}", achatStatutDossierDTO);
        if (achatStatutDossierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatStatutDossierDTO result = achatStatutDossierService.save(achatStatutDossierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatStatutDossierDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-statut-dossiers : get all the achatStatutDossiers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatStatutDossiers in body
     */
    @GetMapping("/achat-statut-dossiers")
    @Timed
    public ResponseEntity<List<AchatStatutDossierDTO>> getAllAchatStatutDossiers(Pageable pageable) {
        log.debug("REST request to get a page of AchatStatutDossiers");
        Page<AchatStatutDossierDTO> page = achatStatutDossierService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-statut-dossiers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-statut-dossiers/:id : get the "id" achatStatutDossier.
     *
     * @param id the id of the achatStatutDossierDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatStatutDossierDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-statut-dossiers/{id}")
    @Timed
    public ResponseEntity<AchatStatutDossierDTO> getAchatStatutDossier(@PathVariable Long id) {
        log.debug("REST request to get AchatStatutDossier : {}", id);
        Optional<AchatStatutDossierDTO> achatStatutDossierDTO = achatStatutDossierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatStatutDossierDTO);
    }

    /**
     * DELETE  /achat-statut-dossiers/:id : delete the "id" achatStatutDossier.
     *
     * @param id the id of the achatStatutDossierDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-statut-dossiers/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatStatutDossier(@PathVariable Long id) {
        log.debug("REST request to delete AchatStatutDossier : {}", id);
        achatStatutDossierService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-statut-dossiers?query=:query : search for the achatStatutDossier corresponding
     * to the query.
     *
     * @param query the query of the achatStatutDossier search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-statut-dossiers")
    @Timed
    public ResponseEntity<List<AchatStatutDossierDTO>> searchAchatStatutDossiers(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatStatutDossiers for query {}", query);
        Page<AchatStatutDossierDTO> page = achatStatutDossierService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-statut-dossiers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
