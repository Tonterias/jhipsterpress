package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.CmessageService;
import com.jhipsterpress.web.domain.Cmessage;
import com.jhipsterpress.web.repository.CmessageRepository;
import com.jhipsterpress.web.service.dto.CmessageDTO;
import com.jhipsterpress.web.service.mapper.CmessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Cmessage.
 */
@Service
@Transactional
public class CmessageServiceImpl implements CmessageService {

    private final Logger log = LoggerFactory.getLogger(CmessageServiceImpl.class);

    private final CmessageRepository cmessageRepository;

    private final CmessageMapper cmessageMapper;

    public CmessageServiceImpl(CmessageRepository cmessageRepository, CmessageMapper cmessageMapper) {
        this.cmessageRepository = cmessageRepository;
        this.cmessageMapper = cmessageMapper;
    }

    /**
     * Save a cmessage.
     *
     * @param cmessageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CmessageDTO save(CmessageDTO cmessageDTO) {
        log.debug("Request to save Cmessage : {}", cmessageDTO);
        Cmessage cmessage = cmessageMapper.toEntity(cmessageDTO);
        cmessage = cmessageRepository.save(cmessage);
        return cmessageMapper.toDto(cmessage);
    }

    /**
     * Get all the cmessages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CmessageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cmessages");
        return cmessageRepository.findAll(pageable)
            .map(cmessageMapper::toDto);
    }


    /**
     * Get one cmessage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CmessageDTO> findOne(Long id) {
        log.debug("Request to get Cmessage : {}", id);
        return cmessageRepository.findById(id)
            .map(cmessageMapper::toDto);
    }

    /**
     * Delete the cmessage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cmessage : {}", id);        cmessageRepository.deleteById(id);
    }
}
