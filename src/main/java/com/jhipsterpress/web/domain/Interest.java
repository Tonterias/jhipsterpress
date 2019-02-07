package com.jhipsterpress.web.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Interest.
 */
@Entity
@Table(name = "interest")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Interest implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 40)
    @Column(name = "interest_name", length = 40, nullable = false)
    private String interestName;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "interest_uprofile",
               joinColumns = @JoinColumn(name = "interest_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "uprofile_id", referencedColumnName = "id"))
    private Set<Uprofile> uprofiles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterestName() {
        return interestName;
    }

    public Interest interestName(String interestName) {
        this.interestName = interestName;
        return this;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public Set<Uprofile> getUprofiles() {
        return uprofiles;
    }

    public Interest uprofiles(Set<Uprofile> uprofiles) {
        this.uprofiles = uprofiles;
        return this;
    }

    public Interest addUprofile(Uprofile uprofile) {
        this.uprofiles.add(uprofile);
//        uprofile.getInterest(interestName)S().add(this);
        uprofile.getInterests().add(this);
        return this;
    }

    public Interest removeUprofile(Uprofile uprofile) {
        this.uprofiles.remove(uprofile);
//        uprofile.getInterest(interestName)S().remove(this);
        uprofile.getInterests().remove(this);
        return this;
    }

    public void setUprofiles(Set<Uprofile> uprofiles) {
        this.uprofiles = uprofiles;
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
        Interest interest = (Interest) o;
        if (interest.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), interest.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Interest{" +
            "id=" + getId() +
            ", interestName='" + getInterestName() + "'" +
            "}";
    }
}
