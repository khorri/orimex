package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatConteneurArrivageService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatConteneurArrivageDTO;
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
 * REST controller for managing AchatConteneurArrivage.
 */
@RestController
@RequestMapping("/api")
public class AchatConteneurArrivageResource {

    private final Logger log = LoggerFactory.getLogger(AchatConteneurArrivageResource.class);

    private static final String ENTITY_NAME = "achatConteneurArrivage";

    private final AchatConteneurArrivageService achatConteneurArrivageService;

    public AchatConteneurArrivageResource(AchatConteneurArrivageService achatConteneurArrivageService) {
        this.achatConteneurArrivageService = achatConteneurArrivageService;
    }

    /**
     * POST  /achat-conteneur-arrivages : Create a new achatConteneurArrivage.
     *
     * @param achatConteneurArrivageDTO the achatConteneurArrivageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatConteneurArrivageDTO, or with status 400 (Bad Request) if the achatConteneurArrivage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-conteneur-arrivages")
    @Timed
    public ResponseEntity<AchatConteneurArrivageDTO> createAchatConteneurArrivage(@RequestBody AchatConteneurArrivageDTO achatConteneurArrivageDTO) throws URISyntaxException {
        log.debug("REST request to save AchatConteneurArrivage : {}", achatConteneurArrivageDTO);
        if (achatConteneurArrivageDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatConteneurArrivage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatConteneurArrivageDTO result = achatConteneurArrivageService.save(achatConteneurArrivageDTO);
        return ResponseEntity.created(new URI("/api/achat-conteneur-arrivages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-conteneur-arrivages : Updates an existing achatConteneurArrivage.
     *
     * @param achatConteneurArrivageDTO the achatConteneurArrivageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatConteneurArrivageDTO,
     * or with status 400 (Bad Request) if the achatConteneurArrivageDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatConteneurArrivageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-conteneur-arrivages")
    @Timed
    public ResponseEntity<AchatConteneurArrivageDTO> updateAchatConteneurArrivage(@RequestBody AchatConteneurArrivageDTO achatConteneurArrivageDTO) throws URISyntaxException {
        log.debug("REST request to update AchatConteneurArrivage : {}", achatConteneurArrivageDTO);
        if (achatConteneurArrivageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatConteneurArrivageDTO result = achatConteneurArrivageService.save(achatConteneurArrivageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatConteneurArrivageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-conteneur-arrivages : get all the achatConteneurArrivages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatConteneurArrivages in body
     */
    @GetMapping("/achat-conteneur-arrivages")
    @Timed
    public ResponseEntity<List<AchatConteneurArrivageDTO>> getAllAchatConteneurArrivages(Pageable pageable) {
        log.debug("REST request to get a page of AchatConteneurArrivages");
        Page<AchatConteneurArrivageDTO> page = achatConteneurArrivageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-conteneur-arrivages");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-conteneur-arrivages/:id : get the "id" achatConteneurArrivage.
     *
     * @param id the id of the achatConteneurArrivageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatConteneurArrivageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-conteneur-arrivages/{id}")
    @Timed
    public ResponseEntity<AchatConteneurArrivageDTO> getAchatConteneurArrivage(@PathVariable Long id) {
        log.debug("REST request to get AchatConteneurArrivage : {}", id);
        Optional<AchatConteneurArrivageDTO> achatConteneurArrivageDTO = achatConteneurArrivageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatConteneurArrivageDTO);
    }

    /**
     * DELETE  /achat-conteneur-arrivages/:id : delete the "id" achatConteneurArrivage.
     *
     * @param id the id of the achatConteneurArrivageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-conteneur-arrivages/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatConteneurArrivage(@PathVariable Long id) {
        log.debug("REST request to delete AchatConteneurArrivage : {}", id);
        achatConteneurArrivageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-conteneur-arrivages?query=:query : search for the achatConteneurArrivage corresponding
     * to the query.
     *
     * @param query the query of the achatConteneurArrivage search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-conteneur-arrivages")
    @Timed
    public ResponseEntity<List<AchatConteneurArrivageDTO>> searchAchatConteneurArrivages(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatConteneurArrivages for query {}", query);
        Page<AchatConteneurArrivageDTO> page = achatConteneurArrivageService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-conteneur-arrivages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
