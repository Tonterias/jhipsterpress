package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.UprofileService;
import com.jhipsterpress.web.domain.Uprofile;
import com.jhipsterpress.web.repository.UprofileRepository;
import com.jhipsterpress.web.service.dto.UprofileDTO;
import com.jhipsterpress.web.service.mapper.UprofileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Uprofile.
 */
@Service
@Transactional
public class UprofileServiceImpl implements UprofileService {

    private final Logger log = LoggerFactory.getLogger(UprofileServiceImpl.class);

    private final UprofileRepository uprofileRepository;

    private final UprofileMapper uprofileMapper;

    public UprofileServiceImpl(UprofileRepository uprofileRepository, UprofileMapper uprofileMapper) {
        this.uprofileRepository = uprofileRepository;
        this.uprofileMapper = uprofileMapper;
    }

    /**
     * Save a uprofile.
     *
     * @param uprofileDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UprofileDTO save(UprofileDTO uprofileDTO) {
        log.debug("Request to save Uprofile : {}", uprofileDTO);
        Uprofile uprofile = uprofileMapper.toEntity(uprofileDTO);
        uprofile = uprofileRepository.save(uprofile);
        return uprofileMapper.toDto(uprofile);
    }

    /**
     * Get all the uprofiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UprofileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Uprofiles");
        return uprofileRepository.findAll(pageable)
            .map(uprofileMapper::toDto);
    }


    /**
     * Get one uprofile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UprofileDTO> findOne(Long id) {
        log.debug("Request to get Uprofile : {}", id);
        return uprofileRepository.findById(id)
            .map(uprofileMapper::toDto);
    }

    /**
     * Delete the uprofile by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Uprofile : {}", id);        uprofileRepository.deleteById(id);
    }
}
