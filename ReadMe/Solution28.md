# Problem 28: Cascade deletion


You will have to the cascade = CascadeType.REMOVE in your annotation to delete a foreign key when you delete an entity. For example, once you delete a Post you will like to delete its comments too, right? Otherwise, you will get an Internal Server Error. Here's an example and you can find more in the other entities like Community (or User).

	package com.jhipsterpress.web.domain;
	...
	
	/**
	 * A Post.
	 */
	@Entity
	@Table(name = "post")
	@Cache(usage = CacheConcurrencyStrategy.NONE)
	//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public class Post implements Serializable {
	
	    private static final long serialVersionUID = 1L;
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	    @SequenceGenerator(name = "sequenceGenerator")
	    private Long id;
	
	    @NotNull
	    @Column(name = "creation_date", nullable = false)
	    private Instant creationDate;
	
	    @Column(name = "publication_date")
	    private Instant publicationDate;
	
	    @NotNull
	    @Size(min = 2, max = 100)
	    @Column(name = "headline", length = 100, nullable = false)
	    private String headline;
	
	    @Size(min = 2, max = 1000)
	    @Column(name = "leadtext", length = 1000)
	    private String leadtext;
	
	    @NotNull
	    @Size(min = 2, max = 65000)
	    @Column(name = "bodytext", length = 65000, nullable = false)
	    private String bodytext;
	
	    @Size(min = 2, max = 1000)
	    @Column(name = "quote", length = 1000)
	    private String quote;
	
	    @Size(min = 2, max = 2000)
	    @Column(name = "conclusion", length = 2000)
	    private String conclusion;
	
	    @Size(min = 2, max = 1000)
	    @Column(name = "link_text", length = 1000)
	    private String linkText;
	
	    @Size(min = 2, max = 1000)
	    @Column(name = "link_url", length = 1000)
	    private String linkURL;
	
	    @Lob
	    @Column(name = "image")
	    private byte[] image;
	
	    @Column(name = "image_content_type")
	    private String imageContentType;
	
	    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	    private Set<Comment> comments = new HashSet<>();
	
	    @ManyToOne(optional = false)
	    @NotNull
	    @JsonIgnoreProperties("posts")
	    private Blog blog;
	
	    @ManyToOne(optional = false)
	    @NotNull
	    @JsonIgnoreProperties("posts")
	    private Profile profile;
	
	    @ManyToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
	    @JsonIgnore
	    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	    private Set<Tag> tags = new HashSet<>();
	
	    @ManyToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
	    @JsonIgnore
	    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	    private Set<Topic> topics = new HashSet<>();