package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.CelebDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Celeb and its DTO CelebDTO.
 */
@Mapper(componentModel = "spring", uses = {UprofileMapper.class})
public interface CelebMapper extends EntityMapper<CelebDTO, Celeb> {



    default Celeb fromId(Long id) {
        if (id == null) {
            return null;
        }
        Celeb celeb = new Celeb();
        celeb.setId(id);
        return celeb;
    }
}
