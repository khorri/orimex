package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatArticleConteneurArrivageService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatArticleConteneurArrivageDTO;
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
 * REST controller for managing AchatArticleConteneurArrivage.
 */
@RestController
@RequestMapping("/api")
public class AchatArticleConteneurArrivageResource {

    private final Logger log = LoggerFactory.getLogger(AchatArticleConteneurArrivageResource.class);

    private static final String ENTITY_NAME = "achatArticleConteneurArrivage";

    private final AchatArticleConteneurArrivageService achatArticleConteneurArrivageService;

    public AchatArticleConteneurArrivageResource(AchatArticleConteneurArrivageService achatArticleConteneurArrivageService) {
        this.achatArticleConteneurArrivageService = achatArticleConteneurArrivageService;
    }

    /**
     * POST  /achat-article-conteneur-arrivages : Create a new achatArticleConteneurArrivage.
     *
     * @param achatArticleConteneurArrivageDTO the achatArticleConteneurArrivageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatArticleConteneurArrivageDTO, or with status 400 (Bad Request) if the achatArticleConteneurArrivage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-article-conteneur-arrivages")
    @Timed
    public ResponseEntity<AchatArticleConteneurArrivageDTO> createAchatArticleConteneurArrivage(@RequestBody AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO) throws URISyntaxException {
        log.debug("REST request to save AchatArticleConteneurArrivage : {}", achatArticleConteneurArrivageDTO);
        if (achatArticleConteneurArrivageDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatArticleConteneurArrivage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatArticleConteneurArrivageDTO result = achatArticleConteneurArrivageService.save(achatArticleConteneurArrivageDTO);
        return ResponseEntity.created(new URI("/api/achat-article-conteneur-arrivages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-article-conteneur-arrivages : Updates an existing achatArticleConteneurArrivage.
     *
     * @param achatArticleConteneurArrivageDTO the achatArticleConteneurArrivageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatArticleConteneurArrivageDTO,
     * or with status 400 (Bad Request) if the achatArticleConteneurArrivageDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatArticleConteneurArrivageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-article-conteneur-arrivages")
    @Timed
    public ResponseEntity<AchatArticleConteneurArrivageDTO> updateAchatArticleConteneurArrivage(@RequestBody AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO) throws URISyntaxException {
        log.debug("REST request to update AchatArticleConteneurArrivage : {}", achatArticleConteneurArrivageDTO);
        if (achatArticleConteneurArrivageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatArticleConteneurArrivageDTO result = achatArticleConteneurArrivageService.save(achatArticleConteneurArrivageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatArticleConteneurArrivageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-article-conteneur-arrivages : get all the achatArticleConteneurArrivages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatArticleConteneurArrivages in body
     */
    @GetMapping("/achat-article-conteneur-arrivages")
    @Timed
    public ResponseEntity<List<AchatArticleConteneurArrivageDTO>> getAllAchatArticleConteneurArrivages(Pageable pageable) {
        log.debug("REST request to get a page of AchatArticleConteneurArrivages");
        Page<AchatArticleConteneurArrivageDTO> page = achatArticleConteneurArrivageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-article-conteneur-arrivages");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-article-conteneur-arrivages/:id : get the "id" achatArticleConteneurArrivage.
     *
     * @param id the id of the achatArticleConteneurArrivageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatArticleConteneurArrivageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-article-conteneur-arrivages/{id}")
    @Timed
    public ResponseEntity<AchatArticleConteneurArrivageDTO> getAchatArticleConteneurArrivage(@PathVariable Long id) {
        log.debug("REST request to get AchatArticleConteneurArrivage : {}", id);
        Optional<AchatArticleConteneurArrivageDTO> achatArticleConteneurArrivageDTO = achatArticleConteneurArrivageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatArticleConteneurArrivageDTO);
    }

    /**
     * DELETE  /achat-article-conteneur-arrivages/:id : delete the "id" achatArticleConteneurArrivage.
     *
     * @param id the id of the achatArticleConteneurArrivageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-article-conteneur-arrivages/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatArticleConteneurArrivage(@PathVariable Long id) {
        log.debug("REST request to delete AchatArticleConteneurArrivage : {}", id);
        achatArticleConteneurArrivageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-article-conteneur-arrivages?query=:query : search for the achatArticleConteneurArrivage corresponding
     * to the query.
     *
     * @param query the query of the achatArticleConteneurArrivage search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-article-conteneur-arrivages")
    @Timed
    public ResponseEntity<List<AchatArticleConteneurArrivageDTO>> searchAchatArticleConteneurArrivages(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatArticleConteneurArrivages for query {}", query);
        Page<AchatArticleConteneurArrivageDTO> page = achatArticleConteneurArrivageService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-article-conteneur-arrivages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
