package com.mdg.spring.rest.webservices.restfulwebservices.users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJpaController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@RequestMapping(path = "/jpa/users/{id}", method = RequestMethod.GET)
	public EntityModel<User> getUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("User with id : " + id + " is not found");
		}

		EntityModel<User> model = new EntityModel<User>(user.get());

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		model.add(linkTo.withRel("all-users"));
		return model;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity addUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getAllPostsForUser(@PathVariable(name = "id") Integer userId) {

		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			throw new UserNotFoundException("User with id : " + userId + " is not found");
		}
		return user.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/post")
	public ResponseEntity savePostForUser(@PathVariable(name = "id") Integer userId, @RequestBody Post post) {

		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			throw new UserNotFoundException("User with id : " + userId + " is not found");
		}

		post.setUser(user.get());

		postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri(); 

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
}
