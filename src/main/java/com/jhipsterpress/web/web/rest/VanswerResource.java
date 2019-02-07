package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.VanswerService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.VanswerDTO;
import com.jhipsterpress.web.service.dto.VanswerCriteria;
import com.jhipsterpress.web.service.VanswerQueryService;
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
 * REST controller for managing Vanswer.
 */
@RestController
@RequestMapping("/api")
public class VanswerResource {

    private final Logger log = LoggerFactory.getLogger(VanswerResource.class);

    private static final String ENTITY_NAME = "vanswer";

    private final VanswerService vanswerService;

    private final VanswerQueryService vanswerQueryService;

    public VanswerResource(VanswerService vanswerService, VanswerQueryService vanswerQueryService) {
        this.vanswerService = vanswerService;
        this.vanswerQueryService = vanswerQueryService;
    }

    /**
     * POST  /vanswers : Create a new vanswer.
     *
     * @param vanswerDTO the vanswerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vanswerDTO, or with status 400 (Bad Request) if the vanswer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vanswers")
    public ResponseEntity<VanswerDTO> createVanswer(@Valid @RequestBody VanswerDTO vanswerDTO) throws URISyntaxException {
        log.debug("REST request to save Vanswer : {}", vanswerDTO);
        if (vanswerDTO.getId() != null) {
            throw new BadRequestAlertException("A new vanswer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VanswerDTO result = vanswerService.save(vanswerDTO);
        return ResponseEntity.created(new URI("/api/vanswers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vanswers : Updates an existing vanswer.
     *
     * @param vanswerDTO the vanswerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vanswerDTO,
     * or with status 400 (Bad Request) if the vanswerDTO is not valid,
     * or with status 500 (Internal Server Error) if the vanswerDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vanswers")
    public ResponseEntity<VanswerDTO> updateVanswer(@Valid @RequestBody VanswerDTO vanswerDTO) throws URISyntaxException {
        log.debug("REST request to update Vanswer : {}", vanswerDTO);
        if (vanswerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VanswerDTO result = vanswerService.save(vanswerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vanswerDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vanswers : get all the vanswers.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of vanswers in body
     */
    @GetMapping("/vanswers")
    public ResponseEntity<List<VanswerDTO>> getAllVanswers(VanswerCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Vanswers by criteria: {}", criteria);
        Page<VanswerDTO> page = vanswerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vanswers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /vanswers/count : count all the vanswers.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/vanswers/count")
    public ResponseEntity<Long> countVanswers(VanswerCriteria criteria) {
        log.debug("REST request to count Vanswers by criteria: {}", criteria);
        return ResponseEntity.ok().body(vanswerQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /vanswers/:id : get the "id" vanswer.
     *
     * @param id the id of the vanswerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vanswerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vanswers/{id}")
    public ResponseEntity<VanswerDTO> getVanswer(@PathVariable Long id) {
        log.debug("REST request to get Vanswer : {}", id);
        Optional<VanswerDTO> vanswerDTO = vanswerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vanswerDTO);
    }

    /**
     * DELETE  /vanswers/:id : delete the "id" vanswer.
     *
     * @param id the id of the vanswerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vanswers/{id}")
    public ResponseEntity<Void> deleteVanswer(@PathVariable Long id) {
        log.debug("REST request to delete Vanswer : {}", id);
        vanswerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
