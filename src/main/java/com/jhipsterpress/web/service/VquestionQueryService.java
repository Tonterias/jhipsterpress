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

import com.jhipsterpress.web.domain.Vquestion;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.VquestionRepository;
import com.jhipsterpress.web.service.dto.VquestionCriteria;
import com.jhipsterpress.web.service.dto.VquestionDTO;
import com.jhipsterpress.web.service.mapper.VquestionMapper;

/**
 * Service for executing complex queries for Vquestion entities in the database.
 * The main input is a {@link VquestionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VquestionDTO} or a {@link Page} of {@link VquestionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VquestionQueryService extends QueryService<Vquestion> {

    private final Logger log = LoggerFactory.getLogger(VquestionQueryService.class);

    private final VquestionRepository vquestionRepository;

    private final VquestionMapper vquestionMapper;

    public VquestionQueryService(VquestionRepository vquestionRepository, VquestionMapper vquestionMapper) {
        this.vquestionRepository = vquestionRepository;
        this.vquestionMapper = vquestionMapper;
    }

    /**
     * Return a {@link List} of {@link VquestionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VquestionDTO> findByCriteria(VquestionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Vquestion> specification = createSpecification(criteria);
        return vquestionMapper.toDto(vquestionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VquestionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VquestionDTO> findByCriteria(VquestionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Vquestion> specification = createSpecification(criteria);
        return vquestionRepository.findAll(specification, page)
            .map(vquestionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VquestionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Vquestion> specification = createSpecification(criteria);
        return vquestionRepository.count(specification);
    }

    /**
     * Function to convert VquestionCriteria to a {@link Specification}
     */
    private Specification<Vquestion> createSpecification(VquestionCriteria criteria) {
        Specification<Vquestion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Vquestion_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Vquestion_.creationDate));
            }
            if (criteria.getVquestion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVquestion(), Vquestion_.vquestion));
            }
            if (criteria.getVquestionDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVquestionDescription(), Vquestion_.vquestionDescription));
            }
            if (criteria.getVanswerId() != null) {
                specification = specification.and(buildSpecification(criteria.getVanswerId(),
                    root -> root.join(Vquestion_.vanswers, JoinType.LEFT).get(Vanswer_.id)));
            }
            if (criteria.getVthumbId() != null) {
                specification = specification.and(buildSpecification(criteria.getVthumbId(),
                    root -> root.join(Vquestion_.vthumbs, JoinType.LEFT).get(Vthumb_.id)));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(Vquestion_.user, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getVtopicId() != null) {
                specification = specification.and(buildSpecification(criteria.getVtopicId(),
                    root -> root.join(Vquestion_.vtopic, JoinType.LEFT).get(Vtopic_.id)));
            }
        }
        return specification;
    }
}
