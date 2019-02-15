package com.jhipsterpress.web.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Vquestion.
 */
@Entity
@Table(name = "vquestion")
@Cache(usage = CacheConcurrencyStrategy.NONE)
public class Vquestion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "vquestion", length = 100, nullable = false)
    private String vquestion;

    @Size(min = 2, max = 250)
    @Column(name = "vquestion_description", length = 250)
    private String vquestionDescription;

    @OneToMany(mappedBy = "vquestion", cascade = CascadeType.REMOVE)
//    @Cache(usage = CacheConcurrencyStrategy.NONE)
    private Set<Vanswer> vanswers = new HashSet<>();
    
    @OneToMany(mappedBy = "vquestion", cascade = CascadeType.REMOVE)
//    @Cache(usage = CacheConcurrencyStrategy.NONE)
    private Set<Vthumb> vthumbs = new HashSet<>();
    
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("vquestion(id)S")
    private User user;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("vquestions")
    private Vtopic vtopic;

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

    public Vquestion creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getVquestion() {
        return vquestion;
    }

    public Vquestion vquestion(String vquestion) {
        this.vquestion = vquestion;
        return this;
    }

    public void setVquestion(String vquestion) {
        this.vquestion = vquestion;
    }

    public String getVquestionDescription() {
        return vquestionDescription;
    }

    public Vquestion vquestionDescription(String vquestionDescription) {
        this.vquestionDescription = vquestionDescription;
        return this;
    }

    public void setVquestionDescription(String vquestionDescription) {
        this.vquestionDescription = vquestionDescription;
    }

    public Set<Vanswer> getVanswers() {
        return vanswers;
    }

    public Vquestion vanswers(Set<Vanswer> vanswers) {
        this.vanswers = vanswers;
        return this;
    }

    public Vquestion addVanswer(Vanswer vanswer) {
        this.vanswers.add(vanswer);
        vanswer.setVquestion(this);
        return this;
    }

    public Vquestion removeVanswer(Vanswer vanswer) {
        this.vanswers.remove(vanswer);
        vanswer.setVquestion(null);
        return this;
    }

    public void setVanswers(Set<Vanswer> vanswers) {
        this.vanswers = vanswers;
    }

    public Set<Vthumb> getVthumbs() {
        return vthumbs;
    }

    public Vquestion vthumbs(Set<Vthumb> vthumbs) {
        this.vthumbs = vthumbs;
        return this;
    }

    public Vquestion addVthumb(Vthumb vthumb) {
        this.vthumbs.add(vthumb);
        vthumb.setVquestion(this);
        return this;
    }

    public Vquestion removeVthumb(Vthumb vthumb) {
        this.vthumbs.remove(vthumb);
        vthumb.setVquestion(null);
        return this;
    }

    public void setVthumbs(Set<Vthumb> vthumbs) {
        this.vthumbs = vthumbs;
    }

    public User getUser() {
        return user;
    }

    public Vquestion user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vtopic getVtopic() {
        return vtopic;
    }

    public Vquestion vtopic(Vtopic vtopic) {
        this.vtopic = vtopic;
        return this;
    }

    public void setVtopic(Vtopic vtopic) {
        this.vtopic = vtopic;
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
        Vquestion vquestion = (Vquestion) o;
        if (vquestion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vquestion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vquestion{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", vquestion='" + getVquestion() + "'" +
            ", vquestionDescription='" + getVquestionDescription() + "'" +
            "}";
    }
}
