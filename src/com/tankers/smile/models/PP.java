package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class PP extends BaseModel {
	@Key
	private long id;

	@Key
	private Text pp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Text getPp() {
		return pp;
	}

	public void setPp(Text pp) {
		this.pp = pp;
	}

}
