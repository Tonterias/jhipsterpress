package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.CmessageService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.CmessageDTO;
import com.jhipsterpress.web.service.dto.CmessageCriteria;
import com.jhipsterpress.web.service.CmessageQueryService;
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
 * REST controller for managing Cmessage.
 */
@RestController
@RequestMapping("/api")
public class CmessageResource {

    private final Logger log = LoggerFactory.getLogger(CmessageResource.class);

    private static final String ENTITY_NAME = "cmessage";

    private final CmessageService cmessageService;

    private final CmessageQueryService cmessageQueryService;

    public CmessageResource(CmessageService cmessageService, CmessageQueryService cmessageQueryService) {
        this.cmessageService = cmessageService;
        this.cmessageQueryService = cmessageQueryService;
    }

    /**
     * POST  /cmessages : Create a new cmessage.
     *
     * @param cmessageDTO the cmessageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cmessageDTO, or with status 400 (Bad Request) if the cmessage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cmessages")
    public ResponseEntity<CmessageDTO> createCmessage(@Valid @RequestBody CmessageDTO cmessageDTO) throws URISyntaxException {
        log.debug("REST request to save Cmessage : {}", cmessageDTO);
        if (cmessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new cmessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CmessageDTO result = cmessageService.save(cmessageDTO);
        return ResponseEntity.created(new URI("/api/cmessages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cmessages : Updates an existing cmessage.
     *
     * @param cmessageDTO the cmessageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cmessageDTO,
     * or with status 400 (Bad Request) if the cmessageDTO is not valid,
     * or with status 500 (Internal Server Error) if the cmessageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cmessages")
    public ResponseEntity<CmessageDTO> updateCmessage(@Valid @RequestBody CmessageDTO cmessageDTO) throws URISyntaxException {
        log.debug("REST request to update Cmessage : {}", cmessageDTO);
        if (cmessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CmessageDTO result = cmessageService.save(cmessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cmessages : get all the cmessages.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of cmessages in body
     */
    @GetMapping("/cmessages")
    public ResponseEntity<List<CmessageDTO>> getAllCmessages(CmessageCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Cmessages by criteria: {}", criteria);
        Page<CmessageDTO> page = cmessageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cmessages");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /cmessages/count : count all the cmessages.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/cmessages/count")
    public ResponseEntity<Long> countCmessages(CmessageCriteria criteria) {
        log.debug("REST request to count Cmessages by criteria: {}", criteria);
        return ResponseEntity.ok().body(cmessageQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /cmessages/:id : get the "id" cmessage.
     *
     * @param id the id of the cmessageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cmessageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cmessages/{id}")
    public ResponseEntity<CmessageDTO> getCmessage(@PathVariable Long id) {
        log.debug("REST request to get Cmessage : {}", id);
        Optional<CmessageDTO> cmessageDTO = cmessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cmessageDTO);
    }

    /**
     * DELETE  /cmessages/:id : delete the "id" cmessage.
     *
     * @param id the id of the cmessageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cmessages/{id}")
    public ResponseEntity<Void> deleteCmessage(@PathVariable Long id) {
        log.debug("REST request to delete Cmessage : {}", id);
        cmessageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
