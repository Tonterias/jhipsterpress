package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.ConfigVariablesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ConfigVariables.
 */
public interface ConfigVariablesService {

    /**
     * Save a configVariables.
     *
     * @param configVariablesDTO the entity to save
     * @return the persisted entity
     */
    ConfigVariablesDTO save(ConfigVariablesDTO configVariablesDTO);

    /**
     * Get all the configVariables.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConfigVariablesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" configVariables.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConfigVariablesDTO> findOne(Long id);

    /**
     * Delete the "id" configVariables.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
