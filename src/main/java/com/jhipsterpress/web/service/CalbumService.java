package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.CalbumDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Calbum.
 */
public interface CalbumService {

    /**
     * Save a calbum.
     *
     * @param calbumDTO the entity to save
     * @return the persisted entity
     */
    CalbumDTO save(CalbumDTO calbumDTO);

    /**
     * Get all the calbums.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CalbumDTO> findAll(Pageable pageable);


    /**
     * Get the "id" calbum.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CalbumDTO> findOne(Long id);

    /**
     * Delete the "id" calbum.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
