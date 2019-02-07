package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.UprofileService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.UprofileDTO;
import com.jhipsterpress.web.service.dto.UprofileCriteria;
import com.jhipsterpress.web.service.UprofileQueryService;
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
 * REST controller for managing Uprofile.
 */
@RestController
@RequestMapping("/api")
public class UprofileResource {

    private final Logger log = LoggerFactory.getLogger(UprofileResource.class);

    private static final String ENTITY_NAME = "uprofile";

    private final UprofileService uprofileService;

    private final UprofileQueryService uprofileQueryService;

    public UprofileResource(UprofileService uprofileService, UprofileQueryService uprofileQueryService) {
        this.uprofileService = uprofileService;
        this.uprofileQueryService = uprofileQueryService;
    }

    /**
     * POST  /uprofiles : Create a new uprofile.
     *
     * @param uprofileDTO the uprofileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uprofileDTO, or with status 400 (Bad Request) if the uprofile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uprofiles")
    public ResponseEntity<UprofileDTO> createUprofile(@Valid @RequestBody UprofileDTO uprofileDTO) throws URISyntaxException {
        log.debug("REST request to save Uprofile : {}", uprofileDTO);
        if (uprofileDTO.getId() != null) {
            throw new BadRequestAlertException("A new uprofile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UprofileDTO result = uprofileService.save(uprofileDTO);
        return ResponseEntity.created(new URI("/api/uprofiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uprofiles : Updates an existing uprofile.
     *
     * @param uprofileDTO the uprofileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uprofileDTO,
     * or with status 400 (Bad Request) if the uprofileDTO is not valid,
     * or with status 500 (Internal Server Error) if the uprofileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uprofiles")
    public ResponseEntity<UprofileDTO> updateUprofile(@Valid @RequestBody UprofileDTO uprofileDTO) throws URISyntaxException {
        log.debug("REST request to update Uprofile : {}", uprofileDTO);
        if (uprofileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UprofileDTO result = uprofileService.save(uprofileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uprofileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uprofiles : get all the uprofiles.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of uprofiles in body
     */
    @GetMapping("/uprofiles")
    public ResponseEntity<List<UprofileDTO>> getAllUprofiles(UprofileCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Uprofiles by criteria: {}", criteria);
        Page<UprofileDTO> page = uprofileQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/uprofiles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /uprofiles/count : count all the uprofiles.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/uprofiles/count")
    public ResponseEntity<Long> countUprofiles(UprofileCriteria criteria) {
        log.debug("REST request to count Uprofiles by criteria: {}", criteria);
        return ResponseEntity.ok().body(uprofileQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /uprofiles/:id : get the "id" uprofile.
     *
     * @param id the id of the uprofileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uprofileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uprofiles/{id}")
    public ResponseEntity<UprofileDTO> getUprofile(@PathVariable Long id) {
        log.debug("REST request to get Uprofile : {}", id);
        Optional<UprofileDTO> uprofileDTO = uprofileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uprofileDTO);
    }

    /**
     * DELETE  /uprofiles/:id : delete the "id" uprofile.
     *
     * @param id the id of the uprofileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uprofiles/{id}")
    public ResponseEntity<Void> deleteUprofile(@PathVariable Long id) {
        log.debug("REST request to delete Uprofile : {}", id);
        uprofileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
