package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.FrontpageconfigService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.FrontpageconfigDTO;
import com.jhipsterpress.web.service.dto.CustomFrontpageconfigDTO;
import com.jhipsterpress.web.service.dto.FrontpageconfigCriteria;
import com.jhipsterpress.web.service.FrontpageconfigQueryService;
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
 * REST controller for managing Frontpageconfig.
 */
@RestController
@RequestMapping("/api")
public class FrontpageconfigResource {

    private final Logger log = LoggerFactory.getLogger(FrontpageconfigResource.class);

    private static final String ENTITY_NAME = "frontpageconfig";

    private final FrontpageconfigService frontpageconfigService;

    private final FrontpageconfigQueryService frontpageconfigQueryService;

    public FrontpageconfigResource(FrontpageconfigService frontpageconfigService, FrontpageconfigQueryService frontpageconfigQueryService) {
        this.frontpageconfigService = frontpageconfigService;
        this.frontpageconfigQueryService = frontpageconfigQueryService;
    }

    /**
     * POST  /frontpageconfigs : Create a new frontpageconfig.
     *
     * @param frontpageconfigDTO the frontpageconfigDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new frontpageconfigDTO, or with status 400 (Bad Request) if the frontpageconfig has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/frontpageconfigs")
    public ResponseEntity<FrontpageconfigDTO> createFrontpageconfig(@Valid @RequestBody FrontpageconfigDTO frontpageconfigDTO) throws URISyntaxException {
        log.debug("REST request to save Frontpageconfig : {}", frontpageconfigDTO);
        if (frontpageconfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new frontpageconfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FrontpageconfigDTO result = frontpageconfigService.save(frontpageconfigDTO);
        return ResponseEntity.created(new URI("/api/frontpageconfigs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /frontpageconfigs : Updates an existing frontpageconfig.
     *
     * @param frontpageconfigDTO the frontpageconfigDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated frontpageconfigDTO,
     * or with status 400 (Bad Request) if the frontpageconfigDTO is not valid,
     * or with status 500 (Internal Server Error) if the frontpageconfigDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/frontpageconfigs")
    public ResponseEntity<FrontpageconfigDTO> updateFrontpageconfig(@Valid @RequestBody FrontpageconfigDTO frontpageconfigDTO) throws URISyntaxException {
        log.debug("REST request to update Frontpageconfig : {}", frontpageconfigDTO);
        if (frontpageconfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FrontpageconfigDTO result = frontpageconfigService.save(frontpageconfigDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, frontpageconfigDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /frontpageconfigs : get all the frontpageconfigs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of frontpageconfigs in body
     */
    @GetMapping("/frontpageconfigs")
    public ResponseEntity<List<FrontpageconfigDTO>> getAllFrontpageconfigs(FrontpageconfigCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Frontpageconfigs by criteria: {}", criteria);
        Page<FrontpageconfigDTO> page = frontpageconfigQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/frontpageconfigs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /frontpageconfigs/count : count all the frontpageconfigs.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/frontpageconfigs/count")
    public ResponseEntity<Long> countFrontpageconfigs(FrontpageconfigCriteria criteria) {
        log.debug("REST request to count Frontpageconfigs by criteria: {}", criteria);
        return ResponseEntity.ok().body(frontpageconfigQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /frontpageconfigs/:id : get the "id" frontpageconfig.
     *
     * @param id the id of the frontpageconfigDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the frontpageconfigDTO, or with status 404 (Not Found)
     */
    @GetMapping("/frontpageconfigs/{id}")
    public ResponseEntity<FrontpageconfigDTO> getFrontpageconfig(@PathVariable Long id) {
        log.debug("REST request to get Frontpageconfig : {}", id);
        Optional<FrontpageconfigDTO> frontpageconfigDTO = frontpageconfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(frontpageconfigDTO);
    }


    /**
     * GET  /frontpageconfigs/:id/posts : get the "id" frontpageconfig, including posts
     *
     * @param id the id of the frontpageconfigDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the frontpageconfigDTO, or with status 404 (Not Found)
     */
    @GetMapping("/frontpageconfigs/{id}/posts")
    public ResponseEntity<CustomFrontpageconfigDTO> getFrontpageconfigIncludingPosts(@PathVariable Long id) {
        log.debug("REST request to get Frontpageconfig : {}", id);
        Optional<CustomFrontpageconfigDTO> frontpageconfigDTO = frontpageconfigService.findOneIncludingPosts(id);
        return ResponseUtil.wrapOrNotFound(frontpageconfigDTO);
    }

    /**
     * DELETE  /frontpageconfigs/:id : delete the "id" frontpageconfig.
     *
     * @param id the id of the frontpageconfigDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/frontpageconfigs/{id}")
    public ResponseEntity<Void> deleteFrontpageconfig(@PathVariable Long id) {
        log.debug("REST request to delete Frontpageconfig : {}", id);
        frontpageconfigService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
