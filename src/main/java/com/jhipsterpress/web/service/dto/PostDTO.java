package com.jhipsterpress.web.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Lob;

/**
 * A DTO for the Post entity.
 */
public class PostDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    private Instant publicationDate;

    @NotNull
    @Size(min = 2, max = 100)
    private String headline;

    @Size(min = 2, max = 1000)
    private String leadtext;

    @NotNull
    @Size(min = 2, max = 65000)
    private String bodytext;

    @Size(min = 2, max = 1000)
    private String quote;

    @Size(min = 2, max = 2000)
    private String conclusion;

    @Size(min = 2, max = 1000)
    private String linkText;

    @Size(min = 2, max = 1000)
    private String linkURL;

    @Lob
    private byte[] image;

    private String imageContentType;

    private Long userId;

    private Long blogId;

    private String blogTitle;
    
    private String userLogin;
    
    private String userFirstName;
    
    private String userLastName;
    
    private Set<CustomTagDTO> tags;
    
    private Set<CustomTopicDTO> topics;
    
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

    public Instant getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getLeadtext() {
        return leadtext;
    }

    public void setLeadtext(String leadtext) {
        this.leadtext = leadtext;
    }

    public String getBodytext() {
        return bodytext;
    }

    public void setBodytext(String bodytext) {
        this.bodytext = bodytext;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public Set<CustomTagDTO> getTags() {
		return tags;
	}

	public void setTags(Set<CustomTagDTO> tags) {
		this.tags = tags;
	}

	public Set<CustomTopicDTO> getTopics() {
		return topics;
	}

	public void setTopics(Set<CustomTopicDTO> topics) {
		this.topics = topics;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PostDTO postDTO = (PostDTO) o;
        if (postDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), postDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", creationDate=" + creationDate + ", publicationDate=" + publicationDate
				+ ", headline=" + headline + ", leadtext=" + leadtext + ", bodytext=" + bodytext + ", quote=" + quote
				+ ", conclusion=" + conclusion + ", linkText=" + linkText + ", linkURL=" + linkURL + ", image="
				+ Arrays.toString(image) + ", imageContentType=" + imageContentType + ", userId=" + userId + ", blogId="
				+ blogId + ", blogTitle=" + blogTitle + ", userLogin=" + userLogin + ", userFirstName=" + userFirstName
				+ ", userLastName=" + userLastName + ", tags=" + tags + ", topics=" + topics + "]";
	}
}
