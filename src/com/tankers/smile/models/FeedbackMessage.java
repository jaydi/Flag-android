package com.tankers.smile.models;

import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public class FeedbackMessage extends BaseModel {
	@Key
	@JsonString
	private Long userId;
	
	@Key
	private String email;

	@Key
	private String message;

	public FeedbackMessage() {
		super();
	}

	public FeedbackMessage(Long userId, String email, String message) {
		super();
		this.userId = userId;
		this.email = email;
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
