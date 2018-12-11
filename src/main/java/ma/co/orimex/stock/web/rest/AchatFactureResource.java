package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatFactureService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatFactureDTO;
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
 * REST controller for managing AchatFacture.
 */
@RestController
@RequestMapping("/api")
public class AchatFactureResource {

    private final Logger log = LoggerFactory.getLogger(AchatFactureResource.class);

    private static final String ENTITY_NAME = "achatFacture";

    private final AchatFactureService achatFactureService;

    public AchatFactureResource(AchatFactureService achatFactureService) {
        this.achatFactureService = achatFactureService;
    }

    /**
     * POST  /achat-factures : Create a new achatFacture.
     *
     * @param achatFactureDTO the achatFactureDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatFactureDTO, or with status 400 (Bad Request) if the achatFacture has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-factures")
    @Timed
    public ResponseEntity<AchatFactureDTO> createAchatFacture(@RequestBody AchatFactureDTO achatFactureDTO) throws URISyntaxException {
        log.debug("REST request to save AchatFacture : {}", achatFactureDTO);
        if (achatFactureDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatFacture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatFactureDTO result = achatFactureService.save(achatFactureDTO);
        return ResponseEntity.created(new URI("/api/achat-factures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-factures : Updates an existing achatFacture.
     *
     * @param achatFactureDTO the achatFactureDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatFactureDTO,
     * or with status 400 (Bad Request) if the achatFactureDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatFactureDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-factures")
    @Timed
    public ResponseEntity<AchatFactureDTO> updateAchatFacture(@RequestBody AchatFactureDTO achatFactureDTO) throws URISyntaxException {
        log.debug("REST request to update AchatFacture : {}", achatFactureDTO);
        if (achatFactureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatFactureDTO result = achatFactureService.save(achatFactureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatFactureDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-factures : get all the achatFactures.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatFactures in body
     */
    @GetMapping("/achat-factures")
    @Timed
    public ResponseEntity<List<AchatFactureDTO>> getAllAchatFactures(Pageable pageable) {
        log.debug("REST request to get a page of AchatFactures");
        Page<AchatFactureDTO> page = achatFactureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-factures");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-factures/:id : get the "id" achatFacture.
     *
     * @param id the id of the achatFactureDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatFactureDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-factures/{id}")
    @Timed
    public ResponseEntity<AchatFactureDTO> getAchatFacture(@PathVariable Long id) {
        log.debug("REST request to get AchatFacture : {}", id);
        Optional<AchatFactureDTO> achatFactureDTO = achatFactureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatFactureDTO);
    }

    /**
     * DELETE  /achat-factures/:id : delete the "id" achatFacture.
     *
     * @param id the id of the achatFactureDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-factures/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatFacture(@PathVariable Long id) {
        log.debug("REST request to delete AchatFacture : {}", id);
        achatFactureService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-factures?query=:query : search for the achatFacture corresponding
     * to the query.
     *
     * @param query the query of the achatFacture search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-factures")
    @Timed
    public ResponseEntity<List<AchatFactureDTO>> searchAchatFactures(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatFactures for query {}", query);
        Page<AchatFactureDTO> page = achatFactureService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-factures");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
