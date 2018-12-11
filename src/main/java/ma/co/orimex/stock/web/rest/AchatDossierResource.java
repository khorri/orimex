package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatDossierService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatDossierDTO;
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
 * REST controller for managing AchatDossier.
 */
@RestController
@RequestMapping("/api")
public class AchatDossierResource {

    private final Logger log = LoggerFactory.getLogger(AchatDossierResource.class);

    private static final String ENTITY_NAME = "achatDossier";

    private final AchatDossierService achatDossierService;

    public AchatDossierResource(AchatDossierService achatDossierService) {
        this.achatDossierService = achatDossierService;
    }

    /**
     * POST  /achat-dossiers : Create a new achatDossier.
     *
     * @param achatDossierDTO the achatDossierDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatDossierDTO, or with status 400 (Bad Request) if the achatDossier has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-dossiers")
    @Timed
    public ResponseEntity<AchatDossierDTO> createAchatDossier(@RequestBody AchatDossierDTO achatDossierDTO) throws URISyntaxException {
        log.debug("REST request to save AchatDossier : {}", achatDossierDTO);
        if (achatDossierDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatDossier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatDossierDTO result = achatDossierService.save(achatDossierDTO);
        return ResponseEntity.created(new URI("/api/achat-dossiers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-dossiers : Updates an existing achatDossier.
     *
     * @param achatDossierDTO the achatDossierDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatDossierDTO,
     * or with status 400 (Bad Request) if the achatDossierDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatDossierDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-dossiers")
    @Timed
    public ResponseEntity<AchatDossierDTO> updateAchatDossier(@RequestBody AchatDossierDTO achatDossierDTO) throws URISyntaxException {
        log.debug("REST request to update AchatDossier : {}", achatDossierDTO);
        if (achatDossierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatDossierDTO result = achatDossierService.save(achatDossierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatDossierDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-dossiers : get all the achatDossiers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatDossiers in body
     */
    @GetMapping("/achat-dossiers")
    @Timed
    public ResponseEntity<List<AchatDossierDTO>> getAllAchatDossiers(Pageable pageable) {
        log.debug("REST request to get a page of AchatDossiers");
        Page<AchatDossierDTO> page = achatDossierService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-dossiers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-dossiers/:id : get the "id" achatDossier.
     *
     * @param id the id of the achatDossierDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatDossierDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-dossiers/{id}")
    @Timed
    public ResponseEntity<AchatDossierDTO> getAchatDossier(@PathVariable Long id) {
        log.debug("REST request to get AchatDossier : {}", id);
        Optional<AchatDossierDTO> achatDossierDTO = achatDossierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatDossierDTO);
    }

    /**
     * DELETE  /achat-dossiers/:id : delete the "id" achatDossier.
     *
     * @param id the id of the achatDossierDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-dossiers/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatDossier(@PathVariable Long id) {
        log.debug("REST request to delete AchatDossier : {}", id);
        achatDossierService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-dossiers?query=:query : search for the achatDossier corresponding
     * to the query.
     *
     * @param query the query of the achatDossier search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-dossiers")
    @Timed
    public ResponseEntity<List<AchatDossierDTO>> searchAchatDossiers(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatDossiers for query {}", query);
        Page<AchatDossierDTO> page = achatDossierService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-dossiers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
