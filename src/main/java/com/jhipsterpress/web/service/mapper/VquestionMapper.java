package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.VquestionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vquestion and its DTO VquestionDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, VtopicMapper.class})
public interface VquestionMapper extends EntityMapper<VquestionDTO, Vquestion> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "vtopic.id", target = "vtopicId")
    VquestionDTO toDto(Vquestion vquestion);

    @Mapping(target = "vanswers", ignore = true)
    @Mapping(target = "vthumbs", ignore = true)
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "vtopicId", target = "vtopic")
    Vquestion toEntity(VquestionDTO vquestionDTO);

    default Vquestion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vquestion vquestion = new Vquestion();
        vquestion.setId(id);
        return vquestion;
    }
}
