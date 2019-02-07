package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.VquestionService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.VquestionDTO;
import com.jhipsterpress.web.service.dto.VquestionCriteria;
import com.jhipsterpress.web.service.VquestionQueryService;
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
 * REST controller for managing Vquestion.
 */
@RestController
@RequestMapping("/api")
public class VquestionResource {

    private final Logger log = LoggerFactory.getLogger(VquestionResource.class);

    private static final String ENTITY_NAME = "vquestion";

    private final VquestionService vquestionService;

    private final VquestionQueryService vquestionQueryService;

    public VquestionResource(VquestionService vquestionService, VquestionQueryService vquestionQueryService) {
        this.vquestionService = vquestionService;
        this.vquestionQueryService = vquestionQueryService;
    }

    /**
     * POST  /vquestions : Create a new vquestion.
     *
     * @param vquestionDTO the vquestionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vquestionDTO, or with status 400 (Bad Request) if the vquestion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vquestions")
    public ResponseEntity<VquestionDTO> createVquestion(@Valid @RequestBody VquestionDTO vquestionDTO) throws URISyntaxException {
        log.debug("REST request to save Vquestion : {}", vquestionDTO);
        if (vquestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new vquestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VquestionDTO result = vquestionService.save(vquestionDTO);
        return ResponseEntity.created(new URI("/api/vquestions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vquestions : Updates an existing vquestion.
     *
     * @param vquestionDTO the vquestionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vquestionDTO,
     * or with status 400 (Bad Request) if the vquestionDTO is not valid,
     * or with status 500 (Internal Server Error) if the vquestionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vquestions")
    public ResponseEntity<VquestionDTO> updateVquestion(@Valid @RequestBody VquestionDTO vquestionDTO) throws URISyntaxException {
        log.debug("REST request to update Vquestion : {}", vquestionDTO);
        if (vquestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VquestionDTO result = vquestionService.save(vquestionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vquestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vquestions : get all the vquestions.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of vquestions in body
     */
    @GetMapping("/vquestions")
    public ResponseEntity<List<VquestionDTO>> getAllVquestions(VquestionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Vquestions by criteria: {}", criteria);
        Page<VquestionDTO> page = vquestionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vquestions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /vquestions/count : count all the vquestions.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/vquestions/count")
    public ResponseEntity<Long> countVquestions(VquestionCriteria criteria) {
        log.debug("REST request to count Vquestions by criteria: {}", criteria);
        return ResponseEntity.ok().body(vquestionQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /vquestions/:id : get the "id" vquestion.
     *
     * @param id the id of the vquestionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vquestionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vquestions/{id}")
    public ResponseEntity<VquestionDTO> getVquestion(@PathVariable Long id) {
        log.debug("REST request to get Vquestion : {}", id);
        Optional<VquestionDTO> vquestionDTO = vquestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vquestionDTO);
    }

    /**
     * DELETE  /vquestions/:id : delete the "id" vquestion.
     *
     * @param id the id of the vquestionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vquestions/{id}")
    public ResponseEntity<Void> deleteVquestion(@PathVariable Long id) {
        log.debug("REST request to delete Vquestion : {}", id);
        vquestionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
