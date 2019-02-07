package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.ConfigVariables;
import com.jhipsterpress.web.repository.ConfigVariablesRepository;
import com.jhipsterpress.web.service.ConfigVariablesService;
import com.jhipsterpress.web.service.dto.ConfigVariablesDTO;
import com.jhipsterpress.web.service.mapper.ConfigVariablesMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.ConfigVariablesCriteria;
import com.jhipsterpress.web.service.ConfigVariablesQueryService;

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
import java.util.List;


import static com.jhipsterpress.web.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConfigVariablesResource REST controller.
 *
 * @see ConfigVariablesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class ConfigVariablesResourceIntTest {

    private static final Long DEFAULT_CONFIG_VAR_LONG_1 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_1 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_2 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_2 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_3 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_3 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_4 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_4 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_5 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_5 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_6 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_6 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_7 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_7 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_8 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_8 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_9 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_9 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_10 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_10 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_11 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_11 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_12 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_12 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_13 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_13 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_14 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_14 = 2L;

    private static final Long DEFAULT_CONFIG_VAR_LONG_15 = 1L;
    private static final Long UPDATED_CONFIG_VAR_LONG_15 = 2L;

    private static final Boolean DEFAULT_CONFIG_VAR_BOOLEAN_16 = false;
    private static final Boolean UPDATED_CONFIG_VAR_BOOLEAN_16 = true;

    private static final Boolean DEFAULT_CONFIG_VAR_BOOLEAN_17 = false;
    private static final Boolean UPDATED_CONFIG_VAR_BOOLEAN_17 = true;

    private static final Boolean DEFAULT_CONFIG_VAR_BOOLEAN_18 = false;
    private static final Boolean UPDATED_CONFIG_VAR_BOOLEAN_18 = true;

    private static final String DEFAULT_CONFIG_VAR_STRING_19 = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_VAR_STRING_19 = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_VAR_STRING_20 = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_VAR_STRING_20 = "BBBBBBBBBB";

    @Autowired
    private ConfigVariablesRepository configVariablesRepository;

    @Autowired
    private ConfigVariablesMapper configVariablesMapper;

    @Autowired
    private ConfigVariablesService configVariablesService;

    @Autowired
    private ConfigVariablesQueryService configVariablesQueryService;

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

    private MockMvc restConfigVariablesMockMvc;

    private ConfigVariables configVariables;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConfigVariablesResource configVariablesResource = new ConfigVariablesResource(configVariablesService, configVariablesQueryService);
        this.restConfigVariablesMockMvc = MockMvcBuilders.standaloneSetup(configVariablesResource)
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
    public static ConfigVariables createEntity(EntityManager em) {
        ConfigVariables configVariables = new ConfigVariables()
            .configVarLong1(DEFAULT_CONFIG_VAR_LONG_1)
            .configVarLong2(DEFAULT_CONFIG_VAR_LONG_2)
            .configVarLong3(DEFAULT_CONFIG_VAR_LONG_3)
            .configVarLong4(DEFAULT_CONFIG_VAR_LONG_4)
            .configVarLong5(DEFAULT_CONFIG_VAR_LONG_5)
            .configVarLong6(DEFAULT_CONFIG_VAR_LONG_6)
            .configVarLong7(DEFAULT_CONFIG_VAR_LONG_7)
            .configVarLong8(DEFAULT_CONFIG_VAR_LONG_8)
            .configVarLong9(DEFAULT_CONFIG_VAR_LONG_9)
            .configVarLong10(DEFAULT_CONFIG_VAR_LONG_10)
            .configVarLong11(DEFAULT_CONFIG_VAR_LONG_11)
            .configVarLong12(DEFAULT_CONFIG_VAR_LONG_12)
            .configVarLong13(DEFAULT_CONFIG_VAR_LONG_13)
            .configVarLong14(DEFAULT_CONFIG_VAR_LONG_14)
            .configVarLong15(DEFAULT_CONFIG_VAR_LONG_15)
            .configVarBoolean16(DEFAULT_CONFIG_VAR_BOOLEAN_16)
            .configVarBoolean17(DEFAULT_CONFIG_VAR_BOOLEAN_17)
            .configVarBoolean18(DEFAULT_CONFIG_VAR_BOOLEAN_18)
            .configVarString19(DEFAULT_CONFIG_VAR_STRING_19)
            .configVarString20(DEFAULT_CONFIG_VAR_STRING_20);
        return configVariables;
    }

    @Before
    public void initTest() {
        configVariables = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfigVariables() throws Exception {
        int databaseSizeBeforeCreate = configVariablesRepository.findAll().size();

        // Create the ConfigVariables
        ConfigVariablesDTO configVariablesDTO = configVariablesMapper.toDto(configVariables);
        restConfigVariablesMockMvc.perform(post("/api/config-variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configVariablesDTO)))
            .andExpect(status().isCreated());

        // Validate the ConfigVariables in the database
        List<ConfigVariables> configVariablesList = configVariablesRepository.findAll();
        assertThat(configVariablesList).hasSize(databaseSizeBeforeCreate + 1);
        ConfigVariables testConfigVariables = configVariablesList.get(configVariablesList.size() - 1);
        assertThat(testConfigVariables.getConfigVarLong1()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_1);
        assertThat(testConfigVariables.getConfigVarLong2()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_2);
        assertThat(testConfigVariables.getConfigVarLong3()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_3);
        assertThat(testConfigVariables.getConfigVarLong4()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_4);
        assertThat(testConfigVariables.getConfigVarLong5()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_5);
        assertThat(testConfigVariables.getConfigVarLong6()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_6);
        assertThat(testConfigVariables.getConfigVarLong7()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_7);
        assertThat(testConfigVariables.getConfigVarLong8()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_8);
        assertThat(testConfigVariables.getConfigVarLong9()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_9);
        assertThat(testConfigVariables.getConfigVarLong10()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_10);
        assertThat(testConfigVariables.getConfigVarLong11()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_11);
        assertThat(testConfigVariables.getConfigVarLong12()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_12);
        assertThat(testConfigVariables.getConfigVarLong13()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_13);
        assertThat(testConfigVariables.getConfigVarLong14()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_14);
        assertThat(testConfigVariables.getConfigVarLong15()).isEqualTo(DEFAULT_CONFIG_VAR_LONG_15);
        assertThat(testConfigVariables.isConfigVarBoolean16()).isEqualTo(DEFAULT_CONFIG_VAR_BOOLEAN_16);
        assertThat(testConfigVariables.isConfigVarBoolean17()).isEqualTo(DEFAULT_CONFIG_VAR_BOOLEAN_17);
        assertThat(testConfigVariables.isConfigVarBoolean18()).isEqualTo(DEFAULT_CONFIG_VAR_BOOLEAN_18);
        assertThat(testConfigVariables.getConfigVarString19()).isEqualTo(DEFAULT_CONFIG_VAR_STRING_19);
        assertThat(testConfigVariables.getConfigVarString20()).isEqualTo(DEFAULT_CONFIG_VAR_STRING_20);
    }

    @Test
    @Transactional
    public void createConfigVariablesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configVariablesRepository.findAll().size();

        // Create the ConfigVariables with an existing ID
        configVariables.setId(1L);
        ConfigVariablesDTO configVariablesDTO = configVariablesMapper.toDto(configVariables);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfigVariablesMockMvc.perform(post("/api/config-variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configVariablesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConfigVariables in the database
        List<ConfigVariables> configVariablesList = configVariablesRepository.findAll();
        assertThat(configVariablesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConfigVariables() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList
        restConfigVariablesMockMvc.perform(get("/api/config-variables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configVariables.getId().intValue())))
            .andExpect(jsonPath("$.[*].configVarLong1").value(hasItem(DEFAULT_CONFIG_VAR_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong2").value(hasItem(DEFAULT_CONFIG_VAR_LONG_2.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong3").value(hasItem(DEFAULT_CONFIG_VAR_LONG_3.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong4").value(hasItem(DEFAULT_CONFIG_VAR_LONG_4.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong5").value(hasItem(DEFAULT_CONFIG_VAR_LONG_5.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong6").value(hasItem(DEFAULT_CONFIG_VAR_LONG_6.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong7").value(hasItem(DEFAULT_CONFIG_VAR_LONG_7.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong8").value(hasItem(DEFAULT_CONFIG_VAR_LONG_8.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong9").value(hasItem(DEFAULT_CONFIG_VAR_LONG_9.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong10").value(hasItem(DEFAULT_CONFIG_VAR_LONG_10.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong11").value(hasItem(DEFAULT_CONFIG_VAR_LONG_11.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong12").value(hasItem(DEFAULT_CONFIG_VAR_LONG_12.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong13").value(hasItem(DEFAULT_CONFIG_VAR_LONG_13.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong14").value(hasItem(DEFAULT_CONFIG_VAR_LONG_14.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong15").value(hasItem(DEFAULT_CONFIG_VAR_LONG_15.intValue())))
            .andExpect(jsonPath("$.[*].configVarBoolean16").value(hasItem(DEFAULT_CONFIG_VAR_BOOLEAN_16.booleanValue())))
            .andExpect(jsonPath("$.[*].configVarBoolean17").value(hasItem(DEFAULT_CONFIG_VAR_BOOLEAN_17.booleanValue())))
            .andExpect(jsonPath("$.[*].configVarBoolean18").value(hasItem(DEFAULT_CONFIG_VAR_BOOLEAN_18.booleanValue())))
            .andExpect(jsonPath("$.[*].configVarString19").value(hasItem(DEFAULT_CONFIG_VAR_STRING_19.toString())))
            .andExpect(jsonPath("$.[*].configVarString20").value(hasItem(DEFAULT_CONFIG_VAR_STRING_20.toString())));
    }
    
    @Test
    @Transactional
    public void getConfigVariables() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get the configVariables
        restConfigVariablesMockMvc.perform(get("/api/config-variables/{id}", configVariables.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(configVariables.getId().intValue()))
            .andExpect(jsonPath("$.configVarLong1").value(DEFAULT_CONFIG_VAR_LONG_1.intValue()))
            .andExpect(jsonPath("$.configVarLong2").value(DEFAULT_CONFIG_VAR_LONG_2.intValue()))
            .andExpect(jsonPath("$.configVarLong3").value(DEFAULT_CONFIG_VAR_LONG_3.intValue()))
            .andExpect(jsonPath("$.configVarLong4").value(DEFAULT_CONFIG_VAR_LONG_4.intValue()))
            .andExpect(jsonPath("$.configVarLong5").value(DEFAULT_CONFIG_VAR_LONG_5.intValue()))
            .andExpect(jsonPath("$.configVarLong6").value(DEFAULT_CONFIG_VAR_LONG_6.intValue()))
            .andExpect(jsonPath("$.configVarLong7").value(DEFAULT_CONFIG_VAR_LONG_7.intValue()))
            .andExpect(jsonPath("$.configVarLong8").value(DEFAULT_CONFIG_VAR_LONG_8.intValue()))
            .andExpect(jsonPath("$.configVarLong9").value(DEFAULT_CONFIG_VAR_LONG_9.intValue()))
            .andExpect(jsonPath("$.configVarLong10").value(DEFAULT_CONFIG_VAR_LONG_10.intValue()))
            .andExpect(jsonPath("$.configVarLong11").value(DEFAULT_CONFIG_VAR_LONG_11.intValue()))
            .andExpect(jsonPath("$.configVarLong12").value(DEFAULT_CONFIG_VAR_LONG_12.intValue()))
            .andExpect(jsonPath("$.configVarLong13").value(DEFAULT_CONFIG_VAR_LONG_13.intValue()))
            .andExpect(jsonPath("$.configVarLong14").value(DEFAULT_CONFIG_VAR_LONG_14.intValue()))
            .andExpect(jsonPath("$.configVarLong15").value(DEFAULT_CONFIG_VAR_LONG_15.intValue()))
            .andExpect(jsonPath("$.configVarBoolean16").value(DEFAULT_CONFIG_VAR_BOOLEAN_16.booleanValue()))
            .andExpect(jsonPath("$.configVarBoolean17").value(DEFAULT_CONFIG_VAR_BOOLEAN_17.booleanValue()))
            .andExpect(jsonPath("$.configVarBoolean18").value(DEFAULT_CONFIG_VAR_BOOLEAN_18.booleanValue()))
            .andExpect(jsonPath("$.configVarString19").value(DEFAULT_CONFIG_VAR_STRING_19.toString()))
            .andExpect(jsonPath("$.configVarString20").value(DEFAULT_CONFIG_VAR_STRING_20.toString()));
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong1IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong1 equals to DEFAULT_CONFIG_VAR_LONG_1
        defaultConfigVariablesShouldBeFound("configVarLong1.equals=" + DEFAULT_CONFIG_VAR_LONG_1);

        // Get all the configVariablesList where configVarLong1 equals to UPDATED_CONFIG_VAR_LONG_1
        defaultConfigVariablesShouldNotBeFound("configVarLong1.equals=" + UPDATED_CONFIG_VAR_LONG_1);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong1IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong1 in DEFAULT_CONFIG_VAR_LONG_1 or UPDATED_CONFIG_VAR_LONG_1
        defaultConfigVariablesShouldBeFound("configVarLong1.in=" + DEFAULT_CONFIG_VAR_LONG_1 + "," + UPDATED_CONFIG_VAR_LONG_1);

        // Get all the configVariablesList where configVarLong1 equals to UPDATED_CONFIG_VAR_LONG_1
        defaultConfigVariablesShouldNotBeFound("configVarLong1.in=" + UPDATED_CONFIG_VAR_LONG_1);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong1IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong1 is not null
        defaultConfigVariablesShouldBeFound("configVarLong1.specified=true");

        // Get all the configVariablesList where configVarLong1 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong1.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong1 greater than or equals to DEFAULT_CONFIG_VAR_LONG_1
        defaultConfigVariablesShouldBeFound("configVarLong1.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_1);

        // Get all the configVariablesList where configVarLong1 greater than or equals to UPDATED_CONFIG_VAR_LONG_1
        defaultConfigVariablesShouldNotBeFound("configVarLong1.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_1);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong1IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong1 less than or equals to DEFAULT_CONFIG_VAR_LONG_1
        defaultConfigVariablesShouldNotBeFound("configVarLong1.lessThan=" + DEFAULT_CONFIG_VAR_LONG_1);

        // Get all the configVariablesList where configVarLong1 less than or equals to UPDATED_CONFIG_VAR_LONG_1
        defaultConfigVariablesShouldBeFound("configVarLong1.lessThan=" + UPDATED_CONFIG_VAR_LONG_1);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong2IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong2 equals to DEFAULT_CONFIG_VAR_LONG_2
        defaultConfigVariablesShouldBeFound("configVarLong2.equals=" + DEFAULT_CONFIG_VAR_LONG_2);

        // Get all the configVariablesList where configVarLong2 equals to UPDATED_CONFIG_VAR_LONG_2
        defaultConfigVariablesShouldNotBeFound("configVarLong2.equals=" + UPDATED_CONFIG_VAR_LONG_2);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong2IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong2 in DEFAULT_CONFIG_VAR_LONG_2 or UPDATED_CONFIG_VAR_LONG_2
        defaultConfigVariablesShouldBeFound("configVarLong2.in=" + DEFAULT_CONFIG_VAR_LONG_2 + "," + UPDATED_CONFIG_VAR_LONG_2);

        // Get all the configVariablesList where configVarLong2 equals to UPDATED_CONFIG_VAR_LONG_2
        defaultConfigVariablesShouldNotBeFound("configVarLong2.in=" + UPDATED_CONFIG_VAR_LONG_2);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong2IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong2 is not null
        defaultConfigVariablesShouldBeFound("configVarLong2.specified=true");

        // Get all the configVariablesList where configVarLong2 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong2.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong2 greater than or equals to DEFAULT_CONFIG_VAR_LONG_2
        defaultConfigVariablesShouldBeFound("configVarLong2.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_2);

        // Get all the configVariablesList where configVarLong2 greater than or equals to UPDATED_CONFIG_VAR_LONG_2
        defaultConfigVariablesShouldNotBeFound("configVarLong2.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_2);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong2IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong2 less than or equals to DEFAULT_CONFIG_VAR_LONG_2
        defaultConfigVariablesShouldNotBeFound("configVarLong2.lessThan=" + DEFAULT_CONFIG_VAR_LONG_2);

        // Get all the configVariablesList where configVarLong2 less than or equals to UPDATED_CONFIG_VAR_LONG_2
        defaultConfigVariablesShouldBeFound("configVarLong2.lessThan=" + UPDATED_CONFIG_VAR_LONG_2);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong3IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong3 equals to DEFAULT_CONFIG_VAR_LONG_3
        defaultConfigVariablesShouldBeFound("configVarLong3.equals=" + DEFAULT_CONFIG_VAR_LONG_3);

        // Get all the configVariablesList where configVarLong3 equals to UPDATED_CONFIG_VAR_LONG_3
        defaultConfigVariablesShouldNotBeFound("configVarLong3.equals=" + UPDATED_CONFIG_VAR_LONG_3);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong3IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong3 in DEFAULT_CONFIG_VAR_LONG_3 or UPDATED_CONFIG_VAR_LONG_3
        defaultConfigVariablesShouldBeFound("configVarLong3.in=" + DEFAULT_CONFIG_VAR_LONG_3 + "," + UPDATED_CONFIG_VAR_LONG_3);

        // Get all the configVariablesList where configVarLong3 equals to UPDATED_CONFIG_VAR_LONG_3
        defaultConfigVariablesShouldNotBeFound("configVarLong3.in=" + UPDATED_CONFIG_VAR_LONG_3);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong3IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong3 is not null
        defaultConfigVariablesShouldBeFound("configVarLong3.specified=true");

        // Get all the configVariablesList where configVarLong3 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong3.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong3 greater than or equals to DEFAULT_CONFIG_VAR_LONG_3
        defaultConfigVariablesShouldBeFound("configVarLong3.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_3);

        // Get all the configVariablesList where configVarLong3 greater than or equals to UPDATED_CONFIG_VAR_LONG_3
        defaultConfigVariablesShouldNotBeFound("configVarLong3.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_3);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong3IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong3 less than or equals to DEFAULT_CONFIG_VAR_LONG_3
        defaultConfigVariablesShouldNotBeFound("configVarLong3.lessThan=" + DEFAULT_CONFIG_VAR_LONG_3);

        // Get all the configVariablesList where configVarLong3 less than or equals to UPDATED_CONFIG_VAR_LONG_3
        defaultConfigVariablesShouldBeFound("configVarLong3.lessThan=" + UPDATED_CONFIG_VAR_LONG_3);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong4IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong4 equals to DEFAULT_CONFIG_VAR_LONG_4
        defaultConfigVariablesShouldBeFound("configVarLong4.equals=" + DEFAULT_CONFIG_VAR_LONG_4);

        // Get all the configVariablesList where configVarLong4 equals to UPDATED_CONFIG_VAR_LONG_4
        defaultConfigVariablesShouldNotBeFound("configVarLong4.equals=" + UPDATED_CONFIG_VAR_LONG_4);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong4IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong4 in DEFAULT_CONFIG_VAR_LONG_4 or UPDATED_CONFIG_VAR_LONG_4
        defaultConfigVariablesShouldBeFound("configVarLong4.in=" + DEFAULT_CONFIG_VAR_LONG_4 + "," + UPDATED_CONFIG_VAR_LONG_4);

        // Get all the configVariablesList where configVarLong4 equals to UPDATED_CONFIG_VAR_LONG_4
        defaultConfigVariablesShouldNotBeFound("configVarLong4.in=" + UPDATED_CONFIG_VAR_LONG_4);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong4IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong4 is not null
        defaultConfigVariablesShouldBeFound("configVarLong4.specified=true");

        // Get all the configVariablesList where configVarLong4 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong4.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong4 greater than or equals to DEFAULT_CONFIG_VAR_LONG_4
        defaultConfigVariablesShouldBeFound("configVarLong4.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_4);

        // Get all the configVariablesList where configVarLong4 greater than or equals to UPDATED_CONFIG_VAR_LONG_4
        defaultConfigVariablesShouldNotBeFound("configVarLong4.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_4);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong4IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong4 less than or equals to DEFAULT_CONFIG_VAR_LONG_4
        defaultConfigVariablesShouldNotBeFound("configVarLong4.lessThan=" + DEFAULT_CONFIG_VAR_LONG_4);

        // Get all the configVariablesList where configVarLong4 less than or equals to UPDATED_CONFIG_VAR_LONG_4
        defaultConfigVariablesShouldBeFound("configVarLong4.lessThan=" + UPDATED_CONFIG_VAR_LONG_4);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong5IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong5 equals to DEFAULT_CONFIG_VAR_LONG_5
        defaultConfigVariablesShouldBeFound("configVarLong5.equals=" + DEFAULT_CONFIG_VAR_LONG_5);

        // Get all the configVariablesList where configVarLong5 equals to UPDATED_CONFIG_VAR_LONG_5
        defaultConfigVariablesShouldNotBeFound("configVarLong5.equals=" + UPDATED_CONFIG_VAR_LONG_5);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong5IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong5 in DEFAULT_CONFIG_VAR_LONG_5 or UPDATED_CONFIG_VAR_LONG_5
        defaultConfigVariablesShouldBeFound("configVarLong5.in=" + DEFAULT_CONFIG_VAR_LONG_5 + "," + UPDATED_CONFIG_VAR_LONG_5);

        // Get all the configVariablesList where configVarLong5 equals to UPDATED_CONFIG_VAR_LONG_5
        defaultConfigVariablesShouldNotBeFound("configVarLong5.in=" + UPDATED_CONFIG_VAR_LONG_5);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong5IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong5 is not null
        defaultConfigVariablesShouldBeFound("configVarLong5.specified=true");

        // Get all the configVariablesList where configVarLong5 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong5.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong5 greater than or equals to DEFAULT_CONFIG_VAR_LONG_5
        defaultConfigVariablesShouldBeFound("configVarLong5.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_5);

        // Get all the configVariablesList where configVarLong5 greater than or equals to UPDATED_CONFIG_VAR_LONG_5
        defaultConfigVariablesShouldNotBeFound("configVarLong5.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_5);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong5IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong5 less than or equals to DEFAULT_CONFIG_VAR_LONG_5
        defaultConfigVariablesShouldNotBeFound("configVarLong5.lessThan=" + DEFAULT_CONFIG_VAR_LONG_5);

        // Get all the configVariablesList where configVarLong5 less than or equals to UPDATED_CONFIG_VAR_LONG_5
        defaultConfigVariablesShouldBeFound("configVarLong5.lessThan=" + UPDATED_CONFIG_VAR_LONG_5);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong6IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong6 equals to DEFAULT_CONFIG_VAR_LONG_6
        defaultConfigVariablesShouldBeFound("configVarLong6.equals=" + DEFAULT_CONFIG_VAR_LONG_6);

        // Get all the configVariablesList where configVarLong6 equals to UPDATED_CONFIG_VAR_LONG_6
        defaultConfigVariablesShouldNotBeFound("configVarLong6.equals=" + UPDATED_CONFIG_VAR_LONG_6);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong6IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong6 in DEFAULT_CONFIG_VAR_LONG_6 or UPDATED_CONFIG_VAR_LONG_6
        defaultConfigVariablesShouldBeFound("configVarLong6.in=" + DEFAULT_CONFIG_VAR_LONG_6 + "," + UPDATED_CONFIG_VAR_LONG_6);

        // Get all the configVariablesList where configVarLong6 equals to UPDATED_CONFIG_VAR_LONG_6
        defaultConfigVariablesShouldNotBeFound("configVarLong6.in=" + UPDATED_CONFIG_VAR_LONG_6);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong6IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong6 is not null
        defaultConfigVariablesShouldBeFound("configVarLong6.specified=true");

        // Get all the configVariablesList where configVarLong6 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong6.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong6 greater than or equals to DEFAULT_CONFIG_VAR_LONG_6
        defaultConfigVariablesShouldBeFound("configVarLong6.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_6);

        // Get all the configVariablesList where configVarLong6 greater than or equals to UPDATED_CONFIG_VAR_LONG_6
        defaultConfigVariablesShouldNotBeFound("configVarLong6.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_6);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong6IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong6 less than or equals to DEFAULT_CONFIG_VAR_LONG_6
        defaultConfigVariablesShouldNotBeFound("configVarLong6.lessThan=" + DEFAULT_CONFIG_VAR_LONG_6);

        // Get all the configVariablesList where configVarLong6 less than or equals to UPDATED_CONFIG_VAR_LONG_6
        defaultConfigVariablesShouldBeFound("configVarLong6.lessThan=" + UPDATED_CONFIG_VAR_LONG_6);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong7IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong7 equals to DEFAULT_CONFIG_VAR_LONG_7
        defaultConfigVariablesShouldBeFound("configVarLong7.equals=" + DEFAULT_CONFIG_VAR_LONG_7);

        // Get all the configVariablesList where configVarLong7 equals to UPDATED_CONFIG_VAR_LONG_7
        defaultConfigVariablesShouldNotBeFound("configVarLong7.equals=" + UPDATED_CONFIG_VAR_LONG_7);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong7IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong7 in DEFAULT_CONFIG_VAR_LONG_7 or UPDATED_CONFIG_VAR_LONG_7
        defaultConfigVariablesShouldBeFound("configVarLong7.in=" + DEFAULT_CONFIG_VAR_LONG_7 + "," + UPDATED_CONFIG_VAR_LONG_7);

        // Get all the configVariablesList where configVarLong7 equals to UPDATED_CONFIG_VAR_LONG_7
        defaultConfigVariablesShouldNotBeFound("configVarLong7.in=" + UPDATED_CONFIG_VAR_LONG_7);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong7IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong7 is not null
        defaultConfigVariablesShouldBeFound("configVarLong7.specified=true");

        // Get all the configVariablesList where configVarLong7 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong7.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong7IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong7 greater than or equals to DEFAULT_CONFIG_VAR_LONG_7
        defaultConfigVariablesShouldBeFound("configVarLong7.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_7);

        // Get all the configVariablesList where configVarLong7 greater than or equals to UPDATED_CONFIG_VAR_LONG_7
        defaultConfigVariablesShouldNotBeFound("configVarLong7.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_7);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong7IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong7 less than or equals to DEFAULT_CONFIG_VAR_LONG_7
        defaultConfigVariablesShouldNotBeFound("configVarLong7.lessThan=" + DEFAULT_CONFIG_VAR_LONG_7);

        // Get all the configVariablesList where configVarLong7 less than or equals to UPDATED_CONFIG_VAR_LONG_7
        defaultConfigVariablesShouldBeFound("configVarLong7.lessThan=" + UPDATED_CONFIG_VAR_LONG_7);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong8IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong8 equals to DEFAULT_CONFIG_VAR_LONG_8
        defaultConfigVariablesShouldBeFound("configVarLong8.equals=" + DEFAULT_CONFIG_VAR_LONG_8);

        // Get all the configVariablesList where configVarLong8 equals to UPDATED_CONFIG_VAR_LONG_8
        defaultConfigVariablesShouldNotBeFound("configVarLong8.equals=" + UPDATED_CONFIG_VAR_LONG_8);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong8IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong8 in DEFAULT_CONFIG_VAR_LONG_8 or UPDATED_CONFIG_VAR_LONG_8
        defaultConfigVariablesShouldBeFound("configVarLong8.in=" + DEFAULT_CONFIG_VAR_LONG_8 + "," + UPDATED_CONFIG_VAR_LONG_8);

        // Get all the configVariablesList where configVarLong8 equals to UPDATED_CONFIG_VAR_LONG_8
        defaultConfigVariablesShouldNotBeFound("configVarLong8.in=" + UPDATED_CONFIG_VAR_LONG_8);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong8IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong8 is not null
        defaultConfigVariablesShouldBeFound("configVarLong8.specified=true");

        // Get all the configVariablesList where configVarLong8 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong8.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong8IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong8 greater than or equals to DEFAULT_CONFIG_VAR_LONG_8
        defaultConfigVariablesShouldBeFound("configVarLong8.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_8);

        // Get all the configVariablesList where configVarLong8 greater than or equals to UPDATED_CONFIG_VAR_LONG_8
        defaultConfigVariablesShouldNotBeFound("configVarLong8.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_8);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong8IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong8 less than or equals to DEFAULT_CONFIG_VAR_LONG_8
        defaultConfigVariablesShouldNotBeFound("configVarLong8.lessThan=" + DEFAULT_CONFIG_VAR_LONG_8);

        // Get all the configVariablesList where configVarLong8 less than or equals to UPDATED_CONFIG_VAR_LONG_8
        defaultConfigVariablesShouldBeFound("configVarLong8.lessThan=" + UPDATED_CONFIG_VAR_LONG_8);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong9IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong9 equals to DEFAULT_CONFIG_VAR_LONG_9
        defaultConfigVariablesShouldBeFound("configVarLong9.equals=" + DEFAULT_CONFIG_VAR_LONG_9);

        // Get all the configVariablesList where configVarLong9 equals to UPDATED_CONFIG_VAR_LONG_9
        defaultConfigVariablesShouldNotBeFound("configVarLong9.equals=" + UPDATED_CONFIG_VAR_LONG_9);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong9IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong9 in DEFAULT_CONFIG_VAR_LONG_9 or UPDATED_CONFIG_VAR_LONG_9
        defaultConfigVariablesShouldBeFound("configVarLong9.in=" + DEFAULT_CONFIG_VAR_LONG_9 + "," + UPDATED_CONFIG_VAR_LONG_9);

        // Get all the configVariablesList where configVarLong9 equals to UPDATED_CONFIG_VAR_LONG_9
        defaultConfigVariablesShouldNotBeFound("configVarLong9.in=" + UPDATED_CONFIG_VAR_LONG_9);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong9IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong9 is not null
        defaultConfigVariablesShouldBeFound("configVarLong9.specified=true");

        // Get all the configVariablesList where configVarLong9 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong9.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong9IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong9 greater than or equals to DEFAULT_CONFIG_VAR_LONG_9
        defaultConfigVariablesShouldBeFound("configVarLong9.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_9);

        // Get all the configVariablesList where configVarLong9 greater than or equals to UPDATED_CONFIG_VAR_LONG_9
        defaultConfigVariablesShouldNotBeFound("configVarLong9.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_9);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong9IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong9 less than or equals to DEFAULT_CONFIG_VAR_LONG_9
        defaultConfigVariablesShouldNotBeFound("configVarLong9.lessThan=" + DEFAULT_CONFIG_VAR_LONG_9);

        // Get all the configVariablesList where configVarLong9 less than or equals to UPDATED_CONFIG_VAR_LONG_9
        defaultConfigVariablesShouldBeFound("configVarLong9.lessThan=" + UPDATED_CONFIG_VAR_LONG_9);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong10IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong10 equals to DEFAULT_CONFIG_VAR_LONG_10
        defaultConfigVariablesShouldBeFound("configVarLong10.equals=" + DEFAULT_CONFIG_VAR_LONG_10);

        // Get all the configVariablesList where configVarLong10 equals to UPDATED_CONFIG_VAR_LONG_10
        defaultConfigVariablesShouldNotBeFound("configVarLong10.equals=" + UPDATED_CONFIG_VAR_LONG_10);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong10IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong10 in DEFAULT_CONFIG_VAR_LONG_10 or UPDATED_CONFIG_VAR_LONG_10
        defaultConfigVariablesShouldBeFound("configVarLong10.in=" + DEFAULT_CONFIG_VAR_LONG_10 + "," + UPDATED_CONFIG_VAR_LONG_10);

        // Get all the configVariablesList where configVarLong10 equals to UPDATED_CONFIG_VAR_LONG_10
        defaultConfigVariablesShouldNotBeFound("configVarLong10.in=" + UPDATED_CONFIG_VAR_LONG_10);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong10IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong10 is not null
        defaultConfigVariablesShouldBeFound("configVarLong10.specified=true");

        // Get all the configVariablesList where configVarLong10 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong10.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong10IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong10 greater than or equals to DEFAULT_CONFIG_VAR_LONG_10
        defaultConfigVariablesShouldBeFound("configVarLong10.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_10);

        // Get all the configVariablesList where configVarLong10 greater than or equals to UPDATED_CONFIG_VAR_LONG_10
        defaultConfigVariablesShouldNotBeFound("configVarLong10.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_10);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong10IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong10 less than or equals to DEFAULT_CONFIG_VAR_LONG_10
        defaultConfigVariablesShouldNotBeFound("configVarLong10.lessThan=" + DEFAULT_CONFIG_VAR_LONG_10);

        // Get all the configVariablesList where configVarLong10 less than or equals to UPDATED_CONFIG_VAR_LONG_10
        defaultConfigVariablesShouldBeFound("configVarLong10.lessThan=" + UPDATED_CONFIG_VAR_LONG_10);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong11IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong11 equals to DEFAULT_CONFIG_VAR_LONG_11
        defaultConfigVariablesShouldBeFound("configVarLong11.equals=" + DEFAULT_CONFIG_VAR_LONG_11);

        // Get all the configVariablesList where configVarLong11 equals to UPDATED_CONFIG_VAR_LONG_11
        defaultConfigVariablesShouldNotBeFound("configVarLong11.equals=" + UPDATED_CONFIG_VAR_LONG_11);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong11IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong11 in DEFAULT_CONFIG_VAR_LONG_11 or UPDATED_CONFIG_VAR_LONG_11
        defaultConfigVariablesShouldBeFound("configVarLong11.in=" + DEFAULT_CONFIG_VAR_LONG_11 + "," + UPDATED_CONFIG_VAR_LONG_11);

        // Get all the configVariablesList where configVarLong11 equals to UPDATED_CONFIG_VAR_LONG_11
        defaultConfigVariablesShouldNotBeFound("configVarLong11.in=" + UPDATED_CONFIG_VAR_LONG_11);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong11IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong11 is not null
        defaultConfigVariablesShouldBeFound("configVarLong11.specified=true");

        // Get all the configVariablesList where configVarLong11 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong11.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong11IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong11 greater than or equals to DEFAULT_CONFIG_VAR_LONG_11
        defaultConfigVariablesShouldBeFound("configVarLong11.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_11);

        // Get all the configVariablesList where configVarLong11 greater than or equals to UPDATED_CONFIG_VAR_LONG_11
        defaultConfigVariablesShouldNotBeFound("configVarLong11.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_11);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong11IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong11 less than or equals to DEFAULT_CONFIG_VAR_LONG_11
        defaultConfigVariablesShouldNotBeFound("configVarLong11.lessThan=" + DEFAULT_CONFIG_VAR_LONG_11);

        // Get all the configVariablesList where configVarLong11 less than or equals to UPDATED_CONFIG_VAR_LONG_11
        defaultConfigVariablesShouldBeFound("configVarLong11.lessThan=" + UPDATED_CONFIG_VAR_LONG_11);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong12IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong12 equals to DEFAULT_CONFIG_VAR_LONG_12
        defaultConfigVariablesShouldBeFound("configVarLong12.equals=" + DEFAULT_CONFIG_VAR_LONG_12);

        // Get all the configVariablesList where configVarLong12 equals to UPDATED_CONFIG_VAR_LONG_12
        defaultConfigVariablesShouldNotBeFound("configVarLong12.equals=" + UPDATED_CONFIG_VAR_LONG_12);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong12IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong12 in DEFAULT_CONFIG_VAR_LONG_12 or UPDATED_CONFIG_VAR_LONG_12
        defaultConfigVariablesShouldBeFound("configVarLong12.in=" + DEFAULT_CONFIG_VAR_LONG_12 + "," + UPDATED_CONFIG_VAR_LONG_12);

        // Get all the configVariablesList where configVarLong12 equals to UPDATED_CONFIG_VAR_LONG_12
        defaultConfigVariablesShouldNotBeFound("configVarLong12.in=" + UPDATED_CONFIG_VAR_LONG_12);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong12IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong12 is not null
        defaultConfigVariablesShouldBeFound("configVarLong12.specified=true");

        // Get all the configVariablesList where configVarLong12 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong12.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong12IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong12 greater than or equals to DEFAULT_CONFIG_VAR_LONG_12
        defaultConfigVariablesShouldBeFound("configVarLong12.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_12);

        // Get all the configVariablesList where configVarLong12 greater than or equals to UPDATED_CONFIG_VAR_LONG_12
        defaultConfigVariablesShouldNotBeFound("configVarLong12.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_12);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong12IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong12 less than or equals to DEFAULT_CONFIG_VAR_LONG_12
        defaultConfigVariablesShouldNotBeFound("configVarLong12.lessThan=" + DEFAULT_CONFIG_VAR_LONG_12);

        // Get all the configVariablesList where configVarLong12 less than or equals to UPDATED_CONFIG_VAR_LONG_12
        defaultConfigVariablesShouldBeFound("configVarLong12.lessThan=" + UPDATED_CONFIG_VAR_LONG_12);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong13IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong13 equals to DEFAULT_CONFIG_VAR_LONG_13
        defaultConfigVariablesShouldBeFound("configVarLong13.equals=" + DEFAULT_CONFIG_VAR_LONG_13);

        // Get all the configVariablesList where configVarLong13 equals to UPDATED_CONFIG_VAR_LONG_13
        defaultConfigVariablesShouldNotBeFound("configVarLong13.equals=" + UPDATED_CONFIG_VAR_LONG_13);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong13IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong13 in DEFAULT_CONFIG_VAR_LONG_13 or UPDATED_CONFIG_VAR_LONG_13
        defaultConfigVariablesShouldBeFound("configVarLong13.in=" + DEFAULT_CONFIG_VAR_LONG_13 + "," + UPDATED_CONFIG_VAR_LONG_13);

        // Get all the configVariablesList where configVarLong13 equals to UPDATED_CONFIG_VAR_LONG_13
        defaultConfigVariablesShouldNotBeFound("configVarLong13.in=" + UPDATED_CONFIG_VAR_LONG_13);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong13IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong13 is not null
        defaultConfigVariablesShouldBeFound("configVarLong13.specified=true");

        // Get all the configVariablesList where configVarLong13 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong13.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong13IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong13 greater than or equals to DEFAULT_CONFIG_VAR_LONG_13
        defaultConfigVariablesShouldBeFound("configVarLong13.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_13);

        // Get all the configVariablesList where configVarLong13 greater than or equals to UPDATED_CONFIG_VAR_LONG_13
        defaultConfigVariablesShouldNotBeFound("configVarLong13.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_13);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong13IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong13 less than or equals to DEFAULT_CONFIG_VAR_LONG_13
        defaultConfigVariablesShouldNotBeFound("configVarLong13.lessThan=" + DEFAULT_CONFIG_VAR_LONG_13);

        // Get all the configVariablesList where configVarLong13 less than or equals to UPDATED_CONFIG_VAR_LONG_13
        defaultConfigVariablesShouldBeFound("configVarLong13.lessThan=" + UPDATED_CONFIG_VAR_LONG_13);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong14IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong14 equals to DEFAULT_CONFIG_VAR_LONG_14
        defaultConfigVariablesShouldBeFound("configVarLong14.equals=" + DEFAULT_CONFIG_VAR_LONG_14);

        // Get all the configVariablesList where configVarLong14 equals to UPDATED_CONFIG_VAR_LONG_14
        defaultConfigVariablesShouldNotBeFound("configVarLong14.equals=" + UPDATED_CONFIG_VAR_LONG_14);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong14IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong14 in DEFAULT_CONFIG_VAR_LONG_14 or UPDATED_CONFIG_VAR_LONG_14
        defaultConfigVariablesShouldBeFound("configVarLong14.in=" + DEFAULT_CONFIG_VAR_LONG_14 + "," + UPDATED_CONFIG_VAR_LONG_14);

        // Get all the configVariablesList where configVarLong14 equals to UPDATED_CONFIG_VAR_LONG_14
        defaultConfigVariablesShouldNotBeFound("configVarLong14.in=" + UPDATED_CONFIG_VAR_LONG_14);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong14IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong14 is not null
        defaultConfigVariablesShouldBeFound("configVarLong14.specified=true");

        // Get all the configVariablesList where configVarLong14 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong14.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong14IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong14 greater than or equals to DEFAULT_CONFIG_VAR_LONG_14
        defaultConfigVariablesShouldBeFound("configVarLong14.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_14);

        // Get all the configVariablesList where configVarLong14 greater than or equals to UPDATED_CONFIG_VAR_LONG_14
        defaultConfigVariablesShouldNotBeFound("configVarLong14.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_14);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong14IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong14 less than or equals to DEFAULT_CONFIG_VAR_LONG_14
        defaultConfigVariablesShouldNotBeFound("configVarLong14.lessThan=" + DEFAULT_CONFIG_VAR_LONG_14);

        // Get all the configVariablesList where configVarLong14 less than or equals to UPDATED_CONFIG_VAR_LONG_14
        defaultConfigVariablesShouldBeFound("configVarLong14.lessThan=" + UPDATED_CONFIG_VAR_LONG_14);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong15IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong15 equals to DEFAULT_CONFIG_VAR_LONG_15
        defaultConfigVariablesShouldBeFound("configVarLong15.equals=" + DEFAULT_CONFIG_VAR_LONG_15);

        // Get all the configVariablesList where configVarLong15 equals to UPDATED_CONFIG_VAR_LONG_15
        defaultConfigVariablesShouldNotBeFound("configVarLong15.equals=" + UPDATED_CONFIG_VAR_LONG_15);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong15IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong15 in DEFAULT_CONFIG_VAR_LONG_15 or UPDATED_CONFIG_VAR_LONG_15
        defaultConfigVariablesShouldBeFound("configVarLong15.in=" + DEFAULT_CONFIG_VAR_LONG_15 + "," + UPDATED_CONFIG_VAR_LONG_15);

        // Get all the configVariablesList where configVarLong15 equals to UPDATED_CONFIG_VAR_LONG_15
        defaultConfigVariablesShouldNotBeFound("configVarLong15.in=" + UPDATED_CONFIG_VAR_LONG_15);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong15IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong15 is not null
        defaultConfigVariablesShouldBeFound("configVarLong15.specified=true");

        // Get all the configVariablesList where configVarLong15 is null
        defaultConfigVariablesShouldNotBeFound("configVarLong15.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong15IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong15 greater than or equals to DEFAULT_CONFIG_VAR_LONG_15
        defaultConfigVariablesShouldBeFound("configVarLong15.greaterOrEqualThan=" + DEFAULT_CONFIG_VAR_LONG_15);

        // Get all the configVariablesList where configVarLong15 greater than or equals to UPDATED_CONFIG_VAR_LONG_15
        defaultConfigVariablesShouldNotBeFound("configVarLong15.greaterOrEqualThan=" + UPDATED_CONFIG_VAR_LONG_15);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarLong15IsLessThanSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarLong15 less than or equals to DEFAULT_CONFIG_VAR_LONG_15
        defaultConfigVariablesShouldNotBeFound("configVarLong15.lessThan=" + DEFAULT_CONFIG_VAR_LONG_15);

        // Get all the configVariablesList where configVarLong15 less than or equals to UPDATED_CONFIG_VAR_LONG_15
        defaultConfigVariablesShouldBeFound("configVarLong15.lessThan=" + UPDATED_CONFIG_VAR_LONG_15);
    }


    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarBoolean16IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarBoolean16 equals to DEFAULT_CONFIG_VAR_BOOLEAN_16
        defaultConfigVariablesShouldBeFound("configVarBoolean16.equals=" + DEFAULT_CONFIG_VAR_BOOLEAN_16);

        // Get all the configVariablesList where configVarBoolean16 equals to UPDATED_CONFIG_VAR_BOOLEAN_16
        defaultConfigVariablesShouldNotBeFound("configVarBoolean16.equals=" + UPDATED_CONFIG_VAR_BOOLEAN_16);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarBoolean16IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarBoolean16 in DEFAULT_CONFIG_VAR_BOOLEAN_16 or UPDATED_CONFIG_VAR_BOOLEAN_16
        defaultConfigVariablesShouldBeFound("configVarBoolean16.in=" + DEFAULT_CONFIG_VAR_BOOLEAN_16 + "," + UPDATED_CONFIG_VAR_BOOLEAN_16);

        // Get all the configVariablesList where configVarBoolean16 equals to UPDATED_CONFIG_VAR_BOOLEAN_16
        defaultConfigVariablesShouldNotBeFound("configVarBoolean16.in=" + UPDATED_CONFIG_VAR_BOOLEAN_16);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarBoolean16IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarBoolean16 is not null
        defaultConfigVariablesShouldBeFound("configVarBoolean16.specified=true");

        // Get all the configVariablesList where configVarBoolean16 is null
        defaultConfigVariablesShouldNotBeFound("configVarBoolean16.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarBoolean17IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarBoolean17 equals to DEFAULT_CONFIG_VAR_BOOLEAN_17
        defaultConfigVariablesShouldBeFound("configVarBoolean17.equals=" + DEFAULT_CONFIG_VAR_BOOLEAN_17);

        // Get all the configVariablesList where configVarBoolean17 equals to UPDATED_CONFIG_VAR_BOOLEAN_17
        defaultConfigVariablesShouldNotBeFound("configVarBoolean17.equals=" + UPDATED_CONFIG_VAR_BOOLEAN_17);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarBoolean17IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarBoolean17 in DEFAULT_CONFIG_VAR_BOOLEAN_17 or UPDATED_CONFIG_VAR_BOOLEAN_17
        defaultConfigVariablesShouldBeFound("configVarBoolean17.in=" + DEFAULT_CONFIG_VAR_BOOLEAN_17 + "," + UPDATED_CONFIG_VAR_BOOLEAN_17);

        // Get all the configVariablesList where configVarBoolean17 equals to UPDATED_CONFIG_VAR_BOOLEAN_17
        defaultConfigVariablesShouldNotBeFound("configVarBoolean17.in=" + UPDATED_CONFIG_VAR_BOOLEAN_17);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarBoolean17IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarBoolean17 is not null
        defaultConfigVariablesShouldBeFound("configVarBoolean17.specified=true");

        // Get all the configVariablesList where configVarBoolean17 is null
        defaultConfigVariablesShouldNotBeFound("configVarBoolean17.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarBoolean18IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarBoolean18 equals to DEFAULT_CONFIG_VAR_BOOLEAN_18
        defaultConfigVariablesShouldBeFound("configVarBoolean18.equals=" + DEFAULT_CONFIG_VAR_BOOLEAN_18);

        // Get all the configVariablesList where configVarBoolean18 equals to UPDATED_CONFIG_VAR_BOOLEAN_18
        defaultConfigVariablesShouldNotBeFound("configVarBoolean18.equals=" + UPDATED_CONFIG_VAR_BOOLEAN_18);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarBoolean18IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarBoolean18 in DEFAULT_CONFIG_VAR_BOOLEAN_18 or UPDATED_CONFIG_VAR_BOOLEAN_18
        defaultConfigVariablesShouldBeFound("configVarBoolean18.in=" + DEFAULT_CONFIG_VAR_BOOLEAN_18 + "," + UPDATED_CONFIG_VAR_BOOLEAN_18);

        // Get all the configVariablesList where configVarBoolean18 equals to UPDATED_CONFIG_VAR_BOOLEAN_18
        defaultConfigVariablesShouldNotBeFound("configVarBoolean18.in=" + UPDATED_CONFIG_VAR_BOOLEAN_18);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarBoolean18IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarBoolean18 is not null
        defaultConfigVariablesShouldBeFound("configVarBoolean18.specified=true");

        // Get all the configVariablesList where configVarBoolean18 is null
        defaultConfigVariablesShouldNotBeFound("configVarBoolean18.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarString19IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarString19 equals to DEFAULT_CONFIG_VAR_STRING_19
        defaultConfigVariablesShouldBeFound("configVarString19.equals=" + DEFAULT_CONFIG_VAR_STRING_19);

        // Get all the configVariablesList where configVarString19 equals to UPDATED_CONFIG_VAR_STRING_19
        defaultConfigVariablesShouldNotBeFound("configVarString19.equals=" + UPDATED_CONFIG_VAR_STRING_19);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarString19IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarString19 in DEFAULT_CONFIG_VAR_STRING_19 or UPDATED_CONFIG_VAR_STRING_19
        defaultConfigVariablesShouldBeFound("configVarString19.in=" + DEFAULT_CONFIG_VAR_STRING_19 + "," + UPDATED_CONFIG_VAR_STRING_19);

        // Get all the configVariablesList where configVarString19 equals to UPDATED_CONFIG_VAR_STRING_19
        defaultConfigVariablesShouldNotBeFound("configVarString19.in=" + UPDATED_CONFIG_VAR_STRING_19);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarString19IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarString19 is not null
        defaultConfigVariablesShouldBeFound("configVarString19.specified=true");

        // Get all the configVariablesList where configVarString19 is null
        defaultConfigVariablesShouldNotBeFound("configVarString19.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarString20IsEqualToSomething() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarString20 equals to DEFAULT_CONFIG_VAR_STRING_20
        defaultConfigVariablesShouldBeFound("configVarString20.equals=" + DEFAULT_CONFIG_VAR_STRING_20);

        // Get all the configVariablesList where configVarString20 equals to UPDATED_CONFIG_VAR_STRING_20
        defaultConfigVariablesShouldNotBeFound("configVarString20.equals=" + UPDATED_CONFIG_VAR_STRING_20);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarString20IsInShouldWork() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarString20 in DEFAULT_CONFIG_VAR_STRING_20 or UPDATED_CONFIG_VAR_STRING_20
        defaultConfigVariablesShouldBeFound("configVarString20.in=" + DEFAULT_CONFIG_VAR_STRING_20 + "," + UPDATED_CONFIG_VAR_STRING_20);

        // Get all the configVariablesList where configVarString20 equals to UPDATED_CONFIG_VAR_STRING_20
        defaultConfigVariablesShouldNotBeFound("configVarString20.in=" + UPDATED_CONFIG_VAR_STRING_20);
    }

    @Test
    @Transactional
    public void getAllConfigVariablesByConfigVarString20IsNullOrNotNull() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        // Get all the configVariablesList where configVarString20 is not null
        defaultConfigVariablesShouldBeFound("configVarString20.specified=true");

        // Get all the configVariablesList where configVarString20 is null
        defaultConfigVariablesShouldNotBeFound("configVarString20.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultConfigVariablesShouldBeFound(String filter) throws Exception {
        restConfigVariablesMockMvc.perform(get("/api/config-variables?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configVariables.getId().intValue())))
            .andExpect(jsonPath("$.[*].configVarLong1").value(hasItem(DEFAULT_CONFIG_VAR_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong2").value(hasItem(DEFAULT_CONFIG_VAR_LONG_2.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong3").value(hasItem(DEFAULT_CONFIG_VAR_LONG_3.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong4").value(hasItem(DEFAULT_CONFIG_VAR_LONG_4.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong5").value(hasItem(DEFAULT_CONFIG_VAR_LONG_5.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong6").value(hasItem(DEFAULT_CONFIG_VAR_LONG_6.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong7").value(hasItem(DEFAULT_CONFIG_VAR_LONG_7.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong8").value(hasItem(DEFAULT_CONFIG_VAR_LONG_8.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong9").value(hasItem(DEFAULT_CONFIG_VAR_LONG_9.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong10").value(hasItem(DEFAULT_CONFIG_VAR_LONG_10.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong11").value(hasItem(DEFAULT_CONFIG_VAR_LONG_11.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong12").value(hasItem(DEFAULT_CONFIG_VAR_LONG_12.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong13").value(hasItem(DEFAULT_CONFIG_VAR_LONG_13.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong14").value(hasItem(DEFAULT_CONFIG_VAR_LONG_14.intValue())))
            .andExpect(jsonPath("$.[*].configVarLong15").value(hasItem(DEFAULT_CONFIG_VAR_LONG_15.intValue())))
            .andExpect(jsonPath("$.[*].configVarBoolean16").value(hasItem(DEFAULT_CONFIG_VAR_BOOLEAN_16.booleanValue())))
            .andExpect(jsonPath("$.[*].configVarBoolean17").value(hasItem(DEFAULT_CONFIG_VAR_BOOLEAN_17.booleanValue())))
            .andExpect(jsonPath("$.[*].configVarBoolean18").value(hasItem(DEFAULT_CONFIG_VAR_BOOLEAN_18.booleanValue())))
            .andExpect(jsonPath("$.[*].configVarString19").value(hasItem(DEFAULT_CONFIG_VAR_STRING_19)))
            .andExpect(jsonPath("$.[*].configVarString20").value(hasItem(DEFAULT_CONFIG_VAR_STRING_20)));

        // Check, that the count call also returns 1
        restConfigVariablesMockMvc.perform(get("/api/config-variables/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultConfigVariablesShouldNotBeFound(String filter) throws Exception {
        restConfigVariablesMockMvc.perform(get("/api/config-variables?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restConfigVariablesMockMvc.perform(get("/api/config-variables/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingConfigVariables() throws Exception {
        // Get the configVariables
        restConfigVariablesMockMvc.perform(get("/api/config-variables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfigVariables() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        int databaseSizeBeforeUpdate = configVariablesRepository.findAll().size();

        // Update the configVariables
        ConfigVariables updatedConfigVariables = configVariablesRepository.findById(configVariables.getId()).get();
        // Disconnect from session so that the updates on updatedConfigVariables are not directly saved in db
        em.detach(updatedConfigVariables);
        updatedConfigVariables
            .configVarLong1(UPDATED_CONFIG_VAR_LONG_1)
            .configVarLong2(UPDATED_CONFIG_VAR_LONG_2)
            .configVarLong3(UPDATED_CONFIG_VAR_LONG_3)
            .configVarLong4(UPDATED_CONFIG_VAR_LONG_4)
            .configVarLong5(UPDATED_CONFIG_VAR_LONG_5)
            .configVarLong6(UPDATED_CONFIG_VAR_LONG_6)
            .configVarLong7(UPDATED_CONFIG_VAR_LONG_7)
            .configVarLong8(UPDATED_CONFIG_VAR_LONG_8)
            .configVarLong9(UPDATED_CONFIG_VAR_LONG_9)
            .configVarLong10(UPDATED_CONFIG_VAR_LONG_10)
            .configVarLong11(UPDATED_CONFIG_VAR_LONG_11)
            .configVarLong12(UPDATED_CONFIG_VAR_LONG_12)
            .configVarLong13(UPDATED_CONFIG_VAR_LONG_13)
            .configVarLong14(UPDATED_CONFIG_VAR_LONG_14)
            .configVarLong15(UPDATED_CONFIG_VAR_LONG_15)
            .configVarBoolean16(UPDATED_CONFIG_VAR_BOOLEAN_16)
            .configVarBoolean17(UPDATED_CONFIG_VAR_BOOLEAN_17)
            .configVarBoolean18(UPDATED_CONFIG_VAR_BOOLEAN_18)
            .configVarString19(UPDATED_CONFIG_VAR_STRING_19)
            .configVarString20(UPDATED_CONFIG_VAR_STRING_20);
        ConfigVariablesDTO configVariablesDTO = configVariablesMapper.toDto(updatedConfigVariables);

        restConfigVariablesMockMvc.perform(put("/api/config-variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configVariablesDTO)))
            .andExpect(status().isOk());

        // Validate the ConfigVariables in the database
        List<ConfigVariables> configVariablesList = configVariablesRepository.findAll();
        assertThat(configVariablesList).hasSize(databaseSizeBeforeUpdate);
        ConfigVariables testConfigVariables = configVariablesList.get(configVariablesList.size() - 1);
        assertThat(testConfigVariables.getConfigVarLong1()).isEqualTo(UPDATED_CONFIG_VAR_LONG_1);
        assertThat(testConfigVariables.getConfigVarLong2()).isEqualTo(UPDATED_CONFIG_VAR_LONG_2);
        assertThat(testConfigVariables.getConfigVarLong3()).isEqualTo(UPDATED_CONFIG_VAR_LONG_3);
        assertThat(testConfigVariables.getConfigVarLong4()).isEqualTo(UPDATED_CONFIG_VAR_LONG_4);
        assertThat(testConfigVariables.getConfigVarLong5()).isEqualTo(UPDATED_CONFIG_VAR_LONG_5);
        assertThat(testConfigVariables.getConfigVarLong6()).isEqualTo(UPDATED_CONFIG_VAR_LONG_6);
        assertThat(testConfigVariables.getConfigVarLong7()).isEqualTo(UPDATED_CONFIG_VAR_LONG_7);
        assertThat(testConfigVariables.getConfigVarLong8()).isEqualTo(UPDATED_CONFIG_VAR_LONG_8);
        assertThat(testConfigVariables.getConfigVarLong9()).isEqualTo(UPDATED_CONFIG_VAR_LONG_9);
        assertThat(testConfigVariables.getConfigVarLong10()).isEqualTo(UPDATED_CONFIG_VAR_LONG_10);
        assertThat(testConfigVariables.getConfigVarLong11()).isEqualTo(UPDATED_CONFIG_VAR_LONG_11);
        assertThat(testConfigVariables.getConfigVarLong12()).isEqualTo(UPDATED_CONFIG_VAR_LONG_12);
        assertThat(testConfigVariables.getConfigVarLong13()).isEqualTo(UPDATED_CONFIG_VAR_LONG_13);
        assertThat(testConfigVariables.getConfigVarLong14()).isEqualTo(UPDATED_CONFIG_VAR_LONG_14);
        assertThat(testConfigVariables.getConfigVarLong15()).isEqualTo(UPDATED_CONFIG_VAR_LONG_15);
        assertThat(testConfigVariables.isConfigVarBoolean16()).isEqualTo(UPDATED_CONFIG_VAR_BOOLEAN_16);
        assertThat(testConfigVariables.isConfigVarBoolean17()).isEqualTo(UPDATED_CONFIG_VAR_BOOLEAN_17);
        assertThat(testConfigVariables.isConfigVarBoolean18()).isEqualTo(UPDATED_CONFIG_VAR_BOOLEAN_18);
        assertThat(testConfigVariables.getConfigVarString19()).isEqualTo(UPDATED_CONFIG_VAR_STRING_19);
        assertThat(testConfigVariables.getConfigVarString20()).isEqualTo(UPDATED_CONFIG_VAR_STRING_20);
    }

    @Test
    @Transactional
    public void updateNonExistingConfigVariables() throws Exception {
        int databaseSizeBeforeUpdate = configVariablesRepository.findAll().size();

        // Create the ConfigVariables
        ConfigVariablesDTO configVariablesDTO = configVariablesMapper.toDto(configVariables);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConfigVariablesMockMvc.perform(put("/api/config-variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configVariablesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConfigVariables in the database
        List<ConfigVariables> configVariablesList = configVariablesRepository.findAll();
        assertThat(configVariablesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConfigVariables() throws Exception {
        // Initialize the database
        configVariablesRepository.saveAndFlush(configVariables);

        int databaseSizeBeforeDelete = configVariablesRepository.findAll().size();

        // Delete the configVariables
        restConfigVariablesMockMvc.perform(delete("/api/config-variables/{id}", configVariables.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConfigVariables> configVariablesList = configVariablesRepository.findAll();
        assertThat(configVariablesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfigVariables.class);
        ConfigVariables configVariables1 = new ConfigVariables();
        configVariables1.setId(1L);
        ConfigVariables configVariables2 = new ConfigVariables();
        configVariables2.setId(configVariables1.getId());
        assertThat(configVariables1).isEqualTo(configVariables2);
        configVariables2.setId(2L);
        assertThat(configVariables1).isNotEqualTo(configVariables2);
        configVariables1.setId(null);
        assertThat(configVariables1).isNotEqualTo(configVariables2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfigVariablesDTO.class);
        ConfigVariablesDTO configVariablesDTO1 = new ConfigVariablesDTO();
        configVariablesDTO1.setId(1L);
        ConfigVariablesDTO configVariablesDTO2 = new ConfigVariablesDTO();
        assertThat(configVariablesDTO1).isNotEqualTo(configVariablesDTO2);
        configVariablesDTO2.setId(configVariablesDTO1.getId());
        assertThat(configVariablesDTO1).isEqualTo(configVariablesDTO2);
        configVariablesDTO2.setId(2L);
        assertThat(configVariablesDTO1).isNotEqualTo(configVariablesDTO2);
        configVariablesDTO1.setId(null);
        assertThat(configVariablesDTO1).isNotEqualTo(configVariablesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(configVariablesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(configVariablesMapper.fromId(null)).isNull();
    }
}
