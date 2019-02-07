package com.jhipsterpress.web.service.impl;

import com.jhipsterpress.web.service.NewsletterService;
import com.jhipsterpress.web.domain.Newsletter;
import com.jhipsterpress.web.repository.NewsletterRepository;
import com.jhipsterpress.web.service.dto.NewsletterDTO;
import com.jhipsterpress.web.service.mapper.NewsletterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Newsletter.
 */
@Service
@Transactional
public class NewsletterServiceImpl implements NewsletterService {

    private final Logger log = LoggerFactory.getLogger(NewsletterServiceImpl.class);

    private final NewsletterRepository newsletterRepository;

    private final NewsletterMapper newsletterMapper;

    public NewsletterServiceImpl(NewsletterRepository newsletterRepository, NewsletterMapper newsletterMapper) {
        this.newsletterRepository = newsletterRepository;
        this.newsletterMapper = newsletterMapper;
    }

    /**
     * Save a newsletter.
     *
     * @param newsletterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NewsletterDTO save(NewsletterDTO newsletterDTO) {
        log.debug("Request to save Newsletter : {}", newsletterDTO);
        Newsletter newsletter = newsletterMapper.toEntity(newsletterDTO);
        newsletter = newsletterRepository.save(newsletter);
        return newsletterMapper.toDto(newsletter);
    }

    /**
     * Get all the newsletters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NewsletterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Newsletters");
        return newsletterRepository.findAll(pageable)
            .map(newsletterMapper::toDto);
    }


    /**
     * Get one newsletter by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NewsletterDTO> findOne(Long id) {
        log.debug("Request to get Newsletter : {}", id);
        return newsletterRepository.findById(id)
            .map(newsletterMapper::toDto);
    }

    /**
     * Delete the newsletter by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Newsletter : {}", id);        newsletterRepository.deleteById(id);
    }
}
