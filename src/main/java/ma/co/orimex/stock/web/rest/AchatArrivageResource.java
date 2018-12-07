package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatArrivageService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatArrivageDTO;
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
 * REST controller for managing AchatArrivage.
 */
@RestController
@RequestMapping("/api")
public class AchatArrivageResource {

    private final Logger log = LoggerFactory.getLogger(AchatArrivageResource.class);

    private static final String ENTITY_NAME = "achatArrivage";

    private final AchatArrivageService achatArrivageService;

    public AchatArrivageResource(AchatArrivageService achatArrivageService) {
        this.achatArrivageService = achatArrivageService;
    }

    /**
     * POST  /achat-arrivages : Create a new achatArrivage.
     *
     * @param achatArrivageDTO the achatArrivageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatArrivageDTO, or with status 400 (Bad Request) if the achatArrivage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-arrivages")
    @Timed
    public ResponseEntity<AchatArrivageDTO> createAchatArrivage(@RequestBody AchatArrivageDTO achatArrivageDTO) throws URISyntaxException {
        log.debug("REST request to save AchatArrivage : {}", achatArrivageDTO);
        if (achatArrivageDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatArrivage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatArrivageDTO result = achatArrivageService.save(achatArrivageDTO);
        return ResponseEntity.created(new URI("/api/achat-arrivages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-arrivages : Updates an existing achatArrivage.
     *
     * @param achatArrivageDTO the achatArrivageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatArrivageDTO,
     * or with status 400 (Bad Request) if the achatArrivageDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatArrivageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-arrivages")
    @Timed
    public ResponseEntity<AchatArrivageDTO> updateAchatArrivage(@RequestBody AchatArrivageDTO achatArrivageDTO) throws URISyntaxException {
        log.debug("REST request to update AchatArrivage : {}", achatArrivageDTO);
        if (achatArrivageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatArrivageDTO result = achatArrivageService.save(achatArrivageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatArrivageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-arrivages : get all the achatArrivages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatArrivages in body
     */
    @GetMapping("/achat-arrivages")
    @Timed
    public ResponseEntity<List<AchatArrivageDTO>> getAllAchatArrivages(Pageable pageable) {
        log.debug("REST request to get a page of AchatArrivages");
        Page<AchatArrivageDTO> page = achatArrivageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-arrivages");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-arrivages/:id : get the "id" achatArrivage.
     *
     * @param id the id of the achatArrivageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatArrivageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-arrivages/{id}")
    @Timed
    public ResponseEntity<AchatArrivageDTO> getAchatArrivage(@PathVariable Long id) {
        log.debug("REST request to get AchatArrivage : {}", id);
        Optional<AchatArrivageDTO> achatArrivageDTO = achatArrivageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatArrivageDTO);
    }

    /**
     * DELETE  /achat-arrivages/:id : delete the "id" achatArrivage.
     *
     * @param id the id of the achatArrivageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-arrivages/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatArrivage(@PathVariable Long id) {
        log.debug("REST request to delete AchatArrivage : {}", id);
        achatArrivageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-arrivages?query=:query : search for the achatArrivage corresponding
     * to the query.
     *
     * @param query the query of the achatArrivage search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-arrivages")
    @Timed
    public ResponseEntity<List<AchatArrivageDTO>> searchAchatArrivages(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatArrivages for query {}", query);
        Page<AchatArrivageDTO> page = achatArrivageService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-arrivages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
