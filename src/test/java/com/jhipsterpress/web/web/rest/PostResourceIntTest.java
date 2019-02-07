package com.jhipsterpress.web.web.rest;

import com.jhipsterpress.web.JhipsterpressApp;

import com.jhipsterpress.web.domain.Post;
import com.jhipsterpress.web.domain.Comment;
import com.jhipsterpress.web.domain.User;
import com.jhipsterpress.web.domain.Blog;
import com.jhipsterpress.web.domain.Tag;
import com.jhipsterpress.web.domain.Topic;
import com.jhipsterpress.web.repository.PostRepository;
import com.jhipsterpress.web.service.PostService;
import com.jhipsterpress.web.service.dto.PostDTO;
import com.jhipsterpress.web.service.mapper.PostMapper;
import com.jhipsterpress.web.web.rest.errors.ExceptionTranslator;
import com.jhipsterpress.web.service.dto.PostCriteria;
import com.jhipsterpress.web.service.PostQueryService;

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

/**
 * Test class for the PostResource REST controller.
 *
 * @see PostResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterpressApp.class)
public class PostResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PUBLICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_HEADLINE = "AAAAAAAAAA";
    private static final String UPDATED_HEADLINE = "BBBBBBBBBB";

    private static final String DEFAULT_LEADTEXT = "AAAAAAAAAA";
    private static final String UPDATED_LEADTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_BODYTEXT = "AAAAAAAAAA";
    private static final String UPDATED_BODYTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_QUOTE = "AAAAAAAAAA";
    private static final String UPDATED_QUOTE = "BBBBBBBBBB";

    private static final String DEFAULT_CONCLUSION = "AAAAAAAAAA";
    private static final String UPDATED_CONCLUSION = "BBBBBBBBBB";

    private static final String DEFAULT_LINK_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_LINK_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_LINK_URL = "AAAAAAAAAA";
    private static final String UPDATED_LINK_URL = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostService postService;

    @Autowired
    private PostQueryService postQueryService;

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

    private MockMvc restPostMockMvc;

    private Post post;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PostResource postResource = new PostResource(postService, postQueryService);
        this.restPostMockMvc = MockMvcBuilders.standaloneSetup(postResource)
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
    public static Post createEntity(EntityManager em) {
        Post post = new Post()
            .creationDate(DEFAULT_CREATION_DATE)
            .publicationDate(DEFAULT_PUBLICATION_DATE)
            .headline(DEFAULT_HEADLINE)
            .leadtext(DEFAULT_LEADTEXT)
            .bodytext(DEFAULT_BODYTEXT)
            .quote(DEFAULT_QUOTE)
            .conclusion(DEFAULT_CONCLUSION)
            .linkText(DEFAULT_LINK_TEXT)
            .linkURL(DEFAULT_LINK_URL)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        post.setUser(user);
        // Add required entity
        Blog blog = BlogResourceIntTest.createEntity(em);
        em.persist(blog);
        em.flush();
        post.setBlog(blog);
        return post;
    }

    @Before
    public void initTest() {
        post = createEntity(em);
    }

    @Test
    @Transactional
    public void createPost() throws Exception {
        int databaseSizeBeforeCreate = postRepository.findAll().size();

        // Create the Post
        PostDTO postDTO = postMapper.toDto(post);
        restPostMockMvc.perform(post("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(postDTO)))
            .andExpect(status().isCreated());

        // Validate the Post in the database
        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeCreate + 1);
        Post testPost = postList.get(postList.size() - 1);
        assertThat(testPost.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testPost.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
        assertThat(testPost.getHeadline()).isEqualTo(DEFAULT_HEADLINE);
        assertThat(testPost.getLeadtext()).isEqualTo(DEFAULT_LEADTEXT);
        assertThat(testPost.getBodytext()).isEqualTo(DEFAULT_BODYTEXT);
        assertThat(testPost.getQuote()).isEqualTo(DEFAULT_QUOTE);
        assertThat(testPost.getConclusion()).isEqualTo(DEFAULT_CONCLUSION);
        assertThat(testPost.getLinkText()).isEqualTo(DEFAULT_LINK_TEXT);
        assertThat(testPost.getLinkURL()).isEqualTo(DEFAULT_LINK_URL);
        assertThat(testPost.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testPost.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createPostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = postRepository.findAll().size();

        // Create the Post with an existing ID
        post.setId(1L);
        PostDTO postDTO = postMapper.toDto(post);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPostMockMvc.perform(post("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(postDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Post in the database
        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = postRepository.findAll().size();
        // set the field null
        post.setCreationDate(null);

        // Create the Post, which fails.
        PostDTO postDTO = postMapper.toDto(post);

        restPostMockMvc.perform(post("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(postDTO)))
            .andExpect(status().isBadRequest());

        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeadlineIsRequired() throws Exception {
        int databaseSizeBeforeTest = postRepository.findAll().size();
        // set the field null
        post.setHeadline(null);

        // Create the Post, which fails.
        PostDTO postDTO = postMapper.toDto(post);

        restPostMockMvc.perform(post("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(postDTO)))
            .andExpect(status().isBadRequest());

        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBodytextIsRequired() throws Exception {
        int databaseSizeBeforeTest = postRepository.findAll().size();
        // set the field null
        post.setBodytext(null);

        // Create the Post, which fails.
        PostDTO postDTO = postMapper.toDto(post);

        restPostMockMvc.perform(post("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(postDTO)))
            .andExpect(status().isBadRequest());

        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPosts() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList
        restPostMockMvc.perform(get("/api/posts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(post.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].publicationDate").value(hasItem(DEFAULT_PUBLICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].headline").value(hasItem(DEFAULT_HEADLINE.toString())))
            .andExpect(jsonPath("$.[*].leadtext").value(hasItem(DEFAULT_LEADTEXT.toString())))
            .andExpect(jsonPath("$.[*].bodytext").value(hasItem(DEFAULT_BODYTEXT.toString())))
            .andExpect(jsonPath("$.[*].quote").value(hasItem(DEFAULT_QUOTE.toString())))
            .andExpect(jsonPath("$.[*].conclusion").value(hasItem(DEFAULT_CONCLUSION.toString())))
            .andExpect(jsonPath("$.[*].linkText").value(hasItem(DEFAULT_LINK_TEXT.toString())))
            .andExpect(jsonPath("$.[*].linkURL").value(hasItem(DEFAULT_LINK_URL.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getPost() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get the post
        restPostMockMvc.perform(get("/api/posts/{id}", post.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(post.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.publicationDate").value(DEFAULT_PUBLICATION_DATE.toString()))
            .andExpect(jsonPath("$.headline").value(DEFAULT_HEADLINE.toString()))
            .andExpect(jsonPath("$.leadtext").value(DEFAULT_LEADTEXT.toString()))
            .andExpect(jsonPath("$.bodytext").value(DEFAULT_BODYTEXT.toString()))
            .andExpect(jsonPath("$.quote").value(DEFAULT_QUOTE.toString()))
            .andExpect(jsonPath("$.conclusion").value(DEFAULT_CONCLUSION.toString()))
            .andExpect(jsonPath("$.linkText").value(DEFAULT_LINK_TEXT.toString()))
            .andExpect(jsonPath("$.linkURL").value(DEFAULT_LINK_URL.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }

    @Test
    @Transactional
    public void getAllPostsByCreationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where creationDate equals to DEFAULT_CREATION_DATE
        defaultPostShouldBeFound("creationDate.equals=" + DEFAULT_CREATION_DATE);

        // Get all the postList where creationDate equals to UPDATED_CREATION_DATE
        defaultPostShouldNotBeFound("creationDate.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllPostsByCreationDateIsInShouldWork() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where creationDate in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultPostShouldBeFound("creationDate.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the postList where creationDate equals to UPDATED_CREATION_DATE
        defaultPostShouldNotBeFound("creationDate.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllPostsByCreationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where creationDate is not null
        defaultPostShouldBeFound("creationDate.specified=true");

        // Get all the postList where creationDate is null
        defaultPostShouldNotBeFound("creationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPostsByPublicationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where publicationDate equals to DEFAULT_PUBLICATION_DATE
        defaultPostShouldBeFound("publicationDate.equals=" + DEFAULT_PUBLICATION_DATE);

        // Get all the postList where publicationDate equals to UPDATED_PUBLICATION_DATE
        defaultPostShouldNotBeFound("publicationDate.equals=" + UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    public void getAllPostsByPublicationDateIsInShouldWork() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where publicationDate in DEFAULT_PUBLICATION_DATE or UPDATED_PUBLICATION_DATE
        defaultPostShouldBeFound("publicationDate.in=" + DEFAULT_PUBLICATION_DATE + "," + UPDATED_PUBLICATION_DATE);

        // Get all the postList where publicationDate equals to UPDATED_PUBLICATION_DATE
        defaultPostShouldNotBeFound("publicationDate.in=" + UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    public void getAllPostsByPublicationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where publicationDate is not null
        defaultPostShouldBeFound("publicationDate.specified=true");

        // Get all the postList where publicationDate is null
        defaultPostShouldNotBeFound("publicationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPostsByHeadlineIsEqualToSomething() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where headline equals to DEFAULT_HEADLINE
        defaultPostShouldBeFound("headline.equals=" + DEFAULT_HEADLINE);

        // Get all the postList where headline equals to UPDATED_HEADLINE
        defaultPostShouldNotBeFound("headline.equals=" + UPDATED_HEADLINE);
    }

    @Test
    @Transactional
    public void getAllPostsByHeadlineIsInShouldWork() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where headline in DEFAULT_HEADLINE or UPDATED_HEADLINE
        defaultPostShouldBeFound("headline.in=" + DEFAULT_HEADLINE + "," + UPDATED_HEADLINE);

        // Get all the postList where headline equals to UPDATED_HEADLINE
        defaultPostShouldNotBeFound("headline.in=" + UPDATED_HEADLINE);
    }

    @Test
    @Transactional
    public void getAllPostsByHeadlineIsNullOrNotNull() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where headline is not null
        defaultPostShouldBeFound("headline.specified=true");

        // Get all the postList where headline is null
        defaultPostShouldNotBeFound("headline.specified=false");
    }

    @Test
    @Transactional
    public void getAllPostsByLeadtextIsEqualToSomething() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where leadtext equals to DEFAULT_LEADTEXT
        defaultPostShouldBeFound("leadtext.equals=" + DEFAULT_LEADTEXT);

        // Get all the postList where leadtext equals to UPDATED_LEADTEXT
        defaultPostShouldNotBeFound("leadtext.equals=" + UPDATED_LEADTEXT);
    }

    @Test
    @Transactional
    public void getAllPostsByLeadtextIsInShouldWork() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where leadtext in DEFAULT_LEADTEXT or UPDATED_LEADTEXT
        defaultPostShouldBeFound("leadtext.in=" + DEFAULT_LEADTEXT + "," + UPDATED_LEADTEXT);

        // Get all the postList where leadtext equals to UPDATED_LEADTEXT
        defaultPostShouldNotBeFound("leadtext.in=" + UPDATED_LEADTEXT);
    }

    @Test
    @Transactional
    public void getAllPostsByLeadtextIsNullOrNotNull() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where leadtext is not null
        defaultPostShouldBeFound("leadtext.specified=true");

        // Get all the postList where leadtext is null
        defaultPostShouldNotBeFound("leadtext.specified=false");
    }

    @Test
    @Transactional
    public void getAllPostsByBodytextIsEqualToSomething() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where bodytext equals to DEFAULT_BODYTEXT
        defaultPostShouldBeFound("bodytext.equals=" + DEFAULT_BODYTEXT);

        // Get all the postList where bodytext equals to UPDATED_BODYTEXT
        defaultPostShouldNotBeFound("bodytext.equals=" + UPDATED_BODYTEXT);
    }

    @Test
    @Transactional
    public void getAllPostsByBodytextIsInShouldWork() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where bodytext in DEFAULT_BODYTEXT or UPDATED_BODYTEXT
        defaultPostShouldBeFound("bodytext.in=" + DEFAULT_BODYTEXT + "," + UPDATED_BODYTEXT);

        // Get all the postList where bodytext equals to UPDATED_BODYTEXT
        defaultPostShouldNotBeFound("bodytext.in=" + UPDATED_BODYTEXT);
    }

    @Test
    @Transactional
    public void getAllPostsByBodytextIsNullOrNotNull() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where bodytext is not null
        defaultPostShouldBeFound("bodytext.specified=true");

        // Get all the postList where bodytext is null
        defaultPostShouldNotBeFound("bodytext.specified=false");
    }

    @Test
    @Transactional
    public void getAllPostsByQuoteIsEqualToSomething() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where quote equals to DEFAULT_QUOTE
        defaultPostShouldBeFound("quote.equals=" + DEFAULT_QUOTE);

        // Get all the postList where quote equals to UPDATED_QUOTE
        defaultPostShouldNotBeFound("quote.equals=" + UPDATED_QUOTE);
    }

    @Test
    @Transactional
    public void getAllPostsByQuoteIsInShouldWork() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where quote in DEFAULT_QUOTE or UPDATED_QUOTE
        defaultPostShouldBeFound("quote.in=" + DEFAULT_QUOTE + "," + UPDATED_QUOTE);

        // Get all the postList where quote equals to UPDATED_QUOTE
        defaultPostShouldNotBeFound("quote.in=" + UPDATED_QUOTE);
    }

    @Test
    @Transactional
    public void getAllPostsByQuoteIsNullOrNotNull() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where quote is not null
        defaultPostShouldBeFound("quote.specified=true");

        // Get all the postList where quote is null
        defaultPostShouldNotBeFound("quote.specified=false");
    }

    @Test
    @Transactional
    public void getAllPostsByConclusionIsEqualToSomething() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where conclusion equals to DEFAULT_CONCLUSION
        defaultPostShouldBeFound("conclusion.equals=" + DEFAULT_CONCLUSION);

        // Get all the postList where conclusion equals to UPDATED_CONCLUSION
        defaultPostShouldNotBeFound("conclusion.equals=" + UPDATED_CONCLUSION);
    }

    @Test
    @Transactional
    public void getAllPostsByConclusionIsInShouldWork() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where conclusion in DEFAULT_CONCLUSION or UPDATED_CONCLUSION
        defaultPostShouldBeFound("conclusion.in=" + DEFAULT_CONCLUSION + "," + UPDATED_CONCLUSION);

        // Get all the postList where conclusion equals to UPDATED_CONCLUSION
        defaultPostShouldNotBeFound("conclusion.in=" + UPDATED_CONCLUSION);
    }

    @Test
    @Transactional
    public void getAllPostsByConclusionIsNullOrNotNull() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where conclusion is not null
        defaultPostShouldBeFound("conclusion.specified=true");

        // Get all the postList where conclusion is null
        defaultPostShouldNotBeFound("conclusion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPostsByLinkTextIsEqualToSomething() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where linkText equals to DEFAULT_LINK_TEXT
        defaultPostShouldBeFound("linkText.equals=" + DEFAULT_LINK_TEXT);

        // Get all the postList where linkText equals to UPDATED_LINK_TEXT
        defaultPostShouldNotBeFound("linkText.equals=" + UPDATED_LINK_TEXT);
    }

    @Test
    @Transactional
    public void getAllPostsByLinkTextIsInShouldWork() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where linkText in DEFAULT_LINK_TEXT or UPDATED_LINK_TEXT
        defaultPostShouldBeFound("linkText.in=" + DEFAULT_LINK_TEXT + "," + UPDATED_LINK_TEXT);

        // Get all the postList where linkText equals to UPDATED_LINK_TEXT
        defaultPostShouldNotBeFound("linkText.in=" + UPDATED_LINK_TEXT);
    }

    @Test
    @Transactional
    public void getAllPostsByLinkTextIsNullOrNotNull() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where linkText is not null
        defaultPostShouldBeFound("linkText.specified=true");

        // Get all the postList where linkText is null
        defaultPostShouldNotBeFound("linkText.specified=false");
    }

    @Test
    @Transactional
    public void getAllPostsByLinkURLIsEqualToSomething() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where linkURL equals to DEFAULT_LINK_URL
        defaultPostShouldBeFound("linkURL.equals=" + DEFAULT_LINK_URL);

        // Get all the postList where linkURL equals to UPDATED_LINK_URL
        defaultPostShouldNotBeFound("linkURL.equals=" + UPDATED_LINK_URL);
    }

    @Test
    @Transactional
    public void getAllPostsByLinkURLIsInShouldWork() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where linkURL in DEFAULT_LINK_URL or UPDATED_LINK_URL
        defaultPostShouldBeFound("linkURL.in=" + DEFAULT_LINK_URL + "," + UPDATED_LINK_URL);

        // Get all the postList where linkURL equals to UPDATED_LINK_URL
        defaultPostShouldNotBeFound("linkURL.in=" + UPDATED_LINK_URL);
    }

    @Test
    @Transactional
    public void getAllPostsByLinkURLIsNullOrNotNull() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        // Get all the postList where linkURL is not null
        defaultPostShouldBeFound("linkURL.specified=true");

        // Get all the postList where linkURL is null
        defaultPostShouldNotBeFound("linkURL.specified=false");
    }

    @Test
    @Transactional
    public void getAllPostsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        Comment comment = CommentResourceIntTest.createEntity(em);
        em.persist(comment);
        em.flush();
        post.addComment(comment);
        postRepository.saveAndFlush(post);
        Long commentId = comment.getId();

        // Get all the postList where comment equals to commentId
        defaultPostShouldBeFound("commentId.equals=" + commentId);

        // Get all the postList where comment equals to commentId + 1
        defaultPostShouldNotBeFound("commentId.equals=" + (commentId + 1));
    }


    @Test
    @Transactional
    public void getAllPostsByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        post.setUser(user);
        postRepository.saveAndFlush(post);
        Long userId = user.getId();

        // Get all the postList where user equals to userId
        defaultPostShouldBeFound("userId.equals=" + userId);

        // Get all the postList where user equals to userId + 1
        defaultPostShouldNotBeFound("userId.equals=" + (userId + 1));
    }


    @Test
    @Transactional
    public void getAllPostsByBlogIsEqualToSomething() throws Exception {
        // Initialize the database
        Blog blog = BlogResourceIntTest.createEntity(em);
        em.persist(blog);
        em.flush();
        post.setBlog(blog);
        postRepository.saveAndFlush(post);
        Long blogId = blog.getId();

        // Get all the postList where blog equals to blogId
        defaultPostShouldBeFound("blogId.equals=" + blogId);

        // Get all the postList where blog equals to blogId + 1
        defaultPostShouldNotBeFound("blogId.equals=" + (blogId + 1));
    }


    @Test
    @Transactional
    public void getAllPostsByTagIsEqualToSomething() throws Exception {
        // Initialize the database
        Tag tag = TagResourceIntTest.createEntity(em);
        em.persist(tag);
        em.flush();
        post.addTag(tag);
        postRepository.saveAndFlush(post);
        Long tagId = tag.getId();

        // Get all the postList where tag equals to tagId
        defaultPostShouldBeFound("tagId.equals=" + tagId);

        // Get all the postList where tag equals to tagId + 1
        defaultPostShouldNotBeFound("tagId.equals=" + (tagId + 1));
    }


    @Test
    @Transactional
    public void getAllPostsByTopicIsEqualToSomething() throws Exception {
        // Initialize the database
        Topic topic = TopicResourceIntTest.createEntity(em);
        em.persist(topic);
        em.flush();
        post.addTopic(topic);
        postRepository.saveAndFlush(post);
        Long topicId = topic.getId();

        // Get all the postList where topic equals to topicId
        defaultPostShouldBeFound("topicId.equals=" + topicId);

        // Get all the postList where topic equals to topicId + 1
        defaultPostShouldNotBeFound("topicId.equals=" + (topicId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPostShouldBeFound(String filter) throws Exception {
        restPostMockMvc.perform(get("/api/posts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(post.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].publicationDate").value(hasItem(DEFAULT_PUBLICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].headline").value(hasItem(DEFAULT_HEADLINE)))
            .andExpect(jsonPath("$.[*].leadtext").value(hasItem(DEFAULT_LEADTEXT)))
            .andExpect(jsonPath("$.[*].bodytext").value(hasItem(DEFAULT_BODYTEXT)))
            .andExpect(jsonPath("$.[*].quote").value(hasItem(DEFAULT_QUOTE)))
            .andExpect(jsonPath("$.[*].conclusion").value(hasItem(DEFAULT_CONCLUSION)))
            .andExpect(jsonPath("$.[*].linkText").value(hasItem(DEFAULT_LINK_TEXT)))
            .andExpect(jsonPath("$.[*].linkURL").value(hasItem(DEFAULT_LINK_URL)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));

        // Check, that the count call also returns 1
        restPostMockMvc.perform(get("/api/posts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPostShouldNotBeFound(String filter) throws Exception {
        restPostMockMvc.perform(get("/api/posts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPostMockMvc.perform(get("/api/posts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPost() throws Exception {
        // Get the post
        restPostMockMvc.perform(get("/api/posts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePost() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        int databaseSizeBeforeUpdate = postRepository.findAll().size();

        // Update the post
        Post updatedPost = postRepository.findById(post.getId()).get();
        // Disconnect from session so that the updates on updatedPost are not directly saved in db
        em.detach(updatedPost);
        updatedPost
            .creationDate(UPDATED_CREATION_DATE)
            .publicationDate(UPDATED_PUBLICATION_DATE)
            .headline(UPDATED_HEADLINE)
            .leadtext(UPDATED_LEADTEXT)
            .bodytext(UPDATED_BODYTEXT)
            .quote(UPDATED_QUOTE)
            .conclusion(UPDATED_CONCLUSION)
            .linkText(UPDATED_LINK_TEXT)
            .linkURL(UPDATED_LINK_URL)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        PostDTO postDTO = postMapper.toDto(updatedPost);

        restPostMockMvc.perform(put("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(postDTO)))
            .andExpect(status().isOk());

        // Validate the Post in the database
        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeUpdate);
        Post testPost = postList.get(postList.size() - 1);
        assertThat(testPost.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testPost.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
        assertThat(testPost.getHeadline()).isEqualTo(UPDATED_HEADLINE);
        assertThat(testPost.getLeadtext()).isEqualTo(UPDATED_LEADTEXT);
        assertThat(testPost.getBodytext()).isEqualTo(UPDATED_BODYTEXT);
        assertThat(testPost.getQuote()).isEqualTo(UPDATED_QUOTE);
        assertThat(testPost.getConclusion()).isEqualTo(UPDATED_CONCLUSION);
        assertThat(testPost.getLinkText()).isEqualTo(UPDATED_LINK_TEXT);
        assertThat(testPost.getLinkURL()).isEqualTo(UPDATED_LINK_URL);
        assertThat(testPost.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testPost.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingPost() throws Exception {
        int databaseSizeBeforeUpdate = postRepository.findAll().size();

        // Create the Post
        PostDTO postDTO = postMapper.toDto(post);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPostMockMvc.perform(put("/api/posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(postDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Post in the database
        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePost() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(post);

        int databaseSizeBeforeDelete = postRepository.findAll().size();

        // Delete the post
        restPostMockMvc.perform(delete("/api/posts/{id}", post.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Post> postList = postRepository.findAll();
        assertThat(postList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Post.class);
        Post post1 = new Post();
        post1.setId(1L);
        Post post2 = new Post();
        post2.setId(post1.getId());
        assertThat(post1).isEqualTo(post2);
        post2.setId(2L);
        assertThat(post1).isNotEqualTo(post2);
        post1.setId(null);
        assertThat(post1).isNotEqualTo(post2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PostDTO.class);
        PostDTO postDTO1 = new PostDTO();
        postDTO1.setId(1L);
        PostDTO postDTO2 = new PostDTO();
        assertThat(postDTO1).isNotEqualTo(postDTO2);
        postDTO2.setId(postDTO1.getId());
        assertThat(postDTO1).isEqualTo(postDTO2);
        postDTO2.setId(2L);
        assertThat(postDTO1).isNotEqualTo(postDTO2);
        postDTO1.setId(null);
        assertThat(postDTO1).isNotEqualTo(postDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(postMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(postMapper.fromId(null)).isNull();
    }
}
