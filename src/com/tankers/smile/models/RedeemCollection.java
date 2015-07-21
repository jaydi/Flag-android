package com.tankers.smile.models;

import java.util.List;

import com.google.api.client.util.Key;

public class RedeemCollection extends BaseModel {
	@Key
	private List<Redeem> redeems;

	public RedeemCollection() {
		super();
	}

	public RedeemCollection(List<Redeem> redeems) {
		super();
		this.redeems = redeems;
	}

	public List<Redeem> getRedeems() {
		return redeems;
	}

	public void setRedeems(List<Redeem> redeems) {
		this.redeems = redeems;
	}
}
