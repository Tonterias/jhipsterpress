package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.BlockuserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Blockuser.
 */
public interface BlockuserService {

    /**
     * Save a blockuser.
     *
     * @param blockuserDTO the entity to save
     * @return the persisted entity
     */
    BlockuserDTO save(BlockuserDTO blockuserDTO);

    /**
     * Get all the blockusers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BlockuserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" blockuser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BlockuserDTO> findOne(Long id);

    /**
     * Delete the "id" blockuser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
