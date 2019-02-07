package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.VtopicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vtopic and its DTO VtopicDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface VtopicMapper extends EntityMapper<VtopicDTO, Vtopic> {

    @Mapping(source = "user.id", target = "userId")
    VtopicDTO toDto(Vtopic vtopic);

    @Mapping(target = "vquestions", ignore = true)
    @Mapping(source = "userId", target = "user")
    Vtopic toEntity(VtopicDTO vtopicDTO);

    default Vtopic fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vtopic vtopic = new Vtopic();
        vtopic.setId(id);
        return vtopic;
    }
}
