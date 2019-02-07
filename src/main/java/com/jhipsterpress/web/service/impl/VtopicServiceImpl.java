package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.VtopicService;
import com.jhipsterpress.web.domain.Vtopic;
import com.jhipsterpress.web.repository.VtopicRepository;
import com.jhipsterpress.web.service.dto.VtopicDTO;
import com.jhipsterpress.web.service.mapper.VtopicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Vtopic.
 */
@Service
@Transactional
public class VtopicServiceImpl implements VtopicService {

    private final Logger log = LoggerFactory.getLogger(VtopicServiceImpl.class);

    private final VtopicRepository vtopicRepository;

    private final VtopicMapper vtopicMapper;

    public VtopicServiceImpl(VtopicRepository vtopicRepository, VtopicMapper vtopicMapper) {
        this.vtopicRepository = vtopicRepository;
        this.vtopicMapper = vtopicMapper;
    }

    /**
     * Save a vtopic.
     *
     * @param vtopicDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VtopicDTO save(VtopicDTO vtopicDTO) {
        log.debug("Request to save Vtopic : {}", vtopicDTO);
        Vtopic vtopic = vtopicMapper.toEntity(vtopicDTO);
        vtopic = vtopicRepository.save(vtopic);
        return vtopicMapper.toDto(vtopic);
    }

    /**
     * Get all the vtopics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VtopicDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vtopics");
        return vtopicRepository.findAll(pageable)
            .map(vtopicMapper::toDto);
    }


    /**
     * Get one vtopic by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VtopicDTO> findOne(Long id) {
        log.debug("Request to get Vtopic : {}", id);
        return vtopicRepository.findById(id)
            .map(vtopicMapper::toDto);
    }

    /**
     * Delete the vtopic by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vtopic : {}", id);        vtopicRepository.deleteById(id);
    }
}
