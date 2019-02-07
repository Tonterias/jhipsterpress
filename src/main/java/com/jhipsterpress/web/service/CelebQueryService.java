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

import com.jhipsterpress.web.domain.Celeb;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.CelebRepository;
import com.jhipsterpress.web.service.dto.CelebCriteria;
import com.jhipsterpress.web.service.dto.CelebDTO;
import com.jhipsterpress.web.service.mapper.CelebMapper;

/**
 * Service for executing complex queries for Celeb entities in the database.
 * The main input is a {@link CelebCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CelebDTO} or a {@link Page} of {@link CelebDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CelebQueryService extends QueryService<Celeb> {

    private final Logger log = LoggerFactory.getLogger(CelebQueryService.class);

    private final CelebRepository celebRepository;

    private final CelebMapper celebMapper;

    public CelebQueryService(CelebRepository celebRepository, CelebMapper celebMapper) {
        this.celebRepository = celebRepository;
        this.celebMapper = celebMapper;
    }

    /**
     * Return a {@link List} of {@link CelebDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CelebDTO> findByCriteria(CelebCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Celeb> specification = createSpecification(criteria);
        return celebMapper.toDto(celebRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CelebDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CelebDTO> findByCriteria(CelebCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Celeb> specification = createSpecification(criteria);
        return celebRepository.findAll(specification, page)
            .map(celebMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CelebCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Celeb> specification = createSpecification(criteria);
        return celebRepository.count(specification);
    }

    /**
     * Function to convert CelebCriteria to a {@link Specification}
     */
    private Specification<Celeb> createSpecification(CelebCriteria criteria) {
        Specification<Celeb> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Celeb_.id));
            }
            if (criteria.getCelebName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCelebName(), Celeb_.celebName));
            }
            if (criteria.getUprofileId() != null) {
                specification = specification.and(buildSpecification(criteria.getUprofileId(),
                    root -> root.join(Celeb_.uprofiles, JoinType.LEFT).get(Uprofile_.id)));
            }
        }
        return specification;
    }
}
