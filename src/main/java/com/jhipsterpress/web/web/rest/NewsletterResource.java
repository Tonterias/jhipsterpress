package com.jhipsterpress.web.web.rest;
import com.jhipsterpress.web.service.NewsletterService;
import com.jhipsterpress.web.web.rest.errors.BadRequestAlertException;
import com.jhipsterpress.web.web.rest.util.HeaderUtil;
import com.jhipsterpress.web.web.rest.util.PaginationUtil;
import com.jhipsterpress.web.service.dto.NewsletterDTO;
import com.jhipsterpress.web.service.dto.NewsletterCriteria;
import com.jhipsterpress.web.service.NewsletterQueryService;
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
 * REST controller for managing Newsletter.
 */
@RestController
@RequestMapping("/api")
public class NewsletterResource {

    private final Logger log = LoggerFactory.getLogger(NewsletterResource.class);

    private static final String ENTITY_NAME = "newsletter";

    private final NewsletterService newsletterService;

    private final NewsletterQueryService newsletterQueryService;

    public NewsletterResource(NewsletterService newsletterService, NewsletterQueryService newsletterQueryService) {
        this.newsletterService = newsletterService;
        this.newsletterQueryService = newsletterQueryService;
    }

    /**
     * POST  /newsletters : Create a new newsletter.
     *
     * @param newsletterDTO the newsletterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new newsletterDTO, or with status 400 (Bad Request) if the newsletter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/newsletters")
    public ResponseEntity<NewsletterDTO> createNewsletter(@Valid @RequestBody NewsletterDTO newsletterDTO) throws URISyntaxException {
        log.debug("REST request to save Newsletter : {}", newsletterDTO);
        if (newsletterDTO.getId() != null) {
            throw new BadRequestAlertException("A new newsletter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NewsletterDTO result = newsletterService.save(newsletterDTO);
        return ResponseEntity.created(new URI("/api/newsletters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /newsletters : Updates an existing newsletter.
     *
     * @param newsletterDTO the newsletterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated newsletterDTO,
     * or with status 400 (Bad Request) if the newsletterDTO is not valid,
     * or with status 500 (Internal Server Error) if the newsletterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/newsletters")
    public ResponseEntity<NewsletterDTO> updateNewsletter(@Valid @RequestBody NewsletterDTO newsletterDTO) throws URISyntaxException {
        log.debug("REST request to update Newsletter : {}", newsletterDTO);
        if (newsletterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NewsletterDTO result = newsletterService.save(newsletterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, newsletterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /newsletters : get all the newsletters.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of newsletters in body
     */
    @GetMapping("/newsletters")
    public ResponseEntity<List<NewsletterDTO>> getAllNewsletters(NewsletterCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Newsletters by criteria: {}", criteria);
        Page<NewsletterDTO> page = newsletterQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/newsletters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /newsletters/count : count all the newsletters.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/newsletters/count")
    public ResponseEntity<Long> countNewsletters(NewsletterCriteria criteria) {
        log.debug("REST request to count Newsletters by criteria: {}", criteria);
        return ResponseEntity.ok().body(newsletterQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /newsletters/:id : get the "id" newsletter.
     *
     * @param id the id of the newsletterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the newsletterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/newsletters/{id}")
    public ResponseEntity<NewsletterDTO> getNewsletter(@PathVariable Long id) {
        log.debug("REST request to get Newsletter : {}", id);
        Optional<NewsletterDTO> newsletterDTO = newsletterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(newsletterDTO);
    }

    /**
     * DELETE  /newsletters/:id : delete the "id" newsletter.
     *
     * @param id the id of the newsletterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/newsletters/{id}")
    public ResponseEntity<Void> deleteNewsletter(@PathVariable Long id) {
        log.debug("REST request to delete Newsletter : {}", id);
        newsletterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
