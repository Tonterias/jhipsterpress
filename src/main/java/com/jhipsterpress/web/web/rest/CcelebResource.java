package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.CcelebService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.CcelebDTO;
import com.jhipsterpress.web.service.dto.CcelebCriteria;
import com.jhipsterpress.web.service.CcelebQueryService;
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
 * REST controller for managing Cceleb.
 */
@RestController
@RequestMapping("/api")
public class CcelebResource {

    private final Logger log = LoggerFactory.getLogger(CcelebResource.class);

    private static final String ENTITY_NAME = "cceleb";

    private final CcelebService ccelebService;

    private final CcelebQueryService ccelebQueryService;

    public CcelebResource(CcelebService ccelebService, CcelebQueryService ccelebQueryService) {
        this.ccelebService = ccelebService;
        this.ccelebQueryService = ccelebQueryService;
    }

    /**
     * POST  /ccelebs : Create a new cceleb.
     *
     * @param ccelebDTO the ccelebDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ccelebDTO, or with status 400 (Bad Request) if the cceleb has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ccelebs")
    public ResponseEntity<CcelebDTO> createCceleb(@Valid @RequestBody CcelebDTO ccelebDTO) throws URISyntaxException {
        log.debug("REST request to save Cceleb : {}", ccelebDTO);
        if (ccelebDTO.getId() != null) {
            throw new BadRequestAlertException("A new cceleb cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CcelebDTO result = ccelebService.save(ccelebDTO);
        return ResponseEntity.created(new URI("/api/ccelebs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ccelebs : Updates an existing cceleb.
     *
     * @param ccelebDTO the ccelebDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ccelebDTO,
     * or with status 400 (Bad Request) if the ccelebDTO is not valid,
     * or with status 500 (Internal Server Error) if the ccelebDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ccelebs")
    public ResponseEntity<CcelebDTO> updateCceleb(@Valid @RequestBody CcelebDTO ccelebDTO) throws URISyntaxException {
        log.debug("REST request to update Cceleb : {}", ccelebDTO);
        if (ccelebDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CcelebDTO result = ccelebService.save(ccelebDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ccelebDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ccelebs : get all the ccelebs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of ccelebs in body
     */
    @GetMapping("/ccelebs")
    public ResponseEntity<List<CcelebDTO>> getAllCcelebs(CcelebCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Ccelebs by criteria: {}", criteria);
        Page<CcelebDTO> page = ccelebQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ccelebs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /ccelebs/count : count all the ccelebs.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/ccelebs/count")
    public ResponseEntity<Long> countCcelebs(CcelebCriteria criteria) {
        log.debug("REST request to count Ccelebs by criteria: {}", criteria);
        return ResponseEntity.ok().body(ccelebQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /ccelebs/:id : get the "id" cceleb.
     *
     * @param id the id of the ccelebDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ccelebDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ccelebs/{id}")
    public ResponseEntity<CcelebDTO> getCceleb(@PathVariable Long id) {
        log.debug("REST request to get Cceleb : {}", id);
        Optional<CcelebDTO> ccelebDTO = ccelebService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ccelebDTO);
    }

    /**
     * DELETE  /ccelebs/:id : delete the "id" cceleb.
     *
     * @param id the id of the ccelebDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ccelebs/{id}")
    public ResponseEntity<Void> deleteCceleb(@PathVariable Long id) {
        log.debug("REST request to delete Cceleb : {}", id);
        ccelebService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
