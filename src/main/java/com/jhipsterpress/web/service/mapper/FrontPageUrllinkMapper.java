package com.jhipsterpress.web.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhipsterpress.web.domain.Urllink;
import com.jhipsterpress.web.repository.UrllinkRepository;

/**
 * Mapper for the entity Urllink and its DTO UrllinkDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class FrontPageUrllinkMapper {

    @Autowired
    private UrllinkRepository urllinkRepository;

    public Urllink urllinkFromId(Long id) {
        if (id == null) {
            return null;
        }
        return urllinkRepository.findById(id).orElse(new Urllink());
    }
    public Long idFromUrllink(Urllink urllink) {
        return urllink.getId();
    }
}