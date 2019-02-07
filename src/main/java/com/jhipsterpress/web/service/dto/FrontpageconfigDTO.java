package com.jhipsterpress.web.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Frontpageconfig entity.
 */
public class FrontpageconfigDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    private Long topNews1;

    private Long topNews2;

    private Long topNews3;

    private Long topNews4;

    private Long topNews5;

    private Long latestNews1;

    private Long latestNews2;

    private Long latestNews3;

    private Long latestNews4;

    private Long latestNews5;

    private Long breakingNews1;

    private Long recentPosts1;

    private Long recentPosts2;

    private Long recentPosts3;

    private Long recentPosts4;

    private Long featuredArticles1;

    private Long featuredArticles2;

    private Long featuredArticles3;

    private Long featuredArticles4;

    private Long featuredArticles5;

    private Long featuredArticles6;

    private Long featuredArticles7;

    private Long featuredArticles8;

    private Long featuredArticles9;

    private Long featuredArticles10;

    private Long popularNews1;

    private Long popularNews2;

    private Long popularNews3;

    private Long popularNews4;

    private Long popularNews5;

    private Long popularNews6;

    private Long popularNews7;

    private Long popularNews8;

    private Long weeklyNews1;

    private Long weeklyNews2;

    private Long weeklyNews3;

    private Long weeklyNews4;

    private Long newsFeeds1;

    private Long newsFeeds2;

    private Long newsFeeds3;

    private Long newsFeeds4;

    private Long newsFeeds5;

    private Long newsFeeds6;

    private Long usefulLinks1;

    private Long usefulLinks2;

    private Long usefulLinks3;

    private Long usefulLinks4;

    private Long usefulLinks5;

    private Long usefulLinks6;

    private Long recentVideos1;

    private Long recentVideos2;

    private Long recentVideos3;

    private Long recentVideos4;

    private Long recentVideos5;

    private Long recentVideos6;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Long getTopNews1() {
        return topNews1;
    }

    public void setTopNews1(Long topNews1) {
        this.topNews1 = topNews1;
    }

    public Long getTopNews2() {
        return topNews2;
    }

    public void setTopNews2(Long topNews2) {
        this.topNews2 = topNews2;
    }

    public Long getTopNews3() {
        return topNews3;
    }

    public void setTopNews3(Long topNews3) {
        this.topNews3 = topNews3;
    }

    public Long getTopNews4() {
        return topNews4;
    }

    public void setTopNews4(Long topNews4) {
        this.topNews4 = topNews4;
    }

    public Long getTopNews5() {
        return topNews5;
    }

    public void setTopNews5(Long topNews5) {
        this.topNews5 = topNews5;
    }

    public Long getLatestNews1() {
        return latestNews1;
    }

    public void setLatestNews1(Long latestNews1) {
        this.latestNews1 = latestNews1;
    }

    public Long getLatestNews2() {
        return latestNews2;
    }

    public void setLatestNews2(Long latestNews2) {
        this.latestNews2 = latestNews2;
    }

    public Long getLatestNews3() {
        return latestNews3;
    }

    public void setLatestNews3(Long latestNews3) {
        this.latestNews3 = latestNews3;
    }

    public Long getLatestNews4() {
        return latestNews4;
    }

    public void setLatestNews4(Long latestNews4) {
        this.latestNews4 = latestNews4;
    }

    public Long getLatestNews5() {
        return latestNews5;
    }

    public void setLatestNews5(Long latestNews5) {
        this.latestNews5 = latestNews5;
    }

    public Long getBreakingNews1() {
        return breakingNews1;
    }

    public void setBreakingNews1(Long breakingNews1) {
        this.breakingNews1 = breakingNews1;
    }

    public Long getRecentPosts1() {
        return recentPosts1;
    }

    public void setRecentPosts1(Long recentPosts1) {
        this.recentPosts1 = recentPosts1;
    }

    public Long getRecentPosts2() {
        return recentPosts2;
    }

    public void setRecentPosts2(Long recentPosts2) {
        this.recentPosts2 = recentPosts2;
    }

    public Long getRecentPosts3() {
        return recentPosts3;
    }

    public void setRecentPosts3(Long recentPosts3) {
        this.recentPosts3 = recentPosts3;
    }

    public Long getRecentPosts4() {
        return recentPosts4;
    }

    public void setRecentPosts4(Long recentPosts4) {
        this.recentPosts4 = recentPosts4;
    }

    public Long getFeaturedArticles1() {
        return featuredArticles1;
    }

    public void setFeaturedArticles1(Long featuredArticles1) {
        this.featuredArticles1 = featuredArticles1;
    }

    public Long getFeaturedArticles2() {
        return featuredArticles2;
    }

    public void setFeaturedArticles2(Long featuredArticles2) {
        this.featuredArticles2 = featuredArticles2;
    }

    public Long getFeaturedArticles3() {
        return featuredArticles3;
    }

    public void setFeaturedArticles3(Long featuredArticles3) {
        this.featuredArticles3 = featuredArticles3;
    }

    public Long getFeaturedArticles4() {
        return featuredArticles4;
    }

    public void setFeaturedArticles4(Long featuredArticles4) {
        this.featuredArticles4 = featuredArticles4;
    }

    public Long getFeaturedArticles5() {
        return featuredArticles5;
    }

    public void setFeaturedArticles5(Long featuredArticles5) {
        this.featuredArticles5 = featuredArticles5;
    }

    public Long getFeaturedArticles6() {
        return featuredArticles6;
    }

    public void setFeaturedArticles6(Long featuredArticles6) {
        this.featuredArticles6 = featuredArticles6;
    }

    public Long getFeaturedArticles7() {
        return featuredArticles7;
    }

    public void setFeaturedArticles7(Long featuredArticles7) {
        this.featuredArticles7 = featuredArticles7;
    }

    public Long getFeaturedArticles8() {
        return featuredArticles8;
    }

    public void setFeaturedArticles8(Long featuredArticles8) {
        this.featuredArticles8 = featuredArticles8;
    }

    public Long getFeaturedArticles9() {
        return featuredArticles9;
    }

    public void setFeaturedArticles9(Long featuredArticles9) {
        this.featuredArticles9 = featuredArticles9;
    }

    public Long getFeaturedArticles10() {
        return featuredArticles10;
    }

    public void setFeaturedArticles10(Long featuredArticles10) {
        this.featuredArticles10 = featuredArticles10;
    }

    public Long getPopularNews1() {
        return popularNews1;
    }

    public void setPopularNews1(Long popularNews1) {
        this.popularNews1 = popularNews1;
    }

    public Long getPopularNews2() {
        return popularNews2;
    }

    public void setPopularNews2(Long popularNews2) {
        this.popularNews2 = popularNews2;
    }

    public Long getPopularNews3() {
        return popularNews3;
    }

    public void setPopularNews3(Long popularNews3) {
        this.popularNews3 = popularNews3;
    }

    public Long getPopularNews4() {
        return popularNews4;
    }

    public void setPopularNews4(Long popularNews4) {
        this.popularNews4 = popularNews4;
    }

    public Long getPopularNews5() {
        return popularNews5;
    }

    public void setPopularNews5(Long popularNews5) {
        this.popularNews5 = popularNews5;
    }

    public Long getPopularNews6() {
        return popularNews6;
    }

    public void setPopularNews6(Long popularNews6) {
        this.popularNews6 = popularNews6;
    }

    public Long getPopularNews7() {
        return popularNews7;
    }

    public void setPopularNews7(Long popularNews7) {
        this.popularNews7 = popularNews7;
    }

    public Long getPopularNews8() {
        return popularNews8;
    }

    public void setPopularNews8(Long popularNews8) {
        this.popularNews8 = popularNews8;
    }

    public Long getWeeklyNews1() {
        return weeklyNews1;
    }

    public void setWeeklyNews1(Long weeklyNews1) {
        this.weeklyNews1 = weeklyNews1;
    }

    public Long getWeeklyNews2() {
        return weeklyNews2;
    }

    public void setWeeklyNews2(Long weeklyNews2) {
        this.weeklyNews2 = weeklyNews2;
    }

    public Long getWeeklyNews3() {
        return weeklyNews3;
    }

    public void setWeeklyNews3(Long weeklyNews3) {
        this.weeklyNews3 = weeklyNews3;
    }

    public Long getWeeklyNews4() {
        return weeklyNews4;
    }

    public void setWeeklyNews4(Long weeklyNews4) {
        this.weeklyNews4 = weeklyNews4;
    }

    public Long getNewsFeeds1() {
        return newsFeeds1;
    }

    public void setNewsFeeds1(Long newsFeeds1) {
        this.newsFeeds1 = newsFeeds1;
    }

    public Long getNewsFeeds2() {
        return newsFeeds2;
    }

    public void setNewsFeeds2(Long newsFeeds2) {
        this.newsFeeds2 = newsFeeds2;
    }

    public Long getNewsFeeds3() {
        return newsFeeds3;
    }

    public void setNewsFeeds3(Long newsFeeds3) {
        this.newsFeeds3 = newsFeeds3;
    }

    public Long getNewsFeeds4() {
        return newsFeeds4;
    }

    public void setNewsFeeds4(Long newsFeeds4) {
        this.newsFeeds4 = newsFeeds4;
    }

    public Long getNewsFeeds5() {
        return newsFeeds5;
    }

    public void setNewsFeeds5(Long newsFeeds5) {
        this.newsFeeds5 = newsFeeds5;
    }

    public Long getNewsFeeds6() {
        return newsFeeds6;
    }

    public void setNewsFeeds6(Long newsFeeds6) {
        this.newsFeeds6 = newsFeeds6;
    }

    public Long getUsefulLinks1() {
        return usefulLinks1;
    }

    public void setUsefulLinks1(Long usefulLinks1) {
        this.usefulLinks1 = usefulLinks1;
    }

    public Long getUsefulLinks2() {
        return usefulLinks2;
    }

    public void setUsefulLinks2(Long usefulLinks2) {
        this.usefulLinks2 = usefulLinks2;
    }

    public Long getUsefulLinks3() {
        return usefulLinks3;
    }

    public void setUsefulLinks3(Long usefulLinks3) {
        this.usefulLinks3 = usefulLinks3;
    }

    public Long getUsefulLinks4() {
        return usefulLinks4;
    }

    public void setUsefulLinks4(Long usefulLinks4) {
        this.usefulLinks4 = usefulLinks4;
    }

    public Long getUsefulLinks5() {
        return usefulLinks5;
    }

    public void setUsefulLinks5(Long usefulLinks5) {
        this.usefulLinks5 = usefulLinks5;
    }

    public Long getUsefulLinks6() {
        return usefulLinks6;
    }

    public void setUsefulLinks6(Long usefulLinks6) {
        this.usefulLinks6 = usefulLinks6;
    }

    public Long getRecentVideos1() {
        return recentVideos1;
    }

    public void setRecentVideos1(Long recentVideos1) {
        this.recentVideos1 = recentVideos1;
    }

    public Long getRecentVideos2() {
        return recentVideos2;
    }

    public void setRecentVideos2(Long recentVideos2) {
        this.recentVideos2 = recentVideos2;
    }

    public Long getRecentVideos3() {
        return recentVideos3;
    }

    public void setRecentVideos3(Long recentVideos3) {
        this.recentVideos3 = recentVideos3;
    }

    public Long getRecentVideos4() {
        return recentVideos4;
    }

    public void setRecentVideos4(Long recentVideos4) {
        this.recentVideos4 = recentVideos4;
    }

    public Long getRecentVideos5() {
        return recentVideos5;
    }

    public void setRecentVideos5(Long recentVideos5) {
        this.recentVideos5 = recentVideos5;
    }

    public Long getRecentVideos6() {
        return recentVideos6;
    }

    public void setRecentVideos6(Long recentVideos6) {
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

        FrontpageconfigDTO frontpageconfigDTO = (FrontpageconfigDTO) o;
        if (frontpageconfigDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), frontpageconfigDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FrontpageconfigDTO{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", topNews1=" + getTopNews1() +
            ", topNews2=" + getTopNews2() +
            ", topNews3=" + getTopNews3() +
            ", topNews4=" + getTopNews4() +
            ", topNews5=" + getTopNews5() +
            ", latestNews1=" + getLatestNews1() +
            ", latestNews2=" + getLatestNews2() +
            ", latestNews3=" + getLatestNews3() +
            ", latestNews4=" + getLatestNews4() +
            ", latestNews5=" + getLatestNews5() +
            ", breakingNews1=" + getBreakingNews1() +
            ", recentPosts1=" + getRecentPosts1() +
            ", recentPosts2=" + getRecentPosts2() +
            ", recentPosts3=" + getRecentPosts3() +
            ", recentPosts4=" + getRecentPosts4() +
            ", featuredArticles1=" + getFeaturedArticles1() +
            ", featuredArticles2=" + getFeaturedArticles2() +
            ", featuredArticles3=" + getFeaturedArticles3() +
            ", featuredArticles4=" + getFeaturedArticles4() +
            ", featuredArticles5=" + getFeaturedArticles5() +
            ", featuredArticles6=" + getFeaturedArticles6() +
            ", featuredArticles7=" + getFeaturedArticles7() +
            ", featuredArticles8=" + getFeaturedArticles8() +
            ", featuredArticles9=" + getFeaturedArticles9() +
            ", featuredArticles10=" + getFeaturedArticles10() +
            ", popularNews1=" + getPopularNews1() +
            ", popularNews2=" + getPopularNews2() +
            ", popularNews3=" + getPopularNews3() +
            ", popularNews4=" + getPopularNews4() +
            ", popularNews5=" + getPopularNews5() +
            ", popularNews6=" + getPopularNews6() +
            ", popularNews7=" + getPopularNews7() +
            ", popularNews8=" + getPopularNews8() +
            ", weeklyNews1=" + getWeeklyNews1() +
            ", weeklyNews2=" + getWeeklyNews2() +
            ", weeklyNews3=" + getWeeklyNews3() +
            ", weeklyNews4=" + getWeeklyNews4() +
            ", newsFeeds1=" + getNewsFeeds1() +
            ", newsFeeds2=" + getNewsFeeds2() +
            ", newsFeeds3=" + getNewsFeeds3() +
            ", newsFeeds4=" + getNewsFeeds4() +
            ", newsFeeds5=" + getNewsFeeds5() +
            ", newsFeeds6=" + getNewsFeeds6() +
            ", usefulLinks1=" + getUsefulLinks1() +
            ", usefulLinks2=" + getUsefulLinks2() +
            ", usefulLinks3=" + getUsefulLinks3() +
            ", usefulLinks4=" + getUsefulLinks4() +
            ", usefulLinks5=" + getUsefulLinks5() +
            ", usefulLinks6=" + getUsefulLinks6() +
            ", recentVideos1=" + getRecentVideos1() +
            ", recentVideos2=" + getRecentVideos2() +
            ", recentVideos3=" + getRecentVideos3() +
            ", recentVideos4=" + getRecentVideos4() +
            ", recentVideos5=" + getRecentVideos5() +
            ", recentVideos6=" + getRecentVideos6() +
            "}";
    }
}
