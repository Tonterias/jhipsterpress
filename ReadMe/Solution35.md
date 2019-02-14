# Problem 35: * Many-to-Many: How the order of factors DOES alter the product:


As is explained in: https://www.jhipster.tech/managing-relationships/ there are two possibilities in a Many 2 Many relationship:

A Driver can drive many cars, but a Car can also have many drivers.

NOTE that the order of factors does alter the product and here you have an example:

CASE A:

	relationship ManyToMany {
    Tag{post(headline)} to Post{tag(tagName)}
    Topic{post(headline)} to Post{topic(topicName)}
	}

CASE B:

	relationship ManyToMany {
    Post{tag(tagName)} to Tag{post(headline)} 
    Post{topic(topicName)} to Topic{post(headline)} 
	}

In case A you will yourself into trouble when trying to delete a Post when the Tags and Topics have not been deleted first. That problem is double: first, because as you will see if you delete a Tag, you will delete all its records in all the Posts (and that is something that you will not want to do). So you will have to make changes at the PostServiceImpl

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
	//            post.removeTag(tag);
    }

    ArrayList<Topic> arrayTopics = new ArrayList<Topic>();
    arrayTopics.addAll(post.getTopics());
    Iterator<Topic> copyOfTopics = arrayTopics.iterator();
    while (copyOfTopics.hasNext()) {
        Topic topic = copyOfTopics.next();
        topic.removePost(post);
	//            post.removeTopic(topic);
    }

    postRepository.save(post);

    postRepository.deleteById(id);
    postSearchRepository.deleteById(id);
	}

So make sure you put them in the correct order (you need).
	