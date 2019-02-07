package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.CactivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cactivity and its DTO CactivityDTO.
 */
@Mapper(componentModel = "spring", uses = {CommunityMapper.class})
public interface CactivityMapper extends EntityMapper<CactivityDTO, Cactivity> {



    default Cactivity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cactivity cactivity = new Cactivity();
        cactivity.setId(id);
        return cactivity;
    }
}
