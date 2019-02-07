package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.ConfigVariablesService;
import com.jhipsterpress.web.domain.ConfigVariables;
import com.jhipsterpress.web.repository.ConfigVariablesRepository;
import com.jhipsterpress.web.service.dto.ConfigVariablesDTO;
import com.jhipsterpress.web.service.mapper.ConfigVariablesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ConfigVariables.
 */
@Service
@Transactional
public class ConfigVariablesServiceImpl implements ConfigVariablesService {

    private final Logger log = LoggerFactory.getLogger(ConfigVariablesServiceImpl.class);

    private final ConfigVariablesRepository configVariablesRepository;

    private final ConfigVariablesMapper configVariablesMapper;

    public ConfigVariablesServiceImpl(ConfigVariablesRepository configVariablesRepository, ConfigVariablesMapper configVariablesMapper) {
        this.configVariablesRepository = configVariablesRepository;
        this.configVariablesMapper = configVariablesMapper;
    }

    /**
     * Save a configVariables.
     *
     * @param configVariablesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConfigVariablesDTO save(ConfigVariablesDTO configVariablesDTO) {
        log.debug("Request to save ConfigVariables : {}", configVariablesDTO);
        ConfigVariables configVariables = configVariablesMapper.toEntity(configVariablesDTO);
        configVariables = configVariablesRepository.save(configVariables);
        return configVariablesMapper.toDto(configVariables);
    }

    /**
     * Get all the configVariables.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConfigVariablesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ConfigVariables");
        return configVariablesRepository.findAll(pageable)
            .map(configVariablesMapper::toDto);
    }


    /**
     * Get one configVariables by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConfigVariablesDTO> findOne(Long id) {
        log.debug("Request to get ConfigVariables : {}", id);
        return configVariablesRepository.findById(id)
            .map(configVariablesMapper::toDto);
    }

    /**
     * Delete the configVariables by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConfigVariables : {}", id);        configVariablesRepository.deleteById(id);
    }
}
