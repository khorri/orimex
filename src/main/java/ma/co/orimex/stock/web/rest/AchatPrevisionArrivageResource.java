package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatPrevisionArrivageService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatPrevisionArrivageDTO;
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
 * REST controller for managing AchatPrevisionArrivage.
 */
@RestController
@RequestMapping("/api")
public class AchatPrevisionArrivageResource {

    private final Logger log = LoggerFactory.getLogger(AchatPrevisionArrivageResource.class);

    private static final String ENTITY_NAME = "achatPrevisionArrivage";

    private final AchatPrevisionArrivageService achatPrevisionArrivageService;

    public AchatPrevisionArrivageResource(AchatPrevisionArrivageService achatPrevisionArrivageService) {
        this.achatPrevisionArrivageService = achatPrevisionArrivageService;
    }

    /**
     * POST  /achat-prevision-arrivages : Create a new achatPrevisionArrivage.
     *
     * @param achatPrevisionArrivageDTO the achatPrevisionArrivageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatPrevisionArrivageDTO, or with status 400 (Bad Request) if the achatPrevisionArrivage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-prevision-arrivages")
    @Timed
    public ResponseEntity<AchatPrevisionArrivageDTO> createAchatPrevisionArrivage(@RequestBody AchatPrevisionArrivageDTO achatPrevisionArrivageDTO) throws URISyntaxException {
        log.debug("REST request to save AchatPrevisionArrivage : {}", achatPrevisionArrivageDTO);
        if (achatPrevisionArrivageDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatPrevisionArrivage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatPrevisionArrivageDTO result = achatPrevisionArrivageService.save(achatPrevisionArrivageDTO);
        return ResponseEntity.created(new URI("/api/achat-prevision-arrivages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-prevision-arrivages : Updates an existing achatPrevisionArrivage.
     *
     * @param achatPrevisionArrivageDTO the achatPrevisionArrivageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatPrevisionArrivageDTO,
     * or with status 400 (Bad Request) if the achatPrevisionArrivageDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatPrevisionArrivageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-prevision-arrivages")
    @Timed
    public ResponseEntity<AchatPrevisionArrivageDTO> updateAchatPrevisionArrivage(@RequestBody AchatPrevisionArrivageDTO achatPrevisionArrivageDTO) throws URISyntaxException {
        log.debug("REST request to update AchatPrevisionArrivage : {}", achatPrevisionArrivageDTO);
        if (achatPrevisionArrivageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatPrevisionArrivageDTO result = achatPrevisionArrivageService.save(achatPrevisionArrivageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatPrevisionArrivageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-prevision-arrivages : get all the achatPrevisionArrivages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatPrevisionArrivages in body
     */
    @GetMapping("/achat-prevision-arrivages")
    @Timed
    public ResponseEntity<List<AchatPrevisionArrivageDTO>> getAllAchatPrevisionArrivages(Pageable pageable) {
        log.debug("REST request to get a page of AchatPrevisionArrivages");
        Page<AchatPrevisionArrivageDTO> page = achatPrevisionArrivageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-prevision-arrivages");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-prevision-arrivages/:id : get the "id" achatPrevisionArrivage.
     *
     * @param id the id of the achatPrevisionArrivageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatPrevisionArrivageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-prevision-arrivages/{id}")
    @Timed
    public ResponseEntity<AchatPrevisionArrivageDTO> getAchatPrevisionArrivage(@PathVariable Long id) {
        log.debug("REST request to get AchatPrevisionArrivage : {}", id);
        Optional<AchatPrevisionArrivageDTO> achatPrevisionArrivageDTO = achatPrevisionArrivageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatPrevisionArrivageDTO);
    }

    /**
     * DELETE  /achat-prevision-arrivages/:id : delete the "id" achatPrevisionArrivage.
     *
     * @param id the id of the achatPrevisionArrivageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-prevision-arrivages/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatPrevisionArrivage(@PathVariable Long id) {
        log.debug("REST request to delete AchatPrevisionArrivage : {}", id);
        achatPrevisionArrivageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-prevision-arrivages?query=:query : search for the achatPrevisionArrivage corresponding
     * to the query.
     *
     * @param query the query of the achatPrevisionArrivage search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-prevision-arrivages")
    @Timed
    public ResponseEntity<List<AchatPrevisionArrivageDTO>> searchAchatPrevisionArrivages(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatPrevisionArrivages for query {}", query);
        Page<AchatPrevisionArrivageDTO> page = achatPrevisionArrivageService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-prevision-arrivages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
