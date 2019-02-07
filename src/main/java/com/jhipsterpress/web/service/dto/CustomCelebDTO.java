package com.jhipsterpress.web.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Celeb entity.
 */
public class CustomCelebDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 2, max = 40)
    private String celebName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCelebName() {
        return celebName;
    }

    public void setCelebName(String celebName) {
        this.celebName = celebName;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celebName == null) ? 0 : celebName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CustomCelebDTO other = (CustomCelebDTO) obj;
		if (celebName == null) {
			if (other.celebName != null)
				return false;
		} else if (!celebName.equals(other.celebName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomCelebDTO [id=" + id + ", celebName=" + celebName + "]";
	}
}
