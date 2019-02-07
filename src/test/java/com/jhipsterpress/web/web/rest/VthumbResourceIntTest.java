package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Vthumb;
import com.jhipsterpress.web.domain.User;
import com.jhipsterpress.web.domain.Vquestion;
import com.jhipsterpress.web.domain.Vanswer;
import com.jhipsterpress.web.repository.VthumbRepository;
import com.jhipsterpress.web.service.VthumbService;
import com.jhipsterpress.web.service.dto.VthumbDTO;
import com.jhipsterpress.web.service.mapper.VthumbMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.VthumbCriteria;
import com.jhipsterpress.web.service.VthumbQueryService;

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
 * Test class for the VthumbResource REST controller.
 *
 * @see VthumbResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class VthumbResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_VTHUMB_UP = false;
    private static final Boolean UPDATED_VTHUMB_UP = true;

    private static final Boolean DEFAULT_VTHUMB_DOWN = false;
    private static final Boolean UPDATED_VTHUMB_DOWN = true;

    @Autowired
    private VthumbRepository vthumbRepository;

    @Autowired
    private VthumbMapper vthumbMapper;

    @Autowired
    private VthumbService vthumbService;

    @Autowired
    private VthumbQueryService vthumbQueryService;

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

    private MockMvc restVthumbMockMvc;

    private Vthumb vthumb;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VthumbResource vthumbResource = new VthumbResource(vthumbService, vthumbQueryService);
        this.restVthumbMockMvc = MockMvcBuilders.standaloneSetup(vthumbResource)
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
    public static Vthumb createEntity(EntityManager em) {
        Vthumb vthumb = new Vthumb()
            .creationDate(DEFAULT_CREATION_DATE)
            .vthumbUp(DEFAULT_VTHUMB_UP)
            .vthumbDown(DEFAULT_VTHUMB_DOWN);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        vthumb.setUser(user);
        return vthumb;
    }

    @Before
    public void initTest() {
        vthumb = createEntity(em);
    }

    @Test
    @Transactional
    public void createVthumb() throws Exception {
        int databaseSizeBeforeCreate = vthumbRepository.findAll().size();

        // Create the Vthumb
        VthumbDTO vthumbDTO = vthumbMapper.toDto(vthumb);
        restVthumbMockMvc.perform(post("/api/vthumbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vthumbDTO)))
            .andExpect(status().isCreated());

        // Validate the Vthumb in the database
        List<Vthumb> vthumbList = vthumbRepository.findAll();
        assertThat(vthumbList).hasSize(databaseSizeBeforeCreate + 1);
        Vthumb testVthumb = vthumbList.get(vthumbList.size() - 1);
        assertThat(testVthumb.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testVthumb.isVthumbUp()).isEqualTo(DEFAULT_VTHUMB_UP);
        assertThat(testVthumb.isVthumbDown()).isEqualTo(DEFAULT_VTHUMB_DOWN);
    }

    @Test
    @Transactional
    public void createVthumbWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vthumbRepository.findAll().size();

        // Create the Vthumb with an existing ID
        vthumb.setId(1L);
        VthumbDTO vthumbDTO = vthumbMapper.toDto(vthumb);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVthumbMockMvc.perform(post("/api/vthumbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vthumbDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vthumb in the database
        List<Vthumb> vthumbList = vthumbRepository.findAll();
        assertThat(vthumbList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = vthumbRepository.findAll().size();
        // set the field null
        vthumb.setCreationDate(null);

        // Create the Vthumb, which fails.
        VthumbDTO vthumbDTO = vthumbMapper.toDto(vthumb);

        restVthumbMockMvc.perform(post("/api/vthumbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vthumbDTO)))
            .andExpect(status().isBadRequest());

        List<Vthumb> vthumbList = vthumbRepository.findAll();
        assertThat(vthumbList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVthumbs() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get all the vthumbList
        restVthumbMockMvc.perform(get("/api/vthumbs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vthumb.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].vthumbUp").value(hasItem(DEFAULT_VTHUMB_UP.booleanValue())))
            .andExpect(jsonPath("$.[*].vthumbDown").value(hasItem(DEFAULT_VTHUMB_DOWN.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getVthumb() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get the vthumb
        restVthumbMockMvc.perform(get("/api/vthumbs/{id}", vthumb.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vthumb.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.vthumbUp").value(DEFAULT_VTHUMB_UP.booleanValue()))
            .andExpect(jsonPath("$.vthumbDown").value(DEFAULT_VTHUMB_DOWN.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllVthumbsByCreationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get all the vthumbList where creationDate equals to DEFAULT_CREATION_DATE
        defaultVthumbShouldBeFound("creationDate.equals=" + DEFAULT_CREATION_DATE);

        // Get all the vthumbList where creationDate equals to UPDATED_CREATION_DATE
        defaultVthumbShouldNotBeFound("creationDate.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllVthumbsByCreationDateIsInShouldWork() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get all the vthumbList where creationDate in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultVthumbShouldBeFound("creationDate.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the vthumbList where creationDate equals to UPDATED_CREATION_DATE
        defaultVthumbShouldNotBeFound("creationDate.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllVthumbsByCreationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get all the vthumbList where creationDate is not null
        defaultVthumbShouldBeFound("creationDate.specified=true");

        // Get all the vthumbList where creationDate is null
        defaultVthumbShouldNotBeFound("creationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVthumbsByVthumbUpIsEqualToSomething() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get all the vthumbList where vthumbUp equals to DEFAULT_VTHUMB_UP
        defaultVthumbShouldBeFound("vthumbUp.equals=" + DEFAULT_VTHUMB_UP);

        // Get all the vthumbList where vthumbUp equals to UPDATED_VTHUMB_UP
        defaultVthumbShouldNotBeFound("vthumbUp.equals=" + UPDATED_VTHUMB_UP);
    }

    @Test
    @Transactional
    public void getAllVthumbsByVthumbUpIsInShouldWork() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get all the vthumbList where vthumbUp in DEFAULT_VTHUMB_UP or UPDATED_VTHUMB_UP
        defaultVthumbShouldBeFound("vthumbUp.in=" + DEFAULT_VTHUMB_UP + "," + UPDATED_VTHUMB_UP);

        // Get all the vthumbList where vthumbUp equals to UPDATED_VTHUMB_UP
        defaultVthumbShouldNotBeFound("vthumbUp.in=" + UPDATED_VTHUMB_UP);
    }

    @Test
    @Transactional
    public void getAllVthumbsByVthumbUpIsNullOrNotNull() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get all the vthumbList where vthumbUp is not null
        defaultVthumbShouldBeFound("vthumbUp.specified=true");

        // Get all the vthumbList where vthumbUp is null
        defaultVthumbShouldNotBeFound("vthumbUp.specified=false");
    }

    @Test
    @Transactional
    public void getAllVthumbsByVthumbDownIsEqualToSomething() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get all the vthumbList where vthumbDown equals to DEFAULT_VTHUMB_DOWN
        defaultVthumbShouldBeFound("vthumbDown.equals=" + DEFAULT_VTHUMB_DOWN);

        // Get all the vthumbList where vthumbDown equals to UPDATED_VTHUMB_DOWN
        defaultVthumbShouldNotBeFound("vthumbDown.equals=" + UPDATED_VTHUMB_DOWN);
    }

    @Test
    @Transactional
    public void getAllVthumbsByVthumbDownIsInShouldWork() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get all the vthumbList where vthumbDown in DEFAULT_VTHUMB_DOWN or UPDATED_VTHUMB_DOWN
        defaultVthumbShouldBeFound("vthumbDown.in=" + DEFAULT_VTHUMB_DOWN + "," + UPDATED_VTHUMB_DOWN);

        // Get all the vthumbList where vthumbDown equals to UPDATED_VTHUMB_DOWN
        defaultVthumbShouldNotBeFound("vthumbDown.in=" + UPDATED_VTHUMB_DOWN);
    }

    @Test
    @Transactional
    public void getAllVthumbsByVthumbDownIsNullOrNotNull() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        // Get all the vthumbList where vthumbDown is not null
        defaultVthumbShouldBeFound("vthumbDown.specified=true");

        // Get all the vthumbList where vthumbDown is null
        defaultVthumbShouldNotBeFound("vthumbDown.specified=false");
    }

    @Test
    @Transactional
    public void getAllVthumbsByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        vthumb.setUser(user);
        vthumbRepository.saveAndFlush(vthumb);
        Long userId = user.getId();

        // Get all the vthumbList where user equals to userId
        defaultVthumbShouldBeFound("userId.equals=" + userId);

        // Get all the vthumbList where user equals to userId + 1
        defaultVthumbShouldNotBeFound("userId.equals=" + (userId + 1));
    }


    @Test
    @Transactional
    public void getAllVthumbsByVquestionIsEqualToSomething() throws Exception {
        // Initialize the database
        Vquestion vquestion = VquestionResourceIntTest.createEntity(em);
        em.persist(vquestion);
        em.flush();
        vthumb.setVquestion(vquestion);
        vthumbRepository.saveAndFlush(vthumb);
        Long vquestionId = vquestion.getId();

        // Get all the vthumbList where vquestion equals to vquestionId
        defaultVthumbShouldBeFound("vquestionId.equals=" + vquestionId);

        // Get all the vthumbList where vquestion equals to vquestionId + 1
        defaultVthumbShouldNotBeFound("vquestionId.equals=" + (vquestionId + 1));
    }


    @Test
    @Transactional
    public void getAllVthumbsByVanswerIsEqualToSomething() throws Exception {
        // Initialize the database
        Vanswer vanswer = VanswerResourceIntTest.createEntity(em);
        em.persist(vanswer);
        em.flush();
        vthumb.setVanswer(vanswer);
        vthumbRepository.saveAndFlush(vthumb);
        Long vanswerId = vanswer.getId();

        // Get all the vthumbList where vanswer equals to vanswerId
        defaultVthumbShouldBeFound("vanswerId.equals=" + vanswerId);

        // Get all the vthumbList where vanswer equals to vanswerId + 1
        defaultVthumbShouldNotBeFound("vanswerId.equals=" + (vanswerId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultVthumbShouldBeFound(String filter) throws Exception {
        restVthumbMockMvc.perform(get("/api/vthumbs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vthumb.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].vthumbUp").value(hasItem(DEFAULT_VTHUMB_UP.booleanValue())))
            .andExpect(jsonPath("$.[*].vthumbDown").value(hasItem(DEFAULT_VTHUMB_DOWN.booleanValue())));

        // Check, that the count call also returns 1
        restVthumbMockMvc.perform(get("/api/vthumbs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultVthumbShouldNotBeFound(String filter) throws Exception {
        restVthumbMockMvc.perform(get("/api/vthumbs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restVthumbMockMvc.perform(get("/api/vthumbs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingVthumb() throws Exception {
        // Get the vthumb
        restVthumbMockMvc.perform(get("/api/vthumbs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVthumb() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        int databaseSizeBeforeUpdate = vthumbRepository.findAll().size();

        // Update the vthumb
        Vthumb updatedVthumb = vthumbRepository.findById(vthumb.getId()).get();
        // Disconnect from session so that the updates on updatedVthumb are not directly saved in db
        em.detach(updatedVthumb);
        updatedVthumb
            .creationDate(UPDATED_CREATION_DATE)
            .vthumbUp(UPDATED_VTHUMB_UP)
            .vthumbDown(UPDATED_VTHUMB_DOWN);
        VthumbDTO vthumbDTO = vthumbMapper.toDto(updatedVthumb);

        restVthumbMockMvc.perform(put("/api/vthumbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vthumbDTO)))
            .andExpect(status().isOk());

        // Validate the Vthumb in the database
        List<Vthumb> vthumbList = vthumbRepository.findAll();
        assertThat(vthumbList).hasSize(databaseSizeBeforeUpdate);
        Vthumb testVthumb = vthumbList.get(vthumbList.size() - 1);
        assertThat(testVthumb.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testVthumb.isVthumbUp()).isEqualTo(UPDATED_VTHUMB_UP);
        assertThat(testVthumb.isVthumbDown()).isEqualTo(UPDATED_VTHUMB_DOWN);
    }

    @Test
    @Transactional
    public void updateNonExistingVthumb() throws Exception {
        int databaseSizeBeforeUpdate = vthumbRepository.findAll().size();

        // Create the Vthumb
        VthumbDTO vthumbDTO = vthumbMapper.toDto(vthumb);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVthumbMockMvc.perform(put("/api/vthumbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vthumbDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vthumb in the database
        List<Vthumb> vthumbList = vthumbRepository.findAll();
        assertThat(vthumbList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVthumb() throws Exception {
        // Initialize the database
        vthumbRepository.saveAndFlush(vthumb);

        int databaseSizeBeforeDelete = vthumbRepository.findAll().size();

        // Delete the vthumb
        restVthumbMockMvc.perform(delete("/api/vthumbs/{id}", vthumb.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vthumb> vthumbList = vthumbRepository.findAll();
        assertThat(vthumbList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vthumb.class);
        Vthumb vthumb1 = new Vthumb();
        vthumb1.setId(1L);
        Vthumb vthumb2 = new Vthumb();
        vthumb2.setId(vthumb1.getId());
        assertThat(vthumb1).isEqualTo(vthumb2);
        vthumb2.setId(2L);
        assertThat(vthumb1).isNotEqualTo(vthumb2);
        vthumb1.setId(null);
        assertThat(vthumb1).isNotEqualTo(vthumb2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VthumbDTO.class);
        VthumbDTO vthumbDTO1 = new VthumbDTO();
        vthumbDTO1.setId(1L);
        VthumbDTO vthumbDTO2 = new VthumbDTO();
        assertThat(vthumbDTO1).isNotEqualTo(vthumbDTO2);
        vthumbDTO2.setId(vthumbDTO1.getId());
        assertThat(vthumbDTO1).isEqualTo(vthumbDTO2);
        vthumbDTO2.setId(2L);
        assertThat(vthumbDTO1).isNotEqualTo(vthumbDTO2);
        vthumbDTO1.setId(null);
        assertThat(vthumbDTO1).isNotEqualTo(vthumbDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vthumbMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vthumbMapper.fromId(null)).isNull();
    }
}
