package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.VthumbService;
import com.jhipsterpress.web.domain.Vthumb;
import com.jhipsterpress.web.repository.VthumbRepository;
import com.jhipsterpress.web.service.dto.VthumbDTO;
import com.jhipsterpress.web.service.mapper.VthumbMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Vthumb.
 */
@Service
@Transactional
public class VthumbServiceImpl implements VthumbService {

    private final Logger log = LoggerFactory.getLogger(VthumbServiceImpl.class);

    private final VthumbRepository vthumbRepository;

    private final VthumbMapper vthumbMapper;

    public VthumbServiceImpl(VthumbRepository vthumbRepository, VthumbMapper vthumbMapper) {
        this.vthumbRepository = vthumbRepository;
        this.vthumbMapper = vthumbMapper;
    }

    /**
     * Save a vthumb.
     *
     * @param vthumbDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VthumbDTO save(VthumbDTO vthumbDTO) {
        log.debug("Request to save Vthumb : {}", vthumbDTO);
        Vthumb vthumb = vthumbMapper.toEntity(vthumbDTO);
        vthumb = vthumbRepository.save(vthumb);
        return vthumbMapper.toDto(vthumb);
    }

    /**
     * Get all the vthumbs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VthumbDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vthumbs");
        return vthumbRepository.findAll(pageable)
            .map(vthumbMapper::toDto);
    }


    /**
     * Get one vthumb by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VthumbDTO> findOne(Long id) {
        log.debug("Request to get Vthumb : {}", id);
        return vthumbRepository.findById(id)
            .map(vthumbMapper::toDto);
    }

    /**
     * Delete the vthumb by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vthumb : {}", id);        vthumbRepository.deleteById(id);
    }
}
