package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.CcelebDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Cceleb.
 */
public interface CcelebService {

    /**
     * Save a cceleb.
     *
     * @param ccelebDTO the entity to save
     * @return the persisted entity
     */
    CcelebDTO save(CcelebDTO ccelebDTO);

    /**
     * Get all the ccelebs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CcelebDTO> findAll(Pageable pageable);

    /**
     * Get all the Cceleb with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<CcelebDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" cceleb.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CcelebDTO> findOne(Long id);

    /**
     * Delete the "id" cceleb.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
