package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.TypeBonService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.TypeBonDTO;
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
 * REST controller for managing TypeBon.
 */
@RestController
@RequestMapping("/api")
public class TypeBonResource {

    private final Logger log = LoggerFactory.getLogger(TypeBonResource.class);

    private static final String ENTITY_NAME = "typeBon";

    private final TypeBonService typeBonService;

    public TypeBonResource(TypeBonService typeBonService) {
        this.typeBonService = typeBonService;
    }

    /**
     * POST  /type-bons : Create a new typeBon.
     *
     * @param typeBonDTO the typeBonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeBonDTO, or with status 400 (Bad Request) if the typeBon has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-bons")
    @Timed
    public ResponseEntity<TypeBonDTO> createTypeBon(@RequestBody TypeBonDTO typeBonDTO) throws URISyntaxException {
        log.debug("REST request to save TypeBon : {}", typeBonDTO);
        if (typeBonDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeBon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeBonDTO result = typeBonService.save(typeBonDTO);
        return ResponseEntity.created(new URI("/api/type-bons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-bons : Updates an existing typeBon.
     *
     * @param typeBonDTO the typeBonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeBonDTO,
     * or with status 400 (Bad Request) if the typeBonDTO is not valid,
     * or with status 500 (Internal Server Error) if the typeBonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-bons")
    @Timed
    public ResponseEntity<TypeBonDTO> updateTypeBon(@RequestBody TypeBonDTO typeBonDTO) throws URISyntaxException {
        log.debug("REST request to update TypeBon : {}", typeBonDTO);
        if (typeBonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeBonDTO result = typeBonService.save(typeBonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeBonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-bons : get all the typeBons.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of typeBons in body
     */
    @GetMapping("/type-bons")
    @Timed
    public ResponseEntity<List<TypeBonDTO>> getAllTypeBons(Pageable pageable) {
        log.debug("REST request to get a page of TypeBons");
        Page<TypeBonDTO> page = typeBonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/type-bons");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /type-bons/:id : get the "id" typeBon.
     *
     * @param id the id of the typeBonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeBonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/type-bons/{id}")
    @Timed
    public ResponseEntity<TypeBonDTO> getTypeBon(@PathVariable Long id) {
        log.debug("REST request to get TypeBon : {}", id);
        Optional<TypeBonDTO> typeBonDTO = typeBonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeBonDTO);
    }

    /**
     * DELETE  /type-bons/:id : delete the "id" typeBon.
     *
     * @param id the id of the typeBonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-bons/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeBon(@PathVariable Long id) {
        log.debug("REST request to delete TypeBon : {}", id);
        typeBonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/type-bons?query=:query : search for the typeBon corresponding
     * to the query.
     *
     * @param query the query of the typeBon search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/type-bons")
    @Timed
    public ResponseEntity<List<TypeBonDTO>> searchTypeBons(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TypeBons for query {}", query);
        Page<TypeBonDTO> page = typeBonService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/type-bons");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
