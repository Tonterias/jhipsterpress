package com.jhipsterpress.web.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Cmessage.
 */
@Entity
@Table(name = "cmessage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cmessage implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 8000)
    @Column(name = "message_text", length = 8000, nullable = false)
    private String messageText;

    @Column(name = "is_delivered")
    private Boolean isDelivered;

    @ManyToOne
    @JsonIgnoreProperties("csenders")
    private Community csender;

    @ManyToOne
    @JsonIgnoreProperties("creceivers")
    private Community creceiver;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Cmessage creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessageText() {
        return messageText;
    }

    public Cmessage messageText(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Boolean isIsDelivered() {
        return isDelivered;
    }

    public Cmessage isDelivered(Boolean isDelivered) {
        this.isDelivered = isDelivered;
        return this;
    }

    public void setIsDelivered(Boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public Community getCsender() {
        return csender;
    }

    public Cmessage csender(Community community) {
        this.csender = community;
        return this;
    }

    public void setCsender(Community community) {
        this.csender = community;
    }

    public Community getCreceiver() {
        return creceiver;
    }

    public Cmessage creceiver(Community community) {
        this.creceiver = community;
        return this;
    }

    public void setCreceiver(Community community) {
        this.creceiver = community;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cmessage cmessage = (Cmessage) o;
        if (cmessage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cmessage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cmessage{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", messageText='" + getMessageText() + "'" +
            ", isDelivered='" + isIsDelivered() + "'" +
            "}";
    }
}
