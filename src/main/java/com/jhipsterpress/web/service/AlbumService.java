package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.AlbumDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Album.
 */
public interface AlbumService {

    /**
     * Save a album.
     *
     * @param albumDTO the entity to save
     * @return the persisted entity
     */
    AlbumDTO save(AlbumDTO albumDTO);

    /**
     * Get all the albums.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AlbumDTO> findAll(Pageable pageable);


    /**
     * Get the "id" album.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AlbumDTO> findOne(Long id);

    /**
     * Delete the "id" album.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
