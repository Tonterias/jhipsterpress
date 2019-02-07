package com.jhipsterpress.web.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Vquestion entity.
 */
public class VquestionDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 100)
    private String vquestion;

    @Size(min = 2, max = 250)
    private String vquestionDescription;


    private Long userId;

    private Long vtopicId;

    private Set <VanswerDTO> vanswers;
    
    private Set <VthumbDTO> vthumbs;
    
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

    public String getVquestion() {
        return vquestion;
    }

    public void setVquestion(String vquestion) {
        this.vquestion = vquestion;
    }

    public String getVquestionDescription() {
        return vquestionDescription;
    }

    public void setVquestionDescription(String vquestionDescription) {
        this.vquestionDescription = vquestionDescription;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVtopicId() {
        return vtopicId;
    }

    public void setVtopicId(Long vtopicId) {
        this.vtopicId = vtopicId;
    }

    public Set<VanswerDTO> getVanswers() {
		return vanswers;
	}

	public void setVanswers(Set<VanswerDTO> vanswers) {
		this.vanswers = vanswers;
	}

	public Set<VthumbDTO> getVthumbs() {
		return vthumbs;
	}

	public void setVthumbs(Set<VthumbDTO> vthumbs) {
		this.vthumbs = vthumbs;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VquestionDTO vquestionDTO = (VquestionDTO) o;
        if (vquestionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vquestionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "VquestionDTO [id=" + id + ", creationDate=" + creationDate + ", vquestion=" + vquestion
				+ ", vquestionDescription=" + vquestionDescription + ", userId=" + userId + ", vtopicId=" + vtopicId
				+ ", vanswers=" + vanswers + ", vthumbs=" + vthumbs + "]";
	}
}
