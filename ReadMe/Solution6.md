# Problem 6: Modify DTOs with MapStruct

FrontPageConfig is the object that has all the Post that should appear on the HomePage. Creating relationships in JDL lead to database exhaustion, so we had to include the PostID and then modify the call to bring the Posts with the DTOs using MapStruct.

First, we change the FrontpageconfigResource that resolves the call with the frontpageconfig ID ("/frontpageconfigs/{id}/posts") by calling the frontpageconfigService.findOneIncludingPosts(id)

src/main/java/com/jhipsterpress2/web/web/rest/FrontpageconfigResource.java

    /**
    * GET  /frontpageconfigs/:id/posts : get the "id" frontpageconfig, including posts
    *
    * @param id the id of the frontpageconfigDTO to retrieve
    * @return the ResponseEntity with status 200 (OK) and with body the frontpageconfigDTO, or with status 404 (Not Found)
    */
    @GetMapping("/frontpageconfigs/{id}/posts")
    @Timed
    public ResponseEntity<CustomFrontpageconfigDTO> getFrontpageconfigIncludingPosts(@PathVariable Long id) {
        log.debug("REST request to get Frontpageconfig : {}", id);
        Optional<CustomFrontpageconfigDTO> frontpageconfigDTO = frontpageconfigService.findOneIncludingPosts(id);
        return ResponseUtil.wrapOrNotFound(frontpageconfigDTO);
    }


So we need to change our FrontpageconfigService to create a method that finds one Fontpageconfig ID and brings all the content of the Posts involved:
src/main/java/com/jhipsterpress2/web/service/FrontpageconfigService.java

	Optional<CustomFrontpageconfigDTO> findOneIncludingPosts(Long id);



Then we need to change the implementation FrontpageconfigServiceImpl so the constructor includes the customFrontpageconfigMapper and the call to findOneIncludingPosts with an ID that will fetch the data to the CustomFrontpageconfigDTO.
src/main/java/com/jhipsterpress2/web/service/impl/FrontpageconfigServiceImpl.java

	public FrontpageconfigServiceImpl(FrontpageconfigRepository frontpageconfigRepository, FrontpageconfigMapper frontpageconfigMapper, CustomFrontpageconfigMapper customFrontpageconfigMapper) {
        this.frontpageconfigRepository = frontpageconfigRepository;
        this.frontpageconfigMapper = frontpageconfigMapper;
        this.customFrontpageconfigMapper = customFrontpageconfigMapper;
    }

    /**
    * Get one frontpageconfig by id, including the posts.
    *
    * @param id the id of the entity
    * @return the entity
    */
    @Transactional(readOnly = true)
    public Optional<CustomFrontpageconfigDTO> findOneIncludingPosts(Long id) {
        log.debug("Request to get Frontpageconfig : {}", id);
        return frontpageconfigRepository.findById(id)
            .map(customFrontpageconfigMapper::toDto);
    }

Then we need to create the CustomFrontpageconfigMapper to map the information from our CustomFrontpageconfigDTO.
package com.jhipsterpress2.web.service.mapper;

	import com.jhipsterpress2.web.domain.Frontpageconfig;
	import com.jhipsterpress2.web.service.dto.CustomFrontpageconfigDTO;
	import org.mapstruct.Mapper;

	/**
	* Mapper for the entity Frontpageconfig and its DTO FrontpageconfigDTO.
	*/
	@Mapper(componentModel = "spring", uses = {FrontPagePostMapper.class})
	public interface CustomFrontpageconfigMapper extends EntityMapper<CustomFrontpageconfigDTO, Frontpageconfig> {

		default Frontpageconfig fromId(Long id) {
			if (id == null) {
				return null;
			}
			Frontpageconfig frontpageconfig = new Frontpageconfig();
			frontpageconfig.setId(id);
			return frontpageconfig;
		}
	}

And it is going to use the CustomFrontpageconfigDTO object that we need to create for this purpose. This new DTO, instead of having the private id for the Posts (as is the case in the regular FrontpageconfigDTO.java), it has private Post objects that will hold the information.
src/main/java/com/jhipsterpress2/web/service/dto/CustomFrontpageconfigDTO.java

    private Long topNews1;

    private Long topNews2;

and so on…

The FrontPagePostMapper maps the entity Post and fills it with Post information.

src/main/java/com/jhipsterpress2/web/service/mapper/FrontPagePostMapper.java

	package com.jhipsterpress2.web.service.mapper;

	import com.jhipsterpress2.web.domain.Post;
	import com.jhipsterpress2.web.repository.PostRepository;
	import org.mapstruct.Mapper;
	import org.springframework.beans.factory.annotation.Autowired;

	/**
	* Mapper for the entity Post and its DTO PostDTO.
	*/
	@Mapper(componentModel = "spring", uses = {})
	public abstract class FrontPagePostMapper {

		@Autowired
		private PostRepository postRepository;

		public Post postFromId(Long id) {
			if (id == null) {
				return null;
			}
			return postRepository.findById(id).orElse(new Post());
		}
		public Long idFromPost(Post post) {
			return post.getId();
		}
	}

Other examples at: PostsMapper (CustomTagMapper+CustomTagDTO y CustomTopicMapper+CustomTopicDTO), en ProfileMapper (Activities: CustomActivityMappers+CustomActivityDTO, Celebs: CustomCelebMappers+CustomCelebDTO, Interests: CustomInterestMappers+CustomInterestDTO).
