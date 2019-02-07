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
 * A Vthumb.
 */
@Entity
@Table(name = "vthumb")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vthumb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @Column(name = "vthumb_up")
    private Boolean vthumbUp;

    @Column(name = "vthumb_down")
    private Boolean vthumbDown;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("vthumb(id)S")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("vthumbs")
    private Vquestion vquestion;

    @ManyToOne
    @JsonIgnoreProperties("vthumbs")
    private Vanswer vanswer;

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

    public Vthumb creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean isVthumbUp() {
        return vthumbUp;
    }

    public Vthumb vthumbUp(Boolean vthumbUp) {
        this.vthumbUp = vthumbUp;
        return this;
    }

    public void setVthumbUp(Boolean vthumbUp) {
        this.vthumbUp = vthumbUp;
    }

    public Boolean isVthumbDown() {
        return vthumbDown;
    }

    public Vthumb vthumbDown(Boolean vthumbDown) {
        this.vthumbDown = vthumbDown;
        return this;
    }

    public void setVthumbDown(Boolean vthumbDown) {
        this.vthumbDown = vthumbDown;
    }

    public User getUser() {
        return user;
    }

    public Vthumb user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vquestion getVquestion() {
        return vquestion;
    }

    public Vthumb vquestion(Vquestion vquestion) {
        this.vquestion = vquestion;
        return this;
    }

    public void setVquestion(Vquestion vquestion) {
        this.vquestion = vquestion;
    }

    public Vanswer getVanswer() {
        return vanswer;
    }

    public Vthumb vanswer(Vanswer vanswer) {
        this.vanswer = vanswer;
        return this;
    }

    public void setVanswer(Vanswer vanswer) {
        this.vanswer = vanswer;
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
        Vthumb vthumb = (Vthumb) o;
        if (vthumb.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vthumb.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vthumb{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", vthumbUp='" + isVthumbUp() + "'" +
            ", vthumbDown='" + isVthumbDown() + "'" +
            "}";
    }
}
