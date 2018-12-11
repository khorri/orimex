package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatArticleLigneProformaService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatArticleLigneProformaDTO;
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
 * REST controller for managing AchatArticleLigneProforma.
 */
@RestController
@RequestMapping("/api")
public class AchatArticleLigneProformaResource {

    private final Logger log = LoggerFactory.getLogger(AchatArticleLigneProformaResource.class);

    private static final String ENTITY_NAME = "achatArticleLigneProforma";

    private final AchatArticleLigneProformaService achatArticleLigneProformaService;

    public AchatArticleLigneProformaResource(AchatArticleLigneProformaService achatArticleLigneProformaService) {
        this.achatArticleLigneProformaService = achatArticleLigneProformaService;
    }

    /**
     * POST  /achat-article-ligne-proformas : Create a new achatArticleLigneProforma.
     *
     * @param achatArticleLigneProformaDTO the achatArticleLigneProformaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatArticleLigneProformaDTO, or with status 400 (Bad Request) if the achatArticleLigneProforma has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-article-ligne-proformas")
    @Timed
    public ResponseEntity<AchatArticleLigneProformaDTO> createAchatArticleLigneProforma(@RequestBody AchatArticleLigneProformaDTO achatArticleLigneProformaDTO) throws URISyntaxException {
        log.debug("REST request to save AchatArticleLigneProforma : {}", achatArticleLigneProformaDTO);
        if (achatArticleLigneProformaDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatArticleLigneProforma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatArticleLigneProformaDTO result = achatArticleLigneProformaService.save(achatArticleLigneProformaDTO);
        return ResponseEntity.created(new URI("/api/achat-article-ligne-proformas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-article-ligne-proformas : Updates an existing achatArticleLigneProforma.
     *
     * @param achatArticleLigneProformaDTO the achatArticleLigneProformaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatArticleLigneProformaDTO,
     * or with status 400 (Bad Request) if the achatArticleLigneProformaDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatArticleLigneProformaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-article-ligne-proformas")
    @Timed
    public ResponseEntity<AchatArticleLigneProformaDTO> updateAchatArticleLigneProforma(@RequestBody AchatArticleLigneProformaDTO achatArticleLigneProformaDTO) throws URISyntaxException {
        log.debug("REST request to update AchatArticleLigneProforma : {}", achatArticleLigneProformaDTO);
        if (achatArticleLigneProformaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatArticleLigneProformaDTO result = achatArticleLigneProformaService.save(achatArticleLigneProformaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatArticleLigneProformaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-article-ligne-proformas : get all the achatArticleLigneProformas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatArticleLigneProformas in body
     */
    @GetMapping("/achat-article-ligne-proformas")
    @Timed
    public ResponseEntity<List<AchatArticleLigneProformaDTO>> getAllAchatArticleLigneProformas(Pageable pageable) {
        log.debug("REST request to get a page of AchatArticleLigneProformas");
        Page<AchatArticleLigneProformaDTO> page = achatArticleLigneProformaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-article-ligne-proformas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-article-ligne-proformas/:id : get the "id" achatArticleLigneProforma.
     *
     * @param id the id of the achatArticleLigneProformaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatArticleLigneProformaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-article-ligne-proformas/{id}")
    @Timed
    public ResponseEntity<AchatArticleLigneProformaDTO> getAchatArticleLigneProforma(@PathVariable Long id) {
        log.debug("REST request to get AchatArticleLigneProforma : {}", id);
        Optional<AchatArticleLigneProformaDTO> achatArticleLigneProformaDTO = achatArticleLigneProformaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatArticleLigneProformaDTO);
    }

    /**
     * DELETE  /achat-article-ligne-proformas/:id : delete the "id" achatArticleLigneProforma.
     *
     * @param id the id of the achatArticleLigneProformaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-article-ligne-proformas/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatArticleLigneProforma(@PathVariable Long id) {
        log.debug("REST request to delete AchatArticleLigneProforma : {}", id);
        achatArticleLigneProformaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-article-ligne-proformas?query=:query : search for the achatArticleLigneProforma corresponding
     * to the query.
     *
     * @param query the query of the achatArticleLigneProforma search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-article-ligne-proformas")
    @Timed
    public ResponseEntity<List<AchatArticleLigneProformaDTO>> searchAchatArticleLigneProformas(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatArticleLigneProformas for query {}", query);
        Page<AchatArticleLigneProformaDTO> page = achatArticleLigneProformaService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-article-ligne-proformas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
