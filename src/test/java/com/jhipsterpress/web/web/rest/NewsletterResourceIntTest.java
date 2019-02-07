package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Newsletter;
import com.jhipsterpress.web.repository.NewsletterRepository;
import com.jhipsterpress.web.service.NewsletterService;
import com.jhipsterpress.web.service.dto.NewsletterDTO;
import com.jhipsterpress.web.service.mapper.NewsletterMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.NewsletterCriteria;
import com.jhipsterpress.web.service.NewsletterQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.jhipsterpress.web.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the NewsletterResource REST controller.
 *
 * @see NewsletterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class NewsletterResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private NewsletterRepository newsletterRepository;

    @Autowired
    private NewsletterMapper newsletterMapper;

    @Autowired
    private NewsletterService newsletterService;

    @Autowired
    private NewsletterQueryService newsletterQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restNewsletterMockMvc;

    private Newsletter newsletter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NewsletterResource newsletterResource = new NewsletterResource(newsletterService, newsletterQueryService);
        this.restNewsletterMockMvc = MockMvcBuilders.standaloneSetup(newsletterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Newsletter createEntity(EntityManager em) {
        Newsletter newsletter = new Newsletter()
            .creationDate(DEFAULT_CREATION_DATE)
            .email(DEFAULT_EMAIL);
        return newsletter;
    }

    @Before
    public void initTest() {
        newsletter = createEntity(em);
    }

    @Test
    @Transactional
    public void createNewsletter() throws Exception {
        int databaseSizeBeforeCreate = newsletterRepository.findAll().size();

        // Create the Newsletter
        NewsletterDTO newsletterDTO = newsletterMapper.toDto(newsletter);
        restNewsletterMockMvc.perform(post("/api/newsletters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newsletterDTO)))
            .andExpect(status().isCreated());

        // Validate the Newsletter in the database
        List<Newsletter> newsletterList = newsletterRepository.findAll();
        assertThat(newsletterList).hasSize(databaseSizeBeforeCreate + 1);
        Newsletter testNewsletter = newsletterList.get(newsletterList.size() - 1);
        assertThat(testNewsletter.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testNewsletter.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createNewsletterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = newsletterRepository.findAll().size();

        // Create the Newsletter with an existing ID
        newsletter.setId(1L);
        NewsletterDTO newsletterDTO = newsletterMapper.toDto(newsletter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNewsletterMockMvc.perform(post("/api/newsletters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newsletterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Newsletter in the database
        List<Newsletter> newsletterList = newsletterRepository.findAll();
        assertThat(newsletterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = newsletterRepository.findAll().size();
        // set the field null
        newsletter.setCreationDate(null);

        // Create the Newsletter, which fails.
        NewsletterDTO newsletterDTO = newsletterMapper.toDto(newsletter);

        restNewsletterMockMvc.perform(post("/api/newsletters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newsletterDTO)))
            .andExpect(status().isBadRequest());

        List<Newsletter> newsletterList = newsletterRepository.findAll();
        assertThat(newsletterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = newsletterRepository.findAll().size();
        // set the field null
        newsletter.setEmail(null);

        // Create the Newsletter, which fails.
        NewsletterDTO newsletterDTO = newsletterMapper.toDto(newsletter);

        restNewsletterMockMvc.perform(post("/api/newsletters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newsletterDTO)))
            .andExpect(status().isBadRequest());

        List<Newsletter> newsletterList = newsletterRepository.findAll();
        assertThat(newsletterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNewsletters() throws Exception {
        // Initialize the database
        newsletterRepository.saveAndFlush(newsletter);

        // Get all the newsletterList
        restNewsletterMockMvc.perform(get("/api/newsletters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(newsletter.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())));
    }
    
    @Test
    @Transactional
    public void getNewsletter() throws Exception {
        // Initialize the database
        newsletterRepository.saveAndFlush(newsletter);

        // Get the newsletter
        restNewsletterMockMvc.perform(get("/api/newsletters/{id}", newsletter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(newsletter.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()));
    }

    @Test
    @Transactional
    public void getAllNewslettersByCreationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        newsletterRepository.saveAndFlush(newsletter);

        // Get all the newsletterList where creationDate equals to DEFAULT_CREATION_DATE
        defaultNewsletterShouldBeFound("creationDate.equals=" + DEFAULT_CREATION_DATE);

        // Get all the newsletterList where creationDate equals to UPDATED_CREATION_DATE
        defaultNewsletterShouldNotBeFound("creationDate.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllNewslettersByCreationDateIsInShouldWork() throws Exception {
        // Initialize the database
        newsletterRepository.saveAndFlush(newsletter);

        // Get all the newsletterList where creationDate in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultNewsletterShouldBeFound("creationDate.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the newsletterList where creationDate equals to UPDATED_CREATION_DATE
        defaultNewsletterShouldNotBeFound("creationDate.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllNewslettersByCreationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        newsletterRepository.saveAndFlush(newsletter);

        // Get all the newsletterList where creationDate is not null
        defaultNewsletterShouldBeFound("creationDate.specified=true");

        // Get all the newsletterList where creationDate is null
        defaultNewsletterShouldNotBeFound("creationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllNewslettersByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        newsletterRepository.saveAndFlush(newsletter);

        // Get all the newsletterList where email equals to DEFAULT_EMAIL
        defaultNewsletterShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the newsletterList where email equals to UPDATED_EMAIL
        defaultNewsletterShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllNewslettersByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        newsletterRepository.saveAndFlush(newsletter);

        // Get all the newsletterList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultNewsletterShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the newsletterList where email equals to UPDATED_EMAIL
        defaultNewsletterShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllNewslettersByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        newsletterRepository.saveAndFlush(newsletter);

        // Get all the newsletterList where email is not null
        defaultNewsletterShouldBeFound("email.specified=true");

        // Get all the newsletterList where email is null
        defaultNewsletterShouldNotBeFound("email.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNewsletterShouldBeFound(String filter) throws Exception {
        restNewsletterMockMvc.perform(get("/api/newsletters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(newsletter.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));

        // Check, that the count call also returns 1
        restNewsletterMockMvc.perform(get("/api/newsletters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNewsletterShouldNotBeFound(String filter) throws Exception {
        restNewsletterMockMvc.perform(get("/api/newsletters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNewsletterMockMvc.perform(get("/api/newsletters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNewsletter() throws Exception {
        // Get the newsletter
        restNewsletterMockMvc.perform(get("/api/newsletters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNewsletter() throws Exception {
        // Initialize the database
        newsletterRepository.saveAndFlush(newsletter);

        int databaseSizeBeforeUpdate = newsletterRepository.findAll().size();

        // Update the newsletter
        Newsletter updatedNewsletter = newsletterRepository.findById(newsletter.getId()).get();
        // Disconnect from session so that the updates on updatedNewsletter are not directly saved in db
        em.detach(updatedNewsletter);
        updatedNewsletter
            .creationDate(UPDATED_CREATION_DATE)
            .email(UPDATED_EMAIL);
        NewsletterDTO newsletterDTO = newsletterMapper.toDto(updatedNewsletter);

        restNewsletterMockMvc.perform(put("/api/newsletters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newsletterDTO)))
            .andExpect(status().isOk());

        // Validate the Newsletter in the database
        List<Newsletter> newsletterList = newsletterRepository.findAll();
        assertThat(newsletterList).hasSize(databaseSizeBeforeUpdate);
        Newsletter testNewsletter = newsletterList.get(newsletterList.size() - 1);
        assertThat(testNewsletter.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testNewsletter.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingNewsletter() throws Exception {
        int databaseSizeBeforeUpdate = newsletterRepository.findAll().size();

        // Create the Newsletter
        NewsletterDTO newsletterDTO = newsletterMapper.toDto(newsletter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNewsletterMockMvc.perform(put("/api/newsletters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newsletterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Newsletter in the database
        List<Newsletter> newsletterList = newsletterRepository.findAll();
        assertThat(newsletterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNewsletter() throws Exception {
        // Initialize the database
        newsletterRepository.saveAndFlush(newsletter);

        int databaseSizeBeforeDelete = newsletterRepository.findAll().size();

        // Delete the newsletter
        restNewsletterMockMvc.perform(delete("/api/newsletters/{id}", newsletter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Newsletter> newsletterList = newsletterRepository.findAll();
        assertThat(newsletterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Newsletter.class);
        Newsletter newsletter1 = new Newsletter();
        newsletter1.setId(1L);
        Newsletter newsletter2 = new Newsletter();
        newsletter2.setId(newsletter1.getId());
        assertThat(newsletter1).isEqualTo(newsletter2);
        newsletter2.setId(2L);
        assertThat(newsletter1).isNotEqualTo(newsletter2);
        newsletter1.setId(null);
        assertThat(newsletter1).isNotEqualTo(newsletter2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewsletterDTO.class);
        NewsletterDTO newsletterDTO1 = new NewsletterDTO();
        newsletterDTO1.setId(1L);
        NewsletterDTO newsletterDTO2 = new NewsletterDTO();
        assertThat(newsletterDTO1).isNotEqualTo(newsletterDTO2);
        newsletterDTO2.setId(newsletterDTO1.getId());
        assertThat(newsletterDTO1).isEqualTo(newsletterDTO2);
        newsletterDTO2.setId(2L);
        assertThat(newsletterDTO1).isNotEqualTo(newsletterDTO2);
        newsletterDTO1.setId(null);
        assertThat(newsletterDTO1).isNotEqualTo(newsletterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(newsletterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(newsletterMapper.fromId(null)).isNull();
    }
}
