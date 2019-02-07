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

import com.jhipsterpress.web.domain.Urllink;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.UrllinkRepository;
import com.jhipsterpress.web.service.dto.UrllinkCriteria;
import com.jhipsterpress.web.service.dto.UrllinkDTO;
import com.jhipsterpress.web.service.mapper.UrllinkMapper;

/**
 * Service for executing complex queries for Urllink entities in the database.
 * The main input is a {@link UrllinkCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UrllinkDTO} or a {@link Page} of {@link UrllinkDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UrllinkQueryService extends QueryService<Urllink> {

    private final Logger log = LoggerFactory.getLogger(UrllinkQueryService.class);

    private final UrllinkRepository urllinkRepository;

    private final UrllinkMapper urllinkMapper;

    public UrllinkQueryService(UrllinkRepository urllinkRepository, UrllinkMapper urllinkMapper) {
        this.urllinkRepository = urllinkRepository;
        this.urllinkMapper = urllinkMapper;
    }

    /**
     * Return a {@link List} of {@link UrllinkDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UrllinkDTO> findByCriteria(UrllinkCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Urllink> specification = createSpecification(criteria);
        return urllinkMapper.toDto(urllinkRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UrllinkDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UrllinkDTO> findByCriteria(UrllinkCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Urllink> specification = createSpecification(criteria);
        return urllinkRepository.findAll(specification, page)
            .map(urllinkMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UrllinkCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Urllink> specification = createSpecification(criteria);
        return urllinkRepository.count(specification);
    }

    /**
     * Function to convert UrllinkCriteria to a {@link Specification}
     */
    private Specification<Urllink> createSpecification(UrllinkCriteria criteria) {
        Specification<Urllink> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Urllink_.id));
            }
            if (criteria.getLinkText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLinkText(), Urllink_.linkText));
            }
            if (criteria.getLinkURL() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLinkURL(), Urllink_.linkURL));
            }
        }
        return specification;
    }
}
