package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Uprofile;
import com.jhipsterpress.web.domain.User;
import com.jhipsterpress.web.domain.Interest;
import com.jhipsterpress.web.domain.Activity;
import com.jhipsterpress.web.domain.Celeb;
import com.jhipsterpress.web.repository.UprofileRepository;
import com.jhipsterpress.web.service.UprofileService;
import com.jhipsterpress.web.service.dto.UprofileDTO;
import com.jhipsterpress.web.service.mapper.UprofileMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.UprofileCriteria;
import com.jhipsterpress.web.service.UprofileQueryService;

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
import org.springframework.util.Base64Utils;
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

import com.jhipsterpress.web.domain.enumeration.Gender;
import com.jhipsterpress.web.domain.enumeration.CivilStatus;
import com.jhipsterpress.web.domain.enumeration.Gender;
import com.jhipsterpress.web.domain.enumeration.Purpose;
import com.jhipsterpress.web.domain.enumeration.Physical;
import com.jhipsterpress.web.domain.enumeration.Religion;
import com.jhipsterpress.web.domain.enumeration.EthnicGroup;
import com.jhipsterpress.web.domain.enumeration.Studies;
import com.jhipsterpress.web.domain.enumeration.Eyes;
import com.jhipsterpress.web.domain.enumeration.Smoker;
import com.jhipsterpress.web.domain.enumeration.Children;
import com.jhipsterpress.web.domain.enumeration.FutureChildren;
/**
 * Test class for the UprofileResource REST controller.
 *
 * @see UprofileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class UprofileResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_BIO = "AAAAAAAAAA";
    private static final String UPDATED_BIO = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_LINKEDIN = "BBBBBBBBBB";

    private static final String DEFAULT_INSTAGRAM = "AAAAAAAAAA";
    private static final String UPDATED_INSTAGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_GOOGLE_PLUS = "AAAAAAAAAA";
    private static final String UPDATED_GOOGLE_PLUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_BIRTHDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BIRTHDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final CivilStatus DEFAULT_CIVIL_STATUS = CivilStatus.NA;
    private static final CivilStatus UPDATED_CIVIL_STATUS = CivilStatus.SINGLE;

    private static final Gender DEFAULT_LOOKING_FOR = Gender.MALE;
    private static final Gender UPDATED_LOOKING_FOR = Gender.FEMALE;

    private static final Purpose DEFAULT_PURPOSE = Purpose.NOT_INTERESTED;
    private static final Purpose UPDATED_PURPOSE = Purpose.FRIENDSHIP;

    private static final Physical DEFAULT_PHYSICAL = Physical.NA;
    private static final Physical UPDATED_PHYSICAL = Physical.THIN;

    private static final Religion DEFAULT_RELIGION = Religion.NA;
    private static final Religion UPDATED_RELIGION = Religion.ATHEIST;

    private static final EthnicGroup DEFAULT_ETHNIC_GROUP = EthnicGroup.NA;
    private static final EthnicGroup UPDATED_ETHNIC_GROUP = EthnicGroup.MIXED;

    private static final Studies DEFAULT_STUDIES = Studies.NA;
    private static final Studies UPDATED_STUDIES = Studies.PRIMARY;

    private static final Integer DEFAULT_SIBBLINGS = -1;
    private static final Integer UPDATED_SIBBLINGS = 0;

    private static final Eyes DEFAULT_EYES = Eyes.NA;
    private static final Eyes UPDATED_EYES = Eyes.BLUE;

    private static final Smoker DEFAULT_SMOKER = Smoker.NA;
    private static final Smoker UPDATED_SMOKER = Smoker.YES;

    private static final Children DEFAULT_CHILDREN = Children.NA;
    private static final Children UPDATED_CHILDREN = Children.YES;

    private static final FutureChildren DEFAULT_FUTURE_CHILDREN = FutureChildren.NA;
    private static final FutureChildren UPDATED_FUTURE_CHILDREN = FutureChildren.YES;

    private static final Boolean DEFAULT_PET = false;
    private static final Boolean UPDATED_PET = true;

    @Autowired
    private UprofileRepository uprofileRepository;

    @Autowired
    private UprofileMapper uprofileMapper;

    @Autowired
    private UprofileService uprofileService;

    @Autowired
    private UprofileQueryService uprofileQueryService;

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

    private MockMvc restUprofileMockMvc;

    private Uprofile uprofile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UprofileResource uprofileResource = new UprofileResource(uprofileService, uprofileQueryService);
        this.restUprofileMockMvc = MockMvcBuilders.standaloneSetup(uprofileResource)
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
    public static Uprofile createEntity(EntityManager em) {
        Uprofile uprofile = new Uprofile()
            .creationDate(DEFAULT_CREATION_DATE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .gender(DEFAULT_GENDER)
            .phone(DEFAULT_PHONE)
            .bio(DEFAULT_BIO)
            .facebook(DEFAULT_FACEBOOK)
            .twitter(DEFAULT_TWITTER)
            .linkedin(DEFAULT_LINKEDIN)
            .instagram(DEFAULT_INSTAGRAM)
            .googlePlus(DEFAULT_GOOGLE_PLUS)
            .birthdate(DEFAULT_BIRTHDATE)
            .civilStatus(DEFAULT_CIVIL_STATUS)
            .lookingFor(DEFAULT_LOOKING_FOR)
            .purpose(DEFAULT_PURPOSE)
            .physical(DEFAULT_PHYSICAL)
            .religion(DEFAULT_RELIGION)
            .ethnicGroup(DEFAULT_ETHNIC_GROUP)
            .studies(DEFAULT_STUDIES)
            .sibblings(DEFAULT_SIBBLINGS)
            .eyes(DEFAULT_EYES)
            .smoker(DEFAULT_SMOKER)
            .children(DEFAULT_CHILDREN)
            .futureChildren(DEFAULT_FUTURE_CHILDREN)
            .pet(DEFAULT_PET);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        uprofile.setUser(user);
        return uprofile;
    }

    @Before
    public void initTest() {
        uprofile = createEntity(em);
    }

    @Test
    @Transactional
    public void createUprofile() throws Exception {
        int databaseSizeBeforeCreate = uprofileRepository.findAll().size();

        // Create the Uprofile
        UprofileDTO uprofileDTO = uprofileMapper.toDto(uprofile);
        restUprofileMockMvc.perform(post("/api/uprofiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uprofileDTO)))
            .andExpect(status().isCreated());

        // Validate the Uprofile in the database
        List<Uprofile> uprofileList = uprofileRepository.findAll();
        assertThat(uprofileList).hasSize(databaseSizeBeforeCreate + 1);
        Uprofile testUprofile = uprofileList.get(uprofileList.size() - 1);
        assertThat(testUprofile.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testUprofile.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testUprofile.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testUprofile.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testUprofile.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUprofile.getBio()).isEqualTo(DEFAULT_BIO);
        assertThat(testUprofile.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testUprofile.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testUprofile.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testUprofile.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testUprofile.getGooglePlus()).isEqualTo(DEFAULT_GOOGLE_PLUS);
        assertThat(testUprofile.getBirthdate()).isEqualTo(DEFAULT_BIRTHDATE);
        assertThat(testUprofile.getCivilStatus()).isEqualTo(DEFAULT_CIVIL_STATUS);
        assertThat(testUprofile.getLookingFor()).isEqualTo(DEFAULT_LOOKING_FOR);
        assertThat(testUprofile.getPurpose()).isEqualTo(DEFAULT_PURPOSE);
        assertThat(testUprofile.getPhysical()).isEqualTo(DEFAULT_PHYSICAL);
        assertThat(testUprofile.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testUprofile.getEthnicGroup()).isEqualTo(DEFAULT_ETHNIC_GROUP);
        assertThat(testUprofile.getStudies()).isEqualTo(DEFAULT_STUDIES);
        assertThat(testUprofile.getSibblings()).isEqualTo(DEFAULT_SIBBLINGS);
        assertThat(testUprofile.getEyes()).isEqualTo(DEFAULT_EYES);
        assertThat(testUprofile.getSmoker()).isEqualTo(DEFAULT_SMOKER);
        assertThat(testUprofile.getChildren()).isEqualTo(DEFAULT_CHILDREN);
        assertThat(testUprofile.getFutureChildren()).isEqualTo(DEFAULT_FUTURE_CHILDREN);
        assertThat(testUprofile.isPet()).isEqualTo(DEFAULT_PET);
    }

    @Test
    @Transactional
    public void createUprofileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uprofileRepository.findAll().size();

        // Create the Uprofile with an existing ID
        uprofile.setId(1L);
        UprofileDTO uprofileDTO = uprofileMapper.toDto(uprofile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUprofileMockMvc.perform(post("/api/uprofiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uprofileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Uprofile in the database
        List<Uprofile> uprofileList = uprofileRepository.findAll();
        assertThat(uprofileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = uprofileRepository.findAll().size();
        // set the field null
        uprofile.setCreationDate(null);

        // Create the Uprofile, which fails.
        UprofileDTO uprofileDTO = uprofileMapper.toDto(uprofile);

        restUprofileMockMvc.perform(post("/api/uprofiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uprofileDTO)))
            .andExpect(status().isBadRequest());

        List<Uprofile> uprofileList = uprofileRepository.findAll();
        assertThat(uprofileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUprofiles() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList
        restUprofileMockMvc.perform(get("/api/uprofiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uprofile.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO.toString())))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK.toString())))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER.toString())))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN.toString())))
            .andExpect(jsonPath("$.[*].instagram").value(hasItem(DEFAULT_INSTAGRAM.toString())))
            .andExpect(jsonPath("$.[*].googlePlus").value(hasItem(DEFAULT_GOOGLE_PLUS.toString())))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())))
            .andExpect(jsonPath("$.[*].civilStatus").value(hasItem(DEFAULT_CIVIL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].lookingFor").value(hasItem(DEFAULT_LOOKING_FOR.toString())))
            .andExpect(jsonPath("$.[*].purpose").value(hasItem(DEFAULT_PURPOSE.toString())))
            .andExpect(jsonPath("$.[*].physical").value(hasItem(DEFAULT_PHYSICAL.toString())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.toString())))
            .andExpect(jsonPath("$.[*].ethnicGroup").value(hasItem(DEFAULT_ETHNIC_GROUP.toString())))
            .andExpect(jsonPath("$.[*].studies").value(hasItem(DEFAULT_STUDIES.toString())))
            .andExpect(jsonPath("$.[*].sibblings").value(hasItem(DEFAULT_SIBBLINGS)))
            .andExpect(jsonPath("$.[*].eyes").value(hasItem(DEFAULT_EYES.toString())))
            .andExpect(jsonPath("$.[*].smoker").value(hasItem(DEFAULT_SMOKER.toString())))
            .andExpect(jsonPath("$.[*].children").value(hasItem(DEFAULT_CHILDREN.toString())))
            .andExpect(jsonPath("$.[*].futureChildren").value(hasItem(DEFAULT_FUTURE_CHILDREN.toString())))
            .andExpect(jsonPath("$.[*].pet").value(hasItem(DEFAULT_PET.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getUprofile() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get the uprofile
        restUprofileMockMvc.perform(get("/api/uprofiles/{id}", uprofile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uprofile.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.bio").value(DEFAULT_BIO.toString()))
            .andExpect(jsonPath("$.facebook").value(DEFAULT_FACEBOOK.toString()))
            .andExpect(jsonPath("$.twitter").value(DEFAULT_TWITTER.toString()))
            .andExpect(jsonPath("$.linkedin").value(DEFAULT_LINKEDIN.toString()))
            .andExpect(jsonPath("$.instagram").value(DEFAULT_INSTAGRAM.toString()))
            .andExpect(jsonPath("$.googlePlus").value(DEFAULT_GOOGLE_PLUS.toString()))
            .andExpect(jsonPath("$.birthdate").value(DEFAULT_BIRTHDATE.toString()))
            .andExpect(jsonPath("$.civilStatus").value(DEFAULT_CIVIL_STATUS.toString()))
            .andExpect(jsonPath("$.lookingFor").value(DEFAULT_LOOKING_FOR.toString()))
            .andExpect(jsonPath("$.purpose").value(DEFAULT_PURPOSE.toString()))
            .andExpect(jsonPath("$.physical").value(DEFAULT_PHYSICAL.toString()))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION.toString()))
            .andExpect(jsonPath("$.ethnicGroup").value(DEFAULT_ETHNIC_GROUP.toString()))
            .andExpect(jsonPath("$.studies").value(DEFAULT_STUDIES.toString()))
            .andExpect(jsonPath("$.sibblings").value(DEFAULT_SIBBLINGS))
            .andExpect(jsonPath("$.eyes").value(DEFAULT_EYES.toString()))
            .andExpect(jsonPath("$.smoker").value(DEFAULT_SMOKER.toString()))
            .andExpect(jsonPath("$.children").value(DEFAULT_CHILDREN.toString()))
            .andExpect(jsonPath("$.futureChildren").value(DEFAULT_FUTURE_CHILDREN.toString()))
            .andExpect(jsonPath("$.pet").value(DEFAULT_PET.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllUprofilesByCreationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where creationDate equals to DEFAULT_CREATION_DATE
        defaultUprofileShouldBeFound("creationDate.equals=" + DEFAULT_CREATION_DATE);

        // Get all the uprofileList where creationDate equals to UPDATED_CREATION_DATE
        defaultUprofileShouldNotBeFound("creationDate.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllUprofilesByCreationDateIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where creationDate in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultUprofileShouldBeFound("creationDate.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the uprofileList where creationDate equals to UPDATED_CREATION_DATE
        defaultUprofileShouldNotBeFound("creationDate.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllUprofilesByCreationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where creationDate is not null
        defaultUprofileShouldBeFound("creationDate.specified=true");

        // Get all the uprofileList where creationDate is null
        defaultUprofileShouldNotBeFound("creationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByGenderIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where gender equals to DEFAULT_GENDER
        defaultUprofileShouldBeFound("gender.equals=" + DEFAULT_GENDER);

        // Get all the uprofileList where gender equals to UPDATED_GENDER
        defaultUprofileShouldNotBeFound("gender.equals=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    public void getAllUprofilesByGenderIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where gender in DEFAULT_GENDER or UPDATED_GENDER
        defaultUprofileShouldBeFound("gender.in=" + DEFAULT_GENDER + "," + UPDATED_GENDER);

        // Get all the uprofileList where gender equals to UPDATED_GENDER
        defaultUprofileShouldNotBeFound("gender.in=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    public void getAllUprofilesByGenderIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where gender is not null
        defaultUprofileShouldBeFound("gender.specified=true");

        // Get all the uprofileList where gender is null
        defaultUprofileShouldNotBeFound("gender.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where phone equals to DEFAULT_PHONE
        defaultUprofileShouldBeFound("phone.equals=" + DEFAULT_PHONE);

        // Get all the uprofileList where phone equals to UPDATED_PHONE
        defaultUprofileShouldNotBeFound("phone.equals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllUprofilesByPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where phone in DEFAULT_PHONE or UPDATED_PHONE
        defaultUprofileShouldBeFound("phone.in=" + DEFAULT_PHONE + "," + UPDATED_PHONE);

        // Get all the uprofileList where phone equals to UPDATED_PHONE
        defaultUprofileShouldNotBeFound("phone.in=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllUprofilesByPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where phone is not null
        defaultUprofileShouldBeFound("phone.specified=true");

        // Get all the uprofileList where phone is null
        defaultUprofileShouldNotBeFound("phone.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByBioIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where bio equals to DEFAULT_BIO
        defaultUprofileShouldBeFound("bio.equals=" + DEFAULT_BIO);

        // Get all the uprofileList where bio equals to UPDATED_BIO
        defaultUprofileShouldNotBeFound("bio.equals=" + UPDATED_BIO);
    }

    @Test
    @Transactional
    public void getAllUprofilesByBioIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where bio in DEFAULT_BIO or UPDATED_BIO
        defaultUprofileShouldBeFound("bio.in=" + DEFAULT_BIO + "," + UPDATED_BIO);

        // Get all the uprofileList where bio equals to UPDATED_BIO
        defaultUprofileShouldNotBeFound("bio.in=" + UPDATED_BIO);
    }

    @Test
    @Transactional
    public void getAllUprofilesByBioIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where bio is not null
        defaultUprofileShouldBeFound("bio.specified=true");

        // Get all the uprofileList where bio is null
        defaultUprofileShouldNotBeFound("bio.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByFacebookIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where facebook equals to DEFAULT_FACEBOOK
        defaultUprofileShouldBeFound("facebook.equals=" + DEFAULT_FACEBOOK);

        // Get all the uprofileList where facebook equals to UPDATED_FACEBOOK
        defaultUprofileShouldNotBeFound("facebook.equals=" + UPDATED_FACEBOOK);
    }

    @Test
    @Transactional
    public void getAllUprofilesByFacebookIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where facebook in DEFAULT_FACEBOOK or UPDATED_FACEBOOK
        defaultUprofileShouldBeFound("facebook.in=" + DEFAULT_FACEBOOK + "," + UPDATED_FACEBOOK);

        // Get all the uprofileList where facebook equals to UPDATED_FACEBOOK
        defaultUprofileShouldNotBeFound("facebook.in=" + UPDATED_FACEBOOK);
    }

    @Test
    @Transactional
    public void getAllUprofilesByFacebookIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where facebook is not null
        defaultUprofileShouldBeFound("facebook.specified=true");

        // Get all the uprofileList where facebook is null
        defaultUprofileShouldNotBeFound("facebook.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByTwitterIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where twitter equals to DEFAULT_TWITTER
        defaultUprofileShouldBeFound("twitter.equals=" + DEFAULT_TWITTER);

        // Get all the uprofileList where twitter equals to UPDATED_TWITTER
        defaultUprofileShouldNotBeFound("twitter.equals=" + UPDATED_TWITTER);
    }

    @Test
    @Transactional
    public void getAllUprofilesByTwitterIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where twitter in DEFAULT_TWITTER or UPDATED_TWITTER
        defaultUprofileShouldBeFound("twitter.in=" + DEFAULT_TWITTER + "," + UPDATED_TWITTER);

        // Get all the uprofileList where twitter equals to UPDATED_TWITTER
        defaultUprofileShouldNotBeFound("twitter.in=" + UPDATED_TWITTER);
    }

    @Test
    @Transactional
    public void getAllUprofilesByTwitterIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where twitter is not null
        defaultUprofileShouldBeFound("twitter.specified=true");

        // Get all the uprofileList where twitter is null
        defaultUprofileShouldNotBeFound("twitter.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByLinkedinIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where linkedin equals to DEFAULT_LINKEDIN
        defaultUprofileShouldBeFound("linkedin.equals=" + DEFAULT_LINKEDIN);

        // Get all the uprofileList where linkedin equals to UPDATED_LINKEDIN
        defaultUprofileShouldNotBeFound("linkedin.equals=" + UPDATED_LINKEDIN);
    }

    @Test
    @Transactional
    public void getAllUprofilesByLinkedinIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where linkedin in DEFAULT_LINKEDIN or UPDATED_LINKEDIN
        defaultUprofileShouldBeFound("linkedin.in=" + DEFAULT_LINKEDIN + "," + UPDATED_LINKEDIN);

        // Get all the uprofileList where linkedin equals to UPDATED_LINKEDIN
        defaultUprofileShouldNotBeFound("linkedin.in=" + UPDATED_LINKEDIN);
    }

    @Test
    @Transactional
    public void getAllUprofilesByLinkedinIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where linkedin is not null
        defaultUprofileShouldBeFound("linkedin.specified=true");

        // Get all the uprofileList where linkedin is null
        defaultUprofileShouldNotBeFound("linkedin.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByInstagramIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where instagram equals to DEFAULT_INSTAGRAM
        defaultUprofileShouldBeFound("instagram.equals=" + DEFAULT_INSTAGRAM);

        // Get all the uprofileList where instagram equals to UPDATED_INSTAGRAM
        defaultUprofileShouldNotBeFound("instagram.equals=" + UPDATED_INSTAGRAM);
    }

    @Test
    @Transactional
    public void getAllUprofilesByInstagramIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where instagram in DEFAULT_INSTAGRAM or UPDATED_INSTAGRAM
        defaultUprofileShouldBeFound("instagram.in=" + DEFAULT_INSTAGRAM + "," + UPDATED_INSTAGRAM);

        // Get all the uprofileList where instagram equals to UPDATED_INSTAGRAM
        defaultUprofileShouldNotBeFound("instagram.in=" + UPDATED_INSTAGRAM);
    }

    @Test
    @Transactional
    public void getAllUprofilesByInstagramIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where instagram is not null
        defaultUprofileShouldBeFound("instagram.specified=true");

        // Get all the uprofileList where instagram is null
        defaultUprofileShouldNotBeFound("instagram.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByGooglePlusIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where googlePlus equals to DEFAULT_GOOGLE_PLUS
        defaultUprofileShouldBeFound("googlePlus.equals=" + DEFAULT_GOOGLE_PLUS);

        // Get all the uprofileList where googlePlus equals to UPDATED_GOOGLE_PLUS
        defaultUprofileShouldNotBeFound("googlePlus.equals=" + UPDATED_GOOGLE_PLUS);
    }

    @Test
    @Transactional
    public void getAllUprofilesByGooglePlusIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where googlePlus in DEFAULT_GOOGLE_PLUS or UPDATED_GOOGLE_PLUS
        defaultUprofileShouldBeFound("googlePlus.in=" + DEFAULT_GOOGLE_PLUS + "," + UPDATED_GOOGLE_PLUS);

        // Get all the uprofileList where googlePlus equals to UPDATED_GOOGLE_PLUS
        defaultUprofileShouldNotBeFound("googlePlus.in=" + UPDATED_GOOGLE_PLUS);
    }

    @Test
    @Transactional
    public void getAllUprofilesByGooglePlusIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where googlePlus is not null
        defaultUprofileShouldBeFound("googlePlus.specified=true");

        // Get all the uprofileList where googlePlus is null
        defaultUprofileShouldNotBeFound("googlePlus.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByBirthdateIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where birthdate equals to DEFAULT_BIRTHDATE
        defaultUprofileShouldBeFound("birthdate.equals=" + DEFAULT_BIRTHDATE);

        // Get all the uprofileList where birthdate equals to UPDATED_BIRTHDATE
        defaultUprofileShouldNotBeFound("birthdate.equals=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    public void getAllUprofilesByBirthdateIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where birthdate in DEFAULT_BIRTHDATE or UPDATED_BIRTHDATE
        defaultUprofileShouldBeFound("birthdate.in=" + DEFAULT_BIRTHDATE + "," + UPDATED_BIRTHDATE);

        // Get all the uprofileList where birthdate equals to UPDATED_BIRTHDATE
        defaultUprofileShouldNotBeFound("birthdate.in=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    public void getAllUprofilesByBirthdateIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where birthdate is not null
        defaultUprofileShouldBeFound("birthdate.specified=true");

        // Get all the uprofileList where birthdate is null
        defaultUprofileShouldNotBeFound("birthdate.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByCivilStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where civilStatus equals to DEFAULT_CIVIL_STATUS
        defaultUprofileShouldBeFound("civilStatus.equals=" + DEFAULT_CIVIL_STATUS);

        // Get all the uprofileList where civilStatus equals to UPDATED_CIVIL_STATUS
        defaultUprofileShouldNotBeFound("civilStatus.equals=" + UPDATED_CIVIL_STATUS);
    }

    @Test
    @Transactional
    public void getAllUprofilesByCivilStatusIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where civilStatus in DEFAULT_CIVIL_STATUS or UPDATED_CIVIL_STATUS
        defaultUprofileShouldBeFound("civilStatus.in=" + DEFAULT_CIVIL_STATUS + "," + UPDATED_CIVIL_STATUS);

        // Get all the uprofileList where civilStatus equals to UPDATED_CIVIL_STATUS
        defaultUprofileShouldNotBeFound("civilStatus.in=" + UPDATED_CIVIL_STATUS);
    }

    @Test
    @Transactional
    public void getAllUprofilesByCivilStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where civilStatus is not null
        defaultUprofileShouldBeFound("civilStatus.specified=true");

        // Get all the uprofileList where civilStatus is null
        defaultUprofileShouldNotBeFound("civilStatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByLookingForIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where lookingFor equals to DEFAULT_LOOKING_FOR
        defaultUprofileShouldBeFound("lookingFor.equals=" + DEFAULT_LOOKING_FOR);

        // Get all the uprofileList where lookingFor equals to UPDATED_LOOKING_FOR
        defaultUprofileShouldNotBeFound("lookingFor.equals=" + UPDATED_LOOKING_FOR);
    }

    @Test
    @Transactional
    public void getAllUprofilesByLookingForIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where lookingFor in DEFAULT_LOOKING_FOR or UPDATED_LOOKING_FOR
        defaultUprofileShouldBeFound("lookingFor.in=" + DEFAULT_LOOKING_FOR + "," + UPDATED_LOOKING_FOR);

        // Get all the uprofileList where lookingFor equals to UPDATED_LOOKING_FOR
        defaultUprofileShouldNotBeFound("lookingFor.in=" + UPDATED_LOOKING_FOR);
    }

    @Test
    @Transactional
    public void getAllUprofilesByLookingForIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where lookingFor is not null
        defaultUprofileShouldBeFound("lookingFor.specified=true");

        // Get all the uprofileList where lookingFor is null
        defaultUprofileShouldNotBeFound("lookingFor.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByPurposeIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where purpose equals to DEFAULT_PURPOSE
        defaultUprofileShouldBeFound("purpose.equals=" + DEFAULT_PURPOSE);

        // Get all the uprofileList where purpose equals to UPDATED_PURPOSE
        defaultUprofileShouldNotBeFound("purpose.equals=" + UPDATED_PURPOSE);
    }

    @Test
    @Transactional
    public void getAllUprofilesByPurposeIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where purpose in DEFAULT_PURPOSE or UPDATED_PURPOSE
        defaultUprofileShouldBeFound("purpose.in=" + DEFAULT_PURPOSE + "," + UPDATED_PURPOSE);

        // Get all the uprofileList where purpose equals to UPDATED_PURPOSE
        defaultUprofileShouldNotBeFound("purpose.in=" + UPDATED_PURPOSE);
    }

    @Test
    @Transactional
    public void getAllUprofilesByPurposeIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where purpose is not null
        defaultUprofileShouldBeFound("purpose.specified=true");

        // Get all the uprofileList where purpose is null
        defaultUprofileShouldNotBeFound("purpose.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByPhysicalIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where physical equals to DEFAULT_PHYSICAL
        defaultUprofileShouldBeFound("physical.equals=" + DEFAULT_PHYSICAL);

        // Get all the uprofileList where physical equals to UPDATED_PHYSICAL
        defaultUprofileShouldNotBeFound("physical.equals=" + UPDATED_PHYSICAL);
    }

    @Test
    @Transactional
    public void getAllUprofilesByPhysicalIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where physical in DEFAULT_PHYSICAL or UPDATED_PHYSICAL
        defaultUprofileShouldBeFound("physical.in=" + DEFAULT_PHYSICAL + "," + UPDATED_PHYSICAL);

        // Get all the uprofileList where physical equals to UPDATED_PHYSICAL
        defaultUprofileShouldNotBeFound("physical.in=" + UPDATED_PHYSICAL);
    }

    @Test
    @Transactional
    public void getAllUprofilesByPhysicalIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where physical is not null
        defaultUprofileShouldBeFound("physical.specified=true");

        // Get all the uprofileList where physical is null
        defaultUprofileShouldNotBeFound("physical.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByReligionIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where religion equals to DEFAULT_RELIGION
        defaultUprofileShouldBeFound("religion.equals=" + DEFAULT_RELIGION);

        // Get all the uprofileList where religion equals to UPDATED_RELIGION
        defaultUprofileShouldNotBeFound("religion.equals=" + UPDATED_RELIGION);
    }

    @Test
    @Transactional
    public void getAllUprofilesByReligionIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where religion in DEFAULT_RELIGION or UPDATED_RELIGION
        defaultUprofileShouldBeFound("religion.in=" + DEFAULT_RELIGION + "," + UPDATED_RELIGION);

        // Get all the uprofileList where religion equals to UPDATED_RELIGION
        defaultUprofileShouldNotBeFound("religion.in=" + UPDATED_RELIGION);
    }

    @Test
    @Transactional
    public void getAllUprofilesByReligionIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where religion is not null
        defaultUprofileShouldBeFound("religion.specified=true");

        // Get all the uprofileList where religion is null
        defaultUprofileShouldNotBeFound("religion.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByEthnicGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where ethnicGroup equals to DEFAULT_ETHNIC_GROUP
        defaultUprofileShouldBeFound("ethnicGroup.equals=" + DEFAULT_ETHNIC_GROUP);

        // Get all the uprofileList where ethnicGroup equals to UPDATED_ETHNIC_GROUP
        defaultUprofileShouldNotBeFound("ethnicGroup.equals=" + UPDATED_ETHNIC_GROUP);
    }

    @Test
    @Transactional
    public void getAllUprofilesByEthnicGroupIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where ethnicGroup in DEFAULT_ETHNIC_GROUP or UPDATED_ETHNIC_GROUP
        defaultUprofileShouldBeFound("ethnicGroup.in=" + DEFAULT_ETHNIC_GROUP + "," + UPDATED_ETHNIC_GROUP);

        // Get all the uprofileList where ethnicGroup equals to UPDATED_ETHNIC_GROUP
        defaultUprofileShouldNotBeFound("ethnicGroup.in=" + UPDATED_ETHNIC_GROUP);
    }

    @Test
    @Transactional
    public void getAllUprofilesByEthnicGroupIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where ethnicGroup is not null
        defaultUprofileShouldBeFound("ethnicGroup.specified=true");

        // Get all the uprofileList where ethnicGroup is null
        defaultUprofileShouldNotBeFound("ethnicGroup.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByStudiesIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where studies equals to DEFAULT_STUDIES
        defaultUprofileShouldBeFound("studies.equals=" + DEFAULT_STUDIES);

        // Get all the uprofileList where studies equals to UPDATED_STUDIES
        defaultUprofileShouldNotBeFound("studies.equals=" + UPDATED_STUDIES);
    }

    @Test
    @Transactional
    public void getAllUprofilesByStudiesIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where studies in DEFAULT_STUDIES or UPDATED_STUDIES
        defaultUprofileShouldBeFound("studies.in=" + DEFAULT_STUDIES + "," + UPDATED_STUDIES);

        // Get all the uprofileList where studies equals to UPDATED_STUDIES
        defaultUprofileShouldNotBeFound("studies.in=" + UPDATED_STUDIES);
    }

    @Test
    @Transactional
    public void getAllUprofilesByStudiesIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where studies is not null
        defaultUprofileShouldBeFound("studies.specified=true");

        // Get all the uprofileList where studies is null
        defaultUprofileShouldNotBeFound("studies.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesBySibblingsIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where sibblings equals to DEFAULT_SIBBLINGS
        defaultUprofileShouldBeFound("sibblings.equals=" + DEFAULT_SIBBLINGS);

        // Get all the uprofileList where sibblings equals to UPDATED_SIBBLINGS
        defaultUprofileShouldNotBeFound("sibblings.equals=" + UPDATED_SIBBLINGS);
    }

    @Test
    @Transactional
    public void getAllUprofilesBySibblingsIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where sibblings in DEFAULT_SIBBLINGS or UPDATED_SIBBLINGS
        defaultUprofileShouldBeFound("sibblings.in=" + DEFAULT_SIBBLINGS + "," + UPDATED_SIBBLINGS);

        // Get all the uprofileList where sibblings equals to UPDATED_SIBBLINGS
        defaultUprofileShouldNotBeFound("sibblings.in=" + UPDATED_SIBBLINGS);
    }

    @Test
    @Transactional
    public void getAllUprofilesBySibblingsIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where sibblings is not null
        defaultUprofileShouldBeFound("sibblings.specified=true");

        // Get all the uprofileList where sibblings is null
        defaultUprofileShouldNotBeFound("sibblings.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesBySibblingsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where sibblings greater than or equals to DEFAULT_SIBBLINGS
        defaultUprofileShouldBeFound("sibblings.greaterOrEqualThan=" + DEFAULT_SIBBLINGS);

        // Get all the uprofileList where sibblings greater than or equals to (DEFAULT_SIBBLINGS + 1)
        defaultUprofileShouldNotBeFound("sibblings.greaterOrEqualThan=" + (DEFAULT_SIBBLINGS + 1));
    }

    @Test
    @Transactional
    public void getAllUprofilesBySibblingsIsLessThanSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where sibblings less than or equals to DEFAULT_SIBBLINGS
        defaultUprofileShouldNotBeFound("sibblings.lessThan=" + DEFAULT_SIBBLINGS);

        // Get all the uprofileList where sibblings less than or equals to (DEFAULT_SIBBLINGS + 1)
        defaultUprofileShouldBeFound("sibblings.lessThan=" + (DEFAULT_SIBBLINGS + 1));
    }


    @Test
    @Transactional
    public void getAllUprofilesByEyesIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where eyes equals to DEFAULT_EYES
        defaultUprofileShouldBeFound("eyes.equals=" + DEFAULT_EYES);

        // Get all the uprofileList where eyes equals to UPDATED_EYES
        defaultUprofileShouldNotBeFound("eyes.equals=" + UPDATED_EYES);
    }

    @Test
    @Transactional
    public void getAllUprofilesByEyesIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where eyes in DEFAULT_EYES or UPDATED_EYES
        defaultUprofileShouldBeFound("eyes.in=" + DEFAULT_EYES + "," + UPDATED_EYES);

        // Get all the uprofileList where eyes equals to UPDATED_EYES
        defaultUprofileShouldNotBeFound("eyes.in=" + UPDATED_EYES);
    }

    @Test
    @Transactional
    public void getAllUprofilesByEyesIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where eyes is not null
        defaultUprofileShouldBeFound("eyes.specified=true");

        // Get all the uprofileList where eyes is null
        defaultUprofileShouldNotBeFound("eyes.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesBySmokerIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where smoker equals to DEFAULT_SMOKER
        defaultUprofileShouldBeFound("smoker.equals=" + DEFAULT_SMOKER);

        // Get all the uprofileList where smoker equals to UPDATED_SMOKER
        defaultUprofileShouldNotBeFound("smoker.equals=" + UPDATED_SMOKER);
    }

    @Test
    @Transactional
    public void getAllUprofilesBySmokerIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where smoker in DEFAULT_SMOKER or UPDATED_SMOKER
        defaultUprofileShouldBeFound("smoker.in=" + DEFAULT_SMOKER + "," + UPDATED_SMOKER);

        // Get all the uprofileList where smoker equals to UPDATED_SMOKER
        defaultUprofileShouldNotBeFound("smoker.in=" + UPDATED_SMOKER);
    }

    @Test
    @Transactional
    public void getAllUprofilesBySmokerIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where smoker is not null
        defaultUprofileShouldBeFound("smoker.specified=true");

        // Get all the uprofileList where smoker is null
        defaultUprofileShouldNotBeFound("smoker.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByChildrenIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where children equals to DEFAULT_CHILDREN
        defaultUprofileShouldBeFound("children.equals=" + DEFAULT_CHILDREN);

        // Get all the uprofileList where children equals to UPDATED_CHILDREN
        defaultUprofileShouldNotBeFound("children.equals=" + UPDATED_CHILDREN);
    }

    @Test
    @Transactional
    public void getAllUprofilesByChildrenIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where children in DEFAULT_CHILDREN or UPDATED_CHILDREN
        defaultUprofileShouldBeFound("children.in=" + DEFAULT_CHILDREN + "," + UPDATED_CHILDREN);

        // Get all the uprofileList where children equals to UPDATED_CHILDREN
        defaultUprofileShouldNotBeFound("children.in=" + UPDATED_CHILDREN);
    }

    @Test
    @Transactional
    public void getAllUprofilesByChildrenIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where children is not null
        defaultUprofileShouldBeFound("children.specified=true");

        // Get all the uprofileList where children is null
        defaultUprofileShouldNotBeFound("children.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByFutureChildrenIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where futureChildren equals to DEFAULT_FUTURE_CHILDREN
        defaultUprofileShouldBeFound("futureChildren.equals=" + DEFAULT_FUTURE_CHILDREN);

        // Get all the uprofileList where futureChildren equals to UPDATED_FUTURE_CHILDREN
        defaultUprofileShouldNotBeFound("futureChildren.equals=" + UPDATED_FUTURE_CHILDREN);
    }

    @Test
    @Transactional
    public void getAllUprofilesByFutureChildrenIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where futureChildren in DEFAULT_FUTURE_CHILDREN or UPDATED_FUTURE_CHILDREN
        defaultUprofileShouldBeFound("futureChildren.in=" + DEFAULT_FUTURE_CHILDREN + "," + UPDATED_FUTURE_CHILDREN);

        // Get all the uprofileList where futureChildren equals to UPDATED_FUTURE_CHILDREN
        defaultUprofileShouldNotBeFound("futureChildren.in=" + UPDATED_FUTURE_CHILDREN);
    }

    @Test
    @Transactional
    public void getAllUprofilesByFutureChildrenIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where futureChildren is not null
        defaultUprofileShouldBeFound("futureChildren.specified=true");

        // Get all the uprofileList where futureChildren is null
        defaultUprofileShouldNotBeFound("futureChildren.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByPetIsEqualToSomething() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where pet equals to DEFAULT_PET
        defaultUprofileShouldBeFound("pet.equals=" + DEFAULT_PET);

        // Get all the uprofileList where pet equals to UPDATED_PET
        defaultUprofileShouldNotBeFound("pet.equals=" + UPDATED_PET);
    }

    @Test
    @Transactional
    public void getAllUprofilesByPetIsInShouldWork() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where pet in DEFAULT_PET or UPDATED_PET
        defaultUprofileShouldBeFound("pet.in=" + DEFAULT_PET + "," + UPDATED_PET);

        // Get all the uprofileList where pet equals to UPDATED_PET
        defaultUprofileShouldNotBeFound("pet.in=" + UPDATED_PET);
    }

    @Test
    @Transactional
    public void getAllUprofilesByPetIsNullOrNotNull() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        // Get all the uprofileList where pet is not null
        defaultUprofileShouldBeFound("pet.specified=true");

        // Get all the uprofileList where pet is null
        defaultUprofileShouldNotBeFound("pet.specified=false");
    }

    @Test
    @Transactional
    public void getAllUprofilesByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        uprofile.setUser(user);
        uprofileRepository.saveAndFlush(uprofile);
        Long userId = user.getId();

        // Get all the uprofileList where user equals to userId
        defaultUprofileShouldBeFound("userId.equals=" + userId);

        // Get all the uprofileList where user equals to userId + 1
        defaultUprofileShouldNotBeFound("userId.equals=" + (userId + 1));
    }


    @Test
    @Transactional
    public void getAllUprofilesByInterestIsEqualToSomething() throws Exception {
        // Initialize the database
        Interest interest = InterestResourceIntTest.createEntity(em);
        em.persist(interest);
        em.flush();
        uprofile.addInterest(interest);
        uprofileRepository.saveAndFlush(uprofile);
        Long interestId = interest.getId();

        // Get all the uprofileList where interest equals to interestId
        defaultUprofileShouldBeFound("interestId.equals=" + interestId);

        // Get all the uprofileList where interest equals to interestId + 1
        defaultUprofileShouldNotBeFound("interestId.equals=" + (interestId + 1));
    }


    @Test
    @Transactional
    public void getAllUprofilesByActivityIsEqualToSomething() throws Exception {
        // Initialize the database
        Activity activity = ActivityResourceIntTest.createEntity(em);
        em.persist(activity);
        em.flush();
        uprofile.addActivity(activity);
        uprofileRepository.saveAndFlush(uprofile);
        Long activityId = activity.getId();

        // Get all the uprofileList where activity equals to activityId
        defaultUprofileShouldBeFound("activityId.equals=" + activityId);

        // Get all the uprofileList where activity equals to activityId + 1
        defaultUprofileShouldNotBeFound("activityId.equals=" + (activityId + 1));
    }


    @Test
    @Transactional
    public void getAllUprofilesByCelebIsEqualToSomething() throws Exception {
        // Initialize the database
        Celeb celeb = CelebResourceIntTest.createEntity(em);
        em.persist(celeb);
        em.flush();
        uprofile.addCeleb(celeb);
        uprofileRepository.saveAndFlush(uprofile);
        Long celebId = celeb.getId();

        // Get all the uprofileList where celeb equals to celebId
        defaultUprofileShouldBeFound("celebId.equals=" + celebId);

        // Get all the uprofileList where celeb equals to celebId + 1
        defaultUprofileShouldNotBeFound("celebId.equals=" + (celebId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultUprofileShouldBeFound(String filter) throws Exception {
        restUprofileMockMvc.perform(get("/api/uprofiles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uprofile.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO)))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK)))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER)))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN)))
            .andExpect(jsonPath("$.[*].instagram").value(hasItem(DEFAULT_INSTAGRAM)))
            .andExpect(jsonPath("$.[*].googlePlus").value(hasItem(DEFAULT_GOOGLE_PLUS)))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())))
            .andExpect(jsonPath("$.[*].civilStatus").value(hasItem(DEFAULT_CIVIL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].lookingFor").value(hasItem(DEFAULT_LOOKING_FOR.toString())))
            .andExpect(jsonPath("$.[*].purpose").value(hasItem(DEFAULT_PURPOSE.toString())))
            .andExpect(jsonPath("$.[*].physical").value(hasItem(DEFAULT_PHYSICAL.toString())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.toString())))
            .andExpect(jsonPath("$.[*].ethnicGroup").value(hasItem(DEFAULT_ETHNIC_GROUP.toString())))
            .andExpect(jsonPath("$.[*].studies").value(hasItem(DEFAULT_STUDIES.toString())))
            .andExpect(jsonPath("$.[*].sibblings").value(hasItem(DEFAULT_SIBBLINGS)))
            .andExpect(jsonPath("$.[*].eyes").value(hasItem(DEFAULT_EYES.toString())))
            .andExpect(jsonPath("$.[*].smoker").value(hasItem(DEFAULT_SMOKER.toString())))
            .andExpect(jsonPath("$.[*].children").value(hasItem(DEFAULT_CHILDREN.toString())))
            .andExpect(jsonPath("$.[*].futureChildren").value(hasItem(DEFAULT_FUTURE_CHILDREN.toString())))
            .andExpect(jsonPath("$.[*].pet").value(hasItem(DEFAULT_PET.booleanValue())));

        // Check, that the count call also returns 1
        restUprofileMockMvc.perform(get("/api/uprofiles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultUprofileShouldNotBeFound(String filter) throws Exception {
        restUprofileMockMvc.perform(get("/api/uprofiles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUprofileMockMvc.perform(get("/api/uprofiles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingUprofile() throws Exception {
        // Get the uprofile
        restUprofileMockMvc.perform(get("/api/uprofiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUprofile() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        int databaseSizeBeforeUpdate = uprofileRepository.findAll().size();

        // Update the uprofile
        Uprofile updatedUprofile = uprofileRepository.findById(uprofile.getId()).get();
        // Disconnect from session so that the updates on updatedUprofile are not directly saved in db
        em.detach(updatedUprofile);
        updatedUprofile
            .creationDate(UPDATED_CREATION_DATE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .gender(UPDATED_GENDER)
            .phone(UPDATED_PHONE)
            .bio(UPDATED_BIO)
            .facebook(UPDATED_FACEBOOK)
            .twitter(UPDATED_TWITTER)
            .linkedin(UPDATED_LINKEDIN)
            .instagram(UPDATED_INSTAGRAM)
            .googlePlus(UPDATED_GOOGLE_PLUS)
            .birthdate(UPDATED_BIRTHDATE)
            .civilStatus(UPDATED_CIVIL_STATUS)
            .lookingFor(UPDATED_LOOKING_FOR)
            .purpose(UPDATED_PURPOSE)
            .physical(UPDATED_PHYSICAL)
            .religion(UPDATED_RELIGION)
            .ethnicGroup(UPDATED_ETHNIC_GROUP)
            .studies(UPDATED_STUDIES)
            .sibblings(UPDATED_SIBBLINGS)
            .eyes(UPDATED_EYES)
            .smoker(UPDATED_SMOKER)
            .children(UPDATED_CHILDREN)
            .futureChildren(UPDATED_FUTURE_CHILDREN)
            .pet(UPDATED_PET);
        UprofileDTO uprofileDTO = uprofileMapper.toDto(updatedUprofile);

        restUprofileMockMvc.perform(put("/api/uprofiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uprofileDTO)))
            .andExpect(status().isOk());

        // Validate the Uprofile in the database
        List<Uprofile> uprofileList = uprofileRepository.findAll();
        assertThat(uprofileList).hasSize(databaseSizeBeforeUpdate);
        Uprofile testUprofile = uprofileList.get(uprofileList.size() - 1);
        assertThat(testUprofile.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testUprofile.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testUprofile.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testUprofile.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testUprofile.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUprofile.getBio()).isEqualTo(UPDATED_BIO);
        assertThat(testUprofile.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testUprofile.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testUprofile.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testUprofile.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testUprofile.getGooglePlus()).isEqualTo(UPDATED_GOOGLE_PLUS);
        assertThat(testUprofile.getBirthdate()).isEqualTo(UPDATED_BIRTHDATE);
        assertThat(testUprofile.getCivilStatus()).isEqualTo(UPDATED_CIVIL_STATUS);
        assertThat(testUprofile.getLookingFor()).isEqualTo(UPDATED_LOOKING_FOR);
        assertThat(testUprofile.getPurpose()).isEqualTo(UPDATED_PURPOSE);
        assertThat(testUprofile.getPhysical()).isEqualTo(UPDATED_PHYSICAL);
        assertThat(testUprofile.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testUprofile.getEthnicGroup()).isEqualTo(UPDATED_ETHNIC_GROUP);
        assertThat(testUprofile.getStudies()).isEqualTo(UPDATED_STUDIES);
        assertThat(testUprofile.getSibblings()).isEqualTo(UPDATED_SIBBLINGS);
        assertThat(testUprofile.getEyes()).isEqualTo(UPDATED_EYES);
        assertThat(testUprofile.getSmoker()).isEqualTo(UPDATED_SMOKER);
        assertThat(testUprofile.getChildren()).isEqualTo(UPDATED_CHILDREN);
        assertThat(testUprofile.getFutureChildren()).isEqualTo(UPDATED_FUTURE_CHILDREN);
        assertThat(testUprofile.isPet()).isEqualTo(UPDATED_PET);
    }

    @Test
    @Transactional
    public void updateNonExistingUprofile() throws Exception {
        int databaseSizeBeforeUpdate = uprofileRepository.findAll().size();

        // Create the Uprofile
        UprofileDTO uprofileDTO = uprofileMapper.toDto(uprofile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUprofileMockMvc.perform(put("/api/uprofiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uprofileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Uprofile in the database
        List<Uprofile> uprofileList = uprofileRepository.findAll();
        assertThat(uprofileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUprofile() throws Exception {
        // Initialize the database
        uprofileRepository.saveAndFlush(uprofile);

        int databaseSizeBeforeDelete = uprofileRepository.findAll().size();

        // Delete the uprofile
        restUprofileMockMvc.perform(delete("/api/uprofiles/{id}", uprofile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Uprofile> uprofileList = uprofileRepository.findAll();
        assertThat(uprofileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Uprofile.class);
        Uprofile uprofile1 = new Uprofile();
        uprofile1.setId(1L);
        Uprofile uprofile2 = new Uprofile();
        uprofile2.setId(uprofile1.getId());
        assertThat(uprofile1).isEqualTo(uprofile2);
        uprofile2.setId(2L);
        assertThat(uprofile1).isNotEqualTo(uprofile2);
        uprofile1.setId(null);
        assertThat(uprofile1).isNotEqualTo(uprofile2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UprofileDTO.class);
        UprofileDTO uprofileDTO1 = new UprofileDTO();
        uprofileDTO1.setId(1L);
        UprofileDTO uprofileDTO2 = new UprofileDTO();
        assertThat(uprofileDTO1).isNotEqualTo(uprofileDTO2);
        uprofileDTO2.setId(uprofileDTO1.getId());
        assertThat(uprofileDTO1).isEqualTo(uprofileDTO2);
        uprofileDTO2.setId(2L);
        assertThat(uprofileDTO1).isNotEqualTo(uprofileDTO2);
        uprofileDTO1.setId(null);
        assertThat(uprofileDTO1).isNotEqualTo(uprofileDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uprofileMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uprofileMapper.fromId(null)).isNull();
    }
}
