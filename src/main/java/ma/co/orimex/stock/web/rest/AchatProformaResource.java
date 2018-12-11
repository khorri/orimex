package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatProformaService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatProformaDTO;
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
 * REST controller for managing AchatProforma.
 */
@RestController
@RequestMapping("/api")
public class AchatProformaResource {

    private final Logger log = LoggerFactory.getLogger(AchatProformaResource.class);

    private static final String ENTITY_NAME = "achatProforma";

    private final AchatProformaService achatProformaService;

    public AchatProformaResource(AchatProformaService achatProformaService) {
        this.achatProformaService = achatProformaService;
    }

    /**
     * POST  /achat-proformas : Create a new achatProforma.
     *
     * @param achatProformaDTO the achatProformaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatProformaDTO, or with status 400 (Bad Request) if the achatProforma has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-proformas")
    @Timed
    public ResponseEntity<AchatProformaDTO> createAchatProforma(@RequestBody AchatProformaDTO achatProformaDTO) throws URISyntaxException {
        log.debug("REST request to save AchatProforma : {}", achatProformaDTO);
        if (achatProformaDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatProforma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatProformaDTO result = achatProformaService.save(achatProformaDTO);
        return ResponseEntity.created(new URI("/api/achat-proformas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-proformas : Updates an existing achatProforma.
     *
     * @param achatProformaDTO the achatProformaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatProformaDTO,
     * or with status 400 (Bad Request) if the achatProformaDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatProformaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-proformas")
    @Timed
    public ResponseEntity<AchatProformaDTO> updateAchatProforma(@RequestBody AchatProformaDTO achatProformaDTO) throws URISyntaxException {
        log.debug("REST request to update AchatProforma : {}", achatProformaDTO);
        if (achatProformaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatProformaDTO result = achatProformaService.save(achatProformaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatProformaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-proformas : get all the achatProformas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatProformas in body
     */
    @GetMapping("/achat-proformas")
    @Timed
    public ResponseEntity<List<AchatProformaDTO>> getAllAchatProformas(Pageable pageable) {
        log.debug("REST request to get a page of AchatProformas");
        Page<AchatProformaDTO> page = achatProformaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-proformas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-proformas/:id : get the "id" achatProforma.
     *
     * @param id the id of the achatProformaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatProformaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-proformas/{id}")
    @Timed
    public ResponseEntity<AchatProformaDTO> getAchatProforma(@PathVariable Long id) {
        log.debug("REST request to get AchatProforma : {}", id);
        Optional<AchatProformaDTO> achatProformaDTO = achatProformaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatProformaDTO);
    }

    /**
     * DELETE  /achat-proformas/:id : delete the "id" achatProforma.
     *
     * @param id the id of the achatProformaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-proformas/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatProforma(@PathVariable Long id) {
        log.debug("REST request to delete AchatProforma : {}", id);
        achatProformaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-proformas?query=:query : search for the achatProforma corresponding
     * to the query.
     *
     * @param query the query of the achatProforma search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-proformas")
    @Timed
    public ResponseEntity<List<AchatProformaDTO>> searchAchatProformas(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatProformas for query {}", query);
        Page<AchatProformaDTO> page = achatProformaService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-proformas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
