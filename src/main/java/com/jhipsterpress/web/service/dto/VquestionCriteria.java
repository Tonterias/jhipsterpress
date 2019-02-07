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
 * Criteria class for the Vquestion entity. This class is used in VquestionResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /vquestions?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VquestionCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter creationDate;

    private StringFilter vquestion;

    private StringFilter vquestionDescription;

    private LongFilter vanswerId;

    private LongFilter vthumbId;

    private LongFilter userId;

    private LongFilter vtopicId;

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

    public StringFilter getVquestion() {
        return vquestion;
    }

    public void setVquestion(StringFilter vquestion) {
        this.vquestion = vquestion;
    }

    public StringFilter getVquestionDescription() {
        return vquestionDescription;
    }

    public void setVquestionDescription(StringFilter vquestionDescription) {
        this.vquestionDescription = vquestionDescription;
    }

    public LongFilter getVanswerId() {
        return vanswerId;
    }

    public void setVanswerId(LongFilter vanswerId) {
        this.vanswerId = vanswerId;
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

    public LongFilter getVtopicId() {
        return vtopicId;
    }

    public void setVtopicId(LongFilter vtopicId) {
        this.vtopicId = vtopicId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VquestionCriteria that = (VquestionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(creationDate, that.creationDate) &&
            Objects.equals(vquestion, that.vquestion) &&
            Objects.equals(vquestionDescription, that.vquestionDescription) &&
            Objects.equals(vanswerId, that.vanswerId) &&
            Objects.equals(vthumbId, that.vthumbId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(vtopicId, that.vtopicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        creationDate,
        vquestion,
        vquestionDescription,
        vanswerId,
        vthumbId,
        userId,
        vtopicId
        );
    }

    @Override
    public String toString() {
        return "VquestionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (creationDate != null ? "creationDate=" + creationDate + ", " : "") +
                (vquestion != null ? "vquestion=" + vquestion + ", " : "") +
                (vquestionDescription != null ? "vquestionDescription=" + vquestionDescription + ", " : "") +
                (vanswerId != null ? "vanswerId=" + vanswerId + ", " : "") +
                (vthumbId != null ? "vthumbId=" + vthumbId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (vtopicId != null ? "vtopicId=" + vtopicId + ", " : "") +
            "}";
    }

}
