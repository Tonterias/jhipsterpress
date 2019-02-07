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
 * A Community.
 */
@Entity
@Table(name = "community")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Community implements Serializable {

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
    @Column(name = "community_name", length = 100, nullable = false)
    private String communityName;

    @NotNull
    @Size(min = 2, max = 7500)
    @Column(name = "community_description", length = 7500, nullable = false)
    private String communityDescription;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "community")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Blog> blogs = new HashSet<>();
    @OneToMany(mappedBy = "csender")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Cmessage> csenders = new HashSet<>();
    @OneToMany(mappedBy = "creceiver")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Cmessage> creceivers = new HashSet<>();
    @OneToMany(mappedBy = "cfollowed")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Follow> cfolloweds = new HashSet<>();
    @OneToMany(mappedBy = "cfollowing")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Follow> cfollowings = new HashSet<>();
    @OneToMany(mappedBy = "cblockeduser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Blockuser> cblockedusers = new HashSet<>();
    @OneToMany(mappedBy = "cblockinguser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Blockuser> cblockingusers = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("communities")
    private User user;

    @OneToMany(mappedBy = "community")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Calbum> calbums = new HashSet<>();
    @ManyToMany(mappedBy = "communities")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Cinterest> cinterests = new HashSet<>();

    @ManyToMany(mappedBy = "communities")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Cactivity> cactivities = new HashSet<>();

    @ManyToMany(mappedBy = "communities")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Cceleb> ccelebs = new HashSet<>();

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

    public Community creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getCommunityName() {
        return communityName;
    }

    public Community communityName(String communityName) {
        this.communityName = communityName;
        return this;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityDescription() {
        return communityDescription;
    }

    public Community communityDescription(String communityDescription) {
        this.communityDescription = communityDescription;
        return this;
    }

    public void setCommunityDescription(String communityDescription) {
        this.communityDescription = communityDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public Community image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Community imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Community isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public Community blogs(Set<Blog> blogs) {
        this.blogs = blogs;
        return this;
    }

    public Community addBlog(Blog blog) {
        this.blogs.add(blog);
        blog.setCommunity(this);
        return this;
    }

    public Community removeBlog(Blog blog) {
        this.blogs.remove(blog);
        blog.setCommunity(null);
        return this;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }

    public Set<Cmessage> getCsenders() {
        return csenders;
    }

    public Community csenders(Set<Cmessage> cmessages) {
        this.csenders = cmessages;
        return this;
    }

    public Community addCsender(Cmessage cmessage) {
        this.csenders.add(cmessage);
        cmessage.setCsender(this);
        return this;
    }

    public Community removeCsender(Cmessage cmessage) {
        this.csenders.remove(cmessage);
        cmessage.setCsender(null);
        return this;
    }

    public void setCsenders(Set<Cmessage> cmessages) {
        this.csenders = cmessages;
    }

    public Set<Cmessage> getCreceivers() {
        return creceivers;
    }

    public Community creceivers(Set<Cmessage> cmessages) {
        this.creceivers = cmessages;
        return this;
    }

    public Community addCreceiver(Cmessage cmessage) {
        this.creceivers.add(cmessage);
        cmessage.setCreceiver(this);
        return this;
    }

    public Community removeCreceiver(Cmessage cmessage) {
        this.creceivers.remove(cmessage);
        cmessage.setCreceiver(null);
        return this;
    }

    public void setCreceivers(Set<Cmessage> cmessages) {
        this.creceivers = cmessages;
    }

    public Set<Follow> getCfolloweds() {
        return cfolloweds;
    }

    public Community cfolloweds(Set<Follow> follows) {
        this.cfolloweds = follows;
        return this;
    }

    public Community addCfollowed(Follow follow) {
        this.cfolloweds.add(follow);
        follow.setCfollowed(this);
        return this;
    }

    public Community removeCfollowed(Follow follow) {
        this.cfolloweds.remove(follow);
        follow.setCfollowed(null);
        return this;
    }

    public void setCfolloweds(Set<Follow> follows) {
        this.cfolloweds = follows;
    }

    public Set<Follow> getCfollowings() {
        return cfollowings;
    }

    public Community cfollowings(Set<Follow> follows) {
        this.cfollowings = follows;
        return this;
    }

    public Community addCfollowing(Follow follow) {
        this.cfollowings.add(follow);
        follow.setCfollowing(this);
        return this;
    }

    public Community removeCfollowing(Follow follow) {
        this.cfollowings.remove(follow);
        follow.setCfollowing(null);
        return this;
    }

    public void setCfollowings(Set<Follow> follows) {
        this.cfollowings = follows;
    }

    public Set<Blockuser> getCblockedusers() {
        return cblockedusers;
    }

    public Community cblockedusers(Set<Blockuser> blockusers) {
        this.cblockedusers = blockusers;
        return this;
    }

    public Community addCblockeduser(Blockuser blockuser) {
        this.cblockedusers.add(blockuser);
        blockuser.setCblockeduser(this);
        return this;
    }

    public Community removeCblockeduser(Blockuser blockuser) {
        this.cblockedusers.remove(blockuser);
        blockuser.setCblockeduser(null);
        return this;
    }

    public void setCblockedusers(Set<Blockuser> blockusers) {
        this.cblockedusers = blockusers;
    }

    public Set<Blockuser> getCblockingusers() {
        return cblockingusers;
    }

    public Community cblockingusers(Set<Blockuser> blockusers) {
        this.cblockingusers = blockusers;
        return this;
    }

    public Community addCblockinguser(Blockuser blockuser) {
        this.cblockingusers.add(blockuser);
        blockuser.setCblockinguser(this);
        return this;
    }

    public Community removeCblockinguser(Blockuser blockuser) {
        this.cblockingusers.remove(blockuser);
        blockuser.setCblockinguser(null);
        return this;
    }

    public void setCblockingusers(Set<Blockuser> blockusers) {
        this.cblockingusers = blockusers;
    }

    public User getUser() {
        return user;
    }

    public Community user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Calbum> getCalbums() {
        return calbums;
    }

    public Community calbums(Set<Calbum> calbums) {
        this.calbums = calbums;
        return this;
    }

    public Community addCalbum(Calbum calbum) {
        this.calbums.add(calbum);
        calbum.setCommunity(this);
        return this;
    }

    public Community removeCalbum(Calbum calbum) {
        this.calbums.remove(calbum);
        calbum.setCommunity(null);
        return this;
    }

    public void setCalbums(Set<Calbum> calbums) {
        this.calbums = calbums;
    }

    public Set<Cinterest> getCinterests() {
        return cinterests;
    }

    public Community cinterests(Set<Cinterest> cinterests) {
        this.cinterests = cinterests;
        return this;
    }

    public Community addCinterest(Cinterest cinterest) {
        this.cinterests.add(cinterest);
        cinterest.getCommunities().add(this);
        return this;
    }

    public Community removeCinterest(Cinterest cinterest) {
        this.cinterests.remove(cinterest);
        cinterest.getCommunities().remove(this);
        return this;
    }

    public void setCinterests(Set<Cinterest> cinterests) {
        this.cinterests = cinterests;
    }

    public Set<Cactivity> getCactivities() {
        return cactivities;
    }

    public Community cactivities(Set<Cactivity> cactivities) {
        this.cactivities = cactivities;
        return this;
    }

    public Community addCactivity(Cactivity cactivity) {
        this.cactivities.add(cactivity);
        cactivity.getCommunities().add(this);
        return this;
    }

    public Community removeCactivity(Cactivity cactivity) {
        this.cactivities.remove(cactivity);
        cactivity.getCommunities().remove(this);
        return this;
    }

    public void setCactivities(Set<Cactivity> cactivities) {
        this.cactivities = cactivities;
    }

    public Set<Cceleb> getCcelebs() {
        return ccelebs;
    }

    public Community ccelebs(Set<Cceleb> ccelebs) {
        this.ccelebs = ccelebs;
        return this;
    }

    public Community addCceleb(Cceleb cceleb) {
        this.ccelebs.add(cceleb);
        cceleb.getCommunities().add(this);
        return this;
    }

    public Community removeCceleb(Cceleb cceleb) {
        this.ccelebs.remove(cceleb);
        cceleb.getCommunities().remove(this);
        return this;
    }

    public void setCcelebs(Set<Cceleb> ccelebs) {
        this.ccelebs = ccelebs;
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
        Community community = (Community) o;
        if (community.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), community.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Community{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", communityName='" + getCommunityName() + "'" +
            ", communityDescription='" + getCommunityDescription() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
