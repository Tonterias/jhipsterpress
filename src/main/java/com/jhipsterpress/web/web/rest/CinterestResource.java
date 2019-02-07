package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.CinterestService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.CinterestDTO;
import com.jhipsterpress.web.service.dto.CinterestCriteria;
import com.jhipsterpress.web.service.CinterestQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Cinterest.
 */
@RestController
@RequestMapping("/api")
public class CinterestResource {

    private final Logger log = LoggerFactory.getLogger(CinterestResource.class);

    private static final String ENTITY_NAME = "cinterest";

    private final CinterestService cinterestService;

    private final CinterestQueryService cinterestQueryService;

    public CinterestResource(CinterestService cinterestService, CinterestQueryService cinterestQueryService) {
        this.cinterestService = cinterestService;
        this.cinterestQueryService = cinterestQueryService;
    }

    /**
     * POST  /cinterests : Create a new cinterest.
     *
     * @param cinterestDTO the cinterestDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cinterestDTO, or with status 400 (Bad Request) if the cinterest has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cinterests")
    public ResponseEntity<CinterestDTO> createCinterest(@Valid @RequestBody CinterestDTO cinterestDTO) throws URISyntaxException {
        log.debug("REST request to save Cinterest : {}", cinterestDTO);
        if (cinterestDTO.getId() != null) {
            throw new BadRequestAlertException("A new cinterest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CinterestDTO result = cinterestService.save(cinterestDTO);
        return ResponseEntity.created(new URI("/api/cinterests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cinterests : Updates an existing cinterest.
     *
     * @param cinterestDTO the cinterestDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cinterestDTO,
     * or with status 400 (Bad Request) if the cinterestDTO is not valid,
     * or with status 500 (Internal Server Error) if the cinterestDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cinterests")
    public ResponseEntity<CinterestDTO> updateCinterest(@Valid @RequestBody CinterestDTO cinterestDTO) throws URISyntaxException {
        log.debug("REST request to update Cinterest : {}", cinterestDTO);
        if (cinterestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CinterestDTO result = cinterestService.save(cinterestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cinterestDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cinterests : get all the cinterests.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of cinterests in body
     */
    @GetMapping("/cinterests")
    public ResponseEntity<List<CinterestDTO>> getAllCinterests(CinterestCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Cinterests by criteria: {}", criteria);
        Page<CinterestDTO> page = cinterestQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cinterests");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /cinterests/count : count all the cinterests.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/cinterests/count")
    public ResponseEntity<Long> countCinterests(CinterestCriteria criteria) {
        log.debug("REST request to count Cinterests by criteria: {}", criteria);
        return ResponseEntity.ok().body(cinterestQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /cinterests/:id : get the "id" cinterest.
     *
     * @param id the id of the cinterestDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cinterestDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cinterests/{id}")
    public ResponseEntity<CinterestDTO> getCinterest(@PathVariable Long id) {
        log.debug("REST request to get Cinterest : {}", id);
        Optional<CinterestDTO> cinterestDTO = cinterestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cinterestDTO);
    }

    /**
     * DELETE  /cinterests/:id : delete the "id" cinterest.
     *
     * @param id the id of the cinterestDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cinterests/{id}")
    public ResponseEntity<Void> deleteCinterest(@PathVariable Long id) {
        log.debug("REST request to delete Cinterest : {}", id);
        cinterestService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
