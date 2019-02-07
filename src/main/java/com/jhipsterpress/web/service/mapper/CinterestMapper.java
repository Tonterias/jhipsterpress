package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.CinterestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cinterest and its DTO CinterestDTO.
 */
@Mapper(componentModel = "spring", uses = {CommunityMapper.class})
public interface CinterestMapper extends EntityMapper<CinterestDTO, Cinterest> {



    default Cinterest fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cinterest cinterest = new Cinterest();
        cinterest.setId(id);
        return cinterest;
    }
}
