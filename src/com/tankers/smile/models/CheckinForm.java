package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class CheckinForm extends BaseModel {
	@Key
	private long userId;

	@Key
	private long flagId;

	public CheckinForm() {
		super();
	}

	public CheckinForm(long userId, long flagId) {
		super();
		this.userId = userId;
		this.flagId = flagId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFlagId() {
		return flagId;
	}

	public void setFlagId(long flagId) {
		this.flagId = flagId;
	}

}
