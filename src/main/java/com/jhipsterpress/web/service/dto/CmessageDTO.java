package com.jhipsterpress.web.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Cmessage entity.
 */
public class CmessageDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 8000)
    private String messageText;

    private Boolean isDelivered;


    private Long csenderId;

    private Long creceiverId;

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

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Boolean isIsDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(Boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public Long getCsenderId() {
        return csenderId;
    }

    public void setCsenderId(Long communityId) {
        this.csenderId = communityId;
    }

    public Long getCreceiverId() {
        return creceiverId;
    }

    public void setCreceiverId(Long communityId) {
        this.creceiverId = communityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CmessageDTO cmessageDTO = (CmessageDTO) o;
        if (cmessageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cmessageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CmessageDTO{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", messageText='" + getMessageText() + "'" +
            ", isDelivered='" + isIsDelivered() + "'" +
            ", csender=" + getCsenderId() +
            ", creceiver=" + getCreceiverId() +
            "}";
    }
}
