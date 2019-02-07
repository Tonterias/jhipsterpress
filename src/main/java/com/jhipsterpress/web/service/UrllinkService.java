package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.UrllinkDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Urllink.
 */
public interface UrllinkService {

    /**
     * Save a urllink.
     *
     * @param urllinkDTO the entity to save
     * @return the persisted entity
     */
    UrllinkDTO save(UrllinkDTO urllinkDTO);

    /**
     * Get all the urllinks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UrllinkDTO> findAll(Pageable pageable);


    /**
     * Get the "id" urllink.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UrllinkDTO> findOne(Long id);

    /**
     * Delete the "id" urllink.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
