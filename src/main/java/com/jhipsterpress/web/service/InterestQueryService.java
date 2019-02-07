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

import com.jhipsterpress.web.domain.Interest;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.InterestRepository;
import com.jhipsterpress.web.service.dto.InterestCriteria;
import com.jhipsterpress.web.service.dto.InterestDTO;
import com.jhipsterpress.web.service.mapper.InterestMapper;

/**
 * Service for executing complex queries for Interest entities in the database.
 * The main input is a {@link InterestCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InterestDTO} or a {@link Page} of {@link InterestDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InterestQueryService extends QueryService<Interest> {

    private final Logger log = LoggerFactory.getLogger(InterestQueryService.class);

    private final InterestRepository interestRepository;

    private final InterestMapper interestMapper;

    public InterestQueryService(InterestRepository interestRepository, InterestMapper interestMapper) {
        this.interestRepository = interestRepository;
        this.interestMapper = interestMapper;
    }

    /**
     * Return a {@link List} of {@link InterestDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InterestDTO> findByCriteria(InterestCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Interest> specification = createSpecification(criteria);
        return interestMapper.toDto(interestRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InterestDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InterestDTO> findByCriteria(InterestCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Interest> specification = createSpecification(criteria);
        return interestRepository.findAll(specification, page)
            .map(interestMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InterestCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Interest> specification = createSpecification(criteria);
        return interestRepository.count(specification);
    }

    /**
     * Function to convert InterestCriteria to a {@link Specification}
     */
    private Specification<Interest> createSpecification(InterestCriteria criteria) {
        Specification<Interest> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Interest_.id));
            }
            if (criteria.getInterestName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInterestName(), Interest_.interestName));
            }
            if (criteria.getUprofileId() != null) {
                specification = specification.and(buildSpecification(criteria.getUprofileId(),
                    root -> root.join(Interest_.uprofiles, JoinType.LEFT).get(Uprofile_.id)));
            }
        }
        return specification;
    }
}
