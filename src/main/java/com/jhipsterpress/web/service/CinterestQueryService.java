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

import com.jhipsterpress.web.domain.Cinterest;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.CinterestRepository;
import com.jhipsterpress.web.service.dto.CinterestCriteria;
import com.jhipsterpress.web.service.dto.CinterestDTO;
import com.jhipsterpress.web.service.mapper.CinterestMapper;

/**
 * Service for executing complex queries for Cinterest entities in the database.
 * The main input is a {@link CinterestCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CinterestDTO} or a {@link Page} of {@link CinterestDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CinterestQueryService extends QueryService<Cinterest> {

    private final Logger log = LoggerFactory.getLogger(CinterestQueryService.class);

    private final CinterestRepository cinterestRepository;

    private final CinterestMapper cinterestMapper;

    public CinterestQueryService(CinterestRepository cinterestRepository, CinterestMapper cinterestMapper) {
        this.cinterestRepository = cinterestRepository;
        this.cinterestMapper = cinterestMapper;
    }

    /**
     * Return a {@link List} of {@link CinterestDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CinterestDTO> findByCriteria(CinterestCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Cinterest> specification = createSpecification(criteria);
        return cinterestMapper.toDto(cinterestRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CinterestDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CinterestDTO> findByCriteria(CinterestCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Cinterest> specification = createSpecification(criteria);
        return cinterestRepository.findAll(specification, page)
            .map(cinterestMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CinterestCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Cinterest> specification = createSpecification(criteria);
        return cinterestRepository.count(specification);
    }

    /**
     * Function to convert CinterestCriteria to a {@link Specification}
     */
    private Specification<Cinterest> createSpecification(CinterestCriteria criteria) {
        Specification<Cinterest> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Cinterest_.id));
            }
            if (criteria.getInterestName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInterestName(), Cinterest_.interestName));
            }
            if (criteria.getCommunityId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommunityId(),
                    root -> root.join(Cinterest_.communities, JoinType.LEFT).get(Community_.id)));
            }
        }
        return specification;
    }
}
