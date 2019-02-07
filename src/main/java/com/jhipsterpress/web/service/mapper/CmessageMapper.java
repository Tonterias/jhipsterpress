package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.CmessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cmessage and its DTO CmessageDTO.
 */
@Mapper(componentModel = "spring", uses = {CommunityMapper.class})
public interface CmessageMapper extends EntityMapper<CmessageDTO, Cmessage> {

    @Mapping(source = "csender.id", target = "csenderId")
    @Mapping(source = "creceiver.id", target = "creceiverId")
    CmessageDTO toDto(Cmessage cmessage);

    @Mapping(source = "csenderId", target = "csender")
    @Mapping(source = "creceiverId", target = "creceiver")
    Cmessage toEntity(CmessageDTO cmessageDTO);

    default Cmessage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cmessage cmessage = new Cmessage();
        cmessage.setId(id);
        return cmessage;
    }
}
