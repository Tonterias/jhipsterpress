# Problem 20: How to change DTOs to load attributes of not related entities


We have a comment (Comment  Profile  User) that is not an Entity directly related to the User Entity and we need to know who is the author (userId) of that comment. So we can not get directly to the userID. 

Create an attribute userId in the /src/main/java/com/jhipsterpress/web/service/dto/CommentDTO.java

	public class CommentDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 65000)
    private String commentText;

    private Boolean isOffensive;

    private Long postId;

    private Long userId;

	
We modify the CommentMapper so the userid can be mapped with the UserMapper.class. Check the @Mapping(source = "post.id", target = "postId") and the @Mapping(source = "postId", target = "post")

		@Mapper(componentModel = "spring", uses = {PostMapper.class, ProfileMapper.class, UserMapper.class})
		public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {
	
	    @Mapping(source = "profile.user.id", target = "userId")
	    @Mapping(source = "post.id", target = "postId")
	    CommentDTO toDto(Comment comment);
	
	    @Mapping(source = "userId", target = "id")
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

You can now use it at the comment.component.html 

	<button *ngIf="owner === comment.userId || isAdmin == true"
	        type="submit"
	        [routerLink]="['/comment', comment.id, 'edit']"
	        class="btn btn-primary btn-sm">
	<fa-icon [icon]="'pencil-alt'"></fa-icon>
	     <span class="d-none d-md-inline" jhiTranslate="entity.action.edit">Edit</span>
	</button>

You can find more example in ProfileMapper, CalbumMapper, CommentsMapper, PostMapper,…