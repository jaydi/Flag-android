package com.tankers.smile.models;

import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public class InviteRewardValue extends BaseModel {
	@Key
	@JsonString
	private Long id;

	@Key
	private int value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
