package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class User extends BaseModel {
	@Key
	private long id;

	@Key
	private String email;

	@Key
	private int reward;

	@Key
	private boolean isGuest;

	public User() {
		super();
	}

	public User(long id, boolean isGuest) {
		super();
		this.id = id;
		this.isGuest = isGuest;
	}

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

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public boolean isGuest() {
		return isGuest;
	}

	public void setGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}

	public boolean isFirst() {
		return id == 0;
	}
}
