package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.ActivityService;
import com.jhipsterpress.web.domain.Activity;
import com.jhipsterpress.web.repository.ActivityRepository;
import com.jhipsterpress.web.service.dto.ActivityDTO;
import com.jhipsterpress.web.service.mapper.ActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Activity.
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    private final Logger log = LoggerFactory.getLogger(ActivityServiceImpl.class);

    private final ActivityRepository activityRepository;

    private final ActivityMapper activityMapper;
    
//    private final ActivitySearchRepository activitySearchRepository;

//    public ActivityServiceImpl(ActivityRepository activityRepository, ActivityMapper activityMapper, ActivitySearchRepository activitySearchRepository) {
    public ActivityServiceImpl(ActivityRepository activityRepository, ActivityMapper activityMapper) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
//        this.activitySearchRepository = activitySearchRepository;
    }

    /**
     * Save a activity.
     *
     * @param activityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ActivityDTO save(ActivityDTO activityDTO) {
        log.debug("Request to save Activity : {}", activityDTO);
        Activity activity = activityMapper.toEntity(activityDTO);
        activity = activityRepository.save(activity);
        return activityMapper.toDto(activity);
    }

    /**
     * Get all the activities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActivityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Activities");
        return activityRepository.findAll(pageable)
            .map(activityMapper::toDto);
    }

    /**
     * Get all the Activity with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ActivityDTO> findAllWithEagerRelationships(Pageable pageable) {
        return activityRepository.findAllWithEagerRelationships(pageable).map(activityMapper::toDto);
    }
    

    /**
     * Get one activity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ActivityDTO> findOne(Long id) {
        log.debug("Request to get Activity : {}", id);
        return activityRepository.findOneWithEagerRelationships(id)
            .map(activityMapper::toDto);
    }

    /**
     * Delete the activity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Activity : {}", id);        activityRepository.deleteById(id);
    }
//
//
//    /**
//     * Search for the activity corresponding to the query.
//     *
//     * @param query the query of the search
//     * @param pageable the pagination information
//     * @return the list of entities
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public Page<ActivityDTO> search(String query, Pageable pageable) {
//        log.debug("Request to search for a page of Activities for query {}", query);
////        return activitySearchRepository.search(queryStringQuery(query), pageable)
////            .map(activityMapper::toDto);
//        return activityRepository.findAllWithEagerRelationships(queryStringQuery(query), pageable)
//                .map(activityMapper::toDto);
//    }
}
