package com.jhipsterpress.web.service.dto;

import javax.validation.constraints.NotNull;

import com.jhipsterpress.web.domain.Post;
import com.jhipsterpress.web.domain.Urllink;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the Frontpageconfig entity.
 */
public class CustomFrontpageconfigDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    private Post topNews1;

    private Post topNews2;

    private Post topNews3;

    private Post topNews4;

    private Post topNews5;

    private Post latestNews1;

    private Post latestNews2;

    private Post latestNews3;

    private Post latestNews4;

    private Post latestNews5;

    private Post breakingNews1;

    private Post recentPosts1;

    private Post recentPosts2;

    private Post recentPosts3;

    private Post recentPosts4;

    private Post featuredArticles1;

    private Post featuredArticles2;

    private Post featuredArticles3;

    private Post featuredArticles4;

    private Post featuredArticles5;

    private Post featuredArticles6;

    private Post featuredArticles7;

    private Post featuredArticles8;

    private Post featuredArticles9;

    private Post featuredArticles10;

    private Post popularNews1;

    private Post popularNews2;

    private Post popularNews3;

    private Post popularNews4;

    private Post popularNews5;

    private Post popularNews6;

    private Post popularNews7;

    private Post popularNews8;

    private Post weeklyNews1;

    private Post weeklyNews2;

    private Post weeklyNews3;

    private Post weeklyNews4;

    private Post newsFeeds1;

    private Post newsFeeds2;

    private Post newsFeeds3;

    private Post newsFeeds4;

    private Post newsFeeds5;

    private Post newsFeeds6;

    private Urllink usefulLinks1;

    private Urllink usefulLinks2;

    private Urllink usefulLinks3;

    private Urllink usefulLinks4;

    private Urllink usefulLinks5;

    private Urllink usefulLinks6;

    private Urllink recentVideos1;

    private Urllink recentVideos2;

    private Urllink recentVideos3;

    private Urllink recentVideos4;

    private Urllink recentVideos5;

    private Urllink recentVideos6;

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

    public Post getTopNews1() {
        return topNews1;
    }

    public void setTopNews1(Post topNews1) {
        this.topNews1 = topNews1;
    }

    public Post getTopNews2() {
        return topNews2;
    }

    public void setTopNews2(Post topNews2) {
        this.topNews2 = topNews2;
    }

    public Post getTopNews3() {
        return topNews3;
    }

    public void setTopNews3(Post topNews3) {
        this.topNews3 = topNews3;
    }

    public Post getTopNews4() {
        return topNews4;
    }

    public void setTopNews4(Post topNews4) {
        this.topNews4 = topNews4;
    }

    public Post getTopNews5() {
        return topNews5;
    }

    public void setTopNews5(Post topNews5) {
        this.topNews5 = topNews5;
    }

    public Post getLatestNews1() {
        return latestNews1;
    }

    public void setLatestNews1(Post latestNews1) {
        this.latestNews1 = latestNews1;
    }

    public Post getLatestNews2() {
        return latestNews2;
    }

    public void setLatestNews2(Post latestNews2) {
        this.latestNews2 = latestNews2;
    }

    public Post getLatestNews3() {
        return latestNews3;
    }

    public void setLatestNews3(Post latestNews3) {
        this.latestNews3 = latestNews3;
    }

    public Post getLatestNews4() {
        return latestNews4;
    }

    public void setLatestNews4(Post latestNews4) {
        this.latestNews4 = latestNews4;
    }

    public Post getLatestNews5() {
        return latestNews5;
    }

    public void setLatestNews5(Post latestNews5) {
        this.latestNews5 = latestNews5;
    }

    public Post getBreakingNews1() {
        return breakingNews1;
    }

    public void setBreakingNews1(Post breakingNews1) {
        this.breakingNews1 = breakingNews1;
    }

    public Post getRecentPosts1() {
        return recentPosts1;
    }

    public void setRecentPosts1(Post recentPosts1) {
        this.recentPosts1 = recentPosts1;
    }

    public Post getRecentPosts2() {
        return recentPosts2;
    }

    public void setRecentPosts2(Post recentPosts2) {
        this.recentPosts2 = recentPosts2;
    }

    public Post getRecentPosts3() {
        return recentPosts3;
    }

    public void setRecentPosts3(Post recentPosts3) {
        this.recentPosts3 = recentPosts3;
    }

    public Post getRecentPosts4() {
        return recentPosts4;
    }

    public void setRecentPosts4(Post recentPosts4) {
        this.recentPosts4 = recentPosts4;
    }

    public Post getFeaturedArticles1() {
        return featuredArticles1;
    }

    public void setFeaturedArticles1(Post featuredArticles1) {
        this.featuredArticles1 = featuredArticles1;
    }

    public Post getFeaturedArticles2() {
        return featuredArticles2;
    }

    public void setFeaturedArticles2(Post featuredArticles2) {
        this.featuredArticles2 = featuredArticles2;
    }

    public Post getFeaturedArticles3() {
        return featuredArticles3;
    }

    public void setFeaturedArticles3(Post featuredArticles3) {
        this.featuredArticles3 = featuredArticles3;
    }

    public Post getFeaturedArticles4() {
        return featuredArticles4;
    }

    public void setFeaturedArticles4(Post featuredArticles4) {
        this.featuredArticles4 = featuredArticles4;
    }

    public Post getFeaturedArticles5() {
        return featuredArticles5;
    }

    public void setFeaturedArticles5(Post featuredArticles5) {
        this.featuredArticles5 = featuredArticles5;
    }

    public Post getFeaturedArticles6() {
        return featuredArticles6;
    }

    public void setFeaturedArticles6(Post featuredArticles6) {
        this.featuredArticles6 = featuredArticles6;
    }

    public Post getFeaturedArticles7() {
        return featuredArticles7;
    }

    public void setFeaturedArticles7(Post featuredArticles7) {
        this.featuredArticles7 = featuredArticles7;
    }

    public Post getFeaturedArticles8() {
        return featuredArticles8;
    }

    public void setFeaturedArticles8(Post featuredArticles8) {
        this.featuredArticles8 = featuredArticles8;
    }

    public Post getFeaturedArticles9() {
        return featuredArticles9;
    }

    public void setFeaturedArticles9(Post featuredArticles9) {
        this.featuredArticles9 = featuredArticles9;
    }

    public Post getFeaturedArticles10() {
        return featuredArticles10;
    }

    public void setFeaturedArticles10(Post featuredArticles10) {
        this.featuredArticles10 = featuredArticles10;
    }

    public Post getPopularNews1() {
        return popularNews1;
    }

    public void setPopularNews1(Post popularNews1) {
        this.popularNews1 = popularNews1;
    }

    public Post getPopularNews2() {
        return popularNews2;
    }

    public void setPopularNews2(Post popularNews2) {
        this.popularNews2 = popularNews2;
    }

    public Post getPopularNews3() {
        return popularNews3;
    }

    public void setPopularNews3(Post popularNews3) {
        this.popularNews3 = popularNews3;
    }

    public Post getPopularNews4() {
        return popularNews4;
    }

    public void setPopularNews4(Post popularNews4) {
        this.popularNews4 = popularNews4;
    }

    public Post getPopularNews5() {
        return popularNews5;
    }

    public void setPopularNews5(Post popularNews5) {
        this.popularNews5 = popularNews5;
    }

    public Post getPopularNews6() {
        return popularNews6;
    }

    public void setPopularNews6(Post popularNews6) {
        this.popularNews6 = popularNews6;
    }

    public Post getPopularNews7() {
        return popularNews7;
    }

    public void setPopularNews7(Post popularNews7) {
        this.popularNews7 = popularNews7;
    }

    public Post getPopularNews8() {
        return popularNews8;
    }

    public void setPopularNews8(Post popularNews8) {
        this.popularNews8 = popularNews8;
    }

    public Post getWeeklyNews1() {
        return weeklyNews1;
    }

    public void setWeeklyNews1(Post weeklyNews1) {
        this.weeklyNews1 = weeklyNews1;
    }

    public Post getWeeklyNews2() {
        return weeklyNews2;
    }

    public void setWeeklyNews2(Post weeklyNews2) {
        this.weeklyNews2 = weeklyNews2;
    }

    public Post getWeeklyNews3() {
        return weeklyNews3;
    }

    public void setWeeklyNews3(Post weeklyNews3) {
        this.weeklyNews3 = weeklyNews3;
    }

    public Post getWeeklyNews4() {
        return weeklyNews4;
    }

    public void setWeeklyNews4(Post weeklyNews4) {
        this.weeklyNews4 = weeklyNews4;
    }

    public Post getNewsFeeds1() {
        return newsFeeds1;
    }

    public void setNewsFeeds1(Post newsFeeds1) {
        this.newsFeeds1 = newsFeeds1;
    }

    public Post getNewsFeeds2() {
        return newsFeeds2;
    }

    public void setNewsFeeds2(Post newsFeeds2) {
        this.newsFeeds2 = newsFeeds2;
    }

    public Post getNewsFeeds3() {
        return newsFeeds3;
    }

    public void setNewsFeeds3(Post newsFeeds3) {
        this.newsFeeds3 = newsFeeds3;
    }

    public Post getNewsFeeds4() {
        return newsFeeds4;
    }

    public void setNewsFeeds4(Post newsFeeds4) {
        this.newsFeeds4 = newsFeeds4;
    }

    public Post getNewsFeeds5() {
        return newsFeeds5;
    }

    public void setNewsFeeds5(Post newsFeeds5) {
        this.newsFeeds5 = newsFeeds5;
    }

    public Post getNewsFeeds6() {
        return newsFeeds6;
    }

    public void setNewsFeeds6(Post newsFeeds6) {
        this.newsFeeds6 = newsFeeds6;
    }

//    public Post getUsefulLinks1() {
//        return usefulLinks1;
//    }
//
//    public void setUsefulLinks1(Post usefulLinks1) {
//        this.usefulLinks1 = usefulLinks1;
//    }
//
//    public Post getUsefulLinks2() {
//        return usefulLinks2;
//    }
//
//    public void setUsefulLinks2(Post usefulLinks2) {
//        this.usefulLinks2 = usefulLinks2;
//    }
//
//    public Post getUsefulLinks3() {
//        return usefulLinks3;
//    }
//
//    public void setUsefulLinks3(Post usefulLinks3) {
//        this.usefulLinks3 = usefulLinks3;
//    }
//
//    public Post getUsefulLinks4() {
//        return usefulLinks4;
//    }
//
//    public void setUsefulLinks4(Post usefulLinks4) {
//        this.usefulLinks4 = usefulLinks4;
//    }
//
//    public Post getUsefulLinks5() {
//        return usefulLinks5;
//    }
//
//    public void setUsefulLinks5(Post usefulLinks5) {
//        this.usefulLinks5 = usefulLinks5;
//    }
//
//    public Post getUsefulLinks6() {
//        return usefulLinks6;
//    }
//
//    public void setUsefulLinks6(Post usefulLinks6) {
//        this.usefulLinks6 = usefulLinks6;
//    }
//
//    public Post getRecentVideos1() {
//        return recentVideos1;
//    }
//
//    public void setRecentVideos1(Post recentVideos1) {
//        this.recentVideos1 = recentVideos1;
//    }
//
//    public Post getRecentVideos2() {
//        return recentVideos2;
//    }
//
//    public void setRecentVideos2(Post recentVideos2) {
//        this.recentVideos2 = recentVideos2;
//    }
//
//    public Post getRecentVideos3() {
//        return recentVideos3;
//    }
//
//    public void setRecentVideos3(Post recentVideos3) {
//        this.recentVideos3 = recentVideos3;
//    }
//
//    public Post getRecentVideos4() {
//        return recentVideos4;
//    }
//
//    public void setRecentVideos4(Post recentVideos4) {
//        this.recentVideos4 = recentVideos4;
//    }
//
//    public Post getRecentVideos5() {
//        return recentVideos5;
//    }
//
//    public void setRecentVideos5(Post recentVideos5) {
//        this.recentVideos5 = recentVideos5;
//    }
//
//    public Post getRecentVideos6() {
//        return recentVideos6;
//    }
//
//    public void setRecentVideos6(Post recentVideos6) {
//        this.recentVideos6 = recentVideos6;
//    }

    public Urllink getUsefulLinks1() {
		return usefulLinks1;
	}

	public void setUsefulLinks1(Urllink usefulLinks1) {
		this.usefulLinks1 = usefulLinks1;
	}

	public Urllink getUsefulLinks2() {
		return usefulLinks2;
	}

	public void setUsefulLinks2(Urllink usefulLinks2) {
		this.usefulLinks2 = usefulLinks2;
	}

	public Urllink getUsefulLinks3() {
		return usefulLinks3;
	}

	public void setUsefulLinks3(Urllink usefulLinks3) {
		this.usefulLinks3 = usefulLinks3;
	}

	public Urllink getUsefulLinks4() {
		return usefulLinks4;
	}

	public void setUsefulLinks4(Urllink usefulLinks4) {
		this.usefulLinks4 = usefulLinks4;
	}

	public Urllink getUsefulLinks5() {
		return usefulLinks5;
	}

	public void setUsefulLinks5(Urllink usefulLinks5) {
		this.usefulLinks5 = usefulLinks5;
	}

	public Urllink getUsefulLinks6() {
		return usefulLinks6;
	}

	public void setUsefulLinks6(Urllink usefulLinks6) {
		this.usefulLinks6 = usefulLinks6;
	}

	public Urllink getRecentVideos1() {
		return recentVideos1;
	}

	public void setRecentVideos1(Urllink recentVideos1) {
		this.recentVideos1 = recentVideos1;
	}

	public Urllink getRecentVideos2() {
		return recentVideos2;
	}

	public void setRecentVideos2(Urllink recentVideos2) {
		this.recentVideos2 = recentVideos2;
	}

	public Urllink getRecentVideos3() {
		return recentVideos3;
	}

	public void setRecentVideos3(Urllink recentVideos3) {
		this.recentVideos3 = recentVideos3;
	}

	public Urllink getRecentVideos4() {
		return recentVideos4;
	}

	public void setRecentVideos4(Urllink recentVideos4) {
		this.recentVideos4 = recentVideos4;
	}

	public Urllink getRecentVideos5() {
		return recentVideos5;
	}

	public void setRecentVideos5(Urllink recentVideos5) {
		this.recentVideos5 = recentVideos5;
	}

	public Urllink getRecentVideos6() {
		return recentVideos6;
	}

	public void setRecentVideos6(Urllink recentVideos6) {
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

        CustomFrontpageconfigDTO frontpageconfigDTO = (CustomFrontpageconfigDTO) o;
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
		return "CustomFrontpageconfigDTO [id=" + id + ", creationDate=" + creationDate + ", topNews1=" + topNews1
				+ ", topNews2=" + topNews2 + ", topNews3=" + topNews3 + ", topNews4=" + topNews4 + ", topNews5="
				+ topNews5 + ", latestNews1=" + latestNews1 + ", latestNews2=" + latestNews2 + ", latestNews3="
				+ latestNews3 + ", latestNews4=" + latestNews4 + ", latestNews5=" + latestNews5 + ", breakingNews1="
				+ breakingNews1 + ", recentPosts1=" + recentPosts1 + ", recentPosts2=" + recentPosts2
				+ ", recentPosts3=" + recentPosts3 + ", recentPosts4=" + recentPosts4 + ", featuredArticles1="
				+ featuredArticles1 + ", featuredArticles2=" + featuredArticles2 + ", featuredArticles3="
				+ featuredArticles3 + ", featuredArticles4=" + featuredArticles4 + ", featuredArticles5="
				+ featuredArticles5 + ", featuredArticles6=" + featuredArticles6 + ", featuredArticles7="
				+ featuredArticles7 + ", featuredArticles8=" + featuredArticles8 + ", featuredArticles9="
				+ featuredArticles9 + ", featuredArticles10=" + featuredArticles10 + ", popularNews1=" + popularNews1
				+ ", popularNews2=" + popularNews2 + ", popularNews3=" + popularNews3 + ", popularNews4=" + popularNews4
				+ ", popularNews5=" + popularNews5 + ", popularNews6=" + popularNews6 + ", popularNews7=" + popularNews7
				+ ", popularNews8=" + popularNews8 + ", weeklyNews1=" + weeklyNews1 + ", weeklyNews2=" + weeklyNews2
				+ ", weeklyNews3=" + weeklyNews3 + ", weeklyNews4=" + weeklyNews4 + ", newsFeeds1=" + newsFeeds1
				+ ", newsFeeds2=" + newsFeeds2 + ", newsFeeds3=" + newsFeeds3 + ", newsFeeds4=" + newsFeeds4
				+ ", newsFeeds5=" + newsFeeds5 + ", newsFeeds6=" + newsFeeds6 + ", usefulLinks1=" + usefulLinks1
				+ ", usefulLinks2=" + usefulLinks2 + ", usefulLinks3=" + usefulLinks3 + ", usefulLinks4=" + usefulLinks4
				+ ", usefulLinks5=" + usefulLinks5 + ", usefulLinks6=" + usefulLinks6 + ", recentVideos1="
				+ recentVideos1 + ", recentVideos2=" + recentVideos2 + ", recentVideos3=" + recentVideos3
				+ ", recentVideos4=" + recentVideos4 + ", recentVideos5=" + recentVideos5 + ", recentVideos6="
				+ recentVideos6 + "]";
	}
    
//    @Override
//    public String toString() {
//        return "FrontpageconfigDTO{" +
//            "id=" + getId() +
//            ", creationDate='" + getCreationDate() + "'" +
//            ", topNews1=" + getTopNews1() +
//            ", topNews2=" + getTopNews2() +
//            ", topNews3=" + getTopNews3() +
//            ", topNews4=" + getTopNews4() +
//            ", topNews5=" + getTopNews5() +
//            ", latestNews1=" + getLatestNews1() +
//            ", latestNews2=" + getLatestNews2() +
//            ", latestNews3=" + getLatestNews3() +
//            ", latestNews4=" + getLatestNews4() +
//            ", latestNews5=" + getLatestNews5() +
//            ", breakingNews1=" + getBreakingNews1() +
//            ", recentPosts1=" + getRecentPosts1() +
//            ", recentPosts2=" + getRecentPosts2() +
//            ", recentPosts3=" + getRecentPosts3() +
//            ", recentPosts4=" + getRecentPosts4() +
//            ", featuredArticles1=" + getFeaturedArticles1() +
//            ", featuredArticles2=" + getFeaturedArticles2() +
//            ", featuredArticles3=" + getFeaturedArticles3() +
//            ", featuredArticles4=" + getFeaturedArticles4() +
//            ", featuredArticles5=" + getFeaturedArticles5() +
//            ", featuredArticles6=" + getFeaturedArticles6() +
//            ", featuredArticles7=" + getFeaturedArticles7() +
//            ", featuredArticles8=" + getFeaturedArticles8() +
//            ", featuredArticles9=" + getFeaturedArticles9() +
//            ", featuredArticles10=" + getFeaturedArticles10() +
//            ", popularNews1=" + getPopularNews1() +
//            ", popularNews2=" + getPopularNews2() +
//            ", popularNews3=" + getPopularNews3() +
//            ", popularNews4=" + getPopularNews4() +
//            ", popularNews5=" + getPopularNews5() +
//            ", popularNews6=" + getPopularNews6() +
//            ", popularNews7=" + getPopularNews7() +
//            ", popularNews8=" + getPopularNews8() +
//            ", weeklyNews1=" + getWeeklyNews1() +
//            ", weeklyNews2=" + getWeeklyNews2() +
//            ", weeklyNews3=" + getWeeklyNews3() +
//            ", weeklyNews4=" + getWeeklyNews4() +
//            ", newsFeeds1=" + getNewsFeeds1() +
//            ", newsFeeds2=" + getNewsFeeds2() +
//            ", newsFeeds3=" + getNewsFeeds3() +
//            ", newsFeeds4=" + getNewsFeeds4() +
//            ", newsFeeds5=" + getNewsFeeds5() +
//            ", newsFeeds6=" + getNewsFeeds6() +
//            ", usefulLinks1=" + getUsefulLinks1() +
//            ", usefulLinks2=" + getUsefulLinks2() +
//            ", usefulLinks3=" + getUsefulLinks3() +
//            ", usefulLinks4=" + getUsefulLinks4() +
//            ", usefulLinks5=" + getUsefulLinks5() +
//            ", usefulLinks6=" + getUsefulLinks6() +
//            ", recentVideos1=" + getRecentVideos1() +
//            ", recentVideos2=" + getRecentVideos2() +
//            ", recentVideos3=" + getRecentVideos3() +
//            ", recentVideos4=" + getRecentVideos4() +
//            ", recentVideos5=" + getRecentVideos5() +
//            ", recentVideos6=" + getRecentVideos6() +
//            "}";
//    }
}
