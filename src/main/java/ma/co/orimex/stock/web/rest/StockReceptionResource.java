package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.StockReceptionService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.StockReceptionDTO;
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
 * REST controller for managing StockReception.
 */
@RestController
@RequestMapping("/api")
public class StockReceptionResource {

    private final Logger log = LoggerFactory.getLogger(StockReceptionResource.class);

    private static final String ENTITY_NAME = "stockReception";

    private final StockReceptionService stockReceptionService;

    public StockReceptionResource(StockReceptionService stockReceptionService) {
        this.stockReceptionService = stockReceptionService;
    }

    /**
     * POST  /stock-receptions : Create a new stockReception.
     *
     * @param stockReceptionDTO the stockReceptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stockReceptionDTO, or with status 400 (Bad Request) if the stockReception has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stock-receptions")
    @Timed
    public ResponseEntity<StockReceptionDTO> createStockReception(@RequestBody StockReceptionDTO stockReceptionDTO) throws URISyntaxException {
        log.debug("REST request to save StockReception : {}", stockReceptionDTO);
        if (stockReceptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new stockReception cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockReceptionDTO result = stockReceptionService.save(stockReceptionDTO);
        return ResponseEntity.created(new URI("/api/stock-receptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stock-receptions : Updates an existing stockReception.
     *
     * @param stockReceptionDTO the stockReceptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stockReceptionDTO,
     * or with status 400 (Bad Request) if the stockReceptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the stockReceptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stock-receptions")
    @Timed
    public ResponseEntity<StockReceptionDTO> updateStockReception(@RequestBody StockReceptionDTO stockReceptionDTO) throws URISyntaxException {
        log.debug("REST request to update StockReception : {}", stockReceptionDTO);
        if (stockReceptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StockReceptionDTO result = stockReceptionService.save(stockReceptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stockReceptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stock-receptions : get all the stockReceptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stockReceptions in body
     */
    @GetMapping("/stock-receptions")
    @Timed
    public ResponseEntity<List<StockReceptionDTO>> getAllStockReceptions(Pageable pageable) {
        log.debug("REST request to get a page of StockReceptions");
        Page<StockReceptionDTO> page = stockReceptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-receptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /stock-receptions/:id : get the "id" stockReception.
     *
     * @param id the id of the stockReceptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stockReceptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-receptions/{id}")
    @Timed
    public ResponseEntity<StockReceptionDTO> getStockReception(@PathVariable Long id) {
        log.debug("REST request to get StockReception : {}", id);
        Optional<StockReceptionDTO> stockReceptionDTO = stockReceptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockReceptionDTO);
    }

    /**
     * DELETE  /stock-receptions/:id : delete the "id" stockReception.
     *
     * @param id the id of the stockReceptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stock-receptions/{id}")
    @Timed
    public ResponseEntity<Void> deleteStockReception(@PathVariable Long id) {
        log.debug("REST request to delete StockReception : {}", id);
        stockReceptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/stock-receptions?query=:query : search for the stockReception corresponding
     * to the query.
     *
     * @param query the query of the stockReception search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/stock-receptions")
    @Timed
    public ResponseEntity<List<StockReceptionDTO>> searchStockReceptions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of StockReceptions for query {}", query);
        Page<StockReceptionDTO> page = stockReceptionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/stock-receptions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
