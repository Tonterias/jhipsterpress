package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Cceleb;
import com.jhipsterpress.web.domain.Community;
import com.jhipsterpress.web.repository.CcelebRepository;
import com.jhipsterpress.web.service.CcelebService;
import com.jhipsterpress.web.service.dto.CcelebDTO;
import com.jhipsterpress.web.service.mapper.CcelebMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.CcelebCriteria;
import com.jhipsterpress.web.service.CcelebQueryService;

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
 * Test class for the CcelebResource REST controller.
 *
 * @see CcelebResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class CcelebResourceIntTest {

    private static final String DEFAULT_CELEB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CELEB_NAME = "BBBBBBBBBB";

    @Autowired
    private CcelebRepository ccelebRepository;

    @Mock
    private CcelebRepository ccelebRepositoryMock;

    @Autowired
    private CcelebMapper ccelebMapper;

    @Mock
    private CcelebService ccelebServiceMock;

    @Autowired
    private CcelebService ccelebService;

    @Autowired
    private CcelebQueryService ccelebQueryService;

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

    private MockMvc restCcelebMockMvc;

    private Cceleb cceleb;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CcelebResource ccelebResource = new CcelebResource(ccelebService, ccelebQueryService);
        this.restCcelebMockMvc = MockMvcBuilders.standaloneSetup(ccelebResource)
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
    public static Cceleb createEntity(EntityManager em) {
        Cceleb cceleb = new Cceleb()
            .celebName(DEFAULT_CELEB_NAME);
        return cceleb;
    }

    @Before
    public void initTest() {
        cceleb = createEntity(em);
    }

    @Test
    @Transactional
    public void createCceleb() throws Exception {
        int databaseSizeBeforeCreate = ccelebRepository.findAll().size();

        // Create the Cceleb
        CcelebDTO ccelebDTO = ccelebMapper.toDto(cceleb);
        restCcelebMockMvc.perform(post("/api/ccelebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ccelebDTO)))
            .andExpect(status().isCreated());

        // Validate the Cceleb in the database
        List<Cceleb> ccelebList = ccelebRepository.findAll();
        assertThat(ccelebList).hasSize(databaseSizeBeforeCreate + 1);
        Cceleb testCceleb = ccelebList.get(ccelebList.size() - 1);
        assertThat(testCceleb.getCelebName()).isEqualTo(DEFAULT_CELEB_NAME);
    }

    @Test
    @Transactional
    public void createCcelebWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ccelebRepository.findAll().size();

        // Create the Cceleb with an existing ID
        cceleb.setId(1L);
        CcelebDTO ccelebDTO = ccelebMapper.toDto(cceleb);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCcelebMockMvc.perform(post("/api/ccelebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ccelebDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cceleb in the database
        List<Cceleb> ccelebList = ccelebRepository.findAll();
        assertThat(ccelebList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCelebNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = ccelebRepository.findAll().size();
        // set the field null
        cceleb.setCelebName(null);

        // Create the Cceleb, which fails.
        CcelebDTO ccelebDTO = ccelebMapper.toDto(cceleb);

        restCcelebMockMvc.perform(post("/api/ccelebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ccelebDTO)))
            .andExpect(status().isBadRequest());

        List<Cceleb> ccelebList = ccelebRepository.findAll();
        assertThat(ccelebList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCcelebs() throws Exception {
        // Initialize the database
        ccelebRepository.saveAndFlush(cceleb);

        // Get all the ccelebList
        restCcelebMockMvc.perform(get("/api/ccelebs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cceleb.getId().intValue())))
            .andExpect(jsonPath("$.[*].celebName").value(hasItem(DEFAULT_CELEB_NAME.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCcelebsWithEagerRelationshipsIsEnabled() throws Exception {
        CcelebResource ccelebResource = new CcelebResource(ccelebServiceMock, ccelebQueryService);
        when(ccelebServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restCcelebMockMvc = MockMvcBuilders.standaloneSetup(ccelebResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCcelebMockMvc.perform(get("/api/ccelebs?eagerload=true"))
        .andExpect(status().isOk());

        verify(ccelebServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCcelebsWithEagerRelationshipsIsNotEnabled() throws Exception {
        CcelebResource ccelebResource = new CcelebResource(ccelebServiceMock, ccelebQueryService);
            when(ccelebServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restCcelebMockMvc = MockMvcBuilders.standaloneSetup(ccelebResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCcelebMockMvc.perform(get("/api/ccelebs?eagerload=true"))
        .andExpect(status().isOk());

            verify(ccelebServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCceleb() throws Exception {
        // Initialize the database
        ccelebRepository.saveAndFlush(cceleb);

        // Get the cceleb
        restCcelebMockMvc.perform(get("/api/ccelebs/{id}", cceleb.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cceleb.getId().intValue()))
            .andExpect(jsonPath("$.celebName").value(DEFAULT_CELEB_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllCcelebsByCelebNameIsEqualToSomething() throws Exception {
        // Initialize the database
        ccelebRepository.saveAndFlush(cceleb);

        // Get all the ccelebList where celebName equals to DEFAULT_CELEB_NAME
        defaultCcelebShouldBeFound("celebName.equals=" + DEFAULT_CELEB_NAME);

        // Get all the ccelebList where celebName equals to UPDATED_CELEB_NAME
        defaultCcelebShouldNotBeFound("celebName.equals=" + UPDATED_CELEB_NAME);
    }

    @Test
    @Transactional
    public void getAllCcelebsByCelebNameIsInShouldWork() throws Exception {
        // Initialize the database
        ccelebRepository.saveAndFlush(cceleb);

        // Get all the ccelebList where celebName in DEFAULT_CELEB_NAME or UPDATED_CELEB_NAME
        defaultCcelebShouldBeFound("celebName.in=" + DEFAULT_CELEB_NAME + "," + UPDATED_CELEB_NAME);

        // Get all the ccelebList where celebName equals to UPDATED_CELEB_NAME
        defaultCcelebShouldNotBeFound("celebName.in=" + UPDATED_CELEB_NAME);
    }

    @Test
    @Transactional
    public void getAllCcelebsByCelebNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        ccelebRepository.saveAndFlush(cceleb);

        // Get all the ccelebList where celebName is not null
        defaultCcelebShouldBeFound("celebName.specified=true");

        // Get all the ccelebList where celebName is null
        defaultCcelebShouldNotBeFound("celebName.specified=false");
    }

    @Test
    @Transactional
    public void getAllCcelebsByCommunityIsEqualToSomething() throws Exception {
        // Initialize the database
        Community community = CommunityResourceIntTest.createEntity(em);
        em.persist(community);
        em.flush();
        cceleb.addCommunity(community);
        ccelebRepository.saveAndFlush(cceleb);
        Long communityId = community.getId();

        // Get all the ccelebList where community equals to communityId
        defaultCcelebShouldBeFound("communityId.equals=" + communityId);

        // Get all the ccelebList where community equals to communityId + 1
        defaultCcelebShouldNotBeFound("communityId.equals=" + (communityId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCcelebShouldBeFound(String filter) throws Exception {
        restCcelebMockMvc.perform(get("/api/ccelebs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cceleb.getId().intValue())))
            .andExpect(jsonPath("$.[*].celebName").value(hasItem(DEFAULT_CELEB_NAME)));

        // Check, that the count call also returns 1
        restCcelebMockMvc.perform(get("/api/ccelebs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCcelebShouldNotBeFound(String filter) throws Exception {
        restCcelebMockMvc.perform(get("/api/ccelebs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCcelebMockMvc.perform(get("/api/ccelebs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCceleb() throws Exception {
        // Get the cceleb
        restCcelebMockMvc.perform(get("/api/ccelebs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCceleb() throws Exception {
        // Initialize the database
        ccelebRepository.saveAndFlush(cceleb);

        int databaseSizeBeforeUpdate = ccelebRepository.findAll().size();

        // Update the cceleb
        Cceleb updatedCceleb = ccelebRepository.findById(cceleb.getId()).get();
        // Disconnect from session so that the updates on updatedCceleb are not directly saved in db
        em.detach(updatedCceleb);
        updatedCceleb
            .celebName(UPDATED_CELEB_NAME);
        CcelebDTO ccelebDTO = ccelebMapper.toDto(updatedCceleb);

        restCcelebMockMvc.perform(put("/api/ccelebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ccelebDTO)))
            .andExpect(status().isOk());

        // Validate the Cceleb in the database
        List<Cceleb> ccelebList = ccelebRepository.findAll();
        assertThat(ccelebList).hasSize(databaseSizeBeforeUpdate);
        Cceleb testCceleb = ccelebList.get(ccelebList.size() - 1);
        assertThat(testCceleb.getCelebName()).isEqualTo(UPDATED_CELEB_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCceleb() throws Exception {
        int databaseSizeBeforeUpdate = ccelebRepository.findAll().size();

        // Create the Cceleb
        CcelebDTO ccelebDTO = ccelebMapper.toDto(cceleb);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCcelebMockMvc.perform(put("/api/ccelebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ccelebDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cceleb in the database
        List<Cceleb> ccelebList = ccelebRepository.findAll();
        assertThat(ccelebList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCceleb() throws Exception {
        // Initialize the database
        ccelebRepository.saveAndFlush(cceleb);

        int databaseSizeBeforeDelete = ccelebRepository.findAll().size();

        // Delete the cceleb
        restCcelebMockMvc.perform(delete("/api/ccelebs/{id}", cceleb.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cceleb> ccelebList = ccelebRepository.findAll();
        assertThat(ccelebList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cceleb.class);
        Cceleb cceleb1 = new Cceleb();
        cceleb1.setId(1L);
        Cceleb cceleb2 = new Cceleb();
        cceleb2.setId(cceleb1.getId());
        assertThat(cceleb1).isEqualTo(cceleb2);
        cceleb2.setId(2L);
        assertThat(cceleb1).isNotEqualTo(cceleb2);
        cceleb1.setId(null);
        assertThat(cceleb1).isNotEqualTo(cceleb2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CcelebDTO.class);
        CcelebDTO ccelebDTO1 = new CcelebDTO();
        ccelebDTO1.setId(1L);
        CcelebDTO ccelebDTO2 = new CcelebDTO();
        assertThat(ccelebDTO1).isNotEqualTo(ccelebDTO2);
        ccelebDTO2.setId(ccelebDTO1.getId());
        assertThat(ccelebDTO1).isEqualTo(ccelebDTO2);
        ccelebDTO2.setId(2L);
        assertThat(ccelebDTO1).isNotEqualTo(ccelebDTO2);
        ccelebDTO1.setId(null);
        assertThat(ccelebDTO1).isNotEqualTo(ccelebDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ccelebMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ccelebMapper.fromId(null)).isNull();
    }
}
