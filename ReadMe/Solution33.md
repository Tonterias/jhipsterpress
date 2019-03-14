# Problem 33: Cascade deletion in Many2Many relationships


NOTE: JhipsterPress is changing its name to Springular, so be aware of it! I apologize for the inconvinience

Probably you have found trouble trying to delete a Post entity when you have many Tags and Topic entities that are shared by other Posts in a Many2Many relationship. You have found a database reference error. This is how I proceed: I modified the delete method in the PostServiceImpl. First, I've found the Post we want to delete, to create a new Array and iterate through it to delete its Tags or Topics before deleting. Then, save the post changes and delete.

NOTE that we are doing a tag.removePost(post); so other Post can still have the tag, not a post.removeTag(tag); that would remove the tag from all the Posts alltogether.

    /**
     * Delete the post by id, but deleting Many2Many relationships ans saving the entity before deleting it.
     *
     * @param id the id of the entity
     */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Post : {}", id);
		
		Optional<Post> postOpt = postRepository.findById(id);
		Post post = postOpt.get();
		
		ArrayList<Tag> arrayTags = new ArrayList<Tag>();
		arrayTags.addAll(post.getTags());
		Iterator<Tag> copyOfTags = arrayTags.iterator();
		while (copyOfTags.hasNext()) {
			Tag tag = copyOfTags.next();
			tag.removePost(post);
		}
		
		ArrayList<Topic> arrayTopics = new ArrayList<Topic>();
		arrayTopics.addAll(post.getTopics());
		Iterator<Topic> copyOfTopics = arrayTopics.iterator();
		while (copyOfTopics.hasNext()) {
			Topic topic = copyOfTopics.next();
			topic.removePost(post);
		}

		postRepository.save(post);

		postRepository.deleteById(id);
		postSearchRepository.deleteById(id);
    }
   

        