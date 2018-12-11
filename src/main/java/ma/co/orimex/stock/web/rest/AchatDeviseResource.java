package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatDeviseService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatDeviseDTO;
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
 * REST controller for managing AchatDevise.
 */
@RestController
@RequestMapping("/api")
public class AchatDeviseResource {

    private final Logger log = LoggerFactory.getLogger(AchatDeviseResource.class);

    private static final String ENTITY_NAME = "achatDevise";

    private final AchatDeviseService achatDeviseService;

    public AchatDeviseResource(AchatDeviseService achatDeviseService) {
        this.achatDeviseService = achatDeviseService;
    }

    /**
     * POST  /achat-devises : Create a new achatDevise.
     *
     * @param achatDeviseDTO the achatDeviseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatDeviseDTO, or with status 400 (Bad Request) if the achatDevise has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-devises")
    @Timed
    public ResponseEntity<AchatDeviseDTO> createAchatDevise(@RequestBody AchatDeviseDTO achatDeviseDTO) throws URISyntaxException {
        log.debug("REST request to save AchatDevise : {}", achatDeviseDTO);
        if (achatDeviseDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatDevise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatDeviseDTO result = achatDeviseService.save(achatDeviseDTO);
        return ResponseEntity.created(new URI("/api/achat-devises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-devises : Updates an existing achatDevise.
     *
     * @param achatDeviseDTO the achatDeviseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatDeviseDTO,
     * or with status 400 (Bad Request) if the achatDeviseDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatDeviseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-devises")
    @Timed
    public ResponseEntity<AchatDeviseDTO> updateAchatDevise(@RequestBody AchatDeviseDTO achatDeviseDTO) throws URISyntaxException {
        log.debug("REST request to update AchatDevise : {}", achatDeviseDTO);
        if (achatDeviseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatDeviseDTO result = achatDeviseService.save(achatDeviseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatDeviseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-devises : get all the achatDevises.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatDevises in body
     */
    @GetMapping("/achat-devises")
    @Timed
    public ResponseEntity<List<AchatDeviseDTO>> getAllAchatDevises(Pageable pageable) {
        log.debug("REST request to get a page of AchatDevises");
        Page<AchatDeviseDTO> page = achatDeviseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-devises");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-devises/:id : get the "id" achatDevise.
     *
     * @param id the id of the achatDeviseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatDeviseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-devises/{id}")
    @Timed
    public ResponseEntity<AchatDeviseDTO> getAchatDevise(@PathVariable Long id) {
        log.debug("REST request to get AchatDevise : {}", id);
        Optional<AchatDeviseDTO> achatDeviseDTO = achatDeviseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatDeviseDTO);
    }

    /**
     * DELETE  /achat-devises/:id : delete the "id" achatDevise.
     *
     * @param id the id of the achatDeviseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-devises/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatDevise(@PathVariable Long id) {
        log.debug("REST request to delete AchatDevise : {}", id);
        achatDeviseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-devises?query=:query : search for the achatDevise corresponding
     * to the query.
     *
     * @param query the query of the achatDevise search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-devises")
    @Timed
    public ResponseEntity<List<AchatDeviseDTO>> searchAchatDevises(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatDevises for query {}", query);
        Page<AchatDeviseDTO> page = achatDeviseService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-devises");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
