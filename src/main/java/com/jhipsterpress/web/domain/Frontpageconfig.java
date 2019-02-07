package com.jhipsterpress.web.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Frontpageconfig.
 */
@Entity
@Table(name = "frontpageconfig")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Frontpageconfig implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @Column(name = "top_news_1")
    private Long topNews1;

    @Column(name = "top_news_2")
    private Long topNews2;

    @Column(name = "top_news_3")
    private Long topNews3;

    @Column(name = "top_news_4")
    private Long topNews4;

    @Column(name = "top_news_5")
    private Long topNews5;

    @Column(name = "latest_news_1")
    private Long latestNews1;

    @Column(name = "latest_news_2")
    private Long latestNews2;

    @Column(name = "latest_news_3")
    private Long latestNews3;

    @Column(name = "latest_news_4")
    private Long latestNews4;

    @Column(name = "latest_news_5")
    private Long latestNews5;

    @Column(name = "breaking_news_1")
    private Long breakingNews1;

    @Column(name = "recent_posts_1")
    private Long recentPosts1;

    @Column(name = "recent_posts_2")
    private Long recentPosts2;

    @Column(name = "recent_posts_3")
    private Long recentPosts3;

    @Column(name = "recent_posts_4")
    private Long recentPosts4;

    @Column(name = "featured_articles_1")
    private Long featuredArticles1;

    @Column(name = "featured_articles_2")
    private Long featuredArticles2;

    @Column(name = "featured_articles_3")
    private Long featuredArticles3;

    @Column(name = "featured_articles_4")
    private Long featuredArticles4;

    @Column(name = "featured_articles_5")
    private Long featuredArticles5;

    @Column(name = "featured_articles_6")
    private Long featuredArticles6;

    @Column(name = "featured_articles_7")
    private Long featuredArticles7;

    @Column(name = "featured_articles_8")
    private Long featuredArticles8;

    @Column(name = "featured_articles_9")
    private Long featuredArticles9;

    @Column(name = "featured_articles_10")
    private Long featuredArticles10;

    @Column(name = "popular_news_1")
    private Long popularNews1;

    @Column(name = "popular_news_2")
    private Long popularNews2;

    @Column(name = "popular_news_3")
    private Long popularNews3;

    @Column(name = "popular_news_4")
    private Long popularNews4;

    @Column(name = "popular_news_5")
    private Long popularNews5;

    @Column(name = "popular_news_6")
    private Long popularNews6;

    @Column(name = "popular_news_7")
    private Long popularNews7;

    @Column(name = "popular_news_8")
    private Long popularNews8;

    @Column(name = "weekly_news_1")
    private Long weeklyNews1;

    @Column(name = "weekly_news_2")
    private Long weeklyNews2;

    @Column(name = "weekly_news_3")
    private Long weeklyNews3;

    @Column(name = "weekly_news_4")
    private Long weeklyNews4;

    @Column(name = "news_feeds_1")
    private Long newsFeeds1;

    @Column(name = "news_feeds_2")
    private Long newsFeeds2;

    @Column(name = "news_feeds_3")
    private Long newsFeeds3;

    @Column(name = "news_feeds_4")
    private Long newsFeeds4;

    @Column(name = "news_feeds_5")
    private Long newsFeeds5;

    @Column(name = "news_feeds_6")
    private Long newsFeeds6;

    @Column(name = "useful_links_1")
    private Long usefulLinks1;

    @Column(name = "useful_links_2")
    private Long usefulLinks2;

    @Column(name = "useful_links_3")
    private Long usefulLinks3;

    @Column(name = "useful_links_4")
    private Long usefulLinks4;

    @Column(name = "useful_links_5")
    private Long usefulLinks5;

    @Column(name = "useful_links_6")
    private Long usefulLinks6;

    @Column(name = "recent_videos_1")
    private Long recentVideos1;

    @Column(name = "recent_videos_2")
    private Long recentVideos2;

    @Column(name = "recent_videos_3")
    private Long recentVideos3;

    @Column(name = "recent_videos_4")
    private Long recentVideos4;

    @Column(name = "recent_videos_5")
    private Long recentVideos5;

    @Column(name = "recent_videos_6")
    private Long recentVideos6;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Frontpageconfig creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Long getTopNews1() {
        return topNews1;
    }

    public Frontpageconfig topNews1(Long topNews1) {
        this.topNews1 = topNews1;
        return this;
    }

    public void setTopNews1(Long topNews1) {
        this.topNews1 = topNews1;
    }

    public Long getTopNews2() {
        return topNews2;
    }

    public Frontpageconfig topNews2(Long topNews2) {
        this.topNews2 = topNews2;
        return this;
    }

    public void setTopNews2(Long topNews2) {
        this.topNews2 = topNews2;
    }

    public Long getTopNews3() {
        return topNews3;
    }

    public Frontpageconfig topNews3(Long topNews3) {
        this.topNews3 = topNews3;
        return this;
    }

    public void setTopNews3(Long topNews3) {
        this.topNews3 = topNews3;
    }

    public Long getTopNews4() {
        return topNews4;
    }

    public Frontpageconfig topNews4(Long topNews4) {
        this.topNews4 = topNews4;
        return this;
    }

    public void setTopNews4(Long topNews4) {
        this.topNews4 = topNews4;
    }

    public Long getTopNews5() {
        return topNews5;
    }

    public Frontpageconfig topNews5(Long topNews5) {
        this.topNews5 = topNews5;
        return this;
    }

    public void setTopNews5(Long topNews5) {
        this.topNews5 = topNews5;
    }

    public Long getLatestNews1() {
        return latestNews1;
    }

    public Frontpageconfig latestNews1(Long latestNews1) {
        this.latestNews1 = latestNews1;
        return this;
    }

    public void setLatestNews1(Long latestNews1) {
        this.latestNews1 = latestNews1;
    }

    public Long getLatestNews2() {
        return latestNews2;
    }

    public Frontpageconfig latestNews2(Long latestNews2) {
        this.latestNews2 = latestNews2;
        return this;
    }

    public void setLatestNews2(Long latestNews2) {
        this.latestNews2 = latestNews2;
    }

    public Long getLatestNews3() {
        return latestNews3;
    }

    public Frontpageconfig latestNews3(Long latestNews3) {
        this.latestNews3 = latestNews3;
        return this;
    }

    public void setLatestNews3(Long latestNews3) {
        this.latestNews3 = latestNews3;
    }

    public Long getLatestNews4() {
        return latestNews4;
    }

    public Frontpageconfig latestNews4(Long latestNews4) {
        this.latestNews4 = latestNews4;
        return this;
    }

    public void setLatestNews4(Long latestNews4) {
        this.latestNews4 = latestNews4;
    }

    public Long getLatestNews5() {
        return latestNews5;
    }

    public Frontpageconfig latestNews5(Long latestNews5) {
        this.latestNews5 = latestNews5;
        return this;
    }

    public void setLatestNews5(Long latestNews5) {
        this.latestNews5 = latestNews5;
    }

    public Long getBreakingNews1() {
        return breakingNews1;
    }

    public Frontpageconfig breakingNews1(Long breakingNews1) {
        this.breakingNews1 = breakingNews1;
        return this;
    }

    public void setBreakingNews1(Long breakingNews1) {
        this.breakingNews1 = breakingNews1;
    }

    public Long getRecentPosts1() {
        return recentPosts1;
    }

    public Frontpageconfig recentPosts1(Long recentPosts1) {
        this.recentPosts1 = recentPosts1;
        return this;
    }

    public void setRecentPosts1(Long recentPosts1) {
        this.recentPosts1 = recentPosts1;
    }

    public Long getRecentPosts2() {
        return recentPosts2;
    }

    public Frontpageconfig recentPosts2(Long recentPosts2) {
        this.recentPosts2 = recentPosts2;
        return this;
    }

    public void setRecentPosts2(Long recentPosts2) {
        this.recentPosts2 = recentPosts2;
    }

    public Long getRecentPosts3() {
        return recentPosts3;
    }

    public Frontpageconfig recentPosts3(Long recentPosts3) {
        this.recentPosts3 = recentPosts3;
        return this;
    }

    public void setRecentPosts3(Long recentPosts3) {
        this.recentPosts3 = recentPosts3;
    }

    public Long getRecentPosts4() {
        return recentPosts4;
    }

    public Frontpageconfig recentPosts4(Long recentPosts4) {
        this.recentPosts4 = recentPosts4;
        return this;
    }

    public void setRecentPosts4(Long recentPosts4) {
        this.recentPosts4 = recentPosts4;
    }

    public Long getFeaturedArticles1() {
        return featuredArticles1;
    }

    public Frontpageconfig featuredArticles1(Long featuredArticles1) {
        this.featuredArticles1 = featuredArticles1;
        return this;
    }

    public void setFeaturedArticles1(Long featuredArticles1) {
        this.featuredArticles1 = featuredArticles1;
    }

    public Long getFeaturedArticles2() {
        return featuredArticles2;
    }

    public Frontpageconfig featuredArticles2(Long featuredArticles2) {
        this.featuredArticles2 = featuredArticles2;
        return this;
    }

    public void setFeaturedArticles2(Long featuredArticles2) {
        this.featuredArticles2 = featuredArticles2;
    }

    public Long getFeaturedArticles3() {
        return featuredArticles3;
    }

    public Frontpageconfig featuredArticles3(Long featuredArticles3) {
        this.featuredArticles3 = featuredArticles3;
        return this;
    }

    public void setFeaturedArticles3(Long featuredArticles3) {
        this.featuredArticles3 = featuredArticles3;
    }

    public Long getFeaturedArticles4() {
        return featuredArticles4;
    }

    public Frontpageconfig featuredArticles4(Long featuredArticles4) {
        this.featuredArticles4 = featuredArticles4;
        return this;
    }

    public void setFeaturedArticles4(Long featuredArticles4) {
        this.featuredArticles4 = featuredArticles4;
    }

    public Long getFeaturedArticles5() {
        return featuredArticles5;
    }

    public Frontpageconfig featuredArticles5(Long featuredArticles5) {
        this.featuredArticles5 = featuredArticles5;
        return this;
    }

    public void setFeaturedArticles5(Long featuredArticles5) {
        this.featuredArticles5 = featuredArticles5;
    }

    public Long getFeaturedArticles6() {
        return featuredArticles6;
    }

    public Frontpageconfig featuredArticles6(Long featuredArticles6) {
        this.featuredArticles6 = featuredArticles6;
        return this;
    }

    public void setFeaturedArticles6(Long featuredArticles6) {
        this.featuredArticles6 = featuredArticles6;
    }

    public Long getFeaturedArticles7() {
        return featuredArticles7;
    }

    public Frontpageconfig featuredArticles7(Long featuredArticles7) {
        this.featuredArticles7 = featuredArticles7;
        return this;
    }

    public void setFeaturedArticles7(Long featuredArticles7) {
        this.featuredArticles7 = featuredArticles7;
    }

    public Long getFeaturedArticles8() {
        return featuredArticles8;
    }

    public Frontpageconfig featuredArticles8(Long featuredArticles8) {
        this.featuredArticles8 = featuredArticles8;
        return this;
    }

    public void setFeaturedArticles8(Long featuredArticles8) {
        this.featuredArticles8 = featuredArticles8;
    }

    public Long getFeaturedArticles9() {
        return featuredArticles9;
    }

    public Frontpageconfig featuredArticles9(Long featuredArticles9) {
        this.featuredArticles9 = featuredArticles9;
        return this;
    }

    public void setFeaturedArticles9(Long featuredArticles9) {
        this.featuredArticles9 = featuredArticles9;
    }

    public Long getFeaturedArticles10() {
        return featuredArticles10;
    }

    public Frontpageconfig featuredArticles10(Long featuredArticles10) {
        this.featuredArticles10 = featuredArticles10;
        return this;
    }

    public void setFeaturedArticles10(Long featuredArticles10) {
        this.featuredArticles10 = featuredArticles10;
    }

    public Long getPopularNews1() {
        return popularNews1;
    }

    public Frontpageconfig popularNews1(Long popularNews1) {
        this.popularNews1 = popularNews1;
        return this;
    }

    public void setPopularNews1(Long popularNews1) {
        this.popularNews1 = popularNews1;
    }

    public Long getPopularNews2() {
        return popularNews2;
    }

    public Frontpageconfig popularNews2(Long popularNews2) {
        this.popularNews2 = popularNews2;
        return this;
    }

    public void setPopularNews2(Long popularNews2) {
        this.popularNews2 = popularNews2;
    }

    public Long getPopularNews3() {
        return popularNews3;
    }

    public Frontpageconfig popularNews3(Long popularNews3) {
        this.popularNews3 = popularNews3;
        return this;
    }

    public void setPopularNews3(Long popularNews3) {
        this.popularNews3 = popularNews3;
    }

    public Long getPopularNews4() {
        return popularNews4;
    }

    public Frontpageconfig popularNews4(Long popularNews4) {
        this.popularNews4 = popularNews4;
        return this;
    }

    public void setPopularNews4(Long popularNews4) {
        this.popularNews4 = popularNews4;
    }

    public Long getPopularNews5() {
        return popularNews5;
    }

    public Frontpageconfig popularNews5(Long popularNews5) {
        this.popularNews5 = popularNews5;
        return this;
    }

    public void setPopularNews5(Long popularNews5) {
        this.popularNews5 = popularNews5;
    }

    public Long getPopularNews6() {
        return popularNews6;
    }

    public Frontpageconfig popularNews6(Long popularNews6) {
        this.popularNews6 = popularNews6;
        return this;
    }

    public void setPopularNews6(Long popularNews6) {
        this.popularNews6 = popularNews6;
    }

    public Long getPopularNews7() {
        return popularNews7;
    }

    public Frontpageconfig popularNews7(Long popularNews7) {
        this.popularNews7 = popularNews7;
        return this;
    }

    public void setPopularNews7(Long popularNews7) {
        this.popularNews7 = popularNews7;
    }

    public Long getPopularNews8() {
        return popularNews8;
    }

    public Frontpageconfig popularNews8(Long popularNews8) {
        this.popularNews8 = popularNews8;
        return this;
    }

    public void setPopularNews8(Long popularNews8) {
        this.popularNews8 = popularNews8;
    }

    public Long getWeeklyNews1() {
        return weeklyNews1;
    }

    public Frontpageconfig weeklyNews1(Long weeklyNews1) {
        this.weeklyNews1 = weeklyNews1;
        return this;
    }

    public void setWeeklyNews1(Long weeklyNews1) {
        this.weeklyNews1 = weeklyNews1;
    }

    public Long getWeeklyNews2() {
        return weeklyNews2;
    }

    public Frontpageconfig weeklyNews2(Long weeklyNews2) {
        this.weeklyNews2 = weeklyNews2;
        return this;
    }

    public void setWeeklyNews2(Long weeklyNews2) {
        this.weeklyNews2 = weeklyNews2;
    }

    public Long getWeeklyNews3() {
        return weeklyNews3;
    }

    public Frontpageconfig weeklyNews3(Long weeklyNews3) {
        this.weeklyNews3 = weeklyNews3;
        return this;
    }

    public void setWeeklyNews3(Long weeklyNews3) {
        this.weeklyNews3 = weeklyNews3;
    }

    public Long getWeeklyNews4() {
        return weeklyNews4;
    }

    public Frontpageconfig weeklyNews4(Long weeklyNews4) {
        this.weeklyNews4 = weeklyNews4;
        return this;
    }

    public void setWeeklyNews4(Long weeklyNews4) {
        this.weeklyNews4 = weeklyNews4;
    }

    public Long getNewsFeeds1() {
        return newsFeeds1;
    }

    public Frontpageconfig newsFeeds1(Long newsFeeds1) {
        this.newsFeeds1 = newsFeeds1;
        return this;
    }

    public void setNewsFeeds1(Long newsFeeds1) {
        this.newsFeeds1 = newsFeeds1;
    }

    public Long getNewsFeeds2() {
        return newsFeeds2;
    }

    public Frontpageconfig newsFeeds2(Long newsFeeds2) {
        this.newsFeeds2 = newsFeeds2;
        return this;
    }

    public void setNewsFeeds2(Long newsFeeds2) {
        this.newsFeeds2 = newsFeeds2;
    }

    public Long getNewsFeeds3() {
        return newsFeeds3;
    }

    public Frontpageconfig newsFeeds3(Long newsFeeds3) {
        this.newsFeeds3 = newsFeeds3;
        return this;
    }

    public void setNewsFeeds3(Long newsFeeds3) {
        this.newsFeeds3 = newsFeeds3;
    }

    public Long getNewsFeeds4() {
        return newsFeeds4;
    }

    public Frontpageconfig newsFeeds4(Long newsFeeds4) {
        this.newsFeeds4 = newsFeeds4;
        return this;
    }

    public void setNewsFeeds4(Long newsFeeds4) {
        this.newsFeeds4 = newsFeeds4;
    }

    public Long getNewsFeeds5() {
        return newsFeeds5;
    }

    public Frontpageconfig newsFeeds5(Long newsFeeds5) {
        this.newsFeeds5 = newsFeeds5;
        return this;
    }

    public void setNewsFeeds5(Long newsFeeds5) {
        this.newsFeeds5 = newsFeeds5;
    }

    public Long getNewsFeeds6() {
        return newsFeeds6;
    }

    public Frontpageconfig newsFeeds6(Long newsFeeds6) {
        this.newsFeeds6 = newsFeeds6;
        return this;
    }

    public void setNewsFeeds6(Long newsFeeds6) {
        this.newsFeeds6 = newsFeeds6;
    }

    public Long getUsefulLinks1() {
        return usefulLinks1;
    }

    public Frontpageconfig usefulLinks1(Long usefulLinks1) {
        this.usefulLinks1 = usefulLinks1;
        return this;
    }

    public void setUsefulLinks1(Long usefulLinks1) {
        this.usefulLinks1 = usefulLinks1;
    }

    public Long getUsefulLinks2() {
        return usefulLinks2;
    }

    public Frontpageconfig usefulLinks2(Long usefulLinks2) {
        this.usefulLinks2 = usefulLinks2;
        return this;
    }

    public void setUsefulLinks2(Long usefulLinks2) {
        this.usefulLinks2 = usefulLinks2;
    }

    public Long getUsefulLinks3() {
        return usefulLinks3;
    }

    public Frontpageconfig usefulLinks3(Long usefulLinks3) {
        this.usefulLinks3 = usefulLinks3;
        return this;
    }

    public void setUsefulLinks3(Long usefulLinks3) {
        this.usefulLinks3 = usefulLinks3;
    }

    public Long getUsefulLinks4() {
        return usefulLinks4;
    }

    public Frontpageconfig usefulLinks4(Long usefulLinks4) {
        this.usefulLinks4 = usefulLinks4;
        return this;
    }

    public void setUsefulLinks4(Long usefulLinks4) {
        this.usefulLinks4 = usefulLinks4;
    }

    public Long getUsefulLinks5() {
        return usefulLinks5;
    }

    public Frontpageconfig usefulLinks5(Long usefulLinks5) {
        this.usefulLinks5 = usefulLinks5;
        return this;
    }

    public void setUsefulLinks5(Long usefulLinks5) {
        this.usefulLinks5 = usefulLinks5;
    }

    public Long getUsefulLinks6() {
        return usefulLinks6;
    }

    public Frontpageconfig usefulLinks6(Long usefulLinks6) {
        this.usefulLinks6 = usefulLinks6;
        return this;
    }

    public void setUsefulLinks6(Long usefulLinks6) {
        this.usefulLinks6 = usefulLinks6;
    }

    public Long getRecentVideos1() {
        return recentVideos1;
    }

    public Frontpageconfig recentVideos1(Long recentVideos1) {
        this.recentVideos1 = recentVideos1;
        return this;
    }

    public void setRecentVideos1(Long recentVideos1) {
        this.recentVideos1 = recentVideos1;
    }

    public Long getRecentVideos2() {
        return recentVideos2;
    }

    public Frontpageconfig recentVideos2(Long recentVideos2) {
        this.recentVideos2 = recentVideos2;
        return this;
    }

    public void setRecentVideos2(Long recentVideos2) {
        this.recentVideos2 = recentVideos2;
    }

    public Long getRecentVideos3() {
        return recentVideos3;
    }

    public Frontpageconfig recentVideos3(Long recentVideos3) {
        this.recentVideos3 = recentVideos3;
        return this;
    }

    public void setRecentVideos3(Long recentVideos3) {
        this.recentVideos3 = recentVideos3;
    }

    public Long getRecentVideos4() {
        return recentVideos4;
    }

    public Frontpageconfig recentVideos4(Long recentVideos4) {
        this.recentVideos4 = recentVideos4;
        return this;
    }

    public void setRecentVideos4(Long recentVideos4) {
        this.recentVideos4 = recentVideos4;
    }

    public Long getRecentVideos5() {
        return recentVideos5;
    }

    public Frontpageconfig recentVideos5(Long recentVideos5) {
        this.recentVideos5 = recentVideos5;
        return this;
    }

    public void setRecentVideos5(Long recentVideos5) {
        this.recentVideos5 = recentVideos5;
    }

    public Long getRecentVideos6() {
        return recentVideos6;
    }

    public Frontpageconfig recentVideos6(Long recentVideos6) {
        this.recentVideos6 = recentVideos6;
        return this;
    }

    public void setRecentVideos6(Long recentVideos6) {
        this.recentVideos6 = recentVideos6;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frontpageconfig frontpageconfig = (Frontpageconfig) o;
        if (frontpageconfig.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), frontpageconfig.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Frontpageconfig{" +
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
