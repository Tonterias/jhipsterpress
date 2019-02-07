package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.UprofileDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Uprofile.
 */
public interface UprofileService {

    /**
     * Save a uprofile.
     *
     * @param uprofileDTO the entity to save
     * @return the persisted entity
     */
    UprofileDTO save(UprofileDTO uprofileDTO);

    /**
     * Get all the uprofiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UprofileDTO> findAll(Pageable pageable);


    /**
     * Get the "id" uprofile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UprofileDTO> findOne(Long id);

    /**
     * Delete the "id" uprofile.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
