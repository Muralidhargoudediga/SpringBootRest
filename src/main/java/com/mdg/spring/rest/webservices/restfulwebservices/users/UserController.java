package com.mdg.spring.rest.webservices.restfulwebservices.users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mdg.spring.rest.webservices.restfulwebservices.posts.Post;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
	public EntityModel<User> getUser(@PathVariable int id) {
		User user = userService.getUser(id);
		
		if(user == null) {
			throw new UserNotFoundException("User with id : "+ id + " is not found");
		}
		
		EntityModel<User> model = new EntityModel<User>(user);
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		model.add(linkTo.withRel("all-users"));
		return model;
	}

	@PostMapping("/users")
	public ResponseEntity addUser(@Valid @RequestBody User user) {
		User savedUser = userService.addUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> getAllPostsForUser(@PathVariable(name="id") Integer userId) {
		return userService.getAllPostsForUser(userId);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteUserById(id);
		
		if(user == null)
			throw new UserNotFoundException("User with id : "+ id + " is not found");
	}
}
