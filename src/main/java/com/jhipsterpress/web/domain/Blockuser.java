package com.jhipsterpress.web.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Blockuser.
 */
@Entity
@Table(name = "blockuser")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Blockuser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "creation_date")
    private Instant creationDate;

    @ManyToOne
    @JsonIgnoreProperties("blockeduser(id)S")
    private User blockeduser;

    @ManyToOne
    @JsonIgnoreProperties("blockinguser(id)S")
    private User blockinguser;

    @ManyToOne
    @JsonIgnoreProperties("cblockedusers")
    private Community cblockeduser;

    @ManyToOne
    @JsonIgnoreProperties("cblockingusers")
    private Community cblockinguser;

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

    public Blockuser creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public User getBlockeduser() {
        return blockeduser;
    }

    public Blockuser blockeduser(User user) {
        this.blockeduser = user;
        return this;
    }

    public void setBlockeduser(User user) {
        this.blockeduser = user;
    }

    public User getBlockinguser() {
        return blockinguser;
    }

    public Blockuser blockinguser(User user) {
        this.blockinguser = user;
        return this;
    }

    public void setBlockinguser(User user) {
        this.blockinguser = user;
    }

    public Community getCblockeduser() {
        return cblockeduser;
    }

    public Blockuser cblockeduser(Community community) {
        this.cblockeduser = community;
        return this;
    }

    public void setCblockeduser(Community community) {
        this.cblockeduser = community;
    }

    public Community getCblockinguser() {
        return cblockinguser;
    }

    public Blockuser cblockinguser(Community community) {
        this.cblockinguser = community;
        return this;
    }

    public void setCblockinguser(Community community) {
        this.cblockinguser = community;
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
        Blockuser blockuser = (Blockuser) o;
        if (blockuser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), blockuser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Blockuser{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
