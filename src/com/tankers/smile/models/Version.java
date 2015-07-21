package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class Version extends BaseModel {
	@Key
	private String version;

	@Key
	private long createdAt;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

}
