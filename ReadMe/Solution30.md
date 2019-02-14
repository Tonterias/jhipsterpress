# Problem 30: How to manually add relationships to the User Entity.


This is the problem. I need to include in my FollowMapper the image of the User's Profile, but this can not be accessed directly since they are not directly related: Follow -- User -- Profile.

How to solve it? Creating the relationship manually in the User Entity like this:

	 @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Uprofile uprofile;

... including ToString, getter and setter (check the entity in GitHub for full details).


Then, create the relationships at the FollowMapper, so the followed user gets its profile image, that would not be accesible if the relation is not created manually:

@Mapping(source = "followed.uprofile.image", target = "followedImage")
@Mapping(source = "followed.uprofile.imageContentType", target = "followedImageContentType")


	package com.jhipsterpress.web.service.mapper;

	import com.jhipsterpress.web.domain.*;
	import com.jhipsterpress.web.service.dto.FollowDTO;
	
	import org.mapstruct.*;
	
	/**
	 * Mapper for the entity Follow and its DTO FollowDTO.
	 */
	@Mapper(componentModel = "spring", uses = {UserMapper.class, CommunityMapper.class, UprofileMapper.class})
	public interface FollowMapper extends EntityMapper<FollowDTO, Follow> {
	
	package com.jhipsterpress.web.service.mapper;
	
	import com.jhipsterpress.web.domain.*;
	import com.jhipsterpress.web.service.dto.FollowDTO;
	
	import org.mapstruct.*;
	
	/**
	 * Mapper for the entity Follow and its DTO FollowDTO.
	 */
	@Mapper(componentModel = "spring", uses = {UserMapper.class, CommunityMapper.class, UprofileMapper.class})
	public interface FollowMapper extends EntityMapper<FollowDTO, Follow> {
	
	    @Mapping(source = "followed.id", target = "followedId")
	    @Mapping(source = "followed.uprofile.image", target = "followedImage")
	    @Mapping(source = "followed.uprofile.imageContentType", target = "followedImageContentType")
	    @Mapping(source = "followed.firstName", target = "followedUserFirstName")
	    @Mapping(source = "followed.lastName", target = "followedUserLastName")
	    @Mapping(source = "following.id", target = "followingId")
	    @Mapping(source = "following.uprofile.image", target = "followingImage")
	    @Mapping(source = "following.uprofile.imageContentType", target = "followingImageContentType")
	    @Mapping(source = "following.firstName", target = "followingUserFirstName")
	    @Mapping(source = "following.lastName", target = "followingUserLastName")
	    @Mapping(source = "cfollowed.id", target = "cfollowedId")
	    @Mapping(source = "cfollowed.image", target = "cfollowedImage")
	    @Mapping(source = "cfollowed.imageContentType", target = "cfollowedImageContentType")
	    @Mapping(source = "cfollowed.communityname", target = "cfollowedCommunityname")
	    @Mapping(source = "cfollowing.id", target = "cfollowingId")
	    @Mapping(source = "cfollowing.image", target = "cfollowingImage")
	    @Mapping(source = "cfollowing.imageContentType", target = "cfollowingImageContentType")
	    @Mapping(source = "cfollowing.communityname", target = "cfollowingCommunityname")
	    FollowDTO toDto(Follow follow);
	
	    @Mapping(source = "followedId", target = "followed")
	    @Mapping(source = "followingId", target = "following")
	    @Mapping(source = "cfollowedId", target = "cfollowed")
	    @Mapping(source = "cfollowingId", target = "cfollowing")
	    Follow toEntity(FollowDTO followDTO);
	
	    default Follow fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        Follow follow = new Follow();
	        follow.setId(id);
	        return follow;
	    }
	}


Then, you need to add the variables to the FollowDTO: 


	 @Lob
    private byte[] followedImage;
    private String followedImageContentType;
    
... including ToString, getter and setter (check the entity in GitHub for full details).

I did the same for the BlockUser and the Comment (User Photo) to show the images (in case you need to check other cases). 
