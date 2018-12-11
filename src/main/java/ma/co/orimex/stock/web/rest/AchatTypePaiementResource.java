package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatTypePaiementService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatTypePaiementDTO;
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
 * REST controller for managing AchatTypePaiement.
 */
@RestController
@RequestMapping("/api")
public class AchatTypePaiementResource {

    private final Logger log = LoggerFactory.getLogger(AchatTypePaiementResource.class);

    private static final String ENTITY_NAME = "achatTypePaiement";

    private final AchatTypePaiementService achatTypePaiementService;

    public AchatTypePaiementResource(AchatTypePaiementService achatTypePaiementService) {
        this.achatTypePaiementService = achatTypePaiementService;
    }

    /**
     * POST  /achat-type-paiements : Create a new achatTypePaiement.
     *
     * @param achatTypePaiementDTO the achatTypePaiementDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatTypePaiementDTO, or with status 400 (Bad Request) if the achatTypePaiement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-type-paiements")
    @Timed
    public ResponseEntity<AchatTypePaiementDTO> createAchatTypePaiement(@RequestBody AchatTypePaiementDTO achatTypePaiementDTO) throws URISyntaxException {
        log.debug("REST request to save AchatTypePaiement : {}", achatTypePaiementDTO);
        if (achatTypePaiementDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatTypePaiement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatTypePaiementDTO result = achatTypePaiementService.save(achatTypePaiementDTO);
        return ResponseEntity.created(new URI("/api/achat-type-paiements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-type-paiements : Updates an existing achatTypePaiement.
     *
     * @param achatTypePaiementDTO the achatTypePaiementDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatTypePaiementDTO,
     * or with status 400 (Bad Request) if the achatTypePaiementDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatTypePaiementDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-type-paiements")
    @Timed
    public ResponseEntity<AchatTypePaiementDTO> updateAchatTypePaiement(@RequestBody AchatTypePaiementDTO achatTypePaiementDTO) throws URISyntaxException {
        log.debug("REST request to update AchatTypePaiement : {}", achatTypePaiementDTO);
        if (achatTypePaiementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatTypePaiementDTO result = achatTypePaiementService.save(achatTypePaiementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatTypePaiementDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-type-paiements : get all the achatTypePaiements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatTypePaiements in body
     */
    @GetMapping("/achat-type-paiements")
    @Timed
    public ResponseEntity<List<AchatTypePaiementDTO>> getAllAchatTypePaiements(Pageable pageable) {
        log.debug("REST request to get a page of AchatTypePaiements");
        Page<AchatTypePaiementDTO> page = achatTypePaiementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-type-paiements");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-type-paiements/:id : get the "id" achatTypePaiement.
     *
     * @param id the id of the achatTypePaiementDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatTypePaiementDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-type-paiements/{id}")
    @Timed
    public ResponseEntity<AchatTypePaiementDTO> getAchatTypePaiement(@PathVariable Long id) {
        log.debug("REST request to get AchatTypePaiement : {}", id);
        Optional<AchatTypePaiementDTO> achatTypePaiementDTO = achatTypePaiementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatTypePaiementDTO);
    }

    /**
     * DELETE  /achat-type-paiements/:id : delete the "id" achatTypePaiement.
     *
     * @param id the id of the achatTypePaiementDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-type-paiements/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatTypePaiement(@PathVariable Long id) {
        log.debug("REST request to delete AchatTypePaiement : {}", id);
        achatTypePaiementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-type-paiements?query=:query : search for the achatTypePaiement corresponding
     * to the query.
     *
     * @param query the query of the achatTypePaiement search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-type-paiements")
    @Timed
    public ResponseEntity<List<AchatTypePaiementDTO>> searchAchatTypePaiements(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatTypePaiements for query {}", query);
        Page<AchatTypePaiementDTO> page = achatTypePaiementService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-type-paiements");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
