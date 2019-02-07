package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Cmessage;
import com.jhipsterpress.web.domain.Community;
import com.jhipsterpress.web.repository.CmessageRepository;
import com.jhipsterpress.web.service.CmessageService;
import com.jhipsterpress.web.service.dto.CmessageDTO;
import com.jhipsterpress.web.service.mapper.CmessageMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.CmessageCriteria;
import com.jhipsterpress.web.service.CmessageQueryService;

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
 * Test class for the CmessageResource REST controller.
 *
 * @see CmessageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class CmessageResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MESSAGE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_TEXT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELIVERED = false;
    private static final Boolean UPDATED_IS_DELIVERED = true;

    @Autowired
    private CmessageRepository cmessageRepository;

    @Autowired
    private CmessageMapper cmessageMapper;

    @Autowired
    private CmessageService cmessageService;

    @Autowired
    private CmessageQueryService cmessageQueryService;

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

    private MockMvc restCmessageMockMvc;

    private Cmessage cmessage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CmessageResource cmessageResource = new CmessageResource(cmessageService, cmessageQueryService);
        this.restCmessageMockMvc = MockMvcBuilders.standaloneSetup(cmessageResource)
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
    public static Cmessage createEntity(EntityManager em) {
        Cmessage cmessage = new Cmessage()
            .creationDate(DEFAULT_CREATION_DATE)
            .messageText(DEFAULT_MESSAGE_TEXT)
            .isDelivered(DEFAULT_IS_DELIVERED);
        return cmessage;
    }

    @Before
    public void initTest() {
        cmessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createCmessage() throws Exception {
        int databaseSizeBeforeCreate = cmessageRepository.findAll().size();

        // Create the Cmessage
        CmessageDTO cmessageDTO = cmessageMapper.toDto(cmessage);
        restCmessageMockMvc.perform(post("/api/cmessages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cmessageDTO)))
            .andExpect(status().isCreated());

        // Validate the Cmessage in the database
        List<Cmessage> cmessageList = cmessageRepository.findAll();
        assertThat(cmessageList).hasSize(databaseSizeBeforeCreate + 1);
        Cmessage testCmessage = cmessageList.get(cmessageList.size() - 1);
        assertThat(testCmessage.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testCmessage.getMessageText()).isEqualTo(DEFAULT_MESSAGE_TEXT);
        assertThat(testCmessage.isIsDelivered()).isEqualTo(DEFAULT_IS_DELIVERED);
    }

    @Test
    @Transactional
    public void createCmessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cmessageRepository.findAll().size();

        // Create the Cmessage with an existing ID
        cmessage.setId(1L);
        CmessageDTO cmessageDTO = cmessageMapper.toDto(cmessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCmessageMockMvc.perform(post("/api/cmessages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cmessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cmessage in the database
        List<Cmessage> cmessageList = cmessageRepository.findAll();
        assertThat(cmessageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = cmessageRepository.findAll().size();
        // set the field null
        cmessage.setCreationDate(null);

        // Create the Cmessage, which fails.
        CmessageDTO cmessageDTO = cmessageMapper.toDto(cmessage);

        restCmessageMockMvc.perform(post("/api/cmessages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cmessageDTO)))
            .andExpect(status().isBadRequest());

        List<Cmessage> cmessageList = cmessageRepository.findAll();
        assertThat(cmessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMessageTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = cmessageRepository.findAll().size();
        // set the field null
        cmessage.setMessageText(null);

        // Create the Cmessage, which fails.
        CmessageDTO cmessageDTO = cmessageMapper.toDto(cmessage);

        restCmessageMockMvc.perform(post("/api/cmessages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cmessageDTO)))
            .andExpect(status().isBadRequest());

        List<Cmessage> cmessageList = cmessageRepository.findAll();
        assertThat(cmessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCmessages() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get all the cmessageList
        restCmessageMockMvc.perform(get("/api/cmessages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cmessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].messageText").value(hasItem(DEFAULT_MESSAGE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].isDelivered").value(hasItem(DEFAULT_IS_DELIVERED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCmessage() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get the cmessage
        restCmessageMockMvc.perform(get("/api/cmessages/{id}", cmessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cmessage.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.messageText").value(DEFAULT_MESSAGE_TEXT.toString()))
            .andExpect(jsonPath("$.isDelivered").value(DEFAULT_IS_DELIVERED.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllCmessagesByCreationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get all the cmessageList where creationDate equals to DEFAULT_CREATION_DATE
        defaultCmessageShouldBeFound("creationDate.equals=" + DEFAULT_CREATION_DATE);

        // Get all the cmessageList where creationDate equals to UPDATED_CREATION_DATE
        defaultCmessageShouldNotBeFound("creationDate.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllCmessagesByCreationDateIsInShouldWork() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get all the cmessageList where creationDate in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultCmessageShouldBeFound("creationDate.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the cmessageList where creationDate equals to UPDATED_CREATION_DATE
        defaultCmessageShouldNotBeFound("creationDate.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllCmessagesByCreationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get all the cmessageList where creationDate is not null
        defaultCmessageShouldBeFound("creationDate.specified=true");

        // Get all the cmessageList where creationDate is null
        defaultCmessageShouldNotBeFound("creationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCmessagesByMessageTextIsEqualToSomething() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get all the cmessageList where messageText equals to DEFAULT_MESSAGE_TEXT
        defaultCmessageShouldBeFound("messageText.equals=" + DEFAULT_MESSAGE_TEXT);

        // Get all the cmessageList where messageText equals to UPDATED_MESSAGE_TEXT
        defaultCmessageShouldNotBeFound("messageText.equals=" + UPDATED_MESSAGE_TEXT);
    }

    @Test
    @Transactional
    public void getAllCmessagesByMessageTextIsInShouldWork() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get all the cmessageList where messageText in DEFAULT_MESSAGE_TEXT or UPDATED_MESSAGE_TEXT
        defaultCmessageShouldBeFound("messageText.in=" + DEFAULT_MESSAGE_TEXT + "," + UPDATED_MESSAGE_TEXT);

        // Get all the cmessageList where messageText equals to UPDATED_MESSAGE_TEXT
        defaultCmessageShouldNotBeFound("messageText.in=" + UPDATED_MESSAGE_TEXT);
    }

    @Test
    @Transactional
    public void getAllCmessagesByMessageTextIsNullOrNotNull() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get all the cmessageList where messageText is not null
        defaultCmessageShouldBeFound("messageText.specified=true");

        // Get all the cmessageList where messageText is null
        defaultCmessageShouldNotBeFound("messageText.specified=false");
    }

    @Test
    @Transactional
    public void getAllCmessagesByIsDeliveredIsEqualToSomething() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get all the cmessageList where isDelivered equals to DEFAULT_IS_DELIVERED
        defaultCmessageShouldBeFound("isDelivered.equals=" + DEFAULT_IS_DELIVERED);

        // Get all the cmessageList where isDelivered equals to UPDATED_IS_DELIVERED
        defaultCmessageShouldNotBeFound("isDelivered.equals=" + UPDATED_IS_DELIVERED);
    }

    @Test
    @Transactional
    public void getAllCmessagesByIsDeliveredIsInShouldWork() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get all the cmessageList where isDelivered in DEFAULT_IS_DELIVERED or UPDATED_IS_DELIVERED
        defaultCmessageShouldBeFound("isDelivered.in=" + DEFAULT_IS_DELIVERED + "," + UPDATED_IS_DELIVERED);

        // Get all the cmessageList where isDelivered equals to UPDATED_IS_DELIVERED
        defaultCmessageShouldNotBeFound("isDelivered.in=" + UPDATED_IS_DELIVERED);
    }

    @Test
    @Transactional
    public void getAllCmessagesByIsDeliveredIsNullOrNotNull() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        // Get all the cmessageList where isDelivered is not null
        defaultCmessageShouldBeFound("isDelivered.specified=true");

        // Get all the cmessageList where isDelivered is null
        defaultCmessageShouldNotBeFound("isDelivered.specified=false");
    }

    @Test
    @Transactional
    public void getAllCmessagesByCsenderIsEqualToSomething() throws Exception {
        // Initialize the database
        Community csender = CommunityResourceIntTest.createEntity(em);
        em.persist(csender);
        em.flush();
        cmessage.setCsender(csender);
        cmessageRepository.saveAndFlush(cmessage);
        Long csenderId = csender.getId();

        // Get all the cmessageList where csender equals to csenderId
        defaultCmessageShouldBeFound("csenderId.equals=" + csenderId);

        // Get all the cmessageList where csender equals to csenderId + 1
        defaultCmessageShouldNotBeFound("csenderId.equals=" + (csenderId + 1));
    }


    @Test
    @Transactional
    public void getAllCmessagesByCreceiverIsEqualToSomething() throws Exception {
        // Initialize the database
        Community creceiver = CommunityResourceIntTest.createEntity(em);
        em.persist(creceiver);
        em.flush();
        cmessage.setCreceiver(creceiver);
        cmessageRepository.saveAndFlush(cmessage);
        Long creceiverId = creceiver.getId();

        // Get all the cmessageList where creceiver equals to creceiverId
        defaultCmessageShouldBeFound("creceiverId.equals=" + creceiverId);

        // Get all the cmessageList where creceiver equals to creceiverId + 1
        defaultCmessageShouldNotBeFound("creceiverId.equals=" + (creceiverId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCmessageShouldBeFound(String filter) throws Exception {
        restCmessageMockMvc.perform(get("/api/cmessages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cmessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].messageText").value(hasItem(DEFAULT_MESSAGE_TEXT)))
            .andExpect(jsonPath("$.[*].isDelivered").value(hasItem(DEFAULT_IS_DELIVERED.booleanValue())));

        // Check, that the count call also returns 1
        restCmessageMockMvc.perform(get("/api/cmessages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCmessageShouldNotBeFound(String filter) throws Exception {
        restCmessageMockMvc.perform(get("/api/cmessages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCmessageMockMvc.perform(get("/api/cmessages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCmessage() throws Exception {
        // Get the cmessage
        restCmessageMockMvc.perform(get("/api/cmessages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCmessage() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        int databaseSizeBeforeUpdate = cmessageRepository.findAll().size();

        // Update the cmessage
        Cmessage updatedCmessage = cmessageRepository.findById(cmessage.getId()).get();
        // Disconnect from session so that the updates on updatedCmessage are not directly saved in db
        em.detach(updatedCmessage);
        updatedCmessage
            .creationDate(UPDATED_CREATION_DATE)
            .messageText(UPDATED_MESSAGE_TEXT)
            .isDelivered(UPDATED_IS_DELIVERED);
        CmessageDTO cmessageDTO = cmessageMapper.toDto(updatedCmessage);

        restCmessageMockMvc.perform(put("/api/cmessages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cmessageDTO)))
            .andExpect(status().isOk());

        // Validate the Cmessage in the database
        List<Cmessage> cmessageList = cmessageRepository.findAll();
        assertThat(cmessageList).hasSize(databaseSizeBeforeUpdate);
        Cmessage testCmessage = cmessageList.get(cmessageList.size() - 1);
        assertThat(testCmessage.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testCmessage.getMessageText()).isEqualTo(UPDATED_MESSAGE_TEXT);
        assertThat(testCmessage.isIsDelivered()).isEqualTo(UPDATED_IS_DELIVERED);
    }

    @Test
    @Transactional
    public void updateNonExistingCmessage() throws Exception {
        int databaseSizeBeforeUpdate = cmessageRepository.findAll().size();

        // Create the Cmessage
        CmessageDTO cmessageDTO = cmessageMapper.toDto(cmessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCmessageMockMvc.perform(put("/api/cmessages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cmessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cmessage in the database
        List<Cmessage> cmessageList = cmessageRepository.findAll();
        assertThat(cmessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCmessage() throws Exception {
        // Initialize the database
        cmessageRepository.saveAndFlush(cmessage);

        int databaseSizeBeforeDelete = cmessageRepository.findAll().size();

        // Delete the cmessage
        restCmessageMockMvc.perform(delete("/api/cmessages/{id}", cmessage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cmessage> cmessageList = cmessageRepository.findAll();
        assertThat(cmessageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cmessage.class);
        Cmessage cmessage1 = new Cmessage();
        cmessage1.setId(1L);
        Cmessage cmessage2 = new Cmessage();
        cmessage2.setId(cmessage1.getId());
        assertThat(cmessage1).isEqualTo(cmessage2);
        cmessage2.setId(2L);
        assertThat(cmessage1).isNotEqualTo(cmessage2);
        cmessage1.setId(null);
        assertThat(cmessage1).isNotEqualTo(cmessage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CmessageDTO.class);
        CmessageDTO cmessageDTO1 = new CmessageDTO();
        cmessageDTO1.setId(1L);
        CmessageDTO cmessageDTO2 = new CmessageDTO();
        assertThat(cmessageDTO1).isNotEqualTo(cmessageDTO2);
        cmessageDTO2.setId(cmessageDTO1.getId());
        assertThat(cmessageDTO1).isEqualTo(cmessageDTO2);
        cmessageDTO2.setId(2L);
        assertThat(cmessageDTO1).isNotEqualTo(cmessageDTO2);
        cmessageDTO1.setId(null);
        assertThat(cmessageDTO1).isNotEqualTo(cmessageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cmessageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cmessageMapper.fromId(null)).isNull();
    }
}
