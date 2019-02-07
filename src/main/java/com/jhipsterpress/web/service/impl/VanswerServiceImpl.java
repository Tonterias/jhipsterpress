package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.VanswerService;
import com.jhipsterpress.web.domain.Vanswer;
import com.jhipsterpress.web.repository.VanswerRepository;
import com.jhipsterpress.web.service.dto.VanswerDTO;
import com.jhipsterpress.web.service.mapper.VanswerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Vanswer.
 */
@Service
@Transactional
public class VanswerServiceImpl implements VanswerService {

    private final Logger log = LoggerFactory.getLogger(VanswerServiceImpl.class);

    private final VanswerRepository vanswerRepository;

    private final VanswerMapper vanswerMapper;

    public VanswerServiceImpl(VanswerRepository vanswerRepository, VanswerMapper vanswerMapper) {
        this.vanswerRepository = vanswerRepository;
        this.vanswerMapper = vanswerMapper;
    }

    /**
     * Save a vanswer.
     *
     * @param vanswerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VanswerDTO save(VanswerDTO vanswerDTO) {
        log.debug("Request to save Vanswer : {}", vanswerDTO);
        Vanswer vanswer = vanswerMapper.toEntity(vanswerDTO);
        vanswer = vanswerRepository.save(vanswer);
        return vanswerMapper.toDto(vanswer);
    }

    /**
     * Get all the vanswers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VanswerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vanswers");
        return vanswerRepository.findAll(pageable)
            .map(vanswerMapper::toDto);
    }


    /**
     * Get one vanswer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VanswerDTO> findOne(Long id) {
        log.debug("Request to get Vanswer : {}", id);
        return vanswerRepository.findById(id)
            .map(vanswerMapper::toDto);
    }

    /**
     * Delete the vanswer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vanswer : {}", id);        vanswerRepository.deleteById(id);
    }
}
