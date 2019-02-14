# Problem 19: How to change DTOs to load attributes of other entities

Let's make a Comment to have its post.headline and the post.id with it. Add a @Mapping within the /src/main/java/com/jhipsterpress/web/service/dto/CommentDTO.java: Comment toDto(Comment comment) who is the responsible of converting Comment en CommentDTO

	@Mapper(componentModel = "spring", uses = {PostMapper.class})
	public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {
	
	    @Mapping(source = "post.id", target = "postId")
	    @Mapping(source = "post.headline", target = "postHeadline")
	    CommentDTO toDto(Comment comment);
	
	    @Mapping(source = "postId", target = "post")
	    Comment toEntity(CommentDTO commentDTO);
	
	    default Comment fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        Comment comment = new Comment();
	        comment.setId(id);
	        return comment;
	    }
	}
	

Inside CommentDTO.java create the attribute with its getter, settter & ToString (HashCode & Equals):

	private String postHeadline;

