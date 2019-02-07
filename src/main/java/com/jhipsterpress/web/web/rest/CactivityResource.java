package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.CactivityService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.CactivityDTO;
import com.jhipsterpress.web.service.dto.CactivityCriteria;
import com.jhipsterpress.web.service.CactivityQueryService;
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
 * REST controller for managing Cactivity.
 */
@RestController
@RequestMapping("/api")
public class CactivityResource {

    private final Logger log = LoggerFactory.getLogger(CactivityResource.class);

    private static final String ENTITY_NAME = "cactivity";

    private final CactivityService cactivityService;

    private final CactivityQueryService cactivityQueryService;

    public CactivityResource(CactivityService cactivityService, CactivityQueryService cactivityQueryService) {
        this.cactivityService = cactivityService;
        this.cactivityQueryService = cactivityQueryService;
    }

    /**
     * POST  /cactivities : Create a new cactivity.
     *
     * @param cactivityDTO the cactivityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cactivityDTO, or with status 400 (Bad Request) if the cactivity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cactivities")
    public ResponseEntity<CactivityDTO> createCactivity(@Valid @RequestBody CactivityDTO cactivityDTO) throws URISyntaxException {
        log.debug("REST request to save Cactivity : {}", cactivityDTO);
        if (cactivityDTO.getId() != null) {
            throw new BadRequestAlertException("A new cactivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CactivityDTO result = cactivityService.save(cactivityDTO);
        return ResponseEntity.created(new URI("/api/cactivities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cactivities : Updates an existing cactivity.
     *
     * @param cactivityDTO the cactivityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cactivityDTO,
     * or with status 400 (Bad Request) if the cactivityDTO is not valid,
     * or with status 500 (Internal Server Error) if the cactivityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cactivities")
    public ResponseEntity<CactivityDTO> updateCactivity(@Valid @RequestBody CactivityDTO cactivityDTO) throws URISyntaxException {
        log.debug("REST request to update Cactivity : {}", cactivityDTO);
        if (cactivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CactivityDTO result = cactivityService.save(cactivityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cactivityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cactivities : get all the cactivities.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of cactivities in body
     */
    @GetMapping("/cactivities")
    public ResponseEntity<List<CactivityDTO>> getAllCactivities(CactivityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Cactivities by criteria: {}", criteria);
        Page<CactivityDTO> page = cactivityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cactivities");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /cactivities/count : count all the cactivities.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/cactivities/count")
    public ResponseEntity<Long> countCactivities(CactivityCriteria criteria) {
        log.debug("REST request to count Cactivities by criteria: {}", criteria);
        return ResponseEntity.ok().body(cactivityQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /cactivities/:id : get the "id" cactivity.
     *
     * @param id the id of the cactivityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cactivityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cactivities/{id}")
    public ResponseEntity<CactivityDTO> getCactivity(@PathVariable Long id) {
        log.debug("REST request to get Cactivity : {}", id);
        Optional<CactivityDTO> cactivityDTO = cactivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cactivityDTO);
    }

    /**
     * DELETE  /cactivities/:id : delete the "id" cactivity.
     *
     * @param id the id of the cactivityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cactivities/{id}")
    public ResponseEntity<Void> deleteCactivity(@PathVariable Long id) {
        log.debug("REST request to delete Cactivity : {}", id);
        cactivityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
