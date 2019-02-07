package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.BlockuserService;
import com.jhipsterpress.web.domain.Blockuser;
import com.jhipsterpress.web.repository.BlockuserRepository;
import com.jhipsterpress.web.service.dto.BlockuserDTO;
import com.jhipsterpress.web.service.mapper.BlockuserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Blockuser.
 */
@Service
@Transactional
public class BlockuserServiceImpl implements BlockuserService {

    private final Logger log = LoggerFactory.getLogger(BlockuserServiceImpl.class);

    private final BlockuserRepository blockuserRepository;

    private final BlockuserMapper blockuserMapper;

    public BlockuserServiceImpl(BlockuserRepository blockuserRepository, BlockuserMapper blockuserMapper) {
        this.blockuserRepository = blockuserRepository;
        this.blockuserMapper = blockuserMapper;
    }

    /**
     * Save a blockuser.
     *
     * @param blockuserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BlockuserDTO save(BlockuserDTO blockuserDTO) {
        log.debug("Request to save Blockuser : {}", blockuserDTO);
        Blockuser blockuser = blockuserMapper.toEntity(blockuserDTO);
        blockuser = blockuserRepository.save(blockuser);
        return blockuserMapper.toDto(blockuser);
    }

    /**
     * Get all the blockusers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BlockuserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Blockusers");
        return blockuserRepository.findAll(pageable)
            .map(blockuserMapper::toDto);
    }


    /**
     * Get one blockuser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BlockuserDTO> findOne(Long id) {
        log.debug("Request to get Blockuser : {}", id);
        return blockuserRepository.findById(id)
            .map(blockuserMapper::toDto);
    }

    /**
     * Delete the blockuser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Blockuser : {}", id);        blockuserRepository.deleteById(id);
    }
}
