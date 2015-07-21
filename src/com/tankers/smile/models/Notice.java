package com.tankers.smile.models;

import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public class Notice extends BaseModel {
	@Key
	@JsonString
	private Long createdAt;

	@Key
	private String message;

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
