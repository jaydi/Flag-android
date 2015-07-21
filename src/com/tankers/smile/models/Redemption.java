package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class Redemption extends BaseModel {
	@Key
	private long userId;

	@Key
	private long redeemId;

	public Redemption() {
		super();
	}

	public Redemption(long userId, long redeemId) {
		super();
		this.userId = userId;
		this.redeemId = redeemId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRedeemId() {
		return redeemId;
	}

	public void setRedeemId(long redeemId) {
		this.redeemId = redeemId;
	}

}
