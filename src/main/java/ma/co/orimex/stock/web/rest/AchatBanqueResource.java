package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatBanqueService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatBanqueDTO;
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
 * REST controller for managing AchatBanque.
 */
@RestController
@RequestMapping("/api")
public class AchatBanqueResource {

    private final Logger log = LoggerFactory.getLogger(AchatBanqueResource.class);

    private static final String ENTITY_NAME = "achatBanque";

    private final AchatBanqueService achatBanqueService;

    public AchatBanqueResource(AchatBanqueService achatBanqueService) {
        this.achatBanqueService = achatBanqueService;
    }

    /**
     * POST  /achat-banques : Create a new achatBanque.
     *
     * @param achatBanqueDTO the achatBanqueDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatBanqueDTO, or with status 400 (Bad Request) if the achatBanque has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-banques")
    @Timed
    public ResponseEntity<AchatBanqueDTO> createAchatBanque(@RequestBody AchatBanqueDTO achatBanqueDTO) throws URISyntaxException {
        log.debug("REST request to save AchatBanque : {}", achatBanqueDTO);
        if (achatBanqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatBanque cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatBanqueDTO result = achatBanqueService.save(achatBanqueDTO);
        return ResponseEntity.created(new URI("/api/achat-banques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-banques : Updates an existing achatBanque.
     *
     * @param achatBanqueDTO the achatBanqueDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatBanqueDTO,
     * or with status 400 (Bad Request) if the achatBanqueDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatBanqueDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-banques")
    @Timed
    public ResponseEntity<AchatBanqueDTO> updateAchatBanque(@RequestBody AchatBanqueDTO achatBanqueDTO) throws URISyntaxException {
        log.debug("REST request to update AchatBanque : {}", achatBanqueDTO);
        if (achatBanqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatBanqueDTO result = achatBanqueService.save(achatBanqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatBanqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-banques : get all the achatBanques.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatBanques in body
     */
    @GetMapping("/achat-banques")
    @Timed
    public ResponseEntity<List<AchatBanqueDTO>> getAllAchatBanques(Pageable pageable) {
        log.debug("REST request to get a page of AchatBanques");
        Page<AchatBanqueDTO> page = achatBanqueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-banques");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-banques/:id : get the "id" achatBanque.
     *
     * @param id the id of the achatBanqueDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatBanqueDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-banques/{id}")
    @Timed
    public ResponseEntity<AchatBanqueDTO> getAchatBanque(@PathVariable Long id) {
        log.debug("REST request to get AchatBanque : {}", id);
        Optional<AchatBanqueDTO> achatBanqueDTO = achatBanqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatBanqueDTO);
    }

    /**
     * DELETE  /achat-banques/:id : delete the "id" achatBanque.
     *
     * @param id the id of the achatBanqueDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-banques/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatBanque(@PathVariable Long id) {
        log.debug("REST request to delete AchatBanque : {}", id);
        achatBanqueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-banques?query=:query : search for the achatBanque corresponding
     * to the query.
     *
     * @param query the query of the achatBanque search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-banques")
    @Timed
    public ResponseEntity<List<AchatBanqueDTO>> searchAchatBanques(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatBanques for query {}", query);
        Page<AchatBanqueDTO> page = achatBanqueService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-banques");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
