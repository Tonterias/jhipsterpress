package com.jhipsterpress.web.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Newsletter entity.
 */
public class NewsletterDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    private String email;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NewsletterDTO newsletterDTO = (NewsletterDTO) o;
        if (newsletterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), newsletterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NewsletterDTO{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
