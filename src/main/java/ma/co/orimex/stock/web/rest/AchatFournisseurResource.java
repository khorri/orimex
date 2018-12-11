package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AchatFournisseurService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AchatFournisseurDTO;
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
 * REST controller for managing AchatFournisseur.
 */
@RestController
@RequestMapping("/api")
public class AchatFournisseurResource {

    private final Logger log = LoggerFactory.getLogger(AchatFournisseurResource.class);

    private static final String ENTITY_NAME = "achatFournisseur";

    private final AchatFournisseurService achatFournisseurService;

    public AchatFournisseurResource(AchatFournisseurService achatFournisseurService) {
        this.achatFournisseurService = achatFournisseurService;
    }

    /**
     * POST  /achat-fournisseurs : Create a new achatFournisseur.
     *
     * @param achatFournisseurDTO the achatFournisseurDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achatFournisseurDTO, or with status 400 (Bad Request) if the achatFournisseur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achat-fournisseurs")
    @Timed
    public ResponseEntity<AchatFournisseurDTO> createAchatFournisseur(@RequestBody AchatFournisseurDTO achatFournisseurDTO) throws URISyntaxException {
        log.debug("REST request to save AchatFournisseur : {}", achatFournisseurDTO);
        if (achatFournisseurDTO.getId() != null) {
            throw new BadRequestAlertException("A new achatFournisseur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatFournisseurDTO result = achatFournisseurService.save(achatFournisseurDTO);
        return ResponseEntity.created(new URI("/api/achat-fournisseurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achat-fournisseurs : Updates an existing achatFournisseur.
     *
     * @param achatFournisseurDTO the achatFournisseurDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achatFournisseurDTO,
     * or with status 400 (Bad Request) if the achatFournisseurDTO is not valid,
     * or with status 500 (Internal Server Error) if the achatFournisseurDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achat-fournisseurs")
    @Timed
    public ResponseEntity<AchatFournisseurDTO> updateAchatFournisseur(@RequestBody AchatFournisseurDTO achatFournisseurDTO) throws URISyntaxException {
        log.debug("REST request to update AchatFournisseur : {}", achatFournisseurDTO);
        if (achatFournisseurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatFournisseurDTO result = achatFournisseurService.save(achatFournisseurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achatFournisseurDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achat-fournisseurs : get all the achatFournisseurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of achatFournisseurs in body
     */
    @GetMapping("/achat-fournisseurs")
    @Timed
    public ResponseEntity<List<AchatFournisseurDTO>> getAllAchatFournisseurs(Pageable pageable) {
        log.debug("REST request to get a page of AchatFournisseurs");
        Page<AchatFournisseurDTO> page = achatFournisseurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/achat-fournisseurs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /achat-fournisseurs/:id : get the "id" achatFournisseur.
     *
     * @param id the id of the achatFournisseurDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achatFournisseurDTO, or with status 404 (Not Found)
     */
    @GetMapping("/achat-fournisseurs/{id}")
    @Timed
    public ResponseEntity<AchatFournisseurDTO> getAchatFournisseur(@PathVariable Long id) {
        log.debug("REST request to get AchatFournisseur : {}", id);
        Optional<AchatFournisseurDTO> achatFournisseurDTO = achatFournisseurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatFournisseurDTO);
    }

    /**
     * DELETE  /achat-fournisseurs/:id : delete the "id" achatFournisseur.
     *
     * @param id the id of the achatFournisseurDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achat-fournisseurs/{id}")
    @Timed
    public ResponseEntity<Void> deleteAchatFournisseur(@PathVariable Long id) {
        log.debug("REST request to delete AchatFournisseur : {}", id);
        achatFournisseurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/achat-fournisseurs?query=:query : search for the achatFournisseur corresponding
     * to the query.
     *
     * @param query the query of the achatFournisseur search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/achat-fournisseurs")
    @Timed
    public ResponseEntity<List<AchatFournisseurDTO>> searchAchatFournisseurs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AchatFournisseurs for query {}", query);
        Page<AchatFournisseurDTO> page = achatFournisseurService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/achat-fournisseurs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
