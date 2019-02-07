package com.jhipsterpress.web.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the Interest entity.
 */
public class CustomInterestDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 2, max = 40)
    private String interestName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interestName == null) ? 0 : interestName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomInterestDTO other = (CustomInterestDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (interestName == null) {
			if (other.interestName != null)
				return false;
		} else if (!interestName.equals(other.interestName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomInterestDTO [id=" + id + ", interestName=" + interestName + "]";
	}
}
