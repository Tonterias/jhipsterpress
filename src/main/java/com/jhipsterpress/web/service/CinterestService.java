package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.CinterestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Cinterest.
 */
public interface CinterestService {

    /**
     * Save a cinterest.
     *
     * @param cinterestDTO the entity to save
     * @return the persisted entity
     */
    CinterestDTO save(CinterestDTO cinterestDTO);

    /**
     * Get all the cinterests.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CinterestDTO> findAll(Pageable pageable);

    /**
     * Get all the Cinterest with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<CinterestDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" cinterest.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CinterestDTO> findOne(Long id);

    /**
     * Delete the "id" cinterest.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
