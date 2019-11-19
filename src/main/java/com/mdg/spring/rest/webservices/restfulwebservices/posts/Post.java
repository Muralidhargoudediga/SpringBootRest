package com.mdg.spring.rest.webservices.restfulwebservices.posts;

import java.util.Date;

public class Post {
	private Integer id;
	private Date postedDate;
	private String message;
	
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
	
}
