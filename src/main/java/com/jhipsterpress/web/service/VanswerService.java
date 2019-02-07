package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.VanswerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Vanswer.
 */
public interface VanswerService {

    /**
     * Save a vanswer.
     *
     * @param vanswerDTO the entity to save
     * @return the persisted entity
     */
    VanswerDTO save(VanswerDTO vanswerDTO);

    /**
     * Get all the vanswers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VanswerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vanswer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VanswerDTO> findOne(Long id);

    /**
     * Delete the "id" vanswer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
