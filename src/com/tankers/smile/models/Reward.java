package com.tankers.smile.models;

import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public class Reward extends BaseModel {
	public static final int TYPE_SHOP = 1;
	public static final int TYPE_ITEM = 2;
	public static final int TYPE_INVITATION = 3;
	public static final int TYPE_INVITED = 4;
	public static final int TYPE_REDEMPTION = 10;

	public static final int VALUE_INVITATION = 300;
	public static final int VALUE_INVITED = 100;

	@Key
	private String id;

	@Key
	private long userId;

	@Key
	private long targetId;

	@Key
	private String targetName;

	@Key
	private int type;

	@Key
	private int reward;

	@Key
	@JsonString
	private long createdAt;

	public Reward() {
		super();
	}

	public Reward(long userId, long targetId, String targetName, int type, int reward) {
		super();
		this.userId = userId;
		this.targetId = targetId;
		this.targetName = targetName;
		this.type = type;
		this.reward = reward;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTargetId() {
		return targetId;
	}

	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
}
