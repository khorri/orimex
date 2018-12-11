package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.StockConteneurReceptionService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.StockConteneurReceptionDTO;
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
 * REST controller for managing StockConteneurReception.
 */
@RestController
@RequestMapping("/api")
public class StockConteneurReceptionResource {

    private final Logger log = LoggerFactory.getLogger(StockConteneurReceptionResource.class);

    private static final String ENTITY_NAME = "stockConteneurReception";

    private final StockConteneurReceptionService stockConteneurReceptionService;

    public StockConteneurReceptionResource(StockConteneurReceptionService stockConteneurReceptionService) {
        this.stockConteneurReceptionService = stockConteneurReceptionService;
    }

    /**
     * POST  /stock-conteneur-receptions : Create a new stockConteneurReception.
     *
     * @param stockConteneurReceptionDTO the stockConteneurReceptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stockConteneurReceptionDTO, or with status 400 (Bad Request) if the stockConteneurReception has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stock-conteneur-receptions")
    @Timed
    public ResponseEntity<StockConteneurReceptionDTO> createStockConteneurReception(@RequestBody StockConteneurReceptionDTO stockConteneurReceptionDTO) throws URISyntaxException {
        log.debug("REST request to save StockConteneurReception : {}", stockConteneurReceptionDTO);
        if (stockConteneurReceptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new stockConteneurReception cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockConteneurReceptionDTO result = stockConteneurReceptionService.save(stockConteneurReceptionDTO);
        return ResponseEntity.created(new URI("/api/stock-conteneur-receptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stock-conteneur-receptions : Updates an existing stockConteneurReception.
     *
     * @param stockConteneurReceptionDTO the stockConteneurReceptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stockConteneurReceptionDTO,
     * or with status 400 (Bad Request) if the stockConteneurReceptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the stockConteneurReceptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stock-conteneur-receptions")
    @Timed
    public ResponseEntity<StockConteneurReceptionDTO> updateStockConteneurReception(@RequestBody StockConteneurReceptionDTO stockConteneurReceptionDTO) throws URISyntaxException {
        log.debug("REST request to update StockConteneurReception : {}", stockConteneurReceptionDTO);
        if (stockConteneurReceptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StockConteneurReceptionDTO result = stockConteneurReceptionService.save(stockConteneurReceptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stockConteneurReceptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stock-conteneur-receptions : get all the stockConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stockConteneurReceptions in body
     */
    @GetMapping("/stock-conteneur-receptions")
    @Timed
    public ResponseEntity<List<StockConteneurReceptionDTO>> getAllStockConteneurReceptions(Pageable pageable) {
        log.debug("REST request to get a page of StockConteneurReceptions");
        Page<StockConteneurReceptionDTO> page = stockConteneurReceptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-conteneur-receptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /stock-conteneur-receptions/:id : get the "id" stockConteneurReception.
     *
     * @param id the id of the stockConteneurReceptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stockConteneurReceptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-conteneur-receptions/{id}")
    @Timed
    public ResponseEntity<StockConteneurReceptionDTO> getStockConteneurReception(@PathVariable Long id) {
        log.debug("REST request to get StockConteneurReception : {}", id);
        Optional<StockConteneurReceptionDTO> stockConteneurReceptionDTO = stockConteneurReceptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockConteneurReceptionDTO);
    }

    /**
     * DELETE  /stock-conteneur-receptions/:id : delete the "id" stockConteneurReception.
     *
     * @param id the id of the stockConteneurReceptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stock-conteneur-receptions/{id}")
    @Timed
    public ResponseEntity<Void> deleteStockConteneurReception(@PathVariable Long id) {
        log.debug("REST request to delete StockConteneurReception : {}", id);
        stockConteneurReceptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/stock-conteneur-receptions?query=:query : search for the stockConteneurReception corresponding
     * to the query.
     *
     * @param query the query of the stockConteneurReception search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/stock-conteneur-receptions")
    @Timed
    public ResponseEntity<List<StockConteneurReceptionDTO>> searchStockConteneurReceptions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of StockConteneurReceptions for query {}", query);
        Page<StockConteneurReceptionDTO> page = stockConteneurReceptionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/stock-conteneur-receptions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
