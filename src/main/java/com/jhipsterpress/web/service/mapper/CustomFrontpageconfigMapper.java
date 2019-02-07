package com.jhipsterpress.web.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jhipsterpress.web.domain.Frontpageconfig;
import com.jhipsterpress.web.service.dto.CustomFrontpageconfigDTO;

/**
 * Mapper for the entity Frontpageconfig and its DTO FrontpageconfigDTO.
 */
@Mapper(componentModel = "spring", uses = {FrontPagePostMapper.class, FrontPageUrllinkMapper.class})
public interface CustomFrontpageconfigMapper extends EntityMapper<CustomFrontpageconfigDTO, Frontpageconfig> {

    CustomFrontpageconfigDTO toDto(Frontpageconfig frontpageconfig);

    @Mapping(target = "usefulLinks1", ignore = true)
    @Mapping(target = "usefulLinks2", ignore = true)
    @Mapping(target = "usefulLinks3", ignore = true)
    @Mapping(target = "usefulLinks4", ignore = true)
    @Mapping(target = "usefulLinks5", ignore = true)
    @Mapping(target = "usefulLinks6", ignore = true)    
    @Mapping(target = "recentVideos1", ignore = true)
    @Mapping(target = "recentVideos2", ignore = true)
    @Mapping(target = "recentVideos3", ignore = true)
    @Mapping(target = "recentVideos4", ignore = true)
    @Mapping(target = "recentVideos5", ignore = true)
    @Mapping(target = "recentVideos6", ignore = true)
    Frontpageconfig toEntity(CustomFrontpageconfigDTO customFrontpageconfigDTO);
    
    default Frontpageconfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        Frontpageconfig frontpageconfig = new Frontpageconfig();
        frontpageconfig.setId(id);
        return frontpageconfig;
    }
}