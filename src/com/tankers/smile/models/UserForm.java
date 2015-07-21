package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class UserForm extends BaseModel {
	@Key
	private long id;

	@Key
	private String email;

	@Key
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
