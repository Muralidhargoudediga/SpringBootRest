package com.mdg.spring.rest.webservices.restfulwebservices.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdg.spring.rest.webservices.restfulwebservices.posts.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	
}
