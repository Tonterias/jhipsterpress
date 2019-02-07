package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.CcelebService;
import com.jhipsterpress.web.domain.Cceleb;
import com.jhipsterpress.web.repository.CcelebRepository;
import com.jhipsterpress.web.service.dto.CcelebDTO;
import com.jhipsterpress.web.service.mapper.CcelebMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Cceleb.
 */
@Service
@Transactional
public class CcelebServiceImpl implements CcelebService {

    private final Logger log = LoggerFactory.getLogger(CcelebServiceImpl.class);

    private final CcelebRepository ccelebRepository;

    private final CcelebMapper ccelebMapper;

    public CcelebServiceImpl(CcelebRepository ccelebRepository, CcelebMapper ccelebMapper) {
        this.ccelebRepository = ccelebRepository;
        this.ccelebMapper = ccelebMapper;
    }

    /**
     * Save a cceleb.
     *
     * @param ccelebDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CcelebDTO save(CcelebDTO ccelebDTO) {
        log.debug("Request to save Cceleb : {}", ccelebDTO);
        Cceleb cceleb = ccelebMapper.toEntity(ccelebDTO);
        cceleb = ccelebRepository.save(cceleb);
        return ccelebMapper.toDto(cceleb);
    }

    /**
     * Get all the ccelebs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CcelebDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ccelebs");
        return ccelebRepository.findAll(pageable)
            .map(ccelebMapper::toDto);
    }

    /**
     * Get all the Cceleb with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<CcelebDTO> findAllWithEagerRelationships(Pageable pageable) {
        return ccelebRepository.findAllWithEagerRelationships(pageable).map(ccelebMapper::toDto);
    }
    

    /**
     * Get one cceleb by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CcelebDTO> findOne(Long id) {
        log.debug("Request to get Cceleb : {}", id);
        return ccelebRepository.findOneWithEagerRelationships(id)
            .map(ccelebMapper::toDto);
    }

    /**
     * Delete the cceleb by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cceleb : {}", id);        ccelebRepository.deleteById(id);
    }
}
