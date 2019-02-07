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

import com.jhipsterpress.web.domain.Uprofile;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.UprofileRepository;
import com.jhipsterpress.web.service.dto.UprofileCriteria;
import com.jhipsterpress.web.service.dto.UprofileDTO;
import com.jhipsterpress.web.service.mapper.UprofileMapper;

/**
 * Service for executing complex queries for Uprofile entities in the database.
 * The main input is a {@link UprofileCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UprofileDTO} or a {@link Page} of {@link UprofileDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UprofileQueryService extends QueryService<Uprofile> {

    private final Logger log = LoggerFactory.getLogger(UprofileQueryService.class);

    private final UprofileRepository uprofileRepository;

    private final UprofileMapper uprofileMapper;

    public UprofileQueryService(UprofileRepository uprofileRepository, UprofileMapper uprofileMapper) {
        this.uprofileRepository = uprofileRepository;
        this.uprofileMapper = uprofileMapper;
    }

    /**
     * Return a {@link List} of {@link UprofileDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UprofileDTO> findByCriteria(UprofileCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Uprofile> specification = createSpecification(criteria);
        return uprofileMapper.toDto(uprofileRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UprofileDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UprofileDTO> findByCriteria(UprofileCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Uprofile> specification = createSpecification(criteria);
        return uprofileRepository.findAll(specification, page)
            .map(uprofileMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UprofileCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Uprofile> specification = createSpecification(criteria);
        return uprofileRepository.count(specification);
    }

    /**
     * Function to convert UprofileCriteria to a {@link Specification}
     */
    private Specification<Uprofile> createSpecification(UprofileCriteria criteria) {
        Specification<Uprofile> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Uprofile_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Uprofile_.creationDate));
            }
            if (criteria.getGender() != null) {
                specification = specification.and(buildSpecification(criteria.getGender(), Uprofile_.gender));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), Uprofile_.phone));
            }
            if (criteria.getBio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBio(), Uprofile_.bio));
            }
            if (criteria.getFacebook() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFacebook(), Uprofile_.facebook));
            }
            if (criteria.getTwitter() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTwitter(), Uprofile_.twitter));
            }
            if (criteria.getLinkedin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLinkedin(), Uprofile_.linkedin));
            }
            if (criteria.getInstagram() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInstagram(), Uprofile_.instagram));
            }
            if (criteria.getGooglePlus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGooglePlus(), Uprofile_.googlePlus));
            }
            if (criteria.getBirthdate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthdate(), Uprofile_.birthdate));
            }
            if (criteria.getCivilStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getCivilStatus(), Uprofile_.civilStatus));
            }
            if (criteria.getLookingFor() != null) {
                specification = specification.and(buildSpecification(criteria.getLookingFor(), Uprofile_.lookingFor));
            }
            if (criteria.getPurpose() != null) {
                specification = specification.and(buildSpecification(criteria.getPurpose(), Uprofile_.purpose));
            }
            if (criteria.getPhysical() != null) {
                specification = specification.and(buildSpecification(criteria.getPhysical(), Uprofile_.physical));
            }
            if (criteria.getReligion() != null) {
                specification = specification.and(buildSpecification(criteria.getReligion(), Uprofile_.religion));
            }
            if (criteria.getEthnicGroup() != null) {
                specification = specification.and(buildSpecification(criteria.getEthnicGroup(), Uprofile_.ethnicGroup));
            }
            if (criteria.getStudies() != null) {
                specification = specification.and(buildSpecification(criteria.getStudies(), Uprofile_.studies));
            }
            if (criteria.getSibblings() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSibblings(), Uprofile_.sibblings));
            }
            if (criteria.getEyes() != null) {
                specification = specification.and(buildSpecification(criteria.getEyes(), Uprofile_.eyes));
            }
            if (criteria.getSmoker() != null) {
                specification = specification.and(buildSpecification(criteria.getSmoker(), Uprofile_.smoker));
            }
            if (criteria.getChildren() != null) {
                specification = specification.and(buildSpecification(criteria.getChildren(), Uprofile_.children));
            }
            if (criteria.getFutureChildren() != null) {
                specification = specification.and(buildSpecification(criteria.getFutureChildren(), Uprofile_.futureChildren));
            }
            if (criteria.getPet() != null) {
                specification = specification.and(buildSpecification(criteria.getPet(), Uprofile_.pet));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(Uprofile_.user, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getInterestId() != null) {
                specification = specification.and(buildSpecification(criteria.getInterestId(),
                    root -> root.join(Uprofile_.interests, JoinType.LEFT).get(Interest_.id)));
            }
            if (criteria.getActivityId() != null) {
                specification = specification.and(buildSpecification(criteria.getActivityId(),
                    root -> root.join(Uprofile_.activities, JoinType.LEFT).get(Activity_.id)));
            }
            if (criteria.getCelebId() != null) {
                specification = specification.and(buildSpecification(criteria.getCelebId(),
                    root -> root.join(Uprofile_.celebs, JoinType.LEFT).get(Celeb_.id)));
            }
        }
        return specification;
    }
}
