package com.tankers.smile.models;

import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public class Like extends BaseModel {
	public static final int TYPE_SHOP = 1;
	public static final int TYPE_ITEM = 2;

	@Key
	private String id;

	@Key
	@JsonString
	private Long userId;

	@Key
	@JsonString
	private Long targetId;

	@Key
	private int type;

	public Like() {
		super();
	}

	public Like(Long userId, Long targetId, int type) {
		super();
		this.userId = userId;
		this.targetId = targetId;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
