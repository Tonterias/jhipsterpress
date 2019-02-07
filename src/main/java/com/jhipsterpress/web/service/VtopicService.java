package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.VtopicDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Vtopic.
 */
public interface VtopicService {

    /**
     * Save a vtopic.
     *
     * @param vtopicDTO the entity to save
     * @return the persisted entity
     */
    VtopicDTO save(VtopicDTO vtopicDTO);

    /**
     * Get all the vtopics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VtopicDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vtopic.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VtopicDTO> findOne(Long id);

    /**
     * Delete the "id" vtopic.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
