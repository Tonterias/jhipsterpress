package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.CcelebDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cceleb and its DTO CcelebDTO.
 */
@Mapper(componentModel = "spring", uses = {CommunityMapper.class})
public interface CcelebMapper extends EntityMapper<CcelebDTO, Cceleb> {



    default Cceleb fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cceleb cceleb = new Cceleb();
        cceleb.setId(id);
        return cceleb;
    }
}
