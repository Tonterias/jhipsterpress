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
 * Criteria class for the Vanswer entity. This class is used in VanswerResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /vanswers?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VanswerCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter creationDate;

    private StringFilter urlVanswer;

    private BooleanFilter accepted;

    private LongFilter vthumbId;

    private LongFilter userId;

    private LongFilter vquestionId;

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

    public StringFilter getUrlVanswer() {
        return urlVanswer;
    }

    public void setUrlVanswer(StringFilter urlVanswer) {
        this.urlVanswer = urlVanswer;
    }

    public BooleanFilter getAccepted() {
        return accepted;
    }

    public void setAccepted(BooleanFilter accepted) {
        this.accepted = accepted;
    }

    public LongFilter getVthumbId() {
        return vthumbId;
    }

    public void setVthumbId(LongFilter vthumbId) {
        this.vthumbId = vthumbId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getVquestionId() {
        return vquestionId;
    }

    public void setVquestionId(LongFilter vquestionId) {
        this.vquestionId = vquestionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VanswerCriteria that = (VanswerCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(creationDate, that.creationDate) &&
            Objects.equals(urlVanswer, that.urlVanswer) &&
            Objects.equals(accepted, that.accepted) &&
            Objects.equals(vthumbId, that.vthumbId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(vquestionId, that.vquestionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        creationDate,
        urlVanswer,
        accepted,
        vthumbId,
        userId,
        vquestionId
        );
    }

    @Override
    public String toString() {
        return "VanswerCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (creationDate != null ? "creationDate=" + creationDate + ", " : "") +
                (urlVanswer != null ? "urlVanswer=" + urlVanswer + ", " : "") +
                (accepted != null ? "accepted=" + accepted + ", " : "") +
                (vthumbId != null ? "vthumbId=" + vthumbId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (vquestionId != null ? "vquestionId=" + vquestionId + ", " : "") +
            "}";
    }

}
