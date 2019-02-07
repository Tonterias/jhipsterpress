package com.jhipsterpress.web.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Follow.
 */
@Entity
@Table(name = "follow")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "creation_date")
    private Instant creationDate;

    @ManyToOne
    @JsonIgnoreProperties("followed(id)S")
    private User followed;

    @ManyToOne
    @JsonIgnoreProperties("following(id)S")
    private User following;

    @ManyToOne
    @JsonIgnoreProperties("cfolloweds")
    private Community cfollowed;

    @ManyToOne
    @JsonIgnoreProperties("cfollowings")
    private Community cfollowing;

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

    public Follow creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public User getFollowed() {
        return followed;
    }

    public Follow followed(User user) {
        this.followed = user;
        return this;
    }

    public void setFollowed(User user) {
        this.followed = user;
    }

    public User getFollowing() {
        return following;
    }

    public Follow following(User user) {
        this.following = user;
        return this;
    }

    public void setFollowing(User user) {
        this.following = user;
    }

    public Community getCfollowed() {
        return cfollowed;
    }

    public Follow cfollowed(Community community) {
        this.cfollowed = community;
        return this;
    }

    public void setCfollowed(Community community) {
        this.cfollowed = community;
    }

    public Community getCfollowing() {
        return cfollowing;
    }

    public Follow cfollowing(Community community) {
        this.cfollowing = community;
        return this;
    }

    public void setCfollowing(Community community) {
        this.cfollowing = community;
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
        Follow follow = (Follow) o;
        if (follow.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), follow.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Follow{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
