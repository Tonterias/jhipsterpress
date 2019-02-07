package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.CalbumDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Calbum and its DTO CalbumDTO.
 */
@Mapper(componentModel = "spring", uses = {CommunityMapper.class, UserMapper.class})
public interface CalbumMapper extends EntityMapper<CalbumDTO, Calbum> {

    @Mapping(source = "community.id", target = "communityId")
    @Mapping(source = "community.communityName", target = "communityCommunityName")
    @Mapping(source = "community.user.id", target = "userId")
    CalbumDTO toDto(Calbum calbum);

    @Mapping(target = "photos", ignore = true)
    @Mapping(source = "communityId", target = "community")
    Calbum toEntity(CalbumDTO calbumDTO);

    default Calbum fromId(Long id) {
        if (id == null) {
            return null;
        }
        Calbum calbum = new Calbum();
        calbum.setId(id);
        return calbum;
    }
}
