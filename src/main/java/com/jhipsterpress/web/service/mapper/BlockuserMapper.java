package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.BlockuserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Blockuser and its DTO BlockuserDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CommunityMapper.class})
public interface BlockuserMapper extends EntityMapper<BlockuserDTO, Blockuser> {

    @Mapping(source = "blockeduser.id", target = "blockeduserId")
    @Mapping(source = "blockinguser.id", target = "blockinguserId")
    @Mapping(source = "cblockeduser.id", target = "cblockeduserId")
    @Mapping(source = "cblockinguser.id", target = "cblockinguserId")
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
