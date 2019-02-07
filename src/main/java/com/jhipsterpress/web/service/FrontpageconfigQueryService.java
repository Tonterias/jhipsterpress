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

import com.jhipsterpress.web.domain.Frontpageconfig;
import com.jhipsterpress.web.domain.*; // for static metamodels
import com.jhipsterpress.web.repository.FrontpageconfigRepository;
import com.jhipsterpress.web.service.dto.FrontpageconfigCriteria;
import com.jhipsterpress.web.service.dto.FrontpageconfigDTO;
import com.jhipsterpress.web.service.mapper.FrontpageconfigMapper;

/**
 * Service for executing complex queries for Frontpageconfig entities in the database.
 * The main input is a {@link FrontpageconfigCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FrontpageconfigDTO} or a {@link Page} of {@link FrontpageconfigDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FrontpageconfigQueryService extends QueryService<Frontpageconfig> {

    private final Logger log = LoggerFactory.getLogger(FrontpageconfigQueryService.class);

    private final FrontpageconfigRepository frontpageconfigRepository;

    private final FrontpageconfigMapper frontpageconfigMapper;

    public FrontpageconfigQueryService(FrontpageconfigRepository frontpageconfigRepository, FrontpageconfigMapper frontpageconfigMapper) {
        this.frontpageconfigRepository = frontpageconfigRepository;
        this.frontpageconfigMapper = frontpageconfigMapper;
    }

    /**
     * Return a {@link List} of {@link FrontpageconfigDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FrontpageconfigDTO> findByCriteria(FrontpageconfigCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Frontpageconfig> specification = createSpecification(criteria);
        return frontpageconfigMapper.toDto(frontpageconfigRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FrontpageconfigDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FrontpageconfigDTO> findByCriteria(FrontpageconfigCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Frontpageconfig> specification = createSpecification(criteria);
        return frontpageconfigRepository.findAll(specification, page)
            .map(frontpageconfigMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FrontpageconfigCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Frontpageconfig> specification = createSpecification(criteria);
        return frontpageconfigRepository.count(specification);
    }

    /**
     * Function to convert FrontpageconfigCriteria to a {@link Specification}
     */
    private Specification<Frontpageconfig> createSpecification(FrontpageconfigCriteria criteria) {
        Specification<Frontpageconfig> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Frontpageconfig_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Frontpageconfig_.creationDate));
            }
            if (criteria.getTopNews1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTopNews1(), Frontpageconfig_.topNews1));
            }
            if (criteria.getTopNews2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTopNews2(), Frontpageconfig_.topNews2));
            }
            if (criteria.getTopNews3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTopNews3(), Frontpageconfig_.topNews3));
            }
            if (criteria.getTopNews4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTopNews4(), Frontpageconfig_.topNews4));
            }
            if (criteria.getTopNews5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTopNews5(), Frontpageconfig_.topNews5));
            }
            if (criteria.getLatestNews1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatestNews1(), Frontpageconfig_.latestNews1));
            }
            if (criteria.getLatestNews2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatestNews2(), Frontpageconfig_.latestNews2));
            }
            if (criteria.getLatestNews3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatestNews3(), Frontpageconfig_.latestNews3));
            }
            if (criteria.getLatestNews4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatestNews4(), Frontpageconfig_.latestNews4));
            }
            if (criteria.getLatestNews5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatestNews5(), Frontpageconfig_.latestNews5));
            }
            if (criteria.getBreakingNews1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBreakingNews1(), Frontpageconfig_.breakingNews1));
            }
            if (criteria.getRecentPosts1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecentPosts1(), Frontpageconfig_.recentPosts1));
            }
            if (criteria.getRecentPosts2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecentPosts2(), Frontpageconfig_.recentPosts2));
            }
            if (criteria.getRecentPosts3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecentPosts3(), Frontpageconfig_.recentPosts3));
            }
            if (criteria.getRecentPosts4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecentPosts4(), Frontpageconfig_.recentPosts4));
            }
            if (criteria.getFeaturedArticles1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeaturedArticles1(), Frontpageconfig_.featuredArticles1));
            }
            if (criteria.getFeaturedArticles2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeaturedArticles2(), Frontpageconfig_.featuredArticles2));
            }
            if (criteria.getFeaturedArticles3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeaturedArticles3(), Frontpageconfig_.featuredArticles3));
            }
            if (criteria.getFeaturedArticles4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeaturedArticles4(), Frontpageconfig_.featuredArticles4));
            }
            if (criteria.getFeaturedArticles5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeaturedArticles5(), Frontpageconfig_.featuredArticles5));
            }
            if (criteria.getFeaturedArticles6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeaturedArticles6(), Frontpageconfig_.featuredArticles6));
            }
            if (criteria.getFeaturedArticles7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeaturedArticles7(), Frontpageconfig_.featuredArticles7));
            }
            if (criteria.getFeaturedArticles8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeaturedArticles8(), Frontpageconfig_.featuredArticles8));
            }
            if (criteria.getFeaturedArticles9() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeaturedArticles9(), Frontpageconfig_.featuredArticles9));
            }
            if (criteria.getFeaturedArticles10() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeaturedArticles10(), Frontpageconfig_.featuredArticles10));
            }
            if (criteria.getPopularNews1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPopularNews1(), Frontpageconfig_.popularNews1));
            }
            if (criteria.getPopularNews2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPopularNews2(), Frontpageconfig_.popularNews2));
            }
            if (criteria.getPopularNews3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPopularNews3(), Frontpageconfig_.popularNews3));
            }
            if (criteria.getPopularNews4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPopularNews4(), Frontpageconfig_.popularNews4));
            }
            if (criteria.getPopularNews5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPopularNews5(), Frontpageconfig_.popularNews5));
            }
            if (criteria.getPopularNews6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPopularNews6(), Frontpageconfig_.popularNews6));
            }
            if (criteria.getPopularNews7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPopularNews7(), Frontpageconfig_.popularNews7));
            }
            if (criteria.getPopularNews8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPopularNews8(), Frontpageconfig_.popularNews8));
            }
            if (criteria.getWeeklyNews1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeeklyNews1(), Frontpageconfig_.weeklyNews1));
            }
            if (criteria.getWeeklyNews2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeeklyNews2(), Frontpageconfig_.weeklyNews2));
            }
            if (criteria.getWeeklyNews3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeeklyNews3(), Frontpageconfig_.weeklyNews3));
            }
            if (criteria.getWeeklyNews4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeeklyNews4(), Frontpageconfig_.weeklyNews4));
            }
            if (criteria.getNewsFeeds1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewsFeeds1(), Frontpageconfig_.newsFeeds1));
            }
            if (criteria.getNewsFeeds2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewsFeeds2(), Frontpageconfig_.newsFeeds2));
            }
            if (criteria.getNewsFeeds3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewsFeeds3(), Frontpageconfig_.newsFeeds3));
            }
            if (criteria.getNewsFeeds4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewsFeeds4(), Frontpageconfig_.newsFeeds4));
            }
            if (criteria.getNewsFeeds5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewsFeeds5(), Frontpageconfig_.newsFeeds5));
            }
            if (criteria.getNewsFeeds6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewsFeeds6(), Frontpageconfig_.newsFeeds6));
            }
            if (criteria.getUsefulLinks1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUsefulLinks1(), Frontpageconfig_.usefulLinks1));
            }
            if (criteria.getUsefulLinks2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUsefulLinks2(), Frontpageconfig_.usefulLinks2));
            }
            if (criteria.getUsefulLinks3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUsefulLinks3(), Frontpageconfig_.usefulLinks3));
            }
            if (criteria.getUsefulLinks4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUsefulLinks4(), Frontpageconfig_.usefulLinks4));
            }
            if (criteria.getUsefulLinks5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUsefulLinks5(), Frontpageconfig_.usefulLinks5));
            }
            if (criteria.getUsefulLinks6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUsefulLinks6(), Frontpageconfig_.usefulLinks6));
            }
            if (criteria.getRecentVideos1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecentVideos1(), Frontpageconfig_.recentVideos1));
            }
            if (criteria.getRecentVideos2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecentVideos2(), Frontpageconfig_.recentVideos2));
            }
            if (criteria.getRecentVideos3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecentVideos3(), Frontpageconfig_.recentVideos3));
            }
            if (criteria.getRecentVideos4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecentVideos4(), Frontpageconfig_.recentVideos4));
            }
            if (criteria.getRecentVideos5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecentVideos5(), Frontpageconfig_.recentVideos5));
            }
            if (criteria.getRecentVideos6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecentVideos6(), Frontpageconfig_.recentVideos6));
            }
        }
        return specification;
    }
}
