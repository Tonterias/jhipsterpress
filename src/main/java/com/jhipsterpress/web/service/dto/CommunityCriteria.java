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
 * Criteria class for the Community entity. This class is used in CommunityResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /communities?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CommunityCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter creationDate;

    private StringFilter communityName;

    private StringFilter communityDescription;

    private BooleanFilter isActive;

    private LongFilter blogId;

    private LongFilter csenderId;

    private LongFilter creceiverId;

    private LongFilter cfollowedId;

    private LongFilter cfollowingId;

    private LongFilter cblockeduserId;

    private LongFilter cblockinguserId;

    private LongFilter userId;

    private LongFilter calbumId;

    private LongFilter cinterestId;

    private LongFilter cactivityId;

    private LongFilter ccelebId;

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

    public StringFilter getCommunityName() {
        return communityName;
    }

    public void setCommunityName(StringFilter communityName) {
        this.communityName = communityName;
    }

    public StringFilter getCommunityDescription() {
        return communityDescription;
    }

    public void setCommunityDescription(StringFilter communityDescription) {
        this.communityDescription = communityDescription;
    }

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
    }

    public LongFilter getBlogId() {
        return blogId;
    }

    public void setBlogId(LongFilter blogId) {
        this.blogId = blogId;
    }

    public LongFilter getCsenderId() {
        return csenderId;
    }

    public void setCsenderId(LongFilter csenderId) {
        this.csenderId = csenderId;
    }

    public LongFilter getCreceiverId() {
        return creceiverId;
    }

    public void setCreceiverId(LongFilter creceiverId) {
        this.creceiverId = creceiverId;
    }

    public LongFilter getCfollowedId() {
        return cfollowedId;
    }

    public void setCfollowedId(LongFilter cfollowedId) {
        this.cfollowedId = cfollowedId;
    }

    public LongFilter getCfollowingId() {
        return cfollowingId;
    }

    public void setCfollowingId(LongFilter cfollowingId) {
        this.cfollowingId = cfollowingId;
    }

    public LongFilter getCblockeduserId() {
        return cblockeduserId;
    }

    public void setCblockeduserId(LongFilter cblockeduserId) {
        this.cblockeduserId = cblockeduserId;
    }

    public LongFilter getCblockinguserId() {
        return cblockinguserId;
    }

    public void setCblockinguserId(LongFilter cblockinguserId) {
        this.cblockinguserId = cblockinguserId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getCalbumId() {
        return calbumId;
    }

    public void setCalbumId(LongFilter calbumId) {
        this.calbumId = calbumId;
    }

    public LongFilter getCinterestId() {
        return cinterestId;
    }

    public void setCinterestId(LongFilter cinterestId) {
        this.cinterestId = cinterestId;
    }

    public LongFilter getCactivityId() {
        return cactivityId;
    }

    public void setCactivityId(LongFilter cactivityId) {
        this.cactivityId = cactivityId;
    }

    public LongFilter getCcelebId() {
        return ccelebId;
    }

    public void setCcelebId(LongFilter ccelebId) {
        this.ccelebId = ccelebId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CommunityCriteria that = (CommunityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(creationDate, that.creationDate) &&
            Objects.equals(communityName, that.communityName) &&
            Objects.equals(communityDescription, that.communityDescription) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(blogId, that.blogId) &&
            Objects.equals(csenderId, that.csenderId) &&
            Objects.equals(creceiverId, that.creceiverId) &&
            Objects.equals(cfollowedId, that.cfollowedId) &&
            Objects.equals(cfollowingId, that.cfollowingId) &&
            Objects.equals(cblockeduserId, that.cblockeduserId) &&
            Objects.equals(cblockinguserId, that.cblockinguserId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(calbumId, that.calbumId) &&
            Objects.equals(cinterestId, that.cinterestId) &&
            Objects.equals(cactivityId, that.cactivityId) &&
            Objects.equals(ccelebId, that.ccelebId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        creationDate,
        communityName,
        communityDescription,
        isActive,
        blogId,
        csenderId,
        creceiverId,
        cfollowedId,
        cfollowingId,
        cblockeduserId,
        cblockinguserId,
        userId,
        calbumId,
        cinterestId,
        cactivityId,
        ccelebId
        );
    }

    @Override
    public String toString() {
        return "CommunityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (creationDate != null ? "creationDate=" + creationDate + ", " : "") +
                (communityName != null ? "communityName=" + communityName + ", " : "") +
                (communityDescription != null ? "communityDescription=" + communityDescription + ", " : "") +
                (isActive != null ? "isActive=" + isActive + ", " : "") +
                (blogId != null ? "blogId=" + blogId + ", " : "") +
                (csenderId != null ? "csenderId=" + csenderId + ", " : "") +
                (creceiverId != null ? "creceiverId=" + creceiverId + ", " : "") +
                (cfollowedId != null ? "cfollowedId=" + cfollowedId + ", " : "") +
                (cfollowingId != null ? "cfollowingId=" + cfollowingId + ", " : "") +
                (cblockeduserId != null ? "cblockeduserId=" + cblockeduserId + ", " : "") +
                (cblockinguserId != null ? "cblockinguserId=" + cblockinguserId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (calbumId != null ? "calbumId=" + calbumId + ", " : "") +
                (cinterestId != null ? "cinterestId=" + cinterestId + ", " : "") +
                (cactivityId != null ? "cactivityId=" + cactivityId + ", " : "") +
                (ccelebId != null ? "ccelebId=" + ccelebId + ", " : "") +
            "}";
    }

}
