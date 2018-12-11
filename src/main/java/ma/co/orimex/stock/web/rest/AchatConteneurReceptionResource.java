package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatConteneurReceptionService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatConteneurReceptionDTO;
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
 * REST controller for managing AchatConteneurReception.
 */
@RestController
@RequestMapping("/api")
public class AchatConteneurReceptionResource {

    private final Logger log = LoggerFactory.getLogger(AchatConteneurReceptionResource.class);

    private static final String ENTITY_NAME = "achatConteneurReception";

    private final AchatConteneurReceptionService achatConteneurReceptionService;

    public AchatConteneurReceptionResource(AchatConteneurReceptionService achatConteneurReceptionService) {
        this.achatConteneurReceptionService = achatConteneurReceptionService;
    }

    /**
     * POST  /achat-conteneur-receptions : Create a new achatConteneurReception.
     *
     * @param achatConteneurReceptionDTO the achatConteneurReceptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatConteneurReceptionDTO, or with status 400 (Bad Request) if the achatConteneurReception has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-conteneur-receptions")
    @Timed
    public ResponseEntity<AchatConteneurReceptionDTO> createAchatConteneurReception(@RequestBody AchatConteneurReceptionDTO achatConteneurReceptionDTO) throws URISyntaxException {
        log.debug("REST request to save AchatConteneurReception : {}", achatConteneurReceptionDTO);
        if (achatConteneurReceptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatConteneurReception cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatConteneurReceptionDTO result = achatConteneurReceptionService.save(achatConteneurReceptionDTO);
        return ResponseEntity.created(new URI("/api/achat-conteneur-receptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-conteneur-receptions : Updates an existing achatConteneurReception.
     *
     * @param achatConteneurReceptionDTO the achatConteneurReceptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatConteneurReceptionDTO,
     * or with status 400 (Bad Request) if the achatConteneurReceptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatConteneurReceptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-conteneur-receptions")
    @Timed
    public ResponseEntity<AchatConteneurReceptionDTO> updateAchatConteneurReception(@RequestBody AchatConteneurReceptionDTO achatConteneurReceptionDTO) throws URISyntaxException {
        log.debug("REST request to update AchatConteneurReception : {}", achatConteneurReceptionDTO);
        if (achatConteneurReceptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatConteneurReceptionDTO result = achatConteneurReceptionService.save(achatConteneurReceptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatConteneurReceptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-conteneur-receptions : get all the achatConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatConteneurReceptions in body
     */
    @GetMapping("/achat-conteneur-receptions")
    @Timed
    public ResponseEntity<List<AchatConteneurReceptionDTO>> getAllAchatConteneurReceptions(Pageable pageable) {
        log.debug("REST request to get a page of AchatConteneurReceptions");
        Page<AchatConteneurReceptionDTO> page = achatConteneurReceptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-conteneur-receptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-conteneur-receptions/:id : get the "id" achatConteneurReception.
     *
     * @param id the id of the achatConteneurReceptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatConteneurReceptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-conteneur-receptions/{id}")
    @Timed
    public ResponseEntity<AchatConteneurReceptionDTO> getAchatConteneurReception(@PathVariable Long id) {
        log.debug("REST request to get AchatConteneurReception : {}", id);
        Optional<AchatConteneurReceptionDTO> achatConteneurReceptionDTO = achatConteneurReceptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatConteneurReceptionDTO);
    }

    /**
     * DELETE  /achat-conteneur-receptions/:id : delete the "id" achatConteneurReception.
     *
     * @param id the id of the achatConteneurReceptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-conteneur-receptions/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatConteneurReception(@PathVariable Long id) {
        log.debug("REST request to delete AchatConteneurReception : {}", id);
        achatConteneurReceptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-conteneur-receptions?query=:query : search for the achatConteneurReception corresponding
     * to the query.
     *
     * @param query the query of the achatConteneurReception search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-conteneur-receptions")
    @Timed
    public ResponseEntity<List<AchatConteneurReceptionDTO>> searchAchatConteneurReceptions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatConteneurReceptions for query {}", query);
        Page<AchatConteneurReceptionDTO> page = achatConteneurReceptionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-conteneur-receptions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
