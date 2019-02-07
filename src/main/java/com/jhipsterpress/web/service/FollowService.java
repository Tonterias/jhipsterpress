package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.FollowDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Follow.
 */
public interface FollowService {

    /**
     * Save a follow.
     *
     * @param followDTO the entity to save
     * @return the persisted entity
     */
    FollowDTO save(FollowDTO followDTO);

    /**
     * Get all the follows.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FollowDTO> findAll(Pageable pageable);


    /**
     * Get the "id" follow.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FollowDTO> findOne(Long id);

    /**
     * Delete the "id" follow.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
