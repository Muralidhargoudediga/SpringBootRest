package com.mdg.spring.rest.webservices.restfulwebservices.posts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdg.spring.rest.webservices.restfulwebservices.users.User;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;
	private Date postedDate;
	private String message;
	@ManyToOne
	@JsonIgnore
	private User user;
	
	public Post() {}

	public Post(Integer id, Date postedDate, String message) {
		super();
		this.id = id;
		this.postedDate = postedDate;
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", postedDate=" + postedDate + ", message=" + message + "]";
	}
}
