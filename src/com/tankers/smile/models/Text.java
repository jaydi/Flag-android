package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class Text extends BaseModel {
	@Key
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
