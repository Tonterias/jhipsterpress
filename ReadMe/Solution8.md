# Problem 8: Entity with double relation to another entity

Let's imagine you want to register the user who are following (and who is followed) by another user:

You will end up with a OneToMany relationship similar to this:

	relationship OneToMany {
	    Profile{followed(id)} to Follow{followed(id)}
	    Profile{following(id)} to Follow{following(id)}
	}

Where you will register a Profile ID in a followed and a following fields in your database.

You can even have two relationships: one for the profile and another one if you want a Community to follow another community or another Profile (or viceversa).

	relationship OneToMany {
	    Community{cfollowed(id)} to Follow{cfollowed(id)}
	    Community{cfollowing(id)} to Follow{cfollowing(id)}
	    Profile{followed(id)} to Follow{followed(id)}
	    Profile{following(id)} to Follow{following(id)}
	}