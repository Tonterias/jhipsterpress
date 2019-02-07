package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Blockuser;
import com.jhipsterpress.web.domain.User;
import com.jhipsterpress.web.domain.Community;
import com.jhipsterpress.web.repository.BlockuserRepository;
import com.jhipsterpress.web.service.BlockuserService;
import com.jhipsterpress.web.service.dto.BlockuserDTO;
import com.jhipsterpress.web.service.mapper.BlockuserMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.BlockuserCriteria;
import com.jhipsterpress.web.service.BlockuserQueryService;

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
 * Test class for the BlockuserResource REST controller.
 *
 * @see BlockuserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class BlockuserResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BlockuserRepository blockuserRepository;

    @Autowired
    private BlockuserMapper blockuserMapper;

    @Autowired
    private BlockuserService blockuserService;

    @Autowired
    private BlockuserQueryService blockuserQueryService;

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

    private MockMvc restBlockuserMockMvc;

    private Blockuser blockuser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlockuserResource blockuserResource = new BlockuserResource(blockuserService, blockuserQueryService);
        this.restBlockuserMockMvc = MockMvcBuilders.standaloneSetup(blockuserResource)
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
    public static Blockuser createEntity(EntityManager em) {
        Blockuser blockuser = new Blockuser()
            .creationDate(DEFAULT_CREATION_DATE);
        return blockuser;
    }

    @Before
    public void initTest() {
        blockuser = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlockuser() throws Exception {
        int databaseSizeBeforeCreate = blockuserRepository.findAll().size();

        // Create the Blockuser
        BlockuserDTO blockuserDTO = blockuserMapper.toDto(blockuser);
        restBlockuserMockMvc.perform(post("/api/blockusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockuserDTO)))
            .andExpect(status().isCreated());

        // Validate the Blockuser in the database
        List<Blockuser> blockuserList = blockuserRepository.findAll();
        assertThat(blockuserList).hasSize(databaseSizeBeforeCreate + 1);
        Blockuser testBlockuser = blockuserList.get(blockuserList.size() - 1);
        assertThat(testBlockuser.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
    }

    @Test
    @Transactional
    public void createBlockuserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blockuserRepository.findAll().size();

        // Create the Blockuser with an existing ID
        blockuser.setId(1L);
        BlockuserDTO blockuserDTO = blockuserMapper.toDto(blockuser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlockuserMockMvc.perform(post("/api/blockusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockuserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Blockuser in the database
        List<Blockuser> blockuserList = blockuserRepository.findAll();
        assertThat(blockuserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBlockusers() throws Exception {
        // Initialize the database
        blockuserRepository.saveAndFlush(blockuser);

        // Get all the blockuserList
        restBlockuserMockMvc.perform(get("/api/blockusers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blockuser.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getBlockuser() throws Exception {
        // Initialize the database
        blockuserRepository.saveAndFlush(blockuser);

        // Get the blockuser
        restBlockuserMockMvc.perform(get("/api/blockusers/{id}", blockuser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blockuser.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getAllBlockusersByCreationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        blockuserRepository.saveAndFlush(blockuser);

        // Get all the blockuserList where creationDate equals to DEFAULT_CREATION_DATE
        defaultBlockuserShouldBeFound("creationDate.equals=" + DEFAULT_CREATION_DATE);

        // Get all the blockuserList where creationDate equals to UPDATED_CREATION_DATE
        defaultBlockuserShouldNotBeFound("creationDate.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllBlockusersByCreationDateIsInShouldWork() throws Exception {
        // Initialize the database
        blockuserRepository.saveAndFlush(blockuser);

        // Get all the blockuserList where creationDate in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultBlockuserShouldBeFound("creationDate.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the blockuserList where creationDate equals to UPDATED_CREATION_DATE
        defaultBlockuserShouldNotBeFound("creationDate.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllBlockusersByCreationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blockuserRepository.saveAndFlush(blockuser);

        // Get all the blockuserList where creationDate is not null
        defaultBlockuserShouldBeFound("creationDate.specified=true");

        // Get all the blockuserList where creationDate is null
        defaultBlockuserShouldNotBeFound("creationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlockusersByBlockeduserIsEqualToSomething() throws Exception {
        // Initialize the database
        User blockeduser = UserResourceIntTest.createEntity(em);
        em.persist(blockeduser);
        em.flush();
        blockuser.setBlockeduser(blockeduser);
        blockuserRepository.saveAndFlush(blockuser);
        Long blockeduserId = blockeduser.getId();

        // Get all the blockuserList where blockeduser equals to blockeduserId
        defaultBlockuserShouldBeFound("blockeduserId.equals=" + blockeduserId);

        // Get all the blockuserList where blockeduser equals to blockeduserId + 1
        defaultBlockuserShouldNotBeFound("blockeduserId.equals=" + (blockeduserId + 1));
    }


    @Test
    @Transactional
    public void getAllBlockusersByBlockinguserIsEqualToSomething() throws Exception {
        // Initialize the database
        User blockinguser = UserResourceIntTest.createEntity(em);
        em.persist(blockinguser);
        em.flush();
        blockuser.setBlockinguser(blockinguser);
        blockuserRepository.saveAndFlush(blockuser);
        Long blockinguserId = blockinguser.getId();

        // Get all the blockuserList where blockinguser equals to blockinguserId
        defaultBlockuserShouldBeFound("blockinguserId.equals=" + blockinguserId);

        // Get all the blockuserList where blockinguser equals to blockinguserId + 1
        defaultBlockuserShouldNotBeFound("blockinguserId.equals=" + (blockinguserId + 1));
    }


    @Test
    @Transactional
    public void getAllBlockusersByCblockeduserIsEqualToSomething() throws Exception {
        // Initialize the database
        Community cblockeduser = CommunityResourceIntTest.createEntity(em);
        em.persist(cblockeduser);
        em.flush();
        blockuser.setCblockeduser(cblockeduser);
        blockuserRepository.saveAndFlush(blockuser);
        Long cblockeduserId = cblockeduser.getId();

        // Get all the blockuserList where cblockeduser equals to cblockeduserId
        defaultBlockuserShouldBeFound("cblockeduserId.equals=" + cblockeduserId);

        // Get all the blockuserList where cblockeduser equals to cblockeduserId + 1
        defaultBlockuserShouldNotBeFound("cblockeduserId.equals=" + (cblockeduserId + 1));
    }


    @Test
    @Transactional
    public void getAllBlockusersByCblockinguserIsEqualToSomething() throws Exception {
        // Initialize the database
        Community cblockinguser = CommunityResourceIntTest.createEntity(em);
        em.persist(cblockinguser);
        em.flush();
        blockuser.setCblockinguser(cblockinguser);
        blockuserRepository.saveAndFlush(blockuser);
        Long cblockinguserId = cblockinguser.getId();

        // Get all the blockuserList where cblockinguser equals to cblockinguserId
        defaultBlockuserShouldBeFound("cblockinguserId.equals=" + cblockinguserId);

        // Get all the blockuserList where cblockinguser equals to cblockinguserId + 1
        defaultBlockuserShouldNotBeFound("cblockinguserId.equals=" + (cblockinguserId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultBlockuserShouldBeFound(String filter) throws Exception {
        restBlockuserMockMvc.perform(get("/api/blockusers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blockuser.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())));

        // Check, that the count call also returns 1
        restBlockuserMockMvc.perform(get("/api/blockusers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultBlockuserShouldNotBeFound(String filter) throws Exception {
        restBlockuserMockMvc.perform(get("/api/blockusers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBlockuserMockMvc.perform(get("/api/blockusers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBlockuser() throws Exception {
        // Get the blockuser
        restBlockuserMockMvc.perform(get("/api/blockusers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlockuser() throws Exception {
        // Initialize the database
        blockuserRepository.saveAndFlush(blockuser);

        int databaseSizeBeforeUpdate = blockuserRepository.findAll().size();

        // Update the blockuser
        Blockuser updatedBlockuser = blockuserRepository.findById(blockuser.getId()).get();
        // Disconnect from session so that the updates on updatedBlockuser are not directly saved in db
        em.detach(updatedBlockuser);
        updatedBlockuser
            .creationDate(UPDATED_CREATION_DATE);
        BlockuserDTO blockuserDTO = blockuserMapper.toDto(updatedBlockuser);

        restBlockuserMockMvc.perform(put("/api/blockusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockuserDTO)))
            .andExpect(status().isOk());

        // Validate the Blockuser in the database
        List<Blockuser> blockuserList = blockuserRepository.findAll();
        assertThat(blockuserList).hasSize(databaseSizeBeforeUpdate);
        Blockuser testBlockuser = blockuserList.get(blockuserList.size() - 1);
        assertThat(testBlockuser.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBlockuser() throws Exception {
        int databaseSizeBeforeUpdate = blockuserRepository.findAll().size();

        // Create the Blockuser
        BlockuserDTO blockuserDTO = blockuserMapper.toDto(blockuser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlockuserMockMvc.perform(put("/api/blockusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockuserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Blockuser in the database
        List<Blockuser> blockuserList = blockuserRepository.findAll();
        assertThat(blockuserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBlockuser() throws Exception {
        // Initialize the database
        blockuserRepository.saveAndFlush(blockuser);

        int databaseSizeBeforeDelete = blockuserRepository.findAll().size();

        // Delete the blockuser
        restBlockuserMockMvc.perform(delete("/api/blockusers/{id}", blockuser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Blockuser> blockuserList = blockuserRepository.findAll();
        assertThat(blockuserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Blockuser.class);
        Blockuser blockuser1 = new Blockuser();
        blockuser1.setId(1L);
        Blockuser blockuser2 = new Blockuser();
        blockuser2.setId(blockuser1.getId());
        assertThat(blockuser1).isEqualTo(blockuser2);
        blockuser2.setId(2L);
        assertThat(blockuser1).isNotEqualTo(blockuser2);
        blockuser1.setId(null);
        assertThat(blockuser1).isNotEqualTo(blockuser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlockuserDTO.class);
        BlockuserDTO blockuserDTO1 = new BlockuserDTO();
        blockuserDTO1.setId(1L);
        BlockuserDTO blockuserDTO2 = new BlockuserDTO();
        assertThat(blockuserDTO1).isNotEqualTo(blockuserDTO2);
        blockuserDTO2.setId(blockuserDTO1.getId());
        assertThat(blockuserDTO1).isEqualTo(blockuserDTO2);
        blockuserDTO2.setId(2L);
        assertThat(blockuserDTO1).isNotEqualTo(blockuserDTO2);
        blockuserDTO1.setId(null);
        assertThat(blockuserDTO1).isNotEqualTo(blockuserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(blockuserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(blockuserMapper.fromId(null)).isNull();
    }
}
