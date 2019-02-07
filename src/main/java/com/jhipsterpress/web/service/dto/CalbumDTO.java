package com.jhipsterpress.web.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Calbum entity.
 */
public class CalbumDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 100)
    private String title;


    private Long communityId;

    private String communityCommunityName;

    private Long userId;
    
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getCommunityCommunityName() {
        return communityCommunityName;
    }

    public void setCommunityCommunityName(String communityCommunityName) {
        this.communityCommunityName = communityCommunityName;
    }

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CalbumDTO calbumDTO = (CalbumDTO) o;
        if (calbumDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), calbumDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "CalbumDTO [id=" + id + ", creationDate=" + creationDate + ", title=" + title + ", communityId="
				+ communityId + ", communityCommunityName=" + communityCommunityName + ", userId=" + userId + "]";
	}
}
