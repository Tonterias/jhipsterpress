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

import com.jhipsterpress.web.domain.Cmessage;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.CmessageRepository;
import com.jhipsterpress.web.service.dto.CmessageCriteria;
import com.jhipsterpress.web.service.dto.CmessageDTO;
import com.jhipsterpress.web.service.mapper.CmessageMapper;

/**
 * Service for executing complex queries for Cmessage entities in the database.
 * The main input is a {@link CmessageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CmessageDTO} or a {@link Page} of {@link CmessageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CmessageQueryService extends QueryService<Cmessage> {

    private final Logger log = LoggerFactory.getLogger(CmessageQueryService.class);

    private final CmessageRepository cmessageRepository;

    private final CmessageMapper cmessageMapper;

    public CmessageQueryService(CmessageRepository cmessageRepository, CmessageMapper cmessageMapper) {
        this.cmessageRepository = cmessageRepository;
        this.cmessageMapper = cmessageMapper;
    }

    /**
     * Return a {@link List} of {@link CmessageDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CmessageDTO> findByCriteria(CmessageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Cmessage> specification = createSpecification(criteria);
        return cmessageMapper.toDto(cmessageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CmessageDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CmessageDTO> findByCriteria(CmessageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Cmessage> specification = createSpecification(criteria);
        return cmessageRepository.findAll(specification, page)
            .map(cmessageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CmessageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Cmessage> specification = createSpecification(criteria);
        return cmessageRepository.count(specification);
    }

    /**
     * Function to convert CmessageCriteria to a {@link Specification}
     */
    private Specification<Cmessage> createSpecification(CmessageCriteria criteria) {
        Specification<Cmessage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Cmessage_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Cmessage_.creationDate));
            }
            if (criteria.getMessageText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessageText(), Cmessage_.messageText));
            }
            if (criteria.getIsDelivered() != null) {
                specification = specification.and(buildSpecification(criteria.getIsDelivered(), Cmessage_.isDelivered));
            }
            if (criteria.getCsenderId() != null) {
                specification = specification.and(buildSpecification(criteria.getCsenderId(),
                    root -> root.join(Cmessage_.csender, JoinType.LEFT).get(Community_.id)));
            }
            if (criteria.getCreceiverId() != null) {
                specification = specification.and(buildSpecification(criteria.getCreceiverId(),
                    root -> root.join(Cmessage_.creceiver, JoinType.LEFT).get(Community_.id)));
            }
        }
        return specification;
    }
}
