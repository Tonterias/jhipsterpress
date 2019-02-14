# Problem 18: How to change DTOs to load entities

Let's say that you want to get the Posts (child) when you fetch a Blog (parent). First, you will have to change the @Cache to .NONE in the entities involved: Blog in the case for Posts or Posts in the case for Comments


	Blog.java
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Cache(usage = CacheConcurrencyStrategy.NONE)
	
Then, in the BlogDTO.java, you will have to create an atribute for the entity with getters and setter (even ToString if you want)

	BlogDTO.java
    private Set<PostDTO> posts;
	
Then you will have to include the class we want to use inside the @Mapper where needed.

In BlogMapper:

	@Mapper(componentModel = "spring", uses = {PostMapper.class})

In PostMapper:

	@Mapper(componentModel = "spring", uses = {BlogMapper.class, CommentMapper.class})
	
Check out if it is working using SWAGGER!