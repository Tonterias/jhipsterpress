package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.VanswerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vanswer and its DTO VanswerDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, VquestionMapper.class})
public interface VanswerMapper extends EntityMapper<VanswerDTO, Vanswer> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "vquestion.id", target = "vquestionId")
    VanswerDTO toDto(Vanswer vanswer);

    @Mapping(target = "vthumbs", ignore = true)
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "vquestionId", target = "vquestion")
    Vanswer toEntity(VanswerDTO vanswerDTO);

    default Vanswer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vanswer vanswer = new Vanswer();
        vanswer.setId(id);
        return vanswer;
    }
}
