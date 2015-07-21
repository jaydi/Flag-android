package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class ItemViewPair extends BaseModel {
	@Key
	private long id;
	@Key
	private long preId;
	@Key
	private long nextId;
	@Key
	private long createdAt;

	public ItemViewPair() {
		super();
	}

	public ItemViewPair(long preId, long nextId, long createdAt) {
		super();
		this.preId = preId;
		this.nextId = nextId;
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPreId() {
		return preId;
	}

	public void setPreId(long preId) {
		this.preId = preId;
	}

	public long getNextId() {
		return nextId;
	}

	public void setNextId(long nextId) {
		this.nextId = nextId;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

}
