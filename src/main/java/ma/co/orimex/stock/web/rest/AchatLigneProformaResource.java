package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatLigneProformaService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatLigneProformaDTO;
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
 * REST controller for managing AchatLigneProforma.
 */
@RestController
@RequestMapping("/api")
public class AchatLigneProformaResource {

    private final Logger log = LoggerFactory.getLogger(AchatLigneProformaResource.class);

    private static final String ENTITY_NAME = "achatLigneProforma";

    private final AchatLigneProformaService achatLigneProformaService;

    public AchatLigneProformaResource(AchatLigneProformaService achatLigneProformaService) {
        this.achatLigneProformaService = achatLigneProformaService;
    }

    /**
     * POST  /achat-ligne-proformas : Create a new achatLigneProforma.
     *
     * @param achatLigneProformaDTO the achatLigneProformaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatLigneProformaDTO, or with status 400 (Bad Request) if the achatLigneProforma has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-ligne-proformas")
    @Timed
    public ResponseEntity<AchatLigneProformaDTO> createAchatLigneProforma(@RequestBody AchatLigneProformaDTO achatLigneProformaDTO) throws URISyntaxException {
        log.debug("REST request to save AchatLigneProforma : {}", achatLigneProformaDTO);
        if (achatLigneProformaDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatLigneProforma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatLigneProformaDTO result = achatLigneProformaService.save(achatLigneProformaDTO);
        return ResponseEntity.created(new URI("/api/achat-ligne-proformas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-ligne-proformas : Updates an existing achatLigneProforma.
     *
     * @param achatLigneProformaDTO the achatLigneProformaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatLigneProformaDTO,
     * or with status 400 (Bad Request) if the achatLigneProformaDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatLigneProformaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-ligne-proformas")
    @Timed
    public ResponseEntity<AchatLigneProformaDTO> updateAchatLigneProforma(@RequestBody AchatLigneProformaDTO achatLigneProformaDTO) throws URISyntaxException {
        log.debug("REST request to update AchatLigneProforma : {}", achatLigneProformaDTO);
        if (achatLigneProformaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatLigneProformaDTO result = achatLigneProformaService.save(achatLigneProformaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatLigneProformaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-ligne-proformas : get all the achatLigneProformas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatLigneProformas in body
     */
    @GetMapping("/achat-ligne-proformas")
    @Timed
    public ResponseEntity<List<AchatLigneProformaDTO>> getAllAchatLigneProformas(Pageable pageable) {
        log.debug("REST request to get a page of AchatLigneProformas");
        Page<AchatLigneProformaDTO> page = achatLigneProformaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-ligne-proformas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-ligne-proformas/:id : get the "id" achatLigneProforma.
     *
     * @param id the id of the achatLigneProformaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatLigneProformaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-ligne-proformas/{id}")
    @Timed
    public ResponseEntity<AchatLigneProformaDTO> getAchatLigneProforma(@PathVariable Long id) {
        log.debug("REST request to get AchatLigneProforma : {}", id);
        Optional<AchatLigneProformaDTO> achatLigneProformaDTO = achatLigneProformaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatLigneProformaDTO);
    }

    /**
     * DELETE  /achat-ligne-proformas/:id : delete the "id" achatLigneProforma.
     *
     * @param id the id of the achatLigneProformaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-ligne-proformas/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatLigneProforma(@PathVariable Long id) {
        log.debug("REST request to delete AchatLigneProforma : {}", id);
        achatLigneProformaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-ligne-proformas?query=:query : search for the achatLigneProforma corresponding
     * to the query.
     *
     * @param query the query of the achatLigneProforma search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-ligne-proformas")
    @Timed
    public ResponseEntity<List<AchatLigneProformaDTO>> searchAchatLigneProformas(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatLigneProformas for query {}", query);
        Page<AchatLigneProformaDTO> page = achatLigneProformaService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-ligne-proformas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
