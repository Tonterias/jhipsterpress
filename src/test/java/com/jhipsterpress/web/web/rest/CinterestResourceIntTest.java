package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Cinterest;
import com.jhipsterpress.web.domain.Community;
import com.jhipsterpress.web.repository.CinterestRepository;
import com.jhipsterpress.web.service.CinterestService;
import com.jhipsterpress.web.service.dto.CinterestDTO;
import com.jhipsterpress.web.service.mapper.CinterestMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.CinterestCriteria;
import com.jhipsterpress.web.service.CinterestQueryService;

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
 * Test class for the CinterestResource REST controller.
 *
 * @see CinterestResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class CinterestResourceIntTest {

    private static final String DEFAULT_INTEREST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INTEREST_NAME = "BBBBBBBBBB";

    @Autowired
    private CinterestRepository cinterestRepository;

    @Mock
    private CinterestRepository cinterestRepositoryMock;

    @Autowired
    private CinterestMapper cinterestMapper;

    @Mock
    private CinterestService cinterestServiceMock;

    @Autowired
    private CinterestService cinterestService;

    @Autowired
    private CinterestQueryService cinterestQueryService;

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

    private MockMvc restCinterestMockMvc;

    private Cinterest cinterest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CinterestResource cinterestResource = new CinterestResource(cinterestService, cinterestQueryService);
        this.restCinterestMockMvc = MockMvcBuilders.standaloneSetup(cinterestResource)
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
    public static Cinterest createEntity(EntityManager em) {
        Cinterest cinterest = new Cinterest()
            .interestName(DEFAULT_INTEREST_NAME);
        return cinterest;
    }

    @Before
    public void initTest() {
        cinterest = createEntity(em);
    }

    @Test
    @Transactional
    public void createCinterest() throws Exception {
        int databaseSizeBeforeCreate = cinterestRepository.findAll().size();

        // Create the Cinterest
        CinterestDTO cinterestDTO = cinterestMapper.toDto(cinterest);
        restCinterestMockMvc.perform(post("/api/cinterests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cinterestDTO)))
            .andExpect(status().isCreated());

        // Validate the Cinterest in the database
        List<Cinterest> cinterestList = cinterestRepository.findAll();
        assertThat(cinterestList).hasSize(databaseSizeBeforeCreate + 1);
        Cinterest testCinterest = cinterestList.get(cinterestList.size() - 1);
        assertThat(testCinterest.getInterestName()).isEqualTo(DEFAULT_INTEREST_NAME);
    }

    @Test
    @Transactional
    public void createCinterestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cinterestRepository.findAll().size();

        // Create the Cinterest with an existing ID
        cinterest.setId(1L);
        CinterestDTO cinterestDTO = cinterestMapper.toDto(cinterest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCinterestMockMvc.perform(post("/api/cinterests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cinterestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cinterest in the database
        List<Cinterest> cinterestList = cinterestRepository.findAll();
        assertThat(cinterestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkInterestNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cinterestRepository.findAll().size();
        // set the field null
        cinterest.setInterestName(null);

        // Create the Cinterest, which fails.
        CinterestDTO cinterestDTO = cinterestMapper.toDto(cinterest);

        restCinterestMockMvc.perform(post("/api/cinterests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cinterestDTO)))
            .andExpect(status().isBadRequest());

        List<Cinterest> cinterestList = cinterestRepository.findAll();
        assertThat(cinterestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCinterests() throws Exception {
        // Initialize the database
        cinterestRepository.saveAndFlush(cinterest);

        // Get all the cinterestList
        restCinterestMockMvc.perform(get("/api/cinterests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cinterest.getId().intValue())))
            .andExpect(jsonPath("$.[*].interestName").value(hasItem(DEFAULT_INTEREST_NAME.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCinterestsWithEagerRelationshipsIsEnabled() throws Exception {
        CinterestResource cinterestResource = new CinterestResource(cinterestServiceMock, cinterestQueryService);
        when(cinterestServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restCinterestMockMvc = MockMvcBuilders.standaloneSetup(cinterestResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCinterestMockMvc.perform(get("/api/cinterests?eagerload=true"))
        .andExpect(status().isOk());

        verify(cinterestServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCinterestsWithEagerRelationshipsIsNotEnabled() throws Exception {
        CinterestResource cinterestResource = new CinterestResource(cinterestServiceMock, cinterestQueryService);
            when(cinterestServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restCinterestMockMvc = MockMvcBuilders.standaloneSetup(cinterestResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCinterestMockMvc.perform(get("/api/cinterests?eagerload=true"))
        .andExpect(status().isOk());

            verify(cinterestServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCinterest() throws Exception {
        // Initialize the database
        cinterestRepository.saveAndFlush(cinterest);

        // Get the cinterest
        restCinterestMockMvc.perform(get("/api/cinterests/{id}", cinterest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cinterest.getId().intValue()))
            .andExpect(jsonPath("$.interestName").value(DEFAULT_INTEREST_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllCinterestsByInterestNameIsEqualToSomething() throws Exception {
        // Initialize the database
        cinterestRepository.saveAndFlush(cinterest);

        // Get all the cinterestList where interestName equals to DEFAULT_INTEREST_NAME
        defaultCinterestShouldBeFound("interestName.equals=" + DEFAULT_INTEREST_NAME);

        // Get all the cinterestList where interestName equals to UPDATED_INTEREST_NAME
        defaultCinterestShouldNotBeFound("interestName.equals=" + UPDATED_INTEREST_NAME);
    }

    @Test
    @Transactional
    public void getAllCinterestsByInterestNameIsInShouldWork() throws Exception {
        // Initialize the database
        cinterestRepository.saveAndFlush(cinterest);

        // Get all the cinterestList where interestName in DEFAULT_INTEREST_NAME or UPDATED_INTEREST_NAME
        defaultCinterestShouldBeFound("interestName.in=" + DEFAULT_INTEREST_NAME + "," + UPDATED_INTEREST_NAME);

        // Get all the cinterestList where interestName equals to UPDATED_INTEREST_NAME
        defaultCinterestShouldNotBeFound("interestName.in=" + UPDATED_INTEREST_NAME);
    }

    @Test
    @Transactional
    public void getAllCinterestsByInterestNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        cinterestRepository.saveAndFlush(cinterest);

        // Get all the cinterestList where interestName is not null
        defaultCinterestShouldBeFound("interestName.specified=true");

        // Get all the cinterestList where interestName is null
        defaultCinterestShouldNotBeFound("interestName.specified=false");
    }

    @Test
    @Transactional
    public void getAllCinterestsByCommunityIsEqualToSomething() throws Exception {
        // Initialize the database
        Community community = CommunityResourceIntTest.createEntity(em);
        em.persist(community);
        em.flush();
        cinterest.addCommunity(community);
        cinterestRepository.saveAndFlush(cinterest);
        Long communityId = community.getId();

        // Get all the cinterestList where community equals to communityId
        defaultCinterestShouldBeFound("communityId.equals=" + communityId);

        // Get all the cinterestList where community equals to communityId + 1
        defaultCinterestShouldNotBeFound("communityId.equals=" + (communityId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCinterestShouldBeFound(String filter) throws Exception {
        restCinterestMockMvc.perform(get("/api/cinterests?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cinterest.getId().intValue())))
            .andExpect(jsonPath("$.[*].interestName").value(hasItem(DEFAULT_INTEREST_NAME)));

        // Check, that the count call also returns 1
        restCinterestMockMvc.perform(get("/api/cinterests/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCinterestShouldNotBeFound(String filter) throws Exception {
        restCinterestMockMvc.perform(get("/api/cinterests?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCinterestMockMvc.perform(get("/api/cinterests/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCinterest() throws Exception {
        // Get the cinterest
        restCinterestMockMvc.perform(get("/api/cinterests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCinterest() throws Exception {
        // Initialize the database
        cinterestRepository.saveAndFlush(cinterest);

        int databaseSizeBeforeUpdate = cinterestRepository.findAll().size();

        // Update the cinterest
        Cinterest updatedCinterest = cinterestRepository.findById(cinterest.getId()).get();
        // Disconnect from session so that the updates on updatedCinterest are not directly saved in db
        em.detach(updatedCinterest);
        updatedCinterest
            .interestName(UPDATED_INTEREST_NAME);
        CinterestDTO cinterestDTO = cinterestMapper.toDto(updatedCinterest);

        restCinterestMockMvc.perform(put("/api/cinterests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cinterestDTO)))
            .andExpect(status().isOk());

        // Validate the Cinterest in the database
        List<Cinterest> cinterestList = cinterestRepository.findAll();
        assertThat(cinterestList).hasSize(databaseSizeBeforeUpdate);
        Cinterest testCinterest = cinterestList.get(cinterestList.size() - 1);
        assertThat(testCinterest.getInterestName()).isEqualTo(UPDATED_INTEREST_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCinterest() throws Exception {
        int databaseSizeBeforeUpdate = cinterestRepository.findAll().size();

        // Create the Cinterest
        CinterestDTO cinterestDTO = cinterestMapper.toDto(cinterest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCinterestMockMvc.perform(put("/api/cinterests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cinterestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cinterest in the database
        List<Cinterest> cinterestList = cinterestRepository.findAll();
        assertThat(cinterestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCinterest() throws Exception {
        // Initialize the database
        cinterestRepository.saveAndFlush(cinterest);

        int databaseSizeBeforeDelete = cinterestRepository.findAll().size();

        // Delete the cinterest
        restCinterestMockMvc.perform(delete("/api/cinterests/{id}", cinterest.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cinterest> cinterestList = cinterestRepository.findAll();
        assertThat(cinterestList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cinterest.class);
        Cinterest cinterest1 = new Cinterest();
        cinterest1.setId(1L);
        Cinterest cinterest2 = new Cinterest();
        cinterest2.setId(cinterest1.getId());
        assertThat(cinterest1).isEqualTo(cinterest2);
        cinterest2.setId(2L);
        assertThat(cinterest1).isNotEqualTo(cinterest2);
        cinterest1.setId(null);
        assertThat(cinterest1).isNotEqualTo(cinterest2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CinterestDTO.class);
        CinterestDTO cinterestDTO1 = new CinterestDTO();
        cinterestDTO1.setId(1L);
        CinterestDTO cinterestDTO2 = new CinterestDTO();
        assertThat(cinterestDTO1).isNotEqualTo(cinterestDTO2);
        cinterestDTO2.setId(cinterestDTO1.getId());
        assertThat(cinterestDTO1).isEqualTo(cinterestDTO2);
        cinterestDTO2.setId(2L);
        assertThat(cinterestDTO1).isNotEqualTo(cinterestDTO2);
        cinterestDTO1.setId(null);
        assertThat(cinterestDTO1).isNotEqualTo(cinterestDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cinterestMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cinterestMapper.fromId(null)).isNull();
    }
}
