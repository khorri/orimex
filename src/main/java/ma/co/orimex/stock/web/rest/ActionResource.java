package ma.co.orimex.stock.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.co.orimex.stock.service.ActionService;
import ma.co.orimex.stock.web.rest.errors.BadRequestAlertException;
import ma.co.orimex.stock.web.rest.util.HeaderUtil;
import ma.co.orimex.stock.web.rest.util.PaginationUtil;
import ma.co.orimex.stock.service.dto.ActionDTO;
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
 * REST controller for managing Action.
 */
@RestController
@RequestMapping("/api")
public class ActionResource {

    private final Logger log = LoggerFactory.getLogger(ActionResource.class);

    private static final String ENTITY_NAME = "action";

    private final ActionService actionService;

    public ActionResource(ActionService actionService) {
        this.actionService = actionService;
    }

    /**
     * POST  /actions : Create a new action.
     *
     * @param actionDTO the actionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new actionDTO, or with status 400 (Bad Request) if the action has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/actions")
    @Timed
    public ResponseEntity<ActionDTO> createAction(@RequestBody ActionDTO actionDTO) throws URISyntaxException {
        log.debug("REST request to save Action : {}", actionDTO);
        if (actionDTO.getId() != null) {
            throw new BadRequestAlertException("A new action cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActionDTO result = actionService.save(actionDTO);
        return ResponseEntity.created(new URI("/api/actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /actions : Updates an existing action.
     *
     * @param actionDTO the actionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated actionDTO,
     * or with status 400 (Bad Request) if the actionDTO is not valid,
     * or with status 500 (Internal Server Error) if the actionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/actions")
    @Timed
    public ResponseEntity<ActionDTO> updateAction(@RequestBody ActionDTO actionDTO) throws URISyntaxException {
        log.debug("REST request to update Action : {}", actionDTO);
        if (actionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActionDTO result = actionService.save(actionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, actionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /actions : get all the actions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of actions in body
     */
    @GetMapping("/actions")
    @Timed
    public ResponseEntity<List<ActionDTO>> getAllActions(Pageable pageable) {
        log.debug("REST request to get a page of Actions");
        Page<ActionDTO> page = actionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/actions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /actions/:id : get the "id" action.
     *
     * @param id the id of the actionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the actionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/actions/{id}")
    @Timed
    public ResponseEntity<ActionDTO> getAction(@PathVariable Long id) {
        log.debug("REST request to get Action : {}", id);
        Optional<ActionDTO> actionDTO = actionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(actionDTO);
    }

    /**
     * DELETE  /actions/:id : delete the "id" action.
     *
     * @param id the id of the actionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/actions/{id}")
    @Timed
    public ResponseEntity<Void> deleteAction(@PathVariable Long id) {
        log.debug("REST request to delete Action : {}", id);
        actionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/actions?query=:query : search for the action corresponding
     * to the query.
     *
     * @param query the query of the action search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/actions")
    @Timed
    public ResponseEntity<List<ActionDTO>> searchActions(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Actions for query {}", query);
        Page<ActionDTO> page = actionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/actions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
