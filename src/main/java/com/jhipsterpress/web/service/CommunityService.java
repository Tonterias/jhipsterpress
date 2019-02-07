package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.CommunityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Community.
 */
public interface CommunityService {

    /**
     * Save a community.
     *
     * @param communityDTO the entity to save
     * @return the persisted entity
     */
    CommunityDTO save(CommunityDTO communityDTO);

    /**
     * Get all the communities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CommunityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" community.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CommunityDTO> findOne(Long id);

    /**
     * Delete the "id" community.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
