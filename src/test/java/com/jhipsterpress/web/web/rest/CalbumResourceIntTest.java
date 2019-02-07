package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Calbum;
import com.jhipsterpress.web.domain.Photo;
import com.jhipsterpress.web.domain.Community;
import com.jhipsterpress.web.repository.CalbumRepository;
import com.jhipsterpress.web.service.CalbumService;
import com.jhipsterpress.web.service.dto.CalbumDTO;
import com.jhipsterpress.web.service.mapper.CalbumMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.CalbumCriteria;
import com.jhipsterpress.web.service.CalbumQueryService;

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
 * Test class for the CalbumResource REST controller.
 *
 * @see CalbumResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class CalbumResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private CalbumRepository calbumRepository;

    @Autowired
    private CalbumMapper calbumMapper;

    @Autowired
    private CalbumService calbumService;

    @Autowired
    private CalbumQueryService calbumQueryService;

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

    private MockMvc restCalbumMockMvc;

    private Calbum calbum;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CalbumResource calbumResource = new CalbumResource(calbumService, calbumQueryService);
        this.restCalbumMockMvc = MockMvcBuilders.standaloneSetup(calbumResource)
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
    public static Calbum createEntity(EntityManager em) {
        Calbum calbum = new Calbum()
            .creationDate(DEFAULT_CREATION_DATE)
            .title(DEFAULT_TITLE);
        // Add required entity
        Community community = CommunityResourceIntTest.createEntity(em);
        em.persist(community);
        em.flush();
        calbum.setCommunity(community);
        return calbum;
    }

    @Before
    public void initTest() {
        calbum = createEntity(em);
    }

    @Test
    @Transactional
    public void createCalbum() throws Exception {
        int databaseSizeBeforeCreate = calbumRepository.findAll().size();

        // Create the Calbum
        CalbumDTO calbumDTO = calbumMapper.toDto(calbum);
        restCalbumMockMvc.perform(post("/api/calbums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calbumDTO)))
            .andExpect(status().isCreated());

        // Validate the Calbum in the database
        List<Calbum> calbumList = calbumRepository.findAll();
        assertThat(calbumList).hasSize(databaseSizeBeforeCreate + 1);
        Calbum testCalbum = calbumList.get(calbumList.size() - 1);
        assertThat(testCalbum.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testCalbum.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void createCalbumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = calbumRepository.findAll().size();

        // Create the Calbum with an existing ID
        calbum.setId(1L);
        CalbumDTO calbumDTO = calbumMapper.toDto(calbum);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCalbumMockMvc.perform(post("/api/calbums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calbumDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Calbum in the database
        List<Calbum> calbumList = calbumRepository.findAll();
        assertThat(calbumList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = calbumRepository.findAll().size();
        // set the field null
        calbum.setCreationDate(null);

        // Create the Calbum, which fails.
        CalbumDTO calbumDTO = calbumMapper.toDto(calbum);

        restCalbumMockMvc.perform(post("/api/calbums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calbumDTO)))
            .andExpect(status().isBadRequest());

        List<Calbum> calbumList = calbumRepository.findAll();
        assertThat(calbumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = calbumRepository.findAll().size();
        // set the field null
        calbum.setTitle(null);

        // Create the Calbum, which fails.
        CalbumDTO calbumDTO = calbumMapper.toDto(calbum);

        restCalbumMockMvc.perform(post("/api/calbums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calbumDTO)))
            .andExpect(status().isBadRequest());

        List<Calbum> calbumList = calbumRepository.findAll();
        assertThat(calbumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCalbums() throws Exception {
        // Initialize the database
        calbumRepository.saveAndFlush(calbum);

        // Get all the calbumList
        restCalbumMockMvc.perform(get("/api/calbums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(calbum.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }
    
    @Test
    @Transactional
    public void getCalbum() throws Exception {
        // Initialize the database
        calbumRepository.saveAndFlush(calbum);

        // Get the calbum
        restCalbumMockMvc.perform(get("/api/calbums/{id}", calbum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(calbum.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }

    @Test
    @Transactional
    public void getAllCalbumsByCreationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        calbumRepository.saveAndFlush(calbum);

        // Get all the calbumList where creationDate equals to DEFAULT_CREATION_DATE
        defaultCalbumShouldBeFound("creationDate.equals=" + DEFAULT_CREATION_DATE);

        // Get all the calbumList where creationDate equals to UPDATED_CREATION_DATE
        defaultCalbumShouldNotBeFound("creationDate.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllCalbumsByCreationDateIsInShouldWork() throws Exception {
        // Initialize the database
        calbumRepository.saveAndFlush(calbum);

        // Get all the calbumList where creationDate in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultCalbumShouldBeFound("creationDate.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the calbumList where creationDate equals to UPDATED_CREATION_DATE
        defaultCalbumShouldNotBeFound("creationDate.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllCalbumsByCreationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        calbumRepository.saveAndFlush(calbum);

        // Get all the calbumList where creationDate is not null
        defaultCalbumShouldBeFound("creationDate.specified=true");

        // Get all the calbumList where creationDate is null
        defaultCalbumShouldNotBeFound("creationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCalbumsByTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        calbumRepository.saveAndFlush(calbum);

        // Get all the calbumList where title equals to DEFAULT_TITLE
        defaultCalbumShouldBeFound("title.equals=" + DEFAULT_TITLE);

        // Get all the calbumList where title equals to UPDATED_TITLE
        defaultCalbumShouldNotBeFound("title.equals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllCalbumsByTitleIsInShouldWork() throws Exception {
        // Initialize the database
        calbumRepository.saveAndFlush(calbum);

        // Get all the calbumList where title in DEFAULT_TITLE or UPDATED_TITLE
        defaultCalbumShouldBeFound("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE);

        // Get all the calbumList where title equals to UPDATED_TITLE
        defaultCalbumShouldNotBeFound("title.in=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllCalbumsByTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        calbumRepository.saveAndFlush(calbum);

        // Get all the calbumList where title is not null
        defaultCalbumShouldBeFound("title.specified=true");

        // Get all the calbumList where title is null
        defaultCalbumShouldNotBeFound("title.specified=false");
    }

    @Test
    @Transactional
    public void getAllCalbumsByPhotoIsEqualToSomething() throws Exception {
        // Initialize the database
        Photo photo = PhotoResourceIntTest.createEntity(em);
        em.persist(photo);
        em.flush();
        calbum.addPhoto(photo);
        calbumRepository.saveAndFlush(calbum);
        Long photoId = photo.getId();

        // Get all the calbumList where photo equals to photoId
        defaultCalbumShouldBeFound("photoId.equals=" + photoId);

        // Get all the calbumList where photo equals to photoId + 1
        defaultCalbumShouldNotBeFound("photoId.equals=" + (photoId + 1));
    }


    @Test
    @Transactional
    public void getAllCalbumsByCommunityIsEqualToSomething() throws Exception {
        // Initialize the database
        Community community = CommunityResourceIntTest.createEntity(em);
        em.persist(community);
        em.flush();
        calbum.setCommunity(community);
        calbumRepository.saveAndFlush(calbum);
        Long communityId = community.getId();

        // Get all the calbumList where community equals to communityId
        defaultCalbumShouldBeFound("communityId.equals=" + communityId);

        // Get all the calbumList where community equals to communityId + 1
        defaultCalbumShouldNotBeFound("communityId.equals=" + (communityId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCalbumShouldBeFound(String filter) throws Exception {
        restCalbumMockMvc.perform(get("/api/calbums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(calbum.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)));

        // Check, that the count call also returns 1
        restCalbumMockMvc.perform(get("/api/calbums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCalbumShouldNotBeFound(String filter) throws Exception {
        restCalbumMockMvc.perform(get("/api/calbums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCalbumMockMvc.perform(get("/api/calbums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCalbum() throws Exception {
        // Get the calbum
        restCalbumMockMvc.perform(get("/api/calbums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCalbum() throws Exception {
        // Initialize the database
        calbumRepository.saveAndFlush(calbum);

        int databaseSizeBeforeUpdate = calbumRepository.findAll().size();

        // Update the calbum
        Calbum updatedCalbum = calbumRepository.findById(calbum.getId()).get();
        // Disconnect from session so that the updates on updatedCalbum are not directly saved in db
        em.detach(updatedCalbum);
        updatedCalbum
            .creationDate(UPDATED_CREATION_DATE)
            .title(UPDATED_TITLE);
        CalbumDTO calbumDTO = calbumMapper.toDto(updatedCalbum);

        restCalbumMockMvc.perform(put("/api/calbums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calbumDTO)))
            .andExpect(status().isOk());

        // Validate the Calbum in the database
        List<Calbum> calbumList = calbumRepository.findAll();
        assertThat(calbumList).hasSize(databaseSizeBeforeUpdate);
        Calbum testCalbum = calbumList.get(calbumList.size() - 1);
        assertThat(testCalbum.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testCalbum.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingCalbum() throws Exception {
        int databaseSizeBeforeUpdate = calbumRepository.findAll().size();

        // Create the Calbum
        CalbumDTO calbumDTO = calbumMapper.toDto(calbum);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCalbumMockMvc.perform(put("/api/calbums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calbumDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Calbum in the database
        List<Calbum> calbumList = calbumRepository.findAll();
        assertThat(calbumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCalbum() throws Exception {
        // Initialize the database
        calbumRepository.saveAndFlush(calbum);

        int databaseSizeBeforeDelete = calbumRepository.findAll().size();

        // Delete the calbum
        restCalbumMockMvc.perform(delete("/api/calbums/{id}", calbum.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Calbum> calbumList = calbumRepository.findAll();
        assertThat(calbumList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Calbum.class);
        Calbum calbum1 = new Calbum();
        calbum1.setId(1L);
        Calbum calbum2 = new Calbum();
        calbum2.setId(calbum1.getId());
        assertThat(calbum1).isEqualTo(calbum2);
        calbum2.setId(2L);
        assertThat(calbum1).isNotEqualTo(calbum2);
        calbum1.setId(null);
        assertThat(calbum1).isNotEqualTo(calbum2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CalbumDTO.class);
        CalbumDTO calbumDTO1 = new CalbumDTO();
        calbumDTO1.setId(1L);
        CalbumDTO calbumDTO2 = new CalbumDTO();
        assertThat(calbumDTO1).isNotEqualTo(calbumDTO2);
        calbumDTO2.setId(calbumDTO1.getId());
        assertThat(calbumDTO1).isEqualTo(calbumDTO2);
        calbumDTO2.setId(2L);
        assertThat(calbumDTO1).isNotEqualTo(calbumDTO2);
        calbumDTO1.setId(null);
        assertThat(calbumDTO1).isNotEqualTo(calbumDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(calbumMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(calbumMapper.fromId(null)).isNull();
    }
}
