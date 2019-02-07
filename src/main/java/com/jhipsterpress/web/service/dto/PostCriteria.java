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
 * Criteria class for the Post entity. This class is used in PostResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /posts?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PostCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter creationDate;

    private InstantFilter publicationDate;

    private StringFilter headline;

    private StringFilter leadtext;

    private StringFilter bodytext;

    private StringFilter quote;

    private StringFilter conclusion;

    private StringFilter linkText;

    private StringFilter linkURL;

    private LongFilter commentId;

    private LongFilter userId;

    private LongFilter blogId;

    private LongFilter tagId;

    private LongFilter topicId;

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

    public InstantFilter getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(InstantFilter publicationDate) {
        this.publicationDate = publicationDate;
    }

    public StringFilter getHeadline() {
        return headline;
    }

    public void setHeadline(StringFilter headline) {
        this.headline = headline;
    }

    public StringFilter getLeadtext() {
        return leadtext;
    }

    public void setLeadtext(StringFilter leadtext) {
        this.leadtext = leadtext;
    }

    public StringFilter getBodytext() {
        return bodytext;
    }

    public void setBodytext(StringFilter bodytext) {
        this.bodytext = bodytext;
    }

    public StringFilter getQuote() {
        return quote;
    }

    public void setQuote(StringFilter quote) {
        this.quote = quote;
    }

    public StringFilter getConclusion() {
        return conclusion;
    }

    public void setConclusion(StringFilter conclusion) {
        this.conclusion = conclusion;
    }

    public StringFilter getLinkText() {
        return linkText;
    }

    public void setLinkText(StringFilter linkText) {
        this.linkText = linkText;
    }

    public StringFilter getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(StringFilter linkURL) {
        this.linkURL = linkURL;
    }

    public LongFilter getCommentId() {
        return commentId;
    }

    public void setCommentId(LongFilter commentId) {
        this.commentId = commentId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getBlogId() {
        return blogId;
    }

    public void setBlogId(LongFilter blogId) {
        this.blogId = blogId;
    }

    public LongFilter getTagId() {
        return tagId;
    }

    public void setTagId(LongFilter tagId) {
        this.tagId = tagId;
    }

    public LongFilter getTopicId() {
        return topicId;
    }

    public void setTopicId(LongFilter topicId) {
        this.topicId = topicId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PostCriteria that = (PostCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(creationDate, that.creationDate) &&
            Objects.equals(publicationDate, that.publicationDate) &&
            Objects.equals(headline, that.headline) &&
            Objects.equals(leadtext, that.leadtext) &&
            Objects.equals(bodytext, that.bodytext) &&
            Objects.equals(quote, that.quote) &&
            Objects.equals(conclusion, that.conclusion) &&
            Objects.equals(linkText, that.linkText) &&
            Objects.equals(linkURL, that.linkURL) &&
            Objects.equals(commentId, that.commentId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(blogId, that.blogId) &&
            Objects.equals(tagId, that.tagId) &&
            Objects.equals(topicId, that.topicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        creationDate,
        publicationDate,
        headline,
        leadtext,
        bodytext,
        quote,
        conclusion,
        linkText,
        linkURL,
        commentId,
        userId,
        blogId,
        tagId,
        topicId
        );
    }

    @Override
    public String toString() {
        return "PostCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (creationDate != null ? "creationDate=" + creationDate + ", " : "") +
                (publicationDate != null ? "publicationDate=" + publicationDate + ", " : "") +
                (headline != null ? "headline=" + headline + ", " : "") +
                (leadtext != null ? "leadtext=" + leadtext + ", " : "") +
                (bodytext != null ? "bodytext=" + bodytext + ", " : "") +
                (quote != null ? "quote=" + quote + ", " : "") +
                (conclusion != null ? "conclusion=" + conclusion + ", " : "") +
                (linkText != null ? "linkText=" + linkText + ", " : "") +
                (linkURL != null ? "linkURL=" + linkURL + ", " : "") +
                (commentId != null ? "commentId=" + commentId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (blogId != null ? "blogId=" + blogId + ", " : "") +
                (tagId != null ? "tagId=" + tagId + ", " : "") +
                (topicId != null ? "topicId=" + topicId + ", " : "") +
            "}";
    }

}
