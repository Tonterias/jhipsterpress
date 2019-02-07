package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.BlockuserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Blockuser and its DTO BlockuserDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CommunityMapper.class})
public interface BlockuserMapper extends EntityMapper<BlockuserDTO, Blockuser> {

    @Mapping(source = "cblockeduser.id", target = "cblockeduserId")
    @Mapping(source = "cblockeduser.image", target = "cblockeduserImage")
    @Mapping(source = "cblockeduser.imageContentType", target = "cblockeduserImageContentType")
    @Mapping(source = "cblockeduser.communityName", target = "cblockeduserCommunityname")
    @Mapping(source = "cblockinguser.id", target = "cblockinguserId")
    @Mapping(source = "cblockinguser.image", target = "cblockinguserImage")
    @Mapping(source = "cblockinguser.imageContentType", target = "cblockinguserImageContentType")
    @Mapping(source = "cblockinguser.communityName", target = "cblockinguserCommunityname")
    @Mapping(source = "blockeduser.id", target = "blockeduserId")
    @Mapping(source = "blockeduser.uprofile.image", target = "blockeduserImage")
    @Mapping(source = "blockeduser.uprofile.imageContentType", target = "blockeduserImageContentType")
    @Mapping(source = "blockeduser.firstName", target = "blockeduserFirstName")
    @Mapping(source = "blockeduser.lastName", target = "blockeduserLastName")
    @Mapping(source = "blockinguser.id", target = "blockinguserId")
    @Mapping(source = "blockinguser.uprofile.image", target = "blockinguserImage")
    @Mapping(source = "blockinguser.uprofile.imageContentType", target = "blockinguserImageContentType")
    @Mapping(source = "blockinguser.firstName", target = "blockinguserFirstName")
    @Mapping(source = "blockinguser.lastName", target = "blockinguserLastName")
    BlockuserDTO toDto(Blockuser blockuser);

    @Mapping(source = "blockeduserId", target = "blockeduser")
    @Mapping(source = "blockinguserId", target = "blockinguser")
    @Mapping(source = "cblockeduserId", target = "cblockeduser")
    @Mapping(source = "cblockinguserId", target = "cblockinguser")
    Blockuser toEntity(BlockuserDTO blockuserDTO);

    default Blockuser fromId(Long id) {
        if (id == null) {
            return null;
        }
        Blockuser blockuser = new Blockuser();
        blockuser.setId(id);
        return blockuser;
    }
}
