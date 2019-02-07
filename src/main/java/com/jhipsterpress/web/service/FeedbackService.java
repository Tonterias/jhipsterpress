package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.FeedbackDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Feedback.
 */
public interface FeedbackService {

    /**
     * Save a feedback.
     *
     * @param feedbackDTO the entity to save
     * @return the persisted entity
     */
    FeedbackDTO save(FeedbackDTO feedbackDTO);

    /**
     * Get all the feedbacks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FeedbackDTO> findAll(Pageable pageable);


    /**
     * Get the "id" feedback.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FeedbackDTO> findOne(Long id);

    /**
     * Delete the "id" feedback.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
