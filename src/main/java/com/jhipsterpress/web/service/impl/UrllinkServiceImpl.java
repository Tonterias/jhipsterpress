package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.UrllinkService;
import com.jhipsterpress.web.domain.Urllink;
import com.jhipsterpress.web.repository.UrllinkRepository;
import com.jhipsterpress.web.service.dto.UrllinkDTO;
import com.jhipsterpress.web.service.mapper.UrllinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Urllink.
 */
@Service
@Transactional
public class UrllinkServiceImpl implements UrllinkService {

    private final Logger log = LoggerFactory.getLogger(UrllinkServiceImpl.class);

    private final UrllinkRepository urllinkRepository;

    private final UrllinkMapper urllinkMapper;

    public UrllinkServiceImpl(UrllinkRepository urllinkRepository, UrllinkMapper urllinkMapper) {
        this.urllinkRepository = urllinkRepository;
        this.urllinkMapper = urllinkMapper;
    }

    /**
     * Save a urllink.
     *
     * @param urllinkDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UrllinkDTO save(UrllinkDTO urllinkDTO) {
        log.debug("Request to save Urllink : {}", urllinkDTO);
        Urllink urllink = urllinkMapper.toEntity(urllinkDTO);
        urllink = urllinkRepository.save(urllink);
        return urllinkMapper.toDto(urllink);
    }

    /**
     * Get all the urllinks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UrllinkDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Urllinks");
        return urllinkRepository.findAll(pageable)
            .map(urllinkMapper::toDto);
    }


    /**
     * Get one urllink by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UrllinkDTO> findOne(Long id) {
        log.debug("Request to get Urllink : {}", id);
        return urllinkRepository.findById(id)
            .map(urllinkMapper::toDto);
    }

    /**
     * Delete the urllink by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Urllink : {}", id);        urllinkRepository.deleteById(id);
    }
}
