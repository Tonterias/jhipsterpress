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

import com.jhipsterpress.web.domain.Blockuser;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.BlockuserRepository;
import com.jhipsterpress.web.service.dto.BlockuserCriteria;
import com.jhipsterpress.web.service.dto.BlockuserDTO;
import com.jhipsterpress.web.service.mapper.BlockuserMapper;

/**
 * Service for executing complex queries for Blockuser entities in the database.
 * The main input is a {@link BlockuserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BlockuserDTO} or a {@link Page} of {@link BlockuserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BlockuserQueryService extends QueryService<Blockuser> {

    private final Logger log = LoggerFactory.getLogger(BlockuserQueryService.class);

    private final BlockuserRepository blockuserRepository;

    private final BlockuserMapper blockuserMapper;

    public BlockuserQueryService(BlockuserRepository blockuserRepository, BlockuserMapper blockuserMapper) {
        this.blockuserRepository = blockuserRepository;
        this.blockuserMapper = blockuserMapper;
    }

    /**
     * Return a {@link List} of {@link BlockuserDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BlockuserDTO> findByCriteria(BlockuserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Blockuser> specification = createSpecification(criteria);
        return blockuserMapper.toDto(blockuserRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BlockuserDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BlockuserDTO> findByCriteria(BlockuserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Blockuser> specification = createSpecification(criteria);
        return blockuserRepository.findAll(specification, page)
            .map(blockuserMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BlockuserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Blockuser> specification = createSpecification(criteria);
        return blockuserRepository.count(specification);
    }

    /**
     * Function to convert BlockuserCriteria to a {@link Specification}
     */
    private Specification<Blockuser> createSpecification(BlockuserCriteria criteria) {
        Specification<Blockuser> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Blockuser_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Blockuser_.creationDate));
            }
            if (criteria.getBlockeduserId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlockeduserId(),
                    root -> root.join(Blockuser_.blockeduser, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getBlockinguserId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlockinguserId(),
                    root -> root.join(Blockuser_.blockinguser, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getCblockeduserId() != null) {
                specification = specification.and(buildSpecification(criteria.getCblockeduserId(),
                    root -> root.join(Blockuser_.cblockeduser, JoinType.LEFT).get(Community_.id)));
            }
            if (criteria.getCblockinguserId() != null) {
                specification = specification.and(buildSpecification(criteria.getCblockinguserId(),
                    root -> root.join(Blockuser_.cblockinguser, JoinType.LEFT).get(Community_.id)));
            }
        }
        return specification;
    }
}
