package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.StockArticleConteneurReceptionService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.StockArticleConteneurReceptionDTO;
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
 * REST controller for managing StockArticleConteneurReception.
 */
@RestController
@RequestMapping("/api")
public class StockArticleConteneurReceptionResource {

    private final Logger log = LoggerFactory.getLogger(StockArticleConteneurReceptionResource.class);

    private static final String ENTITY_NAME = "stockArticleConteneurReception";

    private final StockArticleConteneurReceptionService stockArticleConteneurReceptionService;

    public StockArticleConteneurReceptionResource(StockArticleConteneurReceptionService stockArticleConteneurReceptionService) {
        this.stockArticleConteneurReceptionService = stockArticleConteneurReceptionService;
    }

    /**
     * POST  /stock-article-conteneur-receptions : Create a new stockArticleConteneurReception.
     *
     * @param stockArticleConteneurReceptionDTO the stockArticleConteneurReceptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stockArticleConteneurReceptionDTO, or with status 400 (Bad Request) if the stockArticleConteneurReception has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stock-article-conteneur-receptions")
    @Timed
    public ResponseEntity<StockArticleConteneurReceptionDTO> createStockArticleConteneurReception(@RequestBody StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO) throws URISyntaxException {
        log.debug("REST request to save StockArticleConteneurReception : {}", stockArticleConteneurReceptionDTO);
        if (stockArticleConteneurReceptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new stockArticleConteneurReception cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockArticleConteneurReceptionDTO result = stockArticleConteneurReceptionService.save(stockArticleConteneurReceptionDTO);
        return ResponseEntity.created(new URI("/api/stock-article-conteneur-receptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stock-article-conteneur-receptions : Updates an existing stockArticleConteneurReception.
     *
     * @param stockArticleConteneurReceptionDTO the stockArticleConteneurReceptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stockArticleConteneurReceptionDTO,
     * or with status 400 (Bad Request) if the stockArticleConteneurReceptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the stockArticleConteneurReceptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stock-article-conteneur-receptions")
    @Timed
    public ResponseEntity<StockArticleConteneurReceptionDTO> updateStockArticleConteneurReception(@RequestBody StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO) throws URISyntaxException {
        log.debug("REST request to update StockArticleConteneurReception : {}", stockArticleConteneurReceptionDTO);
        if (stockArticleConteneurReceptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StockArticleConteneurReceptionDTO result = stockArticleConteneurReceptionService.save(stockArticleConteneurReceptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stockArticleConteneurReceptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stock-article-conteneur-receptions : get all the stockArticleConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stockArticleConteneurReceptions in body
     */
    @GetMapping("/stock-article-conteneur-receptions")
    @Timed
    public ResponseEntity<List<StockArticleConteneurReceptionDTO>> getAllStockArticleConteneurReceptions(Pageable pageable) {
        log.debug("REST request to get a page of StockArticleConteneurReceptions");
        Page<StockArticleConteneurReceptionDTO> page = stockArticleConteneurReceptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-article-conteneur-receptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /stock-article-conteneur-receptions/:id : get the "id" stockArticleConteneurReception.
     *
     * @param id the id of the stockArticleConteneurReceptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stockArticleConteneurReceptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-article-conteneur-receptions/{id}")
    @Timed
    public ResponseEntity<StockArticleConteneurReceptionDTO> getStockArticleConteneurReception(@PathVariable Long id) {
        log.debug("REST request to get StockArticleConteneurReception : {}", id);
        Optional<StockArticleConteneurReceptionDTO> stockArticleConteneurReceptionDTO = stockArticleConteneurReceptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockArticleConteneurReceptionDTO);
    }

    /**
     * DELETE  /stock-article-conteneur-receptions/:id : delete the "id" stockArticleConteneurReception.
     *
     * @param id the id of the stockArticleConteneurReceptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stock-article-conteneur-receptions/{id}")
    @Timed
    public ResponseEntity<Void> deleteStockArticleConteneurReception(@PathVariable Long id) {
        log.debug("REST request to delete StockArticleConteneurReception : {}", id);
        stockArticleConteneurReceptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/stock-article-conteneur-receptions?query=:query : search for the stockArticleConteneurReception corresponding
     * to the query.
     *
     * @param query the query of the stockArticleConteneurReception search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/stock-article-conteneur-receptions")
    @Timed
    public ResponseEntity<List<StockArticleConteneurReceptionDTO>> searchStockArticleConteneurReceptions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of StockArticleConteneurReceptions for query {}", query);
        Page<StockArticleConteneurReceptionDTO> page = stockArticleConteneurReceptionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/stock-article-conteneur-receptions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
