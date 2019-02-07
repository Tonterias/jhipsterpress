package com.jhipsterpress.web.service;

import com.jhipsterpress.web.service.dto.TopicDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Topic.
 */
public interface TopicService {

    /**
     * Save a topic.
     *
     * @param topicDTO the entity to save
     * @return the persisted entity
     */
    TopicDTO save(TopicDTO topicDTO);

    /**
     * Get all the topics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TopicDTO> findAll(Pageable pageable);

    /**
     * Get all the Topic with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<TopicDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" topic.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TopicDTO> findOne(Long id);

    /**
     * Delete the "id" topic.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
