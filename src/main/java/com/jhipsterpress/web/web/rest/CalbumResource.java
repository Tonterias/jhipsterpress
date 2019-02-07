package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.CalbumService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.CalbumDTO;
import com.jhipsterpress.web.service.dto.CalbumCriteria;
import com.jhipsterpress.web.service.CalbumQueryService;
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
 * REST controller for managing Calbum.
 */
@RestController
@RequestMapping("/api")
public class CalbumResource {

    private final Logger log = LoggerFactory.getLogger(CalbumResource.class);

    private static final String ENTITY_NAME = "calbum";

    private final CalbumService calbumService;

    private final CalbumQueryService calbumQueryService;

    public CalbumResource(CalbumService calbumService, CalbumQueryService calbumQueryService) {
        this.calbumService = calbumService;
        this.calbumQueryService = calbumQueryService;
    }

    /**
     * POST  /calbums : Create a new calbum.
     *
     * @param calbumDTO the calbumDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new calbumDTO, or with status 400 (Bad Request) if the calbum has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/calbums")
    public ResponseEntity<CalbumDTO> createCalbum(@Valid @RequestBody CalbumDTO calbumDTO) throws URISyntaxException {
        log.debug("REST request to save Calbum : {}", calbumDTO);
        if (calbumDTO.getId() != null) {
            throw new BadRequestAlertException("A new calbum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CalbumDTO result = calbumService.save(calbumDTO);
        return ResponseEntity.created(new URI("/api/calbums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /calbums : Updates an existing calbum.
     *
     * @param calbumDTO the calbumDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated calbumDTO,
     * or with status 400 (Bad Request) if the calbumDTO is not valid,
     * or with status 500 (Internal Server Error) if the calbumDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/calbums")
    public ResponseEntity<CalbumDTO> updateCalbum(@Valid @RequestBody CalbumDTO calbumDTO) throws URISyntaxException {
        log.debug("REST request to update Calbum : {}", calbumDTO);
        if (calbumDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CalbumDTO result = calbumService.save(calbumDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, calbumDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /calbums : get all the calbums.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of calbums in body
     */
    @GetMapping("/calbums")
    public ResponseEntity<List<CalbumDTO>> getAllCalbums(CalbumCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Calbums by criteria: {}", criteria);
        Page<CalbumDTO> page = calbumQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/calbums");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /calbums/count : count all the calbums.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/calbums/count")
    public ResponseEntity<Long> countCalbums(CalbumCriteria criteria) {
        log.debug("REST request to count Calbums by criteria: {}", criteria);
        return ResponseEntity.ok().body(calbumQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /calbums/:id : get the "id" calbum.
     *
     * @param id the id of the calbumDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the calbumDTO, or with status 404 (Not Found)
     */
    @GetMapping("/calbums/{id}")
    public ResponseEntity<CalbumDTO> getCalbum(@PathVariable Long id) {
        log.debug("REST request to get Calbum : {}", id);
        Optional<CalbumDTO> calbumDTO = calbumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(calbumDTO);
    }

    /**
     * DELETE  /calbums/:id : delete the "id" calbum.
     *
     * @param id the id of the calbumDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/calbums/{id}")
    public ResponseEntity<Void> deleteCalbum(@PathVariable Long id) {
        log.debug("REST request to delete Calbum : {}", id);
        calbumService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
