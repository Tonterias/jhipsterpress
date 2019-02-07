package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.CelebDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Celeb.
 */
public interface CelebService {

    /**
     * Save a celeb.
     *
     * @param celebDTO the entity to save
     * @return the persisted entity
     */
    CelebDTO save(CelebDTO celebDTO);

    /**
     * Get all the celebs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CelebDTO> findAll(Pageable pageable);

    /**
     * Get all the Celeb with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<CelebDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" celeb.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CelebDTO> findOne(Long id);

    /**
     * Delete the "id" celeb.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
