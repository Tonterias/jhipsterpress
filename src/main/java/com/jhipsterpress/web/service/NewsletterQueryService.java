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

import com.jhipsterpress.web.domain.Newsletter;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.NewsletterRepository;
import com.jhipsterpress.web.service.dto.NewsletterCriteria;
import com.jhipsterpress.web.service.dto.NewsletterDTO;
import com.jhipsterpress.web.service.mapper.NewsletterMapper;

/**
 * Service for executing complex queries for Newsletter entities in the database.
 * The main input is a {@link NewsletterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NewsletterDTO} or a {@link Page} of {@link NewsletterDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NewsletterQueryService extends QueryService<Newsletter> {

    private final Logger log = LoggerFactory.getLogger(NewsletterQueryService.class);

    private final NewsletterRepository newsletterRepository;

    private final NewsletterMapper newsletterMapper;

    public NewsletterQueryService(NewsletterRepository newsletterRepository, NewsletterMapper newsletterMapper) {
        this.newsletterRepository = newsletterRepository;
        this.newsletterMapper = newsletterMapper;
    }

    /**
     * Return a {@link List} of {@link NewsletterDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NewsletterDTO> findByCriteria(NewsletterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Newsletter> specification = createSpecification(criteria);
        return newsletterMapper.toDto(newsletterRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NewsletterDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NewsletterDTO> findByCriteria(NewsletterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Newsletter> specification = createSpecification(criteria);
        return newsletterRepository.findAll(specification, page)
            .map(newsletterMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NewsletterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Newsletter> specification = createSpecification(criteria);
        return newsletterRepository.count(specification);
    }

    /**
     * Function to convert NewsletterCriteria to a {@link Specification}
     */
    private Specification<Newsletter> createSpecification(NewsletterCriteria criteria) {
        Specification<Newsletter> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Newsletter_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Newsletter_.creationDate));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Newsletter_.email));
            }
        }
        return specification;
    }
}
