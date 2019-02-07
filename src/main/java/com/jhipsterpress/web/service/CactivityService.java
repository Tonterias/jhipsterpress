package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.CactivityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Cactivity.
 */
public interface CactivityService {

    /**
     * Save a cactivity.
     *
     * @param cactivityDTO the entity to save
     * @return the persisted entity
     */
    CactivityDTO save(CactivityDTO cactivityDTO);

    /**
     * Get all the cactivities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CactivityDTO> findAll(Pageable pageable);

    /**
     * Get all the Cactivity with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<CactivityDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" cactivity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CactivityDTO> findOne(Long id);

    /**
     * Delete the "id" cactivity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
