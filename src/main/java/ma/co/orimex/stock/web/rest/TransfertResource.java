package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.TransfertService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.TransfertDTO;
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
 * REST controller for managing Transfert.
 */
@RestController
@RequestMapping("/api")
public class TransfertResource {

    private final Logger log = LoggerFactory.getLogger(TransfertResource.class);

    private static final String ENTITY_NAME = "transfert";

    private final TransfertService transfertService;

    public TransfertResource(TransfertService transfertService) {
        this.transfertService = transfertService;
    }

    /**
     * POST  /transferts : Create a new transfert.
     *
     * @param transfertDTO the transfertDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transfertDTO, or with status 400 (Bad Request) if the transfert has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transferts")
    @Timed
    public ResponseEntity<TransfertDTO> createTransfert(@RequestBody TransfertDTO transfertDTO) throws URISyntaxException {
        log.debug("REST request to save Transfert : {}", transfertDTO);
        if (transfertDTO.getId() != null) {
            throw new BadRequestAlertException("A new transfert cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransfertDTO result = transfertService.save(transfertDTO);
        return ResponseEntity.created(new URI("/api/transferts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transferts : Updates an existing transfert.
     *
     * @param transfertDTO the transfertDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transfertDTO,
     * or with status 400 (Bad Request) if the transfertDTO is not valid,
     * or with status 500 (Internal Server Error) if the transfertDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transferts")
    @Timed
    public ResponseEntity<TransfertDTO> updateTransfert(@RequestBody TransfertDTO transfertDTO) throws URISyntaxException {
        log.debug("REST request to update Transfert : {}", transfertDTO);
        if (transfertDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransfertDTO result = transfertService.save(transfertDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transfertDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transferts : get all the transferts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of transferts in body
     */
    @GetMapping("/transferts")
    @Timed
    public ResponseEntity<List<TransfertDTO>> getAllTransferts(Pageable pageable) {
        log.debug("REST request to get a page of Transferts");
        Page<TransfertDTO> page = transfertService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/transferts");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /transferts/:id : get the "id" transfert.
     *
     * @param id the id of the transfertDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transfertDTO, or with status 404 (Not Found)
     */
    @GetMapping("/transferts/{id}")
    @Timed
    public ResponseEntity<TransfertDTO> getTransfert(@PathVariable Long id) {
        log.debug("REST request to get Transfert : {}", id);
        Optional<TransfertDTO> transfertDTO = transfertService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transfertDTO);
    }

    /**
     * DELETE  /transferts/:id : delete the "id" transfert.
     *
     * @param id the id of the transfertDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transferts/{id}")
    @Timed
    public ResponseEntity<Void> deleteTransfert(@PathVariable Long id) {
        log.debug("REST request to delete Transfert : {}", id);
        transfertService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/transferts?query=:query : search for the transfert corresponding
     * to the query.
     *
     * @param query the query of the transfert search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/transferts")
    @Timed
    public ResponseEntity<List<TransfertDTO>> searchTransferts(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Transferts for query {}", query);
        Page<TransfertDTO> page = transfertService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/transferts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
