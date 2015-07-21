package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class TOU extends BaseModel {
	@Key
	private long id;

	@Key
	private Text tou;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Text getTou() {
		return tou;
	}

	public void setTou(Text tou) {
		this.tou = tou;
	}

}
