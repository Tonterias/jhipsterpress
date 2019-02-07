package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.VquestionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Vquestion.
 */
public interface VquestionService {

    /**
     * Save a vquestion.
     *
     * @param vquestionDTO the entity to save
     * @return the persisted entity
     */
    VquestionDTO save(VquestionDTO vquestionDTO);

    /**
     * Get all the vquestions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VquestionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vquestion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VquestionDTO> findOne(Long id);

    /**
     * Delete the "id" vquestion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
