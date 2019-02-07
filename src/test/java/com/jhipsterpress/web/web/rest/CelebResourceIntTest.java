package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Celeb;
import com.jhipsterpress.web.domain.Uprofile;
import com.jhipsterpress.web.repository.CelebRepository;
import com.jhipsterpress.web.service.CelebService;
import com.jhipsterpress.web.service.dto.CelebDTO;
import com.jhipsterpress.web.service.mapper.CelebMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.CelebCriteria;
import com.jhipsterpress.web.service.CelebQueryService;

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
 * Test class for the CelebResource REST controller.
 *
 * @see CelebResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class CelebResourceIntTest {

    private static final String DEFAULT_CELEB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CELEB_NAME = "BBBBBBBBBB";

    @Autowired
    private CelebRepository celebRepository;

    @Mock
    private CelebRepository celebRepositoryMock;

    @Autowired
    private CelebMapper celebMapper;

    @Mock
    private CelebService celebServiceMock;

    @Autowired
    private CelebService celebService;

    @Autowired
    private CelebQueryService celebQueryService;

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

    private MockMvc restCelebMockMvc;

    private Celeb celeb;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CelebResource celebResource = new CelebResource(celebService, celebQueryService);
        this.restCelebMockMvc = MockMvcBuilders.standaloneSetup(celebResource)
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
    public static Celeb createEntity(EntityManager em) {
        Celeb celeb = new Celeb()
            .celebName(DEFAULT_CELEB_NAME);
        return celeb;
    }

    @Before
    public void initTest() {
        celeb = createEntity(em);
    }

    @Test
    @Transactional
    public void createCeleb() throws Exception {
        int databaseSizeBeforeCreate = celebRepository.findAll().size();

        // Create the Celeb
        CelebDTO celebDTO = celebMapper.toDto(celeb);
        restCelebMockMvc.perform(post("/api/celebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(celebDTO)))
            .andExpect(status().isCreated());

        // Validate the Celeb in the database
        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeCreate + 1);
        Celeb testCeleb = celebList.get(celebList.size() - 1);
        assertThat(testCeleb.getCelebName()).isEqualTo(DEFAULT_CELEB_NAME);
    }

    @Test
    @Transactional
    public void createCelebWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = celebRepository.findAll().size();

        // Create the Celeb with an existing ID
        celeb.setId(1L);
        CelebDTO celebDTO = celebMapper.toDto(celeb);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCelebMockMvc.perform(post("/api/celebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(celebDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Celeb in the database
        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCelebNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = celebRepository.findAll().size();
        // set the field null
        celeb.setCelebName(null);

        // Create the Celeb, which fails.
        CelebDTO celebDTO = celebMapper.toDto(celeb);

        restCelebMockMvc.perform(post("/api/celebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(celebDTO)))
            .andExpect(status().isBadRequest());

        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCelebs() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);

        // Get all the celebList
        restCelebMockMvc.perform(get("/api/celebs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(celeb.getId().intValue())))
            .andExpect(jsonPath("$.[*].celebName").value(hasItem(DEFAULT_CELEB_NAME.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCelebsWithEagerRelationshipsIsEnabled() throws Exception {
        CelebResource celebResource = new CelebResource(celebServiceMock, celebQueryService);
        when(celebServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restCelebMockMvc = MockMvcBuilders.standaloneSetup(celebResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCelebMockMvc.perform(get("/api/celebs?eagerload=true"))
        .andExpect(status().isOk());

        verify(celebServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCelebsWithEagerRelationshipsIsNotEnabled() throws Exception {
        CelebResource celebResource = new CelebResource(celebServiceMock, celebQueryService);
            when(celebServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restCelebMockMvc = MockMvcBuilders.standaloneSetup(celebResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCelebMockMvc.perform(get("/api/celebs?eagerload=true"))
        .andExpect(status().isOk());

            verify(celebServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCeleb() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);

        // Get the celeb
        restCelebMockMvc.perform(get("/api/celebs/{id}", celeb.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(celeb.getId().intValue()))
            .andExpect(jsonPath("$.celebName").value(DEFAULT_CELEB_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllCelebsByCelebNameIsEqualToSomething() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);

        // Get all the celebList where celebName equals to DEFAULT_CELEB_NAME
        defaultCelebShouldBeFound("celebName.equals=" + DEFAULT_CELEB_NAME);

        // Get all the celebList where celebName equals to UPDATED_CELEB_NAME
        defaultCelebShouldNotBeFound("celebName.equals=" + UPDATED_CELEB_NAME);
    }

    @Test
    @Transactional
    public void getAllCelebsByCelebNameIsInShouldWork() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);

        // Get all the celebList where celebName in DEFAULT_CELEB_NAME or UPDATED_CELEB_NAME
        defaultCelebShouldBeFound("celebName.in=" + DEFAULT_CELEB_NAME + "," + UPDATED_CELEB_NAME);

        // Get all the celebList where celebName equals to UPDATED_CELEB_NAME
        defaultCelebShouldNotBeFound("celebName.in=" + UPDATED_CELEB_NAME);
    }

    @Test
    @Transactional
    public void getAllCelebsByCelebNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);

        // Get all the celebList where celebName is not null
        defaultCelebShouldBeFound("celebName.specified=true");

        // Get all the celebList where celebName is null
        defaultCelebShouldNotBeFound("celebName.specified=false");
    }

    @Test
    @Transactional
    public void getAllCelebsByUprofileIsEqualToSomething() throws Exception {
        // Initialize the database
        Uprofile uprofile = UprofileResourceIntTest.createEntity(em);
        em.persist(uprofile);
        em.flush();
        celeb.addUprofile(uprofile);
        celebRepository.saveAndFlush(celeb);
        Long uprofileId = uprofile.getId();

        // Get all the celebList where uprofile equals to uprofileId
        defaultCelebShouldBeFound("uprofileId.equals=" + uprofileId);

        // Get all the celebList where uprofile equals to uprofileId + 1
        defaultCelebShouldNotBeFound("uprofileId.equals=" + (uprofileId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCelebShouldBeFound(String filter) throws Exception {
        restCelebMockMvc.perform(get("/api/celebs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(celeb.getId().intValue())))
            .andExpect(jsonPath("$.[*].celebName").value(hasItem(DEFAULT_CELEB_NAME)));

        // Check, that the count call also returns 1
        restCelebMockMvc.perform(get("/api/celebs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCelebShouldNotBeFound(String filter) throws Exception {
        restCelebMockMvc.perform(get("/api/celebs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCelebMockMvc.perform(get("/api/celebs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCeleb() throws Exception {
        // Get the celeb
        restCelebMockMvc.perform(get("/api/celebs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCeleb() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);

        int databaseSizeBeforeUpdate = celebRepository.findAll().size();

        // Update the celeb
        Celeb updatedCeleb = celebRepository.findById(celeb.getId()).get();
        // Disconnect from session so that the updates on updatedCeleb are not directly saved in db
        em.detach(updatedCeleb);
        updatedCeleb
            .celebName(UPDATED_CELEB_NAME);
        CelebDTO celebDTO = celebMapper.toDto(updatedCeleb);

        restCelebMockMvc.perform(put("/api/celebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(celebDTO)))
            .andExpect(status().isOk());

        // Validate the Celeb in the database
        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeUpdate);
        Celeb testCeleb = celebList.get(celebList.size() - 1);
        assertThat(testCeleb.getCelebName()).isEqualTo(UPDATED_CELEB_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCeleb() throws Exception {
        int databaseSizeBeforeUpdate = celebRepository.findAll().size();

        // Create the Celeb
        CelebDTO celebDTO = celebMapper.toDto(celeb);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCelebMockMvc.perform(put("/api/celebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(celebDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Celeb in the database
        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCeleb() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);

        int databaseSizeBeforeDelete = celebRepository.findAll().size();

        // Delete the celeb
        restCelebMockMvc.perform(delete("/api/celebs/{id}", celeb.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Celeb.class);
        Celeb celeb1 = new Celeb();
        celeb1.setId(1L);
        Celeb celeb2 = new Celeb();
        celeb2.setId(celeb1.getId());
        assertThat(celeb1).isEqualTo(celeb2);
        celeb2.setId(2L);
        assertThat(celeb1).isNotEqualTo(celeb2);
        celeb1.setId(null);
        assertThat(celeb1).isNotEqualTo(celeb2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CelebDTO.class);
        CelebDTO celebDTO1 = new CelebDTO();
        celebDTO1.setId(1L);
        CelebDTO celebDTO2 = new CelebDTO();
        assertThat(celebDTO1).isNotEqualTo(celebDTO2);
        celebDTO2.setId(celebDTO1.getId());
        assertThat(celebDTO1).isEqualTo(celebDTO2);
        celebDTO2.setId(2L);
        assertThat(celebDTO1).isNotEqualTo(celebDTO2);
        celebDTO1.setId(null);
        assertThat(celebDTO1).isNotEqualTo(celebDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(celebMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(celebMapper.fromId(null)).isNull();
    }
}
