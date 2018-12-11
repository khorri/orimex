package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.AuthorityService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.AuthorityDTO;
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
 * REST controller for managing Authority.
 */
@RestController
@RequestMapping("/api")
public class AuthorityResource {

    private final Logger log = LoggerFactory.getLogger(AuthorityResource.class);

    private static final String ENTITY_NAME = "authority";

    private final AuthorityService authorityService;

    public AuthorityResource(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * POST  /authorities : Create a new authority.
     *
     * @param authorityDTO the authorityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new authorityDTO, or with status 400 (Bad Request) if the authority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/authorities")
    @Timed
    public ResponseEntity<AuthorityDTO> createAuthority(@RequestBody AuthorityDTO authorityDTO) throws URISyntaxException {
        log.debug("REST request to save Authority : {}", authorityDTO);
        if (authorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new authority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthorityDTO result = authorityService.save(authorityDTO);
        return ResponseEntity.created(new URI("/api/authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /authorities : Updates an existing authority.
     *
     * @param authorityDTO the authorityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated authorityDTO,
     * or with status 400 (Bad Request) if the authorityDTO is not valid,
     * or with status 500 (Internal Server Error) if the authorityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/authorities")
    @Timed
    public ResponseEntity<AuthorityDTO> updateAuthority(@RequestBody AuthorityDTO authorityDTO) throws URISyntaxException {
        log.debug("REST request to update Authority : {}", authorityDTO);
        if (authorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuthorityDTO result = authorityService.save(authorityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, authorityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /authorities : get all the authorities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of authorities in body
     */
    @GetMapping("/authorities")
    @Timed
    public ResponseEntity<List<AuthorityDTO>> getAllAuthorities(Pageable pageable) {
        log.debug("REST request to get a page of Authorities");
        Page<AuthorityDTO> page = authorityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/authorities");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /authorities/:id : get the "id" authority.
     *
     * @param id the id of the authorityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the authorityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/authorities/{id}")
    @Timed
    public ResponseEntity<AuthorityDTO> getAuthority(@PathVariable Long id) {
        log.debug("REST request to get Authority : {}", id);
        Optional<AuthorityDTO> authorityDTO = authorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(authorityDTO);
    }

    /**
     * DELETE  /authorities/:id : delete the "id" authority.
     *
     * @param id the id of the authorityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/authorities/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuthority(@PathVariable Long id) {
        log.debug("REST request to delete Authority : {}", id);
        authorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/authorities?query=:query : search for the authority corresponding
     * to the query.
     *
     * @param query the query of the authority search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/authorities")
    @Timed
    public ResponseEntity<List<AuthorityDTO>> searchAuthorities(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Authorities for query {}", query);
        Page<AuthorityDTO> page = authorityService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/authorities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
