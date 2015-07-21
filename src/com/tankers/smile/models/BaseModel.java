package com.tankers.smile.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class BaseModel extends GenericJson {
	@Key
	protected int statusCode;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
