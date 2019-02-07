package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.CelebService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.CelebDTO;
import com.jhipsterpress.web.service.dto.CelebCriteria;
import com.jhipsterpress.web.service.CelebQueryService;
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
 * REST controller for managing Celeb.
 */
@RestController
@RequestMapping("/api")
public class CelebResource {

    private final Logger log = LoggerFactory.getLogger(CelebResource.class);

    private static final String ENTITY_NAME = "celeb";

    private final CelebService celebService;

    private final CelebQueryService celebQueryService;

    public CelebResource(CelebService celebService, CelebQueryService celebQueryService) {
        this.celebService = celebService;
        this.celebQueryService = celebQueryService;
    }

    /**
     * POST  /celebs : Create a new celeb.
     *
     * @param celebDTO the celebDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new celebDTO, or with status 400 (Bad Request) if the celeb has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/celebs")
    public ResponseEntity<CelebDTO> createCeleb(@Valid @RequestBody CelebDTO celebDTO) throws URISyntaxException {
        log.debug("REST request to save Celeb : {}", celebDTO);
        if (celebDTO.getId() != null) {
            throw new BadRequestAlertException("A new celeb cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CelebDTO result = celebService.save(celebDTO);
        return ResponseEntity.created(new URI("/api/celebs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /celebs : Updates an existing celeb.
     *
     * @param celebDTO the celebDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated celebDTO,
     * or with status 400 (Bad Request) if the celebDTO is not valid,
     * or with status 500 (Internal Server Error) if the celebDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/celebs")
    public ResponseEntity<CelebDTO> updateCeleb(@Valid @RequestBody CelebDTO celebDTO) throws URISyntaxException {
        log.debug("REST request to update Celeb : {}", celebDTO);
        if (celebDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CelebDTO result = celebService.save(celebDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, celebDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /celebs : get all the celebs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of celebs in body
     */
    @GetMapping("/celebs")
    public ResponseEntity<List<CelebDTO>> getAllCelebs(CelebCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Celebs by criteria: {}", criteria);
        Page<CelebDTO> page = celebQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/celebs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /celebs/count : count all the celebs.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/celebs/count")
    public ResponseEntity<Long> countCelebs(CelebCriteria criteria) {
        log.debug("REST request to count Celebs by criteria: {}", criteria);
        return ResponseEntity.ok().body(celebQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /celebs/:id : get the "id" celeb.
     *
     * @param id the id of the celebDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the celebDTO, or with status 404 (Not Found)
     */
    @GetMapping("/celebs/{id}")
    public ResponseEntity<CelebDTO> getCeleb(@PathVariable Long id) {
        log.debug("REST request to get Celeb : {}", id);
        Optional<CelebDTO> celebDTO = celebService.findOne(id);
        return ResponseUtil.wrapOrNotFound(celebDTO);
    }

    /**
     * DELETE  /celebs/:id : delete the "id" celeb.
     *
     * @param id the id of the celebDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/celebs/{id}")
    public ResponseEntity<Void> deleteCeleb(@PathVariable Long id) {
        log.debug("REST request to delete Celeb : {}", id);
        celebService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
