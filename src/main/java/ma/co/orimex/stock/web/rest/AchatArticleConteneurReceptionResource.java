package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatArticleConteneurReceptionService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatArticleConteneurReceptionDTO;
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
 * REST controller for managing AchatArticleConteneurReception.
 */
@RestController
@RequestMapping("/api")
public class AchatArticleConteneurReceptionResource {

    private final Logger log = LoggerFactory.getLogger(AchatArticleConteneurReceptionResource.class);

    private static final String ENTITY_NAME = "achatArticleConteneurReception";

    private final AchatArticleConteneurReceptionService achatArticleConteneurReceptionService;

    public AchatArticleConteneurReceptionResource(AchatArticleConteneurReceptionService achatArticleConteneurReceptionService) {
        this.achatArticleConteneurReceptionService = achatArticleConteneurReceptionService;
    }

    /**
     * POST  /achat-article-conteneur-receptions : Create a new achatArticleConteneurReception.
     *
     * @param achatArticleConteneurReceptionDTO the achatArticleConteneurReceptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatArticleConteneurReceptionDTO, or with status 400 (Bad Request) if the achatArticleConteneurReception has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-article-conteneur-receptions")
    @Timed
    public ResponseEntity<AchatArticleConteneurReceptionDTO> createAchatArticleConteneurReception(@RequestBody AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO) throws URISyntaxException {
        log.debug("REST request to save AchatArticleConteneurReception : {}", achatArticleConteneurReceptionDTO);
        if (achatArticleConteneurReceptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatArticleConteneurReception cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatArticleConteneurReceptionDTO result = achatArticleConteneurReceptionService.save(achatArticleConteneurReceptionDTO);
        return ResponseEntity.created(new URI("/api/achat-article-conteneur-receptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-article-conteneur-receptions : Updates an existing achatArticleConteneurReception.
     *
     * @param achatArticleConteneurReceptionDTO the achatArticleConteneurReceptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatArticleConteneurReceptionDTO,
     * or with status 400 (Bad Request) if the achatArticleConteneurReceptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatArticleConteneurReceptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-article-conteneur-receptions")
    @Timed
    public ResponseEntity<AchatArticleConteneurReceptionDTO> updateAchatArticleConteneurReception(@RequestBody AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO) throws URISyntaxException {
        log.debug("REST request to update AchatArticleConteneurReception : {}", achatArticleConteneurReceptionDTO);
        if (achatArticleConteneurReceptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatArticleConteneurReceptionDTO result = achatArticleConteneurReceptionService.save(achatArticleConteneurReceptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatArticleConteneurReceptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-article-conteneur-receptions : get all the achatArticleConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatArticleConteneurReceptions in body
     */
    @GetMapping("/achat-article-conteneur-receptions")
    @Timed
    public ResponseEntity<List<AchatArticleConteneurReceptionDTO>> getAllAchatArticleConteneurReceptions(Pageable pageable) {
        log.debug("REST request to get a page of AchatArticleConteneurReceptions");
        Page<AchatArticleConteneurReceptionDTO> page = achatArticleConteneurReceptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-article-conteneur-receptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-article-conteneur-receptions/:id : get the "id" achatArticleConteneurReception.
     *
     * @param id the id of the achatArticleConteneurReceptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatArticleConteneurReceptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-article-conteneur-receptions/{id}")
    @Timed
    public ResponseEntity<AchatArticleConteneurReceptionDTO> getAchatArticleConteneurReception(@PathVariable Long id) {
        log.debug("REST request to get AchatArticleConteneurReception : {}", id);
        Optional<AchatArticleConteneurReceptionDTO> achatArticleConteneurReceptionDTO = achatArticleConteneurReceptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatArticleConteneurReceptionDTO);
    }

    /**
     * DELETE  /achat-article-conteneur-receptions/:id : delete the "id" achatArticleConteneurReception.
     *
     * @param id the id of the achatArticleConteneurReceptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-article-conteneur-receptions/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatArticleConteneurReception(@PathVariable Long id) {
        log.debug("REST request to delete AchatArticleConteneurReception : {}", id);
        achatArticleConteneurReceptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-article-conteneur-receptions?query=:query : search for the achatArticleConteneurReception corresponding
     * to the query.
     *
     * @param query the query of the achatArticleConteneurReception search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-article-conteneur-receptions")
    @Timed
    public ResponseEntity<List<AchatArticleConteneurReceptionDTO>> searchAchatArticleConteneurReceptions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatArticleConteneurReceptions for query {}", query);
        Page<AchatArticleConteneurReceptionDTO> page = achatArticleConteneurReceptionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-article-conteneur-receptions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
