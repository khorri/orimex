package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.CamionService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.CamionDTO;
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
 * REST controller for managing Camion.
 */
@RestController
@RequestMapping("/api")
public class CamionResource {

    private final Logger log = LoggerFactory.getLogger(CamionResource.class);

    private static final String ENTITY_NAME = "camion";

    private final CamionService camionService;

    public CamionResource(CamionService camionService) {
        this.camionService = camionService;
    }

    /**
     * POST  /camions : Create a new camion.
     *
     * @param camionDTO the camionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new camionDTO, or with status 400 (Bad Request) if the camion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/camions")
    @Timed
    public ResponseEntity<CamionDTO> createCamion(@RequestBody CamionDTO camionDTO) throws URISyntaxException {
        log.debug("REST request to save Camion : {}", camionDTO);
        if (camionDTO.getId() != null) {
            throw new BadRequestAlertException("A new camion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CamionDTO result = camionService.save(camionDTO);
        return ResponseEntity.created(new URI("/api/camions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /camions : Updates an existing camion.
     *
     * @param camionDTO the camionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated camionDTO,
     * or with status 400 (Bad Request) if the camionDTO is not valid,
     * or with status 500 (Internal Server Error) if the camionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/camions")
    @Timed
    public ResponseEntity<CamionDTO> updateCamion(@RequestBody CamionDTO camionDTO) throws URISyntaxException {
        log.debug("REST request to update Camion : {}", camionDTO);
        if (camionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CamionDTO result = camionService.save(camionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, camionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /camions : get all the camions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of camions in body
     */
    @GetMapping("/camions")
    @Timed
    public ResponseEntity<List<CamionDTO>> getAllCamions(Pageable pageable) {
        log.debug("REST request to get a page of Camions");
        Page<CamionDTO> page = camionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/camions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /camions/:id : get the "id" camion.
     *
     * @param id the id of the camionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the camionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/camions/{id}")
    @Timed
    public ResponseEntity<CamionDTO> getCamion(@PathVariable Long id) {
        log.debug("REST request to get Camion : {}", id);
        Optional<CamionDTO> camionDTO = camionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(camionDTO);
    }

    /**
     * DELETE  /camions/:id : delete the "id" camion.
     *
     * @param id the id of the camionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/camions/{id}")
    @Timed
    public ResponseEntity<Void> deleteCamion(@PathVariable Long id) {
        log.debug("REST request to delete Camion : {}", id);
        camionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/camions?query=:query : search for the camion corresponding
     * to the query.
     *
     * @param query the query of the camion search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/camions")
    @Timed
    public ResponseEntity<List<CamionDTO>> searchCamions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Camions for query {}", query);
        Page<CamionDTO> page = camionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/camions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
