package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.ConfigVariablesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ConfigVariables and its DTO ConfigVariablesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConfigVariablesMapper extends EntityMapper<ConfigVariablesDTO, ConfigVariables> {



    default ConfigVariables fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConfigVariables configVariables = new ConfigVariables();
        configVariables.setId(id);
        return configVariables;
    }
}
