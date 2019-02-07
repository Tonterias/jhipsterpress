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

import com.jhipsterpress.web.domain.ConfigVariables;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.ConfigVariablesRepository;
import com.jhipsterpress.web.service.dto.ConfigVariablesCriteria;
import com.jhipsterpress.web.service.dto.ConfigVariablesDTO;
import com.jhipsterpress.web.service.mapper.ConfigVariablesMapper;

/**
 * Service for executing complex queries for ConfigVariables entities in the database.
 * The main input is a {@link ConfigVariablesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ConfigVariablesDTO} or a {@link Page} of {@link ConfigVariablesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ConfigVariablesQueryService extends QueryService<ConfigVariables> {

    private final Logger log = LoggerFactory.getLogger(ConfigVariablesQueryService.class);

    private final ConfigVariablesRepository configVariablesRepository;

    private final ConfigVariablesMapper configVariablesMapper;

    public ConfigVariablesQueryService(ConfigVariablesRepository configVariablesRepository, ConfigVariablesMapper configVariablesMapper) {
        this.configVariablesRepository = configVariablesRepository;
        this.configVariablesMapper = configVariablesMapper;
    }

    /**
     * Return a {@link List} of {@link ConfigVariablesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ConfigVariablesDTO> findByCriteria(ConfigVariablesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ConfigVariables> specification = createSpecification(criteria);
        return configVariablesMapper.toDto(configVariablesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ConfigVariablesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ConfigVariablesDTO> findByCriteria(ConfigVariablesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ConfigVariables> specification = createSpecification(criteria);
        return configVariablesRepository.findAll(specification, page)
            .map(configVariablesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ConfigVariablesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ConfigVariables> specification = createSpecification(criteria);
        return configVariablesRepository.count(specification);
    }

    /**
     * Function to convert ConfigVariablesCriteria to a {@link Specification}
     */
    private Specification<ConfigVariables> createSpecification(ConfigVariablesCriteria criteria) {
        Specification<ConfigVariables> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ConfigVariables_.id));
            }
            if (criteria.getConfigVarLong1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong1(), ConfigVariables_.configVarLong1));
            }
            if (criteria.getConfigVarLong2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong2(), ConfigVariables_.configVarLong2));
            }
            if (criteria.getConfigVarLong3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong3(), ConfigVariables_.configVarLong3));
            }
            if (criteria.getConfigVarLong4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong4(), ConfigVariables_.configVarLong4));
            }
            if (criteria.getConfigVarLong5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong5(), ConfigVariables_.configVarLong5));
            }
            if (criteria.getConfigVarLong6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong6(), ConfigVariables_.configVarLong6));
            }
            if (criteria.getConfigVarLong7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong7(), ConfigVariables_.configVarLong7));
            }
            if (criteria.getConfigVarLong8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong8(), ConfigVariables_.configVarLong8));
            }
            if (criteria.getConfigVarLong9() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong9(), ConfigVariables_.configVarLong9));
            }
            if (criteria.getConfigVarLong10() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong10(), ConfigVariables_.configVarLong10));
            }
            if (criteria.getConfigVarLong11() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong11(), ConfigVariables_.configVarLong11));
            }
            if (criteria.getConfigVarLong12() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong12(), ConfigVariables_.configVarLong12));
            }
            if (criteria.getConfigVarLong13() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong13(), ConfigVariables_.configVarLong13));
            }
            if (criteria.getConfigVarLong14() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong14(), ConfigVariables_.configVarLong14));
            }
            if (criteria.getConfigVarLong15() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConfigVarLong15(), ConfigVariables_.configVarLong15));
            }
            if (criteria.getConfigVarBoolean16() != null) {
                specification = specification.and(buildSpecification(criteria.getConfigVarBoolean16(), ConfigVariables_.configVarBoolean16));
            }
            if (criteria.getConfigVarBoolean17() != null) {
                specification = specification.and(buildSpecification(criteria.getConfigVarBoolean17(), ConfigVariables_.configVarBoolean17));
            }
            if (criteria.getConfigVarBoolean18() != null) {
                specification = specification.and(buildSpecification(criteria.getConfigVarBoolean18(), ConfigVariables_.configVarBoolean18));
            }
            if (criteria.getConfigVarString19() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConfigVarString19(), ConfigVariables_.configVarString19));
            }
            if (criteria.getConfigVarString20() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConfigVarString20(), ConfigVariables_.configVarString20));
            }
        }
        return specification;
    }
}
