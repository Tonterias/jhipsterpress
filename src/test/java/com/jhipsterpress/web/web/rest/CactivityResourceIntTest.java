package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Cactivity;
import com.jhipsterpress.web.domain.Community;
import com.jhipsterpress.web.repository.CactivityRepository;
import com.jhipsterpress.web.service.CactivityService;
import com.jhipsterpress.web.service.dto.CactivityDTO;
import com.jhipsterpress.web.service.mapper.CactivityMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.CactivityCriteria;
import com.jhipsterpress.web.service.CactivityQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.jhipsterpress.web.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CactivityResource REST controller.
 *
 * @see CactivityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class CactivityResourceIntTest {

    private static final String DEFAULT_ACTIVITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_NAME = "BBBBBBBBBB";

    @Autowired
    private CactivityRepository cactivityRepository;

    @Mock
    private CactivityRepository cactivityRepositoryMock;

    @Autowired
    private CactivityMapper cactivityMapper;

    @Mock
    private CactivityService cactivityServiceMock;

    @Autowired
    private CactivityService cactivityService;

    @Autowired
    private CactivityQueryService cactivityQueryService;

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

    private MockMvc restCactivityMockMvc;

    private Cactivity cactivity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CactivityResource cactivityResource = new CactivityResource(cactivityService, cactivityQueryService);
        this.restCactivityMockMvc = MockMvcBuilders.standaloneSetup(cactivityResource)
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
    public static Cactivity createEntity(EntityManager em) {
        Cactivity cactivity = new Cactivity()
            .activityName(DEFAULT_ACTIVITY_NAME);
        return cactivity;
    }

    @Before
    public void initTest() {
        cactivity = createEntity(em);
    }

    @Test
    @Transactional
    public void createCactivity() throws Exception {
        int databaseSizeBeforeCreate = cactivityRepository.findAll().size();

        // Create the Cactivity
        CactivityDTO cactivityDTO = cactivityMapper.toDto(cactivity);
        restCactivityMockMvc.perform(post("/api/cactivities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cactivityDTO)))
            .andExpect(status().isCreated());

        // Validate the Cactivity in the database
        List<Cactivity> cactivityList = cactivityRepository.findAll();
        assertThat(cactivityList).hasSize(databaseSizeBeforeCreate + 1);
        Cactivity testCactivity = cactivityList.get(cactivityList.size() - 1);
        assertThat(testCactivity.getActivityName()).isEqualTo(DEFAULT_ACTIVITY_NAME);
    }

    @Test
    @Transactional
    public void createCactivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cactivityRepository.findAll().size();

        // Create the Cactivity with an existing ID
        cactivity.setId(1L);
        CactivityDTO cactivityDTO = cactivityMapper.toDto(cactivity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCactivityMockMvc.perform(post("/api/cactivities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cactivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cactivity in the database
        List<Cactivity> cactivityList = cactivityRepository.findAll();
        assertThat(cactivityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkActivityNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cactivityRepository.findAll().size();
        // set the field null
        cactivity.setActivityName(null);

        // Create the Cactivity, which fails.
        CactivityDTO cactivityDTO = cactivityMapper.toDto(cactivity);

        restCactivityMockMvc.perform(post("/api/cactivities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cactivityDTO)))
            .andExpect(status().isBadRequest());

        List<Cactivity> cactivityList = cactivityRepository.findAll();
        assertThat(cactivityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCactivities() throws Exception {
        // Initialize the database
        cactivityRepository.saveAndFlush(cactivity);

        // Get all the cactivityList
        restCactivityMockMvc.perform(get("/api/cactivities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cactivity.getId().intValue())))
            .andExpect(jsonPath("$.[*].activityName").value(hasItem(DEFAULT_ACTIVITY_NAME.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCactivitiesWithEagerRelationshipsIsEnabled() throws Exception {
        CactivityResource cactivityResource = new CactivityResource(cactivityServiceMock, cactivityQueryService);
        when(cactivityServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restCactivityMockMvc = MockMvcBuilders.standaloneSetup(cactivityResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCactivityMockMvc.perform(get("/api/cactivities?eagerload=true"))
        .andExpect(status().isOk());

        verify(cactivityServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCactivitiesWithEagerRelationshipsIsNotEnabled() throws Exception {
        CactivityResource cactivityResource = new CactivityResource(cactivityServiceMock, cactivityQueryService);
            when(cactivityServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restCactivityMockMvc = MockMvcBuilders.standaloneSetup(cactivityResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCactivityMockMvc.perform(get("/api/cactivities?eagerload=true"))
        .andExpect(status().isOk());

            verify(cactivityServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCactivity() throws Exception {
        // Initialize the database
        cactivityRepository.saveAndFlush(cactivity);

        // Get the cactivity
        restCactivityMockMvc.perform(get("/api/cactivities/{id}", cactivity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cactivity.getId().intValue()))
            .andExpect(jsonPath("$.activityName").value(DEFAULT_ACTIVITY_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllCactivitiesByActivityNameIsEqualToSomething() throws Exception {
        // Initialize the database
        cactivityRepository.saveAndFlush(cactivity);

        // Get all the cactivityList where activityName equals to DEFAULT_ACTIVITY_NAME
        defaultCactivityShouldBeFound("activityName.equals=" + DEFAULT_ACTIVITY_NAME);

        // Get all the cactivityList where activityName equals to UPDATED_ACTIVITY_NAME
        defaultCactivityShouldNotBeFound("activityName.equals=" + UPDATED_ACTIVITY_NAME);
    }

    @Test
    @Transactional
    public void getAllCactivitiesByActivityNameIsInShouldWork() throws Exception {
        // Initialize the database
        cactivityRepository.saveAndFlush(cactivity);

        // Get all the cactivityList where activityName in DEFAULT_ACTIVITY_NAME or UPDATED_ACTIVITY_NAME
        defaultCactivityShouldBeFound("activityName.in=" + DEFAULT_ACTIVITY_NAME + "," + UPDATED_ACTIVITY_NAME);

        // Get all the cactivityList where activityName equals to UPDATED_ACTIVITY_NAME
        defaultCactivityShouldNotBeFound("activityName.in=" + UPDATED_ACTIVITY_NAME);
    }

    @Test
    @Transactional
    public void getAllCactivitiesByActivityNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        cactivityRepository.saveAndFlush(cactivity);

        // Get all the cactivityList where activityName is not null
        defaultCactivityShouldBeFound("activityName.specified=true");

        // Get all the cactivityList where activityName is null
        defaultCactivityShouldNotBeFound("activityName.specified=false");
    }

    @Test
    @Transactional
    public void getAllCactivitiesByCommunityIsEqualToSomething() throws Exception {
        // Initialize the database
        Community community = CommunityResourceIntTest.createEntity(em);
        em.persist(community);
        em.flush();
        cactivity.addCommunity(community);
        cactivityRepository.saveAndFlush(cactivity);
        Long communityId = community.getId();

        // Get all the cactivityList where community equals to communityId
        defaultCactivityShouldBeFound("communityId.equals=" + communityId);

        // Get all the cactivityList where community equals to communityId + 1
        defaultCactivityShouldNotBeFound("communityId.equals=" + (communityId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCactivityShouldBeFound(String filter) throws Exception {
        restCactivityMockMvc.perform(get("/api/cactivities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cactivity.getId().intValue())))
            .andExpect(jsonPath("$.[*].activityName").value(hasItem(DEFAULT_ACTIVITY_NAME)));

        // Check, that the count call also returns 1
        restCactivityMockMvc.perform(get("/api/cactivities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCactivityShouldNotBeFound(String filter) throws Exception {
        restCactivityMockMvc.perform(get("/api/cactivities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCactivityMockMvc.perform(get("/api/cactivities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCactivity() throws Exception {
        // Get the cactivity
        restCactivityMockMvc.perform(get("/api/cactivities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCactivity() throws Exception {
        // Initialize the database
        cactivityRepository.saveAndFlush(cactivity);

        int databaseSizeBeforeUpdate = cactivityRepository.findAll().size();

        // Update the cactivity
        Cactivity updatedCactivity = cactivityRepository.findById(cactivity.getId()).get();
        // Disconnect from session so that the updates on updatedCactivity are not directly saved in db
        em.detach(updatedCactivity);
        updatedCactivity
            .activityName(UPDATED_ACTIVITY_NAME);
        CactivityDTO cactivityDTO = cactivityMapper.toDto(updatedCactivity);

        restCactivityMockMvc.perform(put("/api/cactivities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cactivityDTO)))
            .andExpect(status().isOk());

        // Validate the Cactivity in the database
        List<Cactivity> cactivityList = cactivityRepository.findAll();
        assertThat(cactivityList).hasSize(databaseSizeBeforeUpdate);
        Cactivity testCactivity = cactivityList.get(cactivityList.size() - 1);
        assertThat(testCactivity.getActivityName()).isEqualTo(UPDATED_ACTIVITY_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCactivity() throws Exception {
        int databaseSizeBeforeUpdate = cactivityRepository.findAll().size();

        // Create the Cactivity
        CactivityDTO cactivityDTO = cactivityMapper.toDto(cactivity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCactivityMockMvc.perform(put("/api/cactivities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cactivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cactivity in the database
        List<Cactivity> cactivityList = cactivityRepository.findAll();
        assertThat(cactivityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCactivity() throws Exception {
        // Initialize the database
        cactivityRepository.saveAndFlush(cactivity);

        int databaseSizeBeforeDelete = cactivityRepository.findAll().size();

        // Delete the cactivity
        restCactivityMockMvc.perform(delete("/api/cactivities/{id}", cactivity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cactivity> cactivityList = cactivityRepository.findAll();
        assertThat(cactivityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cactivity.class);
        Cactivity cactivity1 = new Cactivity();
        cactivity1.setId(1L);
        Cactivity cactivity2 = new Cactivity();
        cactivity2.setId(cactivity1.getId());
        assertThat(cactivity1).isEqualTo(cactivity2);
        cactivity2.setId(2L);
        assertThat(cactivity1).isNotEqualTo(cactivity2);
        cactivity1.setId(null);
        assertThat(cactivity1).isNotEqualTo(cactivity2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CactivityDTO.class);
        CactivityDTO cactivityDTO1 = new CactivityDTO();
        cactivityDTO1.setId(1L);
        CactivityDTO cactivityDTO2 = new CactivityDTO();
        assertThat(cactivityDTO1).isNotEqualTo(cactivityDTO2);
        cactivityDTO2.setId(cactivityDTO1.getId());
        assertThat(cactivityDTO1).isEqualTo(cactivityDTO2);
        cactivityDTO2.setId(2L);
        assertThat(cactivityDTO1).isNotEqualTo(cactivityDTO2);
        cactivityDTO1.setId(null);
        assertThat(cactivityDTO1).isNotEqualTo(cactivityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cactivityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cactivityMapper.fromId(null)).isNull();
    }
}
