package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.NewsletterDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Newsletter.
 */
public interface NewsletterService {

    /**
     * Save a newsletter.
     *
     * @param newsletterDTO the entity to save
     * @return the persisted entity
     */
    NewsletterDTO save(NewsletterDTO newsletterDTO);

    /**
     * Get all the newsletters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NewsletterDTO> findAll(Pageable pageable);


    /**
     * Get the "id" newsletter.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NewsletterDTO> findOne(Long id);

    /**
     * Delete the "id" newsletter.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
