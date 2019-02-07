package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.CinterestService;
import com.jhipsterpress.web.domain.Cinterest;
import com.jhipsterpress.web.repository.CinterestRepository;
import com.jhipsterpress.web.service.dto.CinterestDTO;
import com.jhipsterpress.web.service.mapper.CinterestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Cinterest.
 */
@Service
@Transactional
public class CinterestServiceImpl implements CinterestService {

    private final Logger log = LoggerFactory.getLogger(CinterestServiceImpl.class);

    private final CinterestRepository cinterestRepository;

    private final CinterestMapper cinterestMapper;

    public CinterestServiceImpl(CinterestRepository cinterestRepository, CinterestMapper cinterestMapper) {
        this.cinterestRepository = cinterestRepository;
        this.cinterestMapper = cinterestMapper;
    }

    /**
     * Save a cinterest.
     *
     * @param cinterestDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CinterestDTO save(CinterestDTO cinterestDTO) {
        log.debug("Request to save Cinterest : {}", cinterestDTO);
        Cinterest cinterest = cinterestMapper.toEntity(cinterestDTO);
        cinterest = cinterestRepository.save(cinterest);
        return cinterestMapper.toDto(cinterest);
    }

    /**
     * Get all the cinterests.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CinterestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cinterests");
        return cinterestRepository.findAll(pageable)
            .map(cinterestMapper::toDto);
    }

    /**
     * Get all the Cinterest with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<CinterestDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cinterestRepository.findAllWithEagerRelationships(pageable).map(cinterestMapper::toDto);
    }
    

    /**
     * Get one cinterest by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CinterestDTO> findOne(Long id) {
        log.debug("Request to get Cinterest : {}", id);
        return cinterestRepository.findOneWithEagerRelationships(id)
            .map(cinterestMapper::toDto);
    }

    /**
     * Delete the cinterest by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cinterest : {}", id);        cinterestRepository.deleteById(id);
    }
}
