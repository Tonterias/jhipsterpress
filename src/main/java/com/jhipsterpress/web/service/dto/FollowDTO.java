package com.jhipsterpress.web.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Follow entity.
 */
public class FollowDTO implements Serializable {

    private Long id;

    private Instant creationDate;


    private Long followedId;

    private Long followingId;

    private Long cfollowedId;

    private Long cfollowingId;

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

    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long userId) {
        this.followedId = userId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long userId) {
        this.followingId = userId;
    }

    public Long getCfollowedId() {
        return cfollowedId;
    }

    public void setCfollowedId(Long communityId) {
        this.cfollowedId = communityId;
    }

    public Long getCfollowingId() {
        return cfollowingId;
    }

    public void setCfollowingId(Long communityId) {
        this.cfollowingId = communityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FollowDTO followDTO = (FollowDTO) o;
        if (followDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), followDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FollowDTO{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", followed=" + getFollowedId() +
            ", following=" + getFollowingId() +
            ", cfollowed=" + getCfollowedId() +
            ", cfollowing=" + getCfollowingId() +
            "}";
    }
}
