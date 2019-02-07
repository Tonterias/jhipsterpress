package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.CelebService;
import com.jhipsterpress.web.domain.Celeb;
import com.jhipsterpress.web.repository.CelebRepository;
import com.jhipsterpress.web.service.dto.CelebDTO;
import com.jhipsterpress.web.service.mapper.CelebMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Celeb.
 */
@Service
@Transactional
public class CelebServiceImpl implements CelebService {

    private final Logger log = LoggerFactory.getLogger(CelebServiceImpl.class);

    private final CelebRepository celebRepository;

    private final CelebMapper celebMapper;

    public CelebServiceImpl(CelebRepository celebRepository, CelebMapper celebMapper) {
        this.celebRepository = celebRepository;
        this.celebMapper = celebMapper;
    }

    /**
     * Save a celeb.
     *
     * @param celebDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CelebDTO save(CelebDTO celebDTO) {
        log.debug("Request to save Celeb : {}", celebDTO);
        Celeb celeb = celebMapper.toEntity(celebDTO);
        celeb = celebRepository.save(celeb);
        return celebMapper.toDto(celeb);
    }

    /**
     * Get all the celebs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CelebDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Celebs");
        return celebRepository.findAll(pageable)
            .map(celebMapper::toDto);
    }

    /**
     * Get all the Celeb with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<CelebDTO> findAllWithEagerRelationships(Pageable pageable) {
        return celebRepository.findAllWithEagerRelationships(pageable).map(celebMapper::toDto);
    }
    

    /**
     * Get one celeb by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CelebDTO> findOne(Long id) {
        log.debug("Request to get Celeb : {}", id);
        return celebRepository.findOneWithEagerRelationships(id)
            .map(celebMapper::toDto);
    }

    /**
     * Delete the celeb by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Celeb : {}", id);        celebRepository.deleteById(id);
    }
}
