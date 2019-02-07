package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.CmessageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Cmessage.
 */
public interface CmessageService {

    /**
     * Save a cmessage.
     *
     * @param cmessageDTO the entity to save
     * @return the persisted entity
     */
    CmessageDTO save(CmessageDTO cmessageDTO);

    /**
     * Get all the cmessages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CmessageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" cmessage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CmessageDTO> findOne(Long id);

    /**
     * Delete the "id" cmessage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
