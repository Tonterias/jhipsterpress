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
 * Criteria class for the Vthumb entity. This class is used in VthumbResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /vthumbs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VthumbCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter creationDate;

    private BooleanFilter vthumbUp;

    private BooleanFilter vthumbDown;

    private LongFilter userId;

    private LongFilter vquestionId;

    private LongFilter vanswerId;

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

    public BooleanFilter getVthumbUp() {
        return vthumbUp;
    }

    public void setVthumbUp(BooleanFilter vthumbUp) {
        this.vthumbUp = vthumbUp;
    }

    public BooleanFilter getVthumbDown() {
        return vthumbDown;
    }

    public void setVthumbDown(BooleanFilter vthumbDown) {
        this.vthumbDown = vthumbDown;
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

    public LongFilter getVanswerId() {
        return vanswerId;
    }

    public void setVanswerId(LongFilter vanswerId) {
        this.vanswerId = vanswerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VthumbCriteria that = (VthumbCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(creationDate, that.creationDate) &&
            Objects.equals(vthumbUp, that.vthumbUp) &&
            Objects.equals(vthumbDown, that.vthumbDown) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(vquestionId, that.vquestionId) &&
            Objects.equals(vanswerId, that.vanswerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        creationDate,
        vthumbUp,
        vthumbDown,
        userId,
        vquestionId,
        vanswerId
        );
    }

    @Override
    public String toString() {
        return "VthumbCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (creationDate != null ? "creationDate=" + creationDate + ", " : "") +
                (vthumbUp != null ? "vthumbUp=" + vthumbUp + ", " : "") +
                (vthumbDown != null ? "vthumbDown=" + vthumbDown + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (vquestionId != null ? "vquestionId=" + vquestionId + ", " : "") +
                (vanswerId != null ? "vanswerId=" + vanswerId + ", " : "") +
            "}";
    }

}
