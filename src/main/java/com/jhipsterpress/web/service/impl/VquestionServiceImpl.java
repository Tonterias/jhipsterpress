package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.VquestionService;
import com.jhipsterpress.web.domain.Vquestion;
import com.jhipsterpress.web.repository.VquestionRepository;
import com.jhipsterpress.web.service.dto.VquestionDTO;
import com.jhipsterpress.web.service.mapper.VquestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Vquestion.
 */
@Service
@Transactional
public class VquestionServiceImpl implements VquestionService {

    private final Logger log = LoggerFactory.getLogger(VquestionServiceImpl.class);

    private final VquestionRepository vquestionRepository;

    private final VquestionMapper vquestionMapper;

    public VquestionServiceImpl(VquestionRepository vquestionRepository, VquestionMapper vquestionMapper) {
        this.vquestionRepository = vquestionRepository;
        this.vquestionMapper = vquestionMapper;
    }

    /**
     * Save a vquestion.
     *
     * @param vquestionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VquestionDTO save(VquestionDTO vquestionDTO) {
        log.debug("Request to save Vquestion : {}", vquestionDTO);
        Vquestion vquestion = vquestionMapper.toEntity(vquestionDTO);
        vquestion = vquestionRepository.save(vquestion);
        return vquestionMapper.toDto(vquestion);
    }

    /**
     * Get all the vquestions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VquestionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vquestions");
        return vquestionRepository.findAll(pageable)
            .map(vquestionMapper::toDto);
    }


    /**
     * Get one vquestion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VquestionDTO> findOne(Long id) {
        log.debug("Request to get Vquestion : {}", id);
        return vquestionRepository.findById(id)
            .map(vquestionMapper::toDto);
    }

    /**
     * Delete the vquestion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vquestion : {}", id);        vquestionRepository.deleteById(id);
    }
}
