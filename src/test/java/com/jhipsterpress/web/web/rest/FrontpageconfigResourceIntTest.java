package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Frontpageconfig;
import com.jhipsterpress.web.repository.FrontpageconfigRepository;
import com.jhipsterpress.web.service.FrontpageconfigService;
import com.jhipsterpress.web.service.dto.FrontpageconfigDTO;
import com.jhipsterpress.web.service.mapper.FrontpageconfigMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.FrontpageconfigCriteria;
import com.jhipsterpress.web.service.FrontpageconfigQueryService;

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
 * Test class for the FrontpageconfigResource REST controller.
 *
 * @see FrontpageconfigResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class FrontpageconfigResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_TOP_NEWS_1 = 1L;
    private static final Long UPDATED_TOP_NEWS_1 = 2L;

    private static final Long DEFAULT_TOP_NEWS_2 = 1L;
    private static final Long UPDATED_TOP_NEWS_2 = 2L;

    private static final Long DEFAULT_TOP_NEWS_3 = 1L;
    private static final Long UPDATED_TOP_NEWS_3 = 2L;

    private static final Long DEFAULT_TOP_NEWS_4 = 1L;
    private static final Long UPDATED_TOP_NEWS_4 = 2L;

    private static final Long DEFAULT_TOP_NEWS_5 = 1L;
    private static final Long UPDATED_TOP_NEWS_5 = 2L;

    private static final Long DEFAULT_LATEST_NEWS_1 = 1L;
    private static final Long UPDATED_LATEST_NEWS_1 = 2L;

    private static final Long DEFAULT_LATEST_NEWS_2 = 1L;
    private static final Long UPDATED_LATEST_NEWS_2 = 2L;

    private static final Long DEFAULT_LATEST_NEWS_3 = 1L;
    private static final Long UPDATED_LATEST_NEWS_3 = 2L;

    private static final Long DEFAULT_LATEST_NEWS_4 = 1L;
    private static final Long UPDATED_LATEST_NEWS_4 = 2L;

    private static final Long DEFAULT_LATEST_NEWS_5 = 1L;
    private static final Long UPDATED_LATEST_NEWS_5 = 2L;

    private static final Long DEFAULT_BREAKING_NEWS_1 = 1L;
    private static final Long UPDATED_BREAKING_NEWS_1 = 2L;

    private static final Long DEFAULT_RECENT_POSTS_1 = 1L;
    private static final Long UPDATED_RECENT_POSTS_1 = 2L;

    private static final Long DEFAULT_RECENT_POSTS_2 = 1L;
    private static final Long UPDATED_RECENT_POSTS_2 = 2L;

    private static final Long DEFAULT_RECENT_POSTS_3 = 1L;
    private static final Long UPDATED_RECENT_POSTS_3 = 2L;

    private static final Long DEFAULT_RECENT_POSTS_4 = 1L;
    private static final Long UPDATED_RECENT_POSTS_4 = 2L;

    private static final Long DEFAULT_FEATURED_ARTICLES_1 = 1L;
    private static final Long UPDATED_FEATURED_ARTICLES_1 = 2L;

    private static final Long DEFAULT_FEATURED_ARTICLES_2 = 1L;
    private static final Long UPDATED_FEATURED_ARTICLES_2 = 2L;

    private static final Long DEFAULT_FEATURED_ARTICLES_3 = 1L;
    private static final Long UPDATED_FEATURED_ARTICLES_3 = 2L;

    private static final Long DEFAULT_FEATURED_ARTICLES_4 = 1L;
    private static final Long UPDATED_FEATURED_ARTICLES_4 = 2L;

    private static final Long DEFAULT_FEATURED_ARTICLES_5 = 1L;
    private static final Long UPDATED_FEATURED_ARTICLES_5 = 2L;

    private static final Long DEFAULT_FEATURED_ARTICLES_6 = 1L;
    private static final Long UPDATED_FEATURED_ARTICLES_6 = 2L;

    private static final Long DEFAULT_FEATURED_ARTICLES_7 = 1L;
    private static final Long UPDATED_FEATURED_ARTICLES_7 = 2L;

    private static final Long DEFAULT_FEATURED_ARTICLES_8 = 1L;
    private static final Long UPDATED_FEATURED_ARTICLES_8 = 2L;

    private static final Long DEFAULT_FEATURED_ARTICLES_9 = 1L;
    private static final Long UPDATED_FEATURED_ARTICLES_9 = 2L;

    private static final Long DEFAULT_FEATURED_ARTICLES_10 = 1L;
    private static final Long UPDATED_FEATURED_ARTICLES_10 = 2L;

    private static final Long DEFAULT_POPULAR_NEWS_1 = 1L;
    private static final Long UPDATED_POPULAR_NEWS_1 = 2L;

    private static final Long DEFAULT_POPULAR_NEWS_2 = 1L;
    private static final Long UPDATED_POPULAR_NEWS_2 = 2L;

    private static final Long DEFAULT_POPULAR_NEWS_3 = 1L;
    private static final Long UPDATED_POPULAR_NEWS_3 = 2L;

    private static final Long DEFAULT_POPULAR_NEWS_4 = 1L;
    private static final Long UPDATED_POPULAR_NEWS_4 = 2L;

    private static final Long DEFAULT_POPULAR_NEWS_5 = 1L;
    private static final Long UPDATED_POPULAR_NEWS_5 = 2L;

    private static final Long DEFAULT_POPULAR_NEWS_6 = 1L;
    private static final Long UPDATED_POPULAR_NEWS_6 = 2L;

    private static final Long DEFAULT_POPULAR_NEWS_7 = 1L;
    private static final Long UPDATED_POPULAR_NEWS_7 = 2L;

    private static final Long DEFAULT_POPULAR_NEWS_8 = 1L;
    private static final Long UPDATED_POPULAR_NEWS_8 = 2L;

    private static final Long DEFAULT_WEEKLY_NEWS_1 = 1L;
    private static final Long UPDATED_WEEKLY_NEWS_1 = 2L;

    private static final Long DEFAULT_WEEKLY_NEWS_2 = 1L;
    private static final Long UPDATED_WEEKLY_NEWS_2 = 2L;

    private static final Long DEFAULT_WEEKLY_NEWS_3 = 1L;
    private static final Long UPDATED_WEEKLY_NEWS_3 = 2L;

    private static final Long DEFAULT_WEEKLY_NEWS_4 = 1L;
    private static final Long UPDATED_WEEKLY_NEWS_4 = 2L;

    private static final Long DEFAULT_NEWS_FEEDS_1 = 1L;
    private static final Long UPDATED_NEWS_FEEDS_1 = 2L;

    private static final Long DEFAULT_NEWS_FEEDS_2 = 1L;
    private static final Long UPDATED_NEWS_FEEDS_2 = 2L;

    private static final Long DEFAULT_NEWS_FEEDS_3 = 1L;
    private static final Long UPDATED_NEWS_FEEDS_3 = 2L;

    private static final Long DEFAULT_NEWS_FEEDS_4 = 1L;
    private static final Long UPDATED_NEWS_FEEDS_4 = 2L;

    private static final Long DEFAULT_NEWS_FEEDS_5 = 1L;
    private static final Long UPDATED_NEWS_FEEDS_5 = 2L;

    private static final Long DEFAULT_NEWS_FEEDS_6 = 1L;
    private static final Long UPDATED_NEWS_FEEDS_6 = 2L;

    private static final Long DEFAULT_USEFUL_LINKS_1 = 1L;
    private static final Long UPDATED_USEFUL_LINKS_1 = 2L;

    private static final Long DEFAULT_USEFUL_LINKS_2 = 1L;
    private static final Long UPDATED_USEFUL_LINKS_2 = 2L;

    private static final Long DEFAULT_USEFUL_LINKS_3 = 1L;
    private static final Long UPDATED_USEFUL_LINKS_3 = 2L;

    private static final Long DEFAULT_USEFUL_LINKS_4 = 1L;
    private static final Long UPDATED_USEFUL_LINKS_4 = 2L;

    private static final Long DEFAULT_USEFUL_LINKS_5 = 1L;
    private static final Long UPDATED_USEFUL_LINKS_5 = 2L;

    private static final Long DEFAULT_USEFUL_LINKS_6 = 1L;
    private static final Long UPDATED_USEFUL_LINKS_6 = 2L;

    private static final Long DEFAULT_RECENT_VIDEOS_1 = 1L;
    private static final Long UPDATED_RECENT_VIDEOS_1 = 2L;

    private static final Long DEFAULT_RECENT_VIDEOS_2 = 1L;
    private static final Long UPDATED_RECENT_VIDEOS_2 = 2L;

    private static final Long DEFAULT_RECENT_VIDEOS_3 = 1L;
    private static final Long UPDATED_RECENT_VIDEOS_3 = 2L;

    private static final Long DEFAULT_RECENT_VIDEOS_4 = 1L;
    private static final Long UPDATED_RECENT_VIDEOS_4 = 2L;

    private static final Long DEFAULT_RECENT_VIDEOS_5 = 1L;
    private static final Long UPDATED_RECENT_VIDEOS_5 = 2L;

    private static final Long DEFAULT_RECENT_VIDEOS_6 = 1L;
    private static final Long UPDATED_RECENT_VIDEOS_6 = 2L;

    @Autowired
    private FrontpageconfigRepository frontpageconfigRepository;

    @Autowired
    private FrontpageconfigMapper frontpageconfigMapper;

    @Autowired
    private FrontpageconfigService frontpageconfigService;

    @Autowired
    private FrontpageconfigQueryService frontpageconfigQueryService;

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

    private MockMvc restFrontpageconfigMockMvc;

    private Frontpageconfig frontpageconfig;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FrontpageconfigResource frontpageconfigResource = new FrontpageconfigResource(frontpageconfigService, frontpageconfigQueryService);
        this.restFrontpageconfigMockMvc = MockMvcBuilders.standaloneSetup(frontpageconfigResource)
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
    public static Frontpageconfig createEntity(EntityManager em) {
        Frontpageconfig frontpageconfig = new Frontpageconfig()
            .creationDate(DEFAULT_CREATION_DATE)
            .topNews1(DEFAULT_TOP_NEWS_1)
            .topNews2(DEFAULT_TOP_NEWS_2)
            .topNews3(DEFAULT_TOP_NEWS_3)
            .topNews4(DEFAULT_TOP_NEWS_4)
            .topNews5(DEFAULT_TOP_NEWS_5)
            .latestNews1(DEFAULT_LATEST_NEWS_1)
            .latestNews2(DEFAULT_LATEST_NEWS_2)
            .latestNews3(DEFAULT_LATEST_NEWS_3)
            .latestNews4(DEFAULT_LATEST_NEWS_4)
            .latestNews5(DEFAULT_LATEST_NEWS_5)
            .breakingNews1(DEFAULT_BREAKING_NEWS_1)
            .recentPosts1(DEFAULT_RECENT_POSTS_1)
            .recentPosts2(DEFAULT_RECENT_POSTS_2)
            .recentPosts3(DEFAULT_RECENT_POSTS_3)
            .recentPosts4(DEFAULT_RECENT_POSTS_4)
            .featuredArticles1(DEFAULT_FEATURED_ARTICLES_1)
            .featuredArticles2(DEFAULT_FEATURED_ARTICLES_2)
            .featuredArticles3(DEFAULT_FEATURED_ARTICLES_3)
            .featuredArticles4(DEFAULT_FEATURED_ARTICLES_4)
            .featuredArticles5(DEFAULT_FEATURED_ARTICLES_5)
            .featuredArticles6(DEFAULT_FEATURED_ARTICLES_6)
            .featuredArticles7(DEFAULT_FEATURED_ARTICLES_7)
            .featuredArticles8(DEFAULT_FEATURED_ARTICLES_8)
            .featuredArticles9(DEFAULT_FEATURED_ARTICLES_9)
            .featuredArticles10(DEFAULT_FEATURED_ARTICLES_10)
            .popularNews1(DEFAULT_POPULAR_NEWS_1)
            .popularNews2(DEFAULT_POPULAR_NEWS_2)
            .popularNews3(DEFAULT_POPULAR_NEWS_3)
            .popularNews4(DEFAULT_POPULAR_NEWS_4)
            .popularNews5(DEFAULT_POPULAR_NEWS_5)
            .popularNews6(DEFAULT_POPULAR_NEWS_6)
            .popularNews7(DEFAULT_POPULAR_NEWS_7)
            .popularNews8(DEFAULT_POPULAR_NEWS_8)
            .weeklyNews1(DEFAULT_WEEKLY_NEWS_1)
            .weeklyNews2(DEFAULT_WEEKLY_NEWS_2)
            .weeklyNews3(DEFAULT_WEEKLY_NEWS_3)
            .weeklyNews4(DEFAULT_WEEKLY_NEWS_4)
            .newsFeeds1(DEFAULT_NEWS_FEEDS_1)
            .newsFeeds2(DEFAULT_NEWS_FEEDS_2)
            .newsFeeds3(DEFAULT_NEWS_FEEDS_3)
            .newsFeeds4(DEFAULT_NEWS_FEEDS_4)
            .newsFeeds5(DEFAULT_NEWS_FEEDS_5)
            .newsFeeds6(DEFAULT_NEWS_FEEDS_6)
            .usefulLinks1(DEFAULT_USEFUL_LINKS_1)
            .usefulLinks2(DEFAULT_USEFUL_LINKS_2)
            .usefulLinks3(DEFAULT_USEFUL_LINKS_3)
            .usefulLinks4(DEFAULT_USEFUL_LINKS_4)
            .usefulLinks5(DEFAULT_USEFUL_LINKS_5)
            .usefulLinks6(DEFAULT_USEFUL_LINKS_6)
            .recentVideos1(DEFAULT_RECENT_VIDEOS_1)
            .recentVideos2(DEFAULT_RECENT_VIDEOS_2)
            .recentVideos3(DEFAULT_RECENT_VIDEOS_3)
            .recentVideos4(DEFAULT_RECENT_VIDEOS_4)
            .recentVideos5(DEFAULT_RECENT_VIDEOS_5)
            .recentVideos6(DEFAULT_RECENT_VIDEOS_6);
        return frontpageconfig;
    }

    @Before
    public void initTest() {
        frontpageconfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createFrontpageconfig() throws Exception {
        int databaseSizeBeforeCreate = frontpageconfigRepository.findAll().size();

        // Create the Frontpageconfig
        FrontpageconfigDTO frontpageconfigDTO = frontpageconfigMapper.toDto(frontpageconfig);
        restFrontpageconfigMockMvc.perform(post("/api/frontpageconfigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frontpageconfigDTO)))
            .andExpect(status().isCreated());

        // Validate the Frontpageconfig in the database
        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeCreate + 1);
        Frontpageconfig testFrontpageconfig = frontpageconfigList.get(frontpageconfigList.size() - 1);
        assertThat(testFrontpageconfig.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testFrontpageconfig.getTopNews1()).isEqualTo(DEFAULT_TOP_NEWS_1);
        assertThat(testFrontpageconfig.getTopNews2()).isEqualTo(DEFAULT_TOP_NEWS_2);
        assertThat(testFrontpageconfig.getTopNews3()).isEqualTo(DEFAULT_TOP_NEWS_3);
        assertThat(testFrontpageconfig.getTopNews4()).isEqualTo(DEFAULT_TOP_NEWS_4);
        assertThat(testFrontpageconfig.getTopNews5()).isEqualTo(DEFAULT_TOP_NEWS_5);
        assertThat(testFrontpageconfig.getLatestNews1()).isEqualTo(DEFAULT_LATEST_NEWS_1);
        assertThat(testFrontpageconfig.getLatestNews2()).isEqualTo(DEFAULT_LATEST_NEWS_2);
        assertThat(testFrontpageconfig.getLatestNews3()).isEqualTo(DEFAULT_LATEST_NEWS_3);
        assertThat(testFrontpageconfig.getLatestNews4()).isEqualTo(DEFAULT_LATEST_NEWS_4);
        assertThat(testFrontpageconfig.getLatestNews5()).isEqualTo(DEFAULT_LATEST_NEWS_5);
        assertThat(testFrontpageconfig.getBreakingNews1()).isEqualTo(DEFAULT_BREAKING_NEWS_1);
        assertThat(testFrontpageconfig.getRecentPosts1()).isEqualTo(DEFAULT_RECENT_POSTS_1);
        assertThat(testFrontpageconfig.getRecentPosts2()).isEqualTo(DEFAULT_RECENT_POSTS_2);
        assertThat(testFrontpageconfig.getRecentPosts3()).isEqualTo(DEFAULT_RECENT_POSTS_3);
        assertThat(testFrontpageconfig.getRecentPosts4()).isEqualTo(DEFAULT_RECENT_POSTS_4);
        assertThat(testFrontpageconfig.getFeaturedArticles1()).isEqualTo(DEFAULT_FEATURED_ARTICLES_1);
        assertThat(testFrontpageconfig.getFeaturedArticles2()).isEqualTo(DEFAULT_FEATURED_ARTICLES_2);
        assertThat(testFrontpageconfig.getFeaturedArticles3()).isEqualTo(DEFAULT_FEATURED_ARTICLES_3);
        assertThat(testFrontpageconfig.getFeaturedArticles4()).isEqualTo(DEFAULT_FEATURED_ARTICLES_4);
        assertThat(testFrontpageconfig.getFeaturedArticles5()).isEqualTo(DEFAULT_FEATURED_ARTICLES_5);
        assertThat(testFrontpageconfig.getFeaturedArticles6()).isEqualTo(DEFAULT_FEATURED_ARTICLES_6);
        assertThat(testFrontpageconfig.getFeaturedArticles7()).isEqualTo(DEFAULT_FEATURED_ARTICLES_7);
        assertThat(testFrontpageconfig.getFeaturedArticles8()).isEqualTo(DEFAULT_FEATURED_ARTICLES_8);
        assertThat(testFrontpageconfig.getFeaturedArticles9()).isEqualTo(DEFAULT_FEATURED_ARTICLES_9);
        assertThat(testFrontpageconfig.getFeaturedArticles10()).isEqualTo(DEFAULT_FEATURED_ARTICLES_10);
        assertThat(testFrontpageconfig.getPopularNews1()).isEqualTo(DEFAULT_POPULAR_NEWS_1);
        assertThat(testFrontpageconfig.getPopularNews2()).isEqualTo(DEFAULT_POPULAR_NEWS_2);
        assertThat(testFrontpageconfig.getPopularNews3()).isEqualTo(DEFAULT_POPULAR_NEWS_3);
        assertThat(testFrontpageconfig.getPopularNews4()).isEqualTo(DEFAULT_POPULAR_NEWS_4);
        assertThat(testFrontpageconfig.getPopularNews5()).isEqualTo(DEFAULT_POPULAR_NEWS_5);
        assertThat(testFrontpageconfig.getPopularNews6()).isEqualTo(DEFAULT_POPULAR_NEWS_6);
        assertThat(testFrontpageconfig.getPopularNews7()).isEqualTo(DEFAULT_POPULAR_NEWS_7);
        assertThat(testFrontpageconfig.getPopularNews8()).isEqualTo(DEFAULT_POPULAR_NEWS_8);
        assertThat(testFrontpageconfig.getWeeklyNews1()).isEqualTo(DEFAULT_WEEKLY_NEWS_1);
        assertThat(testFrontpageconfig.getWeeklyNews2()).isEqualTo(DEFAULT_WEEKLY_NEWS_2);
        assertThat(testFrontpageconfig.getWeeklyNews3()).isEqualTo(DEFAULT_WEEKLY_NEWS_3);
        assertThat(testFrontpageconfig.getWeeklyNews4()).isEqualTo(DEFAULT_WEEKLY_NEWS_4);
        assertThat(testFrontpageconfig.getNewsFeeds1()).isEqualTo(DEFAULT_NEWS_FEEDS_1);
        assertThat(testFrontpageconfig.getNewsFeeds2()).isEqualTo(DEFAULT_NEWS_FEEDS_2);
        assertThat(testFrontpageconfig.getNewsFeeds3()).isEqualTo(DEFAULT_NEWS_FEEDS_3);
        assertThat(testFrontpageconfig.getNewsFeeds4()).isEqualTo(DEFAULT_NEWS_FEEDS_4);
        assertThat(testFrontpageconfig.getNewsFeeds5()).isEqualTo(DEFAULT_NEWS_FEEDS_5);
        assertThat(testFrontpageconfig.getNewsFeeds6()).isEqualTo(DEFAULT_NEWS_FEEDS_6);
        assertThat(testFrontpageconfig.getUsefulLinks1()).isEqualTo(DEFAULT_USEFUL_LINKS_1);
        assertThat(testFrontpageconfig.getUsefulLinks2()).isEqualTo(DEFAULT_USEFUL_LINKS_2);
        assertThat(testFrontpageconfig.getUsefulLinks3()).isEqualTo(DEFAULT_USEFUL_LINKS_3);
        assertThat(testFrontpageconfig.getUsefulLinks4()).isEqualTo(DEFAULT_USEFUL_LINKS_4);
        assertThat(testFrontpageconfig.getUsefulLinks5()).isEqualTo(DEFAULT_USEFUL_LINKS_5);
        assertThat(testFrontpageconfig.getUsefulLinks6()).isEqualTo(DEFAULT_USEFUL_LINKS_6);
        assertThat(testFrontpageconfig.getRecentVideos1()).isEqualTo(DEFAULT_RECENT_VIDEOS_1);
        assertThat(testFrontpageconfig.getRecentVideos2()).isEqualTo(DEFAULT_RECENT_VIDEOS_2);
        assertThat(testFrontpageconfig.getRecentVideos3()).isEqualTo(DEFAULT_RECENT_VIDEOS_3);
        assertThat(testFrontpageconfig.getRecentVideos4()).isEqualTo(DEFAULT_RECENT_VIDEOS_4);
        assertThat(testFrontpageconfig.getRecentVideos5()).isEqualTo(DEFAULT_RECENT_VIDEOS_5);
        assertThat(testFrontpageconfig.getRecentVideos6()).isEqualTo(DEFAULT_RECENT_VIDEOS_6);
    }

    @Test
    @Transactional
    public void createFrontpageconfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = frontpageconfigRepository.findAll().size();

        // Create the Frontpageconfig with an existing ID
        frontpageconfig.setId(1L);
        FrontpageconfigDTO frontpageconfigDTO = frontpageconfigMapper.toDto(frontpageconfig);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFrontpageconfigMockMvc.perform(post("/api/frontpageconfigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frontpageconfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Frontpageconfig in the database
        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = frontpageconfigRepository.findAll().size();
        // set the field null
        frontpageconfig.setCreationDate(null);

        // Create the Frontpageconfig, which fails.
        FrontpageconfigDTO frontpageconfigDTO = frontpageconfigMapper.toDto(frontpageconfig);

        restFrontpageconfigMockMvc.perform(post("/api/frontpageconfigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frontpageconfigDTO)))
            .andExpect(status().isBadRequest());

        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigs() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList
        restFrontpageconfigMockMvc.perform(get("/api/frontpageconfigs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(frontpageconfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].topNews1").value(hasItem(DEFAULT_TOP_NEWS_1.intValue())))
            .andExpect(jsonPath("$.[*].topNews2").value(hasItem(DEFAULT_TOP_NEWS_2.intValue())))
            .andExpect(jsonPath("$.[*].topNews3").value(hasItem(DEFAULT_TOP_NEWS_3.intValue())))
            .andExpect(jsonPath("$.[*].topNews4").value(hasItem(DEFAULT_TOP_NEWS_4.intValue())))
            .andExpect(jsonPath("$.[*].topNews5").value(hasItem(DEFAULT_TOP_NEWS_5.intValue())))
            .andExpect(jsonPath("$.[*].latestNews1").value(hasItem(DEFAULT_LATEST_NEWS_1.intValue())))
            .andExpect(jsonPath("$.[*].latestNews2").value(hasItem(DEFAULT_LATEST_NEWS_2.intValue())))
            .andExpect(jsonPath("$.[*].latestNews3").value(hasItem(DEFAULT_LATEST_NEWS_3.intValue())))
            .andExpect(jsonPath("$.[*].latestNews4").value(hasItem(DEFAULT_LATEST_NEWS_4.intValue())))
            .andExpect(jsonPath("$.[*].latestNews5").value(hasItem(DEFAULT_LATEST_NEWS_5.intValue())))
            .andExpect(jsonPath("$.[*].breakingNews1").value(hasItem(DEFAULT_BREAKING_NEWS_1.intValue())))
            .andExpect(jsonPath("$.[*].recentPosts1").value(hasItem(DEFAULT_RECENT_POSTS_1.intValue())))
            .andExpect(jsonPath("$.[*].recentPosts2").value(hasItem(DEFAULT_RECENT_POSTS_2.intValue())))
            .andExpect(jsonPath("$.[*].recentPosts3").value(hasItem(DEFAULT_RECENT_POSTS_3.intValue())))
            .andExpect(jsonPath("$.[*].recentPosts4").value(hasItem(DEFAULT_RECENT_POSTS_4.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles1").value(hasItem(DEFAULT_FEATURED_ARTICLES_1.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles2").value(hasItem(DEFAULT_FEATURED_ARTICLES_2.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles3").value(hasItem(DEFAULT_FEATURED_ARTICLES_3.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles4").value(hasItem(DEFAULT_FEATURED_ARTICLES_4.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles5").value(hasItem(DEFAULT_FEATURED_ARTICLES_5.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles6").value(hasItem(DEFAULT_FEATURED_ARTICLES_6.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles7").value(hasItem(DEFAULT_FEATURED_ARTICLES_7.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles8").value(hasItem(DEFAULT_FEATURED_ARTICLES_8.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles9").value(hasItem(DEFAULT_FEATURED_ARTICLES_9.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles10").value(hasItem(DEFAULT_FEATURED_ARTICLES_10.intValue())))
            .andExpect(jsonPath("$.[*].popularNews1").value(hasItem(DEFAULT_POPULAR_NEWS_1.intValue())))
            .andExpect(jsonPath("$.[*].popularNews2").value(hasItem(DEFAULT_POPULAR_NEWS_2.intValue())))
            .andExpect(jsonPath("$.[*].popularNews3").value(hasItem(DEFAULT_POPULAR_NEWS_3.intValue())))
            .andExpect(jsonPath("$.[*].popularNews4").value(hasItem(DEFAULT_POPULAR_NEWS_4.intValue())))
            .andExpect(jsonPath("$.[*].popularNews5").value(hasItem(DEFAULT_POPULAR_NEWS_5.intValue())))
            .andExpect(jsonPath("$.[*].popularNews6").value(hasItem(DEFAULT_POPULAR_NEWS_6.intValue())))
            .andExpect(jsonPath("$.[*].popularNews7").value(hasItem(DEFAULT_POPULAR_NEWS_7.intValue())))
            .andExpect(jsonPath("$.[*].popularNews8").value(hasItem(DEFAULT_POPULAR_NEWS_8.intValue())))
            .andExpect(jsonPath("$.[*].weeklyNews1").value(hasItem(DEFAULT_WEEKLY_NEWS_1.intValue())))
            .andExpect(jsonPath("$.[*].weeklyNews2").value(hasItem(DEFAULT_WEEKLY_NEWS_2.intValue())))
            .andExpect(jsonPath("$.[*].weeklyNews3").value(hasItem(DEFAULT_WEEKLY_NEWS_3.intValue())))
            .andExpect(jsonPath("$.[*].weeklyNews4").value(hasItem(DEFAULT_WEEKLY_NEWS_4.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds1").value(hasItem(DEFAULT_NEWS_FEEDS_1.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds2").value(hasItem(DEFAULT_NEWS_FEEDS_2.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds3").value(hasItem(DEFAULT_NEWS_FEEDS_3.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds4").value(hasItem(DEFAULT_NEWS_FEEDS_4.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds5").value(hasItem(DEFAULT_NEWS_FEEDS_5.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds6").value(hasItem(DEFAULT_NEWS_FEEDS_6.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks1").value(hasItem(DEFAULT_USEFUL_LINKS_1.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks2").value(hasItem(DEFAULT_USEFUL_LINKS_2.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks3").value(hasItem(DEFAULT_USEFUL_LINKS_3.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks4").value(hasItem(DEFAULT_USEFUL_LINKS_4.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks5").value(hasItem(DEFAULT_USEFUL_LINKS_5.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks6").value(hasItem(DEFAULT_USEFUL_LINKS_6.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos1").value(hasItem(DEFAULT_RECENT_VIDEOS_1.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos2").value(hasItem(DEFAULT_RECENT_VIDEOS_2.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos3").value(hasItem(DEFAULT_RECENT_VIDEOS_3.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos4").value(hasItem(DEFAULT_RECENT_VIDEOS_4.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos5").value(hasItem(DEFAULT_RECENT_VIDEOS_5.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos6").value(hasItem(DEFAULT_RECENT_VIDEOS_6.intValue())));
    }
    
    @Test
    @Transactional
    public void getFrontpageconfig() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get the frontpageconfig
        restFrontpageconfigMockMvc.perform(get("/api/frontpageconfigs/{id}", frontpageconfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(frontpageconfig.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.topNews1").value(DEFAULT_TOP_NEWS_1.intValue()))
            .andExpect(jsonPath("$.topNews2").value(DEFAULT_TOP_NEWS_2.intValue()))
            .andExpect(jsonPath("$.topNews3").value(DEFAULT_TOP_NEWS_3.intValue()))
            .andExpect(jsonPath("$.topNews4").value(DEFAULT_TOP_NEWS_4.intValue()))
            .andExpect(jsonPath("$.topNews5").value(DEFAULT_TOP_NEWS_5.intValue()))
            .andExpect(jsonPath("$.latestNews1").value(DEFAULT_LATEST_NEWS_1.intValue()))
            .andExpect(jsonPath("$.latestNews2").value(DEFAULT_LATEST_NEWS_2.intValue()))
            .andExpect(jsonPath("$.latestNews3").value(DEFAULT_LATEST_NEWS_3.intValue()))
            .andExpect(jsonPath("$.latestNews4").value(DEFAULT_LATEST_NEWS_4.intValue()))
            .andExpect(jsonPath("$.latestNews5").value(DEFAULT_LATEST_NEWS_5.intValue()))
            .andExpect(jsonPath("$.breakingNews1").value(DEFAULT_BREAKING_NEWS_1.intValue()))
            .andExpect(jsonPath("$.recentPosts1").value(DEFAULT_RECENT_POSTS_1.intValue()))
            .andExpect(jsonPath("$.recentPosts2").value(DEFAULT_RECENT_POSTS_2.intValue()))
            .andExpect(jsonPath("$.recentPosts3").value(DEFAULT_RECENT_POSTS_3.intValue()))
            .andExpect(jsonPath("$.recentPosts4").value(DEFAULT_RECENT_POSTS_4.intValue()))
            .andExpect(jsonPath("$.featuredArticles1").value(DEFAULT_FEATURED_ARTICLES_1.intValue()))
            .andExpect(jsonPath("$.featuredArticles2").value(DEFAULT_FEATURED_ARTICLES_2.intValue()))
            .andExpect(jsonPath("$.featuredArticles3").value(DEFAULT_FEATURED_ARTICLES_3.intValue()))
            .andExpect(jsonPath("$.featuredArticles4").value(DEFAULT_FEATURED_ARTICLES_4.intValue()))
            .andExpect(jsonPath("$.featuredArticles5").value(DEFAULT_FEATURED_ARTICLES_5.intValue()))
            .andExpect(jsonPath("$.featuredArticles6").value(DEFAULT_FEATURED_ARTICLES_6.intValue()))
            .andExpect(jsonPath("$.featuredArticles7").value(DEFAULT_FEATURED_ARTICLES_7.intValue()))
            .andExpect(jsonPath("$.featuredArticles8").value(DEFAULT_FEATURED_ARTICLES_8.intValue()))
            .andExpect(jsonPath("$.featuredArticles9").value(DEFAULT_FEATURED_ARTICLES_9.intValue()))
            .andExpect(jsonPath("$.featuredArticles10").value(DEFAULT_FEATURED_ARTICLES_10.intValue()))
            .andExpect(jsonPath("$.popularNews1").value(DEFAULT_POPULAR_NEWS_1.intValue()))
            .andExpect(jsonPath("$.popularNews2").value(DEFAULT_POPULAR_NEWS_2.intValue()))
            .andExpect(jsonPath("$.popularNews3").value(DEFAULT_POPULAR_NEWS_3.intValue()))
            .andExpect(jsonPath("$.popularNews4").value(DEFAULT_POPULAR_NEWS_4.intValue()))
            .andExpect(jsonPath("$.popularNews5").value(DEFAULT_POPULAR_NEWS_5.intValue()))
            .andExpect(jsonPath("$.popularNews6").value(DEFAULT_POPULAR_NEWS_6.intValue()))
            .andExpect(jsonPath("$.popularNews7").value(DEFAULT_POPULAR_NEWS_7.intValue()))
            .andExpect(jsonPath("$.popularNews8").value(DEFAULT_POPULAR_NEWS_8.intValue()))
            .andExpect(jsonPath("$.weeklyNews1").value(DEFAULT_WEEKLY_NEWS_1.intValue()))
            .andExpect(jsonPath("$.weeklyNews2").value(DEFAULT_WEEKLY_NEWS_2.intValue()))
            .andExpect(jsonPath("$.weeklyNews3").value(DEFAULT_WEEKLY_NEWS_3.intValue()))
            .andExpect(jsonPath("$.weeklyNews4").value(DEFAULT_WEEKLY_NEWS_4.intValue()))
            .andExpect(jsonPath("$.newsFeeds1").value(DEFAULT_NEWS_FEEDS_1.intValue()))
            .andExpect(jsonPath("$.newsFeeds2").value(DEFAULT_NEWS_FEEDS_2.intValue()))
            .andExpect(jsonPath("$.newsFeeds3").value(DEFAULT_NEWS_FEEDS_3.intValue()))
            .andExpect(jsonPath("$.newsFeeds4").value(DEFAULT_NEWS_FEEDS_4.intValue()))
            .andExpect(jsonPath("$.newsFeeds5").value(DEFAULT_NEWS_FEEDS_5.intValue()))
            .andExpect(jsonPath("$.newsFeeds6").value(DEFAULT_NEWS_FEEDS_6.intValue()))
            .andExpect(jsonPath("$.usefulLinks1").value(DEFAULT_USEFUL_LINKS_1.intValue()))
            .andExpect(jsonPath("$.usefulLinks2").value(DEFAULT_USEFUL_LINKS_2.intValue()))
            .andExpect(jsonPath("$.usefulLinks3").value(DEFAULT_USEFUL_LINKS_3.intValue()))
            .andExpect(jsonPath("$.usefulLinks4").value(DEFAULT_USEFUL_LINKS_4.intValue()))
            .andExpect(jsonPath("$.usefulLinks5").value(DEFAULT_USEFUL_LINKS_5.intValue()))
            .andExpect(jsonPath("$.usefulLinks6").value(DEFAULT_USEFUL_LINKS_6.intValue()))
            .andExpect(jsonPath("$.recentVideos1").value(DEFAULT_RECENT_VIDEOS_1.intValue()))
            .andExpect(jsonPath("$.recentVideos2").value(DEFAULT_RECENT_VIDEOS_2.intValue()))
            .andExpect(jsonPath("$.recentVideos3").value(DEFAULT_RECENT_VIDEOS_3.intValue()))
            .andExpect(jsonPath("$.recentVideos4").value(DEFAULT_RECENT_VIDEOS_4.intValue()))
            .andExpect(jsonPath("$.recentVideos5").value(DEFAULT_RECENT_VIDEOS_5.intValue()))
            .andExpect(jsonPath("$.recentVideos6").value(DEFAULT_RECENT_VIDEOS_6.intValue()));
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByCreationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where creationDate equals to DEFAULT_CREATION_DATE
        defaultFrontpageconfigShouldBeFound("creationDate.equals=" + DEFAULT_CREATION_DATE);

        // Get all the frontpageconfigList where creationDate equals to UPDATED_CREATION_DATE
        defaultFrontpageconfigShouldNotBeFound("creationDate.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByCreationDateIsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where creationDate in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultFrontpageconfigShouldBeFound("creationDate.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the frontpageconfigList where creationDate equals to UPDATED_CREATION_DATE
        defaultFrontpageconfigShouldNotBeFound("creationDate.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByCreationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where creationDate is not null
        defaultFrontpageconfigShouldBeFound("creationDate.specified=true");

        // Get all the frontpageconfigList where creationDate is null
        defaultFrontpageconfigShouldNotBeFound("creationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews1IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews1 equals to DEFAULT_TOP_NEWS_1
        defaultFrontpageconfigShouldBeFound("topNews1.equals=" + DEFAULT_TOP_NEWS_1);

        // Get all the frontpageconfigList where topNews1 equals to UPDATED_TOP_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("topNews1.equals=" + UPDATED_TOP_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews1IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews1 in DEFAULT_TOP_NEWS_1 or UPDATED_TOP_NEWS_1
        defaultFrontpageconfigShouldBeFound("topNews1.in=" + DEFAULT_TOP_NEWS_1 + "," + UPDATED_TOP_NEWS_1);

        // Get all the frontpageconfigList where topNews1 equals to UPDATED_TOP_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("topNews1.in=" + UPDATED_TOP_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews1IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews1 is not null
        defaultFrontpageconfigShouldBeFound("topNews1.specified=true");

        // Get all the frontpageconfigList where topNews1 is null
        defaultFrontpageconfigShouldNotBeFound("topNews1.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews1 greater than or equals to DEFAULT_TOP_NEWS_1
        defaultFrontpageconfigShouldBeFound("topNews1.greaterOrEqualThan=" + DEFAULT_TOP_NEWS_1);

        // Get all the frontpageconfigList where topNews1 greater than or equals to UPDATED_TOP_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("topNews1.greaterOrEqualThan=" + UPDATED_TOP_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews1IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews1 less than or equals to DEFAULT_TOP_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("topNews1.lessThan=" + DEFAULT_TOP_NEWS_1);

        // Get all the frontpageconfigList where topNews1 less than or equals to UPDATED_TOP_NEWS_1
        defaultFrontpageconfigShouldBeFound("topNews1.lessThan=" + UPDATED_TOP_NEWS_1);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews2IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews2 equals to DEFAULT_TOP_NEWS_2
        defaultFrontpageconfigShouldBeFound("topNews2.equals=" + DEFAULT_TOP_NEWS_2);

        // Get all the frontpageconfigList where topNews2 equals to UPDATED_TOP_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("topNews2.equals=" + UPDATED_TOP_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews2IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews2 in DEFAULT_TOP_NEWS_2 or UPDATED_TOP_NEWS_2
        defaultFrontpageconfigShouldBeFound("topNews2.in=" + DEFAULT_TOP_NEWS_2 + "," + UPDATED_TOP_NEWS_2);

        // Get all the frontpageconfigList where topNews2 equals to UPDATED_TOP_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("topNews2.in=" + UPDATED_TOP_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews2IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews2 is not null
        defaultFrontpageconfigShouldBeFound("topNews2.specified=true");

        // Get all the frontpageconfigList where topNews2 is null
        defaultFrontpageconfigShouldNotBeFound("topNews2.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews2 greater than or equals to DEFAULT_TOP_NEWS_2
        defaultFrontpageconfigShouldBeFound("topNews2.greaterOrEqualThan=" + DEFAULT_TOP_NEWS_2);

        // Get all the frontpageconfigList where topNews2 greater than or equals to UPDATED_TOP_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("topNews2.greaterOrEqualThan=" + UPDATED_TOP_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews2IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews2 less than or equals to DEFAULT_TOP_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("topNews2.lessThan=" + DEFAULT_TOP_NEWS_2);

        // Get all the frontpageconfigList where topNews2 less than or equals to UPDATED_TOP_NEWS_2
        defaultFrontpageconfigShouldBeFound("topNews2.lessThan=" + UPDATED_TOP_NEWS_2);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews3IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews3 equals to DEFAULT_TOP_NEWS_3
        defaultFrontpageconfigShouldBeFound("topNews3.equals=" + DEFAULT_TOP_NEWS_3);

        // Get all the frontpageconfigList where topNews3 equals to UPDATED_TOP_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("topNews3.equals=" + UPDATED_TOP_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews3IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews3 in DEFAULT_TOP_NEWS_3 or UPDATED_TOP_NEWS_3
        defaultFrontpageconfigShouldBeFound("topNews3.in=" + DEFAULT_TOP_NEWS_3 + "," + UPDATED_TOP_NEWS_3);

        // Get all the frontpageconfigList where topNews3 equals to UPDATED_TOP_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("topNews3.in=" + UPDATED_TOP_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews3IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews3 is not null
        defaultFrontpageconfigShouldBeFound("topNews3.specified=true");

        // Get all the frontpageconfigList where topNews3 is null
        defaultFrontpageconfigShouldNotBeFound("topNews3.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews3 greater than or equals to DEFAULT_TOP_NEWS_3
        defaultFrontpageconfigShouldBeFound("topNews3.greaterOrEqualThan=" + DEFAULT_TOP_NEWS_3);

        // Get all the frontpageconfigList where topNews3 greater than or equals to UPDATED_TOP_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("topNews3.greaterOrEqualThan=" + UPDATED_TOP_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews3IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews3 less than or equals to DEFAULT_TOP_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("topNews3.lessThan=" + DEFAULT_TOP_NEWS_3);

        // Get all the frontpageconfigList where topNews3 less than or equals to UPDATED_TOP_NEWS_3
        defaultFrontpageconfigShouldBeFound("topNews3.lessThan=" + UPDATED_TOP_NEWS_3);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews4IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews4 equals to DEFAULT_TOP_NEWS_4
        defaultFrontpageconfigShouldBeFound("topNews4.equals=" + DEFAULT_TOP_NEWS_4);

        // Get all the frontpageconfigList where topNews4 equals to UPDATED_TOP_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("topNews4.equals=" + UPDATED_TOP_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews4IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews4 in DEFAULT_TOP_NEWS_4 or UPDATED_TOP_NEWS_4
        defaultFrontpageconfigShouldBeFound("topNews4.in=" + DEFAULT_TOP_NEWS_4 + "," + UPDATED_TOP_NEWS_4);

        // Get all the frontpageconfigList where topNews4 equals to UPDATED_TOP_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("topNews4.in=" + UPDATED_TOP_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews4IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews4 is not null
        defaultFrontpageconfigShouldBeFound("topNews4.specified=true");

        // Get all the frontpageconfigList where topNews4 is null
        defaultFrontpageconfigShouldNotBeFound("topNews4.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews4 greater than or equals to DEFAULT_TOP_NEWS_4
        defaultFrontpageconfigShouldBeFound("topNews4.greaterOrEqualThan=" + DEFAULT_TOP_NEWS_4);

        // Get all the frontpageconfigList where topNews4 greater than or equals to UPDATED_TOP_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("topNews4.greaterOrEqualThan=" + UPDATED_TOP_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews4IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews4 less than or equals to DEFAULT_TOP_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("topNews4.lessThan=" + DEFAULT_TOP_NEWS_4);

        // Get all the frontpageconfigList where topNews4 less than or equals to UPDATED_TOP_NEWS_4
        defaultFrontpageconfigShouldBeFound("topNews4.lessThan=" + UPDATED_TOP_NEWS_4);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews5IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews5 equals to DEFAULT_TOP_NEWS_5
        defaultFrontpageconfigShouldBeFound("topNews5.equals=" + DEFAULT_TOP_NEWS_5);

        // Get all the frontpageconfigList where topNews5 equals to UPDATED_TOP_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("topNews5.equals=" + UPDATED_TOP_NEWS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews5IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews5 in DEFAULT_TOP_NEWS_5 or UPDATED_TOP_NEWS_5
        defaultFrontpageconfigShouldBeFound("topNews5.in=" + DEFAULT_TOP_NEWS_5 + "," + UPDATED_TOP_NEWS_5);

        // Get all the frontpageconfigList where topNews5 equals to UPDATED_TOP_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("topNews5.in=" + UPDATED_TOP_NEWS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews5IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews5 is not null
        defaultFrontpageconfigShouldBeFound("topNews5.specified=true");

        // Get all the frontpageconfigList where topNews5 is null
        defaultFrontpageconfigShouldNotBeFound("topNews5.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews5 greater than or equals to DEFAULT_TOP_NEWS_5
        defaultFrontpageconfigShouldBeFound("topNews5.greaterOrEqualThan=" + DEFAULT_TOP_NEWS_5);

        // Get all the frontpageconfigList where topNews5 greater than or equals to UPDATED_TOP_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("topNews5.greaterOrEqualThan=" + UPDATED_TOP_NEWS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByTopNews5IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where topNews5 less than or equals to DEFAULT_TOP_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("topNews5.lessThan=" + DEFAULT_TOP_NEWS_5);

        // Get all the frontpageconfigList where topNews5 less than or equals to UPDATED_TOP_NEWS_5
        defaultFrontpageconfigShouldBeFound("topNews5.lessThan=" + UPDATED_TOP_NEWS_5);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews1IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews1 equals to DEFAULT_LATEST_NEWS_1
        defaultFrontpageconfigShouldBeFound("latestNews1.equals=" + DEFAULT_LATEST_NEWS_1);

        // Get all the frontpageconfigList where latestNews1 equals to UPDATED_LATEST_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("latestNews1.equals=" + UPDATED_LATEST_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews1IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews1 in DEFAULT_LATEST_NEWS_1 or UPDATED_LATEST_NEWS_1
        defaultFrontpageconfigShouldBeFound("latestNews1.in=" + DEFAULT_LATEST_NEWS_1 + "," + UPDATED_LATEST_NEWS_1);

        // Get all the frontpageconfigList where latestNews1 equals to UPDATED_LATEST_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("latestNews1.in=" + UPDATED_LATEST_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews1IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews1 is not null
        defaultFrontpageconfigShouldBeFound("latestNews1.specified=true");

        // Get all the frontpageconfigList where latestNews1 is null
        defaultFrontpageconfigShouldNotBeFound("latestNews1.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews1 greater than or equals to DEFAULT_LATEST_NEWS_1
        defaultFrontpageconfigShouldBeFound("latestNews1.greaterOrEqualThan=" + DEFAULT_LATEST_NEWS_1);

        // Get all the frontpageconfigList where latestNews1 greater than or equals to UPDATED_LATEST_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("latestNews1.greaterOrEqualThan=" + UPDATED_LATEST_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews1IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews1 less than or equals to DEFAULT_LATEST_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("latestNews1.lessThan=" + DEFAULT_LATEST_NEWS_1);

        // Get all the frontpageconfigList where latestNews1 less than or equals to UPDATED_LATEST_NEWS_1
        defaultFrontpageconfigShouldBeFound("latestNews1.lessThan=" + UPDATED_LATEST_NEWS_1);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews2IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews2 equals to DEFAULT_LATEST_NEWS_2
        defaultFrontpageconfigShouldBeFound("latestNews2.equals=" + DEFAULT_LATEST_NEWS_2);

        // Get all the frontpageconfigList where latestNews2 equals to UPDATED_LATEST_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("latestNews2.equals=" + UPDATED_LATEST_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews2IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews2 in DEFAULT_LATEST_NEWS_2 or UPDATED_LATEST_NEWS_2
        defaultFrontpageconfigShouldBeFound("latestNews2.in=" + DEFAULT_LATEST_NEWS_2 + "," + UPDATED_LATEST_NEWS_2);

        // Get all the frontpageconfigList where latestNews2 equals to UPDATED_LATEST_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("latestNews2.in=" + UPDATED_LATEST_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews2IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews2 is not null
        defaultFrontpageconfigShouldBeFound("latestNews2.specified=true");

        // Get all the frontpageconfigList where latestNews2 is null
        defaultFrontpageconfigShouldNotBeFound("latestNews2.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews2 greater than or equals to DEFAULT_LATEST_NEWS_2
        defaultFrontpageconfigShouldBeFound("latestNews2.greaterOrEqualThan=" + DEFAULT_LATEST_NEWS_2);

        // Get all the frontpageconfigList where latestNews2 greater than or equals to UPDATED_LATEST_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("latestNews2.greaterOrEqualThan=" + UPDATED_LATEST_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews2IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews2 less than or equals to DEFAULT_LATEST_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("latestNews2.lessThan=" + DEFAULT_LATEST_NEWS_2);

        // Get all the frontpageconfigList where latestNews2 less than or equals to UPDATED_LATEST_NEWS_2
        defaultFrontpageconfigShouldBeFound("latestNews2.lessThan=" + UPDATED_LATEST_NEWS_2);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews3IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews3 equals to DEFAULT_LATEST_NEWS_3
        defaultFrontpageconfigShouldBeFound("latestNews3.equals=" + DEFAULT_LATEST_NEWS_3);

        // Get all the frontpageconfigList where latestNews3 equals to UPDATED_LATEST_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("latestNews3.equals=" + UPDATED_LATEST_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews3IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews3 in DEFAULT_LATEST_NEWS_3 or UPDATED_LATEST_NEWS_3
        defaultFrontpageconfigShouldBeFound("latestNews3.in=" + DEFAULT_LATEST_NEWS_3 + "," + UPDATED_LATEST_NEWS_3);

        // Get all the frontpageconfigList where latestNews3 equals to UPDATED_LATEST_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("latestNews3.in=" + UPDATED_LATEST_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews3IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews3 is not null
        defaultFrontpageconfigShouldBeFound("latestNews3.specified=true");

        // Get all the frontpageconfigList where latestNews3 is null
        defaultFrontpageconfigShouldNotBeFound("latestNews3.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews3 greater than or equals to DEFAULT_LATEST_NEWS_3
        defaultFrontpageconfigShouldBeFound("latestNews3.greaterOrEqualThan=" + DEFAULT_LATEST_NEWS_3);

        // Get all the frontpageconfigList where latestNews3 greater than or equals to UPDATED_LATEST_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("latestNews3.greaterOrEqualThan=" + UPDATED_LATEST_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews3IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews3 less than or equals to DEFAULT_LATEST_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("latestNews3.lessThan=" + DEFAULT_LATEST_NEWS_3);

        // Get all the frontpageconfigList where latestNews3 less than or equals to UPDATED_LATEST_NEWS_3
        defaultFrontpageconfigShouldBeFound("latestNews3.lessThan=" + UPDATED_LATEST_NEWS_3);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews4IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews4 equals to DEFAULT_LATEST_NEWS_4
        defaultFrontpageconfigShouldBeFound("latestNews4.equals=" + DEFAULT_LATEST_NEWS_4);

        // Get all the frontpageconfigList where latestNews4 equals to UPDATED_LATEST_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("latestNews4.equals=" + UPDATED_LATEST_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews4IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews4 in DEFAULT_LATEST_NEWS_4 or UPDATED_LATEST_NEWS_4
        defaultFrontpageconfigShouldBeFound("latestNews4.in=" + DEFAULT_LATEST_NEWS_4 + "," + UPDATED_LATEST_NEWS_4);

        // Get all the frontpageconfigList where latestNews4 equals to UPDATED_LATEST_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("latestNews4.in=" + UPDATED_LATEST_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews4IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews4 is not null
        defaultFrontpageconfigShouldBeFound("latestNews4.specified=true");

        // Get all the frontpageconfigList where latestNews4 is null
        defaultFrontpageconfigShouldNotBeFound("latestNews4.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews4 greater than or equals to DEFAULT_LATEST_NEWS_4
        defaultFrontpageconfigShouldBeFound("latestNews4.greaterOrEqualThan=" + DEFAULT_LATEST_NEWS_4);

        // Get all the frontpageconfigList where latestNews4 greater than or equals to UPDATED_LATEST_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("latestNews4.greaterOrEqualThan=" + UPDATED_LATEST_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews4IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews4 less than or equals to DEFAULT_LATEST_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("latestNews4.lessThan=" + DEFAULT_LATEST_NEWS_4);

        // Get all the frontpageconfigList where latestNews4 less than or equals to UPDATED_LATEST_NEWS_4
        defaultFrontpageconfigShouldBeFound("latestNews4.lessThan=" + UPDATED_LATEST_NEWS_4);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews5IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews5 equals to DEFAULT_LATEST_NEWS_5
        defaultFrontpageconfigShouldBeFound("latestNews5.equals=" + DEFAULT_LATEST_NEWS_5);

        // Get all the frontpageconfigList where latestNews5 equals to UPDATED_LATEST_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("latestNews5.equals=" + UPDATED_LATEST_NEWS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews5IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews5 in DEFAULT_LATEST_NEWS_5 or UPDATED_LATEST_NEWS_5
        defaultFrontpageconfigShouldBeFound("latestNews5.in=" + DEFAULT_LATEST_NEWS_5 + "," + UPDATED_LATEST_NEWS_5);

        // Get all the frontpageconfigList where latestNews5 equals to UPDATED_LATEST_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("latestNews5.in=" + UPDATED_LATEST_NEWS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews5IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews5 is not null
        defaultFrontpageconfigShouldBeFound("latestNews5.specified=true");

        // Get all the frontpageconfigList where latestNews5 is null
        defaultFrontpageconfigShouldNotBeFound("latestNews5.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews5 greater than or equals to DEFAULT_LATEST_NEWS_5
        defaultFrontpageconfigShouldBeFound("latestNews5.greaterOrEqualThan=" + DEFAULT_LATEST_NEWS_5);

        // Get all the frontpageconfigList where latestNews5 greater than or equals to UPDATED_LATEST_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("latestNews5.greaterOrEqualThan=" + UPDATED_LATEST_NEWS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByLatestNews5IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where latestNews5 less than or equals to DEFAULT_LATEST_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("latestNews5.lessThan=" + DEFAULT_LATEST_NEWS_5);

        // Get all the frontpageconfigList where latestNews5 less than or equals to UPDATED_LATEST_NEWS_5
        defaultFrontpageconfigShouldBeFound("latestNews5.lessThan=" + UPDATED_LATEST_NEWS_5);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByBreakingNews1IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where breakingNews1 equals to DEFAULT_BREAKING_NEWS_1
        defaultFrontpageconfigShouldBeFound("breakingNews1.equals=" + DEFAULT_BREAKING_NEWS_1);

        // Get all the frontpageconfigList where breakingNews1 equals to UPDATED_BREAKING_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("breakingNews1.equals=" + UPDATED_BREAKING_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByBreakingNews1IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where breakingNews1 in DEFAULT_BREAKING_NEWS_1 or UPDATED_BREAKING_NEWS_1
        defaultFrontpageconfigShouldBeFound("breakingNews1.in=" + DEFAULT_BREAKING_NEWS_1 + "," + UPDATED_BREAKING_NEWS_1);

        // Get all the frontpageconfigList where breakingNews1 equals to UPDATED_BREAKING_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("breakingNews1.in=" + UPDATED_BREAKING_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByBreakingNews1IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where breakingNews1 is not null
        defaultFrontpageconfigShouldBeFound("breakingNews1.specified=true");

        // Get all the frontpageconfigList where breakingNews1 is null
        defaultFrontpageconfigShouldNotBeFound("breakingNews1.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByBreakingNews1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where breakingNews1 greater than or equals to DEFAULT_BREAKING_NEWS_1
        defaultFrontpageconfigShouldBeFound("breakingNews1.greaterOrEqualThan=" + DEFAULT_BREAKING_NEWS_1);

        // Get all the frontpageconfigList where breakingNews1 greater than or equals to UPDATED_BREAKING_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("breakingNews1.greaterOrEqualThan=" + UPDATED_BREAKING_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByBreakingNews1IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where breakingNews1 less than or equals to DEFAULT_BREAKING_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("breakingNews1.lessThan=" + DEFAULT_BREAKING_NEWS_1);

        // Get all the frontpageconfigList where breakingNews1 less than or equals to UPDATED_BREAKING_NEWS_1
        defaultFrontpageconfigShouldBeFound("breakingNews1.lessThan=" + UPDATED_BREAKING_NEWS_1);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts1IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts1 equals to DEFAULT_RECENT_POSTS_1
        defaultFrontpageconfigShouldBeFound("recentPosts1.equals=" + DEFAULT_RECENT_POSTS_1);

        // Get all the frontpageconfigList where recentPosts1 equals to UPDATED_RECENT_POSTS_1
        defaultFrontpageconfigShouldNotBeFound("recentPosts1.equals=" + UPDATED_RECENT_POSTS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts1IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts1 in DEFAULT_RECENT_POSTS_1 or UPDATED_RECENT_POSTS_1
        defaultFrontpageconfigShouldBeFound("recentPosts1.in=" + DEFAULT_RECENT_POSTS_1 + "," + UPDATED_RECENT_POSTS_1);

        // Get all the frontpageconfigList where recentPosts1 equals to UPDATED_RECENT_POSTS_1
        defaultFrontpageconfigShouldNotBeFound("recentPosts1.in=" + UPDATED_RECENT_POSTS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts1IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts1 is not null
        defaultFrontpageconfigShouldBeFound("recentPosts1.specified=true");

        // Get all the frontpageconfigList where recentPosts1 is null
        defaultFrontpageconfigShouldNotBeFound("recentPosts1.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts1 greater than or equals to DEFAULT_RECENT_POSTS_1
        defaultFrontpageconfigShouldBeFound("recentPosts1.greaterOrEqualThan=" + DEFAULT_RECENT_POSTS_1);

        // Get all the frontpageconfigList where recentPosts1 greater than or equals to UPDATED_RECENT_POSTS_1
        defaultFrontpageconfigShouldNotBeFound("recentPosts1.greaterOrEqualThan=" + UPDATED_RECENT_POSTS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts1IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts1 less than or equals to DEFAULT_RECENT_POSTS_1
        defaultFrontpageconfigShouldNotBeFound("recentPosts1.lessThan=" + DEFAULT_RECENT_POSTS_1);

        // Get all the frontpageconfigList where recentPosts1 less than or equals to UPDATED_RECENT_POSTS_1
        defaultFrontpageconfigShouldBeFound("recentPosts1.lessThan=" + UPDATED_RECENT_POSTS_1);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts2IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts2 equals to DEFAULT_RECENT_POSTS_2
        defaultFrontpageconfigShouldBeFound("recentPosts2.equals=" + DEFAULT_RECENT_POSTS_2);

        // Get all the frontpageconfigList where recentPosts2 equals to UPDATED_RECENT_POSTS_2
        defaultFrontpageconfigShouldNotBeFound("recentPosts2.equals=" + UPDATED_RECENT_POSTS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts2IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts2 in DEFAULT_RECENT_POSTS_2 or UPDATED_RECENT_POSTS_2
        defaultFrontpageconfigShouldBeFound("recentPosts2.in=" + DEFAULT_RECENT_POSTS_2 + "," + UPDATED_RECENT_POSTS_2);

        // Get all the frontpageconfigList where recentPosts2 equals to UPDATED_RECENT_POSTS_2
        defaultFrontpageconfigShouldNotBeFound("recentPosts2.in=" + UPDATED_RECENT_POSTS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts2IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts2 is not null
        defaultFrontpageconfigShouldBeFound("recentPosts2.specified=true");

        // Get all the frontpageconfigList where recentPosts2 is null
        defaultFrontpageconfigShouldNotBeFound("recentPosts2.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts2 greater than or equals to DEFAULT_RECENT_POSTS_2
        defaultFrontpageconfigShouldBeFound("recentPosts2.greaterOrEqualThan=" + DEFAULT_RECENT_POSTS_2);

        // Get all the frontpageconfigList where recentPosts2 greater than or equals to UPDATED_RECENT_POSTS_2
        defaultFrontpageconfigShouldNotBeFound("recentPosts2.greaterOrEqualThan=" + UPDATED_RECENT_POSTS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts2IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts2 less than or equals to DEFAULT_RECENT_POSTS_2
        defaultFrontpageconfigShouldNotBeFound("recentPosts2.lessThan=" + DEFAULT_RECENT_POSTS_2);

        // Get all the frontpageconfigList where recentPosts2 less than or equals to UPDATED_RECENT_POSTS_2
        defaultFrontpageconfigShouldBeFound("recentPosts2.lessThan=" + UPDATED_RECENT_POSTS_2);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts3IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts3 equals to DEFAULT_RECENT_POSTS_3
        defaultFrontpageconfigShouldBeFound("recentPosts3.equals=" + DEFAULT_RECENT_POSTS_3);

        // Get all the frontpageconfigList where recentPosts3 equals to UPDATED_RECENT_POSTS_3
        defaultFrontpageconfigShouldNotBeFound("recentPosts3.equals=" + UPDATED_RECENT_POSTS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts3IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts3 in DEFAULT_RECENT_POSTS_3 or UPDATED_RECENT_POSTS_3
        defaultFrontpageconfigShouldBeFound("recentPosts3.in=" + DEFAULT_RECENT_POSTS_3 + "," + UPDATED_RECENT_POSTS_3);

        // Get all the frontpageconfigList where recentPosts3 equals to UPDATED_RECENT_POSTS_3
        defaultFrontpageconfigShouldNotBeFound("recentPosts3.in=" + UPDATED_RECENT_POSTS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts3IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts3 is not null
        defaultFrontpageconfigShouldBeFound("recentPosts3.specified=true");

        // Get all the frontpageconfigList where recentPosts3 is null
        defaultFrontpageconfigShouldNotBeFound("recentPosts3.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts3 greater than or equals to DEFAULT_RECENT_POSTS_3
        defaultFrontpageconfigShouldBeFound("recentPosts3.greaterOrEqualThan=" + DEFAULT_RECENT_POSTS_3);

        // Get all the frontpageconfigList where recentPosts3 greater than or equals to UPDATED_RECENT_POSTS_3
        defaultFrontpageconfigShouldNotBeFound("recentPosts3.greaterOrEqualThan=" + UPDATED_RECENT_POSTS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts3IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts3 less than or equals to DEFAULT_RECENT_POSTS_3
        defaultFrontpageconfigShouldNotBeFound("recentPosts3.lessThan=" + DEFAULT_RECENT_POSTS_3);

        // Get all the frontpageconfigList where recentPosts3 less than or equals to UPDATED_RECENT_POSTS_3
        defaultFrontpageconfigShouldBeFound("recentPosts3.lessThan=" + UPDATED_RECENT_POSTS_3);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts4IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts4 equals to DEFAULT_RECENT_POSTS_4
        defaultFrontpageconfigShouldBeFound("recentPosts4.equals=" + DEFAULT_RECENT_POSTS_4);

        // Get all the frontpageconfigList where recentPosts4 equals to UPDATED_RECENT_POSTS_4
        defaultFrontpageconfigShouldNotBeFound("recentPosts4.equals=" + UPDATED_RECENT_POSTS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts4IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts4 in DEFAULT_RECENT_POSTS_4 or UPDATED_RECENT_POSTS_4
        defaultFrontpageconfigShouldBeFound("recentPosts4.in=" + DEFAULT_RECENT_POSTS_4 + "," + UPDATED_RECENT_POSTS_4);

        // Get all the frontpageconfigList where recentPosts4 equals to UPDATED_RECENT_POSTS_4
        defaultFrontpageconfigShouldNotBeFound("recentPosts4.in=" + UPDATED_RECENT_POSTS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts4IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts4 is not null
        defaultFrontpageconfigShouldBeFound("recentPosts4.specified=true");

        // Get all the frontpageconfigList where recentPosts4 is null
        defaultFrontpageconfigShouldNotBeFound("recentPosts4.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts4 greater than or equals to DEFAULT_RECENT_POSTS_4
        defaultFrontpageconfigShouldBeFound("recentPosts4.greaterOrEqualThan=" + DEFAULT_RECENT_POSTS_4);

        // Get all the frontpageconfigList where recentPosts4 greater than or equals to UPDATED_RECENT_POSTS_4
        defaultFrontpageconfigShouldNotBeFound("recentPosts4.greaterOrEqualThan=" + UPDATED_RECENT_POSTS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentPosts4IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentPosts4 less than or equals to DEFAULT_RECENT_POSTS_4
        defaultFrontpageconfigShouldNotBeFound("recentPosts4.lessThan=" + DEFAULT_RECENT_POSTS_4);

        // Get all the frontpageconfigList where recentPosts4 less than or equals to UPDATED_RECENT_POSTS_4
        defaultFrontpageconfigShouldBeFound("recentPosts4.lessThan=" + UPDATED_RECENT_POSTS_4);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles1IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles1 equals to DEFAULT_FEATURED_ARTICLES_1
        defaultFrontpageconfigShouldBeFound("featuredArticles1.equals=" + DEFAULT_FEATURED_ARTICLES_1);

        // Get all the frontpageconfigList where featuredArticles1 equals to UPDATED_FEATURED_ARTICLES_1
        defaultFrontpageconfigShouldNotBeFound("featuredArticles1.equals=" + UPDATED_FEATURED_ARTICLES_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles1IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles1 in DEFAULT_FEATURED_ARTICLES_1 or UPDATED_FEATURED_ARTICLES_1
        defaultFrontpageconfigShouldBeFound("featuredArticles1.in=" + DEFAULT_FEATURED_ARTICLES_1 + "," + UPDATED_FEATURED_ARTICLES_1);

        // Get all the frontpageconfigList where featuredArticles1 equals to UPDATED_FEATURED_ARTICLES_1
        defaultFrontpageconfigShouldNotBeFound("featuredArticles1.in=" + UPDATED_FEATURED_ARTICLES_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles1IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles1 is not null
        defaultFrontpageconfigShouldBeFound("featuredArticles1.specified=true");

        // Get all the frontpageconfigList where featuredArticles1 is null
        defaultFrontpageconfigShouldNotBeFound("featuredArticles1.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles1 greater than or equals to DEFAULT_FEATURED_ARTICLES_1
        defaultFrontpageconfigShouldBeFound("featuredArticles1.greaterOrEqualThan=" + DEFAULT_FEATURED_ARTICLES_1);

        // Get all the frontpageconfigList where featuredArticles1 greater than or equals to UPDATED_FEATURED_ARTICLES_1
        defaultFrontpageconfigShouldNotBeFound("featuredArticles1.greaterOrEqualThan=" + UPDATED_FEATURED_ARTICLES_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles1IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles1 less than or equals to DEFAULT_FEATURED_ARTICLES_1
        defaultFrontpageconfigShouldNotBeFound("featuredArticles1.lessThan=" + DEFAULT_FEATURED_ARTICLES_1);

        // Get all the frontpageconfigList where featuredArticles1 less than or equals to UPDATED_FEATURED_ARTICLES_1
        defaultFrontpageconfigShouldBeFound("featuredArticles1.lessThan=" + UPDATED_FEATURED_ARTICLES_1);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles2IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles2 equals to DEFAULT_FEATURED_ARTICLES_2
        defaultFrontpageconfigShouldBeFound("featuredArticles2.equals=" + DEFAULT_FEATURED_ARTICLES_2);

        // Get all the frontpageconfigList where featuredArticles2 equals to UPDATED_FEATURED_ARTICLES_2
        defaultFrontpageconfigShouldNotBeFound("featuredArticles2.equals=" + UPDATED_FEATURED_ARTICLES_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles2IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles2 in DEFAULT_FEATURED_ARTICLES_2 or UPDATED_FEATURED_ARTICLES_2
        defaultFrontpageconfigShouldBeFound("featuredArticles2.in=" + DEFAULT_FEATURED_ARTICLES_2 + "," + UPDATED_FEATURED_ARTICLES_2);

        // Get all the frontpageconfigList where featuredArticles2 equals to UPDATED_FEATURED_ARTICLES_2
        defaultFrontpageconfigShouldNotBeFound("featuredArticles2.in=" + UPDATED_FEATURED_ARTICLES_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles2IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles2 is not null
        defaultFrontpageconfigShouldBeFound("featuredArticles2.specified=true");

        // Get all the frontpageconfigList where featuredArticles2 is null
        defaultFrontpageconfigShouldNotBeFound("featuredArticles2.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles2 greater than or equals to DEFAULT_FEATURED_ARTICLES_2
        defaultFrontpageconfigShouldBeFound("featuredArticles2.greaterOrEqualThan=" + DEFAULT_FEATURED_ARTICLES_2);

        // Get all the frontpageconfigList where featuredArticles2 greater than or equals to UPDATED_FEATURED_ARTICLES_2
        defaultFrontpageconfigShouldNotBeFound("featuredArticles2.greaterOrEqualThan=" + UPDATED_FEATURED_ARTICLES_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles2IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles2 less than or equals to DEFAULT_FEATURED_ARTICLES_2
        defaultFrontpageconfigShouldNotBeFound("featuredArticles2.lessThan=" + DEFAULT_FEATURED_ARTICLES_2);

        // Get all the frontpageconfigList where featuredArticles2 less than or equals to UPDATED_FEATURED_ARTICLES_2
        defaultFrontpageconfigShouldBeFound("featuredArticles2.lessThan=" + UPDATED_FEATURED_ARTICLES_2);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles3IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles3 equals to DEFAULT_FEATURED_ARTICLES_3
        defaultFrontpageconfigShouldBeFound("featuredArticles3.equals=" + DEFAULT_FEATURED_ARTICLES_3);

        // Get all the frontpageconfigList where featuredArticles3 equals to UPDATED_FEATURED_ARTICLES_3
        defaultFrontpageconfigShouldNotBeFound("featuredArticles3.equals=" + UPDATED_FEATURED_ARTICLES_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles3IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles3 in DEFAULT_FEATURED_ARTICLES_3 or UPDATED_FEATURED_ARTICLES_3
        defaultFrontpageconfigShouldBeFound("featuredArticles3.in=" + DEFAULT_FEATURED_ARTICLES_3 + "," + UPDATED_FEATURED_ARTICLES_3);

        // Get all the frontpageconfigList where featuredArticles3 equals to UPDATED_FEATURED_ARTICLES_3
        defaultFrontpageconfigShouldNotBeFound("featuredArticles3.in=" + UPDATED_FEATURED_ARTICLES_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles3IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles3 is not null
        defaultFrontpageconfigShouldBeFound("featuredArticles3.specified=true");

        // Get all the frontpageconfigList where featuredArticles3 is null
        defaultFrontpageconfigShouldNotBeFound("featuredArticles3.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles3 greater than or equals to DEFAULT_FEATURED_ARTICLES_3
        defaultFrontpageconfigShouldBeFound("featuredArticles3.greaterOrEqualThan=" + DEFAULT_FEATURED_ARTICLES_3);

        // Get all the frontpageconfigList where featuredArticles3 greater than or equals to UPDATED_FEATURED_ARTICLES_3
        defaultFrontpageconfigShouldNotBeFound("featuredArticles3.greaterOrEqualThan=" + UPDATED_FEATURED_ARTICLES_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles3IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles3 less than or equals to DEFAULT_FEATURED_ARTICLES_3
        defaultFrontpageconfigShouldNotBeFound("featuredArticles3.lessThan=" + DEFAULT_FEATURED_ARTICLES_3);

        // Get all the frontpageconfigList where featuredArticles3 less than or equals to UPDATED_FEATURED_ARTICLES_3
        defaultFrontpageconfigShouldBeFound("featuredArticles3.lessThan=" + UPDATED_FEATURED_ARTICLES_3);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles4IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles4 equals to DEFAULT_FEATURED_ARTICLES_4
        defaultFrontpageconfigShouldBeFound("featuredArticles4.equals=" + DEFAULT_FEATURED_ARTICLES_4);

        // Get all the frontpageconfigList where featuredArticles4 equals to UPDATED_FEATURED_ARTICLES_4
        defaultFrontpageconfigShouldNotBeFound("featuredArticles4.equals=" + UPDATED_FEATURED_ARTICLES_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles4IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles4 in DEFAULT_FEATURED_ARTICLES_4 or UPDATED_FEATURED_ARTICLES_4
        defaultFrontpageconfigShouldBeFound("featuredArticles4.in=" + DEFAULT_FEATURED_ARTICLES_4 + "," + UPDATED_FEATURED_ARTICLES_4);

        // Get all the frontpageconfigList where featuredArticles4 equals to UPDATED_FEATURED_ARTICLES_4
        defaultFrontpageconfigShouldNotBeFound("featuredArticles4.in=" + UPDATED_FEATURED_ARTICLES_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles4IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles4 is not null
        defaultFrontpageconfigShouldBeFound("featuredArticles4.specified=true");

        // Get all the frontpageconfigList where featuredArticles4 is null
        defaultFrontpageconfigShouldNotBeFound("featuredArticles4.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles4 greater than or equals to DEFAULT_FEATURED_ARTICLES_4
        defaultFrontpageconfigShouldBeFound("featuredArticles4.greaterOrEqualThan=" + DEFAULT_FEATURED_ARTICLES_4);

        // Get all the frontpageconfigList where featuredArticles4 greater than or equals to UPDATED_FEATURED_ARTICLES_4
        defaultFrontpageconfigShouldNotBeFound("featuredArticles4.greaterOrEqualThan=" + UPDATED_FEATURED_ARTICLES_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles4IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles4 less than or equals to DEFAULT_FEATURED_ARTICLES_4
        defaultFrontpageconfigShouldNotBeFound("featuredArticles4.lessThan=" + DEFAULT_FEATURED_ARTICLES_4);

        // Get all the frontpageconfigList where featuredArticles4 less than or equals to UPDATED_FEATURED_ARTICLES_4
        defaultFrontpageconfigShouldBeFound("featuredArticles4.lessThan=" + UPDATED_FEATURED_ARTICLES_4);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles5IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles5 equals to DEFAULT_FEATURED_ARTICLES_5
        defaultFrontpageconfigShouldBeFound("featuredArticles5.equals=" + DEFAULT_FEATURED_ARTICLES_5);

        // Get all the frontpageconfigList where featuredArticles5 equals to UPDATED_FEATURED_ARTICLES_5
        defaultFrontpageconfigShouldNotBeFound("featuredArticles5.equals=" + UPDATED_FEATURED_ARTICLES_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles5IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles5 in DEFAULT_FEATURED_ARTICLES_5 or UPDATED_FEATURED_ARTICLES_5
        defaultFrontpageconfigShouldBeFound("featuredArticles5.in=" + DEFAULT_FEATURED_ARTICLES_5 + "," + UPDATED_FEATURED_ARTICLES_5);

        // Get all the frontpageconfigList where featuredArticles5 equals to UPDATED_FEATURED_ARTICLES_5
        defaultFrontpageconfigShouldNotBeFound("featuredArticles5.in=" + UPDATED_FEATURED_ARTICLES_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles5IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles5 is not null
        defaultFrontpageconfigShouldBeFound("featuredArticles5.specified=true");

        // Get all the frontpageconfigList where featuredArticles5 is null
        defaultFrontpageconfigShouldNotBeFound("featuredArticles5.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles5 greater than or equals to DEFAULT_FEATURED_ARTICLES_5
        defaultFrontpageconfigShouldBeFound("featuredArticles5.greaterOrEqualThan=" + DEFAULT_FEATURED_ARTICLES_5);

        // Get all the frontpageconfigList where featuredArticles5 greater than or equals to UPDATED_FEATURED_ARTICLES_5
        defaultFrontpageconfigShouldNotBeFound("featuredArticles5.greaterOrEqualThan=" + UPDATED_FEATURED_ARTICLES_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles5IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles5 less than or equals to DEFAULT_FEATURED_ARTICLES_5
        defaultFrontpageconfigShouldNotBeFound("featuredArticles5.lessThan=" + DEFAULT_FEATURED_ARTICLES_5);

        // Get all the frontpageconfigList where featuredArticles5 less than or equals to UPDATED_FEATURED_ARTICLES_5
        defaultFrontpageconfigShouldBeFound("featuredArticles5.lessThan=" + UPDATED_FEATURED_ARTICLES_5);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles6IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles6 equals to DEFAULT_FEATURED_ARTICLES_6
        defaultFrontpageconfigShouldBeFound("featuredArticles6.equals=" + DEFAULT_FEATURED_ARTICLES_6);

        // Get all the frontpageconfigList where featuredArticles6 equals to UPDATED_FEATURED_ARTICLES_6
        defaultFrontpageconfigShouldNotBeFound("featuredArticles6.equals=" + UPDATED_FEATURED_ARTICLES_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles6IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles6 in DEFAULT_FEATURED_ARTICLES_6 or UPDATED_FEATURED_ARTICLES_6
        defaultFrontpageconfigShouldBeFound("featuredArticles6.in=" + DEFAULT_FEATURED_ARTICLES_6 + "," + UPDATED_FEATURED_ARTICLES_6);

        // Get all the frontpageconfigList where featuredArticles6 equals to UPDATED_FEATURED_ARTICLES_6
        defaultFrontpageconfigShouldNotBeFound("featuredArticles6.in=" + UPDATED_FEATURED_ARTICLES_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles6IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles6 is not null
        defaultFrontpageconfigShouldBeFound("featuredArticles6.specified=true");

        // Get all the frontpageconfigList where featuredArticles6 is null
        defaultFrontpageconfigShouldNotBeFound("featuredArticles6.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles6 greater than or equals to DEFAULT_FEATURED_ARTICLES_6
        defaultFrontpageconfigShouldBeFound("featuredArticles6.greaterOrEqualThan=" + DEFAULT_FEATURED_ARTICLES_6);

        // Get all the frontpageconfigList where featuredArticles6 greater than or equals to UPDATED_FEATURED_ARTICLES_6
        defaultFrontpageconfigShouldNotBeFound("featuredArticles6.greaterOrEqualThan=" + UPDATED_FEATURED_ARTICLES_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles6IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles6 less than or equals to DEFAULT_FEATURED_ARTICLES_6
        defaultFrontpageconfigShouldNotBeFound("featuredArticles6.lessThan=" + DEFAULT_FEATURED_ARTICLES_6);

        // Get all the frontpageconfigList where featuredArticles6 less than or equals to UPDATED_FEATURED_ARTICLES_6
        defaultFrontpageconfigShouldBeFound("featuredArticles6.lessThan=" + UPDATED_FEATURED_ARTICLES_6);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles7IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles7 equals to DEFAULT_FEATURED_ARTICLES_7
        defaultFrontpageconfigShouldBeFound("featuredArticles7.equals=" + DEFAULT_FEATURED_ARTICLES_7);

        // Get all the frontpageconfigList where featuredArticles7 equals to UPDATED_FEATURED_ARTICLES_7
        defaultFrontpageconfigShouldNotBeFound("featuredArticles7.equals=" + UPDATED_FEATURED_ARTICLES_7);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles7IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles7 in DEFAULT_FEATURED_ARTICLES_7 or UPDATED_FEATURED_ARTICLES_7
        defaultFrontpageconfigShouldBeFound("featuredArticles7.in=" + DEFAULT_FEATURED_ARTICLES_7 + "," + UPDATED_FEATURED_ARTICLES_7);

        // Get all the frontpageconfigList where featuredArticles7 equals to UPDATED_FEATURED_ARTICLES_7
        defaultFrontpageconfigShouldNotBeFound("featuredArticles7.in=" + UPDATED_FEATURED_ARTICLES_7);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles7IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles7 is not null
        defaultFrontpageconfigShouldBeFound("featuredArticles7.specified=true");

        // Get all the frontpageconfigList where featuredArticles7 is null
        defaultFrontpageconfigShouldNotBeFound("featuredArticles7.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles7IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles7 greater than or equals to DEFAULT_FEATURED_ARTICLES_7
        defaultFrontpageconfigShouldBeFound("featuredArticles7.greaterOrEqualThan=" + DEFAULT_FEATURED_ARTICLES_7);

        // Get all the frontpageconfigList where featuredArticles7 greater than or equals to UPDATED_FEATURED_ARTICLES_7
        defaultFrontpageconfigShouldNotBeFound("featuredArticles7.greaterOrEqualThan=" + UPDATED_FEATURED_ARTICLES_7);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles7IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles7 less than or equals to DEFAULT_FEATURED_ARTICLES_7
        defaultFrontpageconfigShouldNotBeFound("featuredArticles7.lessThan=" + DEFAULT_FEATURED_ARTICLES_7);

        // Get all the frontpageconfigList where featuredArticles7 less than or equals to UPDATED_FEATURED_ARTICLES_7
        defaultFrontpageconfigShouldBeFound("featuredArticles7.lessThan=" + UPDATED_FEATURED_ARTICLES_7);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles8IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles8 equals to DEFAULT_FEATURED_ARTICLES_8
        defaultFrontpageconfigShouldBeFound("featuredArticles8.equals=" + DEFAULT_FEATURED_ARTICLES_8);

        // Get all the frontpageconfigList where featuredArticles8 equals to UPDATED_FEATURED_ARTICLES_8
        defaultFrontpageconfigShouldNotBeFound("featuredArticles8.equals=" + UPDATED_FEATURED_ARTICLES_8);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles8IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles8 in DEFAULT_FEATURED_ARTICLES_8 or UPDATED_FEATURED_ARTICLES_8
        defaultFrontpageconfigShouldBeFound("featuredArticles8.in=" + DEFAULT_FEATURED_ARTICLES_8 + "," + UPDATED_FEATURED_ARTICLES_8);

        // Get all the frontpageconfigList where featuredArticles8 equals to UPDATED_FEATURED_ARTICLES_8
        defaultFrontpageconfigShouldNotBeFound("featuredArticles8.in=" + UPDATED_FEATURED_ARTICLES_8);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles8IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles8 is not null
        defaultFrontpageconfigShouldBeFound("featuredArticles8.specified=true");

        // Get all the frontpageconfigList where featuredArticles8 is null
        defaultFrontpageconfigShouldNotBeFound("featuredArticles8.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles8IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles8 greater than or equals to DEFAULT_FEATURED_ARTICLES_8
        defaultFrontpageconfigShouldBeFound("featuredArticles8.greaterOrEqualThan=" + DEFAULT_FEATURED_ARTICLES_8);

        // Get all the frontpageconfigList where featuredArticles8 greater than or equals to UPDATED_FEATURED_ARTICLES_8
        defaultFrontpageconfigShouldNotBeFound("featuredArticles8.greaterOrEqualThan=" + UPDATED_FEATURED_ARTICLES_8);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles8IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles8 less than or equals to DEFAULT_FEATURED_ARTICLES_8
        defaultFrontpageconfigShouldNotBeFound("featuredArticles8.lessThan=" + DEFAULT_FEATURED_ARTICLES_8);

        // Get all the frontpageconfigList where featuredArticles8 less than or equals to UPDATED_FEATURED_ARTICLES_8
        defaultFrontpageconfigShouldBeFound("featuredArticles8.lessThan=" + UPDATED_FEATURED_ARTICLES_8);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles9IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles9 equals to DEFAULT_FEATURED_ARTICLES_9
        defaultFrontpageconfigShouldBeFound("featuredArticles9.equals=" + DEFAULT_FEATURED_ARTICLES_9);

        // Get all the frontpageconfigList where featuredArticles9 equals to UPDATED_FEATURED_ARTICLES_9
        defaultFrontpageconfigShouldNotBeFound("featuredArticles9.equals=" + UPDATED_FEATURED_ARTICLES_9);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles9IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles9 in DEFAULT_FEATURED_ARTICLES_9 or UPDATED_FEATURED_ARTICLES_9
        defaultFrontpageconfigShouldBeFound("featuredArticles9.in=" + DEFAULT_FEATURED_ARTICLES_9 + "," + UPDATED_FEATURED_ARTICLES_9);

        // Get all the frontpageconfigList where featuredArticles9 equals to UPDATED_FEATURED_ARTICLES_9
        defaultFrontpageconfigShouldNotBeFound("featuredArticles9.in=" + UPDATED_FEATURED_ARTICLES_9);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles9IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles9 is not null
        defaultFrontpageconfigShouldBeFound("featuredArticles9.specified=true");

        // Get all the frontpageconfigList where featuredArticles9 is null
        defaultFrontpageconfigShouldNotBeFound("featuredArticles9.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles9IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles9 greater than or equals to DEFAULT_FEATURED_ARTICLES_9
        defaultFrontpageconfigShouldBeFound("featuredArticles9.greaterOrEqualThan=" + DEFAULT_FEATURED_ARTICLES_9);

        // Get all the frontpageconfigList where featuredArticles9 greater than or equals to UPDATED_FEATURED_ARTICLES_9
        defaultFrontpageconfigShouldNotBeFound("featuredArticles9.greaterOrEqualThan=" + UPDATED_FEATURED_ARTICLES_9);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles9IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles9 less than or equals to DEFAULT_FEATURED_ARTICLES_9
        defaultFrontpageconfigShouldNotBeFound("featuredArticles9.lessThan=" + DEFAULT_FEATURED_ARTICLES_9);

        // Get all the frontpageconfigList where featuredArticles9 less than or equals to UPDATED_FEATURED_ARTICLES_9
        defaultFrontpageconfigShouldBeFound("featuredArticles9.lessThan=" + UPDATED_FEATURED_ARTICLES_9);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles10IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles10 equals to DEFAULT_FEATURED_ARTICLES_10
        defaultFrontpageconfigShouldBeFound("featuredArticles10.equals=" + DEFAULT_FEATURED_ARTICLES_10);

        // Get all the frontpageconfigList where featuredArticles10 equals to UPDATED_FEATURED_ARTICLES_10
        defaultFrontpageconfigShouldNotBeFound("featuredArticles10.equals=" + UPDATED_FEATURED_ARTICLES_10);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles10IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles10 in DEFAULT_FEATURED_ARTICLES_10 or UPDATED_FEATURED_ARTICLES_10
        defaultFrontpageconfigShouldBeFound("featuredArticles10.in=" + DEFAULT_FEATURED_ARTICLES_10 + "," + UPDATED_FEATURED_ARTICLES_10);

        // Get all the frontpageconfigList where featuredArticles10 equals to UPDATED_FEATURED_ARTICLES_10
        defaultFrontpageconfigShouldNotBeFound("featuredArticles10.in=" + UPDATED_FEATURED_ARTICLES_10);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles10IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles10 is not null
        defaultFrontpageconfigShouldBeFound("featuredArticles10.specified=true");

        // Get all the frontpageconfigList where featuredArticles10 is null
        defaultFrontpageconfigShouldNotBeFound("featuredArticles10.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles10IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles10 greater than or equals to DEFAULT_FEATURED_ARTICLES_10
        defaultFrontpageconfigShouldBeFound("featuredArticles10.greaterOrEqualThan=" + DEFAULT_FEATURED_ARTICLES_10);

        // Get all the frontpageconfigList where featuredArticles10 greater than or equals to UPDATED_FEATURED_ARTICLES_10
        defaultFrontpageconfigShouldNotBeFound("featuredArticles10.greaterOrEqualThan=" + UPDATED_FEATURED_ARTICLES_10);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByFeaturedArticles10IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where featuredArticles10 less than or equals to DEFAULT_FEATURED_ARTICLES_10
        defaultFrontpageconfigShouldNotBeFound("featuredArticles10.lessThan=" + DEFAULT_FEATURED_ARTICLES_10);

        // Get all the frontpageconfigList where featuredArticles10 less than or equals to UPDATED_FEATURED_ARTICLES_10
        defaultFrontpageconfigShouldBeFound("featuredArticles10.lessThan=" + UPDATED_FEATURED_ARTICLES_10);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews1IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews1 equals to DEFAULT_POPULAR_NEWS_1
        defaultFrontpageconfigShouldBeFound("popularNews1.equals=" + DEFAULT_POPULAR_NEWS_1);

        // Get all the frontpageconfigList where popularNews1 equals to UPDATED_POPULAR_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("popularNews1.equals=" + UPDATED_POPULAR_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews1IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews1 in DEFAULT_POPULAR_NEWS_1 or UPDATED_POPULAR_NEWS_1
        defaultFrontpageconfigShouldBeFound("popularNews1.in=" + DEFAULT_POPULAR_NEWS_1 + "," + UPDATED_POPULAR_NEWS_1);

        // Get all the frontpageconfigList where popularNews1 equals to UPDATED_POPULAR_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("popularNews1.in=" + UPDATED_POPULAR_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews1IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews1 is not null
        defaultFrontpageconfigShouldBeFound("popularNews1.specified=true");

        // Get all the frontpageconfigList where popularNews1 is null
        defaultFrontpageconfigShouldNotBeFound("popularNews1.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews1 greater than or equals to DEFAULT_POPULAR_NEWS_1
        defaultFrontpageconfigShouldBeFound("popularNews1.greaterOrEqualThan=" + DEFAULT_POPULAR_NEWS_1);

        // Get all the frontpageconfigList where popularNews1 greater than or equals to UPDATED_POPULAR_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("popularNews1.greaterOrEqualThan=" + UPDATED_POPULAR_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews1IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews1 less than or equals to DEFAULT_POPULAR_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("popularNews1.lessThan=" + DEFAULT_POPULAR_NEWS_1);

        // Get all the frontpageconfigList where popularNews1 less than or equals to UPDATED_POPULAR_NEWS_1
        defaultFrontpageconfigShouldBeFound("popularNews1.lessThan=" + UPDATED_POPULAR_NEWS_1);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews2IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews2 equals to DEFAULT_POPULAR_NEWS_2
        defaultFrontpageconfigShouldBeFound("popularNews2.equals=" + DEFAULT_POPULAR_NEWS_2);

        // Get all the frontpageconfigList where popularNews2 equals to UPDATED_POPULAR_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("popularNews2.equals=" + UPDATED_POPULAR_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews2IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews2 in DEFAULT_POPULAR_NEWS_2 or UPDATED_POPULAR_NEWS_2
        defaultFrontpageconfigShouldBeFound("popularNews2.in=" + DEFAULT_POPULAR_NEWS_2 + "," + UPDATED_POPULAR_NEWS_2);

        // Get all the frontpageconfigList where popularNews2 equals to UPDATED_POPULAR_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("popularNews2.in=" + UPDATED_POPULAR_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews2IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews2 is not null
        defaultFrontpageconfigShouldBeFound("popularNews2.specified=true");

        // Get all the frontpageconfigList where popularNews2 is null
        defaultFrontpageconfigShouldNotBeFound("popularNews2.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews2 greater than or equals to DEFAULT_POPULAR_NEWS_2
        defaultFrontpageconfigShouldBeFound("popularNews2.greaterOrEqualThan=" + DEFAULT_POPULAR_NEWS_2);

        // Get all the frontpageconfigList where popularNews2 greater than or equals to UPDATED_POPULAR_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("popularNews2.greaterOrEqualThan=" + UPDATED_POPULAR_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews2IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews2 less than or equals to DEFAULT_POPULAR_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("popularNews2.lessThan=" + DEFAULT_POPULAR_NEWS_2);

        // Get all the frontpageconfigList where popularNews2 less than or equals to UPDATED_POPULAR_NEWS_2
        defaultFrontpageconfigShouldBeFound("popularNews2.lessThan=" + UPDATED_POPULAR_NEWS_2);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews3IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews3 equals to DEFAULT_POPULAR_NEWS_3
        defaultFrontpageconfigShouldBeFound("popularNews3.equals=" + DEFAULT_POPULAR_NEWS_3);

        // Get all the frontpageconfigList where popularNews3 equals to UPDATED_POPULAR_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("popularNews3.equals=" + UPDATED_POPULAR_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews3IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews3 in DEFAULT_POPULAR_NEWS_3 or UPDATED_POPULAR_NEWS_3
        defaultFrontpageconfigShouldBeFound("popularNews3.in=" + DEFAULT_POPULAR_NEWS_3 + "," + UPDATED_POPULAR_NEWS_3);

        // Get all the frontpageconfigList where popularNews3 equals to UPDATED_POPULAR_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("popularNews3.in=" + UPDATED_POPULAR_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews3IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews3 is not null
        defaultFrontpageconfigShouldBeFound("popularNews3.specified=true");

        // Get all the frontpageconfigList where popularNews3 is null
        defaultFrontpageconfigShouldNotBeFound("popularNews3.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews3 greater than or equals to DEFAULT_POPULAR_NEWS_3
        defaultFrontpageconfigShouldBeFound("popularNews3.greaterOrEqualThan=" + DEFAULT_POPULAR_NEWS_3);

        // Get all the frontpageconfigList where popularNews3 greater than or equals to UPDATED_POPULAR_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("popularNews3.greaterOrEqualThan=" + UPDATED_POPULAR_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews3IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews3 less than or equals to DEFAULT_POPULAR_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("popularNews3.lessThan=" + DEFAULT_POPULAR_NEWS_3);

        // Get all the frontpageconfigList where popularNews3 less than or equals to UPDATED_POPULAR_NEWS_3
        defaultFrontpageconfigShouldBeFound("popularNews3.lessThan=" + UPDATED_POPULAR_NEWS_3);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews4IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews4 equals to DEFAULT_POPULAR_NEWS_4
        defaultFrontpageconfigShouldBeFound("popularNews4.equals=" + DEFAULT_POPULAR_NEWS_4);

        // Get all the frontpageconfigList where popularNews4 equals to UPDATED_POPULAR_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("popularNews4.equals=" + UPDATED_POPULAR_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews4IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews4 in DEFAULT_POPULAR_NEWS_4 or UPDATED_POPULAR_NEWS_4
        defaultFrontpageconfigShouldBeFound("popularNews4.in=" + DEFAULT_POPULAR_NEWS_4 + "," + UPDATED_POPULAR_NEWS_4);

        // Get all the frontpageconfigList where popularNews4 equals to UPDATED_POPULAR_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("popularNews4.in=" + UPDATED_POPULAR_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews4IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews4 is not null
        defaultFrontpageconfigShouldBeFound("popularNews4.specified=true");

        // Get all the frontpageconfigList where popularNews4 is null
        defaultFrontpageconfigShouldNotBeFound("popularNews4.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews4 greater than or equals to DEFAULT_POPULAR_NEWS_4
        defaultFrontpageconfigShouldBeFound("popularNews4.greaterOrEqualThan=" + DEFAULT_POPULAR_NEWS_4);

        // Get all the frontpageconfigList where popularNews4 greater than or equals to UPDATED_POPULAR_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("popularNews4.greaterOrEqualThan=" + UPDATED_POPULAR_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews4IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews4 less than or equals to DEFAULT_POPULAR_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("popularNews4.lessThan=" + DEFAULT_POPULAR_NEWS_4);

        // Get all the frontpageconfigList where popularNews4 less than or equals to UPDATED_POPULAR_NEWS_4
        defaultFrontpageconfigShouldBeFound("popularNews4.lessThan=" + UPDATED_POPULAR_NEWS_4);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews5IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews5 equals to DEFAULT_POPULAR_NEWS_5
        defaultFrontpageconfigShouldBeFound("popularNews5.equals=" + DEFAULT_POPULAR_NEWS_5);

        // Get all the frontpageconfigList where popularNews5 equals to UPDATED_POPULAR_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("popularNews5.equals=" + UPDATED_POPULAR_NEWS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews5IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews5 in DEFAULT_POPULAR_NEWS_5 or UPDATED_POPULAR_NEWS_5
        defaultFrontpageconfigShouldBeFound("popularNews5.in=" + DEFAULT_POPULAR_NEWS_5 + "," + UPDATED_POPULAR_NEWS_5);

        // Get all the frontpageconfigList where popularNews5 equals to UPDATED_POPULAR_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("popularNews5.in=" + UPDATED_POPULAR_NEWS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews5IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews5 is not null
        defaultFrontpageconfigShouldBeFound("popularNews5.specified=true");

        // Get all the frontpageconfigList where popularNews5 is null
        defaultFrontpageconfigShouldNotBeFound("popularNews5.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews5 greater than or equals to DEFAULT_POPULAR_NEWS_5
        defaultFrontpageconfigShouldBeFound("popularNews5.greaterOrEqualThan=" + DEFAULT_POPULAR_NEWS_5);

        // Get all the frontpageconfigList where popularNews5 greater than or equals to UPDATED_POPULAR_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("popularNews5.greaterOrEqualThan=" + UPDATED_POPULAR_NEWS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews5IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews5 less than or equals to DEFAULT_POPULAR_NEWS_5
        defaultFrontpageconfigShouldNotBeFound("popularNews5.lessThan=" + DEFAULT_POPULAR_NEWS_5);

        // Get all the frontpageconfigList where popularNews5 less than or equals to UPDATED_POPULAR_NEWS_5
        defaultFrontpageconfigShouldBeFound("popularNews5.lessThan=" + UPDATED_POPULAR_NEWS_5);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews6IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews6 equals to DEFAULT_POPULAR_NEWS_6
        defaultFrontpageconfigShouldBeFound("popularNews6.equals=" + DEFAULT_POPULAR_NEWS_6);

        // Get all the frontpageconfigList where popularNews6 equals to UPDATED_POPULAR_NEWS_6
        defaultFrontpageconfigShouldNotBeFound("popularNews6.equals=" + UPDATED_POPULAR_NEWS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews6IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews6 in DEFAULT_POPULAR_NEWS_6 or UPDATED_POPULAR_NEWS_6
        defaultFrontpageconfigShouldBeFound("popularNews6.in=" + DEFAULT_POPULAR_NEWS_6 + "," + UPDATED_POPULAR_NEWS_6);

        // Get all the frontpageconfigList where popularNews6 equals to UPDATED_POPULAR_NEWS_6
        defaultFrontpageconfigShouldNotBeFound("popularNews6.in=" + UPDATED_POPULAR_NEWS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews6IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews6 is not null
        defaultFrontpageconfigShouldBeFound("popularNews6.specified=true");

        // Get all the frontpageconfigList where popularNews6 is null
        defaultFrontpageconfigShouldNotBeFound("popularNews6.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews6 greater than or equals to DEFAULT_POPULAR_NEWS_6
        defaultFrontpageconfigShouldBeFound("popularNews6.greaterOrEqualThan=" + DEFAULT_POPULAR_NEWS_6);

        // Get all the frontpageconfigList where popularNews6 greater than or equals to UPDATED_POPULAR_NEWS_6
        defaultFrontpageconfigShouldNotBeFound("popularNews6.greaterOrEqualThan=" + UPDATED_POPULAR_NEWS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews6IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews6 less than or equals to DEFAULT_POPULAR_NEWS_6
        defaultFrontpageconfigShouldNotBeFound("popularNews6.lessThan=" + DEFAULT_POPULAR_NEWS_6);

        // Get all the frontpageconfigList where popularNews6 less than or equals to UPDATED_POPULAR_NEWS_6
        defaultFrontpageconfigShouldBeFound("popularNews6.lessThan=" + UPDATED_POPULAR_NEWS_6);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews7IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews7 equals to DEFAULT_POPULAR_NEWS_7
        defaultFrontpageconfigShouldBeFound("popularNews7.equals=" + DEFAULT_POPULAR_NEWS_7);

        // Get all the frontpageconfigList where popularNews7 equals to UPDATED_POPULAR_NEWS_7
        defaultFrontpageconfigShouldNotBeFound("popularNews7.equals=" + UPDATED_POPULAR_NEWS_7);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews7IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews7 in DEFAULT_POPULAR_NEWS_7 or UPDATED_POPULAR_NEWS_7
        defaultFrontpageconfigShouldBeFound("popularNews7.in=" + DEFAULT_POPULAR_NEWS_7 + "," + UPDATED_POPULAR_NEWS_7);

        // Get all the frontpageconfigList where popularNews7 equals to UPDATED_POPULAR_NEWS_7
        defaultFrontpageconfigShouldNotBeFound("popularNews7.in=" + UPDATED_POPULAR_NEWS_7);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews7IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews7 is not null
        defaultFrontpageconfigShouldBeFound("popularNews7.specified=true");

        // Get all the frontpageconfigList where popularNews7 is null
        defaultFrontpageconfigShouldNotBeFound("popularNews7.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews7IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews7 greater than or equals to DEFAULT_POPULAR_NEWS_7
        defaultFrontpageconfigShouldBeFound("popularNews7.greaterOrEqualThan=" + DEFAULT_POPULAR_NEWS_7);

        // Get all the frontpageconfigList where popularNews7 greater than or equals to UPDATED_POPULAR_NEWS_7
        defaultFrontpageconfigShouldNotBeFound("popularNews7.greaterOrEqualThan=" + UPDATED_POPULAR_NEWS_7);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews7IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews7 less than or equals to DEFAULT_POPULAR_NEWS_7
        defaultFrontpageconfigShouldNotBeFound("popularNews7.lessThan=" + DEFAULT_POPULAR_NEWS_7);

        // Get all the frontpageconfigList where popularNews7 less than or equals to UPDATED_POPULAR_NEWS_7
        defaultFrontpageconfigShouldBeFound("popularNews7.lessThan=" + UPDATED_POPULAR_NEWS_7);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews8IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews8 equals to DEFAULT_POPULAR_NEWS_8
        defaultFrontpageconfigShouldBeFound("popularNews8.equals=" + DEFAULT_POPULAR_NEWS_8);

        // Get all the frontpageconfigList where popularNews8 equals to UPDATED_POPULAR_NEWS_8
        defaultFrontpageconfigShouldNotBeFound("popularNews8.equals=" + UPDATED_POPULAR_NEWS_8);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews8IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews8 in DEFAULT_POPULAR_NEWS_8 or UPDATED_POPULAR_NEWS_8
        defaultFrontpageconfigShouldBeFound("popularNews8.in=" + DEFAULT_POPULAR_NEWS_8 + "," + UPDATED_POPULAR_NEWS_8);

        // Get all the frontpageconfigList where popularNews8 equals to UPDATED_POPULAR_NEWS_8
        defaultFrontpageconfigShouldNotBeFound("popularNews8.in=" + UPDATED_POPULAR_NEWS_8);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews8IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews8 is not null
        defaultFrontpageconfigShouldBeFound("popularNews8.specified=true");

        // Get all the frontpageconfigList where popularNews8 is null
        defaultFrontpageconfigShouldNotBeFound("popularNews8.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews8IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews8 greater than or equals to DEFAULT_POPULAR_NEWS_8
        defaultFrontpageconfigShouldBeFound("popularNews8.greaterOrEqualThan=" + DEFAULT_POPULAR_NEWS_8);

        // Get all the frontpageconfigList where popularNews8 greater than or equals to UPDATED_POPULAR_NEWS_8
        defaultFrontpageconfigShouldNotBeFound("popularNews8.greaterOrEqualThan=" + UPDATED_POPULAR_NEWS_8);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByPopularNews8IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where popularNews8 less than or equals to DEFAULT_POPULAR_NEWS_8
        defaultFrontpageconfigShouldNotBeFound("popularNews8.lessThan=" + DEFAULT_POPULAR_NEWS_8);

        // Get all the frontpageconfigList where popularNews8 less than or equals to UPDATED_POPULAR_NEWS_8
        defaultFrontpageconfigShouldBeFound("popularNews8.lessThan=" + UPDATED_POPULAR_NEWS_8);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews1IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews1 equals to DEFAULT_WEEKLY_NEWS_1
        defaultFrontpageconfigShouldBeFound("weeklyNews1.equals=" + DEFAULT_WEEKLY_NEWS_1);

        // Get all the frontpageconfigList where weeklyNews1 equals to UPDATED_WEEKLY_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("weeklyNews1.equals=" + UPDATED_WEEKLY_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews1IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews1 in DEFAULT_WEEKLY_NEWS_1 or UPDATED_WEEKLY_NEWS_1
        defaultFrontpageconfigShouldBeFound("weeklyNews1.in=" + DEFAULT_WEEKLY_NEWS_1 + "," + UPDATED_WEEKLY_NEWS_1);

        // Get all the frontpageconfigList where weeklyNews1 equals to UPDATED_WEEKLY_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("weeklyNews1.in=" + UPDATED_WEEKLY_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews1IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews1 is not null
        defaultFrontpageconfigShouldBeFound("weeklyNews1.specified=true");

        // Get all the frontpageconfigList where weeklyNews1 is null
        defaultFrontpageconfigShouldNotBeFound("weeklyNews1.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews1 greater than or equals to DEFAULT_WEEKLY_NEWS_1
        defaultFrontpageconfigShouldBeFound("weeklyNews1.greaterOrEqualThan=" + DEFAULT_WEEKLY_NEWS_1);

        // Get all the frontpageconfigList where weeklyNews1 greater than or equals to UPDATED_WEEKLY_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("weeklyNews1.greaterOrEqualThan=" + UPDATED_WEEKLY_NEWS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews1IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews1 less than or equals to DEFAULT_WEEKLY_NEWS_1
        defaultFrontpageconfigShouldNotBeFound("weeklyNews1.lessThan=" + DEFAULT_WEEKLY_NEWS_1);

        // Get all the frontpageconfigList where weeklyNews1 less than or equals to UPDATED_WEEKLY_NEWS_1
        defaultFrontpageconfigShouldBeFound("weeklyNews1.lessThan=" + UPDATED_WEEKLY_NEWS_1);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews2IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews2 equals to DEFAULT_WEEKLY_NEWS_2
        defaultFrontpageconfigShouldBeFound("weeklyNews2.equals=" + DEFAULT_WEEKLY_NEWS_2);

        // Get all the frontpageconfigList where weeklyNews2 equals to UPDATED_WEEKLY_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("weeklyNews2.equals=" + UPDATED_WEEKLY_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews2IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews2 in DEFAULT_WEEKLY_NEWS_2 or UPDATED_WEEKLY_NEWS_2
        defaultFrontpageconfigShouldBeFound("weeklyNews2.in=" + DEFAULT_WEEKLY_NEWS_2 + "," + UPDATED_WEEKLY_NEWS_2);

        // Get all the frontpageconfigList where weeklyNews2 equals to UPDATED_WEEKLY_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("weeklyNews2.in=" + UPDATED_WEEKLY_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews2IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews2 is not null
        defaultFrontpageconfigShouldBeFound("weeklyNews2.specified=true");

        // Get all the frontpageconfigList where weeklyNews2 is null
        defaultFrontpageconfigShouldNotBeFound("weeklyNews2.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews2 greater than or equals to DEFAULT_WEEKLY_NEWS_2
        defaultFrontpageconfigShouldBeFound("weeklyNews2.greaterOrEqualThan=" + DEFAULT_WEEKLY_NEWS_2);

        // Get all the frontpageconfigList where weeklyNews2 greater than or equals to UPDATED_WEEKLY_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("weeklyNews2.greaterOrEqualThan=" + UPDATED_WEEKLY_NEWS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews2IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews2 less than or equals to DEFAULT_WEEKLY_NEWS_2
        defaultFrontpageconfigShouldNotBeFound("weeklyNews2.lessThan=" + DEFAULT_WEEKLY_NEWS_2);

        // Get all the frontpageconfigList where weeklyNews2 less than or equals to UPDATED_WEEKLY_NEWS_2
        defaultFrontpageconfigShouldBeFound("weeklyNews2.lessThan=" + UPDATED_WEEKLY_NEWS_2);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews3IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews3 equals to DEFAULT_WEEKLY_NEWS_3
        defaultFrontpageconfigShouldBeFound("weeklyNews3.equals=" + DEFAULT_WEEKLY_NEWS_3);

        // Get all the frontpageconfigList where weeklyNews3 equals to UPDATED_WEEKLY_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("weeklyNews3.equals=" + UPDATED_WEEKLY_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews3IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews3 in DEFAULT_WEEKLY_NEWS_3 or UPDATED_WEEKLY_NEWS_3
        defaultFrontpageconfigShouldBeFound("weeklyNews3.in=" + DEFAULT_WEEKLY_NEWS_3 + "," + UPDATED_WEEKLY_NEWS_3);

        // Get all the frontpageconfigList where weeklyNews3 equals to UPDATED_WEEKLY_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("weeklyNews3.in=" + UPDATED_WEEKLY_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews3IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews3 is not null
        defaultFrontpageconfigShouldBeFound("weeklyNews3.specified=true");

        // Get all the frontpageconfigList where weeklyNews3 is null
        defaultFrontpageconfigShouldNotBeFound("weeklyNews3.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews3 greater than or equals to DEFAULT_WEEKLY_NEWS_3
        defaultFrontpageconfigShouldBeFound("weeklyNews3.greaterOrEqualThan=" + DEFAULT_WEEKLY_NEWS_3);

        // Get all the frontpageconfigList where weeklyNews3 greater than or equals to UPDATED_WEEKLY_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("weeklyNews3.greaterOrEqualThan=" + UPDATED_WEEKLY_NEWS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews3IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews3 less than or equals to DEFAULT_WEEKLY_NEWS_3
        defaultFrontpageconfigShouldNotBeFound("weeklyNews3.lessThan=" + DEFAULT_WEEKLY_NEWS_3);

        // Get all the frontpageconfigList where weeklyNews3 less than or equals to UPDATED_WEEKLY_NEWS_3
        defaultFrontpageconfigShouldBeFound("weeklyNews3.lessThan=" + UPDATED_WEEKLY_NEWS_3);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews4IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews4 equals to DEFAULT_WEEKLY_NEWS_4
        defaultFrontpageconfigShouldBeFound("weeklyNews4.equals=" + DEFAULT_WEEKLY_NEWS_4);

        // Get all the frontpageconfigList where weeklyNews4 equals to UPDATED_WEEKLY_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("weeklyNews4.equals=" + UPDATED_WEEKLY_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews4IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews4 in DEFAULT_WEEKLY_NEWS_4 or UPDATED_WEEKLY_NEWS_4
        defaultFrontpageconfigShouldBeFound("weeklyNews4.in=" + DEFAULT_WEEKLY_NEWS_4 + "," + UPDATED_WEEKLY_NEWS_4);

        // Get all the frontpageconfigList where weeklyNews4 equals to UPDATED_WEEKLY_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("weeklyNews4.in=" + UPDATED_WEEKLY_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews4IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews4 is not null
        defaultFrontpageconfigShouldBeFound("weeklyNews4.specified=true");

        // Get all the frontpageconfigList where weeklyNews4 is null
        defaultFrontpageconfigShouldNotBeFound("weeklyNews4.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews4 greater than or equals to DEFAULT_WEEKLY_NEWS_4
        defaultFrontpageconfigShouldBeFound("weeklyNews4.greaterOrEqualThan=" + DEFAULT_WEEKLY_NEWS_4);

        // Get all the frontpageconfigList where weeklyNews4 greater than or equals to UPDATED_WEEKLY_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("weeklyNews4.greaterOrEqualThan=" + UPDATED_WEEKLY_NEWS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByWeeklyNews4IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where weeklyNews4 less than or equals to DEFAULT_WEEKLY_NEWS_4
        defaultFrontpageconfigShouldNotBeFound("weeklyNews4.lessThan=" + DEFAULT_WEEKLY_NEWS_4);

        // Get all the frontpageconfigList where weeklyNews4 less than or equals to UPDATED_WEEKLY_NEWS_4
        defaultFrontpageconfigShouldBeFound("weeklyNews4.lessThan=" + UPDATED_WEEKLY_NEWS_4);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds1IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds1 equals to DEFAULT_NEWS_FEEDS_1
        defaultFrontpageconfigShouldBeFound("newsFeeds1.equals=" + DEFAULT_NEWS_FEEDS_1);

        // Get all the frontpageconfigList where newsFeeds1 equals to UPDATED_NEWS_FEEDS_1
        defaultFrontpageconfigShouldNotBeFound("newsFeeds1.equals=" + UPDATED_NEWS_FEEDS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds1IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds1 in DEFAULT_NEWS_FEEDS_1 or UPDATED_NEWS_FEEDS_1
        defaultFrontpageconfigShouldBeFound("newsFeeds1.in=" + DEFAULT_NEWS_FEEDS_1 + "," + UPDATED_NEWS_FEEDS_1);

        // Get all the frontpageconfigList where newsFeeds1 equals to UPDATED_NEWS_FEEDS_1
        defaultFrontpageconfigShouldNotBeFound("newsFeeds1.in=" + UPDATED_NEWS_FEEDS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds1IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds1 is not null
        defaultFrontpageconfigShouldBeFound("newsFeeds1.specified=true");

        // Get all the frontpageconfigList where newsFeeds1 is null
        defaultFrontpageconfigShouldNotBeFound("newsFeeds1.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds1 greater than or equals to DEFAULT_NEWS_FEEDS_1
        defaultFrontpageconfigShouldBeFound("newsFeeds1.greaterOrEqualThan=" + DEFAULT_NEWS_FEEDS_1);

        // Get all the frontpageconfigList where newsFeeds1 greater than or equals to UPDATED_NEWS_FEEDS_1
        defaultFrontpageconfigShouldNotBeFound("newsFeeds1.greaterOrEqualThan=" + UPDATED_NEWS_FEEDS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds1IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds1 less than or equals to DEFAULT_NEWS_FEEDS_1
        defaultFrontpageconfigShouldNotBeFound("newsFeeds1.lessThan=" + DEFAULT_NEWS_FEEDS_1);

        // Get all the frontpageconfigList where newsFeeds1 less than or equals to UPDATED_NEWS_FEEDS_1
        defaultFrontpageconfigShouldBeFound("newsFeeds1.lessThan=" + UPDATED_NEWS_FEEDS_1);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds2IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds2 equals to DEFAULT_NEWS_FEEDS_2
        defaultFrontpageconfigShouldBeFound("newsFeeds2.equals=" + DEFAULT_NEWS_FEEDS_2);

        // Get all the frontpageconfigList where newsFeeds2 equals to UPDATED_NEWS_FEEDS_2
        defaultFrontpageconfigShouldNotBeFound("newsFeeds2.equals=" + UPDATED_NEWS_FEEDS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds2IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds2 in DEFAULT_NEWS_FEEDS_2 or UPDATED_NEWS_FEEDS_2
        defaultFrontpageconfigShouldBeFound("newsFeeds2.in=" + DEFAULT_NEWS_FEEDS_2 + "," + UPDATED_NEWS_FEEDS_2);

        // Get all the frontpageconfigList where newsFeeds2 equals to UPDATED_NEWS_FEEDS_2
        defaultFrontpageconfigShouldNotBeFound("newsFeeds2.in=" + UPDATED_NEWS_FEEDS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds2IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds2 is not null
        defaultFrontpageconfigShouldBeFound("newsFeeds2.specified=true");

        // Get all the frontpageconfigList where newsFeeds2 is null
        defaultFrontpageconfigShouldNotBeFound("newsFeeds2.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds2 greater than or equals to DEFAULT_NEWS_FEEDS_2
        defaultFrontpageconfigShouldBeFound("newsFeeds2.greaterOrEqualThan=" + DEFAULT_NEWS_FEEDS_2);

        // Get all the frontpageconfigList where newsFeeds2 greater than or equals to UPDATED_NEWS_FEEDS_2
        defaultFrontpageconfigShouldNotBeFound("newsFeeds2.greaterOrEqualThan=" + UPDATED_NEWS_FEEDS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds2IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds2 less than or equals to DEFAULT_NEWS_FEEDS_2
        defaultFrontpageconfigShouldNotBeFound("newsFeeds2.lessThan=" + DEFAULT_NEWS_FEEDS_2);

        // Get all the frontpageconfigList where newsFeeds2 less than or equals to UPDATED_NEWS_FEEDS_2
        defaultFrontpageconfigShouldBeFound("newsFeeds2.lessThan=" + UPDATED_NEWS_FEEDS_2);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds3IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds3 equals to DEFAULT_NEWS_FEEDS_3
        defaultFrontpageconfigShouldBeFound("newsFeeds3.equals=" + DEFAULT_NEWS_FEEDS_3);

        // Get all the frontpageconfigList where newsFeeds3 equals to UPDATED_NEWS_FEEDS_3
        defaultFrontpageconfigShouldNotBeFound("newsFeeds3.equals=" + UPDATED_NEWS_FEEDS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds3IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds3 in DEFAULT_NEWS_FEEDS_3 or UPDATED_NEWS_FEEDS_3
        defaultFrontpageconfigShouldBeFound("newsFeeds3.in=" + DEFAULT_NEWS_FEEDS_3 + "," + UPDATED_NEWS_FEEDS_3);

        // Get all the frontpageconfigList where newsFeeds3 equals to UPDATED_NEWS_FEEDS_3
        defaultFrontpageconfigShouldNotBeFound("newsFeeds3.in=" + UPDATED_NEWS_FEEDS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds3IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds3 is not null
        defaultFrontpageconfigShouldBeFound("newsFeeds3.specified=true");

        // Get all the frontpageconfigList where newsFeeds3 is null
        defaultFrontpageconfigShouldNotBeFound("newsFeeds3.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds3 greater than or equals to DEFAULT_NEWS_FEEDS_3
        defaultFrontpageconfigShouldBeFound("newsFeeds3.greaterOrEqualThan=" + DEFAULT_NEWS_FEEDS_3);

        // Get all the frontpageconfigList where newsFeeds3 greater than or equals to UPDATED_NEWS_FEEDS_3
        defaultFrontpageconfigShouldNotBeFound("newsFeeds3.greaterOrEqualThan=" + UPDATED_NEWS_FEEDS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds3IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds3 less than or equals to DEFAULT_NEWS_FEEDS_3
        defaultFrontpageconfigShouldNotBeFound("newsFeeds3.lessThan=" + DEFAULT_NEWS_FEEDS_3);

        // Get all the frontpageconfigList where newsFeeds3 less than or equals to UPDATED_NEWS_FEEDS_3
        defaultFrontpageconfigShouldBeFound("newsFeeds3.lessThan=" + UPDATED_NEWS_FEEDS_3);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds4IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds4 equals to DEFAULT_NEWS_FEEDS_4
        defaultFrontpageconfigShouldBeFound("newsFeeds4.equals=" + DEFAULT_NEWS_FEEDS_4);

        // Get all the frontpageconfigList where newsFeeds4 equals to UPDATED_NEWS_FEEDS_4
        defaultFrontpageconfigShouldNotBeFound("newsFeeds4.equals=" + UPDATED_NEWS_FEEDS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds4IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds4 in DEFAULT_NEWS_FEEDS_4 or UPDATED_NEWS_FEEDS_4
        defaultFrontpageconfigShouldBeFound("newsFeeds4.in=" + DEFAULT_NEWS_FEEDS_4 + "," + UPDATED_NEWS_FEEDS_4);

        // Get all the frontpageconfigList where newsFeeds4 equals to UPDATED_NEWS_FEEDS_4
        defaultFrontpageconfigShouldNotBeFound("newsFeeds4.in=" + UPDATED_NEWS_FEEDS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds4IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds4 is not null
        defaultFrontpageconfigShouldBeFound("newsFeeds4.specified=true");

        // Get all the frontpageconfigList where newsFeeds4 is null
        defaultFrontpageconfigShouldNotBeFound("newsFeeds4.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds4 greater than or equals to DEFAULT_NEWS_FEEDS_4
        defaultFrontpageconfigShouldBeFound("newsFeeds4.greaterOrEqualThan=" + DEFAULT_NEWS_FEEDS_4);

        // Get all the frontpageconfigList where newsFeeds4 greater than or equals to UPDATED_NEWS_FEEDS_4
        defaultFrontpageconfigShouldNotBeFound("newsFeeds4.greaterOrEqualThan=" + UPDATED_NEWS_FEEDS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds4IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds4 less than or equals to DEFAULT_NEWS_FEEDS_4
        defaultFrontpageconfigShouldNotBeFound("newsFeeds4.lessThan=" + DEFAULT_NEWS_FEEDS_4);

        // Get all the frontpageconfigList where newsFeeds4 less than or equals to UPDATED_NEWS_FEEDS_4
        defaultFrontpageconfigShouldBeFound("newsFeeds4.lessThan=" + UPDATED_NEWS_FEEDS_4);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds5IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds5 equals to DEFAULT_NEWS_FEEDS_5
        defaultFrontpageconfigShouldBeFound("newsFeeds5.equals=" + DEFAULT_NEWS_FEEDS_5);

        // Get all the frontpageconfigList where newsFeeds5 equals to UPDATED_NEWS_FEEDS_5
        defaultFrontpageconfigShouldNotBeFound("newsFeeds5.equals=" + UPDATED_NEWS_FEEDS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds5IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds5 in DEFAULT_NEWS_FEEDS_5 or UPDATED_NEWS_FEEDS_5
        defaultFrontpageconfigShouldBeFound("newsFeeds5.in=" + DEFAULT_NEWS_FEEDS_5 + "," + UPDATED_NEWS_FEEDS_5);

        // Get all the frontpageconfigList where newsFeeds5 equals to UPDATED_NEWS_FEEDS_5
        defaultFrontpageconfigShouldNotBeFound("newsFeeds5.in=" + UPDATED_NEWS_FEEDS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds5IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds5 is not null
        defaultFrontpageconfigShouldBeFound("newsFeeds5.specified=true");

        // Get all the frontpageconfigList where newsFeeds5 is null
        defaultFrontpageconfigShouldNotBeFound("newsFeeds5.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds5 greater than or equals to DEFAULT_NEWS_FEEDS_5
        defaultFrontpageconfigShouldBeFound("newsFeeds5.greaterOrEqualThan=" + DEFAULT_NEWS_FEEDS_5);

        // Get all the frontpageconfigList where newsFeeds5 greater than or equals to UPDATED_NEWS_FEEDS_5
        defaultFrontpageconfigShouldNotBeFound("newsFeeds5.greaterOrEqualThan=" + UPDATED_NEWS_FEEDS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds5IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds5 less than or equals to DEFAULT_NEWS_FEEDS_5
        defaultFrontpageconfigShouldNotBeFound("newsFeeds5.lessThan=" + DEFAULT_NEWS_FEEDS_5);

        // Get all the frontpageconfigList where newsFeeds5 less than or equals to UPDATED_NEWS_FEEDS_5
        defaultFrontpageconfigShouldBeFound("newsFeeds5.lessThan=" + UPDATED_NEWS_FEEDS_5);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds6IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds6 equals to DEFAULT_NEWS_FEEDS_6
        defaultFrontpageconfigShouldBeFound("newsFeeds6.equals=" + DEFAULT_NEWS_FEEDS_6);

        // Get all the frontpageconfigList where newsFeeds6 equals to UPDATED_NEWS_FEEDS_6
        defaultFrontpageconfigShouldNotBeFound("newsFeeds6.equals=" + UPDATED_NEWS_FEEDS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds6IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds6 in DEFAULT_NEWS_FEEDS_6 or UPDATED_NEWS_FEEDS_6
        defaultFrontpageconfigShouldBeFound("newsFeeds6.in=" + DEFAULT_NEWS_FEEDS_6 + "," + UPDATED_NEWS_FEEDS_6);

        // Get all the frontpageconfigList where newsFeeds6 equals to UPDATED_NEWS_FEEDS_6
        defaultFrontpageconfigShouldNotBeFound("newsFeeds6.in=" + UPDATED_NEWS_FEEDS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds6IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds6 is not null
        defaultFrontpageconfigShouldBeFound("newsFeeds6.specified=true");

        // Get all the frontpageconfigList where newsFeeds6 is null
        defaultFrontpageconfigShouldNotBeFound("newsFeeds6.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds6 greater than or equals to DEFAULT_NEWS_FEEDS_6
        defaultFrontpageconfigShouldBeFound("newsFeeds6.greaterOrEqualThan=" + DEFAULT_NEWS_FEEDS_6);

        // Get all the frontpageconfigList where newsFeeds6 greater than or equals to UPDATED_NEWS_FEEDS_6
        defaultFrontpageconfigShouldNotBeFound("newsFeeds6.greaterOrEqualThan=" + UPDATED_NEWS_FEEDS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByNewsFeeds6IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where newsFeeds6 less than or equals to DEFAULT_NEWS_FEEDS_6
        defaultFrontpageconfigShouldNotBeFound("newsFeeds6.lessThan=" + DEFAULT_NEWS_FEEDS_6);

        // Get all the frontpageconfigList where newsFeeds6 less than or equals to UPDATED_NEWS_FEEDS_6
        defaultFrontpageconfigShouldBeFound("newsFeeds6.lessThan=" + UPDATED_NEWS_FEEDS_6);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks1IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks1 equals to DEFAULT_USEFUL_LINKS_1
        defaultFrontpageconfigShouldBeFound("usefulLinks1.equals=" + DEFAULT_USEFUL_LINKS_1);

        // Get all the frontpageconfigList where usefulLinks1 equals to UPDATED_USEFUL_LINKS_1
        defaultFrontpageconfigShouldNotBeFound("usefulLinks1.equals=" + UPDATED_USEFUL_LINKS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks1IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks1 in DEFAULT_USEFUL_LINKS_1 or UPDATED_USEFUL_LINKS_1
        defaultFrontpageconfigShouldBeFound("usefulLinks1.in=" + DEFAULT_USEFUL_LINKS_1 + "," + UPDATED_USEFUL_LINKS_1);

        // Get all the frontpageconfigList where usefulLinks1 equals to UPDATED_USEFUL_LINKS_1
        defaultFrontpageconfigShouldNotBeFound("usefulLinks1.in=" + UPDATED_USEFUL_LINKS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks1IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks1 is not null
        defaultFrontpageconfigShouldBeFound("usefulLinks1.specified=true");

        // Get all the frontpageconfigList where usefulLinks1 is null
        defaultFrontpageconfigShouldNotBeFound("usefulLinks1.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks1 greater than or equals to DEFAULT_USEFUL_LINKS_1
        defaultFrontpageconfigShouldBeFound("usefulLinks1.greaterOrEqualThan=" + DEFAULT_USEFUL_LINKS_1);

        // Get all the frontpageconfigList where usefulLinks1 greater than or equals to UPDATED_USEFUL_LINKS_1
        defaultFrontpageconfigShouldNotBeFound("usefulLinks1.greaterOrEqualThan=" + UPDATED_USEFUL_LINKS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks1IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks1 less than or equals to DEFAULT_USEFUL_LINKS_1
        defaultFrontpageconfigShouldNotBeFound("usefulLinks1.lessThan=" + DEFAULT_USEFUL_LINKS_1);

        // Get all the frontpageconfigList where usefulLinks1 less than or equals to UPDATED_USEFUL_LINKS_1
        defaultFrontpageconfigShouldBeFound("usefulLinks1.lessThan=" + UPDATED_USEFUL_LINKS_1);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks2IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks2 equals to DEFAULT_USEFUL_LINKS_2
        defaultFrontpageconfigShouldBeFound("usefulLinks2.equals=" + DEFAULT_USEFUL_LINKS_2);

        // Get all the frontpageconfigList where usefulLinks2 equals to UPDATED_USEFUL_LINKS_2
        defaultFrontpageconfigShouldNotBeFound("usefulLinks2.equals=" + UPDATED_USEFUL_LINKS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks2IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks2 in DEFAULT_USEFUL_LINKS_2 or UPDATED_USEFUL_LINKS_2
        defaultFrontpageconfigShouldBeFound("usefulLinks2.in=" + DEFAULT_USEFUL_LINKS_2 + "," + UPDATED_USEFUL_LINKS_2);

        // Get all the frontpageconfigList where usefulLinks2 equals to UPDATED_USEFUL_LINKS_2
        defaultFrontpageconfigShouldNotBeFound("usefulLinks2.in=" + UPDATED_USEFUL_LINKS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks2IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks2 is not null
        defaultFrontpageconfigShouldBeFound("usefulLinks2.specified=true");

        // Get all the frontpageconfigList where usefulLinks2 is null
        defaultFrontpageconfigShouldNotBeFound("usefulLinks2.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks2 greater than or equals to DEFAULT_USEFUL_LINKS_2
        defaultFrontpageconfigShouldBeFound("usefulLinks2.greaterOrEqualThan=" + DEFAULT_USEFUL_LINKS_2);

        // Get all the frontpageconfigList where usefulLinks2 greater than or equals to UPDATED_USEFUL_LINKS_2
        defaultFrontpageconfigShouldNotBeFound("usefulLinks2.greaterOrEqualThan=" + UPDATED_USEFUL_LINKS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks2IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks2 less than or equals to DEFAULT_USEFUL_LINKS_2
        defaultFrontpageconfigShouldNotBeFound("usefulLinks2.lessThan=" + DEFAULT_USEFUL_LINKS_2);

        // Get all the frontpageconfigList where usefulLinks2 less than or equals to UPDATED_USEFUL_LINKS_2
        defaultFrontpageconfigShouldBeFound("usefulLinks2.lessThan=" + UPDATED_USEFUL_LINKS_2);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks3IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks3 equals to DEFAULT_USEFUL_LINKS_3
        defaultFrontpageconfigShouldBeFound("usefulLinks3.equals=" + DEFAULT_USEFUL_LINKS_3);

        // Get all the frontpageconfigList where usefulLinks3 equals to UPDATED_USEFUL_LINKS_3
        defaultFrontpageconfigShouldNotBeFound("usefulLinks3.equals=" + UPDATED_USEFUL_LINKS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks3IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks3 in DEFAULT_USEFUL_LINKS_3 or UPDATED_USEFUL_LINKS_3
        defaultFrontpageconfigShouldBeFound("usefulLinks3.in=" + DEFAULT_USEFUL_LINKS_3 + "," + UPDATED_USEFUL_LINKS_3);

        // Get all the frontpageconfigList where usefulLinks3 equals to UPDATED_USEFUL_LINKS_3
        defaultFrontpageconfigShouldNotBeFound("usefulLinks3.in=" + UPDATED_USEFUL_LINKS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks3IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks3 is not null
        defaultFrontpageconfigShouldBeFound("usefulLinks3.specified=true");

        // Get all the frontpageconfigList where usefulLinks3 is null
        defaultFrontpageconfigShouldNotBeFound("usefulLinks3.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks3 greater than or equals to DEFAULT_USEFUL_LINKS_3
        defaultFrontpageconfigShouldBeFound("usefulLinks3.greaterOrEqualThan=" + DEFAULT_USEFUL_LINKS_3);

        // Get all the frontpageconfigList where usefulLinks3 greater than or equals to UPDATED_USEFUL_LINKS_3
        defaultFrontpageconfigShouldNotBeFound("usefulLinks3.greaterOrEqualThan=" + UPDATED_USEFUL_LINKS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks3IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks3 less than or equals to DEFAULT_USEFUL_LINKS_3
        defaultFrontpageconfigShouldNotBeFound("usefulLinks3.lessThan=" + DEFAULT_USEFUL_LINKS_3);

        // Get all the frontpageconfigList where usefulLinks3 less than or equals to UPDATED_USEFUL_LINKS_3
        defaultFrontpageconfigShouldBeFound("usefulLinks3.lessThan=" + UPDATED_USEFUL_LINKS_3);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks4IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks4 equals to DEFAULT_USEFUL_LINKS_4
        defaultFrontpageconfigShouldBeFound("usefulLinks4.equals=" + DEFAULT_USEFUL_LINKS_4);

        // Get all the frontpageconfigList where usefulLinks4 equals to UPDATED_USEFUL_LINKS_4
        defaultFrontpageconfigShouldNotBeFound("usefulLinks4.equals=" + UPDATED_USEFUL_LINKS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks4IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks4 in DEFAULT_USEFUL_LINKS_4 or UPDATED_USEFUL_LINKS_4
        defaultFrontpageconfigShouldBeFound("usefulLinks4.in=" + DEFAULT_USEFUL_LINKS_4 + "," + UPDATED_USEFUL_LINKS_4);

        // Get all the frontpageconfigList where usefulLinks4 equals to UPDATED_USEFUL_LINKS_4
        defaultFrontpageconfigShouldNotBeFound("usefulLinks4.in=" + UPDATED_USEFUL_LINKS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks4IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks4 is not null
        defaultFrontpageconfigShouldBeFound("usefulLinks4.specified=true");

        // Get all the frontpageconfigList where usefulLinks4 is null
        defaultFrontpageconfigShouldNotBeFound("usefulLinks4.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks4 greater than or equals to DEFAULT_USEFUL_LINKS_4
        defaultFrontpageconfigShouldBeFound("usefulLinks4.greaterOrEqualThan=" + DEFAULT_USEFUL_LINKS_4);

        // Get all the frontpageconfigList where usefulLinks4 greater than or equals to UPDATED_USEFUL_LINKS_4
        defaultFrontpageconfigShouldNotBeFound("usefulLinks4.greaterOrEqualThan=" + UPDATED_USEFUL_LINKS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks4IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks4 less than or equals to DEFAULT_USEFUL_LINKS_4
        defaultFrontpageconfigShouldNotBeFound("usefulLinks4.lessThan=" + DEFAULT_USEFUL_LINKS_4);

        // Get all the frontpageconfigList where usefulLinks4 less than or equals to UPDATED_USEFUL_LINKS_4
        defaultFrontpageconfigShouldBeFound("usefulLinks4.lessThan=" + UPDATED_USEFUL_LINKS_4);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks5IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks5 equals to DEFAULT_USEFUL_LINKS_5
        defaultFrontpageconfigShouldBeFound("usefulLinks5.equals=" + DEFAULT_USEFUL_LINKS_5);

        // Get all the frontpageconfigList where usefulLinks5 equals to UPDATED_USEFUL_LINKS_5
        defaultFrontpageconfigShouldNotBeFound("usefulLinks5.equals=" + UPDATED_USEFUL_LINKS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks5IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks5 in DEFAULT_USEFUL_LINKS_5 or UPDATED_USEFUL_LINKS_5
        defaultFrontpageconfigShouldBeFound("usefulLinks5.in=" + DEFAULT_USEFUL_LINKS_5 + "," + UPDATED_USEFUL_LINKS_5);

        // Get all the frontpageconfigList where usefulLinks5 equals to UPDATED_USEFUL_LINKS_5
        defaultFrontpageconfigShouldNotBeFound("usefulLinks5.in=" + UPDATED_USEFUL_LINKS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks5IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks5 is not null
        defaultFrontpageconfigShouldBeFound("usefulLinks5.specified=true");

        // Get all the frontpageconfigList where usefulLinks5 is null
        defaultFrontpageconfigShouldNotBeFound("usefulLinks5.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks5 greater than or equals to DEFAULT_USEFUL_LINKS_5
        defaultFrontpageconfigShouldBeFound("usefulLinks5.greaterOrEqualThan=" + DEFAULT_USEFUL_LINKS_5);

        // Get all the frontpageconfigList where usefulLinks5 greater than or equals to UPDATED_USEFUL_LINKS_5
        defaultFrontpageconfigShouldNotBeFound("usefulLinks5.greaterOrEqualThan=" + UPDATED_USEFUL_LINKS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks5IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks5 less than or equals to DEFAULT_USEFUL_LINKS_5
        defaultFrontpageconfigShouldNotBeFound("usefulLinks5.lessThan=" + DEFAULT_USEFUL_LINKS_5);

        // Get all the frontpageconfigList where usefulLinks5 less than or equals to UPDATED_USEFUL_LINKS_5
        defaultFrontpageconfigShouldBeFound("usefulLinks5.lessThan=" + UPDATED_USEFUL_LINKS_5);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks6IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks6 equals to DEFAULT_USEFUL_LINKS_6
        defaultFrontpageconfigShouldBeFound("usefulLinks6.equals=" + DEFAULT_USEFUL_LINKS_6);

        // Get all the frontpageconfigList where usefulLinks6 equals to UPDATED_USEFUL_LINKS_6
        defaultFrontpageconfigShouldNotBeFound("usefulLinks6.equals=" + UPDATED_USEFUL_LINKS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks6IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks6 in DEFAULT_USEFUL_LINKS_6 or UPDATED_USEFUL_LINKS_6
        defaultFrontpageconfigShouldBeFound("usefulLinks6.in=" + DEFAULT_USEFUL_LINKS_6 + "," + UPDATED_USEFUL_LINKS_6);

        // Get all the frontpageconfigList where usefulLinks6 equals to UPDATED_USEFUL_LINKS_6
        defaultFrontpageconfigShouldNotBeFound("usefulLinks6.in=" + UPDATED_USEFUL_LINKS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks6IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks6 is not null
        defaultFrontpageconfigShouldBeFound("usefulLinks6.specified=true");

        // Get all the frontpageconfigList where usefulLinks6 is null
        defaultFrontpageconfigShouldNotBeFound("usefulLinks6.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks6 greater than or equals to DEFAULT_USEFUL_LINKS_6
        defaultFrontpageconfigShouldBeFound("usefulLinks6.greaterOrEqualThan=" + DEFAULT_USEFUL_LINKS_6);

        // Get all the frontpageconfigList where usefulLinks6 greater than or equals to UPDATED_USEFUL_LINKS_6
        defaultFrontpageconfigShouldNotBeFound("usefulLinks6.greaterOrEqualThan=" + UPDATED_USEFUL_LINKS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByUsefulLinks6IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where usefulLinks6 less than or equals to DEFAULT_USEFUL_LINKS_6
        defaultFrontpageconfigShouldNotBeFound("usefulLinks6.lessThan=" + DEFAULT_USEFUL_LINKS_6);

        // Get all the frontpageconfigList where usefulLinks6 less than or equals to UPDATED_USEFUL_LINKS_6
        defaultFrontpageconfigShouldBeFound("usefulLinks6.lessThan=" + UPDATED_USEFUL_LINKS_6);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos1IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos1 equals to DEFAULT_RECENT_VIDEOS_1
        defaultFrontpageconfigShouldBeFound("recentVideos1.equals=" + DEFAULT_RECENT_VIDEOS_1);

        // Get all the frontpageconfigList where recentVideos1 equals to UPDATED_RECENT_VIDEOS_1
        defaultFrontpageconfigShouldNotBeFound("recentVideos1.equals=" + UPDATED_RECENT_VIDEOS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos1IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos1 in DEFAULT_RECENT_VIDEOS_1 or UPDATED_RECENT_VIDEOS_1
        defaultFrontpageconfigShouldBeFound("recentVideos1.in=" + DEFAULT_RECENT_VIDEOS_1 + "," + UPDATED_RECENT_VIDEOS_1);

        // Get all the frontpageconfigList where recentVideos1 equals to UPDATED_RECENT_VIDEOS_1
        defaultFrontpageconfigShouldNotBeFound("recentVideos1.in=" + UPDATED_RECENT_VIDEOS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos1IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos1 is not null
        defaultFrontpageconfigShouldBeFound("recentVideos1.specified=true");

        // Get all the frontpageconfigList where recentVideos1 is null
        defaultFrontpageconfigShouldNotBeFound("recentVideos1.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos1 greater than or equals to DEFAULT_RECENT_VIDEOS_1
        defaultFrontpageconfigShouldBeFound("recentVideos1.greaterOrEqualThan=" + DEFAULT_RECENT_VIDEOS_1);

        // Get all the frontpageconfigList where recentVideos1 greater than or equals to UPDATED_RECENT_VIDEOS_1
        defaultFrontpageconfigShouldNotBeFound("recentVideos1.greaterOrEqualThan=" + UPDATED_RECENT_VIDEOS_1);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos1IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos1 less than or equals to DEFAULT_RECENT_VIDEOS_1
        defaultFrontpageconfigShouldNotBeFound("recentVideos1.lessThan=" + DEFAULT_RECENT_VIDEOS_1);

        // Get all the frontpageconfigList where recentVideos1 less than or equals to UPDATED_RECENT_VIDEOS_1
        defaultFrontpageconfigShouldBeFound("recentVideos1.lessThan=" + UPDATED_RECENT_VIDEOS_1);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos2IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos2 equals to DEFAULT_RECENT_VIDEOS_2
        defaultFrontpageconfigShouldBeFound("recentVideos2.equals=" + DEFAULT_RECENT_VIDEOS_2);

        // Get all the frontpageconfigList where recentVideos2 equals to UPDATED_RECENT_VIDEOS_2
        defaultFrontpageconfigShouldNotBeFound("recentVideos2.equals=" + UPDATED_RECENT_VIDEOS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos2IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos2 in DEFAULT_RECENT_VIDEOS_2 or UPDATED_RECENT_VIDEOS_2
        defaultFrontpageconfigShouldBeFound("recentVideos2.in=" + DEFAULT_RECENT_VIDEOS_2 + "," + UPDATED_RECENT_VIDEOS_2);

        // Get all the frontpageconfigList where recentVideos2 equals to UPDATED_RECENT_VIDEOS_2
        defaultFrontpageconfigShouldNotBeFound("recentVideos2.in=" + UPDATED_RECENT_VIDEOS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos2IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos2 is not null
        defaultFrontpageconfigShouldBeFound("recentVideos2.specified=true");

        // Get all the frontpageconfigList where recentVideos2 is null
        defaultFrontpageconfigShouldNotBeFound("recentVideos2.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos2 greater than or equals to DEFAULT_RECENT_VIDEOS_2
        defaultFrontpageconfigShouldBeFound("recentVideos2.greaterOrEqualThan=" + DEFAULT_RECENT_VIDEOS_2);

        // Get all the frontpageconfigList where recentVideos2 greater than or equals to UPDATED_RECENT_VIDEOS_2
        defaultFrontpageconfigShouldNotBeFound("recentVideos2.greaterOrEqualThan=" + UPDATED_RECENT_VIDEOS_2);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos2IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos2 less than or equals to DEFAULT_RECENT_VIDEOS_2
        defaultFrontpageconfigShouldNotBeFound("recentVideos2.lessThan=" + DEFAULT_RECENT_VIDEOS_2);

        // Get all the frontpageconfigList where recentVideos2 less than or equals to UPDATED_RECENT_VIDEOS_2
        defaultFrontpageconfigShouldBeFound("recentVideos2.lessThan=" + UPDATED_RECENT_VIDEOS_2);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos3IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos3 equals to DEFAULT_RECENT_VIDEOS_3
        defaultFrontpageconfigShouldBeFound("recentVideos3.equals=" + DEFAULT_RECENT_VIDEOS_3);

        // Get all the frontpageconfigList where recentVideos3 equals to UPDATED_RECENT_VIDEOS_3
        defaultFrontpageconfigShouldNotBeFound("recentVideos3.equals=" + UPDATED_RECENT_VIDEOS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos3IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos3 in DEFAULT_RECENT_VIDEOS_3 or UPDATED_RECENT_VIDEOS_3
        defaultFrontpageconfigShouldBeFound("recentVideos3.in=" + DEFAULT_RECENT_VIDEOS_3 + "," + UPDATED_RECENT_VIDEOS_3);

        // Get all the frontpageconfigList where recentVideos3 equals to UPDATED_RECENT_VIDEOS_3
        defaultFrontpageconfigShouldNotBeFound("recentVideos3.in=" + UPDATED_RECENT_VIDEOS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos3IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos3 is not null
        defaultFrontpageconfigShouldBeFound("recentVideos3.specified=true");

        // Get all the frontpageconfigList where recentVideos3 is null
        defaultFrontpageconfigShouldNotBeFound("recentVideos3.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos3 greater than or equals to DEFAULT_RECENT_VIDEOS_3
        defaultFrontpageconfigShouldBeFound("recentVideos3.greaterOrEqualThan=" + DEFAULT_RECENT_VIDEOS_3);

        // Get all the frontpageconfigList where recentVideos3 greater than or equals to UPDATED_RECENT_VIDEOS_3
        defaultFrontpageconfigShouldNotBeFound("recentVideos3.greaterOrEqualThan=" + UPDATED_RECENT_VIDEOS_3);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos3IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos3 less than or equals to DEFAULT_RECENT_VIDEOS_3
        defaultFrontpageconfigShouldNotBeFound("recentVideos3.lessThan=" + DEFAULT_RECENT_VIDEOS_3);

        // Get all the frontpageconfigList where recentVideos3 less than or equals to UPDATED_RECENT_VIDEOS_3
        defaultFrontpageconfigShouldBeFound("recentVideos3.lessThan=" + UPDATED_RECENT_VIDEOS_3);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos4IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos4 equals to DEFAULT_RECENT_VIDEOS_4
        defaultFrontpageconfigShouldBeFound("recentVideos4.equals=" + DEFAULT_RECENT_VIDEOS_4);

        // Get all the frontpageconfigList where recentVideos4 equals to UPDATED_RECENT_VIDEOS_4
        defaultFrontpageconfigShouldNotBeFound("recentVideos4.equals=" + UPDATED_RECENT_VIDEOS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos4IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos4 in DEFAULT_RECENT_VIDEOS_4 or UPDATED_RECENT_VIDEOS_4
        defaultFrontpageconfigShouldBeFound("recentVideos4.in=" + DEFAULT_RECENT_VIDEOS_4 + "," + UPDATED_RECENT_VIDEOS_4);

        // Get all the frontpageconfigList where recentVideos4 equals to UPDATED_RECENT_VIDEOS_4
        defaultFrontpageconfigShouldNotBeFound("recentVideos4.in=" + UPDATED_RECENT_VIDEOS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos4IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos4 is not null
        defaultFrontpageconfigShouldBeFound("recentVideos4.specified=true");

        // Get all the frontpageconfigList where recentVideos4 is null
        defaultFrontpageconfigShouldNotBeFound("recentVideos4.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos4 greater than or equals to DEFAULT_RECENT_VIDEOS_4
        defaultFrontpageconfigShouldBeFound("recentVideos4.greaterOrEqualThan=" + DEFAULT_RECENT_VIDEOS_4);

        // Get all the frontpageconfigList where recentVideos4 greater than or equals to UPDATED_RECENT_VIDEOS_4
        defaultFrontpageconfigShouldNotBeFound("recentVideos4.greaterOrEqualThan=" + UPDATED_RECENT_VIDEOS_4);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos4IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos4 less than or equals to DEFAULT_RECENT_VIDEOS_4
        defaultFrontpageconfigShouldNotBeFound("recentVideos4.lessThan=" + DEFAULT_RECENT_VIDEOS_4);

        // Get all the frontpageconfigList where recentVideos4 less than or equals to UPDATED_RECENT_VIDEOS_4
        defaultFrontpageconfigShouldBeFound("recentVideos4.lessThan=" + UPDATED_RECENT_VIDEOS_4);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos5IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos5 equals to DEFAULT_RECENT_VIDEOS_5
        defaultFrontpageconfigShouldBeFound("recentVideos5.equals=" + DEFAULT_RECENT_VIDEOS_5);

        // Get all the frontpageconfigList where recentVideos5 equals to UPDATED_RECENT_VIDEOS_5
        defaultFrontpageconfigShouldNotBeFound("recentVideos5.equals=" + UPDATED_RECENT_VIDEOS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos5IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos5 in DEFAULT_RECENT_VIDEOS_5 or UPDATED_RECENT_VIDEOS_5
        defaultFrontpageconfigShouldBeFound("recentVideos5.in=" + DEFAULT_RECENT_VIDEOS_5 + "," + UPDATED_RECENT_VIDEOS_5);

        // Get all the frontpageconfigList where recentVideos5 equals to UPDATED_RECENT_VIDEOS_5
        defaultFrontpageconfigShouldNotBeFound("recentVideos5.in=" + UPDATED_RECENT_VIDEOS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos5IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos5 is not null
        defaultFrontpageconfigShouldBeFound("recentVideos5.specified=true");

        // Get all the frontpageconfigList where recentVideos5 is null
        defaultFrontpageconfigShouldNotBeFound("recentVideos5.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos5 greater than or equals to DEFAULT_RECENT_VIDEOS_5
        defaultFrontpageconfigShouldBeFound("recentVideos5.greaterOrEqualThan=" + DEFAULT_RECENT_VIDEOS_5);

        // Get all the frontpageconfigList where recentVideos5 greater than or equals to UPDATED_RECENT_VIDEOS_5
        defaultFrontpageconfigShouldNotBeFound("recentVideos5.greaterOrEqualThan=" + UPDATED_RECENT_VIDEOS_5);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos5IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos5 less than or equals to DEFAULT_RECENT_VIDEOS_5
        defaultFrontpageconfigShouldNotBeFound("recentVideos5.lessThan=" + DEFAULT_RECENT_VIDEOS_5);

        // Get all the frontpageconfigList where recentVideos5 less than or equals to UPDATED_RECENT_VIDEOS_5
        defaultFrontpageconfigShouldBeFound("recentVideos5.lessThan=" + UPDATED_RECENT_VIDEOS_5);
    }


    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos6IsEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos6 equals to DEFAULT_RECENT_VIDEOS_6
        defaultFrontpageconfigShouldBeFound("recentVideos6.equals=" + DEFAULT_RECENT_VIDEOS_6);

        // Get all the frontpageconfigList where recentVideos6 equals to UPDATED_RECENT_VIDEOS_6
        defaultFrontpageconfigShouldNotBeFound("recentVideos6.equals=" + UPDATED_RECENT_VIDEOS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos6IsInShouldWork() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos6 in DEFAULT_RECENT_VIDEOS_6 or UPDATED_RECENT_VIDEOS_6
        defaultFrontpageconfigShouldBeFound("recentVideos6.in=" + DEFAULT_RECENT_VIDEOS_6 + "," + UPDATED_RECENT_VIDEOS_6);

        // Get all the frontpageconfigList where recentVideos6 equals to UPDATED_RECENT_VIDEOS_6
        defaultFrontpageconfigShouldNotBeFound("recentVideos6.in=" + UPDATED_RECENT_VIDEOS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos6IsNullOrNotNull() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos6 is not null
        defaultFrontpageconfigShouldBeFound("recentVideos6.specified=true");

        // Get all the frontpageconfigList where recentVideos6 is null
        defaultFrontpageconfigShouldNotBeFound("recentVideos6.specified=false");
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos6 greater than or equals to DEFAULT_RECENT_VIDEOS_6
        defaultFrontpageconfigShouldBeFound("recentVideos6.greaterOrEqualThan=" + DEFAULT_RECENT_VIDEOS_6);

        // Get all the frontpageconfigList where recentVideos6 greater than or equals to UPDATED_RECENT_VIDEOS_6
        defaultFrontpageconfigShouldNotBeFound("recentVideos6.greaterOrEqualThan=" + UPDATED_RECENT_VIDEOS_6);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigsByRecentVideos6IsLessThanSomething() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList where recentVideos6 less than or equals to DEFAULT_RECENT_VIDEOS_6
        defaultFrontpageconfigShouldNotBeFound("recentVideos6.lessThan=" + DEFAULT_RECENT_VIDEOS_6);

        // Get all the frontpageconfigList where recentVideos6 less than or equals to UPDATED_RECENT_VIDEOS_6
        defaultFrontpageconfigShouldBeFound("recentVideos6.lessThan=" + UPDATED_RECENT_VIDEOS_6);
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultFrontpageconfigShouldBeFound(String filter) throws Exception {
        restFrontpageconfigMockMvc.perform(get("/api/frontpageconfigs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(frontpageconfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].topNews1").value(hasItem(DEFAULT_TOP_NEWS_1.intValue())))
            .andExpect(jsonPath("$.[*].topNews2").value(hasItem(DEFAULT_TOP_NEWS_2.intValue())))
            .andExpect(jsonPath("$.[*].topNews3").value(hasItem(DEFAULT_TOP_NEWS_3.intValue())))
            .andExpect(jsonPath("$.[*].topNews4").value(hasItem(DEFAULT_TOP_NEWS_4.intValue())))
            .andExpect(jsonPath("$.[*].topNews5").value(hasItem(DEFAULT_TOP_NEWS_5.intValue())))
            .andExpect(jsonPath("$.[*].latestNews1").value(hasItem(DEFAULT_LATEST_NEWS_1.intValue())))
            .andExpect(jsonPath("$.[*].latestNews2").value(hasItem(DEFAULT_LATEST_NEWS_2.intValue())))
            .andExpect(jsonPath("$.[*].latestNews3").value(hasItem(DEFAULT_LATEST_NEWS_3.intValue())))
            .andExpect(jsonPath("$.[*].latestNews4").value(hasItem(DEFAULT_LATEST_NEWS_4.intValue())))
            .andExpect(jsonPath("$.[*].latestNews5").value(hasItem(DEFAULT_LATEST_NEWS_5.intValue())))
            .andExpect(jsonPath("$.[*].breakingNews1").value(hasItem(DEFAULT_BREAKING_NEWS_1.intValue())))
            .andExpect(jsonPath("$.[*].recentPosts1").value(hasItem(DEFAULT_RECENT_POSTS_1.intValue())))
            .andExpect(jsonPath("$.[*].recentPosts2").value(hasItem(DEFAULT_RECENT_POSTS_2.intValue())))
            .andExpect(jsonPath("$.[*].recentPosts3").value(hasItem(DEFAULT_RECENT_POSTS_3.intValue())))
            .andExpect(jsonPath("$.[*].recentPosts4").value(hasItem(DEFAULT_RECENT_POSTS_4.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles1").value(hasItem(DEFAULT_FEATURED_ARTICLES_1.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles2").value(hasItem(DEFAULT_FEATURED_ARTICLES_2.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles3").value(hasItem(DEFAULT_FEATURED_ARTICLES_3.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles4").value(hasItem(DEFAULT_FEATURED_ARTICLES_4.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles5").value(hasItem(DEFAULT_FEATURED_ARTICLES_5.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles6").value(hasItem(DEFAULT_FEATURED_ARTICLES_6.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles7").value(hasItem(DEFAULT_FEATURED_ARTICLES_7.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles8").value(hasItem(DEFAULT_FEATURED_ARTICLES_8.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles9").value(hasItem(DEFAULT_FEATURED_ARTICLES_9.intValue())))
            .andExpect(jsonPath("$.[*].featuredArticles10").value(hasItem(DEFAULT_FEATURED_ARTICLES_10.intValue())))
            .andExpect(jsonPath("$.[*].popularNews1").value(hasItem(DEFAULT_POPULAR_NEWS_1.intValue())))
            .andExpect(jsonPath("$.[*].popularNews2").value(hasItem(DEFAULT_POPULAR_NEWS_2.intValue())))
            .andExpect(jsonPath("$.[*].popularNews3").value(hasItem(DEFAULT_POPULAR_NEWS_3.intValue())))
            .andExpect(jsonPath("$.[*].popularNews4").value(hasItem(DEFAULT_POPULAR_NEWS_4.intValue())))
            .andExpect(jsonPath("$.[*].popularNews5").value(hasItem(DEFAULT_POPULAR_NEWS_5.intValue())))
            .andExpect(jsonPath("$.[*].popularNews6").value(hasItem(DEFAULT_POPULAR_NEWS_6.intValue())))
            .andExpect(jsonPath("$.[*].popularNews7").value(hasItem(DEFAULT_POPULAR_NEWS_7.intValue())))
            .andExpect(jsonPath("$.[*].popularNews8").value(hasItem(DEFAULT_POPULAR_NEWS_8.intValue())))
            .andExpect(jsonPath("$.[*].weeklyNews1").value(hasItem(DEFAULT_WEEKLY_NEWS_1.intValue())))
            .andExpect(jsonPath("$.[*].weeklyNews2").value(hasItem(DEFAULT_WEEKLY_NEWS_2.intValue())))
            .andExpect(jsonPath("$.[*].weeklyNews3").value(hasItem(DEFAULT_WEEKLY_NEWS_3.intValue())))
            .andExpect(jsonPath("$.[*].weeklyNews4").value(hasItem(DEFAULT_WEEKLY_NEWS_4.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds1").value(hasItem(DEFAULT_NEWS_FEEDS_1.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds2").value(hasItem(DEFAULT_NEWS_FEEDS_2.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds3").value(hasItem(DEFAULT_NEWS_FEEDS_3.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds4").value(hasItem(DEFAULT_NEWS_FEEDS_4.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds5").value(hasItem(DEFAULT_NEWS_FEEDS_5.intValue())))
            .andExpect(jsonPath("$.[*].newsFeeds6").value(hasItem(DEFAULT_NEWS_FEEDS_6.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks1").value(hasItem(DEFAULT_USEFUL_LINKS_1.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks2").value(hasItem(DEFAULT_USEFUL_LINKS_2.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks3").value(hasItem(DEFAULT_USEFUL_LINKS_3.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks4").value(hasItem(DEFAULT_USEFUL_LINKS_4.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks5").value(hasItem(DEFAULT_USEFUL_LINKS_5.intValue())))
            .andExpect(jsonPath("$.[*].usefulLinks6").value(hasItem(DEFAULT_USEFUL_LINKS_6.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos1").value(hasItem(DEFAULT_RECENT_VIDEOS_1.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos2").value(hasItem(DEFAULT_RECENT_VIDEOS_2.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos3").value(hasItem(DEFAULT_RECENT_VIDEOS_3.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos4").value(hasItem(DEFAULT_RECENT_VIDEOS_4.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos5").value(hasItem(DEFAULT_RECENT_VIDEOS_5.intValue())))
            .andExpect(jsonPath("$.[*].recentVideos6").value(hasItem(DEFAULT_RECENT_VIDEOS_6.intValue())));

        // Check, that the count call also returns 1
        restFrontpageconfigMockMvc.perform(get("/api/frontpageconfigs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultFrontpageconfigShouldNotBeFound(String filter) throws Exception {
        restFrontpageconfigMockMvc.perform(get("/api/frontpageconfigs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFrontpageconfigMockMvc.perform(get("/api/frontpageconfigs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingFrontpageconfig() throws Exception {
        // Get the frontpageconfig
        restFrontpageconfigMockMvc.perform(get("/api/frontpageconfigs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFrontpageconfig() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        int databaseSizeBeforeUpdate = frontpageconfigRepository.findAll().size();

        // Update the frontpageconfig
        Frontpageconfig updatedFrontpageconfig = frontpageconfigRepository.findById(frontpageconfig.getId()).get();
        // Disconnect from session so that the updates on updatedFrontpageconfig are not directly saved in db
        em.detach(updatedFrontpageconfig);
        updatedFrontpageconfig
            .creationDate(UPDATED_CREATION_DATE)
            .topNews1(UPDATED_TOP_NEWS_1)
            .topNews2(UPDATED_TOP_NEWS_2)
            .topNews3(UPDATED_TOP_NEWS_3)
            .topNews4(UPDATED_TOP_NEWS_4)
            .topNews5(UPDATED_TOP_NEWS_5)
            .latestNews1(UPDATED_LATEST_NEWS_1)
            .latestNews2(UPDATED_LATEST_NEWS_2)
            .latestNews3(UPDATED_LATEST_NEWS_3)
            .latestNews4(UPDATED_LATEST_NEWS_4)
            .latestNews5(UPDATED_LATEST_NEWS_5)
            .breakingNews1(UPDATED_BREAKING_NEWS_1)
            .recentPosts1(UPDATED_RECENT_POSTS_1)
            .recentPosts2(UPDATED_RECENT_POSTS_2)
            .recentPosts3(UPDATED_RECENT_POSTS_3)
            .recentPosts4(UPDATED_RECENT_POSTS_4)
            .featuredArticles1(UPDATED_FEATURED_ARTICLES_1)
            .featuredArticles2(UPDATED_FEATURED_ARTICLES_2)
            .featuredArticles3(UPDATED_FEATURED_ARTICLES_3)
            .featuredArticles4(UPDATED_FEATURED_ARTICLES_4)
            .featuredArticles5(UPDATED_FEATURED_ARTICLES_5)
            .featuredArticles6(UPDATED_FEATURED_ARTICLES_6)
            .featuredArticles7(UPDATED_FEATURED_ARTICLES_7)
            .featuredArticles8(UPDATED_FEATURED_ARTICLES_8)
            .featuredArticles9(UPDATED_FEATURED_ARTICLES_9)
            .featuredArticles10(UPDATED_FEATURED_ARTICLES_10)
            .popularNews1(UPDATED_POPULAR_NEWS_1)
            .popularNews2(UPDATED_POPULAR_NEWS_2)
            .popularNews3(UPDATED_POPULAR_NEWS_3)
            .popularNews4(UPDATED_POPULAR_NEWS_4)
            .popularNews5(UPDATED_POPULAR_NEWS_5)
            .popularNews6(UPDATED_POPULAR_NEWS_6)
            .popularNews7(UPDATED_POPULAR_NEWS_7)
            .popularNews8(UPDATED_POPULAR_NEWS_8)
            .weeklyNews1(UPDATED_WEEKLY_NEWS_1)
            .weeklyNews2(UPDATED_WEEKLY_NEWS_2)
            .weeklyNews3(UPDATED_WEEKLY_NEWS_3)
            .weeklyNews4(UPDATED_WEEKLY_NEWS_4)
            .newsFeeds1(UPDATED_NEWS_FEEDS_1)
            .newsFeeds2(UPDATED_NEWS_FEEDS_2)
            .newsFeeds3(UPDATED_NEWS_FEEDS_3)
            .newsFeeds4(UPDATED_NEWS_FEEDS_4)
            .newsFeeds5(UPDATED_NEWS_FEEDS_5)
            .newsFeeds6(UPDATED_NEWS_FEEDS_6)
            .usefulLinks1(UPDATED_USEFUL_LINKS_1)
            .usefulLinks2(UPDATED_USEFUL_LINKS_2)
            .usefulLinks3(UPDATED_USEFUL_LINKS_3)
            .usefulLinks4(UPDATED_USEFUL_LINKS_4)
            .usefulLinks5(UPDATED_USEFUL_LINKS_5)
            .usefulLinks6(UPDATED_USEFUL_LINKS_6)
            .recentVideos1(UPDATED_RECENT_VIDEOS_1)
            .recentVideos2(UPDATED_RECENT_VIDEOS_2)
            .recentVideos3(UPDATED_RECENT_VIDEOS_3)
            .recentVideos4(UPDATED_RECENT_VIDEOS_4)
            .recentVideos5(UPDATED_RECENT_VIDEOS_5)
            .recentVideos6(UPDATED_RECENT_VIDEOS_6);
        FrontpageconfigDTO frontpageconfigDTO = frontpageconfigMapper.toDto(updatedFrontpageconfig);

        restFrontpageconfigMockMvc.perform(put("/api/frontpageconfigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frontpageconfigDTO)))
            .andExpect(status().isOk());

        // Validate the Frontpageconfig in the database
        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeUpdate);
        Frontpageconfig testFrontpageconfig = frontpageconfigList.get(frontpageconfigList.size() - 1);
        assertThat(testFrontpageconfig.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testFrontpageconfig.getTopNews1()).isEqualTo(UPDATED_TOP_NEWS_1);
        assertThat(testFrontpageconfig.getTopNews2()).isEqualTo(UPDATED_TOP_NEWS_2);
        assertThat(testFrontpageconfig.getTopNews3()).isEqualTo(UPDATED_TOP_NEWS_3);
        assertThat(testFrontpageconfig.getTopNews4()).isEqualTo(UPDATED_TOP_NEWS_4);
        assertThat(testFrontpageconfig.getTopNews5()).isEqualTo(UPDATED_TOP_NEWS_5);
        assertThat(testFrontpageconfig.getLatestNews1()).isEqualTo(UPDATED_LATEST_NEWS_1);
        assertThat(testFrontpageconfig.getLatestNews2()).isEqualTo(UPDATED_LATEST_NEWS_2);
        assertThat(testFrontpageconfig.getLatestNews3()).isEqualTo(UPDATED_LATEST_NEWS_3);
        assertThat(testFrontpageconfig.getLatestNews4()).isEqualTo(UPDATED_LATEST_NEWS_4);
        assertThat(testFrontpageconfig.getLatestNews5()).isEqualTo(UPDATED_LATEST_NEWS_5);
        assertThat(testFrontpageconfig.getBreakingNews1()).isEqualTo(UPDATED_BREAKING_NEWS_1);
        assertThat(testFrontpageconfig.getRecentPosts1()).isEqualTo(UPDATED_RECENT_POSTS_1);
        assertThat(testFrontpageconfig.getRecentPosts2()).isEqualTo(UPDATED_RECENT_POSTS_2);
        assertThat(testFrontpageconfig.getRecentPosts3()).isEqualTo(UPDATED_RECENT_POSTS_3);
        assertThat(testFrontpageconfig.getRecentPosts4()).isEqualTo(UPDATED_RECENT_POSTS_4);
        assertThat(testFrontpageconfig.getFeaturedArticles1()).isEqualTo(UPDATED_FEATURED_ARTICLES_1);
        assertThat(testFrontpageconfig.getFeaturedArticles2()).isEqualTo(UPDATED_FEATURED_ARTICLES_2);
        assertThat(testFrontpageconfig.getFeaturedArticles3()).isEqualTo(UPDATED_FEATURED_ARTICLES_3);
        assertThat(testFrontpageconfig.getFeaturedArticles4()).isEqualTo(UPDATED_FEATURED_ARTICLES_4);
        assertThat(testFrontpageconfig.getFeaturedArticles5()).isEqualTo(UPDATED_FEATURED_ARTICLES_5);
        assertThat(testFrontpageconfig.getFeaturedArticles6()).isEqualTo(UPDATED_FEATURED_ARTICLES_6);
        assertThat(testFrontpageconfig.getFeaturedArticles7()).isEqualTo(UPDATED_FEATURED_ARTICLES_7);
        assertThat(testFrontpageconfig.getFeaturedArticles8()).isEqualTo(UPDATED_FEATURED_ARTICLES_8);
        assertThat(testFrontpageconfig.getFeaturedArticles9()).isEqualTo(UPDATED_FEATURED_ARTICLES_9);
        assertThat(testFrontpageconfig.getFeaturedArticles10()).isEqualTo(UPDATED_FEATURED_ARTICLES_10);
        assertThat(testFrontpageconfig.getPopularNews1()).isEqualTo(UPDATED_POPULAR_NEWS_1);
        assertThat(testFrontpageconfig.getPopularNews2()).isEqualTo(UPDATED_POPULAR_NEWS_2);
        assertThat(testFrontpageconfig.getPopularNews3()).isEqualTo(UPDATED_POPULAR_NEWS_3);
        assertThat(testFrontpageconfig.getPopularNews4()).isEqualTo(UPDATED_POPULAR_NEWS_4);
        assertThat(testFrontpageconfig.getPopularNews5()).isEqualTo(UPDATED_POPULAR_NEWS_5);
        assertThat(testFrontpageconfig.getPopularNews6()).isEqualTo(UPDATED_POPULAR_NEWS_6);
        assertThat(testFrontpageconfig.getPopularNews7()).isEqualTo(UPDATED_POPULAR_NEWS_7);
        assertThat(testFrontpageconfig.getPopularNews8()).isEqualTo(UPDATED_POPULAR_NEWS_8);
        assertThat(testFrontpageconfig.getWeeklyNews1()).isEqualTo(UPDATED_WEEKLY_NEWS_1);
        assertThat(testFrontpageconfig.getWeeklyNews2()).isEqualTo(UPDATED_WEEKLY_NEWS_2);
        assertThat(testFrontpageconfig.getWeeklyNews3()).isEqualTo(UPDATED_WEEKLY_NEWS_3);
        assertThat(testFrontpageconfig.getWeeklyNews4()).isEqualTo(UPDATED_WEEKLY_NEWS_4);
        assertThat(testFrontpageconfig.getNewsFeeds1()).isEqualTo(UPDATED_NEWS_FEEDS_1);
        assertThat(testFrontpageconfig.getNewsFeeds2()).isEqualTo(UPDATED_NEWS_FEEDS_2);
        assertThat(testFrontpageconfig.getNewsFeeds3()).isEqualTo(UPDATED_NEWS_FEEDS_3);
        assertThat(testFrontpageconfig.getNewsFeeds4()).isEqualTo(UPDATED_NEWS_FEEDS_4);
        assertThat(testFrontpageconfig.getNewsFeeds5()).isEqualTo(UPDATED_NEWS_FEEDS_5);
        assertThat(testFrontpageconfig.getNewsFeeds6()).isEqualTo(UPDATED_NEWS_FEEDS_6);
        assertThat(testFrontpageconfig.getUsefulLinks1()).isEqualTo(UPDATED_USEFUL_LINKS_1);
        assertThat(testFrontpageconfig.getUsefulLinks2()).isEqualTo(UPDATED_USEFUL_LINKS_2);
        assertThat(testFrontpageconfig.getUsefulLinks3()).isEqualTo(UPDATED_USEFUL_LINKS_3);
        assertThat(testFrontpageconfig.getUsefulLinks4()).isEqualTo(UPDATED_USEFUL_LINKS_4);
        assertThat(testFrontpageconfig.getUsefulLinks5()).isEqualTo(UPDATED_USEFUL_LINKS_5);
        assertThat(testFrontpageconfig.getUsefulLinks6()).isEqualTo(UPDATED_USEFUL_LINKS_6);
        assertThat(testFrontpageconfig.getRecentVideos1()).isEqualTo(UPDATED_RECENT_VIDEOS_1);
        assertThat(testFrontpageconfig.getRecentVideos2()).isEqualTo(UPDATED_RECENT_VIDEOS_2);
        assertThat(testFrontpageconfig.getRecentVideos3()).isEqualTo(UPDATED_RECENT_VIDEOS_3);
        assertThat(testFrontpageconfig.getRecentVideos4()).isEqualTo(UPDATED_RECENT_VIDEOS_4);
        assertThat(testFrontpageconfig.getRecentVideos5()).isEqualTo(UPDATED_RECENT_VIDEOS_5);
        assertThat(testFrontpageconfig.getRecentVideos6()).isEqualTo(UPDATED_RECENT_VIDEOS_6);
    }

    @Test
    @Transactional
    public void updateNonExistingFrontpageconfig() throws Exception {
        int databaseSizeBeforeUpdate = frontpageconfigRepository.findAll().size();

        // Create the Frontpageconfig
        FrontpageconfigDTO frontpageconfigDTO = frontpageconfigMapper.toDto(frontpageconfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFrontpageconfigMockMvc.perform(put("/api/frontpageconfigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frontpageconfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Frontpageconfig in the database
        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFrontpageconfig() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        int databaseSizeBeforeDelete = frontpageconfigRepository.findAll().size();

        // Delete the frontpageconfig
        restFrontpageconfigMockMvc.perform(delete("/api/frontpageconfigs/{id}", frontpageconfig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Frontpageconfig.class);
        Frontpageconfig frontpageconfig1 = new Frontpageconfig();
        frontpageconfig1.setId(1L);
        Frontpageconfig frontpageconfig2 = new Frontpageconfig();
        frontpageconfig2.setId(frontpageconfig1.getId());
        assertThat(frontpageconfig1).isEqualTo(frontpageconfig2);
        frontpageconfig2.setId(2L);
        assertThat(frontpageconfig1).isNotEqualTo(frontpageconfig2);
        frontpageconfig1.setId(null);
        assertThat(frontpageconfig1).isNotEqualTo(frontpageconfig2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FrontpageconfigDTO.class);
        FrontpageconfigDTO frontpageconfigDTO1 = new FrontpageconfigDTO();
        frontpageconfigDTO1.setId(1L);
        FrontpageconfigDTO frontpageconfigDTO2 = new FrontpageconfigDTO();
        assertThat(frontpageconfigDTO1).isNotEqualTo(frontpageconfigDTO2);
        frontpageconfigDTO2.setId(frontpageconfigDTO1.getId());
        assertThat(frontpageconfigDTO1).isEqualTo(frontpageconfigDTO2);
        frontpageconfigDTO2.setId(2L);
        assertThat(frontpageconfigDTO1).isNotEqualTo(frontpageconfigDTO2);
        frontpageconfigDTO1.setId(null);
        assertThat(frontpageconfigDTO1).isNotEqualTo(frontpageconfigDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(frontpageconfigMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(frontpageconfigMapper.fromId(null)).isNull();
    }
}
