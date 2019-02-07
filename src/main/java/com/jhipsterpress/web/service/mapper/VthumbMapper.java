package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.VthumbDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vthumb and its DTO VthumbDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, VquestionMapper.class, VanswerMapper.class})
public interface VthumbMapper extends EntityMapper<VthumbDTO, Vthumb> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "vquestion.id", target = "vquestionId")
    @Mapping(source = "vanswer.id", target = "vanswerId")
    VthumbDTO toDto(Vthumb vthumb);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "vquestionId", target = "vquestion")
    @Mapping(source = "vanswerId", target = "vanswer")
    Vthumb toEntity(VthumbDTO vthumbDTO);

    default Vthumb fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vthumb vthumb = new Vthumb();
        vthumb.setId(id);
        return vthumb;
    }
}
