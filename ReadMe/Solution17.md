# Problem 16: How to avoid unauthorized calls to the API

CASE 1: THE ENTITY IS DIRECTLY RELATED WITH THE USER ENTITY:

In the example at /src/main/java/com/raro/web/web/rest/ProfileResource.java we used the SecurityUtils within the IF to if (!SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) to return a not authorized alert.

	/**
	 * POST  /profiles : Create a new profile.
	 *
	 * @param profileDTO the profileDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new profileDTO, or with status 400 (Bad Request) if the profile has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/profiles")
	@Timed
	public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileDTO profileDTO) throws URISyntaxException {
	        if (!SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
	        	return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "Not-Authorized", "You need to be logged in to perform this action")).body(null);
	        }
		log.debug("REST request to save Profile : {}", profileDTO);
	        if (profileDTO.getId() != null) {
	            throw new BadRequestAlertException("A new profile cannot already have an ID", ENTITY_NAME, "idexists");
	        }
	        ProfileDTO result = profileService.save(profileDTO);
	        return ResponseEntity.created(new URI("/api/profiles/" + result.getId()))
	            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
	            .body(result);
	    }

You can also include other formulas:

	(!SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN) && 		!SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER))
	(!SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN) || !SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER))
	

CASE 2: THE ENTITY IS NOT DIRECTLY RELATED WITH THE USER ENTITY:

In the example at blog/src/main/java/org/jhipster/web/rest/BlogResource.java we used the SecurityUtils, but this time we need to get the login of the user who created the Blog entity to see if he is the one logged.

	/**
     * GET  /entries/:id : get the "id" entry.
     *
     * @param id the id of the entry to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entry, or with status 404 (Not Found)
     */
    @GetMapping("/entries/{id}")
    @Timed
    public ResponseEntity<Entry> getEntry(@PathVariable Long id) {
        log.debug("REST request to get Entry : {}", id);
        Entry entry = entryRepository.findOneWithEagerRelationships(id);
     // !!!!!! ATENCION CAMBIO DE MATT!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(!entry.getBlog().getUser().getLogin().equals(SecurityUtils.getCurrentUserLogin().get())) {
        	return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "Not-Authorized", "You need to be the owner in to perform this action")).body(null);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entry));
    }


  
		