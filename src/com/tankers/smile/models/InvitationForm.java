package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class InvitationForm extends BaseModel {
	@Key
	private long userId;

	@Key
	private String recoEmail;

	public InvitationForm() {
		super();
	}

	public InvitationForm(long userId, String recoEmail) {
		super();
		this.userId = userId;
		this.recoEmail = recoEmail;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getRecoEmail() {
		return recoEmail;
	}

	public void setRecoEmail(String recoEmail) {
		this.recoEmail = recoEmail;
	}

}
