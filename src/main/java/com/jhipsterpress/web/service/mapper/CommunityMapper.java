package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.CommunityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Community and its DTO CommunityDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommunityMapper extends EntityMapper<CommunityDTO, Community> {

    @Mapping(source = "user.id", target = "userId")
    CommunityDTO toDto(Community community);

    @Mapping(target = "blogs", ignore = true)
    @Mapping(target = "csenders", ignore = true)
    @Mapping(target = "creceivers", ignore = true)
    @Mapping(target = "cfolloweds", ignore = true)
    @Mapping(target = "cfollowings", ignore = true)
    @Mapping(target = "cblockedusers", ignore = true)
    @Mapping(target = "cblockingusers", ignore = true)
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "calbums", ignore = true)
    @Mapping(target = "cinterests", ignore = true)
    @Mapping(target = "cactivities", ignore = true)
    @Mapping(target = "ccelebs", ignore = true)
    Community toEntity(CommunityDTO communityDTO);

    default Community fromId(Long id) {
        if (id == null) {
            return null;
        }
        Community community = new Community();
        community.setId(id);
        return community;
    }
}
