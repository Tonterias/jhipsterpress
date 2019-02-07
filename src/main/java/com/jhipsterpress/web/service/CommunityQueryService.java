package com.jhipsterpress.web.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.jhipsterpress.web.domain.Community;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.CommunityRepository;
import com.jhipsterpress.web.service.dto.CommunityCriteria;
import com.jhipsterpress.web.service.dto.CommunityDTO;
import com.jhipsterpress.web.service.mapper.CommunityMapper;

/**
 * Service for executing complex queries for Community entities in the database.
 * The main input is a {@link CommunityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CommunityDTO} or a {@link Page} of {@link CommunityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CommunityQueryService extends QueryService<Community> {

    private final Logger log = LoggerFactory.getLogger(CommunityQueryService.class);

    private final CommunityRepository communityRepository;

    private final CommunityMapper communityMapper;

    public CommunityQueryService(CommunityRepository communityRepository, CommunityMapper communityMapper) {
        this.communityRepository = communityRepository;
        this.communityMapper = communityMapper;
    }

    /**
     * Return a {@link List} of {@link CommunityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CommunityDTO> findByCriteria(CommunityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Community> specification = createSpecification(criteria);
        return communityMapper.toDto(communityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CommunityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommunityDTO> findByCriteria(CommunityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Community> specification = createSpecification(criteria);
        return communityRepository.findAll(specification, page)
            .map(communityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CommunityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Community> specification = createSpecification(criteria);
        return communityRepository.count(specification);
    }

    /**
     * Function to convert CommunityCriteria to a {@link Specification}
     */
    private Specification<Community> createSpecification(CommunityCriteria criteria) {
        Specification<Community> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Community_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Community_.creationDate));
            }
            if (criteria.getCommunityName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCommunityName(), Community_.communityName));
            }
            if (criteria.getCommunityDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCommunityDescription(), Community_.communityDescription));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Community_.isActive));
            }
            if (criteria.getBlogId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlogId(),
                    root -> root.join(Community_.blogs, JoinType.LEFT).get(Blog_.id)));
            }
            if (criteria.getCsenderId() != null) {
                specification = specification.and(buildSpecification(criteria.getCsenderId(),
                    root -> root.join(Community_.csenders, JoinType.LEFT).get(Cmessage_.id)));
            }
            if (criteria.getCreceiverId() != null) {
                specification = specification.and(buildSpecification(criteria.getCreceiverId(),
                    root -> root.join(Community_.creceivers, JoinType.LEFT).get(Cmessage_.id)));
            }
            if (criteria.getCfollowedId() != null) {
                specification = specification.and(buildSpecification(criteria.getCfollowedId(),
                    root -> root.join(Community_.cfolloweds, JoinType.LEFT).get(Follow_.id)));
            }
            if (criteria.getCfollowingId() != null) {
                specification = specification.and(buildSpecification(criteria.getCfollowingId(),
                    root -> root.join(Community_.cfollowings, JoinType.LEFT).get(Follow_.id)));
            }
            if (criteria.getCblockeduserId() != null) {
                specification = specification.and(buildSpecification(criteria.getCblockeduserId(),
                    root -> root.join(Community_.cblockedusers, JoinType.LEFT).get(Blockuser_.id)));
            }
            if (criteria.getCblockinguserId() != null) {
                specification = specification.and(buildSpecification(criteria.getCblockinguserId(),
                    root -> root.join(Community_.cblockingusers, JoinType.LEFT).get(Blockuser_.id)));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(Community_.user, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getCalbumId() != null) {
                specification = specification.and(buildSpecification(criteria.getCalbumId(),
                    root -> root.join(Community_.calbums, JoinType.LEFT).get(Calbum_.id)));
            }
            if (criteria.getCinterestId() != null) {
                specification = specification.and(buildSpecification(criteria.getCinterestId(),
                    root -> root.join(Community_.cinterests, JoinType.LEFT).get(Cinterest_.id)));
            }
            if (criteria.getCactivityId() != null) {
                specification = specification.and(buildSpecification(criteria.getCactivityId(),
                    root -> root.join(Community_.cactivities, JoinType.LEFT).get(Cactivity_.id)));
            }
            if (criteria.getCcelebId() != null) {
                specification = specification.and(buildSpecification(criteria.getCcelebId(),
                    root -> root.join(Community_.ccelebs, JoinType.LEFT).get(Cceleb_.id)));
            }
        }
        return specification;
    }
}
