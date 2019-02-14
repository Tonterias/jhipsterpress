# Problem 2: List screen shows only user’s data:

Let's see how to show only the user's data in the component.PROFILE.html screen (list):

1.- We create and IF to see if the user is an ADMIN (show everything) or a USER (profileService.findByUserIsCurrentUser(pageable);) 
 
	src/main/java/com/raro/webh2memoryin/web/rest/ProfileResource.java
	
	/**
	 * GET  /profiles : get all the profiles.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of profiles in body
	 */    
	 @GetMapping("/profiles")
	 @Timed
	 public ResponseEntity<List<ProfileDTO>> getAllProfiles(Pageable pageable) {
	    log.debug("REST request to get a page of Profiles");
	    Page<ProfileDTO> page;
	      	if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
	       		page = profileService.findAll(pageable);
	       	} else {
	       		page = profileService.findByUserIsCurrentUser(pageable);
	       	}
	    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/profiles");
	    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	    }


If we are not using ServiceImpl we would go directly to the repository and do the same:

    @GetMapping("/profiles")
    @Timed
    public ResponseEntity<List<Profile>> getAllProfiles(Pageable pageable) {
        log.debug("REST request to get a page of Profiles");
        Page<Profile> page;
        
      	if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
       		page = profileRepository.findAll(pageable);
       	} else {
       		page = profileRepository.findByUserIsCurrentUser(pageable);
       	}

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/profiles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

2.- Go to the src/main/java/com/raro/web/service/impl/ProfileServiceImpl.java and create the method
	
	@Override
	@Transactional(readOnly = true)
	public Page<ProfileDTO> findByUserIsCurrentUser(Pageable pageable) {
		// TODO Auto-generated method stub
		//		return null;
		log.debug("Request to get all Users' Profiles");
		Page<Profile> result = profileRepository.findByUserIsCurrentUser(pageable);
	       return result.map(profileMapper::toDto);
	}


3.- Change the repository src/main/java/com/raro/web/repository/ProfileRepository.java:

	/**
	 * Spring Data JPA repository for the Profile entity.
	 */
	@SuppressWarnings("unused")
	@Repository
	public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	@Query("select profile from Profile profile where profile.user.login = ?#{principal.username}")
	Page<Profile> findByUserIsCurrentUser(Pageable pageable);
	
OR: 

	@Query("select profile from Profile profile where profile.user.login = ?#{principal.username} order by profile.date desc")
	Page<Profile> findByUserIsCurrentUser(Pageable pageable);


Without ServiceImpl:

    @Query("select profile from Profile profile where profile.user.login = ?#{principal.username}")
    Page<Profile> findByUserIsCurrentUser(Pageable pageable);


VIDEO HELP: https://www.youtube.com/watch?v=7okYI6ijUz8&t=11s&list=PLP8n25bNfkdcGTINO2ZJXnzvMGK3nm3zY&index=6 
