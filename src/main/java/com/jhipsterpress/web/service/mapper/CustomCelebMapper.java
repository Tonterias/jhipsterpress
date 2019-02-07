package com.jhipsterpress.web.service.mapper;

import org.mapstruct.Mapper;

import com.jhipsterpress.web.domain.Celeb;
import com.jhipsterpress.web.service.dto.CustomCelebDTO;



/**
 * Mapper for the entity Celeb and its DTO CelebDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomCelebMapper extends EntityMapper<CustomCelebDTO, Celeb> {



    default Celeb fromId(Long id) {
        if (id == null) {
            return null;
        }
        Celeb celeb = new Celeb();
        celeb.setId(id);
        return celeb;
    }
}
