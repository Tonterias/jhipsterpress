package com.jhipsterpress.web.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Photo entity.
 */
public class PhotoDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @Lob
    private byte[] image;

    private String imageContentType;

    private Long albumId;

    private String albumTitle;

    private Long calbumId;

    private String calbumTitle;
    
    private Long albumUserId;
    
    private Long calbumUserId;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public Long getCalbumId() {
        return calbumId;
    }

    public void setCalbumId(Long calbumId) {
        this.calbumId = calbumId;
    }

    public String getCalbumTitle() {
        return calbumTitle;
    }

    public void setCalbumTitle(String calbumTitle) {
        this.calbumTitle = calbumTitle;
    }

    public Long getAlbumUserId() {
		return albumUserId;
	}

	public void setAlbumUserId(Long albumUserId) {
		this.albumUserId = albumUserId;
	}

	public Long getCalbumUserId() {
		return calbumUserId;
	}

	public void setCalbumUserId(Long calbumUserId) {
		this.calbumUserId = calbumUserId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhotoDTO photoDTO = (PhotoDTO) o;
        if (photoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), photoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "PhotoDTO [id=" + id + ", creationDate=" + creationDate + ", image=" + Arrays.toString(image)
				+ ", imageContentType=" + imageContentType + ", albumId=" + albumId + ", albumTitle=" + albumTitle
				+ ", calbumId=" + calbumId + ", calbumTitle=" + calbumTitle + ", albumUserId=" + albumUserId
				+ ", calbumUserId=" + calbumUserId + "]";
	}
}
