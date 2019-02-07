package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Vquestion;
import com.jhipsterpress.web.domain.Vanswer;
import com.jhipsterpress.web.domain.Vthumb;
import com.jhipsterpress.web.domain.User;
import com.jhipsterpress.web.domain.Vtopic;
import com.jhipsterpress.web.repository.VquestionRepository;
import com.jhipsterpress.web.service.VquestionService;
import com.jhipsterpress.web.service.dto.VquestionDTO;
import com.jhipsterpress.web.service.mapper.VquestionMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.VquestionCriteria;
import com.jhipsterpress.web.service.VquestionQueryService;

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
 * Test class for the VquestionResource REST controller.
 *
 * @see VquestionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class VquestionResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VQUESTION = "AAAAAAAAAA";
    private static final String UPDATED_VQUESTION = "BBBBBBBBBB";

    private static final String DEFAULT_VQUESTION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_VQUESTION_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private VquestionRepository vquestionRepository;

    @Autowired
    private VquestionMapper vquestionMapper;

    @Autowired
    private VquestionService vquestionService;

    @Autowired
    private VquestionQueryService vquestionQueryService;

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

    private MockMvc restVquestionMockMvc;

    private Vquestion vquestion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VquestionResource vquestionResource = new VquestionResource(vquestionService, vquestionQueryService);
        this.restVquestionMockMvc = MockMvcBuilders.standaloneSetup(vquestionResource)
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
    public static Vquestion createEntity(EntityManager em) {
        Vquestion vquestion = new Vquestion()
            .creationDate(DEFAULT_CREATION_DATE)
            .vquestion(DEFAULT_VQUESTION)
            .vquestionDescription(DEFAULT_VQUESTION_DESCRIPTION);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        vquestion.setUser(user);
        // Add required entity
        Vtopic vtopic = VtopicResourceIntTest.createEntity(em);
        em.persist(vtopic);
        em.flush();
        vquestion.setVtopic(vtopic);
        return vquestion;
    }

    @Before
    public void initTest() {
        vquestion = createEntity(em);
    }

    @Test
    @Transactional
    public void createVquestion() throws Exception {
        int databaseSizeBeforeCreate = vquestionRepository.findAll().size();

        // Create the Vquestion
        VquestionDTO vquestionDTO = vquestionMapper.toDto(vquestion);
        restVquestionMockMvc.perform(post("/api/vquestions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vquestionDTO)))
            .andExpect(status().isCreated());

        // Validate the Vquestion in the database
        List<Vquestion> vquestionList = vquestionRepository.findAll();
        assertThat(vquestionList).hasSize(databaseSizeBeforeCreate + 1);
        Vquestion testVquestion = vquestionList.get(vquestionList.size() - 1);
        assertThat(testVquestion.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testVquestion.getVquestion()).isEqualTo(DEFAULT_VQUESTION);
        assertThat(testVquestion.getVquestionDescription()).isEqualTo(DEFAULT_VQUESTION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createVquestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vquestionRepository.findAll().size();

        // Create the Vquestion with an existing ID
        vquestion.setId(1L);
        VquestionDTO vquestionDTO = vquestionMapper.toDto(vquestion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVquestionMockMvc.perform(post("/api/vquestions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vquestionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vquestion in the database
        List<Vquestion> vquestionList = vquestionRepository.findAll();
        assertThat(vquestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = vquestionRepository.findAll().size();
        // set the field null
        vquestion.setCreationDate(null);

        // Create the Vquestion, which fails.
        VquestionDTO vquestionDTO = vquestionMapper.toDto(vquestion);

        restVquestionMockMvc.perform(post("/api/vquestions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vquestionDTO)))
            .andExpect(status().isBadRequest());

        List<Vquestion> vquestionList = vquestionRepository.findAll();
        assertThat(vquestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVquestionIsRequired() throws Exception {
        int databaseSizeBeforeTest = vquestionRepository.findAll().size();
        // set the field null
        vquestion.setVquestion(null);

        // Create the Vquestion, which fails.
        VquestionDTO vquestionDTO = vquestionMapper.toDto(vquestion);

        restVquestionMockMvc.perform(post("/api/vquestions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vquestionDTO)))
            .andExpect(status().isBadRequest());

        List<Vquestion> vquestionList = vquestionRepository.findAll();
        assertThat(vquestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVquestions() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get all the vquestionList
        restVquestionMockMvc.perform(get("/api/vquestions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vquestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].vquestion").value(hasItem(DEFAULT_VQUESTION.toString())))
            .andExpect(jsonPath("$.[*].vquestionDescription").value(hasItem(DEFAULT_VQUESTION_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getVquestion() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get the vquestion
        restVquestionMockMvc.perform(get("/api/vquestions/{id}", vquestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vquestion.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.vquestion").value(DEFAULT_VQUESTION.toString()))
            .andExpect(jsonPath("$.vquestionDescription").value(DEFAULT_VQUESTION_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllVquestionsByCreationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get all the vquestionList where creationDate equals to DEFAULT_CREATION_DATE
        defaultVquestionShouldBeFound("creationDate.equals=" + DEFAULT_CREATION_DATE);

        // Get all the vquestionList where creationDate equals to UPDATED_CREATION_DATE
        defaultVquestionShouldNotBeFound("creationDate.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllVquestionsByCreationDateIsInShouldWork() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get all the vquestionList where creationDate in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultVquestionShouldBeFound("creationDate.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the vquestionList where creationDate equals to UPDATED_CREATION_DATE
        defaultVquestionShouldNotBeFound("creationDate.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllVquestionsByCreationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get all the vquestionList where creationDate is not null
        defaultVquestionShouldBeFound("creationDate.specified=true");

        // Get all the vquestionList where creationDate is null
        defaultVquestionShouldNotBeFound("creationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVquestionsByVquestionIsEqualToSomething() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get all the vquestionList where vquestion equals to DEFAULT_VQUESTION
        defaultVquestionShouldBeFound("vquestion.equals=" + DEFAULT_VQUESTION);

        // Get all the vquestionList where vquestion equals to UPDATED_VQUESTION
        defaultVquestionShouldNotBeFound("vquestion.equals=" + UPDATED_VQUESTION);
    }

    @Test
    @Transactional
    public void getAllVquestionsByVquestionIsInShouldWork() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get all the vquestionList where vquestion in DEFAULT_VQUESTION or UPDATED_VQUESTION
        defaultVquestionShouldBeFound("vquestion.in=" + DEFAULT_VQUESTION + "," + UPDATED_VQUESTION);

        // Get all the vquestionList where vquestion equals to UPDATED_VQUESTION
        defaultVquestionShouldNotBeFound("vquestion.in=" + UPDATED_VQUESTION);
    }

    @Test
    @Transactional
    public void getAllVquestionsByVquestionIsNullOrNotNull() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get all the vquestionList where vquestion is not null
        defaultVquestionShouldBeFound("vquestion.specified=true");

        // Get all the vquestionList where vquestion is null
        defaultVquestionShouldNotBeFound("vquestion.specified=false");
    }

    @Test
    @Transactional
    public void getAllVquestionsByVquestionDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get all the vquestionList where vquestionDescription equals to DEFAULT_VQUESTION_DESCRIPTION
        defaultVquestionShouldBeFound("vquestionDescription.equals=" + DEFAULT_VQUESTION_DESCRIPTION);

        // Get all the vquestionList where vquestionDescription equals to UPDATED_VQUESTION_DESCRIPTION
        defaultVquestionShouldNotBeFound("vquestionDescription.equals=" + UPDATED_VQUESTION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllVquestionsByVquestionDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get all the vquestionList where vquestionDescription in DEFAULT_VQUESTION_DESCRIPTION or UPDATED_VQUESTION_DESCRIPTION
        defaultVquestionShouldBeFound("vquestionDescription.in=" + DEFAULT_VQUESTION_DESCRIPTION + "," + UPDATED_VQUESTION_DESCRIPTION);

        // Get all the vquestionList where vquestionDescription equals to UPDATED_VQUESTION_DESCRIPTION
        defaultVquestionShouldNotBeFound("vquestionDescription.in=" + UPDATED_VQUESTION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllVquestionsByVquestionDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        // Get all the vquestionList where vquestionDescription is not null
        defaultVquestionShouldBeFound("vquestionDescription.specified=true");

        // Get all the vquestionList where vquestionDescription is null
        defaultVquestionShouldNotBeFound("vquestionDescription.specified=false");
    }

    @Test
    @Transactional
    public void getAllVquestionsByVanswerIsEqualToSomething() throws Exception {
        // Initialize the database
        Vanswer vanswer = VanswerResourceIntTest.createEntity(em);
        em.persist(vanswer);
        em.flush();
        vquestion.addVanswer(vanswer);
        vquestionRepository.saveAndFlush(vquestion);
        Long vanswerId = vanswer.getId();

        // Get all the vquestionList where vanswer equals to vanswerId
        defaultVquestionShouldBeFound("vanswerId.equals=" + vanswerId);

        // Get all the vquestionList where vanswer equals to vanswerId + 1
        defaultVquestionShouldNotBeFound("vanswerId.equals=" + (vanswerId + 1));
    }


    @Test
    @Transactional
    public void getAllVquestionsByVthumbIsEqualToSomething() throws Exception {
        // Initialize the database
        Vthumb vthumb = VthumbResourceIntTest.createEntity(em);
        em.persist(vthumb);
        em.flush();
        vquestion.addVthumb(vthumb);
        vquestionRepository.saveAndFlush(vquestion);
        Long vthumbId = vthumb.getId();

        // Get all the vquestionList where vthumb equals to vthumbId
        defaultVquestionShouldBeFound("vthumbId.equals=" + vthumbId);

        // Get all the vquestionList where vthumb equals to vthumbId + 1
        defaultVquestionShouldNotBeFound("vthumbId.equals=" + (vthumbId + 1));
    }


    @Test
    @Transactional
    public void getAllVquestionsByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        vquestion.setUser(user);
        vquestionRepository.saveAndFlush(vquestion);
        Long userId = user.getId();

        // Get all the vquestionList where user equals to userId
        defaultVquestionShouldBeFound("userId.equals=" + userId);

        // Get all the vquestionList where user equals to userId + 1
        defaultVquestionShouldNotBeFound("userId.equals=" + (userId + 1));
    }


    @Test
    @Transactional
    public void getAllVquestionsByVtopicIsEqualToSomething() throws Exception {
        // Initialize the database
        Vtopic vtopic = VtopicResourceIntTest.createEntity(em);
        em.persist(vtopic);
        em.flush();
        vquestion.setVtopic(vtopic);
        vquestionRepository.saveAndFlush(vquestion);
        Long vtopicId = vtopic.getId();

        // Get all the vquestionList where vtopic equals to vtopicId
        defaultVquestionShouldBeFound("vtopicId.equals=" + vtopicId);

        // Get all the vquestionList where vtopic equals to vtopicId + 1
        defaultVquestionShouldNotBeFound("vtopicId.equals=" + (vtopicId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultVquestionShouldBeFound(String filter) throws Exception {
        restVquestionMockMvc.perform(get("/api/vquestions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vquestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].vquestion").value(hasItem(DEFAULT_VQUESTION)))
            .andExpect(jsonPath("$.[*].vquestionDescription").value(hasItem(DEFAULT_VQUESTION_DESCRIPTION)));

        // Check, that the count call also returns 1
        restVquestionMockMvc.perform(get("/api/vquestions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultVquestionShouldNotBeFound(String filter) throws Exception {
        restVquestionMockMvc.perform(get("/api/vquestions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restVquestionMockMvc.perform(get("/api/vquestions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingVquestion() throws Exception {
        // Get the vquestion
        restVquestionMockMvc.perform(get("/api/vquestions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVquestion() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        int databaseSizeBeforeUpdate = vquestionRepository.findAll().size();

        // Update the vquestion
        Vquestion updatedVquestion = vquestionRepository.findById(vquestion.getId()).get();
        // Disconnect from session so that the updates on updatedVquestion are not directly saved in db
        em.detach(updatedVquestion);
        updatedVquestion
            .creationDate(UPDATED_CREATION_DATE)
            .vquestion(UPDATED_VQUESTION)
            .vquestionDescription(UPDATED_VQUESTION_DESCRIPTION);
        VquestionDTO vquestionDTO = vquestionMapper.toDto(updatedVquestion);

        restVquestionMockMvc.perform(put("/api/vquestions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vquestionDTO)))
            .andExpect(status().isOk());

        // Validate the Vquestion in the database
        List<Vquestion> vquestionList = vquestionRepository.findAll();
        assertThat(vquestionList).hasSize(databaseSizeBeforeUpdate);
        Vquestion testVquestion = vquestionList.get(vquestionList.size() - 1);
        assertThat(testVquestion.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testVquestion.getVquestion()).isEqualTo(UPDATED_VQUESTION);
        assertThat(testVquestion.getVquestionDescription()).isEqualTo(UPDATED_VQUESTION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingVquestion() throws Exception {
        int databaseSizeBeforeUpdate = vquestionRepository.findAll().size();

        // Create the Vquestion
        VquestionDTO vquestionDTO = vquestionMapper.toDto(vquestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVquestionMockMvc.perform(put("/api/vquestions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vquestionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vquestion in the database
        List<Vquestion> vquestionList = vquestionRepository.findAll();
        assertThat(vquestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVquestion() throws Exception {
        // Initialize the database
        vquestionRepository.saveAndFlush(vquestion);

        int databaseSizeBeforeDelete = vquestionRepository.findAll().size();

        // Delete the vquestion
        restVquestionMockMvc.perform(delete("/api/vquestions/{id}", vquestion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vquestion> vquestionList = vquestionRepository.findAll();
        assertThat(vquestionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vquestion.class);
        Vquestion vquestion1 = new Vquestion();
        vquestion1.setId(1L);
        Vquestion vquestion2 = new Vquestion();
        vquestion2.setId(vquestion1.getId());
        assertThat(vquestion1).isEqualTo(vquestion2);
        vquestion2.setId(2L);
        assertThat(vquestion1).isNotEqualTo(vquestion2);
        vquestion1.setId(null);
        assertThat(vquestion1).isNotEqualTo(vquestion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VquestionDTO.class);
        VquestionDTO vquestionDTO1 = new VquestionDTO();
        vquestionDTO1.setId(1L);
        VquestionDTO vquestionDTO2 = new VquestionDTO();
        assertThat(vquestionDTO1).isNotEqualTo(vquestionDTO2);
        vquestionDTO2.setId(vquestionDTO1.getId());
        assertThat(vquestionDTO1).isEqualTo(vquestionDTO2);
        vquestionDTO2.setId(2L);
        assertThat(vquestionDTO1).isNotEqualTo(vquestionDTO2);
        vquestionDTO1.setId(null);
        assertThat(vquestionDTO1).isNotEqualTo(vquestionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vquestionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vquestionMapper.fromId(null)).isNull();
    }
}
