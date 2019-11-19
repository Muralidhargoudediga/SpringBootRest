package com.mdg.spring.rest.webservices.restfulwebservices.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mdg.spring.rest.webservices.restfulwebservices.posts.Post;

@Component
public class UserService {
	private static List<User> users = new ArrayList<User>();
	private static Map<Integer, List<Post>> userToPosts = new HashMap<Integer, List<Post>>();
	private static int postCounter = 1;
	private static int counter = 1;
	
	static {
		List<Post> posts = new ArrayList<Post>();
		posts.add(new Post(postCounter++, new Date(), "Enjoy at least one sunset per day!"));
		posts.add(new Post(postCounter++, new Date(), "They say don’t try this at home…so I went to my friends home!"));
	
		User user = new User(counter++, "Siva", new Date());
		userToPosts.put(user.getId(), posts);
		users.add(user);
		users.add(new User(counter++, "Pavan", new Date()));
		users.add(new User(counter++, "Kanta", new Date()));
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User getUser(int id) {
		for(User user : users) {
			if(user.getId() == id)
				return user;
		}
		
		return null;
	}
	
	public User addUser(User user) {
		if(user.getId() == null) {
			user.setId(counter++);
		}
		users.add(user);
		return user;
	}
	
	public List<Post> getAllPostsForUser(Integer userId) {
		return userToPosts.get(userId);
	}
	
	public Post getPostForUser(Integer userId, Integer postId) {
		List<Post> posts = userToPosts.get(userId);
		
		for(Post post : posts) {
			if(post.getId() == postId)
				return post;
		}
		
		return null;
	}
	
	public Post addPostForUser(Integer userId, Post post) {
		if(post.getId() == null)
			post.setId(postCounter++);
		
		List<Post> posts = userToPosts.get(userId);
		if(posts == null)
			posts = new ArrayList<Post>();
		posts.add(post);
		userToPosts.put(userId, posts);
		return post;	
	}
	
	public User deleteUserById(int id) {
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		
		return null;
	}
}
