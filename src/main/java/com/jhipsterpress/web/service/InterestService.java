package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.InterestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Interest.
 */
public interface InterestService {

    /**
     * Save a interest.
     *
     * @param interestDTO the entity to save
     * @return the persisted entity
     */
    InterestDTO save(InterestDTO interestDTO);

    /**
     * Get all the interests.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<InterestDTO> findAll(Pageable pageable);

    /**
     * Get all the Interest with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<InterestDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" interest.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InterestDTO> findOne(Long id);

    /**
     * Delete the "id" interest.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
