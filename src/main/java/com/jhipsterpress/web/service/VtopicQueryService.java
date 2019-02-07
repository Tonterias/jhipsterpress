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

import com.jhipsterpress.web.domain.Vtopic;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.VtopicRepository;
import com.jhipsterpress.web.service.dto.VtopicCriteria;
import com.jhipsterpress.web.service.dto.VtopicDTO;
import com.jhipsterpress.web.service.mapper.VtopicMapper;

/**
 * Service for executing complex queries for Vtopic entities in the database.
 * The main input is a {@link VtopicCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VtopicDTO} or a {@link Page} of {@link VtopicDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VtopicQueryService extends QueryService<Vtopic> {

    private final Logger log = LoggerFactory.getLogger(VtopicQueryService.class);

    private final VtopicRepository vtopicRepository;

    private final VtopicMapper vtopicMapper;

    public VtopicQueryService(VtopicRepository vtopicRepository, VtopicMapper vtopicMapper) {
        this.vtopicRepository = vtopicRepository;
        this.vtopicMapper = vtopicMapper;
    }

    /**
     * Return a {@link List} of {@link VtopicDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VtopicDTO> findByCriteria(VtopicCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Vtopic> specification = createSpecification(criteria);
        return vtopicMapper.toDto(vtopicRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VtopicDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VtopicDTO> findByCriteria(VtopicCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Vtopic> specification = createSpecification(criteria);
        return vtopicRepository.findAll(specification, page)
            .map(vtopicMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VtopicCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Vtopic> specification = createSpecification(criteria);
        return vtopicRepository.count(specification);
    }

    /**
     * Function to convert VtopicCriteria to a {@link Specification}
     */
    private Specification<Vtopic> createSpecification(VtopicCriteria criteria) {
        Specification<Vtopic> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Vtopic_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Vtopic_.creationDate));
            }
            if (criteria.getVtopicTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVtopicTitle(), Vtopic_.vtopicTitle));
            }
            if (criteria.getVtopicDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVtopicDescription(), Vtopic_.vtopicDescription));
            }
            if (criteria.getVquestionId() != null) {
                specification = specification.and(buildSpecification(criteria.getVquestionId(),
                    root -> root.join(Vtopic_.vquestions, JoinType.LEFT).get(Vquestion_.id)));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(Vtopic_.user, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
