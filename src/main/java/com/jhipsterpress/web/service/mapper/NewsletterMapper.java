package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.NewsletterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Newsletter and its DTO NewsletterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NewsletterMapper extends EntityMapper<NewsletterDTO, Newsletter> {



    default Newsletter fromId(Long id) {
        if (id == null) {
            return null;
        }
        Newsletter newsletter = new Newsletter();
        newsletter.setId(id);
        return newsletter;
    }
}
