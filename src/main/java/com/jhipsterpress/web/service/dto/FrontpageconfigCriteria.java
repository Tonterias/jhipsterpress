package com.jhipsterpress.web.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the Frontpageconfig entity. This class is used in FrontpageconfigResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /frontpageconfigs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FrontpageconfigCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter creationDate;

    private LongFilter topNews1;

    private LongFilter topNews2;

    private LongFilter topNews3;

    private LongFilter topNews4;

    private LongFilter topNews5;

    private LongFilter latestNews1;

    private LongFilter latestNews2;

    private LongFilter latestNews3;

    private LongFilter latestNews4;

    private LongFilter latestNews5;

    private LongFilter breakingNews1;

    private LongFilter recentPosts1;

    private LongFilter recentPosts2;

    private LongFilter recentPosts3;

    private LongFilter recentPosts4;

    private LongFilter featuredArticles1;

    private LongFilter featuredArticles2;

    private LongFilter featuredArticles3;

    private LongFilter featuredArticles4;

    private LongFilter featuredArticles5;

    private LongFilter featuredArticles6;

    private LongFilter featuredArticles7;

    private LongFilter featuredArticles8;

    private LongFilter featuredArticles9;

    private LongFilter featuredArticles10;

    private LongFilter popularNews1;

    private LongFilter popularNews2;

    private LongFilter popularNews3;

    private LongFilter popularNews4;

    private LongFilter popularNews5;

    private LongFilter popularNews6;

    private LongFilter popularNews7;

    private LongFilter popularNews8;

    private LongFilter weeklyNews1;

    private LongFilter weeklyNews2;

    private LongFilter weeklyNews3;

    private LongFilter weeklyNews4;

    private LongFilter newsFeeds1;

    private LongFilter newsFeeds2;

    private LongFilter newsFeeds3;

    private LongFilter newsFeeds4;

    private LongFilter newsFeeds5;

    private LongFilter newsFeeds6;

    private LongFilter usefulLinks1;

    private LongFilter usefulLinks2;

    private LongFilter usefulLinks3;

    private LongFilter usefulLinks4;

    private LongFilter usefulLinks5;

    private LongFilter usefulLinks6;

    private LongFilter recentVideos1;

    private LongFilter recentVideos2;

    private LongFilter recentVideos3;

    private LongFilter recentVideos4;

    private LongFilter recentVideos5;

    private LongFilter recentVideos6;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(InstantFilter creationDate) {
        this.creationDate = creationDate;
    }

    public LongFilter getTopNews1() {
        return topNews1;
    }

    public void setTopNews1(LongFilter topNews1) {
        this.topNews1 = topNews1;
    }

    public LongFilter getTopNews2() {
        return topNews2;
    }

    public void setTopNews2(LongFilter topNews2) {
        this.topNews2 = topNews2;
    }

    public LongFilter getTopNews3() {
        return topNews3;
    }

    public void setTopNews3(LongFilter topNews3) {
        this.topNews3 = topNews3;
    }

    public LongFilter getTopNews4() {
        return topNews4;
    }

    public void setTopNews4(LongFilter topNews4) {
        this.topNews4 = topNews4;
    }

    public LongFilter getTopNews5() {
        return topNews5;
    }

    public void setTopNews5(LongFilter topNews5) {
        this.topNews5 = topNews5;
    }

    public LongFilter getLatestNews1() {
        return latestNews1;
    }

    public void setLatestNews1(LongFilter latestNews1) {
        this.latestNews1 = latestNews1;
    }

    public LongFilter getLatestNews2() {
        return latestNews2;
    }

    public void setLatestNews2(LongFilter latestNews2) {
        this.latestNews2 = latestNews2;
    }

    public LongFilter getLatestNews3() {
        return latestNews3;
    }

    public void setLatestNews3(LongFilter latestNews3) {
        this.latestNews3 = latestNews3;
    }

    public LongFilter getLatestNews4() {
        return latestNews4;
    }

    public void setLatestNews4(LongFilter latestNews4) {
        this.latestNews4 = latestNews4;
    }

    public LongFilter getLatestNews5() {
        return latestNews5;
    }

    public void setLatestNews5(LongFilter latestNews5) {
        this.latestNews5 = latestNews5;
    }

    public LongFilter getBreakingNews1() {
        return breakingNews1;
    }

    public void setBreakingNews1(LongFilter breakingNews1) {
        this.breakingNews1 = breakingNews1;
    }

    public LongFilter getRecentPosts1() {
        return recentPosts1;
    }

    public void setRecentPosts1(LongFilter recentPosts1) {
        this.recentPosts1 = recentPosts1;
    }

    public LongFilter getRecentPosts2() {
        return recentPosts2;
    }

    public void setRecentPosts2(LongFilter recentPosts2) {
        this.recentPosts2 = recentPosts2;
    }

    public LongFilter getRecentPosts3() {
        return recentPosts3;
    }

    public void setRecentPosts3(LongFilter recentPosts3) {
        this.recentPosts3 = recentPosts3;
    }

    public LongFilter getRecentPosts4() {
        return recentPosts4;
    }

    public void setRecentPosts4(LongFilter recentPosts4) {
        this.recentPosts4 = recentPosts4;
    }

    public LongFilter getFeaturedArticles1() {
        return featuredArticles1;
    }

    public void setFeaturedArticles1(LongFilter featuredArticles1) {
        this.featuredArticles1 = featuredArticles1;
    }

    public LongFilter getFeaturedArticles2() {
        return featuredArticles2;
    }

    public void setFeaturedArticles2(LongFilter featuredArticles2) {
        this.featuredArticles2 = featuredArticles2;
    }

    public LongFilter getFeaturedArticles3() {
        return featuredArticles3;
    }

    public void setFeaturedArticles3(LongFilter featuredArticles3) {
        this.featuredArticles3 = featuredArticles3;
    }

    public LongFilter getFeaturedArticles4() {
        return featuredArticles4;
    }

    public void setFeaturedArticles4(LongFilter featuredArticles4) {
        this.featuredArticles4 = featuredArticles4;
    }

    public LongFilter getFeaturedArticles5() {
        return featuredArticles5;
    }

    public void setFeaturedArticles5(LongFilter featuredArticles5) {
        this.featuredArticles5 = featuredArticles5;
    }

    public LongFilter getFeaturedArticles6() {
        return featuredArticles6;
    }

    public void setFeaturedArticles6(LongFilter featuredArticles6) {
        this.featuredArticles6 = featuredArticles6;
    }

    public LongFilter getFeaturedArticles7() {
        return featuredArticles7;
    }

    public void setFeaturedArticles7(LongFilter featuredArticles7) {
        this.featuredArticles7 = featuredArticles7;
    }

    public LongFilter getFeaturedArticles8() {
        return featuredArticles8;
    }

    public void setFeaturedArticles8(LongFilter featuredArticles8) {
        this.featuredArticles8 = featuredArticles8;
    }

    public LongFilter getFeaturedArticles9() {
        return featuredArticles9;
    }

    public void setFeaturedArticles9(LongFilter featuredArticles9) {
        this.featuredArticles9 = featuredArticles9;
    }

    public LongFilter getFeaturedArticles10() {
        return featuredArticles10;
    }

    public void setFeaturedArticles10(LongFilter featuredArticles10) {
        this.featuredArticles10 = featuredArticles10;
    }

    public LongFilter getPopularNews1() {
        return popularNews1;
    }

    public void setPopularNews1(LongFilter popularNews1) {
        this.popularNews1 = popularNews1;
    }

    public LongFilter getPopularNews2() {
        return popularNews2;
    }

    public void setPopularNews2(LongFilter popularNews2) {
        this.popularNews2 = popularNews2;
    }

    public LongFilter getPopularNews3() {
        return popularNews3;
    }

    public void setPopularNews3(LongFilter popularNews3) {
        this.popularNews3 = popularNews3;
    }

    public LongFilter getPopularNews4() {
        return popularNews4;
    }

    public void setPopularNews4(LongFilter popularNews4) {
        this.popularNews4 = popularNews4;
    }

    public LongFilter getPopularNews5() {
        return popularNews5;
    }

    public void setPopularNews5(LongFilter popularNews5) {
        this.popularNews5 = popularNews5;
    }

    public LongFilter getPopularNews6() {
        return popularNews6;
    }

    public void setPopularNews6(LongFilter popularNews6) {
        this.popularNews6 = popularNews6;
    }

    public LongFilter getPopularNews7() {
        return popularNews7;
    }

    public void setPopularNews7(LongFilter popularNews7) {
        this.popularNews7 = popularNews7;
    }

    public LongFilter getPopularNews8() {
        return popularNews8;
    }

    public void setPopularNews8(LongFilter popularNews8) {
        this.popularNews8 = popularNews8;
    }

    public LongFilter getWeeklyNews1() {
        return weeklyNews1;
    }

    public void setWeeklyNews1(LongFilter weeklyNews1) {
        this.weeklyNews1 = weeklyNews1;
    }

    public LongFilter getWeeklyNews2() {
        return weeklyNews2;
    }

    public void setWeeklyNews2(LongFilter weeklyNews2) {
        this.weeklyNews2 = weeklyNews2;
    }

    public LongFilter getWeeklyNews3() {
        return weeklyNews3;
    }

    public void setWeeklyNews3(LongFilter weeklyNews3) {
        this.weeklyNews3 = weeklyNews3;
    }

    public LongFilter getWeeklyNews4() {
        return weeklyNews4;
    }

    public void setWeeklyNews4(LongFilter weeklyNews4) {
        this.weeklyNews4 = weeklyNews4;
    }

    public LongFilter getNewsFeeds1() {
        return newsFeeds1;
    }

    public void setNewsFeeds1(LongFilter newsFeeds1) {
        this.newsFeeds1 = newsFeeds1;
    }

    public LongFilter getNewsFeeds2() {
        return newsFeeds2;
    }

    public void setNewsFeeds2(LongFilter newsFeeds2) {
        this.newsFeeds2 = newsFeeds2;
    }

    public LongFilter getNewsFeeds3() {
        return newsFeeds3;
    }

    public void setNewsFeeds3(LongFilter newsFeeds3) {
        this.newsFeeds3 = newsFeeds3;
    }

    public LongFilter getNewsFeeds4() {
        return newsFeeds4;
    }

    public void setNewsFeeds4(LongFilter newsFeeds4) {
        this.newsFeeds4 = newsFeeds4;
    }

    public LongFilter getNewsFeeds5() {
        return newsFeeds5;
    }

    public void setNewsFeeds5(LongFilter newsFeeds5) {
        this.newsFeeds5 = newsFeeds5;
    }

    public LongFilter getNewsFeeds6() {
        return newsFeeds6;
    }

    public void setNewsFeeds6(LongFilter newsFeeds6) {
        this.newsFeeds6 = newsFeeds6;
    }

    public LongFilter getUsefulLinks1() {
        return usefulLinks1;
    }

    public void setUsefulLinks1(LongFilter usefulLinks1) {
        this.usefulLinks1 = usefulLinks1;
    }

    public LongFilter getUsefulLinks2() {
        return usefulLinks2;
    }

    public void setUsefulLinks2(LongFilter usefulLinks2) {
        this.usefulLinks2 = usefulLinks2;
    }

    public LongFilter getUsefulLinks3() {
        return usefulLinks3;
    }

    public void setUsefulLinks3(LongFilter usefulLinks3) {
        this.usefulLinks3 = usefulLinks3;
    }

    public LongFilter getUsefulLinks4() {
        return usefulLinks4;
    }

    public void setUsefulLinks4(LongFilter usefulLinks4) {
        this.usefulLinks4 = usefulLinks4;
    }

    public LongFilter getUsefulLinks5() {
        return usefulLinks5;
    }

    public void setUsefulLinks5(LongFilter usefulLinks5) {
        this.usefulLinks5 = usefulLinks5;
    }

    public LongFilter getUsefulLinks6() {
        return usefulLinks6;
    }

    public void setUsefulLinks6(LongFilter usefulLinks6) {
        this.usefulLinks6 = usefulLinks6;
    }

    public LongFilter getRecentVideos1() {
        return recentVideos1;
    }

    public void setRecentVideos1(LongFilter recentVideos1) {
        this.recentVideos1 = recentVideos1;
    }

    public LongFilter getRecentVideos2() {
        return recentVideos2;
    }

    public void setRecentVideos2(LongFilter recentVideos2) {
        this.recentVideos2 = recentVideos2;
    }

    public LongFilter getRecentVideos3() {
        return recentVideos3;
    }

    public void setRecentVideos3(LongFilter recentVideos3) {
        this.recentVideos3 = recentVideos3;
    }

    public LongFilter getRecentVideos4() {
        return recentVideos4;
    }

    public void setRecentVideos4(LongFilter recentVideos4) {
        this.recentVideos4 = recentVideos4;
    }

    public LongFilter getRecentVideos5() {
        return recentVideos5;
    }

    public void setRecentVideos5(LongFilter recentVideos5) {
        this.recentVideos5 = recentVideos5;
    }

    public LongFilter getRecentVideos6() {
        return recentVideos6;
    }

    public void setRecentVideos6(LongFilter recentVideos6) {
        this.recentVideos6 = recentVideos6;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FrontpageconfigCriteria that = (FrontpageconfigCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(creationDate, that.creationDate) &&
            Objects.equals(topNews1, that.topNews1) &&
            Objects.equals(topNews2, that.topNews2) &&
            Objects.equals(topNews3, that.topNews3) &&
            Objects.equals(topNews4, that.topNews4) &&
            Objects.equals(topNews5, that.topNews5) &&
            Objects.equals(latestNews1, that.latestNews1) &&
            Objects.equals(latestNews2, that.latestNews2) &&
            Objects.equals(latestNews3, that.latestNews3) &&
            Objects.equals(latestNews4, that.latestNews4) &&
            Objects.equals(latestNews5, that.latestNews5) &&
            Objects.equals(breakingNews1, that.breakingNews1) &&
            Objects.equals(recentPosts1, that.recentPosts1) &&
            Objects.equals(recentPosts2, that.recentPosts2) &&
            Objects.equals(recentPosts3, that.recentPosts3) &&
            Objects.equals(recentPosts4, that.recentPosts4) &&
            Objects.equals(featuredArticles1, that.featuredArticles1) &&
            Objects.equals(featuredArticles2, that.featuredArticles2) &&
            Objects.equals(featuredArticles3, that.featuredArticles3) &&
            Objects.equals(featuredArticles4, that.featuredArticles4) &&
            Objects.equals(featuredArticles5, that.featuredArticles5) &&
            Objects.equals(featuredArticles6, that.featuredArticles6) &&
            Objects.equals(featuredArticles7, that.featuredArticles7) &&
            Objects.equals(featuredArticles8, that.featuredArticles8) &&
            Objects.equals(featuredArticles9, that.featuredArticles9) &&
            Objects.equals(featuredArticles10, that.featuredArticles10) &&
            Objects.equals(popularNews1, that.popularNews1) &&
            Objects.equals(popularNews2, that.popularNews2) &&
            Objects.equals(popularNews3, that.popularNews3) &&
            Objects.equals(popularNews4, that.popularNews4) &&
            Objects.equals(popularNews5, that.popularNews5) &&
            Objects.equals(popularNews6, that.popularNews6) &&
            Objects.equals(popularNews7, that.popularNews7) &&
            Objects.equals(popularNews8, that.popularNews8) &&
            Objects.equals(weeklyNews1, that.weeklyNews1) &&
            Objects.equals(weeklyNews2, that.weeklyNews2) &&
            Objects.equals(weeklyNews3, that.weeklyNews3) &&
            Objects.equals(weeklyNews4, that.weeklyNews4) &&
            Objects.equals(newsFeeds1, that.newsFeeds1) &&
            Objects.equals(newsFeeds2, that.newsFeeds2) &&
            Objects.equals(newsFeeds3, that.newsFeeds3) &&
            Objects.equals(newsFeeds4, that.newsFeeds4) &&
            Objects.equals(newsFeeds5, that.newsFeeds5) &&
            Objects.equals(newsFeeds6, that.newsFeeds6) &&
            Objects.equals(usefulLinks1, that.usefulLinks1) &&
            Objects.equals(usefulLinks2, that.usefulLinks2) &&
            Objects.equals(usefulLinks3, that.usefulLinks3) &&
            Objects.equals(usefulLinks4, that.usefulLinks4) &&
            Objects.equals(usefulLinks5, that.usefulLinks5) &&
            Objects.equals(usefulLinks6, that.usefulLinks6) &&
            Objects.equals(recentVideos1, that.recentVideos1) &&
            Objects.equals(recentVideos2, that.recentVideos2) &&
            Objects.equals(recentVideos3, that.recentVideos3) &&
            Objects.equals(recentVideos4, that.recentVideos4) &&
            Objects.equals(recentVideos5, that.recentVideos5) &&
            Objects.equals(recentVideos6, that.recentVideos6);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        creationDate,
        topNews1,
        topNews2,
        topNews3,
        topNews4,
        topNews5,
        latestNews1,
        latestNews2,
        latestNews3,
        latestNews4,
        latestNews5,
        breakingNews1,
        recentPosts1,
        recentPosts2,
        recentPosts3,
        recentPosts4,
        featuredArticles1,
        featuredArticles2,
        featuredArticles3,
        featuredArticles4,
        featuredArticles5,
        featuredArticles6,
        featuredArticles7,
        featuredArticles8,
        featuredArticles9,
        featuredArticles10,
        popularNews1,
        popularNews2,
        popularNews3,
        popularNews4,
        popularNews5,
        popularNews6,
        popularNews7,
        popularNews8,
        weeklyNews1,
        weeklyNews2,
        weeklyNews3,
        weeklyNews4,
        newsFeeds1,
        newsFeeds2,
        newsFeeds3,
        newsFeeds4,
        newsFeeds5,
        newsFeeds6,
        usefulLinks1,
        usefulLinks2,
        usefulLinks3,
        usefulLinks4,
        usefulLinks5,
        usefulLinks6,
        recentVideos1,
        recentVideos2,
        recentVideos3,
        recentVideos4,
        recentVideos5,
        recentVideos6
        );
    }

    @Override
    public String toString() {
        return "FrontpageconfigCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (creationDate != null ? "creationDate=" + creationDate + ", " : "") +
                (topNews1 != null ? "topNews1=" + topNews1 + ", " : "") +
                (topNews2 != null ? "topNews2=" + topNews2 + ", " : "") +
                (topNews3 != null ? "topNews3=" + topNews3 + ", " : "") +
                (topNews4 != null ? "topNews4=" + topNews4 + ", " : "") +
                (topNews5 != null ? "topNews5=" + topNews5 + ", " : "") +
                (latestNews1 != null ? "latestNews1=" + latestNews1 + ", " : "") +
                (latestNews2 != null ? "latestNews2=" + latestNews2 + ", " : "") +
                (latestNews3 != null ? "latestNews3=" + latestNews3 + ", " : "") +
                (latestNews4 != null ? "latestNews4=" + latestNews4 + ", " : "") +
                (latestNews5 != null ? "latestNews5=" + latestNews5 + ", " : "") +
                (breakingNews1 != null ? "breakingNews1=" + breakingNews1 + ", " : "") +
                (recentPosts1 != null ? "recentPosts1=" + recentPosts1 + ", " : "") +
                (recentPosts2 != null ? "recentPosts2=" + recentPosts2 + ", " : "") +
                (recentPosts3 != null ? "recentPosts3=" + recentPosts3 + ", " : "") +
                (recentPosts4 != null ? "recentPosts4=" + recentPosts4 + ", " : "") +
                (featuredArticles1 != null ? "featuredArticles1=" + featuredArticles1 + ", " : "") +
                (featuredArticles2 != null ? "featuredArticles2=" + featuredArticles2 + ", " : "") +
                (featuredArticles3 != null ? "featuredArticles3=" + featuredArticles3 + ", " : "") +
                (featuredArticles4 != null ? "featuredArticles4=" + featuredArticles4 + ", " : "") +
                (featuredArticles5 != null ? "featuredArticles5=" + featuredArticles5 + ", " : "") +
                (featuredArticles6 != null ? "featuredArticles6=" + featuredArticles6 + ", " : "") +
                (featuredArticles7 != null ? "featuredArticles7=" + featuredArticles7 + ", " : "") +
                (featuredArticles8 != null ? "featuredArticles8=" + featuredArticles8 + ", " : "") +
                (featuredArticles9 != null ? "featuredArticles9=" + featuredArticles9 + ", " : "") +
                (featuredArticles10 != null ? "featuredArticles10=" + featuredArticles10 + ", " : "") +
                (popularNews1 != null ? "popularNews1=" + popularNews1 + ", " : "") +
                (popularNews2 != null ? "popularNews2=" + popularNews2 + ", " : "") +
                (popularNews3 != null ? "popularNews3=" + popularNews3 + ", " : "") +
                (popularNews4 != null ? "popularNews4=" + popularNews4 + ", " : "") +
                (popularNews5 != null ? "popularNews5=" + popularNews5 + ", " : "") +
                (popularNews6 != null ? "popularNews6=" + popularNews6 + ", " : "") +
                (popularNews7 != null ? "popularNews7=" + popularNews7 + ", " : "") +
                (popularNews8 != null ? "popularNews8=" + popularNews8 + ", " : "") +
                (weeklyNews1 != null ? "weeklyNews1=" + weeklyNews1 + ", " : "") +
                (weeklyNews2 != null ? "weeklyNews2=" + weeklyNews2 + ", " : "") +
                (weeklyNews3 != null ? "weeklyNews3=" + weeklyNews3 + ", " : "") +
                (weeklyNews4 != null ? "weeklyNews4=" + weeklyNews4 + ", " : "") +
                (newsFeeds1 != null ? "newsFeeds1=" + newsFeeds1 + ", " : "") +
                (newsFeeds2 != null ? "newsFeeds2=" + newsFeeds2 + ", " : "") +
                (newsFeeds3 != null ? "newsFeeds3=" + newsFeeds3 + ", " : "") +
                (newsFeeds4 != null ? "newsFeeds4=" + newsFeeds4 + ", " : "") +
                (newsFeeds5 != null ? "newsFeeds5=" + newsFeeds5 + ", " : "") +
                (newsFeeds6 != null ? "newsFeeds6=" + newsFeeds6 + ", " : "") +
                (usefulLinks1 != null ? "usefulLinks1=" + usefulLinks1 + ", " : "") +
                (usefulLinks2 != null ? "usefulLinks2=" + usefulLinks2 + ", " : "") +
                (usefulLinks3 != null ? "usefulLinks3=" + usefulLinks3 + ", " : "") +
                (usefulLinks4 != null ? "usefulLinks4=" + usefulLinks4 + ", " : "") +
                (usefulLinks5 != null ? "usefulLinks5=" + usefulLinks5 + ", " : "") +
                (usefulLinks6 != null ? "usefulLinks6=" + usefulLinks6 + ", " : "") +
                (recentVideos1 != null ? "recentVideos1=" + recentVideos1 + ", " : "") +
                (recentVideos2 != null ? "recentVideos2=" + recentVideos2 + ", " : "") +
                (recentVideos3 != null ? "recentVideos3=" + recentVideos3 + ", " : "") +
                (recentVideos4 != null ? "recentVideos4=" + recentVideos4 + ", " : "") +
                (recentVideos5 != null ? "recentVideos5=" + recentVideos5 + ", " : "") +
                (recentVideos6 != null ? "recentVideos6=" + recentVideos6 + ", " : "") +
            "}";
    }

}
