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

/**
 * Criteria class for the Urllink entity. This class is used in UrllinkResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /urllinks?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UrllinkCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter linkText;

    private StringFilter linkURL;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UrllinkCriteria that = (UrllinkCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(linkText, that.linkText) &&
            Objects.equals(linkURL, that.linkURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        linkText,
        linkURL
        );
    }

    @Override
    public String toString() {
        return "UrllinkCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (linkText != null ? "linkText=" + linkText + ", " : "") +
                (linkURL != null ? "linkURL=" + linkURL + ", " : "") +
            "}";
    }

}
